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

import java.util.logging.Logger;

import com.lineage.server.ClientThread;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_PacketBox;

/**
 * 处理收到由客户端传来选择新角色的封包
 */
public class C_NewCharSelect extends ClientBasePacket {

    private static final String C_NEW_CHAR_SELECT = "[C] C_NewCharSelect";

    private static Logger _log = Logger.getLogger(C_NewCharSelect.class
            .getName());

    public C_NewCharSelect(final byte[] decrypt, final ClientThread client) {

        super(decrypt);
        client.sendPacket(new S_PacketBox(S_PacketBox.LOGOUT)); // 2.70C->3.0で追加
        client.CharReStart(true);
        if (client.getActiveChar() != null) {
            final L1PcInstance pc = client.getActiveChar();
            _log.fine("断开: " + pc.getName());
            ClientThread.quitGame(pc);

            synchronized (pc) {
                pc.logout();
                client.setActiveChar(null);
            }
        } else {
            _log.fine("从帐户断开请求 : " + client.getAccountName());
        }
    }

    @Override
    public String getType() {
        return C_NEW_CHAR_SELECT;
    }
}
