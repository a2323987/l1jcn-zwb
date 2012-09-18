package com.lineage.server.model.item.etcitem.potion.status;

import static com.lineage.server.model.skill.L1SkillId.EFFECT_ENCHANTING_BATTLE;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_SPMR;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 强化战斗卷轴 - 47009
 * 
 * @author jrwz
 */
public class ScrollForEnchantingBattle implements ItemExecutor {

    public static ItemExecutor get() {
        return new ScrollForEnchantingBattle();
    }

    private ScrollForEnchantingBattle() {
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

        final short skillId = EFFECT_ENCHANTING_BATTLE;
        final short time = 3600;
        final short gfxid = 6995;

        pc.deleteRepeatedSkills(skillId);

        pc.sendPackets(new S_SkillSound(pc.getId(), gfxid));
        pc.broadcastPacket(new S_SkillSound(pc.getId(), gfxid));
        pc.setSkillEffect(skillId, time * 1000);
        pc.addHitup(3); // 攻击成功 +3
        pc.addDmgup(3); // 额外攻击点数 +3
        pc.addBowHitup(3); // 远距离命中率 +3
        pc.addBowDmgup(3); // 远距离攻击力 +3
        pc.addSp(3); // 魔攻 +3
        pc.sendPackets(new S_SPMR(pc)); // 更新魔攻与魔防

        pc.getInventory().removeItem(item, 1);
    }
}
