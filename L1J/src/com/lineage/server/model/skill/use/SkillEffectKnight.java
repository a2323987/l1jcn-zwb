package com.lineage.server.model.skill.use;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 技能效果:骑士
 * 
 * @author jrwz
 */
public class SkillEffectKnight implements L1SkillEffect {

    @Override
    public int execute(final L1Character _user, final L1Character cha,
            final L1Character _target, final int skillId,
            final int _getBuffIconDuration, final int dmg) {

        @SuppressWarnings("unused")
        L1PcInstance _player = null;
        if (_user instanceof L1PcInstance) {
            final L1PcInstance _pc = (L1PcInstance) _user;
            _player = _pc;
        }

        switch (skillId) {

        }
        return dmg;
    }

}
