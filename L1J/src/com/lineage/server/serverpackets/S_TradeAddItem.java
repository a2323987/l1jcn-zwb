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
import com.lineage.server.model.Instance.L1ItemInstance;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 增加交易物品
 */
public class S_TradeAddItem extends ServerBasePacket {

    private static final String S_TRADE_ADD_ITEM = "[S] S_TradeAddItem";

    /**
     * 增加交易物品
     * 
     * @param item
     * @param count
     * @param type
     */
    public S_TradeAddItem(final L1ItemInstance item, final int count,
            final int type) {
        this.writeC(Opcodes.S_OPCODE_TRADEADDITEM);
        this.writeC(type); // 0:最大的交易窗口 1:最小的交易窗口
        this.writeH(item.getItem().getGfxId());
        this.writeS(item.getNumberedViewName(count));
        // 0:祝福 1:通常 2:诅咒 3:未鉴定
        // 128:祝福&封印 129:&封印 130:诅咒&封印 131:未鉴定&封印
        if (!item.isIdentified()) {
            this.writeC(3);
            this.writeC(0);
        } else {
            this.writeC(item.getBless());
            final byte[] status = item.getStatusBytes();
            this.writeC(status.length);
            for (final byte b : status) {
                this.writeC(b);
            }
        }
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }

    @Override
    public String getType() {
        return S_TRADE_ADD_ITEM;
    }
}
