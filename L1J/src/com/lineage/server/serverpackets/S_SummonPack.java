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
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1SummonInstance;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket, S_SummonPack

/**
 * 物件封包 (召唤)
 */
public class S_SummonPack extends ServerBasePacket {

    private static final String _S__1F_SUMMONPACK = "[S] S_SummonPack";

    private static final int STATUS_POISON = 1;

    private byte[] _byte = null;

    public S_SummonPack(final L1SummonInstance pet, final L1PcInstance pc) {
        this.buildPacket(pet, pc, true);
    }

    public S_SummonPack(final L1SummonInstance pet, final L1PcInstance pc,
            final boolean isCheckMaster) {
        this.buildPacket(pet, pc, isCheckMaster);
    }

    private void buildPacket(final L1SummonInstance pet, final L1PcInstance pc,
            final boolean isCheckMaster) {
        this.writeC(Opcodes.S_OPCODE_CHARPACK);
        this.writeH(pet.getX());
        this.writeH(pet.getY());
        this.writeD(pet.getId());
        this.writeH(pet.getGfxId()); // SpriteID in List.spr
        this.writeC(pet.getStatus()); // Modes in List.spr
        this.writeC(pet.getHeading());
        this.writeC(pet.getChaLightSize()); // (Bright) - 0~15
        this.writeC(pet.getMoveSpeed()); // 速度 - 0:normal, 1:fast, 2:slow
        this.writeD(0);
        this.writeH(0);
        this.writeS(pet.getNameId());
        this.writeS(pet.getTitle());
        int status = 0;
        if (pet.getPoison() != null) { // 毒状态
            if (pet.getPoison().getEffectId() == 1) {
                status |= STATUS_POISON;
            }
        }
        this.writeC(status);
        this.writeD(0);
        this.writeS(null);
        if (isCheckMaster && pet.isExsistMaster()) {
            this.writeS(pet.getMaster().getName());
        } else {
            this.writeS("");
        }
        this.writeC(0); // ??
        // HP的百分比
        if ((pet.getMaster() != null)
                && (pet.getMaster().getId() == pc.getId())) {
            final int percent = pet.getMaxHp() != 0 ? 100 * pet.getCurrentHp()
                    / pet.getMaxHp() : 100;
            this.writeC(percent);
        } else {
            this.writeC(0xFF);
        }
        this.writeC(0);
        this.writeC(pet.getLevel()); // PC = 0, Mon = Lv
        this.writeC(0);
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
        return _S__1F_SUMMONPACK;
    }

}
