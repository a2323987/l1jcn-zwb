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
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.identity.L1ItemId;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来选择清单的封包
 */
public class C_SelectList extends ClientBasePacket {

    private static final String C_SELECT_LIST = "[C] C_SelectList";

    public C_SelectList(final byte abyte0[], final ClientThread clientthread) {
        super(abyte0);
        // アイテム每にリクエストが来る。
        final int itemObjectId = this.readD();
        final int npcObjectId = this.readD();
        final L1PcInstance pc = clientthread.getActiveChar();

        if (npcObjectId != 0) { // 武器的修理
            final L1Object obj = L1World.getInstance().findObject(npcObjectId);
            if (obj != null) {
                if (obj instanceof L1NpcInstance) {
                    final L1NpcInstance npc = (L1NpcInstance) obj;
                    final int difflocx = Math.abs(pc.getX() - npc.getX());
                    final int difflocy = Math.abs(pc.getY() - npc.getY());
                    // 3格以上的距离视为无效请求
                    if ((difflocx > 3) || (difflocy > 3)) {
                        return;
                    }
                }
            }

            final L1PcInventory pcInventory = pc.getInventory();
            final L1ItemInstance item = pcInventory.getItem(itemObjectId);
            final int cost = item.get_durability() * 200;
            if (!pc.getInventory().consumeItem(L1ItemId.ADENA, cost)) {
                return;
            }
            item.set_durability(0);
            pcInventory.updateItem(item, L1PcInventory.COL_DURABILITY);
        }
    }

    @Override
    public String getType() {
        return C_SELECT_LIST;
    }
}
