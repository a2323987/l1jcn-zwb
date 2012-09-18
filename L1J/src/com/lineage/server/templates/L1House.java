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

public class L1House {
    private int _houseId;

    private String _houseName;

    private int _houseArea;

    private String _location;

    private int _keeperId;

    private boolean _isOnSale;

    private boolean _isPurchaseBasement;

    private Calendar _taxDeadline;

    public L1House() {
    }

    public int getHouseArea() {
        return this._houseArea;
    }

    public int getHouseId() {
        return this._houseId;
    }

    public String getHouseName() {
        return this._houseName;
    }

    public int getKeeperId() {
        return this._keeperId;
    }

    public String getLocation() {
        return this._location;
    }

    public Calendar getTaxDeadline() {
        return this._taxDeadline;
    }

    public boolean isOnSale() {
        return this._isOnSale;
    }

    public boolean isPurchaseBasement() {
        return this._isPurchaseBasement;
    }

    public void setHouseArea(final int i) {
        this._houseArea = i;
    }

    public void setHouseId(final int i) {
        this._houseId = i;
    }

    public void setHouseName(final String s) {
        this._houseName = s;
    }

    public void setKeeperId(final int i) {
        this._keeperId = i;
    }

    public void setLocation(final String s) {
        this._location = s;
    }

    public void setOnSale(final boolean flag) {
        this._isOnSale = flag;
    }

    public void setPurchaseBasement(final boolean flag) {
        this._isPurchaseBasement = flag;
    }

    public void setTaxDeadline(final Calendar i) {
        this._taxDeadline = i;
    }

}
