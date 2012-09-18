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

import com.lineage.Config;
import com.lineage.server.ClientThread;
import com.lineage.server.model.L1ItemCheck;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1DollInstance;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.LogRecorder;

/**
 * 处理收到由客户端传来丢道具到地上的封包
 */
public class C_DropItem extends ClientBasePacket {

    private static final String C_DROP_ITEM = "[C] C_DropItem";

    public C_DropItem(final byte[] decrypt, final ClientThread client)
            throws Exception {
        super(decrypt);
        final int x = this.readH();
        final int y = this.readH();
        final int objectId = this.readD();
        int count = this.readD();

        if ((count > 0x77359400) || (count < 0)) { // 确保数量不会溢位
            count = 0;
        }

        final L1PcInstance pc = client.getActiveChar();
        if (pc.isGhost()) {
            return;
        } else if ((pc.getMapId() >= 16384) && (pc.getMapId() <= 25088)) { // 旅馆内判断
            pc.sendPackets(new S_ServerMessage(539)); // \f1你无法将它放在这。
            return;
        }

        final L1ItemInstance item = pc.getInventory().getItem(objectId);
        if (item != null) {
            final L1ItemCheck checkItem = new L1ItemCheck(); // 物品状态检查
            if (checkItem.ItemCheck(item, pc)) { // 是否作弊
                return;
            }
            if (!item.getItem().isTradable()) {
                // \f1%0%d是不可转移的…
                pc.sendPackets(new S_ServerMessage(210, item.getItem()
                        .getName()));
                return;
            }

            // 使用中的宠物项链 - 无法丢弃
            for (final L1NpcInstance petNpc : pc.getPetList().values()) {
                if (petNpc instanceof L1PetInstance) {
                    final L1PetInstance pet = (L1PetInstance) petNpc;
                    if (item.getId() == pet.getItemObjId()) {
                        pc.sendPackets(new S_ServerMessage(1187)); // 宠物项链正在使用中。
                        return;
                    }
                }
            }
            // 使用中的魔法娃娃 - 无法丢弃
            for (final L1DollInstance doll : pc.getDollList().values()) {
                if (doll.getItemObjId() == item.getId()) {
                    pc.sendPackets(new S_ServerMessage(1181)); // 这个魔法娃娃目前正在使用中。
                    return;

                }
            }

            if (item.isEquipped()) {
                // \f1你不能够放弃此样物品。
                pc.sendPackets(new S_ServerMessage(125));
                return;
            }
            if (item.getBless() >= 128) { // 封印的装备
                // \f1%0%d是不可转移的…
                pc.sendPackets(new S_ServerMessage(210, item.getItem()
                        .getName()));
                return;
            }

            // 交易纪录
            if (Config.writeDropLog) {
                LogRecorder.writeDropLog(pc, item);
            }

            pc.getInventory().tradeItem(item, count,
                    L1World.getInstance().getInventory(x, y, pc.getMapId()));
            pc.turnOnOffLight();
        }
    }

    @Override
    public String getType() {
        return C_DROP_ITEM;
    }
}
