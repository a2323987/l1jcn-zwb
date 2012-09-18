package com.lineage.server.model.item.etcitem.teleport;

import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 精灵结晶 - 40417
 * 
 * @author jrwz
 */
public class ElfCrystallization implements ItemExecutor {

    public static ItemExecutor get() {
        return new ElfCrystallization();
    }

    private ElfCrystallization() {
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

        // 海贼岛前半部魔法阵
        if (((pc.getX() >= 32665) && (pc.getX() <= 32674))
                && ((pc.getY() >= 32976) && (pc.getY() <= 32985))
                && (pc.getMapId() == 440)) {
            final short mapid = 430;
            L1Teleport.teleport(pc, 32922, 32812, mapid, 5, true);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
