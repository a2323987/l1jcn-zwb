package com.lineage.server.model.skill;

import static com.lineage.server.model.skill.L1SkillId.ABSOLUTE_BARRIER;
import static com.lineage.server.model.skill.L1SkillId.EARTH_BIND;
import static com.lineage.server.model.skill.L1SkillId.FREEZING_BLIZZARD;
import static com.lineage.server.model.skill.L1SkillId.ICE_LANCE;

import com.lineage.server.ActionCodes;
import com.lineage.server.GeneralThreadPool;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1EffectInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.serverpackets.S_DoActionGFX;

/**
 * NPC 火牢伤害
 */
public class NpcFireDamage {

    class Damage implements Runnable {

        Damage() {
        }

        @Override
        public void run() {
            for (int findObjecCounts = 0; findObjecCounts < 8; findObjecCounts++) {
                try {
                    for (final L1Object objects : L1World.getInstance()
                            .getVisibleObjects(NpcFireDamage.this.user, 15)) { // 玩家视线范围15格

                        // 对PC
                        if (objects instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) objects;
                            if (pc.getLocation().equals(
                                    NpcFireDamage.this.fire.getLocation())) {

                                // 火牢伤害无效的状态
                                if (pc.isDead() || pc.hasSkillEffect(ICE_LANCE) // 冰矛围篱
                                        || pc.hasSkillEffect(ABSOLUTE_BARRIER) // 绝对屏障
                                        || pc.hasSkillEffect(FREEZING_BLIZZARD) // 冰雪飓风
                                        || pc.hasSkillEffect(EARTH_BIND)) {
                                    continue;
                                }

                                pc.sendPackets(new S_DoActionGFX(pc.getId(),
                                        ActionCodes.ACTION_Damage)); // 发送伤害动作
                                pc.broadcastPacket(new S_DoActionGFX(
                                        pc.getId(), ActionCodes.ACTION_Damage));
                                pc.receiveDamage(NpcFireDamage.this.user, 25, 0); // 伤害
                                pc.removeSkillEffect(66);
                            }
                        }

                        // 对 NPC
                        else if (objects instanceof L1NpcInstance) {

                            // 对宠物或召唤怪
                            if ((objects instanceof L1PetInstance)
                                    || (objects instanceof L1SummonInstance)) {
                                final L1NpcInstance npc = (L1NpcInstance) objects;
                                if (npc.getLocation().equals(
                                        NpcFireDamage.this.fire.getLocation())) {

                                    // 火牢伤害无效的状态
                                    if (npc.isDead()
                                            || (npc.getHiddenStatus() != 0 // 隐藏状态
                                            )
                                            || npc.hasSkillEffect(ICE_LANCE) // 冰矛围篱
                                            || npc.hasSkillEffect(ABSOLUTE_BARRIER) // 绝对屏障
                                            || npc.hasSkillEffect(FREEZING_BLIZZARD)// 冰雪飓风
                                            || npc.hasSkillEffect(EARTH_BIND)) {
                                        continue;
                                    }

                                    npc.broadcastPacket(new S_DoActionGFX(npc
                                            .getId(), ActionCodes.ACTION_Damage));
                                    npc.receiveDamage(NpcFireDamage.this.user,
                                            25); // 伤害
                                    npc.removeSkillEffect(66);
                                }
                            }
                        }
                    }
                    Thread.sleep(12 * 100); // 即时伤害 by a8889888 (暂停1200毫秒)
                } catch (final Exception ex) {
                    // 不抛出任何异常
                }
            }
        }
    }

    /** 使用者 */
    L1Character user = null;

    /** 火牢 */
    L1EffectInstance fire = null;

    /**
     * NPC 火牢伤害
     * 
     * @param cha
     * @param firewall
     */
    public NpcFireDamage(final L1Character cha, final L1NpcInstance firewall) {
        this.user = cha;
        this.fire = (L1EffectInstance) firewall;
    }

    public void onDamageAction() {
        final Damage damage_run = new Damage();
        GeneralThreadPool.getInstance().execute(damage_run);
    }

}
