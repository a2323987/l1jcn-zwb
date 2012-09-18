package com.lineage.server.model.item.etcitem;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.UniversalUseItem;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 药水效果 (增加魔量)
 * 
 * @author jrwz
 */
public class UsePotion_AddMp implements UniversalUseItem {

    private static UniversalUseItem _instance;

    public static UniversalUseItem get() {
        if (_instance == null) {
            _instance = new UsePotion_AddMp();
        }
        return _instance;
    }

    @Override
    public void useItem(final L1PcInstance pc, final L1ItemInstance item,
            final int itemId, int effect, final int time, final int gfxid) {

        pc.delAbsoluteBarrier(); // 删除绝对屏障效果

        pc.sendPackets(new S_SkillSound(pc.getId(), gfxid)); // 效果动画 (自己看得到)
        pc.broadcastPacket(new S_SkillSound(pc.getId(), gfxid)); // 效果动画
                                                                 // (同画面的其他人看得到)
        pc.sendPackets(new S_ServerMessage(338, "$1084")); // 你的 魔力 渐渐恢复。
        effect *= ((new java.util.Random()).nextGaussian() / 5.0D) + 1.0D; // 随机加魔量
        pc.setCurrentMp(pc.getCurrentMp() + effect); // 为角色增加MP
    }

}
