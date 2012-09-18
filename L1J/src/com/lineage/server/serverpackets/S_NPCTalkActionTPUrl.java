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
import com.lineage.server.model.L1NpcTalkData;

/**
 * NPC对话视窗
 */
public class S_NPCTalkActionTPUrl extends ServerBasePacket {

    private static final String _S__25_TalkReturnAction = "[S] S_NPCTalkActionTPUrl";

    private byte[] _byte = null;

    /**
     * NPC对话视窗
     * 
     * @param cha
     * @param prices
     * @param objid
     */
    public S_NPCTalkActionTPUrl(final L1NpcTalkData cha, final Object[] prices,
            final int objid) {
        this.buildPacket(cha, prices, objid);
    }

    private void buildPacket(final L1NpcTalkData npc, final Object[] prices,
            final int objid) {
        String htmlid = "";
        htmlid = npc.getTeleportURL();
        this.writeC(Opcodes.S_OPCODE_SHOWHTML);
        this.writeD(objid);
        this.writeS(htmlid);
        this.writeH(0x01); // 不明
        this.writeH(prices.length); // 数的参数

        for (final Object price : prices) {
            this.writeS(String.valueOf(((Integer) price).intValue()));
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
        return _S__25_TalkReturnAction;
    }
}
