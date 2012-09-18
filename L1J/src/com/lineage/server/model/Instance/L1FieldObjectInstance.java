/**
 *                            License
 * THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS  
 * CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
 * THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW.  
 * ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE OR  
 * COPYRIGHT LAW IS PROHIBITED.
 * 
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND  
 * AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE  
 * MAY BE CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package com.lineage.server.model.Instance;

import static com.lineage.server.model.skill.L1SkillId.CANCELLATION;

import com.lineage.server.datatables.ItemTable;
import com.lineage.server.model.L1HauntedHouse;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.L1World;
import com.lineage.server.model.skill.L1SkillUse;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Npc;

/**
 * 景观控制项
 */
public class L1FieldObjectInstance extends L1NpcInstance {

    private static final long serialVersionUID = 1L;

    public L1FieldObjectInstance(final L1Npc template) {
        super(template);
    }

    @Override
    public void deleteMe() {
        this._destroyed = true;
        if (this.getInventory() != null) {
            this.getInventory().clearItems();
        }
        L1World.getInstance().removeVisibleObject(this);
        L1World.getInstance().removeObject(this);
        for (final L1PcInstance pc : L1World.getInstance().getRecognizePlayer(
                this)) {
            pc.removeKnownObject(this);
            pc.sendPackets(new S_RemoveObject(this));
        }
        this.removeAllKnownObjects();
    }

    @Override
    public void onAction(final L1PcInstance pc) {
        if (this.getNpcTemplate().get_npcId() == 81171) { // おばけ屋敷のゴールの炎
            if (L1HauntedHouse.getInstance().getHauntedHouseStatus() == L1HauntedHouse.STATUS_PLAYING) {
                final int winnersCount = L1HauntedHouse.getInstance()
                        .getWinnersCount();
                final int goalCount = L1HauntedHouse.getInstance()
                        .getGoalCount();
                if (winnersCount == goalCount + 1) {
                    final L1ItemInstance item = ItemTable.getInstance()
                            .createItem(49280); // 勇者的南瓜袋子(铜)
                    final int count = 1;
                    if (item != null) {
                        if (pc.getInventory().checkAddItem(item, count) == L1Inventory.OK) {
                            item.setCount(count);
                            pc.getInventory().storeItem(item);
                            pc.sendPackets(new S_ServerMessage(403, item
                                    .getLogName())); // 获得%0%o 。
                        }
                    }
                    L1HauntedHouse.getInstance().endHauntedHouse();
                } else if (winnersCount > goalCount + 1) {
                    L1HauntedHouse.getInstance().setGoalCount(goalCount + 1);
                    L1HauntedHouse.getInstance().removeMember(pc);
                    L1ItemInstance item = null;
                    if (winnersCount == 3) {
                        if (goalCount == 1) {
                            item = ItemTable.getInstance().createItem(49278); // 勇者的南瓜袋子(金)
                        } else if (goalCount == 2) {
                            item = ItemTable.getInstance().createItem(49279); // 勇者的南瓜袋子(银)
                        }
                    } else if (winnersCount == 2) {
                        item = ItemTable.getInstance().createItem(49279); // 勇者的南瓜袋子(银)
                    }
                    final int count = 1;
                    if (item != null) {
                        if (pc.getInventory().checkAddItem(item, count) == L1Inventory.OK) {
                            item.setCount(count);
                            pc.getInventory().storeItem(item);
                            pc.sendPackets(new S_ServerMessage(403, item
                                    .getLogName())); // 获得%0%o 。
                        }
                    }
                    final L1SkillUse l1skilluse = new L1SkillUse();
                    l1skilluse.handleCommands(pc, CANCELLATION, pc.getId(),
                            pc.getX(), pc.getY(), null, 0,
                            L1SkillUse.TYPE_LOGIN);
                    L1Teleport.teleport(pc, 32624, 32813, (short) 4, 5, true);
                }
            }
        }
    }
}
