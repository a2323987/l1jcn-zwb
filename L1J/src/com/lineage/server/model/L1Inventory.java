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

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lineage.Config;
import com.lineage.server.IdFactory;
import com.lineage.server.datatables.FurnitureSpawnTable;
import com.lineage.server.datatables.InnKeyTable;
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.datatables.LetterTable;
import com.lineage.server.datatables.PetTable;
import com.lineage.server.datatables.RaceTicketTable;
import com.lineage.server.model.Instance.L1FurnitureInstance;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.templates.L1Item;
import com.lineage.server.templates.L1RaceTicket;
import com.lineage.server.utils.Random;
import com.lineage.server.utils.collections.Lists;

/**
 * 角色背包道具
 * 
 * @author jrwz
 */
public class L1Inventory extends L1Object {

    /**
     * 按照强化值 由低至高排列物品
     * 
     * @return 道具1的强化等级 - 道具2的强化等级
     */
    public class DataComparator<T> implements Comparator<L1ItemInstance> {
        @Override
        public int compare(final L1ItemInstance item1,
                final L1ItemInstance item2) {
            return item1.getEnchantLevel() - item2.getEnchantLevel();
        }
    }

    /** 提示信息 */
    private static final Log _log = LogFactory.getLog(L1Inventory.class);

    /** 序列编号UID */
    private static final long serialVersionUID = 1L;

    /** 背包内的全部道具 */
    protected List<L1ItemInstance> _items = Lists.newConcurrentList();

    /** 最大数量 */
    public static final int MAX_AMOUNT = 2000000000; // 2G

    /** 最大负重 */
    public static final int MAX_WEIGHT = 1500;

    // 确认增加道具的参数、检查承重能力
    /** 增加道具成功 */
    public static final int OK = 0;

    /** 增加道具超过数量 */
    public static final int SIZE_OVER = 1;

    /** 增加道具超过可携带重量 */
    public static final int WEIGHT_OVER = 2;

    /** 增加道具超过LONG最大值 */
    public static final int AMOUNT_OVER = 3;

    // 确认增加道具的参数、检查仓库容量
    /** 个人/精灵仓库 */
    public static final int WAREHOUSE_TYPE_PERSONAL = 0;

    /** 血盟仓库 */
    public static final int WAREHOUSE_TYPE_CLAN = 1;

    public L1Inventory() {
    }

    /**
     * 检查增加道具是否成功 (背包)
     * 
     * @param item
     *            道具
     * @param count
     *            数量
     * @return 0:成功 1:超过数量 2:超过可携带重量 3:超过LONG最大值
     */
    public int checkAddItem(final L1ItemInstance item, final int count) {

        // 空道具
        if (item == null) {
            return -1;
        }

        // 数量小于0
        if ((item.getCount() <= 0) || (count <= 0)) {
            return -1;
        }

        // 超过最大数量
        if ((this.getSize() > Config.MAX_NPC_ITEM)
                || ((this.getSize() == Config.MAX_NPC_ITEM) && (!item
                        .isStackable() || !this.checkItem(item.getItem()
                        .getItemId())))) { // 容量确认
            return SIZE_OVER;
        }

        // 超重
        final int weight = this.getWeight() + item.getItem().getWeight()
                * count / 1000 + 1;
        if ((weight < 0) || ((item.getItem().getWeight() * count / 1000) < 0)) {
            return WEIGHT_OVER;
        }

        // 其他重量检查（主要是召唤和宠物）
        if (weight > (MAX_WEIGHT * Config.RATE_WEIGHT_LIMIT_PET)) {
            return WEIGHT_OVER;
        }

        // 超过最大数量 (20亿)
        final L1ItemInstance itemExist = this.findItemId(item.getItemId());
        if ((itemExist != null)
                && ((itemExist.getCount() + count) > MAX_AMOUNT)) {
            return AMOUNT_OVER;
        }

        return OK;
    }

