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
 * 物件攻击 (PC 用)
 */
public class S_AttackPacket extends ServerBasePacket {

    private static final String S_ATTACK_PACKET = "[S] S_AttackPacket";

    private byte[] _byte = null;

    public S_AttackPacket(final L1Character atk, final int objid,
            final int actid) {
        final int[] data = { actid, 0, 0 };
        this.buildpacket(atk, objid, data);
    }

    public S_AttackPacket(final L1Character atk, final int objid,
            final int[] data) {
        this.buildpacket(atk, objid, data);
    }

    private void buildpacket(final L1Character atk, final int objid,
            final int[] data) { // data = {actid, dmg, effect}
        this.writeC(Opcodes.S_OPCODE_ATTACKPACKET);
        this.writeC(data[0]); // actid
        this.writeD(atk.getId());
        this.writeD(objid);
        this.writeH(data[1]); // dmg
        this.writeC(atk.getHeading());
        this.writeD(0x00000000);
        this.writeC(data[2]); // effect 0:none 2:爪痕 4:双击 8:镜返射
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
        return S_ATTACK_PACKET;
    }
}
