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
import com.lineage.server.types.Point;
import com.lineage.server.utils.Random;

/**
 * 位置、地点
 */
public class L1Location extends Point {

    /**
     * 对于参数的Location、返回随机移动范围的Location。 对于随机传送、城堡区域、藏身处のLocation将不返回。
     * 
     * @param baseLocation
     *            随机范围中的Location
     * @param min
     *            随机范围的最小值(0包含自身的坐标)
     * @param max
     *            随机范围的最大值
     * @param isRandomTeleport
     *            汇报随机
     * @return 新Location
     */
    public static L1Location randomLocation(final L1Location baseLocation,
            int min, final int max, final boolean isRandomTeleport) {
        if (min > max) {
            throw new IllegalArgumentException("min > max参数是无效的");
        }
        if (max <= 0) {
            return new L1Location(baseLocation);
        }
        if (min < 0) {
            min = 0;
        }

        final L1Location newLocation = new L1Location();
        int newX = 0;
        int newY = 0;
        final int locX = baseLocation.getX();
        final int locY = baseLocation.getY();
        final short mapId = (short) baseLocation.getMapId();
        final L1Map map = baseLocation.getMap();

        newLocation.setMap(map);

        int locX1 = locX - max;
        int locX2 = locX + max;
        int locY1 = locY - max;
        int locY2 = locY + max;

        // map范围
        final int mapX1 = map.getX();
        final int mapX2 = mapX1 + map.getWidth();
        final int mapY1 = map.getY();
        final int mapY2 = mapY1 + map.getHeight();

        // 最大でもマップの範囲内までに補正
        if (locX1 < mapX1) {
            locX1 = mapX1;
        }
        if (locX2 > mapX2) {
            locX2 = mapX2;
        }
        if (locY1 < mapY1) {
            locY1 = mapY1;
        }
        if (locY2 > mapY2) {
            locY2 = mapY2;
        }

        final int diffX = locX2 - locX1; // x方向
        final int diffY = locY2 - locY1; // y方向

        int trial = 0;
        // 試行回数を範囲最小値によってあげる為の計算
        final int amax = (int) Math.pow(1 + (max * 2), 2);
        final int amin = (min == 0) ? 0 : (int) Math
                .pow(1 + ((min - 1) * 2), 2);
        final int trialLimit = 40 * amax / (amax - amin);

        while (true) {
            if (trial >= trialLimit) {
                newLocation.set(locX, locY);
                break;
            }
            trial++;

            newX = locX1 + Random.nextInt(diffX + 1);
            newY = locY1 + Random.nextInt(diffY + 1);

            newLocation.set(newX, newY);

            if (baseLocation.getTileLineDistance(newLocation) < min) {
                continue;

            }
            if (isRandomTeleport) { // 对于随机传送
                if (L1CastleLocation.checkInAllWarArea(newX, newY, mapId)) { // 一个领域的城堡
                    continue;
                }

                // いずれかのアジト内
                if (L1HouseLocation.isInHouse(newX, newY, mapId)) {
                    continue;
                }
            }

            if (map.isInMap(newX, newY) && map.isPassable(newX, newY)) {
                break;
            }
        }
        return newLocation;
    }

    protected L1Map _map = L1Map.newNull();

    public L1Location() {
        super();
    }

    public L1Location(final int x, final int y, final int mapId) {
        super(x, y);
        this.setMap(mapId);
    }

    public L1Location(final int x, final int y, final L1Map map) {
        super(x, y);
        this._map = map;
    }

    public L1Location(final L1Location loc) {
        this(loc._x, loc._y, loc._map);
    }

    public L1Location(final Point pt, final int mapId) {
        super(pt);
        this.setMap(mapId);
    }

    public L1Location(final Point pt, final L1Map map) {
        super(pt);
        this._map = map;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof L1Location)) {
            return false;
        }
        final L1Location loc = (L1Location) obj;
        return (this.getMap() == loc.getMap()) && (this.getX() == loc.getX())
                && (this.getY() == loc.getY());
    }

    public L1Map getMap() {
        return this._map;
    }

    public int getMapId() {
        return this._map.getId();
    }

    @Override
    public int hashCode() {
        return 7 * this._map.getId() + super.hashCode();
    }

    /**
     * 对于这个Location、返回随机移动范围的Location。 对于随机传送、城堡区域、藏身处のLocation将不返回。
     * 
     * @param max
     *            随机范围的最大值
     * @param isRandomTeleport
     *            汇报随机
     * @return 新Location
     */
    public L1Location randomLocation(final int max,
            final boolean isRandomTeleport) {
        return this.randomLocation(0, max, isRandomTeleport);
    }

    /**
     * 对于这个Location、返回随机移动范围的Location。 对于随机传送、城堡区域、藏身处のLocation将不返回。
     * 
     * @param min
     *            随机范围的最小值(0包含自身的坐标)
     * @param max
     *            随机范围的最大值
     * @param isRandomTeleport
     *            汇报随机
     * @return 新Location
     */
    public L1Location randomLocation(final int min, final int max,
            final boolean isRandomTeleport) {
        return L1Location.randomLocation(this, min, max, isRandomTeleport);
    }

    public void set(final int x, final int y, final int mapId) {
        this.set(x, y);
        this.setMap(mapId);
    }

    public void set(final int x, final int y, final L1Map map) {
        this.set(x, y);
        this._map = map;
    }

    public void set(final L1Location loc) {
        this._map = loc._map;
        this._x = loc._x;
        this._y = loc._y;
    }

    public void set(final Point pt, final int mapId) {
        this.set(pt);
        this.setMap(mapId);
    }

    public void set(final Point pt, final L1Map map) {
        this.set(pt);
        this._map = map;
    }

    public void setMap(final int mapId) {
        this._map = L1WorldMap.getInstance().getMap((short) mapId);
    }

    public void setMap(final L1Map map) {
        this._map = map;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d) on %d", this._x, this._y,
                this._map.getId());
    }
}
