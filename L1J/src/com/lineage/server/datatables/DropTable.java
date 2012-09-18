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
package com.lineage.server.datatables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.L1DatabaseFactory;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.L1Quest;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Drop;
import com.lineage.server.utils.Random;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Lists;
import com.lineage.server.utils.collections.Maps;

/**
 * 掉落物品资料表
 */
public class DropTable {

    private static final Logger _log = Logger.getLogger(DropTable.class
            .getName());

    private static DropTable _instance;

    public static DropTable getInstance() {
        if (_instance == null) {
            _instance = new DropTable();
        }
        return _instance;
    }

    /** 每个怪物掉宝列表 */
    private final Map<Integer, List<L1Drop>> _droplists;

    private DropTable() {
        this._droplists = this.allDropList();
    }

    /**
     * 所有掉宝列表
     */
    private Map<Integer, List<L1Drop>> allDropList() {
        final Map<Integer, List<L1Drop>> droplistMap = Maps.newMap();

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("select * from droplist");
            rs = pstm.executeQuery();
            while (rs.next()) {
                final int mobId = rs.getInt("mobId"); // 怪物ID
                final int itemId = rs.getInt("itemId"); // 道具ID
                final int min = rs.getInt("min"); // 最大数量
                final int max = rs.getInt("max"); // 最小数量
                final int chance = rs.getInt("chance"); // 几率

                final L1Drop drop = new L1Drop(mobId, itemId, min, max, chance);

                List<L1Drop> dropList = droplistMap.get(drop.getMobid());
                if (dropList == null) {
                    dropList = Lists.newList();
                    droplistMap.put(new Integer(drop.getMobid()), dropList);
                }
                dropList.add(drop);
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
        return droplistMap;
    }

    /**
     * 掉落分配
     * 
     * @param npc
     * @param acquisitorList
     * @param hateList
     */
    public void dropShare(final L1NpcInstance npc,
            final List<L1Character> acquisitorList, final List<Integer> hateList) {
        final L1Inventory inventory = npc.getInventory();
        if (inventory.getSize() == 0) {
            return;
        }
        if (acquisitorList.size() != hateList.size()) {
            return;
        }
        // ヘイトの合計を取得
        int totalHate = 0;
        L1Character acquisitor;
        for (int i = hateList.size() - 1; i >= 0; i--) {
            acquisitor = acquisitorList.get(i);
            if ((Config.AUTO_LOOT == 2) // オートルーティング２の場合はサモン及びペットは省く
                    && ((acquisitor instanceof L1SummonInstance) || (acquisitor instanceof L1PetInstance))) {
                acquisitorList.remove(i);
                hateList.remove(i);
            } else if ((acquisitor != null)
                    && (acquisitor.getMapId() == npc.getMapId())
                    && (acquisitor.getLocation().getTileLineDistance(
                            npc.getLocation()) <= Config.LOOTING_RANGE)) {
                totalHate += hateList.get(i);
            } else { // nullだったり死んでたり遠かったら排除
                acquisitorList.remove(i);
                hateList.remove(i);
            }
        }

        // 掉落分配
        L1ItemInstance item;
        L1Inventory targetInventory = null;
        L1PcInstance player;
        L1PcInstance[] partyMember;
        int randomInt;
        int chanceHate;
        int itemId;
        for (int i = inventory.getSize(); i > 0; i--) {
            item = inventory.getItems().get(0);
            itemId = item.getItemId();
            boolean isGround = false;
            if ((item.getItem().getType2() == 0)
                    && (item.getItem().getType() == 2)) { // light系列道具
                item.setNowLighting(false);
            }

            if (((Config.AUTO_LOOT != 0) || (itemId == L1ItemId.ADENA))
                    && (totalHate > 0)) { // 如果是自动取得或金币
                randomInt = Random.nextInt(totalHate);
                chanceHate = 0;
                for (int j = hateList.size() - 1; j >= 0; j--) {
                    chanceHate += hateList.get(j);
                    if (chanceHate > randomInt) {
                        acquisitor = acquisitorList.get(j);
                        if ((itemId >= 40131) && (itemId <= 40135)) {
                            if (!(acquisitor instanceof L1PcInstance)
                                    || (hateList.size() > 1)) {
                                targetInventory = null;
                                break;
                            }
                            player = (L1PcInstance) acquisitor;
                            if (player.getQuest().get_step(L1Quest.QUEST_LYRA) != 1) {
                                targetInventory = null;
                                break;
                            }
                        }
                        if (acquisitor.getInventory().checkAddItem(item,
                                item.getCount()) == L1Inventory.OK) {
                            targetInventory = acquisitor.getInventory();
                            if (acquisitor instanceof L1PcInstance) {
                                player = (L1PcInstance) acquisitor;
                                final L1ItemInstance l1iteminstance = player
                                        .getInventory().findItemId(
                                                L1ItemId.ADENA); // 检查您的物品Adena
                                if ((l1iteminstance != null)
                                        && (l1iteminstance.getCount() > 2000000000)) {
                                    targetInventory = L1World.getInstance()
                                            .getInventory(acquisitor.getX(),
                                                    acquisitor.getY(),
                                                    acquisitor.getMapId()); // 持てないので足元に落とす
                                    isGround = true;
                                    player.sendPackets(new S_ServerMessage(166,
                                            "所持有的金币", "超过 2,000,000,000。")); // \f1%0%s
                                                                             // %4%1%3
                                                                             // %2。
                                } else {
                                    if (player.isInParty()) { // 组队中
                                        partyMember = player.getParty()
                                                .getMembers();
                                        // 组队自动分配 (item.getCount() >
                                        // 现场队员人数才分配，分配后剩余数量 otherCount
                                        // 归第一順位队员所有(应该是杀死怪的人))
                                        if (player.getPartyType() == 1) {
                                            int partySize = 0;
                                            int memberItemCount = 0;
                                            for (final L1PcInstance member : partyMember) {
                                                if ((member != null)
                                                        && (member.getMapId() == npc
                                                                .getMapId())
                                                        && (member
                                                                .getCurrentHp() > 0)
                                                        && !member.isDead()) {
                                                    partySize++;
                                                }
                                            }
                                            if ((partySize > 1)
                                                    && (item.getCount() >= partySize)) {
                                                memberItemCount = item
                                                        .getCount() / partySize;
                                                int otherCount = item
                                                        .getCount()
                                                        - memberItemCount
                                                        * partySize;
                                                if (otherCount > 0) {
                                                    item.setCount(memberItemCount
                                                            + otherCount);
                                                }
                                                for (final L1PcInstance member : partyMember) {
                                                    if ((member != null)
                                                            && (member
                                                                    .getMapId() == npc
                                                                    .getMapId())
                                                            && (member
                                                                    .getCurrentHp() > 0)
                                                            && !member.isDead()) {
                                                        member.getInventory()
                                                                .storeItem(
                                                                        itemId,
                                                                        memberItemCount);
                                                        for (final L1PcInstance pc : player
                                                                .getParty()
                                                                .getMembers()) {
                                                            pc.sendPackets(new S_ServerMessage(
                                                                    813,
                                                                    npc.getName(),
                                                                    item.getLogName(),
                                                                    member.getName()));
                                                        }
                                                        if (otherCount > 0) {
                                                            item.setCount(memberItemCount);
                                                            otherCount = 0;
                                                        }
                                                    }
                                                }
                                                inventory.removeItem(item,
                                                        item.getCount());
                                            } else {
                                                for (final L1PcInstance pc : player
                                                        .getParty()
                                                        .getMembers()) {
                                                    pc.sendPackets(new S_ServerMessage(
                                                            813, npc.getName(),
                                                            item.getLogName(),
                                                            player.getName()));
                                                }
                                            }
                                        } else {
                                            for (final L1PcInstance element : partyMember) {
                                                element.sendPackets(new S_ServerMessage(
                                                        813, npc.getName(),
                                                        item.getLogName(),
                                                        player.getName()));
                                            }
                                        }
                                    } else {
                                        // 自身情况
                                        player.sendPackets(new S_ServerMessage(
                                                143, npc.getName(), item
                                                        .getLogName())); // \f1%0%s
                                                                         // 给你
                                                                         // %1%o
                                                                         // 。
                                    }
                                }
                            }
                        } else {
                            targetInventory = L1World.getInstance()
                                    .getInventory(acquisitor.getX(),
                                            acquisitor.getY(),
                                            acquisitor.getMapId()); // 掉落地面
                            isGround = true;
                        }
                        break;
                    }
                }
            } else { // ノンオートルーティング
                final List<Integer> dirList = Lists.newList();
                for (int j = 0; j < 8; j++) {
                    dirList.add(j);
                }
                int x = 0;
                int y = 0;
                int dir = 0;
                do {
                    if (dirList.isEmpty()) {
                        x = 0;
                        y = 0;
                        break;
                    }
                    randomInt = Random.nextInt(dirList.size());
                    dir = dirList.get(randomInt);
                    dirList.remove(randomInt);
                    switch (dir) {
                        case 0:
                            x = 0;
                            y = -1;
                            break;
                        case 1:
                            x = 1;
                            y = -1;
                            break;
                        case 2:
                            x = 1;
                            y = 0;
                            break;
                        case 3:
                            x = 1;
                            y = 1;
                            break;
                        case 4:
                            x = 0;
                            y = 1;
                            break;
                        case 5:
                            x = -1;
                            y = 1;
                            break;
                        case 6:
                            x = -1;
                            y = 0;
                            break;
                        case 7:
                            x = -1;
                            y = -1;
                            break;
                    }
                } while (!npc.getMap().isPassable(npc.getX(), npc.getY(), dir));
                targetInventory = L1World.getInstance().getInventory(
                        npc.getX() + x, npc.getY() + y, npc.getMapId());
                isGround = true;
            }
            if ((itemId >= 40131) && (itemId <= 40135)) { // 图腾
                if (isGround || (targetInventory == null)) {
                    inventory.removeItem(item, item.getCount());
                    continue;
                }
            }
            inventory.tradeItem(item, item.getCount(), targetInventory);
        }
        npc.turnOnOffLight();
    }

    /**
     * 设定掉落
     * 
     * @param npc
     * @param inventory
     */
    public void setDrop(final L1NpcInstance npc, final L1Inventory inventory) {

        // 取得掉落列表
        final int mobId = npc.getNpcTemplate().get_npcId();
        final List<L1Drop> dropList = this._droplists.get(mobId);
        if (dropList == null) {
            return;
        }

        // 取得掉落倍率
        double droprate = Config.RATE_DROP_ITEMS; // 道具
        if (droprate <= 0) {
            droprate = 0;
        }
        double adenarate = Config.RATE_DROP_ADENA; // 金币
        if (adenarate <= 0) {
            adenarate = 0;
        }
        if ((droprate <= 0) && (adenarate <= 0)) {
            return;
        }

        int itemId;
        int itemCount;
        int addCount;
        int randomChance;
        L1ItemInstance item;

        for (final L1Drop drop : dropList) {
            // 获得掉落物品
            itemId = drop.getItemid();
            if ((adenarate == 0) && (itemId == L1ItemId.ADENA)) {
                continue; // 掉落金币倍率为0的情况
            }

            // 掉落几率判定
            randomChance = Random.nextInt(0xf4240) + 1;
            final double rateOfMapId = MapsTable.getInstance().getDropRate(
                    npc.getMapId());
            final double rateOfItem = DropItemTable.getInstance().getDropRate(
                    itemId);
            if ((droprate == 0)
                    || (drop.getChance() * droprate * rateOfMapId * rateOfItem < randomChance)) {
                continue;
            }

            // 设定掉落数量
            final double amount = DropItemTable.getInstance().getDropAmount(
                    itemId);
            final int min = (int) (drop.getMin() * amount);
            final int max = (int) (drop.getMax() * amount);

            itemCount = min;
            addCount = max - min + 1;
            if (addCount > 1) {
                itemCount += Random.nextInt(addCount);
            }
            if (itemId == L1ItemId.ADENA) { // 掉落金币数量 + 掉落金币倍率
                itemCount *= adenarate;
            }
            if (itemCount < 0) {
                itemCount = 0;
            }
            if (itemCount > 2000000000) { // 最高20亿
                itemCount = 2000000000;
            }

            // 道具生成
            item = ItemTable.getInstance().createItem(itemId);
            item.setCount(itemCount);

            // 储存道具
            inventory.storeItem(item);
        }
    }

}
