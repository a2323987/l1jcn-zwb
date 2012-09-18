package com.lineage.server.model.skill.use;

import static com.lineage.server.model.skill.L1SkillId.BLIND_HIDING;
import static com.lineage.server.model.skill.L1SkillId.DRESS_DEXTERITY;
import static com.lineage.server.model.skill.L1SkillId.DRESS_MIGHTY;
import static com.lineage.server.model.skill.L1SkillId.MOVING_ACCELERATION;
import static com.lineage.server.model.skill.L1SkillId.SHADOW_ARMOR;
import static com.lineage.server.model.skill.L1SkillId.UNCANNY_DODGE;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Dexup;
import com.lineage.server.serverpackets.S_Invis;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.serverpackets.S_SkillBrave;
import com.lineage.server.serverpackets.S_SkillIconShield;
import com.lineage.server.serverpackets.S_Strup;

/**
 * 技能效果:黑暗精灵
 * 
 * @author jrwz
 */
public class SkillEffectDarkElf implements L1SkillEffect {

    @Override
    public int execute(final L1Character _user, final L1Character cha,
            final L1Character _target, final int skillId,
            final int _getBuffIconDuration, final int dmg) {

        switch (skillId) {
            case UNCANNY_DODGE: // 暗影闪避
                if (_user instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) _user;
                    pc.addDodge((byte) 5); // 闪避率 + 50%
                    pc.sendPackets(new S_PacketBox(88, pc.getDodge())); // 更新闪避率显示
                }
                break;

            case SHADOW_ARMOR: // 影之防护
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addAc(-3);
                    pc.sendPackets(new S_SkillIconShield(3,
                            _getBuffIconDuration));
                }
                break;

            case DRESS_MIGHTY: // 力量提升
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addStr((byte) 2);
                    pc.sendPackets(new S_Strup(pc, 2, _getBuffIconDuration));
                }
                break;

            case DRESS_DEXTERITY: // 敏捷提升
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addDex((byte) 2);
                    pc.sendPackets(new S_Dexup(pc, 2, _getBuffIconDuration));
                }
                break;

            case BLIND_HIDING: // 暗隐术
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Invis(pc.getId(), 1));
                    pc.broadcastPacketForFindInvis(new S_RemoveObject(pc),
                            false);
                }
                break;

            case MOVING_ACCELERATION: // 行走加速
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.setBraveSpeed(4);
                    pc.sendPackets(new S_SkillBrave(pc.getId(), 4,
                            _getBuffIconDuration));
                    pc.broadcastPacket(new S_SkillBrave(pc.getId(), 4, 0));
                }
                break;
        }
        return dmg;
    }

}
