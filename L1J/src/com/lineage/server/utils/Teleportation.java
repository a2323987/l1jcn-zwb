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
package com.lineage.server.utils;

import static com.lineage.server.model.skill.L1SkillId.MEDITATION;
import static com.lineage.server.model.skill.L1SkillId.WIND_SHACKLE;

import java.util.HashSet;

import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1DragonSlayer;
import com.lineage.server.model.L1Location;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1DollInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.model.map.L1Map;
import com.lineage.server.model.map.L1WorldMap;
import com.lineage.server.serverpackets.S_CharVisualUpdate;
import com.lineage.server.serverpackets.S_DollPack;
import com.lineage.server.serverpackets.S_MapID;
import com.lineage.server.serverpackets.S_OtherCharPacks;
import com.lineage.server.serverpackets.S_OwnCharPack;
import com.lineage.server.serverpackets.S_PetPack;
import com.lineage.server.serverpackets.S_SkillIconWindShackle;
import com.lineage.server.serverpackets.S_SummonPack;

// Referenced classes of package com.lineage.server.utils:
// FaceToFace

/**
 * 更新移动后的坐标
 */
public class Teleportation {

    /**
     * 更新移动后的坐标
     * 
     * @param pc
     */
    public static void actionTeleportation(final L1PcInstance pc) {
        if (pc.isDead() || pc.isTeleport()) {
            return;
        }

        int x = pc.getTeleportX();
        int y = pc.getTeleportY();
        short mapId = pc.getTeleportMapId();
        final int head = pc.getTeleportHeading();

        // 原来的坐标传送，如果目标是无效的(GM除外)
        // 防止坐标异常
        final L1Map map = L1WorldMap.getInstance().getMap(mapId);

        if (!map.isInMap(x, y) && !pc.isGm()) {
            x = pc.getX();
            y = pc.getY();
            mapId = pc.getMapId();
        }

        pc.setTeleport(true);

        final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
        if (clan != null) {
            if (clan.getWarehouseUsingChar() == pc.getId()) { // 使用血盟仓库中
                clan.setWarehouseUsingChar(0); // 解除使用状态
            }
        }

        L1World.getInstance().moveVisibleObject(pc, mapId);
        pc.setLocation(x, y, mapId);
        pc.setHeading(head);
        pc.sendPackets(new S_MapID(pc.getMapId(), pc.getMap().isUnderwater()));

        if (pc.isReserveGhost()) { // 解除幽灵状态
            pc.endGhost();
        }
        if (pc.isGhost() || pc.isGmInvis()) {
        } else if (pc.isInvisble()) {
            pc.broadcastPacketForFindInvis(new S_OtherCharPacks(pc, true), true);
        } else {
            pc.broadcastPacket(new S_OtherCharPacks(pc));
        }
        pc.sendPackets(new S_OwnCharPack(pc));

        pc.removeAllKnownObjects();
        pc.sendVisualEffectAtTeleport(); // クラウン、毒、水中等の視覚効果を表示
        pc.updateObject();
        // spr番号6310, 5641の変身中にテレポートするとテレポート後に移動できなくなる
        // 武器を着脱すると移動できるようになるため、S_CharVisualUpdateを送信する
        pc.sendPackets(new S_CharVisualUpdate(pc));

        pc.killSkillEffectTimer(MEDITATION);
        pc.setCallClanId(0); // コールクランを唱えた後に移動すると召喚無効

        /*
         * subjects ペットとサモンのテレポート先画面内へ居たプレイヤー。
         * 各ペット毎にUpdateObjectを行う方がコード上ではスマートだが、
         * ネットワーク負荷が大きくなる為、一旦Setへ格納して最後にまとめてUpdateObjectする。
         */
        final HashSet<L1PcInstance> subjects = new HashSet<L1PcInstance>();
        subjects.add(pc);

        if (!pc.isGhost()) {
            if (pc.getMap().isTakePets()) {
                // ペットとサモンも一緒に移動させる。
                for (final L1NpcInstance petNpc : pc.getPetList().values()) {
                    // テレポート先の設定
                    final L1Location loc = pc.getLocation().randomLocation(3,
                            false);
                    int nx = loc.getX();
                    int ny = loc.getY();
                    if ((pc.getMapId() == 5125) || (pc.getMapId() == 5131)
                            || (pc.getMapId() == 5132)
                            || (pc.getMapId() == 5133)
                            || (pc.getMapId() == 5134)) { // ペットマッチ会場
                        nx = 32799 + Random.nextInt(5) - 3;
                        ny = 32864 + Random.nextInt(5) - 3;
                    }
                    teleport(petNpc, nx, ny, mapId, head);
                    if (petNpc instanceof L1SummonInstance) { // サモンモンスター
                        final L1SummonInstance summon = (L1SummonInstance) petNpc;
                        pc.sendPackets(new S_SummonPack(summon, pc));
                    } else if (petNpc instanceof L1PetInstance) { // ペット
                        final L1PetInstance pet = (L1PetInstance) petNpc;
                        pc.sendPackets(new S_PetPack(pet, pc));
                    }

                    for (final L1PcInstance visiblePc : L1World.getInstance()
                            .getVisiblePlayer(petNpc)) {
                        // テレポート元と先に同じPCが居た場合、正しく更新されない為、一度removeする。
                        visiblePc.removeKnownObject(petNpc);
                        subjects.add(visiblePc);
                    }
                }

                // マジックドールも一緒に移動させる。
                for (final L1DollInstance doll : pc.getDollList().values()) {
                    // テレポート先の設定
                    final L1Location loc = pc.getLocation().randomLocation(3,
                            false);
                    final int nx = loc.getX();
                    final int ny = loc.getY();

                    teleport(doll, nx, ny, mapId, head);
                    pc.sendPackets(new S_DollPack(doll));

                    for (final L1PcInstance visiblePc : L1World.getInstance()
                            .getVisiblePlayer(doll)) {
                        // テレポート元と先に同じPCが居た場合、正しく更新されない為、一度removeする。
                        visiblePc.removeKnownObject(doll);
                        subjects.add(visiblePc);
                    }
                }
            } else {
                for (final L1DollInstance doll : pc.getDollList().values()) {
                    // テレポート先の設定
                    final L1Location loc = pc.getLocation().randomLocation(3,
                            false);
                    final int nx = loc.getX();
                    final int ny = loc.getY();

                    teleport(doll, nx, ny, mapId, head);
                    pc.sendPackets(new S_DollPack(doll));

                    for (final L1PcInstance visiblePc : L1World.getInstance()
                            .getVisiblePlayer(doll)) {
                        // テレポート元と先に同じPCが居た場合、正しく更新されない為、一度removeする。
                        visiblePc.removeKnownObject(doll);
                        subjects.add(visiblePc);
                    }
                }
            }
        }

        for (final L1PcInstance updatePc : subjects) {
            updatePc.updateObject();
        }

        pc.setTeleport(false);

        if (pc.hasSkillEffect(WIND_SHACKLE)) {
            pc.sendPackets(new S_SkillIconWindShackle(pc.getId(), pc
                    .getSkillEffectTimeSec(WIND_SHACKLE)));
        }

        // 副本编号与副本地图不符
        if ((pc.getPortalNumber() != -1)
                && (pc.getMapId() != (1005 + pc.getPortalNumber()))) {
            L1DragonSlayer.getInstance().removePlayer(pc, pc.getPortalNumber());
            pc.setPortalNumber(-1);
        }
        // 离开旅馆地图，旅馆钥匙归零
        if ((pc.getMapId() <= 10000) && (pc.getInnKeyId() != 0)) {
            pc.setInnKeyId(0);
        }
    }

    private static void teleport(final L1NpcInstance npc, final int x,
            final int y, final short map, final int head) {
        L1World.getInstance().moveVisibleObject(npc, map);
        L1WorldMap.getInstance().getMap(npc.getMapId())
                .setPassable(npc.getX(), npc.getY(), true);
        npc.setX(x);
        npc.setY(y);
        npc.setMap(map);
        npc.setHeading(head);
        L1WorldMap.getInstance().getMap(npc.getMapId())
                .setPassable(npc.getX(), npc.getY(), false);
    }

    private Teleportation() {
    }

}
