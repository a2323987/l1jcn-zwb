package com.lineage.server.model.item.etcitem.potion.status;

import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLESS_OF_MAZU;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 妈祖祝福平安符 - 47005
 * 
 * @author jrwz
 */
public class BlessOfMaZu implements ItemExecutor {

    public static ItemExecutor get() {
        return new BlessOfMaZu();
    }

    private BlessOfMaZu() {
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

        final short skillId = EFFECT_BLESS_OF_MAZU;
        final short time = 2400;
        final short gfxid = 7321;

        pc.deleteRepeatedSkills(skillId); // 与妖精属性魔法相冲！

        pc.sendPackets(new S_SkillSound(pc.getId(), gfxid));
        pc.broadcastPacket(new S_SkillSound(pc.getId(), gfxid));
        pc.setSkillEffect(skillId, time * 1000);
        pc.addHitup(3); // 攻击成功 +3
        pc.addDmgup(3); // 额外攻击点数 +3
        pc.addMpr(2); // 回魔量+2

        pc.getInventory().removeItem(item, 1);
    }
}
