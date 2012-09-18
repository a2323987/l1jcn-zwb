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

import com.lineage.Config;
import com.lineage.server.model.L1Character;

/**
 * 创造技能计时器
 */
public class L1SkillTimerCreator {

    public static L1SkillTimer create(final L1Character cha, final int skillId,
            final int timeMillis) {
        if (Config.SKILLTIMER_IMPLTYPE == 1) {
            return new L1SkillTimerTimerImpl(cha, skillId, timeMillis);
        } else if (Config.SKILLTIMER_IMPLTYPE == 2) {
            return new L1SkillTimerThreadImpl(cha, skillId, timeMillis);
        }

        // 如果该值不正确、とりあえずTimer
        return new L1SkillTimerTimerImpl(cha, skillId, timeMillis);
    }
}
