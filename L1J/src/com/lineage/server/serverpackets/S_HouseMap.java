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
 * 血盟小屋地图 (地点)
 */
public class S_HouseMap extends ServerBasePacket {

    private static final String S_HOUSEMAP = "[S] S_HouseMap";

    private byte[] _byte = null;

    /**
     * 血盟小屋地图 (地点)
     * 
     * @param objectId
     * @param house_number
     */
    public S_HouseMap(final int objectId, final String house_number) {
        this.buildPacket(objectId, house_number);
    }

    private void buildPacket(final int objectId, final String house_number) {
        final int number = Integer.valueOf(house_number);

        this.writeC(Opcodes.S_OPCODE_HOUSEMAP);
        this.writeD(objectId);
        this.writeD(number);
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
        return S_HOUSEMAP;
    }
}
