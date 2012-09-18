package com.lineage.server.model.item;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 道具模组相关
 * 
 * @author jrwz
 */
public interface ItemClass {

    /**
     * 加入Class清单
     * 
     * @param itemid
     *            道具编号
     * @param className
     *            执行位置
     * @param mode
     *            0 if L1EtcItem, 1 if L1Weapon, 2 if L1Armor
     */
    public abstract void addList(final int itemid, final String className,
            final int mode);

    /**
     * 道具的执行
     * 
     * @param data
     *            资料
     * @param pc
     *            对象
     * @param item
     *            道具
     */
    public abstract void item(final int[] data, final L1PcInstance pc,
            final L1ItemInstance item);

    /**
     * 防具的执行
     * 
     * @param equipped
     *            装备 (使用)
     * @param pc
     *            对象
     * @param item
     *            防具
     */
    public abstract void item_armor(final boolean equipped,
            final L1PcInstance pc, final L1ItemInstance item);

    /**
     * 武器的执行
     * 
     * @param equipped
     *            装备 (使用)
     * @param pc
     *            对象
     * @param item
     *            武器
     */
    public abstract void item_weapon(final boolean equipped,
            final L1PcInstance pc, final L1ItemInstance item);

}
