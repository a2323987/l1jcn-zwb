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

import com.lineage.Config;
import com.lineage.server.Opcodes;

/**
 * 伺服器版本
 */
public class S_ServerVersion extends ServerBasePacket {

    private static final int CLIENT_LANGUAGE = Config.CLIENT_LANGUAGE;

    private static final int uptime = 0x4f1b86c3; // (int)
                                                  // (System.currentTimeMillis()
                                                  // / 1000);

    /*
     * [来源:Server]<位址:17>{长度:32}(时间:1314150068749) 0000: 11 00 38 32 c7 a8 00 a7
     * c6 a8 00 ba 6e cf 77 ad ..82........n.w. 0010: cd a8 00 71 23 53 4e 00 00
     * 03 00 00 00 00 08 00 ...q#SN.........
     */
    /**
     * 伺服器版本
     */
    public S_ServerVersion() {
        this.writeC(Opcodes.S_OPCODE_SERVERVERSION);
        this.writeC(0x00);
        this.writeC(0x02);
        this.writeD(0x00a9b81b); // server verion 3.51C Taiwan Server
        this.writeD(0x00a9b590); // cache verion 3.51C Taiwan Server
        this.writeD(0x77cf6eba); // auth verion 3.51C Taiwan Server
        this.writeD(0x00a93e2d); // npc verion 3.51C Taiwan Server
        this.writeD(uptime);
        this.writeC(0x00); // unknown
        this.writeC(0x00); // unknown
        this.writeC(CLIENT_LANGUAGE); // Country: 0.US 3.Taiwan 4.Janpan 5.China
        // this.writeD(0x00000000);
        // this.writeC(0xae); // unknown
        // this.writeC(0xb2); // unknown
        this.writeC(0x00);
        this.writeC(0x00);
        this.writeC(0x00);
        this.writeC(0x00);
        this.writeC(0x08);
        this.writeC(0x00);
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }
}
