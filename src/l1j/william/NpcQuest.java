package l1j.william;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.sql.*;

import l1j.server.L1DatabaseFactory;
import l1j.server.Server;
import l1j.server.server.datatables.ItemTable;
import l1j.server.server.model.L1Inventory;
import l1j.server.server.model.L1Quest;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_NPCTalkReturn;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.templates.L1Item;


import l1j.william.L1WilliamSystemMessage;

public class NpcQuest {
	private static ArrayList aData = new ArrayList();
	private static boolean NO_GET_DATA = false;
	public static final String TOKEN = ",";

	public static void main(String a[]) {
		while(true) {
			try {
			Server.main(null);
			} catch(Exception ex) {
			}
		}
	}

	public static boolean forNpcQuest(String s, L1PcInstance pc, L1NpcInstance npc, int npcid, int oid) {
		ArrayList aTempData = null;
		if(!NO_GET_DATA) {
			NO_GET_DATA = true;
			getData();
		}

		L1Quest quest = pc.getQuest();

		int class_id = 0;
		if (pc.isCrown()) {
			class_id = 1;
		} else if (pc.isKnight()) {
			class_id = 2;
		} else if (pc.isWizard()) {
			class_id = 3;
		} else if (pc.isElf()) {
			class_id = 4;
		} else if (pc.isDarkelf()) {
			class_id = 5;
		} else if (pc.isDragonKnight()) { // 龙骑士 sosodemon add
			class_id = 6;
		} else if (pc.isIllusionist()) { // 幻术师 sosodemon add
			class_id = 7;
		}
		
		for (int i=0;i < aData.size(); i++) {
			aTempData = (ArrayList) aData.get(i);
			if ((aTempData.get(0) != null && ((Integer)aTempData.get(0)).intValue() == npcid) // NPC编号
				&& ((String) aTempData.get(1)).equals(s) // 确认选项
				&& ((((Integer)aTempData.get(2)).intValue() != 0 && pc.getLevel() >= ((Integer)aTempData.get(2)).intValue()) || ((Integer)aTempData.get(2)).intValue() == 0) // 确认等级
				&& ((((Integer)aTempData.get(3)).intValue() != 0 && ((Integer)aTempData.get(3)).intValue() == class_id) || ((Integer)aTempData.get(3)).intValue() == 0) // 确认职业
				&& ((((Integer)aTempData.get(4)).intValue() != 0 && pc.getTempCharGfx() == ((Integer)aTempData.get(4)).intValue()) || ((Integer)aTempData.get(4)).intValue() == 0) // 确认变身
				&& ((((Integer)aTempData.get(5)).intValue() != 0 && quest.get_step(((Integer)aTempData.get(5)).intValue()) == ((Integer)aTempData.get(6)).intValue()) || ((Integer)aTempData.get(5)).intValue() == 0) // 确认任务流程
				&& ((((Integer)aTempData.get(7)).intValue() != 0 && quest.get_step(((Integer)aTempData.get(7)).intValue()) != ((Integer)aTempData.get(8)).intValue()) || ((Integer)aTempData.get(7)).intValue() == 0) // 确认任务流程
				&& (((int[]) aTempData.get(11) != null && (int[]) aTempData.get(12) != null && !pc.getInventory().checkItem((int[]) aTempData.get(11), (int[]) aTempData.get(12))) || (int[]) aTempData.get(11) == null && (int[]) aTempData.get(12) == null)
				&& (((int[]) aTempData.get(9) != null && (int[]) aTempData.get(10) != null && pc.getInventory().checkItem((int[]) aTempData.get(9), (int[]) aTempData.get(10))) || (int[]) aTempData.get(9) == null && (int[]) aTempData.get(10) == null)
				) {

				boolean isGet = false;
				boolean isCreate = true;

				// 检查身上的物品
				if ((int[]) aTempData.get(13) != null && (int[]) aTempData.get(14) != null) {

					int[] materials = (int[]) aTempData.get(13);
					int[] counts = (int[]) aTempData.get(14);

					for (int j = 0; j < materials.length; j++) {
						if (!pc.getInventory().checkItem(materials[j], counts[j])) {
							L1Item temp = ItemTable.getInstance().getTemplate(materials[j]);
							pc.sendPackets(new S_ServerMessage(337, temp.getName() + "(" + (counts[j] - pc.getInventory().countItems(temp.getItemId()))+ ")")); // \f1%0が不足しています。
							isCreate = false;
						}
					}
					
					if (!isCreate) {
						if ((String[]) aTempData.get(23) != null) {
							pc.sendPackets(new S_NPCTalkReturn(oid, (String) aTempData.get(22), (String[])aTempData.get(23)));
						} else if (aTempData.get(22) != null) {
							pc.sendPackets(new S_NPCTalkReturn(oid, (String) aTempData.get(22)));
						}
						return true;
					}

					if (isCreate && ((Integer)aTempData.get(15)).intValue() == 0) { // 删除确认的道具、并给予任务道具
						// 删除道具
						for (int k = 0; k < materials.length; k++) {
							pc.getInventory().consumeItem(materials[k], counts[k]);
						}
					}
				}
				
				// 给予道具
				if (((Integer)aTempData.get(15)).intValue() == 0 
					&& (int[]) aTempData.get(16) != null && (int[]) aTempData.get(17) != null) {

					int[] giveMaterials = (int[]) aTempData.get(16);
					int[] giveCounts = (int[]) aTempData.get(17);

					for (int l = 0; l < giveMaterials.length; l++) {
						L1ItemInstance item = ItemTable.getInstance().createItem(giveMaterials[l]);

						if (item.isStackable()) {//可重叠
							item.setCount(giveCounts[l]);//数量
						} else {
							item.setCount(1);
						}

						if (item != null) {
							isGet = true;
							if (pc.getInventory().checkAddItem(item, (giveCounts[l])) == L1Inventory.OK) {
								pc.getInventory().storeItem(item);
							} else { // 持てない场合は地面に落とす 处理のキャンセルはしない（不正防止）
								L1World.getInstance().getInventory(pc.getX(), pc.getY(),
								pc.getMapId()).storeItem(item);
							}

							pc.sendPackets(new S_ServerMessage(143, npc.getNpcTemplate().get_name(),item.getLogName()));
						}
					}
				}

				// 纪录任务
				if (((Integer)aTempData.get(18)).intValue() != 0 && ((Integer)aTempData.get(19)).intValue() != 0 && (isGet || isCreate)) {
					pc.getQuest().set_step(((Integer) aTempData.get(18)).intValue(), ((Integer) aTempData.get(19)).intValue());
				}

				// 显示对话档
				if ((String[]) aTempData.get(21) != null && (isGet || isCreate)) {
					pc.sendPackets(new S_NPCTalkReturn(oid, (String) aTempData.get(20), (String[])aTempData.get(21)));
				return true;
				} else if (aTempData.get(20) != null && (isGet || isCreate)) {
					pc.sendPackets(new S_NPCTalkReturn(oid, (String) aTempData.get(20)));
				return true;
				} else {
					pc.sendPackets(new S_NPCTalkReturn(oid, ""));
					return true;
				}
				// 显示对话档 end
			}
		}
		return false;
	}

