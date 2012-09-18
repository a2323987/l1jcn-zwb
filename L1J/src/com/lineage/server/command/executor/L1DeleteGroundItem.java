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
package com.lineage.server.command.executor;

import java.util.List;

import com.lineage.server.datatables.FurnitureSpawnTable;
import com.lineage.server.datatables.LetterTable;
import com.lineage.server.datatables.PetTable;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1FurnitureInstance;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * GM指令：删除地上道具
 */
public class L1DeleteGroundItem implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1DeleteGroundItem();
    }

    private L1DeleteGroundItem() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        for (final L1Object l1object : L1World.getInstance().getObject()) {
            if (l1object instanceof L1ItemInstance) {
                final L1ItemInstance l1iteminstance = (L1ItemInstance) l1object;
                if ((l1iteminstance.getX() == 0)
                        && (l1iteminstance.getY() == 0)) { // 地面上のアイテムではなく、谁かの所有物
                    continue;
                }

                final List<L1PcInstance> players = L1World.getInstance()
                        .getVisiblePlayer(l1iteminstance, 0);
                if (0 == players.size()) {
                    final L1Inventory groundInventory = L1World.getInstance()
                            .getInventory(l1iteminstance.getX(),
                                    l1iteminstance.getY(),
                                    l1iteminstance.getMapId());
                    final int itemId = l1iteminstance.getItem().getItemId();
                    if ((itemId == 40314) || (itemId == 40316)) { // ペットのアミュレット
                        PetTable.getInstance()
                                .deletePet(l1iteminstance.getId());
                    } else if ((itemId >= 49016) && (itemId <= 49025)) { // 便笺
                        final LetterTable lettertable = new LetterTable();
                        lettertable.deleteLetter(l1iteminstance.getId());
                    } else if ((itemId >= 41383) && (itemId <= 41400)) { // 家具
                        if (l1object instanceof L1FurnitureInstance) {
                            final L1FurnitureInstance furniture = (L1FurnitureInstance) l1object;
                            if (furniture.getItemObjId() == l1iteminstance
                                    .getId()) { // 既に引き出している家具
                                FurnitureSpawnTable.getInstance()
                                        .deleteFurniture(furniture);
                            }
                        }
                    }
                    groundInventory.deleteItem(l1iteminstance);
                    L1World.getInstance().removeVisibleObject(l1iteminstance);
                    L1World.getInstance().removeObject(l1iteminstance);
                }
            }
        }
        L1World.getInstance().broadcastServerMessage("地上的垃圾被GM清除了。");
    }
}
