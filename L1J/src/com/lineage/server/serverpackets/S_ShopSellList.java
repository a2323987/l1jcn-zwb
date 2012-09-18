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

import java.io.IOException;
import java.util.List;

import com.lineage.Config;
import com.lineage.server.Opcodes;
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.datatables.ShopTable;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1TaxCalculator;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.shop.L1Shop;
import com.lineage.server.templates.L1Item;
import com.lineage.server.templates.L1ShopItem;

/**
 * 商店贩卖清单
 */
public class S_ShopSellList extends ServerBasePacket {

    /**
     * 表示商店道具清单。角色按下购买按钮时发送。
     */
    public S_ShopSellList(final int objId, final L1PcInstance pc) {
        this.writeC(Opcodes.S_OPCODE_SHOWSHOPBUYLIST);
        this.writeD(objId);

        final L1Object npcObj = L1World.getInstance().findObject(objId);
        if (!(npcObj instanceof L1NpcInstance)) {
            this.writeH(0);
            return;
        }
        final int npcId = ((L1NpcInstance) npcObj).getNpcTemplate().get_npcId();

        final L1TaxCalculator calc = new L1TaxCalculator(npcId);
        final L1Shop shop = ShopTable.getInstance().get(npcId);
        final List<L1ShopItem> shopItems = shop.getSellingItems();

        this.writeH(shopItems.size());

        // L1ItemInstanceのgetStatusBytesを利用するため
        final L1ItemInstance dummy = new L1ItemInstance();

        for (int i = 0; i < shopItems.size(); i++) {
            final L1ShopItem shopItem = shopItems.get(i);
            final L1Item item = shopItem.getItem();
            final int price = calc
                    .layTax((int) (shopItem.getPrice() * Config.RATE_SHOP_SELLING_PRICE));
            this.writeD(i); // 排序
            this.writeH(shopItem.getItem().getGfxId()); // 圆形
            this.writeD(price); // 售价

            if (shopItem.getPackCount() > 1) {
                this.writeS(item.getName() + " (" + shopItem.getPackCount()
                        + ")");
            } else {
                if (item.getItemId() == 40309) {// 食人妖精RaceTicket
                    String[] temp = item.getName().split(" ");
                    final String buf = temp[temp.length - 1];
                    temp = buf.split("-");
                    this.writeS(buf + " $"
                            + (1212 + Integer.parseInt(temp[temp.length - 1])));
                } else {
                    this.writeS(item.getName());
                }
            }

            final L1Item template = ItemTable.getInstance().getTemplate(
                    item.getItemId());
            if (template == null) {
                this.writeC(0);
            } else {
                dummy.setItem(template);
                final byte[] status = dummy.getStatusBytes();
                this.writeC(status.length);
                for (final byte b : status) {
                    this.writeC(b);
                }
            }
        }
        this.writeH(0x07); // 0x00:无显示 0x01:珍珠 0x07:金币
    }

    @Override
    public byte[] getContent() throws IOException {
        return this._bao.toByteArray();
    }
}
