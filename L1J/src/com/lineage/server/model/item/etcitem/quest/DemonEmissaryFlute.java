package com.lineage.server.model.item.etcitem.quest;

import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.L1SpawnUtil;

/**
 * 妖魔密使之笛子 - 49222
 * 
 * @author jrwz
 */
public class DemonEmissaryFlute implements ItemExecutor {

    public static ItemExecutor get() {
        return new DemonEmissaryFlute();
    }

    private DemonEmissaryFlute() {
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

        if (pc.isDragonKnight() && (pc.getMapId() == 61)) { // HC3F
            boolean found = false;
            for (final L1Object obj : L1World.getInstance().getObject()) {
                if (obj instanceof L1MonsterInstance) {
                    final L1MonsterInstance mob = (L1MonsterInstance) obj;
                    if (mob != null) {
                        if (mob.getNpcTemplate().get_npcId() == 46161) {
                            found = true;
                            break;
                        }
                    }
                }
            }
            if (found) {
                pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            } else {
                L1SpawnUtil.spawn(pc, 46161, 0, 0); // 妖魔密使首领
            }
            pc.getInventory().consumeItem(49222, 1);// 妖魔密使之笛子
        }
    }
}
