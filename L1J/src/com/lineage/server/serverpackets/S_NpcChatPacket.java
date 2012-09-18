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
import com.lineage.server.model.Instance.L1NpcInstance;

public class S_NpcChatPacket extends ServerBasePacket {
    private static final String S_NPC_CHAT_PACKET = "[S] S_NpcChatPacket";

    private byte[] _byte = null;

    public S_NpcChatPacket(final L1NpcInstance npc, final String chat,
            final int type) {
        this.buildPacket(npc, chat, type);
    }

    private void buildPacket(final L1NpcInstance npc, final String chat,
            final int type) {
        switch (type) {
            case 0: // normal chat
                this.writeC(Opcodes.S_OPCODE_NPCSHOUT); // Key is 16 , can use
                // desc-?.tbl
                this.writeC(type); // Color
                this.writeD(npc.getId());
                this.writeS(npc.getName() + ": " + chat);
                break;

            case 2: // shout
                this.writeC(Opcodes.S_OPCODE_NPCSHOUT); // Key is 16 , can use
                // desc-?.tbl
                this.writeC(type); // Color
                this.writeD(npc.getId());
                this.writeS("<" + npc.getName() + "> " + chat);
                break;

            case 3: // world chat
                this.writeC(Opcodes.S_OPCODE_NPCSHOUT);
                this.writeC(type); // XXX 白色になる
                this.writeD(npc.getId());
                this.writeS("[" + npc.getName() + "] " + chat);
                break;

            default:
                break;
        }
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
        return S_NPC_CHAT_PACKET;
    }
}
