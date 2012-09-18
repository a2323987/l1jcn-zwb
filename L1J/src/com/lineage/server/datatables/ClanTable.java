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
package com.lineage.server.datatables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.IdFactory;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 血盟资料表
 */
public class ClanTable {

    private static Logger _log = Logger.getLogger(ClanTable.class.getName());

    private final Map<Integer, L1Clan> _clans = Maps.newMap();

    private static ClanTable _instance;

    public static ClanTable getInstance() {
        if (_instance == null) {
            _instance = new ClanTable();
        }
        return _instance;
    }

    private ClanTable() {
        {
            Connection con = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;

            try {
                con = L1DatabaseFactory.getInstance().getConnection();
                pstm = con
                        .prepareStatement("SELECT * FROM clan_data ORDER BY clan_id");

                rs = pstm.executeQuery();
                while (rs.next()) {
                    final L1Clan clan = new L1Clan();
                    // clan.SetClanId(clanData.getInt(1));
                    final int clan_id = rs.getInt(1);
                    clan.setClanId(clan_id);
                    clan.setClanName(rs.getString(2));
                    clan.setLeaderId(rs.getInt(3));
                    clan.setLeaderName(rs.getString(4));
                    clan.setCastleId(rs.getInt(5));
                    clan.setHouseId(rs.getInt(6));

                    L1World.getInstance().storeClan(clan);
                    this._clans.put(clan_id, clan);
                }

            } catch (final SQLException e) {
                _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            } finally {
                SQLUtil.close(rs);
                SQLUtil.close(pstm);
                SQLUtil.close(con);
            }
        }

        final Collection<L1Clan> AllClan = L1World.getInstance().getAllClans();
        for (final L1Clan clan : AllClan) {
            Connection con = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;

            try {
                con = L1DatabaseFactory.getInstance().getConnection();
                pstm = con
                        .prepareStatement("SELECT char_name FROM characters WHERE ClanID = ?");
                pstm.setInt(1, clan.getClanId());
                rs = pstm.executeQuery();

                while (rs.next()) {
                    clan.addMemberName(rs.getString(1));
                }
            } catch (final SQLException e) {
                _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            } finally {
                SQLUtil.close(rs);
                SQLUtil.close(pstm);
                SQLUtil.close(con);
            }
        }
        // 血盟仓库
        for (final L1Clan clan : AllClan) {
            clan.getDwarfForClanInventory().loadItems();
        }
    }

    /**
     * 创建血盟
     * 
     * @param player
     *            角色
     * @param clan_name
     *            血盟名称
     * @return clan
     */
    public L1Clan createClan(final L1PcInstance player, final String clan_name) {
        for (final L1Clan oldClans : L1World.getInstance().getAllClans()) {
            if (oldClans.getClanName().equalsIgnoreCase(clan_name)) {
                return null;
            }
        }
        final L1Clan clan = new L1Clan();
        clan.setClanId(IdFactory.getInstance().nextId());
        clan.setClanName(clan_name);
        clan.setLeaderId(player.getId());
        clan.setLeaderName(player.getName());
        clan.setCastleId(0);
        clan.setHouseId(0);

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("INSERT INTO clan_data SET clan_id=?, clan_name=?, leader_id=?, leader_name=?, hascastle=?, hashouse=?");
            pstm.setInt(1, clan.getClanId());
            pstm.setString(2, clan.getClanName());
            pstm.setInt(3, clan.getLeaderId());
            pstm.setString(4, clan.getLeaderName());
            pstm.setInt(5, clan.getCastleId());
            pstm.setInt(6, clan.getHouseId());
            pstm.execute();
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }

        L1World.getInstance().storeClan(clan);
        this._clans.put(clan.getClanId(), clan);

        player.setClanid(clan.getClanId());
        player.setClanname(clan.getClanName());
        player.setClanRank(L1Clan.CLAN_RANK_PRINCE);
        clan.addMemberName(player.getName());
        try {
            // 将玩家资料保存至资料库
            player.save();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
        return clan;
    }

    /**
     * 删除血盟
     * 
     * @param clan_name
     */
    public void deleteClan(final String clan_name) {
        final L1Clan clan = L1World.getInstance().getClan(clan_name);
        if (clan == null) {
            return;
        }
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("DELETE FROM clan_data WHERE clan_name=?");
            pstm.setString(1, clan_name);
            pstm.execute();
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
        clan.getDwarfForClanInventory().clearItems();
        clan.getDwarfForClanInventory().deleteAllItems();

        L1World.getInstance().removeClan(clan);
        this._clans.remove(clan.getClanId());
    }

    public L1Clan getTemplate(final int clan_id) {
        return this._clans.get(clan_id);
    }

    /**
     * 更新血盟
     * 
     * @param clan
     */
    public void updateClan(final L1Clan clan) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("UPDATE clan_data SET clan_id=?, leader_id=?, leader_name=?, hascastle=?, hashouse=? WHERE clan_name=?");
            pstm.setInt(1, clan.getClanId());
            pstm.setInt(2, clan.getLeaderId());
            pstm.setString(3, clan.getLeaderName());
            pstm.setInt(4, clan.getCastleId());
            pstm.setInt(5, clan.getHouseId());
            pstm.setString(6, clan.getClanName());
            pstm.execute();
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

}
