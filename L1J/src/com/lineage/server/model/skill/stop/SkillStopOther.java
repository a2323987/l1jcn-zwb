package com.lineage.server.model.skill.stop;

import static com.lineage.server.model.skill.L1SkillId.COOKING_WONDER_DRUG;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLESS_OF_CRAY;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLESS_OF_MAZU;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLESS_OF_SAELL;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLOODSTAIN_OF_ANTHARAS;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLOODSTAIN_OF_FAFURION;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_ENCHANTING_BATTLE;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_AHTHARTS;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_BIRTH;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_FAFURION;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_FIGURE;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_LIFE;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_LINDVIOR;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_VALAKAS;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_1;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_2;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_3;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_4;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_5;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_6;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_7;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_8;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_9;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_1;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_2;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_3;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_4;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_5;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_6;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_7;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_8;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_9;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_1;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_2;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_3;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_4;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_5;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_6;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_7;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_8;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_9;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_1;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_2;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_3;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_4;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_5;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_6;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_7;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_8;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_9;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_STRENGTHENING_HP;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_STRENGTHENING_MP;
import static com.lineage.server.model.skill.L1SkillId.ICE_LANCE_BASILISK;
import static com.lineage.server.model.skill.L1SkillId.ICE_LANCE_COCKATRICE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CHAT_PROHIBITED;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CURSE_BARLOG;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CURSE_YAHEE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_FREEZE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_POISON;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.serverpackets.S_HPUpdate;
import com.lineage.server.serverpackets.S_MPUpdate;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_Paralysis;
import com.lineage.server.serverpackets.S_Poison;
import com.lineage.server.serverpackets.S_SPMR;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillIconAura;
import com.lineage.server.serverpackets.S_SkillIconBloodstain;

/**
 * 技能停止:其他
 * 
 * @author jrwz
 */
public class SkillStopOther implements L1SkillStop {

    @Override
    public void stopSkill(final L1Character cha, final int skillId) {

        switch (skillId) {

            case EFFECT_MAGIC_STONE_A_1: // 附魔石1阶(近战)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-10);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_A_2: // 附魔石2阶(近战)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-20);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_A_3: // 附魔石3阶(近战)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-30);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_A_4: // 附魔石4阶(近战)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-40);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_A_5: // 附魔石5阶(近战)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-50);
                    pc.addHpr(-1);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_A_6: // 附魔石6阶(近战)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-60);
                    pc.addHpr(-2);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_A_7: // 附魔石7阶(近战)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-70);
                    pc.addHpr(-3);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_A_8: // 附魔石8阶(近战)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-80);
                    pc.addHpr(-4);
                    pc.addHitup(-1);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_A_9: // 附魔石9阶(近战)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-100);
                    pc.addHpr(-5);
                    pc.addHitup(-2);
                    pc.addDmgup(-2);
                    pc.addStr((byte) -1);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_B_1: // 附魔石1阶(远攻)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-5);
                    pc.addMaxMp(-3);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_B_2: // 附魔石2阶(远攻)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-10);
                    pc.addMaxMp(-6);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_B_3: // 附魔石3阶(远攻)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-15);
                    pc.addMaxMp(-10);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_B_4: // 附魔石4阶(远攻)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-20);
                    pc.addMaxMp(-15);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_B_5: // 附魔石5阶(远攻)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-25);
                    pc.addMaxMp(-20);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_B_6: // 附魔石6阶(远攻)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-30);
                    pc.addMaxMp(-20);
                    pc.addHpr(-1);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_B_7: // 附魔石7阶(远攻)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-35);
                    pc.addMaxMp(-20);
                    pc.addHpr(-1);
                    pc.addMpr(-1);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_B_8: // 附魔石8阶(远攻)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-40);
                    pc.addMaxMp(-25);
                    pc.addHpr(-2);
                    pc.addMpr(-1);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_B_9: // 附魔石9阶(远攻)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-50);
                    pc.addMaxMp(-30);
                    pc.addHpr(-2);
                    pc.addMpr(-2);
                    pc.addBowDmgup(-2);
                    pc.addBowHitup(-2);
                    pc.addDex((byte) -1);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_C_1: // 附魔石1阶(恢复)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxMp(-5);
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_C_2: // 附魔石2阶(恢复)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxMp(-10);
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_C_3: // 附魔石3阶(恢复)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxMp(-15);
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_C_4: // 附魔石4阶(恢复)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxMp(-20);
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_C_5: // 附魔石5阶(恢复)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxMp(-25);
                    pc.addMpr(-1);
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_C_6: // 附魔石6阶(恢复)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxMp(-30);
                    pc.addMpr(-2);
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_C_7: // 附魔石7阶(恢复)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxMp(-35);
                    pc.addMpr(-3);
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_C_8: // 附魔石8阶(恢复)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxMp(-40);
                    pc.addMpr(-4);
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_C_9: // 附魔石9阶(恢复)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxMp(-50);
                    pc.addMpr(-5);
                    pc.addInt((byte) -1);
                    pc.addSp(-1);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_D_1: // 附魔石1阶(防御)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMr(-2);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_D_2: // 附魔石2阶(防御)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMr(-4);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_D_3: // 附魔石3阶(防御)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMr(-6);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_D_4: // 附魔石4阶(防御)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMr(-8);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_D_5: // 附魔石5阶(防御)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMr(-10);
                    pc.addAc(1);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_D_6: // 附魔石6阶(防御)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMr(-10);
                    pc.addAc(2);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_D_7: // 附魔石7阶(防御)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMr(-10);
                    pc.addAc(3);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_D_8: // 附魔石8阶(防御)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMr(-15);
                    pc.addAc(4);
                    pc.addDamageReductionByArmor(-1);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_STONE_D_9: // 附魔石9阶(防御)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMr(-20);
                    pc.addAc(5);
                    pc.addCon((byte) -1);
                    pc.addDamageReductionByArmor(-3);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.setMagicStoneLevel((byte) 0);
                }
                break;

            case EFFECT_MAGIC_EYE_OF_AHTHARTS: // 地龙之魔眼
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addRegistStone(-3); // 石化耐性
                    pc.addDodge((byte) -1); // 闪避率 - 10%
                    // 更新闪避率显示
                    pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
                }
                break;

