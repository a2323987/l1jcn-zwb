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
 * 范围魔法
 */
public class S_RangeSkill extends ServerBasePacket {

    private static final String S_RANGE_SKILL = "[S] S_RangeSkill";

    private static AtomicInteger _sequentialNumber = new AtomicInteger(0);

    private byte[] _byte = null;

    public static final int TYPE_NODIR = 0;

    public static final int TYPE_DIR = 8;

    private static int calcheading(final int myx, final int myy, final int tx,
            final int ty) {
        int newheading = 0;
        if ((tx > myx) && (ty > myy)) {
            newheading = 3;
        }
        if ((tx < myx) && (ty < myy)) {
            newheading = 7;
        }
        if ((tx > myx) && (ty == myy)) {
            newheading = 2;
        }
        if ((tx < myx) && (ty == myy)) {
            newheading = 6;
        }
        if ((tx == myx) && (ty < myy)) {
            newheading = 0;
        }
        if ((tx == myx) && (ty > myy)) {
            newheading = 4;
        }
        if ((tx < myx) && (ty > myy)) {
            newheading = 5;
        }
        if ((tx > myx) && (ty < myy)) {
            newheading = 1;
        }
        return newheading;
    }

    public S_RangeSkill(final L1Character cha, final L1Character[] target,
            final int spellgfx, final int actionId, final int type) {
        this.buildPacket(cha, target, spellgfx, actionId, type);
    }

    /**
     * 范围魔法
     * 
     * @param cha
     * @param target
     * @param spellgfx
     * @param actionId
     * @param type
     */
    private void buildPacket(final L1Character cha, final L1Character[] target,
            final int spellgfx, final int actionId, final int type) {
        this.writeC(Opcodes.S_OPCODE_RANGESKILLS);
        this.writeC(actionId);
        this.writeD(cha.getId());
        this.writeH(cha.getX());
        this.writeH(cha.getY());
        if (type == TYPE_NODIR) {
            this.writeC(cha.getHeading());
        } else if (type == TYPE_DIR) {
            final int newHeading = calcheading(cha.getX(), cha.getY(),
                    target[0].getX(), target[0].getY());
            cha.setHeading(newHeading);
            this.writeC(cha.getHeading());
        }
        this.writeD(_sequentialNumber.incrementAndGet()); // 番号がダブらないように送る。
        this.writeH(spellgfx);
        this.writeC(type); // 0:范围 6:远距离 8:范围&远距离
        this.writeH(0);
        this.writeH(target.length);
        for (final L1Character element : target) {
            this.writeD(element.getId());
            this.writeH(0x20); // 0:伤害动作 0以外:无
        }
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
        return S_RANGE_SKILL;
    }

}
