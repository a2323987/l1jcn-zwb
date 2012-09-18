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
package com.lineage.server.clientpackets;

import com.lineage.server.ClientThread;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SkillBuy;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来买魔法的封包
 */
public class C_SkillBuy extends ClientBasePacket {

    private static final String C_SKILL_BUY = "[C] C_SkillBuy";

    public C_SkillBuy(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);

        final int i = this.readD();

        final L1PcInstance pc = clientthread.getActiveChar();
        if (pc.isGhost()) {
            return;
        }
        pc.sendPackets(new S_SkillBuy(i, pc));
        /*
         * int type = player.get_type(); int lvl = player.get_level();
         * switch(type) { case 0: // 君主 if(lvl >= 10) { player.sendPackets(new
         * S_SkillBuy(i, player)); } break; case 1: // ナイト if(lvl >= 50) {
         * player.sendPackets(new S_SkillBuy(i, player)); } break; case 2: //
         * エルフ if(lvl >= 8) { player.sendPackets(new S_SkillBuy(i, player)); }
         * break; case 3: // WIZ if(lvl >= 4) { player.sendPackets(new
         * S_SkillBuy(i, player)); } break; case 4: //DE if(lvl >= 12) {
         * player.sendPackets(new S_SkillBuy(i, player)); } break; default:
         * break; }
         */
    }

    @Override
    public String getType() {
        return C_SKILL_BUY;
    }

}
