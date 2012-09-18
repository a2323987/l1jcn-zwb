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
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * @category 初始能力加成, 用于查看创角色时初始能力的增幅
 */
public class S_InitialAbilityGrowth extends ServerBasePacket {
    public S_InitialAbilityGrowth(final L1PcInstance pc) {

        final int Str = pc.getOriginalStr();// 力量
        final int Dex = pc.getOriginalDex();// 敏捷
        final int Con = pc.getOriginalCon();// 体质
        final int Wis = pc.getOriginalWis();// 精神
        final int Cha = pc.getOriginalCha();// 魅力
        final int Int = pc.getOriginalInt();// 智力
        final int[] growth = new int[6];

        // 王族
        if (pc.isCrown()) {
            final int Initial[] = { 13, 10, 10, 11, 13, 10 };
            growth[0] = Str - Initial[0];
            growth[1] = Dex - Initial[1];
            growth[2] = Con - Initial[2];
            growth[3] = Wis - Initial[3];
            growth[4] = Cha - Initial[4];
            growth[5] = Int - Initial[5];
        }
        // 法师
        if (pc.isWizard()) {
            final int[] Initial = { 8, 7, 12, 12, 8, 12 };
            growth[0] = Str - Initial[0];
            growth[1] = Dex - Initial[1];
            growth[2] = Con - Initial[2];
            growth[3] = Wis - Initial[3];
            growth[4] = Cha - Initial[4];
            growth[5] = Int - Initial[5];
        }
        // 骑士
        if (pc.isKnight()) {
            final int[] Initial = { 16, 12, 14, 9, 12, 8 };
            growth[0] = Str - Initial[0];
            growth[1] = Dex - Initial[1];
            growth[2] = Con - Initial[2];
            growth[3] = Wis - Initial[3];
            growth[4] = Cha - Initial[4];
            growth[5] = Int - Initial[5];
        }
        // 妖精
        if (pc.isElf()) {
            final int[] Initial = { 11, 12, 12, 12, 9, 12 };
            growth[0] = Str - Initial[0];
            growth[1] = Dex - Initial[1];
            growth[2] = Con - Initial[2];
            growth[3] = Wis - Initial[3];
            growth[4] = Cha - Initial[4];
            growth[5] = Int - Initial[5];
        }
        // 黑妖
        if (pc.isDarkelf()) {
            final int[] Initial = { 12, 15, 8, 10, 9, 11 };
            growth[0] = Str - Initial[0];
            growth[1] = Dex - Initial[1];
            growth[2] = Con - Initial[2];
            growth[3] = Wis - Initial[3];
            growth[4] = Cha - Initial[4];
            growth[5] = Int - Initial[5];
        }
        // 龙骑士
        if (pc.isDragonKnight()) {
            final int[] Initial = { 13, 11, 14, 12, 8, 11 };
            growth[0] = Str - Initial[0];
            growth[1] = Dex - Initial[1];
            growth[2] = Con - Initial[2];
            growth[3] = Wis - Initial[3];
            growth[4] = Cha - Initial[4];
            growth[5] = Int - Initial[5];
        }
        // 幻术师
        if (pc.isIllusionist()) {
            final int[] Initial = { 11, 10, 12, 12, 8, 12 };
            growth[0] = Str - Initial[0];
            growth[1] = Dex - Initial[1];
            growth[2] = Con - Initial[2];
            growth[3] = Wis - Initial[3];
            growth[4] = Cha - Initial[4];
            growth[5] = Int - Initial[5];
        }

        this.buildPacket(pc, growth[0], growth[1], growth[2], growth[3],
                growth[4], growth[5]);
    }

    /**
     * @param pc
     *            角色
     * @param Str
     *            力量
     * @param Dex
     *            敏捷
     * @param Con
     *            体质
     * @param Wis
     *            精神
     * @param Cha
     *            魅力
     * @param Int
     *            智力
     */
    private void buildPacket(final L1PcInstance pc, final int Str,
            final int Dex, final int Con, final int Wis, final int Cha,
            final int Int) {
        final int write1 = (Int * 16) + Str;
        final int write2 = (Dex * 16) + Wis;
        final int write3 = (Cha * 16) + Con;
        this.writeC(Opcodes.S_OPCODE_CHARRESET);
        this.writeC(0x04);
        this.writeC(write1);// 智力&力量
        this.writeC(write2);// 敏捷&精神
        this.writeC(write3);// 魅力&体质
        this.writeC(0x00);
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }
}
