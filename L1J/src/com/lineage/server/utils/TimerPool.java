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
package com.lineage.server.utils;

import java.util.Timer;

/**
 * 定时器池
 */
public class TimerPool {

    private final Timer _timers[];
    private final int _numOfTimers;
    private int _pointer = 0;

    /**
     * 定时器池
     * 
     * @param numOfTimers
     */
    public TimerPool(final int numOfTimers) {
        this._timers = new Timer[numOfTimers];
        for (int i = 0; i < numOfTimers; i++) {
            this._timers[i] = new Timer();
        }
        this._numOfTimers = numOfTimers;
    }

    /**
     * 取得定时器池
     * 
     * @return
     */
    public synchronized Timer getTimer() {
        if (this._numOfTimers <= this._pointer) {
            this._pointer = 0;
        }
        return this._timers[this._pointer++];
    }
}
