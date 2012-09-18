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
package com.lineage.server.model;

import com.lineage.server.model.map.L1Map;
import com.lineage.server.model.map.L1WorldMap;
import com.lineage.server.types.Rectangle;

/**
 * 地图区域
 */
public class L1MapArea extends Rectangle {

    /** 地图 */
    private L1Map _map = L1Map.newNull();

    /** 地图区域 */
    public L1MapArea(final int left, final int top, final int right,
            final int bottom, final int mapId) {
        super(left, top, right, bottom);

        this._map = L1WorldMap.getInstance().getMap((short) mapId);
    }

    /** 包含 */
    public boolean contains(final L1Location loc) {
        return (this._map.getId() == loc.getMap().getId())
                && super.contains(loc);
    }

    /** 取得地图 */
    public L1Map getMap() {
        return this._map;
    }

    /** 取得地图ID */
    public int getMapId() {
        return this._map.getId();
    }

    /** 设定地图 */
    public void setMap(final L1Map map) {
        this._map = map;
    }
}
