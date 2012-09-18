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
import com.lineage.server.model.Instance.L1BoardInstance;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 收到由客户端传送读取公告栏的封包
 */
public class C_BoardRead extends ClientBasePacket {

    private static final String C_BOARD_READ = "[C] C_BoardRead";

    public C_BoardRead(final byte decrypt[], final ClientThread client) {
        super(decrypt);
        final int objId = this.readD();
        final int topicNumber = this.readD();
        final L1Object obj = L1World.getInstance().findObject(objId);
        final L1BoardInstance board = (L1BoardInstance) obj;
        board.onActionRead(client.getActiveChar(), topicNumber);
    }

    @Override
    public String getType() {
        return C_BOARD_READ;
    }

}
