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
// ServerBasePacket, S_SendInvOnLogin

/**
 * 更新物品显示名称 (背包)
 */
public class S_ItemName extends ServerBasePacket {

    private static final String S_ITEM_NAME = "[S] S_ItemName";

    /**
     * 更新道具名称。装备强化状态变动时发送。
     */
    public S_ItemName(final L1ItemInstance item) {
        if (item == null) {
            return;
        }
        // 至于jump、Opcode目的很可能是只用于更新道具的名称（装备上之后OE後専用？）
        // 之后继续发送数据 全部无视
        this.writeC(Opcodes.S_OPCODE_ITEMNAME);
        this.writeD(item.getId());
        this.writeS(item.getViewName());
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }

    @Override
    public String getType() {
        return S_ITEM_NAME;
    }
}
