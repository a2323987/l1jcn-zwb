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
package com.lineage.server.command.executor;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.map.L1WorldMap;
import com.lineage.server.serverpackets.S_SystemMessage;

public class L1Tile implements L1CommandExecutor {
    private static Logger _log = Logger.getLogger(L1Tile.class.getName());

    public static L1CommandExecutor getInstance() {
        return new L1Tile();
    }

    private L1Tile() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final int locX = pc.getX();
            final int locY = pc.getY();
            final short mapId = pc.getMapId();
            final int tile0 = L1WorldMap.getInstance().getMap(mapId)
                    .getOriginalTile(locX, locY - 1);
            final int tile1 = L1WorldMap.getInstance().getMap(mapId)
                    .getOriginalTile(locX + 1, locY - 1);
            final int tile2 = L1WorldMap.getInstance().getMap(mapId)
                    .getOriginalTile(locX + 1, locY);
            final int tile3 = L1WorldMap.getInstance().getMap(mapId)
                    .getOriginalTile(locX + 1, locY + 1);
            final int tile4 = L1WorldMap.getInstance().getMap(mapId)
                    .getOriginalTile(locX, locY + 1);
            final int tile5 = L1WorldMap.getInstance().getMap(mapId)
                    .getOriginalTile(locX - 1, locY + 1);
            final int tile6 = L1WorldMap.getInstance().getMap(mapId)
                    .getOriginalTile(locX - 1, locY);
            final int tile7 = L1WorldMap.getInstance().getMap(mapId)
                    .getOriginalTile(locX - 1, locY - 1);
            final String msg = String.format(
                    "0:%d 1:%d 2:%d 3:%d 4:%d 5:%d 6:%d 7:%d", tile0, tile1,
                    tile2, tile3, tile4, tile5, tile6, tile7);
            pc.sendPackets(new S_SystemMessage(msg));
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }
}
