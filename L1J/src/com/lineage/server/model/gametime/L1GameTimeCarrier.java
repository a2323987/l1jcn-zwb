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
package com.lineage.server.model.gametime;

import java.util.Timer;
import java.util.TimerTask;

import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_GameTime;

/**
 * 传送游戏时间？
 */
public class L1GameTimeCarrier extends TimerTask {

    private static final Timer _timer = new Timer();

    private final L1PcInstance _pc;

    public L1GameTimeCarrier(final L1PcInstance pc) {
        this._pc = pc;
    }

    @Override
    public void run() {
        try {
            if (this._pc.getNetConnection() == null) {
                this.cancel();
                return;
            }

            final int serverTime = L1GameTimeClock.getInstance().currentTime()
                    .getSeconds();
            if (serverTime % 300 == 0) {
                this._pc.sendPackets(new S_GameTime(serverTime));
            }
        } catch (final Exception e) {
            // ignore（忽略）
        }
    }

    public void start() {
        _timer.scheduleAtFixedRate(this, 0, 500);
    }

    public void stop() {
        this.cancel();
    }
}
