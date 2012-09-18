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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.lineage.Config;

/**
 * <strong>Time Information(时间信息)</strong>
 * 
 * @author ibm
 */
public class TimeInform {
    /**
     * Calendar本身是abstract,使用getInstance()来取得物件
     */
    static TimeZone timezone = TimeZone.getTimeZone(Config.TIME_ZONE);
    static Calendar rightNow = Calendar.getInstance(timezone);

    /**
     * @return getDay 日
     */
    public static String getDay() {

        return String.valueOf(rightNow.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * @return getDayOfWeek 星期
     */
    public static String getDayOfWeek() {
        String DayOfWeek = null;
        switch (rightNow.get(Calendar.DAY_OF_WEEK)) {
            case 1:// index 1~7 星期日~星期六
                DayOfWeek = "星期日";
                break;
            case 2:
                DayOfWeek = "星期一";
                break;
            case 3:
                DayOfWeek = "星期二";
                break;
            case 4:
                DayOfWeek = "星期三";
                break;
            case 5:
                DayOfWeek = "星期四";
                break;
            case 6:
                DayOfWeek = "星期五";
                break;
            case 7:
                DayOfWeek = "星期六";
                break;

        }
        return DayOfWeek;
    }

    /**
     * @return getHour 时
     */
    public static String getHour() {

        return String.valueOf(rightNow.get(Calendar.HOUR_OF_DAY));
    }

    /**
     * @return getMinute 分
     */
    public static String getMinute() {

        return String.valueOf(rightNow.get(Calendar.MINUTE));
    }

    /**
     * @return getMonth 月
     */
    public static String getMonth() {
        // Calendar.MONTH - index从0开始
        return String.valueOf(rightNow.get(Calendar.MONTH) + 1);
    }

    /**
     * @param type
     *            时间格式<BR>
     *            type = 1 : X年X月X日星期X<BR>
     *            type = 2 : X时X分X秒<BR>
     *            type = 3 : X年X月X日-X时X分X秒<BR>
     * @param type_year
     *            0:西元 1:民国
     * @return
     */
    public static String getNowTime(final int type, final int type_year) {
        String NowTime = null;
        switch (type) {
            case 1:
                NowTime = TimeInform.getYear(type_year, 0) + "年 "
                        + TimeInform.getMonth() + "月" + TimeInform.getDay()
                        + "日 " + TimeInform.getDayOfWeek();
                break;
            case 2:
                NowTime = TimeInform.getHour() + "时" + TimeInform.getMinute()
                        + "分" + TimeInform.getSecond() + "秒";
                break;
            case 3:
                NowTime = TimeInform.getYear(type_year, 0) + "年"
                        + TimeInform.getMonth() + "月" + TimeInform.getDay()
                        + "日" + TimeInform.getHour() + "时"
                        + TimeInform.getMinute() + "分" + TimeInform.getSecond()
                        + "秒";
            default:

        }
        return NowTime;
    }

    /**
     * @return getSecond 秒
     */
    public static String getSecond() {

        return String.valueOf(rightNow.get(Calendar.MINUTE));
    }

    /**
     * @return getYear 年
     * @param type
     *            0:原始(可加减) 1:西元 2:民国
     * @param i
     *            = +|- years
     */
    public static String getYear(final int type, final int i) {
        String year = null;
        if (type == 0) {
            year = String.valueOf(rightNow.get(Calendar.YEAR) + i);
        } else if (type == 1) {
            year = "西元 " + String.valueOf(rightNow.get(Calendar.YEAR));
        } else if (type == 2) {
            // 民国
            year = "民国 " + String.valueOf(rightNow.get(Calendar.YEAR) - 1911);
        } else {
            year = null;
        }
        return year;
    }

    /**
     * @return yyyy-MM-dd HH:mm:ss
     */
    public String getNowTime_Standard() {

        final String NowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date());

        return NowTime;
    }
}
