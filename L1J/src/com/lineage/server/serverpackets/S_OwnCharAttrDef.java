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
import com.lineage.server.model.Instance.L1PcInstance;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 更新角色物理防御与四属性防御
 */
public class S_OwnCharAttrDef extends ServerBasePacket {

    private static final String S_OWNCHARATTRDEF = "[S] S_OwnCharAttrDef";

    private byte[] _byte = null;

    /**
     * 更新角色物理防御与四属性防御
     */
    public S_OwnCharAttrDef(final L1PcInstance pc) {
        this.buildPacket(pc);
    }

    private void buildPacket(final L1PcInstance pc) {
        this.writeC(Opcodes.S_OPCODE_OWNCHARATTRDEF);
        this.writeC(pc.getAc());
        this.writeC(pc.getFire());
        this.writeC(pc.getWater());
        this.writeC(pc.getWind());
        this.writeC(pc.getEarth());
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
        return S_OWNCHARATTRDEF;
    }
}
