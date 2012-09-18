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
import com.lineage.server.serverpackets.S_HPMeter;
import com.lineage.server.serverpackets.S_Party;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.collections.Lists;

// Referenced classes of package com.lineage.server.model:
// L1Party

/**
 * 队伍
 */
public class L1Party {

    /** 成员列表 */
    private final List<L1PcInstance> _membersList = Lists.newList();

    /** 领导者 */
    private L1PcInstance _leader = null;

    /** 增加成员 */
    public void addMember(final L1PcInstance pc) {
        if (pc == null) {
            throw new NullPointerException();
        }
        if (((this._membersList.size() == Config.MAX_PT) && !this._leader
                .isGm()) || this._membersList.contains(pc)) {
            return;
        }

        if (this._membersList.isEmpty()) {
            // 最初のPTメンバーであればリーダーにする
            this.setLeader(pc);
        } else {
            this.createMiniHp(pc);
        }

        this._membersList.add(pc);
        pc.setParty(this);
        this.showAddPartyInfo(pc);
        pc.startRefreshParty();
    }

    /** 解散队伍 */
    private void breakup() {
        final L1PcInstance[] members = this.getMembers();

        for (final L1PcInstance member : members) {
            this.removeMember(member);
            member.sendPackets(new S_ServerMessage(418)); // 您解散您的队伍了!!
        }
    }

    /** 创建组队血条 */
    private void createMiniHp(final L1PcInstance pc) {
        // 成员加入时、显示对方的HP血条
        final L1PcInstance[] members = this.getMembers();

        for (final L1PcInstance member : members) {
            member.sendPackets(new S_HPMeter(pc.getId(), 100
                    * pc.getCurrentHp() / pc.getMaxHp()));
            pc.sendPackets(new S_HPMeter(member.getId(), 100
                    * member.getCurrentHp() / member.getMaxHp()));
        }
    }

    /** 删除组队血条 */
    private void deleteMiniHp(final L1PcInstance pc) {
        // 队员离队时、删除HP血条。
        final L1PcInstance[] members = this.getMembers();

        for (final L1PcInstance member : members) {
            pc.sendPackets(new S_HPMeter(member.getId(), 0xff));
            member.sendPackets(new S_HPMeter(pc.getId(), 0xff));
        }
    }

    /** 获得领导者 */
    public L1PcInstance getLeader() {
        return this._leader;
    }

    /** 获得成员 */
    public L1PcInstance[] getMembers() {
        return this._membersList.toArray(new L1PcInstance[this._membersList
                .size()]);
    }

    /** 获得成员名称列表 */
    public String getMembersNameList() {
        String _result = new String("");
        for (final L1PcInstance pc : this._membersList) {
            _result = _result + pc.getName() + " ";
        }
        return _result;
    }

    /** 成员数量 */
    public int getNumOfMembers() {
        return this._membersList.size();
    }

    /** 获得空闲 */
    public int getVacancy() {
        return Config.MAX_PT - this._membersList.size();
    }

    /** 是领导者 */
    public boolean isLeader(final L1PcInstance pc) {
        return pc.getId() == this._leader.getId();
    }

    /** 是成员 */
    public boolean isMember(final L1PcInstance pc) {
        return this._membersList.contains(pc);
    }

    /** 空闲的 */
    public boolean isVacancy() {
        return this._membersList.size() < Config.MAX_PT;
    }

    /** 踢成员 */
    public void kickMember(final L1PcInstance pc) {
        if (this.getNumOfMembers() == 2) {
            // パーティーメンバーが自分とリーダーのみ
            this.breakup();
        } else {
            this.removeMember(pc);
            for (final L1PcInstance member : this.getMembers()) {
                this.sendLeftMessage(member, pc);
            }
            this.sendKickMessage(pc);
        }
    }

    /** 离开队员 */
    public void leaveMember(final L1PcInstance pc) {
        if (this.isLeader(pc) || (this.getNumOfMembers() == 2)) {
            // 有组队领导者的场合
            this.breakup();
        } else {
            this.removeMember(pc);
            for (final L1PcInstance member : this.getMembers()) {
                this.sendLeftMessage(member, pc);
            }
            this.sendLeftMessage(pc, pc);
            // 没有组队领导者的场合
            /*
             * if (getNumOfMembers() == 2) { // パーティーメンバーが自分とリーダーのみ
             * removeMember(pc); L1PcInstance leader = getLeader();
             * removeMember(leader); sendLeftMessage(pc, pc);
             * sendLeftMessage(leader, pc); } else { // 残りのパーティーメンバーが２人以上いる
             * removeMember(pc); for (L1PcInstance member : members) {
             * sendLeftMessage(member, pc); } sendLeftMessage(pc, pc); }
             */
        }
    }

    /** 通过领导者 */
    public void passLeader(final L1PcInstance pc) {
        pc.getParty().setLeader(pc);
        for (final L1PcInstance member : this.getMembers()) {
            member.sendPackets(new S_Party(0x6A, pc));
        }
    }

    /** 删除成员 */
    private void removeMember(final L1PcInstance pc) {
        if (!this._membersList.contains(pc)) {
            return;
        }
        pc.stopRefreshParty();
        this._membersList.remove(pc);
        pc.setParty(null);
        if (!this._membersList.isEmpty()) {
            this.deleteMiniHp(pc);
        }
    }

    /** 发送踢人信息 */
    private void sendKickMessage(final L1PcInstance kickpc) {
        kickpc.sendPackets(new S_ServerMessage(419)); // 您从队伍中被驱逐了。
    }

    /** 发送离队信息 */
    private void sendLeftMessage(final L1PcInstance sendTo,
            final L1PcInstance left) {
        // %0%s 离开了队伍。
        sendTo.sendPackets(new S_ServerMessage(420, left.getName()));
    }

    /** 设置领导者 */
    private void setLeader(final L1PcInstance pc) {
        this._leader = pc;
    }

    /** 显示加入队伍的信息 */
    private void showAddPartyInfo(final L1PcInstance pc) {
        for (final L1PcInstance member : this.getMembers()) {
            if ((pc.getId() == this.getLeader().getId())
                    && (this.getNumOfMembers() == 1)) {
                continue;
            }
            // 发送给队长的封包
            if (pc.getId() == member.getId()) {
                pc.sendPackets(new S_Party(0x68, pc));
            } else { // 其他成员封包
                member.sendPackets(new S_Party(0x69, pc));
            }
            member.sendPackets(new S_Party(0x6e, member));
            this.createMiniHp(member);
        }
    }

    /** 更新组队血条 */
    public void updateMiniHP(final L1PcInstance pc) {
        final L1PcInstance[] members = this.getMembers();

        for (final L1PcInstance member : members) { // パーティーメンバー分更新
            member.sendPackets(new S_HPMeter(pc.getId(), 100
                    * pc.getCurrentHp() / pc.getMaxHp()));
        }
    }

}
