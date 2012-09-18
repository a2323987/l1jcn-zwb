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

import java.util.Calendar;

public class L1Castle {
    private final int _id;

    private final String _name;

    private Calendar _warTime;

    private int _taxRate;

    private int _publicMoney;

    public L1Castle(final int id, final String name) {
        this._id = id;
        this._name = name;
    }

    public int getId() {
        return this._id;
    }

    public String getName() {
        return this._name;
    }

    public int getPublicMoney() {
        if (this._publicMoney < 0) {
            return 0;
        }
        return this._publicMoney;
    }

    public int getTaxRate() {
        return this._taxRate;
    }

    public Calendar getWarTime() {
        return this._warTime;
    }

    public void setPublicMoney(final int i) {
        this._publicMoney = i;
    }

    public void setTaxRate(final int i) {
        this._taxRate = i;
    }

    public void setWarTime(final Calendar i) {
        this._warTime = i;
    }

}
