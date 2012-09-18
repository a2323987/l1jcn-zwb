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

import com.lineage.server.ClientThread;
import com.lineage.server.model.Instance.L1DollInstance;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来删除身上道具的封包
 */
public class C_DeleteInventoryItem extends ClientBasePacket {

    private static final String C_DELETE_INVENTORY_ITEM = "[C] C_DeleteInventoryItem";

    public C_DeleteInventoryItem(final byte[] decrypt, final ClientThread client) {
        super(decrypt);
        final int itemObjectId = this.readD();
        final L1PcInstance pc = client.getActiveChar();
        final L1ItemInstance item = pc.getInventory().getItem(itemObjectId);

        // 没有要删除的道具
        if (item == null) {
            return;
        }
        // 无法删除的道具
        if (item.getItem().isCantDelete()) {
            pc.sendPackets(new S_ServerMessage(125)); // \f1你不能够放弃此样物品。
            return;
        }

        // 使用中的宠物项链 - 无法删除
        for (final L1NpcInstance petNpc : pc.getPetList().values()) {
            if (petNpc instanceof L1PetInstance) {
                final L1PetInstance pet = (L1PetInstance) petNpc;
                if (item.getId() == pet.getItemObjId()) {
                    pc.sendPackets(new S_ServerMessage(1187)); // 宠物项链正在使用中。
                    return;
                }
            }
        }
        // 使用中的魔法娃娃 - 无法删除
        for (final L1DollInstance doll : pc.getDollList().values()) {
            if (doll.getItemObjId() == item.getId()) {
                pc.sendPackets(new S_ServerMessage(1181)); // 这个魔法娃娃目前正在使用中。
                return;
            }
        }
        // 装备中的道具 - 无法删除
        if (item.isEquipped()) {
            pc.sendPackets(new S_ServerMessage(125)); // \f1你不能够放弃此样物品。
            return;
        }
        // 封印中的装备 - 无法删除
        if (item.getBless() >= 128) {
            pc.sendPackets(new S_ServerMessage(210, item.getItem().getName())); // \f1%0%d是不可转移的…
            return;
        }

        pc.getInventory().removeItem(item, item.getCount());
        pc.turnOnOffLight();
    }

    @Override
    public String getType() {
        return C_DELETE_INVENTORY_ITEM;
    }
}
