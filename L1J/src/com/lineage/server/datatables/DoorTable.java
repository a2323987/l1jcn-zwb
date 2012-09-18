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
package com.lineage.server.datatables;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.ActionCodes;
import com.lineage.server.IdFactory;
import com.lineage.server.model.L1Location;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1DoorInstance;
import com.lineage.server.templates.L1DoorGfx;
import com.lineage.server.templates.L1DoorSpawn;
import com.lineage.server.utils.collections.Lists;
import com.lineage.server.utils.collections.Maps;

/**
 * 门资料表
 */
public class DoorTable {

    private static Logger _log = Logger.getLogger(DoorTable.class.getName());

    private static DoorTable _instance;

    public static DoorTable getInstance() {
        return _instance;
    }

    /** 初始化 */
    public static void initialize() {
        _instance = new DoorTable();
    }

    /** 门 */
    private final Map<L1Location, L1DoorInstance> _doors = Maps
            .newConcurrentHashMap();

    /** 门的方向 */
    private final Map<L1Location, L1DoorInstance> _doorDirections = Maps
            .newConcurrentHashMap();

    private DoorTable() {
        this.loadDoors();
    }

    /**
     * 创建门
     * 
     * @param doorId
     * @param gfx
     * @param loc
     * @param hp
     * @param keeper
     * @param isOpening
     */
    public L1DoorInstance createDoor(final int doorId, final L1DoorGfx gfx,
            final L1Location loc, final int hp, final int keeper,
            final boolean isOpening) {
        if (this._doors.containsKey(loc)) {
            return null;
        }
        final L1DoorInstance door = new L1DoorInstance(doorId, gfx, loc, hp,
                keeper, isOpening);

        door.setId(IdFactory.getInstance().nextId());

        L1World.getInstance().storeObject(door);
        L1World.getInstance().addVisibleObject(door);

        this._doors.put(door.getLocation(), door);
        this.putDirections(door);
        return door;
    }

    /**
     * 删除门位置
     * 
     * @param loc
     */
    public void deleteDoorByLocation(final L1Location loc) {
        final L1DoorInstance door = this._doors.remove(loc);
        if (door != null) {
            this.removeDirections(door);
            door.deleteMe();
        }
    }

    /**
     * 查找门的ID
     * 
     * @param doorId
     */
    public L1DoorInstance findByDoorId(final int doorId) {
        for (final L1DoorInstance door : this._doors.values()) {
            if (door.getDoorId() == doorId) {
                return door;
            }
        }
        return null;
    }

    /**
     * 取得门的方向
     * 
     * @param loc
     */
    public int getDoorDirection(final L1Location loc) {
        final L1DoorInstance door = this._doorDirections.get(loc);
        if ((door == null) || (door.getOpenStatus() == ActionCodes.ACTION_Open)) {
            return -1;
        }
        return door.getDirection();
    }

    /**
     * 取得门列表
     * 
     * @return
     */
    public L1DoorInstance[] getDoorList() {
        return this._doors.values().toArray(
                new L1DoorInstance[this._doors.size()]);
    }

    /**
     * 加载门
     */
    private void loadDoors() {
        for (final L1DoorSpawn spawn : L1DoorSpawn.all()) {
            final L1Location loc = spawn.getLocation();
            if (this._doors.containsKey(loc)) {
                _log.log(Level.WARNING,
                        String.format("重复的门 位置: id = %d", spawn.getId()));
                continue;
            }
            this.createDoor(spawn.getId(), spawn.getGfx(), loc, spawn.getHp(),
                    spawn.getKeeper(), spawn.isOpening());
        }
    }

    /**
     * 创建方向Keys
     * 
     * @param door
     */
    private List<L1Location> makeDirectionsKeys(final L1DoorInstance door) {
        final List<L1Location> keys = Lists.newArrayList();
        final int left = door.getLeftEdgeLocation();
        final int right = door.getRightEdgeLocation();
        if (door.getDirection() == 0) {
            for (int x = left; x <= right; x++) {
                keys.add(new L1Location(x, door.getY(), door.getMapId()));
            }
        } else {
            for (int y = left; y <= right; y++) {
                keys.add(new L1Location(door.getX(), y, door.getMapId()));
            }
        }
        return keys;
    }

    /**
     * 打开方向
     * 
     * @param door
     */
    private void putDirections(final L1DoorInstance door) {
        for (final L1Location key : this.makeDirectionsKeys(door)) {
            this._doorDirections.put(key, door);
        }
    }

    /**
     * 删除方向
     * 
     * @param door
     */
    private void removeDirections(final L1DoorInstance door) {
        for (final L1Location key : this.makeDirectionsKeys(door)) {
            this._doorDirections.remove(key);
        }
    }
}
