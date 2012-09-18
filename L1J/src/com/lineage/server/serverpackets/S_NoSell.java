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
package com.lineage.server.serverpackets;

import com.lineage.server.Opcodes;
import com.lineage.server.datatables.NPCTalkDataTable;
import com.lineage.server.model.L1NpcTalkData;
import com.lineage.server.model.Instance.L1NpcInstance;

/**
 * 
 */
public class S_NoSell extends ServerBasePacket {

    private static final String _S__25_NoSell = "[S] _S__25_NoSell";

    private byte[] _byte = null;

    public S_NoSell(final L1NpcInstance npc) {
        this.buildPacket(npc);
    }

    private void buildPacket(final L1NpcInstance npc) {
        this.writeC(Opcodes.S_OPCODE_SHOWHTML);
        this.writeD(npc.getId());
        if (npc != null) {
            final L1NpcTalkData talkdata = NPCTalkDataTable.getInstance()
                    .getTemplate(npc.getNpcTemplate().get_npcId());
            if (talkdata != null) {
                this.writeS(talkdata.getNoSell());
            } else {
                this.writeS(null);
            }
        }
        this.writeC(0x00);
        this.writeH(0x00);
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this.getBytes();
        }

        return this._byte;
    }

    @Override
    public String getType() {
        return _S__25_NoSell;
    }
}
