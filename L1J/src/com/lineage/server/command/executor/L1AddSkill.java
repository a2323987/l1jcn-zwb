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
package com.lineage.server.command.executor;

import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_AddSkill;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.templates.L1Skills;

/**
 * TODO: 翻译 GM指令：增加魔法
 */
public class L1AddSkill implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1AddSkill();
    }

    private L1AddSkill() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            int cnt = 0; // 计数器
            String skill_name = ""; // 技能名称
            int skill_id = 0; // 技能ID

            final int object_id = pc.getId(); // 取得角色的 objectid
            pc.sendPackets(new S_SkillSound(object_id, '\343')); // 魔法习得的效果音效
            pc.broadcastPacket(new S_SkillSound(object_id, '\343'));

            // 王族
            if (pc.isCrown()) {
                pc.sendPackets(new S_AddSkill(255, 255, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 255, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
                for (cnt = 1; cnt <= 16; cnt++) // LV1~2魔法
                {
                    final L1Skills l1skills = SkillsTable.getInstance()
                            .getTemplate(cnt); // 技能情报取得
                    skill_name = l1skills.getName();
                    skill_id = l1skills.getSkillId();
                    SkillsTable.getInstance().spellMastery(object_id, skill_id,
                            skill_name, 0, 0); // 写入DB
                }
                for (cnt = 113; cnt <= 120; cnt++) // 王族魔法
                {
                    final L1Skills l1skills = SkillsTable.getInstance()
                            .getTemplate(cnt); // 技能情报取得
                    skill_name = l1skills.getName();
                    skill_id = l1skills.getSkillId();
                    SkillsTable.getInstance().spellMastery(object_id, skill_id,
                            skill_name, 0, 0); // 写入DB
                }
            }

            // 骑士
            else if (pc.isKnight()) {
                pc.sendPackets(new S_AddSkill(255, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        192, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
                for (cnt = 1; cnt <= 8; cnt++) // LV1魔法
                {
                    final L1Skills l1skills = SkillsTable.getInstance()
                            .getTemplate(cnt); // 技能情报取得
                    skill_name = l1skills.getName();
                    skill_id = l1skills.getSkillId();
                    SkillsTable.getInstance().spellMastery(object_id, skill_id,
                            skill_name, 0, 0); // 写入DB
                }
                for (cnt = 87; cnt <= 91; cnt++) // 骑士魔法
                {
                    final L1Skills l1skills = SkillsTable.getInstance()
                            .getTemplate(cnt); // 技能情报取得
                    skill_name = l1skills.getName();
                    skill_id = l1skills.getSkillId();
                    SkillsTable.getInstance().spellMastery(object_id, skill_id,
                            skill_name, 0, 0); // 写入DB
                }
            }

            // 精灵
            else if (pc.isElf()) {
                pc.sendPackets(new S_AddSkill(255, 255, 127, 255, 255, 255, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 127, 3, 255, 255, 255, 255,
                        0, 0, 0, 0, 0, 0));
                for (cnt = 1; cnt <= 48; cnt++) // LV1~6魔法
                {
                    final L1Skills l1skills = SkillsTable.getInstance()
                            .getTemplate(cnt); // 技能情报取得
                    skill_name = l1skills.getName();
                    skill_id = l1skills.getSkillId();
                    SkillsTable.getInstance().spellMastery(object_id, skill_id,
                            skill_name, 0, 0); // 写入DB
                }
                for (cnt = 129; cnt <= 176; cnt++) // 精灵魔法
                {
                    final L1Skills l1skills = SkillsTable.getInstance()
                            .getTemplate(cnt); // 技能情报取得
                    skill_name = l1skills.getName();
                    skill_id = l1skills.getSkillId();
                    SkillsTable.getInstance().spellMastery(object_id, skill_id,
                            skill_name, 0, 0); // 写入DB
                }
            }

            // 法师
            else if (pc.isWizard()) {
                pc.sendPackets(new S_AddSkill(255, 255, 127, 255, 255, 255,
                        255, 255, 255, 255, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0));
                for (cnt = 1; cnt <= 80; cnt++) // LV1~10魔法
                {
                    final L1Skills l1skills = SkillsTable.getInstance()
                            .getTemplate(cnt); // 技能情报取得
                    skill_name = l1skills.getName();
                    skill_id = l1skills.getSkillId();
                    SkillsTable.getInstance().spellMastery(object_id, skill_id,
                            skill_name, 0, 0); // 写入DB
                }
            } else if (pc.isDarkelf()) {
                pc.sendPackets(new S_AddSkill(255, 255, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 255, 127, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0));
                for (cnt = 1; cnt <= 16; cnt++) // LV1~2魔法
                {
                    final L1Skills l1skills = SkillsTable.getInstance()
                            .getTemplate(cnt); // 技能情报取得
                    skill_name = l1skills.getName();
                    skill_id = l1skills.getSkillId();
                    SkillsTable.getInstance().spellMastery(object_id, skill_id,
                            skill_name, 0, 0); // 写入DB
                }
                for (cnt = 97; cnt <= 111; cnt++) // DE魔法
                {
                    final L1Skills l1skills = SkillsTable.getInstance()
                            .getTemplate(cnt); // 技能情报取得
                    skill_name = l1skills.getName();
                    skill_id = l1skills.getSkillId();
                    SkillsTable.getInstance().spellMastery(object_id, skill_id,
                            skill_name, 0, 0); // 写入DB
                }
            }

            // 龙骑士
            else if (pc.isDragonKnight()) {
                pc.sendPackets(new S_AddSkill(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 240, 255, 7, 0, 0, 0));
                for (cnt = 181; cnt <= 195; cnt++) // 龙骑士秘技
                {
                    final L1Skills l1skills = SkillsTable.getInstance()
                            .getTemplate(cnt); // 技能情报取得
                    skill_name = l1skills.getName();
                    skill_id = l1skills.getSkillId();
                    SkillsTable.getInstance().spellMastery(object_id, skill_id,
                            skill_name, 0, 0); // 写入DB
                }
            }

            // 幻术师
            else if (pc.isIllusionist()) {
                pc.sendPackets(new S_AddSkill(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 255, 255, 15));
                for (cnt = 201; cnt <= 220; cnt++) // 幻术师魔法
                {
                    final L1Skills l1skills = SkillsTable.getInstance()
                            .getTemplate(cnt); // 技能情报取得
                    skill_name = l1skills.getName();
                    skill_id = l1skills.getSkillId();
                    SkillsTable.getInstance().spellMastery(object_id, skill_id,
                            skill_name, 0, 0); // 写入DB
                }
            }
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage(cmdName + " 指令错误。"));
        }
    }
}
