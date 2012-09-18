package com.lineage.server.model.item.etcitem.potion.speed;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.item.etcitem.UseSpeedPotion_1;

/**
 * 受祝福的 强化 绿色药水 - 140018
 * 
 * @author jrwz
 */
public class B_S_GreenPotion implements ItemExecutor {

    public static ItemExecutor get() {
        return new B_S_GreenPotion();
    }

    private B_S_GreenPotion() {
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

        UseSpeedPotion_1.get().useItem(pc, item, 0, 0, 2100, 191);

        // 效果时间 (秒)与动画ID
        // Factory.getPotion().useGreenPotion(pc, 2100, 191);

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
