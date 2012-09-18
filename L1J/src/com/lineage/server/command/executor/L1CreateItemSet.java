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
package com.lineage.server.command.executor;

import java.util.List;
import java.util.StringTokenizer;

import com.lineage.server.GMCommandsConfig;
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.templates.L1Item;
import com.lineage.server.templates.L1ItemSetItem;

/**
 * GM指令：创立套装
 */
public class L1CreateItemSet implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1CreateItemSet();
    }

    private L1CreateItemSet() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final String name = new StringTokenizer(arg).nextToken();
            final List<L1ItemSetItem> list = GMCommandsConfig.ITEM_SETS
                    .get(name);
            if (list == null) {
                pc.sendPackets(new S_SystemMessage(name + " 是未定义的套装。"));
                return;
            }
            for (final L1ItemSetItem item : list) {
                final L1Item temp = ItemTable.getInstance().getTemplate(
                        item.getId());
                if (!temp.isStackable() && (0 != item.getEnchant())) {
                    for (int i = 0; i < item.getAmount(); i++) {
                        final L1ItemInstance inst = ItemTable.getInstance()
                                .createItem(item.getId());
                        inst.setEnchantLevel(item.getEnchant());
                        pc.getInventory().storeItem(inst);
                    }
                } else {
                    pc.getInventory().storeItem(item.getId(), item.getAmount());
                }
            }
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage("请输入 .itemset 套装名称。"));
        }
    }
}
