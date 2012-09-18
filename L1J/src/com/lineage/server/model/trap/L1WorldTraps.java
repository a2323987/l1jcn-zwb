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
package com.lineage.server.model.trap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.IdFactory;
import com.lineage.server.datatables.TrapTable;
import com.lineage.server.model.L1Location;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1TrapInstance;
import com.lineage.server.types.Point;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Lists;

/**
 * 世界陷阱
 */
public class L1WorldTraps {

    private class TrapSpawnTimer extends TimerTask {
        private final L1TrapInstance _targetTrap;

        public TrapSpawnTimer(final L1TrapInstance trap) {
            this._targetTrap = trap;
        }

        @Override
        public void run() {
            this._targetTrap.resetLocation();
            this._targetTrap.enableTrap();
        }
    }

    private static Logger _log = Logger.getLogger(L1WorldTraps.class.getName());

    public static L1WorldTraps getInstance() {
        if (_instance == null) {
            _instance = new L1WorldTraps();
        }
        return _instance;
    }

    public static void reloadTraps() {
        TrapTable.reload();
        final L1WorldTraps oldInstance = _instance;
        _instance = new L1WorldTraps();
        oldInstance.resetTimer();
        removeTraps(oldInstance._allTraps);
        removeTraps(oldInstance._allBases);
    }

    private static void removeTraps(final List<L1TrapInstance> traps) {
        for (final L1TrapInstance trap : traps) {
            trap.disableTrap();
            L1World.getInstance().removeVisibleObject(trap);
        }
    }

    private final List<L1TrapInstance> _allTraps = Lists.newList();

    private final List<L1TrapInstance> _allBases = Lists.newList();

    private Timer _timer = new Timer();

    private static L1WorldTraps _instance;

    private L1WorldTraps() {
        this.initialize();
    }

    private void disableTrap(final L1TrapInstance trap) {
        trap.disableTrap();

        synchronized (this) {
            this._timer.schedule(new TrapSpawnTimer(trap), trap.getSpan());
        }
    }

    private void initialize() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = L1DatabaseFactory.getInstance().getConnection();

            pstm = con.prepareStatement("SELECT * FROM spawnlist_trap");

            rs = pstm.executeQuery();

            while (rs.next()) {
                final int trapId = rs.getInt("trapId");
                final L1Trap trapTemp = TrapTable.getInstance().getTemplate(
                        trapId);
                final L1Location loc = new L1Location();
                loc.setMap(rs.getInt("mapId"));
                loc.setX(rs.getInt("locX"));
                loc.setY(rs.getInt("locY"));
                final Point rndPt = new Point();
                rndPt.setX(rs.getInt("locRndX"));
                rndPt.setY(rs.getInt("locRndY"));
                final int count = rs.getInt("count");
                final int span = rs.getInt("span");

                for (int i = 0; i < count; i++) {
                    final L1TrapInstance trap = new L1TrapInstance(IdFactory
                            .getInstance().nextId(), trapTemp, loc, rndPt, span);
                    L1World.getInstance().addVisibleObject(trap);
                    this._allTraps.add(trap);
                }
                final L1TrapInstance base = new L1TrapInstance(IdFactory
                        .getInstance().nextId(), loc);
                L1World.getInstance().addVisibleObject(base);
                this._allBases.add(base);
            }

        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    public void onDetection(final L1PcInstance caster) {
        final L1Location loc = caster.getLocation();

        for (final L1TrapInstance trap : this._allTraps) {
            if (trap.isEnable() && loc.isInScreen(trap.getLocation())) {
                trap.onDetection(caster);
                this.disableTrap(trap);
            }
        }
    }

    public void onPlayerMoved(final L1PcInstance player) {
        final L1Location loc = player.getLocation();

        for (final L1TrapInstance trap : this._allTraps) {
            if (trap.isEnable() && loc.equals(trap.getLocation())) {
                trap.onTrod(player);
                this.disableTrap(trap);
            }
        }
    }

    public void resetAllTraps() {
        for (final L1TrapInstance trap : this._allTraps) {
            trap.resetLocation();
            trap.enableTrap();
        }
    }

    private void resetTimer() {
        synchronized (this) {
            this._timer.cancel();
            this._timer = new Timer();
        }
    }
}
