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


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.Config;
import l1j.server.server.ActionCodes;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.model.L1Clan;
import l1j.server.server.model.L1Inventory;
import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.L1War;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1MonsterInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_BlueMessage;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;


public class ZhuduiPK {
	/*
	 * boss馆 by 431200 游戏开始后 中途死亡离开boss馆 还可以再找NPC进入 游戏开始后 还没参加过的玩家不可进入
	 */ 
	private final ArrayList<L1PcInstance> _members = new ArrayList<L1PcInstance>();
	private static ZhuduiPK _instance;
	public static ZhuduiPK getInstance() {
		if (_instance == null) {
			_instance = new ZhuduiPK();
		}
		return _instance;
	}
	private static Logger _log = Logger.getLogger(ZhuduiPK.class.getName());
	
	private  static String enemyClanName = "";
	private  static String clanName = "";
 
	public static final int STATUS_NONE = 0; // 闲置
	public static final int STATUS_READY = 1; // 等待中
	public static final int STATUS_PLAYING = 2; // 游戏开始
	public static final int STATUS_CLEANING = 4; // 清洁中
	private static final int _readytime = Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(125)).intValue()* 1000; // 等待时间 80秒 + 倒数 = 总共90秒
	private static final int _cleartime = Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(126)).intValue() * 1000; // 清洁时间 五分钟

	private static final int _item = Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(117)).intValue(); // 扣除物品
	private static final int _adena = Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(118)).intValue(); // 扣除金币数量
	private static final int _itemOne = Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(119)).intValue(); // 血盟成员进入扣除物品
	private static final int _adenaOne = Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(120)).intValue(); // 血盟成员进入扣除金币数量
	
	private static final int _itemWin1 = Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(121)).intValue(); // 胜利奖励物品1
	private static final int _adenaWin1 = Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(122)).intValue(); // 胜利奖励物品1数量
	private static final int _itemWin2 = Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(123)).intValue(); // 胜利奖励物品2
	private static final int _adenaWin2 = Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(124)).intValue(); // 胜利奖励物品2数量
	
	public String enterZhuduiPK(L1PcInstance pc) {
		if (ZhuduiPK.getInstance().getZhuduiPKStatus() == ZhuduiPK.STATUS_CLEANING) {
			pc.sendPackets(new S_SystemMessage("血盟PK大赛正在筹备中。"));
			return "";
		}
		if (ZhuduiPK.getInstance().getZhuduiPKStatus() == ZhuduiPK.STATUS_PLAYING) {			
			pc.sendPackets(new S_ServerMessage(1182)); // 游戏已经开始了。
			if (!pc.getClan().getClanName().equals(enemyClanName) && !pc.getClan().getClanName().equals(clanName)) {
				pc.sendPackets(new S_SystemMessage("只有交战双方成员才可以进入。"));
			}else {//血盟成员直接进入
				pc.getInventory().consumeItem(_itemOne, _adenaOne);
				L1Teleport.teleport(pc, 32700, 32895, (short) 802, pc.getHeading(), true);
			}
			return "";
		}
		if (getMembersCount() >= 2) {
			pc.sendPackets(new S_SystemMessage("血盟PK大赛已经到达饱和的状态了。"));
			return "";
		}
		if(pc.getClassId() != 1 && pc.getClassId() != 0){//只有王子，公主才可以			
			pc.sendPackets(new S_SystemMessage("请让你们王子或者公主来报名。"));
			return "";
		}

		if (!pc.getInventory().checkItem(_item, _adena) && !isMember(pc)) {
			pc.sendPackets(new S_SystemMessage("请带够所需物品再说。")); // 金币不足
			return "";
		}
		L1Teleport.teleport(pc, 32700, 32895, (short) 802, pc.getHeading(), true);
		addMember(pc);
		return "";
	}

	public void callPartyPlayers(L1PcInstance pc) {
		// L1Party party = pc.getParty();
		L1Clan party = pc.getClan();
		if (party != null) {
			int x = pc.getX();
			int y = pc.getY() + 2;
			short map = pc.getMapId();
			L1PcInstance[] players = party.getOnlineClanMember();
			for (L1PcInstance pc2 : players) {
				try {
					if (!pc2.getParty().isLeader(pc2)) {
						L1Teleport.teleport(pc2, x, y, map, 5, true);
						pc2.sendPackets(new S_SystemMessage("您被传唤到大王身边。"));
					}
				} catch (Exception e) {
					_log.log(Level.SEVERE, "", e);
				}
			}
		}
	}
	private void addMember(L1PcInstance pc) {
		if (!_members.contains(pc)) {
			_members.add(pc);
			pc.getInventory().consumeItem(_item, _adena);
			callPartyPlayers(pc);
		}
		if (getMembersCount() == 1 && getZhuduiPKStatus() == STATUS_NONE) {
			enemyClanName = pc.getClanname();			
		}else {
			clanName = pc.getClanname();
			GeneralThreadPool.getInstance().execute(new runZhuduiPK());
		}
	}

	private void starWar() {
		boolean inWar = false;
		List<L1War> warList = L1World.getInstance().getWarList(); // 全战开启
		for (L1War war : warList) {
			inWar = war.CheckClanInSameWar(clanName, enemyClanName);
		}
		if (inWar == false) {
			L1War war = new L1War();
			war.handleCommands(3, clanName, enemyClanName); // 打架
		}
	}

	private class runZhuduiPK implements Runnable {
		public void run() {
			try {
				setZhuduiPKStatus(STATUS_READY);
				sendMessage((Config.readytime+10) + "秒后开始游戏");
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
					setZhuduiPKStatus(STATUS_PLAYING);
					starWar();
					while (true) {
						if(_members.get(0).isDead() || _members.get(0).getMapId() != 802){//第一个血盟失败
							_members.get(1).getInventory().storeItem(_itemWin1,_adenaWin1);
							_members.get(1).getInventory().storeItem(_itemWin2,_adenaWin2);
							ShowMeaagae showMeaagae = new ShowMeaagae();
							showMeaagae.broadcastToAll("血盟"+_members.get(1).getClanname()+"打赢了血盟"+_members.get(0).getClanname());
							break;
						}else if( _members.get(1).isDead() ||_members.get(1).getMapId() != 802){
							_members.get(0).getInventory().storeItem(_itemWin1,_adenaWin1);
							_members.get(0).getInventory().storeItem(_itemWin2,_adenaWin2);
							ShowMeaagae showMeaagae = new ShowMeaagae();
							showMeaagae.broadcastToAll("血盟"+_members.get(0).getClanname()+"打赢了血盟"+_members.get(1).getClanname());
							break;
						}
						Thread.sleep(1000);
					}
					endZhuduiPK();
				}
				Thread.sleep(_cleartime);
				setZhuduiPKStatus(STATUS_NONE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private boolean checkPlayerCount() {
		if (getMembersCount() < 1) {
			setZhuduiPKStatus(STATUS_CLEANING);
			sendMessage("血盟不足 2队，所以强制关闭游戏");
			for (L1PcInstance pc : getMembersArray()) {
				pc.getInventory().storeItem(_item, _adena);
				pc.sendPackets(new S_SystemMessage("血盟PK大赛退还元宝(" + _adena + ")。"));
			}
			clearMembers();
			return false;
		}
		return true;
	}
	private void endZhuduiPK() {
		setZhuduiPKStatus(STATUS_CLEANING);
		sendMessage("血盟PK大赛已经结束，请下次再来");
		clearMembers();
	}
	private void clearColosseum() {
		for (Object obj : L1World.getInstance().getVisibleObjects(802).values()) {
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
			} else if (obj instanceof L1PcInstance){
				L1PcInstance l1PcInstance = (L1PcInstance) obj;
				L1Teleport.teleport(l1PcInstance, 32581, 32929, (short) 0, l1PcInstance.getHeading(), true);
			}
		}
	}

	private void sendMessage(String msg) {
		for (L1PcInstance pc : getMembersArray()) {
			if (pc.getMapId() == 802) {
				pc.sendPackets(new S_BlueMessage(166, "\\f3" + msg)); // 夜小空 创意广播
			}
		}
	}
	private int _ZhuduiPKStatus = STATUS_NONE;
	private void setZhuduiPKStatus(int i) {
		_ZhuduiPKStatus = i;
	}
	private int getZhuduiPKStatus() {
		return _ZhuduiPKStatus;
	}
	private void clearMembers() {
		_members.clear();
		clearColosseum();
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