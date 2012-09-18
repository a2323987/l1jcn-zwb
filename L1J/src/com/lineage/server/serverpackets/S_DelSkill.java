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
 * 移除指定的魔法
 */
public class S_DelSkill extends ServerBasePacket {

    /**
     * 移除指定的魔法(技能群) <BR>
     * 
     * @param i
     *            法师技能LV1
     * @param j
     *            法师技能LV2
     * @param k
     *            法师技能LV3
     * @param l
     *            法师技能LV4
     * @param i1
     *            法师技能LV5
     * @param j1
     *            法师技能LV6
     * @param k1
     *            法师技能LV7
     * @param l1
     *            法师技能LV8
     * @param i2
     *            法师技能LV9
     * @param j2
     *            法师技能LV10 <BR>
     * @param k2
     *            骑士技能LV1
     * @param l2
     *            骑士技能LV2 <BR>
     * @param i3
     *            黑暗精灵技能LV1
     * @param j3
     *            黑暗精灵技能LV2 <BR>
     * @param k3
     *            王族技能 <BR>
     * @param l3
     * <BR>
     * @param i4
     *            精灵技能LV1
     * @param j4
     *            精灵技能LV2
     * @param k4
     *            精灵技能LV3
     * @param l4
     *            精灵技能LV4
     * @param i5
     *            精灵技能LV5
     * @param j5
     *            精灵技能LV6 <BR>
     * @param k5
     *            龙骑士技能LV1
     * @param l5
     *            龙骑士技能LV2
     * @param m5
     *            龙骑士技能LV3 <BR>
     * @param n5
     *            幻术师LV1
     * @param o5
     *            幻术师LV2
     * @param p5
     *            幻术师LV3
     */
    public S_DelSkill(final int i, final int j, final int k, final int l,
            final int i1, final int j1, final int k1, final int l1,
            final int i2, final int j2, final int k2, final int l2,
            final int i3, final int j3, final int k3, final int l3,
            final int i4, final int j4, final int k4, final int l4,
            final int i5, final int j5, final int k5, final int l5,
            final int m5, final int n5, final int o5, final int p5) {
        final int i6 = i1 + j1 + k1 + l1;
        final int j6 = i2 + j2;
        this.writeC(Opcodes.S_OPCODE_DELSKILL);
        if ((i6 > 0) && (j6 == 0)) {
            this.writeC(50);
        } else if (j6 > 0) {
            this.writeC(100);
        } else {
            this.writeC(32);
        }
        this.writeC(i);
        this.writeC(j);
        this.writeC(k);
        this.writeC(l);
        this.writeC(i1);
        this.writeC(j1);
        this.writeC(k1);
        this.writeC(l1);
        this.writeC(i2);
        this.writeC(j2);
        this.writeC(k2);
        this.writeC(l2);
        this.writeC(i3);
        this.writeC(j3);
        this.writeC(k3);
        this.writeC(l3);
        this.writeC(i4);
        this.writeC(j4);
        this.writeC(k4);
        this.writeC(l4);
        this.writeC(i5);
        this.writeC(j5);
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
        return this.getBytes();
    }

    @Override
    public String getType() {
        return "[S] S_DelSkill";
    }

}
