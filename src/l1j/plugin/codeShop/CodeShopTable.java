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
package l1j.plugin.codeShop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.utils.SQLUtil;

public class CodeShopTable {
	private static Logger _log = Logger.getLogger(CodeShopTable.class.getName());

	public static CodeShop loadCodeShopTable(String code) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		CodeShop codeShop = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM codeShop WHERE code=?");
			pstm.setString(1, code);
			rs = pstm.executeQuery();
			while (rs.next()) {
				String _code = rs.getString("code"); //兑换序列号
				int _item_id = rs.getInt("item_id");// 兑换得到的道具
				int _count = rs.getInt("count");//得到的数量
				int _enchantlvl = rs.getInt("enchantlvl");//物品等级
				int _attr_enchant_kind = rs.getInt("attr_enchant_kind");//物品属性种类
				int _attr_enchant_level = rs.getInt("attr_enchant_level");//物品属性等级
				int _is_used = rs.getInt("is_used");//是否使用 1为已经兑换，0为未兑换
				String _use_character = rs.getString("use_character");//兑换的人物
				codeShop = new CodeShop(_code,_item_id,_count,_enchantlvl,_attr_enchant_kind,_attr_enchant_level,_is_used,_use_character);
				return codeShop;
			}
		} catch (SQLException e) {
			_log.log(Level.SEVERE,
					"error while creating codeShop table", e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return codeShop;
	}
 
	public static void updateCodeShopTable(String code,String use_character) {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("UPDATE codeShop SET is_used=?,use_character=? WHERE code=?");
			pstm.setInt(1, 1);
			pstm.setString(2, use_character);
			pstm.setString(3, code);
			pstm.execute();
		}
		catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}
	

}
