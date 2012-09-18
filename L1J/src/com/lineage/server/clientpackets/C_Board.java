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
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1AuctionBoardInstance;
import com.lineage.server.model.Instance.L1BoardInstance;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket, C_Board

/**
 * 收到由客户端传送打开公告栏的封包
 */
public class C_Board extends ClientBasePacket {

    private static final String C_BOARD = "[C] C_Board";

    public C_Board(final byte abyte0[], final ClientThread client) {
        super(abyte0);
        final int objectId = this.readD();
        final L1Object obj = L1World.getInstance().findObject(objectId);
        if (!this.isBoardInstance(obj)) {
            return; // 对象不是布告栏停止
        }
        obj.onAction(client.getActiveChar());
    }

    @Override
    public String getType() {
        return C_BOARD;
    }

    private boolean isBoardInstance(final L1Object obj) {
        return ((obj instanceof L1BoardInstance) || (obj instanceof L1AuctionBoardInstance));
    }

}
