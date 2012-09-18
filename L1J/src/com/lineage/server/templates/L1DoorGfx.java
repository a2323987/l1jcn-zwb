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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.utils.SQLUtil;

public class L1DoorGfx {
    private static Logger _log = Logger.getLogger(L1DoorGfx.class.getName());

    /**
     * door_gfxsテーブルから指定されたgfxidを主キーとする行を返します。<br>
     * このメソッドは常に最新のデータをテーブルから返します。
     * 
     * @param gfxId
     * @return
     */
    public static L1DoorGfx findByGfxId(final int gfxId) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT * FROM door_gfxs WHERE gfxid = ?");
            pstm.setInt(1, gfxId);
            rs = pstm.executeQuery();
            if (!rs.next()) {
                return null;
            }
            final int id = rs.getInt("gfxid");
            final int dir = rs.getInt("direction");
            final int rEdge = rs.getInt("right_edge_offset");
            final int lEdge = rs.getInt("left_edge_offset");
            return new L1DoorGfx(id, dir, rEdge, lEdge);

        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
        return null;
    }

    private final int _gfxId;
    private final int _direction;
    private final int _rightEdgeOffset;

    private final int _leftEdgeOffset;

    private L1DoorGfx(final int gfxId, final int direction,
            final int rightEdgeOffset, final int leftEdgeOffset) {
        this._gfxId = gfxId;
        this._direction = direction;
        this._rightEdgeOffset = rightEdgeOffset;
        this._leftEdgeOffset = leftEdgeOffset;
    }

    public int getDirection() {
        return this._direction;
    }

    public int getGfxId() {
        return this._gfxId;
    }

    public int getLeftEdgeOffset() {
        return this._leftEdgeOffset;
    }

    public int getRightEdgeOffset() {
        return this._rightEdgeOffset;
    }
}
