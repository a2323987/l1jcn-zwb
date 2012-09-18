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
package com.lineage.server;

import java.util.List;

import com.lineage.Config;
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_CharVisualUpdate;
import com.lineage.server.serverpackets.S_OwnCharStatus;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.Random;
import com.lineage.server.utils.collections.Lists;

/**
 * 钓鱼控制器
 */
public class FishingTimeController implements Runnable {

    private static FishingTimeController _instance;

    public static FishingTimeController getInstance() {
        if (_instance == null) {
            _instance = new FishingTimeController();
        }
        return _instance;
    }

    /** 钓鱼清单 */
    private final List<L1PcInstance> _fishingList = Lists.newList();

    /** 增加成员 */
    public void addMember(final L1PcInstance pc) {
        if ((pc == null) || this._fishingList.contains(pc)) {
            return;
        }
        this._fishingList.add(pc);
    }

    // 钓鱼完成
    private void finishFishing(final L1PcInstance pc) {
        final int chance = Random.nextInt(215) + 1;
        boolean finish = false;

        // 钓到的物品
        final int[] fish = { 41296, 41297, 41298, 41299, // 鲷鱼`鲑鱼`鳕鱼`虎斑带鱼
                41300, 41301, 41302, 41303, // 鲔鱼`发红光的鱼`发绿光的鱼`发蓝光的鱼
                41304, 41305, 41306, 41307, // 发白光的鱼`破碎的耳环`破碎的戒指`破碎的项练
                21051, 21052, 21053, 21054, // 泡水的头具`泡水的斗篷`泡水的盔甲`泡水的手套
                21055, 21056, 21140, 21141, // 泡水的靴子`泡水的盾牌`泡水的帽子`泡水的头巾
                41252, 46001, 47104
        // 珍奇的乌龟`河豚`闪烁的鳞片
        };

        // 钓到物品的几率
        final int[] random = { 20, 40, 60, 80, 100, 110, 120, 130, 140, 145,
                150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 198, 201, 204 };
        for (int i = 0; i < fish.length; i++) {
            if (random[i] > chance) {
                this.successFishing(pc, fish[i]);
                finish = true;
                break;
            }
        }
        if (!finish) {
            pc.sendPackets(new S_ServerMessage(1517)); // 没有钓到鱼。
            if (pc.isFishingReady()) {
                this.restartFishing(pc);
            }
        }
    }

    /** 钓鱼 */
    private void fishing() {
        if (this._fishingList.size() > 0) {
            final long currentTime = System.currentTimeMillis();
            for (int i = 0; i < this._fishingList.size(); i++) {
                final L1PcInstance pc = this._fishingList.get(i);
                if (pc.isFishing()) { // 钓鱼中
                    final long time = pc.getFishingTime();
                    if ((currentTime <= (time + 500))
                            && (currentTime >= (time - 500))
                            && !pc.isFishingReady()) {
                        pc.setFishingReady(true);
                        this.finishFishing(pc);
                    }
                }
            }
        }
    }

    /** 删除成员 */
    public void removeMember(final L1PcInstance pc) {
        if ((pc == null) || !this._fishingList.contains(pc)) {
            return;
        }
        this._fishingList.remove(pc);
    }

    // 重新钓鱼
    private void restartFishing(final L1PcInstance pc) {
        if (pc.getInventory().consumeItem(47103, 1)) { // 消耗饵，重新钓鱼
            final long fishTime = System.currentTimeMillis() + 10000
                    + Random.nextInt(5) * 1000;
            pc.setFishingTime(fishTime);
            pc.setFishingReady(false);
        } else {
            pc.sendPackets(new S_ServerMessage(1137)); // 钓鱼需要有饵。
            this.stopFishing(pc);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(300);
                this.fishing();
            }
        } catch (final Exception e1) {
        }
    }

    // 停止钓鱼
    private void stopFishing(final L1PcInstance pc) {
        pc.setFishingTime(0);
        pc.setFishingReady(false);
        pc.setFishing(false);
        pc.sendPackets(new S_CharVisualUpdate(pc));
        pc.broadcastPacket(new S_CharVisualUpdate(pc));
        FishingTimeController.getInstance().removeMember(pc);
    }

    // 钓鱼成功
    private void successFishing(final L1PcInstance pc, final int itemId) {
        final L1ItemInstance item = ItemTable.getInstance().createItem(itemId);
        if (item != null) {
            pc.sendPackets(new S_ServerMessage(403, item.getItem().getName()));
            pc.addExp((int) (2 * Config.RATE_XP));
            pc.sendPackets(new S_OwnCharStatus(pc));
            item.setCount(1);
            if (pc.getInventory().checkAddItem(item, 1) == L1Inventory.OK) {
                pc.getInventory().storeItem(item);
            } else { // 负重过重，结束钓鱼
                this.stopFishing(pc);
                item.startItemOwnerTimer(pc);
                L1World.getInstance()
                        .getInventory(pc.getX(), pc.getY(), pc.getMapId())
                        .storeItem(item);
                return;
            }
        } else { // 结束钓鱼
            pc.sendPackets(new S_ServerMessage(1517)); // 没有钓到鱼。
            this.stopFishing(pc);
            return;
        }

        if (pc.isFishingReady()) {
            if (itemId == 47104) {
                pc.sendPackets(new S_ServerMessage(1739)); // 钓到了闪烁的鳞片，自动钓鱼已停止。
                this.stopFishing(pc);
                return;
            }
            this.restartFishing(pc);
        }
    }
}
