package com.lineage.server.model.skill.stop;

import com.lineage.server.model.L1Character;

/**
 * 技能停止动作接口
 * 
 * @author jrwz
 */
public interface L1SkillStop {

    public abstract void stopSkill(final L1Character cha, final int skillId);
}
