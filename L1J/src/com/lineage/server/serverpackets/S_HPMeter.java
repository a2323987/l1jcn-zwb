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
import com.lineage.server.model.L1Character;

/**
 * 物件血条
 */
public class S_HPMeter extends ServerBasePacket {

    private static final String _typeString = "[S] S_HPMeter";

    private byte[] _byte = null;

    /**
     * 物件血条
     * 
     * @param objId
     * @param hpRatio
     */
    public S_HPMeter(final int objId, final int hpRatio) {
        this.buildPacket(objId, hpRatio);
    }

    /**
     * 物件血条
     * 
     * @param cha
     */
    public S_HPMeter(final L1Character cha) {
        final int objId = cha.getId();
        int hpRatio = 100;
        if (0 < cha.getMaxHp()) {
            hpRatio = 100 * cha.getCurrentHp() / cha.getMaxHp();
        }

        this.buildPacket(objId, hpRatio);
    }

    private void buildPacket(final int objId, final int hpRatio) {
        this.writeC(Opcodes.S_OPCODE_HPMETER);
        this.writeD(objId);
        this.writeC(hpRatio);
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this._bao.toByteArray();
        }

        return this._byte;
    }

    @Override
    public String getType() {
        return _typeString;
    }
}
