package com.lineage.server.model.item.etcitem.potion.speed;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.item.etcitem.UseSpeedPotion_1;

/**
 * 强化 绿色药水 - 40018
 * 
 * @author jrwz
 */
public class S_GreenPotion implements ItemExecutor {

    public static ItemExecutor get() {
        return new S_GreenPotion();
    }

    private S_GreenPotion() {
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

        UseSpeedPotion_1.get().useItem(pc, item, 0, 0, 1800, 191);

        // 效果时间 (秒)与动画ID
        // Factory.getPotion().useGreenPotion(pc, 1800, 191);

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
