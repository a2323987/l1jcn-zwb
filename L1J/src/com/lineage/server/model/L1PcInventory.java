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
package com.lineage.server.model;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.datatables.RaceTicketTable;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.serverpackets.S_AddItem;
import com.lineage.server.serverpackets.S_CharVisualUpdate;
import com.lineage.server.serverpackets.S_DeleteInventoryItem;
import com.lineage.server.serverpackets.S_ItemAmount;
import com.lineage.server.serverpackets.S_ItemColor;
import com.lineage.server.serverpackets.S_ItemName;
import com.lineage.server.serverpackets.S_ItemStatus;
import com.lineage.server.serverpackets.S_OwnCharStatus;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.storage.CharactersItemStorage;
import com.lineage.server.templates.L1Item;
import com.lineage.server.templates.L1RaceTicket;
import com.lineage.server.utils.Random;

/**
 * 
 */
public class L1PcInventory extends L1Inventory {

    private static final long serialVersionUID = 1L;

    private static Logger _log = Logger
            .getLogger(L1PcInventory.class.getName());
    /** 最高规格 */
    private static final int MAX_SIZE = 180;
    /** 所有者(玩家) */
    private final L1PcInstance _owner;
    /** 优先使用ItemID的箭 */
    private int _arrowId;
    /** 优先使用ItemID的飞刀 */
    private int _stingId;

    public static final int COL_ALL = 0;

    public static final int COL_DURABILITY = 1;

    public static final int COL_IS_ID = 2;

    public static final int COL_ENCHANTLVL = 4;

    public static final int COL_EQUIPPED = 8;

    public static final int COL_COUNT = 16;

    public static final int COL_DELAY_EFFECT = 32;

    public static final int COL_ITEMID = 64;

    public static final int COL_CHARGE_COUNT = 128;

    public static final int COL_REMAINING_TIME = 256;

    public static final int COL_BLESS = 512;

    public static final int COL_ATTR_ENCHANT_KIND = 1024;

    public static final int COL_ATTR_ENCHANT_LEVEL = 2048;

    public static final int COL_ADDHP = 1;

    public static final int COL_ADDMP = 2;

    public static final int COL_HPR = 4;

    public static final int COL_MPR = 8;

    public static final int COL_ADDSP = 16;

    public static final int COL_M_DEF = 32;

    public static final int COL_EARTHMR = 64;

    public static final int COL_FIREMR = 128;

    public static final int COL_WATERMR = 256;

    public static final int COL_WINDMR = 512;

    public L1PcInventory(final L1PcInstance owner) {
        this._owner = owner;
        this._arrowId = 0;
        this._stingId = 0;
    }

    /** 242阶段的重量数值计算 */
    public int calcWeight242(final int weight) {
        int weight242 = 0;
        if (Config.RATE_WEIGHT_LIMIT != 0) {
            final double maxWeight = this._owner.getMaxWeight();
            if (weight > maxWeight) {
                weight242 = 242;
            } else {
                double wpTemp = (weight * 100 / maxWeight) * 242.00 / 100.00;
                final DecimalFormat df = new DecimalFormat("00.##");
                df.format(wpTemp);
                wpTemp = Math.round(wpTemp);
                weight242 = (int) (wpTemp);
            }
        } else { // 如果负重率为0体重始终为0
            weight242 = 0;
        }
        return weight242;
    }

    public L1ItemInstance CaoPenalty() {
        final int rnd = Random.nextInt(this._items.size());
        final L1ItemInstance penaltyItem = this._items.get(rnd);
        if ((penaltyItem.getItem().getItemId() == L1ItemId.ADENA // 金币、不能交易的道具掉落
                )
                || !penaltyItem.getItem().isTradable()) {
            return null;
        }
        final Object[] petlist = this._owner.getPetList().values().toArray();
        for (final Object petObject : petlist) {
            if (petObject instanceof L1PetInstance) {
                final L1PetInstance pet = (L1PetInstance) petObject;
                if (penaltyItem.getId() == pet.getItemObjId()) {
                    return null;
                }
            }
        }
        this.setEquipped(penaltyItem, false);
        return penaltyItem;
    }

