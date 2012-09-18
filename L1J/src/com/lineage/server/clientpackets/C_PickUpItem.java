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
package com.lineage.server.clientpackets;

import com.lineage.server.ActionCodes;
import com.lineage.server.ClientThread;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.serverpackets.S_AttackPacket;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 处理收到由客户端传来捡起道具的封包
 */
public class C_PickUpItem extends ClientBasePacket {

    private static final String C_PICK_UP_ITEM = "[C] C_PickUpItem";

    public C_PickUpItem(final byte decrypt[], final ClientThread client)
            throws Exception {
        super(decrypt);
        final int x = this.readH();
        final int y = this.readH();
        final int objectId = this.readD();
        final int pickupCount = this.readD();

        final L1PcInstance pc = client.getActiveChar();
        if (pc.isDead() || pc.isGhost() || (objectId == pc.getId())) {
            return;
        }

        if (pc.isInvisble()) { // 隐身状态
            return;
        }
        if (pc.isInvisDelay()) { // 还在解除隐身的延迟
            return;
        }

        final L1Inventory groundInventory = L1World.getInstance().getInventory(
                x, y, pc.getMapId());
        final L1Object object = groundInventory.getItem(objectId);

        if ((object != null) && !pc.isDead()) {
            final L1ItemInstance item = (L1ItemInstance) object;
            if ((item.getItemOwnerId() != 0)
                    && (pc.getId() != item.getItemOwnerId())) {
                pc.sendPackets(new S_ServerMessage(623)); // 道具取得失败。
                return;
            }
            if (pc.getLocation().getTileLineDistance(item.getLocation()) > 3) {
                return;
            }

            if (item.getItem().getItemId() == L1ItemId.ADENA) {
                final L1ItemInstance inventoryItem = pc.getInventory()
                        .findItemId(L1ItemId.ADENA);
                int inventoryItemCount = 0;
                if (inventoryItem != null) {
                    inventoryItemCount = inventoryItem.getCount();
                }
                // 超过20亿
                if ((long) inventoryItemCount + (long) pickupCount > 2000000000L) {
                    pc.sendPackets(new S_ServerMessage(166, // \f1%0%s %4%1%3
                                                            // %2。
                            "你身上的金币已经超过", "2,000,000,000了，所以不能捡取金币。"));
                    return;
                }
            }

            // 检查容量与重量
            if (pc.getInventory().checkAddItem(item, pickupCount) == L1Inventory.OK) {
                // 世界地图上的道具
                if ((item.getX() != 0) && (item.getY() != 0)) {
                    groundInventory.tradeItem(item, pickupCount,
                            pc.getInventory());
                    pc.turnOnOffLight();

                    final S_AttackPacket s_attackPacket = new S_AttackPacket(
                            pc, objectId, ActionCodes.ACTION_Pickup);
                    pc.sendPackets(s_attackPacket);
                    if (!pc.isGmInvis()) {
                        pc.broadcastPacket(s_attackPacket);
                    }
                }
            }
        }
    }

    @Override
    public String getType() {
        return C_PICK_UP_ITEM;
    }
}
