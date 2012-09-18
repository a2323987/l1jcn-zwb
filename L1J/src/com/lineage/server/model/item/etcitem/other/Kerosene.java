package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ItemName;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 灯油 - 40003
 * 
 * @author jrwz
 */
public class Kerosene implements ItemExecutor {

    public static ItemExecutor get() {
        return new Kerosene();
    }

    private Kerosene() {
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

        for (final L1ItemInstance lightItem : pc.getInventory().getItems()) {
            if (lightItem.getItem().getItemId() == 40002) { // 灯笼
                lightItem.setRemainingTime(item.getItem().getLightFuel());
                pc.sendPackets(new S_ItemName(lightItem));
                pc.sendPackets(new S_ServerMessage(230)); // 你在灯笼里加满了新的灯油。
                break;
            }
        }
        pc.getInventory().removeItem(item, 1);
    }
}
