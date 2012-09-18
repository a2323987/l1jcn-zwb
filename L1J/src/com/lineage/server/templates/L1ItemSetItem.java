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

/**
 * 
 */
public class L1ItemSetItem {
    private final int id;
    private final int amount;
    private final int enchant;

    public L1ItemSetItem(final int id, final int amount, final int enchant) {
        super();
        this.id = id;
        this.amount = amount;
        this.enchant = enchant;
    }

    /** 取得数量 */
    public int getAmount() {
        return this.amount;
    }

    /** 取得加成 */
    public int getEnchant() {
        return this.enchant;
    }

    /** 取得ID */
    public int getId() {
        return this.id;
    }
}
