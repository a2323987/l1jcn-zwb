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

import java.util.Timer;
import java.util.TimerTask;

import com.lineage.server.model.Instance.L1ItemInstance;

/**
 * 道具所有者定时器
 */
public class L1ItemOwnerTimer extends TimerTask {

    private final L1ItemInstance _item;

    private final int _timeMillis;

    public L1ItemOwnerTimer(final L1ItemInstance item, final int timeMillis) {
        this._item = item;
        this._timeMillis = timeMillis;
    }

    public void begin() {
        final Timer timer = new Timer();
        timer.schedule(this, this._timeMillis);
    }

    @Override
    public void run() {
        this._item.setItemOwnerId(0);
        this.cancel();
    }
}
