package com.lineage.server.model.item.etcitem.teleport;

import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1EtcItem;

/**
 * 完成的藏宝图 - 40692
 * 
 * @author jrwz
 */
public class TreasureMap implements ItemExecutor {

    public static ItemExecutor get() {
        return new TreasureMap();
    }

    private TreasureMap() {
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

        // 有德雷克钥匙
        if (pc.getInventory().checkItem(40621)) {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
        // 海贼岛地监３楼
        else if (((pc.getX() >= 32856) && (pc.getX() <= 32858))
                && ((pc.getY() >= 32857) && (pc.getY() <= 32858))
                && (pc.getMapId() == 443)) {
            L1Teleport.teleport(pc, ((L1EtcItem) item.getItem()).get_locx(),
                    ((L1EtcItem) item.getItem()).get_locy(),
                    ((L1EtcItem) item.getItem()).get_mapid(), 5, true);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
