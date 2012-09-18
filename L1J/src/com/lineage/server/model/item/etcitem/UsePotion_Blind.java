package com.lineage.server.model.item.etcitem;

import static com.lineage.server.model.skill.L1SkillId.CURSE_BLIND;
import static com.lineage.server.model.skill.L1SkillId.DARKNESS;
import static com.lineage.server.model.skill.L1SkillId.STATUS_FLOATING_EYE;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.UniversalUseItem;
import com.lineage.server.serverpackets.S_CurseBlind;

/**
 * 药水效果 (黑色药水)
 * 
 * @author jrwz
 */
public class UsePotion_Blind implements UniversalUseItem {

    private static UniversalUseItem _instance;

    public static UniversalUseItem get() {
        if (_instance == null) {
            _instance = new UsePotion_Blind();
        }
        return _instance;
    }

    @Override
    public void useItem(final L1PcInstance pc, final L1ItemInstance item,
            final int itemId, final int effect, final int time, final int gfxid) {

        // 删除重复的技能效果
        pc.delRepeatSkillEffect(CURSE_BLIND); // 法师魔法 (闇盲咒术)
        pc.delRepeatSkillEffect(DARKNESS); // 法师魔法 (黑闇之影)

        // 漂浮之眼肉
        if (pc.hasSkillEffect(STATUS_FLOATING_EYE)) {
            pc.sendPackets(new S_CurseBlind(2)); // 周边物件可见
        } else {
            pc.sendPackets(new S_CurseBlind(1)); // 自己
        }
        pc.setSkillEffect(CURSE_BLIND, time * 1000); // 给予16秒效果
    }

}
