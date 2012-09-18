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

import static com.lineage.server.Opcodes.S_OPCODE_LOGINTOGAME;

/**
 * 由3.0的S_Unkown1改名 正式命名为LoginGame Server OP NO: 54 0000: 36 03 c9 ea a5 c4 f2
 * 1c
 */
public class S_LoginGame extends ServerBasePacket {
    public S_LoginGame() {
        /*
         * 【Server】 id:41 size:8 time:1314325723125 0000: 29 03 00 ac c2 7c 00
         * c1
         */
        this.writeC(S_OPCODE_LOGINTOGAME);
        this.writeC(0x03);
        /*
         * this.writeC(0x00); this.writeC(0xac); this.writeC(0xc2);
         * this.writeC(0x7c); this.writeC(0x00); this.writeC(0xc1);
         */
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }
}