    /**
     * 检查增加道具是否成功 (仓库)
     * 
     * @param item
     *            道具
     * @param count
     *            数量
     * @param type
     *            类型 0:个人/精灵仓库 1:血盟仓库
     * @return 0:成功 1:超过数量
     */
    public int checkAddItemToWarehouse(final L1ItemInstance item,
            final int count, final int type) {

        // 空道具
        if (item == null) {
            return -1;
        }

        // 数量小于0
        if ((item.getCount() <= 0) || (count <= 0)) {
            return -1;
        }

        // 仓库默认储存道具最高数量
        int maxSize = 100;

        // 个人/精灵仓库
        if (type == WAREHOUSE_TYPE_PERSONAL) {
            maxSize = Config.MAX_PERSONAL_WAREHOUSE_ITEM;
        }

        // 血盟仓库
        else if (type == WAREHOUSE_TYPE_CLAN) {
            maxSize = Config.MAX_CLAN_WAREHOUSE_ITEM;
        }

        // 超过数量
        if ((this.getSize() > maxSize)
                || ((this.getSize() == maxSize) && (!item.isStackable() || !this
                        .checkItem(item.getItem().getItemId())))) { // 容量确认
            return SIZE_OVER;
        }

        return OK;
    }

    // 強化された特定のアイテムを指定された個数以上所持しているか確認
    // 装備中のアイテムは所持していないと判別する
    /**
     * 检查指定的物品包含强化值 (未装备状态)
     * 
     * @param id
     *            指定物件编号
     * @param enchant
     *            指定强化值
     * @param count
     *            数量
     * @return true:找到了 false:没找到
     */
    public boolean checkEnchantItem(final int id, final int enchant,
            final int count) {
        int num = 0;
        for (final L1ItemInstance item : this._items) {
            if (item.isEquipped()) { // 道具为装备状态
                continue;
            }
            if ((item.getItemId() == id) && (item.getEnchantLevel() == enchant)) {
                num++;
                if (num == count) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检查指定物品是否足够数量 (1个)（矢 魔石的確認）
     * 
     * @param id
     *            物品编号
     * @return 指定的物品数量 (1个)
     */
    public boolean checkItem(final int id) {
        return this.checkItem(id, 1);
    }

    /**
     * 检查指定物品是否足够数量
     * 
     * @param id
     *            物品编号
     * @param count
     *            需要数量
     * @return true:足够 false:不足
     */
    public boolean checkItem(final int id, final int count) {

        // 数量为0
        if (count == 0) {
            return true;
        }

        // 可堆叠
        if (ItemTable.getInstance().getTemplate(id).isStackable()) {
            final L1ItemInstance item = this.findItemId(id);
            if ((item != null) && (item.getCount() >= count)) {
                return true;
            }
        } else { // 不可堆叠
            final Object[] itemList = this.findItemsId(id);
            if (itemList.length >= count) {
                return true;
            }
        }
        return false;
    }

    // 特定のアイテムを全て必要な個数所持しているか確認（イベントとかで複数のアイテムを所持しているか確認するため）
    /**
     * 检查指定的全部物品是否足够
     * 
     * @param ids
     *            全部道具的ID
     * @return checkItem(ids, counts)
     */
    public boolean checkItem(final int[] ids) {
        final int len = ids.length;
        final int[] counts = new int[len];
        for (int i = 0; i < len; i++) {
            counts[i] = 1;
        }
        return this.checkItem(ids, counts);
    }

    /**
     * 检查指定的全部物品与全部数量是否足够
     * 
     * @param ids
     *            全部道具的ID
     * @param counts
     *            全部道具的数量
     * @return true:足够 false:不足
     */
    public boolean checkItem(final int[] ids, final int[] counts) {
        for (int i = 0; i < ids.length; i++) {
            if (!this.checkItem(ids[i], counts[i])) {
                return false;
            }
        }
        return true;
    }

    // 特定のアイテムを指定された個数以上所持しているか確認
    // 装備中のアイテムは所持していないと判別する
    /**
     * 检查指定的物品数量 (未装备状态)
     * 
     * @param id
     *            指定物件编号
     * @param count
     *            数量
     * @return
     */
    public boolean checkItemNotEquipped(final int id, final int count) {
        if (count == 0) {
            return true;
        }
        return count <= this.countItems(id);
    }

    /**
     * 刪除背包内全部物件
     */
    public void clearItems() {
        for (final Object itemObject : this._items) {
            final L1ItemInstance item = (L1ItemInstance) itemObject;
            L1World.getInstance().removeObject(item);
        }
        this._items.clear();
    }

    // 強化された特定のアイテムを消費する
    // 装備中のアイテムは所持していないと判別する
    /**
     * 刪除指定的物品包含强化值 (未装备状态)
     * 
     * @param id
     *            指定物件编号
     * @param enchant
     *            指定强化值
     * @param count
     *            数量
     * @return true:刪除完成 false:刪除失败
     */
    public boolean consumeEnchantItem(final int id, final int enchant,
            final int count) {
        for (final L1ItemInstance item : this._items) {
            if (item.isEquipped()) { // 道具装备状态
                continue;
            }
            if ((item.getItemId() == id) && (item.getEnchantLevel() == enchant)) {
                this.removeItem(item);
                return true;
            }
        }
        return false;
    }

    /**
     * 删除指定编号物品及数量 (背包)
     * 
     * @param itemid
     *            - 删除物品的编号
     * @param count
     *            - 删除的数量
     * @return true:刪除完成 false:刪除失败
     */
    public boolean consumeItem(final int itemid, final int count) {

        // 数量小于0
        if (count <= 0) {
            return false;
        }

        // 可堆叠
        if (ItemTable.getInstance().getTemplate(itemid).isStackable()) {
            final L1ItemInstance item = this.findItemId(itemid);
            if ((item != null) && (item.getCount() >= count)) {
                this.removeItem(item, count);
                return true;
            }
        } else {
            final L1ItemInstance[] itemList = this.findItemsId(itemid);
            if (itemList.length == count) {
                for (int i = 0; i < count; i++) {
                    this.removeItem(itemList[i], 1);
                }
                return true;
            } else if (itemList.length > count) { // 所持有的数量超过指定数量
                final DataComparator<L1ItemInstance> dc = new DataComparator<L1ItemInstance>();
                Arrays.sort(itemList, dc); // 按照强化值 由低至高排列
                for (int i = 0; i < count; i++) {
                    this.removeItem(itemList[i], 1); // 先由强化值低的开始移除
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 查找未装备物品数量
     * 
     * @param id
     * @return 0 如果沒有找到。
     */
    public int countItems(final int id) {

        // 可堆叠
        if (ItemTable.getInstance().getTemplate(id).isStackable()) {
            final L1ItemInstance item = this.findItemId(id);
            if (item != null) {
                return item.getCount();
            }
        } else { // 不可堆叠
            final Object[] itemList = this.findItemsIdNotEquipped(id);
            return itemList.length;
        }
        return 0;
    }

    // _itemsから指定オブジェクトを削除(L1PcInstance、L1DwarfInstance、L1GroundInstance部分)
    /**
     * 物品资料删除
     * 
     * @param item
     * @return
     */
    public void deleteItem(final L1ItemInstance item) {
        // 删除钥匙记录
        if (item.getItem().getItemId() == 40312) {
            InnKeyTable.DeleteKey(item);
        }
        this._items.remove(item);
    }

    /**
     * 搜索指定物品 (不检查装备状态)
     * 
     * @param id
     *            道具的ID
     * @return 如果没有找到 null
     */
    public L1ItemInstance findItemId(final int id) {
        for (final L1ItemInstance item : this._items) {
            if (item.getItem().getItemId() == id) {
                return item;
            }
        }
        return null;
    }

    /**
     * 查找鉴定道具的名称ID
     * 
     * @param nameId
     * @return item null 如果沒有找到。
     */
    public L1ItemInstance findItemNameId(final String nameId) {
        for (final L1ItemInstance item : this._items) {
            if (nameId.equals(item.getItem().getIdentifiedNameId())) {
                return item;
            }
        }
        return null;
    }

    /**
     * 传出是否有该编号物品 (阵列)
     * 
     * @param itemId
     *            物品ID
     * @return 物品清单
     */
    public L1ItemInstance[] findItemsId(final int id) {
        final List<L1ItemInstance> itemList = Lists.newList();
        for (final L1ItemInstance item : this._items) {
            if (item.getItemId() == id) {
                itemList.add(item);
            }
        }
        return itemList.toArray(new L1ItemInstance[itemList.size()]);
    }

    /**
     * 未装备物品清单 (阵列)
     * 
     * @param itemId
     *            物品ID
     * @return 物品清单
     */
    public L1ItemInstance[] findItemsIdNotEquipped(final int id) {
        final List<L1ItemInstance> itemList = Lists.newList();
        for (final L1ItemInstance item : this._items) {
            if (item.getItemId() == id) {
                if (!item.isEquipped()) {
                    itemList.add(item);
                }
            }
        }
        return itemList.toArray(new L1ItemInstance[itemList.size()]);
    }

    /**
     * 搜索指定Key
     * 
     * @param id
     *            KeyId
     * @return 如果没有找到 null
     */
    public L1ItemInstance findKeyId(final int id) {
        for (final L1ItemInstance item : this._items) {
            if (item.getKeyId() == id) {
                return item;
            }
        }
        return null;
    }

    /**
     * 检查是否具有指定Objid物品
     * 
     * @param objectId
     * @return 如果没有找到 null
     */
    public L1ItemInstance getItem(final int objectId) {
        for (final Object itemObject : this._items) {
            final L1ItemInstance item = (L1ItemInstance) itemObject;
            if (item.getId() == objectId) {
                return item;
            }
        }
        return null;
    }

    /**
     * 背包内的全部道具
     * 
     * @return 全部道具
     */
    public List<L1ItemInstance> getItems() {
        return this._items;
    }

    /**
     * 背包内的道具总数
     * 
     * @return 道具总数
     */
    public int getSize() {
        if (this._items.isEmpty()) {
            return 0;
        }
        return this._items.size();
    }

    /**
     * 背包内的总重量
     * 
     * @return 总重量
     */
    public int getWeight() {
        int weight = 0;

        for (final L1ItemInstance item : this._items) {
            weight += item.getWeight();
        }

        return weight;
    }

    public void insertItem(final L1ItemInstance item) {
    }

    // 用于覆盖
    public void loadItems() {
    }

    /**
     * 如果该道具为损坏的道具・损耗度（包括 武器・防具）、损耗的正负 代表 武器・防具的损伤程度。
     * 
     * @param objectId
     *            道具的OBJID
     * @return
     */
    public L1ItemInstance receiveDamage(final int objectId) {
        final L1ItemInstance item = this.getItem(objectId);
        return this.receiveDamage(item);
    }

    /**
     * 如果该道具为损坏的道具・损耗度（包括 武器・防具）、损耗的正负 代表 武器・防具的损伤程度。
     * 
     * @param item
     *            道具
     * @return
     */
    public L1ItemInstance receiveDamage(final L1ItemInstance item) {
        return this.receiveDamage(item, 1);
    }

    /**
     * 如果该道具为损坏的道具・损耗度（包括 武器・防具）、损耗的正负 代表 武器・防具的损伤程度。
     * 
     * @param item
     *            道具
     * @param count
     *            损坏次数
     * @return
     */
    public L1ItemInstance receiveDamage(final L1ItemInstance item,
            final int count) {

        // 空道具
        if (item == null) {
            return null;
        }

        final int itemType = item.getItem().getType2(); // 道具类型
        final int currentDurability = item.get_durability(); // 耐久度

        if (((currentDurability == 0) && (itemType == 0))
                || (currentDurability < 0)) {
            item.set_durability(0);
            return null;
        }

        // 武器・防具的损耗度
        if (itemType == 0) {
            final int minDurability = (item.getEnchantLevel() + 5) * -1;
            int durability = currentDurability - count;
            if (durability < minDurability) {
                durability = minDurability;
            }
            if (currentDurability > durability) {
                item.set_durability(durability);
            }
        } else {
            final int maxDurability = item.getEnchantLevel() + 5;
            int durability = currentDurability + count;
            if (durability > maxDurability) {
                durability = maxDurability;
            }
            if (currentDurability < durability) {
                item.set_durability(durability);
            }
        }

        this.updateItem(item, L1PcInventory.COL_DURABILITY);
        return item;
    }

    /**
     * 恢复损坏的道具 (包括武器与防具)
     * 
     * @param item
     *            道具
     * @return
     */
    public L1ItemInstance recoveryDamage(final L1ItemInstance item) {

        // 空道具
        if (item == null) {
            return null;
        }

        final int itemType = item.getItem().getType2();
        final int durability = item.get_durability();

        if (((durability == 0) && (itemType != 0)) || (durability < 0)) {
            item.set_durability(0);
            return null;
        }

        if (itemType == 0) {
            // 增加耐久度。
            item.set_durability(durability + 1);
        } else {
            // 增加损伤度。
            item.set_durability(durability - 1);
        }

        this.updateItem(item, L1PcInventory.COL_DURABILITY);
        return item;
    }

    /**
     * 删除指定道具的指定数量 (指定Objid以及数量 刪除物品)
     * 
     * @param objectId
     * @param count
     * @return 实际删除数量
     */
    public int removeItem(final int objectId, final int count) {
        final L1ItemInstance item = this.getItem(objectId);
        return this.removeItem(item, count);
    }

    /**
     * 指定道具 (全部数量) 删除物品
     * 
     * @param item
     * @return 实际删除数量
     */
    public int removeItem(final L1ItemInstance item) {
        return this.removeItem(item, item.getCount());
    }

    /**
     * 指定道具及数量 删除物品
     * 
     * @param item
     * @param count
     * @return 实际删除数量
     */
    public int removeItem(final L1ItemInstance item, int count) {

        // 空道具
        if (item == null) {
            return 0;
        }

        if (!this._items.contains(item)) {
            return 0;
        }

        // 数量小于0
        if ((item.getCount() <= 0) || (count <= 0)) {
            return 0;
        }

        if (item.getCount() < count) {
            count = item.getCount();
        }
        if (item.getCount() == count) {
            final int itemId = item.getItem().getItemId();
            if ((itemId == 40314) || (itemId == 40316)) { // 宠物项圈
                PetTable.getInstance().deletePet(item.getId());
            } else if ((itemId >= 49016) && (itemId <= 49025)) { // 便箋
                final LetterTable lettertable = new LetterTable();
                lettertable.deleteLetter(item.getId());
            } else if ((itemId >= 41383) && (itemId <= 41400)) { // 家具
                for (final L1Object l1object : L1World.getInstance()
                        .getObject()) {
                    if (l1object instanceof L1FurnitureInstance) {
                        final L1FurnitureInstance furniture = (L1FurnitureInstance) l1object;
                        if (furniture.getItemObjId() == item.getId()) { // 已经退出了家具
                            FurnitureSpawnTable.getInstance().deleteFurniture(
                                    furniture);
                        }
                    }
                }
            } else if (item.getItemId() == 40309) {// Race Tickets
                RaceTicketTable.getInstance().deleteTicket(item.getId());
            }
            this.deleteItem(item);
            L1World.getInstance().removeObject(item);
        } else {
            item.setCount(item.getCount() - count);
            this.updateItem(item);
        }
        return count;
    }

    /**
	 * 
	 */
    public void shuffle() {
        Collections.shuffle(this._items);
    }

    /**
     * 加入新道具 (背包)
     * 
     * @param id
     *            道具ID
     * @param count
     *            道具数量
     * @return 如果加入失败 null
     */
    public synchronized L1ItemInstance storeItem(final int id, final int count) {

        try {

            // 数量小于0
            if (count <= 0) {
                return null;
            }

            // 取回道具资料
            final L1Item temp = ItemTable.getInstance().getTemplate(id);

            // 道具资料为空
            if (temp == null) {
                return null;
            }

            // 旅馆钥匙
            if (id == 40312) {
                final L1ItemInstance item = new L1ItemInstance(temp, count);

                if (this.findKeyId(id) == null) { // 新しく生成する必要がある場合のみIDの発行とL1Worldへの登録を行う
                    item.setId(IdFactory.getInstance().nextId());
                    L1World.getInstance().storeObject(item);
                }

                return this.storeItem(item);
            } else if (temp.isStackable()) {
                final L1ItemInstance item = new L1ItemInstance(temp, count);

                if (this.findItemId(id) == null) { // 新しく生成する必要がある場合のみIDの発行とL1Worldへの登録を行う
                    item.setId(IdFactory.getInstance().nextId());
                    L1World.getInstance().storeObject(item);
                }

                return this.storeItem(item);
            }

            // 该道具能不能叠加
            L1ItemInstance result = null;
            for (int i = 0; i < count; i++) {
                final L1ItemInstance item = new L1ItemInstance(temp, 1);
                item.setId(IdFactory.getInstance().nextId());
                L1World.getInstance().storeObject(item);
                this.storeItem(item);
                result = item;
            }
            // 返回最后(最近一次)的道具。改变可能会更好的定义方法，返回一个数组。
            return result;
        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }

    /**
     * 加入新道具 (背包) (道具 购买/交换)
     * 
     * @param item
     *            道具
     * @return 如果加入失败 null
     */
    public synchronized L1ItemInstance storeItem(final L1ItemInstance item) {

        try {

            // 空道具
            if (item == null) {
                return null;
            }

            // 数量小于0
            if (item.getCount() <= 0) {
                return null;
            }

            // 取回道具ID
            final int itemId = item.getItem().getItemId();

            // 是可堆叠道具
            if (item.isStackable()) {
                L1ItemInstance findItem = this.findItemId(itemId);
                if (itemId == 40309) { // Race Tickets (食人妖精竞赛票)
                    findItem = this.findItemNameId(item.getItem()
                            .getIdentifiedNameId());
                } else if (itemId == 40312) { // 旅馆钥匙
                    findItem = this.findKeyId(itemId);
                } else {
                    findItem = this.findItemId(itemId);
                }
                if (findItem != null) {
                    findItem.setCount(findItem.getCount() + item.getCount());
                    this.updateItem(findItem);
                    return findItem;
                }
            }

            if (itemId == 40309) {// Race Tickets (食人妖精竞赛票)
                String[] temp = item.getItem().getIdentifiedNameId().split(" ");
                temp = temp[temp.length - 1].split("-");
                final L1RaceTicket ticket = new L1RaceTicket();
                ticket.set_itemobjid(item.getId());
                ticket.set_round(Integer.parseInt(temp[0]));
                ticket.set_allotment_percentage(0.0);
                ticket.set_victory(0);
                ticket.set_runner_num(Integer.parseInt(temp[1]));
                RaceTicketTable.getInstance().storeNewTiket(ticket);
            }
            item.setX(this.getX());
            item.setY(this.getY());
            item.setMap(this.getMapId());

            // 取回最大可用次数
            int chargeCount = item.getItem().getMaxChargeCount();
            switch (itemId) {
                case 40006: // 创造怪物魔杖
                case 40007: // 闪电魔杖
                case 40008: // 变形魔杖
                case 140006: // 受祝福的 创造怪物魔杖
                case 140008: // 受祝福的 变形魔杖
                case 41401: // 移除家具魔杖
                    chargeCount -= Random.nextInt(5);
                    break;

                case 20383: // 军马头盔
                    chargeCount = 50;
                    break;

                default:
                    break;
            }

            item.setChargeCount(chargeCount);

            // light系列时间设置
            if ((item.getItem().getType2() == 0)
                    && (item.getItem().getType() == 2)) {
                item.setRemainingTime(item.getItem().getLightFuel());
            } else {
                item.setRemainingTime(item.getItem().getMaxUseTime());
            }

            item.setBless(item.getItem().getBless());

            // 登入钥匙记录
            if (item.getItem().getItemId() == 40312) {
                if (!InnKeyTable.checkey(item)) {
                    InnKeyTable.StoreKey(item);
                }
            }

            this._items.add(item);
            this.insertItem(item);
            return item;

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }

    // スタック可能なアイテムリストからnameIdと同じ値を持つitemを返す (取得鉴定道具名称)

    /**
     * 加入新道具 (背包) (道具 仓库存取/捡取/丢弃)
     * 
     * @param item
     *            道具
     * @return 如果加入失败 null
     */
    public synchronized L1ItemInstance storeTradeItem(final L1ItemInstance item) {

        try {

            // 空道具
            if (item == null) {
                return null;
            }

            // 数量小于0
            if (item.getCount() <= 0) {
                return null;
            }

            // 旅馆钥匙
            if (item.getItem().getItemId() == 40312) {
                final L1ItemInstance findItem = this.findKeyId(item.getKeyId()); // 检查钥匙编号是否相同
                if (findItem != null) {
                    findItem.setCount(findItem.getCount() + item.getCount());
                    this.updateItem(findItem);
                    return findItem;
                }
            } else if (item.isStackable()) {
                final L1ItemInstance findItem = this.findItemId(item.getItem()
                        .getItemId());
                if (findItem != null) {
                    findItem.setCount(findItem.getCount() + item.getCount());
                    this.updateItem(findItem);
                    return findItem;
                }
            }
            item.setX(this.getX());
            item.setY(this.getY());
            item.setMap(this.getMapId());

            // 登入钥匙记录
            if (item.getItem().getItemId() == 40312) {
                if (!InnKeyTable.checkey(item)) {
                    InnKeyTable.StoreKey(item);
                }
            }
            this._items.add(item);
            this.insertItem(item);
            return item;
        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
        return null;
    }

    /**
     * 物品转移
     * 
     * @param objectId
     *            转移对象的Objid
     * @param count
     *            移出的数量
     * @param inventory
     *            移出对象的背包
     * @return
     */
    public synchronized L1ItemInstance tradeItem(final int objectId,
            final int count, final L1Inventory inventory) {
        final L1ItemInstance item = this.getItem(objectId);
        return this.tradeItem(item, count, inventory);
    }

    /**
     * 物品转移
     * 
     * @param item
     *            转移的物品
     * @param count
     *            移出的数量
     * @param inventory
     *            移出对象的背包
     * @return 背包内新加入的道具
     */
    public synchronized L1ItemInstance tradeItem(final L1ItemInstance item,
            final int count, final L1Inventory inventory) {

        // 空道具
        if (item == null) {
            return null;
        }

        // 数量小于0
        if ((item.getCount() <= 0) || (count <= 0)) {
            return null;
        }

        // 装备中
        if (item.isEquipped()) {
            return null;
        }

        if (!this.checkItem(item.getItem().getItemId(), count)) {
            return null;
        }

        L1ItemInstance carryItem;

        if (item.getCount() <= count) {
            this.deleteItem(item);
            carryItem = item;
        } else {
            item.setCount(item.getCount() - count);
            this.updateItem(item);
            carryItem = ItemTable.getInstance().createItem(
                    item.getItem().getItemId());
            carryItem.setCount(count);
            carryItem.setEnchantLevel(item.getEnchantLevel());
            carryItem.setIdentified(item.isIdentified());
            carryItem.set_durability(item.get_durability());
            carryItem.setChargeCount(item.getChargeCount());
            carryItem.setRemainingTime(item.getRemainingTime());
            carryItem.setLastUsed(item.getLastUsed());
            carryItem.setBless(item.getBless());
            // 旅馆钥匙
            if (carryItem.getItem().getItemId() == 40312) {
                carryItem.setInnNpcId(item.getInnNpcId()); // 旅馆NPC
                carryItem.setKeyId(item.getKeyId()); // 钥匙编号
                carryItem.setHall(item.checkRoomOrHall()); // 房间或会议室
                carryItem.setDueTime(item.getDueTime()); // 租用时间
            }
        }
        return inventory.storeTradeItem(carryItem);
    }

    public void updateEnchantAccessory(final L1ItemInstance item,
            final int colmn) {
    }

    public void updateItem(final L1ItemInstance item) {
    }

    public void updateItem(final L1ItemInstance item, final int colmn) {
    }

}
