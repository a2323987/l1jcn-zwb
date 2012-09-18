package com.lineage.server.model.item.etcitem.teleport;

import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 傲慢之塔传送符(11F) - 40289 <br>
 * 傲慢之塔传送符(21F) - 40290 <br>
 * 傲慢之塔传送符(31F) - 40291 <br>
 * 傲慢之塔传送符(41F) - 40292 <br>
 * 傲慢之塔传送符(51F) - 40293 <br>
 * 傲慢之塔传送符(61F) - 40294 <br>
 * 傲慢之塔传送符(71F) - 40295 <br>
 * 傲慢之塔传送符(81F) - 40296 <br>
 * 傲慢之塔传送符(91F) - 40297 <br>
 * 
 * @author jrwz
 */
public class ArrogantTower_Amulet implements ItemExecutor {

    public static ItemExecutor get() {
        return new ArrogantTower_Amulet();
    }

    private ArrogantTower_Amulet() {
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

        final int itemId = item.getItemId();
        boolean isTeleport = false;
        final int x = pc.getX();
        final int y = pc.getY();
        final short map = pc.getMapId();

        switch (itemId) {
            case 40289: // 傲慢之塔传送符(11F),傲慢之塔传送符(51F)
            case 40293:
                // 傲慢之塔1层上方魔法阵
                if ((x >= 32816) && (x <= 32821) && (y >= 32778)
                        && (y <= 32783) && (map == 101)) {
                    isTeleport = true;
                }
                break;

            case 40290: // 傲慢之塔传送符(21F),傲慢之塔传送符(61F)
            case 40294:
                // 傲慢之塔1层右方魔法阵
                if ((x >= 32815) && (x <= 32820) && (y >= 32815)
                        && (y <= 32820) && (map == 101)) {
                    isTeleport = true;
                }
                break;

            case 40291: // 傲慢之塔传送符(31F),傲慢之塔传送符(71F)
            case 40295:
                // 傲慢之塔1层左方魔法阵
                if ((x >= 32779) && (x <= 32784) && (y >= 32778)
                        && (y <= 32783) && (map == 101)) {
                    isTeleport = true;
                }
                break;

            case 40292: // 傲慢之塔传送符(41F),傲慢之塔传送符(81F)
            case 40296:
                // 傲慢之塔1层下方魔法阵
                if ((x >= 32779) && (x <= 32784) && (y >= 32815)
                        && (y <= 32820) && (map == 101)) {
                    isTeleport = true;
                }
                break;

            case 40297: // 傲慢之塔传送符(91F)
                // 傲慢之塔90层右方魔法阵
                if ((x >= 32706) && (x <= 32710) && (y >= 32909)
                        && (y <= 32913) && (map == 190)) {
                    isTeleport = true;
                }
                break;
        }

        if (isTeleport) {
            L1Teleport.teleport(pc, item.getItem().get_locx(), item.getItem()
                    .get_locy(), item.getItem().get_mapid(), 5, true);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
