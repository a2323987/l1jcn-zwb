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
 * 战斗特化
 */
public class S_Fight extends ServerBasePacket {
    private static final String S_FIGHT = "[S] S_Fight";

    private byte[] _byte = null;

    // 开启
    public static final int FLAG_ON = 1;

    // 关闭
    public static final int FLAG_OFF = 0;

    // 正义第一阶段(10000 ~ 19999)
    public static final int TYPE_JUSTICE_LEVEL1 = 0;

    // 正义第二阶段(20000 ~ 29999)
    public static final int TYPE_JUSTICE_LEVEL2 = 1;

    // 正义第三阶段(30000 ~ 32767)
    public static final int TYPE_JUSTICE_LEVEL3 = 2;

    // 邪恶第一阶段(-10000 ~ -19999)
    public static final int TYPE_EVIL_LEVEL1 = 3;

    // 邪恶第二阶段(-20000 ~ -29999)
    public static final int TYPE_EVIL_LEVEL2 = 4;

    // 邪恶第三阶段(-30000 ~ -32768)
    public static final int TYPE_EVIL_LEVEL3 = 5;

    // 遭遇的守护(新手保护)
    public static final int TYPE_ENCOUNTER = 6;

    /**
     * 战斗特化
     * 
     * @param type
     * @param flag
     */
    public S_Fight(final int type, final int flag) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(0x72);
        this.writeD(type);
        this.writeD((flag == FLAG_OFF) ? FLAG_OFF : FLAG_ON);
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
        return S_FIGHT;
    }
}
