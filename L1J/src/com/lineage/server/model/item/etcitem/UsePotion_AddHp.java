package com.lineage.server.model.item.etcitem;

import static com.lineage.server.model.skill.L1SkillId.POLLUTE_WATER;

import java.util.Random;

import com.lineage.server.PacketCreate;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.UniversalUseItem;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.serverpackets.ServerBasePacket;

/**
 * 药水效果 (治愈类)
 * 
 * @author jrwz
 */
public class UsePotion_AddHp implements UniversalUseItem {

    private static UniversalUseItem _instance;

    public static UniversalUseItem get() {
        if (_instance == null) {
            _instance = new UsePotion_AddHp();
        }
        return _instance;
    }

    @Override
    public final void useItem(final L1PcInstance pc, final L1ItemInstance item,
            final int itemId, int effect, final int time, final int gfxid) {

        pc.delAbsoluteBarrier(); // 删除绝对屏障效果

        ServerBasePacket msg;
        msg = new S_SkillSound(pc.getId(), gfxid);
        pc.sendPackets(msg);
        pc.broadcastPacket(msg);
        // pc.sendPackets(new S_ServerMessage(77)); // \f1你觉得舒服多了。
        msg = PacketCreate.get().getPacket("你觉得舒服多了");
        pc.sendPackets(msg);
        effect *= ((new Random()).nextGaussian() / 5.0D) + 1.0D; // 随机加血量

        // 污浊之水 - 效果减半
        if (pc.hasSkillEffect(POLLUTE_WATER)) {
            effect /= 2;
        }
        pc.setCurrentHp(pc.getCurrentHp() + effect); // 为角色增加HP
    }

}
