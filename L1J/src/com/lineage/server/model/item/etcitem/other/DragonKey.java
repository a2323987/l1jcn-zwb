package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.model.L1CastleLocation;
import com.lineage.server.model.L1DragonSlayer;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_DragonGate;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 龙之钥匙 - 47010
 * 
 * @author jrwz
 */
public class DragonKey implements ItemExecutor {

    public static ItemExecutor get() {
        return new DragonKey();
    }

    private DragonKey() {
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

        if (!L1CastleLocation.checkInAllWarArea(pc.getLocation())) { // 检查是否在城堡区域内
            pc.sendPackets(new S_DragonGate(pc, L1DragonSlayer.getInstance()
                    .checkDragonPortal()));
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生
        }
    }
}
