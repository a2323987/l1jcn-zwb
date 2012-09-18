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

/**
 * 血盟成员清单
 */
public class S_Pledge extends ServerBasePacket {

    private static final String _S_Pledge = "[S] _S_Pledge";

    private byte[] _byte = null;

    public S_Pledge(final String htmlid, final int objid) {
        this.buildPacket(htmlid, objid, 0, "", "", "");
    }

    public S_Pledge(final String htmlid, final int objid,
            final String clanname, final String olmembers) {
        this.buildPacket(htmlid, objid, 1, clanname, olmembers, "");
    }

    public S_Pledge(final String htmlid, final int objid,
            final String clanname, final String olmembers,
            final String allmembers) {
        this.buildPacket(htmlid, objid, 2, clanname, olmembers, allmembers);
    }

    private void buildPacket(final String htmlid, final int objid,
            final int type, final String clanname, final String olmembers,
            final String allmembers) {

        this.writeC(Opcodes.S_OPCODE_SHOWHTML);
        this.writeD(objid);
        this.writeS(htmlid);
        this.writeH(type);
        this.writeH(0x03);
        this.writeS(clanname); // clanname
        this.writeS(olmembers); // clanmember with a space in the end
        this.writeS(allmembers); // all clan members names with a space in the
        // end
        // example: "player1 player2 player3 "
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
        return _S_Pledge;
    }
}
