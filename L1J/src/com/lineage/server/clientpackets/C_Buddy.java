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
import com.lineage.server.datatables.BuddyTable;
import com.lineage.server.model.L1Buddy;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Buddy;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 收到由客户端传来取得好友名单的封包
 */
public class C_Buddy extends ClientBasePacket {

    private static final String C_BUDDY = "[C] C_Buddy";

    public C_Buddy(final byte abyte0[], final ClientThread clientthread) {
        super(abyte0);
        final L1PcInstance pc = clientthread.getActiveChar();
        final L1Buddy buddy = BuddyTable.getInstance()
                .getBuddyTable(pc.getId());
        pc.sendPackets(new S_Buddy(pc.getId(), buddy));
    }

    @Override
    public String getType() {
        return C_BUDDY;
    }

}
