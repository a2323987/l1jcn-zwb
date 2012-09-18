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
 * 更新角色所在的地图
 */
public class S_MapID extends ServerBasePacket {

    /**
     * 更新角色所在的地图
     * 
     * @param pc
     *            更新角色
     * @param mapid
     *            地图编号
     * @param isUnderwater
     *            是否在水中
     */
    public S_MapID(final int mapid, final boolean isUnderwater) {
        this.writeC(Opcodes.S_OPCODE_MAPID);
        this.writeH(mapid);
        this.writeC(isUnderwater ? 1 : 0); // 水底:1
        this.writeC(0);
        this.writeH(0);
        this.writeC(0);
        this.writeD(0); // 正服值 2
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }
}
