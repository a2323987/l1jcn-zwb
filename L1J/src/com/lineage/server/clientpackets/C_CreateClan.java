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
import com.lineage.server.datatables.ClanTable;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来建立血盟的封包
 */
public class C_CreateClan extends ClientBasePacket {

    private static final String C_CREATE_CLAN = "[C] C_CreateClan";

    public C_CreateClan(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);
        final String s = this.readS();

        final L1PcInstance pc = clientthread.getActiveChar();
        if (pc.isCrown()) { // 是王族
            if (pc.getClanid() == 0) {
                for (final L1Clan clan : L1World.getInstance().getAllClans()) { // 检查是否有同名的血盟
                    if (clan.getClanName().toLowerCase()
                            .equals(s.toLowerCase())) {
                        pc.sendPackets(new S_ServerMessage(99)); // \f1那个血盟名称已经存在。
                        return;
                    }
                }
                if (pc.getInventory().checkItem(L1ItemId.ADENA, 30000)) { // 身上有金币3万
                    final L1Clan clan = ClanTable.getInstance().createClan(pc,
                            s); // 建立血盟
                    if (clan != null) {
                        pc.sendPackets(new S_ServerMessage(84, s)); // 创立\f1%0
                                                                    // 血盟。
                        pc.getInventory().consumeItem(L1ItemId.ADENA, 30000);
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(189)); // \f1金币不足。
                }
            } else {
                pc.sendPackets(new S_ServerMessage(86)); // \f1已经创立血盟。
            }
        } else {
            pc.sendPackets(new S_ServerMessage(85)); // \f1王子和公主才可创立血盟。
        }
    }

    @Override
    public String getType() {
        return C_CREATE_CLAN;
    }

}
