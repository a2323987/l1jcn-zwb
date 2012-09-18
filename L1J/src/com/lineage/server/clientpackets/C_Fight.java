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
import com.lineage.server.serverpackets.S_Message_YN;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.FaceToFace;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来决斗的封包
 */
public class C_Fight extends ClientBasePacket {

    private static final String C_FIGHT = "[C] C_Fight";

    public C_Fight(final byte abyte0[], final ClientThread client)
            throws Exception {
        super(abyte0);

        final L1PcInstance pc = client.getActiveChar();
        if (pc.isGhost()) {
            return;
        }
        final L1PcInstance target = FaceToFace.faceToFace(pc);
        if (target != null) {
            if (!target.isParalyzed()) {
                if (pc.getFightId() != 0) {
                    pc.sendPackets(new S_ServerMessage(633)); // \f1你已经与其他人决斗中。
                    return;
                } else if (target.getFightId() != 0) {
                    target.sendPackets(new S_ServerMessage(634)); // \f11对方已经与其他人决斗中。
                    return;
                }
                pc.setFightId(target.getId());
                target.setFightId(pc.getId());
                target.sendPackets(new S_Message_YN(630, pc.getName())); // %0%s
                                                                         // 要与你决斗。你是否同意？(Y/N)
            }
        }
    }

    @Override
    public String getType() {
        return C_FIGHT;
    }

}
