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

import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.datatables.NpcTable;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.utils.L1SpawnUtil;

public class L1SpawnCmd implements L1CommandExecutor {
    private static Logger _log = Logger.getLogger(L1SpawnCmd.class.getName());

    public static L1CommandExecutor getInstance() {
        return new L1SpawnCmd();
    }

    private L1SpawnCmd() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final StringTokenizer tok = new StringTokenizer(arg);
            final String nameId = tok.nextToken();
            int count = 1;
            if (tok.hasMoreTokens()) {
                count = Integer.parseInt(tok.nextToken());
            }
            int randomrange = 0;
            if (tok.hasMoreTokens()) {
                randomrange = Integer.parseInt(tok.nextToken(), 10);
            }
            final int npcid = this.parseNpcId(nameId);

            final L1Npc npc = NpcTable.getInstance().getTemplate(npcid);
            if (npc == null) {
                pc.sendPackets(new S_SystemMessage("找不到符合条件的NPC。"));
                return;
            }
            for (int i = 0; i < count; i++) {
                L1SpawnUtil.spawn(pc, npcid, randomrange, 0);
            }
            final String msg = String.format("%s(%d) (%d) 召唤了。 (范围:%d)",
                    npc.get_name(), npcid, count, randomrange);
            pc.sendPackets(new S_SystemMessage(msg));
        } catch (final NoSuchElementException e) {
            this.sendErrorMessage(pc, cmdName);
        } catch (final NumberFormatException e) {
            this.sendErrorMessage(pc, cmdName);
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            pc.sendPackets(new S_SystemMessage(cmdName + " 内部错误。"));
        }
    }

    private int parseNpcId(final String nameId) {
        int npcid = 0;
        try {
            npcid = Integer.parseInt(nameId);
        } catch (final NumberFormatException e) {
            npcid = NpcTable.getInstance().findNpcIdByNameWithoutSpace(nameId);
        }
        return npcid;
    }

    private void sendErrorMessage(final L1PcInstance pc, final String cmdName) {
        final String errorMsg = "请输入: " + cmdName + " npcid|name [数量] [范围] 。";
        pc.sendPackets(new S_SystemMessage(errorMsg));
    }
}
