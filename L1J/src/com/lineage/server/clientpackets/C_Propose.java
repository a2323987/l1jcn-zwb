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
 * 处理收到由客户端传来结婚的封包
 */
public class C_Propose extends ClientBasePacket {

    private static final String C_PROPOSE = "[C] C_Propose";

    public C_Propose(final byte abyte0[], final ClientThread clientthread) {
        super(abyte0);
        final int c = this.readC();

        final L1PcInstance pc = clientthread.getActiveChar();
        if (c == 0) { // /propose（/结婚）
            if (pc.isGhost()) {
                return;
            }
            final L1PcInstance target = FaceToFace.faceToFace(pc);
            if (target != null) {
                if (pc.getPartnerId() != 0) {
                    pc.sendPackets(new S_ServerMessage(657)); // \f1你(你)己经结婚。
                    return;
                }
                if (target.getPartnerId() != 0) {
                    pc.sendPackets(new S_ServerMessage(658)); // \f1你(你)的对象已经结婚了。
                    return;
                }
                if (pc.get_sex() == target.get_sex()) {
                    pc.sendPackets(new S_ServerMessage(661)); // \f1结婚对象性别必须和您不同。
                    return;
                }
                if ((pc.getX() >= 33974) && (pc.getX() <= 33976)
                        && (pc.getY() >= 33362) && (pc.getY() <= 33365)
                        && (pc.getMapId() == 4) && (target.getX() >= 33974)
                        && (target.getX() <= 33976) && (target.getY() >= 33362)
                        && (target.getY() <= 33365) && (target.getMapId() == 4)) {
                    target.setTempID(pc.getId()); // 暂时储存对象的角色ID
                    target.sendPackets(new S_Message_YN(654, pc.getName())); // %0
                                                                             // 向你(你)求婚，你(你)答应吗?
                }
            }
        } else if (c == 1) { // /divorce（/离婚）
            if (pc.getPartnerId() == 0) {
                pc.sendPackets(new S_ServerMessage(662)); // \f1你(你)目前未婚。
                return;
            }
            pc.sendPackets(new S_Message_YN(653, "")); // 若你离婚，你的结婚戒指将会消失。你决定要离婚吗？(Y/N)
        }
    }

    @Override
    public String getType() {
        return C_PROPOSE;
    }
}