	private static void getData() {
		java.sql.Connection con = null;
		try { 
			con = L1DatabaseFactory.getInstance().getConnection(); 
			Statement stat = con.createStatement();
			ResultSet rset = stat.executeQuery("SELECT * FROM william_npc_quest");
			ArrayList aReturn = null;
			String sTemp = null;
			if (rset != null)
				while(rset.next()) {
				aReturn = new ArrayList();
				aReturn.add(0, new Integer(rset.getInt("npcid"))); // NPC编号
				sTemp = rset.getString("action");
             	aReturn.add(1,sTemp);
				aReturn.add(2, new Integer(rset.getInt("checkLevel"))); // 确认等级
				aReturn.add(3, new Integer(rset.getInt("checkClass"))); // 确认职业
				aReturn.add(4, new Integer(rset.getInt("checkPoly"))); // 确认变身
				aReturn.add(5, new Integer(rset.getInt("checkHaveQuestId"))); // 确认任务编号
				aReturn.add(6, new Integer(rset.getInt("checkHaveQuestOrder"))); // 确认任务流程
				aReturn.add(7, new Integer(rset.getInt("notHaveQuestId"))); // 确认任务编号
				aReturn.add(8, new Integer(rset.getInt("notHaveQuestOrder"))); // 确认任务流程

				if (rset.getString("checkItem") != null && !rset.getString("checkItem").equals("") && !rset.getString("checkItem").equals("0")) // 确认道具
					aReturn.add(9, getArray(rset.getString("checkItem"), TOKEN, 1));
				else 
					aReturn.add(9, null);

				if (rset.getString("checkItemCount") != null && !rset.getString("checkItemCount").equals("") && !rset.getString("checkItemCount").equals("0")) // 确认道具数量
					aReturn.add(10, getArray(rset.getString("checkItemCount"), TOKEN, 1));
				else 
					aReturn.add(10, null);

				if (rset.getString("notHaveItem") != null && !rset.getString("notHaveItem").equals("") && !rset.getString("notHaveItem").equals("0")) // 确认道具
					aReturn.add(11, getArray(rset.getString("notHaveItem"), TOKEN, 1));
				else 
					aReturn.add(11, null);

				if (rset.getString("notHaveItemCount") != null && !rset.getString("notHaveItemCount").equals("") && !rset.getString("notHaveItemCount").equals("0")) // 确认道具数量
					aReturn.add(12, getArray(rset.getString("notHaveItemCount"), TOKEN, 1));
				else 
					aReturn.add(12, null);

				if (rset.getString("material") != null && !rset.getString("material").equals("") && !rset.getString("material").equals("0")) // 确认道具
					aReturn.add(13, getArray(rset.getString("material"), TOKEN, 1));
				else 
					aReturn.add(13, null);

				if (rset.getString("materialCount") != null && !rset.getString("materialCount").equals("") && !rset.getString("materialCount").equals("0")) // 确认道具数量
					aReturn.add(14, getArray(rset.getString("materialCount"), TOKEN, 1));
				else 
					aReturn.add(14, null);

				aReturn.add(15, new Integer(rset.getInt("justCheckMaterial"))); // 只确认道具、不删除确认的道具

				if (rset.getString("GiveItem") != null && !rset.getString("GiveItem").equals("") && !rset.getString("GiveItem").equals("0")) // 给予道具
					aReturn.add(16, getArray(rset.getString("GiveItem"), TOKEN, 1));
				else 
					aReturn.add(16, null);

				if (rset.getString("GiveItemCount") != null && !rset.getString("GiveItemCount").equals("") && !rset.getString("GiveItemCount").equals("0")) // 给予道具数量
					aReturn.add(17, getArray(rset.getString("GiveItemCount"), TOKEN, 1));
				else 
					aReturn.add(17, null);

				aReturn.add(18, new Integer(rset.getInt("saveQuestId"))); // 纪录任务编号
				aReturn.add(19, new Integer(rset.getInt("saveQuestOrder"))); // 纪录任务流程

				if (rset.getString("ShowHtml") != null && !rset.getString("ShowHtml").equals(""))
					aReturn.add(20, new String(rset.getString("ShowHtml"))); // 显示对话档
				else 
					aReturn.add(20, null);

				if (rset.getString("ShowHtmlData") != null && !rset.getString("ShowHtmlData").equals(""))
					aReturn.add(21,getArray(rset.getString("ShowHtmlData"), TOKEN, 2)); // 显示对话内容
				else 
					aReturn.add(21, null);
					
				if (rset.getString("ShowNotHaveHtml") != null && !rset.getString("ShowNotHaveHtml").equals(""))
					aReturn.add(22, new String(rset.getString("ShowNotHaveHtml"))); // 显示对话档
				else 
					aReturn.add(22, null);

				if (rset.getString("ShowNotHaveHtmlData") != null && !rset.getString("ShowNotHaveHtmlData").equals(""))
					aReturn.add(23,getArray(rset.getString("ShowNotHaveHtmlData"), TOKEN, 2)); // 显示对话内容
				else 
					aReturn.add(23, null);

            	 aData.add(aReturn);
			}
			if(con != null && !con.isClosed())
				con.close();
		}
		catch(Exception ex){}
	}

	private static Object getArray(String s,String sToken,int iType) {
		StringTokenizer st = new StringTokenizer(s,sToken);
		int iSize = st.countTokens();
		String  sTemp = null;

		if(iType == 1) { // int
			int[] iReturn = new int[iSize];
			for(int i = 0;i < iSize; i++) {
				sTemp = st.nextToken();
				iReturn[i] = Integer.parseInt( sTemp );
			}
			return iReturn;
		}

		if(iType == 2) { // String
			String[] sReturn = new String[iSize];
			for(int i = 0; i < iSize; i++) {
				sTemp = st.nextToken();
				sReturn[i] = sTemp;
			}
			return sReturn;
		}

		if(iType == 3) { // String
			String sReturn = null;
			for(int i = 0;i < iSize; i++){
				sTemp = st.nextToken();
				sReturn = sTemp;
			}
			return sReturn;
		}
		return null;
	}
}