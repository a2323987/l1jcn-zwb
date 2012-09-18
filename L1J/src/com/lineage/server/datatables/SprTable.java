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

import static com.lineage.server.ActionCodes.ACTION_Aggress;
import static com.lineage.server.ActionCodes.ACTION_AltAttack;
import static com.lineage.server.ActionCodes.ACTION_Attack;
import static com.lineage.server.ActionCodes.ACTION_AxeAttack;
import static com.lineage.server.ActionCodes.ACTION_AxeWalk;
import static com.lineage.server.ActionCodes.ACTION_BowAttack;
import static com.lineage.server.ActionCodes.ACTION_BowWalk;
import static com.lineage.server.ActionCodes.ACTION_ClawAttack;
import static com.lineage.server.ActionCodes.ACTION_ClawWalk;
import static com.lineage.server.ActionCodes.ACTION_DaggerAttack;
import static com.lineage.server.ActionCodes.ACTION_DaggerWalk;
import static com.lineage.server.ActionCodes.ACTION_EdoryuAttack;
import static com.lineage.server.ActionCodes.ACTION_EdoryuWalk;
import static com.lineage.server.ActionCodes.ACTION_SkillAttack;
import static com.lineage.server.ActionCodes.ACTION_SkillBuff;
import static com.lineage.server.ActionCodes.ACTION_SpearAttack;
import static com.lineage.server.ActionCodes.ACTION_SpearWalk;
import static com.lineage.server.ActionCodes.ACTION_SpellDirectionExtra;
import static com.lineage.server.ActionCodes.ACTION_StaffAttack;
import static com.lineage.server.ActionCodes.ACTION_StaffWalk;
import static com.lineage.server.ActionCodes.ACTION_SwordAttack;
import static com.lineage.server.ActionCodes.ACTION_SwordWalk;
import static com.lineage.server.ActionCodes.ACTION_Think;
import static com.lineage.server.ActionCodes.ACTION_ThrowingKnifeAttack;
import static com.lineage.server.ActionCodes.ACTION_ThrowingKnifeWalk;
import static com.lineage.server.ActionCodes.ACTION_TwoHandSwordAttack;
import static com.lineage.server.ActionCodes.ACTION_TwoHandSwordWalk;
import static com.lineage.server.ActionCodes.ACTION_Walk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 图形速度资料表
 */
public class SprTable {

    private static class Spr {
        final Map<Integer, Integer> moveSpeed = Maps.newMap();

        final Map<Integer, Integer> attackSpeed = Maps.newMap();

        final Map<Integer, Integer> specialSpeed = Maps.newMap();

        int nodirSpellSpeed = 1200;

        int dirSpellSpeed = 1200;

        public Spr() {
            // TODO Auto-generated constructor stub
        }
    }

    /** 提示信息 */
    private static Logger _log = Logger.getLogger(SprTable.class.getName());

    private static final Map<Integer, Spr> _dataMap = Maps.newMap();

    private static final SprTable _instance = new SprTable();

    public static SprTable getInstance() {
        return _instance;
    }

    private SprTable() {
        this.loadSprAction();
    }

    /**
     * 为从帧和帧速率（MS）动作的总时间来计算，并返回。
     * 
     * @param frameCount
     * @param frameRate
     */
    private int calcActionSpeed(final int frameCount, final int frameRate) {
        return (int) (frameCount * 40 * (24D / frameRate));
    }

    /**
     * 传回攻击速度。
     * 
     * @param sprid
     *            - 检查spr的ID
     * @param actid
     *            - 武器种类表值。L1Item.getType1()一致返回值 + 1
     * @return 指定spr的攻击速度(ms)
     */
    public int getAttackSpeed(final int sprid, final int actid) {
        if (_dataMap.containsKey(sprid)) {
            if (_dataMap.get(sprid).attackSpeed.containsKey(actid)) {
                return _dataMap.get(sprid).attackSpeed.get(actid);
            } else if (actid == ACTION_Attack) {
                return 0;
            } else {
                return _dataMap.get(sprid).attackSpeed.get(ACTION_Attack);
            }
        }
        return 0;
    }

    /**
     * 传回有向施法速度
     * 
     * @param sprid
     * @return
     */
    public int getDirSpellSpeed(final int sprid) {
        if (_dataMap.containsKey(sprid)) {
            return _dataMap.get(sprid).dirSpellSpeed;
        }
        return 0;
    }

    /**
     * 传回移动速度。
     * 
     * @param sprid
     * @param actid
     * @return
     */
    public int getMoveSpeed(final int sprid, final int actid) {
        if (_dataMap.containsKey(sprid)) {
            if (_dataMap.get(sprid).moveSpeed.containsKey(actid)) {
                return _dataMap.get(sprid).moveSpeed.get(actid);
            } else if (actid == ACTION_Walk) {
                return 0;
            } else {
                return _dataMap.get(sprid).moveSpeed.get(ACTION_Walk);
            }
        }
        return 0;
    }

