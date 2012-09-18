package com.lineage.server.model.item.etcitem.scroll;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.skill.L1SkillUse;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 魔法卷轴 (寒冷战栗) - 40868 <br>
 * 魔法卷轴 (吸血鬼之吻) - 40886 <br>
 * 魔法卷轴 (魔力夺取) - 40897 <br>
 * 
 * @author jrwz
 */
public class MagicScroll_Short implements ItemExecutor {

    public static ItemExecutor get() {
        return new MagicScroll_Short();
    }

    private MagicScroll_Short() {
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

        // 隐身状态不可用
        if (pc.isInvisble() || pc.isInvisDelay()) {
            pc.sendPackets(new S_ServerMessage(281)); // \f1施咒取消。
            return;
        }

        final int targetID = data[0];
        final int spellsc_x = data[1];
        final int spellsc_y = data[2];

        // 目标为自己
        if ((targetID == pc.getId()) || (targetID == 0)) {
            pc.sendPackets(new S_ServerMessage(281)); // \f1施咒取消。
            return;
        }

        final int useCount = 1;
        if (pc.getInventory().removeItem(item, useCount) >= useCount) {

            final int itemId = item.getItemId();
            final int skillid = itemId - 40858;

            final L1SkillUse l1skilluse = new L1SkillUse();
            l1skilluse.handleCommands(pc, skillid, targetID, spellsc_x,
                    spellsc_y, null, 0, L1SkillUse.TYPE_SPELLSC);
        }
    }
}
