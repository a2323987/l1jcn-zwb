package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.datatables.ItemTable;
import com.lineage.server.datatables.ResolventTable;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.Random;

/**
 * 溶解剂 - 41245
 * 
 * @author jrwz
 */
public class Solvent implements ItemExecutor {

    public static ItemExecutor get() {
        return new Solvent();
    }

    private Solvent() {
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

        final int itemobj = data[0];
        final L1ItemInstance l1iteminstance1 = pc.getInventory().getItem(
                itemobj);

        this.useResolvent(pc, l1iteminstance1, item);
    }

    // 溶解
    private void useResolvent(final L1PcInstance pc, final L1ItemInstance item,
            final L1ItemInstance resolvent) {

        // 空道具
        if ((item == null) || (resolvent == null)) {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            return;
        }

        // 武器・防具
        if ((item.getItem().getType2() == 1)
                || (item.getItem().getType2() == 2)) {

            // 强化过的道具
            if (item.getEnchantLevel() != 0) {
                pc.sendPackets(new S_ServerMessage(1161)); // 无法溶解。
                return;
            }

            // 装备中
            if (item.isEquipped()) {
                pc.sendPackets(new S_ServerMessage(1161)); // 无法溶解。
                return;
            }
        }
        int crystalCount = ResolventTable.getInstance().getCrystalCount(
                item.getItem().getItemId());
        if (crystalCount == 0) {
            pc.sendPackets(new S_ServerMessage(1161)); // 无法溶解。
            return;
        }

        final int rnd = Random.nextInt(100) + 1;
        if ((rnd >= 1) && (rnd <= 50)) {
            crystalCount = 0;
            pc.sendPackets(new S_ServerMessage(158, item.getName())); // \f1%0%s
                                                                      // 消失。
        } else if ((rnd >= 51) && (rnd <= 90)) {
            crystalCount *= 1;
        } else if ((rnd >= 91) && (rnd <= 100)) {
            crystalCount *= 1.5;
            pc.getInventory().storeItem(41246, (int) (crystalCount * 1.5));
        }
        if (crystalCount != 0) {
            final L1ItemInstance crystal = ItemTable.getInstance().createItem(
                    41246);
            crystal.setCount(crystalCount);
            if (pc.getInventory().checkAddItem(crystal, 1) == L1Inventory.OK) {
                pc.getInventory().storeItem(crystal);
                pc.sendPackets(new S_ServerMessage(403, crystal.getLogName())); // 获得%0%o
                                                                                // 。
            } else { // 身上道具满掉落地面（不正防止）
                L1World.getInstance()
                        .getInventory(pc.getX(), pc.getY(), pc.getMapId())
                        .storeItem(crystal);
            }
        }
        pc.getInventory().removeItem(item, 1);
        pc.getInventory().removeItem(resolvent, 1);
    }
}
