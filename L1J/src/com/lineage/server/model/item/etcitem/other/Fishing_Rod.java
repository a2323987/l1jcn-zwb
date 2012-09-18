package com.lineage.server.model.item.etcitem.other;

import com.lineage.server.ActionCodes;
import com.lineage.server.FishingTimeController;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_Fishing;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.Random;

/**
 * 魔法钓竿 - 41293
 * 
 * @author jrwz
 */
public class Fishing_Rod implements ItemExecutor {

    public static ItemExecutor get() {
        return new Fishing_Rod();
    }

    private Fishing_Rod() {
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
        final int fishX = data[0];
        final int fishY = data[1];
        this.startFishing(pc, itemId, fishX, fishY);
    }

    // 钓鱼
    private void startFishing(final L1PcInstance pc, final int itemId,
            final int fishX, final int fishY) {
        if ((pc.getMapId() != 5300) && (pc.getMapId() != 5301)) {
            pc.sendPackets(new S_ServerMessage(1138)); // 无法在这个地区使用钓竿。
            return;
        }
        if (pc.getTempCharGfx() != pc.getClassId()) {
            pc.sendPackets(new S_ServerMessage(1170)); // 这里不可以变身。
            return;
        }

        final int rodLength = 6;

        if (pc.getMap().isFishingZone(fishX, fishY)) {
            if (pc.getMap().isFishingZone(fishX + 1, fishY)
                    && pc.getMap().isFishingZone(fishX - 1, fishY)
                    && pc.getMap().isFishingZone(fishX, fishY + 1)
                    && pc.getMap().isFishingZone(fishX, fishY - 1)) {
                if ((fishX > pc.getX() + rodLength)
                        || (fishX < pc.getX() - rodLength)) {
                    pc.sendPackets(new S_ServerMessage(1138));
                } else if ((fishY > pc.getY() + rodLength)
                        || (fishY < pc.getY() - rodLength)) {
                    pc.sendPackets(new S_ServerMessage(1138));
                } else if (pc.getInventory().consumeItem(47103, 1)) { // 新鲜的饵
                    pc.setFishX(fishX);
                    pc.setFishY(fishY);
                    pc.sendPackets(new S_Fishing(pc.getId(),
                            ActionCodes.ACTION_Fishing, fishX, fishY));
                    pc.broadcastPacket(new S_Fishing(pc.getId(),
                            ActionCodes.ACTION_Fishing, fishX, fishY));
                    pc.setFishing(true);
                    final long time = System.currentTimeMillis() + 10000
                            + Random.nextInt(5) * 1000;
                    pc.setFishingTime(time);
                    FishingTimeController.getInstance().addMember(pc);
                } else {
                    pc.sendPackets(new S_ServerMessage(1137)); // 钓鱼需要有饵。
                }
            } else {
                pc.sendPackets(new S_ServerMessage(1138)); // 无法在这个地区使用钓竿。
            }
        } else {
            pc.sendPackets(new S_ServerMessage(1138)); // 无法在这个地区使用钓竿。
        }
    }
}
