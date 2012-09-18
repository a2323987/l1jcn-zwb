package com.lineage.server.model.skill.use;

import static com.lineage.server.model.skill.L1SkillId.BLOODY_SOUL;
import static com.lineage.server.model.skill.L1SkillId.BODY_TO_MIND;
import static com.lineage.server.model.skill.L1SkillId.BURNING_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.CALL_OF_NATURE;
import static com.lineage.server.model.skill.L1SkillId.CLEAR_MIND;
import static com.lineage.server.model.skill.L1SkillId.EARTH_BLESS;
import static com.lineage.server.model.skill.L1SkillId.EARTH_SKIN;
import static com.lineage.server.model.skill.L1SkillId.ELEMENTAL_FALL_DOWN;
import static com.lineage.server.model.skill.L1SkillId.ELEMENTAL_PROTECTION;
import static com.lineage.server.model.skill.L1SkillId.FIRE_BLESS;
import static com.lineage.server.model.skill.L1SkillId.FIRE_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.GREATER_ELEMENTAL;
import static com.lineage.server.model.skill.L1SkillId.IRON_SKIN;
import static com.lineage.server.model.skill.L1SkillId.LESSER_ELEMENTAL;
import static com.lineage.server.model.skill.L1SkillId.RESIST_ELEMENTAL;
import static com.lineage.server.model.skill.L1SkillId.RESIST_MAGIC;
import static com.lineage.server.model.skill.L1SkillId.RETURN_TO_NATURE;
import static com.lineage.server.model.skill.L1SkillId.STORM_EYE;
import static com.lineage.server.model.skill.L1SkillId.STORM_SHOT;
import static com.lineage.server.model.skill.L1SkillId.TELEPORT_TO_MATHER;
import static com.lineage.server.model.skill.L1SkillId.TRIPLE_ARROW;
import static com.lineage.server.model.skill.L1SkillId.WIND_SHACKLE;
import static com.lineage.server.model.skill.L1SkillId.WIND_SHOT;
import static com.lineage.server.model.skill.L1SkillId.WIND_WALK;

import com.lineage.Config;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.model.Instance.L1TowerInstance;
import com.lineage.server.serverpackets.S_Message_YN;
import com.lineage.server.serverpackets.S_OwnCharAttrDef;
import com.lineage.server.serverpackets.S_Paralysis;
import com.lineage.server.serverpackets.S_SPMR;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillBrave;
import com.lineage.server.serverpackets.S_SkillIconAura;
import com.lineage.server.serverpackets.S_SkillIconShield;
import com.lineage.server.serverpackets.S_SkillIconWindShackle;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.utils.Random;

/**
 * 技能效果:精灵
 * 
 * @author jrwz
 */
public class SkillEffectElf implements L1SkillEffect {

