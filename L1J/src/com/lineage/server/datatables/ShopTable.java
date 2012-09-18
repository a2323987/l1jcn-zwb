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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.model.shop.L1Shop;
import com.lineage.server.templates.L1ShopItem;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Lists;
import com.lineage.server.utils.collections.Maps;

/**
 * 商店资料表
 */
public class ShopTable {

    private static final long serialVersionUID = 1L;

    private static Logger _log = Logger.getLogger(ShopTable.class.getName());

    private static ShopTable _instance;

    public static ShopTable getInstance() {
        if (_instance == null) {
            _instance = new ShopTable();
        }
        return _instance;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private final Map<Integer, L1Shop> _allShops = Maps.newMap();

    private ShopTable() {
        this.loadShops();
    }

    private List<Integer> enumNpcIds() {
        final List<Integer> ids = Lists.newList();

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT DISTINCT npc_id FROM shop");
            rs = pstm.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("npc_id"));
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs, pstm, con);
        }
        return ids;
    }

    public L1Shop get(final int npcId) {
        return this._allShops.get(npcId);
    }

    private L1Shop loadShop(final int npcId, final ResultSet rs)
            throws SQLException {
        final List<L1ShopItem> sellingList = Lists.newList();
        final List<L1ShopItem> purchasingList = Lists.newList();
        while (rs.next()) {
            final int itemId = rs.getInt("item_id");
            final int sellingPrice = rs.getInt("selling_price");
            final int purchasingPrice = rs.getInt("purchasing_price");
            int packCount = rs.getInt("pack_count");
            packCount = packCount == 0 ? 1 : packCount;
            if (0 <= sellingPrice) {
                final L1ShopItem item = new L1ShopItem(itemId, sellingPrice,
                        packCount);
                sellingList.add(item);
            }
            if (0 <= purchasingPrice) {
                final L1ShopItem item = new L1ShopItem(itemId, purchasingPrice,
                        packCount);
                purchasingList.add(item);
            }
        }
        return new L1Shop(npcId, sellingList, purchasingList);
    }

    private void loadShops() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT * FROM shop WHERE npc_id=? ORDER BY order_id");
            for (final int npcId : this.enumNpcIds()) {
                pstm.setInt(1, npcId);
                rs = pstm.executeQuery();
                final L1Shop shop = this.loadShop(npcId, rs);
                this._allShops.put(npcId, shop);
                rs.close();
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs, pstm, con);
        }
    }

    /**
     * 重新加载Shop物品贩卖价格与数量
     */
    public void reloadShops() {
        this.loadShops();
    }
}
