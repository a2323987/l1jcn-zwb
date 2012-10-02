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
import java.sql.Timestamp; // 道具天数删除系统

import l1j.server.Config;
import l1j.server.server.datatables.CastleTable;
import l1j.server.server.datatables.ItemTable;
import l1j.server.server.datatables.TownTable;
import l1j.server.server.model.L1CastleLocation;
import l1j.server.server.model.L1PcInventory;
import l1j.server.server.model.L1TaxCalculator;
import l1j.server.server.model.L1TownLocation;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.game.L1BugBearRace;
import l1j.server.server.model.identity.L1ItemId;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.templates.L1Castle;
import l1j.server.server.templates.L1Item;
import l1j.server.server.templates.L1ShopItem;
import l1j.server.server.utils.IntRange;
import l1j.server.server.utils.Random;
import l1j.server.server.utils.collections.Lists;

public class L1Shop {
	private final int _npcId;

	private final List<L1ShopItem> _sellingItems;

	private final List<L1ShopItem> _purchasingItems;

	public L1Shop(int npcId, List<L1ShopItem> sellingItems,
			List<L1ShopItem> purchasingItems) {
		if ((sellingItems == null) || (purchasingItems == null)) {
			throw new NullPointerException();
		}

		_npcId = npcId;
		_sellingItems = sellingItems;
		_purchasingItems = purchasingItems;
	}

	public int getNpcId() {
		return _npcId;
	}

	public List<L1ShopItem> getSellingItems() {
		return _sellingItems;
	}

	/**
	 * この商店で、指定されたアイテムが买取可能な状态であるかを返す。
	 * 
	 * @param item
	 * @return アイテムが买取可能であればtrue
	 */
	private boolean isPurchaseableItem(L1ItemInstance item) {
		if (item == null) {
			return false;
		}
		if (item.isEquipped()) { // 装备中であれば不可
			return false;
		}
		if (item.getEnchantLevel() != 0) { // 强化(or弱化)されていれば不可
			return false;
		}
		if (item.getBless() >= 128) { // 封印的装备
			return false;
		}

		return true;
	}

	private L1ShopItem getPurchasingItem(int itemId) {
		for (L1ShopItem shopItem : _purchasingItems) {
			if (shopItem.getItemId() == itemId) {
				return shopItem;
			}
		}
		return null;
	}

	public L1AssessedItem assessItem(L1ItemInstance item) {
		L1ShopItem shopItem = getPurchasingItem(item.getItemId());
		if (shopItem == null) {
			return null;
		}
		return new L1AssessedItem(item.getId(), getAssessedPrice(shopItem));
	}

	private int getAssessedPrice(L1ShopItem item) {
		return (int) (item.getPrice() * Config.RATE_SHOP_PURCHASING_PRICE / item
				.getPackCount());
	}

	/**
	 * インベントリ内の买取可能アイテムを查定する。
	 * 
	 * @param inv
	 *            查定对象のインベントリ
	 * @return 查定された买取可能アイテムのリスト
	 */
	public List<L1AssessedItem> assessItems(L1PcInventory inv) {
		List<L1AssessedItem> result = Lists.newList();
		for (L1ShopItem item : _purchasingItems) {
			for (L1ItemInstance targetItem : inv.findItemsId(item.getItemId())) {
				if (!isPurchaseableItem(targetItem)) {
					continue;
				}

				result.add(new L1AssessedItem(targetItem.getId(),
						getAssessedPrice(item)));
			}
		}
		return result;
	}

	/**
	 * プレイヤーへアイテムを贩卖できることを保证する。
	 * 
	 * @return 何らかの理由でアイテムを贩卖できない场合、false
	 */
	private boolean ensureSell(L1PcInstance pc, L1ShopBuyOrderList orderList) {
		// int price = orderList.getTotalPriceTaxIncluded();

		// GASH币商城[提供:liumy]
		int price;
		if (_npcId >= Config.GASH_SHOP_MIN_ID
				&& _npcId <= Config.GASH_SHOP_MAX_ID) {
			price = orderList.getTotalPrice(); // 无税收
		} else {
			price = orderList.getTotalPriceTaxIncluded();
		}
		// end
		// オーバーフローチェック
		if (!IntRange.includes(price, 0, 2000000000)) {
			// 总贩卖価格は%dアデナを超过できません。
			pc.sendPackets(new S_ServerMessage(904, "2000000000"));
			return false;
		}
		// 购入できるかチェック
		// GASH币商城[提供:liumy]
		int howpay;
		if (_npcId >= Config.GASH_SHOP_MIN_ID
				&& _npcId <= Config.GASH_SHOP_MAX_ID) {
			howpay = Config.GASH_SHOP_ITEM_ID; // GASH币
		} else {
			howpay = L1ItemId.ADENA; // 金币
		}
		// end
		if (!pc.getInventory().checkItem(howpay, price)) { // GASH币商城[提供:liumy]
			System.out.println(price);
			// \f1アデナが不足しています。
			// pc.sendPackets(new S_ServerMessage(189));
			// GASH币商城[提供:liumy]
			if (howpay == L1ItemId.ADENA) {
				pc.sendPackets(new S_ServerMessage(189));
			} else {
				pc.sendPackets(new S_ServerMessage(166, "商城专用货币不足"));
			}
			// end
			return false;
		}
		// 重量チェック
		int currentWeight = pc.getInventory().getWeight() * 1000;
		if (currentWeight + orderList.getTotalWeight() > pc.getMaxWeight() * 1000) {
			// アイテムが重すぎて、これ以上持てません。
			pc.sendPackets(new S_ServerMessage(82));
			return false;
		}
		// 个数チェック
		int totalCount = pc.getInventory().getSize();
		for (L1ShopBuyOrder order : orderList.getList()) {
			L1Item temp = order.getItem().getItem();
			if (temp.isStackable()) {
				if (!pc.getInventory().checkItem(temp.getItemId())) {
					totalCount += 1;
				}
			} else {
				totalCount += 1;
			}
		}
		if (totalCount > 180) {
			// \f1一人のキャラクターが持って步けるアイテムは最大180个までです。
			pc.sendPackets(new S_ServerMessage(263));
			return false;
		}
		return true;
	}

