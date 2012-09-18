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
import static com.lineage.server.model.skill.L1SkillId.COOKING_WONDER_DRUG;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.serverpackets.S_HPUpdate;
import com.lineage.server.serverpackets.S_MPUpdate;
import com.lineage.server.serverpackets.S_OwnCharAttrDef;
import com.lineage.server.serverpackets.S_OwnCharStatus;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_SPMR;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.model:
// L1Cooking

/**
 * 烹饪(料理)
 */
public class L1Cooking {

    public static void eatCooking(final L1PcInstance pc, final int cookingId,
            final int time) {
        int cookingType = 0;
        if ((cookingId == COOKING_1_0_N) || (cookingId == COOKING_1_0_S)) { // 漂浮之眼肉排
            cookingType = 0;
            pc.addWind(10);
            pc.addWater(10);
            pc.addFire(10);
            pc.addEarth(10);
            pc.sendPackets(new S_OwnCharAttrDef(pc));
        } else if ((cookingId == COOKING_1_1_N) || (cookingId == COOKING_1_1_S)) { // 烤熊肉
            cookingType = 1;
            pc.addMaxHp(30);
            pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
            if (pc.isInParty()) { // 组队中
                pc.getParty().updateMiniHP(pc);
            }
        } else if ((cookingId == COOKING_1_2_N) || (cookingId == COOKING_1_2_S)) { // 煎饼
            cookingType = 2;
        } else if ((cookingId == COOKING_1_3_N) || (cookingId == COOKING_1_3_S)) { // 烤蚂蚁腿起司
            cookingType = 3;
            pc.addAc(-1);
            pc.sendPackets(new S_OwnCharStatus(pc));
        } else if ((cookingId == COOKING_1_4_N) || (cookingId == COOKING_1_4_S)) { // 水果沙拉
            cookingType = 4;
            pc.addMaxMp(20);
            pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
        } else if ((cookingId == COOKING_1_5_N) || (cookingId == COOKING_1_5_S)) { // 水果糖醋肉
            cookingType = 5;
        } else if ((cookingId == COOKING_1_6_N) || (cookingId == COOKING_1_6_S)) { // 烤山猪肉串
            cookingType = 6;
            pc.addMr(5);
            pc.sendPackets(new S_SPMR(pc));
        } else if ((cookingId == COOKING_1_7_N) || (cookingId == COOKING_1_7_S)) { // 蘑菇汤
            cookingType = 7;
        } else if ((cookingId == COOKING_2_0_N) || (cookingId == COOKING_2_0_S)) { // 鱼子酱
            cookingType = 8;
        } else if ((cookingId == COOKING_2_1_N) || (cookingId == COOKING_2_1_S)) { // 鳄鱼肉排
            cookingType = 9;
            pc.addMaxHp(30);
            pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
            if (pc.isInParty()) { // 组队中
                pc.getParty().updateMiniHP(pc);
            }
            pc.addMaxMp(30);
            pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
        } else if ((cookingId == COOKING_2_2_N) || (cookingId == COOKING_2_2_S)) { // 龙龟蛋饼干
            cookingType = 10;
            pc.addAc(-2);
            pc.sendPackets(new S_OwnCharStatus(pc));
        } else if ((cookingId == COOKING_2_3_N) || (cookingId == COOKING_2_3_S)) { // 烤奇异鹦鹉
            cookingType = 11;
        } else if ((cookingId == COOKING_2_4_N) || (cookingId == COOKING_2_4_S)) { // 毒蝎串烧
            cookingType = 12;
        } else if ((cookingId == COOKING_2_5_N) || (cookingId == COOKING_2_5_S)) { // 炖伊莱克顿
            cookingType = 13;
            pc.addMr(10);
            pc.sendPackets(new S_SPMR(pc));
        } else if ((cookingId == COOKING_2_6_N) || (cookingId == COOKING_2_6_S)) { // 蜘蛛腿串烧
            cookingType = 14;
            pc.addSp(1);
            pc.sendPackets(new S_SPMR(pc));
        } else if ((cookingId == COOKING_2_7_N) || (cookingId == COOKING_2_7_S)) { // 蟹肉汤
            cookingType = 15;
        } else if ((cookingId == COOKING_3_0_N) || (cookingId == COOKING_3_0_S)) { // 烤奎斯坦修的螯
            cookingType = 16;
        } else if ((cookingId == COOKING_3_1_N) || (cookingId == COOKING_3_1_S)) { // 烤格利芬肉
            cookingType = 17;
            pc.addMaxHp(50);
            pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
            if (pc.isInParty()) { // 组队中
                pc.getParty().updateMiniHP(pc);
            }
            pc.addMaxMp(50);
            pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
        } else if ((cookingId == COOKING_3_2_N) || (cookingId == COOKING_3_2_S)) { // 亚力安的尾巴肉排
            cookingType = 18;
        } else if ((cookingId == COOKING_3_3_N) || (cookingId == COOKING_3_3_S)) { // 烤巨王龟肉
            cookingType = 19;
            pc.addAc(-3);
            pc.sendPackets(new S_OwnCharStatus(pc));
        } else if ((cookingId == COOKING_3_4_N) || (cookingId == COOKING_3_4_S)) { // 幼龙翅膀串烧
            cookingType = 20;
            pc.addMr(15);
            pc.sendPackets(new S_SPMR(pc));
            pc.addWind(10);
            pc.addWater(10);
            pc.addFire(10);
            pc.addEarth(10);
            pc.sendPackets(new S_OwnCharAttrDef(pc));
        } else if ((cookingId == COOKING_3_5_N) || (cookingId == COOKING_3_5_S)) { // 烤飞龙肉
            cookingType = 21;
            pc.addSp(2);
            pc.sendPackets(new S_SPMR(pc));
        } else if ((cookingId == COOKING_3_6_N) || (cookingId == COOKING_3_6_S)) { // 炖深海鱼肉
            cookingType = 22;
            pc.addMaxHp(30);
            pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
            if (pc.isInParty()) { // 组队中
                pc.getParty().updateMiniHP(pc);
            }
        } else if ((cookingId == COOKING_3_7_N) || (cookingId == COOKING_3_7_S)) { // 邪恶蜥蜴蛋汤
            cookingType = 23;
        } else if (cookingId == COOKING_WONDER_DRUG) { // 象牙塔妙药
            cookingType = 54;
            pc.addHpr(10);
            pc.addMpr(2);
        }
        pc.sendPackets(new S_PacketBox(53, cookingType, time));
        pc.setSkillEffect(cookingId, time * 1000);
        if (((cookingId >= COOKING_1_0_N) && (cookingId <= COOKING_1_6_N))
                || ((cookingId >= COOKING_1_0_S) && (cookingId <= COOKING_1_6_S))
                || ((cookingId >= COOKING_2_0_N) && (cookingId <= COOKING_2_6_N))
                || ((cookingId >= COOKING_2_0_S) && (cookingId <= COOKING_2_6_S))
                || ((cookingId >= COOKING_3_0_N) && (cookingId <= COOKING_3_6_N))
                || ((cookingId >= COOKING_3_0_S) && (cookingId <= COOKING_3_6_S))) {
            pc.setCookingId(cookingId);
        } else if ((cookingId == COOKING_1_7_N) || (cookingId == COOKING_1_7_S)
                || (cookingId == COOKING_2_7_N) || (cookingId == COOKING_2_7_S)
                || (cookingId == COOKING_3_7_N) || (cookingId == COOKING_3_7_S)) {
            pc.setDessertId(cookingId);
        }

        // XXX 饥饿度17%再送信。S_PacketBox包含饥饿度更新的代码？
        pc.sendPackets(new S_OwnCharStatus(pc));
    }

