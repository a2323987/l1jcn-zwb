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
 * 物件动作种类 (短时间)<BR>
 * - 钓鱼
 */
public class S_Fishing extends ServerBasePacket {

    private static final String S_FISHING = "[S] S_Fishing";

    private byte[] _byte = null;

    public S_Fishing() {
        this.buildPacket();
    }

    /**
     * 物件动作种类 (短时间)<BR>
     * - 钓鱼
     * 
     * @param objectId
     * @param motionNum
     * @param x
     * @param y
     */
    public S_Fishing(final int objectId, final int motionNum, final int x,
            final int y) {
        this.buildPacket(objectId, motionNum, x, y);
    }

    private void buildPacket() {
        this.writeC(Opcodes.S_OPCODE_DOACTIONGFX);
        this.writeC(0x37); // ?
        this.writeD(0x76002822); // ?
        this.writeH(0x8AC3); // ?
    }

    private void buildPacket(final int objectId, final int motionNum,
            final int x, final int y) {
        this.writeC(Opcodes.S_OPCODE_DOACTIONGFX);
        this.writeD(objectId);
        this.writeC(motionNum);
        this.writeH(x);
        this.writeH(y);
        this.writeD(0);
        this.writeH(0);
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
        return S_FISHING;
    }
}
