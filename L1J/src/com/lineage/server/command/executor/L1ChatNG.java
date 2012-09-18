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

import static com.lineage.server.model.skill.L1SkillId.STATUS_CHAT_PROHIBITED;

import java.util.StringTokenizer;

import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillIconGFX;
import com.lineage.server.serverpackets.S_SystemMessage;

/**
 * GM指令：禁言
 */
public class L1ChatNG implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1ChatNG();
    }

    private L1ChatNG() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final StringTokenizer st = new StringTokenizer(arg);
            final String name = st.nextToken();
            final int time = Integer.parseInt(st.nextToken());

            final L1PcInstance tg = L1World.getInstance().getPlayer(name);

            if (tg != null) {
                tg.setSkillEffect(STATUS_CHAT_PROHIBITED, time * 60 * 1000);
                tg.sendPackets(new S_SkillIconGFX(36, time * 60));
                tg.sendPackets(new S_ServerMessage(286, String.valueOf(time))); // \f3因你不正当的行为而
                                                                                // %0分钟之内无法交谈。
                pc.sendPackets(new S_ServerMessage(287, name)); // 你已经被禁止交谈%0
                                                                // 分钟。
            }
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage("请输入 " + cmdName
                    + " 玩家名称 时间(分)。"));
        }
    }
}
