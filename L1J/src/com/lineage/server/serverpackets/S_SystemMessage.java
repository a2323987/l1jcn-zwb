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
 * 系统信息
 */
public class S_SystemMessage extends ServerBasePacket {

    private static final String S_SYSTEM_MESSAGE = "[S] S_SystemMessage";

    private byte[] _byte = null;

    private final String _msg;

    /**
     * クライアントにデータの存在しないオリジナルのメッセージを表示する。
     * メッセージにnameid($xxx)が含まれている場合はオーバーロードされたもう一方を使用する。
     * 
     * @param msg
     *            - 要显示的文字信息
     */
    public S_SystemMessage(final String msg) {
        this._msg = msg;
        this.writeC(Opcodes.S_OPCODE_GLOBALCHAT);
        this.writeC(0x09);
        this.writeS(msg);
    }

    /**
     * クライアントにデータの存在しないオリジナルのメッセージを表示する。
     * 
     * @param msg
     *            - 要显示的文字信息
     * @param nameid
     *            - 文字列にnameid($xxx)が含まれている場合trueにする。
     */
    public S_SystemMessage(final String msg, final boolean nameid) {
        this._msg = msg;
        this.writeC(Opcodes.S_OPCODE_NPCSHOUT);
        this.writeC(2);
        this.writeD(0);
        this.writeS(msg);
        // NPCチャットパケットであればnameidが解釈されるためこれを利用する
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
        return S_SYSTEM_MESSAGE;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", S_SYSTEM_MESSAGE, this._msg);
    }
}
