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
import com.lineage.server.datatables.PetItemTable;
import com.lineage.server.datatables.PetTypeTable;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.serverpackets.S_PetEquipment;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1PetItem;
import com.lineage.server.templates.L1PetType;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来使用宠物道具的封包
 */
public class C_UsePetItem extends ClientBasePacket {

    /**
     * 【Client】 id:60 size:8 time:1302335819781 0000 3c 00 04 bd 54 00 00 00
     * <...T... 【Server】 id:82 size:16 time:1302335819812 0000 52 25 00 04 bd 54
     * 00 00 0a 37 80 08 7e ec d0 46 R%...T...7..~..F
     */

    private static final String C_USE_PET_ITEM = "[C] C_UsePetItem";

    /** 使用宠物装备 */
    public C_UsePetItem(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);

        final int data = this.readC();
        final int petId = this.readD();
        final int listNo = this.readC();

        final L1PetInstance pet = (L1PetInstance) L1World.getInstance()
                .findObject(petId);
        final L1PcInstance pc = clientthread.getActiveChar();

        // 宠物与角色为空
        if ((pet == null) && (pc == null)) {
            return;
        }
        final L1ItemInstance item = pet.getInventory().getItems().get(listNo);
        if (item == null) {
            return;
        }

        // 宠物道具
        if ((item.getItem().getType2() == 0)
                && (item.getItem().getType() == 11)) {
            final L1PetType petType = PetTypeTable.getInstance().get(
                    pet.getNpcTemplate().get_npcId());
            // 判断是否可用宠物装备
            if (!petType.canUseEquipment()) {
                pc.sendPackets(new S_ServerMessage(74, item.getLogName())); // \f1%0%o
                                                                            // 无法使用。
                return;
            }
            final int itemId = item.getItem().getItemId();
            final L1PetItem petItem = PetItemTable.getInstance().getTemplate(
                    itemId);
            if (petItem.getUseType() == 1) { // 牙齿
                pet.usePetWeapon(pet, item);
                pc.sendPackets(new S_PetEquipment(data, pet, listNo)); // 装备时更新宠物资讯
            } else if (petItem.getUseType() == 0) { // 盔甲
                pet.usePetArmor(pet, item);
                pc.sendPackets(new S_PetEquipment(data, pet, listNo)); // 装备时更新宠物资讯
            } else {
                pc.sendPackets(new S_ServerMessage(74, item.getLogName())); // \f1%0%o
                                                                            // 无法使用。
            }
        } else {
            pc.sendPackets(new S_ServerMessage(74, item.getLogName())); // \f1%0%o
                                                                        // 无法使用。
        }
    }

    @Override
    public String getType() {
        return C_USE_PET_ITEM;
    }
}
