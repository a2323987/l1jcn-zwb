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
 * 增加魔法进魔法清单的封包
 */
public class S_AddSkill extends ServerBasePacket {

    private static final String S_ADD_SKILL = "[S] S_AddSkill";

    private byte[] _byte = null;

    public S_AddSkill(final int level, final int id) {
        final int ids[] = new int[28];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = 0;
        }
        ids[level] = id;

        final boolean hasLevel5to8 = 0 < (ids[4] + ids[5] + ids[6] + ids[7]);
        final boolean hasLevel9to10 = 0 < (ids[8] + ids[9]);

        this.writeC(Opcodes.S_OPCODE_ADDSKILL);
        if (hasLevel5to8 && !hasLevel9to10) {
            this.writeC(50);
        } else if (hasLevel9to10) {
            this.writeC(100);
        } else {
            this.writeC(32);
        }
        for (final int i : ids) {
            this.writeC(i);
        }
        this.writeD(0);
        this.writeD(0);
    }

    public S_AddSkill(final int level1, final int level2, final int level3,
            final int level4, final int level5, final int level6,
            final int level7, final int level8, final int level9,
            final int level10, final int knight, final int l2, final int de1,
            final int de2, final int royal, final int l3, final int elf1,
            final int elf2, final int elf3, final int elf4, final int elf5,
            final int elf6, final int k5, final int l5, final int m5,
            final int n5, final int o5, final int p5) {
        final int i6 = level5 + level6 + level7 + level8;
        final int j6 = level9 + level10;
        this.writeC(Opcodes.S_OPCODE_ADDSKILL);
        if ((i6 > 0) && (j6 == 0)) {
            this.writeC(50);
        } else if (j6 > 0) {
            this.writeC(100);
        } else {
            this.writeC(32);
        }
        this.writeC(level1);
        this.writeC(level2);
        this.writeC(level3);
        this.writeC(level4);
        this.writeC(level5);
        this.writeC(level6);
        this.writeC(level7);
        this.writeC(level8);
        this.writeC(level9);
        this.writeC(level10);
        this.writeC(knight);
        this.writeC(l2);
        this.writeC(de1);
        this.writeC(de2);
        this.writeC(royal);
        this.writeC(l3);
        this.writeC(elf1);
        this.writeC(elf2);
        this.writeC(elf3);
        this.writeC(elf4);
        this.writeC(elf5);
        this.writeC(elf6);
        this.writeC(k5);
        this.writeC(l5);
        this.writeC(m5);
        this.writeC(n5);
        this.writeC(o5);
        this.writeC(p5);
        this.writeD(0);
        this.writeD(0);
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
        return S_ADD_SKILL;
    }

}
