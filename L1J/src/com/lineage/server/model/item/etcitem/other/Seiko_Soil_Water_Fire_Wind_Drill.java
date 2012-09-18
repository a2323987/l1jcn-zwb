package com.lineage.server.model.item.etcitem.other;

import com.lineage.Config;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.Random;

/**
 * 精工的土、水、火、风之钻 - (40943 - 40958)
 * 
 * @author jrwz
 */
public class Seiko_Soil_Water_Fire_Wind_Drill implements ItemExecutor {

    public static ItemExecutor get() {
        return new Seiko_Soil_Water_Fire_Wind_Drill();
    }

    private Seiko_Soil_Water_Fire_Wind_Drill() {
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
        final int itemobj = data[0];
        final L1ItemInstance l1iteminstance1 = pc.getInventory().getItem(
                itemobj);
        final int ringId = l1iteminstance1.getItem().getItemId();

        int ringlevel = 0;
        int gmas = 0;
        int gmam = 0;

        // 精致的属性戒指类
        if ((ringId >= 41185) && (41200 >= ringId)) {
            // 精工的土、水、火、风之钻类
            switch (itemId) {
                case 40943:
                case 40947:
                case 40951:
                case 40955:
                    gmas = 443;
                    gmam = 447;
                    break;

                case 40944:
                case 40948:
                case 40952:
                case 40956:
                    gmas = 442;
                    gmam = 446;
                    break;

                case 40945:
                case 40949:
                case 40953:
                case 40957:
                    gmas = 441;
                    gmam = 445;
                    break;

                case 40946:
                case 40950:
                case 40954:
                case 40958:
                    gmas = 444;
                    gmam = 448;
                    break;
            }
            if (ringId == (itemId + 242)) {
                if ((Random.nextInt(99) + 1) < Config.CREATE_CHANCE_PROCESSING_DIAMOND) {
                    ringlevel = 20435 + (ringId - 41185);
                    pc.sendPackets(new S_ServerMessage(gmas, l1iteminstance1
                            .getName()));
                    pc.createNewItem(pc, ringlevel, 1);
                    pc.getInventory().removeItem(l1iteminstance1, 1);
                    pc.getInventory().removeItem(item, 1);
                } else {
                    pc.sendPackets(new S_ServerMessage(gmam, item.getName()));
                    pc.getInventory().removeItem(item, 1);
                }
            } else {
                pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            }
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }
}
