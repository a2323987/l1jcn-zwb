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
package com.lineage.server.clientpackets;

import com.lineage.server.ClientThread;
import com.lineage.server.model.L1Trade;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来交易OK的封包
 */
public class C_TradeOK extends ClientBasePacket {

    private static final String C_TRADE_CANCEL = "[C] C_TradeOK";

    public C_TradeOK(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);

        final L1PcInstance player = clientthread.getActiveChar();
        final L1PcInstance trading_partner = (L1PcInstance) L1World
                .getInstance().findObject(player.getTradeID());
        if (trading_partner != null) {
            player.setTradeOk(true);

            if (player.getTradeOk() && trading_partner.getTradeOk()) // 同时都压OK
            {
                // 检查身上的空间是否还有 (180 - 16)
                if ((player.getInventory().getSize() < (180 - 16))
                        && (trading_partner.getInventory().getSize() < (180 - 16))) // お互いのアイテムを相手に渡す
                {
                    final L1Trade trade = new L1Trade();
                    trade.TradeOK(player);
                } else // 返回对方的道具
                {
                    player.sendPackets(new S_ServerMessage(263)); // \f1一个角色最多可携带180个道具。
                    trading_partner.sendPackets(new S_ServerMessage(263)); // \f1一个角色最多可携带180个道具。
                    final L1Trade trade = new L1Trade();
                    trade.TradeCancel(player);
                }
            }
        }
    }

    @Override
    public String getType() {
        return C_TRADE_CANCEL;
    }

}
