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
package com.lineage.server.datatables;

import static com.lineage.server.model.skill.L1SkillId.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.skill.L1BuffUtil;
import com.lineage.server.serverpackets.S_HPUpdate;
import com.lineage.server.serverpackets.S_MPUpdate;
import com.lineage.server.serverpackets.S_OwnCharAttrDef;
import com.lineage.server.serverpackets.S_OwnCharStatus2;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_SPMR;
import com.lineage.server.utils.SQLUtil;

/**
 * 角色状态资料表
 */
public class CharBuffTable {

    private static Logger _log = Logger
            .getLogger(CharBuffTable.class.getName());

    /**
     * 技能状态
     */
    private static final int[] buffSkill = {
            LIGHT,
            SHIELD,
            PHYSICAL_ENCHANT_DEX,
            PHYSICAL_ENCHANT_STR,
            HASTE,
            HOLY_WALK,
            GREATER_HASTE,
            SHAPE_CHANGE,
            SHADOW_ARMOR,
            MOVING_ACCELERATION,
            DRESS_MIGHTY,
            DRESS_DEXTERITY,
            GLOWING_AURA,
            SHINING_AURA,
            BRAVE_AURA,
            FIRE_WEAPON,
            WIND_SHOT,
            WIND_WALK,
            EARTH_SKIN,
            FIRE_BLESS,
            STORM_EYE,
            EARTH_BLESS,
            BURNING_WEAPON,
            STORM_SHOT,
            IRON_SKIN,
            DRESS_EVASION, // 闪避提升
            RESIST_FEAR, // 恐惧无助
            MIRROR_IMAGE, // 镜像
            UNCANNY_DODGE, // 暗影闪避
            STATUS_HASTE, // 一段加速
            STATUS_BRAVE,// 二段加速
            STATUS_ELFBRAVE, // 精灵饼干
            STATUS_RIBRAVE, // 生命之树果实
            STATUS_BRAVE2, // 超级加速
            STATUS_THIRD_SPEED, // 三段加速
            STATUS_BLUE_POTION, // 蓝水
            STATUS_CHAT_PROHIBITED, // 禁言

            // 魔法料理
            COOKING_1_0_N,
            COOKING_1_0_S,
            COOKING_1_1_N,
            COOKING_1_1_S,
            COOKING_1_2_N,
            COOKING_1_2_S,
            COOKING_1_3_N,
            COOKING_1_3_S,
            COOKING_1_4_N,
            COOKING_1_4_S,
            COOKING_1_5_N,
            COOKING_1_5_S,
            COOKING_1_6_N,
            COOKING_1_6_S,
            COOKING_2_0_N,
            COOKING_2_0_S,
            COOKING_2_1_N,
            COOKING_2_1_S,
            COOKING_2_2_N,
            COOKING_2_2_S,
            COOKING_2_3_N,
            COOKING_2_3_S,
            COOKING_2_4_N,
            COOKING_2_4_S,
            COOKING_2_5_N,
            COOKING_2_5_S,
            COOKING_2_6_N,
            COOKING_2_6_S,
            COOKING_3_0_N,
            COOKING_3_0_S,
            COOKING_3_1_N,
            COOKING_3_1_S,
            COOKING_3_2_N,
            COOKING_3_2_S,
            COOKING_3_3_N,
            COOKING_3_3_S,
            COOKING_3_4_N,
            COOKING_3_4_S,
            COOKING_3_5_N,
            COOKING_3_5_S,
            COOKING_3_6_N,
            COOKING_3_6_S,

            // 神力药水
            EFFECT_POTION_OF_EXP_150,
            EFFECT_POTION_OF_EXP_175,
            EFFECT_POTION_OF_EXP_200,
            EFFECT_POTION_OF_EXP_225,
            EFFECT_POTION_OF_EXP_250,

            EFFECT_POTION_OF_BATTLE, // 战斗药水
            EFFECT_BLESS_OF_MAZU, // 妈祖的祝福
            EFFECT_ENCHANTING_BATTLE, // 强化战斗卷轴
            EFFECT_STRENGTHENING_HP, // 体力增强卷轴
            EFFECT_STRENGTHENING_MP, // 魔力增强卷轴
            COOKING_WONDER_DRUG, // 象牙塔妙药

            // 龙之血痕
            EFFECT_BLOODSTAIN_OF_ANTHARAS,
            EFFECT_BLOODSTAIN_OF_FAFURION,

            // 附魔石
            EFFECT_MAGIC_STONE_A_1, EFFECT_MAGIC_STONE_A_2,
            EFFECT_MAGIC_STONE_A_3, EFFECT_MAGIC_STONE_A_4,
            EFFECT_MAGIC_STONE_A_5, EFFECT_MAGIC_STONE_A_6,
            EFFECT_MAGIC_STONE_A_7, EFFECT_MAGIC_STONE_A_8,
            EFFECT_MAGIC_STONE_A_9, EFFECT_MAGIC_STONE_B_1,
            EFFECT_MAGIC_STONE_B_2, EFFECT_MAGIC_STONE_B_3,
            EFFECT_MAGIC_STONE_B_4, EFFECT_MAGIC_STONE_B_5,
            EFFECT_MAGIC_STONE_B_6, EFFECT_MAGIC_STONE_B_7,
            EFFECT_MAGIC_STONE_B_8, EFFECT_MAGIC_STONE_B_9,
            EFFECT_MAGIC_STONE_C_1, EFFECT_MAGIC_STONE_C_2,
            EFFECT_MAGIC_STONE_C_3, EFFECT_MAGIC_STONE_C_4,
            EFFECT_MAGIC_STONE_C_5, EFFECT_MAGIC_STONE_C_6,
            EFFECT_MAGIC_STONE_C_7, EFFECT_MAGIC_STONE_C_8,
            EFFECT_MAGIC_STONE_C_9, EFFECT_MAGIC_STONE_D_1,
            EFFECT_MAGIC_STONE_D_2, EFFECT_MAGIC_STONE_D_3,
            EFFECT_MAGIC_STONE_D_4, EFFECT_MAGIC_STONE_D_5,
            EFFECT_MAGIC_STONE_D_6,
            EFFECT_MAGIC_STONE_D_7,
            EFFECT_MAGIC_STONE_D_8,
            EFFECT_MAGIC_STONE_D_9,

            // 龙之魔眼
            EFFECT_MAGIC_EYE_OF_AHTHARTS, EFFECT_MAGIC_EYE_OF_FAFURION,
            EFFECT_MAGIC_EYE_OF_LINDVIOR, EFFECT_MAGIC_EYE_OF_VALAKAS,
            EFFECT_MAGIC_EYE_OF_BIRTH, EFFECT_MAGIC_EYE_OF_FIGURE,
            EFFECT_MAGIC_EYE_OF_LIFE,

            EFFECT_BLESS_OF_CRAY, EFFECT_BLESS_OF_SAELL
    // 卡瑞、莎尔的祝福
    };

