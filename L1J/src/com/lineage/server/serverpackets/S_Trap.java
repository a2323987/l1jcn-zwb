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

import java.io.IOException;

import com.lineage.server.Opcodes;
import com.lineage.server.model.Instance.L1TrapInstance;

/**
 * 物件封包 - 陷阱 (GM测试用)
 */
public class S_Trap extends ServerBasePacket {

    /**
     * 物件封包 - 陷阱 (GM测试用)
     * 
     * @param trap
     * @param name
     */
    public S_Trap(final L1TrapInstance trap, final String name) {

        this.writeC(Opcodes.S_OPCODE_DROPITEM);
        this.writeH(trap.getX());
        this.writeH(trap.getY());
        this.writeD(trap.getId());
        this.writeH(7); // adena
        this.writeC(0); // 物件外观属性
        this.writeC(0); // 方向
        this.writeC(0); // 亮度 0:normal, 1:fast, 2:slow
        this.writeC(0); // 速度
        this.writeD(0); // 数量 经验值
        this.writeC(0); //
        this.writeC(0); //
        this.writeS(name); // 名称
        this.writeC(0); //
        this.writeD(0); //
        this.writeD(0); //
        this.writeC(255); //
        this.writeC(0); //
        this.writeC(0); //
        this.writeC(0); //
        this.writeH(65535); //
        // writeD(0x401799a);
        this.writeD(0); //
        this.writeC(8); //
        this.writeC(0); //
    }

    @Override
    public byte[] getContent() throws IOException {
        return this.getBytes();
    }
}
