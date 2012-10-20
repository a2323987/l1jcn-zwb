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
package l1j.plugin.tdTower;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.output.Pcdata;

import l1j.server.Config;
import l1j.server.L1DatabaseFactory;
import l1j.server.server.ActionCodes;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.IdFactory;
import l1j.server.server.datatables.NpcTable;
import l1j.server.server.model.L1Character;
import l1j.server.server.model.L1Inventory;
import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1MonsterInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_BlueMessage;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.utils.SQLUtil;

public class TDTower {
	/*
	 *TD塔防 进入 游戏开始后 还没参加过的玩家不可进入
	 */
	private final ArrayList<L1PcInstance> _members = new ArrayList<L1PcInstance>();
	private static TDTower _instance;
	public static TDTower getInstance() {
		if (_instance == null) {
			_instance = new TDTower();
		}
		return _instance;
	}
	public static final int STATUS_NONE = 0; // 闲置
	public static final int STATUS_READY = 1; // 等待中
	public static final int STATUS_PLAYING = 2; // 游戏开始
	public static final int STATUS_CLEANING = 4; // 清洁中
	private static final int _minPlayer = Config.minPlayerTD; // 最少玩家数
	private static final int _maxPlayer = Config.maxPlayerTD; // 最多玩家数
	private static final int _readytime = Config.readytimeTD * 1000; // 等待时间 80秒 + 倒数 = 总共90秒
	private static final int _cleartime = Config.cleartimeTD * 1000; // 清洁时间 五分钟
	private static final int _bossId1 = Config.bossId1TD; // 火焰领主
	private static final int _bossId2 = Config.bossId2TD; // 冰霜君主
	private static final int _bossId3 = Config.bossId3TD; // 噩梦君主
	private static final int _bossId4 = Config.bossId4TD; // 蛮牛暴君
	private static final int _bossId5 = Config.bossId5TD; // 噬血暴君
	private static final int _bossId6 = Config.bossId6TD; // 独眼暴君
	private static final int _bossId7 = Config.bossId7TD; // 不朽暴君
	private static final int _bossId8 = Config.bossId8TD; // 幻影魔豹
	private static final int _bossId9 = Config.bossId9TD; // 血族亲王
	private static final int _bossId10 = Config.bossId10TD; // 半神强者吉尔塔斯
	private static final int _item = Config.bossItemTD; // 扣除物品
	private static final int _adena = Config.itemCountTD; // 扣除金币数量
	
	private static final int _itemReward = Config.bossItemTDReward; // 扣除物品
	private static final int _adenaReward = Config.itemCountTDReward; // 扣除金币数量
	
