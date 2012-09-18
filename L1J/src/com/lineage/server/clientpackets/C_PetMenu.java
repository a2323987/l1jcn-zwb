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
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.serverpackets.S_PetInventory;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来宠物选单的封包
 */
public class C_PetMenu extends ClientBasePacket {

    private static final String C_PET_MENU = "[C] C_PetMenu";

    public C_PetMenu(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);

        final int petId = this.readD();

        final L1PetInstance pet = (L1PetInstance) L1World.getInstance()
                .findObject(petId);
        final L1PcInstance pc = clientthread.getActiveChar();

        if ((pet != null) && (pc != null)) {
            pc.sendPackets(new S_PetInventory(pet));
        }
    }

    @Override
    public String getType() {
        return C_PET_MENU;
    }
}
