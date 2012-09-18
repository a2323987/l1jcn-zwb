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

import java.util.Collection;

import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.serverpackets.S_WhoAmount;

public class L1Who implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1Who();
    }

    private L1Who() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final Collection<L1PcInstance> players = L1World.getInstance()
                    .getAllPlayers();
            final String amount = String.valueOf(players.size());
            final S_WhoAmount s_whoamount = new S_WhoAmount(amount);
            pc.sendPackets(s_whoamount);

            // オンラインのプレイヤーリストを表示
            if (arg.equalsIgnoreCase("all")) {
                pc.sendPackets(new S_SystemMessage("-- 线上玩家 --"));
                final StringBuffer buf = new StringBuffer();
                for (final L1PcInstance each : players) {
                    buf.append(each.getName());
                    buf.append(" / ");
                    if (buf.length() > 50) {
                        pc.sendPackets(new S_SystemMessage(buf.toString()));
                        buf.delete(0, buf.length() - 1);
                    }
                }
                if (buf.length() > 0) {
                    pc.sendPackets(new S_SystemMessage(buf.toString()));
                }
            }
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage("请输入: .who [all] 。"));
        }
    }
}
