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

import java.util.StringTokenizer;

import com.lineage.server.datatables.ItemTable;
import com.lineage.server.model.L1DwarfInventory;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.templates.L1Item;

public class L1Present implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1Present();
    }

    private L1Present() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final StringTokenizer st = new StringTokenizer(arg);
            final String account = st.nextToken();
            final int itemid = Integer.parseInt(st.nextToken(), 10);
            final int enchant = Integer.parseInt(st.nextToken(), 10);
            final int count = Integer.parseInt(st.nextToken(), 10);

            final L1Item temp = ItemTable.getInstance().getTemplate(itemid);
            if (temp == null) {
                pc.sendPackets(new S_SystemMessage("不存在的道具编号。"));
                return;
            }

            L1DwarfInventory.present(account, itemid, enchant, count);
            pc.sendPackets(new S_SystemMessage(temp.getIdentifiedNameId()
                    + "数量" + count + "个发送出去了。", true));
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage("请输入 : "
                    + ".present 帐号 道具编号 数量 强化等级。（* 等于所有帐号）"));
        }
    }
}
