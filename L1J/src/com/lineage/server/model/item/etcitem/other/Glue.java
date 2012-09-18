package com.lineage.server.model.item.etcitem.other;

import com.lineage.Config;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.Random;

/**
 * 胶水 - 41036
 * 
 * @author jrwz
 */
public class Glue implements ItemExecutor {

    public static ItemExecutor get() {
        return new Glue();
    }

    private Glue() {
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
        final int diaryId = l1iteminstance1.getItem().getItemId();

        if ((diaryId >= 41038) && (41047 >= diaryId)) {
            if ((Random.nextInt(99) + 1) <= Config.CREATE_CHANCE_DIARY) {
                pc.createNewItem(pc, diaryId + 10, 1);
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
