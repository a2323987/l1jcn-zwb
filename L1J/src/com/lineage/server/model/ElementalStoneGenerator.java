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
package com.lineage.server.model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.map.L1Map;
import com.lineage.server.model.map.L1WorldMap;
import com.lineage.server.types.Point;
import com.lineage.server.utils.Random;

/**
 * 生成精灵元素石
 */
public class ElementalStoneGenerator implements Runnable {

    private static Logger _log = Logger.getLogger(ElementalStoneGenerator.class
            .getName());

    private static final int ELVEN_FOREST_MAPID = 4;
    /** 最高数量 */
    private static final int MAX_COUNT = Config.ELEMENTAL_STONE_AMOUNT; // 设置个数
    /** 时间间隔(秒) */
    private static final int INTERVAL = 3; // 设置间隔 (秒)
    /** 再次产生的时间(秒) */
    private static final int SLEEP_TIME = 300; // 设置终了后、再设置的睡眠时间 (秒)
    private static final int FIRST_X = 32911;
    private static final int FIRST_Y = 32210;
    private static final int LAST_X = 33141;
    private static final int LAST_Y = 32500;
    private static final int ELEMENTAL_STONE_ID = 40515; // 元素石

    public static ElementalStoneGenerator getInstance() {
        if (_instance == null) {
            _instance = new ElementalStoneGenerator();
        }
        return _instance;
    }

    private final ArrayList<L1GroundInventory> _itemList = new ArrayList<L1GroundInventory>(
            MAX_COUNT);

    private static ElementalStoneGenerator _instance = null;

    private final L1Object _dummy = new L1Object();

    private ElementalStoneGenerator() {
    }

    /**
     * 返回在指定地点放置精灵石。
     */
    private boolean canPut(final L1Location loc) {
        this._dummy.setMap(loc.getMap());
        this._dummy.setX(loc.getX());
        this._dummy.setY(loc.getY());

        // 检查玩家的可视范围
        if (L1World.getInstance().getVisiblePlayer(this._dummy).size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 设置下一个放置点。
     */
    private Point nextPoint() {
        final int newX = Random.nextInt(LAST_X - FIRST_X) + FIRST_X;
        final int newY = Random.nextInt(LAST_Y - FIRST_Y) + FIRST_Y;

        return new Point(newX, newY);
    }

    /**
     * 把石头放入指定地点。
     */
    private void putElementalStone(final L1Location loc) {
        final L1GroundInventory gInventory = L1World.getInstance()
                .getInventory(loc);

        final L1ItemInstance item = ItemTable.getInstance().createItem(
                ELEMENTAL_STONE_ID);
        item.setEnchantLevel(0);
        item.setCount(1);
        gInventory.storeItem(item);
        this._itemList.add(gInventory);
    }

    /**
     * 捡起石头从名单中删除。
     */
    private void removeItemsPickedUp() {
        for (int i = 0; i < this._itemList.size(); i++) {
            final L1GroundInventory gInventory = this._itemList.get(i);
            if (!gInventory.checkItem(ELEMENTAL_STONE_ID)) {
                this._itemList.remove(i);
                i--;
            }
        }
    }

    @Override
    public void run() {
        try {
            final L1Map map = L1WorldMap.getInstance().getMap(
                    (short) ELVEN_FOREST_MAPID);
            while (true) {
                this.removeItemsPickedUp();

                while (this._itemList.size() < MAX_COUNT) { // 减少的情况
                    final L1Location loc = new L1Location(this.nextPoint(), map);

                    if (!this.canPut(loc)) {
                        // XXX 设置范围内全てにPCが居た场合无限循环…
                        continue;
                    }

                    this.putElementalStone(loc);

                    Thread.sleep(INTERVAL * 1000); // 设置时间间隔
                }
                Thread.sleep(SLEEP_TIME * 1000); // maxまで设置终了后一定时间は再设置しない
            }
        } catch (final Throwable e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }
}
