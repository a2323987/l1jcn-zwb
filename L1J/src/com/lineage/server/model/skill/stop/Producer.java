package com.lineage.server.model.skill.stop;

import java.util.ArrayList;
import java.util.List;

import com.lineage.server.model.skill.use.L1SkillEffect;
import com.lineage.server.model.skill.use.SkillEffectDarkElf;
import com.lineage.server.model.skill.use.SkillEffectDragonKnight;
import com.lineage.server.model.skill.use.SkillEffectElf;
import com.lineage.server.model.skill.use.SkillEffectIllusionist;
import com.lineage.server.model.skill.use.SkillEffectKnight;
import com.lineage.server.model.skill.use.SkillEffectNpc;
import com.lineage.server.model.skill.use.SkillEffectRoyal;
import com.lineage.server.model.skill.use.SkillEffectWizard;

public class Producer {
    public static List<L1SkillStop> produceRequests() {
        final List<L1SkillStop> queue = new ArrayList<L1SkillStop>(20);
        queue.add(new SkillStopRoyal()); // 王族
        queue.add(new SkillStopKnight()); // 骑士
        queue.add(new SkillStopElf()); // 精灵
        queue.add(new SkillStopWizard()); // 法师
        queue.add(new SkillStopDarkElf()); // 黑暗精灵
        queue.add(new SkillStopDragonKnight()); // 龙骑士
        queue.add(new SkillStopIllusionist()); // 幻术师
        queue.add(new SkillStopOther()); // 其他
        queue.add(new SkillStopCooking()); // 料理
        queue.add(new SkillStopArmor()); // 装备
        queue.add(new SkillStopPotion()); // 药水
        queue.add(new SendStopMessage()); // 发送技能停止信息
        return queue;
    }

    /**
     * 技能使用效果
     */
    public static List<L1SkillEffect> useRequests() {
        final List<L1SkillEffect> queue = new ArrayList<L1SkillEffect>(10);
        queue.add(new SkillEffectRoyal()); // 王族
        queue.add(new SkillEffectKnight()); // 骑士
        queue.add(new SkillEffectElf()); // 精灵
        queue.add(new SkillEffectWizard()); // 法师
        queue.add(new SkillEffectDarkElf()); // 黑暗精灵
        queue.add(new SkillEffectDragonKnight()); // 龙骑士
        queue.add(new SkillEffectIllusionist()); // 幻术师
        queue.add(new SkillEffectNpc()); // 怪物
        return queue;
    }
}
