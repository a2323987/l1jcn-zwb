package com.lineage.server.model.item.etcitem.spellbook;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.item.etcitem.UseSpellBook;

/**
 * 法师技能书 (LV1~LV10)
 * 
 * @author jrwz
 */
public class WizardSpellBook implements ItemExecutor {

    public static ItemExecutor get() {
        return new WizardSpellBook();
    }

    private WizardSpellBook() {
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

        final int itemId = item.getItemId();
        UseSpellBook.get().useItem(pc, item, itemId, 0, 0, 0);
        // Factory.getSpellBook().useSpellBook(pc, item, itemId);
    }
}
