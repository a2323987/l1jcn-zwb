package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 磨刀石 - 40317
 * 
 * @author jrwz
 */
public class Grindstone implements ItemExecutor {

    public static ItemExecutor get() {
        return new Grindstone();
    }

    private Grindstone() {
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

        // 只能用于武器与防具
        if ((l1iteminstance1.getItem().getType2() != 0)
                && (l1iteminstance1.get_durability() > 0)) {
            String msg0;
            pc.getInventory().recoveryDamage(l1iteminstance1);
            msg0 = l1iteminstance1.getLogName();
            if (l1iteminstance1.get_durability() == 0) {
                pc.sendPackets(new S_ServerMessage(464, msg0)); // %0
                                                                // 现在变成像个新的一样。
            } else {
                pc.sendPackets(new S_ServerMessage(463, msg0)); // %0 变好多了。
            }
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
        pc.getInventory().removeItem(item, 1);
    }
}
