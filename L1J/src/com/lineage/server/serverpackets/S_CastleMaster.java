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
 * 角色皇冠
 */
public class S_CastleMaster extends ServerBasePacket {

    private static final String _S__08_CASTLEMASTER = "[S] S_CastleMaster";

    private byte[] _byte = null;

    /**
     * 角色皇冠
     * 
     * @param type
     *            城堡编号
     * @param objecId
     *            角色的OBJID
     */
    public S_CastleMaster(final int type, final int objecId) {
        this.buildPacket(type, objecId);
    }

    private void buildPacket(final int type, final int objecId) {
        this.writeC(Opcodes.S_OPCODE_CASTLEMASTER);
        this.writeC(type);
        this.writeD(objecId);
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
        return _S__08_CASTLEMASTER;
    }

}
