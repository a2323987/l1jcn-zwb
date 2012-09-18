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
 * 魔法效果：诅咒
 */
public class S_Paralysis extends ServerBasePacket {
    /** 你的身体完全麻痹了 */
    public static final int TYPE_PARALYSIS = 1;
    /** 你的身体完全麻痹了 */
    public static final int TYPE_PARALYSIS2 = 2;
    /** 睡眠状态 */
    public static final int TYPE_SLEEP = 3;
    /** 冻结状态 */
    public static final int TYPE_FREEZE = 4;
    /** 昏迷状态 */
    public static final int TYPE_STUN = 5;
    /** 束缚状态 */
    public static final int TYPE_BIND = 6;
    /** 解除传送锁定状态 */
    public static final int TYPE_TELEPORT_UNLOCK = 7;

    public S_Paralysis(final int type, final boolean flag) {
        this.writeC(Opcodes.S_OPCODE_PARALYSIS);
        if (type == TYPE_PARALYSIS) // 你的身体完全麻痹了。
        {
            if (flag == true) {
                this.writeC(2);
            } else {
                this.writeC(3);
            }
        } else if (type == TYPE_PARALYSIS2) // 你的身体完全麻痹了。
        {
            if (flag == true) {
                this.writeC(4);
            } else {
                this.writeC(5);
            }
        } else if (type == TYPE_TELEPORT_UNLOCK) // 解除传送锁定
        {
            this.writeC(7);
        } else if (type == TYPE_SLEEP) // 你开始沉睡。
        {
            if (flag == true) {
                this.writeC(10);
            } else {
                this.writeC(11);
            }
        } else if (type == TYPE_FREEZE) // 被冻结了。
        {
            if (flag == true) {
                this.writeC(12);
            } else {
                this.writeC(13);
            }
        } else if (type == TYPE_STUN) // 处于昏迷状态。
        {
            if (flag == true) {
                this.writeC(22);
            } else {
                this.writeC(23);
            }
        } else if (type == TYPE_BIND) // 双脚受困无法移动。
        {
            if (flag == true) {
                this.writeC(24);
            } else {
                this.writeC(25);
            }
        }
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }

    @Override
    public String getType() {
        return "[S] S_Paralysis";
    }

}
