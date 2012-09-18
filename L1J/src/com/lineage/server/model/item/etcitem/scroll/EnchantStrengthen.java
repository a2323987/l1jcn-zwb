package com.lineage.server.model.item.etcitem.scroll;

import com.lineage.Config;
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Item;
import com.lineage.server.utils.Random;

/**
 * 附魔强化卷轴 - 47048 <br>
 * 
 * @author jrwz
 */
public class EnchantStrengthen implements ItemExecutor {

    public static ItemExecutor get() {
        return new EnchantStrengthen();
    }

    private EnchantStrengthen() {
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
        final int item_id = l1iteminstance1.getItemId();
        if ((item_id < 47053) || (item_id > 47102) || (item_id == 47062)
                || (item_id == 47072) || (item_id == 47082)
                || (item_id == 47092) || (item_id == 47102)) {
            pc.sendPackets(new S_ServerMessage(79));
            return;
        }

        final int rnd = Random.nextInt(100) + 1;
        if ((Config.MAGIC_STONE_LEVEL < rnd)
                || ((item_id >= 47053) && (item_id <= 47056))
                || ((item_id >= 47063) && (item_id <= 47066))
                || ((item_id >= 47073) && (item_id <= 47076))
                || ((item_id >= 47083) && (item_id <= 47086))
                || ((item_id >= 47093) && (item_id <= 47096))) {
            final int newItem = l1iteminstance1.getItemId() + 1; // X 阶附魔石 ->
                                                                 // X+1 阶附魔石
            final L1Item template = ItemTable.getInstance()
                    .getTemplate(newItem);
            if (template == null) {
                pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生。
                return;
            }
            pc.sendPackets(new S_ServerMessage(1410, l1iteminstance1.getName())); // 对\f1%0附加强大的魔法力量成功。

            l1iteminstance1.setItem(template);
            pc.getInventory().updateItem(l1iteminstance1,
                    L1PcInventory.COL_ITEMID);
            pc.getInventory().saveItem(l1iteminstance1,
                    L1PcInventory.COL_ITEMID);
        } else {
            pc.sendPackets(new S_ServerMessage(1411, l1iteminstance1.getName())); // 对\f1%0附加魔法失败。
            pc.getInventory().removeItem(l1iteminstance1, 1);
        }
        pc.getInventory().removeItem(item, 1);
    }
}
