package com.lineage.server.model.item.etcitem.scroll;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.skill.L1SkillUse;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 40862 魔法卷轴 (光箭) <br>
 * 40864 魔法卷轴 (冰箭) <br>
 * 40865 魔法卷轴 (风刃) <br>
 * 40869 魔法卷轴 (毒咒) <br>
 * 40873 魔法卷轴 (火箭) <br>
 * 40874 魔法卷轴 (地狱之牙) <br>
 * 40875 魔法卷轴 (极光雷电) <br>
 * 40876 魔法卷轴 (起死回生术) <br>
 * 40878 魔法卷轴 (闇盲咒术) <br>
 * 40880 魔法卷轴 (寒冰气息) <br>
 * 40881 魔法卷轴 (能量感测) <br>
 * 40883 魔法卷轴 (燃烧的火球) <br>
 * 40885 魔法卷轴 (坏物术) <br>
 * 40887 魔法卷轴 (缓速术) <br>
 * 40888 魔法卷轴 (岩牢) <br>
 * 40891 魔法卷轴 (木乃伊的诅咒) <br>
 * 40892 魔法卷轴 (极道落雷) <br>
 * 40894 魔法卷轴 (迷魅术) <br>
 * 40896 魔法卷轴 (冰锥) <br>
 * 40898 魔法卷轴 (黑闇之影) <br>
 * 
 * @author jrwz
 */
public class MagicScroll_Long implements ItemExecutor {

    public static ItemExecutor get() {
        return new MagicScroll_Long();
    }

    private MagicScroll_Long() {
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

        // 目标为自己或空
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
