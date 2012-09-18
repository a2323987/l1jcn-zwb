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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.templates.L1House;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Lists;
import com.lineage.server.utils.collections.Maps;

/**
 * 盟屋资料表
 */
public class HouseTable {

    private static Logger _log = Logger.getLogger(HouseTable.class.getName());

    private static HouseTable _instance;

    /**
     * 取得盟屋ID列表
     * 
     * @return
     */
    public static List<Integer> getHouseIdList() {
        final List<Integer> houseIdList = Lists.newList();

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT house_id FROM house ORDER BY house_id");
            rs = pstm.executeQuery();
            while (rs.next()) {
                final int houseId = rs.getInt("house_id");
                houseIdList.add(Integer.valueOf(houseId));
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }

        return houseIdList;
    }

    public static HouseTable getInstance() {
        if (_instance == null) {
            _instance = new HouseTable();
        }
        return _instance;
    }

    /** 盟屋 */
    private final Map<Integer, L1House> _house = Maps.newConcurrentMap();

    public HouseTable() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT * FROM house ORDER BY house_id");
            rs = pstm.executeQuery();
            while (rs.next()) {
                final L1House house = new L1House();
                house.setHouseId(rs.getInt(1));
                house.setHouseName(rs.getString(2));
                house.setHouseArea(rs.getInt(3));
                house.setLocation(rs.getString(4));
                house.setKeeperId(rs.getInt(5));
                house.setOnSale(rs.getInt(6) == 1 ? true : false);
                house.setPurchaseBasement(rs.getInt(7) == 1 ? true : false);
                house.setTaxDeadline(this.timestampToCalendar((Timestamp) rs
                        .getObject(8)));
                this._house.put(house.getHouseId(), house);
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    /**
     * 取得盟屋表
     * 
     * @return
     */
    public L1House getHouseTable(final int houseId) {
        return this._house.get(houseId);
    }

    /**
     * 取得盟屋表列表
     * 
     * @return
     */
    public L1House[] getHouseTableList() {
        return this._house.values().toArray(new L1House[this._house.size()]);
    }

    private Calendar timestampToCalendar(final Timestamp ts) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ts.getTime());
        return cal;
    }

    /**
     * 更新盟屋
     */
    public void updateHouse(final L1House house) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("UPDATE house SET house_name=?, house_area=?, location=?, keeper_id=?, is_on_sale=?, is_purchase_basement=?, tax_deadline=? WHERE house_id=?");
            pstm.setString(1, house.getHouseName());
            pstm.setInt(2, house.getHouseArea());
            pstm.setString(3, house.getLocation());
            pstm.setInt(4, house.getKeeperId());
            pstm.setInt(5, house.isOnSale() == true ? 1 : 0);
            pstm.setInt(6, house.isPurchaseBasement() == true ? 1 : 0);
            final SimpleDateFormat sdf = new SimpleDateFormat(
                    "yyyy/MM/dd HH:mm:ss");
            final String fm = sdf.format(house.getTaxDeadline().getTime());
            pstm.setString(7, fm);
            pstm.setInt(8, house.getHouseId());
            pstm.execute();
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }
}
