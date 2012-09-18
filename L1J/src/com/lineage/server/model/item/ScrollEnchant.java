package com.lineage.server.model.item;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 强化卷轴接口 (对武器施法的卷轴、对盔甲施法的卷轴、饰品强化卷轴及强化效果)
 * 
 * @author jrwz
 */
public interface ScrollEnchant {

    /**
     * 饰品强化卷轴
     * 
     * @param pc
     * @param l1iteminstance
     * @param l1iteminstance1
     */
    void scrollOfEnchantAccessory(L1PcInstance pc,
            L1ItemInstance l1iteminstance, L1ItemInstance l1iteminstance1);

    /**
     * 对盔甲施法的卷轴
     * 
     * @param pc
     * @param l1iteminstance
     * @param l1iteminstance1
     */
    void scrollOfEnchantArmor(L1PcInstance pc, L1ItemInstance l1iteminstance,
            L1ItemInstance l1iteminstance1);

    /**
     * 象牙塔对盔甲施法的卷轴
     * 
     * @param pc
     * @param l1iteminstance
     * @param l1iteminstance1
     */
    void scrollOfEnchantArmorIvoryTower(L1PcInstance pc,
            L1ItemInstance l1iteminstance, L1ItemInstance l1iteminstance1);

    /**
     * 对武器施法的卷轴
     * 
     * @param pc
     * @param l1iteminstance
     * @param l1iteminstance1
     */
    void scrollOfEnchantWeapon(L1PcInstance pc, L1ItemInstance l1iteminstance,
            L1ItemInstance l1iteminstance1);

    /**
     * 武器属性强化卷轴
     * 
     * @param pc
     * @param l1iteminstance
     * @param l1iteminstance1
     */
    void scrollOfEnchantWeaponAttr(L1PcInstance pc,
            L1ItemInstance l1iteminstance, L1ItemInstance l1iteminstance1);

    /**
     * 象牙塔对武器施法的卷轴
     * 
     * @param pc
     * @param l1iteminstance
     * @param l1iteminstance1
     */
    void scrollOfEnchantWeaponIvoryTower(L1PcInstance pc,
            L1ItemInstance l1iteminstance, L1ItemInstance l1iteminstance1);

}
