package com.lineage.server.model.item.etcitem.scroll;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.model.item.etcitem.CiteScrollEnchant;

/**
 * 对武器施法的卷轴 <br>
 * scroll.S_Scroll_Weapon 40077 - 古代人的练金术卷轴 <br>
 * 40087 - 对武器施法的卷轴 <br>
 * 40128 - 对武器施法的幻象卷轴 <br>
 * 40130 - 金侃的卷轴 <br>
 * 40660 - 试炼卷轴 <br>
 * 41429 - 风之武器强化卷轴 <br>
 * 41430 - 地之武器强化卷轴 <br>
 * 41431 - 水之武器强化卷轴 <br>
 * 41432 - 火之武器强化卷轴 <br>
 * 49312 - 象牙塔对武器施法的卷轴 <br>
 * 140087 - 受祝福的 对武器施法的卷轴 <br>
 * 140130 - 受祝福的 金侃的卷轴 <br>
 * 240087 - 受祝福的 对武器施法的卷轴 <br>
 * 
 * @author jrwz
 */
public class S_Scroll_Weapon implements ItemExecutor {

    public static ItemExecutor get() {
        return new S_Scroll_Weapon();
    }

    private S_Scroll_Weapon() {
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

        final int targetID = data[0];
        final L1ItemInstance l1iteminstance1 = pc.getInventory().getItem(
                targetID);
        final int itemId = item.getItemId();

        switch (itemId) {
            case 49312: // 象牙塔对武器施法的卷轴
                CiteScrollEnchant.get().scrollOfEnchantWeaponIvoryTower(pc,
                        item, l1iteminstance1);
                break;

            case 41429: // 风之武器强化卷轴
            case 41430: // 地之武器强化卷轴
            case 41431: // 水之武器强化卷轴
            case 41432: // 火之武器强化卷轴
                CiteScrollEnchant.get().scrollOfEnchantWeaponAttr(pc, item,
                        l1iteminstance1);
                break;

            default: // 其他
                CiteScrollEnchant.get().scrollOfEnchantWeapon(pc, item,
                        l1iteminstance1);
                break;
        }
    }
}
