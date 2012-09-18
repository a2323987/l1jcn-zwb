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

import static com.lineage.server.model.skill.L1SkillId.FIRE_WALL;

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.IdFactory;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.model.Instance.L1EffectInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.map.L1Map;
import com.lineage.server.model.map.L1WorldMap;
import com.lineage.server.model.skill.NpcFireDamage;
import com.lineage.server.serverpackets.S_NPCPack;
import com.lineage.server.templates.L1Npc;

// Referenced classes of package com.lineage.server.model:
// L1EffectSpawn

/**
 * 产生效果
 */
public class L1EffectSpawn {

    private static final Logger _log = Logger.getLogger(L1EffectSpawn.class
            .getName());

    private static L1EffectSpawn _instance;

    public static L1EffectSpawn getInstance() {
        if (_instance == null) {
            _instance = new L1EffectSpawn();
        }
        return _instance;
    }

    private Constructor<?> _constructor;

    private L1EffectSpawn() {
    }

    /** 产生火牢 */
    public void doSpawnFireWall(final L1Character cha, final int targetX,
            final int targetY) {
        final L1Npc firewall = NpcTable.getInstance().getTemplate(81157); // 火牢
        final int duration = SkillsTable.getInstance().getTemplate(FIRE_WALL)
                .getBuffDuration();

        if (firewall == null) {
            throw new NullPointerException("没有找到火牢数据:npcid=81157");
        }

        L1Character base = cha;
        for (int i = 0; i < 8; i++) {
            final int a = base.targetDirection(targetX, targetY);
            int x = base.getX();
            int y = base.getY();
            if (a == 1) {
                x++;
                y--;
            } else if (a == 2) {
                x++;
            } else if (a == 3) {
                x++;
                y++;
            } else if (a == 4) {
                y++;
            } else if (a == 5) {
                x--;
                y++;
            } else if (a == 6) {
                x--;
            } else if (a == 7) {
                x--;
                y--;
            } else if (a == 0) {
                y--;
            }
            if (!base.isAttackPosition(x, y, 1)) {
                x = base.getX();
                y = base.getY();
            }
            final L1Map map = L1WorldMap.getInstance().getMap(cha.getMapId());
            if (!map.isArrowPassable(x, y, cha.getHeading())) {
                break;
            }

            final L1EffectInstance effect = this.spawnEffect(81157,
                    duration * 1000, x, y, cha.getMapId());
            if (effect == null) {
                break;
            }
            for (final L1Object objects : L1World.getInstance()
                    .getVisibleObjects(effect, 0)) {
                if (objects instanceof L1EffectInstance) {
                    final L1EffectInstance npc = (L1EffectInstance) objects;
                    if (npc.getNpcTemplate().get_npcId() == 81157) {
                        npc.deleteMe();
                    }
                }
            }
            if ((targetX == x) && (targetY == y)) {
                break;
            }
            base = effect;
        }
    }

    /** 巴拉卡斯火牢 */
    public void doSpawnFireWallforNpc(final L1Character _user,
            final L1Character target) {
        @SuppressWarnings("unused")
        L1Character base = _user;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                final L1EffectInstance effect = this.spawnEffect(81157,
                        10 * 1000, target.getX() + i, target.getY() + j,
                        target.getMapId());
                if (effect == null) {
                    break;
                }
                for (final L1Object objects : L1World.getInstance()
                        .getVisibleObjects(effect, 0)) {
                    if (objects instanceof L1EffectInstance) {
                        final L1EffectInstance npc = (L1EffectInstance) objects;
                        if (npc.getNpcTemplate().get_npcId() == 81157) {
                            npc.deleteMe();
                        }
                    }
                }
                // 火牢伤害
                @SuppressWarnings("unused")
                final L1Map map = L1WorldMap.getInstance().getMap(
                        _user.getMapId());

                final NpcFireDamage firedamage = new NpcFireDamage(_user,
                        effect);
                firedamage.onDamageAction();
                // 火牢伤害 end
                base = effect;
            }
        }
    }

    /**
     * 设置生成效果对象
     * 
     * @param npcId
     *            影响NPC的模板ID
     * @param time
     *            存在时间(ms)
     * @param locX
     *            设置坐标X
     * @param locY
     *            设置坐标Y
     * @param mapId
     *            设置地图的ID
     * @return 生成的对象的影响
     */
    public L1EffectInstance spawnEffect(final int npcId, final int time,
            final int locX, final int locY, final short mapId) {
        return this.spawnEffect(npcId, time, locX, locY, mapId, null, 0);
    }

    public L1EffectInstance spawnEffect(final int npcId, final int time,
            final int locX, final int locY, final short mapId,
            final L1PcInstance user, final int skiiId) {
        final L1Npc template = NpcTable.getInstance().getTemplate(npcId);
        L1EffectInstance effect = null;

        if (template == null) {
            return null;
        }

        final String className = (new StringBuilder())
                .append("com.lineage.server.model.Instance.")
                .append(template.getImpl()).append("Instance").toString();

        try {
            this._constructor = Class.forName(className).getConstructors()[0];
            final Object obj[] = { template };
            effect = (L1EffectInstance) this._constructor.newInstance(obj);

            effect.setId(IdFactory.getInstance().nextId());
            effect.setGfxId(template.get_gfxid());
            effect.setX(locX);
            effect.setY(locY);
            effect.setHomeX(locX);
            effect.setHomeY(locY);
            effect.setHeading(0);
            effect.setMap(mapId);
            effect.setUser(user);
            effect.setSkillId(skiiId);
            L1World.getInstance().storeObject(effect);
            L1World.getInstance().addVisibleObject(effect);

            for (final L1PcInstance pc : L1World.getInstance()
                    .getRecognizePlayer(effect)) {
                effect.addKnownObject(pc);
                pc.addKnownObject(effect);
                pc.sendPackets(new S_NPCPack(effect));
                pc.broadcastPacket(new S_NPCPack(effect));
            }
            final L1NpcDeleteTimer timer = new L1NpcDeleteTimer(effect, time);
            timer.begin();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }

        return effect;
    }

}
