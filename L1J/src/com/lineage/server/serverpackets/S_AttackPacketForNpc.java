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
import com.lineage.server.model.L1Character;

/**
 * 物件攻击 (NPC用 - 近距离)
 */
public class S_AttackPacketForNpc extends ServerBasePacket {

    private static final String S_ATTACK_PACKET_FOR_NPC = "[S] S_AttackPacketForNpc";

    private byte[] _byte = null;

    /**
     * 物件攻击
     * 
     * @param cha
     * @param npcObjectId
     * @param type
     *            动作编号
     */
    public S_AttackPacketForNpc(final L1Character cha, final int npcObjectId,
            final int type) {
        this.buildpacket(cha, npcObjectId, type);
    }

    private void buildpacket(final L1Character cha, final int npcObjectId,
            final int type) {
        this.writeC(Opcodes.S_OPCODE_ATTACKPACKET);
        this.writeC(type);
        this.writeD(npcObjectId);
        this.writeD(cha.getId());
        this.writeH(0x01); // 3.3C damage
        this.writeC(cha.getHeading());
        this.writeH(0x0000); // target x
        this.writeH(0x0000); // target y
        this.writeC(0x00); // 0x00:none 0x04:Claw 0x08:CounterMirror
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
        return S_ATTACK_PACKET_FOR_NPC;
    }
}
