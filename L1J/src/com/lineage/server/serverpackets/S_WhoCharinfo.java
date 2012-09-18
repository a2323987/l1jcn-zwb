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

import java.util.logging.Logger;

import com.lineage.server.Opcodes;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 伺服器讯息(行数/行数,附加字串)
 */
public class S_WhoCharinfo extends ServerBasePacket {

    private static final String S_WHO_CHARINFO = "[S] S_WhoCharinfo";

    private static Logger _log = Logger
            .getLogger(S_WhoCharinfo.class.getName());

    private byte[] _byte = null;

    /**
     * 伺服器讯息(行数/行数,附加字串)
     * 
     * @param pc
     */
    public S_WhoCharinfo(final L1Character cha) {
        _log.fine("Who charpack for : " + cha.getName());

        String lawfulness = "";
        final int lawful = cha.getLawful();
        if (lawful < 0) {
            lawfulness = "(Chaotic)"; // 邪恶者
        } else if ((lawful >= 0) && (lawful < 500)) {
            lawfulness = "(Neutral)"; // 中立者
        } else if (lawful >= 500) {
            lawfulness = "(Lawful)"; // 正义者
        }

        this.writeC(Opcodes.S_OPCODE_SERVERMSG);
        // this.writeC(0x08);
        this.writeH(0x00a6); // 166
        this.writeC(0x01);

        String title = "";
        String clan = "";

        if (cha.getTitle().equalsIgnoreCase("") == false) {
            title = cha.getTitle() + " ";
        }

        String name = "";

        if (cha instanceof L1PcInstance) {
            final L1PcInstance pc = (L1PcInstance) cha;
            name = pc.getName();
            if (pc.getClanid() > 0) {
                clan = "[" + pc.getClanname() + "]";
            }
        }
        this.writeS(title + name + " " + lawfulness + " " + clan);
        // writeD(0x80157FE4);
        // this.writeD(0);
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this._bao.toByteArray();
        }
        return this._byte;
    }

    @Override
    public String getType() {
        return S_WHO_CHARINFO;
    }
}
