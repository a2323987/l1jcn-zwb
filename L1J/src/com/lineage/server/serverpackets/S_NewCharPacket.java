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

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 创造新角色
 */
public class S_NewCharPacket extends ServerBasePacket {

    private static final String _S__25_NEWCHARPACK = "[S] New Char Packet";

    private byte[] _byte = null;

    /**
     * 创造新角色
     * 
     * @param pc
     */
    public S_NewCharPacket(final L1PcInstance pc) {
        this.buildPacket(pc);
    }

    private void buildPacket(final L1PcInstance pc) {
        this.writeC(Opcodes.S_OPCODE_NEWCHARPACK);
        this.writeS(pc.getName());
        this.writeS("");
        this.writeC(pc.getType());
        this.writeC(pc.get_sex());
        this.writeH(pc.getLawful());
        this.writeH(pc.getMaxHp());
        this.writeH(pc.getMaxMp());
        this.writeC(pc.getAc());
        this.writeC(pc.getLevel());
        this.writeC(pc.getStr());
        this.writeC(pc.getDex());
        this.writeC(pc.getCon());
        this.writeC(pc.getWis());
        this.writeC(pc.getCha());
        this.writeC(pc.getInt());
        this.writeC(0);// is Administrator 大于0为GM
        /* 生日待后续的实做 */
        // writeD(Integer.parseInt(new
        // SimpleDateFormat("yyyyMMdd").format(pc.getBirthday())));
        this.writeD(pc.getSimpleBirthday());
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
        return _S__25_NEWCHARPACK;
    }

}
