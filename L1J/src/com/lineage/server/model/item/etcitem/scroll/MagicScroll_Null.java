package com.lineage.server.model.item.etcitem.scroll;

import com.lineage.server.datatables.ItemTable;
import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Skills;

/**
 * 空的魔法卷轴LV1 - 40090 空的魔法卷轴LV2 - 40091 空的魔法卷轴LV3 - 40092 空的魔法卷轴LV4 - 40093
 * 空的魔法卷轴LV5 - 40094
 * 
 * @author jrwz
 */
public class MagicScroll_Null implements ItemExecutor {

    public static ItemExecutor get() {
        return new MagicScroll_Null();
    }

    private MagicScroll_Null() {
    }

    /**
     * 道具执行
     * 
     * @param data
     *            参数
     * @param pc
     *            对象
     * @param item
     *            道具
     */
    @Override
    public void execute(final int[] data, final L1PcInstance pc,
            final L1ItemInstance item) {

        final int blanksc_skillid = data[0];
        final int itemId = item.getItemId();

        // 职业必须是 (魔法师)
        if (pc.isWizard()) {
            if (((itemId == 40090) && (blanksc_skillid <= 7)) // 空的魔法卷轴(等级1)可写1级以下的魔法
                    || ((itemId == 40091) && (blanksc_skillid <= 15)) // 空的魔法卷轴(等级2)可写2级以下的魔法
                    || ((itemId == 40092) && (blanksc_skillid <= 22)) // 空的魔法卷轴(等级3)可写3级以下的魔法
                    || ((itemId == 40093) && (blanksc_skillid <= 31)) // 空的魔法卷轴(等级4)可写4级以下的魔法
                    || ((itemId == 40094) && (blanksc_skillid <= 39)) // 空的魔法卷轴(等级5)可写5级以下的魔法
            ) {

                final L1ItemInstance spellsc = ItemTable.getInstance()
                        .createItem(40859 + blanksc_skillid);
                if (spellsc != null) {
                    if (pc.getInventory().checkAddItem(spellsc, 1) == L1Inventory.OK) {
                        final L1Skills l1skills = SkillsTable.getInstance()
                                .getTemplate(blanksc_skillid + 1); // blanksc_skillid
                                                                   // 0
                        if (pc.getCurrentHp() + 1 < l1skills.getHpConsume() + 1) {
                            pc.sendPackets(new S_ServerMessage(279)); // \f1因体力不足而无法使用魔法。
                            return;
                        }
                        if (pc.getCurrentMp() < l1skills.getMpConsume()) {
                            pc.sendPackets(new S_ServerMessage(278)); // \f1因魔力不足而无法使用魔法。
                            return;
                        }
                        if (l1skills.getItemConsumeId() != 0) { // 检查材料数量
                            if (!pc.getInventory().checkItem(
                                    l1skills.getItemConsumeId(),
                                    l1skills.getItemConsumeCount())) { // 必要材料不足
                                pc.sendPackets(new S_ServerMessage(299)); // \f1施放魔法所需材料不足。
                                return;
                            }
                        }
                        pc.setCurrentHp(pc.getCurrentHp()
                                - l1skills.getHpConsume());
                        pc.setCurrentMp(pc.getCurrentMp()
                                - l1skills.getMpConsume());
                        int lawful = pc.getLawful() + l1skills.getLawful();
                        if (lawful > 32767) {
                            lawful = 32767;
                        }
                        if (lawful < -32767) {
                            lawful = -32767;
                        }
                        pc.setLawful(lawful);
                        if (l1skills.getItemConsumeId() != 0) { // 检查材料数量
                            pc.getInventory().consumeItem(
                                    l1skills.getItemConsumeId(),
                                    l1skills.getItemConsumeCount());
                        }
                        pc.getInventory().removeItem(item, 1);
                        pc.getInventory().storeItem(spellsc);
                    }
                }
            } else {
                pc.sendPackets(new S_ServerMessage(591)); // \f1这张卷轴太易碎所以无法纪录魔法。
            }
        } else {
            pc.sendPackets(new S_ServerMessage(264)); // \f1你的职业无法使用此道具。
        }
    }
}
