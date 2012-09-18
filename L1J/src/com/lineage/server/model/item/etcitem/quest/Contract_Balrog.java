package com.lineage.server.model.item.etcitem.quest;

import com.lineage.server.model.L1Quest;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 炎魔的契约书 - 41130
 * 
 * @author jrwz
 */
public class Contract_Balrog implements ItemExecutor {

    public static ItemExecutor get() {
        return new Contract_Balrog();
    }

    private Contract_Balrog() {
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

        if ((pc.getQuest().get_step(L1Quest.QUEST_DESIRE) == L1Quest.QUEST_END)
                || pc.getInventory().checkItem(41131, 1)) {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        } else {
            pc.createNewItem(pc, 41131, 1);
        }
    }
}
