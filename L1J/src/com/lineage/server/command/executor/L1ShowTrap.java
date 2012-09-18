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

import static com.lineage.server.model.skill.L1SkillId.GMSTATUS_SHOWTRAPS;

import com.lineage.server.model.L1Object;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1TrapInstance;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.serverpackets.S_SystemMessage;

public class L1ShowTrap implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1ShowTrap();
    }

    private L1ShowTrap() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        if (arg.equalsIgnoreCase("on")) {
            pc.setSkillEffect(GMSTATUS_SHOWTRAPS, 0);
        } else if (arg.equalsIgnoreCase("off")) {
            pc.removeSkillEffect(GMSTATUS_SHOWTRAPS);

            for (final L1Object obj : pc.getKnownObjects()) {
                if (obj instanceof L1TrapInstance) {
                    pc.removeKnownObject(obj);
                    pc.sendPackets(new S_RemoveObject(obj));
                }
            }
        } else {
            pc.sendPackets(new S_SystemMessage("请输入: " + cmdName + " on|off 。"));
        }
    }
}
