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
package l1j.server.server.model.item.action;

import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_NPCTalkReturn;

public class FilterItem {

	public static void useFilterItem(L1PcInstance pc, L1ItemInstance f_item,
			L1ItemInstance u_item) {
		if ((f_item == null) || (u_item == null)) {
			pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
			return;
		}
		if(u_item.getItemId() == 60014){ // 物品过滤器
			if(pc.checkFilterList(f_item.getItemId())){
				pc.deleteFilterList(f_item.getItemId());
				pc.sendPackets(new S_ServerMessage(166, f_item.getItem().getName()+" 已从物品过滤清单删除"));	
				String msg[] = { pc.getFilterList() };
				pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "filter_list", msg));
				return;
			}
			pc.addFilterList(f_item.getItemId());
			pc.sendPackets(new S_ServerMessage(166, f_item.getItem().getName()+" 已从物品过滤清单加入"));	
			String msg[] = { pc.getFilterList() };
			pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "filter_list", msg));
		}
	}
	
	public static void useFilterItemCleaner(L1PcInstance pc, L1ItemInstance u_item) {
		if (u_item == null) {
			pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
			return;
		}
		if(u_item.getItemId() == 60015){ // 物品过滤清单
			String msg[] = { pc.getFilterList() };
			pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "filter_list", msg));
		}
	}

}
