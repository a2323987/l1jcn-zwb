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
package com.lineage.server.command.executor;

import java.util.StringTokenizer;

import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;

/**
 * GM指令：全体聊天
 */
public class L1Chat implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1Chat();
    }

    private L1Chat() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final StringTokenizer st = new StringTokenizer(arg);
            if (st.hasMoreTokens()) {
                final String flag = st.nextToken();
                String msg;
                if (flag.compareToIgnoreCase("on") == 0) {
                    L1World.getInstance().set_worldChatElabled(true);
                    msg = "开启全体聊天。";
                } else if (flag.compareToIgnoreCase("off") == 0) {
                    L1World.getInstance().set_worldChatElabled(false);
                    msg = "关闭全体聊天。";
                } else {
                    throw new Exception();
                }
                pc.sendPackets(new S_SystemMessage(msg));
            } else {
                String msg;
                if (L1World.getInstance().isWorldChatElabled()) {
                    msg = "全体聊天已开启。.chat off 能使其关闭。";
                } else {
                    msg = "全体聊天已关闭。.chat on 能使其开启。";
                }
                pc.sendPackets(new S_SystemMessage(msg));
            }
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage("请输入 " + cmdName + " [on|off]"));
        }
    }
}
