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
package com.lineage.server.clientpackets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.L1DatabaseFactory;
import com.lineage.server.ClientThread;
import com.lineage.server.datatables.CharacterTable;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1World;
import com.lineage.server.serverpackets.S_CharAmount;
import com.lineage.server.serverpackets.S_CharPacks;
import com.lineage.server.utils.SQLUtil;

/**
 * 处理收到由客户端传来的角色选择画面封包
 */
public class C_CommonClick {

    private static final String C_COMMON_CLICK = "[C] C_CommonClick";

    private static Logger _log = Logger
            .getLogger(C_CommonClick.class.getName());

    public C_CommonClick(final ClientThread client) {
        this.deleteCharacter(client); // 到达删除期限，删除角色
        final int amountOfChars = client.getAccount().countCharacters();
        client.sendPacket(new S_CharAmount(amountOfChars, client));
        if (amountOfChars > 0) {
            this.sendCharPacks(client);
        }
    }

    /** 删除角色 */
    private void deleteCharacter(final ClientThread client) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            conn = L1DatabaseFactory.getInstance().getConnection();
            pstm = conn
                    .prepareStatement("SELECT * FROM characters WHERE account_name=? ORDER BY objid");
            pstm.setString(1, client.getAccountName());
            rs = pstm.executeQuery();

            while (rs.next()) {
                final String name = rs.getString("char_name");
                final String clanname = rs.getString("Clanname");

                final Timestamp deleteTime = rs.getTimestamp("DeleteTime");
                if (deleteTime != null) {
                    final Calendar cal = Calendar.getInstance();
                    final long checkDeleteTime = ((cal.getTimeInMillis() - deleteTime
                            .getTime()) / 1000) / 3600;
                    if (checkDeleteTime >= 0) {
                        final L1Clan clan = L1World.getInstance().getClan(
                                clanname);
                        if (clan != null) {
                            clan.delMemberName(name);
                        }
                        CharacterTable.getInstance().deleteCharacter(
                                client.getAccountName(), name);
                    }
                }
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(conn);
        }
    }

    public String getType() {
        return C_COMMON_CLICK;
    }

    /** 发送角色封包 */
    private void sendCharPacks(final ClientThread client) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            conn = L1DatabaseFactory.getInstance().getConnection();
            pstm = conn
                    .prepareStatement("SELECT * FROM characters WHERE account_name=? ORDER BY objid");
            pstm.setString(1, client.getAccountName());
            rs = pstm.executeQuery();

            while (rs.next()) {
                final String name = rs.getString("char_name");
                final String clanname = rs.getString("Clanname");
                final int type = rs.getInt("Type");
                final byte sex = rs.getByte("Sex");
                final int lawful = rs.getInt("Lawful");

                int currenthp = rs.getInt("CurHp");
                if (currenthp < 1) {
                    currenthp = 1;
                } else if (currenthp > 32767) {
                    currenthp = 32767;
                }

                int currentmp = rs.getInt("CurMp");
                if (currentmp < 1) {
                    currentmp = 1;
                } else if (currentmp > 32767) {
                    currentmp = 32767;
                }

                int lvl;
                if (Config.CHARACTER_CONFIG_IN_SERVER_SIDE) {
                    lvl = rs.getInt("level");
                    if (lvl < 1) {
                        lvl = 1;
                    } else if (lvl > 127) {
                        lvl = 127;
                    }
                } else {
                    lvl = 1;
                }

                int ac = rs.getInt("Ac");
                if (ac < -211) {
                    ac = -211;
                } else if (ac > 211) { // 貌似不可能达到
                    ac = 211;
                }
                int str = rs.getShort("Str");
                if (str < 1) {
                    str = 1;
                } else if (str > 255) {
                    str = 255;
                }
                int dex = rs.getShort("Dex");
                if (dex < 1) {
                    dex = 1;
                } else if (dex > 255) {
                    dex = 255;
                }
                int con = rs.getShort("Con");
                if (con < 1) {
                    con = 1;
                } else if (con > 255) {
                    con = 255;
                }
                int wis = rs.getShort("Wis");
                if (wis < 1) {
                    wis = 1;
                } else if (wis > 255) {
                    wis = 255;
                }
                int cha = rs.getShort("Cha");
                if (cha < 1) {
                    cha = 1;
                } else if (cha > 255) {
                    cha = 255;
                }
                int intel = rs.getShort("Intel");
                if (intel < 1) {
                    intel = 1;
                } else if (intel > 255) {
                    intel = 255;
                }
                final int accessLevel = rs.getShort("AccessLevel");
                final Timestamp _birthday = rs.getTimestamp("birthday");
                final SimpleDateFormat SimpleDate = new SimpleDateFormat(
                        "yyyyMMdd");
                final int birthday = Integer.parseInt(SimpleDate
                        .format(_birthday.getTime()));

                final S_CharPacks cpk = new S_CharPacks(name, clanname, type,
                        sex, lawful, currenthp, currentmp, ac, lvl, str, dex,
                        con, wis, cha, intel, accessLevel, birthday);

                client.sendPacket(cpk);
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(conn);
        }
    }
}
