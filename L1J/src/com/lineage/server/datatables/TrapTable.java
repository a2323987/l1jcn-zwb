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

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.model.trap.L1Trap;
import com.lineage.server.storage.TrapStorage;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 陷阱资料表
 */
public class TrapTable {

    private class SqlTrapStorage implements TrapStorage {
        private final ResultSet _rs;

        public SqlTrapStorage(final ResultSet rs) {
            this._rs = rs;
        }

        @Override
        public boolean getBoolean(final String name) {
            try {
                return this._rs.getBoolean(name);
            } catch (final SQLException e) {
            }
            return false;
        }

        @Override
        public int getInt(final String name) {
            try {
                return this._rs.getInt(name);
            } catch (final SQLException e) {

            }
            return 0;
        }

        @Override
        public String getString(final String name) {
            try {
                return this._rs.getString(name);
            } catch (final SQLException e) {
            }
            return "";
        }
    }

    private static Logger _log = Logger.getLogger(TrapTable.class.getName());

    private static TrapTable _instance;

    public static TrapTable getInstance() {
        if (_instance == null) {
            _instance = new TrapTable();
        }
        return _instance;
    }

    public static void reload() {
        final TrapTable oldInstance = _instance;
        _instance = new TrapTable();

        oldInstance._traps.clear();
    }

    private final Map<Integer, L1Trap> _traps = Maps.newMap();

    private TrapTable() {
        this.initialize();
    }

    private L1Trap createTrapInstance(final String name,
            final TrapStorage storage) throws Exception {
        final String packageName = "com.lineage.server.model.trap.";

        final Constructor<?> con = Class.forName(packageName + name)
                .getConstructor(new Class[] { TrapStorage.class });
        return (L1Trap) con.newInstance(storage);
    }

    public L1Trap getTemplate(final int id) {
        return this._traps.get(id);
    }

    private void initialize() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = L1DatabaseFactory.getInstance().getConnection();

            pstm = con.prepareStatement("SELECT * FROM trap");

            rs = pstm.executeQuery();

            while (rs.next()) {
                final String typeName = rs.getString("type");

                final L1Trap trap = this.createTrapInstance(typeName,
                        new SqlTrapStorage(rs));

                this._traps.put(trap.getId(), trap);
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }
}
