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
package com.lineage.server.serverpackets;

import com.lineage.server.Opcodes;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.utils.IntRange;

/**
 * 体力与最大体力更新
 */
public class S_HPUpdate extends ServerBasePacket {

    private static final IntRange hpRange = new IntRange(1, 32767);

    /**
     * 体力与最大体力更新
     * 
     * @param currentHp
     * @param maxHp
     */
    public S_HPUpdate(final int currentHp, final int maxHp) {
        this.buildPacket(currentHp, maxHp);
    }

    /**
     * 体力与最大体力更新
     * 
     * @param pc
     */
    public S_HPUpdate(final L1PcInstance pc) {
        this.buildPacket(pc.getCurrentHp(), pc.getMaxHp());
    }

    public void buildPacket(final int currentHp, final int maxHp) {
        this.writeC(Opcodes.S_OPCODE_HPUPDATE);
        this.writeH(hpRange.ensure(currentHp));
        this.writeH(hpRange.ensure(maxHp));
        // writeC(0);
        // writeD(GameTimeController.getInstance().getGameTime());
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }
}
