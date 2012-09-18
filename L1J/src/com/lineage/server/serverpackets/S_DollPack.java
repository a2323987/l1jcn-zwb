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
import com.lineage.server.model.Instance.L1DollInstance;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket , S_DollPack

/**
 * 物件封包 (门)
 */
public class S_DollPack extends ServerBasePacket {

    private static final String S_DOLLPACK = "[S] S_DollPack";

    private byte[] _byte = null;

    /**
     * 物件封包 (门)
     * 
     * @param door
     */
    public S_DollPack(final L1DollInstance doll) {
        /*
         * int addbyte = 0; int addbyte1 = 1; int addbyte2 = 13; int setting =
         * 4;
         */
        this.writeC(Opcodes.S_OPCODE_CHARPACK);
        this.writeH(doll.getX());
        this.writeH(doll.getY());
        this.writeD(doll.getId());
        this.writeH(doll.getGfxId()); // SpriteID in List.spr
        this.writeC(doll.getStatus()); // Modes in List.spr
        this.writeC(doll.getHeading());
        this.writeC(0); // (Bright) - 0~15
        this.writeC(doll.getMoveSpeed()); // 1段加速状态
        this.writeD(0);
        this.writeH(0);
        this.writeS(doll.getNameId());
        this.writeS(doll.getTitle());
        this.writeC((doll.getBraveSpeed() * 16)); // 状态、2段加速状态
        this.writeD(0); // ??
        this.writeS(null); // ??
        this.writeS(doll.getMaster() != null ? doll.getMaster().getName() : "");
        this.writeC(0); // 物件分类
        this.writeC(0xFF); // HP
        this.writeC(0);
        this.writeC(doll.getLevel()); // PC = 0, Mon = Lv
        this.writeC(0);
        this.writeC(0xFF);
        this.writeC(0xFF);
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
        return S_DOLLPACK;
    }

}
