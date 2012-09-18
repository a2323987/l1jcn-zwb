package com.lineage.server.model.item.etcitem.scroll;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.item.etcitem.CiteScrollEnchant;

/**
 * 饰品强化卷轴 - 49148
 * 
 * @author jrwz
 */
public class S_Scroll_Jewelry implements ItemExecutor {

    public static ItemExecutor get() {
        return new S_Scroll_Jewelry();
    }

    private S_Scroll_Jewelry() {
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

        final int targetID = data[0];
        final L1ItemInstance l1iteminstance1 = pc.getInventory().getItem(
                targetID);

        CiteScrollEnchant.get().scrollOfEnchantAccessory(pc, item,
                l1iteminstance1);
    }
}
