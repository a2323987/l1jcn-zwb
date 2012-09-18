package com.lineage.server.model.skill.stop;

import static com.lineage.server.model.skill.L1SkillId.COOKING_1_0_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_0_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_1_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_1_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_2_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_2_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_3_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_3_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_4_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_4_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_5_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_5_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_6_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_6_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_7_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_7_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_0_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_0_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_1_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_1_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_2_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_2_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_3_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_3_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_4_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_4_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_5_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_5_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_6_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_6_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_7_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_7_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_0_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_0_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_1_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_1_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_2_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_2_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_3_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_3_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_4_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_4_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_5_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_5_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_6_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_6_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_7_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_7_S;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_HPUpdate;
import com.lineage.server.serverpackets.S_MPUpdate;
import com.lineage.server.serverpackets.S_OwnCharAttrDef;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_SPMR;

/**
 * 技能停止:料理
 * 
 * @author jrwz
 */
public class SkillStopCooking implements L1SkillStop {

    @Override
    public void stopSkill(final L1Character cha, final int skillId) {

        switch (skillId) {
            case COOKING_1_0_N: // 漂浮之眼肉排
            case COOKING_1_0_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addWind(-10);
                    pc.addWater(-10);
                    pc.addFire(-10);
                    pc.addEarth(-10);
                    pc.sendPackets(new S_OwnCharAttrDef(pc));
                    pc.sendPackets(new S_PacketBox(53, 0, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_1_1_N: // 烤熊肉
            case COOKING_1_1_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-30);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.sendPackets(new S_PacketBox(53, 1, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_1_2_N: // 煎饼
            case COOKING_1_2_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_PacketBox(53, 2, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_1_3_N: // 蚂蚁腿起司
            case COOKING_1_3_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addAc(1);
                    pc.sendPackets(new S_PacketBox(53, 3, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_1_4_N: // 水果沙拉
            case COOKING_1_4_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxMp(-20);
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    pc.sendPackets(new S_PacketBox(53, 4, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_1_5_N: // 水果糖醋肉
            case COOKING_1_5_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_PacketBox(53, 5, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_1_6_N: // 烤山猪肉串
            case COOKING_1_6_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMr(-5);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.sendPackets(new S_PacketBox(53, 6, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_1_7_N: // 蘑菇汤
            case COOKING_1_7_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_PacketBox(53, 7, 0));
                    pc.setDessertId(0);
                }
                break;

            case COOKING_2_0_N: // 鱼子酱
            case COOKING_2_0_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_PacketBox(53, 8, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_2_1_N: // 鳄鱼肉排
            case COOKING_2_1_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-30);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.addMaxMp(-30);
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    pc.sendPackets(new S_PacketBox(53, 9, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_2_2_N: // 龙龟蛋饼干
            case COOKING_2_2_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addAc(2);
                    pc.sendPackets(new S_PacketBox(53, 10, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_2_3_N: // 烤奇异鹦鹉
            case COOKING_2_3_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_PacketBox(53, 11, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_2_4_N: // 毒蝎串烧
            case COOKING_2_4_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_PacketBox(53, 12, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_2_5_N: // 烤伊莱克顿
            case COOKING_2_5_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMr(-10);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.sendPackets(new S_PacketBox(53, 13, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_2_6_N: // 蜘蛛腿串烧
            case COOKING_2_6_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addSp(-1);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.sendPackets(new S_PacketBox(53, 14, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_2_7_N: // 蟹肉汤
            case COOKING_2_7_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_PacketBox(53, 15, 0));
                    pc.setDessertId(0);
                }
                break;

            case COOKING_3_0_N: // 烤奎斯坦修的鳌
            case COOKING_3_0_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_PacketBox(53, 16, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_3_1_N: // 烤格利芬肉
            case COOKING_3_1_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-50);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.addMaxMp(-50);
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    pc.sendPackets(new S_PacketBox(53, 17, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_3_2_N: // 亚力安的尾巴肉排
            case COOKING_3_2_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_PacketBox(53, 18, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_3_3_N: // 烤巨王龟肉
            case COOKING_3_3_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addAc(3);
                    pc.sendPackets(new S_PacketBox(53, 19, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_3_4_N: // 幼龙翅膀串烧
            case COOKING_3_4_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMr(-15);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.addWind(-10);
                    pc.addWater(-10);
                    pc.addFire(-10);
                    pc.addEarth(-10);
                    pc.sendPackets(new S_OwnCharAttrDef(pc));
                    pc.sendPackets(new S_PacketBox(53, 20, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_3_5_N: // 烤飞龙肉
            case COOKING_3_5_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addSp(-2);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.sendPackets(new S_PacketBox(53, 21, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_3_6_N: // 炖深海鱼肉
            case COOKING_3_6_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-30);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.sendPackets(new S_PacketBox(53, 22, 0));
                    pc.setCookingId(0);
                }
                break;

            case COOKING_3_7_N: // 邪恶蜥蜴蛋汤
            case COOKING_3_7_S:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_PacketBox(53, 23, 0));
                    pc.setDessertId(0);
                }
                break;
        }
    }

}
