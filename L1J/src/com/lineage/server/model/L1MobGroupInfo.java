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

import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.utils.collections.Lists;

// Referenced classes of package com.lineage.server.model:
// L1MobGroupInfo

/**
 * MOB群组信息
 */
public class L1MobGroupInfo {

    /** 成员列表 */
    private final List<L1NpcInstance> _membersList = Lists.newList();
    /** 领导者 */
    private L1NpcInstance _leader;

    /** 产生 */
    private L1Spawn _spawn;

    /** 删除群组 */
    private boolean _isRemoveGroup;

    public L1MobGroupInfo() {
    }

    /** 增加成员 */
    public void addMember(final L1NpcInstance npc) {
        if (npc == null) {
            throw new NullPointerException();
        }

        // 最初のメンバーであればリーダーにする
        if (this._membersList.isEmpty()) {
            this.setLeader(npc);
            // リーダーの再ポップ情報を保存する
            if (npc.isReSpawn()) {
                this.setSpawn(npc.getSpawn());
            }
        }

        if (!this._membersList.contains(npc)) {
            this._membersList.add(npc);
        }
        npc.setMobGroupInfo(this);
        npc.setMobGroupId(this._leader.getId());
    }

    /** 取得领导者 */
    public L1NpcInstance getLeader() {
        return this._leader;
    }

    /** NUM各成员 */
    public int getNumOfMembers() {
        return this._membersList.size();
    }

    /** 取得产生 */
    public L1Spawn getSpawn() {
        return this._spawn;
    }

    /** 领导者 */
    public boolean isLeader(final L1NpcInstance npc) {
        return npc.getId() == this._leader.getId();
    }

    /** 删除群组 */
    public boolean isRemoveGroup() {
        return this._isRemoveGroup;
    }

    /** 删除成员 */
    public synchronized int removeMember(final L1NpcInstance npc) {
        if (npc == null) {
            throw new NullPointerException();
        }

        if (this._membersList.contains(npc)) {
            this._membersList.remove(npc);
        }
        npc.setMobGroupInfo(null);

        // リーダーで他のメンバーがいる場合は、新リーダーにする
        if (this.isLeader(npc)) {
            if (this.isRemoveGroup() && (this._membersList.size() != 0)) { // リーダーが死亡したらグループ解除する場合
                for (final L1NpcInstance minion : this._membersList) {
                    minion.setMobGroupInfo(null);
                    minion.setSpawn(null);
                    minion.setreSpawn(false);
                }
                return 0;
            }
            if (this._membersList.size() != 0) {
                this.setLeader(this._membersList.get(0));
            }
        }

        // 返回剩余的成员数目
        return this._membersList.size();
    }

    /** 设定领导者 */
    public void setLeader(final L1NpcInstance npc) {
        this._leader = npc;
    }

    /** 设定删除群组 */
    public void setRemoveGroup(final boolean flag) {
        this._isRemoveGroup = flag;
    }

    /** 设定产生 */
    public void setSpawn(final L1Spawn spawn) {
        this._spawn = spawn;
    }

}
