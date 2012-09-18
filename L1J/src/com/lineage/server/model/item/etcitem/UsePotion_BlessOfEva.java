package com.lineage.server.model.item.etcitem;

import static com.lineage.server.model.skill.L1SkillId.STATUS_UNDERWATER_BREATH;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.UniversalUseItem;
import com.lineage.server.serverpackets.S_SkillIconBlessOfEva;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 药水效果 (可以在水底呼吸)
 * 
 * @author jrwz
 */
public class UsePotion_BlessOfEva implements UniversalUseItem {

    private static UniversalUseItem _instance;

    public static UniversalUseItem get() {
        if (_instance == null) {
            _instance = new UsePotion_BlessOfEva();
        }
        return _instance;
    }

    @Override
    public void useItem(final L1PcInstance pc, final L1ItemInstance item,
            final int itemId, final int effect, int time, final int gfxid) {

        // 持续时间可累加
        if (pc.hasSkillEffect(STATUS_UNDERWATER_BREATH)) {
            final int timeSec = pc
                    .getSkillEffectTimeSec(STATUS_UNDERWATER_BREATH);
            time += timeSec;
            if (time > 7200) {
                time = 7200;
            }
            pc.killSkillEffectTimer(STATUS_UNDERWATER_BREATH);
        }
        pc.sendPackets(new S_SkillIconBlessOfEva(pc.getId(), time)); // 状态图示
        pc.sendPackets(new S_SkillSound(pc.getId(), gfxid)); // 效果动画 (自己看得到)
        pc.broadcastPacket(new S_SkillSound(pc.getId(), gfxid)); // 效果动画
                                                                 // (同画面的其他人看得到)
        pc.setSkillEffect(STATUS_UNDERWATER_BREATH, time * 1000); // 给予时间 (秒)
    }

}
