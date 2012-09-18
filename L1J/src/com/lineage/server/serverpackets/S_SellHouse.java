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
 * 选取物品数量 (卖出盟屋)
 */
public class S_SellHouse extends ServerBasePacket {

    private static final String S_SELLHOUSE = "[S] S_SellHouse";

    private byte[] _byte = null;

    /**
     * 选取物品数量 (卖出盟屋)
     * 
     * @param objectId
     * @param houseNumber
     */
    public S_SellHouse(final int objectId, final String houseNumber) {
        this.buildPacket(objectId, houseNumber);
    }

    private void buildPacket(final int objectId, final String houseNumber) {
        this.writeC(Opcodes.S_OPCODE_INPUTAMOUNT);
        this.writeD(objectId);
        this.writeD(0); // ?
        this.writeD(100000); // 初始价格
        this.writeD(100000); // 价格下限
        this.writeD(2000000000); // 价格上限
        this.writeH(0); // ?
        this.writeS("agsell");
        this.writeS("agsell " + houseNumber);
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
        return S_SELLHOUSE;
    }
}
