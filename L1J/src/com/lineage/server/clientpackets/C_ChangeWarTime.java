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

import java.util.Calendar;

import com.lineage.server.ClientThread;
import com.lineage.server.datatables.CastleTable;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_WarTime;
import com.lineage.server.templates.L1Castle;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 收到由客户端传来改变攻城时间的封包
 */
public class C_ChangeWarTime extends ClientBasePacket {

    private static final String C_CHANGE_WAR_TIME = "[C] C_ChangeWarTime";

    public C_ChangeWarTime(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);

        final L1PcInstance player = clientthread.getActiveChar();

        final L1Clan clan = L1World.getInstance().getClan(player.getClanname());
        if (clan != null) {
            final int castle_id = clan.getCastleId();
            if (castle_id != 0) { // 有城
                final L1Castle l1castle = CastleTable.getInstance()
                        .getCastleTable(castle_id);
                final Calendar cal = l1castle.getWarTime();
                player.sendPackets(new S_WarTime(cal));
            }
        }
    }

    @Override
    public String getType() {
        return C_CHANGE_WAR_TIME;
    }

}
