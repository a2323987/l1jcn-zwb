package com.lineage.server.model.item.etcitem.teleport;

import com.lineage.server.model.L1Quest;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 时空裂痕邪念碎片 - 49202
 * 
 * @author jrwz
 */
public class EvilDebris implements ItemExecutor {

    public static ItemExecutor get() {
        return new EvilDebris();
    }

    private EvilDebris() {
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

        if ((pc.getMapId() != 2004)
                && (pc.getQuest().get_step(L1Quest.QUEST_LEVEL50) > 1)) {
            final short mapid = 2004;
            L1Teleport.teleport(pc, 32723, 32834, mapid, 5, true);
            pc.getInventory().removeItem(item, 1);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
