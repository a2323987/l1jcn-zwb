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

// Referenced classes of package com.lineage.server.templates:
// L1PrivateShopSellList

/**
 * 个人商店贩卖清单
 */
public class L1PrivateShopSellList {

    private int _itemObjectId;

    private int _sellTotalCount; // 预期销售数量

    private int _sellPrice;

    private int _sellCount; // 共售出

    public L1PrivateShopSellList() {
    }

    public int getItemObjectId() {
        return this._itemObjectId;
    }

    public int getSellCount() {
        return this._sellCount;
    }

    public int getSellPrice() {
        return this._sellPrice;
    }

    public int getSellTotalCount() {
        return this._sellTotalCount;
    }

    public void setItemObjectId(final int i) {
        this._itemObjectId = i;
    }

    public void setSellCount(final int i) {
        this._sellCount = i;
    }

    public void setSellPrice(final int i) {
        this._sellPrice = i;
    }

    public void setSellTotalCount(final int i) {
        this._sellTotalCount = i;
    }
}
