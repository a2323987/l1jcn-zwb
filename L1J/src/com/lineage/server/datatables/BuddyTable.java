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
import com.lineage.server.model.L1Buddy;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 好友资料表
 */
public class BuddyTable {

    private static Logger _log = Logger.getLogger(BuddyTable.class.getName());

    private static BuddyTable _instance;

    public static BuddyTable getInstance() {
        if (_instance == null) {
            _instance = new BuddyTable();
        }
        return _instance;
    }

    private final Map<Integer, L1Buddy> _buddys = Maps.newMap();

    private BuddyTable() {

        Connection con = null;
        PreparedStatement charIdPS = null;
        ResultSet charIdRS = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            charIdPS = con
                    .prepareStatement("SELECT distinct(char_id) as char_id FROM character_buddys");

            charIdRS = charIdPS.executeQuery();
            while (charIdRS.next()) {
                PreparedStatement buddysPS = null;
                ResultSet buddysRS = null;

                try {
                    buddysPS = con
                            .prepareStatement("SELECT buddy_id, buddy_name FROM character_buddys WHERE char_id = ?");
                    final int charId = charIdRS.getInt("char_id");
                    buddysPS.setInt(1, charId);
                    final L1Buddy buddy = new L1Buddy(charId);

                    buddysRS = buddysPS.executeQuery();
                    while (buddysRS.next()) {
                        buddy.add(buddysRS.getInt("buddy_id"),
                                buddysRS.getString("buddy_name"));
                    }

                    this._buddys.put(buddy.getCharId(), buddy);
                } catch (final Exception e) {
                    _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                } finally {
                    SQLUtil.close(buddysRS);
                    SQLUtil.close(buddysPS);
                }
            }
            _log.config("加载 " + this._buddys.size() + " 角色的好友列表");
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(charIdRS);
            SQLUtil.close(charIdPS);
            SQLUtil.close(con);
        }
    }

    /**
     * 增加好友
     * 
     * @param charId
     * @param objId
     * @param name
     */
    public void addBuddy(final int charId, final int objId, final String name) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("INSERT INTO character_buddys SET char_id=?, buddy_id=?, buddy_name=?");
            pstm.setInt(1, charId);
            pstm.setInt(2, objId);
            pstm.setString(3, name);
            pstm.execute();
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    /**
     * 取得好友资料表
     * 
     * @param charId
     * @return 好友
     */
    public L1Buddy getBuddyTable(final int charId) {
        L1Buddy buddy = this._buddys.get(charId);
        if (buddy == null) {
            buddy = new L1Buddy(charId);
            this._buddys.put(charId, buddy);
        }
        return buddy;
    }

    /**
     * 删除好友
     * 
     * @param charId
     * @param buddyName
     */
    public void removeBuddy(final int charId, final String buddyName) {
        Connection con = null;
        PreparedStatement pstm = null;
        final L1Buddy buddy = this.getBuddyTable(charId);
        if (!buddy.containsName(buddyName)) {
            return;
        }

        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("DELETE FROM character_buddys WHERE char_id=? AND buddy_name=?");
            pstm.setInt(1, charId);
            pstm.setString(2, buddyName);
            pstm.execute();

            buddy.remove(buddyName);
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }
}
