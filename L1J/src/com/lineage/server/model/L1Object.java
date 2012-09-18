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

import java.io.Serializable;

import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.map.L1Map;
import com.lineage.server.model.map.L1WorldMap;

// Referenced classes of package com.lineage.server.model:
// L1PcInstance, L1Character

/**
 * 所有对象的基底
 */
public class L1Object implements Serializable {

    private static final long serialVersionUID = 1L;

    private final L1Location _loc = new L1Location();

    private int _id = 0;

    /**
     * 取得对象在世界中唯一的ID
     * 
     * @return 唯一的ID
     */
    public int getId() {
        return this._id;
    }

    /**
     * 取得与另一个对象间的直线距离。
     */
    public double getLineDistance(final L1Object obj) {
        return this.getLocation().getLineDistance(obj.getLocation());
    }

    /**
     * 对象存在在地图上的L1Location
     * 
     * @return L1Location的座标对应
     */
    public L1Location getLocation() {
        return this._loc;
    }

    /**
     * 取得对象所存在的地图
     */
    public L1Map getMap() {
        return this._loc.getMap();
    }

    /**
     * 取得对象所存在的地图ID
     * 
     * @return 地图ID
     */
    public short getMapId() {
        return (short) this._loc.getMap().getId();
    }

    /**
     * 取得与另一个对象间的X轴+Y轴的距离。
     */
    public int getTileDistance(final L1Object obj) {
        return this.getLocation().getTileDistance(obj.getLocation());
    }

    /**
     * 取得与另一个对象间的距离X轴或Y轴较大的那一个。
     */
    public int getTileLineDistance(final L1Object obj) {
        return this.getLocation().getTileLineDistance(obj.getLocation());
    }

    /**
     * 取得对象在地图上的X轴值
     * 
     * @return 座标X轴值
     */
    public int getX() {
        return this._loc.getX();
    }

    /**
     * 取得对象在地图上的Y轴值
     * 
     * @return 座标Y轴值
     */
    public int getY() {
        return this._loc.getY();
    }

    /**
     * 对象对玩家采取的行动
     * 
     * @param actionFrom
     *            要采取行动的玩家目标
     */
    public void onAction(final L1PcInstance actionFrom) {
    }

    public void onAction(final L1PcInstance attacker, final int skillId) {

    }

    /**
     * 对象的荧幕范围进入玩家
     * 
     * @param perceivedFrom
     *            进入荧幕范围的玩家
     */
    public void onPerceive(final L1PcInstance perceivedFrom) {
    }

    /**
     * 与对象交谈的玩家
     * 
     * @param talkFrom
     *            交谈的玩家
     */
    public void onTalkAction(final L1PcInstance talkFrom) {
    }

    /**
     * 设定对象在世界中唯一的ID
     * 
     * @param id
     *            唯一的ID
     */
    public void setId(final int id) {
        this._id = id;
    }

    /**
     * 设置对象存在在地图上的L1Location
     */
    public void setLocation(final int x, final int y, final int mapid) {
        this._loc.setX(x);
        this._loc.setY(y);
        this._loc.setMap(mapid);
    }

    /**
     * 设置对象存在在地图上的L1Location
     */
    public void setLocation(final L1Location loc) {
        this._loc.setX(loc.getX());
        this._loc.setY(loc.getY());
        this._loc.setMap(loc.getMapId());
    }

    /**
     * 设定对象所存在的地图
     * 
     * @param map
     *            设定地图
     */
    public void setMap(final L1Map map) {
        if (map == null) {
            throw new NullPointerException();
        }
        this._loc.setMap(map);
    }

    /**
     * 设定对象所存在的地图ID
     * 
     * @param mapId
     *            地图ID
     */
    public void setMap(final short mapId) {
        this._loc.setMap(L1WorldMap.getInstance().getMap(mapId));
    }

    /**
     * 设定对象在地图上的X轴值
     * 
     * @param x
     *            座标X轴值
     */
    public void setX(final int x) {
        this._loc.setX(x);
    }

    /**
     * 设定对象在地图上的Y轴值
     * 
     * @param y
     *            座标Y轴值
     */
    public void setY(final int y) {
        this._loc.setY(y);
    }
}
