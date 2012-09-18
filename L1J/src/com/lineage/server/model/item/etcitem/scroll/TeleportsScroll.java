package com.lineage.server.model.item.etcitem.scroll;

import com.lineage.server.model.L1ItemDelay;
import com.lineage.server.model.L1Location;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_Paralysis;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1BookMark;

/**
 * 瞬间移动卷轴 - 40100 <br>
 * 受祝福的 瞬间移动卷轴 - 140100 <br>
 * 全体传送术的卷轴 - 40086 <br>
 * 象牙塔瞬间移动卷轴 - 40099 <br>
 * 魔法卷轴 (指定传送) - 40863 <br>
 * 
 * @author jrwz
 */
public class TeleportsScroll implements ItemExecutor {

    public static ItemExecutor get() {
        return new TeleportsScroll();
    }

    private TeleportsScroll() {
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

        final int btele = data[0];
        final L1BookMark bookm = pc.getBookMark(btele);

        if (bookm != null) { // 取得坐标点
            if (pc.getMap().isEscapable() || pc.isGm()) {
                final int newX = bookm.getLocX();
                final int newY = bookm.getLocY();
                final short mapId = bookm.getMapId();
                final int itemId = item.getItemId();

                // 全体传送术的卷轴
                if (itemId == 40086) {
                    for (final L1PcInstance member : L1World.getInstance()
                            .getVisiblePlayer(pc)) {
                        if ((pc.getLocation().getTileLineDistance(
                                member.getLocation()) <= 3)
                                && (member.getClanid() == pc.getClanid())
                                && (pc.getClanid() != 0)
                                && (member.getId() != pc.getId())) {
                            L1Teleport.teleport(member, newX, newY, mapId, 5,
                                    true);
                        }
                    }
                }

                L1Teleport.teleport(pc, newX, newY, mapId, 5, true);
                L1ItemDelay.teleportUnlock(pc, item); // 卷轴传送后 使用物品延迟完才解开停止状态
                pc.getInventory().removeItem(item, 1);
            } else {
                pc.sendPackets(new S_ServerMessage(79));
                pc.sendPackets(new S_Paralysis(
                        S_Paralysis.TYPE_TELEPORT_UNLOCK, true));
            }
        } else {
            if (pc.getMap().isTeleportable() || pc.isGm()) {
                final L1Location newLocation = pc.getLocation().randomLocation(
                        200, true);
                final int newX = newLocation.getX();
                final int newY = newLocation.getY();
                final short mapId = (short) newLocation.getMapId();
                final int itemId = item.getItemId();

                // 全体传送术的卷轴
                if (itemId == 40086) {
                    for (final L1PcInstance member : L1World.getInstance()
                            .getVisiblePlayer(pc)) {
                        if ((pc.getLocation().getTileLineDistance(
                                member.getLocation()) <= 3)
                                && (member.getClanid() == pc.getClanid())
                                && (pc.getClanid() != 0)
                                && (member.getId() != pc.getId())) {
                            L1Teleport.teleport(member, newX, newY, mapId, 5,
                                    true);
                        }
                    }
                }
                L1Teleport.teleport(pc, newX, newY, mapId, 5, true);
                L1ItemDelay.teleportUnlock(pc, item); // 卷轴传送后 使用物品延迟完才解开停止状态
                pc.getInventory().removeItem(item, 1);
            } else {
                pc.sendPackets(new S_ServerMessage(276));
                pc.sendPackets(new S_Paralysis(
                        S_Paralysis.TYPE_TELEPORT_UNLOCK, true));
            }
        }
    }
}
