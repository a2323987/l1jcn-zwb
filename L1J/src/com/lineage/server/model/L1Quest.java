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
package com.lineage.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 任务
 */
public class L1Quest {

    private static Logger _log = Logger.getLogger(L1Quest.class.getName());

    /** 15级任务 */
    public static final int QUEST_LEVEL15 = 1;
    /** 30级任务 */
    public static final int QUEST_LEVEL30 = 2;
    /** 45级任务 */
    public static final int QUEST_LEVEL45 = 3;
    /** 50级任务 */
    public static final int QUEST_LEVEL50 = 4;
    /**  */
    public static final int QUEST_LYRA = 10;
    /**  */
    public static final int QUEST_OILSKINMANT = 11;
    /**  */
    public static final int QUEST_DOROMOND = 20;
    /**  */
    public static final int QUEST_RUBA = 21;
    /**  */
    public static final int QUEST_AREX = 22;
    /**  */
    public static final int QUEST_LUKEIN1 = 23;
    /**  */
    public static final int QUEST_TBOX1 = 24;
    /**  */
    public static final int QUEST_TBOX2 = 25;
    /**  */
    public static final int QUEST_TBOX3 = 26;
    /**  */
    public static final int QUEST_SIMIZZ = 27;
    /**  */
    public static final int QUEST_DOIL = 28;
    /**  */
    public static final int QUEST_RUDIAN = 29;
    /**  */
    public static final int QUEST_RESTA = 30;
    /**  */
    public static final int QUEST_CADMUS = 31;
    /**  */
    public static final int QUEST_KAMYLA = 32;
    /**  */
    public static final int QUEST_CRYSTAL = 33;
    /**  */
    public static final int QUEST_LIZARD = 34;
    /**  */
    public static final int QUEST_KEPLISHA = 35;
    /**  */
    public static final int QUEST_DESIRE = 36;
    /**  */
    public static final int QUEST_SHADOWS = 37;
    /**  */
    public static final int QUEST_ROI = 38;
    /**  */
    public static final int QUEST_TOSCROLL = 39;
    /**  */
    public static final int QUEST_MOONOFLONGBOW = 40;
    /**  */
    public static final int QUEST_GENERALHAMELOFRESENTMENT = 41;
    /**  */
    public static final int QUEST_TUTOR = 300; // 新手导师
    /**  */
    public static final int QUEST_TUTOR2 = 304; // 修炼场管理员
    /**  */
    public static final int QUEST_END = 255; // 终止任务
    /**  */
    private L1PcInstance _owner = null;
    /**  */
    private Map<Integer, Integer> _quest = null;

    public L1Quest(final L1PcInstance owner) {
        this._owner = owner;
    }

    /** 增加步骤 */
    public void add_step(final int quest_id, final int add) {
        int step = this.get_step(quest_id);
        step += add;
        this.set_step(quest_id, step);
    }

    /** 获得所有者 */
    public L1PcInstance get_owner() {
        return this._owner;
    }

    /** 获得步骤 */
    public int get_step(final int quest_id) {

        if (this._quest == null) {

            Connection con = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;
            try {
                this._quest = Maps.newMap();

                con = L1DatabaseFactory.getInstance().getConnection();
                pstm = con
                        .prepareStatement("SELECT * FROM character_quests WHERE char_id=?");
                pstm.setInt(1, this._owner.getId());
                rs = pstm.executeQuery();

                while (rs.next()) {
                    this._quest.put(new Integer(rs.getInt(2)),
                            new Integer(rs.getInt(3)));
                }

            } catch (final SQLException e) {
                _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            } finally {
                SQLUtil.close(rs);
                SQLUtil.close(pstm);
                SQLUtil.close(con);
            }
        }
        final Integer step = this._quest.get(new Integer(quest_id));
        if (step == null) {
            return 0;
        }
        return step.intValue();
    }

    /** 是结束 */
    public boolean isEnd(final int quest_id) {
        if (this.get_step(quest_id) == QUEST_END) {
            return true;
        }
        return false;
    }

    /** 设置结束 */
    public void set_end(final int quest_id) {
        this.set_step(quest_id, QUEST_END);
    }

    /** 设置步骤 */
    public void set_step(final int quest_id, final int step) {

        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();

            if (this._quest.get(new Integer(quest_id)) == null) {

                pstm = con.prepareStatement("INSERT INTO character_quests "
                        + "SET char_id = ?, quest_id = ?, quest_step = ?");
                pstm.setInt(1, this._owner.getId());
                pstm.setInt(2, quest_id);
                pstm.setInt(3, step);
                pstm.execute();
            } else {
                pstm = con
                        .prepareStatement("UPDATE character_quests "
                                + "SET quest_step = ? WHERE char_id = ? AND quest_id = ?");
                pstm.setInt(1, step);
                pstm.setInt(2, this._owner.getId());
                pstm.setInt(3, quest_id);
                pstm.execute();
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);

        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);

        }
        this._quest.put(new Integer(quest_id), new Integer(step));
    }

}
