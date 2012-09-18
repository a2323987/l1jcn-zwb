package com.lineage.server.model.item.etcitem.other;

import com.lineage.Config;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.Random;

/**
 * 召唤球之核 - 41029
 * 
 * @author jrwz
 */
public class Call_Ball_Nuclear implements ItemExecutor {

    public static ItemExecutor get() {
        return new Call_Ball_Nuclear();
    }

    private Call_Ball_Nuclear() {
    }

    @Override
    public void execute(final int[] data, final L1PcInstance pc,
            final L1ItemInstance item) {

        final int itemobj = data[0];
        final L1ItemInstance l1iteminstance1 = pc.getInventory().getItem(
                itemobj);
        final int dantesId = l1iteminstance1.getItem().getItemId();

        // 召唤球碎片・各阶段
        if ((dantesId >= 41030) && (41034 >= dantesId)) {
            if ((Random.nextInt(99) + 1) < Config.CREATE_CHANCE_DANTES) {
                pc.createNewItem(pc, dantesId + 1, 1);
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
