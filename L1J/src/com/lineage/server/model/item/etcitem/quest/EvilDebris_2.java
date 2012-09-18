package com.lineage.server.model.item.etcitem.quest;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 第二次邪念碎片 - 49198
 * 
 * @author jrwz
 */
public class EvilDebris_2 implements ItemExecutor {

    public static ItemExecutor get() {
        return new EvilDebris_2();
    }

    private EvilDebris_2() {
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

        if (l1iteminstance1.getItem().getItemId() == 49197) {
            pc.getInventory().consumeItem(49197, 1);
            pc.getInventory().consumeItem(49198, 1);
            pc.createNewItem(pc, 49200, 1);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
