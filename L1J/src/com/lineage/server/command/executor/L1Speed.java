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

import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.etcitem.UseSpeedPotion_1;
import com.lineage.server.model.item.etcitem.UseSpeedPotion_2_Brave;
import com.lineage.server.model.item.etcitem.UseSpeedPotion_3;
import com.lineage.server.serverpackets.S_SystemMessage;

public class L1Speed implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1Speed();
    }

    private L1Speed() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            UseSpeedPotion_1.get().useItem(pc, null, 0, 0, 3600, 191);
            UseSpeedPotion_2_Brave.get().useItem(pc, null, 0, 0, 3600, 751);
            UseSpeedPotion_3.get().useItem(pc, null, 0, 0, 600, 8031);
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage(".speed 指令错误"));
        }
    }
}
