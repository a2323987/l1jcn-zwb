package com.lineage.server.model.item.etcitem.other;

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.IdFactory;
import com.lineage.server.clientpackets.C_ItemUSe;
import com.lineage.server.datatables.FurnitureSpawnTable;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.model.L1HouseLocation;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1FurnitureInstance;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Npc;

/**
 * 家具 (41383 - 41400)
 * 
 * @author jrwz
 */
public class Furniture implements ItemExecutor {

    private static Logger _log = Logger.getLogger(C_ItemUSe.class.getName());

    public static ItemExecutor get() {
        return new Furniture();
    }

    private Furniture() {
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

        final int itemObjid = item.getId();
        final int itemId = item.getItemId();
        this.useFurnitureItem(pc, itemId, itemObjid);
    }

    private void useFurnitureItem(final L1PcInstance pc, final int itemId,
            final int itemObjectId) {

        // 不能使用家具的地图
        if (!L1HouseLocation.isInHouse(pc.getX(), pc.getY(), pc.getMapId())) {
            pc.sendPackets(new S_ServerMessage(563)); // \f1你无法在这个地方使用。
            return;
        }

        boolean isAppear = true;
        L1FurnitureInstance furniture = null;
        for (final L1Object l1object : L1World.getInstance().getObject()) {
            if (l1object instanceof L1FurnitureInstance) {
                furniture = (L1FurnitureInstance) l1object;
                if (furniture.getItemObjId() == itemObjectId) {
                    isAppear = false;
                    break;
                }
            }
        }

        if (isAppear) {
            if ((pc.getHeading() != 0) && (pc.getHeading() != 2)) {
                return;
            }
            int npcId = 0;
            if (itemId == 41383) { // 巨大兵蚁标本
                npcId = 80109;
            } else if (itemId == 41384) { // 熊标本
                npcId = 80110;
            } else if (itemId == 41385) { // 蛇女标本
                npcId = 80113;
            } else if (itemId == 41386) { // 黑虎标本
                npcId = 80114;
            } else if (itemId == 41387) { // 鹿标本
                npcId = 80115;
            } else if (itemId == 41388) { // 哈维标本
                npcId = 80124;
            } else if (itemId == 41389) { // 青铜骑士
                npcId = 80118;
            } else if (itemId == 41390) { // 青铜马
                npcId = 80119;
            } else if (itemId == 41391) { // 烛台
                npcId = 80120;
            } else if (itemId == 41392) { // 茶几
                npcId = 80121;
            } else if (itemId == 41393) { // 火炉
                npcId = 80126;
            } else if (itemId == 41394) { // 火把
                npcId = 80125;
            } else if (itemId == 41395) { // 君主用讲台
                npcId = 80111;
            } else if (itemId == 41396) { // 旗帜
                npcId = 80112;
            } else if (itemId == 41397) { // 茶几椅子(右)
                npcId = 80116;
            } else if (itemId == 41398) { // 茶几椅子(左)
                npcId = 80117;
            } else if (itemId == 41399) { // 屏风(右)
                npcId = 80122;
            } else if (itemId == 41400) { // 屏风(左)
                npcId = 80123;
            }

            try {
                final L1Npc l1npc = NpcTable.getInstance().getTemplate(npcId);
                if (l1npc != null) {
                    try {
                        final String s = l1npc.getImpl();
                        final Constructor<?> constructor = Class.forName(
                                "com.lineage.server.model.Instance." + s
                                        + "Instance").getConstructors()[0];
                        final Object aobj[] = { l1npc };
                        furniture = (L1FurnitureInstance) constructor
                                .newInstance(aobj);
                        furniture.setId(IdFactory.getInstance().nextId());
                        furniture.setMap(pc.getMapId());
                        if (pc.getHeading() == 0) {
                            furniture.setX(pc.getX());
                            furniture.setY(pc.getY() - 1);
                        } else if (pc.getHeading() == 2) {
                            furniture.setX(pc.getX() + 1);
                            furniture.setY(pc.getY());
                        }
                        furniture.setHomeX(furniture.getX());
                        furniture.setHomeY(furniture.getY());
                        furniture.setHeading(0);
                        furniture.setItemObjId(itemObjectId);

                        L1World.getInstance().storeObject(furniture);
                        L1World.getInstance().addVisibleObject(furniture);
                        FurnitureSpawnTable.getInstance().insertFurniture(
                                furniture);
                    } catch (final Exception e) {
                        _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                    }
                }
            } catch (final Exception exception) {
            }
        } else {
            furniture.deleteMe();
            FurnitureSpawnTable.getInstance().deleteFurniture(furniture);
        }
    }
}
