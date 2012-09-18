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
package com.lineage.server.utils;

import java.sql.Time;

import com.lineage.server.model.gametime.L1GameTime;

/**
 * 时间周期
 */
public class TimePeriod {

    /** 开始时间 */
    private final Time _timeStart;

    /** 结束时间 */
    private final Time _timeEnd;

    /**
     * 时间周期
     * 
     * @param timeStart
     *            开始时间
     * @param timeEnd
     *            结束时间
     */
    public TimePeriod(final Time timeStart, final Time timeEnd) {
        if (timeStart.equals(timeEnd)) {
            throw new IllegalArgumentException("开始时间不能等于结束时间");
        }

        this._timeStart = timeStart;
        this._timeEnd = timeEnd;
    }

    /**  */
    public boolean includes(final L1GameTime time) {
        /*
         * 分かりづらいロジック・・・ timeStart after timeEndのとき(例:18:00~06:00)
         * timeEnd~timeStart(06:00~18:00)の範囲内でなければ、
         * timeStart~timeEnd(18:00~06:00)の範囲内と見なせる
         */
        return this._timeStart.after(this._timeEnd) ? !this.includes(time,
                this._timeEnd, this._timeStart) : this.includes(time,
                this._timeStart, this._timeEnd);
    }

    /**  */
    private boolean includes(final L1GameTime time, final Time timeStart,
            final Time timeEnd) {
        final Time when = time.toTime();
        return (timeStart.compareTo(when) <= 0)
                && (0 < timeEnd.compareTo(when));
    }
}
