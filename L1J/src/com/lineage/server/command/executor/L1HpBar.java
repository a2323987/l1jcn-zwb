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
package com.lineage.server.command.executor;

import static com.lineage.server.model.skill.L1SkillId.GMSTATUS_HPBAR;

import com.lineage.server.model.L1Object;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.serverpackets.S_HPMeter;
import com.lineage.server.serverpackets.S_SystemMessage;

public class L1HpBar implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1HpBar();
    }

    public static boolean isHpBarTarget(final L1Object obj) {
        if (obj instanceof L1MonsterInstance) {
            return true;
        }
        if (obj instanceof L1PcInstance) {
            return true;
        }
        if (obj instanceof L1SummonInstance) {
            return true;
        }
        if (obj instanceof L1PetInstance) {
            return true;
        }
        return false;
    }

    private L1HpBar() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        if (arg.equalsIgnoreCase("on")) {
            pc.setSkillEffect(GMSTATUS_HPBAR, 0);
        } else if (arg.equalsIgnoreCase("off")) {
            pc.removeSkillEffect(GMSTATUS_HPBAR);

            for (final L1Object obj : pc.getKnownObjects()) {
                if (isHpBarTarget(obj)) {
                    pc.sendPackets(new S_HPMeter(obj.getId(), 0xFF));
                }
            }
        } else {
            pc.sendPackets(new S_SystemMessage("請輸入 : " + cmdName + " on|off 。"));
        }
    }
}
