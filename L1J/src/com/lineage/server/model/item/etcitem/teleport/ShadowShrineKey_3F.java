package com.lineage.server.model.item.etcitem.teleport;

import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1EtcItem;

/**
 * 暗影神殿3楼钥匙 - 40616、40782、40783
 * 
 * @author jrwz
 */
public class ShadowShrineKey_3F implements ItemExecutor {

    public static ItemExecutor get() {
        return new ShadowShrineKey_3F();
    }

    private ShadowShrineKey_3F() {
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

        // 暗影神殿2F
        if (((pc.getX() >= 32698) && (pc.getX() <= 32702))
                && ((pc.getY() >= 32894) && (pc.getY() <= 32898))
                && (pc.getMapId() == 523)) {
            L1Teleport.teleport(pc, ((L1EtcItem) item.getItem()).get_locx(),
                    ((L1EtcItem) item.getItem()).get_locy(),
                    ((L1EtcItem) item.getItem()).get_mapid(), 5, true);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
