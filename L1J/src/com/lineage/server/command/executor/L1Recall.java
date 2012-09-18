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

import java.util.Collection;

import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.utils.collections.Lists;

public class L1Recall implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1Recall();
    }

    private L1Recall() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            Collection<L1PcInstance> targets = null;
            if (arg.equalsIgnoreCase("all")) {
                targets = L1World.getInstance().getAllPlayers();
            } else {
                targets = Lists.newList();
                final L1PcInstance tg = L1World.getInstance().getPlayer(arg);
                if (tg == null) {
                    pc.sendPackets(new S_SystemMessage("ID不存在。"));
                    return;
                }
                targets.add(tg);
            }

            for (final L1PcInstance target : targets) {
                if (target.isGm()) {
                    continue;
                }
                L1Teleport.teleportToTargetFront(target, pc, 2);
                pc.sendPackets(new S_SystemMessage((new StringBuilder())
                        .append(target.getName()).append("成功被您召唤回来。")
                        .toString()));
                target.sendPackets(new S_SystemMessage("您被召唤到GM身边。"));
            }
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage("请输入: " + cmdName + " all|玩家名称。"));
        }
    }
}
