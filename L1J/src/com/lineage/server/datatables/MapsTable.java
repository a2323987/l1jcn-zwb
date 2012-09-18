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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 地图资料表
 */
public final class MapsTable {

    private class MapData {
        /** 开始坐标X */
        public int startX = 0;

        /** 结束坐标X */
        public int endX = 0;
        /** 开始坐标Y */
        public int startY = 0;
        /** 结束坐标Y */
        public int endY = 0;
        /** 怪物数量 */
        public double monster_amount = 1;
        /** 掉宝倍率 */
        public double dropRate = 1;
        /** 水下 */
        public boolean isUnderwater = false;
        /** 记忆坐标 */
        public boolean markable = false;
        /** 传送 */
        public boolean teleportable = false;
        /** 整个地图传送 */
        public boolean escapable = false;
        /** 复活 */
        public boolean isUseResurrection = false;
        /** 使用魔杖 */
        public boolean isUsePainwand = false;
        /** 死亡惩罚 */
        public boolean isEnabledDeathPenalty = false;
        /** 召唤宠物 */
        public boolean isTakePets = false;
        /** 召回宠物 */
        public boolean isRecallPets = false;
        /** 使用道具 */
        public boolean isUsableItem = false;
        /** 使用技能 */
        public boolean isUsableSkill = false;

        public MapData() {
            // TODO Auto-generated constructor stub
        }
    }

    private static Logger _log = Logger.getLogger(MapsTable.class.getName());

    private static MapsTable _instance;

    /**
     * 返回一个MapsTable的实例。
     * 
     * @return MapsTable的实例
     */
    public static MapsTable getInstance() {
        if (_instance == null) {
            _instance = new MapsTable();
        }
        return _instance;
    }

    /**
     * 地图ID的Key、Valueにテレポート可否フラグが格納されるHashMap
     */
    private final Map<Integer, MapData> _maps = Maps.newMap();

    /**
     * 新しくMapsTableオブジェクトを生成し、マップのテレポート可否フラグを読み込む。
     */
    private MapsTable() {
        this.loadMapsFromDatabase();
    }

    /**
     * 返回地图的掉宝倍率
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return 掉宝倍率
     */
    public double getDropRate(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return 0;
        }
        return map.dropRate;
    }

    /**
     * 取得地图的X结束坐标。
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return X结束坐标
     */
    public int getEndX(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return 0;
        }
        return this._maps.get(mapId).endX;
    }

    /**
     * 取得地图的Y结束坐标。
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return Y结束坐标
     */
    public int getEndY(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return 0;
        }
        return this._maps.get(mapId).endY;
    }

    /**
     * 返回地图的怪物数量规模
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return 怪物数量规模
     */
    public double getMonsterAmount(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return 0;
        }
        return map.monster_amount;
    }

    /**
     * 取得地图的X开始坐标。
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return X开始坐标
     */
    public int getStartX(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return 0;
        }
        return this._maps.get(mapId).startX;
    }

    /**
     * 取得地图的Y开始坐标。
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return Y开始坐标
     */
    public int getStartY(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return 0;
        }
        return this._maps.get(mapId).startY;
    }

    /**
     * 地图、返回是否有死亡惩罚。
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return 如果有死亡惩罚true
     */
    public boolean isEnabledDeathPenalty(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return false;
        }
        return this._maps.get(mapId).isEnabledDeathPenalty;
    }

    /**
     * 地图、返回是否可以整个MAP传送。
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return 如果可能true
     */
    public boolean isEscapable(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return false;
        }
        return this._maps.get(mapId).escapable;
    }

    /**
     * 地图、返回能否记忆坐标。
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return 如果能记忆坐标true
     */
    public boolean isMarkable(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return false;
        }
        return this._maps.get(mapId).markable;
    }

    /**
     * 地图、返回是否可以召回宠物。
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return 如果可以 召回宠物true
     */
    public boolean isRecallPets(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return false;
        }
        return this._maps.get(mapId).isRecallPets;
    }

    /**
     * 地图、返回是否可以召唤宠物。
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return 如果可以召唤宠物true
     */
    public boolean isTakePets(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return false;
        }
        return this._maps.get(mapId).isTakePets;
    }

    /**
     * 地图、返回能否随机传送。
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return 如果可能true
     */
    public boolean isTeleportable(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return false;
        }
        return this._maps.get(mapId).teleportable;
    }

    /**
     * 地图、返回是否在水中。
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return 如果在水中true
     */
    public boolean isUnderwater(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return false;
        }
        return this._maps.get(mapId).isUnderwater;
    }

    /**
     * 地图、返回是否可以使用道具。
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return 如果可以使用道具true
     */
    public boolean isUsableItem(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return false;
        }
        return this._maps.get(mapId).isUsableItem;
    }

    /**
     * 地图、返回是否可以使用技能。
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return 如果可以使用技能true
     */
    public boolean isUsableSkill(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return false;
        }
        return this._maps.get(mapId).isUsableSkill;
    }

    /**
     * 地图、返回能否使用魔杖。
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return 如果能使用魔杖 true
     */
    public boolean isUsePainwand(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return false;
        }
        return this._maps.get(mapId).isUsePainwand;
    }

    /**
     * 地图、返回能否复活。
     * 
     * @param mapId
     *            检查地图的地图ID
     * @return 如果能复活true
     */
    public boolean isUseResurrection(final int mapId) {
        final MapData map = this._maps.get(mapId);
        if (map == null) {
            return false;
        }
        return this._maps.get(mapId).isUseResurrection;
    }

    /**
     * 从数据库中加载地图。
     */
    private void loadMapsFromDatabase() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM mapids");

            for (rs = pstm.executeQuery(); rs.next();) {
                final MapData data = new MapData();
                final int mapId = rs.getInt("mapid");
                // rs.getString("locationname");
                data.startX = rs.getInt("startX");
                data.endX = rs.getInt("endX");
                data.startY = rs.getInt("startY");
                data.endY = rs.getInt("endY");
                data.monster_amount = rs.getDouble("monster_amount");
                data.dropRate = rs.getDouble("drop_rate");
                data.isUnderwater = rs.getBoolean("underwater");
                data.markable = rs.getBoolean("markable");
                data.teleportable = rs.getBoolean("teleportable");
                data.escapable = rs.getBoolean("escapable");
                data.isUseResurrection = rs.getBoolean("resurrection");
                data.isUsePainwand = rs.getBoolean("painwand");
                data.isEnabledDeathPenalty = rs.getBoolean("penalty");
                data.isTakePets = rs.getBoolean("take_pets");
                data.isRecallPets = rs.getBoolean("recall_pets");
                data.isUsableItem = rs.getBoolean("usable_item");
                data.isUsableSkill = rs.getBoolean("usable_skill");

                this._maps.put(new Integer(mapId), data);
            }

            _log.config("Maps " + this._maps.size());
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

}
