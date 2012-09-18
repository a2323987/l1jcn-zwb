package com.lineage.server.model.item.etcitem.teleport;

import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 密室钥匙 - 40555
 * 
 * @author jrwz
 */
public class PrivateRoomKey implements ItemExecutor {

    public static ItemExecutor get() {
        return new PrivateRoomKey();
    }

    private PrivateRoomKey() {
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

        if (pc.isKnight() && ((pc.getX() >= 32806) && (pc.getX() <= 32814))
                && ((pc.getY() >= 32798) && (pc.getY() <= 32807))
                && (pc.getMapId() == 13)) {
            final short mapid = 13;
            L1Teleport.teleport(pc, 32815, 32810, mapid, 5, false);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
