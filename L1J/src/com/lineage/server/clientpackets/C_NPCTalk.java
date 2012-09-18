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
import com.lineage.server.datatables.NpcActionTable;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.npc.L1NpcHtml;
import com.lineage.server.model.npc.action.L1NpcAction;
import com.lineage.server.serverpackets.S_NPCTalkReturn;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket, C_NPCTalk

/**
 * 处理收到由客户端传来NPC讲话的封包
 */
public class C_NPCTalk extends ClientBasePacket {

    private static final String C_NPC_TALK = "[C] C_NPCTalk";

    private static Logger _log = Logger.getLogger(C_NPCTalk.class.getName());

    public C_NPCTalk(final byte abyte0[], final ClientThread client)
            throws Exception {

        super(abyte0);
        final int objid = this.readD();
        final L1Object obj = L1World.getInstance().findObject(objid);
        final L1PcInstance pc = client.getActiveChar();
        if ((obj != null) && (pc != null)) {

            if (obj instanceof L1NpcInstance) {
                final L1NpcInstance npc = (L1NpcInstance) obj;
                if (npc.TALK != null) {
                    npc.TALK.npcTalkReturn(pc, npc);
                    return;
                }
            }

            final L1NpcAction action = NpcActionTable.getInstance()
                    .get(pc, obj);
            if (action != null) {
                final L1NpcHtml html = action.execute("", pc, obj, new byte[0]);
                if (html != null) {
                    pc.sendPackets(new S_NPCTalkReturn(obj.getId(), html));
                }
                return;
            }
            obj.onTalkAction(pc);
        } else {
            _log.severe("不正确的NPC objid=" + objid);
        }
    }

    @Override
    public String getType() {
        return C_NPC_TALK;
    }
}
