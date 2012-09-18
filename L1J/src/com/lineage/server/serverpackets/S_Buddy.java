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
import com.lineage.server.model.L1Buddy;

/**
 * 好友清单
 */
public class S_Buddy extends ServerBasePacket {

    private static final String _S_Buddy = "[S] _S_Buddy";

    private static final String _HTMLID = "buddy";

    private byte[] _byte = null;

    /**
     * 好友清单
     * 
     * @param objId
     * @param buddy
     */
    public S_Buddy(final int objId, final L1Buddy buddy) {
        this.buildPacket(objId, buddy);
    }

    private void buildPacket(final int objId, final L1Buddy buddy) {
        this.writeC(Opcodes.S_OPCODE_SHOWHTML);
        this.writeD(objId);
        this.writeS(_HTMLID);
        this.writeH(0x02);
        this.writeH(0x02);

        this.writeS(buddy.getBuddyListString());
        this.writeS(buddy.getOnlineBuddyListString());
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this._bao.toByteArray();
        }
        return this._byte;
    }

    @Override
    public String getType() {
        return _S_Buddy;
    }
}
