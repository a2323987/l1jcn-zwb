package com.lineage.server.model.item.etcitem.scroll;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1TowerInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_Message_YN;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 复活卷轴 - 40089 受祝福的 复活卷轴 - 140089
 * 
 * @author jrwz
 */
public class ResurrectionScrolls implements ItemExecutor {

    public static ItemExecutor get() {
        return new ResurrectionScrolls();
    }

    private ResurrectionScrolls() {
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
        final int resid = data[0];
        final L1Character resobject = (L1Character) L1World.getInstance()
                .findObject(resid);

        if (resobject != null) {
            if (resobject instanceof L1PcInstance) {
                final L1PcInstance target = (L1PcInstance) resobject;
                if (pc.getId() == target.getId()) {
                    return;
                }
                if (L1World.getInstance().getVisiblePlayer(target, 0).size() > 0) {
                    for (final L1PcInstance visiblePc : L1World.getInstance()
                            .getVisiblePlayer(target, 0)) {
                        if (!visiblePc.isDead()) {
                            // \f1复活失败，因为这个位置已被占据
                            pc.sendPackets(new S_ServerMessage(592));
                            return;
                        }
                    }
                }
                if ((target.getCurrentHp() == 0) && (target.isDead() == true)) {
                    if (pc.getMap().isUseResurrection()) {
                        target.setTempID(pc.getId());
                        if (itemId == 40089) {
                            // 是否要复活？ (Y/N)
                            target.sendPackets(new S_Message_YN(321, ""));
                        } else if (itemId == 140089) {
                            // 是否要复活？ (Y/N)
                            target.sendPackets(new S_Message_YN(322, ""));
                        }
                    } else {
                        return;
                    }
                }
            } else if (resobject instanceof L1NpcInstance) {
                if (!(resobject instanceof L1TowerInstance)) {
                    final L1NpcInstance npc = (L1NpcInstance) resobject;
                    if (npc.getNpcTemplate().isCantResurrect()
                            && !(npc instanceof L1PetInstance)) {
                        pc.getInventory().removeItem(item, 1);
                        return;
                    }
                    if ((npc instanceof L1PetInstance)
                            && (L1World.getInstance().getVisiblePlayer(npc, 0)
                                    .size() > 0)) {
                        for (final L1PcInstance visiblePc : L1World
                                .getInstance().getVisiblePlayer(npc, 0)) {
                            if (!visiblePc.isDead()) {
                                // \f1复活失败，因为这个位置已被占据
                                pc.sendPackets(new S_ServerMessage(592));
                                return;
                            }
                        }
                    }
                    if ((npc.getCurrentHp() == 0) && npc.isDead()) {
                        npc.resurrect(npc.getMaxHp() / 4);
                        npc.setResurrect(true);
                        if ((npc instanceof L1PetInstance)) {
                            final L1PetInstance pet = (L1PetInstance) npc;
                            // 开始饱食度计时
                            pet.startFoodTimer(pet);
                        }
                    }
                }
            }
        }
        pc.getInventory().removeItem(item, 1);
    }
}
