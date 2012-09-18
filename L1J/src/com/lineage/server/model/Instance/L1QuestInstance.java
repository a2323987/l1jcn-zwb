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

import java.util.Timer;
import java.util.TimerTask;

import com.lineage.server.datatables.NpcTable;
import com.lineage.server.model.L1Attack;
import com.lineage.server.model.L1Quest;
import com.lineage.server.serverpackets.S_ChangeHeading;
import com.lineage.server.serverpackets.S_NPCTalkReturn;
import com.lineage.server.templates.L1Npc;

/**
 * 任务NPC控制项
 */
public class L1QuestInstance extends L1NpcInstance {

    public class RestMonitor extends TimerTask {
        @Override
        public void run() {
            L1QuestInstance.this.setRest(false);
        }
    }

    private static final long serialVersionUID = 1L;

    private static final long REST_MILLISEC = 10000;

    private static final Timer _restTimer = new Timer(true);

    private RestMonitor _monitor;

    public L1QuestInstance(final L1Npc template) {
        super(template);
    }

    @Override
    public void onAction(final L1PcInstance pc) {
        this.onAction(pc, 0);
    }

    @Override
    public void onAction(final L1PcInstance pc, final int skillId) {
        final L1Attack attack = new L1Attack(pc, this, skillId);
        if (attack.calcHit()) {
            attack.calcDamage();
            attack.calcStaffOfMana();
            attack.addPcPoisonAttack(pc, this);
            attack.addChaserAttack();
        }
        attack.action();
        attack.commit();
    }

    @Override
    public void onFinalAction(final L1PcInstance pc, final String action) {
        if (action.equalsIgnoreCase("start")) {
            final int npcId = this.getNpcTemplate().get_npcId();
            if (((npcId == 71092) || (npcId == 71093)) && pc.isKnight()
                    && (pc.getQuest().get_step(3) == 4)) {
                final L1Npc l1npc = NpcTable.getInstance().getTemplate(71093);
                new L1FollowerInstance(l1npc, this, pc);
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), ""));
            } else if ((npcId == 71094) && pc.isDarkelf()
                    && (pc.getQuest().get_step(4) == 2)) {
                final L1Npc l1npc = NpcTable.getInstance().getTemplate(71094);
                new L1FollowerInstance(l1npc, this, pc);
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), ""));
            } else if ((npcId == 71062)
                    && (pc.getQuest().get_step(L1Quest.QUEST_CADMUS) == 2)) {
                final L1Npc l1npc = NpcTable.getInstance().getTemplate(71062);
                new L1FollowerInstance(l1npc, this, pc);
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), ""));
            } else if ((npcId == 71075)
                    && (pc.getQuest().get_step(L1Quest.QUEST_LIZARD) == 1)) {
                final L1Npc l1npc = NpcTable.getInstance().getTemplate(71075);
                new L1FollowerInstance(l1npc, this, pc);
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), ""));
            } else if ((npcId == 70957) || (npcId == 81209)) {
                final L1Npc l1npc = NpcTable.getInstance().getTemplate(70957);
                new L1FollowerInstance(l1npc, this, pc);
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), ""));
            } else if ((npcId == 81350) && (pc.getQuest().get_step(4) == 3)) {
                final L1Npc l1npc = NpcTable.getInstance().getTemplate(81350);
                new L1FollowerInstance(l1npc, this, pc);
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), ""));
            }

        }
    }

    @Override
    public void onNpcAI() {
        final int npcId = this.getNpcTemplate().get_npcId();
        if (this.isAiRunning()) {
            return;
        }
        if ((npcId == 71075 // 疲惫的蜥蜴人战士
                )
                || (npcId == 70957 // 罗伊
                ) || (npcId == 81209 // 罗伊
                )) {
            return;
        }
        this.setActived(false);
        this.startAI();
    }

    @Override
    public void onTalkAction(final L1PcInstance pc) {
        final int pcX = pc.getX();
        final int pcY = pc.getY();
        final int npcX = this.getX();
        final int npcY = this.getY();

        if ((pcX == npcX) && (pcY < npcY)) {
            this.setHeading(0);
        } else if ((pcX > npcX) && (pcY < npcY)) {
            this.setHeading(1);
        } else if ((pcX > npcX) && (pcY == npcY)) {
            this.setHeading(2);
        } else if ((pcX > npcX) && (pcY > npcY)) {
            this.setHeading(3);
        } else if ((pcX == npcX) && (pcY > npcY)) {
            this.setHeading(4);
        } else if ((pcX < npcX) && (pcY > npcY)) {
            this.setHeading(5);
        } else if ((pcX < npcX) && (pcY == npcY)) {
            this.setHeading(6);
        } else if ((pcX < npcX) && (pcY < npcY)) {
            this.setHeading(7);
        }
        this.broadcastPacket(new S_ChangeHeading(this));

        final int npcId = this.getNpcTemplate().get_npcId();
        if ((npcId == 71092) || (npcId == 71093)) { // 调查员
            if (pc.isKnight() && (pc.getQuest().get_step(3) == 4)) {
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), "searcherk1"));
            } else {
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), "searcherk4"));
            }
        } else if (npcId == 71094) { // 安迪亚
            if (pc.isDarkelf() && (pc.getQuest().get_step(4) == 2)) {
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), "endiaq1"));
            } else {
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), "endiaq4"));
            }
        } else if (npcId == 71062) { // 卡米特
            if (pc.getQuest().get_step(L1Quest.QUEST_CADMUS) == 2) {
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), "kamit1b"));
            } else {
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), "kamit1"));
            }
        } else if (npcId == 71075) { // 疲惫的蜥蜴人战士
            if (pc.getQuest().get_step(L1Quest.QUEST_LIZARD) == 1) {
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), "llizard1b"));
            } else {
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), "llizard1a"));
            }
        } else if ((npcId == 70957) || (npcId == 81209)) { // 罗伊
            if (pc.getQuest().get_step(L1Quest.QUEST_ROI) != 1) {
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), "roi1"));
            } else {
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), "roi2"));
            }
        } else if (npcId == 81350) { // 迪嘉勒廷的女间谍
            if (pc.isElf() && (pc.getQuest().get_step(4) == 3)) {
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), "dspy2"));
            } else {
                pc.sendPackets(new S_NPCTalkReturn(this.getId(), "dspy1"));
            }
        }

        synchronized (this) {
            if (this._monitor != null) {
                this._monitor.cancel();
            }
            this.setRest(true);
            this._monitor = new RestMonitor();
            _restTimer.schedule(this._monitor, REST_MILLISEC);
        }
    }

}
