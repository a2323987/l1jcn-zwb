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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.model.map.L1Map;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.serverpackets.ServerBasePacket;
import com.lineage.server.types.Point;
import com.lineage.server.utils.collections.Lists;
import com.lineage.server.utils.collections.Maps;

/**
 * 世界
 */
public class L1World {

    private static Logger _log = Logger.getLogger(L1World.class.getName());

    public static L1World getInstance() {
        if (_instance == null) {
            _instance = new L1World();
        }
        return _instance;
    }

    /**
     * 所有角色
     */
    private final Map<String, L1PcInstance> _allPlayers;

    /**
     * 所有宠物
     */
    private final Map<Integer, L1PetInstance> _allPets;

    /**
     * 所有召唤物
     */
    private final Map<Integer, L1SummonInstance> _allSummons;

    /**
     * 所有对象
     */
    private final Map<Integer, L1Object> _allObjects;

    /**
     * 所有可见对象
     */
    private final Map<Integer, L1Object>[] _visibleObjects;

    /**
     * 所有战争
     */
    private final List<L1War> _allWars;

    /**
     * 所有血盟
     */
    private final Map<String, L1Clan> _allClans;

    /**
     * 天气
     */
    private int _weather = 4;

    /**
     * 开启时间聊天
     */
    private boolean _worldChatEnabled = true;

    /**
     * 处理全部贡献
     */
    private boolean _processingContributionTotal = false;

    /**
     * 最大地图编号
     */
    private static final int MAX_MAP_ID = 10000;

    private static L1World _instance;

    // _allObjectsのビュー
    private Collection<L1Object> _allValues;

    // _allPlayers查看
    private Collection<L1PcInstance> _allPlayerValues;

    // _allPets查看
    private Collection<L1PetInstance> _allPetValues;

    // _allSummonsのビュー
    private Collection<L1SummonInstance> _allSummonValues;

    // _allWars查看
    private List<L1War> _allWarList;

    // _allClans查看
    private Collection<L1Clan> _allClanValues;

    @SuppressWarnings("unchecked")
    private L1World() {
        this._allPlayers = Maps.newConcurrentMap(); // 所有玩家
        this._allPets = Maps.newConcurrentMap(); // 所有宠物
        this._allSummons = Maps.newConcurrentMap(); // 所有召唤怪
        this._allObjects = Maps.newConcurrentMap(); // 所有对象(L1ItemInstance入り、L1Inventoryはなし)
        this._visibleObjects = new Map[MAX_MAP_ID + 1]; // 映射每个对象(L1Inventory入り、L1ItemInstanceはなし)
        this._allWars = Lists.newConcurrentList(); // 所有战争
        this._allClans = Maps.newConcurrentMap(); // 所有血盟(Online/Offline两个)

        for (int i = 0; i <= MAX_MAP_ID; i++) {
            this._visibleObjects[i] = Maps.newConcurrentMap();
        }
    }

    /**
     * 增加可见对象
     * 
     * @param object
     *            对象
     */
    public void addVisibleObject(final L1Object object) {
        if (object.getMapId() <= MAX_MAP_ID) {
            this._visibleObjects[object.getMapId()].put(object.getId(), object);
        }
    }

    /**
     * 增加战争
     * 
     * @param war
     *            战争
     */
    public void addWar(final L1War war) {
        if (!this._allWars.contains(war)) {
            this._allWars.add(war);
        }
    }

    /**
     * 给所有在世界上的玩家发送数据包。
     * 
     * @param packet
     *            ServerBasePacket对象，表示要发送的数据包。
     */
    public void broadcastPacketToAll(final ServerBasePacket packet) {
        _log.finest("玩家通知 : " + this.getAllPlayers().size());
        for (final L1PcInstance pc : this.getAllPlayers()) {
            pc.sendPackets(packet);
        }
    }

    /**
     * 给所有在世界上的玩家发送发送服务器信息。
     * 
     * @param message
     *            发送信息
     */
    public void broadcastServerMessage(final String message) {
        this.broadcastPacketToAll(new S_SystemMessage(message));
    }

    /**
     * 重置全部状态。
     */
    public void clear() {
        _instance = new L1World();
    }

