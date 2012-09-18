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
package com.lineage.server.model.shop;

import java.util.List;

import com.lineage.Config;
import com.lineage.server.datatables.CastleTable;
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.datatables.TownTable;
import com.lineage.server.model.L1CastleLocation;
import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.L1TaxCalculator;
import com.lineage.server.model.L1TownLocation;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.game.L1BugBearRace;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Castle;
import com.lineage.server.templates.L1Item;
import com.lineage.server.templates.L1ShopItem;
import com.lineage.server.utils.IntRange;
import com.lineage.server.utils.Random;
import com.lineage.server.utils.collections.Lists;

/**
 * 商店
 */
public class L1Shop {

    /** NPC ID */
    private final int _npcId;
    /** 销售项目 */
    private final List<L1ShopItem> _sellingItems;
    /** 购买项目 */
    private final List<L1ShopItem> _purchasingItems;

    /**
     * 商店
     * 
     * @param npcId
     * @param sellingItems
     *            销售项目
     * @param purchasingItems
     *            购买项目
     */
    public L1Shop(final int npcId, final List<L1ShopItem> sellingItems,
            final List<L1ShopItem> purchasingItems) {
        if ((sellingItems == null) || (purchasingItems == null)) {
            throw new NullPointerException();
        }

        this._npcId = npcId;
        this._sellingItems = sellingItems;
        this._purchasingItems = purchasingItems;
    }

    /**
     * 评估道具
     * 
     * @param item
     */
    public L1AssessedItem assessItem(final L1ItemInstance item) {
        final L1ShopItem shopItem = this.getPurchasingItem(item.getItemId());
        if (shopItem == null) {
            return null;
        }
        return new L1AssessedItem(item.getId(), this.getAssessedPrice(shopItem));
    }

    /**
     * 检查库存内道具能否购买。
     * 
     * @param inv
     *            检查对象的库存
     * @return 检查可购买道具的清单
     */
    public List<L1AssessedItem> assessItems(final L1PcInventory inv) {
        final List<L1AssessedItem> result = Lists.newList();
        for (final L1ShopItem item : this._purchasingItems) {
            for (final L1ItemInstance targetItem : inv.findItemsId(item
                    .getItemId())) {
                if (!this.isPurchaseableItem(targetItem)) {
                    continue;
                }

                result.add(new L1AssessedItem(targetItem.getId(), this
                        .getAssessedPrice(item)));
            }
        }
        return result;
    }

    /**
     * 购买L1ShopSellOrderList所列的项目。
     * 
     * @param orderList
     *            取得购买列出的项目和价格L1ShopSellOrderList
     */
    public void buyItems(final L1ShopSellOrderList orderList) {
        final L1PcInventory inv = orderList.getPc().getInventory();
        int totalPrice = 0;
        for (final L1ShopSellOrder order : orderList.getList()) {
            final int count = inv.removeItem(order.getItem().getTargetId(),
                    order.getCount());
            totalPrice += order.getItem().getAssessedPrice() * count;
        }

        totalPrice = IntRange.ensure(totalPrice, 0, 2000000000);
        if (0 < totalPrice) {
            inv.storeItem(L1ItemId.ADENA, totalPrice);
        }
    }

    /**
     * 为了确保玩家可以出售物品。
     * 
     * @return 如果你不能以任何理由出售项目、false
     */
    private boolean ensureSell(final L1PcInstance pc,
            final L1ShopBuyOrderList orderList) {
        final int price = orderList.getTotalPriceTaxIncluded();

        // 溢出检查
        if (!IntRange.includes(price, 0, 2000000000)) {
            pc.sendPackets(new S_ServerMessage(904, "2000000000")); // 总共贩卖价格无法超过
                                                                    // %d金币。
            return false;
        }

        // 检查是否可以购买
        if (!pc.getInventory().checkItem(L1ItemId.ADENA, price)) {
            System.out.println(price);
            pc.sendPackets(new S_ServerMessage(189)); // \f1金币不足。
            return false;
        }

        // 检查负重
        final int currentWeight = pc.getInventory().getWeight() * 1000;
        if (currentWeight + orderList.getTotalWeight() > pc.getMaxWeight() * 1000) {
            pc.sendPackets(new S_ServerMessage(82)); // 此物品太重了，所以你无法携带。
            return false;
        }

        // 检查道具总数
        int totalCount = pc.getInventory().getSize();
        for (final L1ShopBuyOrder order : orderList.getList()) {
            final L1Item temp = order.getItem().getItem();
            if (temp.isStackable()) {
                if (!pc.getInventory().checkItem(temp.getItemId())) {
                    totalCount += 1;
                }
            } else {
                totalCount += 1;
            }
        }
        if (totalCount > 180) {
            pc.sendPackets(new S_ServerMessage(263)); // \f1一个角色最多可携带180个道具。
            return false;
        }
        return true;
    }

