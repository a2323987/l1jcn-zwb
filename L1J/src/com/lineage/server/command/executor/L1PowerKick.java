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

import com.lineage.server.datatables.IpTable;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Disconnect;
import com.lineage.server.serverpackets.S_SystemMessage;

public class L1PowerKick implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1PowerKick();
    }

    private L1PowerKick() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final L1PcInstance target = L1World.getInstance().getPlayer(arg);

            final IpTable iptable = IpTable.getInstance();
            if (target != null) {
                iptable.banIp(target.getNetConnection().getIp()); // 加入IP至BAN名单
                pc.sendPackets(new S_SystemMessage((new StringBuilder())
                        .append(target.getName()).append("被您强制踢除游戏并封锁IP。")
                        .toString()));
                target.sendPackets(new S_Disconnect());
            } else {
                pc.sendPackets(new S_SystemMessage("您指定的角色名称不存在。"));
            }
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage("请输入 : " + cmdName + " 玩家名称。"));
        }
    }
}
