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

import com.lineage.Config;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.map.L1Map;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.serverpackets.S_Teleport;
import com.lineage.server.utils.Teleportation;

/**
 * 传送
 */
public class L1Teleport {

    /** 瞬移技能类型 */
    public static final int TELEPORT = 0;
    /** 位置变化 */
    public static final int CHANGE_POSITION = 1;
    /** 集体传送 */
    public static final int ADVANCED_MASS_TELEPORT = 2;
    /** 呼叫血盟 */
    public static final int CALL_CLAN = 3;

    // 瞬移动画效果teleport(白), change position e(青), ad mass teleport e(赤), call
    // clan(緑)
    public static final int[] EFFECT_SPR = { 169, 2235, 2236, 2281 };

    public static final int[] EFFECT_TIME = { 280, 440, 440, 1120 };

    /** 随机传送 */
    public static void randomTeleport(final L1PcInstance pc,
            final boolean effectable) {
        // まだ本サーバのランテレ処理と違うところが結構あるような・・・
        final L1Location newLocation = pc.getLocation().randomLocation(200,
                true);
        final int newX = newLocation.getX();
        final int newY = newLocation.getY();
        final short mapId = (short) newLocation.getMapId();

        L1Teleport.teleport(pc, newX, newY, mapId, 5, effectable);
    }

    public static void teleport(final L1PcInstance pc, final int x,
            final int y, final short mapid, final int head,
            final boolean effectable) {
        teleport(pc, x, y, mapid, head, effectable, TELEPORT);
    }

    public static void teleport(final L1PcInstance pc, final int x,
            final int y, final short mapId, final int head,
            final boolean effectable, final int skillType) {

        // pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_TELEPORT_UNLOCK,
        // false));

        // 查看效果
        if (effectable
                && ((skillType >= 0) && (skillType <= EFFECT_SPR.length))) {
            final S_SkillSound packet = new S_SkillSound(pc.getId(),
                    EFFECT_SPR[skillType]);
            pc.sendPackets(packet);
            pc.broadcastPacket(packet);

            // テレポート以外のsprはキャラが消えないので見た目上送っておきたいが
            // 移動中だった場合クラ落ちすることがある
            // if (skillType != TELEPORT) {
            // pc.sendPackets(new S_DeleteNewObject(pc));
            // pc.broadcastPacket(new S_DeleteObjectFromScreen(pc));
            // }

            try {
                Thread.sleep((int) (EFFECT_TIME[skillType] * 0.7));
            } catch (final Exception e) {
            }
        }

        pc.setTeleportX(x);
        pc.setTeleportY(y);
        pc.setTeleportMapId(mapId);
        pc.setTeleportHeading(head);
        if (Config.SEND_PACKET_BEFORE_TELEPORT) {
            pc.sendPackets(new S_Teleport(pc));
        } else {
            Teleportation.actionTeleportation(pc);
        }
    }

    /**  */
    public static void teleport(final L1PcInstance pc, final L1Location loc,
            final int head, final boolean effectable) {
        teleport(pc, loc.getX(), loc.getY(), (short) loc.getMapId(), head,
                effectable, TELEPORT);
    }

    public static void teleport(final L1PcInstance pc, final L1Location loc,
            final int head, final boolean effectable, final int skillType) {
        teleport(pc, loc.getX(), loc.getY(), (short) loc.getMapId(), head,
                effectable, skillType);
    }

    /**
     * target角色的distance指定传送到身前(传送到目标前)。指定的地图 什么也不做。
     */
    public static void teleportToTargetFront(final L1Character cha,
            final L1Character target, final int distance) {
        int locX = target.getX();
        int locY = target.getY();
        final int heading = target.getHeading();
        final L1Map map = target.getMap();
        final short mapId = target.getMapId();

        // 确定目标面向的坐标。
        switch (heading) {
            case 1:
                locX += distance;
                locY -= distance;
                break;

            case 2:
                locX += distance;
                break;

            case 3:
                locX += distance;
                locY += distance;
                break;

            case 4:
                locY += distance;
                break;

            case 5:
                locX -= distance;
                locY += distance;
                break;

            case 6:
                locX -= distance;
                break;

            case 7:
                locX -= distance;
                locY -= distance;
                break;

            case 0:
                locY -= distance;
                break;

            default:
                break;

        }

        if (map.isPassable(locX, locY)) {
            if (cha instanceof L1PcInstance) {
                teleport((L1PcInstance) cha, locX, locY, mapId,
                        cha.getHeading(), true);
            } else if (cha instanceof L1NpcInstance) {
                ((L1NpcInstance) cha).teleport(locX, locY, cha.getHeading());
            }
        }
    }

    private L1Teleport() {
    }
}
