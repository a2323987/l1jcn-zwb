package com.lineage.server.model.item.etcitem.quest;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 灵魂水晶（黑） - 40577
 * 
 * @author jrwz
 */
public class SoulCrystalBlack implements ItemExecutor {

    public static ItemExecutor get() {
        return new SoulCrystalBlack();
    }

    private SoulCrystalBlack() {
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

        if (!pc.isWizard()) {
            pc.sendPackets(new S_ServerMessage(264)); // \f1你的职业无法使用此装备。
            return;
        }
        if (pc.castleWarResult() || (pc.getMapId() == 303)) {
            pc.sendPackets(new S_ServerMessage(563)); // \f1你无法在这个地方使用。
            return;
        }
        pc.death(null);
        pc.getInventory().removeItem(item, 1);
        pc.createNewItem(pc, 40574, 1); // 灵魂之证
    }
}
