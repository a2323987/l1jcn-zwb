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
import com.lineage.server.model.Instance.L1SignboardInstance;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket, S_SignboardPack

/**
 * 
 */
public class S_SignboardPack extends ServerBasePacket {

    private static final String S_SIGNBOARD_PACK = "[S] S_SignboardPack";

    private static final int STATUS_POISON = 1;

    private byte[] _byte = null;

    public S_SignboardPack(final L1SignboardInstance signboard) {
        this.writeC(Opcodes.S_OPCODE_CHARPACK);
        this.writeH(signboard.getX());
        this.writeH(signboard.getY());
        this.writeD(signboard.getId());
        this.writeH(signboard.getGfxId());
        this.writeC(0);
        this.writeC(this.getDirection(signboard.getHeading()));
        this.writeC(0);
        this.writeC(0);
        this.writeD(0);
        this.writeH(0);
        this.writeS(null);
        this.writeS(signboard.getNameId());
        int status = 0;
        if (signboard.getPoison() != null) { // 毒状态
            if (signboard.getPoison().getEffectId() == 1) {
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
        this.writeS(null);
        this.writeH(0xFFFF);
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this._bao.toByteArray();
        }

        return this._byte;
    }

    private int getDirection(final int heading) {
        int dir = 0;
        switch (heading) {
            case 2:
                dir = 1;
                break;
            case 3:
                dir = 2;
                break;
            case 4:
                dir = 3;
                break;
            case 6:
                dir = 4;
                break;
            case 7:
                dir = 5;
                break;
        }
        return dir;
    }

    @Override
    public String getType() {
        return S_SIGNBOARD_PACK;
    }

}
