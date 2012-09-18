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

import com.lineage.Config;
import com.lineage.server.Account;
import com.lineage.server.ClientThread;
import com.lineage.server.Opcodes;

/**
 * 角色列表
 */
public class S_CharAmount extends ServerBasePacket {

    private byte[] _byte = null;

    /**
     * 角色列表
     * 
     * @param value
     *            已创建的角色数量
     * @param client
     */
    public S_CharAmount(final int value, final ClientThread client) {
        this.buildPacket(value, client);
    }

    private void buildPacket(final int value, final ClientThread client) {
        final Account account = Account.load(client.getAccountName());
        final int characterSlot = account.getCharacterSlot();
        final int maxAmount = Config.DEFAULT_CHARACTER_SLOT + characterSlot;

        this.writeC(Opcodes.S_OPCODE_CHARAMOUNT);
        this.writeC(value);
        this.writeC(maxAmount); // max amount
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this.getBytes();
        }
        return this._byte;
    }
}
