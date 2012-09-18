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
package com.lineage.server;

import java.util.Calendar;
import java.util.TimeZone;

import com.lineage.Config;
import com.lineage.server.datatables.AuctionBoardTable;
import com.lineage.server.datatables.ClanTable;
import com.lineage.server.datatables.HouseTable;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1World;
import com.lineage.server.templates.L1AuctionBoard;
import com.lineage.server.templates.L1House;

/**
 * 控制盟屋税时间
 */
public class HouseTaxTimeController implements Runnable {

    private static HouseTaxTimeController _instance;

    public static HouseTaxTimeController getInstance() {
        if (_instance == null) {
            _instance = new HouseTaxTimeController();
        }
        return _instance;
    }

    // 检查税期限
    private void checkTaxDeadline() {
        for (final L1House house : HouseTable.getInstance().getHouseTableList()) {
            if (!house.isOnSale()) { // 不检查再拍卖的血盟小屋
                if (house.getTaxDeadline().before(this.getRealTime())) {
                    this.sellHouse(house);
                }
            }
        }
    }

    /**
     * 取得现实时间
     */
    public Calendar getRealTime() {
        final TimeZone tz = TimeZone.getTimeZone(Config.TIME_ZONE);
        final Calendar cal = Calendar.getInstance(tz);
        return cal;
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.checkTaxDeadline();
                Thread.sleep(600000);
            }
        } catch (final Exception e1) {
        }
    }

    // 拍卖盟屋
    private void sellHouse(final L1House house) {
        final AuctionBoardTable boardTable = new AuctionBoardTable();
        final L1AuctionBoard board = new L1AuctionBoard();
        if (board != null) {
            // 在拍卖板张贴新公告
            final int houseId = house.getHouseId();
            board.setHouseId(houseId);
            board.setHouseName(house.getHouseName());
            board.setHouseArea(house.getHouseArea());
            final TimeZone tz = TimeZone.getTimeZone(Config.TIME_ZONE);
            final Calendar cal = Calendar.getInstance(tz);
            cal.add(Calendar.DATE, 5); // 5天以后
            cal.set(Calendar.MINUTE, 0); //
            cal.set(Calendar.SECOND, 0);
            board.setDeadline(cal);
            board.setPrice(100000);
            board.setLocation(house.getLocation());
            board.setOldOwner("");
            board.setOldOwnerId(0);
            board.setBidder("");
            board.setBidderId(0);
            boardTable.insertAuctionBoard(board);
            house.setOnSale(true); // 设定为拍卖中
            house.setPurchaseBasement(true); // TODO: 翻译 地下アジト未购入に设定
            cal.add(Calendar.DATE, Config.HOUSE_TAX_INTERVAL);
            house.setTaxDeadline(cal);
            HouseTable.getInstance().updateHouse(house); // 储存到资料库中
            // 取消之前的拥有者
            for (final L1Clan clan : L1World.getInstance().getAllClans()) {
                if (clan.getHouseId() == houseId) {
                    clan.setHouseId(0);
                    ClanTable.getInstance().updateClan(clan);
                }
            }
        }
    }

}
