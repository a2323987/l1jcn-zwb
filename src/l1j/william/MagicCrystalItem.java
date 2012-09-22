/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */

package l1j.william;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.utils.SQLUtil;
import l1j.william.L1WilliamMagicCrystalItem;

public class MagicCrystalItem {
	private static Logger _log = Logger.getLogger(MagicCrystalItem.class
			.getName());

	private static MagicCrystalItem _instance;

	private final HashMap<Integer, L1WilliamMagicCrystalItem> _itemIdIndex
			= new HashMap<Integer, L1WilliamMagicCrystalItem>();

	public static MagicCrystalItem getInstance() {
		if (_instance == null) {
			_instance = new MagicCrystalItem();
		}
		return _instance;
	}

	private MagicCrystalItem() {
		loadMagicCrystalItem();
	}

	private void loadMagicCrystalItem() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {

			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM william_magic_crysta");
			rs = pstm.executeQuery();
			fillMagicCrystalItem(rs);
		} catch (SQLException e) {
			_log.log(Level.SEVERE, "error while creating william_magic_crysta table",
					e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	private void fillMagicCrystalItem(ResultSet rs) throws SQLException {
		while (rs.next()) {
			int itemId = rs.getInt("item_id");
			int min_count = rs.getInt("min_count");
			int max_count = rs.getInt("max_count");
			
			L1WilliamMagicCrystalItem MagicCrystal_Item = new L1WilliamMagicCrystalItem(itemId, min_count, max_count);
			_itemIdIndex.put(itemId, MagicCrystal_Item);
		}
	}

	public L1WilliamMagicCrystalItem getTemplate(int itemId) {
		return _itemIdIndex.get(itemId);
	}
}
