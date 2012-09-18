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
 * 产生动画 (物件)
 */
public class S_SkillSound extends ServerBasePacket {

    private static final String S_SKILL_SOUND = "[S] S_SkillSound";

    private byte[] _byte = null;

    /**
     * 产生动画 (物件)
     * 
     * @param objid
     * @param gfxid
     */
    public S_SkillSound(final int objid, final int gfxid) {
        this.buildPacket(objid, gfxid, 0);
    }

    /**
     * 产生动画 (物件)
     * 
     * @param objid
     * @param gfxid
     * @param aid
     */
    public S_SkillSound(final int objid, final int gfxid, final int aid) {
        this.buildPacket(objid, gfxid, aid);
    }

    private void buildPacket(final int objid, final int gfxid, final int aid) {
        // 不使用aid
        this.writeC(Opcodes.S_OPCODE_SKILLSOUNDGFX);
        this.writeD(objid);
        this.writeH(gfxid);
        this.writeH(0);
        this.writeD(0x00000000);
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
        return S_SKILL_SOUND;
    }
}
