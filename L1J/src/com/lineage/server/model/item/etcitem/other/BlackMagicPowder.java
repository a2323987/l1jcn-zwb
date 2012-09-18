package com.lineage.server.model.item.etcitem.other;

import com.lineage.Config;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.Random;

/**
 * 黑魔法粉 - 40964
 * 
 * @author jrwz
 */
public class BlackMagicPowder implements ItemExecutor {

    public static ItemExecutor get() {
        return new BlackMagicPowder();
    }

    private BlackMagicPowder() {
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
        final int historybookId = l1iteminstance1.getItem().getItemId();

        // 封印的历史书１～８页
        if ((historybookId >= 41011) && (41018 >= historybookId)) {
            if ((Random.nextInt(99) + 1) <= Config.CREATE_CHANCE_HISTORY_BOOK) {
                pc.createNewItem(pc, historybookId + 8, 1);
            } else {
                pc.sendPackets(new S_ServerMessage(158, l1iteminstance1
                        .getName())); // \f1%0%s 消失。
            }
            pc.getInventory().removeItem(l1iteminstance1, 1);
            pc.getInventory().removeItem(item, 1);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
