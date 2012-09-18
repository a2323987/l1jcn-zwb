package com.lineage.server.templates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.model.L1Location;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Lists;

public class L1DoorSpawn {
    private static Logger _log = Logger.getLogger(L1DoorSpawn.class.getName());

    public static List<L1DoorSpawn> all() {
        final List<L1DoorSpawn> result = Lists.newArrayList();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM spawnlist_door");
            rs = pstm.executeQuery();
            while (rs.next()) {
                final int id = rs.getInt("id");
                final int gfxId = rs.getInt("gfxid");
                final int x = rs.getInt("locx");
                final int y = rs.getInt("locy");
                final int mapId = rs.getInt("mapid");
                final int hp = rs.getInt("hp");
                final int keeper = rs.getInt("keeper");
                final boolean isOpening = rs.getBoolean("isOpening");
                final L1DoorGfx gfx = L1DoorGfx.findByGfxId(gfxId);
                final L1DoorSpawn spawn = new L1DoorSpawn(id, gfx, x, y, mapId,
                        hp, keeper, isOpening);
                result.add(spawn);
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

    private final int _id;
    private final L1DoorGfx _gfx;
    private final int _x;
    private final int _y;
    private final int _mapId;
    private final L1Location _loc;
    private final int _hp;
    private final int _keeper;

    private final boolean _isOpening;

    public L1DoorSpawn(final int id, final L1DoorGfx gfx, final int x,
            final int y, final int mapId, final int hp, final int keeper,
            final boolean isOpening) {
        super();
        this._id = id;
        this._gfx = gfx;
        this._x = x;
        this._y = y;
        this._mapId = mapId;
        this._loc = new L1Location(this._x, this._y, this._mapId);
        this._hp = hp;
        this._keeper = keeper;
        this._isOpening = isOpening;
    }

    public L1DoorGfx getGfx() {
        return this._gfx;
    }

    public int getHp() {
        return this._hp;
    }

    public int getId() {
        return this._id;
    }

    public int getKeeper() {
        return this._keeper;
    }

    public L1Location getLocation() {
        return this._loc;
    }

    public int getMapId() {
        return this._mapId;
    }

    public int getX() {
        return this._x;
    }

    public int getY() {
        return this._y;
    }

    public boolean isOpening() {
        return this._isOpening;
    }
}
