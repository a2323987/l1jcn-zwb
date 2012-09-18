package com.lineage.server.model.item.etcitem.other;

import static com.lineage.server.model.skill.L1SkillId.STATUS_FLOATING_EYE;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 肉类 (增加饱食度) <br>
 * 40056 - 肉 <br>
 * 40057 - 漂浮之眼肉 <br>
 * 40059 - 蛋 <br>
 * 40060 - 胡萝卜 <br>
 * 40061 - 柠檬 <br>
 * 40062 - 香蕉 <br>
 * 40063 - 巧克力 <br>
 * 40064 - 苹果 <br>
 * 40065 - 情人礼物(糖果) <br>
 * 40069 - 橘子 <br>
 * 40072 - 烤薄饼 <br>
 * 40073 - 复活蛋 <br>
 * 41252 - 珍奇的乌龟 <br>
 * 41266 - 蕃茄 <br>
 * 41267 - 起士 <br>
 * 41274 - 蚂蚁腿 <br>
 * 41275 - 熊肉 <br>
 * 41276 - 山猪肉 <br>
 * 41296 - 鲷鱼 <br>
 * 41297 - 鲑鱼 <br>
 * 49040 - 鲨鱼卵 <br>
 * 49041 - 鳄鱼肉 <br>
 * 49042 - 龙龟蛋 <br>
 * 49043 - 奇异鹦鹉肉 <br>
 * 49044 - 毒蝎肉 <br>
 * 49045 - 伊莱克顿肉 <br>
 * 49046 - 蜘蛛腿肉 <br>
 * 49047 - 蟹肉 <br>
 * 140061 - 柠檬 <br>
 * 140062 - 香蕉 <br>
 * 140065 - 情人礼物(糖果) <br>
 * 140069 - 橘子 <br>
 * 140072 - 烤薄饼 <br>
 * 
 * @author jrwz
 */
public class Meat implements ItemExecutor {

    public static ItemExecutor get() {
        return new Meat();
    }

    private Meat() {
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
        pc.getInventory().removeItem(item, 1);

        short foodvolume1 = (short) (item.getItem().getFoodVolume() / 10);
        short foodvolume2 = 0;
        if (foodvolume1 <= 0) {
            foodvolume1 = 5;
        }
        if (pc.get_food() >= 225) {
            pc.sendPackets(new S_PacketBox(S_PacketBox.FOOD, (short) pc
                    .get_food()));
        } else {
            foodvolume2 = (short) (pc.get_food() + foodvolume1);
            if (foodvolume2 <= 225) {
                pc.set_food(foodvolume2);
                pc.sendPackets(new S_PacketBox(S_PacketBox.FOOD, (short) pc
                        .get_food()));
            } else {
                pc.set_food((short) 225);
                pc.sendPackets(new S_PacketBox(S_PacketBox.FOOD, (short) pc
                        .get_food()));
                // pc.setCryTime(0);
                pc.startCryOfSurvival();
            }
        }
        if (itemId == 40057) { // 漂浮之眼肉
            pc.setSkillEffect(STATUS_FLOATING_EYE, 0);
        }
        pc.sendPackets(new S_ServerMessage(76, item.getItem()
                .getIdentifiedNameId()));
    }
}
