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
import com.lineage.server.model.L1WeaponSkill;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 魔法武器资料表
 */
public class WeaponSkillTable {

    private static Logger _log = Logger.getLogger(WeaponSkillTable.class
            .getName());

    private static WeaponSkillTable _instance;

    public static WeaponSkillTable getInstance() {
        if (_instance == null) {
            _instance = new WeaponSkillTable();
        }
        return _instance;
    }

    private final Map<Integer, L1WeaponSkill> _weaponIdIndex = Maps.newMap();

    private WeaponSkillTable() {
        this.loadWeaponSkill();
    }

    private void fillWeaponSkillTable(final ResultSet rs) throws SQLException {
        while (rs.next()) {
            final int weaponId = rs.getInt("weapon_id");
            final int probability = rs.getInt("probability");
            final int fixDamage = rs.getInt("fix_damage");
            final int randomDamage = rs.getInt("random_damage");
            final int area = rs.getInt("area");
            final int skillId = rs.getInt("skill_id");
            final int skillTime = rs.getInt("skill_time");
            final int effectId = rs.getInt("effect_id");
            final int effectTarget = rs.getInt("effect_target");
            final boolean isArrowType = rs.getBoolean("arrow_type");
            final int attr = rs.getInt("attr");
            final L1WeaponSkill weaponSkill = new L1WeaponSkill(weaponId,
                    probability, fixDamage, randomDamage, area, skillId,
                    skillTime, effectId, effectTarget, isArrowType, attr);
            this._weaponIdIndex.put(weaponId, weaponSkill);
        }
        _log.config("魔法武器列表 " + this._weaponIdIndex.size() + "件");
    }

    public L1WeaponSkill getTemplate(final int weaponId) {
        return this._weaponIdIndex.get(weaponId);
    }

    private void loadWeaponSkill() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM weapon_skill");
            rs = pstm.executeQuery();
            this.fillWeaponSkillTable(rs);
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, "创建weapon_skill表时出现错误", e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

}
