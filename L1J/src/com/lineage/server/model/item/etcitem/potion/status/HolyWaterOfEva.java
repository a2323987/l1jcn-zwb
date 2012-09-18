package com.lineage.server.model.item.etcitem.potion.status;

import static com.lineage.server.model.skill.L1SkillId.STATUS_HOLY_MITHRIL_POWDER;
import static com.lineage.server.model.skill.L1SkillId.STATUS_HOLY_WATER;
import static com.lineage.server.model.skill.L1SkillId.STATUS_HOLY_WATER_OF_EVA;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 伊娃的圣水 - 41354
 * 
 * @author jrwz
 */
public class HolyWaterOfEva implements ItemExecutor {

    public static ItemExecutor get() {
        return new HolyWaterOfEva();
    }

    private HolyWaterOfEva() {
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

        // 圣水或神圣的米索莉粉状态
        if (pc.hasSkillEffect(STATUS_HOLY_WATER)
                || pc.hasSkillEffect(STATUS_HOLY_MITHRIL_POWDER)) {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            return;
        }

        pc.setSkillEffect(STATUS_HOLY_WATER_OF_EVA, 900 * 1000);
        pc.sendPackets(new S_SkillSound(pc.getId(), 190));
        pc.broadcastPacket(new S_SkillSound(pc.getId(), 190));
        pc.sendPackets(new S_ServerMessage(1140)); // 你得到可以攻击受诅咒的巫女莎尔的力量。
        pc.getInventory().removeItem(item, 1);
    }
}
