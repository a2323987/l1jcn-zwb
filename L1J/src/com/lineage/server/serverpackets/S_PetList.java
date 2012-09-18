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

import java.util.List;

import com.lineage.server.Opcodes;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.utils.collections.Lists;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 宠物领取清单
 */
public class S_PetList extends ServerBasePacket {

    private static final String S_PETLIST = "[S] S_PetList";

    private byte[] _byte = null;

    public S_PetList(final int npcObjId, final L1PcInstance pc) {
        this.buildPacket(npcObjId, pc);
    }

    private void buildPacket(final int npcObjId, final L1PcInstance pc) {
        final List<L1ItemInstance> amuletList = Lists.newList();
        // 判断身上是否有宠物项圈！
        for (final L1ItemInstance item : pc.getInventory().getItems()) {
            if ((item.getItem().getItemId() == 40314)
                    || (item.getItem().getItemId() == 40316)) {
                if (!this.isWithdraw(pc, item)) {
                    amuletList.add(item);
                }
            }
        }

        if (amuletList.size() != 0) {
            this.writeC(Opcodes.S_OPCODE_SHOWRETRIEVELIST);
            this.writeD(npcObjId);
            this.writeH(amuletList.size());
            this.writeC(0x0c);
            for (final L1ItemInstance item : amuletList) {
                this.writeD(item.getId());
                this.writeC(0x00);
                this.writeH(item.get_gfxid());
                this.writeC(item.getBless());
                this.writeD(item.getCount());
                this.writeC(item.isIdentified() ? 1 : 0);
                this.writeS(item.getViewName());
            }
        } else {
            return;
        }
        this.writeD(0x00000073); // Price
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this.getBytes();
        }
        return this._byte;
    }

    @Override
    public String getType() {
        return S_PETLIST;
    }

    private boolean isWithdraw(final L1PcInstance pc, final L1ItemInstance item) {
        for (final L1NpcInstance petNpc : pc.getPetList().values()) {
            if (petNpc instanceof L1PetInstance) {
                final L1PetInstance pet = (L1PetInstance) petNpc;
                if (item.getId() == pet.getItemObjId()) {
                    return true;
                }
            }
        }
        return false;
    }
}
