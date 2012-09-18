package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 涂着胶水的航海日志：１～８页 <br>
 * 涂着胶水的航海日志：１页 - 41048 <br>
 * 涂着胶水的航海日志：２页 - 41049 <br>
 * 涂着胶水的航海日志：３页 - 41050 <br>
 * 涂着胶水的航海日志：４页 - 41051 <br>
 * 涂着胶水的航海日志：５页 - 41052 <br>
 * 涂着胶水的航海日志：６页 - 41053 <br>
 * 涂着胶水的航海日志：７页 - 41054 <br>
 * 涂着胶水的航海日志：８页 - 41055 <br>
 * 
 * @author jrwz
 */
public class StickybackLogbook_1_8 implements ItemExecutor {

    public static ItemExecutor get() {
        return new StickybackLogbook_1_8();
    }

    private StickybackLogbook_1_8() {
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
            pc.createNewItem(pc, logbookId + 2, 1);
            pc.getInventory().removeItem(l1iteminstance1, 1);
            pc.getInventory().removeItem(item, 1);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
