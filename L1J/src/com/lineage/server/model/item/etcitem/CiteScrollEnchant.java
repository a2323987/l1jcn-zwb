package com.lineage.server.model.item.etcitem;

import com.lineage.Config;
import com.lineage.server.datatables.LogEnchantTable;
import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.model.item.ScrollEnchant;
import com.lineage.server.serverpackets.S_ItemStatus;
import com.lineage.server.serverpackets.S_OwnCharAttrDef;
import com.lineage.server.serverpackets.S_SPMR;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Armor;
import com.lineage.server.utils.Random;

/**
 * 强化卷轴效果 (对武器施法的卷轴、对盔甲施法的卷轴、饰品强化卷轴及强化效果)
 * 
 * @author jrwz
 */
public class CiteScrollEnchant implements ScrollEnchant {

    private static ScrollEnchant _instance;

    /**
     * 强化失败
     * 
     * @param pc
     * @param item
     */
    private static void FailureEnchant(final L1PcInstance pc,
            final L1ItemInstance item) {
        final String[] sa = { "", "$245", "$252" }; // ""、蓝色的、银色的
        final int itemType2 = item.getItem().getType2();

        if (item.getEnchantLevel() < 0) { // 强化等级为负值
            sa[itemType2] = "$246"; // 黑色的
        }
        pc.sendPackets(new S_ServerMessage(164, item.getLogName(),
                sa[itemType2])); // \f1%0%s 强烈的发出%1光芒就消失了。
        pc.getInventory().removeItem(item, item.getCount());
    }

    public static ScrollEnchant get() {
        if (_instance == null) {
            _instance = new CiteScrollEnchant();
        }
        return _instance;
    }

    /**
     * 随机强化等级
     * 
     * @param item
     * @param itemId
     */
    private static int RandomELevel(final L1ItemInstance item, final int itemId) {

        switch (itemId) {
            case 140074: // 受祝福的 对盔甲施法的卷轴
            case 140087: // 受祝福的 对武器施法的卷轴
            case 140129: // 奇安的卷轴
            case 140130: // 金侃的卷轴
                if (item.getEnchantLevel() <= 2) {
                    final int j = Random.nextInt(100) + 1;
                    if (j < 32) {
                        return 1;
                    } else if ((j >= 33) && (j <= 76)) {
                        return 2;
                    } else if ((j >= 77) && (j <= 100)) {
                        return 3;
                    }
                } else if ((item.getEnchantLevel() >= 3)
                        && (item.getEnchantLevel() <= 5)) {
                    final int j = Random.nextInt(100) + 1;
                    if (j < 50) {
                        return 2;
                    }
                    return 1;
                }
                break;
        }
        return 1;
    }

