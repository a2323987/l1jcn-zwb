package com.lineage.server.model.item.etcitem;

import static com.lineage.server.model.skill.L1SkillId.BLOODLUST;
import static com.lineage.server.model.skill.L1SkillId.HOLY_WALK;
import static com.lineage.server.model.skill.L1SkillId.MOVING_ACCELERATION;
import static com.lineage.server.model.skill.L1SkillId.STATUS_BRAVE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_BRAVE2;
import static com.lineage.server.model.skill.L1SkillId.STATUS_ELFBRAVE;
import static com.lineage.server.model.skill.L1SkillId.WIND_WALK;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.UniversalUseItem;
import com.lineage.server.serverpackets.S_SkillBrave;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 加速药水效果 (二段:勇敢药水)
 * 
 * @author jrwz
 */
public class UseSpeedPotion_2_Brave implements UniversalUseItem {

    private static UniversalUseItem _instance;

    public static UniversalUseItem get() {
        if (_instance == null) {
            _instance = new UseSpeedPotion_2_Brave();
        }
        return _instance;
    }

    @Override
    public void useItem(final L1PcInstance pc, final L1ItemInstance item,
            final int itemId, final int effect, final int time, final int gfxid) {

        // 删除重复的二段加速效果
        pc.delRepeatSkillEffect(STATUS_BRAVE); // 勇敢药水类 1.33倍
        pc.delRepeatSkillEffect(STATUS_ELFBRAVE); // 精灵饼干 1.15倍
        pc.delRepeatSkillEffect(HOLY_WALK); // 神圣疾走 移速1.33倍
        pc.delRepeatSkillEffect(MOVING_ACCELERATION); // 行走加速 移速1.33倍
        pc.delRepeatSkillEffect(WIND_WALK); // 风之疾走 移速1.33倍
        pc.delRepeatSkillEffect(BLOODLUST); // 血之渴望 攻速1.33倍
        pc.delRepeatSkillEffect(STATUS_BRAVE2); // 超级加速 2.66倍

        // 给予状态 && 效果
        pc.sendPackets(new S_SkillSound(pc.getId(), gfxid)); // 效果动画 (自己看得到)
        pc.broadcastPacket(new S_SkillSound(pc.getId(), gfxid)); // 效果动画
                                                                 // (同画面的其他人看得到)
        pc.sendPackets(new S_SkillBrave(pc.getId(), 1, time)); // 加速效果与时间
                                                               // (自己看得到)
        pc.broadcastPacket(new S_SkillBrave(pc.getId(), 1, 0)); // 加速效果与时间
                                                                // (同画面的其他人看得到)
        pc.setSkillEffect(STATUS_BRAVE, time * 1000); // 给予二段加速时间 (秒)
        pc.setBraveSpeed(1); // 设置勇水速度
    }

}
