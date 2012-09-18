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
package com.lineage.server.clientpackets;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.TimeZone;

import com.lineage.Config;
import com.lineage.server.ClientThread;
import com.lineage.server.datatables.AuctionBoardTable;
import com.lineage.server.datatables.HouseTable;
import com.lineage.server.datatables.InnKeyTable;
import com.lineage.server.datatables.InnTable;
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.datatables.NpcActionTable;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.model.npc.L1NpcHtml;
import com.lineage.server.model.npc.action.L1NpcAction;
import com.lineage.server.serverpackets.S_NPCTalkReturn;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.storage.CharactersItemStorage;
import com.lineage.server.templates.L1AuctionBoard;
import com.lineage.server.templates.L1House;
import com.lineage.server.templates.L1Inn;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket, C_Amount

/**
 * 处理客戶端传来拍卖的封包
 */
public class C_Amount extends ClientBasePacket {

    private static final String C_AMOUNT = "[C] C_Amount";

    public C_Amount(final byte[] decrypt, final ClientThread client)
            throws Exception {
        super(decrypt);
        final int objectId = this.readD();
        final int amount = this.readD();
        this.readC();
        final String s = this.readS();

        final L1PcInstance pc = client.getActiveChar();
        final L1NpcInstance npc = (L1NpcInstance) L1World.getInstance()
                .findObject(objectId);
        if (npc == null) {
            return;
        }

        String s1 = "";
        String s2 = "";
        try {
            final StringTokenizer stringtokenizer = new StringTokenizer(s);
            s1 = stringtokenizer.nextToken();
            s2 = stringtokenizer.nextToken();
        } catch (final NoSuchElementException e) {
            s1 = "";
            s2 = "";
        }
        if (s1.equalsIgnoreCase("agapply")) { // 如果你在拍卖竞标
            final String pcName = pc.getName();
            final AuctionBoardTable boardTable = new AuctionBoardTable();
            for (final L1AuctionBoard board : boardTable
                    .getAuctionBoardTableList()) {
                if (pcName.equalsIgnoreCase(board.getBidder())) {
                    pc.sendPackets(new S_ServerMessage(523)); // 已经参与其他血盟小屋拍卖。
                    return;
                }
            }
            final int houseId = Integer.valueOf(s2);
            final L1AuctionBoard board = boardTable
                    .getAuctionBoardTable(houseId);
            if (board != null) {
                final int nowPrice = board.getPrice();
                final int nowBidderId = board.getBidderId();
                if (pc.getInventory().consumeItem(L1ItemId.ADENA, amount)) {
                    // 更新拍卖公告
                    board.setPrice(amount);
                    board.setBidder(pcName);
                    board.setBidderId(pc.getId());
                    boardTable.updateAuctionBoard(board);
                    if (nowBidderId != 0) {
                        // 将金币退还给投标者
                        final L1PcInstance bidPc = (L1PcInstance) L1World
                                .getInstance().findObject(nowBidderId);
                        if (bidPc != null) { // 玩家在线上
                            bidPc.getInventory().storeItem(L1ItemId.ADENA,
                                    nowPrice);
                            // 有人提出比您高的金额，因此无法给你购买权。%n因为您参与拍卖没有得标，所以还给你
                            // %0金币。%n谢谢。%n%n
                            bidPc.sendPackets(new S_ServerMessage(525, String
                                    .valueOf(nowPrice)));
                        } else { // 玩家离线中
                            final L1ItemInstance item = ItemTable.getInstance()
                                    .createItem(L1ItemId.ADENA);
                            item.setCount(nowPrice);
                            final CharactersItemStorage storage = CharactersItemStorage
                                    .create();
                            storage.storeItem(nowBidderId, item);
                        }
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(189)); // \f1金币不足。
                }
            }
        } else if (s1.equalsIgnoreCase("agsell")) { // 出售盟屋
            final int houseId = Integer.valueOf(s2);
            final AuctionBoardTable boardTable = new AuctionBoardTable();
            final L1AuctionBoard board = new L1AuctionBoard();
            if (board != null) {
                // 新增拍卖公告到拍卖板
                board.setHouseId(houseId);
                final L1House house = HouseTable.getInstance().getHouseTable(
                        houseId);
                board.setHouseName(house.getHouseName());
                board.setHouseArea(house.getHouseArea());
                final TimeZone tz = TimeZone.getTimeZone(Config.TIME_ZONE);
                final Calendar cal = Calendar.getInstance(tz);
                cal.add(Calendar.DATE, 5); // 5天后
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                board.setDeadline(cal);
                board.setPrice(amount);
                board.setLocation(house.getLocation());
                board.setOldOwner(pc.getName());
                board.setOldOwnerId(pc.getId());
                board.setBidder("");
                board.setBidderId(0);
                boardTable.insertAuctionBoard(board);

                house.setOnSale(true); // 设定盟屋为拍卖中
                house.setPurchaseBasement(true); // 地下アジト未購入に設定
                HouseTable.getInstance().updateHouse(house); // 更新到资料库中
            }
        } else {
            // 旅馆NPC
            final int npcId = npc.getNpcId();
            if ((npcId == 70070) || (npcId == 70019) || (npcId == 70075)
                    || (npcId == 70012) || (npcId == 70031) || (npcId == 70084)
                    || (npcId == 70065) || (npcId == 70054) || (npcId == 70096)) {

                if (pc.getInventory().checkItem(L1ItemId.ADENA, (300 * amount))) { // 所需金币
                                                                                   // =
                                                                                   // 钥匙价格(300)
                                                                                   // *
                                                                                   // 钥匙数量(amount)
                    final L1Inn inn = InnTable.getInstance().getTemplate(npcId,
                            pc.getInnRoomNumber());
                    if (inn != null) {
                        final Timestamp dueTime = inn.getDueTime();
                        if (dueTime != null) { // 再次判断房间租用时间
                            final Calendar cal = Calendar.getInstance();
                            if (((cal.getTimeInMillis() - dueTime.getTime()) / 1000) < 0) { // 租用时间未到
                                // 此房间被抢走了...
                                pc.sendPackets(new S_NPCTalkReturn(npcId, ""));
                                return;
                            }
                        }
                        // 租用时间 4小时
                        final Timestamp ts = new Timestamp(
                                System.currentTimeMillis()
                                        + (60 * 60 * 4 * 1000));
                        // 登入旅馆資料
                        final L1ItemInstance item = ItemTable.getInstance()
                                .createItem(40312); // 旅馆钥匙
                        if (item != null) {
                            item.setKeyId(item.getId()); // 钥匙编号
                            item.setInnNpcId(npcId); // 旅馆NPC
                            item.setHall(pc.checkRoomOrHall()); // 判断租房间 or 会议室
                            item.setDueTime(ts); // 租用时间
                            item.setCount(amount); // 钥匙数量

                            inn.setKeyId(item.getKeyId()); // 旅馆钥匙
                            inn.setLodgerId(pc.getId()); // 租用人
                            inn.setHall(pc.checkRoomOrHall()); // 判断租房间 or 会议室
                            inn.setDueTime(ts); // 租用时间
                            // DB更新
                            InnTable.getInstance().updateInn(inn);

                            pc.getInventory().consumeItem(L1ItemId.ADENA,
                                    (300 * amount)); // 扣除金币

                            // 给予钥匙并登入钥匙資料
                            L1Inventory inventory;
                            if (pc.getInventory().checkAddItem(item, amount) == L1Inventory.OK) {
                                inventory = pc.getInventory();
                            } else {
                                inventory = L1World.getInstance().getInventory(
                                        pc.getLocation());
                            }
                            inventory.storeItem(item);

                            if (InnKeyTable.checkey(item)) {
                                InnKeyTable.DeleteKey(item);
                                InnKeyTable.StoreKey(item);
                            } else {
                                InnKeyTable.StoreKey(item);
                            }

                            String itemName = (item.getItem().getName() + item
                                    .getInnKeyName());
                            if (amount > 1) {
                                itemName = (itemName + " (" + amount + ")");
                            }
                            pc.sendPackets(new S_ServerMessage(143, npc
                                    .getName(), itemName)); // \f1%0%s 给你 %1%o 。
                            final String[] msg = { npc.getName() };
                            pc.sendPackets(new S_NPCTalkReturn(npcId, "inn4",
                                    msg)); // 要一起使用房间的话，请把钥匙给其他人，往旁边的楼梯上去即可。
                        }
                    }
                } else {
                    final String[] msg = { npc.getName() };
                    pc.sendPackets(new S_NPCTalkReturn(npcId, "inn3", msg)); // 对不起，你手中的金币不够哦！
                }
            } else {
                final L1NpcAction action = NpcActionTable.getInstance().get(s,
                        pc, npc);
                if (action != null) {
                    final L1NpcHtml result = action.executeWithAmount(s, pc,
                            npc, amount);
                    if (result != null) {
                        pc.sendPackets(new S_NPCTalkReturn(npcId, result));
                    }
                    return;
                }
            }
        }
    }

    @Override
    public String getType() {
        return C_AMOUNT;
    }
}