    /** 检查增加道具 */
    @Override
    public int checkAddItem(final L1ItemInstance item, final int count) {
        return this.checkAddItem(item, count, true);
    }

    /** 检查增加道具 */
    public int checkAddItem(final L1ItemInstance item, final int count,
            final boolean message) {
        if (item == null) {
            return -1;
        }
        if ((this.getSize() > MAX_SIZE)
                || ((this.getSize() == MAX_SIZE) && (!item.isStackable() || !this
                        .checkItem(item.getItem().getItemId())))) { // 容量确认
            if (message) {
                this.sendOverMessage(263); // \f1一个角色最多可携带180个道具。
            }
            return SIZE_OVER;
        }

        final int weight = this.getWeight() + item.getItem().getWeight()
                * count / 1000 + 1;
        if ((weight < 0) || ((item.getItem().getWeight() * count / 1000) < 0)) {
            if (message) {
                this.sendOverMessage(82); // 此物品太重了，所以你无法携带。
            }
            return WEIGHT_OVER;
        }
        if (this.calcWeight242(weight) >= 242) {
            if (message) {
                this.sendOverMessage(82); // 此物品太重了，所以你无法携带。
            }
            return WEIGHT_OVER;
        }

        final L1ItemInstance itemExist = this.findItemId(item.getItemId());
        if ((itemExist != null)
                && ((itemExist.getCount() + count) > MAX_AMOUNT)) {
            if (message) {
                this.getOwner().sendPackets(
                        new S_ServerMessage(166, "所持有的金币",
                                "超过了2,000,000,000上限。")); // \f1%0%s %4%1%3 %2。
            }
            return AMOUNT_OVER;
        }

        return OK;
    }

    /** 检查特定的道具装备上 */
    public boolean checkEquipped(final int id) {
        for (final Object itemObject : this._items) {
            final L1ItemInstance item = (L1ItemInstance) itemObject;
            if ((item.getItem().getItemId() == id) && item.isEquipped()) {
                return true;
            }
        }
        return false;
    }

    /** 检查特定的道具全部装备上（用于套装的验证） */
    public boolean checkEquipped(final int[] ids) {
        for (final int id : ids) {
            if (!this.checkEquipped(id)) {
                return false;
            }
        }
        return true;
    }

