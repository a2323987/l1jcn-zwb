package com.lineage.server.model.item.etcitem.scroll;

import com.lineage.Config;
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Item;
import com.lineage.server.utils.Random;

/**
 * 近战附魔转换卷轴 - 47049 <br>
 * 远攻附魔转换卷轴 - 47050 <br>
 * 恢复附魔转换卷轴 - 47051 <br>
 * 防御附魔转换卷轴 - 47052 <br>
 * 
 * @author jrwz
 */
public class EnchantConversion implements ItemExecutor {

    public static ItemExecutor get() {
        return new EnchantConversion();
    }

    private EnchantConversion() {
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
        final int itemobj = data[0];
        final L1ItemInstance l1iteminstance1 = pc.getInventory().getItem(
                itemobj);
        if ((l1iteminstance1.getItemId() >= 47053)
                && (l1iteminstance1.getItemId() <= 47062)) { // 附魔石 ~ 9阶附魔石
            final int type = (itemId - 47048) * 10; // type = 10,20,30,40
            final int rnd = Random.nextInt(100) + 1;
            if (Config.MAGIC_STONE_TYPE < rnd) {
                final int newItem = l1iteminstance1.getItemId() + type; // 附魔石(近战)
                                                                        // ~
                                                                        // 9阶附魔石(近战)
                final L1Item template = ItemTable.getInstance().getTemplate(
                        newItem);
                if (template == null) {
                    pc.sendPackets(new S_ServerMessage(79));
                }
                pc.createNewItem(pc, newItem, 1); // 获得附魔石(XX)
            } else {
                pc.sendPackets(new S_ServerMessage(1411, l1iteminstance1
                        .getName())); // 对\f1%0附加魔法失败。
            }
            pc.getInventory().removeItem(l1iteminstance1, 1); // 删除 - 附魔石
            pc.getInventory().removeItem(item, 1); // 删除 - 附魔转换卷轴
        } else {
            pc.sendPackets(new S_ServerMessage(79));
        }
    }
}
