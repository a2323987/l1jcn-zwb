package com.lineage.server.model.item.etcitem;

import static com.lineage.server.model.skill.L1SkillId.STATUS_THIRD_SPEED;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.UniversalUseItem;
import com.lineage.server.serverpackets.S_Liquor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 加速药水效果 (三段) (巧克力蛋糕)
 * 
 * @author jrwz
 */
public class UseSpeedPotion_3 implements UniversalUseItem {

    private static UniversalUseItem _instance;

    public static UniversalUseItem get() {
        if (_instance == null) {
            _instance = new UseSpeedPotion_3();
        }
        return _instance;
    }

    @Override
    public void useItem(final L1PcInstance pc, final L1ItemInstance item,
            final int itemId, final int effect, final int time, final int gfxid) {

        pc.delRepeatSkillEffect(STATUS_THIRD_SPEED); // 删除重复的三段加速效果

        pc.sendPackets(new S_SkillSound(pc.getId(), gfxid)); // 效果动画 (自己看得到)
        pc.broadcastPacket(new S_SkillSound(pc.getId(), gfxid)); // 效果动画
                                                                 // (同画面的其他人看得到)
        pc.sendPackets(new S_Liquor(pc.getId(), 8)); // 人物 * 1.15
        pc.broadcastPacket(new S_Liquor(pc.getId(), 8)); // 人物 * 1.15
        pc.sendPackets(new S_ServerMessage(1065)); // 将发生神秘的奇迹力量。
        pc.setSkillEffect(STATUS_THIRD_SPEED, time * 1000); // 给予三段加速时间 (秒)
    }

}
