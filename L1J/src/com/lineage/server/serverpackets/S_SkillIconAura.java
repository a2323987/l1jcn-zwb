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
// ServerBasePacket

public class S_SkillIconAura extends ServerBasePacket {

    public S_SkillIconAura(final int i, final int j) {
        this.writeC(Opcodes.S_OPCODE_SKILLICONGFX);
        this.writeC(0x16);
        this.writeC(i);
        this.writeH(j);
    }

    public S_SkillIconAura(final int i, final int j, final int k) {
        this.writeC(Opcodes.S_OPCODE_SKILLICONGFX);
        this.writeC(0x16);
        this.writeC(i);
        this.writeH(j);
        this.writeC(k);
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }
}
