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
package com.lineage.server.model;

import static com.lineage.server.model.skill.L1SkillId.ABSOLUTE_BARRIER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.utils.Random;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

// Referenced classes of package com.lineage.server.model:
// L1Teleport, L1PcInstance

/**
 * 随机传送点
 */
public class DungeonRandom {

    /** 新的随机传送点 */
    private static class NewDungeonRandom {
        int[] _newX = new int[5];

        int[] _newY = new int[5];

        short[] _newMapId = new short[5];

        int _heading;

        NewDungeonRandom(final int[] newX, final int[] newY,
                final short[] newMapId, final int heading) {
            for (int i = 0; i < 5; i++) {
                this._newX[i] = newX[i];
                this._newY[i] = newY[i];
                this._newMapId[i] = newMapId[i];
            }
            this._heading = heading;
        }
    }

    private static Logger _log = Logger
            .getLogger(DungeonRandom.class.getName());

    private static DungeonRandom _instance = null;

    private static Map<String, NewDungeonRandom> _dungeonMap = Maps.newMap();

    public static DungeonRandom getInstance() {
        if (_instance == null) {
            _instance = new DungeonRandom();
        }
        return _instance;
    }

    private DungeonRandom() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = L1DatabaseFactory.getInstance().getConnection();

            pstm = con.prepareStatement("SELECT * FROM dungeon_random");
            rs = pstm.executeQuery();
            while (rs.next()) {
                final int srcMapId = rs.getInt("src_mapid");
                final int srcX = rs.getInt("src_x");
                final int srcY = rs.getInt("src_y");
                final String key = new StringBuilder().append(srcMapId)
                        .append(srcX).append(srcY).toString();
                final int[] newX = new int[5];
                final int[] newY = new int[5];
                final short[] newMapId = new short[5];
                newX[0] = rs.getInt("new_x1");
                newY[0] = rs.getInt("new_y1");
                newMapId[0] = rs.getShort("new_mapid1");
                newX[1] = rs.getInt("new_x2");
                newY[1] = rs.getInt("new_y2");
                newMapId[1] = rs.getShort("new_mapid2");
                newX[2] = rs.getInt("new_x3");
                newY[2] = rs.getInt("new_y3");
                newMapId[2] = rs.getShort("new_mapid3");
                newX[3] = rs.getInt("new_x4");
                newY[3] = rs.getInt("new_y4");
                newMapId[3] = rs.getShort("new_mapid4");
                newX[4] = rs.getInt("new_x5");
                newY[4] = rs.getInt("new_y5");
                newMapId[4] = rs.getShort("new_mapid5");
                final int heading = rs.getInt("new_heading");
                final NewDungeonRandom newDungeonRandom = new NewDungeonRandom(
                        newX, newY, newMapId, heading);
                if (_dungeonMap.containsKey(key)) {
                    _log.log(Level.WARNING, "具有相同的dungeon关键数据。key=" + key);
                }
                _dungeonMap.put(key, newDungeonRandom);
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    public boolean dg(final int locX, final int locY, final int mapId,
            final L1PcInstance pc) {
        final String key = new StringBuilder().append(mapId).append(locX)
                .append(locY).toString();
        if (_dungeonMap.containsKey(key)) {
            final int rnd = Random.nextInt(5);
            final NewDungeonRandom newDungeonRandom = _dungeonMap.get(key);
            final short newMap = newDungeonRandom._newMapId[rnd];
            final int newX = newDungeonRandom._newX[rnd];
            final int newY = newDungeonRandom._newY[rnd];
            final int heading = newDungeonRandom._heading;

            // 2秒无敌状态。
            pc.setSkillEffect(ABSOLUTE_BARRIER, 2000);
            pc.stopHpRegeneration();
            pc.stopMpRegeneration();
            pc.stopHpRegenerationByDoll();
            pc.stopMpRegenerationByDoll();
            L1Teleport.teleport(pc, newX, newY, newMap, heading, true);
            return true;
        }
        return false;
    }
}
