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
 * 更新物品使用状态 (背包) - 数量/状态
 */
public class S_ItemStatus extends ServerBasePacket {

    private static final String S_ITEM_STATUS = "[S] S_ItemStatus";

    /**
     * 更新道具的名称、状态、特性、数量
     */
    public S_ItemStatus(final L1ItemInstance item) {
        this.writeC(Opcodes.S_OPCODE_ITEMSTATUS);
        this.writeD(item.getId());
        this.writeS(item.getViewName());
        this.writeD(item.getCount());
        if (!item.isIdentified()) {
            // 未鉴定情况不发送详细资料
            this.writeC(0);
        } else {
            final byte[] status = item.getStatusBytes();
            this.writeC(status.length);
            for (final byte b : status) {
                this.writeC(b);
            }
        }
    }

    @Override
    public byte[] getContent() {
        return this._bao.toByteArray();
    }

    @Override
    public String getType() {
        return S_ITEM_STATUS;
    }
}
