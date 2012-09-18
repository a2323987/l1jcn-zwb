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
import com.lineage.server.model.Instance.L1PetInstance;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 物品名单 (宠物背包)
 */
public class S_PetInventory extends ServerBasePacket {

    private static final String S_PET_INVENTORY = "[S] S_PetInventory";

    private byte[] _byte = null;

    /**
     * 物品名单 (宠物背包)
     * 
     * @param pet
     */
    public S_PetInventory(final L1PetInstance pet) {
        final List<L1ItemInstance> itemList = pet.getInventory().getItems();

        this.writeC(Opcodes.S_OPCODE_SHOWRETRIEVELIST);
        this.writeD(pet.getId());
        this.writeH(itemList.size());
        this.writeC(0x0b);

        for (final Object itemObject : itemList) {
            final L1ItemInstance petItem = (L1ItemInstance) itemObject;
            if (petItem == null) {
                continue;
            }
            this.writeD(petItem.getId());
            this.writeC(0x02); // 值:0x00 无、0x01:武器类、0x02:防具类、0x16:牙齿类 、0x33:药水类
            this.writeH(petItem.get_gfxid());
            this.writeC(petItem.getBless());
            this.writeD(petItem.getCount());

            // 显示装备中的宠物装备
            if ((petItem.getItem().getType2() == 0)
                    && (petItem.getItem().getType() == 11)
                    && petItem.isEquipped()) {
                this.writeC(petItem.isIdentified() ? 3 : 2);
            } else {
                this.writeC(petItem.isIdentified() ? 1 : 0);
            }
            this.writeS(petItem.getViewName());

        }
        this.writeC(pet.getAc()); // 宠物防御
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
        return S_PET_INVENTORY;
    }
}
