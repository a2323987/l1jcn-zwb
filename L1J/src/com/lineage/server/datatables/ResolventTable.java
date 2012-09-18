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
 * 可溶解道具资料表
 */
public final class ResolventTable {

    private static Logger _log = Logger.getLogger(ResolventTable.class
            .getName());

    private static ResolventTable _instance;

    public static ResolventTable getInstance() {
        if (_instance == null) {
            _instance = new ResolventTable();
        }
        return _instance;
    }

    private final Map<Integer, Integer> _resolvent = Maps.newMap();

    private ResolventTable() {
        this.loadMapsFromDatabase();
    }

    public int getCrystalCount(final int itemId) {
        int crystalCount = 0;
        if (this._resolvent.containsKey(itemId)) {
            crystalCount = this._resolvent.get(itemId);
        }
        return crystalCount;
    }

    private void loadMapsFromDatabase() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM resolvent");

            for (rs = pstm.executeQuery(); rs.next();) {
                final int itemId = rs.getInt("item_id");
                final int crystalCount = rs.getInt("crystal_count");

                this._resolvent.put(new Integer(itemId), crystalCount);
            }

            _log.config("resolvent " + this._resolvent.size());
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

}
