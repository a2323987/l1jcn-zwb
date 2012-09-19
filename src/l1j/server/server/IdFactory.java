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
package l1j.server.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.utils.SQLUtil;

public class IdFactory {
	private static Logger _log = Logger.getLogger(IdFactory.class.getName());

	private int _curId = 0;

	private int i = 0;

	private boolean isLoadState = false;

	private Object _monitor = new Object();

	private static final int FIRST_ID = 0x80; // id从128开始

	private static IdFactory _instance = new IdFactory();

	private ArrayList<Integer> idList = new ArrayList<Integer>();

	private IdFactory() {
		resetId();
		if (idList.size() > 1) { // 运行结果有两个的时候，才能够进行回收
			while (i + 1 < idList.size()) {
				if ((idList.get(i + 1) - idList.get(i)) > 1) {
					_curId = idList.get(i) + 1;
					break;
				}
				++i;
			}
			if (_curId > 0) {
				_log.info("目前对象使用回收ID: " + _curId);
			} else {
				loadState();
				_log.info("目前对象使用新ID: " + _curId);
			}
		} else {
			loadState();
			_log.info("目前对象使用新ID: " + _curId);
		}
	}

	public static IdFactory getInstance() {
		return _instance;
	}

	public int nextId() {
		synchronized (_monitor) { // 锁住newobject，防止同时另外一个对象产生
			if (isLoadState) {// 启用新id后使用递增
				return ++_curId;
			}

			if (_curId + 1 < idList.get(i + 1)) {
				++_curId;
			} else {
				++i; // i值取下一位
				_curId = 0; // _curId重置
				while (i + 1 < idList.size()) {
					if ((idList.get(i + 1) - idList.get(i)) > 1) {
						_curId = idList.get(i) + 1;
						break;
					}
					++i;
				}
				if (_curId <= 0) {
					loadState();
				}
			}
			return _curId;
		}
	}

	private void resetId() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con
					.prepareStatement("select id from (select id from character_items union all select id from character_teleport union all select id from character_warehouse union all select id from character_elf_warehouse union all select objid as id from characters union all select clan_id as id from clan_data union all select id from clan_warehouse union all select objid as id from pets) t order by id");
			rs = pstm.executeQuery();
			while (rs.next()) {
				idList.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

	private void loadState() {
		if (idList.size() > 0) {
			_curId = idList.get(idList.size() - 1) + 1;
			if (_curId < FIRST_ID)
				_curId = FIRST_ID;
		} else {
			_curId = FIRST_ID;
		}
		isLoadState = true;
	}
}