    // 删除character_items内的道具
    @Override
    public void deleteItem(final L1ItemInstance item) {
        try {
            final CharactersItemStorage storage = CharactersItemStorage
                    .create();

            storage.deleteItem(item);
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
        if (item.isEquipped()) {
            this.setEquipped(item, false);
        }
        this._owner.sendPackets(new S_DeleteInventoryItem(item));
        this._items.remove(item);
        if (item.getItem().getWeight() != 0) {
            this._owner.sendPackets(new S_PacketBox(S_PacketBox.WEIGHT, this
                    .getWeight242()));
        }
    }

    /** 取得使用箭 */
    public L1ItemInstance getArrow() {
        return this.getBullet(0);
    }

    private L1ItemInstance getBullet(final int type) {
        L1ItemInstance bullet;
        int priorityId = 0;
        if (type == 0) {
            priorityId = this._arrowId; // 箭
        }
        if (type == 15) {
            priorityId = this._stingId; // 飞刀
        }
        if (priorityId > 0) // 优先する弹があるか
        {
            bullet = this.findItemId(priorityId);
            if (bullet != null) {
                return bullet;
            }
            if (type == 0) {
                this._arrowId = 0;
            }
            if (type == 15) {
                this._stingId = 0;
            }
        }

        for (final Object itemObject : this._items) // 弹を探す
        {
            bullet = (L1ItemInstance) itemObject;
            if ((bullet.getItem().getType() == type)
                    && (bullet.getItem().getType2() == 0)) {
                if (type == 0) {
                    this._arrowId = bullet.getItem().getItemId(); // 保持优先
                }
                if (type == 15) {
                    this._stingId = bullet.getItem().getItemId(); // 保持优先
                }
                return bullet;
            }
        }
        return null;
    }

    /** 取得装备特定类型的道具 */
    public L1ItemInstance getItemEquipped(final int type2, final int type) {
        L1ItemInstance equipeitem = null;
        for (final Object itemObject : this._items) {
            final L1ItemInstance item = (L1ItemInstance) itemObject;
            if ((item.getItem().getType2() == type2)
                    && (item.getItem().getType() == type) && item.isEquipped()) {
                equipeitem = item;
                break;
            }
        }
        return equipeitem;
    }

    public L1PcInstance getOwner() {
        return this._owner;
    }

    /** 装备戒指 */
    public L1ItemInstance[] getRingEquipped() {
        final L1ItemInstance equipeItem[] = new L1ItemInstance[2];
        int equipeCount = 0;
        for (final Object itemObject : this._items) {
            final L1ItemInstance item = (L1ItemInstance) itemObject;
            if ((item.getItem().getType2() == 2)
                    && (item.getItem().getType() == 9) && item.isEquipped()) {
                equipeItem[equipeCount] = item;
                equipeCount++;
                if (equipeCount == 2) {
                    break;
                }
            }
        }
        return equipeItem;
    }

    /** 取得使用飞刀 */
    public L1ItemInstance getSting() {
        return this.getBullet(15);
    }

    /** 特定类型的道具装备数量 */
    public int getTypeEquipped(final int type2, final int type) {
        int equipeCount = 0;
        for (final Object itemObject : this._items) {
            final L1ItemInstance item = (L1ItemInstance) itemObject;
            if ((item.getItem().getType2() == type2)
                    && (item.getItem().getType() == type) && item.isEquipped()) {
                equipeCount++;
            }
        }
        return equipeCount;
    }

    /** 分为242段的重量数值 */
    public int getWeight242() {
        return this.calcWeight242(this.getWeight());
    }

    /** 由于装备的ＨＰ自然回复补正 */
    public int hpRegenPerTick() {
        int hpr = 0;
        for (final Object itemObject : this._items) {
            final L1ItemInstance item = (L1ItemInstance) itemObject;
            if (item.isEquipped()) {
                hpr += item.getItem().get_addhpr() + item.getHpr();
            }
        }
        return hpr;
    }

    // 对资料库中的character_items资料表写入
    @Override
    public void insertItem(final L1ItemInstance item) {
        this._owner.sendPackets(new S_AddItem(item));
        if (item.getItem().getWeight() != 0) {
            this._owner.sendPackets(new S_PacketBox(S_PacketBox.WEIGHT, this
                    .getWeight242()));
        }
        try {
            final CharactersItemStorage storage = CharactersItemStorage
                    .create();
            storage.storeItem(this._owner.getId(), item);
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    // 读取资料库中的character_items资料表
    @Override
    public void loadItems() {
        try {
            final CharactersItemStorage storage = CharactersItemStorage
                    .create();

            for (final L1ItemInstance item : storage.loadItems(this._owner
                    .getId())) {
                this._items.add(item);

                if (item.isEquipped()) {
                    item.setEquipped(false);
                    this.setEquipped(item, true, true, false);
                }
                if ((item.getItem().getType2() == 0)
                        && (item.getItem().getType() == 2)) { // light系列道具
                    item.setRemainingTime(item.getItem().getLightFuel());
                }
                /**
                 * 玩家身上的食人妖精RaceTicket 显示场次、及选手编号
                 */
                if (item.getItemId() == 40309) {
                    final L1RaceTicket ticket = RaceTicketTable.getInstance()
                            .getTemplate(item.getId());
                    if (ticket != null) {
                        final L1Item temp = (L1Item) item.getItem().clone();
                        final String buf = temp.getIdentifiedNameId() + " "
                                + ticket.get_round() + "-"
                                + ticket.get_runner_num();
                        temp.setName(buf);
                        temp.setUnidentifiedNameId(buf);
                        temp.setIdentifiedNameId(buf);
                        item.setItem(temp);
                    }
                }
                L1World.getInstance().storeObject(item);
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    /** 由于装备的ＭＰ自然回复补正 */
    public int mpRegenPerTick() {
        int mpr = 0;
        for (final Object itemObject : this._items) {
            final L1ItemInstance item = (L1ItemInstance) itemObject;
            if (item.isEquipped()) {
                mpr += item.getItem().get_addmpr() + item.getMpr();
            }
        }
        return mpr;
    }

    /** 饰品强化 */
    public void saveEnchantAccessory(final L1ItemInstance item, int column) {
        if (column == 0) {
            return;
        }

        try {
            final CharactersItemStorage storage = CharactersItemStorage
                    .create();
            if (column >= COL_WINDMR) {
                storage.updateWindMr(item);
                column -= COL_WINDMR;
            }
            if (column >= COL_WATERMR) {
                storage.updateWaterMr(item);
                column -= COL_WATERMR;
            }
            if (column >= COL_FIREMR) {
                storage.updateFireMr(item);
                column -= COL_FIREMR;
            }
            if (column >= COL_EARTHMR) {
                storage.updateEarthMr(item);
                column -= COL_EARTHMR;
            }
            if (column >= COL_M_DEF) {
                storage.updateM_Def(item);
                column -= COL_M_DEF;
            }
            if (column >= COL_ADDSP) {
                storage.updateaddSp(item);
                column -= COL_ADDSP;
            }
            if (column >= COL_MPR) {
                storage.updateMpr(item);
                column -= COL_MPR;
            }
            if (column >= COL_HPR) {
                storage.updateHpr(item);
                column -= COL_HPR;
            }
            if (column >= COL_ADDMP) {
                storage.updateaddMp(item);
                column -= COL_ADDMP;
            }
            if (column >= COL_ADDHP) {
                storage.updateaddHp(item);
                column -= COL_ADDHP;
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    /**
     * 背包内的道具状态保存到资料库内。
     * 
     * @param item
     *            - 更新对象的道具
     * @param column
     *            - 更新状态的种类
     */
    public void saveItem(final L1ItemInstance item, int column) {
        if (column == 0) {
            return;
        }

        try {
            final CharactersItemStorage storage = CharactersItemStorage
                    .create();
            if (column >= COL_ATTR_ENCHANT_LEVEL) { // 属性强化数
                storage.updateItemAttrEnchantLevel(item);
                column -= COL_ATTR_ENCHANT_LEVEL;
            }
            if (column >= COL_ATTR_ENCHANT_KIND) { // 属性强化种类
                storage.updateItemAttrEnchantKind(item);
                column -= COL_ATTR_ENCHANT_KIND;
            }
            if (column >= COL_BLESS) { // 祝福・封印
                storage.updateItemBless(item);
                column -= COL_BLESS;
            }
            if (column >= COL_REMAINING_TIME) { // 使用可能剩余时间
                storage.updateItemRemainingTime(item);
                column -= COL_REMAINING_TIME;
            }
            if (column >= COL_CHARGE_COUNT) { // 收费次数？
                storage.updateItemChargeCount(item);
                column -= COL_CHARGE_COUNT;
            }
            if (column >= COL_ITEMID) { // 其他道具的情况(如 当开打一个信封)
                storage.updateItemId(item);
                column -= COL_ITEMID;
            }
            if (column >= COL_DELAY_EFFECT) { // 延迟效果
                storage.updateItemDelayEffect(item);
                column -= COL_DELAY_EFFECT;
            }
            if (column >= COL_COUNT) { // 数量
                storage.updateItemCount(item);
                column -= COL_COUNT;
            }
            if (column >= COL_EQUIPPED) { // 装备状态
                storage.updateItemEquipped(item);
                column -= COL_EQUIPPED;
            }
            if (column >= COL_ENCHANTLVL) { // エンチャント
                storage.updateItemEnchantLevel(item);
                column -= COL_ENCHANTLVL;
            }
            if (column >= COL_IS_ID) { // 确认状态
                storage.updateItemIdentified(item);
                column -= COL_IS_ID;
            }
            if (column >= COL_DURABILITY) { // 耐久性
                storage.updateItemDurability(item);
                column -= COL_DURABILITY;
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    /** 发送结束讯息 */
    public void sendOverMessage(int message_id) {
        // 钓鱼中负重讯息变更
        if (this._owner.isFishing() && (message_id == 82)) {
            message_id = 1518; // 负重太高的状态下无法进行钓鱼。
        }
        this._owner.sendPackets(new S_ServerMessage(message_id));
    }

    /** 箭的优先设定 */
    public void setArrow(final int id) {
        this._arrowId = id;
    }

    // 设定装备的装脱（L1ItemInstance变更、补正值设定、character_items更新、发送一个数据包管理）
    public void setEquipped(final L1ItemInstance item, final boolean equipped) {
        this.setEquipped(item, equipped, false, false);
    }

    /** 设定特定的道具装备上 */
    public void setEquipped(final L1ItemInstance item, final boolean equipped,
            final boolean loaded, final boolean changeWeapon) {
        if (item.isEquipped() != equipped) { // 处理设定值不同的情况
            final L1Item temp = item.getItem();
            if (equipped) { // 装着
                item.setEquipped(true);
                this._owner.getEquipSlot().set(item);
            } else { // 脱着
                if (!loaded) {
                    // 脱下隐身斗篷 炎魔的血光斗篷解除隐身状态
                    if ((temp.getItemId() == 20077)
                            || (temp.getItemId() == 20062)
                            || (temp.getItemId() == 120077)) {
                        if (this._owner.isInvisble()) {
                            this._owner.delInvis();
                            return;
                        }
                    }
                }
                item.setEquipped(false);
                this._owner.getEquipSlot().remove(item);
            }
            if (!loaded) { // 处理第一次读取DB时的封包关联
                // XXX:设置无意义
                this._owner.setCurrentHp(this._owner.getCurrentHp());
                this._owner.setCurrentMp(this._owner.getCurrentMp());
                this.updateItem(item, COL_EQUIPPED);
                this._owner.sendPackets(new S_OwnCharStatus(this._owner));
                if ((temp.getType2() == 1) && (changeWeapon == false)) { // 武器的视觉更新。持有武器
                                                                         // 脱着武器时不更新
                    this._owner
                            .sendPackets(new S_CharVisualUpdate(this._owner));
                    this._owner.broadcastPacket(new S_CharVisualUpdate(
                            this._owner));
                }
                // _owner.getNetConnection().saveCharToDisk(_owner); //
                // DBにキャラクター情报を书き迂む
            }
        }
    }

    /** 飞刀的优先设定 */
    public void setSting(final int id) {
        this._stingId = id;
    }

    /** 卸下变身时不能装备的防具 */
    private void takeoffArmor(final int polyid) {
        L1ItemInstance armor = null;

        // ヘルムからガーダーまでチェックする
        for (int type = 0; type <= 13; type++) {
            // 装备していて、装备不可の场合は外す
            if ((this.getTypeEquipped(2, type) != 0)
                    && !L1PolyMorph.isEquipableArmor(polyid, type)) {
                if (type == 9) { // 戒指的场合、两手分外す
                    armor = this.getItemEquipped(2, type);
                    if (armor != null) {
                        this.setEquipped(armor, false, false, false);
                    }
                    armor = this.getItemEquipped(2, type);
                    if (armor != null) {
                        this.setEquipped(armor, false, false, false);
                    }
                } else {
                    armor = this.getItemEquipped(2, type);
                    if (armor != null) {
                        this.setEquipped(armor, false, false, false);
                    }
                }
            }
        }
    }

    /** 卸下变身时不能装备的装备 */
    public void takeoffEquip(final int polyid) {
        this.takeoffWeapon(polyid);
        this.takeoffArmor(polyid);
    }

    /** 卸下变身时不能装备的武器 */
    private void takeoffWeapon(final int polyid) {
        if (this._owner.getWeapon() == null) { // 空手
            return;
        }

        boolean takeoff = false;
        final int weapon_type = this._owner.getWeapon().getItem().getType();
        // 装备出来ない武器を装备してるか？
        takeoff = !L1PolyMorph.isEquipableWeapon(polyid, weapon_type);

        if (takeoff) {
            this.setEquipped(this._owner.getWeapon(), false, false, false);
        }
    }

    @Override
    public void updateItem(final L1ItemInstance item) {
        this.updateItem(item, COL_COUNT);
        if (item.getItem().isToBeSavedAtOnce()) {
            this.saveItem(item, COL_COUNT);
        }
    }

    /**
     * 玩家背包内道具的状态更新。
     * 
     * @param item
     *            - 更新对象的道具
     * @param column
     *            - 状态更新的类型
     */
    @Override
    public void updateItem(final L1ItemInstance item, int column) {
        if (column >= COL_ATTR_ENCHANT_LEVEL) { // 属性强化数
            this._owner.sendPackets(new S_ItemStatus(item));
            column -= COL_ATTR_ENCHANT_LEVEL;
        }
        if (column >= COL_ATTR_ENCHANT_KIND) { // 属性强化种类
            this._owner.sendPackets(new S_ItemStatus(item));
            column -= COL_ATTR_ENCHANT_KIND;
        }
        if (column >= COL_BLESS) { // 祝福・封印
            this._owner.sendPackets(new S_ItemColor(item));
            column -= COL_BLESS;
        }
        if (column >= COL_REMAINING_TIME) { // 使用可能剩余时间
            this._owner.sendPackets(new S_ItemName(item));
            column -= COL_REMAINING_TIME;
        }
        if (column >= COL_CHARGE_COUNT) { // 收费次数？
            this._owner.sendPackets(new S_ItemName(item));
            column -= COL_CHARGE_COUNT;
        }
        if (column >= COL_ITEMID) { // 其他道具的情况(如 当开打一个信封)
            this._owner.sendPackets(new S_ItemStatus(item));
            this._owner.sendPackets(new S_ItemColor(item));
            this._owner.sendPackets(new S_PacketBox(S_PacketBox.WEIGHT, this
                    .getWeight242()));
            column -= COL_ITEMID;
        }
        if (column >= COL_DELAY_EFFECT) { // 延迟效果
            column -= COL_DELAY_EFFECT;
        }
        if (column >= COL_COUNT) { // 数量
            this._owner.sendPackets(new S_ItemAmount(item));

            final int weight = item.getWeight();
            if (weight != item.getLastWeight()) {
                item.setLastWeight(weight);
                this._owner.sendPackets(new S_ItemStatus(item));
            } else {
                this._owner.sendPackets(new S_ItemName(item));
            }
            if (item.getItem().getWeight() != 0) {
                // XXX 发送242阶段的重量变化
                this._owner.sendPackets(new S_PacketBox(S_PacketBox.WEIGHT,
                        this.getWeight242()));
            }
            column -= COL_COUNT;
        }
        if (column >= COL_EQUIPPED) { // 装备状态
            this._owner.sendPackets(new S_ItemName(item));
            column -= COL_EQUIPPED;
        }
        if (column >= COL_ENCHANTLVL) { // エンチャント
            this._owner.sendPackets(new S_ItemStatus(item));
            column -= COL_ENCHANTLVL;
        }
        if (column >= COL_IS_ID) { // 确认状态
            this._owner.sendPackets(new S_ItemStatus(item));
            this._owner.sendPackets(new S_ItemColor(item));
            column -= COL_IS_ID;
        }
        if (column >= COL_DURABILITY) { // 耐久性
            this._owner.sendPackets(new S_ItemStatus(item));
            column -= COL_DURABILITY;
        }
    }
}
