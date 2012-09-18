package com.lineage.server.model.item.etcitem.quest;

import com.lineage.server.model.L1Quest;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 火焰之影的契约书 - 41121
 * 
 * @author jrwz
 */
public class Contract_FlameShadow implements ItemExecutor {

    public static ItemExecutor get() {
        return new Contract_FlameShadow();
    }

    private Contract_FlameShadow() {
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

        if ((pc.getQuest().get_step(L1Quest.QUEST_SHADOWS) == L1Quest.QUEST_END)
                || pc.getInventory().checkItem(41122, 1)) {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        } else {
            pc.createNewItem(pc, 41122, 1);
        }
    }
}
