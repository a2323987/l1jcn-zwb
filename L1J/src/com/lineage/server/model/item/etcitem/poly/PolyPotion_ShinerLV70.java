package com.lineage.server.model.item.etcitem.poly;

import static com.lineage.server.model.skill.L1SkillId.AWAKEN_ANTHARAS;
import static com.lineage.server.model.skill.L1SkillId.AWAKEN_FAFURION;
import static com.lineage.server.model.skill.L1SkillId.AWAKEN_VALAKAS;

import com.lineage.server.model.L1PolyMorph;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 夏纳的变身卷轴(70级) - 49155
 * 
 * @author jrwz
 */
public class PolyPotion_ShinerLV70 implements ItemExecutor {

    public static ItemExecutor get() {
        return new PolyPotion_ShinerLV70();
    }

    private PolyPotion_ShinerLV70() {
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

        // 不能变身的状态
        // 取回觉醒技能ID
        final int awakeSkillId = pc.getAwakeSkillId();
        if ((awakeSkillId == AWAKEN_ANTHARAS)
                || (awakeSkillId == AWAKEN_FAFURION)
                || (awakeSkillId == AWAKEN_VALAKAS)) {
            pc.sendPackets(new S_ServerMessage(1384)); // 目前状态中无法变身。
            return;
        }

        // 变身时间 (秒)与变身编号
        short polyId = 0;
        final short time = 1800;

        switch (pc.get_sex()) {
            case 0:
                if (pc.isCrown()) {
                    polyId = 6882;
                } else if (pc.isKnight()) {
                    polyId = 6884;
                } else if (pc.isElf()) {
                    polyId = 6886;
                } else if (pc.isWizard()) {
                    polyId = 6888;
                } else if (pc.isDarkelf()) {
                    polyId = 6890;
                } else if (pc.isDragonKnight()) {
                    polyId = 7163;
                } else if (pc.isIllusionist()) {
                    polyId = 7165;
                }
                break;

            case 1:
                if (pc.isCrown()) {
                    polyId = 6883;
                } else if (pc.isKnight()) {
                    polyId = 6885;
                } else if (pc.isElf()) {
                    polyId = 6887;
                } else if (pc.isWizard()) {
                    polyId = 6889;
                } else if (pc.isDarkelf()) {
                    polyId = 6891;
                } else if (pc.isDragonKnight()) {
                    polyId = 7164;
                } else if (pc.isIllusionist()) {
                    polyId = 7166;
                }
                break;
        }

        L1PolyMorph.doPoly(pc, polyId, time, L1PolyMorph.MORPH_BY_ITEMMAGIC);

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
