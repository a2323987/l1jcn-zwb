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
package com.lineage.server.model.gametime;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.GeneralThreadPool;
import com.lineage.server.utils.collections.Lists;

/**
 * 游戏时钟
 */
public class L1GameTimeClock {

    /** 更新时间 */
    private class TimeUpdater implements Runnable {
        public TimeUpdater() {
            // TODO Auto-generated constructor stub
        }

        @Override
        public void run() {
            while (true) {
                L1GameTimeClock.this._previousTime = L1GameTimeClock.this._currentTime;
                L1GameTimeClock.this._currentTime = L1GameTime
                        .fromSystemCurrentTime();
                L1GameTimeClock.this.notifyChanged();

                try {
                    Thread.sleep(500);
                } catch (final InterruptedException e) {
                    _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                }
            }
        }
    }

    static Logger _log = Logger.getLogger(L1GameTimeClock.class.getName());
    private static L1GameTimeClock _instance;

    public static L1GameTimeClock getInstance() {
        return _instance;
    }

    /** 初始化 */
    public static void init() {
        _instance = new L1GameTimeClock();
    }

    /** 当前时间 */
    volatile L1GameTime _currentTime = L1GameTime.fromSystemCurrentTime();

    /** 以前的时间 */
    L1GameTime _previousTime = null;

    /**  */
    private final List<L1GameTimeListener> _listeners = Lists
            .newConcurrentList();

    private L1GameTimeClock() {
        GeneralThreadPool.getInstance().execute(new TimeUpdater());
    }

    /** 增加监听 */
    public void addListener(final L1GameTimeListener listener) {
        this._listeners.add(listener);
    }

    /** 当前时间 */
    public L1GameTime currentTime() {
        return this._currentTime;
    }

    /** 改变 */
    private boolean isFieldChanged(final int field) {
        return this._previousTime.get(field) != this._currentTime.get(field);
    }

    /** 通知改变 */
    void notifyChanged() {
        if (this.isFieldChanged(Calendar.MONTH)) {
            for (final L1GameTimeListener listener : this._listeners) {
                listener.onMonthChanged(this._currentTime);
            }
        }
        if (this.isFieldChanged(Calendar.DAY_OF_MONTH)) {
            for (final L1GameTimeListener listener : this._listeners) {
                listener.onDayChanged(this._currentTime);
            }
        }
        if (this.isFieldChanged(Calendar.HOUR_OF_DAY)) {
            for (final L1GameTimeListener listener : this._listeners) {
                listener.onHourChanged(this._currentTime);
            }
        }
        if (this.isFieldChanged(Calendar.MINUTE)) {
            for (final L1GameTimeListener listener : this._listeners) {
                listener.onMinuteChanged(this._currentTime);
            }
        }
    }

    /** 删除监听 */
    public void removeListener(final L1GameTimeListener listener) {
        this._listeners.remove(listener);
    }
}
