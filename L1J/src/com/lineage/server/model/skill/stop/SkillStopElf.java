package com.lineage.server.model.skill.stop;

import static com.lineage.server.model.skill.L1SkillId.BURNING_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.CLEAR_MIND;
import static com.lineage.server.model.skill.L1SkillId.EARTH_BIND;
import static com.lineage.server.model.skill.L1SkillId.EARTH_BLESS;
import static com.lineage.server.model.skill.L1SkillId.EARTH_SKIN;
import static com.lineage.server.model.skill.L1SkillId.ELEMENTAL_FALL_DOWN;
import static com.lineage.server.model.skill.L1SkillId.ELEMENTAL_PROTECTION;
import static com.lineage.server.model.skill.L1SkillId.ENTANGLE;
import static com.lineage.server.model.skill.L1SkillId.ERASE_MAGIC;
import static com.lineage.server.model.skill.L1SkillId.FIRE_BLESS;
import static com.lineage.server.model.skill.L1SkillId.FIRE_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.IRON_SKIN;
import static com.lineage.server.model.skill.L1SkillId.RESIST_ELEMENTAL;
import static com.lineage.server.model.skill.L1SkillId.RESIST_MAGIC;
import static com.lineage.server.model.skill.L1SkillId.STORM_EYE;
import static com.lineage.server.model.skill.L1SkillId.STORM_SHOT;
import static com.lineage.server.model.skill.L1SkillId.WIND_SHACKLE;
import static com.lineage.server.model.skill.L1SkillId.WIND_SHOT;
import static com.lineage.server.model.skill.L1SkillId.WIND_WALK;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.serverpackets.S_OwnCharAttrDef;
import com.lineage.server.serverpackets.S_Paralysis;
import com.lineage.server.serverpackets.S_Poison;
import com.lineage.server.serverpackets.S_SPMR;
import com.lineage.server.serverpackets.S_SkillBrave;
import com.lineage.server.serverpackets.S_SkillHaste;
import com.lineage.server.serverpackets.S_SkillIconAura;
import com.lineage.server.serverpackets.S_SkillIconShield;
import com.lineage.server.serverpackets.S_SkillIconWindShackle;

/**
 * 技能停止:精灵
 * 
 * @author jrwz
 */
public class SkillStopElf implements L1SkillStop {

    @Override
    public void stopSkill(final L1Character cha, final int skillId) {

        switch (skillId) {
            case EARTH_BLESS: // 妖精魔法 (大地的祝福)
                cha.addAc(7);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconShield(7, 0));
                }
                break;

            case RESIST_MAGIC: // 妖精魔法 (魔法防御)
                cha.addMr(-10);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SPMR(pc));
                }
                break;

            case CLEAR_MIND: // 妖精魔法 (精化精神)
                cha.addWis((byte) -3);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.resetBaseMr();
                }
                break;

            case RESIST_ELEMENTAL: // 妖精魔法 (属性防御)
                cha.addWind(-10);
                cha.addWater(-10);
                cha.addFire(-10);
                cha.addEarth(-10);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_OwnCharAttrDef(pc));
                }
                break;

            case ELEMENTAL_PROTECTION: // 妖精魔法 (单属性防御)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    final int attr = pc.getElfAttr();
                    switch (attr) {
                        case 1: // 地
                            cha.addEarth(-50);
                            break;

                        case 2: // 火
                            cha.addFire(-50);
                            break;

                        case 4: // 水
                            cha.addWater(-50);
                            break;

                        case 8: // 风
                            cha.addWind(-50);
                            break;

                        default:
                            break;
                    }
                    pc.sendPackets(new S_OwnCharAttrDef(pc));
                }
                break;

            case ELEMENTAL_FALL_DOWN: // 妖精魔法 (弱化属性)
                final int attr = cha.getAddAttrKind();
                final int i = 50;
                switch (attr) {
                    case 1:
                        cha.addEarth(i);
                        break;
                    case 2:
                        cha.addFire(i);
                        break;
                    case 4:
                        cha.addWater(i);
                        break;
                    case 8:
                        cha.addWind(i);
                        break;
                    default:
                        break;
                }
                cha.setAddAttrKind(0);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_OwnCharAttrDef(pc));
                }
                break;

            case IRON_SKIN: // 妖精魔法 (钢铁防护)
                cha.addAc(10);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconShield(10, 0));
                }
                break;

            case EARTH_SKIN: // 妖精魔法 (大地防护)
                cha.addAc(6);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconShield(6, 0));
                }
                break;

            case FIRE_WEAPON: // 妖精魔法 (火焰武器)
                cha.addDmgup(-4);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconAura(147, 0));
                }
                break;

            case FIRE_BLESS: // 妖精魔法 (烈炎气息)
                cha.addDmgup(-4);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconAura(154, 0));
                }
                break;

            case BURNING_WEAPON: // 妖精魔法 (烈炎武器)
                cha.addDmgup(-6);
                cha.addHitup(-3);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconAura(162, 0));
                }
                break;

            case WIND_SHOT: // 妖精魔法 (风之神射)
                cha.addBowHitup(-6);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconAura(148, 0));
                }
                break;

            case STORM_EYE: // 妖精魔法 (暴风之眼)
                cha.addBowHitup(-2);
                cha.addBowDmgup(-3);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconAura(155, 0));
                }
                break;

            case STORM_SHOT: // 妖精魔法 (暴风神射)
                cha.addBowDmgup(-5);
                cha.addBowHitup(1);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconAura(165, 0));
                }
                break;

            case ERASE_MAGIC: // 妖精魔法 (魔法消除)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconAura(152, 0));
                }
                break;

            case EARTH_BIND: // 妖精魔法 (大地屏障)
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

            case ENTANGLE: // 妖精魔法 (地面障碍)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
                    pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
                }
                cha.setMoveSpeed(0);
                break;

            case WIND_SHACKLE: // 风之枷锁
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconWindShackle(pc.getId(), 0));
                    pc.broadcastPacket(new S_SkillIconWindShackle(pc.getId(), 0));
                }
                break;

            case WIND_WALK: // 风之疾走
                cha.setBraveSpeed(0);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
                    pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
                }
                break;
        }
    }

}
