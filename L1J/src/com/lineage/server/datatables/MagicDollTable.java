package com.lineage.server.datatables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.templates.L1MagicDoll;
import com.lineage.server.utils.SQLUtil;

/**
 * 魔法娃娃资料表
 */
public class MagicDollTable {

    private static Logger _log = Logger.getLogger(MagicDollTable.class
            .getName());

    private static MagicDollTable _instance;

    public static MagicDollTable getInstance() {
        if (_instance == null) {
            _instance = new MagicDollTable();
        }
        return _instance;
    }

    private final HashMap<Integer, L1MagicDoll> _dolls = new HashMap<Integer, L1MagicDoll>();

    private MagicDollTable() {
        this.load();
    }

    public L1MagicDoll getTemplate(final int itemId) {
        if (this._dolls.containsKey(itemId)) {
            return this._dolls.get(itemId);
        }
        return null;
    }

    private void load() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM magic_doll");
            rs = pstm.executeQuery();
            while (rs.next()) {
                final L1MagicDoll doll = new L1MagicDoll();
                final int itemId = rs.getInt("item_id"); // 魔法娃娃道具编号 对应 etcitem
                doll.setItemId(itemId);
                doll.setDollId(rs.getInt("doll_id")); // 魔法娃娃编号 对应 npc
                doll.setAc(rs.getInt("ac")); // 增加物理防御
                doll.setHpr(rs.getInt("hpr")); // 增加体力回覆量
                doll.setHprTime(rs.getBoolean("hpr_time")); // 体力回覆判断
                                                            // 0:增加自身体力回覆量、1:固定时间回覆一次
                                                            // (希尔黛斯)
                doll.setMpr(rs.getInt("mpr")); // 增加魔力回覆量
                doll.setMprTime(rs.getBoolean("mpr_time")); // 魔力回覆判断
                                                            // 0:增加自身体力回覆量
                                                            // (蛇女、幼龙)、1:固定时间回覆一次
                                                            // (小思克巴、长老)
                doll.setHit(rs.getInt("hit")); // 增加近距离物理命中率
                doll.setDmg(rs.getInt("dmg")); // 增加近距离物理攻击力
                doll.setDmgChance(rs.getInt("dmg_chance")); // 机率性增加物理攻击力
                doll.setBowHit(rs.getInt("bow_hit")); // 增加远距离物理命中率
                doll.setBowDmg(rs.getInt("bow_dmg")); // 增加远距离物理攻击力
                doll.setDmgReduction(rs.getInt("dmg_reduction")); // 增加伤害减免
                doll.setDmgReductionChance(rs.getInt("dmg_reduction_chance")); // 机率性伤害减免
                doll.setDmgEvasionChance(rs.getInt("dmg_evasion_chance")); // 机率性回避伤害
                doll.setWeightReduction(rs.getInt("weight_reduction")); // 增加负重
                doll.setRegistStun(rs.getInt("regist_stun")); // 增加昏迷耐性
                doll.setRegistStone(rs.getInt("regist_stone")); // 增加石化耐性
                doll.setRegistSleep(rs.getInt("regist_sleep")); // 增加睡眠耐性
                doll.setRegistFreeze(rs.getInt("regist_freeze")); // 增加寒冰耐性
                doll.setRegistSustain(rs.getInt("regist_sustain")); // 增加支撑耐性
                doll.setRegistBlind(rs.getInt("regist_blind")); // 增加闇黑耐性
                doll.setMakeItemId(rs.getInt("make_itemid")); // 获得道具
                doll.setEffect(rs.getByte("effect")); // 效果 1:中毒
                doll.setEffectChance(rs.getInt("effect_chance")); // 中毒几率
                doll.setExp(rs.getDouble("exp")); // 经验值加成

                this._dolls.put(new Integer(itemId), doll);
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);

        }
    }

}
