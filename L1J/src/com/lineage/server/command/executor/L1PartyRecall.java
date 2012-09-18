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

import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.model.L1Party;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;

public class L1PartyRecall implements L1CommandExecutor {
    private static Logger _log = Logger
            .getLogger(L1PartyRecall.class.getName());

    public static L1CommandExecutor getInstance() {
        return new L1PartyRecall();
    }

    private L1PartyRecall() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        final L1PcInstance target = L1World.getInstance().getPlayer(arg);

        if (target != null) {
            final L1Party party = target.getParty();
            if (party != null) {
                final int x = pc.getX();
                final int y = pc.getY() + 2;
                final short map = pc.getMapId();
                final L1PcInstance[] players = party.getMembers();
                for (final L1PcInstance pc2 : players) {
                    try {
                        L1Teleport.teleport(pc2, x, y, map, 5, true);
                        pc2.sendPackets(new S_SystemMessage("您被传唤到GM身边。"));
                    } catch (final Exception e) {
                        _log.log(Level.SEVERE, "", e);
                    }
                }
            } else {
                pc.sendPackets(new S_SystemMessage("请输入要召唤的角色名称。"));
            }
        } else {
            pc.sendPackets(new S_SystemMessage("不在线上。"));
        }
    }
}
