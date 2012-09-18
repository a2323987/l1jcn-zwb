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

import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.IdFactory;
import com.lineage.server.datatables.MobGroupTable;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.templates.L1MobGroup;
import com.lineage.server.templates.L1NpcCount;
import com.lineage.server.utils.Random;

// Referenced classes of package com.lineage.server.model:
// L1MobGroupSpawn

/**
 * 产生MOB群组
 */
public class L1MobGroupSpawn {

    private static final Logger _log = Logger.getLogger(L1MobGroupSpawn.class
            .getName());

    private static L1MobGroupSpawn _instance;

    public static L1MobGroupSpawn getInstance() {
        if (_instance == null) {
            _instance = new L1MobGroupSpawn();
        }
        return _instance;
    }

    private boolean _isRespawnScreen;

    private boolean _isInitSpawn;

    private L1MobGroupSpawn() {
    }

    private boolean canSpawn(final L1NpcInstance mob) {
        if (mob.getMap().isInMap(mob.getLocation())
                && mob.getMap().isPassable(mob.getLocation())) {
            if (this._isRespawnScreen) {
                return true;
            }
            if (L1World.getInstance().getVisiblePlayer(mob).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void doSpawn(final L1NpcInstance leader, final int groupId,
            final boolean isRespawnScreen, final boolean isInitSpawn) {

        final L1MobGroup mobGroup = MobGroupTable.getInstance().getTemplate(
                groupId);
        if (mobGroup == null) {
            return;
        }

        L1NpcInstance mob;
        this._isRespawnScreen = isRespawnScreen;
        this._isInitSpawn = isInitSpawn;

        final L1MobGroupInfo mobGroupInfo = new L1MobGroupInfo();

        mobGroupInfo.setRemoveGroup(mobGroup.isRemoveGroupIfLeaderDie());
        mobGroupInfo.addMember(leader);

        for (final L1NpcCount minion : mobGroup.getMinions()) {
            if (minion.isZero()) {
                continue;
            }
            for (int i = 0; i < minion.getCount(); i++) {
                mob = this.spawn(leader, minion.getId());
                if (mob != null) {
                    mobGroupInfo.addMember(mob);
                }
            }
        }
    }

    private L1NpcInstance spawn(final L1NpcInstance leader, final int npcId) {
        L1NpcInstance mob = null;
        try {
            mob = NpcTable.getInstance().newNpcInstance(npcId);

            mob.setId(IdFactory.getInstance().nextId());

            mob.setHeading(leader.getHeading());
            mob.setMap(leader.getMapId());
            mob.setMovementDistance(leader.getMovementDistance());
            mob.setRest(leader.isRest());

            mob.setX(leader.getX() + Random.nextInt(5) - 2);
            mob.setY(leader.getY() + Random.nextInt(5) - 2);
            // 地图外、障碍物上、同画面内有PC、与领导者相同的坐标
            if (!this.canSpawn(mob)) {
                mob.setX(leader.getX());
                mob.setY(leader.getY());
            }
            mob.setHomeX(mob.getX());
            mob.setHomeY(mob.getY());

            if (mob instanceof L1MonsterInstance) {
                ((L1MonsterInstance) mob).initHideForMinion(leader);
            }

            mob.setSpawn(leader.getSpawn());
            mob.setreSpawn(leader.isReSpawn());
            mob.setSpawnNumber(leader.getSpawnNumber());

            if (mob instanceof L1MonsterInstance) {
                if (mob.getMapId() == 666) {
                    ((L1MonsterInstance) mob).set_storeDroped(true);
                }
            }

            L1World.getInstance().storeObject(mob);
            L1World.getInstance().addVisibleObject(mob);

            if (mob instanceof L1MonsterInstance) {
                if (!this._isInitSpawn && (mob.getHiddenStatus() == 0)) {
                    mob.onNpcAI(); // 开始怪物AI
                }
            }
            mob.turnOnOffLight();
            mob.startChat(L1NpcInstance.CHAT_TIMING_APPEARANCE); // 开始喊话
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
        return mob;
    }

}
