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
package l1j.server.server.command.executor;

import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SystemMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import l1j.server.server.utils.SQLUtil;
import l1j.server.server.datatables.ItemTable;
import l1j.server.server.templates.L1Item;
import l1j.server.L1DatabaseFactory;

public class L1ItemDrops implements L1CommandExecutor {
	private L1ItemDrops() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1ItemDrops();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		int dropID = 0;
		try {
			dropID = Integer.parseInt(arg);
		} catch (NumberFormatException e) {

		}
		if (dropID == 40308) {
			pc.sendPackets(new S_SystemMessage("金币(40308) 几乎都会掉落"));
		} else {
			Connection con = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			int[] mobID;
			int[] min;
			int[] max;
			double[] chance;
			String[] name;
			try {
				L1Item item = ItemTable.getInstance().getTemplate(dropID);
				String blessed;
				if (item.getBless() == 1) {
					blessed = "";
				} else if (item.getBless() == 0) {
					blessed = "\\fR";
				} else {
					blessed = "\\fY";
				}

				con = L1DatabaseFactory.getInstance().getConnection();
				pstm = con
						.prepareStatement("SELECT mobId,min,max,chance FROM droplist WHERE itemId=?");
				pstm.setInt(1, dropID);
				rs = pstm.executeQuery();
				rs.last();
				int rows = rs.getRow();
				mobID = new int[rows];
				min = new int[rows];
				max = new int[rows];
				chance = new double[rows];
				name = new String[rows];
				rs.beforeFirst();

				int i = 0;
				while (rs.next()) {
					mobID[i] = rs.getInt("mobId");
					min[i] = rs.getInt("min");
					max[i] = rs.getInt("max");
					chance[i] = rs.getInt("chance") / (double) 10000;
					i++;
				}
				rs.close();
				pstm.close();

				pc.sendPackets(new S_SystemMessage(blessed + item.getName()
						+ "(" + dropID + ") 掉落查询:"));
				for (int j = 0; j < mobID.length; j++) {
					pstm = con
							.prepareStatement("SELECT name FROM npc WHERE npcid=?");
					pstm.setInt(1, mobID[j]);
					rs = pstm.executeQuery();
					while (rs.next()) {
						name[j] = rs.getString("name");
					}
					rs.close();
					pstm.close();
					pc.sendPackets(new S_SystemMessage("数量: " + min[j] + "~"
							+ max[j] + " 怪物: " + mobID[j] + " " + name[j]
							+ " 机率: " + chance[j] + "%"));
				}
			} catch (Exception e) {
				pc.sendPackets(new S_SystemMessage("请输入 ." + cmdName
						+ " [物品编号]。"));
			} finally {
				SQLUtil.close(rs);
				SQLUtil.close(pstm);
				SQLUtil.close(con);
			}
		}
	}
}