    /**
     * 状态剩余时间
     * 
     * @param pc
     */
    public static void buffRemainingTime(final L1PcInstance pc) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT * FROM character_buff WHERE char_obj_id=?");
            pstm.setInt(1, pc.getId());
            rs = pstm.executeQuery();
            while (rs.next()) {
                final int skillid = rs.getInt("skill_id");
                int remaining_time = rs.getInt("remaining_time");
                switch (skillid) {
                    case STATUS_RIBRAVE: // 生命之树果实
                    case DRESS_EVASION: // 闪避提升
                        remaining_time = remaining_time / 4;
                        pc.setSkillEffect(skillid, remaining_time * 4 * 1000);
                        break;
                    case COOKING_WONDER_DRUG: // 象牙塔妙药
                        pc.addHpr(10);
                        pc.addMpr(2);
                        remaining_time = remaining_time / 4;
                        pc.setSkillEffect(skillid, remaining_time * 4 * 1000);
                        break;
                    case EFFECT_BLESS_OF_MAZU: // 妈祖的祝福
                    case EFFECT_ENCHANTING_BATTLE: // 强化战斗卷轴
                    case EFFECT_STRENGTHENING_HP: // 体力增强卷轴
                    case EFFECT_STRENGTHENING_MP: // 魔力增强卷轴
                        remaining_time = remaining_time / 16;
                        useEffect(pc, skillid, remaining_time * 16);
                        break;
                    case EFFECT_POTION_OF_BATTLE: // 战斗药水
                    case EFFECT_POTION_OF_EXP_150: // 神力药水
                    case EFFECT_POTION_OF_EXP_175:
                    case EFFECT_POTION_OF_EXP_200:
                    case EFFECT_POTION_OF_EXP_225:
                    case EFFECT_POTION_OF_EXP_250:
                        remaining_time = remaining_time / 16;
                        // pc.setSkillEffect(skillid, remaining_time * 16 *
                        // 1000);
                        useEffect(pc, skillid, remaining_time * 16);
                        break;
                    case EFFECT_MAGIC_EYE_OF_AHTHARTS: // 魔眼
                    case EFFECT_MAGIC_EYE_OF_FAFURION:
                    case EFFECT_MAGIC_EYE_OF_LINDVIOR:
                    case EFFECT_MAGIC_EYE_OF_VALAKAS:
                    case EFFECT_MAGIC_EYE_OF_BIRTH:
                    case EFFECT_MAGIC_EYE_OF_FIGURE:
                    case EFFECT_MAGIC_EYE_OF_LIFE:
                        remaining_time = remaining_time / 32;
                        useEffect(pc, skillid, remaining_time * 32);
                        break;
                    case RESIST_FEAR: // 恐惧无助
                        remaining_time = remaining_time / 4;
                        pc.addNdodge((byte) 5); // 闪避率 - 50%
                        // 更新闪避率显示
                        pc.sendPackets(new S_PacketBox(101, pc.getNdodge()));
                        pc.setSkillEffect(skillid, remaining_time * 4 * 1000);
                        break;
                    case EFFECT_BLESS_OF_CRAY: // 卡瑞、莎尔的祝福
                    case EFFECT_BLESS_OF_SAELL:
                        remaining_time = remaining_time / 32;
                        L1BuffUtil.effectBlessOfDragonSlayer(pc, skillid,
                                remaining_time * 32, 0);
                        break;
                    default:
                        if ((skillid >= EFFECT_MAGIC_STONE_A_1)
                                && (skillid <= EFFECT_MAGIC_STONE_D_9)) { // 附魔石
                            remaining_time = remaining_time / 32;
                            magicStoneEffect(pc, skillid, remaining_time * 32);
                            break;
                        }
                        break;
                }
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    /**
     * 删除状态
     * 
     * @param pc
     */
    public static void DeleteBuff(final L1PcInstance pc) {
        java.sql.Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("DELETE FROM character_buff WHERE char_obj_id=?");
            pstm.setInt(1, pc.getId());
            pstm.execute();
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);

        }
    }

    /**
     * 附魔石效果
     * 
     * @param pc
     *            对象
     * @param skillId
     *            技能编号
     * @param time
     *            效果时间
     */
    private static void magicStoneEffect(final L1PcInstance pc,
            final int skillId, final int time) {
        byte type = 0;
        if (!pc.hasSkillEffect(skillId)) {
            switch (skillId) {
                case EFFECT_MAGIC_STONE_A_1:
                    pc.addMaxHp(10);
                    type = 84;
                    break;
                case EFFECT_MAGIC_STONE_A_2:
                    pc.addMaxHp(20);
                    type = 85;
                    break;
                case EFFECT_MAGIC_STONE_A_3:
                    pc.addMaxHp(30);
                    type = 86;
                    break;
                case EFFECT_MAGIC_STONE_A_4:
                    pc.addMaxHp(40);
                    type = 87;
                    break;
                case EFFECT_MAGIC_STONE_A_5:
                    pc.addMaxHp(50);
                    pc.addHpr(1);
                    type = 88;
                    break;
                case EFFECT_MAGIC_STONE_A_6:
                    pc.addMaxHp(60);
                    pc.addHpr(2);
                    type = 89;
                    break;
                case EFFECT_MAGIC_STONE_A_7:
                    pc.addMaxHp(70);
                    pc.addHpr(3);
                    type = 90;
                    break;
                case EFFECT_MAGIC_STONE_A_8:
                    pc.addMaxHp(80);
                    pc.addHpr(4);
                    pc.addHitup(1);
                    type = 91;
                    break;
                case EFFECT_MAGIC_STONE_A_9:
                    pc.addMaxHp(100);
                    pc.addHpr(5);
                    pc.addHitup(2);
                    pc.addDmgup(2);
                    pc.addStr((byte) 1);
                    pc.sendPackets(new S_OwnCharStatus2(pc));
                    type = 92;
                    break;
                case EFFECT_MAGIC_STONE_B_1:
                    pc.addMaxHp(5);
                    pc.addMaxMp(3);
                    type = 93;
                    break;
                case EFFECT_MAGIC_STONE_B_2:
                    pc.addMaxHp(10);
                    pc.addMaxMp(6);
                    type = 94;
                    break;
                case EFFECT_MAGIC_STONE_B_3:
                    pc.addMaxHp(15);
                    pc.addMaxMp(10);
                    type = 95;
                    break;
                case EFFECT_MAGIC_STONE_B_4:
                    pc.addMaxHp(20);
                    pc.addMaxMp(15);
                    type = 96;
                    break;
                case EFFECT_MAGIC_STONE_B_5:
                    pc.addMaxHp(25);
                    pc.addMaxMp(20);
                    type = 97;
                    break;
                case EFFECT_MAGIC_STONE_B_6:
                    pc.addMaxHp(30);
                    pc.addMaxMp(20);
                    pc.addHpr(1);
                    type = 98;
                    break;
                case EFFECT_MAGIC_STONE_B_7:
                    pc.addMaxHp(35);
                    pc.addMaxMp(20);
                    pc.addHpr(1);
                    pc.addMpr(1);
                    type = 99;
                    break;
                case EFFECT_MAGIC_STONE_B_8:
                    pc.addMaxHp(40);
                    pc.addMaxMp(25);
                    pc.addHpr(2);
                    pc.addMpr(1);
                    type = 100;
                    break;
                case EFFECT_MAGIC_STONE_B_9:
                    pc.addMaxHp(50);
                    pc.addMaxMp(30);
                    pc.addHpr(2);
                    pc.addMpr(2);
                    pc.addBowDmgup(2);
                    pc.addBowHitup(2);
                    pc.addDex((byte) 1);
                    pc.sendPackets(new S_OwnCharStatus2(pc));
                    type = 101;
                    break;
                case EFFECT_MAGIC_STONE_C_1:
                    pc.addMaxMp(5);
                    type = 102;
                    break;
                case EFFECT_MAGIC_STONE_C_2:
                    pc.addMaxMp(10);
                    type = 103;
                    break;
                case EFFECT_MAGIC_STONE_C_3:
                    pc.addMaxMp(15);
                    type = 104;
                    break;
                case EFFECT_MAGIC_STONE_C_4:
                    pc.addMaxMp(20);
                    type = 105;
                    break;
                case EFFECT_MAGIC_STONE_C_5:
                    pc.addMaxMp(25);
                    pc.addMpr(1);
                    type = 106;
                    break;
                case EFFECT_MAGIC_STONE_C_6:
                    pc.addMaxMp(30);
                    pc.addMpr(2);
                    type = 107;
                    break;
                case EFFECT_MAGIC_STONE_C_7:
                    pc.addMaxMp(35);
                    pc.addMpr(3);
                    type = 108;
                    break;
                case EFFECT_MAGIC_STONE_C_8:
                    pc.addMaxMp(40);
                    pc.addMpr(4);
                    type = 109;
                    break;
                case EFFECT_MAGIC_STONE_C_9:
                    pc.addMaxMp(50);
                    pc.addMpr(5);
                    pc.addInt((byte) 1);
                    pc.addSp(1);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.sendPackets(new S_OwnCharStatus2(pc));
                    type = 110;
                    break;
                case EFFECT_MAGIC_STONE_D_1:
                    pc.addMr(2);
                    type = 111;
                    break;
                case EFFECT_MAGIC_STONE_D_2:
                    pc.addMr(4);
                    type = 112;
                    break;
                case EFFECT_MAGIC_STONE_D_3:
                    pc.addMr(6);
                    type = 113;
                    break;
                case EFFECT_MAGIC_STONE_D_4:
                    pc.addMr(8);
                    type = 114;
                    break;
                case EFFECT_MAGIC_STONE_D_5:
                    pc.addMr(10);
                    pc.addAc(-1);
                    type = 115;
                    break;
                case EFFECT_MAGIC_STONE_D_6:
                    pc.addMr(10);
                    pc.addAc(-2);
                    type = 116;
                    break;
                case EFFECT_MAGIC_STONE_D_7:
                    pc.addMr(10);
                    pc.addAc(-3);
                    type = 117;
                    break;
                case EFFECT_MAGIC_STONE_D_8:
                    pc.addMr(15);
                    pc.addAc(-4);
                    pc.addDamageReductionByArmor(1);
                    type = 118;
                    break;
                case EFFECT_MAGIC_STONE_D_9:
                    pc.addMr(20);
                    pc.addAc(-5);
                    pc.addCon((byte) 1);
                    pc.addDamageReductionByArmor(3);
                    type = 119;
                    break;
                default:
                    break;
            }

            if ((type >= 84) && (type <= 92)) { // (近战)
                pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
                if (pc.isInParty()) { // 组队中
                    pc.getParty().updateMiniHP(pc);
                }
            } else if ((type >= 93) && (type <= 101)) { // (远攻)
                pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
                pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
                if (pc.isInParty()) { // 组队中
                    pc.getParty().updateMiniHP(pc);
                }
            } else if ((type >= 102) && (type <= 110)) { // 恢复
                pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
            } else if ((type >= 111) && (type <= 119)) { // 防御
                pc.sendPackets(new S_SPMR(pc));
                pc.sendPackets(new S_OwnCharAttrDef(pc));
                pc.sendPackets(new S_OwnCharStatus2(pc));

            }
        }
        pc.setMagicStoneLevel(type);
        pc.setSkillEffect(skillId, time * 1000);
    }

    /**
     * 储存状态
     * 
     * @param pc
     */
    public static void SaveBuff(final L1PcInstance pc) {
        for (final int skillId : buffSkill) {
            final int timeSec = pc.getSkillEffectTimeSec(skillId);
            if (0 < timeSec) {
                int polyId = 0;
                if (skillId == SHAPE_CHANGE) {
                    polyId = pc.getTempCharGfx();
                }
                StoreBuff(pc.getId(), skillId, timeSec, polyId);
            }
        }
    }

    /**
     * 储存状态
     * 
     * @param objId
     *            角色OBJID
     * @param skillId
     *            技能ID
     * @param time
     *            技能时间
     * @param polyId
     *            变身ID
     */
    private static void StoreBuff(final int objId, final int skillId,
            final int time, final int polyId) {
        java.sql.Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("INSERT INTO character_buff SET char_obj_id=?, skill_id=?, remaining_time=?, poly_id=?");
            pstm.setInt(1, objId);
            pstm.setInt(2, skillId);
            pstm.setInt(3, time);
            pstm.setInt(4, polyId);
            pstm.execute();
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    /**
     * 状态药水类效果
     * 
     * @param pc
     *            对象
     * @param skillId
     *            技能编号
     * @param time
     *            效果时间
     */
    private static void useEffect(final L1PcInstance pc, final int skillId,
            final int time) {
        if (!pc.hasSkillEffect(skillId)) {
            switch (skillId) {
                case EFFECT_POTION_OF_BATTLE: // 战斗药水
                case EFFECT_POTION_OF_EXP_150: // 神力药水
                case EFFECT_POTION_OF_EXP_175:
                case EFFECT_POTION_OF_EXP_200:
                case EFFECT_POTION_OF_EXP_225:
                case EFFECT_POTION_OF_EXP_250:
                    break;

                case EFFECT_BLESS_OF_MAZU: // 妈祖的祝福
                    pc.addHitup(3); // 攻击成功 +3
                    pc.addDmgup(3); // 额外攻击点数 +3
                    pc.addMpr(2);
                    break;
                case EFFECT_ENCHANTING_BATTLE: // 强化战斗卷轴
                    pc.addHitup(3); // 攻击成功 +3
                    pc.addDmgup(3); // 额外攻击点数 +3
                    pc.addBowHitup(3); // 远距离命中率 +3
                    pc.addBowDmgup(3); // 远距离攻击力 +3
                    pc.addSp(3); // 魔攻 +3
                    pc.sendPackets(new S_SPMR(pc));
                    break;
                case EFFECT_STRENGTHENING_HP: // 体力增强卷轴
                    pc.addMaxHp(50);
                    pc.addHpr(4);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    break;
                case EFFECT_STRENGTHENING_MP: // 魔力增强卷轴
                    pc.addMaxMp(40);
                    pc.addMpr(4);
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    break;
                case EFFECT_MAGIC_EYE_OF_AHTHARTS: // 地龙之魔眼
                    pc.addRegistStone(3); // 石化耐性 +3

                    pc.addDodge((byte) 1); // 闪避率 + 10%
                    // 更新闪避率显示
                    pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
                    break;
                case EFFECT_MAGIC_EYE_OF_FAFURION: // 水龙之魔眼
                    pc.add_regist_freeze(3); // 寒冰耐性 +3
                    // 魔法伤害减免 +50
                    break;
                case EFFECT_MAGIC_EYE_OF_LINDVIOR: // 风龙之魔眼
                    pc.addRegistSleep(3); // 睡眠耐性 +3
                    // 魔法暴击率 +1
                    break;
                case EFFECT_MAGIC_EYE_OF_VALAKAS: // 火龙之魔眼
                    pc.addRegistStun(3); // 昏迷耐性 +3
                    pc.addDmgup(2); // 额外攻击点数 +2
                    break;
                case EFFECT_MAGIC_EYE_OF_BIRTH: // 诞生之魔眼
                    pc.addRegistBlind(3); // 闇黑耐性 +3
                    // 魔法伤害减免 +50

                    pc.addDodge((byte) 1); // 闪避率 + 10%
                    // 更新闪避率显示
                    pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
                    break;
                case EFFECT_MAGIC_EYE_OF_FIGURE: // 形象之魔眼
                    pc.addRegistSustain(3); // 支撑耐性 +3
                    // 魔法伤害减免 +50
                    // 魔法暴击率 +1

                    pc.addDodge((byte) 1); // 闪避率 + 10%
                    // 更新闪避率显示
                    pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
                    break;
                case EFFECT_MAGIC_EYE_OF_LIFE: // 生命之魔眼
                    pc.addDmgup(2); // 额外攻击点数 +2
                    // 魔法伤害减免 +50
                    // 魔法暴击率 +1
                    // 防护中毒状态

                    pc.addDodge((byte) 1); // 闪避率 + 10%
                    // 更新闪避率显示
                    pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
                    break;
                default:
                    break;
            }
        }
        pc.setSkillEffect(skillId, time * 1000);
    }

    private CharBuffTable() {
    }

}
