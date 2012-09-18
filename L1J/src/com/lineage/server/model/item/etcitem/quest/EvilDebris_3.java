package com.lineage.server.model.item.etcitem.quest;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 第三次邪念碎片 - 49199
 * 
 * @author jrwz
 */
public class EvilDebris_3 implements ItemExecutor {

    public static ItemExecutor get() {
        return new EvilDebris_3();
    }

    private EvilDebris_3() {
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

        if (l1iteminstance1.getItem().getItemId() == 49200) {
            pc.getInventory().consumeItem(49199, 1);
            pc.getInventory().consumeItem(49200, 1);
            pc.createNewItem(pc, 49201, 1);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