    /**
     * 强化成功
     * 
     * @param pc
     * @param item
     * @param i
     */
    private static void SuccessEnchant(final L1PcInstance pc,
            final L1ItemInstance item, final int i) {

        // 取得类型
        final int itemType2 = item.getItem().getType2();

        final String[][] sa = { { "", "", "", "", "" },
                { "$246", "", "$245", "$245", "$245" },
                { "$246", "", "$252", "$252", "$252" } };
        final String[][] sb = { { "", "", "", "", "" },
                { "$247", "", "$247", "$248", "$248" },
                { "$247", "", "$247", "$248", "$248" } };
        final String sa_temp = sa[itemType2][i + 1];
        final String sb_temp = sb[itemType2][i + 1];

        pc.sendPackets(new S_ServerMessage(161, item.getLogName(), sa_temp,
                sb_temp));
        final int oldEnchantLvl = item.getEnchantLevel();
        final int newEnchantLvl = oldEnchantLvl + i;
        final int safe_enchant = item.getItem().get_safeenchant();
        item.setEnchantLevel(newEnchantLvl);
        pc.getInventory().updateItem(item, L1PcInventory.COL_ENCHANTLVL);

        if (newEnchantLvl > safe_enchant) {
            pc.getInventory().saveItem(item, L1PcInventory.COL_ENCHANTLVL);
        }
        if ((item.getItem().getType2() == 1)
                && (Config.LOGGING_WEAPON_ENCHANT != 0)) {
            if ((safe_enchant == 0)
                    || (newEnchantLvl >= Config.LOGGING_WEAPON_ENCHANT)) {
                final LogEnchantTable logenchant = new LogEnchantTable();
                logenchant.storeLogEnchant(pc.getId(), item.getId(),
                        oldEnchantLvl, newEnchantLvl);
            }
        } else if ((item.getItem().getType2() == 2)
                && (Config.LOGGING_ARMOR_ENCHANT != 0)) {
            if ((safe_enchant == 0)
                    || (newEnchantLvl >= Config.LOGGING_ARMOR_ENCHANT)) {
                final LogEnchantTable logenchant = new LogEnchantTable();
                logenchant.storeLogEnchant(pc.getId(), item.getId(),
                        oldEnchantLvl, newEnchantLvl);
            }
        }

        // 防具类
        if (item.getItem().getType2() == 2) {
            if (item.isEquipped()) {
                if (((item.getItem().getType() < 8) || (item.getItem()
                        .getType() > 12))) {
                    pc.addAc(-i);
                }
                final int armorId = item.getItem().getItemId();
                // 强化等级+1，魔防+1
                final int[] i1 = { 20011, 20110, 21123, 21124, 21125, 21126,
                        120011 };
                // 抗魔法头盔、抗魔法链甲、林德拜尔的XX、受祝福的 抗魔法头盔
                for (final int element : i1) {
                    if (armorId == element) {
                        pc.addMr(i);
                        pc.sendPackets(new S_SPMR(pc));
                        break;
                    }
                }
                // 强化等级+1，魔防+2
                final int[] i2 = { 20056, 120056, 220056 };
                // 抗魔法斗篷
                for (final int element : i2) {
                    if (armorId == element) {
                        pc.addMr(i * 2);
                        pc.sendPackets(new S_SPMR(pc));
                        break;
                    }
                }
            }
            pc.sendPackets(new S_OwnCharAttrDef(pc));
        }
    }

