package com.lineage.server.model.item.etcitem.potion.black;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.item.etcitem.UsePotion_Blind;

/**
 * 黑色药水 - 40025
 * 
 * @author jrwz
 */
public class BlackPotion implements ItemExecutor {

    public static ItemExecutor get() {
        return new BlackPotion();
    }

    private BlackPotion() {
    }

    /**
     * 道具执行
     * 
     * @param data
     *            参数
     * @param pc
     *            对象
     * @param item
     *            道具
     */
    @Override
    public void execute(final int[] data, final L1PcInstance pc,
            final L1ItemInstance item) {

        UsePotion_Blind.get().useItem(pc, item, 0, 0, 16, 0);
        // 效果时间 (秒)
        // Factory.getPotion().useBlindPotion(pc, 16);

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
