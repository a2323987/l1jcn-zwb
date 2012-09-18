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

import java.util.List;

import com.lineage.server.Opcodes;
import com.lineage.server.templates.L1BoardTopic;

/**
 * 布告栏列表 (对话视窗)
 */
public class S_Board extends ServerBasePacket {

    private static final String S_BOARD = "[S] S_Board";

    private static final int TOPIC_LIMIT = 8;

    private byte[] _byte = null;

    /**
     * 布告栏列表 (对话视窗)
     * 
     * @param boardObjId
     */
    public S_Board(final int boardObjId) {
        this.buildPacket(boardObjId, 0);
    }

    /**
     * 布告栏列表 (对话视窗)
     * 
     * @param boardObjId
     * @param number
     */
    public S_Board(final int boardObjId, final int number) {
        this.buildPacket(boardObjId, number);
    }

    private void buildPacket(final int boardObjId, final int number) {
        final List<L1BoardTopic> topics = L1BoardTopic.index(number,
                TOPIC_LIMIT);
        this.writeC(Opcodes.S_OPCODE_BOARD);
        this.writeC(0); // DragonKeybbs = 1
        this.writeD(boardObjId);
        if (number == 0) {
            this.writeD(0x7FFFFFFF);
        } else {
            this.writeD(number);
        }
        this.writeC(topics.size());
        if (number == 0) {
            this.writeC(0);
            this.writeH(300);
        }
        for (final L1BoardTopic topic : topics) {
            this.writeD(topic.getId());
            this.writeS(topic.getName());
            this.writeS(topic.getDate());
            this.writeS(topic.getTitle());
        }
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
        return S_BOARD;
    }
}
