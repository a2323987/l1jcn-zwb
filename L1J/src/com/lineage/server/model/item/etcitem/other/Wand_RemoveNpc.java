package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;

/**
 * 管理者驱逐魔杖 - 48001 <br>
 * 删除NPC魔杖 - GM专用 <br>
 * 删除画面内指定的一个Npc
 * 
 * @author jrwz
 */
public class Wand_RemoveNpc implements ItemExecutor {

    public static ItemExecutor get() {
        return new Wand_RemoveNpc();
    }

    private static void useNpcRemovalWand(final L1PcInstance pc,
            final int targetId) {
        final L1Object target = L1World.getInstance().findObject(targetId);
        if ((target != null) && (target instanceof L1NpcInstance)) {
            final L1NpcInstance furniture = (L1NpcInstance) target;
            furniture.deleteMe();
        }
    }

    private Wand_RemoveNpc() {
    }

    @Override
    public void execute(final int[] data, final L1PcInstance pc,
            final L1ItemInstance item) {
        if (!pc.isGm()) {
            return;
        }
        final int spellsc_objid = data[0];
        Wand_RemoveNpc.useNpcRemovalWand(pc, spellsc_objid);
    }

}
