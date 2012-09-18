package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ItemName;

/**
 * 灯类道具 (照明类)
 * 
 * @author jrwz
 */
public class Light implements ItemExecutor {

    public static ItemExecutor get() {
        return new Light();
    }

    private Light() {
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

        if ((item.getRemainingTime() <= 0) && (itemId != 40004)) {
            return;
        }

        if (item.isNowLighting()) {
            item.setNowLighting(false);
            pc.turnOnOffLight();
        } else {
            item.setNowLighting(true);
            pc.turnOnOffLight();
        }

        pc.sendPackets(new S_ItemName(item)); // 更改道具名称
    }
}