    @Override
    public int execute(final L1Character _user, final L1Character cha,
            final L1Character _target, final int skillId,
            final int _getBuffIconDuration, final int dmg) {

        L1PcInstance _player = null;
        if (_user instanceof L1PcInstance) {
            final L1PcInstance _pc = (L1PcInstance) _user;
            _player = _pc;
        }

        switch (skillId) {
            // 弱化属性
            case ELEMENTAL_FALL_DOWN:
                if (_user instanceof L1PcInstance) {
                    final int playerAttr = _player.getElfAttr();
                    final int i = -50;
                    if (playerAttr != 0) {
                        _player.sendPackets(new S_SkillSound(cha.getId(), 4396));
                        _player.broadcastPacket(new S_SkillSound(cha.getId(),
                                4396));
                    }
                    switch (playerAttr) {
                        case 0:
                            _player.sendPackets(new S_ServerMessage(79));
                            break;
                        case 1:
                            cha.addEarth(i);
                            cha.setAddAttrKind(1);
                            break;
                        case 2:
                            cha.addFire(i);
                            cha.setAddAttrKind(2);
                            break;
                        case 4:
                            cha.addWater(i);
                            cha.setAddAttrKind(4);
                            break;
                        case 8:
                            cha.addWind(i);
                            cha.setAddAttrKind(8);
                            break;
                        default:
                            break;
                    }
                }
                break;

            // 三重矢
            case TRIPLE_ARROW:
                boolean gfxcheck = false;
                final int[] bowGFX = { 37, 138, 3860, 3126, 3420, 2284, 3105,
                        3145, 3148, 3151, 3871, 4125, 2323, 3892, 3895, 3898,
                        3901, 4917, 4918, 4919, 4950, 6087, 6140, 6145, 6150,
                        6155, 6160, 6269, 6272, 6275, 6278, 6826, 6827, 6836,
                        6837, 6846, 6847, 6856, 6857, 6866, 6867, 6876, 6877,
                        6886, 6887, 8719, 8786, 8792, 8798, 8804, 8808, 8900,
                        8913, 9225, 9226, };
                final int playerGFX = _player.getTempCharGfx();
                for (final int gfx : bowGFX) {
                    if (playerGFX == gfx) {
                        gfxcheck = true;
                        break;
                    }
                }
                if (!gfxcheck) {
                    return 0;
                }

                final int amount = 3; // 次数
                for (int i = amount; i > 0; i--) {
                    _target.onAction(_player);
                }
                final S_SkillSound sound = new S_SkillSound(_player.getId(),
                        4394);
                _player.sendPackets(sound);
                _player.broadcastPacket(sound);
                break;

            // 魔法防御
            case RESIST_MAGIC:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMr(10);
                    pc.sendPackets(new S_SPMR(pc));
                }
                break;
            // 净化精神
            case CLEAR_MIND:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addWis((byte) 3);
                    pc.resetBaseMr();
                }
                break;
            // 属性防御
            case RESIST_ELEMENTAL:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addWind(10);
                    pc.addWater(10);
                    pc.addFire(10);
                    pc.addEarth(10);
                    pc.sendPackets(new S_OwnCharAttrDef(pc));
                }
                break;
            // 单属性防御
            case ELEMENTAL_PROTECTION:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    final int attr = pc.getElfAttr();
                    if (attr == 1) {
                        pc.addEarth(50);
                    } else if (attr == 2) {
                        pc.addFire(50);
                    } else if (attr == 4) {
                        pc.addWater(50);
                    } else if (attr == 8) {
                        pc.addWind(50);
                    }
                }
                break;
            // 心灵转换
            case BODY_TO_MIND:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.setCurrentMp(pc.getCurrentMp() + 2);
                }
                break;
            // 魂体转换
            case BLOODY_SOUL:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.setCurrentMp(pc.getCurrentMp() + 12);
                }
                break;

            // 火焰武器
            case FIRE_WEAPON:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addDmgup(4);
                    pc.sendPackets(new S_SkillIconAura(147,
                            _getBuffIconDuration));
                }
                break;
            // 烈炎气息
            case FIRE_BLESS:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addDmgup(4);
                    pc.sendPackets(new S_SkillIconAura(154,
                            _getBuffIconDuration));
                }
                break;
            // 烈炎武器
            case BURNING_WEAPON:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addDmgup(6);
                    pc.addHitup(3);
                    pc.sendPackets(new S_SkillIconAura(162,
                            _getBuffIconDuration));
                }
                break;
            // 风之神射
            case WIND_SHOT:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addBowHitup(6);
                    pc.sendPackets(new S_SkillIconAura(148,
                            _getBuffIconDuration));
                }
                break;
            // 暴风之眼
            case STORM_EYE:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addBowHitup(2);
                    pc.addBowDmgup(3);
                    pc.sendPackets(new S_SkillIconAura(155,
                            _getBuffIconDuration));
                }
                break;
            // 暴风神射
            case STORM_SHOT:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addBowDmgup(5);
                    pc.addBowHitup(-1);
                    pc.sendPackets(new S_SkillIconAura(165,
                            _getBuffIconDuration));
                }
                break;

            // 风之疾走
            case WIND_WALK:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.setBraveSpeed(4);
                    pc.sendPackets(new S_SkillBrave(pc.getId(), 4,
                            _getBuffIconDuration));
                    pc.broadcastPacket(new S_SkillBrave(pc.getId(), 4, 0));
                }
                break;

            // 风之枷锁
            case WIND_SHACKLE:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconWindShackle(pc.getId(),
                            _getBuffIconDuration));
                    pc.broadcastPacket(new S_SkillIconWindShackle(pc.getId(),
                            _getBuffIconDuration));
                }
                break;

            // 世界树的呼唤
            case TELEPORT_TO_MATHER:
                if (_user instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    if (pc.getMap().isEscapable() || pc.isGm()) {
                        L1Teleport.teleport(pc, 33051, 32337, (short) 4, 5,
                                true);
                    } else {
                        pc.sendPackets(new S_ServerMessage(276)); // \f1在此无法使用传送。
                        pc.sendPackets(new S_Paralysis(
                                S_Paralysis.TYPE_TELEPORT_UNLOCK, true));
                    }
                }
                break;

            // 召唤属性精灵、召唤强力属性精灵
            case LESSER_ELEMENTAL:
            case GREATER_ELEMENTAL:
                if (_user instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    final int attr = pc.getElfAttr();
                    if (attr != 0) { // 无属性でなければ实行
                        if (pc.getMap().isRecallPets()) {
                            int petcost = 0;
                            for (final L1NpcInstance petNpc : pc.getPetList()
                                    .values()) {
                                // 现在のペットコスト
                                petcost += petNpc.getPetcost();
                            }

                            if (petcost == 0) { // 1匹も所属NPCがいなければ实行
                                int summonid = 0;
                                int summons[];
                                if (skillId == LESSER_ELEMENTAL) { // 召唤属性精灵[地,火,水,风]
                                    summons = new int[] { 45306, 45303, 45304,
                                            45305 };
                                } else {
                                    // 召唤强力属性精灵[地,火,水,风]
                                    summons = new int[] { 81053, 81050, 81051,
                                            81052 };
                                }
                                int npcattr = 1;
                                for (int i = 0; i < summons.length; i++) {
                                    if (npcattr == attr) {
                                        summonid = summons[i];
                                        i = summons.length;
                                    }
                                    npcattr *= 2;
                                }
                                // 设定特殊的场合随机出现
                                if (summonid == 0) {

                                    final int k3 = Random.nextInt(4);
                                    summonid = summons[k3];
                                }

                                final L1Npc npcTemp = NpcTable.getInstance()
                                        .getTemplate(summonid);
                                final L1SummonInstance summon = new L1SummonInstance(
                                        npcTemp, pc);
                                summon.setPetcost(pc.getCha() + 7); // 精灵の他にはNPCを所属させられない
                            }
                        } else {
                            pc.sendPackets(new S_ServerMessage(79));
                        }
                    } else {
                        pc.sendPackets(new S_ServerMessage(79));
                    }
                }
                break;

            // 释放元素
            case RETURN_TO_NATURE:
                if (Config.RETURN_TO_NATURE
                        && (cha instanceof L1SummonInstance)) {
                    final L1SummonInstance summon = (L1SummonInstance) cha;
                    summon.broadcastPacket(new S_SkillSound(summon.getId(),
                            2245));
                    summon.returnToNature();
                } else {
                    if (_user instanceof L1PcInstance) {
                        _player.sendPackets(new S_ServerMessage(79));
                    }
                }
                break;

            // 大地防护
            case EARTH_SKIN:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addAc(-6);
                    pc.sendPackets(new S_SkillIconShield(6,
                            _getBuffIconDuration));
                }
                break;
            // 大地的祝福
            case EARTH_BLESS:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addAc(-7);
                    pc.sendPackets(new S_SkillIconShield(7,
                            _getBuffIconDuration));
                }
                break;
            // 钢铁防护
            case IRON_SKIN:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addAc(-10);
                    pc.sendPackets(new S_SkillIconShield(10,
                            _getBuffIconDuration));
                }
                break;

            // 生命呼唤
            case CALL_OF_NATURE:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    if (_player.getId() != pc.getId()) {
                        if (L1World.getInstance().getVisiblePlayer(pc, 0)
                                .size() > 0) {
                            for (final L1PcInstance visiblePc : L1World
                                    .getInstance().getVisiblePlayer(pc, 0)) {
                                if (!visiblePc.isDead()) {
                                    _player.sendPackets(new S_ServerMessage(592)); // 复活失败，因为这个位置已被占据。
                                    return 0;
                                }
                            }
                        }
                        if ((pc.getCurrentHp() == 0) && pc.isDead()) {
                            pc.setTempID(_player.getId());
                            pc.sendPackets(new S_Message_YN(322, "")); // 是否要复活？
                                                                       // (Y/N)
                        }
                    }
                } else if (cha instanceof L1NpcInstance) {
                    if (!(cha instanceof L1TowerInstance)) {
                        final L1NpcInstance npc = (L1NpcInstance) cha;
                        if (npc.getNpcTemplate().isCantResurrect()
                                && !(npc instanceof L1PetInstance)) {
                            return 0;
                        }
                        if ((npc instanceof L1PetInstance)
                                && (L1World.getInstance()
                                        .getVisiblePlayer(npc, 0).size() > 0)) {
                            for (final L1PcInstance visiblePc : L1World
                                    .getInstance().getVisiblePlayer(npc, 0)) {
                                if (!visiblePc.isDead()) {
                                    _player.sendPackets(new S_ServerMessage(592)); // 复活失败，因为这个位置已被占据。
                                    return 0;
                                }
                            }
                        }
                        if ((npc.getCurrentHp() == 0) && npc.isDead()) {
                            npc.resurrect(cha.getMaxHp());
                            npc.resurrect(cha.getMaxMp() / 100);
                            npc.setResurrect(true);
                            if ((npc instanceof L1PetInstance)) {
                                final L1PetInstance pet = (L1PetInstance) npc;
                                // 开始饱食度计时
                                pet.startFoodTimer(pet);
                                // 开始回血回魔
                                pet.startHpRegeneration();
                                pet.startMpRegeneration();
                            }
                        }
                    }
                }
                break;
        }
        return dmg;
    }

}
