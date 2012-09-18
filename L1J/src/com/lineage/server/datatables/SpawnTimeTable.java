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
import com.lineage.server.templates.L1SpawnTime;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * NPC定时产生资料表
 */
public class SpawnTimeTable {

    private static Logger _log = Logger.getLogger(SpawnTimeTable.class
            .getName());

    private static SpawnTimeTable _instance;

    public static SpawnTimeTable getInstance() {
        if (_instance == null) {
            _instance = new SpawnTimeTable();
        }
        return _instance;
    }

    private final Map<Integer, L1SpawnTime> _times = Maps.newMap();

    private SpawnTimeTable() {
        this.load();
    }

    public L1SpawnTime get(final int id) {
        return this._times.get(id);
    }

    private void load() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM spawnlist_time");
            rs = pstm.executeQuery();
            while (rs.next()) {
                final int id = rs.getInt("spawn_id");
                final L1SpawnTime.L1SpawnTimeBuilder builder = new L1SpawnTime.L1SpawnTimeBuilder(
                        id);
                builder.setTimeStart(rs.getTime("time_start"));
                builder.setTimeEnd(rs.getTime("time_end"));
                // builder.setPeriodStart(rs.getTimestamp("period_start"));
                // builder.setPeriodEnd(rs.getTimestamp("period_end"));
                builder.setDeleteAtEndTime(rs.getBoolean("delete_at_endtime"));

                this._times.put(id, builder.build());
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }
}
