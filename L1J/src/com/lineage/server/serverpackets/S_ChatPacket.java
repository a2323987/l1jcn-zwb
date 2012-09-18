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

import com.lineage.server.model.Instance.L1PcInstance;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 聊天频道
 * 
 * @author jrwz
 */
public class S_ChatPacket extends ServerBasePacket {

    private static final String _S__1F_NORMALCHATPACK = "[S] S_ChatPacket";

    private byte[] _byte = null;

    /**
     * 聊天频道
     * 
     * @param pc
     * @param chat
     * @param opcode
     * @param type
     */
    public S_ChatPacket(final L1PcInstance pc, final String chat,
            final int opcode, final int type) {

        switch (type) {
            case 0: // 普通:一般频道
                this.writeC(opcode);
                this.writeC(type);
                if (pc.isInvisble()) {
                    this.writeD(0);
                } else {
                    this.writeD(pc.getId());
                }
                this.writeS(pc.getName() + ": " + chat);
                break;

            case 2: // 大喊频道
                this.writeC(opcode);
                this.writeC(type);
                if (pc.isInvisble()) {
                    this.writeD(0);
                } else {
                    this.writeD(pc.getId());
                }
                this.writeS("<" + pc.getName() + "> " + chat);
                this.writeH(pc.getX());
                this.writeH(pc.getY());
                break;

            case 3: // 所有:全体频道
                this.writeC(opcode);
                this.writeC(type);
                if (pc.isGm() == true) {
                    this.writeS("[******] " + chat);
                } else {
                    this.writeS("[" + pc.getName() + "] " + chat);
                }
                break;

            case 4: // 血盟:血盟频道
                this.writeC(opcode);
                this.writeC(type);
                this.writeS("{" + pc.getName() + "} " + chat);
                break;

            case 9: // 密语频道 (发送)
                this.writeC(opcode);
                this.writeC(type);
                this.writeS("-> (" + pc.getName() + ") " + chat);
                break;

            case 11: // 组队:组队频道
                this.writeC(opcode);
                this.writeC(type);
                this.writeS("(" + pc.getName() + ") " + chat);
                break;

            case 12: // 交易:交易频道
                this.writeC(opcode);
                this.writeC(type);
                this.writeS("[" + pc.getName() + "] " + chat);
                break;

            case 13: // 管理
                // GM专用频道 指令
                break;

            case 14: // 频道:组队频道 (聊天)
                this.writeC(opcode);
                this.writeC(type);
                if (pc.isInvisble()) {
                    this.writeD(0);
                } else {
                    this.writeD(pc.getId());
                }
                this.writeS("(" + pc.getName() + ") " + chat);
                break;

            case 15: // 同盟:联盟频道
                this.writeC(opcode);
                this.writeC(type);
                this.writeS("{{" + pc.getName() + "}} " + chat);
                break;

            case 16: // 密语频道
                this.writeC(opcode);
                this.writeS(pc.getName());
                this.writeS(chat);
                break;
        }
    }

    @Override
    public byte[] getContent() {
        if (null == this._byte) {
            this._byte = this._bao.toByteArray();
        }
        return this._byte;
    }

    @Override
    public String getType() {
        return _S__1F_NORMALCHATPACK;
    }

}
