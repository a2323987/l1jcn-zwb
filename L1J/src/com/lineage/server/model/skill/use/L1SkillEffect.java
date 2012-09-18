package com.lineage.server.model.skill.use;

import com.lineage.server.model.L1Character;

/**
 * 技能使用效果接口
 * 
 * @author jrwz
 */
public interface L1SkillEffect {

    /**
     * 技能效果执行
     * 
     * @param _user
     *            技能执行者
     * @param cha
     *            目标
     * @param _target
     *            技能攻击目标
     * @param skillId
     *            技能编号
     * @param _getBuffIconDuration
     *            技能状态图示时间
     * @param dmg
     *            技能伤害值
     */
    public abstract int execute(final L1Character _user, final L1Character cha,
            L1Character _target, final int skillId,
            final int _getBuffIconDuration, final int dmg);
}
