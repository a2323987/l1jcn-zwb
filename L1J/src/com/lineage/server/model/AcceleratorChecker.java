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

import java.util.EnumMap;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.datatables.SprTable;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Disconnect;
import com.lineage.server.serverpackets.S_Paralysis;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.utils.LogRecorder;

/**
 * 检查加速器使用的类别。
 */
public class AcceleratorChecker {

    public static enum ACT_TYPE {
        MOVE, ATTACK, SPELL_DIR, SPELL_NODIR
    }

    private static final Logger _log = Logger
            .getLogger(AcceleratorChecker.class.getName());

    private final L1PcInstance _pc;

    private int _injusticeCount;

    private int _justiceCount;

    private static final int INJUSTICE_COUNT_LIMIT = Config.INJUSTICE_COUNT;

    private static final int JUSTICE_COUNT_LIMIT = Config.JUSTICE_COUNT;

    // 实际上移动间隔和攻击间隔的数据包比spr的理论值延迟5%。
    // 考虑忽视-5。
    private static final double CHECK_STRICTNESS = (Config.CHECK_STRICTNESS - 5) / 100D;

    private static final double HASTE_RATE = 0.75; // 速度 * 1.33

    private static final double WAFFLE_RATE = 0.87; // 速度 * 1.15

    private static final double DOUBLE_HASTE_RATE = 0.375; // 速度 * 2.66

    private final EnumMap<ACT_TYPE, Long> _actTimers = new EnumMap<ACT_TYPE, Long>(
            ACT_TYPE.class);

    private final EnumMap<ACT_TYPE, Long> _checkTimers = new EnumMap<ACT_TYPE, Long>(
            ACT_TYPE.class);

    // 检查结果
    public static final int R_OK = 0;

    public static final int R_DETECTED = 1;

    public static final int R_DISPOSED = 2;

    public AcceleratorChecker(final L1PcInstance pc) {
        this._pc = pc;
        this._injusticeCount = 0;
        this._justiceCount = 0;
        final long now = System.currentTimeMillis();
        for (final ACT_TYPE each : ACT_TYPE.values()) {
            this._actTimers.put(each, now);
            this._checkTimers.put(each, now);
        }
    }

    /**
     * 检查是否行动是不合法间隔、做合适处理。
     * 
     * @param type
     *            - 检查行动方式
     * @return 没有问题0、非法场合1、不正动作达到一定回数切断玩家连线2。
     */
    public int checkInterval(final ACT_TYPE type) {
        int result = R_OK;
        final long now = System.currentTimeMillis();
        long interval = now - this._actTimers.get(type);
        final int rightInterval = this.getRightInterval(type);

        interval *= CHECK_STRICTNESS;

        if ((0 < interval) && (interval < rightInterval)) {
            this._injusticeCount++;
            this._justiceCount = 0;
            if (this._injusticeCount >= INJUSTICE_COUNT_LIMIT) {
                this.doPunishment(Config.ILLEGAL_SPEEDUP_PUNISHMENT);
                return R_DISPOSED;
            }
            result = R_DETECTED;
        } else if (interval >= rightInterval) {
            this._justiceCount++;
            if (this._justiceCount >= JUSTICE_COUNT_LIMIT) {
                this._injusticeCount = 0;
                this._justiceCount = 0;
            }
        }

        // 检证用
        // double rate = (double) interval / rightInterval;
        // System.out.println(String.format("%s: %d / %d = %.2f (o-%d x-%d)",
        // type.toString(), interval, rightInterval, rate,
        // _justiceCount, _injusticeCount));

        this._actTimers.put(type, now);
        return result;
    }

