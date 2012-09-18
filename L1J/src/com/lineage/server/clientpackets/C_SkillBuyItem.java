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
 * MAY BE CONSIDERED TO BE A CONTRACT,
 * THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package com.lineage.server.clientpackets;

import com.lineage.server.ClientThread;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillBuyItem;

/**
 * Class <code>C_SkillBuyItem</code> 购买魔法:材料.
 * 
 * @author jrwz
 * @version 2012-4-13上午02:32:56
 * @see com.lineage.server.clientpackets
 * @since JDK1.7
 */
public final class C_SkillBuyItem extends ClientBasePacket {

    /** 返回一个字符串,表示客户端的数据包类型. */
    private static final String C_SKILL_BUY_ITEM = "[C] C_SkillBuyItem";

    /**
     * 购买魔法:材料.
     * 
     * @param abyte0
     *            资料
     * @param client
     *            客户端对象
     * @throws Exception
     *             执行异常
     */
    public C_SkillBuyItem(final byte[] abyte0, final ClientThread client)
            throws Exception {
        super(abyte0);
        final int i = this.readD();

        final L1PcInstance pc = client.getActiveChar();
        if (pc.isGhost()) {
            return;
        }
        if (pc.getLevel() >= 50) {
            final S_SkillBuyItem s_skillbuyitem = new S_SkillBuyItem(i, pc);
            pc.sendPackets(s_skillbuyitem);
            // System.out.println("买魔法:材料C_SkillBuyItem");
        } else {
            final S_ServerMessage msg = new S_ServerMessage(1530);
            pc.sendPackets(msg); // 由于等级不够无法进行
        }
    }

    @Override
    public String getType() {
        return C_SKILL_BUY_ITEM;
    }
}
