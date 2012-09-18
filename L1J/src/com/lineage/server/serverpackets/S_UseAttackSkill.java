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

import static com.lineage.server.model.skill.L1SkillId.SHAPE_CHANGE;

import java.util.concurrent.atomic.AtomicInteger;

import com.lineage.server.ActionCodes;
import com.lineage.server.Opcodes;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1PcInstance;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 物件攻击 (使用技能)
 */
public class S_UseAttackSkill extends ServerBasePacket {

    private static final String S_USE_ATTACK_SKILL = "[S] S_UseAttackSkill";

    private static AtomicInteger _sequentialNumber = new AtomicInteger(0);

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

    private byte[] _byte = null;

    public S_UseAttackSkill(final L1Character cha, final int targetobj,
            final int x, final int y, final int[] data) {
        this.buildPacket(cha, targetobj, x, y, data, true);
    }

    public S_UseAttackSkill(final L1Character cha, final int targetobj,
            final int x, final int y, final int[] data,
            final boolean withCastMotion) {
        this.buildPacket(cha, targetobj, x, y, data, withCastMotion);
    }

    private void buildPacket(final L1Character cha, final int targetobj,
            final int x, final int y, final int[] data,
            final boolean withCastMotion) {
        if (cha instanceof L1PcInstance) {
            // 变身中使用攻击魔法时动作代号异动
            if (cha.hasSkillEffect(SHAPE_CHANGE)
                    && (data[0] == ActionCodes.ACTION_SkillAttack)) {
                final int tempchargfx = cha.getTempCharGfx();
                if ((tempchargfx == 5727) || (tempchargfx == 5730)) {
                    data[0] = ActionCodes.ACTION_SkillBuff;
                } else if ((tempchargfx == 5733) || (tempchargfx == 5736)) {
                    // 物件具有变身 改变动作代码
                    data[0] = ActionCodes.ACTION_Attack;
                }
            }
        }
        // 火灵之主动作代码强制变更
        if (cha.getTempCharGfx() == 4013) {
            data[0] = ActionCodes.ACTION_Attack;
        }

        // 设置新面向
        final int newheading = calcheading(cha.getX(), cha.getY(), x, y);
        cha.setHeading(newheading);
        this.writeC(Opcodes.S_OPCODE_ATTACKPACKET);
        this.writeC(data[0]); // actionId 动作代码
        this.writeD(withCastMotion ? cha.getId() : 0); // 使用者的OBJ
        this.writeD(targetobj); // 目标的OBJ
        this.writeH(data[1]); // dmg 伤害值
        this.writeC(newheading); // 新面向
        this.writeD(_sequentialNumber.incrementAndGet()); // 以原子方式将当前值加 1。
        this.writeH(data[2]); // spellgfx 远程动画代码
        this.writeC(data[3]); // use_type 0:弓箭 6:远距离魔法 8:远距离范围魔法
        this.writeH(cha.getX()); // 使用者X坐标
        this.writeH(cha.getY()); // 使用者Y坐标
        this.writeH(x); // 目标X坐标
        this.writeH(y); // 目标Y坐标
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
        return S_USE_ATTACK_SKILL;
    }

}
