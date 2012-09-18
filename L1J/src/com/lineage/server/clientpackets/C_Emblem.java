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

import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.ClientThread;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Emblem;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来上传盟徽的封包
 */
public class C_Emblem extends ClientBasePacket {

    private static final String C_EMBLEM = "[C] C_Emblem";
    private static Logger _log = Logger.getLogger(C_Emblem.class.getName());

    public C_Emblem(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);

        final L1PcInstance player = clientthread.getActiveChar();
        if (player.getClanid() != 0) {
            final String emblem_file = String.valueOf(player.getClanid());

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream("emblem/" + emblem_file);
                for (short cnt = 0; cnt < 384; cnt++) {
                    fos.write(this.readC());
                }
            } catch (final Exception e) {
                _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                throw e;
            } finally {
                if (null != fos) {
                    fos.close();
                }
                fos = null;
            }
            player.sendPackets(new S_Emblem(player.getClanid()));
            // player.broadcastPacket(new S_Emblem(player.getClanid()));
            L1World.getInstance().broadcastPacketToAll(
                    new S_Emblem(player.getClanid()));
        }
    }

    @Override
    public String getType() {
        return C_EMBLEM;
    }
}
