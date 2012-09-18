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
import com.lineage.server.utils.FaceToFace;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来交易的封包
 */
public class C_Trade extends ClientBasePacket {

    private static final String C_TRADE = "[C] C_Trade";

    public C_Trade(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);

        final L1PcInstance player = clientthread.getActiveChar();
        if (player.isGhost()) {
            return;
        }
        final L1PcInstance target = FaceToFace.faceToFace(player);
        if (target != null) {
            if (!target.isParalyzed()) {
                player.setTradeID(target.getId()); // 保存对象的ID
                target.setTradeID(player.getId());
                target.sendPackets(new S_Message_YN(252, player.getName())); // \f2%0%s
                                                                             // 要与你交易。愿不愿交易？
                                                                             // (Y/N)
            }
        }
    }

    @Override
    public String getType() {
        return C_TRADE;
    }
}
