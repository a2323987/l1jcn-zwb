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
 * 蓝色系统讯息 (游戏画面中间)
 */
public class S_BlueMessage extends ServerBasePacket {

    private static final String _S__18_BLUEMESSAGE = "[S] S_BlueMessage";

    private byte[] _byte = null;

    /**
     * 画面中的蓝色讯息
     * 
     * @param type
     * @param msg1
     */
    public S_BlueMessage(final int type, final String msg1) {
        this.buildPacket(type, msg1, null, null, 1);
    }

    /**
     * 画面中的蓝色讯息
     * 
     * @param type
     * @param msg1
     * @param msg2
     */
    public S_BlueMessage(final int type, final String msg1, final String msg2) {
        this.buildPacket(type, msg1, msg2, null, 2);
    }

    /**
     * 画面中的蓝色讯息
     * 
     * @param type
     * @param msg1
     * @param msg2
     * @param msg3
     */
    public S_BlueMessage(final int type, final String msg1, final String msg2,
            final String msg3) {
        this.buildPacket(type, msg1, msg2, msg3, 3);
    }

    private void buildPacket(final int type, final String msg1,
            final String msg2, final String msg3, final int check) {
        this.writeC(Opcodes.S_OPCODE_BLUEMESSAGE);
        this.writeH(type);
        if (check == 1) {
            if (msg1.length() <= 0) {
                this.writeC(0);
            } else {
                this.writeC(1);
                this.writeS(msg1);
            }
        } else if (check == 2) {
            this.writeC(2);
            this.writeS(msg1);
            this.writeS(msg2);
        } else if (check == 3) {
            this.writeC(3);
            this.writeS(msg1);
            this.writeS(msg2);
            this.writeS(msg3);
        }
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
        return _S__18_BLUEMESSAGE;
    }
}
