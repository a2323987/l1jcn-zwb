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
package com.lineage.server.model;

import static com.lineage.server.model.skill.L1SkillId.AWAKEN_ANTHARAS;
import static com.lineage.server.model.skill.L1SkillId.AWAKEN_FAFURION;
import static com.lineage.server.model.skill.L1SkillId.AWAKEN_VALAKAS;
import static com.lineage.server.model.skill.L1SkillId.SHAPE_CHANGE;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ChangeShape;
import com.lineage.server.serverpackets.S_HPUpdate;
import com.lineage.server.serverpackets.S_OwnCharAttrDef;
import com.lineage.server.serverpackets.S_OwnCharStatus2;
import com.lineage.server.serverpackets.S_SPMR;

// Referenced classes of package com.lineage.server.model:
// L1Cooking

/**
 * 觉醒
 */
public class L1Awake {

    /** 觉醒变身 */
    public static void doPoly(final L1PcInstance pc) {
        final int polyId = 6894;
        if (pc.hasSkillEffect(SHAPE_CHANGE)) {
            pc.killSkillEffectTimer(SHAPE_CHANGE);
        }
        final L1ItemInstance weapon = pc.getWeapon();
        final boolean weaponTakeoff = ((weapon != null) && !L1PolyMorph
                .isEquipableWeapon(polyId, weapon.getItem().getType()));
        if (weaponTakeoff) { // 解除武器时
            pc.setCurrentWeapon(0);
        }
        pc.setTempCharGfx(polyId);
        pc.sendPackets(new S_ChangeShape(pc.getId(), polyId, pc
                .getCurrentWeapon()));
        if (pc.isGmInvis()) { // GM隐身
        } else if (pc.isInvisble()) { // 一般隐身
            pc.broadcastPacketForFindInvis(new S_ChangeShape(pc.getId(),
                    polyId, pc.getCurrentWeapon()), true);
        } else {
            pc.broadcastPacket(new S_ChangeShape(pc.getId(), polyId, pc
                    .getCurrentWeapon()));
        }
        pc.getInventory().takeoffEquip(polyId); // 是否将装备的武器强制解除。
    }

    /** 开始觉醒 */
    public static void start(final L1PcInstance pc, final int skillId) {

        // 再次咏唱时解除觉醒状态
        if (skillId == pc.getAwakeSkillId()) {
            stop(pc);
        } else if (pc.getAwakeSkillId() != 0) { // 无法与其他觉醒状态并存
            return;
        } else {
            if (skillId == AWAKEN_ANTHARAS) { // 觉醒：安塔瑞斯
                pc.addMaxHp(35);
                pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
                if (pc.isInParty()) { // 组队中
                    pc.getParty().updateMiniHP(pc);
                }
                pc.addAc(-8);
                pc.sendPackets(new S_OwnCharStatus2(pc));
            } else if (skillId == AWAKEN_FAFURION) { // 觉醒：法力昂
                pc.addMr(15);
                pc.sendPackets(new S_SPMR(pc));
                pc.addWind(15);
                pc.addWater(15);
                pc.addFire(15);
                pc.addEarth(15);
                pc.sendPackets(new S_OwnCharAttrDef(pc));
            } else if (skillId == AWAKEN_VALAKAS) { // 觉醒：巴拉卡斯
                pc.addStr(3);
                pc.addCon(3);
                pc.addDex(3);
                pc.addCha(3);
                pc.addInt(3);
                pc.addWis(3);
                pc.sendPackets(new S_OwnCharStatus2(pc));
            }
            pc.setAwakeSkillId(skillId);
            doPoly(pc);
            pc.startMpReductionByAwake();
        }
    }

    /** 停止觉醒 */
    public static void stop(final L1PcInstance pc) {
        final int skillId = pc.getAwakeSkillId();
        if (skillId == AWAKEN_ANTHARAS) { // 觉醒：安塔瑞斯
            pc.addMaxHp(-35);
            pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
            if (pc.isInParty()) { // 在组队中
                pc.getParty().updateMiniHP(pc);
            }
            pc.addAc(8);
            pc.sendPackets(new S_OwnCharAttrDef(pc));
        } else if (skillId == AWAKEN_FAFURION) { // 觉醒：法力昂
            pc.addMr(-15);
            pc.addWind(-15);
            pc.addWater(-15);
            pc.addFire(-15);
            pc.addEarth(-15);
            pc.sendPackets(new S_SPMR(pc));
            pc.sendPackets(new S_OwnCharAttrDef(pc));
        } else if (skillId == AWAKEN_VALAKAS) { // 觉醒：巴拉卡斯
            pc.addStr(-3);
            pc.addCon(-3);
            pc.addDex(-3);
            pc.addCha(-3);
            pc.addInt(-3);
            pc.addWis(-3);
            pc.sendPackets(new S_OwnCharStatus2(pc));
        }
        pc.setAwakeSkillId(0);
        undoPoly(pc);
        pc.stopMpReductionByAwake();
    }

    /** 觉醒解除变身 */
    public static void undoPoly(final L1PcInstance pc) {
        final int classId = pc.getClassId();
        pc.setTempCharGfx(classId);
        if (!pc.isDead()) {
            pc.sendPackets(new S_ChangeShape(pc.getId(), classId, pc
                    .getCurrentWeapon()));
            pc.broadcastPacket(new S_ChangeShape(pc.getId(), classId, pc
                    .getCurrentWeapon()));
        }
    }

    private L1Awake() {
    }

}
