package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.ActionCodes;
import com.lineage.server.datatables.FurnitureSpawnTable;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1FurnitureInstance;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_AttackPacket;

/**
 * 移除家具魔杖 - 41401
 * 
 * @author jrwz
 */
public class Wand_RemoveFurniture implements ItemExecutor {

    public static ItemExecutor get() {
        return new Wand_RemoveFurniture();
    }

    private Wand_RemoveFurniture() {
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

        final int spellsc_objid = data[0];

        this.useFurnitureRemovalWand(pc, spellsc_objid, item);
    }

    // 移除家具
    private void useFurnitureRemovalWand(final L1PcInstance pc,
            final int targetId, final L1ItemInstance item) {
        final S_AttackPacket s_attackPacket = new S_AttackPacket(pc, 0,
                ActionCodes.ACTION_Wand);
        pc.sendPackets(s_attackPacket);
        pc.broadcastPacket(s_attackPacket);
        final int chargeCount = item.getChargeCount();
        if (chargeCount <= 0) {
            return;
        }

        final L1Object target = L1World.getInstance().findObject(targetId);
        if ((target != null) && (target instanceof L1FurnitureInstance)) {
            final L1FurnitureInstance furniture = (L1FurnitureInstance) target;
            furniture.deleteMe();
            FurnitureSpawnTable.getInstance().deleteFurniture(furniture);
            item.setChargeCount(item.getChargeCount() - 1);
            pc.getInventory().updateItem(item, L1PcInventory.COL_CHARGE_COUNT);
        }
    }
}
