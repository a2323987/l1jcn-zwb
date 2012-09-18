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
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Pledge;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来血盟的封包
 */
public class C_Pledge extends ClientBasePacket {

    private static final String C_PLEDGE = "[C] C_Pledge";

    public C_Pledge(final byte abyte0[], final ClientThread clientthread) {
        super(abyte0);
        final L1PcInstance pc = clientthread.getActiveChar();

        if (pc.getClanid() > 0) {
            final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
            if (pc.isCrown() && (pc.getId() == clan.getLeaderId())) {
                pc.sendPackets(new S_Pledge("pledgeM", pc.getId(), clan
                        .getClanName(), clan.getOnlineMembersFPWithRank(), clan
                        .getAllMembersFPWithRank()));
            } else {
                pc.sendPackets(new S_Pledge("pledge", pc.getId(), clan
                        .getClanName(), clan.getOnlineMembersFP()));
            }
        } else {
            pc.sendPackets(new S_ServerMessage(1064)); // 不属于血盟。
            // pc.sendPackets(new S_Pledge("pledge", pc.getId()));
        }
    }

    @Override
    public String getType() {
        return C_PLEDGE;
    }

}
