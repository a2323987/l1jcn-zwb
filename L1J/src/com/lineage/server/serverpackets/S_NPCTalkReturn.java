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
import com.lineage.server.model.L1NpcTalkData;
import com.lineage.server.model.npc.L1NpcHtml;

/**
 * NPC对话视窗
 */
public class S_NPCTalkReturn extends ServerBasePacket {

    private static final String _S__25_TalkReturn = "[S] _S__25_TalkReturn";

    private byte[] _byte = null;

    /**
     * NPC对话视窗
     * 
     * @param objid
     * @param html
     */
    public S_NPCTalkReturn(final int objid, final L1NpcHtml html) {
        this.buildPacket(objid, html.getName(), html.getArgs());
    }

    /**
     * NPC对话视窗
     * 
     * @param objid
     * @param htmlid
     */
    public S_NPCTalkReturn(final int objid, final String htmlid) {
        this.buildPacket(objid, htmlid, null);
    }

    /**
     * NPC对话视窗
     * 
     * @param objid
     * @param htmlid
     * @param data
     */
    public S_NPCTalkReturn(final int objid, final String htmlid,
            final String[] data) {
        this.buildPacket(objid, htmlid, data);
    }

    /**
     * NPC对话视窗
     * 
     * @param npc
     * @param objid
     * @param action
     */
    public S_NPCTalkReturn(final L1NpcTalkData npc, final int objid,
            final int action) {
        this(npc, objid, action, null);
    }

    /**
     * NPC对话视窗
     * 
     * @param npc
     * @param objid
     * @param action
     * @param data
     */
    public S_NPCTalkReturn(final L1NpcTalkData npc, final int objid,
            final int action, final String[] data) {

        String htmlid = "";

        if (action == 1) {
            htmlid = npc.getNormalAction();
        } else if (action == 2) {
            htmlid = npc.getCaoticAction();
        } else {
            throw new IllegalArgumentException();
        }

        this.buildPacket(objid, htmlid, data);
    }

    private void buildPacket(final int objid, final String htmlid,
            final String[] data) {

        this.writeC(Opcodes.S_OPCODE_SHOWHTML);
        this.writeD(objid);
        this.writeS(htmlid);
        if ((data != null) && (1 <= data.length)) {
            this.writeH(0x01); // 如果有人知道请修复未知字节
            this.writeH(data.length); // 数的参数
            for (final String datum : data) {
                this.writeS(datum);
            }
        } else {
            this.writeH(0x00);
            this.writeH(0x00);
        }
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
        return _S__25_TalkReturn;
    }
}
