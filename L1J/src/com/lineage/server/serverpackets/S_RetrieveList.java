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

import java.io.IOException;

import com.lineage.server.Opcodes;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 物品名单 (个人仓库)
 */
public class S_RetrieveList extends ServerBasePacket {
    public S_RetrieveList(final int objid, final L1PcInstance pc) {
        if (pc.getInventory().getSize() < 180) {
            final int size = pc.getDwarfInventory().getSize();
            if (size > 0) {
                this.writeC(Opcodes.S_OPCODE_SHOWRETRIEVELIST);
                this.writeD(objid);
                this.writeH(size);
                this.writeC(3); // 个人仓库
                for (final Object itemObject : pc.getDwarfInventory()
                        .getItems()) {
                    final L1ItemInstance item = (L1ItemInstance) itemObject;
                    this.writeD(item.getId());
                    this.writeC(0); // 道具:0 武器:1 防具:2...
                    this.writeH(item.get_gfxid());
                    this.writeC(item.getBless());
                    this.writeD(item.getCount());
                    this.writeC(item.isIdentified() ? 1 : 0);
                    this.writeS(item.getViewName());
                }
                this.writeH(0x001e); // 金币30
            } else {
                pc.sendPackets(new S_ServerMessage(1625)); // 仓库里没有委托的物品。
            }
            // クライアントに適当なメッセージ見つからなかったので非表示
            /*
             * else { l1pcinstance.sendPackets(new
             * S_SystemMessage("何もお預かりしていません。")); }
             */
        } else {
            pc.sendPackets(new S_ServerMessage(263)); // \f1一个角色最多可携带180个道具。
        }
    }

    @Override
    public byte[] getContent() throws IOException {
        return this.getBytes();
    }

}
