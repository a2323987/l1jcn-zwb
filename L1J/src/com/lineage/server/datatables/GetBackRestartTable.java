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
import com.lineage.server.templates.L1GetBackRestart;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 回城坐标资料表
 */
public class GetBackRestartTable {

    private static Logger _log = Logger.getLogger(GetBackRestartTable.class
            .getName());

    private static GetBackRestartTable _instance;

    public static GetBackRestartTable getInstance() {
        if (_instance == null) {
            _instance = new GetBackRestartTable();
        }
        return _instance;
    }

    private final Map<Integer, L1GetBackRestart> _getbackrestart = Maps
            .newMap();

    public GetBackRestartTable() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM getback_restart");
            rs = pstm.executeQuery();
            while (rs.next()) {
                final L1GetBackRestart gbr = new L1GetBackRestart();
                final int area = rs.getInt("area"); // 范围
                gbr.setArea(area);
                gbr.setLocX(rs.getInt("locx"));
                gbr.setLocY(rs.getInt("locy"));
                gbr.setMapId(rs.getShort("mapid"));

                this._getbackrestart.put(new Integer(area), gbr);
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    /**
     * 取得回城资料清单
     * 
     * @return
     */
    public L1GetBackRestart[] getGetBackRestartTableList() {
        return this._getbackrestart.values().toArray(
                new L1GetBackRestart[this._getbackrestart.size()]);
    }

}
