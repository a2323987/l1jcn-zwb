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
import com.lineage.server.datatables.BuddyTable;
import com.lineage.server.datatables.CharacterTable;
import com.lineage.server.model.L1Buddy;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1CharName;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理客戶端传来增加好友的封包
 */
public class C_AddBuddy extends ClientBasePacket {

    private static final String C_ADD_BUDDY = "[C] C_AddBuddy";

    public C_AddBuddy(final byte[] decrypt, final ClientThread client) {
        super(decrypt);
        final L1PcInstance pc = client.getActiveChar();
        final BuddyTable buddyTable = BuddyTable.getInstance();
        final L1Buddy buddyList = buddyTable.getBuddyTable(pc.getId());
        final String charName = this.readS();

        if (charName.equalsIgnoreCase(pc.getName())) {
            return;
        } else if (buddyList.containsName(charName)) {
            pc.sendPackets(new S_ServerMessage(1052, charName)); // %s 已注册。
            return;
        }

        for (final L1CharName cn : CharacterTable.getInstance()
                .getCharNameList()) {
            if (charName.equalsIgnoreCase(cn.getName())) {
                final int objId = cn.getId();
                final String name = cn.getName();
                buddyList.add(objId, name);
                buddyTable.addBuddy(pc.getId(), objId, name);
                return;
            }
        }
        pc.sendPackets(new S_ServerMessage(109, charName)); // 没有叫%0的人。
    }

    @Override
    public String getType() {
        return C_ADD_BUDDY;
    }
}
