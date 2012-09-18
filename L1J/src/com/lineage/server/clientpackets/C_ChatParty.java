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
package com.lineage.server.clientpackets;

import com.lineage.server.ClientThread;
import com.lineage.server.model.L1ChatParty;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Party;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来的聊天组队封包
 */
public class C_ChatParty extends ClientBasePacket {

    private static final String C_CHAT_PARTY = "[C] C_ChatParty";

    public C_ChatParty(final byte abyte0[], final ClientThread clientthread) {
        super(abyte0);

        final L1PcInstance pc = clientthread.getActiveChar();
        if (pc.isGhost()) {
            return;
        }

        final int type = this.readC();
        if (type == 0) { // /chatbanish 的命令
            final String name = this.readS();

            if (!pc.isInChatParty()) {
                pc.sendPackets(new S_ServerMessage(425)); // 没有加入聊天组队
                return;
            }
            if (!pc.getChatParty().isLeader(pc)) {
                pc.sendPackets(new S_ServerMessage(427)); // 只有队长可以踢人
                return;
            }
            final L1PcInstance targetPc = L1World.getInstance().getPlayer(name);
            if (targetPc == null) {
                pc.sendPackets(new S_ServerMessage(109)); // 没有叫%0的人。
                return;
            }
            if (pc.getId() == targetPc.getId()) {
                return;
            }

            for (final L1PcInstance member : pc.getChatParty().getMembers()) {
                if (member.getName().toLowerCase().equals(name.toLowerCase())) {
                    pc.getChatParty().kickMember(member);
                    return;
                }
            }
            pc.sendPackets(new S_ServerMessage(426, name)); // %0%d 不属于任何队伍。
        } else if (type == 1) { // /chatoutparty 的命令
            if (pc.isInChatParty()) {
                pc.getChatParty().leaveMember(pc);
            }
        } else if (type == 2) { // /chatparty 的命令
            final L1ChatParty chatParty = pc.getChatParty();
            if (pc.isInChatParty()) {
                pc.sendPackets(new S_Party("party", pc.getId(), chatParty
                        .getLeader().getName(), chatParty.getMembersNameList()));
            } else {
                pc.sendPackets(new S_ServerMessage(425)); // 您并没有参加任何队伍。
                // pc.sendPackets(new S_Party("party", pc.getId()));
            }
        }
    }

    @Override
    public String getType() {
        return C_CHAT_PARTY;
    }

}
