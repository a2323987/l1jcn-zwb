package com.lineage.server.model.item.etcitem.potion;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_Liquor;

/**
 * 酒 - 40858
 * 
 * @author jrwz
 */
public class Liquor implements ItemExecutor {

    public static ItemExecutor get() {
        return new Liquor();
    }

    private Liquor() {
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

        // 醉酒状态开
        pc.setDrink(true);
        pc.sendPackets(new S_Liquor(pc.getId(), 1));

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
