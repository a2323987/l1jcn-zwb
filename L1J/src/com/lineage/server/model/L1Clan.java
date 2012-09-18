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
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.datatables.CharacterTable;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.utils.collections.Lists;

/**
 * 血盟
 */
public class L1Clan {

    /**  */
    private static final Logger _log = Logger.getLogger(L1Clan.class.getName());

    /** 血盟等级[见习] */
    public static final int CLAN_RANK_PROBATION = 1;
    /** 血盟等级[一般] */
    public static final int CLAN_RANK_PUBLIC = 2;
    /** 血盟等级[守护骑士] */
    public static final int CLAN_RANK_GUARDIAN = 3;
    /** 血盟等级[血盟君主] */
    public static final int CLAN_RANK_PRINCE = 4;
    /** 血盟ID */
    private int _clanId;
    /** 血盟名称 */
    private String _clanName;
    /** 领导者的ID */
    private int _leaderId;
    /** 领导者的名称 */
    private String _leaderName;
    /** 城堡ID */
    private int _castleId;
    /** 盟屋ID */
    private int _houseId;
    /** 仓库 */
    private int _warehouse = 0;
    /**  */
    private final L1DwarfForClanInventory _dwarfForClan = new L1DwarfForClanInventory(
            this);
    /** 成员名单 */
    private final List<String> membersNameList = Lists.newList();

    /** 增加成员名称 */
    public void addMemberName(final String member_name) {
        if (!this.membersNameList.contains(member_name)) {
            this.membersNameList.add(member_name);
        }
    }

    /** 删除成员名称 */
    public void delMemberName(final String member_name) {
        if (this.membersNameList.contains(member_name)) {
            this.membersNameList.remove(member_name);
        }
    }

    /** 取得所有成员 */
    public String[] getAllMembers() {
        return this.membersNameList.toArray(new String[this.membersNameList
                .size()]);
    }

    /** 获得所有成员FP */
    public String getAllMembersFP() {
        String result = "";
        for (final String name : this.membersNameList) {
            result = result + name + " ";
        }
        return result;
    }

    /** 获得所有成员FP排名 */
    public String getAllMembersFPWithRank() {
        String result = "";
        try {
            for (final String name : this.membersNameList) {
                final L1PcInstance pc = CharacterTable.getInstance()
                        .restoreCharacter(name);
                if (pc != null) {
                    result = result + name + this.getRankString(pc) + " ";
                }
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
        return result;
    }

    /** 获得城堡ID */
    public int getCastleId() {
        return this._castleId;
    }

    /** 获得血盟ID */
    public int getClanId() {
        return this._clanId;
    }

    /** 获得血盟名称 */
    public String getClanName() {
        return this._clanName;
    }

    /**  */
    public L1DwarfForClanInventory getDwarfForClanInventory() {
        return this._dwarfForClan;
    }

    /** 获得盟屋ID */
    public int getHouseId() {
        return this._houseId;
    }

    /** 获得领导者的ID */
    public int getLeaderId() {
        return this._leaderId;
    }

    /** 获得领导者的名称 */
    public String getLeaderName() {
        return this._leaderName;
    }

    /** 获得在线血盟成员 */
    public L1PcInstance[] getOnlineClanMember() {
        final List<L1PcInstance> onlineMembers = Lists.newList();
        for (final String name : this.membersNameList) {
            final L1PcInstance pc = L1World.getInstance().getPlayer(name);
            if ((pc != null) && !onlineMembers.contains(pc)) {
                onlineMembers.add(pc);
            }
        }
        return onlineMembers.toArray(new L1PcInstance[onlineMembers.size()]);
    }

    /** 获得在线成员FP */
    public String getOnlineMembersFP() { // FP means "For Pledge" (FP表示承诺)
        String result = "";
        for (final String name : this.membersNameList) {
            final L1PcInstance pc = L1World.getInstance().getPlayer(name);
            if (pc != null) {
                result = result + name + " ";
            }
        }
        return result;
    }

    /** 获得在线成员FP排名 */
    public String getOnlineMembersFPWithRank() {
        String result = "";
        for (final String name : this.membersNameList) {
            final L1PcInstance pc = L1World.getInstance().getPlayer(name);
            if (pc != null) {
                result = result + name + this.getRankString(pc) + " ";
            }
        }
        return result;
    }

    /** 获得排名字符串 */
    private String getRankString(final L1PcInstance pc) {
        String rank = "";
        final String[] msg = { "[見習]", "[一般]", "[守護騎士]", "[血盟君主]", "[见习]",
                "[一般]", "[守护骑士]", "[血盟君主]", "[見習い]", "[一般]", "[ガーディアン]",
                "[血盟君主]" };
        byte i = 0; // 预设：繁体
        if (Config.CLIENT_LANGUAGE == 5) { // 简体
            i = 4;
        } else if (Config.CLIENT_LANGUAGE == 4) { // 日文
            i = 8;
        }
        if (pc != null) {
            if (pc.getClanRank() == CLAN_RANK_PROBATION) {
                rank = msg[0 + i];
            } else if (pc.getClanRank() == CLAN_RANK_PUBLIC) {
                rank = msg[1 + i];
            } else if (pc.getClanRank() == CLAN_RANK_GUARDIAN) {
                rank = msg[2 + i];
            } else if (pc.getClanRank() == CLAN_RANK_PRINCE) {
                rank = msg[3 + i];
            } else {
                rank = "";
            }

        }
        return rank;
    }

    /** 取得使用仓库的角色 */
    public int getWarehouseUsingChar() {
        return this._warehouse;
    }

    /** 设置城堡ID */
    public void setCastleId(final int hasCastle) {
        this._castleId = hasCastle;
    }

    /** 设置血盟ID */
    public void setClanId(final int clan_id) {
        this._clanId = clan_id;
    }

    /** 设置血盟名称 */
    public void setClanName(final String clan_name) {
        this._clanName = clan_name;
    }

    /** 设置盟屋ID */
    public void setHouseId(final int hasHideout) {
        this._houseId = hasHideout;
    }

    /** 设置领导者的ID */
    public void setLeaderId(final int leader_id) {
        this._leaderId = leader_id;
    }

    /** 设置领导者的名称 */
    public void setLeaderName(final String leader_name) {
        this._leaderName = leader_name;
    }

    /** 设定使用仓库的角色 */
    public void setWarehouseUsingChar(final int objid) {
        this._warehouse = objid;
    }
}
