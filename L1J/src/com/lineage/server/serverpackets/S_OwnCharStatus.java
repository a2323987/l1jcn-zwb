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
import com.lineage.server.model.gametime.L1GameTimeClock;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 角色属性与能力值
 */
public class S_OwnCharStatus extends ServerBasePacket {

    private static final String S_OWB_CHAR_STATUS = "[S] S_OwnCharStatus";

    private byte[] _byte = null;

    /**
     * 更新角色属性与能力值
     */
    public S_OwnCharStatus(final L1PcInstance pc) {
        int time = L1GameTimeClock.getInstance().currentTime().getSeconds();
        time = time - (time % 300);
        // _log.warning((new
        // StringBuilder()).append("送信时间:").append(i).toString());
        this.writeC(Opcodes.S_OPCODE_OWNCHARSTATUS);
        this.writeD(pc.getId());

        if (pc.getLevel() < 1) {
            this.writeC(1);
        } else if (pc.getLevel() > 127) {
            this.writeC(127);
        } else {
            this.writeC(pc.getLevel());
        }

        this.writeExp(pc.getExp());
        this.writeC(pc.getStr());
        this.writeC(pc.getInt());
        this.writeC(pc.getWis());
        this.writeC(pc.getDex());
        this.writeC(pc.getCon());
        this.writeC(pc.getCha());
        this.writeH(pc.getCurrentHp());
        this.writeH(pc.getMaxHp());
        this.writeH(pc.getCurrentMp());
        this.writeH(pc.getMaxMp());
        this.writeC(pc.getAc());
        this.writeD(time);
        this.writeC(pc.get_food());
        this.writeC(pc.getInventory().getWeight242());
        this.writeH(pc.getLawful());
        this.writeC(pc.getFire());
        this.writeC(pc.getWater());
        this.writeC(pc.getWind());
        this.writeC(pc.getEarth());
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
        return S_OWB_CHAR_STATUS;
    }
}
