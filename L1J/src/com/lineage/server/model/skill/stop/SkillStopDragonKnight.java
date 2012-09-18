package com.lineage.server.model.skill.stop;

import static com.lineage.server.model.skill.L1SkillId.BLOODLUST;
import static com.lineage.server.model.skill.L1SkillId.FREEZING_BREATH;
import static com.lineage.server.model.skill.L1SkillId.GUARD_BRAKE;
import static com.lineage.server.model.skill.L1SkillId.HORROR_OF_DEATH;
import static com.lineage.server.model.skill.L1SkillId.RESIST_FEAR;
import static com.lineage.server.model.skill.L1SkillId.STATUS_FREEZE;
import static com.lineage.server.model.skill.L1SkillId.THUNDER_GRAB;
import static com.lineage.server.model.skill.L1SkillId.THUNDER_GRAB_START;

import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1EffectSpawn;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_Paralysis;
import com.lineage.server.serverpackets.S_Poison;
import com.lineage.server.serverpackets.S_SkillBrave;
import com.lineage.server.templates.L1Skills;

/**
 * 技能停止:龙骑士
 * 
 * @author jrwz
 */
public class SkillStopDragonKnight implements L1SkillStop {

    @Override
    public void stopSkill(final L1Character cha, final int skillId) {

        switch (skillId) {
            case FREEZING_BREATH: // 龙骑士魔法 (寒冰喷吐)
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Poison(pc.getId(), 0));
                    pc.broadcastPacket(new S_Poison(pc.getId(), 0));
                    pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_FREEZE,
                            false));
                } else if ((cha instanceof L1MonsterInstance)
                        || (cha instanceof L1SummonInstance)
                        || (cha instanceof L1PetInstance)) {
                    final L1NpcInstance npc = (L1NpcInstance) cha;
                    npc.broadcastPacket(new S_Poison(npc.getId(), 0));
                    npc.setParalyzed(false);
                }
                break;

            case BLOODLUST: // 血之渴望
                cha.setBraveSpeed(0);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillBrave(pc.getId(), 0, 0));
                    pc.broadcastPacket(new S_SkillBrave(pc.getId(), 0, 0));
                }
                break;

            case RESIST_FEAR: // 恐惧无助
                cha.addNdodge((byte) -5); // 闪避率 + 50%
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    // 更新闪避率显示
                    pc.sendPackets(new S_PacketBox(101, pc.getNdodge()));
                }
                break;

            case THUNDER_GRAB_START: // 夺命之雷(发动)
                final L1Skills _skill = SkillsTable.getInstance().getTemplate(
                        THUNDER_GRAB); // 夺命之雷
                final int _fetterDuration = _skill.getBuffDuration() * 1000;
                cha.setSkillEffect(STATUS_FREEZE, _fetterDuration);
                L1EffectSpawn.getInstance().spawnEffect(81182, _fetterDuration,
                        cha.getX(), cha.getY(), cha.getMapId());
                break;

            case GUARD_BRAKE: // 护卫毁灭
                cha.addAc(-10);
                break;

            case HORROR_OF_DEATH: // 惊悚死神
                cha.addStr(3);
                cha.addInt(3);
                break;
        }
    }

}
