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
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Castle;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * TODO: 处理收到由客户端传来取得城堡税收的封包(?)
 */
public class C_Drawal extends ClientBasePacket {

    private static final String C_DRAWAL = "[C] C_Drawal";

    public C_Drawal(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);
        this.readD();
        final int j = Math.abs(this.readD());

        final L1PcInstance pc = clientthread.getActiveChar();
        final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
        if (clan != null) {
            final int castle_id = clan.getCastleId();
            if (castle_id != 0) {
                final L1Castle l1castle = CastleTable.getInstance()
                        .getCastleTable(castle_id);
                int money = l1castle.getPublicMoney();
                money -= j;
                final L1ItemInstance item = ItemTable.getInstance().createItem(
                        L1ItemId.ADENA);
                if (item != null) {
                    l1castle.setPublicMoney(money);
                    CastleTable.getInstance().updateCastle(l1castle);
                    if (pc.getInventory().checkAddItem(item, j) == L1Inventory.OK) {
                        pc.getInventory().storeItem(L1ItemId.ADENA, j);
                    } else {
                        L1World.getInstance()
                                .getInventory(pc.getX(), pc.getY(),
                                        pc.getMapId())
                                .storeItem(L1ItemId.ADENA, j);
                    }
                    pc.sendPackets(new S_ServerMessage(143, "$457", "$4" + " ("
                            + j + ")")); // \f1%0%s 给你 %1%o 。
                }
            }
        }
    }

    @Override
    public String getType() {
        return C_DRAWAL;
    }

}
