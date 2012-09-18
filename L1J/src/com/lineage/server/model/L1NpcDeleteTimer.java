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

import com.lineage.server.ActionCodes;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.serverpackets.S_DoActionGFX;

/**
 * NPC删除定时器
 */
public class L1NpcDeleteTimer extends TimerTask {

    private final L1NpcInstance _npc;

    private final int _timeMillis;

    public L1NpcDeleteTimer(final L1NpcInstance npc, final int timeMillis) {
        this._npc = npc;
        this._timeMillis = timeMillis;
    }

    public void begin() {
        final Timer timer = new Timer();
        timer.schedule(this, this._timeMillis);
    }

    @Override
    public void run() {
        // 龙之门扉存在时间到时
        if (this._npc != null) {
            if ((this._npc.getNpcId() == 81273)
                    || (this._npc.getNpcId() == 81274)
                    || (this._npc.getNpcId() == 81275)
                    || (this._npc.getNpcId() == 81276)
                    || (this._npc.getNpcId() == 81277)) {
                if (this._npc.getNpcId() == 81277) { // 隐匿的巨龙谷入口关闭
                    L1DragonSlayer.getInstance().setHiddenDragonValleyStstus(0);
                }
                // 结束屠龙副本
                L1DragonSlayer.getInstance().setPortalPack(
                        this._npc.getPortalNumber(), null);
                L1DragonSlayer.getInstance().endDragonPortal(
                        this._npc.getPortalNumber());
                // 门扉消失动作
                this._npc.setStatus(ActionCodes.ACTION_Die);
                this._npc.broadcastPacket(new S_DoActionGFX(this._npc.getId(),
                        ActionCodes.ACTION_Die));
            }
            this._npc.deleteMe();
            this.cancel();
        }
    }
}
