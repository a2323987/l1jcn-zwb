/**
 *                            License
 * THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS  
 * CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
 * THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW.  
 * ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE OR  
 * COPYRIGHT LAW IS PROHIBITED.
 * 
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND  
 * AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE  
 * MAY BE CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package com.lineage.server;

import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.command.L1Commands;
import com.lineage.server.command.executor.L1CommandExecutor;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.templates.L1Command;
import com.lineage.server.utils.collections.Maps;

// Referenced classes of package com.lineage.server:
// ClientThread, Shutdown, IpTable, MobTable,
// PolyTable, IdFactory
//

/**
 * GM指令
 */
public class GMCommands {

    private static Logger _log = Logger.getLogger(GMCommands.class.getName());

    private static GMCommands _instance;

    private static Map<Integer, String> _lastCommands = Maps.newMap();

    public static GMCommands getInstance() {
        if (_instance == null) {
            _instance = new GMCommands();
        }
        return _instance;
    }

    private GMCommands() {
    }

    private String complementClassName(final String className) {
        // 如果包涵 . 则认为他已经有完整路径，所以直接丢回去
        if (className.contains(".")) {
            return className;
        }

        // 如果没有点的话则自动帮他补完前面的路径
        return "com.lineage.server.command.executor." + className;
    }

    private boolean executeDatabaseCommand(final L1PcInstance pc,
            final String name, final String arg) {
        try {
            final L1Command command = L1Commands.get(name);
            if (command == null) {
                return false;
            }
            if (pc.getAccessLevel() < command.getLevel()) {
                pc.sendPackets(new S_ServerMessage(74, "指令 " + name)); // \f1%0%o
                                                                       // 无法使用。
                return true;
            }

            final Class<?> cls = Class.forName(this.complementClassName(command
                    .getExecutorClassName()));
            final L1CommandExecutor exe = (L1CommandExecutor) cls.getMethod(
                    "getInstance").invoke(null);
            exe.execute(pc, name, arg);
            _log.info(pc.getName() + " 使用 " + name + " " + arg + " 的指令。");
            return true;
        } catch (final Exception e) {
            _log.log(Level.SEVERE, " 错误的GM指令。", e);
        }
        return false;
    }

    /** 处理GM指令 */
    public void handleCommands(final L1PcInstance gm, final String cmdLine) {
        final StringTokenizer token = new StringTokenizer(cmdLine);
        // 命令，直到第一个空白，并在其后当作参数空格隔开
        final String cmd = token.nextToken();
        String param = "";
        while (token.hasMoreTokens()) {
            param = new StringBuilder(param).append(token.nextToken())
                    .append(' ').toString();
        }
        param = param.trim();

        // 将使用过的指令存起来
        if (this.executeDatabaseCommand(gm, cmd, param)) {
            if (!cmd.equalsIgnoreCase("r")) {
                _lastCommands.put(gm.getId(), cmdLine);
            }
            return;
        }
        if (cmd.equalsIgnoreCase("r")) {
            if (!_lastCommands.containsKey(gm.getId())) {
                gm.sendPackets(new S_ServerMessage(74, "指令 " + cmd)); // \f1%0%o
                                                                      // 无法使用。
                return;
            }
            this.redo(gm, param);
            return;
        }
        gm.sendPackets(new S_SystemMessage("指令 " + cmd + " 不存在。"));
    }

    private void redo(final L1PcInstance pc, final String arg) {
        try {
            final String lastCmd = _lastCommands.get(pc.getId());
            if (arg.isEmpty()) {
                pc.sendPackets(new S_SystemMessage("指令 " + lastCmd + " 重新执行。"));
                this.handleCommands(pc, lastCmd);
            } else {
                // 引数を变えて实行
                final StringTokenizer token = new StringTokenizer(lastCmd);
                final String cmd = token.nextToken() + " " + arg;
                pc.sendPackets(new S_SystemMessage("指令 " + cmd + " 执行。"));
                this.handleCommands(pc, cmd);
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            pc.sendPackets(new S_SystemMessage(".r 指令错误。"));
        }
    }
}
