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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.templates.L1MobGroup;
import com.lineage.server.templates.L1NpcCount;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Lists;
import com.lineage.server.utils.collections.Maps;

/**
 * MOB集团(队伍)资料表
 */
public class MobGroupTable {

    private static Logger _log = Logger
            .getLogger(MobGroupTable.class.getName());

    private static MobGroupTable _instance;

    public static MobGroupTable getInstance() {
        if (_instance == null) {
            _instance = new MobGroupTable();
        }
        return _instance;
    }

    private final Map<Integer, L1MobGroup> _mobGroupIndex = Maps.newMap();

    private MobGroupTable() {
        this.loadMobGroup();
    }

    public L1MobGroup getTemplate(final int mobGroupId) {
        return this._mobGroupIndex.get(mobGroupId);
    }

    private void loadMobGroup() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM mobgroup");
            rs = pstm.executeQuery();
            while (rs.next()) {
                final int mobGroupId = rs.getInt("id");
                final boolean isRemoveGroup = (rs
                        .getBoolean("remove_group_if_leader_die"));
                final int leaderId = rs.getInt("leader_id");
                final List<L1NpcCount> minions = Lists.newList();
                for (int i = 1; i <= 7; i++) {
                    final int id = rs.getInt("minion" + i + "_id");
                    final int count = rs.getInt("minion" + i + "_count");
                    minions.add(new L1NpcCount(id, count));
                }
                final L1MobGroup mobGroup = new L1MobGroup(mobGroupId,
                        leaderId, minions, isRemoveGroup);
                this._mobGroupIndex.put(mobGroupId, mobGroup);
            }
            _log.config("MOB集团名单 " + this._mobGroupIndex.size() + "件");
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, "创建mobgroup表时出现错误", e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

}
