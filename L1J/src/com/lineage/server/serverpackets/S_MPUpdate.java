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

/**
 * 魔力与最大魔力更新
 */
public class S_MPUpdate extends ServerBasePacket {

    /**
     * 魔力与最大魔力更新
     * 
     * @param currentmp
     * @param maxmp
     */
    public S_MPUpdate(final int currentmp, final int maxmp) {
        this.writeC(Opcodes.S_OPCODE_MPUPDATE);

        if (currentmp < 0) {
            this.writeH(0);
        } else if (currentmp > 32767) {
            this.writeH(32767);
        } else {
            this.writeH(currentmp);
        }

        if (maxmp < 1) {
            this.writeH(1);
        } else if (maxmp > 32767) {
            this.writeH(32767);
        } else {
            this.writeH(maxmp);
        }

        // writeH(currentmp);
        // writeH(maxmp);
        // writeC(0);
        // writeD(GameTimeController.getInstance().getGameTime());
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }
}
