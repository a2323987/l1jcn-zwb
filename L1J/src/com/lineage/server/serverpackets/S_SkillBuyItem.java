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
package com.lineage.server.serverpackets;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.Opcodes;
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * Class <code>S_SkillBuyItem</code> 购买魔法:材料.
 * 
 * @author jrwz
 * @version 2012-4-13上午02:37:11
 * @see com.lineage.server.serverpackets
 * @since JDK1.7
 */
public final class S_SkillBuyItem extends ServerBasePacket {

    /** 提示信息. */
    private static final Logger _log = Logger.getLogger(S_SkillBuy.class
            .getName());

    /** 返回服务器数据包类型的文字信息. */
    private static final String S_SKILL_BUY_ITEM = "[S] S_SkillBuyItem";

    private byte[] _byte = null;

    /**
     * 购买魔法:材料.
     * 
     * @param o
     *            o
     * @param pc
     *            对象
     */
    public S_SkillBuyItem(final int o, final L1PcInstance pc) {
        final int count = this.Scount(pc);
        int inCount = 0;
        for (int k = 0; k < count; k++) {
            if (!pc.isSkillMastery((k + 1))) {
                inCount++;
            }
        }

        try {
            this.writeC(Opcodes.S_OPCODE_SKILLBUYITEM);
            // this.writeD(100);
            this.writeH(inCount);
            for (int k = 0; k < count; k++) {
                if (!pc.isSkillMastery((k + 1))) {
                    this.writeD(k);
                }
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
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
        return S_SKILL_BUY_ITEM;
    }

    public int Scount(final L1PcInstance pc) {
        int RC = 0;
        switch (pc.getType()) {
            case 1: // 骑士
                if ((pc.getLevel() >= 50) || pc.isGm()) {
                    RC = 8;
                }
                break;

            default:
                break;
        }
        return RC;
    }
}
