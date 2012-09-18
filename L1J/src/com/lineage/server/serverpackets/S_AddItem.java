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
 * 物品增加
 */
public class S_AddItem extends ServerBasePacket {

    private static final String S_ADD_ITEM = "[S] S_AddItem";

    /**
     * 在清单中增加一个道具。
     */
    public S_AddItem(final L1ItemInstance item) {
        this.writeC(Opcodes.S_OPCODE_ADDITEM);
        this.writeD(item.getId());
        this.writeC(item.getItem().getUseType());
        this.writeC(0);
        this.writeH(item.get_gfxid());
        this.writeC(item.getBless());
        this.writeD(item.getCount());
        this.writeC((item.isIdentified()) ? 1 : 0);
        this.writeS(item.getViewName());
        if (!item.isIdentified()) {
            // 未鉴定不发送详细讯息
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
        return S_ADD_ITEM;
    }
}
