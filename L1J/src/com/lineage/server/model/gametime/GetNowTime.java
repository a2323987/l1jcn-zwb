/********************************************************************
 *  GetNowTime.java
 *  by mca
 *  2008/4/1
 */

package com.lineage.server.model.gametime;

import java.util.Calendar;

/**
 * 取得现实时间
 */
public class GetNowTime {

    /** 传回取得今日之值 */
    public static int GetNowDay() {
        final Calendar rightNow = Calendar.getInstance(); // 取得预设月历物件
        int nowDay;
        nowDay = rightNow.get(Calendar.DATE); // 取得今日之值
        return nowDay; // 传回取得今日之值
    }

    /** 传回取得此时之值 */
    public static int GetNowHour() {
        final Calendar rightNow = Calendar.getInstance(); // 取得预设月历物件
        int nowHour;
        nowHour = rightNow.get(Calendar.HOUR_OF_DAY); // 取得此时之值
        return nowHour; // 传回取得此时之值
    }

    /** 传回取得此分之值 */
    public static int GetNowMinute() {
        final Calendar rightNow = Calendar.getInstance(); // 取得预设月历物件
        int nowMinute;
        nowMinute = rightNow.get(Calendar.MINUTE); // 取得此分之值
        return nowMinute; // 传回取得此分之值
    }

    /** 传回取得现月之值 */
    public static int GetNowMonth() {
        final Calendar rightNow = Calendar.getInstance(); // 取得预设月历物件
        int nowMonth;
        nowMonth = rightNow.get(Calendar.MONTH); // 取得现月之值
        return nowMonth; // 传回取得现月之值
    }

    /** 传回取得此秒之值 */
    public static int GetNowSecond() {
        final Calendar rightNow = Calendar.getInstance(); // 取得预设月历物件
        int nowSecond;
        nowSecond = rightNow.get(Calendar.SECOND); // 取得此秒之值
        return nowSecond; // 传回取得此秒之值
    }

    /** 传回取得现年之值 */
    public static int GetNowYear() {
        final Calendar rightNow = Calendar.getInstance(); // 取得预设月历物件
        int nowYear;
        nowYear = rightNow.get(Calendar.YEAR); // 取得现年之值
        return nowYear; // 传回取得现年之值
    }
}
