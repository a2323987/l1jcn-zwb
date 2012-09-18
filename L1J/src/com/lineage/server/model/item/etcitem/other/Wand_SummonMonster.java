package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.ActionCodes;
import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_AttackPacket;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.L1SpawnUtil;
import com.lineage.server.utils.Random;

/**
 * 松木魔杖 - 40006 <br>
 * 黑暗安特的树枝 - 40412 <br>
 * 受祝福的 松木魔杖 - 140006 <br>
 * 
 * @author jrwz
 */
public class Wand_SummonMonster implements ItemExecutor {

    public static ItemExecutor get() {
        return new Wand_SummonMonster();
    }

    private Wand_SummonMonster() {
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

        // 可使用魔杖的地图
        if (pc.getMap().isUsePainwand()) {
            final S_AttackPacket s_attackPacket = new S_AttackPacket(pc, 0,
                    ActionCodes.ACTION_Wand);
            pc.sendPackets(s_attackPacket);
            pc.broadcastPacket(s_attackPacket);
            final int[] mobArray = { 45008, 45140, 45016, 45021, 45025, // 哥布林・哈柏哥布林・鹿・葛林
                    45033, 45099, 45147, 45123, // 安普・安普长老・欧熊・骷髅弓箭手
                    45130, 45046, 45092, 45138, // 骷髅斧手・小猎犬・侏儒战士・妖魔巡守
                    45098, 45127, 45143, 45149, // 甘地妖魔・罗孚妖魔・都达玛拉妖魔・阿吐巴妖魔
                    45171, 45040, 45155, 45192, // 那鲁加妖魔・熊・穴居人・鼠人
                    45173, 45213, 45079, 45144
            // 莱肯・卡司特・冰原狼人・蜥蜴人
            };
            final int rnd = Random.nextInt(mobArray.length);
            L1SpawnUtil.spawn(pc, mobArray[rnd], 0, 300000);

            final int itemId = item.getItemId();
            if ((itemId == 40006) || (itemId == 140006)) {
                item.setChargeCount(item.getChargeCount() - 1);
                pc.getInventory().updateItem(item,
                        L1PcInventory.COL_CHARGE_COUNT);
                if (item.getChargeCount() <= 0) { // 次数为 0时删除
                    pc.getInventory().removeItem(item, 1);
                }
            } else {
                pc.getInventory().removeItem(item, 1);
            }
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
