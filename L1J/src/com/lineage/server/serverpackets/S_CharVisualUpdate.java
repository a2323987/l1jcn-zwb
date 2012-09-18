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
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 物件动作种类 (长时间)
 */
public class S_CharVisualUpdate extends ServerBasePacket {

    private static final String _S__0B_S_CharVisualUpdate = "[C] S_CharVisualUpdate";

    /**
     * 物件动作种类 (长时间)
     * 
     * @param cha
     * @param status
     */
    public S_CharVisualUpdate(final L1Character cha, final int status) {
        this.writeC(Opcodes.S_OPCODE_CHARVISUALUPDATE);
        this.writeD(cha.getId());
        this.writeC(status);
        this.writeC(0xff);
        this.writeC(0xff);
    }

    /**
     * 物件动作种类 (长时间)
     * 
     * @param cha
     */
    public S_CharVisualUpdate(final L1PcInstance pc) {
        this.writeC(Opcodes.S_OPCODE_CHARVISUALUPDATE);
        this.writeD(pc.getId());
        this.writeC(pc.getCurrentWeapon());
        this.writeC(0xff);
        this.writeC(0xff);
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }

    @Override
    public String getType() {
        return _S__0B_S_CharVisualUpdate;
    }
}
