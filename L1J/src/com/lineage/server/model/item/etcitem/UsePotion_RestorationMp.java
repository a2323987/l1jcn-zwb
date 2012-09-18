package com.lineage.server.model.item.etcitem;

import static com.lineage.server.model.skill.L1SkillId.STATUS_BLUE_POTION;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.UniversalUseItem;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillIconGFX;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 药水效果 (恢复魔量)
 * 
 * @author jrwz
 */
public class UsePotion_RestorationMp implements UniversalUseItem {

    private static UniversalUseItem _instance;

    public static UniversalUseItem get() {
        if (_instance == null) {
            _instance = new UsePotion_RestorationMp();
        }
        return _instance;
    }

    @Override
    public void useItem(final L1PcInstance pc, final L1ItemInstance item,
            final int itemId, final int effect, final int time, final int gfxid) {

        pc.delAbsoluteBarrier(); // 删除绝对屏障效果
        pc.delRepeatSkillEffect(STATUS_BLUE_POTION); // 删除重复的蓝水效果

        pc.sendPackets(new S_SkillIconGFX(34, time)); // 发送状态图示
        pc.sendPackets(new S_SkillSound(pc.getId(), gfxid)); // 效果动画 (自己看得到)
        pc.broadcastPacket(new S_SkillSound(pc.getId(), gfxid)); // 效果动画
                                                                 // (同画面的其他人看得到)
        pc.sendPackets(new S_ServerMessage(1007)); // 你感觉到魔力恢复速度加快。
        pc.setSkillEffect(STATUS_BLUE_POTION, time * 1000); // 给予蓝水时间 (秒)
    }

}
