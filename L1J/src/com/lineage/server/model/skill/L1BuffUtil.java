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
package com.lineage.server.model.skill;

import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLESS_OF_CRAY;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLESS_OF_SAELL;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLOODSTAIN_OF_ANTHARAS;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLOODSTAIN_OF_FAFURION;

import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_HPUpdate;
import com.lineage.server.serverpackets.S_MPUpdate;
import com.lineage.server.serverpackets.S_OwnCharAttrDef;
import com.lineage.server.serverpackets.S_OwnCharStatus2;
import com.lineage.server.serverpackets.S_SkillIconBloodstain;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * Buff使用率
 */
public class L1BuffUtil {

    /** 龙之血痕 */
    public static void bloodstain(final L1PcInstance pc, final byte type,
            final int time, final boolean showGfx) {

        if (showGfx) {
            pc.sendPackets(new S_SkillSound(pc.getId(), 7783));
            pc.broadcastPacket(new S_SkillSound(pc.getId(), 7783));
        }

        int skillId = EFFECT_BLOODSTAIN_OF_ANTHARAS;
        int iconType = 0;
        if (type == 0) { // 安塔瑞斯
            if (!pc.hasSkillEffect(skillId)) {
                pc.addAc(-2); // 防御 -2
                pc.addWater(50); // 水属性 +50
            }
            iconType = 82;
            // 安塔瑞斯的血痕
        } else if (type == 1) { // 法利昂
            skillId = EFFECT_BLOODSTAIN_OF_FAFURION;
            if (!pc.hasSkillEffect(skillId)) {
                pc.addWind(50); // 风属性 +50
            }
            iconType = 85;
        }
        pc.sendPackets(new S_OwnCharAttrDef(pc));
        pc.sendPackets(new S_SkillIconBloodstain(iconType, time));
        pc.setSkillEffect(skillId, (time * 60 * 1000));
    }

    /** 卡瑞、莎尔的祝福 */
    public static void effectBlessOfDragonSlayer(final L1PcInstance pc,
            final int skillId, final int time, final int showGfx) {
        if (showGfx != 0) {
            pc.sendPackets(new S_SkillSound(pc.getId(), showGfx));
            pc.broadcastPacket(new S_SkillSound(pc.getId(), showGfx));
        }

        if (!pc.hasSkillEffect(skillId)) {
            switch (skillId) {
                case EFFECT_BLESS_OF_CRAY: // 卡瑞的祝福
                    if (pc.hasSkillEffect(EFFECT_BLESS_OF_SAELL)) {
                        pc.removeSkillEffect(EFFECT_BLESS_OF_SAELL);
                    }
                    pc.addMaxHp(100);
                    pc.addMaxMp(50);
                    pc.addHpr(3);
                    pc.addMpr(3);
                    pc.addEarth(30);
                    pc.addDmgup(1);
                    pc.addHitup(5);
                    pc.addWeightReduction(40);
                    break;
                case EFFECT_BLESS_OF_SAELL: // 莎尔的祝福
                    if (pc.hasSkillEffect(EFFECT_BLESS_OF_CRAY)) {
                        pc.removeSkillEffect(EFFECT_BLESS_OF_CRAY);
                    }
                    pc.addMaxHp(80);
                    pc.addMaxMp(10);
                    pc.addWater(30);
                    pc.addAc(-8);
                    break;
            }
            pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
            if (pc.isInParty()) {
                pc.getParty().updateMiniHP(pc);
            }
            pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
            pc.sendPackets(new S_OwnCharStatus2(pc));
            pc.sendPackets(new S_OwnCharAttrDef(pc));
        }
        pc.setSkillEffect(skillId, (time * 1000));
    }

}
