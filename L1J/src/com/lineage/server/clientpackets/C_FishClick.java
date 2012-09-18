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
import com.lineage.server.FishingTimeController;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_CharVisualUpdate;

/**
 * 处理收到由客户端传来停止钓鱼的封包
 */
public class C_FishClick extends ClientBasePacket {

    private static final String C_FISHCLICK = "[C] C_FishClick";

    public C_FishClick(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);
        final L1PcInstance pc = clientthread.getActiveChar();
        if ((pc == null) || pc.isDead()) {
            return;
        }
        pc.setFishingTime(0);
        pc.setFishingReady(false);
        pc.setFishing(false);
        pc.sendPackets(new S_CharVisualUpdate(pc));
        pc.broadcastPacket(new S_CharVisualUpdate(pc));
        FishingTimeController.getInstance().removeMember(pc);
    }

    @Override
    public String getType() {
        return C_FISHCLICK;
    }

}
