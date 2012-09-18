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

/**
 * 正义值更新
 */
public class S_Lawful extends ServerBasePacket {

    private static final String S_LAWFUL = "[S] S_Lawful";

    private byte[] _byte = null;

    /**
     * 正义值更新
     * 
     * @param objid
     * @param lawful
     */
    public S_Lawful(final int objid, final int lawful) {
        this.buildPacket(objid, lawful);
    }

    private void buildPacket(final int objid, final int lawful) {
        this.writeC(Opcodes.S_OPCODE_LAWFUL);
        this.writeD(objid);
        this.writeH(lawful);
        this.writeD(0);
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
        return S_LAWFUL;
    }

}
