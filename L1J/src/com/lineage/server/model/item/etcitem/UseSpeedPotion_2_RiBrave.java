package com.lineage.server.model.item.etcitem;

import static com.lineage.server.model.skill.L1SkillId.STATUS_RIBRAVE;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.UniversalUseItem;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 加速药水效果 (二段:生命之树果实)
 * 
 * @author jrwz
 */
public class UseSpeedPotion_2_RiBrave implements UniversalUseItem {

    private static UniversalUseItem _instance;

    public static UniversalUseItem get() {
        if (_instance == null) {
            _instance = new UseSpeedPotion_2_RiBrave();
        }
        return _instance;
    }

    @Override
    public void useItem(final L1PcInstance pc, final L1ItemInstance item,
            final int itemId, final int effect, final int time, final int gfxid) {

        // 删除状态不明
        pc.setSkillEffect(STATUS_RIBRAVE, time * 1000); // 给予二段加速时间 (秒)
        pc.sendPackets(new S_SkillSound(pc.getId(), gfxid)); // 效果动画 (自己看得到)
        pc.broadcastPacket(new S_SkillSound(pc.getId(), gfxid)); // 效果动画
                                                                 // (同画面的其他人看得到)
    }

}
