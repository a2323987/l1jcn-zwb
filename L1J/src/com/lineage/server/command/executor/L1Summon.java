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

import com.lineage.server.datatables.NpcTable;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.templates.L1Npc;

public class L1Summon implements L1CommandExecutor {
    public static L1Summon getInstance() {
        return new L1Summon();
    }

    private L1Summon() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final StringTokenizer tok = new StringTokenizer(arg);
            String nameid = tok.nextToken();
            int npcid = 0;
            try {
                npcid = Integer.parseInt(nameid);
            } catch (final NumberFormatException e) {
                npcid = NpcTable.getInstance().findNpcIdByNameWithoutSpace(
                        nameid);
                if (npcid == 0) {
                    pc.sendPackets(new S_SystemMessage("找不到符合条件的NPC。"));
                    return;
                }
            }
            int count = 1;
            if (tok.hasMoreTokens()) {
                count = Integer.parseInt(tok.nextToken());
            }
            final L1Npc npc = NpcTable.getInstance().getTemplate(npcid);
            for (int i = 0; i < count; i++) {
                final L1SummonInstance summonInst = new L1SummonInstance(npc,
                        pc);
                summonInst.setPetcost(0);
            }
            nameid = NpcTable.getInstance().getTemplate(npcid).get_name();
            pc.sendPackets(new S_SystemMessage(nameid + "(ID:" + npcid + ") ("
                    + count + ") 召唤了。"));
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage("请输入" + cmdName
                    + " npcid|name [数量] 。"));
        }
    }
}