	/**
	 * 地域税纳税处理 アデン城・ディアド要塞を除く城はアデン城へ国税として10%纳税する
	 * 
	 * @param orderList
	 */
	private void payCastleTax(L1ShopBuyOrderList orderList) {
		L1TaxCalculator calc = orderList.getTaxCalculator();

		int price = orderList.getTotalPrice();

		int castleId = L1CastleLocation.getCastleIdByNpcid(_npcId);
		int castleTax = calc.calcCastleTaxPrice(price);
		int nationalTax = calc.calcNationalTaxPrice(price);
		// アデン城・ディアド城の场合は国税なし
		if ((castleId == L1CastleLocation.ADEN_CASTLE_ID)
				|| (castleId == L1CastleLocation.DIAD_CASTLE_ID)) {
			castleTax += nationalTax;
			nationalTax = 0;
		}

		if ((castleId != 0) && (castleTax > 0)) {
			L1Castle castle = CastleTable.getInstance()
					.getCastleTable(castleId);

			synchronized (castle) {
				int money = castle.getPublicMoney();
				if (2000000000 > money) {
					money = money + castleTax;
					castle.setPublicMoney(money);
					CastleTable.getInstance().updateCastle(castle);
				}
			}

			if (nationalTax > 0) {
				L1Castle aden = CastleTable.getInstance().getCastleTable(
						L1CastleLocation.ADEN_CASTLE_ID);
				synchronized (aden) {
					int money = aden.getPublicMoney();
					if (2000000000 > money) {
						money = money + nationalTax;
						aden.setPublicMoney(money);
						CastleTable.getInstance().updateCastle(aden);
					}
				}
			}
		}
	}

	/**
	 * ディアド税纳税处理 战争税の10%がディアド要塞の公金となる。
	 * 
	 * @param orderList
	 */
	private void payDiadTax(L1ShopBuyOrderList orderList) {
		L1TaxCalculator calc = orderList.getTaxCalculator();

		int price = orderList.getTotalPrice();

		// ディアド税
		int diadTax = calc.calcDiadTaxPrice(price);
		if (diadTax <= 0) {
			return;
		}

		L1Castle castle = CastleTable.getInstance().getCastleTable(
				L1CastleLocation.DIAD_CASTLE_ID);
		synchronized (castle) {
			int money = castle.getPublicMoney();
			if (2000000000 > money) {
				money = money + diadTax;
				castle.setPublicMoney(money);
				CastleTable.getInstance().updateCastle(castle);
			}
		}
	}

	/**
	 * 町税纳税处理
	 * 
	 * @param orderList
	 */
	private void payTownTax(L1ShopBuyOrderList orderList) {
		int price = orderList.getTotalPrice();

		// 町の卖上
		if (!L1World.getInstance().isProcessingContributionTotal()) {
			int town_id = L1TownLocation.getTownIdByNpcid(_npcId);
			if ((town_id >= 1) && (town_id <= 10)) {
				TownTable.getInstance().addSalesMoney(town_id, price);
			}
		}
	}

	// XXX 纳税处理はこのクラスの责务では无い气がするが、とりあえず
	private void payTax(L1ShopBuyOrderList orderList) {
		payCastleTax(orderList);
		payTownTax(orderList);
		payDiadTax(orderList);
	}

