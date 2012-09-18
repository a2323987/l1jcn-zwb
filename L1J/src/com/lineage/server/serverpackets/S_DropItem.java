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
 * 物件封包 (道具)
 */
public class S_DropItem extends ServerBasePacket {

    private static final String _S__OB_DropItem = "[S] S_DropItem";

    private byte[] _byte = null;

    public S_DropItem(final L1ItemInstance item) {
        this.buildPacket(item);
    }

    private void buildPacket(final L1ItemInstance item) {
        // int addbyte = 0;
        // int addbyte1 = 1;
        // int addbyte2 = 13;
        // int setting = 4;

        String itemName = item.getItem().getUnidentifiedNameId();
        // 已鉴定
        final int isId = item.isIdentified() ? 1 : 0;
        if (isId == 1) {
            itemName = item.getItem().getIdentifiedNameId();
        }
        this.writeC(Opcodes.S_OPCODE_DROPITEM);
        this.writeH(item.getX());
        this.writeH(item.getY());
        this.writeD(item.getId());
        this.writeH(item.getItem().getGroundGfxId());
        this.writeC(0);
        this.writeC(0);
        if (item.isNowLighting()) {
            this.writeC(item.getItem().getLightRange());
        } else {
            this.writeC(0);
        }
        this.writeC(0);
        this.writeD(item.getCount());
        this.writeC(0);
        this.writeC(0);
        if (item.getCount() > 1) {
            if ((item.getItem().getItemId() == 40312) && (item.getKeyId() != 0)) { // 旅馆钥匙
                this.writeS(itemName + item.getInnKeyName() + " ("
                        + item.getCount() + ")");
            } else {
                this.writeS(itemName + " (" + item.getCount() + ")");
            }
        } else {
            final int itemId = item.getItem().getItemId();
            if ((itemId == 20383) && (isId == 1)) { // 军马头盔
                this.writeS(itemName + " [" + item.getChargeCount() + "]");
            } else if ((item.getChargeCount() != 0) && (isId == 1)) { // 可使用的次数
                this.writeS(itemName + " (" + item.getChargeCount() + ")");
            } else if ((item.getItem().getLightRange() != 0)
                    && item.isNowLighting()) { // 灯具
                this.writeS(itemName + " ($10)");
            } else if ((item.getItem().getItemId() == 40312)
                    && (item.getKeyId() != 0)) { // 旅馆钥匙
                this.writeS(itemName + item.getInnKeyName());
            } else {
                this.writeS(itemName);
            }
        }
        this.writeC(0);
        this.writeD(0);
        this.writeD(0);
        this.writeC(255);
        this.writeC(0);
        this.writeC(0);
        this.writeC(0);
        this.writeH(65535);
        // writeD(0x401799a);
        this.writeD(0);
        this.writeC(8);
        this.writeC(0);
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
        return _S__OB_DropItem;
    }

}
