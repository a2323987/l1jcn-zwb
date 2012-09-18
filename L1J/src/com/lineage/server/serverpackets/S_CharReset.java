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

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

public class S_CharReset extends ServerBasePacket {

    private static final String S_CHAR_RESET = "[S] S_CharReset";

    private byte[] _byte = null;

    public S_CharReset(final int point) {
        this.writeC(Opcodes.S_OPCODE_CHARRESET);
        this.writeC(0x03);
        this.writeC(point);
    }

    /**
     * 45及腰精進入崇志 [Server] opcode = 43 0000: 2b 01 0f 00 04 00 0a 2d 56法進入崇志
     * [Server] opcode = 43 0000: 2b 01 0c 00 06 00 0a 38
     */
    public S_CharReset(final L1PcInstance pc) {
        this.writeC(Opcodes.S_OPCODE_CHARRESET);
        this.writeC(0x01);
        if (pc.isCrown()) {
            this.writeH(14);
            this.writeH(2);
        } else if (pc.isKnight()) {
            this.writeH(16);
            this.writeH(1);
        } else if (pc.isElf()) {
            this.writeH(15);
            this.writeH(4);
        } else if (pc.isWizard()) {
            this.writeH(12);
            this.writeH(6);
        } else if (pc.isDarkelf()) {
            this.writeH(12);
            this.writeH(3);
        } else if (pc.isDragonKnight()) {
            this.writeH(15);
            this.writeH(4);
        } else if (pc.isIllusionist()) {
            this.writeH(15);
            this.writeH(4);
        }
        this.writeC(0x0a); // AC
        this.writeC(pc.getTempMaxLevel()); // Lv
        /**
         * 0000: 2b 04 60 04 06 01 07 1e 不知道幹麻用的
         */
        // }else if(type == 4){
        // writeC(Opcodes.S_OPCODE_CHARRESET);
        // writeC(4);
        // writeC(0x60);
        // writeC(0x04);
        // writeC(0x09);
        // writeC(0x01);
        // writeC(0x07);
    }

    /**
     * 重置升级能力更新 [Server] opcode = 43 0000: 2b /02/ 01 2d/ 0f 00/ 04 00/ 0a 00
     * /0c 0c 0c 0c 12 09 +..-............
     */
    public S_CharReset(final L1PcInstance pc, final int lv, final int hp,
            final int mp, final int ac, final int str, final int intel,
            final int wis, final int dex, final int con, final int cha) {
        this.writeC(Opcodes.S_OPCODE_CHARRESET);
        this.writeC(0x02);
        this.writeC(lv);
        this.writeC(pc.getTempMaxLevel()); // max lv
        this.writeH(hp);
        this.writeH(mp);
        this.writeH(ac);
        this.writeC(str);
        this.writeC(intel);
        this.writeC(wis);
        this.writeC(dex);
        this.writeC(con);
        this.writeC(cha);
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
        return S_CHAR_RESET;
    }
}
