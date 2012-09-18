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
package com.lineage.server.model.map;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.utils.PerformanceTimer;

/**
 * 世界地图信息
 */
public class L1WorldMap {

    private static Logger _log = Logger.getLogger(L1WorldMap.class.getName());

    private static L1WorldMap _instance;

    public static L1WorldMap getInstance() {
        if (_instance == null) {
            _instance = new L1WorldMap();
        }
        return _instance;
    }

    /** MAPID MAP信息 */
    private Map<Integer, L1Map> _maps;

    private L1WorldMap() {
        final PerformanceTimer timer = new PerformanceTimer();
        System.out.print("╔》正在读取 Map...");

        try {
            this._maps = MapReader.getDefaultReader().read();
            if (this._maps == null) {
                throw new RuntimeException("地图档案读取失败...");
            }
        } catch (final FileNotFoundException e) {
            System.out.println("提示: 地图档案缺失，请检查330_maps.zip是否尚未解压缩。");
            System.exit(0);
        } catch (final Exception e) {
            // 没有回报
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            System.exit(0);
        }

        System.out.println("完成!\t\t耗时: " + timer.get() + "\t毫秒");
    }

    /**
     * 指定的地图ID 返回给L1Map。
     * 
     * @param mapId
     *            地图ID
     * @return 地图信息、L1Map对象。
     */
    public L1Map getMap(final short mapId) {
        L1Map map = this._maps.get((int) mapId);
        if (map == null) { // 没有地图信息
            map = L1Map.newNull(); // 返回一个没有任何信息的Map。
        }
        return map;
    }
}
