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

import java.util.List;

import com.lineage.server.model.L1Attack;
import com.lineage.server.model.L1Character;
import com.lineage.server.serverpackets.S_ChangeHeading;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.utils.CalcExp;
import com.lineage.server.utils.collections.Lists;

/**
 * 木人控制项
 */
public class L1ScarecrowInstance extends L1NpcInstance {

    private static final long serialVersionUID = 1L;

    public L1ScarecrowInstance(final L1Npc template) {
        super(template);
    }

    public void doFinalAction() {
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

    public void onFinalAction() {

    }

    @Override
    public void onTalkAction(final L1PcInstance l1pcinstance) {

    }

    @Override
    public void receiveDamage(final L1Character attacker, final int damage) {
        if ((this.getCurrentHp() > 0) && !this.isDead()) {
            if (damage > 0) {
                if (this.getHeading() < 7) {
                    this.setHeading(this.getHeading() + 1);
                } else {
                    this.setHeading(0);
                }
                this.broadcastPacket(new S_ChangeHeading(this));

                if ((attacker instanceof L1PcInstance)) {
                    final L1PcInstance pc = (L1PcInstance) attacker;
                    pc.setPetTarget(this);

                    if (pc.getLevel() < 5) {
                        final List<L1Character> targetList = Lists.newList();
                        targetList.add(pc);
                        final List<Integer> hateList = Lists.newList();
                        hateList.add(1);
                        CalcExp.calcExp(pc, this.getId(), targetList, hateList,
                                this.getExp());
                    }
                }
            }
        }
    }
}
