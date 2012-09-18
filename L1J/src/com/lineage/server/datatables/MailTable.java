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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.L1DatabaseFactory;
import com.lineage.server.IdFactory;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.templates.L1Mail;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Lists;

/**
 * 邮件资料表
 */
public class MailTable {

    private static Logger _log = Logger.getLogger(MailTable.class.getName());

    private static MailTable _instance;

    private static List<L1Mail> _allMail = Lists.newList();

    /**
     * 取得所有邮件
     * 
     * @return
     */
    public static List<L1Mail> getAllMail() {
        return _allMail;
    }

    public static MailTable getInstance() {
        if (_instance == null) {
            _instance = new MailTable();
        }
        return _instance;
    }

    /**
     * 取得邮件
     * 
     * @param mailId
     */
    public static L1Mail getMail(final int mailId) {
        for (final L1Mail mail : _allMail) {
            if (mail.getId() == mailId) {
                return mail;
            }
        }
        return null;
    }

    private MailTable() {
        this.loadMail();
    }

    /**
     * 更改邮件状态
     * 
     * @param mailId
     */
    private void changeMailStatus(final int mailId) {
        for (final L1Mail mail : _allMail) {
            if (mail.getId() == mailId) {
                final L1Mail newMail = mail;
                newMail.setReadStatus(1);

                _allMail.remove(mail);
                _allMail.add(newMail);
                break;
            }
        }
    }

    /**
     * 更改邮件类型
     * 
     * @param mailId
     * @param type
     */
    private void changeMailType(final int mailId, final int type) {
        for (final L1Mail mail : _allMail) {
            if (mail.getId() == mailId) {
                final L1Mail newMail = mail;
                newMail.setType(type);

                _allMail.remove(mail);
                _allMail.add(newMail);
                break;
            }
        }
    }

    /**
     * 删除邮件
     * 
     * @param mailId
     */
    public void deleteMail(final int mailId) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("DELETE FROM mail WHERE id=?");
            pstm.setInt(1, mailId);
            pstm.execute();

            this.delMail(mailId);
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }

    }

    /**
     * 删除邮件
     * 
     * @param mailId
     */
    private void delMail(final int mailId) {
        for (final L1Mail mail : _allMail) {
            if (mail.getId() == mailId) {
                _allMail.remove(mail);
                break;
            }
        }
    }

    private void loadMail() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM mail");
            rs = pstm.executeQuery();
            while (rs.next()) {
                final L1Mail mail = new L1Mail();
                mail.setId(rs.getInt("id"));
                mail.setType(rs.getInt("type"));
                mail.setSenderName(rs.getString("sender"));
                mail.setReceiverName(rs.getString("receiver"));
                mail.setDate(rs.getString("date"));
                mail.setReadStatus(rs.getInt("read_status"));
                mail.setSubject(rs.getBytes("subject"));
                mail.setContent(rs.getBytes("content"));

                _allMail.add(mail);
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, "创建邮件表时出现错误", e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    /**
     * 设置邮件类型
     * 
     * @param mailId
     * @param type
     */
    public void setMailType(final int mailId, final int type) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            rs = con.createStatement().executeQuery(
                    "SELECT * FROM mail WHERE id=" + mailId);
            if ((rs != null) && rs.next()) {
                pstm = con.prepareStatement("UPDATE mail SET type=? WHERE id="
                        + mailId);
                pstm.setInt(1, type);
                pstm.execute();

                this.changeMailType(mailId, type);
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
     * 设定阅读状态
     * 
     * @param mailId
     */
    public void setReadStatus(final int mailId) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            rs = con.createStatement().executeQuery(
                    "SELECT * FROM mail WHERE id=" + mailId);
            if ((rs != null) && rs.next()) {
                pstm = con
                        .prepareStatement("UPDATE mail SET read_status=? WHERE id="
                                + mailId);
                pstm.setInt(1, 1);
                pstm.execute();

                this.changeMailStatus(mailId);
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
     * 写邮件
     * 
     * @param type
     * @param receiver
     * @param writer
     * @param text
     */
    public void writeMail(final int type, final String receiver,
            final L1PcInstance writer, final byte[] text) {
        final int readStatus = 0;

        final SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
        final TimeZone tz = TimeZone.getTimeZone(Config.TIME_ZONE);
        final String date = sdf.format(Calendar.getInstance(tz).getTime());

        // subjectとcontent分离(0x00 0x00)寻找位置
        int spacePosition1 = 0;
        int spacePosition2 = 0;
        for (int i = 0; i < text.length; i += 2) {
            if ((text[i] == 0) && (text[i + 1] == 0)) {
                if (spacePosition1 == 0) {
                    spacePosition1 = i;
                } else if ((spacePosition1 != 0) && (spacePosition2 == 0)) {
                    spacePosition2 = i;
                    break;
                }
            }
        }

        // 写入mail(邮件)表
        final int subjectLength = spacePosition1 + 2;
        int contentLength = spacePosition2 - spacePosition1;
        if (contentLength <= 0) {
            contentLength = 1;
        }
        final byte[] subject = new byte[subjectLength];
        final byte[] content = new byte[contentLength];
        System.arraycopy(text, 0, subject, 0, subjectLength);
        System.arraycopy(text, subjectLength, content, 0, contentLength);

        Connection con = null;
        PreparedStatement pstm2 = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm2 = con.prepareStatement("INSERT INTO mail SET "
                    + "id=?, type=?, sender=?, receiver=?,"
                    + " date=?, read_status=?, subject=?, content=?");
            final int id = IdFactory.getInstance().nextId();
            pstm2.setInt(1, id);
            pstm2.setInt(2, type);
            pstm2.setString(3, writer.getName());
            pstm2.setString(4, receiver);
            pstm2.setString(5, date);
            pstm2.setInt(6, readStatus);
            pstm2.setBytes(7, subject);
            pstm2.setBytes(8, content);
            pstm2.execute();

            final L1Mail mail = new L1Mail();
            mail.setId(id);
            mail.setType(type);
            mail.setSenderName(writer.getName());
            mail.setReceiverName(receiver);
            mail.setDate(date);
            mail.setSubject(subject);
            mail.setContent(content);
            mail.setReadStatus(readStatus);

            _allMail.add(mail);
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm2);
            SQLUtil.close(con);
        }
    }

}
