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

/**
 * 组队名单
 */
public class S_Party extends ServerBasePacket {

    private static final String _S_Party = "[S] S_Party";

    private byte[] _byte = null;

    public S_Party(final int type, final L1PcInstance pc) { // 3.3C 组队系统
        switch (type) {
            case 104:
                this.newMember(pc);
                break;
            case 105:
                this.oldMember(pc);
                break;
            case 106:
                this.changeLeader(pc);
            case 110:
                this.refreshParty(pc);
                break;
            default:
                break;
        }
    }

    public S_Party(final String htmlid, final int objid) {
        this.buildPacket(htmlid, objid, "", "", 0);
    }

    public S_Party(final String htmlid, final int objid,
            final String partyname, final String partymembers) {
        this.buildPacket(htmlid, objid, partyname, partymembers, 1);
    }

    private void buildPacket(final String htmlid, final int objid,
            final String partyname, final String partymembers, final int type) {
        this.writeC(Opcodes.S_OPCODE_SHOWHTML);
        this.writeD(objid);
        this.writeS(htmlid);
        this.writeH(type);
        this.writeH(0x02);
        this.writeS(partyname);
        this.writeS(partymembers);
    }

    /**
     * 更换队长
     * 
     * @param pc
     */
    public void changeLeader(final L1PcInstance pc) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(S_PacketBox.PATRY_SET_MASTER);
        this.writeD(pc.getId());
        this.writeH(0x0000);
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
        return _S_Party;
    }

    /**
     * 新加入队伍的玩家
     * 
     * @param pc
     */
    public void newMember(final L1PcInstance pc) {
        final L1PcInstance leader = pc.getParty().getLeader();
        final L1PcInstance member[] = pc.getParty().getMembers();
        double nowhp = 0.0d;
        double maxhp = 0.0d;
        if (pc.getParty() == null) {
            return;
        }
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(S_PacketBox.UPDATE_OLD_PART_MEMBER);
        nowhp = leader.getCurrentHp();
        maxhp = leader.getMaxHp();
        this.writeC(member.length - 1);
        this.writeD(leader.getId());
        this.writeS(leader.getName());
        this.writeC((int) (nowhp / maxhp) * 100);
        this.writeD(leader.getMapId());
        this.writeH(leader.getX());
        this.writeH(leader.getY());
        for (final L1PcInstance element : member) {
            if ((element.getId() == leader.getId()) || (element == null)) {
                continue;
            }
            nowhp = element.getCurrentHp();
            maxhp = element.getMaxHp();
            this.writeD(element.getId());
            this.writeS(element.getName());
            this.writeC((int) (nowhp / maxhp) * 100);
            this.writeD(element.getMapId());
            this.writeH(element.getX());
            this.writeH(element.getY());
        }
        this.writeC(0x00);
    }

    /**
     * 旧的队员
     * 
     * @param pc
     */
    public void oldMember(final L1PcInstance pc) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(S_PacketBox.PATRY_UPDATE_MEMBER);
        this.writeD(pc.getId());
        this.writeS(pc.getName());
        this.writeD(pc.getMapId());
        this.writeH(pc.getX());
        this.writeH(pc.getY());
    }

    /**
     * 更新队伍
     * 
     * @param pc
     */
    public void refreshParty(final L1PcInstance pc) {
        final L1PcInstance member[] = pc.getParty().getMembers();
        if (pc.getParty() == null) {
            return;
        }
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(S_PacketBox.PATRY_MEMBERS);
        this.writeC(member.length);
        for (final L1PcInstance element : member) {
            this.writeD(element.getId());
            this.writeD(element.getMapId());
            this.writeH(element.getX());
            this.writeH(element.getY());
        }
        this.writeC(0x00);
    }

}
