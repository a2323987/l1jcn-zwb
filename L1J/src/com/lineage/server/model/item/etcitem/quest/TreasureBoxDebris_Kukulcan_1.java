package com.lineage.server.model.item.etcitem.quest;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 库库尔坎初级宝箱碎片(下) - 49317
 * 
 * @author jrwz
 */
public class TreasureBoxDebris_Kukulcan_1 implements ItemExecutor {

    public static ItemExecutor get() {
        return new TreasureBoxDebris_Kukulcan_1();
    }

    private TreasureBoxDebris_Kukulcan_1() {
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

        final int itemobj = data[0];
        final L1ItemInstance l1iteminstance1 = pc.getInventory().getItem(
                itemobj);

        if (l1iteminstance1.getItem().getItemId() == 49316) {
            pc.getInventory().consumeItem(49316, 1);
            pc.getInventory().consumeItem(49317, 1);
            pc.createNewItem(pc, 49318, 1);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
