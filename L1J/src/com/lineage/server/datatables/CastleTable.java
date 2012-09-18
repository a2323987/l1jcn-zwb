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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.templates.L1Castle;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 城堡资料表
 */
public class CastleTable {

    private static Logger _log = Logger.getLogger(CastleTable.class.getName());

    private static CastleTable _instance;

    public static CastleTable getInstance() {
        if (_instance == null) {
            _instance = new CastleTable();
        }
        return _instance;
    }

    private final Map<Integer, L1Castle> _castles = Maps.newConcurrentMap();

    private CastleTable() {
        this.load();
    }

    public L1Castle getCastleTable(final int id) {
        return this._castles.get(id);
    }

    public L1Castle[] getCastleTableList() {
        return this._castles.values().toArray(
                new L1Castle[this._castles.size()]);
    }

    private void load() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM castle");

            rs = pstm.executeQuery();

            while (rs.next()) {
                final L1Castle castle = new L1Castle(rs.getInt(1),
                        rs.getString(2));
                castle.setWarTime(this.timestampToCalendar((Timestamp) rs
                        .getObject(3)));
                castle.setTaxRate(rs.getInt(4));
                castle.setPublicMoney(rs.getInt(5));

                this._castles.put(castle.getId(), castle);
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    private Calendar timestampToCalendar(final Timestamp ts) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ts.getTime());
        return cal;
    }

    /**
     * 更新城堡
     * 
     * @param castle
     *            城堡
     */
    public void updateCastle(final L1Castle castle) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("UPDATE castle SET name=?, war_time=?, tax_rate=?, public_money=? WHERE castle_id=?");
            pstm.setString(1, castle.getName());
            final SimpleDateFormat sdf = new SimpleDateFormat(
                    "yyyy/MM/dd HH:mm:ss");
            final String fm = sdf.format(castle.getWarTime().getTime());
            pstm.setString(2, fm);
            pstm.setInt(3, castle.getTaxRate());
            pstm.setInt(4, castle.getPublicMoney());
            pstm.setInt(5, castle.getId());
            pstm.execute();

            this._castles.put(castle.getId(), castle);
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

}
