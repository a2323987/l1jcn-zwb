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

import com.lineage.server.serverpackets.S_Board;
import com.lineage.server.serverpackets.S_BoardRead;
import com.lineage.server.templates.L1Npc;

/**
 * 布告栏列表控制项
 */
public class L1BoardInstance extends L1NpcInstance {

    private static final long serialVersionUID = 1L;

    public L1BoardInstance(final L1Npc template) {
        super(template);
    }

    @Override
    public void onAction(final L1PcInstance player) {
        player.sendPackets(new S_Board(this.getId()));
    }

    @Override
    public void onAction(final L1PcInstance player, final int number) {
        player.sendPackets(new S_Board(this.getId(), number));
    }

    public void onActionRead(final L1PcInstance player, final int number) {
        player.sendPackets(new S_BoardRead(number));
    }
}
