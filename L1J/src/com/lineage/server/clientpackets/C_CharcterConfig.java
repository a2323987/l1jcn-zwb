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
package com.lineage.server.clientpackets;

import com.lineage.Config;
import com.lineage.server.ClientThread;
import com.lineage.server.datatables.CharacterConfigTable;
import com.lineage.server.model.Instance.L1PcInstance;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket, C_RequestDoors

/**
 * 收到由客户端传来角色设定的封包
 */
public class C_CharcterConfig extends ClientBasePacket {

    private static final String C_CHARCTER_CONFIG = "[C] C_CharcterConfig";

    public C_CharcterConfig(final byte abyte0[], final ClientThread client)
            throws Exception {
        super(abyte0);
        if (Config.CHARACTER_CONFIG_IN_SERVER_SIDE) {
            final L1PcInstance pc = client.getActiveChar();
            final int length = this.readD() - 3;
            final byte data[] = this.readByte();
            final int count = CharacterConfigTable.getInstance()
                    .countCharacterConfig(pc.getId());
            if (count == 0) {
                CharacterConfigTable.getInstance().storeCharacterConfig(
                        pc.getId(), length, data);
            } else {
                CharacterConfigTable.getInstance().updateCharacterConfig(
                        pc.getId(), length, data);
            }
        }
    }

    @Override
    public String getType() {
        return C_CHARCTER_CONFIG;
    }
}
