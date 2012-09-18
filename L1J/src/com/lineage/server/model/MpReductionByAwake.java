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

/**
 * 觉醒的MP消耗
 */
public class MpReductionByAwake extends TimerTask {

    private static Logger _log = Logger.getLogger(MpReductionByAwake.class
            .getName());

    private final L1PcInstance _pc;

    public MpReductionByAwake(final L1PcInstance pc) {
        this._pc = pc;
    }

    public void decreaseMp() {
        int newMp = this._pc.getCurrentMp() - 6; // 当前MP每四秒-6
        if (newMp < 0) {
            newMp = 0;
            L1Awake.stop(this._pc);
        }
        this._pc.setCurrentMp(newMp);
    }

    @Override
    public void run() {
        try {
            if (this._pc.isDead()) {
                return;
            }
            this.decreaseMp();
        } catch (final Throwable e) {
            _log.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }

}
