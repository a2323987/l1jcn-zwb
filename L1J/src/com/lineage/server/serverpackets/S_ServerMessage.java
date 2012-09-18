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
 * 服务器讯息
 */
public class S_ServerMessage extends ServerBasePacket {

    private static final String S_SERVER_MESSAGE = "[S] S_ServerMessage";

    public static final int NO_PLEDGE = 208;

    public static final int CANNOT_GLOBAL = 195;

    public static final int CANNOT_BOOKMARK_LOCATION = 214;

    public static final int USER_NOT_ON = 73;

    public static final int NOT_ENOUGH_MP = 278;

    public static final int YOU_FEEL_BETTER = 77;

    public static final int YOUR_WEAPON_BLESSING = 693;

    public static final int YOUR_Are_Slowed = 29;

    private byte[] _byte = null;

    /**
     * 服务器讯息
     * 
     * @param type
     */
    public S_ServerMessage(final int type) {
        this.buildPacket(type, null, null, null, null, null, 0);
    }

    /**
     * 服务器讯息
     * 
     * @param type
     * @param msg1
     */
    public S_ServerMessage(final int type, final String msg1) {
        this.buildPacket(type, msg1, null, null, null, null, 1);
    }

    /**
     * 服务器讯息
     * 
     * @param type
     * @param msg1
     * @param msg2
     */
    public S_ServerMessage(final int type, final String msg1, final String msg2) {
        this.buildPacket(type, msg1, msg2, null, null, null, 2);
    }

    /**
     * 服务器讯息
     * 
     * @param type
     * @param msg1
     * @param msg2
     * @param msg3
     */
    public S_ServerMessage(final int type, final String msg1,
            final String msg2, final String msg3) {
        this.buildPacket(type, msg1, msg2, msg3, null, null, 3);
    }

    /**
     * 服务器讯息
     * 
     * @param type
     * @param msg1
     * @param msg2
     * @param msg3
     * @param msg4
     */
    public S_ServerMessage(final int type, final String msg1,
            final String msg2, final String msg3, final String msg4) {
        this.buildPacket(type, msg1, msg2, msg3, msg4, null, 4);
    }

    /**
     * 服务器讯息
     * 
     * @param type
     * @param msg1
     * @param msg2
     * @param msg3
     * @param msg4
     * @param msg5
     */
    public S_ServerMessage(final int type, final String msg1,
            final String msg2, final String msg3, final String msg4,
            final String msg5) {

        this.buildPacket(type, msg1, msg2, msg3, msg4, msg5, 5);
    }

    private void buildPacket(final int type, final String msg1,
            final String msg2, final String msg3, final String msg4,
            final String msg5, final int check) {

        this.writeC(Opcodes.S_OPCODE_SERVERMSG);
        this.writeH(type);

        if (check == 0) {
            this.writeC(0);
        } else if (check == 1) {
            this.writeC(1);
            this.writeS(msg1);
        } else if (check == 2) {
            this.writeC(2);
            this.writeS(msg1);
            this.writeS(msg2);
        } else if (check == 3) {
            this.writeC(3);
            this.writeS(msg1);
            this.writeS(msg2);
            this.writeS(msg3);
        } else if (check == 4) {
            this.writeC(4);
            this.writeS(msg1);
            this.writeS(msg2);
            this.writeS(msg3);
            this.writeS(msg4);
        } else {
            this.writeC(5);
            this.writeS(msg1);
            this.writeS(msg2);
            this.writeS(msg3);
            this.writeS(msg4);
            this.writeS(msg5);
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
        return S_SERVER_MESSAGE;
    }
}
