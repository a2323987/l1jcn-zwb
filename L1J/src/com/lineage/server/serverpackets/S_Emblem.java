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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.lineage.server.Opcodes;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 血盟徽章
 */
public class S_Emblem extends ServerBasePacket {

    private static final String S_EMBLEM = "[S] S_Emblem";

    public S_Emblem(final int clanid) {
        BufferedInputStream bis = null;
        try {
            final String emblem_file = String.valueOf(clanid);
            final File file = new File("emblem/" + emblem_file);
            if (file.exists()) {
                int data = 0;
                bis = new BufferedInputStream(new FileInputStream(file));
                this.writeC(Opcodes.S_OPCODE_EMBLEM);
                this.writeD(clanid);
                while ((data = bis.read()) != -1) {
                    this.writeP(data);
                }
                this.writeC(0x00); // 3.5C 於盟徽封包末未知的值
                this.writeH(0x0000);
            }
        } catch (final Exception e) {
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (final IOException ignore) {
                    // ignore
                }
            }
        }
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }

    @Override
    public String getType() {
        return S_EMBLEM;
    }
}
