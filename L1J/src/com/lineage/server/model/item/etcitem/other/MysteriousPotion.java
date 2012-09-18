package com.lineage.server.model.item.etcitem.other;

import com.lineage.Config;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.Random;

/**
 * 一～四阶神秘药水 <br>
 * 一阶神秘药水 - 40926 <br>
 * 二阶神秘药水 - 40927 <br>
 * 三阶神秘药水 - 40928 <br>
 * 四阶神秘药水 - 40929 <br>
 * 
 * @author jrwz
 */
public class MysteriousPotion implements ItemExecutor {

    public static ItemExecutor get() {
        return new MysteriousPotion();
    }

    private MysteriousPotion() {
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
        final int itemobj = data[0];
        final L1ItemInstance l1iteminstance1 = pc.getInventory().getItem(
                itemobj);
        final int earing2Id = l1iteminstance1.getItem().getItemId();

        int potion1 = 0;
        int potion2 = 0;
        if ((earing2Id >= 41173) && (41184 >= earing2Id)) {
            switch (itemId) {
                case 40926:
                    potion1 = 247;
                    potion2 = 249;
                    break;

                case 40927:
                    potion1 = 249;
                    potion2 = 251;
                    break;

                case 40928:
                    potion1 = 251;
                    potion2 = 253;
                    break;

                case 40929:
                    potion1 = 253;
                    potion2 = 255;
                    break;
            }
            if ((earing2Id >= (itemId + potion1))
                    && ((itemId + potion2) >= earing2Id)) {
                if ((Random.nextInt(99) + 1) < Config.CREATE_CHANCE_MYSTERIOUS) {
                    pc.createNewItem(pc, (earing2Id - 12), 1);
                    pc.getInventory().removeItem(l1iteminstance1, 1);
                    pc.getInventory().removeItem(item, 1);
                } else {
                    pc.sendPackets(new S_ServerMessage(160, l1iteminstance1
                            .getName())); // \f1%0%s %2 产生激烈的 %1 光芒，但是没有任何事情发生。
                    pc.getInventory().removeItem(item, 1);
                }
            } else {
                pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            }
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
