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
package com.lineage.server.templates;

import com.lineage.server.datatables.ItemTable;
import com.lineage.server.model.game.L1BugBearRace;

/**
 * 商店道具
 */
public class L1ShopItem {

    private static final long serialVersionUID = 1L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private final int _itemId;

    private L1Item _item;

    private final int _price;

    private final int _packCount;

    public L1ShopItem(final int itemId, final int price, final int packCount) {
        this._itemId = itemId;
        this._item = ItemTable.getInstance().getTemplate(itemId);
        this._price = price;
        this._packCount = packCount;
    }

    public L1Item getItem() {
        return this._item;
    }

    public int getItemId() {
        return this._itemId;
    }

    public int getPackCount() {
        return this._packCount;
    }

    public int getPrice() {
        return this._price;
    }

    // 食人妖精賽跑用
    public void setName(final int num) {
        final int trueNum = L1BugBearRace.getInstance().getRunner(num)
                .getNpcId() - 91350 + 1;
        this._item = (L1Item) this._item.clone();
        final String temp = "" + this._item.getIdentifiedNameId() + " "
                + L1BugBearRace.getInstance().getRound() + "-" + trueNum;
        this._item.setName(temp);
        this._item.setUnidentifiedNameId(temp);
        this._item.setIdentifiedNameId(temp);
    }
}
