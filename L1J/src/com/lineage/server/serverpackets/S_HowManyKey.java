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
import com.lineage.server.model.Instance.L1NpcInstance;

/**
 * 选取物品数量 (与NPC交换道具数量)
 */
public class S_HowManyKey extends ServerBasePacket {

    /*
     * 【Server】 id:14 size:40 time:1300606757968 0000 0e cc 1e 00 00 2c 01 00 00
     * 01 00 00 00 01 00 00 .....,.......... 0010 00 08 00 00 00 00 00 69 6e 6e
     * 32 00 00 02 00 24 .......inn2....$ 0020 39 35 35 00 33 30 30 00 955.300.
     */

    /**
     * 选取物品数量 (与NPC道具交换 - 附加HTML)
     * 
     * @param objId
     * @param max
     * @param htmlId
     */
    public S_HowManyKey(final L1NpcInstance npc, final int price,
            final int min, final int max, final String htmlId) {
        this.writeC(Opcodes.S_OPCODE_INPUTAMOUNT);
        this.writeD(npc.getId());
        this.writeD(price); // 价钱
        this.writeD(min); // 起始数量
        this.writeD(min); // 起始数量
        this.writeD(max); // 购买上限
        this.writeH(0); // ?
        this.writeS(htmlId); // 对话档档名
        this.writeH(1); // ?
        this.writeH(0x02); // writeS 数量
        this.writeS(npc.getName()); // 显示NPC名称
        this.writeS(String.valueOf(price)); // 显示价钱
    }

    @Override
    public byte[] getContent() throws IOException {
        return this.getBytes();
    }
}
