package com.lineage.server.model.item.etcitem;

import static com.lineage.server.model.skill.L1SkillId.STATUS_WISDOM_POTION;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.UniversalUseItem;
import com.lineage.server.serverpackets.S_SkillIconWisdomPotion;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 药水效果 (增加魔攻)
 * 
 * @author jrwz
 */
public class UsePotion_Wisdom implements UniversalUseItem {

    private static UniversalUseItem _instance;

    public static UniversalUseItem get() {
        if (_instance == null) {
            _instance = new UsePotion_Wisdom();
        }
        return _instance;
    }

    @Override
    public final void useItem(final L1PcInstance pc, final L1ItemInstance item,
            final int itemId, final int effect, final int time, final int gfxid) {

        if (!pc.hasSkillEffect(STATUS_WISDOM_POTION)) {
            pc.addSp(2); // 魔攻 + 2
        } else {
            pc.killSkillEffectTimer(STATUS_WISDOM_POTION);
        }

        final S_SkillIconWisdomPotion icon;
        icon = new S_SkillIconWisdomPotion(time / 4);
        pc.sendPackets(icon); // 状态图示
        final S_SkillSound sound = new S_SkillSound(pc.getId(), gfxid);
        pc.sendPackets(sound); // 效果动画 (自己看得到)
        pc.broadcastPacket(sound); // 效果动画 (同画面的其他人看得到)
        pc.setSkillEffect(STATUS_WISDOM_POTION, time * 1000); // 给予智慧药水时间 (秒)
    }

}
