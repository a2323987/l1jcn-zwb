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
package com.lineage.server.command.executor;

import java.util.Map;
import java.util.StringTokenizer;

import com.lineage.server.datatables.NpcSpawnTable;
import com.lineage.server.datatables.SpawnTable;
import com.lineage.server.model.L1Spawn;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.utils.collections.Maps;

public class L1ToSpawn implements L1CommandExecutor {
    private static final Map<Integer, Integer> _spawnId = Maps.newMap();

    public static L1CommandExecutor getInstance() {
        return new L1ToSpawn();
    }

    private L1ToSpawn() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            if (!_spawnId.containsKey(pc.getId())) {
                _spawnId.put(pc.getId(), 0);
            }
            int id = _spawnId.get(pc.getId());
            if (arg.isEmpty() || arg.equals("+")) {
                id++;
            } else if (arg.equals("-")) {
                id--;
            } else {
                final StringTokenizer st = new StringTokenizer(arg);
                id = Integer.parseInt(st.nextToken());
            }
            L1Spawn spawn = NpcSpawnTable.getInstance().getTemplate(id);
            if (spawn == null) {
                spawn = SpawnTable.getInstance().getTemplate(id);
            }
            if (spawn != null) {
                L1Teleport.teleport(pc, spawn.getLocX(), spawn.getLocY(),
                        spawn.getMapId(), 5, false);
                pc.sendPackets(new S_SystemMessage("spawnid(" + id + ")已传送到"));
            } else {
                pc.sendPackets(new S_SystemMessage("spawnid(" + id + ")找不到"));
            }
            _spawnId.put(pc.getId(), id);
        } catch (final Exception exception) {
            pc.sendPackets(new S_SystemMessage(cmdName + " spawnid|+|-"));
        }
    }
}
