package com.lineage.server.model.item.etcitem.potion.speed3;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.item.etcitem.UseSpeedPotion_3;

/**
 * 巧克力蛋糕 - 49138
 * 
 * @author jrwz
 */
public class ChocolateCake implements ItemExecutor {

    public static ItemExecutor get() {
        return new ChocolateCake();
    }

    private ChocolateCake() {
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

        UseSpeedPotion_3.get().useItem(pc, item, 0, 0, 600, 8031);
        // 效果时间 (秒)与动画ID
        // Factory.getPotion().useThirdSpeedPotion(pc, 600, 8031);

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
