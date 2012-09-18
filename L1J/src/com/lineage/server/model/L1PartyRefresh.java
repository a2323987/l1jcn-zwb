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
package com.lineage.server.model;

import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Party;

/**
 * 刷新组队
 */
public class L1PartyRefresh extends TimerTask {

    private static Logger _log = Logger.getLogger(L1PartyRefresh.class
            .getName());

    private final L1PcInstance _pc;

    public L1PartyRefresh(final L1PcInstance pc) {
        this._pc = pc;
    }

    /**
     * 3.3C 更新队伍封包
     */
    public void fresh() {
        this._pc.sendPackets(new S_Party(110, this._pc));
    }

    @Override
    public void run() {
        try {
            if (this._pc.isDead() || (this._pc.getParty() == null)) {
                this._pc.stopRefreshParty();
                return;
            }
            this.fresh();
        } catch (final Throwable e) {
            this._pc.stopRefreshParty();
            _log.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }
}
