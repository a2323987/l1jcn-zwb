package com.lineage.server.model.skill.use;

import static com.lineage.server.model.skill.L1SkillId.BRAVE_AURA;
import static com.lineage.server.model.skill.L1SkillId.GLOWING_AURA;
import static com.lineage.server.model.skill.L1SkillId.SHINING_AURA;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SPMR;
import com.lineage.server.serverpackets.S_SkillIconAura;

/**
 * 技能效果:王族
 * 
 * @author jrwz
 */
public class SkillEffectRoyal implements L1SkillEffect {

    @Override
    public int execute(final L1Character _user, final L1Character cha,
            final L1Character _target, final int skillId,
            final int _getBuffIconDuration, final int dmg) {

        switch (skillId) {
            // 激励士气
            case GLOWING_AURA:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addHitup(5);
                    pc.addBowHitup(5);
                    pc.addMr(20);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.sendPackets(new S_SkillIconAura(113,
                            _getBuffIconDuration));
                }
                break;

            // 钢铁士气
            case SHINING_AURA:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addAc(-8);
                    pc.sendPackets(new S_SkillIconAura(114,
                            _getBuffIconDuration));
                }
                break;

            // 冲击士气
            case BRAVE_AURA:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addDmgup(5);
                    pc.sendPackets(new S_SkillIconAura(116,
                            _getBuffIconDuration));
                }
                break;
        }
        return dmg;
    }

}
