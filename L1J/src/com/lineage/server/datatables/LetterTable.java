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
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.utils.SQLUtil;

/**
 * 信件资料表
 */
public class LetterTable {

    private static Logger _log = Logger.getLogger(LetterTable.class.getName());

    private static LetterTable _instance;

    public static LetterTable getInstance() {
        if (_instance == null) {
            _instance = new LetterTable();
        }
        return _instance;
    }

    public LetterTable() {
    }

    // 列表模板ID
    // 16:角色不存在
    // 32:太多的行李
    // 48:没有血盟
    // 64:※内容不显示(白字)
    // 80:※内容不显示(黑字)
    // 96:※内容不显示(黑字)
    // 112:祝贺您。以 %1金币卖出您所拥有的房子。因此给您扣掉%n手续费 10%%的金额金币 %0。%n谢谢。%n%n。
    // 128:有人提出比您高的金额，因此无法给你购买权。%n因为您参与拍卖没有得标，所以还给你 %0金币。%n谢谢。%n%n
    // 144:%n你在拍卖会上以 %0金币成交。%n现在去您的血盟小屋后，可利用多样的设备。%n谢谢。%n%n
    // 160:あなたが所有していた家が最終価格%1アデナで落札されました。
    // 176:あなたが申請なさった競売は、競売期間内に提示した金額以上での支払いを表明した方が現れなかったため、結局取り消されました。
    // 192:あなたが申請なさった競売は、競売期間内に提示した金額以上での支払いを表明した方が現れなかったため、結局取り消されました。
    // 208:あなたの血盟が所有している家は、本領主の領地に帰属しているため、今後利用したいのなら当方に税金を収めなければなりません。
    // 224:あなたは、あなたの家に課せられた税金%0アデナをまだ納めていません。
    // 240:あなたは、結局あなたの家に課された税金%0を納めなかったので、警告どおりにあなたの家に対する所有権を剥奪します。

    /**
     * 删除信件
     * 
     * @param itemObjectId
     */
    public void deleteLetter(final int itemObjectId) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("DELETE FROM letter WHERE item_object_id=?");
            pstm.setInt(1, itemObjectId);
            pstm.execute();
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    /**
     * 写信
     * 
     * @param itemObjectId
     * @param code
     * @param sender
     * @param receiver
     * @param date
     * @param templateId
     * @param subject
     * @param content
     */
    public void writeLetter(final int itemObjectId, final int code,
            final String sender, final String receiver, final String date,
            final int templateId, final byte[] subject, final byte[] content) {

        Connection con = null;
        PreparedStatement pstm1 = null;
        ResultSet rs = null;
        PreparedStatement pstm2 = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm1 = con
                    .prepareStatement("SELECT * FROM letter ORDER BY item_object_id");
            rs = pstm1.executeQuery();
            pstm2 = con
                    .prepareStatement("INSERT INTO letter SET item_object_id=?, code=?, sender=?, receiver=?, date=?, template_id=?, subject=?, content=?");
            pstm2.setInt(1, itemObjectId);
            pstm2.setInt(2, code);
            pstm2.setString(3, sender);
            pstm2.setString(4, receiver);
            pstm2.setString(5, date);
            pstm2.setInt(6, templateId);
            pstm2.setBytes(7, subject);
            pstm2.setBytes(8, content);
            pstm2.execute();
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm1);
            SQLUtil.close(pstm2);
            SQLUtil.close(con);
        }
    }

}
