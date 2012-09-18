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

/**
 * 送出顯示龍門選單動作
 */
public class S_DragonGate extends ServerBasePacket {
    private static final String S_DRAGON_GATE = "[S] S_DragonGate";

    private byte[] _byte = null;

    public S_DragonGate(final L1PcInstance pc, final boolean[] i) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(0x66); // = 102
        this.writeD(pc.getId());
        // true 可點選，false 不能點選
        this.writeC(i[0] ? 1 : 0); // 安塔瑞斯
        this.writeC(i[1] ? 1 : 0); // 法利昂
        this.writeC(i[2] ? 1 : 0); // 林德拜爾
        this.writeC(i[3] ? 1 : 0); // 巴拉卡斯
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this.getBytes();
        }

        return this._byte;
    }

    @Override
    public String getType() {
        return S_DRAGON_GATE;
    }
}
