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
package com.lineage.server.model;

import java.util.List;

import com.lineage.Config;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.collections.Lists;

// Referenced classes of package com.lineage.server.model:
// L1ChatParty

/**
 * 聊天组队
 */
public class L1ChatParty {

    private final List<L1PcInstance> _membersList = Lists.newList();

    private L1PcInstance _leader = null;

    public void addMember(final L1PcInstance pc) {
        if (pc == null) {
            throw new NullPointerException();
        }
        if (((this._membersList.size() == Config.MAX_CHAT_PT) && !this._leader
                .isGm()) || this._membersList.contains(pc)) {
            return;
        }

        if (this._membersList.isEmpty()) {
            // 最初のPTメンバーであればリーダーにする
            this.setLeader(pc);
        }

        this._membersList.add(pc);
        pc.setChatParty(this);
    }

    private void breakup() {
        final L1PcInstance[] members = this.getMembers();

        for (final L1PcInstance member : members) {
            this.removeMember(member);
            member.sendPackets(new S_ServerMessage(418)); // 您解散您的队伍了!!
        }
    }

    public L1PcInstance getLeader() {
        return this._leader;
    }

    public L1PcInstance[] getMembers() {
        return this._membersList.toArray(new L1PcInstance[this._membersList
                .size()]);
    }

    public String getMembersNameList() {
        String _result = new String("");
        for (final L1PcInstance pc : this._membersList) {
            _result = _result + pc.getName() + " ";
        }
        return _result;
    }

    public int getNumOfMembers() {
        return this._membersList.size();
    }

    public int getVacancy() {
        return Config.MAX_CHAT_PT - this._membersList.size();
    }

    public boolean isLeader(final L1PcInstance pc) {
        return pc.getId() == this._leader.getId();
    }

    public boolean isMember(final L1PcInstance pc) {
        return this._membersList.contains(pc);
    }

    public boolean isVacancy() {
        return this._membersList.size() < Config.MAX_CHAT_PT;
    }

    public void kickMember(final L1PcInstance pc) {
        if (this.getNumOfMembers() == 2) {
            // 自己是队长
            this.removeMember(pc);
            final L1PcInstance leader = this.getLeader();
            this.removeMember(leader);
        } else {
            // 组队的剩余成员超过两个以上
            this.removeMember(pc);
        }
        pc.sendPackets(new S_ServerMessage(419)); // 您从队伍中被驱逐了。
    }

    public void leaveMember(final L1PcInstance pc) {
        final L1PcInstance[] members = this.getMembers();
        if (this.isLeader(pc)) {
            // 是队长的情况
            this.breakup();
        } else {
            // 不是队长的情况
            if (this.getNumOfMembers() == 2) {
                // 自己是队长
                this.removeMember(pc);
                final L1PcInstance leader = this.getLeader();
                this.removeMember(leader);

                this.sendLeftMessage(pc, pc);
                this.sendLeftMessage(leader, pc);
            } else {
                // 组队的剩余成员超过两个以上
                this.removeMember(pc);
                for (final L1PcInstance member : members) {
                    this.sendLeftMessage(member, pc);
                }
                this.sendLeftMessage(pc, pc);
            }
        }
    }

    private void removeMember(final L1PcInstance pc) {
        if (!this._membersList.contains(pc)) {
            return;
        }

        this._membersList.remove(pc);
        pc.setChatParty(null);
    }

    private void sendLeftMessage(final L1PcInstance sendTo,
            final L1PcInstance left) {
        sendTo.sendPackets(new S_ServerMessage(420, left.getName())); // %0%s
                                                                      // 离开了队伍。
    }

    private void setLeader(final L1PcInstance pc) {
        this._leader = pc;
    }

}
