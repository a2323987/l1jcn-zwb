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
package com.lineage.server.model.Instance;

import java.util.logging.Logger;

import com.lineage.server.datatables.NPCTalkDataTable;
import com.lineage.server.model.L1NpcTalkData;
import com.lineage.server.serverpackets.S_NPCTalkReturn;
import com.lineage.server.templates.L1Npc;

/**
 * 
 */
public class L1RequestInstance extends L1NpcInstance {

    private static final long serialVersionUID = 1L;

    private static Logger _log = Logger.getLogger(L1RequestInstance.class
            .getName());

    public L1RequestInstance(final L1Npc template) {
        super(template);
    }

    public void doFinalAction(final L1PcInstance player) {

    }

    @Override
    public void onAction(final L1PcInstance player) {
        final int objid = this.getId();

        final L1NpcTalkData talking = NPCTalkDataTable.getInstance()
                .getTemplate(this.getNpcTemplate().get_npcId());

        if (talking != null) {
            if (player.getLawful() < -1000) { // プレイヤーがカオティック
                player.sendPackets(new S_NPCTalkReturn(talking, objid, 2));
            } else {
                player.sendPackets(new S_NPCTalkReturn(talking, objid, 1));
            }
        } else {
            _log.finest("npc没有动作id : " + objid);
        }
    }

    @Override
    public void onFinalAction(final L1PcInstance player, final String action) {

    }
}
