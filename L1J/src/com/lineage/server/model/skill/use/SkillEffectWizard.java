package com.lineage.server.model.skill.use;

import static com.lineage.server.model.skill.L1SkillId.ABSOLUTE_BARRIER;
import static com.lineage.server.model.skill.L1SkillId.ADVANCE_SPIRIT;
import static com.lineage.server.model.skill.L1SkillId.AWAKEN_ANTHARAS;
import static com.lineage.server.model.skill.L1SkillId.AWAKEN_FAFURION;
import static com.lineage.server.model.skill.L1SkillId.AWAKEN_VALAKAS;
import static com.lineage.server.model.skill.L1SkillId.BERSERKERS;
import static com.lineage.server.model.skill.L1SkillId.BLESSED_ARMOR;
import static com.lineage.server.model.skill.L1SkillId.CANCELLATION;
import static com.lineage.server.model.skill.L1SkillId.COOKING_BEGIN;
import static com.lineage.server.model.skill.L1SkillId.COOKING_END;
import static com.lineage.server.model.skill.L1SkillId.COOKING_WONDER_DRUG;
import static com.lineage.server.model.skill.L1SkillId.COUNTER_BARRIER;
import static com.lineage.server.model.skill.L1SkillId.CREATE_ZOMBIE;
import static com.lineage.server.model.skill.L1SkillId.CURE_POISON;
import static com.lineage.server.model.skill.L1SkillId.CURSE_BLIND;
import static com.lineage.server.model.skill.L1SkillId.CURSE_PARALYZE;
import static com.lineage.server.model.skill.L1SkillId.CURSE_PARALYZE2;
import static com.lineage.server.model.skill.L1SkillId.CURSE_POISON;
import static com.lineage.server.model.skill.L1SkillId.DARKNESS;
import static com.lineage.server.model.skill.L1SkillId.DETECTION;
import static com.lineage.server.model.skill.L1SkillId.DISEASE;
import static com.lineage.server.model.skill.L1SkillId.EARTH_BIND;
import static com.lineage.server.model.skill.L1SkillId.ENCHANT_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.FOG_OF_SLEEPING;
import static com.lineage.server.model.skill.L1SkillId.FREEZING_BLIZZARD;
import static com.lineage.server.model.skill.L1SkillId.FREEZING_BREATH;
import static com.lineage.server.model.skill.L1SkillId.GREATER_RESURRECTION;
import static com.lineage.server.model.skill.L1SkillId.HOLY_WALK;
import static com.lineage.server.model.skill.L1SkillId.ICE_LANCE;
import static com.lineage.server.model.skill.L1SkillId.INVISIBILITY;
import static com.lineage.server.model.skill.L1SkillId.MEDITATION;
import static com.lineage.server.model.skill.L1SkillId.PHYSICAL_ENCHANT_DEX;
import static com.lineage.server.model.skill.L1SkillId.PHYSICAL_ENCHANT_STR;
import static com.lineage.server.model.skill.L1SkillId.REDUCTION_ARMOR;
import static com.lineage.server.model.skill.L1SkillId.REMOVE_CURSE;
import static com.lineage.server.model.skill.L1SkillId.RESURRECTION;
import static com.lineage.server.model.skill.L1SkillId.SHADOW_FANG;
import static com.lineage.server.model.skill.L1SkillId.SHAPE_CHANGE;
import static com.lineage.server.model.skill.L1SkillId.SHIELD;
import static com.lineage.server.model.skill.L1SkillId.SHOCK_STUN;
import static com.lineage.server.model.skill.L1SkillId.SKILLS_BEGIN;
import static com.lineage.server.model.skill.L1SkillId.SKILLS_END;
import static com.lineage.server.model.skill.L1SkillId.SOLID_CARRIAGE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_BEGIN;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CHAT_PROHIBITED;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CURSE_PARALYZED;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CURSE_PARALYZING;
import static com.lineage.server.model.skill.L1SkillId.STATUS_END;
import static com.lineage.server.model.skill.L1SkillId.STATUS_FLOATING_EYE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_FREEZE;
import static com.lineage.server.model.skill.L1SkillId.SUMMON_MONSTER;
import static com.lineage.server.model.skill.L1SkillId.TAMING_MONSTER;
import static com.lineage.server.model.skill.L1SkillId.WEAKNESS;
import static com.lineage.server.model.skill.L1SkillId.WEAK_ELEMENTAL;
import static com.lineage.server.model.skill.L1SkillId.WEAPON_BREAK;