    /**
	 * 
	 */
    private Map<Integer, Integer> createLineMap(final Point src,
            final Point target) {
        final Map<Integer, Integer> lineMap = Maps.newConcurrentMap();

        /*
         * http://www2.starcat.ne.jp/~fussy/algo/algo1-1.htmより
         */
        int E;
        int x;
        int y;
        int key;
        int i;
        final int x0 = src.getX();
        final int y0 = src.getY();
        final int x1 = target.getX();
        final int y1 = target.getY();
        final int sx = (x1 > x0) ? 1 : -1;
        final int dx = (x1 > x0) ? x1 - x0 : x0 - x1;
        final int sy = (y1 > y0) ? 1 : -1;
        final int dy = (y1 > y0) ? y1 - y0 : y0 - y1;

        x = x0;
        y = y0;
        /* 傾きが1以下の場合 */
        if (dx >= dy) {
            E = -dx;
            for (i = 0; i <= dx; i++) {
                key = (x << 16) + y;
                lineMap.put(key, key);
                x += sx;
                E += 2 * dy;
                if (E >= 0) {
                    y += sy;
                    E -= 2 * dx;
                }
            }
            /* 傾きが1より大きい場合 */
        } else {
            E = -dy;
            for (i = 0; i <= dy; i++) {
                key = (x << 16) + y;
                lineMap.put(key, key);
                y += sy;
                E += 2 * dx;
                if (E >= 0) {
                    x += sx;
                    E -= 2 * dy;
                }
            }
        }

        return lineMap;
    }

    /**
     * 发现对象
     * 
     * @param oID
     *            对象ID
     * @return
     */
    public L1Object findObject(final int oID) {
        return this._allObjects.get(oID);
    }

    /**
     * 取得所有血盟
     * 
     * @return
     */
    public Collection<L1Clan> getAllClans() {
        final Collection<L1Clan> vs = this._allClanValues;
        return (vs != null) ? vs : (this._allClanValues = Collections
                .unmodifiableCollection(this._allClans.values()));
    }

    /**
     * 取得所有宠物
     * 
     * @return
     */
    public Collection<L1PetInstance> getAllPets() {
        final Collection<L1PetInstance> vs = this._allPetValues;
        return (vs != null) ? vs : (this._allPetValues = Collections
                .unmodifiableCollection(this._allPets.values()));
    }

    /**
     * 取得所有角色
     * 
     * @return
     */
    public Collection<L1PcInstance> getAllPlayers() {
        final Collection<L1PcInstance> vs = this._allPlayerValues;
        return (vs != null) ? vs : (this._allPlayerValues = Collections
                .unmodifiableCollection(this._allPlayers.values()));
    }

    /**
     * 取得所有召唤物
     * 
     * @return
     */
    public Collection<L1SummonInstance> getAllSummons() {
        final Collection<L1SummonInstance> vs = this._allSummonValues;
        return (vs != null) ? vs : (this._allSummonValues = Collections
                .unmodifiableCollection(this._allSummons.values()));
    }

    /**
     * 取得所有可见对象
     * 
     * @return
     */
    public final Map<Integer, L1Object> getAllVisibleObjects() {
        return this._allObjects;
    }

    /**
     * 取得血盟
     * 
     * @param clan_name
     *            血盟名称
     * @return
     */
    public L1Clan getClan(final String clan_name) {
        return this._allClans.get(clan_name);
    }

    /**
     * 取得地面道具
     * 
     * @param x
     *            X坐标
     * @param y
     *            Y坐标
     * @param map
     *            地图ID
     * @return object 对象
     */
    public L1GroundInventory getInventory(final int x, final int y,
            final short map) {
        final int inventoryKey = ((x - 30000) * 10000 + (y - 30000)) * -1; // xyのマイナス値をインベントリキーとして使用

        final Object object = this._visibleObjects[map].get(inventoryKey);
        if (object == null) {
            return new L1GroundInventory(inventoryKey, x, y, map);
        }
        return (L1GroundInventory) object;
    }

    /**
     * 取得地面道具
     * 
     * @param loc
     * @return 道具的XY坐标与地图ID
     */
    public L1GroundInventory getInventory(final L1Location loc) {
        return this.getInventory(loc.getX(), loc.getY(), (short) loc.getMap()
                .getId());
    }

