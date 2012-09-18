package com.lineage.server.model.item.etcitem.potion.mp;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.item.etcitem.UsePotion_RestorationMp;

/**
 * 福利蓝色药水 - 49306
 * 
 * @author jrwz
 */
public class WelfareBluePotion implements ItemExecutor {

    public static ItemExecutor get() {
        return new WelfareBluePotion();
    }

    private WelfareBluePotion() {
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

        UsePotion_RestorationMp.get().useItem(pc, item, 0, 0, 2400, 190);
        // 效果时间 (秒)与动画ID
        // Factory.getPotion().useBluePotion(pc, 2400, 190);

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
