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
package com.lineage.server.serverpackets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.Opcodes;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Lists;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 拍卖盟屋公告栏列表
 */
public class S_AuctionBoard extends ServerBasePacket {

    private static Logger _log = Logger.getLogger(S_AuctionBoard.class
            .getName());

    private static final String S_AUCTIONBOARD = "[S] S_AuctionBoard";

    private byte[] _byte = null;

    public S_AuctionBoard(final L1NpcInstance board) {
        this.buildPacket(board);
    }

    private void buildPacket(final L1NpcInstance board) {
        final List<Integer> houseList = Lists.newList();
        int houseId = 0;
        int count = 0;
        int[] id = null;
        String[] name = null;
        int[] area = null;
        int[] month = null;
        int[] day = null;
        int[] price = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM board_auction");
            rs = pstm.executeQuery();
            while (rs.next()) {
                houseId = rs.getInt(1);
                if ((board.getX() == 33421) && (board.getY() == 32823)) { // 拍卖公告栏(奇岩)
                    if ((houseId >= 262145) && (houseId <= 262189)) {
                        houseList.add(houseId);
                        count++;
                    }
                } else if ((board.getX() == 33585) && (board.getY() == 33235)) { // 拍卖公告栏(海音)
                    if ((houseId >= 327681) && (houseId <= 327691)) {
                        houseList.add(houseId);
                        count++;
                    }
                } else if ((board.getX() == 33959) && (board.getY() == 33253)) { // 拍卖公告栏(亚丁)
                    if ((houseId >= 458753) && (houseId <= 458819)) {
                        houseList.add(houseId);
                        count++;
                    }
                } else if ((board.getX() == 32611) && (board.getY() == 32775)) { // 拍卖公告栏(古鲁丁)
                    if ((houseId >= 524289) && (houseId <= 524294)) {
                        houseList.add(houseId);
                        count++;
                    }
                }
            }
            id = new int[count];
            name = new String[count];
            area = new int[count];
            month = new int[count];
            day = new int[count];
            price = new int[count];

            for (int i = 0; i < count; ++i) {
                pstm = con
                        .prepareStatement("SELECT * FROM board_auction WHERE house_id=?");
                houseId = houseList.get(i);
                pstm.setInt(1, houseId);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    id[i] = rs.getInt(1);
                    name[i] = rs.getString(2);
                    area[i] = rs.getInt(3);
                    final Calendar cal = this
                            .timestampToCalendar((Timestamp) rs.getObject(4));
                    month[i] = cal.get(Calendar.MONTH) + 1;
                    day[i] = cal.get(Calendar.DATE);
                    price[i] = rs.getInt(5);
                }
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }

        this.writeC(Opcodes.S_OPCODE_HOUSELIST);
        this.writeD(board.getId());
        this.writeH(count); // 记录数
        for (int i = 0; i < count; ++i) {
            this.writeD(id[i]); // 盟屋编号
            this.writeS(name[i]); // 盟屋名称
            this.writeH(area[i]); // 盟屋面积
            this.writeC(month[i]); // 截止月
            this.writeC(day[i]); // 截止日
            this.writeD(price[i]); // 售屋价格
        }
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this.getBytes();
        }
        return this._byte;
    }

    @Override
    public String getType() {
        return S_AUCTIONBOARD;
    }

    private Calendar timestampToCalendar(final Timestamp ts) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ts.getTime());
        return cal;
    }
}
