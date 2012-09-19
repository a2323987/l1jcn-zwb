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
package l1j.plugin;

import java.util.*;// class Scanner

public class GetNowTime {

	public static int GetNowYear() {
		Calendar rightNow = Calendar.getInstance(); // 取得预设月历物件
		int nowYear;
		nowYear = rightNow.get(Calendar.YEAR); // 取得现年之值
		return nowYear; // 传回取得现年之值
	}

	public static int GetNowMonth() {
		Calendar rightNow = Calendar.getInstance(); // 取得预设月历物件
		int nowMonth;
		nowMonth = rightNow.get(Calendar.MONTH); // 取得现月之值
		return nowMonth + 1; // 传回取得现月之值
	}

	public static int GetNowDay() {
		Calendar rightNow = Calendar.getInstance(); // 取得预设月历物件
		int nowDay;
		nowDay = rightNow.get(Calendar.DATE); // 取得今日之值
		return nowDay; // 传回取得今日之值
	}

	public static int GetNowWeek() {
		Calendar rightNow = Calendar.getInstance(); // 取得预设月历物件
		int nowWeek;
		nowWeek = rightNow.get(Calendar.DAY_OF_WEEK); // 取得今日星期之值
		return nowWeek - 1; // 传回取得今日星期之值
	}

	public static int GetNowHour() {
		Calendar rightNow = Calendar.getInstance(); // 取得预设月历物件
		int nowHour;
		nowHour = rightNow.get(Calendar.HOUR_OF_DAY); // 取得此时之值
		return nowHour; // 传回取得此时之值
	}

	public static int GetNowMinute() {
		Calendar rightNow = Calendar.getInstance(); // 取得预设月历物件
		int nowMinute;
		nowMinute = rightNow.get(Calendar.MINUTE); // 取得此分之值
		return nowMinute; // 传回取得此分之值
	}

	public static int GetNowSecond() {
		Calendar rightNow = Calendar.getInstance(); // 取得预设月历物件
		int nowSecond;
		nowSecond = rightNow.get(Calendar.SECOND); // 取得此秒之值
		return nowSecond; // 传回取得此秒之值
	}
}