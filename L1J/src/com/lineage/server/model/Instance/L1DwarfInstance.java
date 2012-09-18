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
import com.lineage.server.model.L1Attack;
import com.lineage.server.model.L1NpcTalkData;
import com.lineage.server.serverpackets.S_NPCTalkReturn;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Npc;

/**
 * 仓库控制项
 */
public class L1DwarfInstance extends L1NpcInstance {

    private static final long serialVersionUID = 1L;

    private static Logger _log = Logger.getLogger(L1DwarfInstance.class
            .getName());

    /**
     * @param template
     */
    public L1DwarfInstance(final L1Npc template) {
        super(template);
    }

    @Override
    public void onAction(final L1PcInstance pc) {
        this.onAction(pc, 0);
    }

    @Override
    public void onAction(final L1PcInstance pc, final int skillId) {
        final L1Attack attack = new L1Attack(pc, this, skillId);
        attack.calcHit();
        attack.action();
        attack.calcDamage();
        attack.addChaserAttack();
        attack.calcStaffOfMana();
        attack.addPcPoisonAttack(pc, this);
        attack.commit();
    }

    @Override
    public void onFinalAction(final L1PcInstance pc, final String Action) {
        if (Action.equalsIgnoreCase("retrieve")) {
            _log.finest("Retrive items in storage");
        } else if (Action.equalsIgnoreCase("retrieve-pledge")) {
            _log.finest("Retrive items in pledge storage");

            if (pc.getClanname().equalsIgnoreCase(" ")) {
                _log.finest("pc isnt in a pledge");
                final S_ServerMessage talk = new S_ServerMessage(
                        (S_ServerMessage.NO_PLEDGE), Action);
                pc.sendPackets(talk);
            } else {
                _log.finest("pc is in a pledge");
            }
        }
    }

    @Override
    public void onTalkAction(final L1PcInstance pc) {
        final int objid = this.getId();
        final L1NpcTalkData talking = NPCTalkDataTable.getInstance()
                .getTemplate(this.getNpcTemplate().get_npcId());
        final int npcId = this.getNpcTemplate().get_npcId();
        String htmlid = null;

        if (talking != null) {
            if (npcId == 60028) { // 艾尔
                if (!pc.isElf()) {
                    htmlid = "elCE1";
                }
            }

            if (htmlid != null) { // htmlidが指定されている場合
                pc.sendPackets(new S_NPCTalkReturn(objid, htmlid));
            } else {
                if (pc.getLevel() < 5) {
                    pc.sendPackets(new S_NPCTalkReturn(talking, objid, 2));
                } else {
                    pc.sendPackets(new S_NPCTalkReturn(talking, objid, 1));
                }
            }
        }
    }
}
