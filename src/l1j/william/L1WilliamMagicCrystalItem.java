/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package l1j.william;

import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import l1j.server.server.datatables.ItemTable;
import l1j.server.server.model.L1Inventory;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.templates.L1EtcItem;

import l1j.william.MagicCrystalItem;

public class L1WilliamMagicCrystalItem {

	private static Logger _log = Logger
			.getLogger(L1WilliamMagicCrystalItem.class.getName());

	private int _itemId;

	private int _min_count;

	private int _max_count;

	public L1WilliamMagicCrystalItem(int itemId, int min_count, int max_count) {
		_itemId = itemId;
		_min_count = min_count;
		_max_count = max_count;
	}

	public int getItemId() {
		return _itemId;
	}

	public int getMinCount() {
		return _min_count;
	}

	public int getMaxCount() {
		return _max_count;
	}


	public static int checkItemId(int itemId) {
		L1WilliamMagicCrystalItem MagicCrystal_Item = MagicCrystalItem.getInstance().getTemplate(itemId);

		if (MagicCrystal_Item == null) {
			return 0;
		}
		
		int item_id = MagicCrystal_Item.getMinCount();
		return item_id;
	}
	
	public static int getMinCount(int itemId) {
		L1WilliamMagicCrystalItem MagicCrystal_Item = MagicCrystalItem.getInstance().getTemplate(itemId);

		if (MagicCrystal_Item == null) {
			return 0;
		}
		
		int MinCount = MagicCrystal_Item.getMinCount();
		return MinCount;
	}
	
	public static int getMaxCount(int itemId) {
		L1WilliamMagicCrystalItem MagicCrystal_Item = MagicCrystalItem.getInstance().getTemplate(itemId);

		if (MagicCrystal_Item == null) {
			return 0;
		}
		
		int MaxCount = MagicCrystal_Item.getMaxCount();
		return MaxCount;
	}
}
