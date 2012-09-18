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

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来维持连线的封包
 */
public class C_KeepALIVE extends ClientBasePacket {

    private static final String C_KEEP_ALIVE = "[C] C_KeepALIVE";

    public C_KeepALIVE(final byte decrypt[], final ClientThread client) {
        super(decrypt);
        // XXX:发送GameTime（也许我应该使用它，因为事情已经到了发送数据的三个字节）
        // L1PcInstance pc = client.getActiveChar();
        // pc.sendPackets(new S_GameTime());
    }

    @Override
    public String getType() {
        return C_KEEP_ALIVE;
    }
}
