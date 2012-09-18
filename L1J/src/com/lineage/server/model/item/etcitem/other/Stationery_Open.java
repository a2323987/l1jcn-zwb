package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_Letter;

/**
 * (未开封) <br>
 * 信纸 - 49016 <br>
 * 血盟的信纸 - 49018 <br>
 * 圣诞卡片 - 49020 <br>
 * 情人节 卡片 - 49022 <br>
 * 白色情人节 卡片 - 49024 <br>
 * 
 * @author jrwz
 */
public class Stationery_Open implements ItemExecutor {

    public static ItemExecutor get() {
        return new Stationery_Open();
    }

    private Stationery_Open() {
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

        final int itemId = item.getItemId();

        pc.sendPackets(new S_Letter(item));
        item.setItemId(itemId + 1);
        pc.getInventory().updateItem(item, L1PcInventory.COL_ITEMID);
        pc.getInventory().saveItem(item, L1PcInventory.COL_ITEMID);
    }
}
