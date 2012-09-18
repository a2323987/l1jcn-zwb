package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_UseMap;

/**
 * 各种旧版地图 <br>
 * 40373 - 40382 <br>
 * 40385 - 40390 <br>
 * 
 * @author jrwz
 */
public class Map implements ItemExecutor {

    public static ItemExecutor get() {
        return new Map();
    }

    private Map() {
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

        pc.sendPackets(new S_UseMap(pc, item.getId(), item.getItem()
                .getItemId()));
    }
}
