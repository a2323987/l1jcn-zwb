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
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.model.L1NpcTalkData;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * NPC说话动作资料表
 */
public class NPCTalkDataTable {

    private static Logger _log = Logger.getLogger(NPCTalkDataTable.class
            .getName());

    private static NPCTalkDataTable _instance;

    public static NPCTalkDataTable getInstance() {
        if (_instance == null) {
            _instance = new NPCTalkDataTable();
        }
        return _instance;
    }

    private final Map<Integer, L1NpcTalkData> _datatable = Maps.newMap();

    private NPCTalkDataTable() {
        this.parseList();
    }

    public L1NpcTalkData getTemplate(final int i) {
        return this._datatable.get(new Integer(i));
    }

    private void parseList() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM npcaction");

            rs = pstm.executeQuery();
            while (rs.next()) {
                final L1NpcTalkData l1npctalkdata = new L1NpcTalkData();
                l1npctalkdata.setNpcID(rs.getInt(1));
                l1npctalkdata.setNormalAction(rs.getString(2));
                l1npctalkdata.setCaoticAction(rs.getString(3));
                l1npctalkdata.setTeleportURL(rs.getString(4));
                l1npctalkdata.setTeleportURLA(rs.getString(5));
                l1npctalkdata.setNoSell(rs.getString(6));
                l1npctalkdata.setNpcName(rs.getString(7));
                this._datatable.put(new Integer(l1npctalkdata.getNpcID()),
                        l1npctalkdata);
            }
            _log.config("NPC说话动作列表 " + this._datatable.size() + "件");
        } catch (final SQLException e) {
            _log.warning("创建NPC说话表时出现错误 " + e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

}