    /** 使用料理道具 */
    public static void useCookingItem(final L1PcInstance pc,
            final L1ItemInstance item) {
        final int itemId = item.getItem().getItemId();
        if ((itemId == 41284) || (itemId == 41292) || (itemId == 49056)
                || (itemId == 49064) || (itemId == 49251) || (itemId == 49259)) { // 增加经验值的料理
            if (pc.get_food() != 225) {
                pc.sendPackets(new S_ServerMessage(74, item.getNumberedName(1))); // \f1%0%o
                                                                                  // 无法使用。
                return;
            }
        }

        // 料理 LV1、特别的料理 LV1、料理 LV2、特别的料理 LV2、料理 LV3、特别的料理 LV3 - 不可重复
        if (((itemId >= 41277) && (itemId <= 41283))
                || ((itemId >= 41285) && (itemId <= 41291))
                || ((itemId >= 49049) && (itemId <= 49055))
                || ((itemId >= 49057) && (itemId <= 49063))
                || ((itemId >= 49244) && (itemId <= 49250))
                || ((itemId >= 49252) && (itemId <= 49258))) {
            final int cookingId = pc.getCookingId();
            if (cookingId != 0) {
                pc.removeSkillEffect(cookingId);
            }
        }

        // 蘑菇汤、特别的蘑菇汤、蟹肉汤、特别的蟹肉汤、邪恶蜥蜴蛋汤、特别的邪恶蜥蜴蛋汤 - 不可重复
        if ((itemId == 41284) || (itemId == 41292) || (itemId == 49056)
                || (itemId == 49064) || (itemId == 49251) || (itemId == 49259)) {
            final int dessertId = pc.getDessertId();
            if (dessertId != 0) {
                pc.removeSkillEffect(dessertId);
            }
        }

        int cookingId;
        final int time = 900;
        if ((itemId == 41277) || (itemId == 41285)) { // 漂浮之眼肉排
            if (itemId == 41277) {
                cookingId = COOKING_1_0_N;
            } else {
                cookingId = COOKING_1_0_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 41278) || (itemId == 41286)) { // 烤熊肉
            if (itemId == 41278) {
                cookingId = COOKING_1_1_N;
            } else {
                cookingId = COOKING_1_1_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 41279) || (itemId == 41287)) { // 煎饼
            if (itemId == 41279) {
                cookingId = COOKING_1_2_N;
            } else {
                cookingId = COOKING_1_2_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 41280) || (itemId == 41288)) { // 烤蚂蚁腿起司
            if (itemId == 41280) {
                cookingId = COOKING_1_3_N;
            } else {
                cookingId = COOKING_1_3_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 41281) || (itemId == 41289)) { // 水果沙拉
            if (itemId == 41281) {
                cookingId = COOKING_1_4_N;
            } else {
                cookingId = COOKING_1_4_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 41282) || (itemId == 41290)) { // 水果糖醋肉
            if (itemId == 41282) {
                cookingId = COOKING_1_5_N;
            } else {
                cookingId = COOKING_1_5_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 41283) || (itemId == 41291)) { // 烤山猪肉串
            if (itemId == 41283) {
                cookingId = COOKING_1_6_N;
            } else {
                cookingId = COOKING_1_6_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 41284) || (itemId == 41292)) { // 蘑菇汤
            if (itemId == 41284) {
                cookingId = COOKING_1_7_N;
            } else {
                cookingId = COOKING_1_7_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49049) || (itemId == 49057)) { // 鱼子酱
            if (itemId == 49049) {
                cookingId = COOKING_2_0_N;
            } else {
                cookingId = COOKING_2_0_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49050) || (itemId == 49058)) { // 鳄鱼肉排
            if (itemId == 49050) {
                cookingId = COOKING_2_1_N;
            } else {
                cookingId = COOKING_2_1_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49051) || (itemId == 49059)) { // 龙龟蛋饼干
            if (itemId == 49051) {
                cookingId = COOKING_2_2_N;
            } else {
                cookingId = COOKING_2_2_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49052) || (itemId == 49060)) { // 烤奇异鹦鹉
            if (itemId == 49052) {
                cookingId = COOKING_2_3_N;
            } else {
                cookingId = COOKING_2_3_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49053) || (itemId == 49061)) { // 毒蝎串烧
            if (itemId == 49053) {
                cookingId = COOKING_2_4_N;
            } else {
                cookingId = COOKING_2_4_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49054) || (itemId == 49062)) { // 炖伊莱克顿
            if (itemId == 49054) {
                cookingId = COOKING_2_5_N;
            } else {
                cookingId = COOKING_2_5_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49055) || (itemId == 49063)) { // 蜘蛛腿串烧
            if (itemId == 49055) {
                cookingId = COOKING_2_6_N;
            } else {
                cookingId = COOKING_2_6_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49056) || (itemId == 49064)) { // 蟹肉汤
            if (itemId == 49056) {
                cookingId = COOKING_2_7_N;
            } else {
                cookingId = COOKING_2_7_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49244) || (itemId == 49252)) { // 烤奎斯坦修的螯
            if (itemId == 49244) {
                cookingId = COOKING_3_0_N;
            } else {
                cookingId = COOKING_3_0_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49245) || (itemId == 49253)) { // 烤格利芬肉
            if (itemId == 49245) {
                cookingId = COOKING_3_1_N;
            } else {
                cookingId = COOKING_3_1_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49246) || (itemId == 49254)) { // 亚力安的尾巴肉排
            if (itemId == 49246) {
                cookingId = COOKING_3_2_N;
            } else {
                cookingId = COOKING_3_2_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49247) || (itemId == 49255)) { // 烤巨王龟肉
            if (itemId == 49247) {
                cookingId = COOKING_3_3_N;
            } else {
                cookingId = COOKING_3_3_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49248) || (itemId == 49256)) { // 幼龙翅膀串烧
            if (itemId == 49248) {
                cookingId = COOKING_3_4_N;
            } else {
                cookingId = COOKING_3_4_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49249) || (itemId == 49257)) { // 烤飞龙肉
            if (itemId == 49249) {
                cookingId = COOKING_3_5_N;
            } else {
                cookingId = COOKING_3_5_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49250) || (itemId == 49258)) { // 炖深海鱼肉
            if (itemId == 49250) {
                cookingId = COOKING_3_6_N;
            } else {
                cookingId = COOKING_3_6_S;
            }
            eatCooking(pc, cookingId, time);
        } else if ((itemId == 49251) || (itemId == 49259)) { // 邪恶蜥蜴蛋汤
            if (itemId == 49251) {
                cookingId = COOKING_3_7_N;
            } else {
                cookingId = COOKING_3_7_S;
            }
            eatCooking(pc, cookingId, time);
        } else if (itemId == L1ItemId.POTION_OF_WONDER_DRUG) { // 象牙塔妙药
            cookingId = COOKING_WONDER_DRUG;
            eatCooking(pc, cookingId, time);
        }
        pc.sendPackets(new S_ServerMessage(76, item.getNumberedName(1))); // \f1吃
                                                                          // %0%o。
        pc.getInventory().removeItem(item, 1);
    }

    private L1Cooking() {
    }

}
