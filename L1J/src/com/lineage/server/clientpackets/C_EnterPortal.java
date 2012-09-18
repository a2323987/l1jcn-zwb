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
import com.lineage.server.model.Dungeon;
import com.lineage.server.model.Instance.L1PcInstance;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来进入传点的封包
 */
public class C_EnterPortal extends ClientBasePacket {

    private static final String C_ENTER_PORTAL = "[C] C_EnterPortal";

    public C_EnterPortal(final byte abyte0[], final ClientThread client)
            throws Exception {
        super(abyte0);
        final int locx = this.readH();
        final int locy = this.readH();
        final L1PcInstance pc = client.getActiveChar();
        if (pc.isTeleport()) { // 传送中
            return;
        }
        // 取得传送的点
        Dungeon.getInstance().dg(locx, locy, pc.getMap().getId(), pc);
    }

    @Override
    public String getType() {
        return C_ENTER_PORTAL;
    }
}
