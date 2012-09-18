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
 * 物件动作类型 (短时间)
 */
public class S_DoActionGFX extends ServerBasePacket {

    private static final String S_DOACTIONGFX = "[S] S_SkillGFX";

    public static int ACTION_MAGIC = 0x16;

    private byte[] _byte = null;

    /**
     * 物件动作类型 (短时间)
     * 
     * @param objectId
     * @param actionId
     */
    public S_DoActionGFX(final int objectId, final int actionId) {
        this.writeC(Opcodes.S_OPCODE_DOACTIONGFX);
        this.writeD(objectId);
        this.writeC(actionId);
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
        return S_DOACTIONGFX;
    }
}