import com.lineage.server.ActionCodes;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1CurseParalysis;
import com.lineage.server.model.L1PinkName;
import com.lineage.server.model.L1PolyMorph;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.model.Instance.L1TowerInstance;
import com.lineage.server.model.poison.L1DamagePoison;
import com.lineage.server.serverpackets.S_ChangeName;
import com.lineage.server.serverpackets.S_CharVisualUpdate;
import com.lineage.server.serverpackets.S_CurseBlind;
import com.lineage.server.serverpackets.S_Dexup;
import com.lineage.server.serverpackets.S_DoActionShop;
import com.lineage.server.serverpackets.S_HPUpdate;
import com.lineage.server.serverpackets.S_Invis;
import com.lineage.server.serverpackets.S_MPUpdate;
import com.lineage.server.serverpackets.S_Message_YN;
import com.lineage.server.serverpackets.S_NpcChangeShape;
import com.lineage.server.serverpackets.S_Paralysis;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_ShowPolyList;
import com.lineage.server.serverpackets.S_ShowSummonList;
import com.lineage.server.serverpackets.S_SkillBrave;
import com.lineage.server.serverpackets.S_SkillHaste;
import com.lineage.server.serverpackets.S_SkillIconShield;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.serverpackets.S_Strup;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.utils.Random;

/**
 * 技能效果:法师
 * 
 * @author jrwz
 */
public class SkillEffectWizard implements L1SkillEffect {

    /**
     * 不可取消
     * 
     * @param skillNum
     * @return
     */
    private static boolean isNotCancelable(final int skillNum) {
        return (skillNum == ENCHANT_WEAPON) || (skillNum == BLESSED_ARMOR)
                || (skillNum == ABSOLUTE_BARRIER)
                || (skillNum == ADVANCE_SPIRIT) || (skillNum == SHOCK_STUN)
                || (skillNum == SHADOW_FANG) || (skillNum == REDUCTION_ARMOR)
                || (skillNum == SOLID_CARRIAGE)
                || (skillNum == COUNTER_BARRIER)
                || (skillNum == AWAKEN_ANTHARAS)
                || (skillNum == AWAKEN_FAFURION)
                || (skillNum == AWAKEN_VALAKAS)
                || (skillNum == COOKING_WONDER_DRUG);
    }

