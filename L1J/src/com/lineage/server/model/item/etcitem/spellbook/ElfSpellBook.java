package com.lineage.server.model.item.etcitem.spellbook;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.item.etcitem.UseSpellBook_Elf;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 精灵技能书
 * 
 * @author jrwz
 */
public class ElfSpellBook implements ItemExecutor {

    public static ItemExecutor get() {
        return new ElfSpellBook();
    }

    private ElfSpellBook() {
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

        if (pc.isElf() || pc.isGm()) {
            final int itemId = item.getItemId();
            UseSpellBook_Elf.get().useItem(pc, item, itemId, 0, 0, 0);
            // Factory.getSpellBook().useElfSpellBook(pc, item, itemId);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生。
        }
    }
}
