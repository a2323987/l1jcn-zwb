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
package com.lineage.server.model.trap;

import com.lineage.server.model.L1Location;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.storage.TrapStorage;

/**
 * 传送陷阱
 */
public class L1TeleportTrap extends L1Trap {

    private final L1Location _loc;

    public L1TeleportTrap(final TrapStorage storage) {
        super(storage);

        final int x = storage.getInt("teleportX");
        final int y = storage.getInt("teleportY");
        final int mapId = storage.getInt("teleportMapId");
        this._loc = new L1Location(x, y, mapId);
    }

    @Override
    public void onTrod(final L1PcInstance trodFrom, final L1Object trapObj) {
        this.sendEffect(trapObj);

        L1Teleport.teleport(trodFrom, this._loc.getX(), this._loc.getY(),
                (short) this._loc.getMapId(), 5, true);
    }
}
