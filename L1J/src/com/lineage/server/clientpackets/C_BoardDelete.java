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
package com.lineage.server.clientpackets;

import java.util.logging.Logger;

import com.lineage.server.ClientThread;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.templates.L1BoardTopic;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 收到由客户端传送删除公告栏的封包
 */
public class C_BoardDelete extends ClientBasePacket {

    private static final String C_BOARD_DELETE = "[C] C_BoardDelete";

    private static Logger _log = Logger
            .getLogger(C_BoardDelete.class.getName());

    public C_BoardDelete(final byte decrypt[], final ClientThread client) {
        super(decrypt);
        final int objId = this.readD();
        final int topicId = this.readD();
        final L1Object obj = L1World.getInstance().findObject(objId);
        if (obj == null) {
            _log.warning("不正确的NPCID : " + objId);
            return;
        }
        final L1BoardTopic topic = L1BoardTopic.findById(topicId);
        if (topic == null) {
            this.logNotExist(topicId);
            return;
        }
        final String name = client.getActiveChar().getName();
        if (!name.equals(topic.getName())) {
            this.logIllegalDeletion(topic, name);
            return;
        }

        topic.delete();
    }

    @Override
    public String getType() {
        return C_BOARD_DELETE;
    }

    private void logIllegalDeletion(final L1BoardTopic topic, final String name) {
        _log.warning(String
                .format("Illegal board deletion request: Name <%s> expected but was <%s>.",
                        topic.getName(), name));
    }

    private void logNotExist(final int topicId) {
        _log.warning(String
                .format("Illegal board deletion request: Topic id <%d> does not exist.",
                        topicId));
    }
}