            case EFFECT_MAGIC_EYE_OF_FAFURION: // 水龙之魔眼
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.add_regist_freeze(-3); // 寒冰耐性
                    // 魔法伤害减免
                }
                break;

            case EFFECT_MAGIC_EYE_OF_LINDVIOR: // 风龙之魔眼
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addRegistSleep(-3); // 睡眠耐性
                    // 魔法暴击率
                }
                break;

            case EFFECT_MAGIC_EYE_OF_VALAKAS: // 火龙之魔眼
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addRegistStun(-3); // 昏迷耐性
                    pc.addDmgup(-2); // 额外攻击点数
                }
                break;

            case EFFECT_MAGIC_EYE_OF_BIRTH: // 诞生之魔眼
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addRegistBlind(-3); // 闇黑耐性
                    // 魔法伤害减免
                    pc.addDodge((byte) -1); // 闪避率 - 10%
                    // 更新闪避率显示
                    pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
                }
                break;

            case EFFECT_MAGIC_EYE_OF_FIGURE: // 形象之魔眼
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addRegistSustain(-3); // 支撑耐性
                    // 魔法伤害减免
                    // 魔法暴击率
                    pc.addDodge((byte) -1); // 闪避率 - 10%
                    // 更新闪避率显示
                    pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
                }
                break;

            case EFFECT_MAGIC_EYE_OF_LIFE: // 生命之魔眼
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addDmgup(2); // 额外攻击点数
                    // 魔法伤害减免
                    // 魔法暴击率
                    // 防护中毒状态
                    pc.addDodge((byte) -1); // 闪避率 - 10%
                    // 更新闪避率显示
                    pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
                }
                break;

            case EFFECT_BLESS_OF_CRAY: // 卡瑞的祝福
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-100);
                    pc.addMaxMp(-50);
                    pc.addHpr(-3);
                    pc.addMpr(-3);
                    pc.addEarth(-30);
                    pc.addDmgup(-1);
                    pc.addHitup(-5);
                    pc.addWeightReduction(-40);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                }
                break;

            case EFFECT_BLESS_OF_SAELL: // 莎尔的祝福
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-80);
                    pc.addMaxMp(-10);
                    pc.addWater(-30);
                    pc.addAc(8);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                }
                break;

            case STATUS_CURSE_YAHEE: // 炎魔的烙印
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconAura(221, 0, 1));
                }
                break;

            case STATUS_CURSE_BARLOG: // 火焰之影的烙印
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconAura(221, 0, 2));
                }
                break;

            case ICE_LANCE_COCKATRICE: // 亚力安冰矛围篱
            case ICE_LANCE_BASILISK: // 邪恶蜥蜴冰矛围篱
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

            case STATUS_FREEZE: // 束缚术
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_BIND, false));
                } else if ((cha instanceof L1MonsterInstance)
                        || (cha instanceof L1SummonInstance)
                        || (cha instanceof L1PetInstance)) {
                    final L1NpcInstance npc = (L1NpcInstance) cha;
                    npc.setParalyzed(false);
                }
                break;

            case STATUS_CHAT_PROHIBITED: // 禁言
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_ServerMessage(288)); // 你现在已经可以交谈了。
                }
                break;

            // ****** 毒关系
            case STATUS_POISON: // 毒伤害
                cha.curePoison();
                break;

            case COOKING_WONDER_DRUG: // 象牙塔妙药
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addHpr(-10);
                    pc.addMpr(-2);
                }
                break;

            case EFFECT_BLESS_OF_MAZU: // 妈祖的祝福
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addHitup(-3);
                    pc.addDmgup(-3);
                    pc.addMpr(-2);
                }
                break;

            case EFFECT_STRENGTHENING_HP: // 体力增强卷轴
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxHp(-50);
                    pc.addHpr(-4);
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                }
                break;

            case EFFECT_STRENGTHENING_MP: // 魔力增强卷轴
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMaxMp(-40);
                    pc.addMpr(-4);
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                }
                break;

            case EFFECT_ENCHANTING_BATTLE: // 强化战斗卷轴
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addHitup(-3);
                    pc.addDmgup(-3);
                    pc.addBowHitup(-3);
                    pc.addBowDmgup(-3);
                    pc.addSp(-3);
                    pc.sendPackets(new S_SPMR(pc));
                }
                break;

            case EFFECT_BLOODSTAIN_OF_ANTHARAS: // 安塔瑞斯的血痕
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addAc(2);
                    pc.addWater(-50);
                    pc.sendPackets(new S_SkillIconBloodstain(82, 0));
                }
                break;

            case EFFECT_BLOODSTAIN_OF_FAFURION: // 法利昂的血痕
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addWind(-50);
                    pc.sendPackets(new S_SkillIconBloodstain(85, 0));
                }
                break;
        }
    }

}