    /**
     * 取得对象
     * 
     * @return
     */
    public Collection<L1Object> getObject() {
        final Collection<L1Object> vs = this._allValues;
        return (vs != null) ? vs : (this._allValues = Collections
                .unmodifiableCollection(this._allObjects.values()));
    }

    /**
     * 取得指定名称的玩家。(世界上)
     * 
     * @param name
     *            - 玩家名称(大写小写字符将被忽略)
     * @return 指定的名称L1PcInstance。如果没有合适的玩家返回null。
     */
    public L1PcInstance getPlayer(final String name) {
        if (this._allPlayers.containsKey(name)) {
            return this._allPlayers.get(name);
        }
        for (final L1PcInstance each : this.getAllPlayers()) {
            if (each.getName().equalsIgnoreCase(name)) {
                return each;
            }
        }
        return null;
    }

    /**
     * 取得对象认识范围内的玩家
     * 
     * @param object
     *            对象
     * @return
     */
    public List<L1PcInstance> getRecognizePlayer(final L1Object object) {
        return this.getVisiblePlayer(object, Config.PC_RECOGNIZE_RANGE);
    }

    /**
     * 取得区域
     * 
     * @param object
     *            对象
     * @return
     */
    public Object getRegion(final Object object) {
        return null;
    }

    /**
     * 取得可见Box对象 (范围)
     * 
     * @param object
     *            对象
     * @param heading
     *            面向
     * @param width
     *            宽度
     * @param height
     *            高度
     * @return
     */
    public List<L1Object> getVisibleBoxObjects(final L1Object object,
            final int heading, final int width, final int height) {
        final int x = object.getX();
        final int y = object.getY();
        final int map = object.getMapId();
        final L1Location location = object.getLocation();
        final List<L1Object> result = Lists.newList();
        final int headingRotate[] = { 6, 7, 0, 1, 2, 3, 4, 5 };
        final double cosSita = Math.cos(headingRotate[heading] * Math.PI / 4);
        final double sinSita = Math.sin(headingRotate[heading] * Math.PI / 4);

        if (map <= MAX_MAP_ID) {
            for (final L1Object element : this._visibleObjects[map].values()) {
                if (element.equals(object)) {
                    continue;
                }
                if (map != element.getMapId()) {
                    continue;
                }

                // 范围内相同的坐标重叠
                if (location.isSamePoint(element.getLocation())) {
                    result.add(element);
                    continue;
                }

                final int distance = location.getTileLineDistance(element
                        .getLocation());
                // 高度的直线距离、幅どちらよりも大きい場合、計算するまでもなく範囲外
                if ((distance > height) && (distance > width)) {
                    continue;
                }

                // objectの位置を原点とするための座標補正
                final int x1 = element.getX() - x;
                final int y1 = element.getY() - y;

                // Z軸回転させ角度を0度にする。
                final int rotX = (int) Math.round(x1 * cosSita + y1 * sinSita);
                final int rotY = (int) Math.round(-x1 * sinSita + y1 * cosSita);

                final int xmin = 0;
                final int xmax = height;
                final int ymin = -width;
                final int ymax = width;

                // 奥行きが射程とかみ合わないので直線距離で判定するように変更。
                // if (rotX > xmin && rotX <= xmax && rotY >= ymin && rotY <=
                // ymax) {
                if ((rotX > xmin) && (distance <= xmax) && (rotY >= ymin)
                        && (rotY <= ymax)) {
                    result.add(element);
                }
            }
        }

        return result;
    }

    /**
     * 取得可见Line对象 (直线距离)
     * 
     * @param src
     *            原始
     * @param target
     *            目标
     * @return
     */
    public List<L1Object> getVisibleLineObjects(final L1Object src,
            final L1Object target) {
        final Map<Integer, Integer> lineMap = this.createLineMap(
                src.getLocation(), target.getLocation());

        final int map = target.getMapId();
        final List<L1Object> result = Lists.newList();

        if (map <= MAX_MAP_ID) {
            for (final L1Object element : this._visibleObjects[map].values()) {
                if (element.equals(src)) {
                    continue;
                }

                final int key = (element.getX() << 16) + element.getY();
                if (lineMap.containsKey(key)) {
                    result.add(element);
                }
            }
        }

        return result;
    }

    /**
     * 取得可见对象
     * 
     * @return
     */
    public final Map<Integer, L1Object>[] getVisibleObjects() {
        return this._visibleObjects;
    }

