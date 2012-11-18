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
package l1j.server.server.datatables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.model.shop.L1Shop;
import l1j.server.server.templates.L1ShopItem;
import l1j.server.server.utils.SQLUtil;
import l1j.server.server.utils.collections.Lists;
import l1j.server.server.utils.collections.Maps;
import java.sql.Timestamp; // 道具天数删除系统

public class ShopTable {

	private static final long serialVersionUID = 1L;

	private static Logger _log = Logger.getLogger(ShopTable.class.getName());

	private static ShopTable _instance;

	private final Map<Integer, L1Shop> _allShops = Maps.newMap();

	public static ShopTable getInstance() {
		if (_instance == null) {
			_instance = new ShopTable();
		}
		return _instance;
	}

	private ShopTable() {
		loadShops();
	}

	private List<Integer> enumNpcIds() {
		List<Integer> ids = Lists.newList();

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
		}
		catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		finally {
			SQLUtil.close(rs, pstm, con);
		}
		return ids;
	}

	private L1Shop loadShop(int npcId, ResultSet rs) throws SQLException {
		List<L1ShopItem> sellingList = Lists.newList();
		List<L1ShopItem> purchasingList = Lists.newList();
		while (rs.next()) {
			int itemId = rs.getInt("item_id");
			int sellingPrice = rs.getInt("selling_price");
			int purchasingPrice = rs.getInt("purchasing_price");
			int packCount = rs.getInt("pack_count");
			int gashPrice = rs.getInt("gash_price"); // GASH币商城[提供:liumy]
			int deleteDay = rs.getInt("delete_day"); // 道具天数删除系统(指定天数)
			int EnchantLevel = rs.getInt("EnchantLevel");  //物品等级
			int selling_count = rs.getInt("selling_count");  //物品等级
			int selling_max = rs.getInt("selling_max");  //物品等级
			Timestamp deleteDate = rs.getTimestamp("delete_date"); // 道具天数删除系统(指定日期)
			packCount = packCount == 0 ? 1 : packCount;
			if (0 <= sellingPrice && 0 >= gashPrice) { // GASH币商城[提供:liumy]
				//L1ShopItem item = new L1ShopItem(itemId, sellingPrice,packCount, deleteDay, deleteDate); // 道具天数删除系统
				L1ShopItem item = new L1ShopItem(itemId, sellingPrice,packCount, deleteDay, deleteDate,EnchantLevel,selling_count,selling_max); // 道具等级+几系统
				sellingList.add(item);
			}
			if (0 <= purchasingPrice && 0 >= gashPrice) { // GASH币商城[提供:liumy]
				//L1ShopItem item = new L1ShopItem(itemId, purchasingPrice,packCount, deleteDay, deleteDate); // 道具天数删除系统
				L1ShopItem item = new L1ShopItem(itemId, purchasingPrice,packCount, deleteDay, deleteDate,EnchantLevel,selling_count,selling_max); // 道具等级+几系统
				if(!checkSellPrice(itemId,purchasingPrice*packCount)){					
					purchasingList.add(item);
				}
			}
			// GASH币商城[提供:liumy]
			if (0 <= gashPrice && 0 >= sellingPrice && 0 >= purchasingPrice) {
				//L1ShopItem item = new L1ShopItem(itemId, gashPrice, packCount, deleteDay, deleteDate); // 道具天数删除系统
				L1ShopItem item = new L1ShopItem(itemId, gashPrice,packCount, deleteDay, deleteDate,EnchantLevel,selling_count,selling_max); // 道具等级+几系统
				sellingList.add(item);
			}
			// end
			
		}
		return new L1Shop(npcId, sellingList, purchasingList);
	}
	private boolean checkSellPrice(int itemId, int purchasingPrice) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean checkOK = false;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM shop WHERE item_id=?");
			pstm.setInt(1, itemId);
			rs = pstm.executeQuery();
			while(rs.next()){
				if(rs.getInt("selling_price") > 0 && rs.getInt("selling_price")*rs.getInt("pack_count") < purchasingPrice ){
					checkOK = true;
					System.out.println("NpcId="+rs.getInt("npc_id")+", ItemID="+itemId+", PriceError!!!");
				}
			}		
			rs.close();
		}
		catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		finally {
			SQLUtil.close(rs, pstm, con);
		}
		return checkOK;
	}
	private void loadShops() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM shop WHERE npc_id=? ORDER BY order_id");
			for (int npcId : enumNpcIds()) {
				pstm.setInt(1, npcId);
				rs = pstm.executeQuery();
				L1Shop shop = loadShop(npcId, rs);
				_allShops.put(npcId, shop);
				rs.close();
			}
		}
		catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		finally {
			SQLUtil.close(rs, pstm, con);
		}
	}

	public L1Shop get(int npcId) {
		return _allShops.get(npcId);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getSelling_count(int npcId,int itemId){
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int selling_count = 0;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM shop WHERE npc_id=? and item_id=?");
				pstm.setInt(1, npcId);
				pstm.setInt(2, itemId);
				rs = pstm.executeQuery();
				while(rs.next())
				{
				selling_count = rs.getInt("selling_count");
				}
				rs.close();
		}
		catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		finally {
			SQLUtil.close(rs, pstm, con);
		}
		return selling_count;
	}
	
	public void setSelling_count(int npcId,int itemId,int selling_count){
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
				pstm = con.prepareStatement("Update shop set selling_count=? WHERE npc_id=? and item_id=?");
				pstm.setInt(1, selling_count);
				pstm.setInt(2, npcId);
				pstm.setInt(3, itemId);
				pstm.execute();
		}
		catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		finally {
			SQLUtil.close(null, pstm, con);
		}
	}
}
