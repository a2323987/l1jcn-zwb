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

import static com.lineage.server.model.skill.L1SkillId.GMSTATUS_FINDINVIS;

import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.serverpackets.S_SystemMessage;

public class L1FindInvis implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1FindInvis();
    }

    private L1FindInvis() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        if (arg.equalsIgnoreCase("on")) {
            pc.setSkillEffect(GMSTATUS_FINDINVIS, 0);
            pc.removeAllKnownObjects();
            pc.updateObject();
        } else if (arg.equalsIgnoreCase("off")) {
            pc.removeSkillEffect(GMSTATUS_FINDINVIS);
            for (final L1PcInstance visible : L1World.getInstance()
                    .getVisiblePlayer(pc)) {
                if (visible.isInvisble()) {
                    pc.sendPackets(new S_RemoveObject(visible));
                }
            }
        } else {
            pc.sendPackets(new S_SystemMessage(cmdName + "请输入  on|off 。"));
        }
    }

}
