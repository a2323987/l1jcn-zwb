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
package com.lineage.server.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * SQL控制项
 */
public class SQLUtil {

    public static SQLException close(final Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (final SQLException e) {
            return e;
        }
        return null;
    }

    public static SQLException close(final ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (final SQLException e) {
            return e;
        }
        return null;
    }

    public static void close(final ResultSet rs, final Statement pstm,
            final Connection con) {
        close(rs);
        close(pstm);
        close(con);
    }

    public static SQLException close(final Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (final SQLException e) {
            return e;
        }
        return null;
    }
}
