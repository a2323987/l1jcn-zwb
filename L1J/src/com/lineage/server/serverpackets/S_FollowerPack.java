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
import com.lineage.server.model.Instance.L1FollowerInstance;
import com.lineage.server.model.Instance.L1PcInstance;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket, S_NPCPack

/**
 * 物件封包 - 跟随者
 */
public class S_FollowerPack extends ServerBasePacket {

    private static final String S_FOLLOWER_PACK = "[S] S_FollowerPack";

    private static final int STATUS_POISON = 1;

    private byte[] _byte = null;

    /**
     * 物件封包 - 跟随者
     * 
     * @param follower
     * @param pc
     */
    public S_FollowerPack(final L1FollowerInstance follower,
            final L1PcInstance pc) {
        this.writeC(Opcodes.S_OPCODE_CHARPACK);
        this.writeH(follower.getX());
        this.writeH(follower.getY());
        this.writeD(follower.getId());
        this.writeH(follower.getGfxId());
        this.writeC(follower.getStatus());
        this.writeC(follower.getHeading());
        this.writeC(follower.getChaLightSize());
        this.writeC(follower.getMoveSpeed());
        this.writeD(0);
        this.writeH(0);
        this.writeS(follower.getNameId());
        this.writeS(follower.getTitle());
        int status = 0;
        if (follower.getPoison() != null) { // 毒状态
            if (follower.getPoison().getEffectId() == 1) {
                status |= STATUS_POISON;
            }
        }
        this.writeC(status); // 状态
        this.writeD(0);
        this.writeS(null);
        this.writeS(null);
        this.writeC(0); // 物件分类
        this.writeC(0xFF); // HP
        this.writeC(0);
        this.writeC(follower.getLevel()); // LV
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
        return S_FOLLOWER_PACK;
    }

}
