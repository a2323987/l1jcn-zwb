package com.lineage.server.model.item.etcitem.other;

import static com.lineage.server.model.skill.L1SkillId.DECAY_POTION;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_AHTHARTS;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 地龙之魔眼 - 47017
 * 
 * @author jrwz
 */
public class DragonEye_Earth implements ItemExecutor {

    public static ItemExecutor get() {
        return new DragonEye_Earth();
    }

    private DragonEye_Earth() {
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

        if (pc.hasSkillEffect(DECAY_POTION)) {
            pc.sendPackets(new S_ServerMessage(698)); // 喉咙灼热，无法喝东西。
            return;
        }

        final int skillId = EFFECT_MAGIC_EYE_OF_AHTHARTS;

        pc.deleteRepeatedSkills(skillId);

        final int time = 600;
        final int gfxid = 7671;

        if (!pc.hasSkillEffect(skillId)) {
            pc.setSkillEffect(skillId, time * 1000);
            pc.addRegistStone(3); // 石化耐性 +3
            pc.addDodge((byte) 1); // 闪避率 + 10%
            pc.sendPackets(new S_PacketBox(88, pc.getDodge())); // 更新闪避率显示
            pc.sendPackets(new S_SkillSound(pc.getId(), gfxid));
            pc.broadcastPacket(new S_SkillSound(pc.getId(), gfxid));
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生。
        }
    }
}
