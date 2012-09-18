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

import com.lineage.Config;
import com.lineage.L1DatabaseFactory;
import com.lineage.server.model.L1Spawn;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.utils.NumberUtil;
import com.lineage.server.utils.PerformanceTimer;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 产生怪物资料表
 */
public class SpawnTable {

    private static Logger _log = Logger.getLogger(SpawnTable.class.getName());

    private static SpawnTable _instance;

    private static int calcCount(final L1Npc npc, final int count,
            final double rate) {
        if (rate == 0) {
            return 0;
        }
        if ((rate == 1) || npc.isAmountFixed()) {
            return count;
        }
        return NumberUtil.randomRound((count * rate));

    }

    public static SpawnTable getInstance() {
        if (_instance == null) {
            _instance = new SpawnTable();
        }
        return _instance;
    }

    public static void storeSpawn(final L1PcInstance pc, final L1Npc npc) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            final int count = 1;
            final int randomXY = 12;
            final int minRespawnDelay = 60;
            final int maxRespawnDelay = 120;
            final String note = npc.get_name();

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("INSERT INTO spawnlist SET location=?,count=?,npc_templateid=?,group_id=?,locx=?,locy=?,randomx=?,randomy=?,heading=?,min_respawn_delay=?,max_respawn_delay=?,mapid=?");
            pstm.setString(1, note);
            pstm.setInt(2, count);
            pstm.setInt(3, npc.get_npcId());
            pstm.setInt(4, 0);
            pstm.setInt(5, pc.getX());
            pstm.setInt(6, pc.getY());
            pstm.setInt(7, randomXY);
            pstm.setInt(8, randomXY);
            pstm.setInt(9, pc.getHeading());
            pstm.setInt(10, minRespawnDelay);
            pstm.setInt(11, maxRespawnDelay);
            pstm.setInt(12, pc.getMapId());
            pstm.execute();

        } catch (final Exception e) {
            NpcTable._log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    private final Map<Integer, L1Spawn> _spawntable = Maps.newMap();

    private int _highestId;

    private SpawnTable() {
        final PerformanceTimer timer = new PerformanceTimer();
        System.out.print("╠》正在产生 Mob...");
        this.fillSpawnTable();
        _log.config("怪物配置清单  " + this._spawntable.size() + "件");
        System.out.println("完成!\t\t耗时: " + timer.get() + "\t毫秒");
    }

    public void addNewSpawn(final L1Spawn spawn) {
        this._highestId++;
        spawn.setId(this._highestId);
        this._spawntable.put(new Integer(spawn.getId()), spawn);
    }

    private void fillSpawnTable() {

        int spawnCount = 0;
        java.sql.Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM spawnlist");
            rs = pstm.executeQuery();

            L1Spawn spawnDat;
            L1Npc template1;
            while (rs.next()) {
                if (Config.ALT_HALLOWEENIVENT == false) {
                    final int npcid = rs.getInt("id");
                    if ((npcid >= 26656) && (npcid <= 26734)) {
                        continue;
                    }
                }
                final int npcTemplateId = rs.getInt("npc_templateid");
                template1 = NpcTable.getInstance().getTemplate(npcTemplateId);
                int count;

                if (template1 == null) {
                    _log.warning("mob data for id:" + npcTemplateId
                            + " missing in npc table");
                    spawnDat = null;
                } else {
                    if (rs.getInt("count") == 0) {
                        continue;
                    }
                    final double amount_rate = MapsTable.getInstance()
                            .getMonsterAmount(rs.getShort("mapid"));
                    count = calcCount(template1, rs.getInt("count"),
                            amount_rate);
                    if (count == 0) {
                        continue;
                    }

                    spawnDat = new L1Spawn(template1);
                    spawnDat.setId(rs.getInt("id"));
                    spawnDat.setAmount(count);
                    spawnDat.setGroupId(rs.getInt("group_id"));
                    spawnDat.setLocX(rs.getInt("locx"));
                    spawnDat.setLocY(rs.getInt("locy"));
                    spawnDat.setRandomx(rs.getInt("randomx"));
                    spawnDat.setRandomy(rs.getInt("randomy"));
                    spawnDat.setLocX1(rs.getInt("locx1"));
                    spawnDat.setLocY1(rs.getInt("locy1"));
                    spawnDat.setLocX2(rs.getInt("locx2"));
                    spawnDat.setLocY2(rs.getInt("locy2"));
                    spawnDat.setHeading(rs.getInt("heading"));
                    spawnDat.setMinRespawnDelay(rs.getInt("min_respawn_delay"));
                    spawnDat.setMaxRespawnDelay(rs.getInt("max_respawn_delay"));
                    spawnDat.setMapId(rs.getShort("mapid"));
                    spawnDat.setRespawnScreen(rs.getBoolean("respawn_screen"));
                    spawnDat.setMovementDistance(rs.getInt("movement_distance"));
                    spawnDat.setRest(rs.getBoolean("rest"));
                    spawnDat.setSpawnType(rs.getInt("near_spawn"));
                    spawnDat.setTime(SpawnTimeTable.getInstance().get(
                            spawnDat.getId()));

                    spawnDat.setName(template1.get_name());

                    if ((count > 1) && (spawnDat.getLocX1() == 0)) {
                        // 複数かつ固定spawnの場合は、個体数 * 6 の範囲spawnに変える。
                        // ただし範囲が30を超えないようにする
                        final int range = Math.min(count * 6, 30);
                        spawnDat.setLocX1(spawnDat.getLocX() - range);
                        spawnDat.setLocY1(spawnDat.getLocY() - range);
                        spawnDat.setLocX2(spawnDat.getLocX() + range);
                        spawnDat.setLocY2(spawnDat.getLocY() + range);
                    }

                    // start the spawning
                    spawnDat.init();
                    spawnCount += spawnDat.getAmount();
                }

                this._spawntable.put(new Integer(spawnDat.getId()), spawnDat);
                if (spawnDat.getId() > this._highestId) {
                    this._highestId = spawnDat.getId();
                }
            }

        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
        _log.fine("普通怪物一共  " + spawnCount + " 只");
    }

    public L1Spawn getTemplate(final int Id) {
        return this._spawntable.get(new Integer(Id));
    }
}
