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
 * 血盟战争讯息 (编号,血盟名称,目标血盟名称)
 */
public class S_War extends ServerBasePacket {

    private static final String S_WAR = "[S] S_War";

    private byte[] _byte = null;

    /**
     * 血盟战争讯息 (编号,血盟名称,目标血盟名称)
     * 
     * @param type
     *            编号 <BR>
     *            1 : %0 血盟向 %1血盟宣战。<BR>
     *            2 : %0 血盟向 %1 血盟投降了。<BR>
     *            3 : %0 血盟与 %1血盟之间的战争结束了。<BR>
     *            4 : %0 血盟赢了对 %1 血盟的战争。<BR>
     *            6 : %0 血盟与 %1血盟成为同盟关系。<BR>
     *            7 : 毁掉%0 血盟与 %1血盟之间的同盟。<BR>
     *            8 : \f1目前你的血盟与 %0 血盟交战当中。<BR>
     * @param clan_name1
     *            血盟名称
     * @param clan_name2
     *            目标血盟名称
     */
    public S_War(final int type, final String clan_name1,
            final String clan_name2) {
        this.buildPacket(type, clan_name1, clan_name2);
    }

    private void buildPacket(final int type, final String clan_name1,
            final String clan_name2) {
        // 1 : _血盟が_血盟に宣戦布告しました。
        // 2 : _血盟が_血盟に降伏しました。
        // 3 : _血盟と_血盟との戦争が終結しました。
        // 4 : _血盟が_血盟との戦争で勝利しました。
        // 6 : _血盟と_血盟が同盟を結びました。
        // 7 : _血盟と_血盟との同盟関係が解除されました。
        // 8 : あなたの血盟が現在_血盟と交戦中です。

        this.writeC(Opcodes.S_OPCODE_WAR);
        this.writeC(type);
        this.writeS(clan_name1);
        this.writeS(clan_name2);
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
        return S_WAR;
    }
}
