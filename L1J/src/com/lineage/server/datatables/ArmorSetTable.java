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
 * MAY BE CONSIDERED TO BE A CONTRACT,
 * THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package com.lineage.server.datatables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.templates.L1ArmorSets;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Lists;

/**
 * Class <code>ArmorSetTable</code> 数据库资料表:套装.
 * 
 * @author jrwz
 * @version 2012-4-3上午10:00:35
 * @see com.lineage.server.datatables
 * @since JDK1.7
 */
public final class ArmorSetTable {

    /** 提示信息. */
    private static final Logger LOG = Logger.getLogger(ArmorSetTable.class
            .getName());
    /** 实例. */
    private static ArmorSetTable instance;

    /**
     * 实例对象为空时才新建一个.
     * 
     * @return 实例
     */
    public static ArmorSetTable getInstance() {
        if (instance == null) {
            instance = new ArmorSetTable();
        }
        return instance;
    }

    /** 套装列表. */
    private final List<L1ArmorSets> armorSetList = Lists.newList();

    /** 数据库资料表:套装. */
    private ArmorSetTable() {
        this.load();
    }

    /**
     * 写入资料表.
     * 
     * @param rs
     *            数据库资料
     * @throws SQLException
     *             数据库访问错误
     */
    private void fillTable(final ResultSet rs) throws SQLException {
        while (rs.next()) {
            final L1ArmorSets as = new L1ArmorSets();
            as.setId(rs.getInt("id")); // 流水编号 (不能重复)
            as.setSets(rs.getString("sets")); // 组件 (几件装备组成一套)
            as.setPolyId(rs.getInt("polyid")); // 变身编号
            as.setAc(rs.getInt("ac")); // 增加物理防御
            as.setHp(rs.getInt("hp")); // 增加血量
            as.setMp(rs.getInt("mp")); // 增加魔量
            as.setHpr(rs.getInt("hpr")); // 增加回血量
            as.setMpr(rs.getInt("mpr")); // 增加回魔量
            as.setMr(rs.getInt("mr")); // 增加魔法防御
            as.setStr(rs.getInt("str")); // 增加力量
            as.setDex(rs.getInt("dex")); // 增加敏捷
            as.setCon(rs.getInt("con")); // 增加体质
            as.setWis(rs.getInt("wis")); // 增加精神
            as.setCha(rs.getInt("cha")); // 增加魅力
            as.setIntl(rs.getInt("intl")); // 增加智力
            as.setHitModifier(rs.getInt("hit_modifier")); // 增加近距离命中率
            as.setDmgModifier(rs.getInt("dmg_modifier")); // 增加近距离伤害值
            as.setBowHitModifier(rs.getInt("bow_hit_modifier")); // 增加远距离命中率
            as.setBowDmgModifier(rs.getInt("bow_dmg_modifier")); // 增加远距离伤害值
            as.setSp(rs.getInt("sp")); // 增加魔法攻击力
            as.setDefenseWater(rs.getInt("defense_water")); // 增加水属性防御
            as.setDefenseWind(rs.getInt("defense_wind")); // 增加风属性防御
            as.setDefenseFire(rs.getInt("defense_fire")); // 增加火属性防御
            as.setDefenseEarth(rs.getInt("defense_earth")); // 增加地属性防御
            as.setGfxEffect(rs.getInt("gfx_effect")); // 动画效果
            as.setGfxEffectTime(rs.getInt("gfx_effect_time")); // 动画效果间隔时间(秒)
            as.setObtainProps(rs.getInt("obtainProps")); // 获得特定道具
            as.setObtainPropsTime(rs.getInt("obtainPropsTime")); // 获得道具的间隔时间(秒)

            this.armorSetList.add(as);
        }
    }

    /**
     * 取得所有套装列表.
     * 
     * @return 所有套装的数量
     */
    public L1ArmorSets[] getAllList() {
        return this.armorSetList.toArray(new L1ArmorSets[this.armorSetList
                .size()]);
    }

    /**
     * 加载.
     */
    private void load() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM armor_set");
            rs = pstm.executeQuery();
            this.fillTable(rs);
        } catch (final SQLException e) {
            LOG.log(Level.SEVERE, "加载数据库资料表:armor_set时出现错误", e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }
}
