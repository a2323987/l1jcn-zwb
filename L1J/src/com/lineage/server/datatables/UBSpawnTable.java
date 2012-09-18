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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.model.L1UbPattern;
import com.lineage.server.model.L1UbSpawn;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 无限大战产生怪物资料表
 */
public class UBSpawnTable {

    private static Logger _log = Logger.getLogger(UBSpawnTable.class.getName());

    private static UBSpawnTable _instance;

    public static UBSpawnTable getInstance() {
        if (_instance == null) {
            _instance = new UBSpawnTable();
        }
        return _instance;
    }

    private final Map<Integer, L1UbSpawn> _spawnTable = Maps.newMap();

    private UBSpawnTable() {
        this.loadSpawnTable();
    }

    /**
     * 返回指定UBID模式的最大数量。
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

    public L1UbPattern getPattern(final int ubId, final int patternNumer) {
        final L1UbPattern pattern = new L1UbPattern();
        for (final L1UbSpawn spawn : this._spawnTable.values()) {
            if ((spawn.getUbId() == ubId)
                    && (spawn.getPattern() == patternNumer)) {
                pattern.addSpawn(spawn.getGroup(), spawn);
            }
        }
        pattern.freeze();

        return pattern;
    }

    public L1UbSpawn getSpawn(final int spawnId) {
        return this._spawnTable.get(spawnId);
    }

    private void loadSpawnTable() {

        java.sql.Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM spawnlist_ub");
            rs = pstm.executeQuery();

            while (rs.next()) {
                final L1Npc npcTemp = NpcTable.getInstance().getTemplate(
                        rs.getInt(6));
                if (npcTemp == null) {
                    continue;
                }

                final L1UbSpawn spawnDat = new L1UbSpawn();
                spawnDat.setId(rs.getInt(1));
                spawnDat.setUbId(rs.getInt(2));
                spawnDat.setPattern(rs.getInt(3));
                spawnDat.setGroup(rs.getInt(4));
                spawnDat.setName(npcTemp.get_name());
                spawnDat.setNpcTemplateId(rs.getInt(6));
                spawnDat.setAmount(rs.getInt(7));
                spawnDat.setSpawnDelay(rs.getInt(8));
                spawnDat.setSealCount(rs.getInt(9));

                this._spawnTable.put(spawnDat.getId(), spawnDat);
            }
        } catch (final SQLException e) {
            // problem with initializing spawn, go to next one
            _log.warning("spawn couldnt 被初始化:" + e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
        _log.config("UB安置怪物列表 " + this._spawnTable.size() + "件");
    }
}
