package com.lineage.server.model.skill.stop;

import static com.lineage.server.model.skill.L1SkillId.STATUS_BLUE_POTION;
import static com.lineage.server.model.skill.L1SkillId.STATUS_BRAVE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_BRAVE2;
import static com.lineage.server.model.skill.L1SkillId.STATUS_ELFBRAVE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_HASTE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_THIRD_SPEED;
import static com.lineage.server.model.skill.L1SkillId.STATUS_UNDERWATER_BREATH;
import static com.lineage.server.model.skill.L1SkillId.STATUS_WISDOM_POTION;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Liquor;
import com.lineage.server.serverpackets.S_SkillBrave;
import com.lineage.server.serverpackets.S_SkillHaste;
import com.lineage.server.serverpackets.S_SkillIconBlessOfEva;
import com.lineage.server.serverpackets.S_SkillIconWisdomPotion;

/**
 * 技能停止:药水
 * 
 * @author jrwz
 */
public class SkillStopPotion implements L1SkillStop {

    @Override
    public void stopSkill(final L1Character cha, final int skillId) {

        switch (skillId) {
            case STATUS_HASTE: // 一段加速
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillHaste(pc.getId(), 0, 0));
                    pc.broadcastPacket(new S_SkillHaste(pc.getId(), 0, 0));
                }
                cha.setMoveSpeed(0);
                break;

            case STATUS_BRAVE: // 二段加速
            case STATUS_ELFBRAVE:
            case STATUS_BRAVE2:
                cha.setBraveSpeed(0);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
                    pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
                }
                break;

            case STATUS_THIRD_SPEED: // 三段加速
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Liquor(pc.getId(), 0)); // 人物 * 1.15
                    pc.broadcastPacket(new S_Liquor(pc.getId(), 0)); // 人物 *
                                                                     // 1.15
                }
                break;

            /** 生命之树果实 */
            // case STATUS_RIBRAVE: // 生命之树果实
            // XXX 生命之树果实状态消除方法不明 cha.setBraveSpeed(0);
            // break;

            case STATUS_BLUE_POTION: // 蓝色药水
                // XXX
                break;

            case STATUS_UNDERWATER_BREATH: // 人鱼之鳞与受祝福的伊娃之鳞
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconBlessOfEva(pc.getId(), 0));
                }
                break;

            case STATUS_WISDOM_POTION: // 智慧药水
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    cha.addSp(-2);
                    pc.sendPackets(new S_SkillIconWisdomPotion(0));
                }
                break;
        }
    }

}
