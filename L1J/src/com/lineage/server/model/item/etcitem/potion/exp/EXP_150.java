package com.lineage.server.model.item.etcitem.potion.exp;

import static com.lineage.server.model.skill.L1SkillId.EFFECT_POTION_OF_EXP_150;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 150%薛赫斯的神力药水 - 47000
 * 
 * @author jrwz
 */
public class EXP_150 implements ItemExecutor {

    public static ItemExecutor get() {
        return new EXP_150();
    }

    private EXP_150() {
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

        final short skillId = EFFECT_POTION_OF_EXP_150;
        final short time = 900;
        final short gfxid = 7301;

        pc.deleteRepeatedSkills(skillId); // 与战斗药水等相冲
        pc.sendPackets(new S_ServerMessage(1292)); // 狩猎的经验值将会增加。
        pc.sendPackets(new S_SkillSound(pc.getId(), gfxid));
        pc.broadcastPacket(new S_SkillSound(pc.getId(), gfxid));
        pc.setSkillEffect(skillId, time * 1000);

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
