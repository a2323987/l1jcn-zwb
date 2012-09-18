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

public class L1GetBackRestart {
    private int _area;

    private String _note;

    private int _locX;

    private int _locY;

    private short _mapId;

    public L1GetBackRestart() {
    }

    public int getArea() {
        return this._area;
    }

    public int getLocX() {
        return this._locX;
    }

    public int getLocY() {
        return this._locY;
    }

    public short getMapId() {
        return this._mapId;
    }

    public String getNote() {
        return this._note;
    }

    public void setArea(final int i) {
        this._area = i;
    }

    public void setLocX(final int i) {
        this._locX = i;
    }

    public void setLocY(final int i) {
        this._locY = i;
    }

    public void setMapId(final short i) {
        this._mapId = i;
    }

    public void setNote(final String s) {
        this._note = s;
    }

}
