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
import com.lineage.server.templates.L1BoardTopic;

/**
 * 布告栏内容 (讯息阅读)
 */
public class S_BoardRead extends ServerBasePacket {

    private static final String S_BoardRead = "[S] S_BoardRead";

    private byte[] _byte = null;

    /**
     * 布告栏内容 (讯息阅读)
     * 
     * @param number
     */
    public S_BoardRead(final int number) {
        this.buildPacket(number);
    }

    private void buildPacket(final int number) {
        final L1BoardTopic topic = L1BoardTopic.findById(number);
        this.writeC(Opcodes.S_OPCODE_BOARDREAD);
        this.writeD(number);
        this.writeS(topic.getName());
        this.writeS(topic.getTitle());
        this.writeS(topic.getDate());
        this.writeS(topic.getContent());
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
        return S_BoardRead;
    }
}
