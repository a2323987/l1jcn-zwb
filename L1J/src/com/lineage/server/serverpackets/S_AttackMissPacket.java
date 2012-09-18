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

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 物件攻击MISS封包
 */
public class S_AttackMissPacket extends ServerBasePacket {

    private static final String _S__OB_ATTACKMISSPACKET = "[S] S_AttackMissPacket";

    private byte[] _byte = null;

    public S_AttackMissPacket(final int attackId, final int targetId) {
        this.writeC(Opcodes.S_OPCODE_ATTACKPACKET);
        this.writeC(1);
        this.writeD(attackId);
        this.writeD(targetId);
        this.writeH(0);
        this.writeC(0);
        this.writeD(0);
    }

    public S_AttackMissPacket(final int attackId, final int targetId,
            final int actId) {
        this.writeC(Opcodes.S_OPCODE_ATTACKPACKET);
        this.writeC(actId);
        this.writeD(attackId);
        this.writeD(targetId);
        this.writeH(0);
        this.writeC(0);
        this.writeD(0);
    }

    public S_AttackMissPacket(final L1Character attacker, final int targetId) {
        this.writeC(Opcodes.S_OPCODE_ATTACKPACKET);
        this.writeC(1);
        this.writeD(attacker.getId());
        this.writeD(targetId);
        this.writeH(0);
        this.writeC(attacker.getHeading());
        this.writeD(0);
        this.writeC(0);
    }

    public S_AttackMissPacket(final L1Character attacker, final int targetId,
            final int actId) {
        this.writeC(Opcodes.S_OPCODE_ATTACKPACKET);
        this.writeC(actId);
        this.writeD(attacker.getId());
        this.writeD(targetId);
        this.writeH(0);
        this.writeC(attacker.getHeading());
        this.writeD(0);
        this.writeC(0);
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
        return _S__OB_ATTACKMISSPACKET;
    }
}
