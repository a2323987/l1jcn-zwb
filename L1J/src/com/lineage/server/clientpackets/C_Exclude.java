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

import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.ClientThread;
import com.lineage.server.model.L1ExcludingList;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来封锁密语的封包
 */
public class C_Exclude extends ClientBasePacket {

    private static final String C_EXCLUDE = "[C] C_Exclude";
    private static Logger _log = Logger.getLogger(C_Exclude.class.getName());

    /**
     * C_1 输入 /exclude 指令的时候
     */
    public C_Exclude(final byte[] decrypt, final ClientThread client) {
        super(decrypt);
        final String name = this.readS();
        if (name.isEmpty()) {
            return;
        }
        final L1PcInstance pc = client.getActiveChar();
        try {
            final L1ExcludingList exList = pc.getExcludingList();
            if (exList.isFull()) {
                pc.sendPackets(new S_ServerMessage(472)); // 被拒绝的玩家太多。
                return;
            }
            if (exList.contains(name)) {
                final String temp = exList.remove(name);
                pc.sendPackets(new S_PacketBox(S_PacketBox.REM_EXCLUDE, temp));
            } else {
                exList.add(name);
                pc.sendPackets(new S_PacketBox(S_PacketBox.ADD_EXCLUDE, name));
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    @Override
    public String getType() {
        return C_EXCLUDE;
    }
}
