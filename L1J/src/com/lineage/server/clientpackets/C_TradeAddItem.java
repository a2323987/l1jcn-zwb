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
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.L1Trade;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1DollInstance;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来增加交易物品的封包
 */
public class C_TradeAddItem extends ClientBasePacket {
    private static final String C_TRADE_ADD_ITEM = "[C] C_TradeAddItem";

    public C_TradeAddItem(final byte abyte0[], final ClientThread client)
            throws Exception {
        super(abyte0);

        final int itemid = this.readD();
        final int itemcount = this.readD();
        final L1PcInstance pc = client.getActiveChar();
        final L1Trade trade = new L1Trade();
        final L1ItemInstance item = pc.getInventory().getItem(itemid);
        if (!item.getItem().isTradable()) {
            pc.sendPackets(new S_ServerMessage(210, item.getItem().getName())); // \f1%0%d是不可转移的…
            return;
        }
        if (item.getBless() >= 128) { // 封印的装备
            pc.sendPackets(new S_ServerMessage(210, item.getItem().getName())); // \f1%0%d是不可转移的…
            return;
        }
        // 使用中的宠物项链 - 无法交易
        for (final L1NpcInstance petNpc : pc.getPetList().values()) {
            if (petNpc instanceof L1PetInstance) {
                final L1PetInstance pet = (L1PetInstance) petNpc;
                if (item.getId() == pet.getItemObjId()) {
                    pc.sendPackets(new S_ServerMessage(1187)); // 宠物项链正在使用中。
                    return;
                }
            }
        }
        // 使用中的魔法娃娃 - 无法交易
        for (final L1DollInstance doll : pc.getDollList().values()) {
            if (doll.getItemObjId() == item.getId()) {
                pc.sendPackets(new S_ServerMessage(1181)); // 这个魔法娃娃目前正在使用中。
                return;
            }
        }

        final L1PcInstance tradingPartner = (L1PcInstance) L1World
                .getInstance().findObject(pc.getTradeID());
        if (tradingPartner == null) {
            return;
        }
        if (pc.getTradeOk()) {
            return;
        }
        if (tradingPartner.getInventory().checkAddItem(item, itemcount) != L1Inventory.OK) { // 检查容量与重量
            tradingPartner.sendPackets(new S_ServerMessage(270)); // \f1当你负担过重时不能交易。
            pc.sendPackets(new S_ServerMessage(271)); // \f1对方携带的物品过重，无法交易。
            return;
        }

        trade.TradeAddItem(pc, itemid, itemcount);
    }

    @Override
    public String getType() {
        return C_TRADE_ADD_ITEM;
    }
}
