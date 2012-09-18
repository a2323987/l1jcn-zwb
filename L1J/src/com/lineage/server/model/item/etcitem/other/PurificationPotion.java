package com.lineage.server.model.item.etcitem.other;

import com.lineage.Config;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.Random;

/**
 * 净化药水 - 40925
 * 
 * @author jrwz
 */
public class PurificationPotion implements ItemExecutor {

    public static ItemExecutor get() {
        return new PurificationPotion();
    }

    private PurificationPotion() {
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
        final int earingId = l1iteminstance1.getItem().getItemId();

        if ((earingId >= 40987) && (40989 >= earingId)) { // 受诅咒的黑色耳环
            if (Random.nextInt(100) < Config.CREATE_CHANCE_RECOLLECTION) {
                pc.createNewItem(pc, earingId + 186, 1);
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
