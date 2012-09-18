package com.lineage.server.model.item.etcitem.quest;

import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.L1SpawnUtil;

/**
 * 暗杀名单(燃柳村) - 40563
 * 
 * @author jrwz
 */
public class Hitlist_FuelLiu implements ItemExecutor {

    public static ItemExecutor get() {
        return new Hitlist_FuelLiu();
    }

    private Hitlist_FuelLiu() {
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

        if ((pc.getX() == 32730) && (pc.getY() == 32426)
                && (pc.getMapId() == 4)) {
            for (final L1Object object : L1World.getInstance().getObject()) {
                if (object instanceof L1NpcInstance) {
                    final L1NpcInstance npc = (L1NpcInstance) object;
                    if (npc.getNpcTemplate().get_npcId() == 45884) {
                        pc.sendPackets(new S_ServerMessage(79));
                        return;
                    }
                }
            }
            L1SpawnUtil.spawn(pc, 45884, 0, 300000);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