    @Override
    public void scrollOfEnchantAccessory(final L1PcInstance pc,
            final L1ItemInstance l1iteminstance,
            final L1ItemInstance l1iteminstance1) {

        // 无法使用的类型
        if ((l1iteminstance1 == null) || (l1iteminstance1.getBless() >= 128) // 封印中
                || ((l1iteminstance1.getItem().getType2() != 2 // 不是装备
                        )
                        || (l1iteminstance1.getItem().getType() < 8 // 8:卷轴
                                                                    // 9:任务物品
                                                                    // 10:魔法书
                        ) || (l1iteminstance1.getItem().getType() > 12 // 11:宠物装备
                                                                       // 12:其他
                        ) || (l1iteminstance1.getItem().getGrade() == -1))) { // 封印中
            pc.sendPackets(new S_ServerMessage(79));
            return;
        }

        // 加成等级
        final int enchant_level = l1iteminstance1.getEnchantLevel();

        // 强化上限 + 10
        if ((enchant_level < 0) || (enchant_level >= 10)) {
            pc.sendPackets(new S_ServerMessage(79));
            return;
        }

        final int rnd = Random.nextInt(100) + 1;
        int enchant_chance_accessory;
        final int enchant_level_tmp = enchant_level;
        int itemStatus = 0;
        // +6 时额外奖励效果判断
        boolean award = false;
        // 成功率： +0-85% ~ +9-40%
        enchant_chance_accessory = (50 + enchant_level_tmp)
                / (enchant_level_tmp + 1) + 35;

        if (rnd < enchant_chance_accessory) { // 成功
            if (l1iteminstance1.getEnchantLevel() == 5) {
                award = true;
            }
            switch (l1iteminstance1.getItem().getGrade()) {
                case 0: // 上等
                    // 四属性 +1
                    l1iteminstance1
                            .setEarthMr(l1iteminstance1.getEarthMr() + 1);
                    itemStatus += L1PcInventory.COL_EARTHMR;
                    l1iteminstance1.setFireMr(l1iteminstance1.getFireMr() + 1);
                    itemStatus += L1PcInventory.COL_FIREMR;
                    l1iteminstance1
                            .setWaterMr(l1iteminstance1.getWaterMr() + 1);
                    itemStatus += L1PcInventory.COL_WATERMR;
                    l1iteminstance1.setWindMr(l1iteminstance1.getWindMr() + 1);
                    itemStatus += L1PcInventory.COL_WINDMR;
                    // LV6 额外奖励：体力与魔力回复量 +1
                    if (award) {
                        l1iteminstance1.setHpr(l1iteminstance1.getHpr() + 1);
                        itemStatus += L1PcInventory.COL_HPR;
                        l1iteminstance1.setMpr(l1iteminstance1.getMpr() + 1);
                        itemStatus += L1PcInventory.COL_MPR;
                    }
                    // 装备中
                    if (l1iteminstance1.isEquipped()) {
                        pc.addFire(1);
                        pc.addWater(1);
                        pc.addEarth(1);
                        pc.addWind(1);
                    }
                    break;
                case 1: // 中等
                    // HP +2
                    l1iteminstance1.setaddHp(l1iteminstance1.getaddHp() + 2);
                    itemStatus += L1PcInventory.COL_ADDHP;
                    // LV6 额外奖励：魔防 +1
                    if (award) {
                        l1iteminstance1
                                .setM_Def(l1iteminstance1.getM_Def() + 1);
                        itemStatus += L1PcInventory.COL_M_DEF;
                    }
                    // 装备中
                    if (l1iteminstance1.isEquipped()) {
                        pc.addMaxHp(2);
                        if (award) {
                            pc.addMr(1);
                            pc.sendPackets(new S_SPMR(pc));
                        }
                    }
                    break;
                case 2: // 初等
                    // MP +1
                    l1iteminstance1.setaddMp(l1iteminstance1.getaddMp() + 1);
                    itemStatus += L1PcInventory.COL_ADDMP;
                    // LV6 额外奖励：魔攻 +1
                    if (award) {
                        l1iteminstance1
                                .setaddSp(l1iteminstance1.getaddSp() + 1);
                        itemStatus += L1PcInventory.COL_ADDSP;
                    }
                    // 装备中
                    if (l1iteminstance1.isEquipped()) {
                        pc.addMaxMp(1);
                        if (award) {
                            pc.addSp(1);
                            pc.sendPackets(new S_SPMR(pc));
                        }
                    }
                    break;
                case 3: // 特等
                    // 功能台版未实装。
                    break;
                default:
                    pc.sendPackets(new S_ServerMessage(79));
                    return;
            }
        } else { // 饰品强化失败
            FailureEnchant(pc, l1iteminstance1);
            pc.getInventory().removeItem(l1iteminstance, 1);
            return;
        }
        SuccessEnchant(pc, l1iteminstance1, 1);
        // 更新
        pc.sendPackets(new S_ItemStatus(l1iteminstance1));
        pc.getInventory().saveEnchantAccessory(l1iteminstance1, itemStatus);
        // 删除
        pc.getInventory().removeItem(l1iteminstance, 1);
    }