    /**
     * 加速检测处罚
     * 
     * @param punishmaent
     *            处罚模式
     */
    private void doPunishment(final int punishmaent) {
        if (!this._pc.isGm()) { // 如果不是GM才执行处罚
            final int x = this._pc.getX(), y = this._pc.getY(), mapid = this._pc
                    .getMapId(); // 记录坐标
            switch (punishmaent) {
                case 0: // 剔除
                    this._pc.sendPackets(new S_ServerMessage(945));
                    this._pc.sendPackets(new S_Disconnect());
                    _log.info(String.format("因为检测到%s正在使用加速器，强制切断其连线。",
                            this._pc.getName()));
                    break;
                case 1: // 锁定人物10秒
                    this._pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_BIND,
                            true));
                    try {
                        Thread.sleep(10000); // 暂停十秒
                    } catch (final Exception e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                    this._pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_BIND,
                            false));
                    break;
                case 2: // 传到地狱
                    L1Teleport.teleport(this._pc, 32737, 32796, (short) 99, 5,
                            false);
                    this._pc.sendPackets(new S_SystemMessage(
                            "因为你使用加速器，被传送到了地域，10秒后传回。"));
                    try {
                        Thread.sleep(10000); // 暂停十秒
                    } catch (final Exception e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                    L1Teleport
                            .teleport(this._pc, x, y, (short) mapid, 5, false);
                    break;
                case 3: // 传到GM房，30秒后传回
                    L1Teleport.teleport(this._pc, 32737, 32796, (short) 99, 5,
                            false);
                    this._pc.sendPackets(new S_SystemMessage(
                            "因为你使用加速器，被传送到了GM房，30秒后传回。"));
                    try {
                        Thread.sleep(30000); // 暂停30秒
                    } catch (final Exception e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                    L1Teleport
                            .teleport(this._pc, x, y, (short) mapid, 5, false);
                    break;
            }
        } else {
            // GM不需要断线
            this._pc.sendPackets(new S_SystemMessage("游戏管理员在游戏中使用加速器检测中。"));
            this._injusticeCount = 0;
        }
        if (Config.writeRobotsLog) {
            LogRecorder.writeRobotsLog(this._pc); // 加速器记录
        }
    }

    /**
     * 返回PCの指定类型的状态の行动の正确的时间间隔(ms)计算。
     * 
     * @param type
     *            - 动作种类
     * @param _pc
     *            - 检查PC
     * @return 正确的时间间隔（ms）
     */
    private int getRightInterval(final ACT_TYPE type) {
        int interval;

        // 动作判断
        switch (type) {
            case ATTACK:
                interval = SprTable.getInstance().getAttackSpeed(
                        this._pc.getTempCharGfx(),
                        this._pc.getCurrentWeapon() + 1);
                break;
            case MOVE:
                interval = SprTable.getInstance().getMoveSpeed(
                        this._pc.getTempCharGfx(), this._pc.getCurrentWeapon());
                break;
            case SPELL_DIR:
                interval = SprTable.getInstance().getDirSpellSpeed(
                        this._pc.getTempCharGfx());
                break;
            case SPELL_NODIR:
                interval = SprTable.getInstance().getNodirSpellSpeed(
                        this._pc.getTempCharGfx());
                break;
            default:
                return 0;
        }

        // 一段加速
        switch (this._pc.getMoveSpeed()) {
            case 1: // 加速术
                interval *= HASTE_RATE;
                break;
            case 2: // 缓速术
                interval /= HASTE_RATE;
                break;
            default:
                break;
        }

        // 二段加速
        switch (this._pc.getBraveSpeed()) {
            case 1: // 勇水
                interval *= HASTE_RATE; // 攻速、移速 * 1.33倍
                break;
            case 3: // 精饼
                interval *= WAFFLE_RATE; // 攻速、移速 * 1.15倍
                break;
            case 4: // 神疾、风走、行走
                if (type.equals(ACT_TYPE.MOVE)) {
                    interval *= HASTE_RATE; // 移速 * 1.33倍
                }
                break;
            case 5: // 超级加速
                interval *= DOUBLE_HASTE_RATE; // 攻速、移速 * 2.66倍
                break;
            case 6: // 血之渴望
                if (type.equals(ACT_TYPE.ATTACK)) {
                    interval *= HASTE_RATE; // 攻速 * 1.33倍
                }
                break;
            default:
                break;
        }

        // 生命之树果实
        if (this._pc.isRibrave() && type.equals(ACT_TYPE.MOVE)) { // 移速 * 1.15倍
            interval *= WAFFLE_RATE;
        }
        // 三段加速
        if (this._pc.isThirdSpeed()) { // 攻速、移速 * 1.15倍
            interval *= WAFFLE_RATE;
        }
        // 风之枷锁
        if (this._pc.isWindShackle() && !type.equals(ACT_TYPE.MOVE)) { // 攻速or施法速度
                                                                       // / 2倍
            interval /= 2;
        }
        if (this._pc.getMapId() == 5143) { // 宠物竞速例外
            interval *= 0.1;
        }
        return interval;
    }
}
