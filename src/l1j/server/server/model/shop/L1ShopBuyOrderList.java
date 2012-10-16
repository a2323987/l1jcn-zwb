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
package l1j.server.server.model.shop;

import java.util.List;

import l1j.server.Config;
import l1j.server.server.datatables.ShopTable;
import l1j.server.server.model.L1TaxCalculator;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.templates.L1ShopItem;
import l1j.server.server.utils.collections.Lists;
import java.sql.Timestamp; // 道具天数删除系统

class L1ShopBuyOrder {
	private final L1ShopItem _item;

	private final int _count;

	private final int _deleteDay; // 道具天数删除系统(指定天数)

	private final Timestamp _deleteDate; // 道具天数删除系统(指定日期)
	
	private final int _enchantlevel; // 道具加成系统

	private int _selling_count; // 已经出售数量系统
	private final int _selling_max; // 最多出售数量系统

	public L1ShopBuyOrder(L1ShopItem item, int count, int deleteDay, Timestamp deleteDate,int enchantlevel,int selling_count,int selling_max) { // 道具天数删除系统
		_item = item;
		_count = count;
		_deleteDay = deleteDay; // 道具天数删除系统(指定天数)
		_deleteDate = deleteDate; // 道具天数删除系统(指定日期)
		_enchantlevel = enchantlevel;//道具加成系统
		_selling_count = selling_count; // 已经出售数量系统
		_selling_max = selling_max; // 最多出售数量系统
	}
	
	public int get_selling_count() {
		return _selling_count;
	}

	public void set_selling_count(int _selling_count) {
		this._selling_count = _selling_count;
	}

	public int get_selling_max() {
		return _selling_max;
	}

	public int get_enchantlevel() {
		return _enchantlevel;
	}
	
	public L1ShopItem getItem() {
		return _item;
	}

	public int getCount() {
		return _count;
	}
	// 道具天数删除系统(指定天数)
	public int getDeleteDay() {
		return _deleteDay;
	}

	// 道具天数删除系统(指定日期)
	public Timestamp getDeleteDate() {
		return _deleteDate;
	}
}

public class L1ShopBuyOrderList {
	private final L1Shop _shop;

	private final List<L1ShopBuyOrder> _list = Lists.newList();

	private final L1TaxCalculator _taxCalc;

	private int _totalWeight = 0;

	private int _totalPrice = 0;

	private int _totalPriceTaxIncluded = 0;

	L1ShopBuyOrderList(L1Shop shop) {
		_shop = shop;
		_taxCalc = new L1TaxCalculator(shop.getNpcId());
	}

	public void add(int orderNumber, int count) {
		if (_shop.getSellingItems().size() < orderNumber) {
			return;
		}
		L1ShopItem shopItem = _shop.getSellingItems().get(orderNumber);

		int price = (int) (shopItem.getPrice() * Config.RATE_SHOP_SELLING_PRICE);
		// オーバーフローチェック

		int deleteDay =  shopItem.getDeleteDay(); // 道具天数删除系统(指定天数)
		Timestamp deleteDate =  shopItem.getDeleteDate(); // 道具天数删除系统(指定日期)
		int enchantlevel = shopItem.getEnchantLevel();
		int selling_count = ShopTable.getInstance().getSelling_count(_shop.getNpcId(),shopItem.getItemId());// 商店已经出售了多少件物品
		int selling_max = shopItem.get_selling_max();		
		if(selling_count + count > selling_max && selling_max !=0){	
			return;
		}
		for (int j = 0; j < count; j++) {
			if (price * j < 0) {
				return;
			}
		}
		if (_totalPrice < 0) {
			return;
		}
		_totalPrice += price * count;
		_totalPriceTaxIncluded += _taxCalc.layTax(price) * count;
		_totalWeight += shopItem.getItem().getWeight() * count * shopItem.getPackCount();

		if (shopItem.getItem().isStackable()) {
			_list.add(new L1ShopBuyOrder(shopItem, count * shopItem.getPackCount(), deleteDay, deleteDate,enchantlevel,selling_count,selling_max)); // 道具天数删除系统
			return;
		}

		for (int i = 0; i < (count * shopItem.getPackCount()); i++) {
			_list.add(new L1ShopBuyOrder(shopItem, 1, deleteDay, deleteDate,enchantlevel,selling_count,selling_max)); // 道具天数删除系统
		}
	}

	List<L1ShopBuyOrder> getList() {
		return _list;
	}

	public int getTotalWeight() {
		return _totalWeight;
	}

	public int getTotalPrice() {
		return _totalPrice;
	}

	public int getTotalPriceTaxIncluded() {
		return _totalPriceTaxIncluded;
	}

	L1TaxCalculator getTaxCalculator() {
		return _taxCalc;
	}
}
