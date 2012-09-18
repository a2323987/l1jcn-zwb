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

import com.lineage.server.Opcodes;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1NpcInstance;

/**
 * 宠物控制界面移除
 */
public class S_PetCtrlMenu extends ServerBasePacket {

    public S_PetCtrlMenu(final L1Character cha, final L1NpcInstance npc,
            final boolean open) {
        // int index = open ? 1 : cha.getPetList().size() - 1;
        this.writeC(Opcodes.S_OPCODE_PETCTRL);
        this.writeC(0x0c);

        if (open) {
            this.writeH(cha.getPetList().size() * 3);
            this.writeD(0x00000000);
            this.writeD(npc.getId());
            this.writeH(npc.getMapId());
            this.writeH(0x0000);
            this.writeH(npc.getX());
            this.writeH(npc.getY());
            this.writeS(npc.getName());
        } else {
            this.writeH(cha.getPetList().size() * 3 - 3);
            this.writeD(0x00000001);
            this.writeD(npc.getId());
        }

        /*
         * for (L1NpcInstance temp : cha.getPetList().values()) { if
         * (npc.equals(temp)) { writeH(index * 3); writeD(!open ? 0x00000001 :
         * 0x00000000); writeD(npc.getId()); if (open) { writeD(0x00000000);
         * writeH(npc.getX()); writeH(npc.getY()); writeS(npc.getName()); } else
         * { writeS(null); } break; } index = open ? ++index : --index; }
         */
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }
}
