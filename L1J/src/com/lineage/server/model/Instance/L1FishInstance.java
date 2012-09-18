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
package com.lineage.server.model.Instance;

import java.util.Timer;
import java.util.TimerTask;

import com.lineage.server.serverpackets.S_ChangeHeading;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_NPCPack;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.utils.Random;

/**
 * 钓鱼控制项
 */
public class L1FishInstance extends L1NpcInstance {

    private class fishTimer extends TimerTask {

        private final L1FishInstance _fish;

        public fishTimer(final L1FishInstance fish) {
            this._fish = fish;
        }

        @Override
        public void run() {
            if (this._fish != null) {
                this._fish.setHeading(Random.nextInt(8)); // 随机面向
                this._fish.broadcastPacket(new S_ChangeHeading(this._fish)); // 更新面向
                this._fish.broadcastPacket(new S_DoActionGFX(
                        this._fish.getId(), 0)); // 动作
            } else {
                this.cancel();
            }
        }
    }

    private static final long serialVersionUID = 1L;

    private final fishTimer _fishTimer;

    public L1FishInstance(final L1Npc template) {
        super(template);
        this._fishTimer = new fishTimer(this);
        final Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(this._fishTimer, 1000,
                (Random.nextInt(30, 30) * 1000));
    }

    @Override
    public void onPerceive(final L1PcInstance perceivedFrom) {
        perceivedFrom.addKnownObject(this);
        perceivedFrom.sendPackets(new S_NPCPack(this));
    }

}