    /**
     * 获得评估价格
     * 
     * @param item
     */
    private int getAssessedPrice(final L1ShopItem item) {
        return (int) (item.getPrice() * Config.RATE_SHOP_PURCHASING_PRICE / item
                .getPackCount());
    }

    /** 获得NPC ID */
    public int getNpcId() {
        return this._npcId;
    }

    /**
     * 获得购买道具
     * 
     * @param itemId
     */
    private L1ShopItem getPurchasingItem(final int itemId) {
        for (final L1ShopItem shopItem : this._purchasingItems) {
            if (shopItem.getItemId() == itemId) {
                return shopItem;
            }
        }
        return null;
    }

    /** 获得销售项目 */
    public List<L1ShopItem> getSellingItems() {
        return this._sellingItems;
    }

    /**
     * 返回商店内指定的道具能否购买。
     * 
     * @param item
     * @return 该道具可以购买true
     */
    private boolean isPurchaseableItem(final L1ItemInstance item) {
        if (item == null) {
            return false;
        }
        if (item.isEquipped()) { // 装备中不可
            return false;
        }
        if (item.getEnchantLevel() != 0) { // 強化(or弱化)不可
            return false;
        }
        if (item.getBless() >= 128) { // 封印装备不可
            return false;
        }

        return true;
    }

    /** 个人商店购买清单顺序 */
    public L1ShopBuyOrderList newBuyOrderList() {
        return new L1ShopBuyOrderList(this);
    }

    /** 个人商店贩卖清单顺序 */
    public L1ShopSellOrderList newSellOrderList(final L1PcInstance pc) {
        return new L1ShopSellOrderList(this, pc);
    }

