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
import com.lineage.server.model.L1CastleLocation;
import com.lineage.server.model.L1HouseLocation;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1BookMark;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到客戶端传来新增书签的封包
 */
public class C_AddBookmark extends ClientBasePacket {

    private static final String C_ADD_BOOKMARK = "[C] C_AddBookmark";

    public C_AddBookmark(final byte[] decrypt, final ClientThread client) {
        super(decrypt);
        final String s = this.readS();

        final L1PcInstance pc = client.getActiveChar();
        if (pc.isGhost()) {
            return;
        }

        if (pc.getMap().isMarkable() || pc.isGm()) {
            if ((L1CastleLocation.checkInAllWarArea(pc.getX(), pc.getY(),
                    pc.getMapId()) || L1HouseLocation.isInHouse(pc.getX(),
                    pc.getY(), pc.getMapId()))
                    && !pc.isGm()) {
                pc.sendPackets(new S_ServerMessage(214)); // \f1这个地点不能够标记。
            } else {
                L1BookMark.addBookmark(pc, s);
            }
        } else {
            pc.sendPackets(new S_ServerMessage(214)); // \f1这个地点不能够标记。
        }
    }

    @Override
    public String getType() {
        return C_ADD_BOOKMARK;
    }
}
