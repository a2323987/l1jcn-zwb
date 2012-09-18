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

import static com.lineage.server.model.skill.L1SkillId.STATUS_THIRD_SPEED;

import com.lineage.server.Opcodes;
import com.lineage.server.model.Instance.L1PcInstance;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket, S_OtherCharPacks

/**
 * 物件封包 - 其他角色
 */
public class S_OtherCharPacks extends ServerBasePacket {

    private static final String S_OTHER_CHAR_PACKS = "[S] S_OtherCharPacks";

    private static final int STATUS_POISON = 1;

    private static final int STATUS_INVISIBLE = 2;

    private static final int STATUS_PC = 4;

    private byte[] _byte = null;

    public S_OtherCharPacks(final L1PcInstance pc) {
        this.buildPacket(pc, false);
    }

    public S_OtherCharPacks(final L1PcInstance pc, final boolean isFindInvis) {
        this.buildPacket(pc, isFindInvis);
    }

    private void buildPacket(final L1PcInstance pc, final boolean isFindInvis) {
        int status = STATUS_PC;

        if (pc.getPoison() != null) { // 毒状态
            if (pc.getPoison().getEffectId() == 1) {
                status |= STATUS_POISON;
            }
        }
        if (pc.isInvisble() && !isFindInvis) {
            status |= STATUS_INVISIBLE;
        }
        if (pc.getBraveSpeed() != 0) { // 2段加速效果
            status |= pc.getBraveSpeed() * 16;
        }

        // int addbyte = 0;
        // int addbyte1 = 1;

        this.writeC(Opcodes.S_OPCODE_CHARPACK);
        this.writeH(pc.getX());
        this.writeH(pc.getY());
        this.writeD(pc.getId());
        if (pc.isDead()) {
            this.writeH(pc.getTempCharGfxAtDead());
        } else {
            this.writeH(pc.getTempCharGfx());
        }
        if (pc.isDead()) {
            this.writeC(pc.getStatus());
        } else {
            this.writeC(pc.getCurrentWeapon());
        }
        this.writeC(pc.getHeading());
        // writeC(0); // makes char invis (0x01), cannot move. spells display
        this.writeC(pc.getChaLightSize());
        this.writeC(pc.getMoveSpeed());
        this.writeD(0x0000); // exp
        // writeC(0x00);
        this.writeH(pc.getLawful());
        this.writeS(pc.getName()); // 名称
        this.writeS(pc.getTitle()); // 封号
        this.writeC(status); // 状态
        this.writeD(pc.getClanid());
        this.writeS(pc.getClanname()); // 血盟名称
        this.writeS(null); // 主人名称？
        this.writeC(0); // ？
        /*
         * if(pc.is_isInParty()) // 组队中 { writeC(100 * pc.get_currentHp() /
         * pc.get_maxHp()); } else { writeC(0xFF); }
         */

        this.writeC(0xFF);
        if (pc.hasSkillEffect(STATUS_THIRD_SPEED)) {
            this.writeC(0x08); // 3段加速
        } else {
            this.writeC(0);
        }
        this.writeC(0); // PC = 0, Mon = Lv
        this.writeC(0); // ？
        this.writeC(0xFF);
        this.writeC(0xFF);
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this.getBytes();
        }
        return this._byte;
    }

    @Override
    public String getType() {
        return S_OTHER_CHAR_PACKS;
    }

}
