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

package l1j.server.server.model.map;

import java.io.IOException;
import java.util.Map;

import l1j.server.Config;

/**
 * 读取地图的 abstract 类别
 */
public abstract class MapReader {
	/**
	 * 取得所有地图与编号的 Mapping (abstract 方法).
	 *
	 * @return Map
	 * @throws IOException
	 */
	public abstract Map<Integer, L1Map> read() throws IOException;

	/**
	 * 从快取地图中读取特定编号的地图 (abstract 方法).
	 *
	 * @param id
	 *            地图编号
	 * @return L1Map
	 * @throws IOException
	 */
	public abstract L1Map read(int id) throws IOException;

	/**
	 * 依照设定档中的设定来选择读取地图的方法(使用V2MapReader 或 快取地图).
	 *
	 * @return MapReader
	 */
	public static MapReader getDefaultReader() {
		if (Config.LOAD_V2_MAP_FILES) {
			return new V2MapReader();
		}
		if (Config.CACHE_MAP_FILES) {
			return new CachedMapReader();
		}
		return new TextMapReader();
	}
}
