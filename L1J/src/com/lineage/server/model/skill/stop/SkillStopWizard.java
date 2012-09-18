package com.lineage.server.model.skill.stop;

import static com.lineage.server.model.skill.L1SkillId.ABSOLUTE_BARRIER;
import static com.lineage.server.model.skill.L1SkillId.ADVANCE_SPIRIT;
import static com.lineage.server.model.skill.L1SkillId.BERSERKERS;
import static com.lineage.server.model.skill.L1SkillId.BLESSED_ARMOR;
import static com.lineage.server.model.skill.L1SkillId.BLESS_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.CURSE_BLIND;
import static com.lineage.server.model.skill.L1SkillId.CURSE_PARALYZE;
import static com.lineage.server.model.skill.L1SkillId.DARKNESS;
import static com.lineage.server.model.skill.L1SkillId.DISEASE;
import static com.lineage.server.model.skill.L1SkillId.ENCHANT_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.FOG_OF_SLEEPING;
import static com.lineage.server.model.skill.L1SkillId.FREEZING_BLIZZARD;
import static com.lineage.server.model.skill.L1SkillId.GREATER_HASTE;
import static com.lineage.server.model.skill.L1SkillId.HASTE;
import static com.lineage.server.model.skill.L1SkillId.HOLY_WALK;
import static com.lineage.server.model.skill.L1SkillId.ICE_LANCE;
import static com.lineage.server.model.skill.L1SkillId.LIGHT;
import static com.lineage.server.model.skill.L1SkillId.MASS_SLOW;
import static com.lineage.server.model.skill.L1SkillId.MEDITATION;
import static com.lineage.server.model.skill.L1SkillId.PHYSICAL_ENCHANT_DEX;
import static com.lineage.server.model.skill.L1SkillId.PHYSICAL_ENCHANT_STR;
import static com.lineage.server.model.skill.L1SkillId.SHAPE_CHANGE;
import static com.lineage.server.model.skill.L1SkillId.SHIELD;
import static com.lineage.server.model.skill.L1SkillId.SLOW;
import static com.lineage.server.model.skill.L1SkillId.WEAKNESS;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1PolyMorph;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.serverpackets.S_CurseBlind;
import com.lineage.server.serverpackets.S_Dexup;
import com.lineage.server.serverpackets.S_HPUpdate;
import com.lineage.server.serverpackets.S_MPUpdate;
import com.lineage.server.serverpackets.S_OwnCharStatus;
import com.lineage.server.serverpackets.S_Paralysis;
import com.lineage.server.serverpackets.S_Poison;
import com.lineage.server.serverpackets.S_SkillBrave;
import com.lineage.server.serverpackets.S_SkillHaste;
import com.lineage.server.serverpackets.S_SkillIconShield;
import com.lineage.server.serverpackets.S_Strup;

/**
 * 技能停止:法师
 * 
 * @author jrwz
 */
public class SkillStopWizard implements L1SkillStop {

    @Override
    public void stopSkill(final L1Character cha, final int skillId) {

        switch (skillId) {
            case LIGHT: // 法师魔法 (日光术)
                if (cha instanceof L1PcInstance) {
                    if (!cha.isInvisble()) {
                        final L1PcInstance pc = (L1PcInstance) cha;
                        pc.turnOnOffLight();
                    }
                }
                break;

            case SHIELD: // 法师魔法 (防护罩)
                cha.addAc(2);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconShield(5, 0));
                }
                break;

            case ENCHANT_WEAPON: // 法师魔法 (拟似魔法武器)
                cha.addDmgup(-2);
                break;

            case CURSE_BLIND: // 法师魔法 (暗盲咒术)
            case DARKNESS: // 法师魔法 (黑暗之影)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_CurseBlind(0));
                }
                break;

            case BLESSED_ARMOR: // 法师魔法 (铠甲护持)
                cha.addAc(3);
                break;

            case PHYSICAL_ENCHANT_DEX: // 法师魔法 (通畅气脉术)：DEX
                cha.addDex((byte) -5);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Dexup(pc, 5, 0));
                }
                break;

            case SLOW: // 法师魔法 (缓速)
            case MASS_SLOW: // 法师魔法 (集体缓速术)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
                    pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
                }
                cha.setMoveSpeed(0);
                break;

            case MEDITATION: // 法师魔法 (冥想术)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMpr(-5);
                }
                break;

            case CURSE_PARALYZE: // 法师魔法 (木乃伊的诅咒
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Poison(pc.getId(), 0));
                    pc.broadcastPacket(new S_Poison(pc.getId(), 0));
                    pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_PARALYSIS,
                            false));
                }
                break;

            case PHYSICAL_ENCHANT_STR: // 法师魔法 (体魄强健术)：STR
                cha.addStr((byte) -5);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Strup(pc, 5, 0));
                }
                break;

            case HASTE: // 法师魔法 (加速术)(强力加速术)
            case GREATER_HASTE:
                cha.setMoveSpeed(0);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
                    pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
                }
                break;

            case WEAKNESS: // 法师魔法 (弱化术)
                cha.addDmgup(5);
                cha.addHitup(1);
                break;

            case BLESS_WEAPON: // 法师魔法 (祝福魔法武器)
                cha.addDmgup(-2);
                cha.addHitup(-2);
                cha.addBowHitup(-2);
                break;

            case ICE_LANCE: // 法师魔法 (冰矛围篱)
            case FREEZING_BLIZZARD: // 法师魔法 (冰雪飓风)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Poison(pc.getId(), 0));
                    pc.broadcastPacket(new S_Poison(pc.getId(), 0));
                    pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_FREEZE,
                            false));
                } else if ((cha instanceof L1MonsterInstance)
                        || (cha instanceof L1SummonInstance)
                        || (cha instanceof L1PetInstance)) {
                    final L1NpcInstance npc = (L1NpcInstance) cha;
                    npc.broadcastPacket(new S_Poison(npc.getId(), 0));
                    npc.setParalyzed(false);
                }
                break;

            case HOLY_WALK: // 神圣疾走
                cha.setBraveSpeed(0);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
                    pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
                }
                break;

            case BERSERKERS: // 法师魔法 (狂暴术)
                cha.addAc(-10);
                cha.addDmgup(-5);
                cha.addHitup(-2);
                break;

            case DISEASE: // 法师魔法 (疾病术)
                cha.addDmgup(6);
                cha.addAc(-12);
                break;

            case FOG_OF_SLEEPING: // 法师魔法 (沉睡之雾)
                cha.setSleeped(false);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_SLEEP,
                            false));
                    pc.sendPackets(new S_OwnCharStatus(pc));
                }
                break;

            case SHAPE_CHANGE: // 法师魔法 (变形术)
                L1PolyMorph.undoPoly(cha);
                break;

            case ABSOLUTE_BARRIER: // 法师魔法 (绝对屏障)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.startHpRegeneration();
                    pc.startMpRegeneration();
                    pc.startHpRegenerationByDoll();
                    pc.startMpRegenerationByDoll();
                }
                break;

            case ADVANCE_SPIRIT: // 法师魔法 (灵魂升华)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-pc.getAdvenHp());
                    pc.addMaxMp(-pc.getAdvenMp());
                    pc.setAdvenHp(0);
                    pc.setAdvenMp(0);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                }
                break;
        }
    }

}
