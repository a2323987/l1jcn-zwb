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

import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;

/**
 * GM指令：描述
 */
public class L1Describe implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1Describe();
    }

    private L1Describe() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final StringBuilder msg = new StringBuilder();
            pc.sendPackets(new S_SystemMessage("-- describe: " + pc.getName()
                    + " --"));
            final int hpr = pc.getHpr() + pc.getInventory().hpRegenPerTick();
            final int mpr = pc.getMpr() + pc.getInventory().mpRegenPerTick();
            msg.append("Dmg: +" + pc.getDmgup() + " / ");
            msg.append("Hit: +" + pc.getHitup() + " / ");
            msg.append("MR: " + pc.getMr() + " / ");
            msg.append("HPR: " + hpr + " / ");
            msg.append("MPR: " + mpr + " / ");
            msg.append("Karma: " + pc.getKarma() + " / ");
            msg.append("Item: " + pc.getInventory().getSize() + " / ");
            pc.sendPackets(new S_SystemMessage(msg.toString()));
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage(cmdName + " 指令错误"));
        }
    }
}
