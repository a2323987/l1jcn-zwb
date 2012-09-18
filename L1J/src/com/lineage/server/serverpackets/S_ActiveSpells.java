/**
 *                            License
 * THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS  
 * CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
 * THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW.  
 * ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE OR  
 * COPYRIGHT LAW IS PROHIBITED.
 * 
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND  
 * AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE  
 * MAY BE CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package com.lineage.server.serverpackets;

import static com.lineage.server.model.skill.L1SkillId.COOKING_WONDER_DRUG;
import static com.lineage.server.model.skill.L1SkillId.DRESS_EVASION;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLESS_OF_CRAY;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLESS_OF_MAZU;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLESS_OF_SAELL;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_AHTHARTS;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_POTION_OF_BATTLE;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_POTION_OF_EXP_150;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_STRENGTHENING_HP;
import static com.lineage.server.model.skill.L1SkillId.RESIST_FEAR;
import static com.lineage.server.model.skill.L1SkillId.STATUS_RIBRAVE;

import com.lineage.server.Opcodes;
import com.lineage.server.datatables.CharBuffTable;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.utils.Random;

/**
 * 登入时给于角色状态剩余时间
 */
public class S_ActiveSpells extends ServerBasePacket {

    private byte[] _byte = null;

    public S_ActiveSpells(final L1PcInstance pc) {
        final byte[] randBox = new byte[2];
        randBox[0] = Random.nextByte();
        randBox[1] = Random.nextByte();

        // 取得技能剩余时间
        CharBuffTable.buffRemainingTime(pc);

        this.writeC(Opcodes.S_OPCODE_ACTIVESPELLS);
        this.writeC(0x14);

        for (final int i : this.activeSpells(pc)) {
            if (i != 72) {
                this.writeC(i);
            } else {
                this.writeD((int) (System.currentTimeMillis() / 1000));
            }
        }
        this.writeByte(randBox);
    }

    // 登入时给于角色状态剩余时间
    private int[] activeSpells(final L1PcInstance pc) {
        final int[] data = new int[104];
        // 生命之树果实
        if (pc.hasSkillEffect(STATUS_RIBRAVE)) {
            data[61] = pc.getSkillEffectTimeSec(STATUS_RIBRAVE) / 4;
        }
        // 回避提升
        if (pc.hasSkillEffect(DRESS_EVASION)) {
            data[17] = pc.getSkillEffectTimeSec(DRESS_EVASION) / 4;
        }
        // 恐惧无助
        if (pc.hasSkillEffect(RESIST_FEAR)) {
            data[57] = pc.getSkillEffectTimeSec(RESIST_FEAR) / 4;
        }
        // 象牙塔妙药
        if (pc.hasSkillEffect(COOKING_WONDER_DRUG)) {
            data[42] = pc.getSkillEffectTimeSec(COOKING_WONDER_DRUG) / 4;
            if (data[42] != 0) {
                data[43] = 54; // 因为妙药，身心都很轻松。提升体力回复量和魔力回复量。
            }
        }
        // 战斗药水
        if (pc.hasSkillEffect(EFFECT_POTION_OF_BATTLE)) {
            data[45] = pc.getSkillEffectTimeSec(EFFECT_POTION_OF_BATTLE) / 16;
            if (data[45] != 0) {
                data[62] = 20; // 经验值加成20%。
            }
        }
        // 150% ~ 250% 神力药水
        for (int i = 0; i < 5; i++) {
            if (pc.hasSkillEffect(EFFECT_POTION_OF_EXP_150 + i)) {
                data[45] = pc.getSkillEffectTimeSec(EFFECT_POTION_OF_EXP_150
                        + i) / 16;
                if (data[45] != 0) {
                    data[62] = 50; // 狩猎经验值将会增加。
                }
            }
        }
        // 妈祖的祝福
        if (pc.hasSkillEffect(EFFECT_BLESS_OF_MAZU)) {
            data[48] = pc.getSkillEffectTimeSec(EFFECT_BLESS_OF_MAZU) / 16;
            if (data[48] != 0) {
                data[49] = 44; // 感受到妈祖的祝福。
            }
        }
        // 体力增强卷轴、魔力增强卷轴、强化战斗卷轴
        for (int i = 0; i < 3; i++) {
            if (pc.hasSkillEffect(EFFECT_STRENGTHENING_HP + i)) {
                data[46] = pc
                        .getSkillEffectTimeSec(EFFECT_STRENGTHENING_HP + i) / 16;
                if (data[46] != 0) {
                    data[47] = i; // 体力上限+50，体力回复+4。
                }
            }
        }
        // 附魔石
        if (pc.getMagicStoneLevel() != 0) {
            final int skillId = pc.getMagicStoneLevel() + 3929; // skillId =
                                                                // 4013 ~ 4048
            data[102] = pc.getSkillEffectTimeSec(skillId) / 32;
            if (data[102] != 0) {
                data[103] = pc.getMagicStoneLevel();
            }
        }
        // 龙之魔眼
        for (int i = 0; i < 7; i++) {
            if (pc.hasSkillEffect(EFFECT_MAGIC_EYE_OF_AHTHARTS + i)) {
                data[78] = pc
                        .getSkillEffectTimeSec(EFFECT_MAGIC_EYE_OF_AHTHARTS + i) / 32;
                if (data[78] != 0) {
                    data[79] = 46 + i;
                }
            }
        }
        // 卡瑞、莎尔的祝福
        if (pc.hasSkillEffect(EFFECT_BLESS_OF_CRAY)) {
            data[76] = pc.getSkillEffectTimeSec(EFFECT_BLESS_OF_CRAY) / 32;
            if (data[76] != 0) {
                data[77] = 45;
            }
        } else if (pc.hasSkillEffect(EFFECT_BLESS_OF_SAELL)) {
            data[76] = pc.getSkillEffectTimeSec(EFFECT_BLESS_OF_SAELL) / 32;
            if (data[76] != 0) {
                data[77] = 60;
            }
        }

        return data;
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this._bao.toByteArray();
        }

        return this._byte;
    }
}
