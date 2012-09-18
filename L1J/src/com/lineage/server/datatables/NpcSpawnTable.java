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
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 产生NPC资料表
 */
public class NpcSpawnTable {

    private static Logger _log = Logger
            .getLogger(NpcSpawnTable.class.getName());

    private static NpcSpawnTable _instance;

    public static NpcSpawnTable getInstance() {
        if (_instance == null) {
            _instance = new NpcSpawnTable();
        }
        return _instance;
    }

    private final Map<Integer, L1Spawn> _spawntable = Maps.newMap();

    private int _highestId;

    private NpcSpawnTable() {
        this.fillNpcSpawnTable();
    }

    public void addNewSpawn(final L1Spawn l1spawn) {
        this._highestId++;
        l1spawn.setId(this._highestId);
        this._spawntable.put(l1spawn.getId(), l1spawn);
    }

    private void fillNpcSpawnTable() {

        int spawnCount = 0;

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM spawnlist_npc");
            rs = pstm.executeQuery();
            while (rs.next()) {

                // GM商店NPC
                if (Config.ALT_GMSHOP == false) {
                    final int npcid = rs.getInt(1);

                    // GM商店NPC
                    if ((npcid >= Config.ALT_GMSHOP_MIN_ID)
                            && (npcid <= Config.ALT_GMSHOP_MAX_ID)) {
                        continue;
                    }
                }

                // 南瓜怪任务NPC
                if (Config.ALT_HALLOWEENIVENT == false) {
                    final int npcid = rs.getInt("id");
                    if (((npcid >= 130852) && (npcid <= 130862))
                            || ((npcid >= 26656) && (npcid <= 26734))
                            || ((npcid >= 89634) && (npcid <= 89644))) {
                        continue;
                    }
                }

                // 日本特典道具NPC
                if (Config.ALT_JPPRIVILEGED == false) {
                    final int npcid = rs.getInt("id");
                    if ((npcid >= 1310368) && (npcid <= 1310379)) {
                        continue;
                    }
                }

                // 说话卷轴任务NPC
                if (Config.ALT_TALKINGSCROLLQUEST == false) {
                    final int npcid = rs.getInt("id");
                    if (((npcid >= 87537) && (npcid <= 87551))
                            || ((npcid >= 1310387) && (npcid <= 1310389))) {
                        continue;
                    }
                }

                // 说话卷轴任务NPC
                if (Config.ALT_TALKINGSCROLLQUEST == true) {
                    final int npcid = rs.getInt("id");
                    if ((npcid >= 90066) && (npcid <= 90069)) {
                        continue;
                    }
                }
                final int npcTemplateid = rs.getInt("npc_templateid");
                final L1Npc l1npc = NpcTable.getInstance().getTemplate(
                        npcTemplateid);
                L1Spawn l1spawn;
                if (l1npc == null) {
                    _log.warning("mob data for id:" + npcTemplateid
                            + " missing in npc table");
                    l1spawn = null;
                } else {
                    if (rs.getInt("count") == 0) {
                        continue;
                    }
                    l1spawn = new L1Spawn(l1npc);
                    l1spawn.setId(rs.getInt("id"));
                    l1spawn.setAmount(rs.getInt("count"));
                    l1spawn.setLocX(rs.getInt("locx"));
                    l1spawn.setLocY(rs.getInt("locy"));
                    l1spawn.setRandomx(rs.getInt("randomx"));
                    l1spawn.setRandomy(rs.getInt("randomy"));
                    l1spawn.setLocX1(0);
                    l1spawn.setLocY1(0);
                    l1spawn.setLocX2(0);
                    l1spawn.setLocY2(0);
                    l1spawn.setHeading(rs.getInt("heading"));
                    l1spawn.setMinRespawnDelay(rs.getInt("respawn_delay"));
                    l1spawn.setMapId(rs.getShort("mapid"));
                    l1spawn.setMovementDistance(rs.getInt("movement_distance"));
                    l1spawn.setName(l1npc.get_name());
                    l1spawn.init();
                    spawnCount += l1spawn.getAmount();

                    this._spawntable.put(new Integer(l1spawn.getId()), l1spawn);
                    if (l1spawn.getId() > this._highestId) {
                        this._highestId = l1spawn.getId();
                    }
                }
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }

        _log.config("NPC配置清单 " + this._spawntable.size() + "件");
        _log.fine("NPC总数 " + spawnCount + "只");
    }

    public L1Spawn getTemplate(final int i) {
        return this._spawntable.get(i);
    }

    /**
     * 手动增加Spwan物件
     * 
     * @param pc
     * @param npc
     */
    public void storeSpawn(final L1PcInstance pc, final L1Npc npc) {
        Connection con = null;
        PreparedStatement pstm = null;

        try {
            final int count = 1;
            final String note = npc.get_name();

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("INSERT INTO spawnlist_npc SET location=?,count=?,npc_templateid=?,locx=?,locy=?,heading=?,mapid=?");
            pstm.setString(1, note);
            pstm.setInt(2, count);
            pstm.setInt(3, npc.get_npcId());
            pstm.setInt(4, pc.getX());
            pstm.setInt(5, pc.getY());
            pstm.setInt(6, pc.getHeading());
            pstm.setInt(7, pc.getMapId());
            pstm.execute();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);

        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

}
