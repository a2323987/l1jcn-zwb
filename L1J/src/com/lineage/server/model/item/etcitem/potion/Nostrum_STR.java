package com.lineage.server.model.item.etcitem.potion;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_OwnCharStatus2;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 万能药:力量 - 40033
 * 
 * @author jrwz
 */
public class Nostrum_STR implements ItemExecutor {

    private static Logger _log = Logger.getLogger(Nostrum_STR.class.getName());

    public static ItemExecutor get() {
        return new Nostrum_STR();
    }

    private Nostrum_STR() {
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

        try {
            if ((pc.getBaseStr() < 35) && (pc.getElixirStats() < 5)) {
                pc.addBaseStr((byte) 1); // STR+1
                pc.setElixirStats(pc.getElixirStats() + 1); // 万能药使用记录+1
                pc.sendPackets(new S_OwnCharStatus2(pc)); // 更新六项能力值以及负重
                pc.getInventory().removeItem(item, 1); // 删除道具
                pc.save(); // 将玩家资料储存到资料库中
            } else {
                pc.sendPackets(new S_ServerMessage(481)); // \f1属性最大值只能到35。
                                                          // 请重试一次。
            }
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new Error("万能药:力量 错误" + " File.");
        }
    }
}
