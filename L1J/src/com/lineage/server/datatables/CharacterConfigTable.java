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
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.utils.SQLUtil;

/**
 * 角色配置资料表
 */
public class CharacterConfigTable {

    private static Logger _log = Logger.getLogger(CharacterConfigTable.class
            .getName());

    private static CharacterConfigTable _instance;

    public static CharacterConfigTable getInstance() {
        if (_instance == null) {
            _instance = new CharacterConfigTable();
        }
        return _instance;
    }

    public CharacterConfigTable() {
    }

    /**
     * 角色配置数量
     * 
     * @param objectId
     */
    public int countCharacterConfig(final int objectId) {
        int result = 0;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT count(*) as cnt FROM character_config WHERE object_id=?");
            pstm.setInt(1, objectId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("cnt");
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
        return result;
    }

    /**
     * 删除角色配置
     * 
     * @param objectId
     */
    public void deleteCharacterConfig(final int objectId) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("DELETE FROM character_config WHERE object_id=?");
            pstm.setInt(1, objectId);
            pstm.execute();
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    /**
     * 储存角色配置
     * 
     * @param objectId
     * @param length
     * @param data
     */
    public void storeCharacterConfig(final int objectId, final int length,
            final byte[] data) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("INSERT INTO character_config SET object_id=?, length=?, data=?");
            pstm.setInt(1, objectId);
            pstm.setInt(2, length);
            pstm.setBytes(3, data);
            pstm.execute();
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    /**
     * 更新角色配置
     * 
     * @param objectId
     * @param length
     * @param data
     */
    public void updateCharacterConfig(final int objectId, final int length,
            final byte[] data) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("UPDATE character_config SET length=?, data=? WHERE object_id=?");
            pstm.setInt(1, length);
            pstm.setBytes(2, data);
            pstm.setInt(3, objectId);
            pstm.execute();
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

}
