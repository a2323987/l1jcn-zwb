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
package com.lineage.server.model.skill;

import com.lineage.server.GeneralThreadPool;
import com.lineage.server.model.L1Character;

// Referenced classes of package com.lineage.server.model:
// L1SkillDelay

/**
 * 技能延迟
 */
public class L1SkillDelay {

    /** 技能延迟计时器 */
    static class SkillDelayTimer implements Runnable {
        private final L1Character _cha;

        public SkillDelayTimer(final L1Character cha, final int time) {
            this._cha = cha;
        }

        @Override
        public void run() {
            this.stopDelayTimer();
        }

        public void stopDelayTimer() {
            this._cha.setSkillDelay(false);
        }
    }

    /** 技能的使用 */
    public static void onSkillUse(final L1Character cha, final int time) {
        cha.setSkillDelay(true);
        GeneralThreadPool.getInstance().schedule(
                new SkillDelayTimer(cha, time), time);
    }

    private L1SkillDelay() {
    }

}
