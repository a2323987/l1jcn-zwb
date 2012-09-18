package com.lineage.server.model.skill.use;

import static com.lineage.server.model.skill.L1SkillId.AWAKEN_ANTHARAS;
import static com.lineage.server.model.skill.L1SkillId.AWAKEN_FAFURION;
import static com.lineage.server.model.skill.L1SkillId.AWAKEN_VALAKAS;
import static com.lineage.server.model.skill.L1SkillId.BLOODLUST;
import static com.lineage.server.model.skill.L1SkillId.FOE_SLAYER;
import static com.lineage.server.model.skill.L1SkillId.GUARD_BRAKE;
import static com.lineage.server.model.skill.L1SkillId.HORROR_OF_DEATH;
import static com.lineage.server.model.skill.L1SkillId.RESIST_FEAR;
import static com.lineage.server.model.skill.L1SkillId.SPECIAL_EFFECT_WEAKNESS_LV1;
import static com.lineage.server.model.skill.L1SkillId.SPECIAL_EFFECT_WEAKNESS_LV2;
import static com.lineage.server.model.skill.L1SkillId.SPECIAL_EFFECT_WEAKNESS_LV3;

import com.lineage.server.model.L1Awake;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_EffectLocation;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_SkillBrave;
import com.lineage.server.serverpackets.S_SkillIconGFX;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 技能效果:龙骑士
 * 
 * @author jrwz
 */
public class SkillEffectDragonKnight implements L1SkillEffect {

    @Override
    public int execute(final L1Character _user, final L1Character cha,
            final L1Character _target, final int skillId,
            final int _getBuffIconDuration, final int dmg) {

        L1PcInstance _player = null;
        if (_user instanceof L1PcInstance) {
            final L1PcInstance _pc = (L1PcInstance) _user;
            _player = _pc;
        }

        switch (skillId) {
            // 屠宰者
            case FOE_SLAYER:
                _player.setFoeSlayer(true);
                for (int i = 3; i > 0; i--) { // 次数
                    _target.onAction(_player);
                }
                _player.setFoeSlayer(false);

                _player.sendPackets(new S_EffectLocation(_target.getX(),
                        _target.getY(), 6509));
                _player.broadcastPacket(new S_EffectLocation(_target.getX(),
                        _target.getY(), 6509));
                _player.sendPackets(new S_SkillSound(_player.getId(), 7020));
                _player.broadcastPacket(new S_SkillSound(_player.getId(), 7020));

                if (_player.hasSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV1)) {
                    _player.killSkillEffectTimer(SPECIAL_EFFECT_WEAKNESS_LV1);
                    _player.sendPackets(new S_SkillIconGFX(75, 0));
                } else if (_player.hasSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV2)) {
                    _player.killSkillEffectTimer(SPECIAL_EFFECT_WEAKNESS_LV2);
                    _player.sendPackets(new S_SkillIconGFX(75, 0));
                } else if (_player.hasSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV3)) {
                    _player.killSkillEffectTimer(SPECIAL_EFFECT_WEAKNESS_LV3);
                    _player.sendPackets(new S_SkillIconGFX(75, 0));
                }
                break;

            // 护卫毁灭
            case GUARD_BRAKE:
                cha.addAc(10); // 一定机率敌人防御力+10 (几率:26% 无视魔防)
                break;
            // 惊悚死神
            case HORROR_OF_DEATH:
                cha.addStr(-3);
                cha.addInt(-3);
                break;

            // 恐惧无助
            case RESIST_FEAR:
                cha.addNdodge((byte) 5); // 闪避率 - 50%
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    // 更新闪避率显示
                    pc.sendPackets(new S_PacketBox(101, pc.getNdodge()));
                }
                break;

            // 血之渴望
            case BLOODLUST:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.setBraveSpeed(6);
                    pc.sendPackets(new S_SkillBrave(pc.getId(), 6,
                            _getBuffIconDuration));
                    pc.broadcastPacket(new S_SkillBrave(pc.getId(), 6, 0));
                }
                break;

            // 觉醒技能
            case AWAKEN_ANTHARAS:
            case AWAKEN_FAFURION:
            case AWAKEN_VALAKAS:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    L1Awake.start(pc, skillId);
                }
                break;
        }
        return dmg;
    }

}
