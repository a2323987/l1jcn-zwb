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

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import com.lineage.server.utils.IntRange;

/**
 * 游戏时间
 */
public class L1GameTime {

    /** 2003年7月3日 12:00(UTC)1月1日00:00 */
    private static final long BASE_TIME_IN_MILLIS_REAL = 1057233600000L;

    public static L1GameTime fromSystemCurrentTime() {
        return L1GameTime.valueOf(System.currentTimeMillis());
    }

    public static L1GameTime valueOf(final long timeMillis) {
        final long t1 = timeMillis - BASE_TIME_IN_MILLIS_REAL;
        if (t1 < 0) {
            throw new IllegalArgumentException();
        }
        final int t2 = (int) ((t1 * 6) / 1000L);
        final int t3 = t2 % 3; // 时间调整到3倍
        return new L1GameTime(t2 - t3);
    }

    public static L1GameTime valueOfGameTime(final Time time) {
        final long t = time.getTime() + TimeZone.getDefault().getRawOffset();
        return new L1GameTime((int) (t / 1000L));
    }

    /** 时间 */
    private final int _time;

    /** 日期 */
    private final Calendar _calendar;

    /** 伺服器重启 */
    public L1GameTime() {
        this((int) System.currentTimeMillis());
    }

    private L1GameTime(final int time) {
        this._time = time;
        this._calendar = this.makeCalendar(time);
    }

    public int get(final int field) {
        return this._calendar.get(field);
    }

    public Calendar getCalendar() {
        return (Calendar) this._calendar.clone();
    }

    public int getSeconds() {
        return this._time;
    }

    public boolean isNight() {
        final int hour = this._calendar.get(Calendar.HOUR_OF_DAY);
        return !IntRange.includes(hour, 6, 17); // 6:00-17:59(昼)で無ければtrue
    }

    /** 构造日期 */
    private Calendar makeCalendar(final int time) {
        final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(0);
        cal.add(Calendar.SECOND, time);
        return cal;
    }

    @Override
    public String toString() {
        final SimpleDateFormat f = new SimpleDateFormat(
                "yyyy.MM.dd G 'at' HH:mm:ss z");
        f.setTimeZone(this._calendar.getTimeZone());
        return f.format(this._calendar.getTime()) + "(" + this.getSeconds()
                + ")";
    }

    public Time toTime() {
        final int t = this._time % (24 * 3600); // 日付情報分を切り捨て
        return new Time(t * 1000L - TimeZone.getDefault().getRawOffset());
    }
}