    @Override
    public void scrollOfEnchantArmor(final L1PcInstance pc,
            final L1ItemInstance l1iteminstance,
            final L1ItemInstance l1iteminstance1) {

        // 无法使用的类型
        if ((l1iteminstance1 == null)
                || (l1iteminstance1.getItem().getType2() != 2)
                || (l1iteminstance1.getBless() >= 128)) {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            return;
        }

        // 安定值
        final int safe_enchant = ((L1Armor) l1iteminstance1.getItem())
                .get_safeenchant();
        // 无法使用的类型
        if (safe_enchant < 0) {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            return;
        }

        // 装备ID
        final int armorId = l1iteminstance1.getItem().getItemId();
        // 象牙塔装备
        if ((armorId == 20028) || (armorId == 20082) || (armorId == 20126)
                || (armorId == 20173) || (armorId == 20206)
                || (armorId == 20232) || (armorId == 21138)
                || (armorId == 21051) || (armorId == 21052)
                || (armorId == 21053) || (armorId == 21054)
                || (armorId == 21055) || (armorId == 21056)
                || (armorId == 21140) || (armorId == 21141)) {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            return;
        }

        // 道具ID
        final int itemId = l1iteminstance.getItem().getItemId();
        // 幻象装备
        if ((armorId == 20161) || ((armorId >= 21035) && (armorId <= 21038))) {
            // 非对盔甲施法的幻象卷轴
            if (itemId != 40127) {
                pc.sendPackets(new S_ServerMessage(79));
                return;
            }
        }

        // 对盔甲施法的幻象卷轴
        if (itemId == 40127) {
            // 非幻象装备
            if ((armorId != 20161) && ((armorId < 21035) || (armorId > 21038))) {
                pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
                return;
            }
        }

        // 强化等级
        final int enchant_level = l1iteminstance1.getEnchantLevel();

        // 受咀咒的 对盔甲施法的卷轴
        if (itemId == L1ItemId.C_SCROLL_OF_ENCHANT_ARMOR) {
            pc.getInventory().removeItem(l1iteminstance, 1);
            if (enchant_level < -6) {
                // -7以上失败。
                FailureEnchant(pc, l1iteminstance1);
            } else {
                SuccessEnchant(pc, l1iteminstance1, -1);
            }
        }

        // 强化等级小于安定值
        else if (enchant_level < safe_enchant) {
            pc.getInventory().removeItem(l1iteminstance, 1);
            SuccessEnchant(pc, l1iteminstance1,
                    RandomELevel(l1iteminstance1, itemId));
        } else {
            pc.getInventory().removeItem(l1iteminstance, 1);
            final int rnd = Random.nextInt(100) + 1;
            int enchant_chance_armor;
            int enchant_level_tmp;
            if (safe_enchant == 0) { // 骨、黑色米索莉用补正
                enchant_level_tmp = enchant_level + 2;
            } else {
                enchant_level_tmp = enchant_level;
            }
            if (enchant_level >= 9) {
                enchant_chance_armor = (100 + enchant_level_tmp
                        * Config.ENCHANT_CHANCE_ARMOR)
                        / (enchant_level_tmp * 2);
            } else {
                enchant_chance_armor = (100 + enchant_level_tmp
                        * Config.ENCHANT_CHANCE_ARMOR)
                        / enchant_level_tmp;
            }

            if (rnd < enchant_chance_armor) {
                final int randomEnchantLevel = RandomELevel(l1iteminstance1,
                        itemId);
                SuccessEnchant(pc, l1iteminstance1, randomEnchantLevel);
            } else if ((enchant_level >= 9)
                    && (rnd < (enchant_chance_armor * 2))) {
                // \f1%0%s 持续发出 产生激烈的 银色的 光芒，但是没有任何事情发生。
                pc.sendPackets(new S_ServerMessage(160, l1iteminstance1
                        .getLogName(), "$252", "$248"));
            } else {
                FailureEnchant(pc, l1iteminstance1);
            }
        }
    }

