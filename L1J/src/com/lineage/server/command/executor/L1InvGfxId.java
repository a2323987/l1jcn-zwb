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
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;

public class L1InvGfxId implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1InvGfxId();
    }

    private L1InvGfxId() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final StringTokenizer st = new StringTokenizer(arg);
            final int gfxid = Integer.parseInt(st.nextToken(), 10);
            final int count = Integer.parseInt(st.nextToken(), 10);
            for (int i = 0; i < count; i++) {
                final L1ItemInstance item = ItemTable.getInstance().createItem(
                        40005);
                item.getItem().setGfxId(gfxid + i);
                item.getItem().setName(String.valueOf(gfxid + i));
                pc.getInventory().storeItem(item);
            }
        } catch (final Exception exception) {
            pc.sendPackets(new S_SystemMessage(cmdName + " 请输入 id 出现的数量。"));
        }
    }
}
