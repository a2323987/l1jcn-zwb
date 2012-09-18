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
import com.lineage.server.serverpackets.S_PrivateShop;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来商店清单的封包
 */
public class C_ShopList extends ClientBasePacket {

    private static final String C_SHOP_LIST = "[C] C_ShopList";

    public C_ShopList(final byte abyte0[], final ClientThread clientthread) {
        super(abyte0);

        final int type = this.readC();
        final int objectId = this.readD();

        final L1PcInstance pc = clientthread.getActiveChar();
        if (pc.isGhost()) {
            return;
        }

        pc.sendPackets(new S_PrivateShop(pc, objectId, type));
    }

    @Override
    public String getType() {
        return C_SHOP_LIST;
    }

}
