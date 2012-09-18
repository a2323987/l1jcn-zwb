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

import java.lang.reflect.Constructor;
import java.util.StringTokenizer;

import com.lineage.server.IdFactory;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.templates.L1Npc;

public class L1GfxId implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1GfxId();
    }

    private L1GfxId() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final StringTokenizer st = new StringTokenizer(arg);
            final int gfxid = Integer.parseInt(st.nextToken(), 10);
            final int count = Integer.parseInt(st.nextToken(), 10);
            for (int i = 0; i < count; i++) {
                final L1Npc l1npc = NpcTable.getInstance().getTemplate(45001);
                if (l1npc != null) {
                    final String s = l1npc.getImpl();
                    final Constructor<?> constructor = Class.forName(
                            "com.lineage.server.model.Instance." + s
                                    + "Instance").getConstructors()[0];
                    final Object aobj[] = { l1npc };
                    final L1NpcInstance npc = (L1NpcInstance) constructor
                            .newInstance(aobj);
                    npc.setId(IdFactory.getInstance().nextId());
                    npc.setGfxId(gfxid + i);
                    npc.setTempCharGfx(0);
                    npc.setNameId("");
                    npc.setMap(pc.getMapId());
                    npc.setX(pc.getX() + i * 2);
                    npc.setY(pc.getY() + i * 2);
                    npc.setHomeX(npc.getX());
                    npc.setHomeY(npc.getY());
                    npc.setHeading(4);

                    L1World.getInstance().storeObject(npc);
                    L1World.getInstance().addVisibleObject(npc);
                }
            }
        } catch (final Exception exception) {
            pc.sendPackets(new S_SystemMessage(cmdName
                    + " 请输入  动画编号  动画数量  人物ID。"));
        }
    }
}
