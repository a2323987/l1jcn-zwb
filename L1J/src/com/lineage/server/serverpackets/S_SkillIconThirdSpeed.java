package com.lineage.server.serverpackets;

import com.lineage.server.Opcodes;

/**
 * 
 */
public class S_SkillIconThirdSpeed extends ServerBasePacket {

    public S_SkillIconThirdSpeed(final int j) {
        this.writeC(Opcodes.S_OPCODE_SKILLICONGFX);
        this.writeC(0x3c);
        this.writeC(j); // time / 4
        this.writeC(0x8);
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }

    @Override
    public String getType() {
        return "[S] S_SkillIconThirdSpeed";
    }
}
