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

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket, S_SkillIconGFX, S_CharVisualUpdate

/**
 * 使用于怪物变身
 */
public class S_NpcChangeShape extends ServerBasePacket {

    private byte[] _byte = null;

    /** 使用于怪物变身 */
    public S_NpcChangeShape(final int objId, final int polyId,
            final int lawful, final int status) {
        this.writeC(Opcodes.S_OPCODE_SPOLY);
        this.writeD(objId);
        this.writeD(0); // ???
        this.writeH(polyId);
        this.writeH(lawful); // 正义值
        this.writeH(status); // 状态
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this.getBytes();
        }
        return this._byte;
    }
}
