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

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.serverpackets.S_SystemMessage;

/**
 * GM指令：增加金币
 */
public class L1Adena implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1Adena();
    }

    private L1Adena() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final StringTokenizer stringtokenizer = new StringTokenizer(arg);
            final int count = Integer.parseInt(stringtokenizer.nextToken());

            final L1ItemInstance adena = pc.getInventory().storeItem(
                    L1ItemId.ADENA, count);
            if (adena != null) {
                pc.sendPackets(new S_SystemMessage((new StringBuilder())
                        .append(count).append(" 金币产生。").toString()));
            }
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage((new StringBuilder()).append(
                    "请输入 .adena 数量||.金币  数量。").toString()));
        }
    }
}
