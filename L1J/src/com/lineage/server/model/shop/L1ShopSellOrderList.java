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
package com.lineage.server.model.shop;

import java.util.List;

import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.utils.collections.Lists;

/** 商店收购 */
class L1ShopSellOrder {

    private final L1AssessedItem _item;

    private final int _count;

    public L1ShopSellOrder(final L1AssessedItem item, final int count) {
        this._item = item;
        this._count = count;
    }

    public int getCount() {
        return this._count;
    }

    public L1AssessedItem getItem() {
        return this._item;
    }

}

public class L1ShopSellOrderList {
    private final L1Shop _shop;

    private final L1PcInstance _pc;

    private final List<L1ShopSellOrder> _list = Lists.newList();

    L1ShopSellOrderList(final L1Shop shop, final L1PcInstance pc) {
        this._shop = shop;
        this._pc = pc;
    }

    public void add(final int itemObjectId, final int count) {
        final L1AssessedItem assessedItem = this._shop.assessItem(this._pc
                .getInventory().getItem(itemObjectId));
        if (assessedItem == null) {
            /*
             * 未指定道具的购买清单。 对流氓包的可能性。
             */
            throw new IllegalArgumentException();
        }

        this._list.add(new L1ShopSellOrder(assessedItem, count));
    }

    List<L1ShopSellOrder> getList() {
        return this._list;
    }

    L1PcInstance getPc() {
        return this._pc;
    }
}
