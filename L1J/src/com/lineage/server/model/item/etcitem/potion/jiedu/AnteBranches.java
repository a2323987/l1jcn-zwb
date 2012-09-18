package com.lineage.server.model.item.etcitem.potion.jiedu;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 安特之树枝 - 40507
 * 
 * @author jrwz
 */
public class AnteBranches implements ItemExecutor {

    public static ItemExecutor get() {
        return new AnteBranches();
    }

    private AnteBranches() {
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

        // 动画ID
        pc.sendPackets(new S_SkillSound(pc.getId(), 192));
        pc.broadcastPacket(new S_SkillSound(pc.getId(), 192));
        pc.curePoison();

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
