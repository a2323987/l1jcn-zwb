package com.lineage.server.model.item.etcitem.potion.mp;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.item.etcitem.UsePotion_RestorationMp;

/**
 * 蓝色药水 - 40015
 * 
 * @author jrwz
 */
public class BluePotion implements ItemExecutor {

    public static ItemExecutor get() {
        return new BluePotion();
    }

    private BluePotion() {
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

        UsePotion_RestorationMp.get().useItem(pc, item, 0, 0, 600, 190);
        // 效果时间 (秒)与动画ID
        // Factory.getPotion().useBluePotion(pc, 600, 190);

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