    /**
     * 传回无向施法速度
     * 
     * @param sprid
     * @return
     */
    public int getNodirSpellSpeed(final int sprid) {
        if (_dataMap.containsKey(sprid)) {
            return _dataMap.get(sprid).nodirSpellSpeed;
        }
        return 0;
    }

    /**
     * 传回魔法娃娃表情动作速度
     * 
     * @param sprid
     * @param actid
     * @return
     */
    public int getSpecialSpeed(final int sprid, final int actid) {
        if (_dataMap.containsKey(sprid)) {
            if (_dataMap.get(sprid).specialSpeed.containsKey(actid)) {
                return _dataMap.get(sprid).specialSpeed.get(actid);
            }
            return 1200;
        }
        return 0;
    }

    /**
     * Npc 各动作延迟时间
     * 
     * @param sprid
     * @param actid
     */
    public int getSprSpeed(final int sprid, final int actid) {
        switch (actid) {
            case ACTION_Walk:
            case ACTION_SwordWalk:
            case ACTION_AxeWalk:
            case ACTION_BowWalk:
            case ACTION_SpearWalk:
            case ACTION_StaffWalk:
            case ACTION_DaggerWalk:
            case ACTION_TwoHandSwordWalk:
            case ACTION_EdoryuWalk:
            case ACTION_ClawWalk:
            case ACTION_ThrowingKnifeWalk:
                // 移动
                return this.getMoveSpeed(sprid, actid);
            case ACTION_SkillAttack:
                // 有向施法
                return this.getDirSpellSpeed(sprid);
            case ACTION_SkillBuff:
                // 无向施法
                return this.getNodirSpellSpeed(sprid);
            case ACTION_Attack:
            case ACTION_SwordAttack:
            case ACTION_AxeAttack:
            case ACTION_BowAttack:
            case ACTION_SpearAttack:
            case ACTION_AltAttack:
            case ACTION_SpellDirectionExtra:
            case ACTION_StaffAttack:
            case ACTION_DaggerAttack:
            case ACTION_TwoHandSwordAttack:
            case ACTION_EdoryuAttack:
            case ACTION_ClawAttack:
            case ACTION_ThrowingKnifeAttack:
                // 攻击
                return this.getAttackSpeed(sprid, actid);
            case ACTION_Think:
            case ACTION_Aggress:
                // 魔法娃娃表情动作
                return this.getSpecialSpeed(sprid, actid);
            default:
                break;
        }
        return 0;
    }

    /**
     * 加载spr_action。
     */
    public void loadSprAction() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Spr spr = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM spr_action");
            rs = pstm.executeQuery();
            while (rs.next()) {
                final int key = rs.getInt("spr_id");
                if (!_dataMap.containsKey(key)) {
                    spr = new Spr();
                    _dataMap.put(key, spr);
                } else {
                    spr = _dataMap.get(key);
                }

                final int actid = rs.getInt("act_id");
                final int frameCount = rs.getInt("framecount");
                final int frameRate = rs.getInt("framerate");
                final int speed = this.calcActionSpeed(frameCount, frameRate);

                switch (actid) {
                    case ACTION_Walk:
                    case ACTION_SwordWalk:
                    case ACTION_AxeWalk:
                    case ACTION_BowWalk:
                    case ACTION_SpearWalk:
                    case ACTION_StaffWalk:
                    case ACTION_DaggerWalk:
                    case ACTION_TwoHandSwordWalk:
                    case ACTION_EdoryuWalk:
                    case ACTION_ClawWalk:
                    case ACTION_ThrowingKnifeWalk:
                        spr.moveSpeed.put(actid, speed);
                        break;
                    case ACTION_SkillAttack:
                        spr.dirSpellSpeed = speed;
                        break;
                    case ACTION_SkillBuff:
                        spr.nodirSpellSpeed = speed;
                        break;
                    case ACTION_Attack:
                    case ACTION_SwordAttack:
                    case ACTION_AxeAttack:
                    case ACTION_BowAttack:
                    case ACTION_SpearAttack:
                    case ACTION_AltAttack:
                    case ACTION_SpellDirectionExtra:
                    case ACTION_StaffAttack:
                    case ACTION_DaggerAttack:
                    case ACTION_TwoHandSwordAttack:
                    case ACTION_EdoryuAttack:
                    case ACTION_ClawAttack:
                    case ACTION_ThrowingKnifeAttack:
                        spr.attackSpeed.put(actid, speed);
                        break;
                    case ACTION_Think:
                    case ACTION_Aggress:
                        spr.specialSpeed.put(actid, speed);
                        break;
                    default:
                        break;
                }
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
        _log.config("SPR数据 " + _dataMap.size() + "件");
    }
}
