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

import java.util.List;

import com.lineage.server.Opcodes;
import com.lineage.server.model.Instance.L1ItemInstance;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 物品名单 (背包)
 */
public class S_InvList extends ServerBasePacket {

    private static final String S_INV_LIST = "[S] S_InvList";

    /**
     * 一起增加多个道具到背包。
     */
    public S_InvList(final List<L1ItemInstance> items) {
        this.writeC(Opcodes.S_OPCODE_INVLIST);
        this.writeC(items.size()); // 道具数量

        for (final L1ItemInstance item : items) {
            this.writeD(item.getId());
            this.writeC(item.getItem().getUseType()); // 使用类型
            this.writeC(0); // 可用次数
            this.writeH(item.get_gfxid()); // 图示
            this.writeC(item.getBless()); // 祝福状态
            this.writeD(item.getCount()); // 数量
            this.writeC((item.isIdentified()) ? 1 : 0); // 鉴定状态
            this.writeS(item.getViewName()); // 名称
            if (!item.isIdentified()) {
                // 未鉴定状态不发送详细资料
                this.writeC(0);
            } else {
                final byte[] status = item.getStatusBytes();
                this.writeC(status.length);
                for (final byte b : status) {
                    this.writeC(b);
                }
            }
        }
    }

    @Override
    public byte[] getContent() {
        return this._bao.toByteArray();
    }

    @Override
    public String getType() {
        return S_INV_LIST;
    }
}
