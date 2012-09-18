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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.model.L1UltimateBattle;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 无限大战资料表
 */
public class UBTable {

    private static Logger _log = Logger.getLogger(UBTable.class.getName());

    private static UBTable _instance = new UBTable();

    public static UBTable getInstance() {
        return _instance;
    }

    private final Map<Integer, L1UltimateBattle> _ub = Maps.newMap();

    private UBTable() {
        this.loadTable();
    }

    public Collection<L1UltimateBattle> getAllUb() {
        return Collections.unmodifiableCollection(this._ub.values());
    }

    /**
     * 返回给定UBID的模式的最大数量。
     * 
     * @param ubId
     *            检查UBID。
     * @return 模式的最大数量。
     */
    public int getMaxPattern(final int ubId) {
        int n = 0;
        java.sql.Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT MAX(pattern) FROM spawnlist_ub WHERE ub_id=?");
            pstm.setInt(1, ubId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
        return n;
    }

    public L1UltimateBattle getUb(final int ubId) {
        return this._ub.get(ubId);
    }

    public L1UltimateBattle getUbForNpcId(final int npcId) {
        for (final L1UltimateBattle ub : this._ub.values()) {
            if (ub.containsManager(npcId)) {
                return ub;
            }
        }
        return null;
    }

    private void loadTable() {

        java.sql.Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM ub_settings");
            rs = pstm.executeQuery();

            while (rs.next()) {

                final L1UltimateBattle ub = new L1UltimateBattle();
                ub.setUbId(rs.getInt("ub_id"));
                ub.setMapId(rs.getShort("ub_mapid"));
                ub.setLocX1(rs.getInt("ub_area_x1"));
                ub.setLocY1(rs.getInt("ub_area_y1"));
                ub.setLocX2(rs.getInt("ub_area_x2"));
                ub.setLocY2(rs.getInt("ub_area_y2"));
                ub.setMinLevel(rs.getInt("min_lvl"));
                ub.setMaxLevel(rs.getInt("max_lvl"));
                ub.setMaxPlayer(rs.getInt("max_player"));
                ub.setEnterRoyal(rs.getBoolean("enter_royal"));
                ub.setEnterKnight(rs.getBoolean("enter_knight"));
                ub.setEnterMage(rs.getBoolean("enter_mage"));
                ub.setEnterElf(rs.getBoolean("enter_elf"));
                ub.setEnterDarkelf(rs.getBoolean("enter_darkelf"));
                ub.setEnterDragonKnight(rs.getBoolean("enter_dragonknight"));
                ub.setEnterIllusionist(rs.getBoolean("enter_illusionist"));
                ub.setEnterMale(rs.getBoolean("enter_male"));
                ub.setEnterFemale(rs.getBoolean("enter_female"));
                ub.setUsePot(rs.getBoolean("use_pot"));
                ub.setHpr(rs.getInt("hpr_bonus"));
                ub.setMpr(rs.getInt("mpr_bonus"));
                ub.resetLoc();

                this._ub.put(ub.getUbId(), ub);
            }
        } catch (final SQLException e) {
            _log.warning("ubsettings couldnt 被初始化:" + e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
        }

        // ub_managers load
        try {
            pstm = con.prepareStatement("SELECT * FROM ub_managers");
            rs = pstm.executeQuery();

            while (rs.next()) {
                final L1UltimateBattle ub = this.getUb(rs.getInt("ub_id"));
                if (ub != null) {
                    ub.addManager(rs.getInt("ub_manager_npc_id"));
                }
            }
        } catch (final SQLException e) {
            _log.warning("ub_managers couldnt 被初始化:" + e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
        }

        // ub_times load
        try {
            pstm = con.prepareStatement("SELECT * FROM ub_times");
            rs = pstm.executeQuery();

            while (rs.next()) {
                final L1UltimateBattle ub = this.getUb(rs.getInt("ub_id"));
                if (ub != null) {
                    ub.addUbTime(rs.getInt("ub_time"));
                }
            }
        } catch (final SQLException e) {
            _log.warning("ub_times couldnt 被初始化:" + e);
        } finally {
            SQLUtil.close(rs, pstm, con);
        }
        _log.config("UB名单 " + this._ub.size() + "件");
    }

}
