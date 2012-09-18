package com.lineage.server.model.item;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 通用接口 (使用道具)
 * 
 * @author jrwz
 */
public interface UniversalUseItem {

    /**
     * 道具类使用
     * 
     * @param pc
     *            使用对象
     * @param item
     *            使用道具
     * @param itemId
     *            道具编号
     * @param effect
     *            道具效果 (加血、变身等)
     * @param time
     *            效果时间 (单位:秒)
     * @param gfxid
     *            特效编号 (动画类)
     */
    void useItem(final L1PcInstance pc, final L1ItemInstance item,
            final int itemId, final int effect, final int time, final int gfxid);
}
