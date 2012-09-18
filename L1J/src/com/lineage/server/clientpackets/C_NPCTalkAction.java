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

import java.io.FileNotFoundException;
import java.util.logging.Logger;

import com.lineage.server.ClientThread;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来NPC讲话动作的封包
 */
public class C_NPCTalkAction extends ClientBasePacket {

    private static final String C_NPC_TALK_ACTION = "[C] C_NPCTalkAction";

    private static Logger _log = Logger.getLogger(C_NPCTalkAction.class
            .getName());

    public C_NPCTalkAction(final byte decrypt[], final ClientThread client)
            throws FileNotFoundException, Exception {

        super(decrypt);
        final int objectId = this.readD();
        final String action = this.readS();
        final L1PcInstance activeChar = client.getActiveChar();

        final L1Object obj = L1World.getInstance().findObject(objectId);
        if (obj == null) {
            _log.warning("找不到对象, oid " + objectId);
            return;
        }

        try {
            final L1NpcInstance npc = (L1NpcInstance) obj;
            npc.onFinalAction(activeChar, action);
        } catch (final ClassCastException e) {
        }
    }

    @Override
    public String getType() {
        return C_NPC_TALK_ACTION;
    }

}
