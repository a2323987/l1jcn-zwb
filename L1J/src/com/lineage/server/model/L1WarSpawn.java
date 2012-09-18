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
package com.lineage.server.model;

import java.lang.reflect.Constructor;

import com.lineage.server.IdFactory;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_NPCPack;
import com.lineage.server.templates.L1Npc;

// Referenced classes of package com.lineage.server.model:
// L1WarSpawn

/**
 * 产生战争
 */
public class L1WarSpawn {

    private static L1WarSpawn _instance;

    public static L1WarSpawn getInstance() {
        if (_instance == null) {
            _instance = new L1WarSpawn();
        }
        return _instance;
    }

    private Constructor<?> _constructor;

    public L1WarSpawn() {
    }

    public void SpawnCrown(final int castleId) {
        final L1Npc l1npc = NpcTable.getInstance().getTemplate(81125); // 王冠
        int[] loc = new int[3];
        loc = L1CastleLocation.getTowerLoc(castleId);
        this.SpawnWarObject(l1npc, loc[0], loc[1], (short) (loc[2]));
    }

    public void SpawnFlag(final int castleId) {
        final L1Npc l1npc = NpcTable.getInstance().getTemplate(81122); // 旗
        int[] loc = new int[5];
        loc = L1CastleLocation.getWarArea(castleId);
        int x = 0;
        int y = 0;
        final int locx1 = loc[0];
        final int locx2 = loc[1];
        final int locy1 = loc[2];
        final int locy2 = loc[3];
        final short mapid = (short) loc[4];

        for (x = locx1, y = locy1; x <= locx2; x += 8) {
            this.SpawnWarObject(l1npc, x, y, mapid);
        }
        for (x = locx2, y = locy1; y <= locy2; y += 8) {
            this.SpawnWarObject(l1npc, x, y, mapid);
        }
        for (x = locx2, y = locy2; x >= locx1; x -= 8) {
            this.SpawnWarObject(l1npc, x, y, mapid);
        }
        for (x = locx1, y = locy2; y >= locy1; y -= 8) {
            this.SpawnWarObject(l1npc, x, y, mapid);
        }
    }

    private void spawnSubTower() {
        L1Npc l1npc;
        int[] loc = new int[3];
        for (int i = 1; i <= 4; i++) {
            l1npc = NpcTable.getInstance().getTemplate(81189 + i); // 守护者之塔
            loc = L1CastleLocation.getSubTowerLoc(i);
            this.SpawnWarObject(l1npc, loc[0], loc[1], (short) (loc[2]));
        }
    }

    public void SpawnTower(final int castleId) {
        int npcId = 81111;
        if (castleId == L1CastleLocation.ADEN_CASTLE_ID) {
            npcId = 81189;
        }
        final L1Npc l1npc = NpcTable.getInstance().getTemplate(npcId); // 塔守护者
        int[] loc = new int[3];
        loc = L1CastleLocation.getTowerLoc(castleId);
        this.SpawnWarObject(l1npc, loc[0], loc[1], (short) (loc[2]));
        if (castleId == L1CastleLocation.ADEN_CASTLE_ID) {
            this.spawnSubTower();
        }
    }

    private void SpawnWarObject(final L1Npc l1npc, final int locx,
            final int locy, final short mapid) {
        try {
            if (l1npc != null) {
                final String s = l1npc.getImpl();
                this._constructor = Class.forName(
                        (new StringBuilder())
                                .append("com.lineage.server.model.Instance.")
                                .append(s).append("Instance").toString())
                        .getConstructors()[0];
                final Object aobj[] = { l1npc };
                final L1NpcInstance npc = (L1NpcInstance) this._constructor
                        .newInstance(aobj);
                npc.setId(IdFactory.getInstance().nextId());
                npc.setX(locx);
                npc.setY(locy);
                npc.setHomeX(locx);
                npc.setHomeY(locy);
                npc.setHeading(0);
                npc.setMap(mapid);
                L1World.getInstance().storeObject(npc);
                L1World.getInstance().addVisibleObject(npc);

                for (final L1PcInstance pc : L1World.getInstance()
                        .getAllPlayers()) {
                    npc.addKnownObject(pc);
                    pc.addKnownObject(npc);
                    pc.sendPackets(new S_NPCPack(npc));
                    pc.broadcastPacket(new S_NPCPack(npc));
                }
            }
        } catch (final Exception exception) {
        }
    }
}