	public String enterTDTower(L1PcInstance pc) {
		if (TDTower.getInstance().getBossRoomStatus() == TDTower.STATUS_CLEANING) {
			pc.sendPackets(new S_SystemMessage("TD塔防正在清理中。"));
			return "";
		}
		if (TDTower.getInstance().getBossRoomStatus() == TDTower.STATUS_PLAYING && !isMember(pc)) {
			pc.sendPackets(new S_ServerMessage(1182)); // 游戏已经开始了。
			return "";
		}
		if (getMembersCount() >= _maxPlayer && !isMember(pc)) {
			pc.sendPackets(new S_SystemMessage("TD塔防已经到达饱和的状态了。"));
			return "";
		}
		if (!pc.getInventory().checkItem(_item, _adena) && !isMember(pc)) {
			pc.sendPackets(new S_ServerMessage(189)); // 金币不足
			return "";
		}
		L1Teleport.teleport(pc, 32768, 32773, (short) 5065, pc.getHeading(), true);
		addMember(pc);
		return "";
	}
	private void addMember(L1PcInstance pc) {
		if (!_members.contains(pc)) {
			_members.add(pc);
			pc.getInventory().consumeItem(_item, _adena);
		}
		if (getMembersCount() == 1 && getBossRoomStatus() == STATUS_NONE) {			
			GeneralThreadPool.getInstance().execute(new runTDTower());
		}
	}
	private class runTDTower implements Runnable {
		public void run() {
			try {
				setBossRoomStatus(STATUS_READY);
				sendMessage(((Config.readytime)+10) + "秒后开始游戏");
				Thread.sleep(_readytime);
				sendMessage("倒数10秒");
				Thread.sleep(5 * 1000);
				sendMessage("倒数5秒");
				Thread.sleep(1000);
				sendMessage("4秒");
				Thread.sleep(1000);
				sendMessage("3秒");
				Thread.sleep(1000);
				sendMessage("2秒");
				Thread.sleep(1000);
				sendMessage("1秒");
				Thread.sleep(1000);
				if (checkPlayerCount()) {
					setBossRoomStatus(STATUS_PLAYING);
					spawnBoss(_bossId1, "1", "2");
					Thread.sleep(2 * 60000);
					spawnBoss(_bossId2, "2", "3");
					Thread.sleep(2 * 60000);
					spawnBoss(_bossId3, "3", "4");
					Thread.sleep(2 * 60000);
					spawnBoss(_bossId4, "4", "5");
					Thread.sleep(2 * 60000);
					spawnBoss(_bossId5, "5", "6");
					Thread.sleep(2 * 60000);
					spawnBoss(_bossId6, "6", "7");
					Thread.sleep(2 * 60000);
					spawnBoss(_bossId7, "7", "8");
					Thread.sleep(2 * 60000);
					spawnBoss(_bossId8, "8", "9");
					Thread.sleep(2 * 60000);
					spawnBoss(_bossId9, "9", "10");
					Thread.sleep(5 * 60000);
					spawnBoss(_bossId10, "10", null);
					Thread.sleep(10 * 60000);
					endBossRoom();
				}
				Thread.sleep(_cleartime);
				setBossRoomStatus(STATUS_NONE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private boolean checkPlayerCount() {
		if (getMembersCount() < _minPlayer) {
			setBossRoomStatus(STATUS_CLEANING);
			sendMessage("人数不足 " + _minPlayer + " 人，所以强制关闭游戏");
			for (L1PcInstance pc : getMembersArray()) {
				pc.getInventory().storeItem(_item, _adena);
				pc.sendPackets(new S_SystemMessage("TD塔防退还通行材料(" + _adena + ")。"));
				L1Teleport.teleport(pc, 32581, 32929, (short) 0, pc.getHeading(), true);
			}
			clearMembers();
			return false;
		}
		return true;
	}
	private void endBossRoom() {
		sendMessage("TD塔防完成了，恭喜您！");
		over();
		reward();
	}
	private void reward(){
		for (L1PcInstance pc : getMembersArray()) {
			pc.getInventory().storeItem(_itemReward, _adenaReward);
			pc.sendPackets(new S_SystemMessage("TD塔防奖励您(" + _adena + ")个物品。"));
		}
	}
	private void over(){
		setBossRoomStatus(STATUS_CLEANING);
		for (L1PcInstance pc : getMembersArray()) {
			L1Teleport.teleport(pc, 32581, 32929, (short) 0, pc.getHeading(), true);
		}
		clearMembers();
		clearColosseum();
	}
	public void gameOver() {
		sendMessage("TD塔防失败，请下次再来");
		over();
		try {
			Thread.sleep(_cleartime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setBossRoomStatus(STATUS_NONE);
		}
	private void clearColosseum() {
		for (Object obj : L1World.getInstance().getVisibleObjects(515).values()) {
			if (obj instanceof L1MonsterInstance) {
				L1MonsterInstance mob = (L1MonsterInstance) obj;
				if (!mob.isDead()) {
					mob.setDead(true);
					mob.setStatus(ActionCodes.ACTION_Die);
					mob.setCurrentHpDirect(0);
					mob.deleteMe();
				}
			} else if (obj instanceof L1Inventory) {
				L1Inventory inventory = (L1Inventory) obj;
				inventory.clearItems();
			}
		}
	}
	private void spawnBoss(int npcid, String msg1, String msg2) {
		if (msg1.equalsIgnoreCase("9")) {
			sendMessage("第 " + msg1 + " 关 [" + getBossName(npcid) + "] 5分后开始第 " + msg2 + " 关");
		} else if (msg1.equalsIgnoreCase("10")) {
			sendMessage("最后一关 [" + getBossName(npcid) + "] 请努力撑下去，20分钟后结束BOSS馆");
		} else {
			sendMessage("第 " + msg1 + " 关 [" + getBossName(npcid) + "] 2分后开始第 " + msg2 + " 关");
		}
		for (int i = 0; i < 10 ; i++) {
			spawn(npcid);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void spawn(int npcid) {
		L1NpcInstance npc = NpcTable.getInstance().newNpcInstance(npcid);
		npc.setId(IdFactory.getInstance().nextId());
		npc.setMap((short) 5065);
		npc.setX(32767);
		npc.setY(32766);

		npc.setHomeX(npc.getX());
		npc.setHomeY(npc.getY());
		npc.setHeading(4);
		
		L1World.getInstance().storeObject(npc);
		L1World.getInstance().addVisibleObject(npc);
		npc.turnOnOffLight();
		npc.startChat(L1NpcInstance.CHAT_TIMING_APPEARANCE);
		
	}

	private String getBossName(int npcId) {
		String BossName = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("select name from npc where npcid = ?");
			pstm.setInt(1, npcId);
			rs = pstm.executeQuery();
			if (rs.next()) {
				BossName = rs.getString("name");
			}
		} catch (SQLException e) {
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return BossName;
	}
	private void sendMessage(String msg) {
		for (L1PcInstance pc : getMembersArray()) {
			if (pc.getMapId() == 5065) {
				pc.sendPackets(new S_BlueMessage(166, "\\f3" + msg)); // 夜小空 创意广播
			}
		}
	}
	private int _BossRoomStatus = STATUS_NONE;
	private void setBossRoomStatus(int i) {
		_BossRoomStatus = i;
	}
	private int getBossRoomStatus() {
		return _BossRoomStatus;
	}
	private void clearMembers() {
		_members.clear();
	}
	private boolean isMember(L1PcInstance pc) {
		return _members.contains(pc);
	}
	private L1PcInstance[] getMembersArray() {
		return _members.toArray(new L1PcInstance[_members.size()]);
	}
	private int getMembersCount() {
		return _members.size();
	}
}