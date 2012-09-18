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

import com.lineage.server.GeneralThreadPool;
import com.lineage.server.WarTimeController;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_PinkName;

// Referenced classes of package com.lineage.server.model:
// L1PinkName

/**
 * 粉名
 */
public class L1PinkName {

    static class PinkNameTimer implements Runnable {

        /** 攻击者 */
        private L1PcInstance _attacker = null;

        /** 粉名计时器 */
        public PinkNameTimer(final L1PcInstance attacker) {
            this._attacker = attacker;
        }

        @Override
        public void run() {
            for (int i = 0; i < 180; i++) {
                try {
                    Thread.sleep(1000);
                } catch (final Exception exception) {
                    break;
                }
                // 死亡、或 对手杀死红名 结束
                if (this._attacker.isDead()) {
                    // setPinkName(false);はL1PcInstance#death()で行う
                    break;
                }

                // 正义值小于零 不粉名
                if (this._attacker.getLawful() < 0) {
                    this._attacker.setPinkName(false);
                    break;
                }
            }
            this.stopPinkName(this._attacker);
        }

        /** 结束粉名 */
        private void stopPinkName(final L1PcInstance attacker) {
            attacker.sendPackets(new S_PinkName(attacker.getId(), 0));
            attacker.broadcastPacket(new S_PinkName(attacker.getId(), 0));
            attacker.setPinkName(false);
        }
    }

    /** 采取行动 */
    public static void onAction(final L1PcInstance pc, final L1Character cha) {
        if ((pc == null) || (cha == null)) {
            return;
        }

        if (!(cha instanceof L1PcInstance)) {
            return;
        }
        final L1PcInstance attacker = (L1PcInstance) cha;
        if (pc.getId() == attacker.getId()) {
            return;
        }
        if (attacker.getFightId() == pc.getId()) {
            return;
        }

        boolean isNowWar = false;
        final int castleId = L1CastleLocation.getCastleIdByArea(pc);
        if (castleId != 0) { // 在旗内
            isNowWar = WarTimeController.getInstance().isNowWar(castleId);
        }

        // pc, attacker全是蓝名
        if ((pc.getLawful() >= 0) && !pc.isPinkName()
                && (attacker.getLawful() >= 0) && !attacker.isPinkName()) {

            // 两方都在这个区域、不是在战争时期的旗内
            if ((pc.getZoneType() == 0) && (attacker.getZoneType() == 0)
                    && (isNowWar == false)) {
                attacker.setPinkName(true);
                attacker.sendPackets(new S_PinkName(attacker.getId(), 180));
                if (!attacker.isGmInvis()) {
                    attacker.broadcastPacket(new S_PinkName(attacker.getId(),
                            180));
                }
                final PinkNameTimer pink = new PinkNameTimer(attacker);
                GeneralThreadPool.getInstance().execute(pink);
            }
        }
    }

    private L1PinkName() {
    }
}
