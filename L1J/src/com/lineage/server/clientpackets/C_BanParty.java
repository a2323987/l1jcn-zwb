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
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 收到由客户端传来离开组队的封包
 */
public class C_BanParty extends ClientBasePacket {

    private static final String C_BAN_PARTY = "[C] C_BanParty";

    public C_BanParty(final byte decrypt[], final ClientThread client)
            throws Exception {
        super(decrypt);
        final String s = this.readS();

        final L1PcInstance player = client.getActiveChar();
        if (!player.getParty().isLeader(player)) {
            // 不是组队队长
            player.sendPackets(new S_ServerMessage(427)); // 只有领导者才有驱逐队伍成员的权力。
            return;
        }

        for (final L1PcInstance member : player.getParty().getMembers()) {
            if (member.getName().toLowerCase().equals(s.toLowerCase())) {
                player.getParty().kickMember(member);
                return;
            }
        }

        player.sendPackets(new S_ServerMessage(426, s)); // %0%d 不属于任何队伍。
    }

    @Override
    public String getType() {
        return C_BAN_PARTY;
    }

}
