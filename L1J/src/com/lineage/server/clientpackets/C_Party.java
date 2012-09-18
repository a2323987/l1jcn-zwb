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
import com.lineage.server.model.L1Party;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Party;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来队伍的封包
 */
public class C_Party extends ClientBasePacket {

    private static final String C_PARTY = "[C] C_Party";

    public C_Party(final byte abyte0[], final ClientThread clientthread) {
        super(abyte0);
        final L1PcInstance pc = clientthread.getActiveChar();
        if (pc.isGhost()) {
            return;
        }
        final L1Party party = pc.getParty();
        if (pc.isInParty()) {
            pc.sendPackets(new S_Party("party", pc.getId(), party.getLeader()
                    .getName(), party.getMembersNameList()));
        } else {
            pc.sendPackets(new S_ServerMessage(425)); // 您并没有参加任何队伍。
            // pc.sendPackets(new S_Party("party", pc
            // .getId()));
        }
    }

    @Override
    public String getType() {
        return C_PARTY;
    }

}
