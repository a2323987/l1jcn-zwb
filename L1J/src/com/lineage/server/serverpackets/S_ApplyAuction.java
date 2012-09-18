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
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.Opcodes;
import com.lineage.server.utils.SQLUtil;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 选取物品数量 (盟屋拍卖公告)
 */
public class S_ApplyAuction extends ServerBasePacket {

    private static Logger _log = Logger.getLogger(S_ApplyAuction.class
            .getName());
    private static final String S_APPLYAUCTION = "[S] S_ApplyAuction";
    private byte[] _byte = null;

    /**
     * 选取物品数量 (盟屋拍卖公告)
     * 
     * @param objectId
     * @param houseNumber
     */
    public S_ApplyAuction(final int objectId, final String houseNumber) {
        this.buildPacket(objectId, houseNumber);
    }

    private void buildPacket(final int objectId, final String houseNumber) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT * FROM board_auction WHERE house_id=?");
            final int number = Integer.valueOf(houseNumber);
            pstm.setInt(1, number);
            rs = pstm.executeQuery();
            while (rs.next()) {
                final int nowPrice = rs.getInt(5);
                final int bidderId = rs.getInt(10);
                this.writeC(Opcodes.S_OPCODE_INPUTAMOUNT);
                this.writeD(objectId);
                this.writeD(0); // ?
                if (bidderId == 0) { // 无投标人
                    this.writeD(nowPrice); // 控制的初始价格
                    this.writeD(nowPrice); // 价格下限
                } else { // 有竞标者
                    this.writeD(nowPrice + 1); // 控制的初始价格
                    this.writeD(nowPrice + 1); // 价格下限
                }
                this.writeD(2000000000); // 价格上限
                this.writeH(0); // ?
                this.writeS("agapply"); // HTML
                this.writeS("agapply " + houseNumber); // 命令
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
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
        return S_APPLYAUCTION;
    }
}
