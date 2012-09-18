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

import java.util.concurrent.atomic.AtomicInteger;

import com.lineage.server.Opcodes;
import com.lineage.server.model.L1Character;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 物件攻击 (远程 - 物理攻击 PC/NPC共用)
 */
public class S_UseArrowSkill extends ServerBasePacket {

    private static final String S_USE_ARROW_SKILL = "[S] S_UseArrowSkill";

    private static AtomicInteger _sequentialNumber = new AtomicInteger(0);

    private byte[] _byte = null;

    public S_UseArrowSkill(final L1Character cha, final int targetobj,
            final int x, final int y, final int[] data) { // data = {actid, dmg,
                                                          // spellgfx}
        this.writeC(Opcodes.S_OPCODE_ATTACKPACKET);
        this.writeC(data[0]); // actid
        this.writeD(cha.getId());
        this.writeD(targetobj);
        this.writeH(data[1]); // dmg
        this.writeC(cha.getHeading());
        this.writeD(_sequentialNumber.incrementAndGet());
        this.writeH(data[2]); // spellgfx
        this.writeC(0); // use_type 箭
        this.writeH(cha.getX());
        this.writeH(cha.getY());
        this.writeH(x);
        this.writeH(y);
        this.writeC(0);
        this.writeC(0);
        this.writeC(0); // 0:none 2:爪痕 4:双击 8:镜返射
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this._bao.toByteArray();
        } else {
            int seq = 0;
            synchronized (this) {
                seq = _sequentialNumber.incrementAndGet();
            }
            this._byte[13] = (byte) (seq & 0xff);
            this._byte[14] = (byte) (seq >> 8 & 0xff);
            this._byte[15] = (byte) (seq >> 16 & 0xff);
            this._byte[16] = (byte) (seq >> 24 & 0xff);
        }
        return this._byte;
    }

    @Override
    public String getType() {
        return S_USE_ARROW_SKILL;
    }

}
