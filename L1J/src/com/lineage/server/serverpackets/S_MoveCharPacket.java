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
import com.lineage.server.model.L1Character;
import com.lineage.server.utils.MoveUtil;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 物件移动
 */
public class S_MoveCharPacket extends ServerBasePacket {

    private static final String _S__1F_MOVECHARPACKET = "[S] S_MoveCharPacket";

    private byte[] _byte = null;

    /**
     * 物件移动
     * 
     * @param cha
     */
    public S_MoveCharPacket(final L1Character cha) {
        final int heading = cha.getHeading();
        final int x = cha.getX() - MoveUtil.MoveX(heading);
        final int y = cha.getY() - MoveUtil.MoveY(heading);

        this.writeC(Opcodes.S_OPCODE_MOVEOBJECT);
        this.writeD(cha.getId());
        this.writeH(x);
        this.writeH(y);
        this.writeC(heading);
        this.writeC(129);
        this.writeD(0);
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
        return _S__1F_MOVECHARPACKET;
    }
}
