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
import com.lineage.server.model.L1PolyMorph;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 变身名单资料表
 */
public class PolyTable {

    private static Logger _log = Logger.getLogger(PolyTable.class.getName());

    private static PolyTable _instance;

    public static PolyTable getInstance() {
        if (_instance == null) {
            _instance = new PolyTable();
        }
        return _instance;
    }

    private final Map<String, L1PolyMorph> _polymorphs = Maps.newMap();

    private final Map<Integer, L1PolyMorph> _polyIdIndex = Maps.newMap();

    private PolyTable() {
        this.loadPolymorphs();
    }

    private void fillPolyTable(final ResultSet rs) throws SQLException {
        while (rs.next()) {
            final int id = rs.getInt("id");
            final String name = rs.getString("name");
            final int polyId = rs.getInt("polyid");
            final int minLevel = rs.getInt("minlevel");
            final int weaponEquipFlg = rs.getInt("weaponequip");
            final int armorEquipFlg = rs.getInt("armorequip");
            final boolean canUseSkill = rs.getBoolean("isSkillUse");
            final int causeFlg = rs.getInt("cause");

            final L1PolyMorph poly = new L1PolyMorph(id, name, polyId,
                    minLevel, weaponEquipFlg, armorEquipFlg, canUseSkill,
                    causeFlg);

            this._polymorphs.put(name, poly);
            this._polyIdIndex.put(polyId, poly);
        }

        _log.config("变身名单 " + this._polymorphs.size() + "件");
    }

    public L1PolyMorph getTemplate(final int polyId) {
        return this._polyIdIndex.get(polyId);
    }

    public L1PolyMorph getTemplate(final String name) {
        return this._polymorphs.get(name);
    }

    private void loadPolymorphs() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM polymorphs");
            rs = pstm.executeQuery();
            this.fillPolyTable(rs);
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, "创建polymorph表时出现错误", e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

}
