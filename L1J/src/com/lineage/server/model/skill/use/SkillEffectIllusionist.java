package com.lineage.server.model.skill.use;

import static com.lineage.server.model.skill.L1SkillId.BONE_BREAK;
import static com.lineage.server.model.skill.L1SkillId.CONCENTRATION;
import static com.lineage.server.model.skill.L1SkillId.CONFUSION;
import static com.lineage.server.model.skill.L1SkillId.CONFUSION_ING;
import static com.lineage.server.model.skill.L1SkillId.ILLUSION_AVATAR;
import static com.lineage.server.model.skill.L1SkillId.ILLUSION_DIA_GOLEM;
import static com.lineage.server.model.skill.L1SkillId.ILLUSION_LICH;
import static com.lineage.server.model.skill.L1SkillId.ILLUSION_OGRE;
import static com.lineage.server.model.skill.L1SkillId.INSIGHT;
import static com.lineage.server.model.skill.L1SkillId.MIRROR_IMAGE;
import static com.lineage.server.model.skill.L1SkillId.PANIC;
import static com.lineage.server.model.skill.L1SkillId.SMASH;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_SPMR;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.utils.Random;

/**
 * 技能效果:幻术师
 * 
 * @author jrwz
 */
public class SkillEffectIllusionist implements L1SkillEffect {

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
            // 暴击
            case SMASH:
                _target.onAction(_player, SMASH);
                break;

            // 骷髅毁坏
            case BONE_BREAK:
                _target.onAction(_player, BONE_BREAK);
                break;

            // 混乱
            case CONFUSION:
                // 发动判断
                if (_user instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) _user;
                    if (!cha.hasSkillEffect(CONFUSION)) {
                        final int change = Random.nextInt(100) + 1;
                        if (change < (30 + Random.nextInt(11))) { // 30 ~ 40%
                            pc.sendPackets(new S_SkillSound(cha.getId(), 6525));
                            pc.broadcastPacket(new S_SkillSound(cha.getId(),
                                    6525));
                            cha.setSkillEffect(CONFUSION, 2 * 1000); // 发动后再次发动间隔
                                                                     // 2秒
                            cha.setSkillEffect(CONFUSION_ING, 8 * 1000);
                            if (cha instanceof L1PcInstance) {
                                final L1PcInstance targetPc = (L1PcInstance) cha;
                                targetPc.sendPackets(new S_ServerMessage(1339)); // 突然感觉到混乱。
                            }
                        }
                    }
                }
                break;

            // 恐慌
            case PANIC:
                cha.addStr((byte) -1);
                cha.addCon((byte) -1);
                cha.addDex((byte) -1);
                cha.addWis((byte) -1);
                cha.addInt((byte) -1);
                break;

            // 镜像
            case MIRROR_IMAGE:
                if (_user instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) _user;
                    pc.addDodge((byte) 5); // 闪避率 + 50%
                    pc.sendPackets(new S_PacketBox(88, pc.getDodge())); // 更新闪避率显示
                }
                break;

            // 幻觉：欧吉
            case ILLUSION_OGRE:
                cha.addDmgup(4);
                cha.addHitup(4);
                cha.addBowDmgup(4);
                cha.addBowHitup(4);
                break;

            // 幻觉：巫妖
            case ILLUSION_LICH:
                cha.addSp(2);
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SPMR(pc));
                }
                break;

            // 幻觉：钻石高仑
            case ILLUSION_DIA_GOLEM:
                cha.addAc(-20);
                break;

            // 幻觉：化身
            case ILLUSION_AVATAR:
                cha.addDmgup(10);
                cha.addBowDmgup(10);
                break;

            // 洞察
            case INSIGHT:
                cha.addStr((byte) 1);
                cha.addCon((byte) 1);
                cha.addDex((byte) 1);
                cha.addWis((byte) 1);
                cha.addInt((byte) 1);
                break;

            // 专注
            case CONCENTRATION:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.addMpr(2);
                }
                break;
        }
        return dmg;
    }

}
