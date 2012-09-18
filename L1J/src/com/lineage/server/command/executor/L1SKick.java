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

import java.util.logging.Logger;

import com.lineage.server.ClientThread;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Disconnect;
import com.lineage.server.serverpackets.S_SystemMessage;

public class L1SKick implements L1CommandExecutor {
    private static Logger _log = Logger.getLogger(L1SKick.class.getName());

    public static L1CommandExecutor getInstance() {
        return new L1SKick();
    }

    private L1SKick() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final L1PcInstance target = L1World.getInstance().getPlayer(arg);
            if (target != null) {
                pc.sendPackets(new S_SystemMessage((new StringBuilder())
                        .append(target.getName()).append("已被您强制踢除游戏。")
                        .toString()));
                // SKTへ移动させる
                target.setX(33080);
                target.setY(33392);
                target.setMap((short) 4);
                target.sendPackets(new S_Disconnect());
                final ClientThread targetClient = target.getNetConnection();
                targetClient.kick();
                _log.warning("GM的踢除指令使得(" + targetClient.getAccountName() + ":"
                        + targetClient.getHostname() + ")的连线被强制中断。");
            } else {
                pc.sendPackets(new S_SystemMessage("指定的ID不存在。"));
            }
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage("请输入: " + cmdName + " 玩家名称。"));
        }
    }
}
