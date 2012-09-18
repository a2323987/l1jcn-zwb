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
package com.lineage.server.model.poison;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Poison;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 毒
 */
public abstract class L1Poison {

    protected static boolean isValidTarget(final L1Character cha) {
        if (cha == null) {
            return false;
        }
        // 毒不重叠
        if (cha.getPoison() != null) {
            return false;
        }

        if (!(cha instanceof L1PcInstance)) {
            return true;
        }

        final L1PcInstance player = (L1PcInstance) cha;
        if (player.getInventory().checkEquipped(20298) // 洁尼斯戒指
                || player.getInventory().checkEquipped(20117) // 巴风特盔甲
                || player.getInventory().checkEquipped(21115) // 安塔瑞斯的力量
                || player.getInventory().checkEquipped(21116) // 安塔瑞斯的魅惑
                || player.getInventory().checkEquipped(21117) // 安塔瑞斯的泉源
                || player.getInventory().checkEquipped(21118) // 安塔瑞斯的霸气
                || player.hasSkillEffect(104)) // 黑暗妖精魔法(毒性抵抗)
        {
            return false;
        }
        return true;
    }

    // 微妙・・・素直にsendPacketsをL1Characterへ引き上げるべきかもしれない
    protected static void sendMessageIfPlayer(final L1Character cha,
            final int msgId) {
        if (!(cha instanceof L1PcInstance)) {
            return;
        }

        final L1PcInstance player = (L1PcInstance) cha;
        player.sendPackets(new S_ServerMessage(msgId));
    }

    /**
     * 消除中毒效果。<br>
     * 
     * @see L1Character#curePoison()
     */
    public abstract void cure();

    /**
     * 返回此毒效果ID。
     * 
     * @see S_Poison#S_Poison(int, int)
     * @return 由S_Poison影响使用的ID
     */
    public abstract int getEffectId();
}
