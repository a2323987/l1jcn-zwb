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
 * 发送坐标
 */
public class S_SendLocation extends ServerBasePacket {

    private static final String S_SEND_LOCATION = "[S] S_SendLocation";

    public S_SendLocation(final int type, final String senderName,
            final int mapId, final int x, final int y, final int msgId) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(0x6f);
        this.writeS(senderName);
        this.writeH(mapId);
        this.writeH(x);
        this.writeH(y);
        this.writeC(msgId); // 发信者所在的地图ID
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }

    @Override
    public String getType() {
        return S_SEND_LOCATION;
    }
}
