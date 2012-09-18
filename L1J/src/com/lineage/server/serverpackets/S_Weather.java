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
 * 游戏天气 1~3雨 17~19雪
 */
public class S_Weather extends ServerBasePacket {

    private static final String S_WEATHER = "[S] S_Weather";

    private byte[] _byte = null;

    /**
     * 游戏天气
     * 
     * @param weather
     */
    public S_Weather(final int weather) {
        this.buildPacket(weather);
    }

    private void buildPacket(final int weather) {
        this.writeC(Opcodes.S_OPCODE_WEATHER);
        this.writeC(weather);
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
        return S_WEATHER;
    }
}
