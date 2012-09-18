package com.lineage.server.model.item.etcitem.scroll;

import com.lineage.server.model.Getback;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 传送回家的卷轴 - 40079 <br>
 * 象牙塔传送回家的卷轴 - 40095 <br>
 * 精灵羽翼 - 40521 <br>
 * 
 * @author jrwz
 */
public class HomeScroll implements ItemExecutor {

    public static ItemExecutor get() {
        return new HomeScroll();
    }

    private HomeScroll() {
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

        if (pc.getMap().isEscapable() || pc.isGm()) {
            final int[] loc = Getback.GetBack_Location(pc, true);
            L1Teleport.teleport(pc, loc[0], loc[1], (short) loc[2], 5, true);
            pc.getInventory().removeItem(item, 1);
        } else {
            pc.sendPackets(new S_ServerMessage(276)); // \f1在此无法使用传送。
        }
    }
}
