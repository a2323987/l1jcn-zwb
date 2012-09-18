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

import com.lineage.Config;
import com.lineage.server.model.L1TaxCalculator;
import com.lineage.server.templates.L1ShopItem;
import com.lineage.server.utils.collections.Lists;

/** 商店购买 */
class L1ShopBuyOrder {

    private final L1ShopItem _item;

    private final int _count;

    public L1ShopBuyOrder(final L1ShopItem item, final int count) {
        this._item = item;
        this._count = count;
    }

    public int getCount() {
        return this._count;
    }

    public L1ShopItem getItem() {
        return this._item;
    }
}

/** 商店购买顺序名单 */
public class L1ShopBuyOrderList {

    private final L1Shop _shop;

    private final List<L1ShopBuyOrder> _list = Lists.newList();
    /** 计算税率 */
    private final L1TaxCalculator _taxCalc;
    /** 总重量 */
    private int _totalWeight = 0;
    /** 总价格 */
    private int _totalPrice = 0;
    /** 总价格含税收 */
    private int _totalPriceTaxIncluded = 0;

    L1ShopBuyOrderList(final L1Shop shop) {
        this._shop = shop;
        this._taxCalc = new L1TaxCalculator(shop.getNpcId());
    }

    public void add(final int orderNumber, final int count) {
        if (this._shop.getSellingItems().size() < orderNumber) {
            return;
        }
        final L1ShopItem shopItem = this._shop.getSellingItems().get(
                orderNumber);

        final int price = (int) (shopItem.getPrice() * Config.RATE_SHOP_SELLING_PRICE);
        // 溢出检查
        for (int j = 0; j < count; j++) {
            if (price * j < 0) {
                return;
            }
        }
        if (this._totalPrice < 0) {
            return;
        }
        this._totalPrice += price * count;
        this._totalPriceTaxIncluded += this._taxCalc.layTax(price) * count;
        this._totalWeight += shopItem.getItem().getWeight() * count
                * shopItem.getPackCount();

        if (shopItem.getItem().isStackable()) {
            this._list.add(new L1ShopBuyOrder(shopItem, count
                    * shopItem.getPackCount()));
            return;
        }

        for (int i = 0; i < (count * shopItem.getPackCount()); i++) {
            this._list.add(new L1ShopBuyOrder(shopItem, 1));
        }
    }

    List<L1ShopBuyOrder> getList() {
        return this._list;
    }

    L1TaxCalculator getTaxCalculator() {
        return this._taxCalc;
    }

    public int getTotalPrice() {
        return this._totalPrice;
    }

    public int getTotalPriceTaxIncluded() {
        return this._totalPriceTaxIncluded;
    }

    public int getTotalWeight() {
        return this._totalWeight;
    }
}
