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

import l1j.server.server.GeneralThreadPool;
import l1j.server.server.model.L1PinkName;
import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_PinkName;
import l1j.server.server.serverpackets.S_SystemMessage;


public class PartyPK {
	/*
	 * 
	 */ 
	private static final int _item = Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(147)).intValue(); // 扣除物品
	private static final int _count = Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(148)).intValue(); // 扣除金币数量

	private static final int _itemWin1 = Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(150)).intValue(); // 胜利奖励物品1
	private static final int _countWin1 = Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(151)).intValue(); // 胜利奖励物品1数量

	public static final int _menberCount = Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(149)).intValue(); // 队员数量

	private final ArrayList<L1PcInstance> _members = new ArrayList<L1PcInstance>();
	private static PartyPK _instance;
	public static PartyPK getInstance() {
		if (_instance == null) {
			_instance = new PartyPK();
		}
		return _instance;
	}
	public String enterPartyPK(L1PcInstance pc) {		
		if(!pc.isInParty()){
			pc.sendPackets(new S_SystemMessage("请先组队，再让队长来报名。"));
			return "";
		}
		if(!pc.getParty().isLeader(pc)){//只有队长才可以来报名		
			pc.sendPackets(new S_SystemMessage("请让队长来报名。"));
			return "";
		}
		if(pc.getParty().getMembers().length != _menberCount){//只有规定人数才可以参加		
			pc.sendPackets(new S_SystemMessage("队员人数必须是"+_menberCount+"名。"));
			return "";
		}
		callPartyPlayers(pc,32766,32838,(short)5113,true);
		return "";
	}

	public void rewardPartyPlayers(L1PcInstance pc) {
		L1PcInstance[] players = pc.getParty().getMembers();
		for (L1PcInstance pc1 : players) {
			pc1.getInventory().storeItem(_itemWin1, _countWin1);
			pc1.sendPackets(new S_SystemMessage("恭喜您获得了组队PK奖励，请查看背包。"));
		}
	}

	public void callPartyPlayers(L1PcInstance pc, int locx, int locy, short mapid, boolean checkItem) {
		L1PcInstance[] players = pc.getParty().getMembers();
		if (players != null) {
			for (L1PcInstance pc1 : players) {
				if (!pc1.getInventory().checkItem(_item, _count)) {
					for (L1PcInstance pc3 : players) {
						pc3.sendPackets(new S_SystemMessage("请所有队员带够所需物品再说。")); // 金币不足
					}
					return;
				}
			}
			for (L1PcInstance pc2 : players) {
				L1Teleport.teleport(pc2, locx, locy, mapid, 5, true);
				pc2.getInventory().consumeItem(_item, _count);
				pc2.sendPackets(new S_SystemMessage("您被传唤到指定的地点。"));
				L1PinkName.onActionAll(pc2);
			}
		}
	}	
}