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

import com.lineage.server.datatables.ExpTable;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.utils.IntRange;

public class L1Level implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1Level();
    }

    private L1Level() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final StringTokenizer tok = new StringTokenizer(arg);
            final int level = Integer.parseInt(tok.nextToken());
            if (level == pc.getLevel()) {
                return;
            }
            if (!IntRange.includes(level, 1, 110)) {
                pc.sendPackets(new S_SystemMessage("请在1-110范围內指定"));
                return;
            }
            pc.setExp(ExpTable.getExpByLevel(level));
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage("请输入 : " + cmdName + " lv "));
        }
    }
}
