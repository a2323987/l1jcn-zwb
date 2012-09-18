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
import com.lineage.server.model.Getback;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_CharVisualUpdate;
import com.lineage.server.serverpackets.S_MapID;
import com.lineage.server.serverpackets.S_OtherCharPacks;
import com.lineage.server.serverpackets.S_OwnCharPack;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.serverpackets.S_Weather;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来重新的封包
 */
public class C_Restart extends ClientBasePacket {

    private static final String C_RESTART = "[C] C_Restart";

    public C_Restart(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);
        final L1PcInstance pc = clientthread.getActiveChar();

        pc.stopPcDeleteTimer();

        int[] loc;

        if (pc.getHellTime() > 0) {
            loc = new int[3];
            loc[0] = 32701;
            loc[1] = 32777;
            loc[2] = 666;
        } else {
            loc = Getback.GetBack_Location(pc, true);
        }

        pc.removeAllKnownObjects();
        pc.broadcastPacket(new S_RemoveObject(pc));

        pc.setCurrentHp(pc.getLevel());
        pc.set_food(40);
        pc.setDead(false);
        pc.setStatus(0);
        L1World.getInstance().moveVisibleObject(pc, loc[2]);
        pc.setX(loc[0]);
        pc.setY(loc[1]);
        pc.setMap((short) loc[2]);
        pc.sendPackets(new S_MapID(pc.getMapId(), pc.getMap().isUnderwater()));
        pc.broadcastPacket(new S_OtherCharPacks(pc));
        pc.sendPackets(new S_OwnCharPack(pc));
        pc.sendPackets(new S_CharVisualUpdate(pc));
        pc.startHpRegeneration();
        pc.startMpRegeneration();
        pc.sendPackets(new S_Weather(L1World.getInstance().getWeather()));
        if (pc.getHellTime() > 0) {
            pc.beginHell(false);
        }
    }

    @Override
    public String getType() {
        return C_RESTART;
    }
}
