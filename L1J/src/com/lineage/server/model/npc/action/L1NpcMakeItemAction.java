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
package com.lineage.server.model.npc.action;

import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lineage.server.datatables.ItemTable;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1ObjectAmount;
import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.npc.L1NpcHtml;
import com.lineage.server.serverpackets.S_HowManyMake;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Item;
import com.lineage.server.utils.IterableElementList;
import com.lineage.server.utils.collections.Lists;

/**
 * NPC创造道具动作
 */
public class L1NpcMakeItemAction extends L1NpcXmlAction {

    private final List<L1ObjectAmount<Integer>> _materials = Lists.newList();

    private final List<L1ObjectAmount<Integer>> _items = Lists.newList();

    private final boolean _isAmountInputable;

    private final L1NpcAction _actionOnSucceed;

    private final L1NpcAction _actionOnFail;

    public L1NpcMakeItemAction(final Element element) {
        super(element);

        this._isAmountInputable = L1NpcXmlParser.getBoolAttribute(element,
                "AmountInputable", true);
        final NodeList list = element.getChildNodes();
        for (final Element elem : new IterableElementList(list)) {
            if (elem.getNodeName().equalsIgnoreCase("Material")) {
                final int id = Integer.valueOf(elem.getAttribute("ItemId"));
                final int amount = Integer.valueOf(elem.getAttribute("Amount"));
                this._materials.add(new L1ObjectAmount<Integer>(id, amount));
                continue;
            }
            if (elem.getNodeName().equalsIgnoreCase("Item")) {
                final int id = Integer.valueOf(elem.getAttribute("ItemId"));
                final int amount = Integer.valueOf(elem.getAttribute("Amount"));
                this._items.add(new L1ObjectAmount<Integer>(id, amount));
                continue;
            }
        }

        if (this._items.isEmpty() || this._materials.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Element elem = L1NpcXmlParser.getFirstChildElementByTagName(element,
                "Succeed");
        this._actionOnSucceed = elem == null ? null : new L1NpcListedAction(
                elem);
        elem = L1NpcXmlParser.getFirstChildElementByTagName(element, "Fail");
        this._actionOnFail = elem == null ? null : new L1NpcListedAction(elem);
    }

    /**
     * 指定されたインベントリ内に、素材が何セットあるか数える
     */
    private int countNumOfMaterials(final L1PcInventory inv) {
        int count = Integer.MAX_VALUE;
        for (final L1ObjectAmount<Integer> material : this._materials) {
            final int numOfSet = inv.countItems(material.getObject())
                    / material.getAmount();
            count = Math.min(count, numOfSet);
        }
        return count;
    }

    @Override
    public L1NpcHtml execute(final String actionName, final L1PcInstance pc,
            final L1Object obj, final byte[] args) {
        final int numOfMaterials = this.countNumOfMaterials(pc.getInventory());
        if ((1 < numOfMaterials) && this._isAmountInputable) {
            pc.sendPackets(new S_HowManyMake(obj.getId(), numOfMaterials,
                    actionName));
            return null;
        }
        return this.executeWithAmount(actionName, pc, obj, 1);
    }

    @Override
    public L1NpcHtml executeWithAmount(final String actionName,
            final L1PcInstance pc, final L1Object obj, final int amount) {
        final L1NpcInstance npc = (L1NpcInstance) obj;
        L1NpcHtml result = null;
        if (this.makeItems(pc, npc.getNpcTemplate().get_name(), amount)) {
            if (this._actionOnSucceed != null) {
                result = this._actionOnSucceed.execute(actionName, pc, obj,
                        new byte[0]);
            }
        } else {
            if (this._actionOnFail != null) {
                result = this._actionOnFail.execute(actionName, pc, obj,
                        new byte[0]);
            }
        }
        return result == null ? L1NpcHtml.HTML_CLOSE : result;
    }

    private boolean makeItems(final L1PcInstance pc, final String npcName,
            final int amount) {
        if (amount <= 0) {
            return false;
        }

        boolean isEnoughMaterials = true;
        for (final L1ObjectAmount<Integer> material : this._materials) {
            if (!pc.getInventory().checkItemNotEquipped(material.getObject(),
                    material.getAmount() * amount)) {
                final L1Item temp = ItemTable.getInstance().getTemplate(
                        material.getObject());
                pc.sendPackets(new S_ServerMessage(337, temp.getName()
                        + "("
                        + ((material.getAmount() * amount) - pc.getInventory()
                                .countItems(temp.getItemId())) + ")")); // \f1%0が不足しています。
                isEnoughMaterials = false;
            }
        }
        if (!isEnoughMaterials) {
            return false;
        }

        // 容量と重量の計算
        int countToCreate = 0; // アイテムの個数（纏まる物は1個）
        int weight = 0;

        for (final L1ObjectAmount<Integer> makingItem : this._items) {
            final L1Item temp = ItemTable.getInstance().getTemplate(
                    makingItem.getObject());
            if (temp.isStackable()) {
                if (!pc.getInventory().checkItem(makingItem.getObject())) {
                    countToCreate += 1;
                }
            } else {
                countToCreate += makingItem.getAmount() * amount;
            }
            weight += temp.getWeight() * (makingItem.getAmount() * amount)
                    / 1000;
        }
        // 容量確認
        if (pc.getInventory().getSize() + countToCreate > 180) {
            pc.sendPackets(new S_ServerMessage(263)); // \f1一人のキャラクターが持って歩けるアイテムは最大180個までです。
            return false;
        }
        // 重量確認
        if (pc.getMaxWeight() < pc.getInventory().getWeight() + weight) {
            pc.sendPackets(new S_ServerMessage(82)); // アイテムが重すぎて、これ以上持てません。
            return false;
        }

        for (final L1ObjectAmount<Integer> material : this._materials) {
            // 材料消費
            pc.getInventory().consumeItem(material.getObject(),
                    material.getAmount() * amount);
        }

        for (final L1ObjectAmount<Integer> makingItem : this._items) {
            final L1ItemInstance item = pc.getInventory().storeItem(
                    makingItem.getObject(), makingItem.getAmount() * amount);
            if (item != null) {
                String itemName = ItemTable.getInstance()
                        .getTemplate(makingItem.getObject()).getName();
                if (makingItem.getAmount() * amount > 1) {
                    itemName = itemName + " (" + makingItem.getAmount()
                            * amount + ")";
                }
                pc.sendPackets(new S_ServerMessage(143, npcName, itemName)); // \f1%0が%1をくれました。
            }
        }
        return true;
    }

}
