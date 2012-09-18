package com.lineage.server.model.item.etcitem.scroll;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 毁灭盔甲的卷轴 - 40075
 * 
 * @author jrwz
 */
public class ScrollOfDestroyArmor implements ItemExecutor {

    public static ItemExecutor get() {
        return new ScrollOfDestroyArmor();
    }

    private ScrollOfDestroyArmor() {
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

        if (l1iteminstance1.getItem().getType2() == 2) {
            int msg = 0;
            switch (l1iteminstance1.getItem().getType()) {
                case 1: // helm
                    msg = 171; // \f1你的钢盔如爆炸般地破碎了。
                    break;
                case 2: // armor
                    msg = 169; // \f1你的盔甲变成尘埃落地。
                    break;
                case 3: // T
                    msg = 170; // \f1你的T恤破碎成线四散。
                    break;
                case 4: // cloak
                    msg = 168; // \f1你的斗蓬破碎化为尘埃。
                    break;
                case 5: // glove
                    msg = 172; // \f1你的手套消失。
                    break;
                case 6: // boots
                    msg = 173; // \f1你的长靴分解。
                    break;
                case 7: // shield
                    msg = 174; // \f1你的盾崩溃分散。
                    break;
                default:
                    msg = 167; // \f1你的皮肤痒。
                    break;
            }
            pc.sendPackets(new S_ServerMessage(msg));
            pc.getInventory().removeItem(l1iteminstance1, 1);
        } else {
            pc.sendPackets(new S_ServerMessage(154)); // \f1这个卷轴散开了。
        }
        pc.getInventory().removeItem(item, 1);
    }
}
