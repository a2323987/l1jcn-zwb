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
// ServerBasePacket

/**
 * 物件封包 - 角色本身
 */
public class S_OwnCharPack extends ServerBasePacket {

    private static final String S_OWN_CHAR_PACK = "[S] S_OwnCharPack";

    private static final int STATUS_INVISIBLE = 2;

    private static final int STATUS_PC = 4;

    private static final int STATUS_GHOST = 128;

    private byte[] _byte = null;

    public S_OwnCharPack(final L1PcInstance pc) {
        this.buildPacket(pc);
    }

    private void buildPacket(final L1PcInstance pc) {
        int status = STATUS_PC;

        // グール毒みたいな緑の毒
        // if (pc.isPoison()) {
        // status |= STATUS_POISON;
        // }

        if (pc.isInvisble() || pc.isGmInvis()) {
            status |= STATUS_INVISIBLE;
        }
        if (pc.getBraveSpeed() != 0) { // 2段加速效果
            status |= pc.getBraveSpeed() * 16;
        }

        if (pc.isGhost()) {
            status |= STATUS_GHOST;
        }

        // int addbyte = 0;
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
        // writeC(addbyte);
        this.writeC(pc.getOwnLightSize());
        this.writeC(pc.getMoveSpeed());
        this.writeD((int) pc.getExp());
        this.writeH(pc.getLawful());
        this.writeS(pc.getName());
        this.writeS(pc.getTitle());
        this.writeC(status);
        this.writeD(pc.getClanid());
        this.writeS(pc.getClanname()); // 血盟名称
        this.writeS(null); // 主人名称？
        this.writeC(0); // ？
        if (pc.isInParty()) // 组队中
        {
            this.writeC(100 * pc.getCurrentHp() / pc.getMaxHp());
        } else {
            this.writeC(0xFF);
        }
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
            this._byte = this._bao.toByteArray();
        }
        return this._byte;
    }

    @Override
    public String getType() {
        return S_OWN_CHAR_PACK;
    }

}
