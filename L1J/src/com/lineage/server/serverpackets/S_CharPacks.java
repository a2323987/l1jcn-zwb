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
 * 角色资讯
 */
public class S_CharPacks extends ServerBasePacket {

    private static final String S_CHAR_PACKS = "[S] S_CharPacks";

    /**
     * 角色资讯
     * 
     * @param name
     * @param clanName
     * @param type
     * @param sex
     * @param lawful
     * @param hp
     * @param mp
     * @param ac
     * @param lv
     * @param str
     * @param dex
     * @param con
     * @param wis
     * @param cha
     * @param intel
     * @param accessLevel
     * @param birthday
     */
    public S_CharPacks(final String name, final String clanName,
            final int type, final int sex, final int lawful, final int hp,
            final int mp, final int ac, final int lv, final int str,
            final int dex, final int con, final int wis, final int cha,
            final int intel, final int accessLevel, final int birthday) {
        this.writeC(Opcodes.S_OPCODE_CHARLIST);
        this.writeS(name);
        this.writeS(clanName);
        this.writeC(type);
        this.writeC(sex);
        this.writeH(lawful);
        this.writeH(hp);
        this.writeH(mp);
        this.writeC(ac);
        this.writeC(lv);
        this.writeC(str);
        this.writeC(dex);
        this.writeC(con);
        this.writeC(wis);
        this.writeC(cha);
        this.writeC(intel);
        this.writeC(0);// is Administrator
        this.writeD(birthday);
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }

    @Override
    public String getType() {
        return S_CHAR_PACKS;
    }
}
