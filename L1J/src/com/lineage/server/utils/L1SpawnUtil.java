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

import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.ActionCodes;
import com.lineage.server.IdFactory;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.model.L1DragonSlayer;
import com.lineage.server.model.L1NpcDeleteTimer;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_CharVisualUpdate;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_NPCPack;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 召唤控制项
 */
public class L1SpawnUtil {

    private static Logger _log = Logger.getLogger(L1SpawnUtil.class.getName());

    /**
     * 依PC位置召唤指定NPC
     * 
     * @param pc
     *            召唤者
     * @param npcId
     *            NPC编号
     * @param randomRange
     *            召唤距离 (不为0 NPC召唤与PC将有距离 否则将重叠)
     * @param timeMillisToDelete
     *            存在时间(秒) 小于等于0不限制
     */
    public static void spawn(final L1PcInstance pc, final int npcId,
            final int randomRange, final int timeMillisToDelete) {
        try {
            final L1NpcInstance npc = NpcTable.getInstance().newNpcInstance(
                    npcId);
            npc.setId(IdFactory.getInstance().nextId());
            npc.setMap(pc.getMapId());
            if (randomRange == 0) {
                npc.getLocation().set(pc.getLocation());
                npc.getLocation().forward(pc.getHeading());
            } else {
                int tryCount = 0;
                do {
                    tryCount++;
                    npc.setX(pc.getX() + Random.nextInt(randomRange)
                            - Random.nextInt(randomRange));
                    npc.setY(pc.getY() + Random.nextInt(randomRange)
                            - Random.nextInt(randomRange));
                    if (npc.getMap().isInMap(npc.getLocation())
                            && npc.getMap().isPassable(npc.getLocation())) {
                        break;
                    }
                    Thread.sleep(1);
                } while (tryCount < 50);

                if (tryCount >= 50) {
                    npc.getLocation().set(pc.getLocation());
                    npc.getLocation().forward(pc.getHeading());
                }
            }

            npc.setHomeX(npc.getX());
            npc.setHomeY(npc.getY());
            npc.setHeading(pc.getHeading());
            // 纪录龙之门扉编号
            if (npc.getNpcId() == 81273) {
                for (int i = 0; i < 6; i++) {
                    if (!L1DragonSlayer.getInstance().getPortalNumber()[i]) {
                        L1DragonSlayer.getInstance().setPortalNumber(i, true);
                        // 重置副本
                        L1DragonSlayer.getInstance().resetDragonSlayer(i);
                        npc.setPortalNumber(i);
                        L1DragonSlayer.getInstance().portalPack()[i] = npc;
                        break;
                    }
                }
            } else if (npc.getNpcId() == 81274) {
                for (int i = 6; i < 12; i++) {
                    if (!L1DragonSlayer.getInstance().getPortalNumber()[i]) {
                        L1DragonSlayer.getInstance().setPortalNumber(i, true);
                        // 重置副本
                        L1DragonSlayer.getInstance().resetDragonSlayer(i);
                        npc.setPortalNumber(i);
                        L1DragonSlayer.getInstance().portalPack()[i] = npc;
                        break;
                    }
                }
            }
            L1World.getInstance().storeObject(npc);
            L1World.getInstance().addVisibleObject(npc);

            if ((npc.getTempCharGfx() == 7548)
                    || (npc.getTempCharGfx() == 7550)
                    || (npc.getTempCharGfx() == 7552)
                    || (npc.getTempCharGfx() == 7554)
                    || (npc.getTempCharGfx() == 7585)
                    || (npc.getTempCharGfx() == 7591)) {
                npc.broadcastPacket(new S_NPCPack(npc));
                npc.broadcastPacket(new S_DoActionGFX(npc.getId(),
                        ActionCodes.ACTION_AxeWalk));
            } else if ((npc.getTempCharGfx() == 7539)
                    || (npc.getTempCharGfx() == 7557)
                    || (npc.getTempCharGfx() == 7558)
                    || (npc.getTempCharGfx() == 7864)
                    || (npc.getTempCharGfx() == 7869)
                    || (npc.getTempCharGfx() == 7870)) {
                for (final L1PcInstance _pc : L1World.getInstance()
                        .getVisiblePlayer(npc, 50)) {
                    if (npc.getTempCharGfx() == 7539) {
                        _pc.sendPackets(new S_ServerMessage(1570));
                    } else if (npc.getTempCharGfx() == 7864) {
                        _pc.sendPackets(new S_ServerMessage(1657));
                    }
                    npc.onPerceive(_pc);
                    final S_DoActionGFX gfx = new S_DoActionGFX(npc.getId(),
                            ActionCodes.ACTION_AxeWalk);
                    _pc.sendPackets(gfx);
                }
                npc.npcSleepTime(ActionCodes.ACTION_AxeWalk,
                        L1NpcInstance.ATTACK_SPEED);
            } else if (npc.getTempCharGfx() == 145) { // 史巴托
                npc.setStatus(11);
                npc.broadcastPacket(new S_NPCPack(npc));
                npc.broadcastPacket(new S_DoActionGFX(npc.getId(),
                        ActionCodes.ACTION_Appear));
                npc.setStatus(0);
                npc.broadcastPacket(new S_CharVisualUpdate(npc, npc.getStatus()));
            }

            npc.turnOnOffLight();
            npc.startChat(L1NpcInstance.CHAT_TIMING_APPEARANCE); // 开始喊话
            if (0 < timeMillisToDelete) {
                final L1NpcDeleteTimer timer = new L1NpcDeleteTimer(npc,
                        timeMillisToDelete);
                timer.begin();
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }
}
