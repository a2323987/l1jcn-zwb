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

import java.util.logging.Logger;

import com.lineage.server.ClientThread;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.templates.L1BoardTopic;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 收到由客户端传送写入公告栏的封包
 */
public class C_BoardWrite extends ClientBasePacket {

    private static final String C_BOARD_WRITE = "[C] C_BoardWrite";

    private static Logger _log = Logger.getLogger(C_BoardWrite.class.getName());

    public C_BoardWrite(final byte decrypt[], final ClientThread client) {
        super(decrypt);
        final int id = this.readD();
        final String title = this.readS();
        final String content = this.readS();

        final L1Object tg = L1World.getInstance().findObject(id);

        if (tg == null) {
            _log.warning("不正确的 NPCID : " + id);
            return;
        }

        final L1PcInstance pc = client.getActiveChar();
        L1BoardTopic.create(pc.getName(), title, content);
        pc.getInventory().consumeItem(L1ItemId.ADENA, 300);
    }

    @Override
    public String getType() {
        return C_BOARD_WRITE;
    }
}
