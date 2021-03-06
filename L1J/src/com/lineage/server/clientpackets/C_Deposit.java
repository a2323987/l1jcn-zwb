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

import com.lineage.server.ClientThread;
import com.lineage.server.datatables.CastleTable;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.templates.L1Castle;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * TODO: 处理收到由客户端传来纳税的封包(?)
 */
public class C_Deposit extends ClientBasePacket {

    private static final String C_DEPOSIT = "[C] C_Deposit";

    public C_Deposit(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);
        final int i = this.readD();
        final int j = this.readD();

        final L1PcInstance player = clientthread.getActiveChar();
        if (i == player.getId()) {
            final L1Clan clan = L1World.getInstance().getClan(
                    player.getClanname());
            if (clan != null) {
                final int castle_id = clan.getCastleId();
                if (castle_id != 0) { // 有城堡的盟主
                    final L1Castle l1castle = CastleTable.getInstance()
                            .getCastleTable(castle_id);
                    synchronized (l1castle) {
                        int money = l1castle.getPublicMoney();
                        if (player.getInventory()
                                .consumeItem(L1ItemId.ADENA, j)) {
                            money += j;
                            l1castle.setPublicMoney(money);
                            CastleTable.getInstance().updateCastle(l1castle);
                        }
                    }
                }
            }
        }
    }

    @Override
    public String getType() {
        return C_DEPOSIT;
    }

}
