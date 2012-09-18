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

public class L1Loc implements L1CommandExecutor {
    private static Logger _log = Logger.getLogger(L1Loc.class.getName());

    public static L1CommandExecutor getInstance() {
        return new L1Loc();
    }

    private L1Loc() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final int locx = pc.getX();
            final int locy = pc.getY();
            final short mapid = pc.getMapId();
            final int gab = L1WorldMap.getInstance().getMap(mapid)
                    .getOriginalTile(locx, locy);
            final String msg = String.format("坐标 (%d, %d, %d) %d", locx, locy,
                    mapid, gab);
            pc.sendPackets(new S_SystemMessage(msg));
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }
}
