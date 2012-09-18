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
 * 夏纳的变身卷轴(60级) - 49153
 * 
 * @author jrwz
 */
public class PolyPotion_ShinerLV60 implements ItemExecutor {

    public static ItemExecutor get() {
        return new PolyPotion_ShinerLV60();
    }

    private PolyPotion_ShinerLV60() {
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
                    polyId = 6862;
                } else if (pc.isKnight()) {
                    polyId = 6864;
                } else if (pc.isElf()) {
                    polyId = 6866;
                } else if (pc.isWizard()) {
                    polyId = 6868;
                } else if (pc.isDarkelf()) {
                    polyId = 6870;
                } else if (pc.isDragonKnight()) {
                    polyId = 7155;
                } else if (pc.isIllusionist()) {
                    polyId = 7157;
                }
                break;

            case 1:
                if (pc.isCrown()) {
                    polyId = 6863;
                } else if (pc.isKnight()) {
                    polyId = 6865;
                } else if (pc.isElf()) {
                    polyId = 6867;
                } else if (pc.isWizard()) {
                    polyId = 6869;
                } else if (pc.isDarkelf()) {
                    polyId = 6871;
                } else if (pc.isDragonKnight()) {
                    polyId = 7156;
                } else if (pc.isIllusionist()) {
                    polyId = 7158;
                }
                break;
        }

        L1PolyMorph.doPoly(pc, polyId, time, L1PolyMorph.MORPH_BY_ITEMMAGIC);

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
