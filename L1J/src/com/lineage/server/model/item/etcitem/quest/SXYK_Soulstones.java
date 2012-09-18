package com.lineage.server.model.item.etcitem.quest;

import com.lineage.server.datatables.ItemTable;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 索夏依卡灵魂之石 - 49188
 * 
 * @author jrwz
 */
public class SXYK_Soulstones implements ItemExecutor {

    public static ItemExecutor get() {
        return new SXYK_Soulstones();
    }

    private SXYK_Soulstones() {
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

        // 生锈的笛子
        if (l1iteminstance1.getItem().getItemId() == 49186) {
            // 索夏依卡灵魂之笛
            final L1ItemInstance item1 = ItemTable.getInstance().createItem(
                    49189);
            item1.setCount(1);
            if (pc.getInventory().checkAddItem(item1, 1) == L1Inventory.OK) {
                pc.getInventory().storeItem(item1);
                pc.sendPackets(new S_ServerMessage(403, item1.getLogName()));
                pc.getInventory().removeItem(item, 1);
                pc.getInventory().removeItem(l1iteminstance1, 1);
            }
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
