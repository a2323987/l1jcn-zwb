package com.lineage.server.model.item.etcitem.scroll;

import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;

/**
 * 受诅咒的 瞬间移动卷轴 - 240100 <br>
 * 
 * @author jrwz
 */
public class C_TeleportsScroll implements ItemExecutor {

    public static ItemExecutor get() {
        return new C_TeleportsScroll();
    }

    private C_TeleportsScroll() {
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

        L1Teleport.teleport(pc, pc.getX(), pc.getY(), pc.getMapId(),
                pc.getHeading(), true);
        pc.getInventory().removeItem(item, 1);
    }
}
