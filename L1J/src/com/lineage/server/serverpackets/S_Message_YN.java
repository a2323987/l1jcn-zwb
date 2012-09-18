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

import com.lineage.server.GameServer;
import com.lineage.server.Opcodes;

/**
 * 选项 (Yes/No)
 */
public class S_Message_YN extends ServerBasePacket {

    private byte[] _byte = null;

    /**
     * 选项 (Yes/No)
     * 
     * @param type
     * @param msg1
     */
    public S_Message_YN(final int type, final String msg1) {
        this.buildPacket(type, msg1, null, null, 1);
    }

    /**
     * 选项 (Yes/No)
     * 
     * @param type
     * @param msg1
     * @param msg2
     */
    public S_Message_YN(final int type, final String msg1, final String msg2) {
        this.buildPacket(type, msg1, msg2, null, 2);
    }

    /**
     * 选项 (Yes/No)
     * 
     * @param type
     * @param msg1
     * @param msg2
     * @param msg3
     */
    public S_Message_YN(final int type, final String msg1, final String msg2,
            final String msg3) {
        this.buildPacket(type, msg1, msg2, msg3, 3);
    }

    private void buildPacket(final int type, final String msg1,
            final String msg2, final String msg3, final int check) {
        this.writeC(Opcodes.S_OPCODE_YES_NO);
        this.writeH(0x0000); // 3.51未知封包
        this.writeD(GameServer.getYesNoCount());
        this.writeH(type);
        if (check == 1) {
            this.writeS(msg1);
        } else if (check == 2) {
            this.writeS(msg1);
            this.writeS(msg2);
        } else if (check == 3) {
            this.writeS(msg1);
            this.writeS(msg2);
            this.writeS(msg3);
        }
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
        return "[S] S_Message_YN";
    }
}
