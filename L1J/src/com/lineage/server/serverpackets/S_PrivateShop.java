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
package com.lineage.server.serverpackets;

import java.util.List;

import com.lineage.server.Opcodes;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.templates.L1PrivateShopBuyList;
import com.lineage.server.templates.L1PrivateShopSellList;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 个人商店清单 (购买)
 */
public class S_PrivateShop extends ServerBasePacket {

    /**
     * 对象是PC
     * 
     * @param pc
     * @param objectId
     * @param type
     */
    public S_PrivateShop(final L1PcInstance pc, final int objectId,
            final int type) {
        final L1PcInstance shopPc = (L1PcInstance) L1World.getInstance()
                .findObject(objectId);

        if (shopPc == null) {
            return;
        }

        this.writeC(Opcodes.S_OPCODE_PRIVATESHOPLIST);
        this.writeC(type);
        this.writeD(objectId);

        if (type == 0) { // 卖出物品
            final List<L1PrivateShopSellList> list = shopPc.getSellList();
            final int size = list.size();
            pc.setPartnersPrivateShopItemCount(size);
            this.writeH(size);
            for (int i = 0; i < size; i++) {
                final L1PrivateShopSellList pssl = list.get(i);
                final int itemObjectId = pssl.getItemObjectId();
                final int count = pssl.getSellTotalCount()
                        - pssl.getSellCount();
                final int price = pssl.getSellPrice();
                final L1ItemInstance item = shopPc.getInventory().getItem(
                        itemObjectId);
                if (item != null) {
                    this.writeC(i);
                    this.writeC(item.getBless());
                    this.writeH(item.getItem().getGfxId());
                    this.writeD(count);
                    this.writeD(price);
                    this.writeS(item.getNumberedViewName(count));
                    this.writeC(0);
                }
            }
        } else if (type == 1) { // 回收物品
            final List<L1PrivateShopBuyList> list = shopPc.getBuyList();
            final int size = list.size();
            this.writeH(size);
            for (int i = 0; i < size; i++) {
                final L1PrivateShopBuyList psbl = list.get(i);
                final int itemObjectId = psbl.getItemObjectId();
                final int count = psbl.getBuyTotalCount();
                final int price = psbl.getBuyPrice();
                final L1ItemInstance item = shopPc.getInventory().getItem(
                        itemObjectId);
                for (final L1ItemInstance pcItem : pc.getInventory().getItems()) {
                    if ((item.getItemId() == pcItem.getItemId())
                            && (item.getEnchantLevel() == pcItem
                                    .getEnchantLevel())) {
                        this.writeC(i);
                        this.writeD(pcItem.getId());
                        this.writeD(count);
                        this.writeD(price);
                    }
                }
            }
        }
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }
}
