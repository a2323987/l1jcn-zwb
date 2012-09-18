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
 * 掉落物品几率资料表
 */
public final class DropItemTable {

    /**
     * 掉落物品几率资料
     */
    private class dropItemData {
        public double dropRate = 1;

        public double dropAmount = 1;

        public dropItemData() {
            // TODO Auto-generated constructor stub
        }
    }

    private static Logger _log = Logger
            .getLogger(DropItemTable.class.getName());

    private static DropItemTable _instance;

    public static DropItemTable getInstance() {
        if (_instance == null) {
            _instance = new DropItemTable();
        }
        return _instance;
    }

    /** 掉落物品 */
    private final Map<Integer, dropItemData> _dropItem = Maps.newMap();

    private DropItemTable() {
        this.loadMapsFromDatabase();
    }

    /**
     * 取得掉落数量
     * 
     * @param itemId
     */
    public double getDropAmount(final int itemId) {
        final dropItemData data = this._dropItem.get(itemId);
        if (data == null) {
            return 1;
        }
        return data.dropAmount;
    }

    /**
     * 取得掉落倍率
     * 
     * @param itemId
     */
    public double getDropRate(final int itemId) {
        final dropItemData data = this._dropItem.get(itemId);
        if (data == null) {
            return 1;
        }
        return data.dropRate;
    }

    /**
     * 从数据库中加载地图
     */
    private void loadMapsFromDatabase() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM drop_item");

            for (rs = pstm.executeQuery(); rs.next();) {
                final dropItemData data = new dropItemData();
                final int itemId = rs.getInt("item_id");
                data.dropRate = rs.getDouble("drop_rate");
                data.dropAmount = rs.getDouble("drop_amount");

                this._dropItem.put(new Integer(itemId), data);
            }

            _log.config("drop_item " + this._dropItem.size());
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

}
