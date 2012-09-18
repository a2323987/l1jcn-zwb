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
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.datatables.NpcSpawnTable;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.datatables.SpawnTable;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.utils.L1SpawnUtil;

public class L1InsertSpawn implements L1CommandExecutor {
    private static Logger _log = Logger
            .getLogger(L1InsertSpawn.class.getName());

    public static L1CommandExecutor getInstance() {
        return new L1InsertSpawn();
    }

    private L1InsertSpawn() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        String msg = null;

        try {
            final StringTokenizer tok = new StringTokenizer(arg);
            final String type = tok.nextToken();
            final int npcId = Integer.parseInt(tok.nextToken().trim());
            final L1Npc template = NpcTable.getInstance().getTemplate(npcId);

            if (template == null) {
                msg = "找不到符合条件的NPC。";
                return;
            }
            if (type.equalsIgnoreCase("mob")) {
                if (!template.getImpl().equals("L1Monster")) {
                    msg = "指定的NPC不是L1Monster类型。";
                    return;
                }
                SpawnTable.storeSpawn(pc, template);
            } else if (type.equalsIgnoreCase("npc")) {
                NpcSpawnTable.getInstance().storeSpawn(pc, template);
            }
            L1SpawnUtil.spawn(pc, npcId, 0, 0);
            msg = new StringBuilder().append(template.get_name())
                    .append(" (" + npcId + ") ").append("新增到资料库中。").toString();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, "", e);
            msg = "请输入 : " + cmdName + " mob|npc NPCID 。";
        } finally {
            if (msg != null) {
                pc.sendPackets(new S_SystemMessage(msg));
            }
        }
    }
}
