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

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 魔法效果:中毒
 */
public class S_Poison extends ServerBasePacket {

    private static final String S_POISON = "[S] S_Poison";

    /**
     * 发送一个使角色外观因为中毒而改变的数据包
     * 
     * @param objId
     *            外观变化的角色ID
     * @param type
     *            外观类型 0 = 通常色, 1 = 绿色, 2 = 灰色
     */
    public S_Poison(final int objId, final int type) {
        this.writeC(Opcodes.S_OPCODE_POISON);
        this.writeD(objId);

        if (type == 0) { // 通常
            this.writeC(0);
            this.writeC(0);
        } else if (type == 1) { // 绿色
            this.writeC(1);
            this.writeC(0);
        } else if (type == 2) { // 灰色
            this.writeC(0);
            this.writeC(1);
        } else {
            throw new IllegalArgumentException("非法参数。type = " + type);
        }
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }

    @Override
    public String getType() {
        return S_POISON;
    }
}