    /**
     * 取得可见对象
     * 
     * @param mapId
     *            地图编号
     * @return
     */
    public final Map<Integer, L1Object> getVisibleObjects(final int mapId) {
        return this._visibleObjects[mapId];
    }

    /**
     * 取得可见对象
     * 
     * @param object
     *            对象
     * @return getVisibleObjects
     */
    public List<L1Object> getVisibleObjects(final L1Object object) {
        return this.getVisibleObjects(object, -1);
    }

    /**
     * 取得可见对象
     * 
     * @param object
     *            对象
     * @param radius
     *            半径
     * @return
     */
    public List<L1Object> getVisibleObjects(final L1Object object,
            final int radius) {
        final L1Map map = object.getMap();
        final Point pt = object.getLocation();
        final List<L1Object> result = Lists.newList();
        if (map.getId() <= MAX_MAP_ID) {
            for (final L1Object element : this._visibleObjects[map.getId()]
                    .values()) {
                if (element.equals(object)) {
                    continue;
                }
                if (map != element.getMap()) {
                    continue;
                }

                if (radius == -1) {
                    if (pt.isInScreen(element.getLocation())) {
                        result.add(element);
                    }
                } else if (radius == 0) {
                    if (pt.isSamePoint(element.getLocation())) {
                        result.add(element);
                    }
                } else {
                    if (pt.getTileLineDistance(element.getLocation()) <= radius) {
                        result.add(element);
                    }
                }
            }
        }

        return result;
    }

    /**
     * 取得可见范围内的角色 (画面内)
     * 
     * @param object
     *            角色的object
     */
    public List<L1PcInstance> getVisiblePlayer(final L1Object object) {
        return this.getVisiblePlayer(object, -1);
    }

    /**
     * 取得可见角色
     * 
     * @param object
     *            对象
     * @param radius
     *            半径
     * @return
     */
    public List<L1PcInstance> getVisiblePlayer(final L1Object object,
            final int radius) {
        final int map = object.getMapId();
        final Point pt = object.getLocation();
        final List<L1PcInstance> result = Lists.newList();

        for (final L1PcInstance element : this._allPlayers.values()) {
            if (element.equals(object)) {
                continue;
            }

            if (map != element.getMapId()) {
                continue;
            }

            if (radius == -1) {
                if (pt.isInScreen(element.getLocation())) {
                    result.add(element);
                }
            } else if (radius == 0) {
                if (pt.isSamePoint(element.getLocation())) {
                    result.add(element);
                }
            } else {
                if (pt.getTileLineDistance(element.getLocation()) <= radius) {
                    result.add(element);
                }
            }
        }
        return result;
    }

