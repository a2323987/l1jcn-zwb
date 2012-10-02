package l1j.william;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.sql.*;

import l1j.server.L1DatabaseFactory;
import l1j.server.Server;
import l1j.server.server.model.L1Quest;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_NPCTalkReturn;

public class NpcAction {
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

	public static boolean forNpcAction(L1PcInstance pc, int npcid, int oid) {
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
		
		for(int i=0;i < aData.size(); i++) {
			aTempData = (ArrayList) aData.get(i);
			if((aTempData.get(0) != null && ((Integer)aTempData.get(0)).intValue() == npcid) // NPC编号
				&& (pc.getLevel() == ((Integer)aTempData.get(1)).intValue() || ((Integer)aTempData.get(1)).intValue() == 0) // 等级
				&& (((Integer)aTempData.get(2)).intValue() != 0 && ((Integer)aTempData.get(2)).intValue() == class_id || ((Integer)aTempData.get(2)).intValue() == 0) // 职业
				&& (((Integer)aTempData.get(3)).intValue() != 0 && pc.getTempCharGfx() == ((Integer)aTempData.get(3)).intValue() || ((Integer)aTempData.get(3)).intValue() == 0) // 变身
				&& (((Integer)aTempData.get(4)).intValue() != 0 && ((Integer)aTempData.get(5)).intValue() == quest.get_step(((Integer)aTempData.get(4)).intValue()) || ((Integer)aTempData.get(4)).intValue() == 0) // 任务编号
				&& ((int[]) aTempData.get(6) != null && (int[]) aTempData.get(7) != null && pc.getInventory().checkItem((int[]) aTempData.get(6), (int[]) aTempData.get(7)) || (int[]) aTempData.get(6) == null && (int[]) aTempData.get(7) == null)
				&& ((int[]) aTempData.get(8) != null && (int[]) aTempData.get(9) != null && !pc.getInventory().checkItem((int[]) aTempData.get(8), (int[]) aTempData.get(9)) || (int[]) aTempData.get(8) == null && (int[]) aTempData.get(9) == null)) {

				// 显示对话档
				if ((String[]) aTempData.get(11) != null) {
					pc.sendPackets(new S_NPCTalkReturn(oid, (String) aTempData.get(10), (String[])aTempData.get(11)));
					return true;
				} else {
					pc.sendPackets(new S_NPCTalkReturn(oid, (String) aTempData.get(10)));
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
			ResultSet rset = stat.executeQuery("SELECT * FROM william_npc_action");
			ArrayList aReturn = null;
			String sTemp = null;
			if (rset != null)
				while(rset.next()) {
				aReturn = new ArrayList();
				aReturn.add(0, new Integer(rset.getInt("npcid"))); // NPC编号
				aReturn.add(1, new Integer(rset.getInt("checkLevel"))); // 确认等级
				aReturn.add(2, new Integer(rset.getInt("checkClass"))); // 确认职业
				aReturn.add(3, new Integer(rset.getInt("checkPoly"))); // 确认变身
				aReturn.add(4, new Integer(rset.getInt("checkQuestId"))); // 确认任务编号
				aReturn.add(5, new Integer(rset.getInt("checkQuestOrder"))); // 确认任务流程

				if (rset.getString("checkItem") != null && !rset.getString("checkItem").equals("")) // 确认道具
					aReturn.add(6, getArray(rset.getString("checkItem"), TOKEN, 1));
				else 
					aReturn.add(6, null);

				if (rset.getString("checkItemCount") != null && !rset.getString("checkItemCount").equals("")) // 确认道具数量
					aReturn.add(7, getArray(rset.getString("checkItemCount"), TOKEN, 1));
				else 
					aReturn.add(7, null);

				if (rset.getString("notHaveItem") != null && !rset.getString("notHaveItem").equals("")) // 确认道具
					aReturn.add(8, getArray(rset.getString("notHaveItem"), TOKEN, 1));
				else 
					aReturn.add(8, null);

				if (rset.getString("notHaveItemCount") != null && !rset.getString("notHaveItemCount").equals("")) // 确认道具数量
					aReturn.add(9, getArray(rset.getString("notHaveItemCount"), TOKEN, 1));
				else 
					aReturn.add(9, null);

				if (rset.getString("ShowHtml") != null && !rset.getString("ShowHtml").equals(""))
					aReturn.add(10, new String(rset.getString("ShowHtml"))); // 显示对话档
				else 
					aReturn.add(10, null);
				if (rset.getString("ShowHtmlData") != null && !rset.getString("ShowHtmlData").equals(""))
					aReturn.add(11,getArray(rset.getString("ShowHtmlData"), TOKEN, 2)); // 显示对话内容
				else 
					aReturn.add(11, null);

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