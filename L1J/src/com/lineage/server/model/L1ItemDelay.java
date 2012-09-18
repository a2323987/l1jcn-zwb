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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lineage.server.ClientThread;
import com.lineage.server.GeneralThreadPool;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Paralysis;
import com.lineage.server.templates.L1EtcItem;

/**
 * 物件使用延迟
 * 
 * @author jrwz
 */
public class L1ItemDelay {

    /**
     * 道具使用延迟计时器
     */
    static class ItemDelayTimer implements Runnable {

        /** 延迟角色 */
        private final L1Character _cha;

        /** 延迟ID */
        private final int _delayId;

        /** 延迟时间 (毫秒) */
        private final int _delayTime;

        /**
         * 道具使用延迟计时器
         * 
         * @param cha
         *            延迟角色
         * @param id
         *            延迟ID
         * @param delayTiem
         *            延迟时间 (毫秒)
         */
        public ItemDelayTimer(final L1Character cha, final int id,
                final int delayTime) {
            this._cha = cha;
            this._delayId = id;
            this._delayTime = delayTime;
        }

        /**
         * 取得该物件延迟时间
         * 
         * @return 延迟时间 (毫秒)
         */
        public int get_delayTime() {
            return this._delayTime;
        }

        @Override
        public void run() {
            this.stopDelayTimer(this._delayId);
        }

        /**
         * 停止该物件使用延迟
         * 
         * @param delayId
         *            延迟ID
         */
        public void stopDelayTimer(final int delayId) {
            this._cha.removeItemDelay(delayId);
        }
    }

    /**
     * 瞬移解锁定时器
     */
    static class TeleportUnlockTimer implements Runnable {

        /** 角色 */
        private final L1PcInstance _pc;

        /**
         * 瞬移解锁定时器
         * 
         * @param pc
         *            角色
         */
        public TeleportUnlockTimer(final L1PcInstance pc) {
            this._pc = pc;
        }

        @Override
        public void run() {
            this._pc.sendPackets(new S_Paralysis(
                    S_Paralysis.TYPE_TELEPORT_UNLOCK, true));
        }
    }

    /** 提示信息 */
    private static final Log _log = LogFactory.getLog(L1ItemDelay.class);

    /**
     * 500:武器禁止使用
     */
    public static final int WEAPON = 500; // 武器禁止使用

    /**
     * 501:防具禁止使用
     */
    public static final int ARMOR = 501; // 防具禁止使用

    /**
     * 502:道具禁止使用
     */
    public static final int ITEM = 502; // 道具禁止使用

    /**
     * 503:变身禁止使用
     */
    public static final int POLY = 503; // 变身禁止使用

    /**
     * 建立物件使用延迟
     * 
     * @param client
     *            执行连线端
     * @param item
     *            物件
     */
    public static void onItemUse(final ClientThread client,
            final L1ItemInstance item) {
        try {
            final L1PcInstance pc = client.getActiveChar();
            if (pc != null) {
                onItemUse(pc, item);
            }

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 建立物件使用延迟
     * 
     * @param pc
     *            使用人物
     * @param delayId
     *            延迟ID<br>
     * <br>
     *            500:武器禁止使用<br>
     *            501:防具禁止使用<br>
     *            502:道具禁止使用<br>
     *            503:变身禁止使用<br>
     *            504:禁止移动<br>
     * @param delayTime
     *            延迟时间 (毫秒)
     */
    public static void onItemUse(final L1PcInstance pc, final int delayId,
            final int delayTime) {
        try {
            if ((delayId != 0) && (delayTime != 0)) {
                final ItemDelayTimer timer = new ItemDelayTimer(pc, delayId,
                        delayTime);

                pc.addItemDelay(delayId, timer);
                GeneralThreadPool.getInstance().schedule(timer, delayTime);
            }

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 建立物件使用延迟
     * 
     * @param pc
     *            执行角色
     * @param item
     *            物件
     */
    public static void onItemUse(final L1PcInstance pc,
            final L1ItemInstance item) {
        try {
            int delayId = 0;
            int delayTime = 0;
            switch (item.getItem().getType2()) {
                case 0: // 类别：道具
                    delayId = ((L1EtcItem) item.getItem()).get_delayid();
                    delayTime = ((L1EtcItem) item.getItem()).get_delaytime();
                    break;

                case 1: // 类别：武器
                    return;

                case 2: // 类别：防具
                    switch (item.getItemId()) {
                        case 20077: // 隐身斗篷
                        case 120077: // 祝福的 隐身斗篷
                        case 20062: // 炎魔的血光斗篷

                            // 装备使用中 && 非隐身状态
                            if (item.isEquipped() && !pc.isInvisble()) {
                                pc.beginInvisTimer();
                            }
                            break;

                        default: // 其他道具
                            return;
                    }
                    break;
            }

            if ((delayId != 0) && (delayTime != 0)) {
                final ItemDelayTimer timer = new ItemDelayTimer(pc, delayId,
                        delayTime);

                pc.addItemDelay(delayId, timer);
                GeneralThreadPool.getInstance().schedule(timer, delayTime);
            }

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 瞬移解锁
     * 
     * @param pc
     *            角色
     * @param item
     *            物件
     */
    public static void teleportUnlock(final L1PcInstance pc,
            final L1ItemInstance item) {
        final int delayTime = ((L1EtcItem) item.getItem()).get_delaytime();
        final TeleportUnlockTimer timer = new TeleportUnlockTimer(pc);
        GeneralThreadPool.getInstance().schedule(timer, delayTime);
    }

    private L1ItemDelay() {
    }

}
