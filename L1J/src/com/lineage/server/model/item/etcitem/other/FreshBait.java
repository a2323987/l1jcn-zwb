package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 新鲜的饵 - 47103
 * 
 * @author jrwz
 */
public class FreshBait implements ItemExecutor {

    public static ItemExecutor get() {
        return new FreshBait();
    }

    private FreshBait() {
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

        pc.sendPackets(new S_ServerMessage(452, item.getLogName())); // %0%s
                                                                     // 被选择了。
    }
}
