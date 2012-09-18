package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_Sound;

/**
 * 哨子 - 40315
 * 
 * @author jrwz
 */
public class PetWhistle implements ItemExecutor {

    public static ItemExecutor get() {
        return new PetWhistle();
    }

    private PetWhistle() {
    }

    /**
     * 道具执行
     * 
     * @param data
     *            参数
     * @param pc
     *            对象
     * @param item
     *            道具
     */
    @Override
    public void execute(final int[] data, final L1PcInstance pc,
            final L1ItemInstance item) {

        pc.sendPackets(new S_Sound(437));
        pc.broadcastPacket(new S_Sound(437));
        for (final L1NpcInstance petNpc : pc.getPetList().values()) {
            if (petNpc instanceof L1PetInstance) { // 宠物
                final L1PetInstance pet = (L1PetInstance) petNpc;
                pet.call();
            }
        }
    }
}
