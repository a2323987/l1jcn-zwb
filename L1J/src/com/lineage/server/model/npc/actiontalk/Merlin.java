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
 * MAY BE CONSIDERED TO BE A CONTRACT,
 * THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package com.lineage.server.model.npc.actiontalk;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.npc.NpcExecutor;
import com.lineage.server.serverpackets.S_NPCTalkReturn;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * Class <code>Merlin</code> 奇岩地监传送NPC:魔法师莫琳 - 81287.
 * 
 * @author jrwz
 * @version 2012-3-27上午02:31:13
 * @see com.lineage.server.model.npc.actiontalk
 * @since JDK1.7
 */
public final class Merlin implements NpcExecutor {

    public static NpcExecutor get() {
        return new Merlin();
    }

    private Merlin() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.lineage.server.model.npc.NpcExecutor#attack(com.lineage.server.model
     * .Instance.L1PcInstance, com.lineage.server.model.Instance.L1NpcInstance)
     */
    @Override
    public void attack(final L1PcInstance pc, final L1NpcInstance npc) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.lineage.server.model.npc.NpcExecutor#death(com.lineage.server.model
     * .L1Character, com.lineage.server.model.Instance.L1NpcInstance)
     */
    @Override
    public L1PcInstance death(final L1Character lastAttacker,
            final L1NpcInstance npc) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.lineage.server.model.npc.NpcExecutor#npcTalkAction(com.lineage.server
     * .model.Instance.L1PcInstance,
     * com.lineage.server.model.Instance.L1NpcInstance, java.lang.String, int)
     */
    @Override
    public void npcTalkAction(final L1PcInstance pc, final L1NpcInstance npc,
            final String s, final int objid) {
        if (s.equalsIgnoreCase("teleportURL")) {
            pc.sendPackets(new S_NPCTalkReturn(npc.getId(), "merlin2"));
        }
        if (s.equalsIgnoreCase("teleport giranD")) {
            final int time = pc.getRocksPrisonTime();
            final int maxTime = 10800; // 3小时
            if (time >= maxTime) {
                final S_ServerMessage msg = new S_ServerMessage(1523,
                        String.valueOf(180));
                pc.sendPackets(msg); // 您已经使用完了可以逗留在地监的%0分钟
                return;
            }

            if (pc.getInventory().consumeItem(40308, 1000)) { // 检查身上金币是否足够
                final int locx = 32810;
                final int locy = 32731;
                final short mapid = 53;
                final int heading = 5;
                L1Teleport.teleport(pc, locx, locy, mapid, heading, true);
                pc.startRocksPrison();
            } else {
                final S_ServerMessage msg = new S_ServerMessage(189);
                pc.sendPackets(msg); // \f1金币不足。
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.lineage.server.model.npc.NpcExecutor#npcTalkReturn(com.lineage.server
     * .model.Instance.L1PcInstance,
     * com.lineage.server.model.Instance.L1NpcInstance)
     */
    @Override
    public void npcTalkReturn(final L1PcInstance pc, final L1NpcInstance npc) {

        pc.sendPackets(new S_NPCTalkReturn(npc.getId(), "merlin1"));

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.lineage.server.model.npc.NpcExecutor#spawn(com.lineage.server.model
     * .Instance.L1NpcInstance)
     */
    @Override
    public void spawn(final L1NpcInstance npc) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lineage.server.model.npc.NpcExecutor#type()
     */
    @Override
    public int type() {
        return 3;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.lineage.server.model.npc.NpcExecutor#work(com.lineage.server.model
     * .Instance.L1NpcInstance)
     */
    @Override
    public void work(final L1NpcInstance npc) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lineage.server.model.npc.NpcExecutor#workTime()
     */
    @Override
    public int workTime() {
        // TODO Auto-generated method stub
        return 0;
    }

}
