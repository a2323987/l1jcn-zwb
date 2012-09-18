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

import com.lineage.server.datatables.IpTable;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;

/**
 * GM指令：禁止登入
 */
public class L1BanIp implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1BanIp();
    }

    private L1BanIp() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final StringTokenizer stringtokenizer = new StringTokenizer(arg);
            // IPを指定
            final String s1 = stringtokenizer.nextToken();

            // add/delを指定(しなくてもOK)
            String s2 = null;
            try {
                s2 = stringtokenizer.nextToken();
            } catch (final Exception e) {
            }

            final IpTable iptable = IpTable.getInstance();
            final boolean isBanned = iptable.isBannedIp(s1);

            for (final L1PcInstance tg : L1World.getInstance().getAllPlayers()) {
                if (s1.equals(tg.getNetConnection().getIp())) {
                    final String msg = new StringBuilder().append("IP:")
                            .append(s1).append(" 连线中的角色名称:")
                            .append(tg.getName()).toString();
                    pc.sendPackets(new S_SystemMessage(msg));
                }
            }

            if ("add".equalsIgnoreCase(s2) && !isBanned) {
                iptable.banIp(s1); // BANリストへIPを加える
                final String msg = new StringBuilder().append("IP:").append(s1)
                        .append(" 被新增到封锁名单。").toString();
                pc.sendPackets(new S_SystemMessage(msg));
            } else if ("del".equalsIgnoreCase(s2) && isBanned) {
                if (iptable.liftBanIp(s1)) { // BANリストからIPを削除する
                    final String msg = new StringBuilder().append("IP:")
                            .append(s1).append(" 已从封锁名单中删除。").toString();
                    pc.sendPackets(new S_SystemMessage(msg));
                }
            } else {
                // BANの确认
                if (isBanned) {
                    final String msg = new StringBuilder().append("IP:")
                            .append(s1).append(" 已被登记在封锁名单中。").toString();
                    pc.sendPackets(new S_SystemMessage(msg));
                } else {
                    final String msg = new StringBuilder().append("IP:")
                            .append(s1).append(" 尚未被登记在封锁名单中。").toString();
                    pc.sendPackets(new S_SystemMessage(msg));
                }
            }
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage("请输入 " + cmdName
                    + " IP [ add | del ]。"));
        }
    }
}
