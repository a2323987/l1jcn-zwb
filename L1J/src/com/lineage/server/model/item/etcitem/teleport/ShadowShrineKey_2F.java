package com.lineage.server.model.item.etcitem.teleport;

import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1EtcItem;

/**
 * 暗影神殿2楼钥匙 - 40615
 * 
 * @author jrwz
 */
public class ShadowShrineKey_2F implements ItemExecutor {

    public static ItemExecutor get() {
        return new ShadowShrineKey_2F();
    }

    private ShadowShrineKey_2F() {
    }

    /**
     * 道具执行
     * 
     * @param data
     *            参数
     * @param pc
     *            对象
     * @param item
     *            道具
     */
    @Override
    public void execute(final int[] data, final L1PcInstance pc,
            final L1ItemInstance item) {

        // 暗影神殿1F
        if (((pc.getX() >= 32701) && (pc.getX() <= 32705))
                && ((pc.getY() >= 32894) && (pc.getY() <= 32898))
                && (pc.getMapId() == 522)) {
            L1Teleport.teleport(pc, ((L1EtcItem) item.getItem()).get_locx(),
                    ((L1EtcItem) item.getItem()).get_locy(),
                    ((L1EtcItem) item.getItem()).get_mapid(), 5, true);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
