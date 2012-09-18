package com.lineage.server.model.skill.stop;

import static com.lineage.server.model.skill.L1SkillId.BRAVE_AURA;
import static com.lineage.server.model.skill.L1SkillId.GLOWING_AURA;
import static com.lineage.server.model.skill.L1SkillId.SHINING_AURA;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SPMR;
import com.lineage.server.serverpackets.S_SkillIconAura;

/**
 * 技能停止:王族
 * 
 * @author jrwz
 */
public class SkillStopRoyal implements L1SkillStop {

    @Override
    public void stopSkill(final L1Character cha, final int skillId) {

        switch (skillId) {
            case GLOWING_AURA: // 王族魔法 (激励士气)
                cha.addHitup(-5);
                cha.addBowHitup(-5);
                cha.addMr(-20);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SPMR(pc));
                    pc.sendPackets(new S_SkillIconAura(113, 0));
                }
                break;

            case SHINING_AURA: // 王族魔法 (钢铁士气)
                cha.addAc(8);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconAura(114, 0));
                }
                break;

            case BRAVE_AURA: // 王族魔法 (冲击士气)
                cha.addDmgup(-5);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillIconAura(116, 0));
                }
                break;
        }
    }

}
