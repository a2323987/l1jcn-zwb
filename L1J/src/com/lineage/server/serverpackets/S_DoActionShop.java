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
 * 物件动作种类 (短时间) - 个人商店
 */
public class S_DoActionShop extends ServerBasePacket {

    /**
     * 物件动作种类 (短时间) - 个人商店
     * 
     * @param object
     * @param gfxid
     * @param message
     */
    public S_DoActionShop(final int object, final int gfxid,
            final byte[] message) {
        this.writeC(Opcodes.S_OPCODE_DOACTIONGFX);
        this.writeD(object);
        this.writeC(gfxid); // 动作编号
        this.writeByte(message); // 文字内容
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }
}
