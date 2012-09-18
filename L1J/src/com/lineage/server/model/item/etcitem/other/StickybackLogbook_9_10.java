package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 涂着胶水的航海日志：９～１０页 <br>
 * 涂着胶水的航海日志：９页 - 41056 <br>
 * 涂着胶水的航海日志：１０页 - 41057 <br>
 * 
 * @author jrwz
 */
public class StickybackLogbook_9_10 implements ItemExecutor {

    public static ItemExecutor get() {
        return new StickybackLogbook_9_10();
    }

    private StickybackLogbook_9_10() {
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
        final int logbookId = l1iteminstance1.getItem().getItemId();

        if (logbookId == (itemId + 8034)) {
            pc.createNewItem(pc, 41058, 1);
            pc.getInventory().removeItem(l1iteminstance1, 1);
            pc.getInventory().removeItem(item, 1);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
