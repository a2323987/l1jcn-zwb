package com.lineage.server.model.skill.stop;

import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_OwnCharStatus;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Skills;

/**
 * 发送技能停止信息
 * 
 * @author jrwz
 */
public class SendStopMessage implements L1SkillStop {

    /**
     * 发送技能停止信息
     * 
     * @param charaPc
     *            角色
     * @param skillid
     *            技能ID
     */
    private static void executor(final L1PcInstance charaPc, final int skillid) {
        final L1Skills l1skills = SkillsTable.getInstance()
                .getTemplate(skillid);
        // 无该技能、空角色
        if ((l1skills == null) || (charaPc == null)) {
            return;
        }

        final int msgID = l1skills.getSysmsgIdStop(); // 取回技能停止时要发送的信息
        if (msgID > 0) {
            charaPc.sendPackets(new S_ServerMessage(msgID));
        }
    }

    @Override
    public void stopSkill(final L1Character cha, final int skillId) {

        if (cha instanceof L1PcInstance) {
            final L1PcInstance pc = (L1PcInstance) cha;
            executor(pc, skillId);
            pc.sendPackets(new S_OwnCharStatus(pc));
        }
    }
}
