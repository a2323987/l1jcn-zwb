package com.lineage.server.serverpackets;

import com.lineage.server.Opcodes;
import com.lineage.server.model.Instance.L1PetInstance;

/**
 * 宠物装备
 */
public class S_PetEquipment extends ServerBasePacket {

    private static final String S_PET_EQUIPMENT = "[S] S_PetEquipment";

    /**
     * 【Client】 id:60 size:8 time:1302335819781 0000 3c 00 04 bd 54 00 00 00
     * <...T... 【Server】 id:82 size:16 time:1302335819812 0000 52 25 00 04 bd 54
     * 00 00 0a 37 80 08 7e ec d0 46 R%...T...7..~..F
     */

    public S_PetEquipment(final int i, final L1PetInstance pet, final int j) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(0x25);
        this.writeC(i);
        this.writeD(pet.getId());
        this.writeC(j);
        this.writeC(pet.getAc()); // 宠物防御
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }

    @Override
    public String getType() {
        return S_PET_EQUIPMENT;
    }
}
