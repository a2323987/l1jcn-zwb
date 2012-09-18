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
package com.lineage.server.templates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.IdFactory;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Bookmarks;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.SQLUtil;

/**
 * 记忆坐标
 */
public class L1BookMark {

    private static Logger _log = Logger.getLogger(L1BookMark.class.getName());

    /** 增加记忆坐标 */
    public static void addBookmark(final L1PcInstance pc, final String s) {
        // クライアント側でチェックされるため不要
        // if (s.length() > 12) {
        // pc.sendPackets(new S_ServerMessage(204));
        // return;
        // }

        // 不能记忆坐标的地图
        if (!pc.getMap().isMarkable()) {
            pc.sendPackets(new S_ServerMessage(214)); // \f1这个地点不能够标记。
            return;
        }

        // 输入的记忆坐标太长
        final int size = pc.getBookMarkSize();
        if (size > 49) {
            return;
        }

        if (pc.getBookMark(s) == null) {
            final L1BookMark bookmark = new L1BookMark();
            bookmark.setId(IdFactory.getInstance().nextId());
            bookmark.setCharId(pc.getId());
            bookmark.setName(s);
            bookmark.setLocX(pc.getX());
            bookmark.setLocY(pc.getY());
            bookmark.setMapId(pc.getMapId());

            Connection con = null;
            PreparedStatement pstm = null;

            try {
                con = L1DatabaseFactory.getInstance().getConnection();
                pstm = con
                        .prepareStatement("INSERT INTO character_teleport SET id = ?, char_id = ?, name = ?, locx = ?, locy = ?, mapid = ?");
                pstm.setInt(1, bookmark.getId());
                pstm.setInt(2, bookmark.getCharId());
                pstm.setString(3, bookmark.getName());
                pstm.setInt(4, bookmark.getLocX());
                pstm.setInt(5, bookmark.getLocY());
                pstm.setInt(6, bookmark.getMapId());
                pstm.execute();
            } catch (final SQLException e) {
                _log.log(Level.SEVERE, "增加记忆坐标时发生错误。", e);
            } finally {
                SQLUtil.close(pstm);
                SQLUtil.close(con);
            }

            pc.addBookMark(bookmark);
            pc.sendPackets(new S_Bookmarks(s, bookmark.getMapId(), bookmark
                    .getId()));
        } else {
            pc.sendPackets(new S_ServerMessage(327)); // 同样的名称已经存在。
        }
    }

    /** 删除记忆的坐标 */
    public static void deleteBookmark(final L1PcInstance player, final String s) {
        final L1BookMark book = player.getBookMark(s);
        if (book != null) {
            Connection con = null;
            PreparedStatement pstm = null;
            try {

                con = L1DatabaseFactory.getInstance().getConnection();
                pstm = con
                        .prepareStatement("DELETE FROM character_teleport WHERE id=?");
                pstm.setInt(1, book.getId());
                pstm.execute();
                player.removeBookMark(book);
            } catch (final SQLException e) {
                _log.log(Level.SEVERE, "删除记忆坐标时发生错误。", e);
            } finally {
                SQLUtil.close(pstm);
                SQLUtil.close(con);
            }
        }
    }

    private int _charId;

    private int _id;

    private String _name;

    private int _locX;

    private int _locY;

    private short _mapId;

    public L1BookMark() {
    }

    public int getCharId() {
        return this._charId;
    }

    public int getId() {
        return this._id;
    }

    public int getLocX() {
        return this._locX;
    }

    public int getLocY() {
        return this._locY;
    }

    public short getMapId() {
        return this._mapId;
    }

    public String getName() {
        return this._name;
    }

    public void setCharId(final int i) {
        this._charId = i;
    }

    public void setId(final int i) {
        this._id = i;
    }

    public void setLocX(final int i) {
        this._locX = i;
    }

    public void setLocY(final int i) {
        this._locY = i;
    }

    public void setMapId(final short i) {
        this._mapId = i;
    }

    public void setName(final String s) {
        this._name = s;
    }

}
