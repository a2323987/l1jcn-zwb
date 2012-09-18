package com.lineage.server.model.skill.use;

import static com.lineage.server.model.skill.L1SkillId.STATUS_FREEZE;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ChatPacket;
import com.lineage.server.serverpackets.S_NpcChatPacket;
import com.lineage.server.serverpackets.S_Paralysis;

/**
 * 技能效果:怪物
 * 
 * @author jrwz
 */
public class SkillEffectNpc implements L1SkillEffect {

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
            // 怪物专属魔法
            case 10026: // 火龙安息字串
            case 10027:
            case 10028:
            case 10029:
                if (_user instanceof L1NpcInstance) {
                    final L1NpcInstance npc = (L1NpcInstance) _user;
                    _user.broadcastPacket(new S_NpcChatPacket(npc, (cha
                            .getName()) + ("! ") + ("$3717"), (byte) 2)); // 龙的安息字串
                } else {
                    _player.broadcastPacket(new S_ChatPacket(_player, (cha
                            .getName()) + ("! ") + ("$3717"), 0, (byte) 0)); // 龙的安息字串
                }
                break;

            case 10057:
                L1Teleport.teleportToTargetFront(cha, _user, 1);
                break;

            case STATUS_FREEZE:
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_BIND, true));
                }
                break;
        }
        return dmg;
    }

}
