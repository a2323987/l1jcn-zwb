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

import com.lineage.server.ActionCodes;
import com.lineage.server.Opcodes;
import com.lineage.server.model.Instance.L1DoorInstance;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket, S_DoorPack

public class S_DoorPack extends ServerBasePacket {

    private static final String S_DOOR_PACK = "[S] S_DoorPack";

    private static final int STATUS_POISON = 1;

    private byte[] _byte = null;

    public S_DoorPack(final L1DoorInstance door) {
        this.buildPacket(door);
    }

    private void buildPacket(final L1DoorInstance door) {
        this.writeC(Opcodes.S_OPCODE_CHARPACK);
        this.writeH(door.getX());
        this.writeH(door.getY());
        this.writeD(door.getId());
        this.writeH(door.getGfxId());
        final int doorStatus = door.getStatus();
        final int openStatus = door.getOpenStatus();
        if (door.isDead()) {
            this.writeC(doorStatus);
        } else if (openStatus == ActionCodes.ACTION_Open) {
            this.writeC(openStatus);
        } else if ((door.getMaxHp() > 1) && (doorStatus != 0)) {
            this.writeC(doorStatus);
        } else {
            this.writeC(openStatus);
        }
        this.writeC(0);
        this.writeC(0);
        this.writeC(0);
        this.writeD(1);
        this.writeH(0);
        this.writeS(null);
        this.writeS(null);
        int status = 0;
        if (door.getPoison() != null) { // 毒状態
            if (door.getPoison().getEffectId() == 1) {
                status |= STATUS_POISON;
            }
        }
        this.writeC(status);
        this.writeD(0);
        this.writeS(null);
        this.writeS(null);
        this.writeC(0);
        this.writeC(0xFF);
        this.writeC(0);
        this.writeC(0);
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
        return S_DOOR_PACK;
    }

}