    @Override
    public int execute(final L1Character _user, final L1Character cha,
            L1Character _target, final int skillId,
            final int _getBuffIconDuration, final int dmg) {

        L1PcInstance _player = null;
        if (_user instanceof L1PcInstance) {
            final L1PcInstance _pc = (L1PcInstance) _user;
            _player = _pc;
        }

        switch (skillId) {
            // PC、NPC 2方皆有效果
            // 解毒术
            case CURE_POISON:
                cha.curePoison();
                break;
            // 圣洁之光
            case REMOVE_CURSE:
                cha.curePoison();
                if (cha.hasSkillEffect(STATUS_CURSE_PARALYZING)
                        || cha.hasSkillEffect(STATUS_CURSE_PARALYZED)) {
                    cha.cureParalaysis();
                }
                break;
            // 返生术、终极返生术
            case RESURRECTION:
            case GREATER_RESURRECTION:
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
                            if (pc.getMap().isUseResurrection()) {
                                if (skillId == RESURRECTION) {
                                    pc.setGres(false);
                                } else if (skillId == GREATER_RESURRECTION) {
                                    pc.setGres(true);
                                }
                                pc.setTempID(_player.getId());
                                pc.sendPackets(new S_Message_YN(322, "")); // 是否要复活？
                                                                           // (Y/N)
                            }
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
                            npc.resurrect(npc.getMaxHp() / 4);
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

            // 无所遁形
            case DETECTION:
                if (cha instanceof L1NpcInstance) {
                    final L1NpcInstance npc = (L1NpcInstance) cha;
                    final int hiddenStatus = npc.getHiddenStatus();
                    if (hiddenStatus == L1NpcInstance.HIDDEN_STATUS_SINK) {
                        npc.appearOnGround(_player);
                    }
                }
                break;

            // 闇盲咒术
            // 黑闇之影
            case CURSE_BLIND:
            case DARKNESS:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    if (pc.hasSkillEffect(STATUS_FLOATING_EYE)) { // 漂浮之眼肉效果
                        pc.sendPackets(new S_CurseBlind(2));
                    } else {
                        pc.sendPackets(new S_CurseBlind(1));
                    }
                }
                break;
            // 毒咒
            case CURSE_POISON:
                L1DamagePoison.doInfection(_user, cha, 3000, 5);
                break;
            // 木乃伊的咀咒
            case CURSE_PARALYZE:
            case CURSE_PARALYZE2:
                if (!cha.hasSkillEffect(EARTH_BIND)
                        && !cha.hasSkillEffect(ICE_LANCE)
                        && !cha.hasSkillEffect(FREEZING_BLIZZARD)
                        && !cha.hasSkillEffect(FREEZING_BREATH)) {
                    if (cha instanceof L1PcInstance) {
                        L1CurseParalysis.curse(cha, 8000, 16000);
                    } else if (cha instanceof L1MonsterInstance) {
                        L1CurseParalysis.curse(cha, 8000, 16000);
                    }
                }
                break;
            // 弱化术
            case WEAKNESS:
                cha.addDmgup(-5);
                cha.addHitup(-1);
                break;
            // 疾病术
            case DISEASE:
                cha.addDmgup(-6);
                cha.addAc(12);
                break;

            // 魔法相消术
            case CANCELLATION:
                if (cha instanceof L1NpcInstance) {
                    final L1NpcInstance npc = (L1NpcInstance) cha;
                    final int npcId = npc.getNpcTemplate().get_npcId();
                    if (npcId == 71092) { // 调查员
                        if (npc.getGfxId() == npc.getTempCharGfx()) {
                            npc.setTempCharGfx(1314);
                            npc.broadcastPacket(new S_NpcChangeShape(npc
                                    .getId(), 1314, npc.getLawful(), npc
                                    .getStatus()));
                            return 0;
                        }
                        return 0;
                    }
                    if (npcId == 45640) { // 独角兽
                        if (npc.getGfxId() == npc.getTempCharGfx()) {
                            npc.setCurrentHp(npc.getMaxHp());
                            npc.setTempCharGfx(2332);
                            npc.broadcastPacket(new S_NpcChangeShape(npc
                                    .getId(), 2332, npc.getLawful(), npc
                                    .getStatus()));
                            npc.setName("$2103");
                            npc.setNameId("$2103");
                            npc.broadcastPacket(new S_ChangeName(npc.getId(),
                                    "$2103"));
                        } else if (npc.getTempCharGfx() == 2332) {
                            npc.setCurrentHp(npc.getMaxHp());
                            npc.setTempCharGfx(2755);
                            npc.broadcastPacket(new S_NpcChangeShape(npc
                                    .getId(), 2755, npc.getLawful(), npc
                                    .getStatus()));
                            npc.setName("$2488");
                            npc.setNameId("$2488");
                            npc.broadcastPacket(new S_ChangeName(npc.getId(),
                                    "$2488"));
                        }
                    }
                    if (npcId == 81209) { // 罗伊
                        if (npc.getGfxId() == npc.getTempCharGfx()) {
                            npc.setTempCharGfx(4310);
                            npc.broadcastPacket(new S_NpcChangeShape(npc
                                    .getId(), 4310, npc.getLawful(), npc
                                    .getStatus()));
                            return 0;
                        }
                        return 0;
                    }
                    if (npcId == 81352) { // 欧姆民兵
                        if (npc.getGfxId() == npc.getTempCharGfx()) {
                            npc.setTempCharGfx(148);
                            npc.broadcastPacket(new S_NpcChangeShape(npc
                                    .getId(), 148, npc.getLawful(), npc
                                    .getStatus()));
                            npc.setName("$6068");
                            npc.setNameId("$6068");
                            npc.broadcastPacket(new S_ChangeName(npc.getId(),
                                    "$6068"));
                        }
                    }
                }
                if ((_player != null) && _player.isInvisble()) {
                    _player.delInvis();
                }
                if (!(cha instanceof L1PcInstance)) {
                    final L1NpcInstance npc = (L1NpcInstance) cha;
                    npc.setMoveSpeed(0);
                    npc.setBraveSpeed(0);
                    npc.broadcastPacket(new S_SkillHaste(cha.getId(), 0, 0));
                    npc.broadcastPacket(new S_SkillBrave(cha.getId(), 0, 0));
                    npc.setWeaponBreaked(false);
                    npc.setParalyzed(false);
                    npc.setParalysisTime(0);
                }

                // 清除技能
                for (int skillNum = SKILLS_BEGIN; skillNum <= SKILLS_END; skillNum++) {
                    if (isNotCancelable(skillNum) && !cha.isDead()) {
                        continue;
                    }
                    cha.removeSkillEffect(skillNum);
                }

                // 解除异常的强化状态
                cha.curePoison();
                cha.cureParalaysis();
                for (int skillNum = STATUS_BEGIN; skillNum <= STATUS_END; skillNum++) {
                    if (skillNum == STATUS_CHAT_PROHIBITED) { // 禁言
                        continue;
                    }
                    cha.removeSkillEffect(skillNum);
                }

                if (cha instanceof L1PcInstance) {
                }

                // 解除料理状态
                for (int skillNum = COOKING_BEGIN; skillNum <= COOKING_END; skillNum++) {
                    if (isNotCancelable(skillNum)) {
                        continue;
                    }
                    cha.removeSkillEffect(skillNum);
                }

                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;

                    // 解除道具装备变身
                    L1PolyMorph.undoPoly(pc);
                    pc.sendPackets(new S_CharVisualUpdate(pc));
                    pc.broadcastPacket(new S_CharVisualUpdate(pc));

                    // 解除装备道具时关联的任何技能类
                    if (pc.getHasteItemEquipped() > 0) {
                        pc.setMoveSpeed(0);
                        pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
                        pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
                    }
                }
                cha.removeSkillEffect(STATUS_FREEZE); // Freeze解除
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_CharVisualUpdate(pc));
                    pc.broadcastPacket(new S_CharVisualUpdate(pc));
                    if (pc.isPrivateShop()) {
                        pc.sendPackets(new S_DoActionShop(pc.getId(),
                                ActionCodes.ACTION_Shop, pc.getShopChat()));
                        pc.broadcastPacket(new S_DoActionShop(pc.getId(),
                                ActionCodes.ACTION_Shop, pc.getShopChat()));
                    }
                    if (_user instanceof L1PcInstance) {
                        L1PinkName.onAction(pc, _user);
                    }
                }
                break;
            // 沉睡之雾
            case FOG_OF_SLEEPING:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_SLEEP, true));
                }
                cha.setSleeped(true);
                break;

            // 坏物术
            case WEAPON_BREAK:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    final L1ItemInstance weapon = pc.getWeapon();
                    if (weapon != null) {
                        final int weaponDamage = Random
                                .nextInt(_user.getInt() / 3) + 1;
                        pc.sendPackets(new S_ServerMessage(268, weapon
                                .getLogName())); // \f1你的%0%s坏了。
                        pc.getInventory().receiveDamage(weapon, weaponDamage);
                    }
                } else {
                    ((L1NpcInstance) cha).setWeaponBreaked(true);
                }
                break;

            // 辅助性魔法

            // 防护罩
            case SHIELD:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addAc(-2);
                    pc.sendPackets(new S_SkillIconShield(5,
                            _getBuffIconDuration));
                }
                break;

            // 体魄强健术
            case PHYSICAL_ENCHANT_STR:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addStr((byte) 5);
                    pc.sendPackets(new S_Strup(pc, 5, _getBuffIconDuration));
                }
                break;
            // 通畅气脉术
            case PHYSICAL_ENCHANT_DEX:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addDex((byte) 5);
                    pc.sendPackets(new S_Dexup(pc, 5, _getBuffIconDuration));
                }
                break;

            // 隐身术
            case INVISIBILITY:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Invis(pc.getId(), 1));
                    pc.broadcastPacketForFindInvis(new S_RemoveObject(pc),
                            false);
                }
                break;

            // 狂暴术
            case BERSERKERS:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addAc(10);
                    pc.addDmgup(5);
                    pc.addHitup(2);
                }
                break;
            // 变形术
            case SHAPE_CHANGE:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_ShowPolyList(pc.getId()));
                    if (!pc.isShapeChange()) {
                        pc.setShapeChange(true);
                    }
                }
                break;
            // 灵魂升华
            case ADVANCE_SPIRIT:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.setAdvenHp(pc.getBaseMaxHp() / 5);
                    pc.setAdvenMp(pc.getBaseMaxMp() / 5);
                    pc.addMaxHp(pc.getAdvenHp());
                    pc.addMaxMp(pc.getAdvenMp());
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                }
                break;
            // 神圣疾走
            case HOLY_WALK:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.setBraveSpeed(4);
                    pc.sendPackets(new S_SkillBrave(pc.getId(), 4,
                            _getBuffIconDuration));
                    pc.broadcastPacket(new S_SkillBrave(pc.getId(), 4, 0));
                }
                break;

            // 绝对屏障
            case ABSOLUTE_BARRIER:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.stopHpRegeneration();
                    pc.stopMpRegeneration();
                    pc.stopHpRegenerationByDoll();
                    pc.stopMpRegenerationByDoll();
                }
                break;
            // 冥想术
            case MEDITATION:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMpr(5);
                }
                break;

            // 目标 NPC
            // 能量感测
            case WEAK_ELEMENTAL:
                if (cha instanceof L1MonsterInstance) {
                    final L1Npc npcTemp = ((L1MonsterInstance) cha)
                            .getNpcTemplate();
                    final int weakAttr = npcTemp.get_weakAttr();
                    if ((weakAttr & 1) == 1) { // 地
                        cha.broadcastPacket(new S_SkillSound(cha.getId(), 2169));
                    } else if ((weakAttr & 2) == 2) { // 火
                        cha.broadcastPacket(new S_SkillSound(cha.getId(), 2166));
                    } else if ((weakAttr & 4) == 4) { // 水
                        cha.broadcastPacket(new S_SkillSound(cha.getId(), 2167));
                    } else if ((weakAttr & 8) == 8) { // 风
                        cha.broadcastPacket(new S_SkillSound(cha.getId(), 2168));
                    } else {
                        if (_user instanceof L1PcInstance) {
                            _player.sendPackets(new S_ServerMessage(79));
                        }
                    }
                } else {
                    if (_user instanceof L1PcInstance) {
                        _player.sendPackets(new S_ServerMessage(79));
                    }
                }
                break;

            // 传送性魔法

            // 召唤、迷魅、造尸
            // 召唤术
            case SUMMON_MONSTER:
                if (_user instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    final int level = pc.getLevel();
                    int[] summons;
                    if (pc.getMap().isRecallPets()) {
                        if (pc.getInventory().checkEquipped(20284)) {
                            pc.sendPackets(new S_ShowSummonList(pc.getId()));
                            if (!pc.isSummonMonster()) {
                                pc.setSummonMonster(true);
                            }
                        } else {
                            /*
                             * summons = new int[] { 81083, 81084, 81085, 81086,
                             * 81087, 81088, 81089 };
                             */
                            summons = new int[] { 81210, 81213, 81216, 81219,
                                    81222, 81225, 81228 };
                            int summonid = 0;
                            // int summoncost = 6;
                            final int summoncost = 8;
                            int levelRange = 32;
                            for (int i = 0; i < summons.length; i++) { // 该当ＬＶ范围检索
                                if ((level < levelRange)
                                        || (i == summons.length - 1)) {
                                    summonid = summons[i];
                                    break;
                                }
                                levelRange += 4;
                            }

                            int petcost = 0;
                            final Object[] petlist = pc.getPetList().values()
                                    .toArray();
                            for (final Object pet : petlist) {
                                // 现在のペットコスト
                                petcost += ((L1NpcInstance) pet).getPetcost();
                            }
                            int pcCha = pc.getCha();
                            if (pcCha > 34) { // max count = 5
                                pcCha = 34;
                            }
                            final int charisma = pcCha + 6 - petcost;
                            // int charisma = pc.getCha() + 6 - petcost;
                            final int summoncount = charisma / summoncost;
                            final L1Npc npcTemp = NpcTable.getInstance()
                                    .getTemplate(summonid);
                            for (int i = 0; i < summoncount; i++) {
                                final L1SummonInstance summon = new L1SummonInstance(
                                        npcTemp, pc);
                                summon.setPetcost(summoncost);
                            }
                        }
                    } else {
                        pc.sendPackets(new S_ServerMessage(79));
                    }
                }
                break;

            // 迷魅术
            case TAMING_MONSTER:
                if (cha instanceof L1MonsterInstance) {
                    final L1MonsterInstance npc = (L1MonsterInstance) cha;
                    // 可迷魅的怪物
                    if (npc.getNpcTemplate().isTamable()) {
                        int petcost = 0;
                        final Object[] petlist = _user.getPetList().values()
                                .toArray();
                        for (final Object pet : petlist) {
                            // 现在のペットコスト
                            petcost += ((L1NpcInstance) pet).getPetcost();
                        }
                        int charisma = _user.getCha();
                        if (_player.isElf()) { // 精灵
                            if (charisma > 30) { // max count = 7
                                charisma = 30;
                            }
                            charisma += 12;
                        } else if (_player.isWizard()) { // 法师
                            if (charisma > 36) { // max count = 7
                                charisma = 36;
                            }
                            charisma += 6;
                        }
                        charisma -= petcost;
                        if (charisma >= 6) { // 确保宠物的成本
                            final L1SummonInstance summon = new L1SummonInstance(
                                    npc, _user, false);
                            _target = summon; // 更换目标
                        } else {
                            _player.sendPackets(new S_ServerMessage(319)); // \f1你不能拥有太多的怪物。
                        }
                    }
                }
                break;
            // 造尸术
            case CREATE_ZOMBIE:
                if (cha instanceof L1MonsterInstance) {
                    final L1MonsterInstance npc = (L1MonsterInstance) cha;
                    int petcost = 0;
                    final Object[] petlist = _user.getPetList().values()
                            .toArray();
                    for (final Object pet : petlist) {
                        // 现在のペットコスト
                        petcost += ((L1NpcInstance) pet).getPetcost();
                    }
                    int charisma = _user.getCha();
                    if (_player.isElf()) { // 精灵
                        if (charisma > 30) { // max count = 7
                            charisma = 30;
                        }
                        charisma += 12;
                    } else if (_player.isWizard()) { // 法师
                        if (charisma > 36) { // max count = 7
                            charisma = 36;
                        }
                        charisma += 6;
                    }
                    charisma -= petcost;
                    if (charisma >= 6) { // 确保宠物的成本
                        final L1SummonInstance summon = new L1SummonInstance(
                                npc, _user, true);
                        _target = summon; // 更换目标
                    } else {
                        _player.sendPackets(new S_ServerMessage(319)); // \f1你不能拥有太多的怪物。
                    }
                }
                break;
        }
        return dmg;
    }

}
