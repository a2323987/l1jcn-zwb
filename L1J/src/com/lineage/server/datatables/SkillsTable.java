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
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.templates.L1Skills;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 技能资料表
 */
public class SkillsTable {

    private static Logger _log = Logger.getLogger(SkillsTable.class.getName());

    private static SkillsTable _instance;

    public static SkillsTable getInstance() {
        if (_instance == null) {
            _instance = new SkillsTable();
        }
        return _instance;
    }

    private final Map<Integer, L1Skills> _skills = Maps.newMap();

    private final boolean _initialized;

    private SkillsTable() {
        this._initialized = true;
        this.RestoreSkills();
    }

    private void FillSkillsTable(final ResultSet rs) throws SQLException {

        while (rs.next()) {
            final L1Skills l1skills = new L1Skills();
            final int skill_id = rs.getInt("skill_id");
            l1skills.setSkillId(skill_id);
            l1skills.setName(rs.getString("name"));
            l1skills.setSkillLevel(rs.getInt("skill_level"));
            l1skills.setSkillNumber(rs.getInt("skill_number"));
            l1skills.setMpConsume(rs.getInt("mpConsume"));
            l1skills.setHpConsume(rs.getInt("hpConsume"));
            l1skills.setItemConsumeId(rs.getInt("itemConsumeId"));
            l1skills.setItemConsumeCount(rs.getInt("itemConsumeCount"));
            l1skills.setReuseDelay(rs.getInt("reuseDelay"));
            l1skills.setBuffDuration(rs.getInt("buffDuration"));
            l1skills.setTarget(rs.getString("target"));
            l1skills.setTargetTo(rs.getInt("target_to"));
            l1skills.setDamageValue(rs.getInt("damage_value"));
            l1skills.setDamageDice(rs.getInt("damage_dice"));
            l1skills.setDamageDiceCount(rs.getInt("damage_dice_count"));
            l1skills.setProbabilityValue(rs.getInt("probability_value"));
            l1skills.setProbabilityDice(rs.getInt("probability_dice"));
            l1skills.setAttr(rs.getInt("attr"));
            l1skills.setType(rs.getInt("type"));
            l1skills.setLawful(rs.getInt("lawful"));
            l1skills.setRanged(rs.getInt("ranged"));
            l1skills.setArea(rs.getInt("area"));
            l1skills.setThrough(rs.getBoolean("through"));
            l1skills.setId(rs.getInt("id"));
            l1skills.setNameId(rs.getString("nameid"));
            l1skills.setActionId(rs.getInt("action_id"));
            l1skills.setCastGfx(rs.getInt("castgfx"));
            l1skills.setCastGfx2(rs.getInt("castgfx2"));
            l1skills.setSysmsgIdHappen(rs.getInt("sysmsgID_happen"));
            l1skills.setSysmsgIdStop(rs.getInt("sysmsgID_stop"));
            l1skills.setSysmsgIdFail(rs.getInt("sysmsgID_fail"));
            l1skills.setIsInvisUsableSkill(rs.getBoolean("isInvisUsableSkill"));

            this._skills.put(new Integer(skill_id), l1skills);
        }
        _log.config("技能 " + this._skills.size() + "件");
    }

    public L1Skills getTemplate(final int i) {
        return this._skills.get(new Integer(i));
    }

    public boolean isInitialized() {
        return this._initialized;
    }

    private void RestoreSkills() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM skills");
            rs = pstm.executeQuery();
            this.FillSkillsTable(rs);

        } catch (final SQLException e) {
            _log.log(Level.SEVERE, "创建skills表时出现错误", e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    /**
     * 检查技能
     * 
     * @param playerobjid
     * @param skillid
     */
    public boolean spellCheck(final int playerobjid, final int skillid) {
        boolean ret = false;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT * FROM character_skills WHERE char_obj_id=? AND skill_id=?");
            pstm.setInt(1, playerobjid);
            pstm.setInt(2, skillid);
            rs = pstm.executeQuery();
            if (rs.next()) {
                ret = true;
            } else {
                ret = false;
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);

        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
        return ret;
    }

    /**
     * 技能丢失
     * 
     * @param playerobjid
     * @param skillid
     */
    public void spellLost(final int playerobjid, final int skillid) {
        final L1PcInstance pc = (L1PcInstance) L1World.getInstance()
                .findObject(playerobjid);
        if (pc != null) {
            pc.removeSkillMastery(skillid);
        }

        Connection con = null;
        PreparedStatement pstm = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("DELETE FROM character_skills WHERE char_obj_id=? AND skill_id=?");
            pstm.setInt(1, playerobjid);
            pstm.setInt(2, skillid);
            pstm.execute();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);

        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    /**
     * 学会的技能
     * 
     * @param playerobjid
     * @param skillid
     * @param skillname
     * @param active
     * @param time
     */
    public void spellMastery(final int playerobjid, final int skillid,
            final String skillname, final int active, final int time) {
        if (this.spellCheck(playerobjid, skillid)) {
            return;
        }
        final L1PcInstance pc = (L1PcInstance) L1World.getInstance()
                .findObject(playerobjid);
        if (pc != null) {
            pc.setSkillMastery(skillid);
        }

        Connection con = null;
        PreparedStatement pstm = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("INSERT INTO character_skills SET char_obj_id=?, skill_id=?, skill_name=?, is_active=?, activetimeleft=?");
            pstm.setInt(1, playerobjid);
            pstm.setInt(2, skillid);
            pstm.setString(3, skillname);
            pstm.setInt(4, active);
            pstm.setInt(5, time);
            pstm.execute();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);

        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

}
