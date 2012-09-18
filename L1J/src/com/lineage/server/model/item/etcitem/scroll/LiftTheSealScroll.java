package com.lineage.server.model.item.etcitem.scroll;

import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 解除封印卷轴 - 41427
 * 
 * @author jrwz
 */
public class LiftTheSealScroll implements ItemExecutor {

    public static ItemExecutor get() {
        return new LiftTheSealScroll();
    }

    private LiftTheSealScroll() {
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
        final L1ItemInstance lockItem = pc.getInventory().getItem(itemobj);

        if (((lockItem != null) && (lockItem.getItem().getType2() == 1))
                || (lockItem.getItem().getType2() == 2)
                || ((lockItem.getItem().getType2() == 0) && lockItem.getItem()
                        .isCanSeal())) {
            if ((lockItem.getBless() == 128) || (lockItem.getBless() == 129)
                    || (lockItem.getBless() == 130)
                    || (lockItem.getBless() == 131)) {
                int bless = 1;
                switch (lockItem.getBless()) {
                    case 128:
                        bless = 0;
                        break;
                    case 129:
                        bless = 1;
                        break;
                    case 130:
                        bless = 2;
                        break;
                    case 131:
                        bless = 3;
                        break;
                }
                lockItem.setBless(bless);
                pc.getInventory().updateItem(lockItem, L1PcInventory.COL_BLESS);
                pc.getInventory().saveItem(lockItem, L1PcInventory.COL_BLESS);
                pc.getInventory().removeItem(item, 1);
            } else {
                pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            }
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
