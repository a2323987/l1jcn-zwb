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

public class S_HowManyMake extends ServerBasePacket {
    public S_HowManyMake(final int objId, final int max, final String htmlId) {
        this.writeC(Opcodes.S_OPCODE_INPUTAMOUNT);
        this.writeD(objId);
        this.writeD(0); // ?
        this.writeD(0); // スピンコントロールの初期価格
        this.writeD(0); // 価格の下限
        this.writeD(max); // 価格の上限
        this.writeH(0); // ?
        this.writeS("request");
        this.writeS(htmlId);
    }

    @Override
    public byte[] getContent() throws IOException {
        return this.getBytes();
    }
}
