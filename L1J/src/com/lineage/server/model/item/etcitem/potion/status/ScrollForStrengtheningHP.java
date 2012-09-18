package com.lineage.server.model.item.etcitem.potion.status;

import static com.lineage.server.model.skill.L1SkillId.EFFECT_STRENGTHENING_HP;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_HPUpdate;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 体力增强卷轴 - 47007
 * 
 * @author jrwz
 */
public class ScrollForStrengtheningHP implements ItemExecutor {

    public static ItemExecutor get() {
        return new ScrollForStrengtheningHP();
    }

    private ScrollForStrengtheningHP() {
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

        final short skillId = EFFECT_STRENGTHENING_HP;
        final short time = 3600;
        final short gfxid = 6993;

        pc.deleteRepeatedSkills(skillId);

        pc.sendPackets(new S_SkillSound(pc.getId(), gfxid));
        pc.broadcastPacket(new S_SkillSound(pc.getId(), gfxid));
        pc.setSkillEffect(skillId, time * 1000);
        pc.addMaxHp(50); // 最高HP+50
        pc.addHpr(4); // 回血量+4
        pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp())); // 更新角色当前血量与最大血量
        if (pc.isInParty()) { // 组队中
            pc.getParty().updateMiniHP(pc); // 更新组队血条
        }

        pc.getInventory().removeItem(item, 1);
    }
}
