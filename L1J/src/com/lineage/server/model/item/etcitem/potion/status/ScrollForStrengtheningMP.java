package com.lineage.server.model.item.etcitem.potion.status;

import static com.lineage.server.model.skill.L1SkillId.EFFECT_STRENGTHENING_MP;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_MPUpdate;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 魔力增强卷轴 - 47008
 * 
 * @author jrwz
 */
public class ScrollForStrengtheningMP implements ItemExecutor {

    public static ItemExecutor get() {
        return new ScrollForStrengtheningMP();
    }

    private ScrollForStrengtheningMP() {
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

        final short skillId = EFFECT_STRENGTHENING_MP;
        final short time = 3600;
        final short gfxid = 6994;

        pc.deleteRepeatedSkills(skillId);

        pc.sendPackets(new S_SkillSound(pc.getId(), gfxid));
        pc.broadcastPacket(new S_SkillSound(pc.getId(), gfxid));
        pc.setSkillEffect(skillId, time * 1000);
        pc.addMaxMp(40); // 最高MP+40
        pc.addMpr(4); // 回魔量+4
        pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp())); // 更新最高魔量与当前魔量

        pc.getInventory().removeItem(item, 1);
    }
}