    @Override
    public void scrollOfEnchantArmorIvoryTower(final L1PcInstance pc,
            final L1ItemInstance l1iteminstance,
            final L1ItemInstance l1iteminstance1) {

        // 装备ID
        final int armorId = l1iteminstance1.getItem().getItemId();

        // 无法使用的状态
        if ((l1iteminstance1 == null) // 为空
                || (l1iteminstance1.getItem().getType2() != 2) // 不是装备
                || (l1iteminstance1.getBless() >= 128)) { // 封印中
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            return;
        }

        // 非象牙塔、泡水装备
        if ((armorId != 20028) && (armorId != 20082) && (armorId != 20126)
                && (armorId != 20173) && (armorId != 20206)
                && (armorId != 20232) && (armorId != 21138)
                && (armorId != 21051) && (armorId != 21052)
                && (armorId != 21053) && (armorId != 21054)
                && (armorId != 21055) && (armorId != 21056)
                && (armorId != 21140) && (armorId != 21141)) {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            return;
        }

        // 安定值
        final int safe_enchant = l1iteminstance1.getItem().get_safeenchant();

        //
        if (l1iteminstance1.getEnchantLevel() < safe_enchant) {
            pc.getInventory().removeItem(l1iteminstance, 1);
            SuccessEnchant(pc, l1iteminstance1, 1);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }

    @Override
    public void scrollOfEnchantWeapon(final L1PcInstance pc,
            final L1ItemInstance l1iteminstance,
            final L1ItemInstance l1iteminstance1) {

        // 无法使用的类型
        if ((l1iteminstance1 == null) // 为空
                || (l1iteminstance1.getItem().getType2() != 1) // 不是武器
                || (l1iteminstance1.getBless() >= 128)) { // 封印状态
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            return;
        }

        // 安定值
        final int safe_enchant = l1iteminstance1.getItem().get_safeenchant();
        // 无法使用的类型
        if (safe_enchant < 0) { // 安定值小于0
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            return;
        }

        // 武器ID
        final int weaponId = l1iteminstance1.getItem().getItemId();
        // 象牙塔装备
        if ((weaponId == 7) || (weaponId == 35) || (weaponId == 48)
                || (weaponId == 73) || (weaponId == 105) || (weaponId == 120)
                || (weaponId == 147) || (weaponId == 156) || (weaponId == 174)
                || (weaponId == 175) || (weaponId == 224)) {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            return;
        }

        // 道具ID
        final int itemId = l1iteminstance.getItem().getItemId();
        // 试炼之剑
        if ((weaponId >= 246) && (weaponId <= 249)) {
            if (itemId != L1ItemId.SCROLL_OF_ENCHANT_QUEST_WEAPON) { // 非试炼卷轴
                pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
                return;
            }
        }

        // 试炼卷轴
        if (itemId == L1ItemId.SCROLL_OF_ENCHANT_QUEST_WEAPON) {
            // 非试炼之剑
            if ((weaponId < 246) || (weaponId > 249)) {
                pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
                return;
            }
        }

        // 幻象武器
        if ((weaponId == 36) || (weaponId == 183)
                || ((weaponId >= 250) && (weaponId <= 255))) {
            // 非对武器施法的幻象卷轴
            if (itemId != 40128) {
                pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
                return;
            }
        }

        // 对武器施法的幻象卷轴
        if (itemId == 40128) {
            if ((weaponId != 36) && (weaponId != 183)
                    && ((weaponId < 250) || (weaponId > 255))) { // 非幻象武器
                pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
                return;
            }
        }

        // 强化等级
        final int enchant_level = l1iteminstance1.getEnchantLevel();

        // 受咀咒的 对武器施法的卷轴
        if (itemId == L1ItemId.C_SCROLL_OF_ENCHANT_WEAPON) {
            pc.getInventory().removeItem(l1iteminstance, 1);
            if (enchant_level < -6) {
                // -7以上失败。
                FailureEnchant(pc, l1iteminstance1);
            } else {
                SuccessEnchant(pc, l1iteminstance1, -1);
            }
        }

        // 强化等级小于安定值
        else if (enchant_level < safe_enchant) {
            pc.getInventory().removeItem(l1iteminstance, 1);
            SuccessEnchant(pc, l1iteminstance1,
                    RandomELevel(l1iteminstance1, itemId));
        } else {
            pc.getInventory().removeItem(l1iteminstance, 1);

            final int rnd = Random.nextInt(100) + 1;
            int enchant_chance_wepon;
            if (enchant_level >= 9) {
                enchant_chance_wepon = (100 + 3 * Config.ENCHANT_CHANCE_WEAPON) / 6;
            } else {
                enchant_chance_wepon = (100 + 3 * Config.ENCHANT_CHANCE_WEAPON) / 3;
            }

            if (rnd < enchant_chance_wepon) {
                final int randomEnchantLevel = RandomELevel(l1iteminstance1,
                        itemId);
                SuccessEnchant(pc, l1iteminstance1, randomEnchantLevel);
            } else if ((enchant_level >= 9)
                    && (rnd < (enchant_chance_wepon * 2))) {
                // \f1%0%s 持续发出 产生激烈的 蓝色的 光芒，但是没有任何事情发生。
                pc.sendPackets(new S_ServerMessage(160, l1iteminstance1
                        .getLogName(), "$245", "$248"));
            } else {
                FailureEnchant(pc, l1iteminstance1);
            }
        }
    }

    @Override
    public void scrollOfEnchantWeaponAttr(final L1PcInstance pc,
            final L1ItemInstance l1iteminstance,
            final L1ItemInstance l1iteminstance1) {

        // 无法使用的类型
        if ((l1iteminstance1 == null) // 为空
                || (l1iteminstance1.getItem().getType2() != 1) // 不是武器
                || (l1iteminstance1.getBless() >= 128)) { // 被封印
            pc.sendPackets(new S_ServerMessage(79));
            return;
        }
        if (l1iteminstance1.getItem().get_safeenchant() < 0) { // 强化不可
            pc.sendPackets(new S_ServerMessage(1453)); // 此装备无法使用强化。
            return;
        }

        // 0:无属性 1:地 2:火 4:水 8:风
        final int oldAttrEnchantKind = l1iteminstance1.getAttrEnchantKind();
        final int oldAttrEnchantLevel = l1iteminstance1.getAttrEnchantLevel();
        // 道具ID
        final int itemId = l1iteminstance.getItem().getItemId();
        boolean isSameAttr = false;
        if (((itemId == 41429) && (oldAttrEnchantKind == 8))
                || ((itemId == 41430) && (oldAttrEnchantKind == 1))
                || ((itemId == 41431) && (oldAttrEnchantKind == 4))
                || ((itemId == 41432) && (oldAttrEnchantKind == 2))) { // 同属性
            isSameAttr = true;
        }
        if (isSameAttr && (oldAttrEnchantLevel >= 3)) { // 最高强化次数
            pc.sendPackets(new S_ServerMessage(1453)); // 此装备无法使用强化。
            return;
        }

        final int rnd = Random.nextInt(100) + 1;
        if (Config.ATTR_ENCHANT_CHANCE >= rnd) {
            pc.sendPackets(new S_ServerMessage(1410, l1iteminstance1
                    .getLogName())); // 对\f1%0附加强大的魔法力量成功。
            int newAttrEnchantKind = 0;
            int newAttrEnchantLevel = 0;
            if (isSameAttr) { // 属性相同 +1
                newAttrEnchantLevel = oldAttrEnchantLevel + 1;
            } else { // 属性不同 1
                newAttrEnchantLevel = 1;
            }
            if (itemId == 41429) { // 风之武器强化卷轴
                newAttrEnchantKind = 8;
            } else if (itemId == 41430) { // 地之武器强化卷轴
                newAttrEnchantKind = 1;
            } else if (itemId == 41431) { // 水之武器强化卷轴
                newAttrEnchantKind = 4;
            } else if (itemId == 41432) { // 火之武器强化卷轴
                newAttrEnchantKind = 2;
            }
            l1iteminstance1.setAttrEnchantKind(newAttrEnchantKind);
            pc.getInventory().updateItem(l1iteminstance1,
                    L1PcInventory.COL_ATTR_ENCHANT_KIND);
            pc.getInventory().saveItem(l1iteminstance1,
                    L1PcInventory.COL_ATTR_ENCHANT_KIND);
            l1iteminstance1.setAttrEnchantLevel(newAttrEnchantLevel);
            pc.getInventory().updateItem(l1iteminstance1,
                    L1PcInventory.COL_ATTR_ENCHANT_LEVEL);
            pc.getInventory().saveItem(l1iteminstance1,
                    L1PcInventory.COL_ATTR_ENCHANT_LEVEL);
        } else {
            pc.sendPackets(new S_ServerMessage(1411, l1iteminstance1
                    .getLogName())); // 对\f1%0附加魔法失败。
        }
        pc.getInventory().removeItem(l1iteminstance, 1);
    }

    @Override
    public void scrollOfEnchantWeaponIvoryTower(final L1PcInstance pc,
            final L1ItemInstance l1iteminstance,
            final L1ItemInstance l1iteminstance1) {

        // 无法使用的类型
        if ((l1iteminstance1 == null) // 为空
                || (l1iteminstance1.getItem().getType2() != 1)) { // 不是武器
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            return;
        }

        // 封印中强化不可
        if (l1iteminstance1.getBless() >= 128) {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            return;
        }

        // 道具ID
        final int weaponId = l1iteminstance1.getItem().getItemId();
        // 非象牙塔装备
        if ((weaponId != 7) && (weaponId != 35) && (weaponId != 48)
                && (weaponId != 73) && (weaponId != 105) && (weaponId != 120)
                && (weaponId != 147) && (weaponId != 156) && (weaponId != 174)
                && (weaponId != 175) && (weaponId != 224)) {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            return;
        }

        // 安定值
        final int safe_enchant = l1iteminstance1.getItem().get_safeenchant();
        if (l1iteminstance1.getEnchantLevel() < safe_enchant) {
            pc.getInventory().removeItem(l1iteminstance, 1);
            SuccessEnchant(pc, l1iteminstance1, 1);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
