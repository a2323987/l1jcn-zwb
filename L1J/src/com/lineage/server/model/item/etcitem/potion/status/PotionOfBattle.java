package com.lineage.server.model.item.etcitem.potion.status;

import static com.lineage.server.model.skill.L1SkillId.EFFECT_POTION_OF_BATTLE;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 战斗药水 - 47006 <br>
 * 效果时间： 3600秒 <br>
 * 狩猎经验值 x1.2 <br>
 * 角色登出时不会减少持续时间 <br>
 * 韩国“Colorful 付费方案”的赠品 <br>
 * 
 * @author jrwz
 */
public class PotionOfBattle implements ItemExecutor {

    public static ItemExecutor get() {
        return new PotionOfBattle();
    }

    private PotionOfBattle() {
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

        final short skillId = EFFECT_POTION_OF_BATTLE;
        final short time = 3600;
        final short gfxid = 7013;

        pc.deleteRepeatedSkills(skillId); // 与神力药水等相冲

        pc.sendPackets(new S_SkillSound(pc.getId(), gfxid));
        pc.broadcastPacket(new S_SkillSound(pc.getId(), gfxid));
        pc.setSkillEffect(skillId, time * 1000);

        pc.getInventory().removeItem(item, 1);
    }
}
