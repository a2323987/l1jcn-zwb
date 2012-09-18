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

import com.lineage.Config;
import com.lineage.server.ClientThread;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_WhoAmount;
import com.lineage.server.serverpackets.S_WhoCharinfo;
import com.lineage.server.serverpackets.S_WhoStationery;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来查询线上人数的封包
 */
public class C_Who extends ClientBasePacket {

    private static final String C_WHO = "[C] C_Who";

    public C_Who(final byte[] decrypt, final ClientThread client) {
        super(decrypt);
        final String s = this.readS();
        final L1PcInstance find = L1World.getInstance().getPlayer(s);
        final L1PcInstance pc = client.getActiveChar();

        if (find != null) {
            final S_WhoCharinfo s_whocharinfo = new S_WhoCharinfo(find);
            pc.sendPackets(s_whocharinfo);
        } else {
            if (Config.ALT_WHO_COMMAND) {
                final String amount = String.valueOf(L1World.getInstance()
                        .getAllPlayers().size());
                final S_WhoAmount s_whoamount = new S_WhoAmount(amount);
                pc.sendPackets(new S_WhoStationery(pc)); // 布告栏(讯息阅读)模式讯息
                pc.sendPackets(s_whoamount);
            }
            // TODO: ChrisLiu: SystemMessage 109
            // 显示消息如果目标是不存在？正方修知道，谢谢你。
        }
    }

    @Override
    public String getType() {
        return C_WHO;
    }
}
