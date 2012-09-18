package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.utils.Random;

/**
 * 六段式魔法骰子 - 40328
 * 
 * @author jrwz
 */
public class MagicDice_LV6 implements ItemExecutor {

    public static ItemExecutor get() {
        return new MagicDice_LV6();
    }

    private MagicDice_LV6() {
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

        int A = 0;
        if (pc.getInventory().consumeItem(40318, 1)) {
            final int SK = Random.nextInt(5) + 1;
            {

                if ((SK >= 1) && (SK <= 6)) {

                    A = SK;
                    pc.sendPackets(new S_SystemMessage("你骰出的点数是(" + (A) + ")"));
                    pc.sendPackets(new S_SkillSound(pc.getId(), 3203 + A)); // 骰子效果显示
                    pc.broadcastPacket(new S_SkillSound(pc.getId(), 3203 + A));
                }
            }
        } else {
            // \f1没有任何事情发生。
            pc.sendPackets(new S_ServerMessage(79));
        }
    }
}
