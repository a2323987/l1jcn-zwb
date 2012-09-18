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

/**
 * 物品资讯讯息 (使用String-h.tbl)
 */
public class S_IdentifyDesc extends ServerBasePacket {

    private byte[] _byte = null;

    /**
     * 物品资讯讯息 (使用String-h.tbl)
     */
    public S_IdentifyDesc(final L1ItemInstance item) {
        this.buildPacket(item);
    }

    private void buildPacket(final L1ItemInstance item) {
        this.writeC(Opcodes.S_OPCODE_IDENTIFYDESC);
        this.writeH(item.getItem().getItemDescId());

        final StringBuilder name = new StringBuilder();

        if (item.getItem().getBless() == 0) {
            name.append("$227 "); // 祝福
        } else if (item.getItem().getBless() == 2) {
            name.append("$228 "); // 诅咒
        }

        name.append(item.getItem().getIdentifiedNameId());

        // 旅馆钥匙
        if ((item.getItem().getItemId() == 40312) && (item.getKeyId() != 0)) {
            name.append(item.getInnKeyName());
        }

        if (item.getItem().getType2() == 1) { // 武器 (weapon)
            this.writeH(134); // \f1%0：对小怪物的伤害 %1 对大怪物的伤害 %2
            this.writeC(3);
            this.writeS(name.toString());
            this.writeS(item.getItem().getDmgSmall() + "+"
                    + item.getEnchantLevel());
            this.writeS(item.getItem().getDmgLarge() + "+"
                    + item.getEnchantLevel());

        } else if (item.getItem().getType2() == 2) { // 防具 (armor)
            if (item.getItem().getItemId() == 20383) { // 军马头盔
                this.writeH(137); // \f1%0：可使用的次数 %1 [重量 %2]
                this.writeC(3);
                this.writeS(name.toString());
                this.writeS(String.valueOf(item.getChargeCount()));
            } else {
                this.writeH(135); // \f1%0：防御力 %1 防御装备
                this.writeC(2);
                this.writeS(name.toString());
                this.writeS(Math.abs(item.getItem().get_ac()) + "+"
                        + item.getEnchantLevel());
            }

        } else if (item.getItem().getType2() == 0) { // 道具 (etcitem)
            if (item.getItem().getType() == 1) { // wand
                this.writeH(137); // \f1%0：可使用的次数 %1 [重量 %2]
                this.writeC(3);
                this.writeS(name.toString());
                this.writeS(String.valueOf(item.getChargeCount()));
            } else if (item.getItem().getType() == 2) { // 照明类道具 (light)
                this.writeH(138); // \f1%0 [重量 %1]
                this.writeC(2);
                name.append(": $231 "); // 剩余燃料量
                name.append(String.valueOf(item.getRemainingTime()));
                this.writeS(name.toString());
            } else if (item.getItem().getType() == 7) { // 食物 (food)
                this.writeH(136); // \f1%0：营养度 %1 [重量 %2]
                this.writeC(3);
                this.writeS(name.toString());
                this.writeS(String.valueOf(item.getItem().getFoodVolume()));
            } else {
                this.writeH(138); // \f1%0 [重量 %1]
                this.writeC(2);
                this.writeS(name.toString());
            }
            this.writeS(String.valueOf(item.getWeight()));
        }
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this.getBytes();
        }
        return this._byte;
    }
}
