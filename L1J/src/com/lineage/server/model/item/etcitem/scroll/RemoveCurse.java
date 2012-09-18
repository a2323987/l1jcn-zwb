package com.lineage.server.model.item.etcitem.scroll;

import com.lineage.server.datatables.ItemTable;
import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Item;

/**
 * 象牙塔解咒卷轴 - 40097 <br>
 * 解除咀咒的卷轴 - 40119 <br>
 * 受祝福的 解除咀咒的卷轴 - 140119 <br>
 * 受祝福的 原住民图腾 - 140329 <br>
 * 
 * @author jrwz
 */
public class RemoveCurse implements ItemExecutor {

    public static ItemExecutor get() {
        return new RemoveCurse();
    }

    private RemoveCurse() {
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

        for (final L1ItemInstance eachItem : pc.getInventory().getItems()) {
            if ((eachItem.getItem().getBless() != 2)
                    && (eachItem.getItem().getBless() != 130)) {
                continue;
            }

            // 装备中才可解除咀咒
            if (!eachItem.isEquipped()
                    && ((itemId == 40119) || (itemId == 40097))) {
                continue;
            }
            final int id_normal = eachItem.getItemId() - 200000;
            final L1Item template = ItemTable.getInstance().getTemplate(
                    id_normal);
            if (template == null) {
                continue;
            }
            if (eachItem.getBless() == 130) { // 封印中的咀咒装备
                eachItem.setBless(129);
            } else { // 未封印的咀咒装备
                eachItem.setBless(1);
            }
            if (pc.getInventory().checkItem(id_normal)
                    && template.isStackable()) {
                pc.getInventory().storeItem(id_normal, eachItem.getCount());
                pc.getInventory().removeItem(eachItem, eachItem.getCount());
            } else {
                eachItem.setItem(template);
                pc.getInventory()
                        .updateItem(eachItem, L1PcInventory.COL_ITEMID);
                pc.getInventory().saveItem(eachItem, L1PcInventory.COL_ITEMID);
            }
        }
        pc.getInventory().removeItem(item, 1);
        pc.sendPackets(new S_ServerMessage(155)); // \f1你感觉到似乎有人正在帮助你。
    }
}
