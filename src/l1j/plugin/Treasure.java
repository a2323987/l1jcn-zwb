/* 
 * This program is free software; you can redistribute it and/or modify 
 * it under the terms of the GNU General Public License as published by 
 * the Free Software Foundation; either version 2, or (at your option) 
 * any later version. 
 * 
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU General Public License for more details. 
 * 
 * You should have received a copy of the GNU General Public License 
 * along with this program; if not, write to the Free Software 
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 
 * 02111-1307, USA. 
 * 
 * http://www.gnu.org/copyleft/gpl.html 
 */
package l1j.plugin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import l1j.server.Config;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_BlueMessage;
import l1j.server.server.serverpackets.S_SystemMessage;

public class Treasure {
	private final ArrayList<L1PcInstance> _members = new ArrayList<L1PcInstance>();
	private static Treasure _instance;
	public static Treasure getInstance() {
		if (_instance == null) {
			_instance = new Treasure();
		}
		return _instance;
	}
	public static final int STATUS_NONE = 0; // 闲置
	public static final int STATUS_PLAYING = 1; // 进行中
	public static final int STATUS_CLEANING = 2; // 准备中

	public String enterTreasure(L1PcInstance pc) {
		if (!checkTime()) {
			pc.sendPackets(new S_SystemMessage("目前活动正在准备中。请在9,13,18,21点后来参加！"));
			return "";
		}
		addMember(pc);
		return "";
	}
	private void addMember(L1PcInstance pc) {
		if (!_members.contains(pc)) {
			_members.add(pc);
		}
		L1Teleport.teleport(pc, 32767, 32830, (short) 610, pc.getHeading(), true);
		//L1Teleport.teleport(pc, 32481, 32929, (short) 0, pc.getHeading(), true);
		if (getTreasureStatus() == STATUS_NONE) {
			GeneralThreadPool.getInstance().execute(new runTreasure());
		}
	}

	private class runTreasure implements Runnable {
		public void run() {
			setTreasureStatus(STATUS_PLAYING);
			while (checkTime()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				endTreasure();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean checkTime() {
		boolean contain = false;
		int nowTime = getRealTime();
		if(nowTime > 900 && nowTime < 1000){
			contain = true;
			return contain;
		}
		if(nowTime > 1300 && nowTime < 1400){
			contain = true;
			return contain;
		}
		if(nowTime > 1800 && nowTime < 1900){
			contain = true;
			return contain;
		}
		if(nowTime > 2100 && nowTime < 2200){
			contain = true;
			return contain;
		}
		return contain;
	}
	private int getRealTime() {
		TimeZone _tz = TimeZone.getTimeZone(Config.TIME_ZONE);
		Calendar cal = Calendar.getInstance(_tz);
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		int nowTime = Integer.valueOf(sdf.format(cal.getTime()));
		return nowTime;
	}

	private void endTreasure() throws InterruptedException {
		sendMessage("活动已经结束，请下次再来");
		setTreasureStatus(STATUS_CLEANING);
		for (L1PcInstance pc : getMembersArray()) {
			if (pc.getMapId() == 610) {
				L1Teleport.teleport(pc, 32581, 32929, (short) 0, pc.getHeading(), true);
			}
		}
		clearMembers();
		while (!checkTime()) {
			Thread.sleep(1000);
		}
		setTreasureStatus(STATUS_NONE);
	}

	private void sendMessage(String msg) {
		for (L1PcInstance pc : getMembersArray()) {
			if (pc.getMapId() == 610) {
				pc.sendPackets(new S_BlueMessage(166, "\\f3" + msg)); // 夜小空 创意广播
			}
		}
	}
	private int _TreasureStatus = STATUS_NONE;
	private void setTreasureStatus(int i) {
		_TreasureStatus = i;
	}
	private int getTreasureStatus() {
		return _TreasureStatus;
	}
	private void clearMembers() {
		_members.clear();
	}

	private L1PcInstance[] getMembersArray() {
		return _members.toArray(new L1PcInstance[_members.size()]);
	}
}