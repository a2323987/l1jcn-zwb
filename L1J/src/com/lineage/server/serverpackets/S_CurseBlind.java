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

/**
 * 法术效果:暗盲咒术
 */
public class S_CurseBlind extends ServerBasePacket {

    private static final String S_CurseBlind = "[S] S_CurseBlind";

    private byte[] _byte = null;

    /**
     * 法术效果:暗盲咒术
     * 
     * @param type
     *            0:OFF 1:自己 2:周边物件可见
     */
    public S_CurseBlind(final int type) {
        this.buildPacket(type);
    }

    private void buildPacket(final int type) {
        this.writeC(Opcodes.S_OPCODE_CURSEBLIND);
        this.writeH(type);
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
        return S_CurseBlind;
    }
}