	/**
	 * 贩卖取引
	 */
	//private void sellItems(L1PcInventory inv, L1ShopBuyOrderList orderList) {
	private void sellItems(L1PcInstance pc, L1PcInventory inv, L1ShopBuyOrderList orderList) { // 增加 L1PcInstance pc,
		/*
		 * if (!inv.consumeItem(L1ItemId.ADENA,
		 * orderList.getTotalPriceTaxIncluded())) { throw new
		 * IllegalStateException("不能消费为购买需要的金币。"); }
		 */
		// GASH币商城[提供:liumy]
		int howpay;
		if (_npcId >= Config.GASH_SHOP_MIN_ID
				&& _npcId <= Config.GASH_SHOP_MAX_ID) {
			howpay = Config.GASH_SHOP_ITEM_ID; // GASH币
		} else {
			howpay = L1ItemId.ADENA; // 金币
		}
		if (howpay == L1ItemId.ADENA) {
			if (!inv.consumeItem(howpay, orderList.getTotalPriceTaxIncluded())) {
				throw new IllegalStateException("不能消费为购买需要的金币。");
			}
		} else {
			if (!inv.consumeItem(howpay, orderList.getTotalPrice())) {
				throw new IllegalStateException("不能消费为购买需要的商城专用货币。");
			}
		}
		// end
		for (L1ShopBuyOrder order : orderList.getList()) {
			int itemId = order.getItem().getItemId();
			int amount = order.getCount();
			int EnchantLevel= order.getItem().getEnchantLevel();//商店装备+几系统
			L1ItemInstance item = ItemTable.getInstance().createItem(itemId);
			// 道具天数删除系统
			int deleteDay = order.getDeleteDay(); // 道具天数删除系统(指定天数)
			Timestamp deleteDate = order.getDeleteDate(); // 道具天数删除系统(指定日期)
			// 道具天数删除系统
			if (deleteDay > 0) { // ● 指定天数
				Timestamp delDay = new Timestamp(
						System.currentTimeMillis()
								+ (86400000 * deleteDay));
				item.setDeleteDate(delDay);
			} else if (deleteDate != null) { // ● 指定日期
				item.setDeleteDate(deleteDate);
			}
			if (item.getDeleteDate() != null) {
				pc.sendPackets(new S_ServerMessage(166, item.getName() + " (" + amount + ") 使用期限:" + item.getDeleteDate()));
			}
			// end
			if (item.getItemId() == 40309) {// Race Tickets
				item.setItem(order.getItem().getItem());
				L1BugBearRace.getInstance().setAllBet(
						L1BugBearRace.getInstance().getAllBet()
								+ (amount * order.getItem().getPrice()));
				String[] runNum = item.getItem().getIdentifiedNameId()
						.split("-");
				int trueNum = 0;
				for (int i = 0; i < 5; i++) {
					if (L1BugBearRace.getInstance().getRunner(i).getNpcId() - 91350 == (Integer
							.parseInt(runNum[runNum.length - 1]) - 1)) {
						trueNum = i;
						break;
					}
				}
				L1BugBearRace.getInstance().setBetCount(
						trueNum,
						L1BugBearRace.getInstance().getBetCount(trueNum)
								+ amount);
			}
			item.setCount(amount);
			item.setIdentified(true);
			item.setEnchantLevel(EnchantLevel);//商店+几系统
			inv.storeItem(item);
			if ((_npcId == 70068) || (_npcId == 70020) || (_npcId == 70056)) { //add 70056
				item.setIdentified(false);
				int chance = Random.nextInt(100) + 1;
				if (chance <= 15) {
					item.setEnchantLevel(-2);
				} else if ((chance >= 16) && (chance <= 30)) {
					item.setEnchantLevel(-1);
				} else if ((chance >= 31) && (chance <= 70)) {
					item.setEnchantLevel(0);
				} else if ((chance >= 71) && (chance <= 87)) {
					item.setEnchantLevel(Random.nextInt(2) + 1);
				} else if ((chance >= 88) && (chance <= 97)) {
					item.setEnchantLevel(Random.nextInt(3) + 3);
				} else if ((chance >= 98) && (chance <= 99)) {
					item.setEnchantLevel(6);
				} else if (chance == 100) {
					item.setEnchantLevel(7);
				}
			}
		}
	}

	/**
	 * プレイヤーに、L1ShopBuyOrderListに记载されたアイテムを贩卖する。
	 * 
	 * @param pc
	 *            贩卖するプレイヤー
	 * @param orderList
	 *            贩卖すべきアイテムが记载されたL1ShopBuyOrderList
	 */
	public void sellItems(L1PcInstance pc, L1ShopBuyOrderList orderList) {
		if (!ensureSell(pc, orderList)) {
			return;
		}

		sellItems(pc, pc.getInventory(), orderList); // 增加pc
		payTax(orderList);
	}

	/**
	 * L1ShopSellOrderListに记载されたアイテムを买い取る。
	 * 
	 * @param orderList
	 *            买い取るべきアイテムと価格が记载されたL1ShopSellOrderList
	 */
	public void buyItems(L1ShopSellOrderList orderList) {
		L1PcInventory inv = orderList.getPc().getInventory();
		int totalPrice = 0;
		for (L1ShopSellOrder order : orderList.getList()) {
			int count = inv.removeItem(order.getItem().getTargetId(),
					order.getCount());
			totalPrice += order.getItem().getAssessedPrice() * count;
		}

		totalPrice = IntRange.ensure(totalPrice, 0, 2000000000);
		if (0 < totalPrice) {
			inv.storeItem(L1ItemId.ADENA, totalPrice);
		}
	}

	public L1ShopBuyOrderList newBuyOrderList() {
		return new L1ShopBuyOrderList(this);
	}

	public L1ShopSellOrderList newSellOrderList(L1PcInstance pc) {
		return new L1ShopSellOrderList(this, pc);
	}
}
