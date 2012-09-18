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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.InflaterInputStream;

import com.lineage.server.datatables.MapsTable;
import com.lineage.server.utils.BinaryInputStream;
import com.lineage.server.utils.FileUtil;
import com.lineage.server.utils.collections.Lists;
import com.lineage.server.utils.collections.Maps;

/**
 * 地图 (v2maps/\d*.txt)读取 (测试用)
 */
public class V2MapReader extends MapReader {

    /** 地图的路径 */
    private static final String MAP_DIR = "./v2maps/";

    /**
     * 传回所有地图的编号
     * 
     * @return ArraryList
     */
    private List<Integer> listMapIds() {
        final List<Integer> ids = Lists.newList();

        final File mapDir = new File(MAP_DIR);
        for (final String name : mapDir.list()) {
            final File mapFile = new File(mapDir, name);
            if (!mapFile.exists()) {
                continue;
            }
            if (!FileUtil.getExtension(mapFile).toLowerCase().equals("md")) {
                continue;
            }
            int id = 0;
            try {
                final String idStr = FileUtil.getNameWithoutExtension(mapFile);
                id = Integer.parseInt(idStr);
            } catch (final NumberFormatException e) {
                continue;
            }
            ids.add(id);
        }
        return ids;
    }

    /**
     * 取得所有地图与编号的 Mapping
     * 
     * @return Map
     * @throws IOException
     */
    @Override
    public Map<Integer, L1Map> read() throws IOException {
        final Map<Integer, L1Map> maps = Maps.newMap();
        for (final int id : this.listMapIds()) {
            maps.put(id, this.read(id));
        }
        return maps;
    }

    /**
     * 从地图中读取特定编号的地图
     * 
     * @param mapId
     *            地图编号
     * @return L1Map
     * @throws IOException
     */
    @Override
    public L1Map read(final int mapId) throws IOException {
        final File file = new File(MAP_DIR + mapId + ".md");
        if (!file.exists()) {
            throw new FileNotFoundException("MapId: " + mapId);
        }

        final BinaryInputStream in = new BinaryInputStream(
                new BufferedInputStream(new InflaterInputStream(
                        new FileInputStream(file))));

        final int id = in.readInt();
        if (mapId != id) {
            throw new FileNotFoundException("MapId: " + mapId);
        }

        final int xLoc = in.readInt();
        final int yLoc = in.readInt();
        final int width = in.readInt();
        final int height = in.readInt();

        final byte[] tiles = new byte[width * height * 2];
        for (int i = 0; i < width * height * 2; i++) {
            tiles[i] = (byte) in.readByte();
        }
        in.close();

        final L1V2Map map = new L1V2Map(id, tiles, xLoc, yLoc, width, height,
                MapsTable.getInstance().isUnderwater(mapId), MapsTable
                        .getInstance().isMarkable(mapId), MapsTable
                        .getInstance().isTeleportable(mapId), MapsTable
                        .getInstance().isEscapable(mapId), MapsTable
                        .getInstance().isUseResurrection(mapId), MapsTable
                        .getInstance().isUsePainwand(mapId), MapsTable
                        .getInstance().isEnabledDeathPenalty(mapId), MapsTable
                        .getInstance().isTakePets(mapId), MapsTable
                        .getInstance().isRecallPets(mapId), MapsTable
                        .getInstance().isUsableItem(mapId), MapsTable
                        .getInstance().isUsableSkill(mapId));
        return map;
    }
}