    /**
     * 取得可见角色 (除目标视野)
     * 
     * @param object
     *            对象
     * @param target
     *            目标
     * @return
     */
    public List<L1PcInstance> getVisiblePlayerExceptTargetSight(
            final L1Object object, final L1Object target) {
        final int map = object.getMapId();
        final Point objectPt = object.getLocation();
        final Point targetPt = target.getLocation();
        final List<L1PcInstance> result = Lists.newList();

        for (final L1PcInstance element : this._allPlayers.values()) {
            if (element.equals(object)) {
                continue;
            }

            if (map != element.getMapId()) {
                continue;
            }

            if (Config.PC_RECOGNIZE_RANGE == -1) {
                if (objectPt.isInScreen(element.getLocation())) {
                    if (!targetPt.isInScreen(element.getLocation())) {
                        result.add(element);
                    }
                }
            } else {
                if (objectPt.getTileLineDistance(element.getLocation()) <= Config.PC_RECOGNIZE_RANGE) {
                    if (targetPt.getTileLineDistance(element.getLocation()) > Config.PC_RECOGNIZE_RANGE) {
                        result.add(element);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 取得可见坐标点
     * 
     * @param loc
     *            位置
     * @param radius
     *            半径
     * @return
     */
    public List<L1Object> getVisiblePoint(final L1Location loc, final int radius) {
        final List<L1Object> result = Lists.newList();
        final int mapId = loc.getMapId(); // ループ内で呼ぶと重いため

        if (mapId <= MAX_MAP_ID) {
            for (final L1Object element : this._visibleObjects[mapId].values()) {
                if (mapId != element.getMapId()) {
                    continue;
                }

                if (loc.getTileLineDistance(element.getLocation()) <= radius) {
                    result.add(element);
                }
            }
        }

        return result;
    }

    /**
     * 取得战争列表
     * 
     * @return
     */
    public List<L1War> getWarList() {
        final List<L1War> vs = this._allWarList;
        return (vs != null) ? vs : (this._allWarList = Collections
                .unmodifiableList(this._allWars));
    }

    /**
     * 取得天气
     * 
     * @return
     */
    public int getWeather() {
        return this._weather;
    }

    /**
     * 处理全部贡献
     * 
     * @return
     */
    public boolean isProcessingContributionTotal() {
        return this._processingContributionTotal;
    }

    /**
     * 开启世界聊天
     * 
     * @return
     */
    public boolean isWorldChatElabled() {
        return this._worldChatEnabled;
    }

    /**
     * 移动可见对象
     * 
     * @param object
     *            对象
     * @param newMap
     *            新地图ID
     */
    public void moveVisibleObject(final L1Object object, final int newMap) // set_Mapで新しいMapにするまえに呼ぶこと
    {
        if (object.getMapId() != newMap) {
            if (object.getMapId() <= MAX_MAP_ID) {
                this._visibleObjects[object.getMapId()].remove(object.getId());
            }
            if (newMap <= MAX_MAP_ID) {
                this._visibleObjects[newMap].put(object.getId(), object);
            }
        }
    }

    /**
     * 删除血盟
     * 
     * @param clan
     *            血盟
     */
    public void removeClan(final L1Clan clan) {
        final L1Clan temp = this.getClan(clan.getClanName());
        if (temp != null) {
            this._allClans.remove(clan.getClanName());
        }
    }

    /**
     * 删除对象
     * 
     * @param object
     *            对象
     */
    public void removeObject(final L1Object object) {
        if (object == null) {
            throw new NullPointerException();
        }

        this._allObjects.remove(object.getId());
        if (object instanceof L1PcInstance) {
            this._allPlayers.remove(((L1PcInstance) object).getName());
        }
        if (object instanceof L1PetInstance) {
            this._allPets.remove(object.getId());
        }
        if (object instanceof L1SummonInstance) {
            this._allSummons.remove(object.getId());
        }
    }

    /**
     * 删除可见对象
     * 
     * @param object
     *            对象
     */
    public void removeVisibleObject(final L1Object object) {
        if (object.getMapId() <= MAX_MAP_ID) {
            this._visibleObjects[object.getMapId()].remove(object.getId());
        }
    }

    /**
     * 删除战争
     * 
     * @param war
     *            战争
     */
    public void removeWar(final L1War war) {
        if (this._allWars.contains(war)) {
            this._allWars.remove(war);
        }
    }

    /**
     * 设置开启世界聊天
     * 
     * @param flag
     */
    public void set_worldChatElabled(final boolean flag) {
        this._worldChatEnabled = flag;
    }

    /**
     * 设置处理全部贡献
     * 
     * @param flag
     */
    public void setProcessingContributionTotal(final boolean flag) {
        this._processingContributionTotal = flag;
    }

    /**
     * 设置天气
     * 
     * @param weather
     *            天气
     */
    public void setWeather(final int weather) {
        this._weather = weather;
    }

    /**
     * 储存血盟
     * 
     * @param clan
     *            血盟
     */
    public void storeClan(final L1Clan clan) {
        final L1Clan temp = this.getClan(clan.getClanName());
        if (temp == null) {
            this._allClans.put(clan.getClanName(), clan);
        }
    }

    /**
     * 储存对象
     * 
     * @param object
     *            对象
     */
    public void storeObject(final L1Object object) {
        if (object == null) {
            throw new NullPointerException();
        }

        this._allObjects.put(object.getId(), object);
        if (object instanceof L1PcInstance) {
            this._allPlayers.put(((L1PcInstance) object).getName(),
                    (L1PcInstance) object);
        }
        if (object instanceof L1PetInstance) {
            this._allPets.put(object.getId(), (L1PetInstance) object);
        }
        if (object instanceof L1SummonInstance) {
            this._allSummons.put(object.getId(), (L1SummonInstance) object);
        }
    }
}
