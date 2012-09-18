package com.lineage.server.model.item.etcitem.potion.magicattack;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.item.etcitem.UsePotion_Wisdom;

/**
 * 福利慎重药水 - 49307
 * 
 * @author jrwz
 */
public class WelfareCarefulPotion implements ItemExecutor {

    public static ItemExecutor get() {
        return new WelfareCarefulPotion();
    }

    private WelfareCarefulPotion() {
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

        // 法师
        if (pc.isWizard()) {
            UsePotion_Wisdom.get().useItem(pc, item, 0, 0, 1000, 750);
            // 效果时间 (秒)与动画ID
            // Factory.getPotion().useWisdomPotion(pc, 1000, 750);
        }

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
