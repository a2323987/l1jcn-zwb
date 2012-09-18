package com.lineage.server.model.skill.stop;

import static com.lineage.server.model.skill.L1SkillId.SHOCK_STUN;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.serverpackets.S_Paralysis;

/**
 * 技能停止:骑士
 * 
 * @author jrwz
 */
public class SkillStopKnight implements L1SkillStop {

    @Override
    public void stopSkill(final L1Character cha, final int skillId) {

        switch (skillId) {
            case SHOCK_STUN: // 骑士魔法 (冲击之晕)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_STUN, false));
                } else if ((cha instanceof L1MonsterInstance)
                        || (cha instanceof L1SummonInstance)
                        || (cha instanceof L1PetInstance)) {
                    final L1NpcInstance npc = (L1NpcInstance) cha;
                    npc.setParalyzed(false);
                }
                break;
        }
    }

}
