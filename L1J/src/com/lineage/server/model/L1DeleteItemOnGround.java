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
 * MAY BE CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package com.lineage.server.model;

import java.util.List;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.L1Message;
import com.lineage.server.GeneralThreadPool;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.model:
// L1DeleteItemOnGround

/**
 * 删除地面上的道具
 */
public class L1DeleteItemOnGround {

    private class DeleteTimer implements Runnable {
        public DeleteTimer() {
        }

        @Override
        public void run() {
            L1Message.getInstance();// Locale 多国语系
            final int time = Config.ALT_ITEM_DELETION_TIME * 60 * 1000 - 10
                    * 1000;
            for (;;) {
                try {
                    Thread.sleep(time);
                } catch (final Exception exception) {
                    _log.warning("L1DeleteItemOnGround error: " + exception);
                    break;
                }
                L1World.getInstance().broadcastPacketToAll(
                        new S_ServerMessage(166, L1Message.onGroundItem,
                                L1Message.secondsDelete + "。"));
                try {
                    Thread.sleep(10000);
                } catch (final Exception exception) {
                    _log.warning("L1DeleteItemOnGround error: " + exception);
                    break;
                }
                L1DeleteItemOnGround.this.deleteItem();
                L1World.getInstance().broadcastPacketToAll(
                        new S_ServerMessage(166, L1Message.onGroundItem,
                                L1Message.deleted + "。"));
            }
        }
    }

    private DeleteTimer _deleteTimer;

    static final Logger _log = Logger.getLogger(L1DeleteItemOnGround.class
            .getName());

    public L1DeleteItemOnGround() {
    }

    /** 删除道具 */
    void deleteItem() {
        int numOfDeleted = 0;
        for (final L1Object obj : L1World.getInstance().getObject()) {
            if (!(obj instanceof L1ItemInstance)) {
                continue;
            }

            final L1ItemInstance item = (L1ItemInstance) obj;
            if ((item.getX() == 0) && (item.getY() == 0)) { // 地面上のアイテムではなく、誰かの所有物
                continue;
            }
            if (item.getItem().getItemId() == 40515) { // 元素石
                continue;
            }
            if (L1HouseLocation.isInHouse(item.getX(), item.getY(),
                    item.getMapId())) { // 藏身点(アジト内)
                continue;
            }

            final List<L1PcInstance> players = L1World.getInstance()
                    .getVisiblePlayer(item, Config.ALT_ITEM_DELETION_RANGE);
            if (players.isEmpty()) { // 删除人物指定范围内的道具
                final L1Inventory groundInventory = L1World
                        .getInstance()
                        .getInventory(item.getX(), item.getY(), item.getMapId());
                groundInventory.removeItem(item);
                numOfDeleted++;
            }
        }
        _log.fine("世界地图上的道具自动删除。删除数量: " + numOfDeleted);
    }

    public void initialize() {
        if (!Config.ALT_ITEM_DELETION_TYPE.equalsIgnoreCase("auto")) {
            return;
        }

        this._deleteTimer = new DeleteTimer();
        GeneralThreadPool.getInstance().execute(this._deleteTimer); // 启动定时器
    }
}
