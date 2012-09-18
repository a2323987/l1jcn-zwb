package com.lineage.server.model.skill.stop;

import static com.lineage.server.model.skill.L1SkillId.BONE_BREAK_END;
import static com.lineage.server.model.skill.L1SkillId.BONE_BREAK_START;
import static com.lineage.server.model.skill.L1SkillId.CONCENTRATION;
import static com.lineage.server.model.skill.L1SkillId.ILLUSION_AVATAR;
import static com.lineage.server.model.skill.L1SkillId.ILLUSION_DIA_GOLEM;
import static com.lineage.server.model.skill.L1SkillId.ILLUSION_LICH;
import static com.lineage.server.model.skill.L1SkillId.ILLUSION_OGRE;
import static com.lineage.server.model.skill.L1SkillId.INSIGHT;
import static com.lineage.server.model.skill.L1SkillId.MIRROR_IMAGE;
import static com.lineage.server.model.skill.L1SkillId.PANIC;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CUBE_BALANCE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CUBE_IGNITION_TO_ALLY;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CUBE_IGNITION_TO_ENEMY;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CUBE_QUAKE_TO_ALLY;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CUBE_QUAKE_TO_ENEMY;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CUBE_SHOCK_TO_ALLY;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CUBE_SHOCK_TO_ENEMY;
import static com.lineage.server.model.skill.L1SkillId.STATUS_MR_REDUCTION_BY_CUBE_SHOCK;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.serverpackets.S_OwnCharAttrDef;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_Paralysis;
import com.lineage.server.serverpackets.S_SPMR;

/**
 * 技能停止:幻术师
 * 
 * @author jrwz
 */
public class SkillStopIllusionist implements L1SkillStop {

    @Override
    public void stopSkill(final L1Character cha, final int skillId) {

        switch (skillId) {
            case MIRROR_IMAGE: // 镜像
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addDodge((byte) -5); // 闪避率 - 50%
                    // 更新闪避率显示
                    pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
                }
                break;

            case ILLUSION_OGRE: // 幻觉：欧吉
                cha.addDmgup(-4);
                cha.addHitup(-4);
                cha.addBowDmgup(-4);
                cha.addBowHitup(-4);
                break;

            case ILLUSION_LICH: // 幻觉：巫妖
                cha.addSp(-2);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SPMR(pc));
                }
                break;

            case ILLUSION_DIA_GOLEM: // 幻觉：钻石高仑
                cha.addAc(20);
                break;

            case ILLUSION_AVATAR: // 幻觉：化身
                cha.addDmgup(-10);
                cha.addBowDmgup(-10);
                break;

            case INSIGHT: // 洞察
                cha.addStr((byte) -1);
                cha.addCon((byte) -1);
                cha.addDex((byte) -1);
                cha.addWis((byte) -1);
                cha.addInt((byte) -1);
                break;

            case PANIC: // 恐慌
                cha.addStr((byte) 1);
                cha.addCon((byte) 1);
                cha.addDex((byte) 1);
                cha.addWis((byte) 1);
                cha.addInt((byte) 1);
                break;

            case BONE_BREAK_START: // 骷髅毁坏 (发动)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_STUN, true));
                    pc.setSkillEffect(BONE_BREAK_END, 1 * 1000);
                } else if ((cha instanceof L1MonsterInstance)
                        || (cha instanceof L1SummonInstance)
                        || (cha instanceof L1PetInstance)) {
                    final L1NpcInstance npc = (L1NpcInstance) cha;
                    npc.setParalyzed(true);
                    npc.setSkillEffect(BONE_BREAK_END, 1 * 1000);
                }
                break;

            case BONE_BREAK_END: // 骷髅毁坏 (结束)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_STUN, false));
                } else if ((cha instanceof L1MonsterInstance)
                        || (cha instanceof L1SummonInstance)
                        || (cha instanceof L1PetInstance)) {
                    final L1NpcInstance npc = (L1NpcInstance) cha;
                    npc.setParalyzed(false);
                }
                break;

            case CONCENTRATION: // 专注
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMpr(-2);
                }
                break;

            case STATUS_CUBE_IGNITION_TO_ALLY: // 立方:燃烧 (友方)
                cha.addFire(-30);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_OwnCharAttrDef(pc));
                }
                break;

            case STATUS_CUBE_QUAKE_TO_ALLY: // 立方:地裂 (友方)
                cha.addEarth(-30);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_OwnCharAttrDef(pc));
                }
                break;

            case STATUS_CUBE_SHOCK_TO_ALLY: // 立方:冲击 (友方)
                cha.addWind(-30);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_OwnCharAttrDef(pc));
                }
                break;

            case STATUS_CUBE_IGNITION_TO_ENEMY: // 立方:燃烧 (敌方)
                // XXX
                break;

            case STATUS_CUBE_QUAKE_TO_ENEMY: // 立方:地裂 (敌方)
                // XXX
                break;

            case STATUS_CUBE_SHOCK_TO_ENEMY: // 立方:冲击 (敌方)
                // XXX
                break;

            case STATUS_MR_REDUCTION_BY_CUBE_SHOCK: // 由于 立方:冲击 (敌方)MR减少
                // cha.addMr(10);
                // if (cha instanceof L1PcInstance) {
                // L1PcInstance pc = (L1PcInstance) cha;
                // pc.sendPackets(new S_SPMR(pc));
                // }
                break;

            case STATUS_CUBE_BALANCE: // 立方:和谐
                // XXX
                break;
        }
    }

}
