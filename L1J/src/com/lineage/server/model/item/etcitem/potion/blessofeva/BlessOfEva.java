package com.lineage.server.model.item.etcitem.potion.blessofeva;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.item.etcitem.UsePotion_BlessOfEva;

/**
 * 伊娃的祝福 - 40032
 * 
 * @author jrwz
 */
public class BlessOfEva implements ItemExecutor {

    public static ItemExecutor get() {
        return new BlessOfEva();
    }

    private BlessOfEva() {
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

        UsePotion_BlessOfEva.get().useItem(pc, item, 0, 0, 1800, 190);
        // 效果时间 (秒)与动画ID
        // Factory.getPotion().useBlessOfEvaPotion(pc, 1800, 190);

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