    /**
     * 地域税納税処理 アデン城・ディアド要塞を除く城はアデン城へ国税として10%納税する
     * 
     * @param orderList
     */
    private void payCastleTax(final L1ShopBuyOrderList orderList) {
        final L1TaxCalculator calc = orderList.getTaxCalculator();

        final int price = orderList.getTotalPrice();

        final int castleId = L1CastleLocation.getCastleIdByNpcid(this._npcId);
        int castleTax = calc.calcCastleTaxPrice(price);
        int nationalTax = calc.calcNationalTaxPrice(price);
        // アデン城・ディアド城の場合は国税なし
        if ((castleId == L1CastleLocation.ADEN_CASTLE_ID)
                || (castleId == L1CastleLocation.DIAD_CASTLE_ID)) {
            castleTax += nationalTax;
            nationalTax = 0;
        }

        if ((castleId != 0) && (castleTax > 0)) {
            final L1Castle castle = CastleTable.getInstance().getCastleTable(
                    castleId);

            synchronized (castle) {
                int money = castle.getPublicMoney();
                if (2000000000 > money) {
                    money = money + castleTax;
                    castle.setPublicMoney(money);
                    CastleTable.getInstance().updateCastle(castle);
                }
            }

            if (nationalTax > 0) {
                final L1Castle aden = CastleTable.getInstance().getCastleTable(
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
     * ディアド税納税処理 戦争税の10%がディアド要塞の公金となる。
     * 
     * @param orderList
     */
    private void payDiadTax(final L1ShopBuyOrderList orderList) {
        final L1TaxCalculator calc = orderList.getTaxCalculator();

        final int price = orderList.getTotalPrice();

        // ディアド税
        final int diadTax = calc.calcDiadTaxPrice(price);
        if (diadTax <= 0) {
            return;
        }

        final L1Castle castle = CastleTable.getInstance().getCastleTable(
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

    // XXX 納税処理はこのクラスの責務では無い気がするが、とりあえず
    /** 纳税处理 */
    private void payTax(final L1ShopBuyOrderList orderList) {
        this.payCastleTax(orderList);
        this.payTownTax(orderList);
        this.payDiadTax(orderList);
    }

    /**
     * 城镇纳税处理
     * 
     * @param orderList
     */
    private void payTownTax(final L1ShopBuyOrderList orderList) {
        final int price = orderList.getTotalPrice();

        // 收入的城镇
        if (!L1World.getInstance().isProcessingContributionTotal()) {
            final int town_id = L1TownLocation.getTownIdByNpcid(this._npcId);
            if ((town_id >= 1) && (town_id <= 10)) {
                TownTable.getInstance().addSalesMoney(town_id, price);
            }
        }
    }

    /**
     * 为角色、列出商店出售的道具L1ShopBuyOrderList。
     * 
     * @param pc
     *            出售给玩家
     * @param orderList
     *            列出商店出售的道具L1ShopBuyOrderList
     */
    public void sellItems(final L1PcInstance pc,
            final L1ShopBuyOrderList orderList) {
        if (!this.ensureSell(pc, orderList)) {
            return;
        }

        this.sellItems(pc.getInventory(), orderList);
        this.payTax(orderList);
    }

    /**
     * 销售交易
     * 
     * @param inv
     * @param orderList
     */
    private void sellItems(final L1PcInventory inv,
            final L1ShopBuyOrderList orderList) {
        if (!inv.consumeItem(L1ItemId.ADENA,
                orderList.getTotalPriceTaxIncluded())) {
            throw new IllegalStateException("因不足对购买所需的货币而不能消费。");
        }
        for (final L1ShopBuyOrder order : orderList.getList()) {
            final int itemId = order.getItem().getItemId();
            final int amount = order.getCount();
            final L1ItemInstance item = ItemTable.getInstance().createItem(
                    itemId);
            if (item.getItemId() == 40309) { // Race Tickets(食人妖精竞赛票)
                item.setItem(order.getItem().getItem());
                L1BugBearRace.getInstance().setAllBet(
                        L1BugBearRace.getInstance().getAllBet()
                                + (amount * order.getItem().getPrice()));
                final String[] runNum = item.getItem().getIdentifiedNameId()
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
            inv.storeItem(item);
            if ((this._npcId == 70068) || (this._npcId == 70020)) { // 福朗克、罗克(贩卖道具随机加成)
                item.setIdentified(true);
                final int chance = Random.nextInt(100) + 1; // 几率
                if (chance <= 15) {
                    item.setEnchantLevel(-2); // -2装备
                    inv.updateItem(item, L1PcInventory.COL_ENCHANTLVL);
                } else if ((chance >= 16) && (chance <= 30)) { // 15%几率
                    item.setEnchantLevel(-1);
                    inv.updateItem(item, L1PcInventory.COL_ENCHANTLVL);
                } else if ((chance >= 31) && (chance <= 70)) { // 40%几率
                    item.setEnchantLevel(0);
                    inv.updateItem(item, L1PcInventory.COL_ENCHANTLVL);
                } else if ((chance >= 71) && (chance <= 87)) { // 17%几率
                    item.setEnchantLevel(Random.nextInt(2) + 1);
                    inv.updateItem(item, L1PcInventory.COL_ENCHANTLVL);
                } else if ((chance >= 88) && (chance <= 97)) { // 10%几率
                    item.setEnchantLevel(Random.nextInt(3) + 3);
                    inv.updateItem(item, L1PcInventory.COL_ENCHANTLVL);
                } else if ((chance >= 98) && (chance <= 99)) { // 2%几率
                    item.setEnchantLevel(6);
                    inv.updateItem(item, L1PcInventory.COL_ENCHANTLVL);
                } else if (chance == 100) {
                    item.setEnchantLevel(7); // +7装备
                    inv.updateItem(item, L1PcInventory.COL_ENCHANTLVL);
                }
            }
        }
    }
}
