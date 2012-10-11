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
package l1j.plugin.codeShop;

import java.sql.SQLException;
import java.util.Random;

import l1j.server.server.datatables.ItemTable;
import l1j.server.server.model.L1Inventory;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_NPCTalkReturn;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.templates.L1Item;
import l1j.kinlinlo.L1Blend;
import l1j.kinlinlo.BlendTable;

public class CodeShop {

	private String _code;

	private int _item_id;

	private int _count;

	private int _enchantlvl;

	private int _attr_enchant_kind;

	private int _attr_enchant_level;
	
	private int _is_used;
	
	private String _use_character;

	public String get_code() {
		return _code;
	}

	public int get_item_id() {
		return _item_id;
	}

	public int get_count() {
		return _count;
	}

	public int get_enchantlvl() {
		return _enchantlvl;
	}

	public int get_attr_enchant_kind() {
		return _attr_enchant_kind;
	}

	public int get_attr_enchant_level() {
		return _attr_enchant_level;
	}

	public int get_is_used() {
		return _is_used;
	}

	public void set_is_used(int _is_used) {
		this._is_used = _is_used;
	}

	public String get_use_character() {
		return _use_character;
	}

	public void set_use_character(String _use_character) {
		this._use_character = _use_character;
	}


	public CodeShop(String code,int item_id,int count,int enchantlvl,int attr_enchant_kind,int attr_enchant_level,int is_used,String use_character) {
		_code = code;
		_item_id = item_id;
		_count = count;
		_enchantlvl = enchantlvl;
		_attr_enchant_kind = attr_enchant_kind;
		_attr_enchant_level = attr_enchant_level;
		_is_used = is_used;
		_use_character = use_character;
	}


	public static boolean useCode(L1PcInstance pc, String code) { // 判断有没有这个序列号
		CodeShop codeShop = CodeShopTable.loadCodeShopTable(code);
		if (codeShop != null) {
			if (codeShop.get_is_used() != 1) {
				giveItem(pc, code,codeShop);
				return true;
			}
		}
		return false;
	}

	public static void giveItem(L1PcInstance pc, String code,CodeShop codeShop) {
		int item_id = codeShop.get_item_id();
		int count = codeShop.get_count();
		int enchantLevel = codeShop.get_enchantlvl();
		int attr_enchant_kind = codeShop.get_attr_enchant_kind();
		int attr_enchant_level = codeShop.get_attr_enchant_level();
		String use_character = pc.getName();
		L1ItemInstance item = null;
		L1Item temp = ItemTable.getInstance().getTemplate(item_id);
		if (temp != null) {
			if (temp.isStackable()) {
				item = ItemTable.getInstance().createItem(item_id);
				item.setEnchantLevel(enchantLevel);
				item.setCount(count);
				if (pc.getInventory().checkAddItem(item, count) == L1Inventory.OK) {
					pc.getInventory().storeItem(item);
				}
			} else {
				int createCount;
				for (createCount = 0; createCount < count; createCount++) {
					item = ItemTable.getInstance().createItem(item_id);
					item.setEnchantLevel(enchantLevel);
					item.setAttrEnchantKind(attr_enchant_kind);
					item.setAttrEnchantLevel(attr_enchant_level);
					if (pc.getInventory().checkAddItem(item, 1) == L1Inventory.OK) {
						pc.getInventory().storeItem(item);
					}
				}				
			}
			CodeShopTable.updateCodeShopTable(code, use_character);
			String msg0 = temp.getName();
			String msg1 = count + "";
			String msg2 = enchantLevel + "";
			String msg3 = attr_enchant_kind + "";
			String msg4 = attr_enchant_level + "";
			String msg[] = { msg0, msg1, msg2, msg3, msg4 };
			pc.sendPackets(new S_NPCTalkReturn(pc.getId(), "codeShop", msg));
		}
	}
}