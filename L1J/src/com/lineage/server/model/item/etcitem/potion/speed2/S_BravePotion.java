package com.lineage.server.model.item.etcitem.potion.speed2;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.item.etcitem.UseSpeedPotion_2_Brave;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 强化勇气的药水 - 41415
 * 
 * @author jrwz
 */
public class S_BravePotion implements ItemExecutor {

    public static ItemExecutor get() {
        return new S_BravePotion();
    }

    private S_BravePotion() {
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

        // 骑士
        if (pc.isKnight()) {
            UseSpeedPotion_2_Brave.get().useItem(pc, item, 0, 0, 1800, 751);
            // 效果时间 (秒)与动画ID
            // Factory.getPotion().useBravePotion(pc, 1800, 751);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
