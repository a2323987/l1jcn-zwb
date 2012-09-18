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
 * 魔法或物品效果图示 - 勇敢药水类
 */
public class S_SkillBrave extends ServerBasePacket {

    /**
     * 魔法或物品效果图示 - 勇敢药水类
     * 
     * @param i
     * @param j
     * <br>
     *            0:你的情绪恢复到正常。(解除 )<br>
     *            1:从身体的深处感到热血沸腾。(第一阶段勇敢药水)<br>
     *            3:身体内深刻的感觉到充满了森林的活力。(精灵饼干)<br>
     *            4:风之疾走 / 神圣疾走 / 行走加速 / 生命之树果实效果<br>
     *            5:从身体的深处感到热血沸腾。(第二阶段勇敢药水)<br>
     *            6:引发龙之血爆发出來了。<br>
     * @param k
     */
    public S_SkillBrave(final int i, final int j, final int k) {
        this.writeC(Opcodes.S_OPCODE_SKILLBRAVE);
        this.writeD(i);
        this.writeC(j);
        this.writeH(k);
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }
}
