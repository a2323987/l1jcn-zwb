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
import com.lineage.server.model.L1Object;

/**
 * 物件删除
 */
public class S_RemoveObject extends ServerBasePacket {

    private byte[] _byte = null;

    /**
     * 物件刪除
     * 
     * @param obj
     */
    public S_RemoveObject(final L1Object obj) {
        this.writeC(Opcodes.S_OPCODE_REMOVE_OBJECT);
        this.writeD(obj.getId());
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this.getBytes();
        }
        return this._byte;
    }
}
