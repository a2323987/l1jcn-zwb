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
 * 效果图示：龙之血痕
 */
public class S_SkillIconBloodstain extends ServerBasePacket {

    private static final String S_SKILL_ICON_BLOODSTAIN = "[S] S_SkillIconBloodstain";

    /**
     * 效果图示：龙之血痕
     * 
     * @param i
     * @param j
     */
    public S_SkillIconBloodstain(final int i, final int j) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(0x64);
        this.writeC(i); // 82:安塔瑞斯的血痕。 85:法利昂的血痕。 88:???的血痕。 91:???的血痕。
        this.writeH(j); // 分
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }

    @Override
    public String getType() {
        return S_SKILL_ICON_BLOODSTAIN;
    }
}
