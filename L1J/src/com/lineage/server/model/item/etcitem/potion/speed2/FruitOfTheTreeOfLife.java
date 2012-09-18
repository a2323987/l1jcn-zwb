package com.lineage.server.model.item.etcitem.potion.speed2;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.item.etcitem.UseSpeedPotion_2_RiBrave;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 生命之树果实 - 49158
 * 
 * @author jrwz
 */
public class FruitOfTheTreeOfLife implements ItemExecutor {

    public static ItemExecutor get() {
        return new FruitOfTheTreeOfLife();
    }

    private FruitOfTheTreeOfLife() {
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

        // 龙骑士、幻术师
        if (pc.isDragonKnight() || pc.isIllusionist()) {
            UseSpeedPotion_2_RiBrave.get().useItem(pc, item, 0, 0, 480, 7110);
            // 效果时间 (秒)与动画ID
            // Factory.getPotion().useRiBravePotion(pc, 480, 7110);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
