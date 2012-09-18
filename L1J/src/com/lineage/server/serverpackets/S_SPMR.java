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

import static com.lineage.server.model.skill.L1SkillId.STATUS_WISDOM_POTION;

import com.lineage.server.Opcodes;
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 更新魔防以及魔攻
 */
public class S_SPMR extends ServerBasePacket {

    private static final String S_SPMR = "[S] S_S_SPMR";

    private byte[] _byte = null;

    /** 更新魔防以及魔攻 */
    public S_SPMR(final L1PcInstance pc) {
        this.buildPacket(pc);
    }

    private void buildPacket(final L1PcInstance pc) {
        this.writeC(Opcodes.S_OPCODE_SPMR);
        // 智慧药水增加的SP在S_SkillBrave送信时更新
        if (pc.hasSkillEffect(STATUS_WISDOM_POTION)) {
            this.writeC(pc.getSp() - pc.getTrueSp() - 2); // 装备增加的SP
        } else {
            this.writeC(pc.getSp() - pc.getTrueSp()); // 装备增加的SP
        }
        this.writeH(pc.getTrueMr() - pc.getBaseMr()); // 装备与魔法增加的MR
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
        return S_SPMR;
    }
}
