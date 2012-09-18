/**
 *                            License
 * THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS  
 * CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
 * THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW.  
 * ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE OR  
 * COPYRIGHT LAW IS PROHIBITED.
 * 
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND  
 * AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE  
 * MAY BE CONSIDERED TO BE A CONTRACT,
 * THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package com.lineage.server.model.item.etcitem.teleport;

import com.lineage.server.PacketCreate;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.ServerBasePacket;
import com.lineage.server.templates.L1EtcItem;

/**
 * Class <code>TeleportNormal</code> 传送类道具(一般).
 * 
 * @author jrwz
 * @version 2012-5-7上午01:48:31
 * @see com.lineage.server.model.item.etcitem.teleport
 * @since JDK1.7
 */
public final class TeleportNormal implements ItemExecutor {

    public static ItemExecutor get() {
        return new TeleportNormal();
    }

    private TeleportNormal() {
    }

    /**
     * 道具执行.
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

        final int locX = ((L1EtcItem) item.getItem()).get_locx();
        final int locY = ((L1EtcItem) item.getItem()).get_locy();
        final short mapId = ((L1EtcItem) item.getItem()).get_mapid();

        // 各种传送卷轴
        if ((locX != 0) && (locY != 0)) {
            if (pc.getMap().isEscapable() || pc.isGm()) {
                L1Teleport.teleport(pc, locX, locY, mapId, pc.getHeading(),
                        true);
                pc.getInventory().removeItem(item, 1);
            } else {
                final ServerBasePacket msg = PacketCreate.get().getPacket(
                        "这附近的能量影响到瞬间移动。在此地无法使用瞬间移动。");
                pc.sendPackets(msg); // 这附近的能量影响到瞬间移动。在此地无法使用瞬间移动。
            }
        }
    }
}
