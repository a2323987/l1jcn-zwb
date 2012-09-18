package com.lineage.server.model.skill.stop;

import static com.lineage.server.model.skill.L1SkillId.BLIND_HIDING;
import static com.lineage.server.model.skill.L1SkillId.DRESS_DEXTERITY;
import static com.lineage.server.model.skill.L1SkillId.DRESS_MIGHTY;
import static com.lineage.server.model.skill.L1SkillId.MOVING_ACCELERATION;
import static com.lineage.server.model.skill.L1SkillId.SHADOW_ARMOR;
import static com.lineage.server.model.skill.L1SkillId.SHADOW_FANG;
import static com.lineage.server.model.skill.L1SkillId.UNCANNY_DODGE;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Dexup;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_SkillBrave;
import com.lineage.server.serverpackets.S_SkillIconShield;
import com.lineage.server.serverpackets.S_Strup;

/**
 * 技能停止:黑暗精灵
 * 
 * @author jrwz
 */
public class SkillStopDarkElf implements L1SkillStop {

    @Override
    public void stopSkill(final L1Character cha, final int skillId) {

        switch (skillId) {
            case BLIND_HIDING: // 黑暗妖精魔法 (暗隐术)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.delBlindHiding();
                }
                break;

            case SHADOW_ARMOR: // 黑暗妖精魔法 (影之防护)
                cha.addAc(3);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconShield(3, 0));
                }
                break;

            case DRESS_DEXTERITY: // 黑暗妖精魔法 (敏捷提升)
                cha.addDex((byte) -2);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Dexup(pc, 2, 0));
                }
                break;

            case DRESS_MIGHTY: // 黑暗妖精魔法 (力量提升)
                cha.addStr((byte) -2);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Strup(pc, 2, 0));
                }
                break;

            case SHADOW_FANG: // 黑暗妖精魔法 (暗影之牙)
                cha.addDmgup(-5);
                break;

            case MOVING_ACCELERATION:// 行走加速
                cha.setBraveSpeed(0);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
                    pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
                }
                break;

            case UNCANNY_DODGE: // 暗影闪避
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addDodge((byte) -5); // 闪避率 - 50%
                    // 更新闪避率显示
                    pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
                }
                break;
        }
    }

}
