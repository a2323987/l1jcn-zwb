/**
 * License THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS
 * CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). THE WORK IS PROTECTED
 * BY COPYRIGHT AND/OR OTHER APPLICABLE LAW. ANY USE OF THE WORK OTHER THAN AS
 * AUTHORIZED UNDER THIS LICENSE OR COPYRIGHT LAW IS PROHIBITED.
 * 
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND AGREE TO
 * BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE MAY BE
 * CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */

package com.lineage.server.clientpackets;

import static com.lineage.server.model.skill.L1SkillId.AWAKEN_ANTHARAS;
import static com.lineage.server.model.skill.L1SkillId.AWAKEN_FAFURION;
import static com.lineage.server.model.skill.L1SkillId.AWAKEN_VALAKAS;
import static com.lineage.server.model.skill.L1SkillId.BLESSED_ARMOR;
import static com.lineage.server.model.skill.L1SkillId.CANCELLATION;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLESS_OF_CRAY;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLESS_OF_SAELL;
import static com.lineage.server.model.skill.L1SkillId.ELEMENTAL_PROTECTION;
import static com.lineage.server.model.skill.L1SkillId.ENCHANT_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.SHAPE_CHANGE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CURSE_BARLOG;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CURSE_YAHEE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_HASTE;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.ClientThread;
import com.lineage.server.HomeTownTimeController;
import com.lineage.server.WarTimeController;
import com.lineage.server.datatables.CastleTable;
import com.lineage.server.datatables.DoorTable;
import com.lineage.server.datatables.ExpTable;
import com.lineage.server.datatables.HouseTable;
import com.lineage.server.datatables.InnKeyTable;
import com.lineage.server.datatables.InnTable;
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.datatables.NpcActionTable;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.datatables.PetTable;
import com.lineage.server.datatables.PolyTable;
import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.datatables.TownTable;
import com.lineage.server.datatables.UBTable;
import com.lineage.server.model.L1CastleLocation;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1HauntedHouse;
import com.lineage.server.model.L1HouseLocation;
import com.lineage.server.model.L1Location;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.L1PetMatch;
import com.lineage.server.model.L1PolyMorph;
import com.lineage.server.model.L1Quest;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.L1TownLocation;
import com.lineage.server.model.L1UltimateBattle;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1DoorInstance;
import com.lineage.server.model.Instance.L1HousekeeperInstance;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1MerchantInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.model.game.L1PolyRace;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.model.npc.L1NpcHtml;
import com.lineage.server.model.npc.action.L1NpcAction;
import com.lineage.server.model.skill.L1BuffUtil;
import com.lineage.server.model.skill.L1SkillUse;
import com.lineage.server.serverpackets.S_ApplyAuction;
import com.lineage.server.serverpackets.S_AuctionBoardRead;
import com.lineage.server.serverpackets.S_CharReset;
import com.lineage.server.serverpackets.S_CloseList;
import com.lineage.server.serverpackets.S_DelSkill;
import com.lineage.server.serverpackets.S_Deposit;
import com.lineage.server.serverpackets.S_Drawal;
import com.lineage.server.serverpackets.S_HPUpdate;
import com.lineage.server.serverpackets.S_HouseMap;
import com.lineage.server.serverpackets.S_HowManyKey;
import com.lineage.server.serverpackets.S_ItemName;
import com.lineage.server.serverpackets.S_MPUpdate;
import com.lineage.server.serverpackets.S_Message_YN;
import com.lineage.server.serverpackets.S_NPCTalkReturn;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_PetCtrlMenu;
import com.lineage.server.serverpackets.S_PetList;
import com.lineage.server.serverpackets.S_RetrieveElfList;
import com.lineage.server.serverpackets.S_RetrieveList;
import com.lineage.server.serverpackets.S_RetrievePledgeList;
import com.lineage.server.serverpackets.S_SelectTarget;
import com.lineage.server.serverpackets.S_SellHouse;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_ShopBuyList;
import com.lineage.server.serverpackets.S_ShopSellList;
import com.lineage.server.serverpackets.S_SkillHaste;
import com.lineage.server.serverpackets.S_SkillIconAura;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.serverpackets.S_TaxRate;
import com.lineage.server.templates.L1Castle;
import com.lineage.server.templates.L1House;
import com.lineage.server.templates.L1Inn;
import com.lineage.server.templates.L1Item;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.templates.L1Skills;
import com.lineage.server.templates.L1Town;
import com.lineage.server.utils.Random;

/**
 * TODO: 翻译，好多 处理收到由客户端传来NPC动作的封包
 */
public class C_NPCAction extends ClientBasePacket {

    private static final String C_NPC_ACTION = "[C] C_NPCAction";

    private static Logger _log = Logger.getLogger(C_NPCAction.class.getName());

    public C_NPCAction(final byte abyte0[], final ClientThread client)
            throws Exception {
        super(abyte0);
        final int objid = this.readD();
        final String s = this.readS();

        String s2 = null;
        if (s.equalsIgnoreCase("select") // 拍卖公告板的选择
                || s.equalsIgnoreCase("map") // 地图位置的确认
                || s.equalsIgnoreCase("apply")) { // 参加拍卖
            s2 = this.readS();
        } else if (s.equalsIgnoreCase("ent")) {
            final L1Object obj = L1World.getInstance().findObject(objid);
            if ((obj != null) && (obj instanceof L1NpcInstance)) {
                if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80088) { // 宠物战管理人
                    s2 = this.readS();
                }
            }
        }

        int[] materials = null;
        int[] counts = null;
        int[] createitem = null;
        int[] createcount = null;

        String htmlid = null;
        String success_htmlid = null;
        String failure_htmlid = null;
        String[] htmldata = null;

        int questid = 0;
        int questvalue = 0;
        int contribution = 0;

        final L1PcInstance pc = client.getActiveChar();
        L1PcInstance target;
        final L1Object obj = L1World.getInstance().findObject(objid);
        if (obj != null) {
            if (obj instanceof L1NpcInstance) {
                final L1NpcInstance npc = (L1NpcInstance) obj;
                final int difflocx = Math.abs(pc.getX() - npc.getX());
                final int difflocy = Math.abs(pc.getY() - npc.getY());
                if (!(obj instanceof L1PetInstance)
                        && !(obj instanceof L1SummonInstance)) {
                    if ((difflocx > 3) || (difflocy > 3)) { // 3格以上的距离对话无效
                        return;
                    }
                    if (npc.ACTION != null) {
                        npc.ACTION.npcTalkAction(pc, npc, s, objid);
                        return;
                    }
                }
                npc.onFinalAction(pc, s);
            } else if (obj instanceof L1PcInstance) {
                target = (L1PcInstance) obj;
                if (s.matches("[0-9]+")) {
                    if (target.isSummonMonster()) {
                        this.summonMonster(target, s);
                        target.setSummonMonster(false);
                    }
                } else {
                    final int awakeSkillId = target.getAwakeSkillId();
                    if ((awakeSkillId == AWAKEN_ANTHARAS // 龙骑士魔法 (觉醒：安塔瑞斯)
                            )
                            || (awakeSkillId == AWAKEN_FAFURION // 龙骑士魔法
                                                                // (觉醒：法利昂)
                            ) || (awakeSkillId == AWAKEN_VALAKAS // 龙骑士魔法
                                                                 // (觉醒：巴拉卡斯)
                            )) {
                        target.sendPackets(new S_ServerMessage(1384)); // 目前状态中无法变身。
                        return;
                    }
                    if (target.isShapeChange()) {
                        L1PolyMorph.handleCommands(target, s);
                        target.setShapeChange(false);
                    } else {
                        final L1PolyMorph poly = PolyTable.getInstance()
                                .getTemplate(s);
                        if ((poly != null) || s.equals("none")) {
                            if (target.getInventory().checkItem(40088)
                                    && this.usePolyScroll(target, 40088, s)) {
                            }
                            if (target.getInventory().checkItem(40096)
                                    && this.usePolyScroll(target, 40096, s)) {
                            }
                            if (target.getInventory().checkItem(140088)
                                    && this.usePolyScroll(target, 140088, s)) {
                            }
                        }
                    }
                }
                return;
            }
        } else {
            // _log.warning("object not found, oid " + i);
        }

        // XML化されたアクション
        final L1NpcAction action = NpcActionTable.getInstance().get(s, pc, obj);
        if (action != null) {
            final L1NpcHtml result = action
                    .execute(s, pc, obj, this.readByte());
            if (result != null) {
                pc.sendPackets(new S_NPCTalkReturn(obj.getId(), result));
            }
            return;
        }

        /*
         * 个别处理行动
         */

        // 购买清单
        if (s.equalsIgnoreCase("buy")) {
            final L1NpcInstance npc = (L1NpcInstance) obj;
            // sell 应该指给 NPC 检查
            if (this.isNpcSellOnly(npc)) { // 只出售的NPC
                return;
            }
            pc.sendPackets(new S_ShopSellList(objid, pc));
        }

        // 贩卖清单
        else if (s.equalsIgnoreCase("sell")) {
            final int npcid = ((L1NpcInstance) obj).getNpcTemplate()
                    .get_npcId();
            if ((npcid == 70523) || (npcid == 70805)) { // 拉达 or 朱莉
                htmlid = "ladar2";
            } else if ((npcid == 70537) || (npcid == 70807)) { // 法林 or 平
                htmlid = "farlin2";
            } else if ((npcid == 70525) || (npcid == 70804)) { // 连 or 乔治
                htmlid = "lien2";
            } else if ((npcid == 50527) || (npcid == 50505) || (npcid == 50519)
                    || (npcid == 50545) || (npcid == 50531) || (npcid == 50529)
                    || (npcid == 50516) || (npcid == 50538) || (npcid == 50518)
                    || (npcid == 50509) || (npcid == 50536) || (npcid == 50520)
                    || (npcid == 50543) || (npcid == 50526) || (npcid == 50512)
                    || (npcid == 50510) || (npcid == 50504) || (npcid == 50525)
                    || (npcid == 50534) || (npcid == 50540) || (npcid == 50515)
                    || (npcid == 50513) || (npcid == 50528) || (npcid == 50533)
                    || (npcid == 50542) || (npcid == 50511) || (npcid == 50501)
                    || (npcid == 50503) || (npcid == 50508) || (npcid == 50514)
                    || (npcid == 50532) || (npcid == 50544) || (npcid == 50524)
                    || (npcid == 50535) || (npcid == 50521) || (npcid == 50517)
                    || (npcid == 50537) || (npcid == 50539) || (npcid == 50507)
                    || (npcid == 50530) || (npcid == 50502) || (npcid == 50506)
                    || (npcid == 50522) || (npcid == 50541) || (npcid == 50523)
                    || (npcid == 50620) || (npcid == 50623) || (npcid == 50619)
                    || (npcid == 50621) || (npcid == 50622) || (npcid == 50624)
                    || (npcid == 50617) || (npcid == 50614) || (npcid == 50618)
                    || (npcid == 50616) || (npcid == 50615) || (npcid == 50626)
                    || (npcid == 50627) || (npcid == 50628) || (npcid == 50629)
                    || (npcid == 50630) || (npcid == 50631)) { // アジトのNPC
                final String sellHouseMessage = this
                        .sellHouse(pc, objid, npcid);
                if (sellHouseMessage != null) {
                    htmlid = sellHouseMessage;
                }
            } else { // 一般商人

                // 可以买的物品清单
                pc.sendPackets(new S_ShopBuyList(objid, pc));
            }
        }

        // 宠物竞速NPC的编号
        else if ((((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 91002)
                && s.equalsIgnoreCase("ent")) {
            L1PolyRace.getInstance().enterGame(pc);
        }

        // “个人仓库：领取物品”
        else if (s.equalsIgnoreCase("retrieve")) {
            if (pc.getLevel() >= 5) {
                if (client.getAccount().getWarePassword() > 0) {
                    pc.sendPackets(new S_ServerMessage(834)); // 仓库密码。
                } else {
                    pc.sendPackets(new S_RetrieveList(objid, pc));
                }
            }
        } else if (s.equalsIgnoreCase("retrieve-elven")) { // “妖精仓库：领取物品”
            if ((pc.getLevel() >= 5) && pc.isElf()) {
                if (pc.isElf() && (pc.getLevel() > 4)) {
                    if (client.getAccount().getWarePassword() > 0) {
                        pc.sendPackets(new S_ServerMessage(834)); // 仓库密码。
                    } else {
                        pc.sendPackets(new S_RetrieveElfList(objid, pc));
                    }
                }
            }
        }

        // “血盟仓库：领取物品”
        else if (s.equalsIgnoreCase("retrieve-pledge")) {
            if (pc.getLevel() >= 5) {
                if (pc.getClanid() == 0) {
                    pc.sendPackets(new S_ServerMessage(208)); // \f1若想使用血盟仓库，必须加入血盟。
                    return;
                }
                final int rank = pc.getClanRank();
                if ((rank != L1Clan.CLAN_RANK_PUBLIC)
                        && (rank != L1Clan.CLAN_RANK_GUARDIAN)
                        && (rank != L1Clan.CLAN_RANK_PRINCE)) {
                    pc.sendPackets(new S_ServerMessage(728)); // 只有收到称谓的人才能使用血盟仓库。
                    return;
                }
                if ((rank != L1Clan.CLAN_RANK_PRINCE)
                        && pc.getTitle().equalsIgnoreCase("")) {
                    pc.sendPackets(new S_ServerMessage(728)); // 只有收到称谓的人才能使用血盟仓库。
                    return;
                }
                if (client.getAccount().getWarePassword() > 0) {
                    pc.sendPackets(new S_ServerMessage(834)); // 仓库密码。
                } else {
                    pc.sendPackets(new S_RetrievePledgeList(objid, pc));
                }
            }
        }

        // 接受礼物.
        else if (s.equalsIgnoreCase("get")) {
            final L1NpcInstance npc = (L1NpcInstance) obj;
            final int npcId = npc.getNpcTemplate().get_npcId();
            // 库伯 or 德汉
            if ((npcId == 70099) || (npcId == 70796)) {
                final L1ItemInstance item = pc.getInventory().storeItem(20081,
                        1); // 油布斗篷
                final String npcName = npc.getNpcTemplate().get_name();
                final String itemName = item.getItem().getName();
                pc.sendPackets(new S_ServerMessage(143, npcName, itemName)); // \f1%0%s
                                                                             // 给你
                                                                             // %1%o
                                                                             // 。
                pc.getQuest().set_end(L1Quest.QUEST_OILSKINMANT);
                htmlid = ""; // 关闭窗口
            }
            // HomeTown 村庄管理人 支付福利金
            else if ((npcId == 70528) || (npcId == 70546) || (npcId == 70567)
                    || (npcId == 70594) || (npcId == 70654) || (npcId == 70748)
                    || (npcId == 70774) || (npcId == 70799) || (npcId == 70815)
                    || (npcId == 70860)) {
                final int townId = pc.getHomeTownId();
                int pay = pc.getPay();
                final int cb = pc.getContribution(); // 贡献度
                htmlid = "";
                if (pay < 1) {
                    pc.sendPackets(new S_ServerMessage(767));// 没有村庄支援费，请在下个月再来。
                } else if ((pay > 0) && (cb < 500)) {
                    pc.sendPackets(new S_ServerMessage(766));// 贡献度不足而无法得到补偿金
                } else if (townId > 0) {
                    double payBonus = 1.0; // cb > 499 && cb < 1000
                    final boolean isLeader = TownTable.getInstance().isLeader(
                            pc, townId); // 村长
                    final L1ItemInstance item = pc.getInventory().findItemId(
                            L1ItemId.ADENA);
                    if ((cb > 999) && (cb < 1500)) {
                        payBonus = 1.5;
                    } else if ((cb > 1499) && (cb < 2000)) {
                        payBonus = 2.0;
                    } else if ((cb > 1999) && (cb < 2500)) {
                        payBonus = 2.5;
                    } else if ((cb > 2499) && (cb < 3000)) {
                        payBonus = 3.0;
                    } else if (cb > 2999) {
                        payBonus = 4.0;
                    }
                    if (isLeader) {
                        payBonus++;
                    }
                    if ((item != null)
                            && (item.getCount() + pay * payBonus > 2000000000)) {
                        pc.sendPackets(new S_ServerMessage(166,
                                "所持有的金币超过2,000,000,000。"));
                        htmlid = "";
                    } else if ((item != null)
                            && (item.getCount() + pay * payBonus < 2000000001)) {
                        pay = (int) (HomeTownTimeController.getPay(pc.getId()) * payBonus);
                        pc.getInventory().storeItem(L1ItemId.ADENA, pay);
                        pc.sendPackets(new S_ServerMessage(761, "" + pay)); // 从村庄得到%0
                                                                            // 金币的支援。
                        pc.setPay(0);
                    }
                }
            }
        }

        // 确认目前贡献度
        else if (s.equalsIgnoreCase("townscore")) {
            final L1NpcInstance npc = (L1NpcInstance) obj;
            final int npcId = npc.getNpcTemplate().get_npcId();
            if ((npcId == 70528) || (npcId == 70546) || (npcId == 70567)
                    || (npcId == 70594) || (npcId == 70654) || (npcId == 70748)
                    || (npcId == 70774) || (npcId == 70799) || (npcId == 70815)
                    || (npcId == 70860)) {
                if (pc.getHomeTownId() > 0) {
                    pc.sendPackets(new S_ServerMessage(1569, String.valueOf(pc
                            .getContribution()))); // 您的贡献度为 %0。
                }
            }
        }

        // 武器的修理
        else if (s.equalsIgnoreCase("fix")) {

        }

        // 租房间
        else if (s.equalsIgnoreCase("room")) {
            final L1NpcInstance npc = (L1NpcInstance) obj;
            final int npcId = npc.getNpcTemplate().get_npcId();
            boolean canRent = false;
            boolean findRoom = false;
            boolean isRent = false;
            boolean isHall = false;
            int roomNumber = 0;
            byte roomCount = 0;
            for (int i = 0; i < 16; i++) {
                final L1Inn inn = InnTable.getInstance().getTemplate(npcId, i);
                if (inn != null) { // 此旅馆NPC资讯不为空值
                    final Timestamp dueTime = inn.getDueTime();
                    final Calendar cal = Calendar.getInstance();
                    final long checkDueTime = (cal.getTimeInMillis() - dueTime
                            .getTime()) / 1000;
                    if ((inn.getLodgerId() == pc.getId()) && (checkDueTime < 0)) { // 出租时间未到的房间租用人判断
                        if (inn.isHall()) { // 租用的是会议室
                            isHall = true;
                        }
                        isRent = true; // 已租用
                        break;
                    } else if (!findRoom && !isRent) { // 未租用且尚未找到可租用的房间
                        if (checkDueTime >= 0) { // 租用时间已到
                            canRent = true;
                            findRoom = true;
                            roomNumber = inn.getRoomNumber();
                        } else { // 计算出租时间未到的数量
                            if (!inn.isHall()) { // 一般房间
                                roomCount++;
                            }
                        }
                    }
                }
            }

            if (isRent) {
                if (isHall) {
                    htmlid = "inn15"; // 真是抱歉，你已经租借过会议厅了。
                } else {
                    htmlid = "inn5"; // 对不起，你已经有租房间了。
                }
            } else if (roomCount >= 12) {
                htmlid = "inn6"; // 真不好意思，现在没有房间了。
            } else if (canRent) {
                pc.setInnRoomNumber(roomNumber); // 房间编号
                pc.setHall(false); // 一般房间
                pc.sendPackets(new S_HowManyKey(npc, 300, 1, 8, "inn2"));
            }
        } else if (s.equalsIgnoreCase("hall")
                && (obj instanceof L1MerchantInstance)) { // 租会议厅
            if (pc.isCrown()) {
                final L1NpcInstance npc = (L1NpcInstance) obj;
                final int npcId = npc.getNpcTemplate().get_npcId();
                boolean canRent = false;
                boolean findRoom = false;
                boolean isRent = false;
                boolean isHall = false;
                int roomNumber = 0;
                byte roomCount = 0;
                for (int i = 0; i < 16; i++) {
                    final L1Inn inn = InnTable.getInstance().getTemplate(npcId,
                            i);
                    if (inn != null) { // 此旅馆NPC资讯不为空值
                        final Timestamp dueTime = inn.getDueTime();
                        final Calendar cal = Calendar.getInstance();
                        final long checkDueTime = (cal.getTimeInMillis() - dueTime
                                .getTime()) / 1000;
                        if ((inn.getLodgerId() == pc.getId())
                                && (checkDueTime < 0)) { // 出租时间未到的房间租用人判断
                            if (inn.isHall()) { // 租用的是会议室
                                isHall = true;
                            }
                            isRent = true; // 已租用
                            break;
                        } else if (!findRoom && !isRent) { // 未租用且尚未找到可租用的房间
                            if (checkDueTime >= 0) { // 租用时间已到
                                canRent = true;
                                findRoom = true;
                                roomNumber = inn.getRoomNumber();
                            } else { // 计算出租时间未到的数量
                                if (inn.isHall()) { // 会议室
                                    roomCount++;
                                }
                            }
                        }
                    }
                }

                if (isRent) {
                    if (isHall) {
                        htmlid = "inn15"; // 真是抱歉，你已经租借过会议厅了。
                    } else {
                        htmlid = "inn5"; // 对不起，你已经有租房间了。
                    }
                } else if (roomCount >= 4) {
                    htmlid = "inn16"; // 不好意思，目前正好没有空的会议厅。
                } else if (canRent) {
                    pc.setInnRoomNumber(roomNumber); // 房间编号
                    pc.setHall(true); // 会议室
                    pc.sendPackets(new S_HowManyKey(npc, 300, 1, 8, "inn12"));
                }
            } else {
                // 王子和公主才能租用会议厅。
                htmlid = "inn10";
            }
        } else if (s.equalsIgnoreCase("return")) { // 退租
            final L1NpcInstance npc = (L1NpcInstance) obj;
            final int npcId = npc.getNpcTemplate().get_npcId();
            int price = 0;
            boolean isBreak = false;
            // 退租判断
            for (int i = 0; i < 16; i++) {
                final L1Inn inn = InnTable.getInstance().getTemplate(npcId, i);
                if (inn != null) { // 此旅馆NPC房间资讯不为空值
                    if (inn.getLodgerId() == pc.getId()) { // 欲退租的租用人
                        final Timestamp dueTime = inn.getDueTime();
                        if (dueTime != null) { // 时间不为空值
                            final Calendar cal = Calendar.getInstance();
                            if (((cal.getTimeInMillis() - dueTime.getTime()) / 1000) < 0) { // 租用时间未到
                                isBreak = true;
                                price += 60; // 退 20%租金
                            }
                        }
                        final Timestamp ts = new Timestamp(
                                System.currentTimeMillis()); // 目前时间
                        inn.setDueTime(ts); // 退租时间
                        inn.setLodgerId(0); // 租用人
                        inn.setKeyId(0); // 旅馆钥匙
                        inn.setHall(false);
                        // DB更新
                        InnTable.getInstance().updateInn(inn);
                        break;
                    }
                }
            }
            // 删除钥匙判断
            for (final L1ItemInstance item : pc.getInventory().getItems()) {
                if (item.getInnNpcId() == npcId) { // 钥匙与退租的NPC相符
                    price += 20 * item.getCount(); // 钥匙的价钱 20 * 钥匙数量
                    InnKeyTable.DeleteKey(item); // 删除钥匙纪录
                    pc.getInventory().removeItem(item); // 删除钥匙
                    isBreak = true;
                }
            }

            if (isBreak) {
                htmldata = new String[] { npc.getName(), String.valueOf(price) };
                htmlid = "inn20";
                pc.getInventory().storeItem(L1ItemId.ADENA, price); // 取得金币
            } else {
                htmlid = "";
            }
        } else if (s.equalsIgnoreCase("enter")) { // 进入房间或会议厅
            final L1NpcInstance npc = (L1NpcInstance) obj;
            final int npcId = npc.getNpcTemplate().get_npcId();

            for (final L1ItemInstance item : pc.getInventory().getItems()) {
                if (item.getInnNpcId() == npcId) { // 钥匙与NPC相符
                    for (int i = 0; i < 16; i++) {
                        final L1Inn inn = InnTable.getInstance().getTemplate(
                                npcId, i);
                        if (inn.getKeyId() == item.getKeyId()) {
                            final Timestamp dueTime = item.getDueTime();
                            if (dueTime != null) { // 时间不为空值
                                final Calendar cal = Calendar.getInstance();
                                if (((cal.getTimeInMillis() - dueTime.getTime()) / 1000) < 0) { // 钥匙租用时间未到
                                    int[] data = null;
                                    switch (npcId) {
                                        case 70012: // 说话之岛 - 瑟琳娜
                                            data = new int[] { 32745, 32803,
                                                    16384, 32743, 32808, 16896 };
                                            break;
                                        case 70019: // 古鲁丁 - 罗利雅
                                            data = new int[] { 32743, 32803,
                                                    17408, 32744, 32807, 17920 };
                                            break;
                                        case 70031: // 奇岩 - 玛理
                                            data = new int[] { 32744, 32803,
                                                    18432, 32744, 32807, 18944 };
                                            break;
                                        case 70065: // 欧瑞 - 小安安
                                            data = new int[] { 32744, 32803,
                                                    19456, 32744, 32807, 19968 };
                                            break;
                                        case 70070: // 风木 - 维莱莎
                                            data = new int[] { 32744, 32803,
                                                    20480, 32744, 32807, 20992 };
                                            break;
                                        case 70075: // 银骑士 - 米兰德
                                            data = new int[] { 32744, 32803,
                                                    21504, 32744, 32807, 22016 };
                                            break;
                                        case 70084: // 海音 - 伊莉
                                            data = new int[] { 32744, 32803,
                                                    22528, 32744, 32807, 23040 };
                                            break;
                                        default:
                                            break;
                                    }

                                    pc.setInnKeyId(item.getKeyId()); // 登入钥匙编号

                                    if (!item.checkRoomOrHall()) { // 房间
                                        L1Teleport.teleport(pc, data[0],
                                                data[1], (short) data[2], 6,
                                                false);
                                    } else { // 会议室
                                        L1Teleport.teleport(pc, data[3],
                                                data[4], (short) data[5], 6,
                                                false);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (s.equalsIgnoreCase("openigate")) { // 看门人 / 打开城门
            final L1NpcInstance npc = (L1NpcInstance) obj;
            this.openCloseGate(pc, npc.getNpcTemplate().get_npcId(), true);
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("closeigate")) { // 看门人 / 关闭城门
            final L1NpcInstance npc = (L1NpcInstance) obj;
            this.openCloseGate(pc, npc.getNpcTemplate().get_npcId(), false);
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("askwartime")) { // 近卫兵 / 查询下次攻城战时间
            final L1NpcInstance npc = (L1NpcInstance) obj;
            if (npc.getNpcTemplate().get_npcId() == 60514) { // 肯特城堡卫兵
                htmldata = this
                        .makeWarTimeStrings(L1CastleLocation.KENT_CASTLE_ID);
                htmlid = "ktguard7";
            } else if (npc.getNpcTemplate().get_npcId() == 60560) { // 妖魔城堡警卫
                htmldata = this
                        .makeWarTimeStrings(L1CastleLocation.OT_CASTLE_ID);
                htmlid = "orcguard7";
            } else if (npc.getNpcTemplate().get_npcId() == 60552) { // 风木城堡卫兵
                htmldata = this
                        .makeWarTimeStrings(L1CastleLocation.WW_CASTLE_ID);
                htmlid = "wdguard7";
            } else if ((npc.getNpcTemplate().get_npcId() == 60524) || // 奇岩左口近卫兵(弓)
                    (npc.getNpcTemplate().get_npcId() == 60525) || // 奇岩左口近卫兵
                    (npc.getNpcTemplate().get_npcId() == 60529)) { // 奇岩内城守卫
                htmldata = this
                        .makeWarTimeStrings(L1CastleLocation.GIRAN_CASTLE_ID);
                htmlid = "grguard7";
            } else if (npc.getNpcTemplate().get_npcId() == 70857) { // 海音城守卫
                htmldata = this
                        .makeWarTimeStrings(L1CastleLocation.HEINE_CASTLE_ID);
                htmlid = "heguard7";
            } else if ((npc.getNpcTemplate().get_npcId() == 60530) || // 侏儒城守卫
                    (npc.getNpcTemplate().get_npcId() == 60531)) {
                htmldata = this
                        .makeWarTimeStrings(L1CastleLocation.DOWA_CASTLE_ID);
                htmlid = "dcguard7";
            } else if ((npc.getNpcTemplate().get_npcId() == 60533) || // 亚丁内城警卫
                    (npc.getNpcTemplate().get_npcId() == 60534)) {
                htmldata = this
                        .makeWarTimeStrings(L1CastleLocation.ADEN_CASTLE_ID);
                htmlid = "adguard7";
            } else if (npc.getNpcTemplate().get_npcId() == 81156) { // 亚丁侦察兵（狄亚得要塞）
                htmldata = this
                        .makeWarTimeStrings(L1CastleLocation.DIAD_CASTLE_ID);
                htmlid = "dfguard3";
            }
        } else if (s.equalsIgnoreCase("inex")) { // 收入/支出の报告を受ける
            // 暂定的に公金をチャットウィンドウに表示させる。
            // メッセージは适当。
            final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
            if (clan != null) {
                final int castle_id = clan.getCastleId();
                if (castle_id != 0) { // 血盟城主
                    final L1Castle l1castle = CastleTable.getInstance()
                            .getCastleTable(castle_id);
                    pc.sendPackets(new S_ServerMessage(309, // %0的总财产额为 %1金币。
                            l1castle.getName(), String.valueOf(l1castle
                                    .getPublicMoney())));
                    htmlid = ""; // 关闭窗口
                }
            }
        } else if (s.equalsIgnoreCase("tax")) { // 税率调节
            pc.sendPackets(new S_TaxRate(pc.getId()));
        } else if (s.equalsIgnoreCase("withdrawal")) { // 提取资金
            final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
            if (clan != null) {
                final int castle_id = clan.getCastleId();
                if (castle_id != 0) { // 血盟城主
                    final L1Castle l1castle = CastleTable.getInstance()
                            .getCastleTable(castle_id);
                    pc.sendPackets(new S_Drawal(pc.getId(), l1castle
                            .getPublicMoney()));
                }
            }
        } else if (s.equalsIgnoreCase("cdeposit")) { // 存入资金
            pc.sendPackets(new S_Deposit(pc.getId()));
        } else if (s.equalsIgnoreCase("employ")) { // 佣兵雇用

        } else if (s.equalsIgnoreCase("arrange")) { // 雇用佣兵配置

        } else if (s.equalsIgnoreCase("castlegate")) { // 城门管理
            this.repairGate(pc);
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("encw")) { // 武器专门家 / 武器强化魔法
            if (pc.getWeapon() == null) {
                pc.sendPackets(new S_ServerMessage(79));
            } else {
                for (final L1ItemInstance item : pc.getInventory().getItems()) {
                    if (pc.getWeapon().equals(item)) {
                        final L1SkillUse l1skilluse = new L1SkillUse();
                        l1skilluse.handleCommands(pc, ENCHANT_WEAPON,
                                item.getId(), 0, 0, null, 0,
                                L1SkillUse.TYPE_SPELLSC);
                        break;
                    }
                }
            }
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("enca")) { // 防具专门家 / 防具强化魔法
            final L1ItemInstance item = pc.getInventory().getItemEquipped(2, 2);
            if (item != null) {
                final L1SkillUse l1skilluse = new L1SkillUse();
                l1skilluse.handleCommands(pc, BLESSED_ARMOR, item.getId(), 0,
                        0, null, 0, L1SkillUse.TYPE_SPELLSC);
            } else {
                pc.sendPackets(new S_ServerMessage(79));
            }
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("depositnpc")) { // 寄托宠物
            for (final L1NpcInstance petNpc : pc.getPetList().values()) {
                if (petNpc instanceof L1PetInstance) { // 宠物
                    final L1PetInstance pet = (L1PetInstance) petNpc;
                    pc.sendPackets(new S_PetCtrlMenu(pc, petNpc, false));// 关闭宠物控制图形介面
                    // 停止饱食度计时
                    pet.stopFoodTimer(pet);
                    pet.collect(true);
                    pc.getPetList().remove(pet.getId());
                    pet.deleteMe();
                }
            }
            /*
             * if (pc.getPetList().isEmpty()) { pc.sendPackets(new
             * S_PetCtrlMenu(pc, null, false));// 关闭宠物控制图形介面 } else { //
             * 更新宠物控制介面 for (L1NpcInstance petNpc : pc.getPetList().values()) {
             * if (petNpc instanceof L1SummonInstance) { L1SummonInstance summon
             * = (L1SummonInstance) petNpc; pc.sendPackets(new
             * S_SummonPack(summon, pc)); pc.sendPackets(new
             * S_ServerMessage(79)); break; } } }
             */
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("withdrawnpc")) { // 领取宠物
            pc.sendPackets(new S_PetList(objid, pc));
        } else if (s.equalsIgnoreCase("aggressive")) { // 攻击型态
            if (obj instanceof L1PetInstance) {
                final L1PetInstance l1pet = (L1PetInstance) obj;
                l1pet.setCurrentPetStatus(1);
            }
        } else if (s.equalsIgnoreCase("defensive")) { // 防御型态
            if (obj instanceof L1PetInstance) {
                final L1PetInstance l1pet = (L1PetInstance) obj;
                l1pet.setCurrentPetStatus(2);
            }
        } else if (s.equalsIgnoreCase("stay")) { // 休息
            if (obj instanceof L1PetInstance) {
                final L1PetInstance l1pet = (L1PetInstance) obj;
                l1pet.setCurrentPetStatus(3);
            }
        } else if (s.equalsIgnoreCase("extend")) { // 配备
            if (obj instanceof L1PetInstance) {
                final L1PetInstance l1pet = (L1PetInstance) obj;
                l1pet.setCurrentPetStatus(4);
            }
        } else if (s.equalsIgnoreCase("alert")) { // 警戒
            if (obj instanceof L1PetInstance) {
                final L1PetInstance l1pet = (L1PetInstance) obj;
                l1pet.setCurrentPetStatus(5);
            }
        } else if (s.equalsIgnoreCase("dismiss")) { // 解散
            if (obj instanceof L1PetInstance) {
                final L1PetInstance l1pet = (L1PetInstance) obj;
                l1pet.setCurrentPetStatus(6);
            }
        } else if (s.equalsIgnoreCase("changename")) { // 宠物改名
            pc.setTempID(objid); // 暂时保存宠物的对象ID
            pc.sendPackets(new S_Message_YN(325, "")); // 你想叫它什么名字？
        } else if (s.equalsIgnoreCase("attackchr")) {
            if (obj instanceof L1Character) {
                final L1Character cha = (L1Character) obj;
                pc.sendPackets(new S_SelectTarget(cha.getId()));
            }
        } else if (s.equalsIgnoreCase("select")) { // 竞卖揭示板のリストをクリック
            pc.sendPackets(new S_AuctionBoardRead(objid, s2));
        } else if (s.equalsIgnoreCase("map")) { // アジトの位置を确かめる
            pc.sendPackets(new S_HouseMap(objid, s2));
        } else if (s.equalsIgnoreCase("apply")) { // 竞卖に参加する
            final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
            if (clan != null) {
                if (pc.isCrown() && (pc.getId() == clan.getLeaderId())) { // 君主、かつ、血盟主
                    if (pc.getLevel() >= 15) {
                        if (clan.getHouseId() == 0) {
                            pc.sendPackets(new S_ApplyAuction(objid, s2));
                        } else {
                            pc.sendPackets(new S_ServerMessage(521)); // 已经拥有血盟小屋。
                            htmlid = ""; // 关闭窗口
                        }
                    } else {
                        pc.sendPackets(new S_ServerMessage(519)); // 等级15以下的王族无法参与拍卖。
                        htmlid = ""; // 关闭窗口
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(518)); // 血盟君主才可使用此命令。
                    htmlid = ""; // 关闭窗口
                }
            } else {
                pc.sendPackets(new S_ServerMessage(518)); // 血盟君主才可使用此命令。
                htmlid = ""; // 关闭窗口
            }
        } else if (s.equalsIgnoreCase("open") // 打开门
                || s.equalsIgnoreCase("close")) { // 关闭门
            final L1NpcInstance npc = (L1NpcInstance) obj;
            this.openCloseDoor(pc, npc, s);
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("expel")) { // 外部の人间を追い出す
            final L1NpcInstance npc = (L1NpcInstance) obj;
            this.expelOtherClan(pc, npc.getNpcTemplate().get_npcId());
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("pay")) { // 税金を纳める
            final L1NpcInstance npc = (L1NpcInstance) obj;
            htmldata = this.makeHouseTaxStrings(pc, npc);
            htmlid = "agpay";
        } else if (s.equalsIgnoreCase("payfee")) { // 税金を纳める
            final L1NpcInstance npc = (L1NpcInstance) obj;
            htmldata = new String[] { npc.getNpcTemplate().get_name(), "2000" };
            htmlid = "";
            if (this.payFee(pc, npc)) {
                htmlid = "agpayfee";
            }
        } else if (s.equalsIgnoreCase("name")) { // 盟屋改名
            final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
            if (clan != null) {
                final int houseId = clan.getHouseId();
                if (houseId != 0) {
                    final L1House house = HouseTable.getInstance()
                            .getHouseTable(houseId);
                    final int keeperId = house.getKeeperId();
                    final L1NpcInstance npc = (L1NpcInstance) obj;
                    if (npc.getNpcTemplate().get_npcId() == keeperId) {
                        pc.setTempID(houseId); // 暂时保存盟屋的ID
                        pc.sendPackets(new S_Message_YN(512, "")); // 请输入血盟小屋名称?
                    }
                }
            }
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("rem")) { // 家の中の家具をすべて取り除く
        } else if (s.equalsIgnoreCase("tel0") // テレポートする(仓库)
                || s.equalsIgnoreCase("tel1") // テレポートする(ペット保管所)
                || s.equalsIgnoreCase("tel2") // テレポートする(赎罪の使者)
                || s.equalsIgnoreCase("tel3")) { // テレポートする(ギラン市场)
            final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
            if (clan != null) {
                final int houseId = clan.getHouseId();
                if (houseId != 0) {
                    final L1House house = HouseTable.getInstance()
                            .getHouseTable(houseId);
                    final int keeperId = house.getKeeperId();
                    final L1NpcInstance npc = (L1NpcInstance) obj;
                    if (npc.getNpcTemplate().get_npcId() == keeperId) {
                        int[] loc = new int[3];
                        if (s.equalsIgnoreCase("tel0")) {
                            loc = L1HouseLocation.getHouseTeleportLoc(houseId,
                                    0);
                        } else if (s.equalsIgnoreCase("tel1")) {
                            loc = L1HouseLocation.getHouseTeleportLoc(houseId,
                                    1);
                        } else if (s.equalsIgnoreCase("tel2")) {
                            loc = L1HouseLocation.getHouseTeleportLoc(houseId,
                                    2);
                        } else if (s.equalsIgnoreCase("tel3")) {
                            loc = L1HouseLocation.getHouseTeleportLoc(houseId,
                                    3);
                        }
                        L1Teleport.teleport(pc, loc[0], loc[1], (short) loc[2],
                                5, true);
                    }
                }
            }
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("upgrade")) { // 制作地下盟屋
            final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
            if (clan != null) {
                final int houseId = clan.getHouseId();
                if (houseId != 0) {
                    final L1House house = HouseTable.getInstance()
                            .getHouseTable(houseId);
                    final int keeperId = house.getKeeperId();
                    final L1NpcInstance npc = (L1NpcInstance) obj;
                    if (npc.getNpcTemplate().get_npcId() == keeperId) {
                        if (pc.isCrown() && (pc.getId() == clan.getLeaderId())) { // 君主、かつ、血盟主
                            if (house.isPurchaseBasement()) {
                                // 已经拥有了!
                                pc.sendPackets(new S_ServerMessage(1135));
                            } else {
                                if (pc.getInventory().consumeItem(
                                        L1ItemId.ADENA, 5000000)) {
                                    house.setPurchaseBasement(true);
                                    HouseTable.getInstance().updateHouse(house); // DBに书き迂み
                                    // 创立成功...!!
                                    pc.sendPackets(new S_ServerMessage(1099));
                                } else {
                                    // \f1金币不足。
                                    pc.sendPackets(new S_ServerMessage(189));
                                }
                            }
                        } else {
                            // 血盟君主才可使用此命令。
                            pc.sendPackets(new S_ServerMessage(518));
                        }
                    }
                }
            }
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("hall")
                && (obj instanceof L1HousekeeperInstance)) { // 瞬移到地下盟屋
            final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
            if (clan != null) {
                final int houseId = clan.getHouseId();
                if (houseId != 0) {
                    final L1House house = HouseTable.getInstance()
                            .getHouseTable(houseId);
                    final int keeperId = house.getKeeperId();
                    final L1NpcInstance npc = (L1NpcInstance) obj;
                    if (npc.getNpcTemplate().get_npcId() == keeperId) {
                        if (house.isPurchaseBasement()) {
                            int[] loc = new int[3];
                            loc = L1HouseLocation.getBasementLoc(houseId);
                            L1Teleport.teleport(pc, loc[0], loc[1],
                                    (short) (loc[2]), 5, true);
                        } else {
                            // 没有开设地下房间或不是血盟的成员
                            pc.sendPackets(new S_ServerMessage(1098));
                        }
                    }
                }
            }
            htmlid = ""; // 关闭窗口
        }

        // ElfAttr:0.无属性,1.地属性,2.火属性,4.水属性,8.风属性
        else if (s.equalsIgnoreCase("fire")) // 精灵属性变更“火系列”
        {
            if (pc.isElf()) {
                if (pc.getElfAttr() != 0) {
                    return;
                }
                pc.setElfAttr(2);
                pc.save(); // 将资料保存到资料库
                pc.sendPackets(new S_PacketBox(S_PacketBox.MSG_ELF, 1)); // 忽然全身充满了火的灵力。
                htmlid = ""; // 关闭窗口
            }
        } else if (s.equalsIgnoreCase("water")) { // 精灵属性变更“水系列”
            if (pc.isElf()) {
                if (pc.getElfAttr() != 0) {
                    return;
                }
                pc.setElfAttr(4);
                pc.save(); // 将资料保存到资料库
                pc.sendPackets(new S_PacketBox(S_PacketBox.MSG_ELF, 2)); // 忽然全身充满了水的灵力。
                htmlid = ""; // 关闭窗口
            }
        } else if (s.equalsIgnoreCase("air")) { // 精灵属性变更“风系列”
            if (pc.isElf()) {
                if (pc.getElfAttr() != 0) {
                    return;
                }
                pc.setElfAttr(8);
                pc.save(); // 将资料保存到资料库
                pc.sendPackets(new S_PacketBox(S_PacketBox.MSG_ELF, 3)); // 忽然全身充满了风的灵力。
                htmlid = ""; // 关闭窗口
            }
        } else if (s.equalsIgnoreCase("earth")) { // 精灵属性变更“地系列”
            if (pc.isElf()) {
                if (pc.getElfAttr() != 0) {
                    return;
                }
                pc.setElfAttr(1);
                pc.save(); // 将资料保存到资料库
                pc.sendPackets(new S_PacketBox(S_PacketBox.MSG_ELF, 4)); // 忽然全身充满了地的灵力。
                htmlid = ""; // 关闭窗口
            }
        } else if (s.equalsIgnoreCase("init")) { // 精灵属性变更“消除属性”
            if (pc.isElf()) {
                if (pc.getElfAttr() == 0) {
                    return;
                }
                for (int cnt = 129; cnt <= 176; cnt++) // 检查所有的魔法精灵
                {
                    final L1Skills l1skills1 = SkillsTable.getInstance()
                            .getTemplate(cnt);
                    final int skill_attr = l1skills1.getAttr();
                    if (skill_attr != 0) // 从资料库删除无属性魔法以外魔法
                    {
                        SkillsTable.getInstance().spellLost(pc.getId(),
                                l1skills1.getSkillId());
                    }
                }
                // 重置属性上的属性防御
                if (pc.hasSkillEffect(ELEMENTAL_PROTECTION)) { // 单属性防御
                    pc.removeSkillEffect(ELEMENTAL_PROTECTION);
                }
                pc.sendPackets(new S_DelSkill(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 248, 252, 252, 255, 0, 0, 0, 0, 0,
                        0)); // 无属性魔法以外のエルフ魔法を魔法ウィンドウから削除する
                pc.setElfAttr(0);
                pc.save(); // 将资料保存到资料库
                pc.sendPackets(new S_ServerMessage(678)); // 感觉到在身上有的精灵力量被空气中融化。
                htmlid = ""; // 关闭窗口
            }
        }

        // “经验值回复”
        else if (s.equalsIgnoreCase("exp")) {
            if (pc.getExpRes() == 1) {
                int cost = 0;
                final int level = pc.getLevel();
                final int lawful = pc.getLawful();
                if (level < 45) {
                    cost = level * level * 100;
                } else {
                    cost = level * level * 200;
                }
                if (lawful >= 0) {
                    cost = (cost / 2);
                }
                pc.sendPackets(new S_Message_YN(738, String.valueOf(cost))); // 恢复经验值需消耗%0金币。想要恢复经验值吗?
            } else {
                pc.sendPackets(new S_ServerMessage(739)); // 目前无法恢复经验值。
                htmlid = ""; // 关闭窗口
            }
        }

        // “赎罪”
        else if (s.equalsIgnoreCase("pk")) {
            if (pc.getLawful() < 30000) {
                pc.sendPackets(new S_ServerMessage(559)); // \f1您目前的条件不符合赎罪的资格，请再次确认您的PK次数、正义值及金币是否符合。
            } else if (pc.get_PKcount() < 5) {
                pc.sendPackets(new S_ServerMessage(560)); // \f1你目前PK的次数还不需要去减轻你的恶行。
            } else {
                if (pc.getInventory().consumeItem(L1ItemId.ADENA, 700000)) {
                    pc.set_PKcount(pc.get_PKcount() - 5);
                    pc.sendPackets(new S_ServerMessage(561, String.valueOf(pc
                            .get_PKcount()))); // 你的PK次数剩余%0次。
                } else {
                    pc.sendPackets(new S_ServerMessage(189)); // \f1金币不足。
                }
            }
            // 关闭窗口
            htmlid = "";
        }

        // “お化け屋敷に入る”
        // “アルティメット バトルに参加する”または
        // “观览モードで斗技场に入る”
        // “ステータス再分配”
        else if (s.equalsIgnoreCase("ent")) {

            final int npcId = ((L1NpcInstance) obj).getNpcId();
            if (npcId == 80085) { // 幽灵之家管理人杜乌
                htmlid = this.enterHauntedHouse(pc);
            } else if (npcId == 80088) { // 宠物战管理人
                htmlid = this.enterPetMatch(pc, Integer.valueOf(s2));
            } else if ((npcId == 50038) || (npcId == 50042) || (npcId == 50029)
                    || (npcId == 50019) || (npcId == 50062)) { // 竞技场入场管理员
                htmlid = this.watchUb(pc, npcId);
            } else if (npcId == 71251) { // 回想之蜡烛看门者露露
                if (!pc.getInventory().checkItem(49142)) { // 回忆蜡烛
                    pc.sendPackets(new S_ServerMessage(1290)); // 没有角色初始化所需要的道具。
                    return;
                }
                final L1SkillUse l1skilluse = new L1SkillUse();
                l1skilluse.handleCommands(pc, CANCELLATION, pc.getId(),
                        pc.getX(), pc.getY(), null, 0, L1SkillUse.TYPE_LOGIN);
                pc.getInventory().takeoffEquip(945); // 牛のpolyIdで装备を全部外す。
                L1Teleport.teleport(pc, 32737, 32789, (short) 997, 4, false);
                final int initStatusPoint = 75 + pc.getElixirStats();
                int pcStatusPoint = pc.getBaseStr() // 角色基本力量
                        + pc.getBaseInt() // 角色基本智力
                        + pc.getBaseWis() // 角色基本精神
                        + pc.getBaseDex() // 角色基本敏捷
                        + pc.getBaseCon() // 角色基本体质
                        + pc.getBaseCha(); // 角色基本魅力
                if (pc.getLevel() > 50) {
                    pcStatusPoint += (pc.getLevel() - 50 - pc.getBonusStats());
                }
                final int diff = pcStatusPoint - initStatusPoint;
                /**
                 * [50级以上] 目前点数 - 初始点数 = 人物应有等级 - 50 -> 人物应有等级 = 50 + (目前点数 -
                 * 初始点数)
                 */
                int maxLevel = 1;

                if (diff > 0) {
                    // 最高到99级:也就是?不支援转生
                    maxLevel = Math.min(50 + diff, 99);
                } else {
                    maxLevel = pc.getLevel();
                }

                pc.setTempMaxLevel(maxLevel);
                pc.setTempLevel(1);
                pc.setInCharReset(true);
                pc.sendPackets(new S_CharReset(pc));
            } else {
                htmlid = this.enterUb(pc, npcId);
            }
        }

        // UB关连
        else if (s.equalsIgnoreCase("par")) { // UB关连“参加无限大战” 副管理人经由
            htmlid = this.enterUb(pc, ((L1NpcInstance) obj).getNpcId());
        }

        // UB关连
        else if (s.equalsIgnoreCase("info")) { // “确认无限大战比赛情报”
            htmlid = "colos2";
        }

        // UB关连
        else if (s.equalsIgnoreCase("sco")) { // UB关连“阅览前十名的名单”
            htmldata = new String[10];
            htmlid = "colos3";
        }

        // 加速师
        else if (s.equalsIgnoreCase("haste")) {
            final L1NpcInstance l1npcinstance = (L1NpcInstance) obj;
            final int npcid = l1npcinstance.getNpcTemplate().get_npcId();
            if (npcid == 70514) {
                pc.sendPackets(new S_ServerMessage(183)); // \f1你的腿得到新的能量。
                pc.sendPackets(new S_SkillHaste(pc.getId(), 1, 1600));
                pc.broadcastPacket(new S_SkillHaste(pc.getId(), 1, 0));
                pc.sendPackets(new S_SkillSound(pc.getId(), 755));
                pc.broadcastPacket(new S_SkillSound(pc.getId(), 755));
                pc.setMoveSpeed(1);
                pc.setSkillEffect(STATUS_HASTE, 1600 * 1000);
                htmlid = ""; // 关闭窗口
            }
        }

        // 变身专门家
        else if (s.equalsIgnoreCase("skeleton nbmorph")) {
            this.poly(client, 2374);
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("lycanthrope nbmorph")) {
            this.poly(client, 3874);
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("shelob nbmorph")) {
            this.poly(client, 95);
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("ghoul nbmorph")) {
            this.poly(client, 3873);
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("ghast nbmorph")) {
            this.poly(client, 3875);
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("atuba orc nbmorph")) {
            this.poly(client, 3868);
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("skeleton axeman nbmorph")) {
            this.poly(client, 2376);
            htmlid = ""; // 关闭窗口
        } else if (s.equalsIgnoreCase("troll nbmorph")) {
            this.poly(client, 3878);
            htmlid = ""; // 关闭窗口
        }

        // 堕落的灵魂
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71095) {
            if (s.equalsIgnoreCase("teleport evil-dungeon")) { // 往邪念地监
                boolean find = false;
                for (final Object objs : L1World.getInstance()
                        .getVisibleObjects(306).values()) {
                    if (objs instanceof L1PcInstance) {
                        final L1PcInstance _pc = (L1PcInstance) objs;
                        if (_pc != null) {
                            find = true;
                            htmlid = "csoulqn"; // 你的邪念还不够！
                            break;
                        }
                    }
                }
                if (!find) {
                    final L1Quest quest = pc.getQuest();
                    final int lv50_step = quest.get_step(L1Quest.QUEST_LEVEL50);
                    if (lv50_step == L1Quest.QUEST_END) {
                        htmlid = "csoulq3";
                    } else if (lv50_step >= 3) {
                        L1Teleport.teleport(pc, 32747, 32799, (short) 306, 6,
                                true);
                    } else {
                        htmlid = "csoulq2";
                    }
                }
            }
        }

        // 卡瑞 - 卡瑞的祝福
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 81279) {
            if (s.equalsIgnoreCase("a")) {
                // 卡瑞的祝福已经环绕整个身躯
                L1BuffUtil.effectBlessOfDragonSlayer(pc, EFFECT_BLESS_OF_CRAY,
                        2400, 7681);
                htmlid = "grayknight2";
            }
        }

        // 受咀咒的巫女莎尔 - 莎尔的祝福
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 81292) {
            if (s.equalsIgnoreCase("a")) {
                // 巫女莎尔的祝福缠绕着整个身体。
                L1BuffUtil.effectBlessOfDragonSlayer(pc, EFFECT_BLESS_OF_SAELL,
                        2400, 7680);
                htmlid = "";
            }
        }

        // 诺曼阿吐巴
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71038) {
            // “接受信件”
            if (s.equalsIgnoreCase("A")) {
                final L1NpcInstance npc = (L1NpcInstance) obj;
                final L1ItemInstance item = pc.getInventory().storeItem(41060,
                        1); // 诺曼阿吐巴的信
                final String npcName = npc.getNpcTemplate().get_name();
                final String itemName = item.getItem().getName();
                pc.sendPackets(new S_ServerMessage(143, npcName, itemName)); // \f1%0%s
                                                                             // 给你
                                                                             // %1%o
                                                                             // 。
                htmlid = "orcfnoname9";
            }
            // “我要停止调查”
            else if (s.equalsIgnoreCase("Z")) {
                if (pc.getInventory().consumeItem(41060, 1)) {
                    htmlid = "orcfnoname11";
                }
            }
        }

        // 芙邬都达玛拉
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71039) {
            // “请传送我过去”
            if (s.equalsIgnoreCase("teleportURL")) {
                htmlid = "orcfbuwoo2";
            }
        }

        // 调查团长 诺亚阿吐巴
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71040) {
            // “我试试看”
            if (s.equalsIgnoreCase("A")) {
                final L1NpcInstance npc = (L1NpcInstance) obj;
                final L1ItemInstance item = pc.getInventory().storeItem(41065,
                        1); // 死亡之树调查书：诺亚阿吐巴
                final String npcName = npc.getNpcTemplate().get_name();
                final String itemName = item.getItem().getName();
                pc.sendPackets(new S_ServerMessage(143, npcName, itemName)); // \f1%0%s
                                                                             // 给你
                                                                             // %1%o
                                                                             // 。
                htmlid = "orcfnoa4";
            }
            // “我要停止调查”
            else if (s.equalsIgnoreCase("Z")) {
                if (pc.getInventory().consumeItem(41065, 1)) {
                    htmlid = "orcfnoa7";
                }
            }
        }

        // 弧邬牟那鲁加
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71041) {
            // “帮助调查”
            if (s.equalsIgnoreCase("A")) {
                final L1NpcInstance npc = (L1NpcInstance) obj;
                final L1ItemInstance item = pc.getInventory().storeItem(41064,
                        1); // 妖魔调查书：弧邬牟那鲁加
                final String npcName = npc.getNpcTemplate().get_name();
                final String itemName = item.getItem().getName();
                pc.sendPackets(new S_ServerMessage(143, npcName, itemName)); // \f1%0%s
                                                                             // 给你
                                                                             // %1%o
                                                                             // 。
                htmlid = "orcfhuwoomo4";
            }
            // “我要停止调查”
            else if (s.equalsIgnoreCase("Z")) {
                if (pc.getInventory().consumeItem(41064, 1)) {
                    htmlid = "orcfhuwoomo6";
                }
            }
        }

        // 巴库摩那鲁加
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71042) {
            // “帮助调查”
            if (s.equalsIgnoreCase("A")) {
                final L1NpcInstance npc = (L1NpcInstance) obj;
                final L1ItemInstance item = pc.getInventory().storeItem(41062,
                        1); // 人类调查书：巴库摩那鲁加
                final String npcName = npc.getNpcTemplate().get_name();
                final String itemName = item.getItem().getName();
                pc.sendPackets(new S_ServerMessage(143, npcName, itemName)); // \f1%0%s
                                                                             // 给你
                                                                             // %1%o
                                                                             // 。
                htmlid = "orcfbakumo4";
            }
            // “我要停止调查”
            else if (s.equalsIgnoreCase("Z")) {
                if (pc.getInventory().consumeItem(41062, 1)) {
                    htmlid = "orcfbakumo6";
                }
            }
        }

        // 可普都达玛拉
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71043) {
            // “帮助调查”
            if (s.equalsIgnoreCase("A")) {
                final L1NpcInstance npc = (L1NpcInstance) obj;
                final L1ItemInstance item = pc.getInventory().storeItem(41063,
                        1); // 精灵调查书：可普都达玛拉
                final String npcName = npc.getNpcTemplate().get_name();
                final String itemName = item.getItem().getName();
                pc.sendPackets(new S_ServerMessage(143, npcName, itemName)); // \f1%0%s
                                                                             // 给你
                                                                             // %1%o
                                                                             // 。
                htmlid = "orcfbuka4";
            }
            // “我要停止调查”
            else if (s.equalsIgnoreCase("Z")) {
                if (pc.getInventory().consumeItem(41063, 1)) {
                    htmlid = "orcfbuka6";
                }
            }
        }

        // 卡麦都达玛拉
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71044) {
            // “帮助调查”
            if (s.equalsIgnoreCase("A")) {
                final L1NpcInstance npc = (L1NpcInstance) obj;
                final L1ItemInstance item = pc.getInventory().storeItem(41061,
                        1); // 妖精调查书：卡麦都达玛拉
                final String npcName = npc.getNpcTemplate().get_name();
                final String itemName = item.getItem().getName();
                pc.sendPackets(new S_ServerMessage(143, npcName, itemName)); // \f1%0%s
                                                                             // 给你
                                                                             // %1%o
                                                                             // 。
                htmlid = "orcfkame4";
            }
            // “我要停止调查”
            else if (s.equalsIgnoreCase("Z")) {
                if (pc.getInventory().consumeItem(41061, 1)) {
                    htmlid = "orcfkame6";
                }
            }
        }

        // 普哇鲁
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71078) {
            // “要进去.”
            if (s.equalsIgnoreCase("teleportURL")) {
                htmlid = "usender2";
            }
        }

        // 治安团长 阿弥树
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71080) {
            // “我愿意帮忙”
            if (s.equalsIgnoreCase("teleportURL")) {
                htmlid = "amisoo2";
            }
        }

        // 扭曲的空间
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80048) {
            // “回头”
            if (s.equalsIgnoreCase("2")) {
                htmlid = ""; // 关闭窗口
            }
        }

        // 反叛者
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80049) {
            // “接受卡亥的意志”
            if (s.equalsIgnoreCase("1")) {
                if (pc.getKarma() <= -10000000) {
                    pc.setKarma(1000000);
                    pc.sendPackets(new S_ServerMessage(1078)); // 炎魔的笑声重击我的脑部。
                    htmlid = "betray13";
                }
            }
        }

        // 火焰之影的执政官
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80050) {
            // “将我的灵魂献给火焰之影大人”
            if (s.equalsIgnoreCase("1")) {
                htmlid = "meet105";
            }
            // “以我的灵魂做为代价誓忠于火焰之影大人”
            else if (s.equalsIgnoreCase("2")) {
                if (pc.getInventory().checkItem(40718)) { // 血石碎片
                    htmlid = "meet106";
                } else {
                    htmlid = "meet110";
                }
            }
            // “贡献１个血石碎片”
            else if (s.equalsIgnoreCase("a")) {
                if (pc.getInventory().consumeItem(40718, 1)) {
                    pc.addKarma((int) (-100 * Config.RATE_KARMA));
                    // 执政官脸上的表情越来越和善了。
                    pc.sendPackets(new S_ServerMessage(1079));
                    htmlid = "meet107";
                } else {
                    htmlid = "meet104";
                }
            }
            // “贡献１０个血石碎片”
            else if (s.equalsIgnoreCase("b")) {
                if (pc.getInventory().consumeItem(40718, 10)) {
                    pc.addKarma((int) (-1000 * Config.RATE_KARMA));
                    // 执政官脸上的表情越来越和善了。
                    pc.sendPackets(new S_ServerMessage(1079));
                    htmlid = "meet108";
                } else {
                    htmlid = "meet104";
                }
            }
            // “贡献１００个血石碎片”
            else if (s.equalsIgnoreCase("c")) {
                if (pc.getInventory().consumeItem(40718, 100)) {
                    pc.addKarma((int) (-10000 * Config.RATE_KARMA));
                    // 执政官脸上的表情越来越和善了。
                    pc.sendPackets(new S_ServerMessage(1079));
                    htmlid = "meet109";
                } else {
                    htmlid = "meet104";
                }
            }
            // “请让我觐见火焰之影大人”
            else if (s.equalsIgnoreCase("d")) {
                if (pc.getInventory().checkItem(40615) // 暗影神殿2楼钥匙
                        || pc.getInventory().checkItem(40616)) { // 暗影神殿3楼钥匙
                    htmlid = "";
                } else {
                    L1Teleport.teleport(pc, 32683, 32895, (short) 608, 5, true);
                }
            }
        }

        // 火焰之影的军师
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80052) {
            // 请赐给我力量
            if (s.equalsIgnoreCase("a")) {
                if (pc.hasSkillEffect(STATUS_CURSE_BARLOG)) { // 火焰之影的烙印
                    pc.killSkillEffectTimer(STATUS_CURSE_BARLOG);
                }
                pc.sendPackets(new S_SkillSound(pc.getId(), 750));
                pc.broadcastPacket(new S_SkillSound(pc.getId(), 750));
                pc.sendPackets(new S_SkillIconAura(221, 1020, 2)); // 火焰之影的烙印
                pc.setSkillEffect(STATUS_CURSE_BARLOG, 1020 * 1000);
                pc.sendPackets(new S_ServerMessage(1127));
                htmlid = "";
            }
        }

        // 火焰之影的铁匠
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80053) {
            // “材料すべてを用意できました”
            if (s.equalsIgnoreCase("a")) {
                // バルログのツーハンド ソード / ヤヒの锻冶屋
                int aliceMaterialId = 0;
                int karmaLevel = 0;
                int[] material = null;
                int[] count = null;
                int createItem = 0;
                String successHtmlId = null;
                String htmlId = null;

                final int[] aliceMaterialIdList = { 40991, 196, 197, 198, 199,
                        200, 201, 202 };
                final int[] karmaLevelList = { -1, -2, -3, -4, -5, -6, -7, -8 };
                final int[][] materialsList = { { 40995, 40718, 40991 },
                        { 40997, 40718, 196 }, { 40990, 40718, 197 },
                        { 40994, 40718, 198 }, { 40993, 40718, 199 },
                        { 40998, 40718, 200 }, { 40996, 40718, 201 },
                        { 40992, 40718, 202 } };
                final int[][] countList = { { 100, 100, 1 }, { 100, 100, 1 },
                        { 100, 100, 1 }, { 50, 100, 1 }, { 50, 100, 1 },
                        { 50, 100, 1 }, { 10, 100, 1 }, { 10, 100, 1 } };
                final int[] createItemList = { 196, 197, 198, 199, 200, 201,
                        202, 203 };
                final String[] successHtmlIdList = { "alice_1", "alice_2",
                        "alice_3", "alice_4", "alice_5", "alice_6", "alice_7",
                        "alice_8" };
                final String[] htmlIdList = { "aliceyet", "alice_1", "alice_2",
                        "alice_3", "alice_4", "alice_5", "alice_5", "alice_7" };

                for (int i = 0; i < aliceMaterialIdList.length; i++) {
                    if (pc.getInventory().checkItem(aliceMaterialIdList[i])) {
                        aliceMaterialId = aliceMaterialIdList[i];
                        karmaLevel = karmaLevelList[i];
                        material = materialsList[i];
                        count = countList[i];
                        createItem = createItemList[i];
                        successHtmlId = successHtmlIdList[i];
                        htmlId = htmlIdList[i];
                        break;
                    }
                }

                if (aliceMaterialId == 0) {
                    htmlid = "alice_no";
                } else if (aliceMaterialId == 203) {
                    htmlid = "alice_8";
                } else {
                    if (pc.getKarmaLevel() <= karmaLevel) {
                        materials = material;
                        counts = count;
                        createitem = new int[] { createItem };
                        createcount = new int[] { 1 };
                        success_htmlid = successHtmlId;
                        failure_htmlid = "alice_no";
                    } else {
                        htmlid = htmlId;
                    }
                }
            }
        }

        // 火焰之影的辅佐官
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80055) {
            final L1NpcInstance npc = (L1NpcInstance) obj;
            htmlid = this.getYaheeAmulet(pc, npc, s);
        }

        // 业の管理者
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80056) {
            final L1NpcInstance npc = (L1NpcInstance) obj;
            if (pc.getKarma() <= -10000000) {
                this.getBloodCrystalByKarma(pc, npc, s);
            }
            htmlid = "";
        }

        // 次元之门(炎魔谒见所)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80063) {
            // “进入次元之门”
            if (s.equalsIgnoreCase("a")) {
                if (pc.getInventory().checkItem(40921)) { // 元素之支配者
                    L1Teleport.teleport(pc, 32674, 32832, (short) 603, 2, true);
                } else {
                    htmlid = "gpass02";
                }
            }
        }

        // 炎魔的执政官
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80064) {
            // “我的主人永远都是炎魔大人”
            if (s.equalsIgnoreCase("1")) {
                htmlid = "meet005";
            }
            // “以灵魂效忠炎魔大人”
            else if (s.equalsIgnoreCase("2")) {
                if (pc.getInventory().checkItem(40678)) { // 灵魂石碎片
                    htmlid = "meet006";
                } else {
                    htmlid = "meet010";
                }
            }
            // “贡献１个灵魂石碎片”
            else if (s.equalsIgnoreCase("a")) {
                if (pc.getInventory().consumeItem(40678, 1)) {
                    pc.addKarma((int) (100 * Config.RATE_KARMA));
                    // 炎魔的笑声重击我的脑部。
                    pc.sendPackets(new S_ServerMessage(1078));
                    htmlid = "meet007";
                } else {
                    htmlid = "meet004";
                }
            }
            // “贡献１０个灵魂石碎片”
            else if (s.equalsIgnoreCase("b")) {
                if (pc.getInventory().consumeItem(40678, 10)) {
                    pc.addKarma((int) (1000 * Config.RATE_KARMA));
                    // 炎魔的笑声重击我的脑部。
                    pc.sendPackets(new S_ServerMessage(1078));
                    htmlid = "meet008";
                } else {
                    htmlid = "meet004";
                }
            }
            // “贡献１００个灵魂石碎片”
            else if (s.equalsIgnoreCase("c")) {
                if (pc.getInventory().consumeItem(40678, 100)) {
                    pc.addKarma((int) (10000 * Config.RATE_KARMA));
                    // 炎魔的笑声重击我的脑部。
                    pc.sendPackets(new S_ServerMessage(1078));
                    htmlid = "meet009";
                } else {
                    htmlid = "meet004";
                }
            }
            // “觐见炎魔大人”
            else if (s.equalsIgnoreCase("d")) {
                if (pc.getInventory().checkItem(40909) // 土之通行证
                        || pc.getInventory().checkItem(40910) // 水之通行证
                        || pc.getInventory().checkItem(40911) // 火之通行证
                        || pc.getInventory().checkItem(40912) // 风之通行证
                        || pc.getInventory().checkItem(40913) // 土之印记
                        || pc.getInventory().checkItem(40914) // 水之印记
                        || pc.getInventory().checkItem(40915) // 火之印记
                        || pc.getInventory().checkItem(40916) // 风之印记
                        || pc.getInventory().checkItem(40917) // 土之支配者
                        || pc.getInventory().checkItem(40918) // 水之支配者
                        || pc.getInventory().checkItem(40919) // 火之支配者
                        || pc.getInventory().checkItem(40920) // 风之支配者
                        || pc.getInventory().checkItem(40921)) { // 元素之支配者
                    htmlid = "";
                } else {
                    L1Teleport.teleport(pc, 32674, 32832, (short) 602, 2, true);
                }
            }
        }

        // 反叛者
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80066) {
            // “接受卡亥的意志”
            if (s.equalsIgnoreCase("1")) {
                if (pc.getKarma() >= 10000000) {
                    pc.setKarma(-1000000);
                    // 执政官脸上的表情越来越和善了。
                    pc.sendPackets(new S_ServerMessage(1079));
                    htmlid = "betray03";
                }
            }
        }

        // 炎魔的辅佐官
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80071) {
            final L1NpcInstance npc = (L1NpcInstance) obj;
            htmlid = this.getBarlogEarring(pc, npc, s);
        } else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80073) { // 炎魔的军师
            if (s.equalsIgnoreCase("a")) { // 请给我力量
                if (pc.hasSkillEffect(STATUS_CURSE_YAHEE)) { // 炎魔的烙印
                    pc.killSkillEffectTimer(STATUS_CURSE_YAHEE);
                }
                pc.sendPackets(new S_SkillSound(pc.getId(), 750));
                pc.broadcastPacket(new S_SkillSound(pc.getId(), 750));
                pc.sendPackets(new S_SkillIconAura(221, 1020, 1)); // 炎魔的烙印
                pc.setSkillEffect(STATUS_CURSE_YAHEE, 1020 * 1000);
                pc.sendPackets(new S_ServerMessage(1127));
                htmlid = "";
            }
        }

        // 炎魔的铁匠
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80072) {
            String sEquals = null;
            int karmaLevel = 0;
            int[] material = null;
            int[] count = null;
            int createItem = 0;
            String failureHtmlId = null;
            String htmlId = null;

            final String[] sEqualsList = { "0", "1", "2", "3", "4", "5", "6",
                    "7", "8", "a", "b", "c", "d", "e", "f", "g", "h" };
            final String[] htmlIdList = { "lsmitha", "lsmithb", "lsmithc",
                    "lsmithd", "lsmithe", "", "lsmithf", "lsmithg", "lsmithh" };
            final int[] karmaLevelList = { 1, 2, 3, 4, 5, 6, 7, 8 };
            final int[][] materialsList = { { 20158, 40669, 40678 },
                    { 20144, 40672, 40678 }, { 20075, 40671, 40678 },
                    { 20183, 40674, 40678 }, { 20190, 40674, 40678 },
                    { 20078, 40674, 40678 }, { 20078, 40670, 40678 },
                    { 40719, 40673, 40678 } };
            final int[][] countList = { { 1, 50, 100 }, { 1, 50, 100 },
                    { 1, 50, 100 }, { 1, 20, 100 }, { 1, 40, 100 },
                    { 1, 5, 100 }, { 1, 1, 100 }, { 1, 1, 100 } };
            final int[] createItemList = { 20083, 20131, 20069, 20179, 20209,
                    20290, 20261, 20031 };
            final String[] failureHtmlIdList = { "lsmithaa", "lsmithbb",
                    "lsmithcc", "lsmithdd", "lsmithee", "lsmithff", "lsmithgg",
                    "lsmithhh" };
            for (int i = 0; i < sEqualsList.length; i++) {
                if (s.equalsIgnoreCase(sEqualsList[i])) {
                    sEquals = sEqualsList[i];
                    if (i <= 8) {
                        htmlId = htmlIdList[i];
                    } else if (i > 8) {
                        karmaLevel = karmaLevelList[i - 9];
                        material = materialsList[i - 9];
                        count = countList[i - 9];
                        createItem = createItemList[i - 9];
                        failureHtmlId = failureHtmlIdList[i - 9];
                    }
                    break;
                }
            }
            if (s.equalsIgnoreCase(sEquals)) {
                if ((karmaLevel != 0) && (pc.getKarmaLevel() >= karmaLevel)) {
                    materials = material;
                    counts = count;
                    createitem = new int[] { createItem };
                    createcount = new int[] { 1 };
                    success_htmlid = "";
                    failure_htmlid = failureHtmlId;
                } else {
                    htmlid = htmlId;
                }
            }
        }

        // 业の管理者
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80074) {
            final L1NpcInstance npc = (L1NpcInstance) obj;
            if (pc.getKarma() >= 10000000) {
                this.getSoulCrystalByKarma(pc, npc, s);
            }
            htmlid = "";
        }

        // 爱尔芬
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80057) {
            htmlid = this.karmaLevelToHtmlId(pc.getKarmaLevel());
            htmldata = new String[] { String.valueOf(pc.getKarmaPercent()) };
        }

        // 次元之门(土风水火)
        else if ((((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80059)
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80060)
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80061)
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80062)) {
            htmlid = this.talkToDimensionDoor(pc, (L1NpcInstance) obj, s);
        }

        // 南瓜怪
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 81124) {
            if (s.equalsIgnoreCase("1")) {
                this.poly(client, 4002);
                htmlid = ""; // 关闭窗口
            } else if (s.equalsIgnoreCase("2")) {
                this.poly(client, 4004);
                htmlid = ""; // 关闭窗口
            } else if (s.equalsIgnoreCase("3")) {
                this.poly(client, 4950);
                htmlid = ""; // 关闭窗口
            }
        }

        // 任务关连
        // 一般任务 / 莱拉(燃柳图腾任务)
        else if (s.equalsIgnoreCase("contract1")) { // 与莱拉订定契约
            pc.getQuest().set_step(L1Quest.QUEST_LYRA, 1);
            htmlid = "lyraev2";
        } else if (s.equalsIgnoreCase("contract1yes") || // 领取赏金和重新订契约 Yes
                s.equalsIgnoreCase("contract1no")) { // 领取赏金和终止契约 No

            if (s.equalsIgnoreCase("contract1yes")) {
                htmlid = "lyraev5";
            } else if (s.equalsIgnoreCase("contract1no")) {
                pc.getQuest().set_step(L1Quest.QUEST_LYRA, 0);
                htmlid = "lyraev4";
            }
            int totem = 0;
            if (pc.getInventory().checkItem(40131)) { // 甘地图腾
                totem++;
            }
            if (pc.getInventory().checkItem(40132)) { // 那鲁加图腾
                totem++;
            }
            if (pc.getInventory().checkItem(40133)) { // 都达玛拉图腾
                totem++;
            }
            if (pc.getInventory().checkItem(40134)) { // 罗孚图腾
                totem++;
            }
            if (pc.getInventory().checkItem(40135)) { // 阿吐巴图腾
                totem++;
            }
            if (totem != 0) {
                materials = new int[totem];
                counts = new int[totem];
                createitem = new int[totem];
                createcount = new int[totem];

                totem = 0;
                if (pc.getInventory().checkItem(40131)) { // 甘地图腾
                    final L1ItemInstance l1iteminstance = pc.getInventory()
                            .findItemId(40131);
                    final int i1 = l1iteminstance.getCount();
                    materials[totem] = 40131;
                    counts[totem] = i1;
                    createitem[totem] = L1ItemId.ADENA;
                    createcount[totem] = i1 * 50;
                    totem++;
                }
                if (pc.getInventory().checkItem(40132)) { // 那鲁加图腾
                    final L1ItemInstance l1iteminstance = pc.getInventory()
                            .findItemId(40132);
                    final int i1 = l1iteminstance.getCount();
                    materials[totem] = 40132;
                    counts[totem] = i1;
                    createitem[totem] = L1ItemId.ADENA;
                    createcount[totem] = i1 * 100;
                    totem++;
                }
                if (pc.getInventory().checkItem(40133)) { // 都达玛拉图腾
                    final L1ItemInstance l1iteminstance = pc.getInventory()
                            .findItemId(40133);
                    final int i1 = l1iteminstance.getCount();
                    materials[totem] = 40133;
                    counts[totem] = i1;
                    createitem[totem] = L1ItemId.ADENA;
                    createcount[totem] = i1 * 50;
                    totem++;
                }
                if (pc.getInventory().checkItem(40134)) { // 罗孚图腾
                    final L1ItemInstance l1iteminstance = pc.getInventory()
                            .findItemId(40134);
                    final int i1 = l1iteminstance.getCount();
                    materials[totem] = 40134;
                    counts[totem] = i1;
                    createitem[totem] = L1ItemId.ADENA;
                    createcount[totem] = i1 * 30;
                    totem++;
                }
                if (pc.getInventory().checkItem(40135)) { // 阿吐巴图腾
                    final L1ItemInstance l1iteminstance = pc.getInventory()
                            .findItemId(40135);
                    final int i1 = l1iteminstance.getCount();
                    materials[totem] = 40135;
                    counts[totem] = i1;
                    createitem[totem] = L1ItemId.ADENA;
                    createcount[totem] = i1 * 200;
                    totem++;
                }
            }
        }

        // 查询最近的物价倍率
        else if (s.equalsIgnoreCase("pandora6") // 潘朵拉(说话之岛 杂货商)
                || s.equalsIgnoreCase("cold6") // 库德(说话之岛 烟火商)
                || s.equalsIgnoreCase("balsim3") // 巴辛(说话之岛 妖魔商)
                || s.equalsIgnoreCase("arieh6") // 70015: 艾莉雅(肯特 烟火商)
                || s.equalsIgnoreCase("andyn3") // 70016: 安迪(肯特 武器商)
                || s.equalsIgnoreCase("ysorya3") // 70018: 索拉雅(肯特 杂货商)
                || s.equalsIgnoreCase("luth3") // 70021: 露西(古鲁丁 杂货商)
                || s.equalsIgnoreCase("catty3") // 70024: 凯蒂(古鲁丁 武器商)
                || s.equalsIgnoreCase("mayer3") // 70030: 迈尔(奇岩 杂货商)
                || s.equalsIgnoreCase("vergil3") // 70032: 范吉尔(奇岩 防具商)
                || s.equalsIgnoreCase("stella6") // 70036: 史堤拉(奇岩 烟火商)
                || s.equalsIgnoreCase("ralf6") // 70044: 瑞福(威顿 武器商)
                || s.equalsIgnoreCase("berry6") // 70045: 蓓莉(威顿 杂货商)
                || s.equalsIgnoreCase("jin6") // 70046: 珍(威顿 烟火商)
                || s.equalsIgnoreCase("defman3") // 70047: 戴夫曼(亚丁 武器商)
                || s.equalsIgnoreCase("mellisa3") // 70052: 马夏(亚丁 杂货商)
                || s.equalsIgnoreCase("mandra3") // 70061: 曼德拉(欧瑞 武器商)
                || s.equalsIgnoreCase("bius3") // 70063: 毕伍斯(欧瑞 杂货商)
                || s.equalsIgnoreCase("momo6") // 70069: 摩摩(风木 烟火商)
                || s.equalsIgnoreCase("ashurEv7") // 70071: 亚修(绿洲 杂货商)
                || s.equalsIgnoreCase("elmina3") // 70072: 艾米娜(风木 杂货商)
                || s.equalsIgnoreCase("glen3") // 70073: 格林(银骑士 武器商)
                || s.equalsIgnoreCase("mellin3") // 70074: 梅林(银骑士 杂货商)
                || s.equalsIgnoreCase("orcm6") // 70078: 欧肯(燃柳 烟火商)
                || s.equalsIgnoreCase("jackson3") // 70079: 杰克森(燃柳 杂货商)
                || s.equalsIgnoreCase("britt3") // 70082: 比特(海音 杂货商)
                || s.equalsIgnoreCase("old6") // 70085: 欧得(海音 烟火商)
                || s.equalsIgnoreCase("shivan3") // 70083: 须凡(海音 武器商)
        ) {
            htmlid = s;
            final int npcid = ((L1NpcInstance) obj).getNpcTemplate()
                    .get_npcId();
            final int taxRatesCastle = L1CastleLocation
                    .getCastleTaxRateByNpcId(npcid);
            htmldata = new String[] { String.valueOf(taxRatesCastle) };
        }

        // 村镇（登记村民）
        else if (s.equalsIgnoreCase("set")) {
            if (obj instanceof L1NpcInstance) {
                final int npcid = ((L1NpcInstance) obj).getNpcTemplate()
                        .get_npcId();
                final int town_id = L1TownLocation.getTownIdByNpcid(npcid);

                if ((town_id >= 1) && (town_id <= 10)) {
                    if (pc.getHomeTownId() == -1) {
                        // \f1想要新登录村庄，必须有暂缓的时间，请下次再登录。
                        pc.sendPackets(new S_ServerMessage(759));
                        htmlid = "";
                    } else if (pc.getHomeTownId() > 0) {
                        // 已经登录
                        if (pc.getHomeTownId() != town_id) {
                            final L1Town town = TownTable.getInstance()
                                    .getTownTable(pc.getHomeTownId());
                            if (town != null) {
                                // 目前你所登录的村庄为%0。
                                pc.sendPackets(new S_ServerMessage(758, town
                                        .get_name()));
                            }
                            htmlid = "";
                        } else {
                            // 不可能得？
                            htmlid = "";
                        }
                    } else if (pc.getHomeTownId() == 0) {
                        // 登录
                        if (pc.getLevel() < 10) {
                            // \f1想要在村庄登录必须在10等级以上。
                            pc.sendPackets(new S_ServerMessage(757));
                            htmlid = "";
                        } else {
                            final int level = pc.getLevel();
                            final int cost = level * level * 10;
                            if (pc.getInventory().consumeItem(L1ItemId.ADENA,
                                    cost)) {
                                pc.setHomeTownId(town_id);
                                pc.setContribution(0); // 贡献
                                pc.save();
                            } else {
                                // \f1%0不足%s。
                                pc.sendPackets(new S_ServerMessage(337, "$4"));
                            }
                            htmlid = "";
                        }
                    }
                }
            }
        }

        // 村镇（取消登记）
        else if (s.equalsIgnoreCase("clear")) {
            if (obj instanceof L1NpcInstance) {
                final int npcid = ((L1NpcInstance) obj).getNpcTemplate()
                        .get_npcId();
                final int town_id = L1TownLocation.getTownIdByNpcid(npcid);
                if (town_id > 0) {
                    if (pc.getHomeTownId() > 0) {
                        if (pc.getHomeTownId() == town_id) {
                            pc.setHomeTownId(-1);
                            pc.setContribution(0); // 清除贡献度
                            pc.save();
                        } else {
                            // \f1你已经登录为所属于其他村庄。
                            pc.sendPackets(new S_ServerMessage(756));
                        }
                    }
                    htmlid = "";
                }
            }
        }

        // 村镇（确认当前村长是谁）
        else if (s.equalsIgnoreCase("ask")) {
            if (obj instanceof L1NpcInstance) {
                final int npcid = ((L1NpcInstance) obj).getNpcTemplate()
                        .get_npcId();
                final int town_id = L1TownLocation.getTownIdByNpcid(npcid);

                if ((town_id >= 1) && (town_id <= 10)) {
                    final L1Town town = TownTable.getInstance().getTownTable(
                            town_id);
                    final String leader = town.get_leader_name();
                    if ((leader != null) && (leader.length() != 0)) {
                        htmlid = "owner"; // 目前这里的村长叫"#0"
                        htmldata = new String[] { leader };
                    } else {
                        htmlid = "noowner"; // 目前这村庄内没有村长。
                    }
                }
            }
        }

        // HomeTown 各村庄 副村长 (取消副村长 for 3.3C)
        else if ((((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70534)
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70556)
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70572)
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70631)
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70663)
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70761)
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70788)
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70806)
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70830)
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70876)) {
            // 村庄顾问（有关收入的报告）
            if (s.equalsIgnoreCase("r")) {
            }
            // 村庄顾问（税率变更）
            else if (s.equalsIgnoreCase("t")) {

            }
            // 村庄顾问（接受薪资）
            else if (s.equalsIgnoreCase("c")) {

            }
        }

        // XXX日本ドロモンド
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70997) {
            // ありがとう、旅立ちます
            if (s.equalsIgnoreCase("0")) {
                final int[] item_ids = { 41146, 4, 20322, 173, 40743, };
                final int[] item_amounts = { 1, 1, 1, 1, 500, };
                for (int i = 0; i < item_ids.length; i++) {
                    final L1ItemInstance item = pc.getInventory().storeItem(
                            item_ids[i], item_amounts[i]);
                    pc.sendPackets(new S_ServerMessage(143,
                            ((L1NpcInstance) obj).getNpcTemplate().get_name(),
                            item.getLogName()));
                }
                pc.getQuest().set_step(L1Quest.QUEST_DOROMOND, 1);
                htmlid = "jpe0015";
            }
        }

        // 艾力克斯(歌唱岛)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70999) {
            // ドロモンドの绍介状を渡す
            if (s.equalsIgnoreCase("1")) {
                if (pc.getInventory().consumeItem(41146, 1)) {
                    final int[] item_ids = { 23, 20219, 20193, };
                    final int[] item_amounts = { 1, 1, 1, };
                    for (int i = 0; i < item_ids.length; i++) {
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(item_ids[i], item_amounts[i]);
                        pc.sendPackets(new S_ServerMessage(143,
                                ((L1NpcInstance) obj).getNpcTemplate()
                                        .get_name(), item.getLogName()));
                    }
                    pc.getQuest().set_step(L1Quest.QUEST_DOROMOND, 2);
                    htmlid = "";
                }
            } else if (s.equalsIgnoreCase("2")) {
                final L1ItemInstance item = pc.getInventory().storeItem(41227,
                        1); // [jp]亚历斯的介绍书
                pc.sendPackets(new S_ServerMessage(143, ((L1NpcInstance) obj)
                        .getNpcTemplate().get_name(), item.getLogName()));
                pc.getQuest().set_step(L1Quest.QUEST_AREX, L1Quest.QUEST_END);
                htmlid = "";
            }
        }

        // 潘派瑞恩(歌唱岛)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71005) {
            // アイテムを受け取る
            if (s.equalsIgnoreCase("0")) {
                if (!pc.getInventory().checkItem(41209)) { // [jp]ポピレアの依赖书
                    final L1ItemInstance item = pc.getInventory().storeItem(
                            41209, 1);
                    pc.sendPackets(new S_ServerMessage(143,
                            ((L1NpcInstance) obj).getNpcTemplate().get_name(),
                            item.getItem().getName()));
                    htmlid = ""; // 关闭窗口
                }
            }
            // アイテムを受け取る
            else if (s.equalsIgnoreCase("1")) {
                if (pc.getInventory().consumeItem(41213, 1)) {
                    final L1ItemInstance item = pc.getInventory().storeItem(
                            40029, 20);
                    pc.sendPackets(new S_ServerMessage(143,
                            ((L1NpcInstance) obj).getNpcTemplate().get_name(),
                            item.getItem().getName() + " (" + 20 + ")"));
                    htmlid = ""; // 关闭窗口
                }
            }
        }

        // 提米(歌唱岛)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71006) {
            if (s.equalsIgnoreCase("0")) {
                if (pc.getLevel() > 25) {
                    htmlid = "jpe0057";
                } else if (pc.getInventory().checkItem(41213)) { // [jp]歌唱岛的人们
                    htmlid = "jpe0056";
                } else if (pc.getInventory().checkItem(41210) // [jp]研磨剂
                        || pc.getInventory().checkItem(41211)) { // 香菜
                    htmlid = "jpe0055";
                } else if (pc.getInventory().checkItem(41209)) { // [jp]ポピレアの依赖书
                    htmlid = "jpe0054";
                } else if (pc.getInventory().checkItem(41212)) { // [jp]特制糖果
                    htmlid = "jpe0056";
                    materials = new int[] { 41212 }; // [jp]特制糖果
                    counts = new int[] { 1 };
                    createitem = new int[] { 41213 }; // [jp]歌唱岛的人们
                    createcount = new int[] { 1 };
                } else {
                    htmlid = "jpe0057";
                }
            }
        }

        // XXX 废止 治疗师（歌う岛の中：ＨＰのみ回复）
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70512) {
            // 治疗を受ける("fullheal"でリクエストが来ることはあるのか？)
            if (s.equalsIgnoreCase("0") || s.equalsIgnoreCase("fullheal")) {
                final int hp = Random.nextInt(21) + 70;
                pc.setCurrentHp(pc.getCurrentHp() + hp);
                pc.sendPackets(new S_ServerMessage(77)); // \f1你觉得舒服多了。
                pc.sendPackets(new S_SkillSound(pc.getId(), 830));
                pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
                htmlid = ""; // 关闭窗口
            }
        }

        // 治疗师（训练场：HPMP回复）
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71037) {
            if (s.equalsIgnoreCase("0")) {
                pc.setCurrentHp(pc.getMaxHp());
                pc.setCurrentMp(pc.getMaxMp());
                pc.sendPackets(new S_ServerMessage(77)); // \f1你觉得舒服多了。
                pc.sendPackets(new S_SkillSound(pc.getId(), 830));
                pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc.getMaxHp()));
                pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc.getMaxMp()));
            }
        }
        // 治疗师（西部）
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71030) {
            if (s.equalsIgnoreCase("fullheal")) {
                if (pc.getInventory().checkItem(L1ItemId.ADENA, 5)) { // check
                    pc.getInventory().consumeItem(L1ItemId.ADENA, 5); // del
                    pc.setCurrentHp(pc.getMaxHp());
                    pc.setCurrentMp(pc.getMaxMp());
                    pc.sendPackets(new S_ServerMessage(77)); // \f1你觉得舒服多了。
                    pc.sendPackets(new S_SkillSound(pc.getId(), 830));
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(337, "$4")); // \f1%0不足%s。
                }
            }
        }

        // XXX 取消配置师 (客户端内无对话档)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71002) {
            // 取得了神奇的取消
            if (s.equalsIgnoreCase("0")) {
                if (pc.getLevel() <= 13) {
                    final L1SkillUse skillUse = new L1SkillUse();
                    skillUse.handleCommands(pc, CANCELLATION, pc.getId(),
                            pc.getX(), pc.getY(), null, 0,
                            L1SkillUse.TYPE_NPCBUFF, (L1NpcInstance) obj);
                    htmlid = ""; // 关闭窗口
                }
            }
        }

        // 卡斯金(歌唱岛)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71025) {
            if (s.equalsIgnoreCase("0")) {
                final L1ItemInstance item = pc.getInventory().storeItem(41225,
                        1); // 遗失钥匙商人的请求
                pc.sendPackets(new S_ServerMessage(143, ((L1NpcInstance) obj)
                        .getNpcTemplate().get_name(), item.getItem().getName()));
                htmlid = "jpe0083";
            }
        }

        // 路卡音(海贼岛)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71055) {
            // 嗯我知道了
            if (s.equalsIgnoreCase("0")) {
                final L1ItemInstance item = pc.getInventory().storeItem(40701,
                        1); // 小藏宝图
                pc.sendPackets(new S_ServerMessage(143, ((L1NpcInstance) obj)
                        .getNpcTemplate().get_name(), item.getItem().getName()));
                pc.getQuest().set_step(L1Quest.QUEST_LUKEIN1, 1);
                htmlid = "lukein8";
            } else if (s.equalsIgnoreCase("2")) {
                htmlid = "lukein12";
                pc.getQuest().set_step(L1Quest.QUEST_RESTA, 3);
            }
        }

        // 小箱子-1番目
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71063) { // 小箱子
            if (s.equalsIgnoreCase("0")) {
                materials = new int[] { 40701 }; // 小藏宝图
                counts = new int[] { 1 };
                createitem = new int[] { 40702 }; // 小袋子
                createcount = new int[] { 1 };
                htmlid = "maptbox1";
                pc.getQuest().set_end(L1Quest.QUEST_TBOX1);
                final int[] nextbox = { 1, 2, 3 };
                final int pid = Random.nextInt(nextbox.length);
                final int nb = nextbox[pid];
                if (nb == 1) { // b地点
                    pc.getQuest().set_step(L1Quest.QUEST_LUKEIN1, 2);
                } else if (nb == 2) { // c地点
                    pc.getQuest().set_step(L1Quest.QUEST_LUKEIN1, 3);
                } else if (nb == 3) { // d地点
                    pc.getQuest().set_step(L1Quest.QUEST_LUKEIN1, 4);
                }
            }
        }

        // 小箱子-2番目
        else if ((((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71064) // 小箱子
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71065) // 小箱子
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71066)) { // 小箱子
            if (s.equalsIgnoreCase("0")) {
                materials = new int[] { 40701 }; // 小藏宝图
                counts = new int[] { 1 };
                createitem = new int[] { 40702 }; // 小袋子
                createcount = new int[] { 1 };
                htmlid = "maptbox1";
                pc.getQuest().set_end(L1Quest.QUEST_TBOX2);
                final int[] nextbox2 = { 1, 2, 3, 4, 5, 6 };
                final int pid = Random.nextInt(nextbox2.length);
                final int nb2 = nextbox2[pid];
                if (nb2 == 1) { // e地点
                    pc.getQuest().set_step(L1Quest.QUEST_LUKEIN1, 5);
                } else if (nb2 == 2) { // f地点
                    pc.getQuest().set_step(L1Quest.QUEST_LUKEIN1, 6);
                } else if (nb2 == 3) { // g地点
                    pc.getQuest().set_step(L1Quest.QUEST_LUKEIN1, 7);
                } else if (nb2 == 4) { // h地点
                    pc.getQuest().set_step(L1Quest.QUEST_LUKEIN1, 8);
                } else if (nb2 == 5) { // i地点
                    pc.getQuest().set_step(L1Quest.QUEST_LUKEIN1, 9);
                } else if (nb2 == 6) { // j地点
                    pc.getQuest().set_step(L1Quest.QUEST_LUKEIN1, 10);
                }
            }
        }

        // 希米哲(海贼岛)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71056) {
            // 我会查出您儿子的下落
            if (s.equalsIgnoreCase("a")) {
                pc.getQuest().set_step(L1Quest.QUEST_SIMIZZ, 1);
                htmlid = "SIMIZZ7";
            } else if (s.equalsIgnoreCase("b")) {
                if (pc.getInventory().checkItem(40661)
                        && pc.getInventory().checkItem(40662)
                        && pc.getInventory().checkItem(40663)) {
                    htmlid = "SIMIZZ8";
                    pc.getQuest().set_step(L1Quest.QUEST_SIMIZZ, 2);
                    materials = new int[] { 40661, 40662, 40663 }; // 儿子的遗骸`儿子的肖像画`儿子的信
                    counts = new int[] { 1, 1, 1 };
                    createitem = new int[] { 20044 }; // 蓝海贼头巾
                    createcount = new int[] { 1 };
                } else {
                    htmlid = "SIMIZZ9";
                }
            } else if (s.equalsIgnoreCase("d")) {
                htmlid = "SIMIZZ12";
                pc.getQuest().set_step(L1Quest.QUEST_SIMIZZ, L1Quest.QUEST_END);
            }
        }

        // 多益(海贼岛)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71057) {
            // 有关拉休
            if (s.equalsIgnoreCase("3")) {
                htmlid = "doil4";
            } else if (s.equalsIgnoreCase("6")) {
                htmlid = "doil6";
            } else if (s.equalsIgnoreCase("1")) {
                if (pc.getInventory().checkItem(40714)) {
                    htmlid = "doil8";
                    materials = new int[] { 40714 }; // 蓝尾蜥蜴之皮
                    counts = new int[] { 1 };
                    createitem = new int[] { 40647 }; // 藏宝图碎片
                    createcount = new int[] { 1 };
                    pc.getQuest().set_step(L1Quest.QUEST_DOIL,
                            L1Quest.QUEST_END);
                } else {
                    htmlid = "doil7";
                }
            }
        }

        // 陆地安(海贼岛)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71059) {
            // 让我为你报仇吧
            if (s.equalsIgnoreCase("A")) {
                htmlid = "rudian6";
                final L1ItemInstance item = pc.getInventory().storeItem(40700,
                        1);
                pc.sendPackets(new S_ServerMessage(143, ((L1NpcInstance) obj)
                        .getNpcTemplate().get_name(), item.getItem().getName()));
                pc.getQuest().set_step(L1Quest.QUEST_RUDIAN, 1);
            } else if (s.equalsIgnoreCase("B")) {
                if (pc.getInventory().checkItem(40710)) { // 朋友的袋子
                    htmlid = "rudian8";
                    materials = new int[] { 40700, 40710 }; // 银笛
                    counts = new int[] { 1, 1 };
                    createitem = new int[] { 40647 }; // 藏宝图碎片
                    createcount = new int[] { 1 };
                    pc.getQuest().set_step(L1Quest.QUEST_RUDIAN,
                            L1Quest.QUEST_END);
                } else {
                    htmlid = "rudian9";
                }
            }
        }

        // 莱斯塔(海贼岛)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71060) {
            // 有关伙伴
            if (s.equalsIgnoreCase("A")) {
                if (pc.getQuest().get_step(L1Quest.QUEST_RUDIAN) == L1Quest.QUEST_END) {
                    htmlid = "resta6";
                } else {
                    htmlid = "resta4";
                }
            } else if (s.equalsIgnoreCase("B")) {
                htmlid = "resta10";
                pc.getQuest().set_step(L1Quest.QUEST_RESTA, 2);
            }
        }

        // 卡得穆斯(海贼岛)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71061) {
            // 请合并地图
            if (s.equalsIgnoreCase("A")) {
                if (pc.getInventory().checkItem(40647, 3)) { // 藏宝图碎片
                    htmlid = "cadmus6";
                    pc.getInventory().consumeItem(40647, 3);
                    pc.getQuest().set_step(L1Quest.QUEST_CADMUS, 2);
                } else {
                    htmlid = "cadmus5";
                    pc.getQuest().set_step(L1Quest.QUEST_CADMUS, 1);
                }
            }
        }

        // 卡蜜拉的灵魂(海贼岛)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71036) {
            if (s.equalsIgnoreCase("a")) {
                htmlid = "kamyla7";
                pc.getQuest().set_step(L1Quest.QUEST_KAMYLA, 1);
            } else if (s.equalsIgnoreCase("c")) {
                htmlid = "kamyla10";
                pc.getInventory().consumeItem(40644, 1); // 迷宫构造图
                pc.getQuest().set_step(L1Quest.QUEST_KAMYLA, 3);
            } else if (s.equalsIgnoreCase("e")) {
                htmlid = "kamyla13";
                pc.getInventory().consumeItem(40630, 1); // 迪哥的旧日记
                pc.getQuest().set_step(L1Quest.QUEST_KAMYLA, 4);
            } else if (s.equalsIgnoreCase("i")) {
                htmlid = "kamyla25";
            } else if (s.equalsIgnoreCase("b")) { // カーミラ（佛朗哥迷宫）
                if (pc.getQuest().get_step(L1Quest.QUEST_KAMYLA) == 1) {
                    L1Teleport.teleport(pc, 32679, 32742, (short) 482, 5, true);
                }
            } else if (s.equalsIgnoreCase("d")) { // カーミラ（ディエゴの闭ざされた牢）
                if (pc.getQuest().get_step(L1Quest.QUEST_KAMYLA) == 3) {
                    L1Teleport.teleport(pc, 32736, 32800, (short) 483, 5, true);
                }
            } else if (s.equalsIgnoreCase("f")) { // カーミラ（ホセ地下牢）
                if (pc.getQuest().get_step(L1Quest.QUEST_KAMYLA) == 4) {
                    L1Teleport.teleport(pc, 32746, 32807, (short) 484, 5, true);
                }
            }
        }

        // 福朗克(海贼岛)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71089) {
            // 我会想办法向她证明你的清白的
            if (s.equalsIgnoreCase("a")) {
                htmlid = "francu10";
                final L1ItemInstance item = pc.getInventory().storeItem(40644,
                        1); // 迷宫构造图
                pc.sendPackets(new S_ServerMessage(143, ((L1NpcInstance) obj)
                        .getNpcTemplate().get_name(), item.getItem().getName()));
                pc.getQuest().set_step(L1Quest.QUEST_KAMYLA, 2);
            }
        }

        // 试炼水晶2(海贼岛)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71090) {
            // 是的，请你把武器跟幻象武卷给我吧
            if (s.equalsIgnoreCase("a")) {
                htmlid = "";
                final int[] item_ids = { 246, 247, 248, 249, 40660 };
                final int[] item_amounts = { 1, 1, 1, 1, 5 };
                for (int i = 0; i < item_ids.length; i++) {
                    final L1ItemInstance item = pc.getInventory().storeItem(
                            item_ids[i], item_amounts[i]);
                    pc.sendPackets(new S_ServerMessage(143,
                            ((L1NpcInstance) obj).getNpcTemplate().get_name(),
                            item.getItem().getName()));
                    pc.getQuest().set_step(L1Quest.QUEST_CRYSTAL, 1);
                }
            } else if (s.equalsIgnoreCase("b")) {
                if (pc.getInventory().checkEquipped(246) // 试炼之剑A
                        || pc.getInventory().checkEquipped(247) // 试炼之剑B
                        || pc.getInventory().checkEquipped(248) // 试炼之剑C
                        || pc.getInventory().checkEquipped(249)) { // 试炼之剑D
                    htmlid = "jcrystal5";
                } else if (pc.getInventory().checkItem(40660)) { // 试炼卷轴
                    htmlid = "jcrystal4";
                } else {
                    pc.getInventory().consumeItem(246, 1);
                    pc.getInventory().consumeItem(247, 1);
                    pc.getInventory().consumeItem(248, 1);
                    pc.getInventory().consumeItem(249, 1);
                    pc.getInventory().consumeItem(40620, 1); // 第二迷宫钥匙
                    pc.getQuest().set_step(L1Quest.QUEST_CRYSTAL, 2);
                    L1Teleport.teleport(pc, 32801, 32895, (short) 483, 4, true);
                }
            } else if (s.equalsIgnoreCase("c")) {
                if (pc.getInventory().checkEquipped(246)
                        || pc.getInventory().checkEquipped(247)
                        || pc.getInventory().checkEquipped(248)
                        || pc.getInventory().checkEquipped(249)) {
                    htmlid = "jcrystal5";
                } else {
                    pc.getInventory().checkItem(40660);
                    final L1ItemInstance l1iteminstance = pc.getInventory()
                            .findItemId(40660);
                    final int sc = l1iteminstance.getCount();
                    if (sc > 0) {
                        pc.getInventory().consumeItem(40660, sc);
                    } else {
                    }
                    pc.getInventory().consumeItem(246, 1);
                    pc.getInventory().consumeItem(247, 1);
                    pc.getInventory().consumeItem(248, 1);
                    pc.getInventory().consumeItem(249, 1);
                    pc.getInventory().consumeItem(40620, 1); // 第二迷宫钥匙
                    pc.getQuest().set_step(L1Quest.QUEST_CRYSTAL, 0);
                    L1Teleport.teleport(pc, 32736, 32800, (short) 483, 4, true);
                }
            }
        }

        // 试炼水晶2(海贼岛)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71091) {
            // 再见啦！
            if (s.equalsIgnoreCase("a")) {
                htmlid = "";
                pc.getInventory().consumeItem(40654, 1); // 第三迷宫钥匙
                pc.getQuest()
                        .set_step(L1Quest.QUEST_CRYSTAL, L1Quest.QUEST_END);
                L1Teleport.teleport(pc, 32744, 32927, (short) 483, 4, true);
            }
        }

        // 蜥蜴人长老(海贼岛后半部)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71074) {
            // 能不能知道他现在人在哪里？
            if (s.equalsIgnoreCase("A")) {
                htmlid = "lelder5";
                pc.getQuest().set_step(L1Quest.QUEST_LIZARD, 1);
                // 虽然是很奇怪的要求但是我愿意帮助您
            } else if (s.equalsIgnoreCase("B")) {
                htmlid = "lelder10";
                pc.getInventory().consumeItem(40633, 1); // 蜥蜴人的报告
                pc.getQuest().set_step(L1Quest.QUEST_LIZARD, 3);
            } else if (s.equalsIgnoreCase("C")) {
                htmlid = "lelder13";
                if (pc.getQuest().get_step(L1Quest.QUEST_LIZARD) == L1Quest.QUEST_END) {
                }
                materials = new int[] { 40634 }; // 蜥蜴人的宝物
                counts = new int[] { 1 };
                createitem = new int[] { 20167 }; // 蜥蜴王手套
                createcount = new int[] { 1 };
                pc.getQuest().set_step(L1Quest.QUEST_LIZARD, L1Quest.QUEST_END);
            }
        }

        // 佣兵团长 多文
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71198) {
            if (s.equalsIgnoreCase("A")) {
                if ((pc.getQuest().get_step(71198) != 0)
                        || pc.getInventory().checkItem(21059, 1)) {
                    return;
                }
                if (pc.getInventory().consumeItem(41339, 5)) { // 亡者的信件
                    final L1ItemInstance item = ItemTable.getInstance()
                            .createItem(41340); // 佣兵团长多文的推荐书
                    if (item != null) {
                        if (pc.getInventory().checkAddItem(item, 1) == 0) {
                            pc.getInventory().storeItem(item);
                            pc.sendPackets(new S_ServerMessage(143,
                                    ((L1NpcInstance) obj).getNpcTemplate()
                                            .get_name(), item.getItem()
                                            .getName())); // \f1%0%s 给你 %1%o 。
                        }
                    }
                    pc.getQuest().set_step(71198, 1);
                    htmlid = "tion4";
                } else {
                    htmlid = "tion9";
                }
            } else if (s.equalsIgnoreCase("B")) {
                if ((pc.getQuest().get_step(71198) != 1)
                        || pc.getInventory().checkItem(21059, 1)) {
                    return;
                }
                if (pc.getInventory().consumeItem(41341, 1)) { // 帝伦之教本
                    pc.getQuest().set_step(71198, 2);
                    htmlid = "tion5";
                } else {
                    htmlid = "tion10";
                }
            } else if (s.equalsIgnoreCase("C")) {
                if ((pc.getQuest().get_step(71198) != 2)
                        || pc.getInventory().checkItem(21059, 1)) {
                    return;
                }
                if (pc.getInventory().consumeItem(41343, 1)) { // 法利昂的血痕
                    final L1ItemInstance item = ItemTable.getInstance()
                            .createItem(21057); // 训练骑士披肩1
                    if (item != null) {
                        if (pc.getInventory().checkAddItem(item, 1) == 0) {
                            pc.getInventory().storeItem(item);
                            pc.sendPackets(new S_ServerMessage(143,
                                    ((L1NpcInstance) obj).getNpcTemplate()
                                            .get_name(), item.getItem()
                                            .getName())); // \f1%0%s 给你 %1%o 。
                        }
                    }
                    pc.getQuest().set_step(71198, 3);
                    htmlid = "tion6";
                } else {
                    htmlid = "tion12";
                }
            } else if (s.equalsIgnoreCase("D")) {
                if ((pc.getQuest().get_step(71198) != 3)
                        || pc.getInventory().checkItem(21059, 1)) {
                    return;
                }
                if (pc.getInventory().consumeItem(41344, 1)) { // 水中的水
                    final L1ItemInstance item = ItemTable.getInstance()
                            .createItem(21058); // 训练骑士披肩2
                    if (item != null) {
                        pc.getInventory().consumeItem(21057, 1); // 训练骑士披肩1
                        if (pc.getInventory().checkAddItem(item, 1) == 0) {
                            pc.getInventory().storeItem(item);
                            pc.sendPackets(new S_ServerMessage(143,
                                    ((L1NpcInstance) obj).getNpcTemplate()
                                            .get_name(), item.getItem()
                                            .getName())); // \f1%0%s 给你 %1%o 。
                        }
                    }
                    pc.getQuest().set_step(71198, 4);
                    htmlid = "tion7";
                } else {
                    htmlid = "tion13";
                }
            } else if (s.equalsIgnoreCase("E")) {
                if ((pc.getQuest().get_step(71198) != 4)
                        || pc.getInventory().checkItem(21059, 1)) {
                    return;
                }
                if (pc.getInventory().consumeItem(41345, 1)) { // 酸性液体
                    final L1ItemInstance item = ItemTable.getInstance()
                            .createItem(21059); // 毒蛇之牙披肩
                    if (item != null) {
                        pc.getInventory().consumeItem(21058, 1); // 训练骑士披肩2
                        if (pc.getInventory().checkAddItem(item, 1) == 0) {
                            pc.getInventory().storeItem(item);
                            pc.sendPackets(new S_ServerMessage(143,
                                    ((L1NpcInstance) obj).getNpcTemplate()
                                            .get_name(), item.getItem()
                                            .getName())); // \f1%0%s 给你 %1%o 。
                        }
                    }
                    pc.getQuest().set_step(71198, 0); // 佣兵团长 多文
                    pc.getQuest().set_step(71199, 0); // 骑士团长 帝伦
                    htmlid = "tion8";
                } else {
                    htmlid = "tion15";
                }
            }
        }

        // 骑士团长 帝伦
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71199) {
            if (s.equalsIgnoreCase("A")) {
                if ((pc.getQuest().get_step(71199) != 0)
                        || pc.getInventory().checkItem(21059, 1)) { // 毒蛇之牙披肩
                    return;
                }
                if (pc.getInventory().checkItem(41340, 1)) { // 佣兵团长多文的推荐书
                    pc.getQuest().set_step(71199, 1);
                    htmlid = "jeron2";
                } else {
                    htmlid = "jeron10";
                }
            } else if (s.equalsIgnoreCase("B")) {
                if ((pc.getQuest().get_step(71199) != 1)
                        || pc.getInventory().checkItem(21059, 1)) { // 毒蛇之牙披肩
                    return;
                }
                if (pc.getInventory().consumeItem(40308, 1000000)) {
                    final L1ItemInstance item = ItemTable.getInstance()
                            .createItem(41341); // 帝伦之教本
                    if (item != null) {
                        if (pc.getInventory().checkAddItem(item, 1) == 0) {
                            pc.getInventory().storeItem(item);
                            pc.sendPackets(new S_ServerMessage(143,
                                    ((L1NpcInstance) obj).getNpcTemplate()
                                            .get_name(), item.getItem()
                                            .getName())); // \f1%0%s 给你 %1%o 。
                        }
                    }
                    pc.getInventory().consumeItem(41340, 1); // 佣兵团长多文的推荐书
                    pc.getQuest().set_step(71199, 255);
                    htmlid = "jeron6";
                } else {
                    htmlid = "jeron8";
                }
            } else if (s.equalsIgnoreCase("C")) {
                if ((pc.getQuest().get_step(71199) != 1)
                        || pc.getInventory().checkItem(21059, 1)) {
                    return;
                }
                if (pc.getInventory().consumeItem(41342, 1)) { // 梅杜莎之血
                    final L1ItemInstance item = ItemTable.getInstance()
                            .createItem(41341); // 帝伦之教本
                    if (item != null) {
                        if (pc.getInventory().checkAddItem(item, 1) == 0) {
                            pc.getInventory().storeItem(item);
                            pc.sendPackets(new S_ServerMessage(143,
                                    ((L1NpcInstance) obj).getNpcTemplate()
                                            .get_name(), item.getItem()
                                            .getName())); // \f1%0%s 给你 %1%o 。
                        }
                    }
                    pc.getInventory().consumeItem(41340, 1); // 佣兵团长多文的推荐书
                    pc.getQuest().set_step(71199, 255);
                    htmlid = "jeron5";
                } else {
                    htmlid = "jeron9";
                }
            }
        }

        // 占星术师 凯莉纱
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80079) {
            // 接受凯莉纱的灵魂契约
            if (s.equalsIgnoreCase("0")) {
                if (!pc.getInventory().checkItem(41312)) { // 占星术师の壶 XXX 貌似有错误
                    final L1ItemInstance item = pc.getInventory().storeItem(
                            41312, 1);
                    if (item != null) {
                        pc.sendPackets(new S_ServerMessage(143,
                                ((L1NpcInstance) obj).getNpcTemplate()
                                        .get_name(), item.getItem().getName())); // \f1%0%s
                                                                                 // 给你
                                                                                 // %1%o
                                                                                 // 。
                        pc.getQuest().set_step(L1Quest.QUEST_KEPLISHA,
                                L1Quest.QUEST_END);
                    }
                    htmlid = "keplisha7";
                }
            }
            // 支付金币来看自己的运势
            else if (s.equalsIgnoreCase("1")) {
                if (!pc.getInventory().checkItem(41314)) { // 占星术师のお守り XXX 貌似有错误
                    if (pc.getInventory().checkItem(L1ItemId.ADENA, 1000)) {
                        materials = new int[] { L1ItemId.ADENA, 41313 }; // アデナ、占星术师の玉
                                                                         // XXX
                                                                         // 貌似有错误
                        counts = new int[] { 1000, 1 };
                        createitem = new int[] { 41314 }; // 占星术师のお守り XXX 貌似有错误
                        createcount = new int[] { 1 };
                        final int htmlA = Random.nextInt(3) + 1;
                        final int htmlB = Random.nextInt(100) + 1;
                        switch (htmlA) {
                            case 1:
                                htmlid = "horosa" + htmlB; // horosa1 ~
                                                           // horosa100
                                break;
                            case 2:
                                htmlid = "horosb" + htmlB; // horosb1 ~
                                                           // horosb100
                                break;
                            case 3:
                                htmlid = "horosc" + htmlB; // horosc1 ~
                                                           // horosc100
                                break;
                            default:
                                break;
                        }
                    } else {
                        htmlid = "keplisha8";
                    }
                }
            }
            // 接受凯莉纱的祝福
            else if (s.equalsIgnoreCase("2")) {
                if (pc.getTempCharGfx() != pc.getClassId()) {
                    htmlid = "keplisha9";
                } else {
                    if (pc.getInventory().checkItem(41314)) { // 占星术师のお守り XXX
                                                              // 貌似有错误
                        pc.getInventory().consumeItem(41314, 1); // 占星术师のお守り XXX
                                                                 // 貌似有错误
                        final int html = Random.nextInt(9) + 1;
                        final int PolyId = 6180 + Random.nextInt(64);
                        this.polyByKeplisha(client, PolyId);
                        switch (html) {
                            case 1:
                                htmlid = "horomon11";
                                break;
                            case 2:
                                htmlid = "horomon12";
                                break;
                            case 3:
                                htmlid = "horomon13";
                                break;
                            case 4:
                                htmlid = "horomon21";
                                break;
                            case 5:
                                htmlid = "horomon22";
                                break;
                            case 6:
                                htmlid = "horomon23";
                                break;
                            case 7:
                                htmlid = "horomon31";
                                break;
                            case 8:
                                htmlid = "horomon32";
                                break;
                            case 9:
                                htmlid = "horomon33";
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
            // 打破瓮器解除契约
            else if (s.equalsIgnoreCase("3")) {
                if (pc.getInventory().checkItem(41312)) { // 占星术师の壶 XXX 貌似有错误
                    pc.getInventory().consumeItem(41312, 1);
                    htmlid = "";
                }
                if (pc.getInventory().checkItem(41313)) { // 占星术师の玉 XXX 貌似有错误
                    pc.getInventory().consumeItem(41313, 1);
                    htmlid = "";
                }
                if (pc.getInventory().checkItem(41314)) { // 占星术师のお守り XXX 貌似有错误
                    pc.getInventory().consumeItem(41314, 1);
                    htmlid = "";
                }
            }
        }

        // 钓鱼小童 波尔 (进入钓鱼池)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80082) {
            if (s.equalsIgnoreCase("a")) {
                if (pc.getLevel() < 15) {
                    htmlid = "fk_in_lv"; // 魔法钓鱼池只对15等级以上的冒险家开放。
                } else if (pc.getInventory().consumeItem(L1ItemId.ADENA, 1000)) {
                    L1PolyMorph.undoPoly(pc);
                    L1Teleport
                            .teleport(pc, 32742, 32799, (short) 5300, 4, true);
                } else {
                    htmlid = "fk_in_0";
                }
            }
        }

        // 奇怪的妖魔商人 波伦
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80084) {
            // “接过资源的清单”
            if (s.equalsIgnoreCase("q")) {
                if (pc.getInventory().checkItem(41356, 1)) { // 波伦的资源清单
                    htmlid = "rparum4";
                } else {
                    final L1ItemInstance item = pc.getInventory().storeItem(
                            41356, 1);
                    if (item != null) {
                        pc.sendPackets(new S_ServerMessage(143,
                                ((L1NpcInstance) obj).getNpcTemplate()
                                        .get_name(), item.getItem().getName())); // \f1%0%s
                                                                                 // 给你
                                                                                 // %1%o
                                                                                 // 。
                    }
                    htmlid = "rparum3";
                }
            }
        }

        // 亚丁骑兵团团员
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80105) {
            // “请赐给我新的力量.”
            if (s.equalsIgnoreCase("c")) {
                if (pc.isCrown()) {
                    if (pc.getInventory().checkItem(20383, 1)) {
                        if (pc.getInventory().checkItem(L1ItemId.ADENA, 100000)) {
                            final L1ItemInstance item = pc.getInventory()
                                    .findItemId(20383);
                            if ((item != null) && (item.getChargeCount() != 50)) {
                                item.setChargeCount(50);
                                pc.getInventory().updateItem(item,
                                        L1PcInventory.COL_CHARGE_COUNT);
                                pc.getInventory().consumeItem(L1ItemId.ADENA,
                                        100000);
                                htmlid = "";
                            }
                        } else {
                            pc.sendPackets(new S_ServerMessage(337, "$4")); // \f1%0不足%s。
                        }
                    }
                }
            }
        }

        // 补佐官 伊莉丝
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71126) {
            // “我一定会带回来”
            if (s.equalsIgnoreCase("B")) {
                if (pc.getInventory().checkItem(41007, 1)) { // 伊莉丝的命令书：灵魂之安息
                    htmlid = "eris10";
                } else {
                    final L1NpcInstance npc = (L1NpcInstance) obj;
                    final L1ItemInstance item = pc.getInventory().storeItem(
                            41007, 1);
                    final String npcName = npc.getNpcTemplate().get_name();
                    final String itemName = item.getItem().getName();
                    pc.sendPackets(new S_ServerMessage(143, npcName, itemName));
                    htmlid = "eris6";
                }
            } else if (s.equalsIgnoreCase("C")) {
                if (pc.getInventory().checkItem(41009, 1)) { // 伊莉丝的命令书：同盟之意志
                    htmlid = "eris10";
                } else {
                    final L1NpcInstance npc = (L1NpcInstance) obj;
                    final L1ItemInstance item = pc.getInventory().storeItem(
                            41009, 1);
                    final String npcName = npc.getNpcTemplate().get_name();
                    final String itemName = item.getItem().getName();
                    pc.sendPackets(new S_ServerMessage(143, npcName, itemName));
                    htmlid = "eris8";
                }
            } else if (s.equalsIgnoreCase("A")) {
                if (pc.getInventory().checkItem(41007, 1)) { // 伊莉丝的命令书：灵魂之安息
                    if (pc.getInventory().checkItem(40969, 20)) { // 黑暗妖精的灵魂水晶
                        htmlid = "eris18";
                        materials = new int[] { 40969, 41007 };
                        counts = new int[] { 20, 1 };
                        createitem = new int[] { 41008 }; // 伊莉丝的袋子
                        createcount = new int[] { 1 };
                    } else {
                        htmlid = "eris5";
                    }
                } else {
                    htmlid = "eris2";
                }
            } else if (s.equalsIgnoreCase("E")) {
                if (pc.getInventory().checkItem(41010, 1)) { // 伊莉丝的推荐函
                    htmlid = "eris19";
                } else {
                    htmlid = "eris7";
                }
            } else if (s.equalsIgnoreCase("D")) {
                if (pc.getInventory().checkItem(41010, 1)) { // 伊莉丝的推荐函
                    htmlid = "eris19";
                } else {
                    if (pc.getInventory().checkItem(41009, 1)) { // 伊莉丝的命令书：同盟之意志
                        if (pc.getInventory().checkItem(40959, 1)) { // 冥法军王徽印
                            htmlid = "eris17";
                            materials = new int[] { 40959, 41009 }; // 冥法军王徽印
                            counts = new int[] { 1, 1 };
                            createitem = new int[] { 41010 }; // 伊莉丝的推荐函
                            createcount = new int[] { 1 };
                        } else if (pc.getInventory().checkItem(40960, 1)) { // 法令军王徽印
                            htmlid = "eris16";
                            materials = new int[] { 40960, 41009 }; // 法令军王徽印
                            counts = new int[] { 1, 1 };
                            createitem = new int[] { 41010 }; // 伊莉丝的推荐函
                            createcount = new int[] { 1 };
                        } else if (pc.getInventory().checkItem(40961, 1)) { // 魔兽军王徽印
                            htmlid = "eris15";
                            materials = new int[] { 40961, 41009 }; // 魔兽军王徽印
                            counts = new int[] { 1, 1 };
                            createitem = new int[] { 41010 }; // 伊莉丝的推荐函
                            createcount = new int[] { 1 };
                        } else if (pc.getInventory().checkItem(40962, 1)) { // 暗杀军王徽印
                            htmlid = "eris14";
                            materials = new int[] { 40962, 41009 }; // 暗杀军王徽印
                            counts = new int[] { 1, 1 };
                            createitem = new int[] { 41010 }; // 伊莉丝的推荐函
                            createcount = new int[] { 1 };
                        } else if (pc.getInventory().checkItem(40635, 10)) { // 法令军团印记
                            htmlid = "eris12";
                            materials = new int[] { 40635, 41009 }; // 法令军团印记
                            counts = new int[] { 10, 1 };
                            createitem = new int[] { 41010 }; // 伊莉丝的推荐函
                            createcount = new int[] { 1 };
                        } else if (pc.getInventory().checkItem(40638, 10)) { // 魔兽军团印记
                            htmlid = "eris11";
                            materials = new int[] { 40638, 41009 }; // 魔兽军团印记
                            counts = new int[] { 10, 1 };
                            createitem = new int[] { 41010 }; // 伊莉丝的推荐函
                            createcount = new int[] { 1 };
                        } else if (pc.getInventory().checkItem(40642, 10)) { // 冥法军团印记
                            htmlid = "eris13";
                            materials = new int[] { 40642, 41009 }; // 冥法军团印记
                            counts = new int[] { 10, 1 };
                            createitem = new int[] { 41010 }; // 伊莉丝的推荐函
                            createcount = new int[] { 1 };
                        } else if (pc.getInventory().checkItem(40667, 10)) { // 暗杀军团印记
                            htmlid = "eris13";
                            materials = new int[] { 40667, 41009 }; // 暗杀军团印记
                            counts = new int[] { 10, 1 };
                            createitem = new int[] { 41010 }; // 伊莉丝的推荐函
                            createcount = new int[] { 1 };
                        } else {
                            htmlid = "eris8";
                        }
                    } else {
                        htmlid = "eris7";
                    }
                }
            }
        }

        // 航海士的灵魂
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80076) {
            if (s.equalsIgnoreCase("A")) {
                final int[] diaryno = { 49082, 49083 };
                final int pid = Random.nextInt(diaryno.length);
                final int di = diaryno[pid];
                if (di == 49082) { // 奇数 不完整的航海日志
                    htmlid = "voyager6a";
                    final L1NpcInstance npc = (L1NpcInstance) obj;
                    final L1ItemInstance item = pc.getInventory().storeItem(di,
                            1);
                    final String npcName = npc.getNpcTemplate().get_name();
                    final String itemName = item.getItem().getName();
                    pc.sendPackets(new S_ServerMessage(143, npcName, itemName));
                } else if (di == 49083) { // 偶数 不完整的航海日志
                    htmlid = "voyager6b";
                    final L1NpcInstance npc = (L1NpcInstance) obj;
                    final L1ItemInstance item = pc.getInventory().storeItem(di,
                            1);
                    final String npcName = npc.getNpcTemplate().get_name();
                    final String itemName = item.getItem().getName();
                    pc.sendPackets(new S_ServerMessage(143, npcName, itemName));
                }
            }
        }

        // 炼金术师 裴利塔
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71128) {
            if (s.equals("A")) {
                if (pc.getInventory().checkItem(41010, 1)) { // 伊莉丝的推荐函
                    htmlid = "perita2";
                } else {
                    htmlid = "perita3";
                }
            } else if (s.equals("p")) {
                // 受诅咒的 黑色耳环职业判别
                if (pc.getInventory().checkItem(40987, 1) // 法师
                        && pc.getInventory().checkItem(40988, 1) // 骑士
                        && pc.getInventory().checkItem(40989, 1)) { // 斗士
                    htmlid = "perita43";
                } else if (pc.getInventory().checkItem(40987, 1) // 法师
                        && pc.getInventory().checkItem(40989, 1)) { // 斗士
                    htmlid = "perita44";
                } else if (pc.getInventory().checkItem(40987, 1) // 法师
                        && pc.getInventory().checkItem(40988, 1)) { // 骑士
                    htmlid = "perita45";
                } else if (pc.getInventory().checkItem(40988, 1) // 骑士
                        && pc.getInventory().checkItem(40989, 1)) { // 斗士
                    htmlid = "perita47";
                } else if (pc.getInventory().checkItem(40987, 1)) { // 法师
                    htmlid = "perita46";
                } else if (pc.getInventory().checkItem(40988, 1)) { // 骑士
                    htmlid = "perita49";
                } else if (pc.getInventory().checkItem(40987, 1)) { // 斗士
                    htmlid = "perita48";
                } else {
                    htmlid = "perita50";
                }
            } else if (s.equals("q")) {
                // 黑色耳环职业判别
                if (pc.getInventory().checkItem(41173, 1) // 法师
                        && pc.getInventory().checkItem(41174, 1) // 骑士
                        && pc.getInventory().checkItem(41175, 1)) { // 斗士
                    htmlid = "perita54";
                } else if (pc.getInventory().checkItem(41173, 1) // 法师
                        && pc.getInventory().checkItem(41175, 1)) { // 斗士
                    htmlid = "perita55";
                } else if (pc.getInventory().checkItem(41173, 1) // 法师
                        && pc.getInventory().checkItem(41174, 1)) { // 骑士
                    htmlid = "perita56";
                } else if (pc.getInventory().checkItem(41174, 1) // 骑士
                        && pc.getInventory().checkItem(41175, 1)) { // 斗士
                    htmlid = "perita58";
                } else if (pc.getInventory().checkItem(41174, 1)) { // 骑士
                    htmlid = "perita57";
                } else if (pc.getInventory().checkItem(41175, 1)) { // 斗士
                    htmlid = "perita60";
                } else if (pc.getInventory().checkItem(41176, 1)) { // 神秘的灰色骑士耳环
                    htmlid = "perita59";
                } else {
                    htmlid = "perita61";
                }
            } else if (s.equals("s")) {
                // 神秘的黑色耳环职业判别
                if (pc.getInventory().checkItem(41161, 1) // 法师
                        && pc.getInventory().checkItem(41162, 1) // 骑士
                        && pc.getInventory().checkItem(41163, 1)) { // 斗士
                    htmlid = "perita62";
                } else if (pc.getInventory().checkItem(41161, 1) // 法师
                        && pc.getInventory().checkItem(41163, 1)) { // 斗士
                    htmlid = "perita63";
                } else if (pc.getInventory().checkItem(41161, 1) // 法师
                        && pc.getInventory().checkItem(41162, 1)) { // 骑士
                    htmlid = "perita64";
                } else if (pc.getInventory().checkItem(41162, 1) // 骑士
                        && pc.getInventory().checkItem(41163, 1)) { // 斗士
                    htmlid = "perita66";
                } else if (pc.getInventory().checkItem(41161, 1)) { // 法师
                    htmlid = "perita65";
                } else if (pc.getInventory().checkItem(41162, 1)) { // 骑士
                    htmlid = "perita68";
                } else if (pc.getInventory().checkItem(41163, 1)) { // 斗士
                    htmlid = "perita67";
                } else {
                    htmlid = "perita69";
                }
            } else if (s.equals("B")) {
                // 净化药水
                if (pc.getInventory().checkItem(40651, 10) // 火之气息
                        && pc.getInventory().checkItem(40643, 10) // 水之气息
                        && pc.getInventory().checkItem(40618, 10) // 土之气息
                        && pc.getInventory().checkItem(40645, 10) // 风之气息
                        && pc.getInventory().checkItem(40676, 10) // 闇之气息
                        && pc.getInventory().checkItem(40442, 5) // 布拉伯的胃液
                        && pc.getInventory().checkItem(40051, 1)) { // 品质绿宝石
                    htmlid = "perita7";
                    materials = new int[] { 40651, 40643, 40618, 40645, 40676,
                            40442, 40051 };
                    counts = new int[] { 10, 10, 10, 10, 20, 5, 1 };
                    createitem = new int[] { 40925 }; // 净化药水
                    createcount = new int[] { 1 };
                } else {
                    htmlid = "perita8";
                }
            } else if (s.equals("G") || s.equals("h") || s.equals("i")) {
                // 神秘药水：１段阶
                if (pc.getInventory().checkItem(40651, 5) // 火之气息
                        && pc.getInventory().checkItem(40643, 5) // 水之气息
                        && pc.getInventory().checkItem(40618, 5) // 土之气息
                        && pc.getInventory().checkItem(40645, 5) // 风之气息
                        && pc.getInventory().checkItem(40676, 5) // 闇之气息
                        && pc.getInventory().checkItem(40675, 5) // 黑暗矿石
                        && pc.getInventory().checkItem(40049, 3) // 品质红宝石
                        && pc.getInventory().checkItem(40051, 1)) { // 品质绿宝石
                    htmlid = "perita27";
                    materials = new int[] { 40651, 40643, 40618, 40645, 40676,
                            40675, 40049, 40051 };
                    counts = new int[] { 5, 5, 5, 5, 10, 10, 3, 1 };
                    createitem = new int[] { 40926 }; // 神秘药水：１段阶
                    createcount = new int[] { 1 };
                } else {
                    htmlid = "perita28";
                }
            } else if (s.equals("H") || s.equals("j") || s.equals("k")) {
                // 神秘药水：２段阶
                if (pc.getInventory().checkItem(40651, 10) // 火之气息
                        && pc.getInventory().checkItem(40643, 10) // 水之气息
                        && pc.getInventory().checkItem(40618, 10) // 土之气息
                        && pc.getInventory().checkItem(40645, 10) // 风之气息
                        && pc.getInventory().checkItem(40676, 20) // 闇之气息
                        && pc.getInventory().checkItem(40675, 10) // 黑暗矿石
                        && pc.getInventory().checkItem(40048, 3) // 品质钻石
                        && pc.getInventory().checkItem(40051, 1)) { // 品质绿宝石
                    htmlid = "perita29";
                    materials = new int[] { 40651, 40643, 40618, 40645, 40676,
                            40675, 40048, 40051 };
                    counts = new int[] { 10, 10, 10, 10, 20, 10, 3, 1 };
                    createitem = new int[] { 40927 }; // 神秘药水：２段阶
                    createcount = new int[] { 1 };
                } else {
                    htmlid = "perita30";
                }
            } else if (s.equals("I") || s.equals("l") || s.equals("m")) {
                // 神秘药水：３段阶
                if (pc.getInventory().checkItem(40651, 20) // 火之气息
                        && pc.getInventory().checkItem(40643, 20) // 水之气息
                        && pc.getInventory().checkItem(40618, 20) // 土之气息
                        && pc.getInventory().checkItem(40645, 20) // 风之气息
                        && pc.getInventory().checkItem(40676, 30) // 闇之气息
                        && pc.getInventory().checkItem(40675, 10) // 黑暗矿石
                        && pc.getInventory().checkItem(40050, 3) // 品质蓝宝石
                        && pc.getInventory().checkItem(40051, 1)) { // 品质绿宝石
                    htmlid = "perita31";
                    materials = new int[] { 40651, 40643, 40618, 40645, 40676,
                            40675, 40050, 40051 };
                    counts = new int[] { 20, 20, 20, 20, 30, 10, 3, 1 };
                    createitem = new int[] { 40928 }; // 神秘药水：３段阶
                    createcount = new int[] { 1 };
                } else {
                    htmlid = "perita32";
                }
            } else if (s.equals("J") || s.equals("n") || s.equals("o")) {
                // 神秘药水：４段阶
                if (pc.getInventory().checkItem(40651, 30) // 火之气息
                        && pc.getInventory().checkItem(40643, 30) // 水之气息
                        && pc.getInventory().checkItem(40618, 30) // 土之气息
                        && pc.getInventory().checkItem(40645, 30) // 风之气息
                        && pc.getInventory().checkItem(40676, 30) // 闇之气息
                        && pc.getInventory().checkItem(40675, 20) // 黑暗矿石
                        && pc.getInventory().checkItem(40052, 1) // 高品质钻石
                        && pc.getInventory().checkItem(40051, 1)) { // 品质绿宝石
                    htmlid = "perita33";
                    materials = new int[] { 40651, 40643, 40618, 40645, 40676,
                            40675, 40052, 40051 };
                    counts = new int[] { 30, 30, 30, 30, 30, 20, 1, 1 };
                    createitem = new int[] { 40928 }; // 神秘药水：４段阶
                    createcount = new int[] { 1 };
                } else {
                    htmlid = "perita34";
                }
            } else if (s.equals("K")) { // １段阶耳环(灵魂耳环)
                int earinga = 0;
                int earingb = 0;
                if (pc.getInventory().checkEquipped(21014)
                        || pc.getInventory().checkEquipped(21006)
                        || pc.getInventory().checkEquipped(21007)) {
                    htmlid = "perita36";
                } else if (pc.getInventory().checkItem(21014, 1)) { // 灵魂耳环
                    earinga = 21014;
                    earingb = 41176;
                } else if (pc.getInventory().checkItem(21006, 1)) { // 灵魂耳环
                    earinga = 21006;
                    earingb = 41177;
                } else if (pc.getInventory().checkItem(21007, 1)) { // 灵魂耳环
                    earinga = 21007;
                    earingb = 41178;
                } else {
                    htmlid = "perita36";
                }
                if (earinga > 0) {
                    materials = new int[] { earinga };
                    counts = new int[] { 1 };
                    createitem = new int[] { earingb };
                    createcount = new int[] { 1 };
                }
            } else if (s.equals("L")) { // ２段阶耳环(智慧耳环)
                if (pc.getInventory().checkEquipped(21015)) {
                    htmlid = "perita22";
                } else if (pc.getInventory().checkItem(21015, 1)) {
                    materials = new int[] { 21015 };
                    counts = new int[] { 1 };
                    createitem = new int[] { 41179 };
                    createcount = new int[] { 1 };
                } else {
                    htmlid = "perita22";
                }
            } else if (s.equals("M")) { // ３段阶耳环(真实耳环)
                if (pc.getInventory().checkEquipped(21016)) {
                    htmlid = "perita26";
                } else if (pc.getInventory().checkItem(21016, 1)) {
                    materials = new int[] { 21016 };
                    counts = new int[] { 1 };
                    createitem = new int[] { 41182 };
                    createcount = new int[] { 1 };
                } else {
                    htmlid = "perita26";
                }
            } else if (s.equals("b")) { // ２段阶耳环(热情耳环)
                if (pc.getInventory().checkEquipped(21009)) {
                    htmlid = "perita39";
                } else if (pc.getInventory().checkItem(21009, 1)) {
                    materials = new int[] { 21009 };
                    counts = new int[] { 1 };
                    createitem = new int[] { 41180 };
                    createcount = new int[] { 1 };
                } else {
                    htmlid = "perita39";
                }
            } else if (s.equals("d")) { // ３段阶耳环(名誉耳环)
                if (pc.getInventory().checkEquipped(21012)) {
                    htmlid = "perita41";
                } else if (pc.getInventory().checkItem(21012, 1)) {
                    materials = new int[] { 21012 };
                    counts = new int[] { 1 };
                    createitem = new int[] { 41183 };
                    createcount = new int[] { 1 };
                } else {
                    htmlid = "perita41";
                }
            } else if (s.equals("a")) { // ２段阶耳环(愤怒耳环)
                if (pc.getInventory().checkEquipped(21008)) {
                    htmlid = "perita38";
                } else if (pc.getInventory().checkItem(21008, 1)) {
                    materials = new int[] { 21008 };
                    counts = new int[] { 1 };
                    createitem = new int[] { 41181 };
                    createcount = new int[] { 1 };
                } else {
                    htmlid = "perita38";
                }
            } else if (s.equals("c")) { // ３段阶耳环(勇猛耳环)
                if (pc.getInventory().checkEquipped(21010)) {
                    htmlid = "perita40";
                } else if (pc.getInventory().checkItem(21010, 1)) {
                    materials = new int[] { 21010 };
                    counts = new int[] { 1 };
                    createitem = new int[] { 41184 };
                    createcount = new int[] { 1 };
                } else {
                    htmlid = "perita40";
                }
            }
        }

        // 宝石细工师 伦提斯
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71129) {
            if (s.equals("Z")) {
                htmlid = "rumtis2";
            } else if (s.equals("Y")) {
                if (pc.getInventory().checkItem(41010, 1)) { // 伊莉丝的推荐函
                    htmlid = "rumtis3";
                } else {
                    htmlid = "rumtis4";
                }
            } else if (s.equals("q")) {
                htmlid = "rumtis92";
            } else if (s.equals("A")) {
                if (pc.getInventory().checkItem(41161, 1)) { // 黑色耳环
                    htmlid = "rumtis6";
                } else {
                    htmlid = "rumtis101";
                }
            } else if (s.equals("B")) {
                if (pc.getInventory().checkItem(41164, 1)) { // 神秘的黑色耳环
                    htmlid = "rumtis7";
                } else {
                    htmlid = "rumtis101";
                }
            } else if (s.equals("C")) {
                if (pc.getInventory().checkItem(41167, 1)) { // 斗士耳环
                    htmlid = "rumtis8";
                } else {
                    htmlid = "rumtis101";
                }
            } else if (s.equals("T")) {
                if (pc.getInventory().checkItem(41167, 1)) { // 斗士耳环
                    htmlid = "rumtis9";
                } else {
                    htmlid = "rumtis101";
                }
            } else if (s.equals("w")) {
                if (pc.getInventory().checkItem(41162, 1)) { // 黑色耳环
                    htmlid = "rumtis14";
                } else {
                    htmlid = "rumtis101";
                }
            } else if (s.equals("x")) {
                if (pc.getInventory().checkItem(41165, 1)) { // 神秘的黑色耳环
                    htmlid = "rumtis15";
                } else {
                    htmlid = "rumtis101";
                }
            } else if (s.equals("y")) {
                if (pc.getInventory().checkItem(41168, 1)) { // 神秘的斗士耳环
                    htmlid = "rumtis16";
                } else {
                    htmlid = "rumtis101";
                }
            } else if (s.equals("z")) {
                if (pc.getInventory().checkItem(41171, 1)) { // 白色斗士耳环
                    htmlid = "rumtis17";
                } else {
                    htmlid = "rumtis101";
                }
            } else if (s.equals("U")) {
                if (pc.getInventory().checkItem(41163, 1)) { // 黑色耳环
                    htmlid = "rumtis10";
                } else {
                    htmlid = "rumtis101";
                }
            } else if (s.equals("V")) {
                if (pc.getInventory().checkItem(41166, 1)) { // 神秘的黑色耳环
                    htmlid = "rumtis11";
                } else {
                    htmlid = "rumtis101";
                }
            } else if (s.equals("W")) {
                if (pc.getInventory().checkItem(41169, 1)) { // 灰色斗士耳环
                    htmlid = "rumtis12";
                } else {
                    htmlid = "rumtis101";
                }
            } else if (s.equals("X")) {
                if (pc.getInventory().checkItem(41172, 1)) { // 神秘的白色斗士耳环
                    htmlid = "rumtis13";
                } else {
                    htmlid = "rumtis101";
                }
            } else if (s.equals("D") || s.equals("E") || s.equals("F")
                    || s.equals("G")) {
                int insn = 0;
                int bacn = 0;
                int me = 0;
                int mr = 0;
                int mj = 0;
                int an = 0;
                int men = 0;
                int mrn = 0;
                int mjn = 0;
                int ann = 0;
                if (pc.getInventory().checkItem(40959, 1) // 冥法军王徽印
                        && pc.getInventory().checkItem(40960, 1) // 法令军王徽印
                        && pc.getInventory().checkItem(40961, 1) // 魔兽军王徽印
                        && pc.getInventory().checkItem(40962, 1)) { // 暗杀军王徽印
                    insn = 1;
                    me = 40959;
                    mr = 40960;
                    mj = 40961;
                    an = 40962;
                    men = 1;
                    mrn = 1;
                    mjn = 1;
                    ann = 1;
                } else if (pc.getInventory().checkItem(40642, 10) // 冥法军团印记
                        && pc.getInventory().checkItem(40635, 10) // 法令军团印记
                        && pc.getInventory().checkItem(40638, 10) // 魔兽军团印记
                        && pc.getInventory().checkItem(40667, 10)) { // 暗杀军团印记
                    bacn = 1;
                    me = 40642;
                    mr = 40635;
                    mj = 40638;
                    an = 40667;
                    men = 10;
                    mrn = 10;
                    mjn = 10;
                    ann = 10;
                }
                if (pc.getInventory().checkItem(40046, 1) // 蓝宝石
                        && pc.getInventory().checkItem(40618, 5) // 土之气息
                        && pc.getInventory().checkItem(40643, 5) // 水之气息
                        && pc.getInventory().checkItem(40645, 5) // 风之气息
                        && pc.getInventory().checkItem(40651, 5) // 火之气息
                        && pc.getInventory().checkItem(40676, 5)) { // 闇之气息
                    if ((insn == 1) || (bacn == 1)) {
                        htmlid = "rumtis60";
                        materials = new int[] { me, mr, mj, an, 40046, 40618,
                                40643, 40651, 40676 };
                        counts = new int[] { men, mrn, mjn, ann, 1, 5, 5, 5, 5,
                                5 };
                        createitem = new int[] { 40926 }; // 一阶神秘药水
                        createcount = new int[] { 1 };
                    } else {
                        htmlid = "rumtis18";
                    }
                }
            }
        }

        // 亚塔路帝
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71119) {
            // “给予历史书１～８章”
            if (s.equalsIgnoreCase("request las history book")) {
                materials = new int[] { 41019, 41020, 41021, 41022, // 拉斯塔巴德历史书1-4
                        41023, 41024, 41025, 41026
                // 拉斯塔巴德历史书2-8
                };
                counts = new int[] { 1, 1, 1, 1, 1, 1, 1, 1 };
                createitem = new int[] { 41027 }; // 完整的拉斯塔巴德历史书
                createcount = new int[] { 1 };
                htmlid = "";
            }
        }

        // 长老随从．可罗兰斯
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71170) {
            // “将拉斯塔巴德历史书交给他”
            if (s.equalsIgnoreCase("request las weapon manual")) {
                materials = new int[] { 41027 }; // 完整的拉斯塔巴德历史书
                counts = new int[] { 1 };
                createitem = new int[] { 40965 }; // 拉斯塔巴德制作武器秘笈
                createcount = new int[] { 1 };
                htmlid = "";
            }
        }

        // 真．冥皇丹特斯
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71168) {
            // “把　我　送　到　异　世　界　吧！”
            if (s.equalsIgnoreCase("a")) {
                if (pc.getInventory().checkItem(41028, 1)) { // 死亡骑士之印记
                    L1Teleport.teleport(pc, 32648, 32921, (short) 535, 6, true);
                    pc.getInventory().consumeItem(41028, 1);
                }
            }
        }

        // 情报员(欲望洞窟侧)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80067) {
            // “慌张的答应”
            if (s.equalsIgnoreCase("n")) {
                htmlid = "";
                this.poly(client, 6034);
                final int[] item_ids = { 41132, 41133, 41134 };
                final int[] item_amounts = { 1, 1, 1 };
                for (int i = 0; i < item_ids.length; i++) {
                    final L1ItemInstance item = pc.getInventory().storeItem(
                            item_ids[i], item_amounts[i]);
                    pc.sendPackets(new S_ServerMessage(143,
                            ((L1NpcInstance) obj).getNpcTemplate().get_name(),
                            item.getItem().getName()));
                    pc.getQuest().set_step(L1Quest.QUEST_DESIRE, 1);
                }
                // “我不想做这种事情”
            } else if (s.equalsIgnoreCase("d")) {
                htmlid = "minicod09";
                pc.getInventory().consumeItem(41130, 1); // 炎魔的契约书
                pc.getInventory().consumeItem(41131, 1); // 炎魔的契约
                // “执行初始化”
            } else if (s.equalsIgnoreCase("k")) {
                htmlid = "";
                pc.getInventory().consumeItem(41132, 1); // 炎魔的堕落粉
                pc.getInventory().consumeItem(41133, 1); // 炎魔的无力粉
                pc.getInventory().consumeItem(41134, 1); // 炎魔的执着粉
                pc.getInventory().consumeItem(41135, 1); // 火焰之影的堕落井水
                pc.getInventory().consumeItem(41136, 1); // 火焰之影的无力井水
                pc.getInventory().consumeItem(41137, 1); // 火焰之影的执着井水
                pc.getInventory().consumeItem(41138, 1); // 火焰之影的井水
                pc.getQuest().set_step(L1Quest.QUEST_DESIRE, 0);
                // 交出井水
            } else if (s.equalsIgnoreCase("e")) {
                if ((pc.getQuest().get_step(L1Quest.QUEST_DESIRE) == L1Quest.QUEST_END)
                        || (pc.getKarmaLevel() >= 1)) {
                    htmlid = "";
                } else {
                    if (pc.getInventory().checkItem(41138)) {
                        htmlid = "";
                        pc.addKarma((int) (1600 * Config.RATE_KARMA));
                        pc.getInventory().consumeItem(41130, 1); // 炎魔的契约书
                        pc.getInventory().consumeItem(41131, 1); // 炎魔的契约
                        pc.getInventory().consumeItem(41138, 1); // 火焰之影的井水
                        pc.getQuest().set_step(L1Quest.QUEST_DESIRE,
                                L1Quest.QUEST_END);
                    } else {
                        htmlid = "minicod04";
                    }
                }
                // 接受礼物
            } else if (s.equalsIgnoreCase("g")) {
                htmlid = "";
                final L1ItemInstance item = pc.getInventory().storeItem(41130,
                        1); // 炎魔的契约书
                pc.sendPackets(new S_ServerMessage(143, ((L1NpcInstance) obj)
                        .getNpcTemplate().get_name(), item.getItem().getName()));
            }
        }

        // 情报员(暗影神殿侧)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 81202) {
            // “慌张的答应”
            if (s.equalsIgnoreCase("n")) {
                htmlid = "";
                this.poly(client, 6035);
                final int[] item_ids = { 41123, 41124, 41125 };
                final int[] item_amounts = { 1, 1, 1 };
                for (int i = 0; i < item_ids.length; i++) {
                    final L1ItemInstance item = pc.getInventory().storeItem(
                            item_ids[i], item_amounts[i]);
                    pc.sendPackets(new S_ServerMessage(143,
                            ((L1NpcInstance) obj).getNpcTemplate().get_name(),
                            item.getItem().getName()));
                    pc.getQuest().set_step(L1Quest.QUEST_SHADOWS, 1);
                }
                // “我不想做这种事情”
            } else if (s.equalsIgnoreCase("d")) {
                htmlid = "minitos09";
                pc.getInventory().consumeItem(41121, 1); // 火焰之影的契约书
                pc.getInventory().consumeItem(41122, 1); // 火焰之影的契约
                // “执行初始化”
            } else if (s.equalsIgnoreCase("k")) {
                htmlid = "";
                pc.getInventory().consumeItem(41123, 1); // 火焰之影的堕落粉
                pc.getInventory().consumeItem(41124, 1); // 火焰之影的无力粉
                pc.getInventory().consumeItem(41125, 1); // 火焰之影的执着粉
                pc.getInventory().consumeItem(41126, 1); // 炎魔的堕落井水
                pc.getInventory().consumeItem(41127, 1); // 炎魔的无力井水
                pc.getInventory().consumeItem(41128, 1); // 炎魔的执着井水
                pc.getInventory().consumeItem(41129, 1); // 炎魔的井水
                pc.getQuest().set_step(L1Quest.QUEST_SHADOWS, 0);
                // 交出井水
            } else if (s.equalsIgnoreCase("e")) {
                if ((pc.getQuest().get_step(L1Quest.QUEST_SHADOWS) == L1Quest.QUEST_END)
                        || (pc.getKarmaLevel() >= 1)) {
                    htmlid = "";
                } else {
                    if (pc.getInventory().checkItem(41129)) {
                        htmlid = "";
                        pc.addKarma((int) (-1600 * Config.RATE_KARMA));
                        pc.getInventory().consumeItem(41121, 1); // 火焰之影的契约书
                        pc.getInventory().consumeItem(41122, 1); // 火焰之影的契约
                        pc.getInventory().consumeItem(41129, 1); // 炎魔的井水
                        pc.getQuest().set_step(L1Quest.QUEST_SHADOWS,
                                L1Quest.QUEST_END);
                    } else {
                        htmlid = "minitos04";
                    }
                }
                // 接受礼物
            } else if (s.equalsIgnoreCase("g")) {
                htmlid = "";
                final L1ItemInstance item = pc.getInventory().storeItem(41121,
                        1); // 火焰之影的契约书
                pc.sendPackets(new S_ServerMessage(143, ((L1NpcInstance) obj)
                        .getNpcTemplate().get_name(), item.getItem().getName()));
            }
        }

        // 宙斯之石头高仑（制作魔法武器）
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71252) {
            int weapon1 = 0;
            int weapon2 = 0;
            int newWeapon = 0;
            if (s.equalsIgnoreCase("A")) {
                weapon1 = 5; // +7精灵匕首
                weapon2 = 6; // +7拉斯塔巴德短剑
                newWeapon = 259; // 魔力短剑
                htmlid = "joegolem9";
            } else if (s.equalsIgnoreCase("B")) {
                weapon1 = 145; // +7狂战士斧
                weapon2 = 148; // +7巨斧
                newWeapon = 260; // 狂风之斧
                htmlid = "joegolem10";
            } else if (s.equalsIgnoreCase("C")) {
                weapon1 = 52; // +7双手剑
                weapon2 = 64; // +7巨剑
                newWeapon = 262; // 毁灭巨剑
                htmlid = "joegolem11";
            } else if (s.equalsIgnoreCase("D")) {
                weapon1 = 125; // +7巫术魔法杖
                weapon2 = 129; // +7美基魔法杖
                newWeapon = 261; // 大法师魔杖
                htmlid = "joegolem12";
            } else if (s.equalsIgnoreCase("E")) {
                weapon1 = 99; // +7精灵之矛
                weapon2 = 104; // +7法丘
                newWeapon = 263; // 酷寒之矛
                htmlid = "joegolem13";
            } else if (s.equalsIgnoreCase("F")) {
                weapon1 = 32; // +7侵略者之剑
                weapon2 = 42; // +7细剑
                newWeapon = 264; // 雷雨之剑
                htmlid = "joegolem14";
            }
            if (pc.getInventory().checkEnchantItem(weapon1, 7, 1)
                    && pc.getInventory().checkEnchantItem(weapon2, 7, 1)
                    && pc.getInventory().checkItem(41246, 1000) // 魔法结晶体
                    && pc.getInventory().checkItem(49143, 10)) { // 勇气结晶
                pc.getInventory().consumeEnchantItem(weapon1, 7, 1);
                pc.getInventory().consumeEnchantItem(weapon2, 7, 1);
                pc.getInventory().consumeItem(41246, 1000);
                pc.getInventory().consumeItem(49143, 10);
                final L1ItemInstance item = pc.getInventory().storeItem(
                        newWeapon, 1);
                pc.sendPackets(new S_ServerMessage(143, ((L1NpcInstance) obj)
                        .getNpcTemplate().get_name(), item.getItem().getName()));
            } else {
                htmlid = "joegolem15";
                if (!pc.getInventory().checkEnchantItem(weapon1, 7, 1)) {
                    pc.sendPackets(new S_ServerMessage(337, "+7 "
                            + ItemTable.getInstance().getTemplate(weapon1)
                                    .getName())); // \f1%0不足%s。
                }
                if (!pc.getInventory().checkEnchantItem(weapon2, 7, 1)) {
                    pc.sendPackets(new S_ServerMessage(337, "+7 "
                            + ItemTable.getInstance().getTemplate(weapon2)
                                    .getName())); // \f1%0不足%s。
                }
                if (!pc.getInventory().checkItem(41246, 1000)) { // 魔法结晶体
                    int itemCount = 0;
                    itemCount = 1000 - pc.getInventory().countItems(41246);
                    pc.sendPackets(new S_ServerMessage(337, ItemTable
                            .getInstance().getTemplate(41246).getName()
                            + "(" + itemCount + ")")); // \f1%0不足%s。
                }
                if (!pc.getInventory().checkItem(49143, 10)) { // 勇气结晶
                    int itemCount = 0;
                    itemCount = 10 - pc.getInventory().countItems(49143);
                    pc.sendPackets(new S_ServerMessage(337, ItemTable
                            .getInstance().getTemplate(49143).getName()
                            + "(" + itemCount + ")")); // \f1%0不足%s。
                }
            }
        }

        // 宙斯之石头高仑(底比斯沙漠)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71253) {
            // “制作 龟裂之核”
            if (s.equalsIgnoreCase("A")) {
                if (pc.getInventory().checkItem(49101, 100)) {
                    materials = new int[] { 49101 };
                    counts = new int[] { 100 };
                    createitem = new int[] { 49092 };
                    createcount = new int[] { 1 };
                    htmlid = "joegolem18";
                } else {
                    htmlid = "joegolem19";
                }
            } else if (s.equalsIgnoreCase("B")) {
                if (pc.getInventory().checkItem(49101, 1)) {
                    pc.getInventory().consumeItem(49101, 1);
                    L1Teleport.teleport(pc, 33966, 33253, (short) 4, 5, true);
                    htmlid = "";
                } else {
                    htmlid = "joegolem20";
                }
            }
        }

        // 底比斯 欧西里斯祭坛守门人
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71255) {
            // “如果持有 底比斯欧西里斯祭坛钥匙、传送到 底比斯欧西里斯祭坛。”
            if (s.equalsIgnoreCase("e")) {
                if (pc.getInventory().checkItem(49242, 1)) { // 检查钥匙(20人限定/时の歪みが现れてから2h30は未实装)
                    pc.getInventory().consumeItem(49242, 1);
                    L1Teleport.teleport(pc, 32735, 32831, (short) 782, 2, true);
                    htmlid = "";
                } else {
                    htmlid = "tebegate3";
                    // “上限人数に达している场合は”
                    // htmlid = "tebegate4";
                }
            }
        }

        // 罗宾孙
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71256) {
            if (s.equalsIgnoreCase("E")) {
                if ((pc.getQuest().get_step(L1Quest.QUEST_MOONOFLONGBOW) == 8)
                        && pc.getInventory().checkItem(40491, 30) // 格利芬羽毛
                        && pc.getInventory().checkItem(40495, 40) // 米索莉线
                        && pc.getInventory().checkItem(100, 1) // 覆上奥里哈鲁根的角
                        && pc.getInventory().checkItem(40509, 12) // 奥里哈鲁根金属板
                        && pc.getInventory().checkItem(40052, 1) // 高品质钻石
                        && pc.getInventory().checkItem(40053, 1) // 高品质红宝石
                        && pc.getInventory().checkItem(40054, 1) // 高品质蓝宝石
                        && pc.getInventory().checkItem(40055, 1) // 高品质绿宝石
                        && pc.getInventory().checkItem(41347, 1) // 罗宾孙的便条纸
                        && pc.getInventory().checkItem(41350, 1)) { // 罗宾孙之戒
                    pc.getInventory().consumeItem(40491, 30);
                    pc.getInventory().consumeItem(40495, 40);
                    pc.getInventory().consumeItem(100, 1);
                    pc.getInventory().consumeItem(40509, 12);
                    pc.getInventory().consumeItem(40052, 1);
                    pc.getInventory().consumeItem(40053, 1);
                    pc.getInventory().consumeItem(40054, 1);
                    pc.getInventory().consumeItem(40055, 1);
                    pc.getInventory().consumeItem(41347, 1);
                    pc.getInventory().consumeItem(41350, 1);
                    htmlid = "robinhood12";
                    pc.getInventory().storeItem(205, 1); // 炽炎天使弓
                    pc.getQuest().set_step(L1Quest.QUEST_MOONOFLONGBOW,
                            L1Quest.QUEST_END);
                }
            } else if (s.equalsIgnoreCase("C")) {
                if (pc.getQuest().get_step(L1Quest.QUEST_MOONOFLONGBOW) == 7) {
                    if (pc.getInventory().checkItem(41352, 4)
                            && pc.getInventory().checkItem(40618, 30)
                            && pc.getInventory().checkItem(40643, 30)
                            && pc.getInventory().checkItem(40645, 30)
                            && pc.getInventory().checkItem(40651, 30)
                            && pc.getInventory().checkItem(40676, 30)
                            && pc.getInventory().checkItem(40514, 20)
                            && pc.getInventory().checkItem(41351, 1)
                            && pc.getInventory().checkItem(41346, 1)) {
                        pc.getInventory().consumeItem(41352, 4);
                        pc.getInventory().consumeItem(40618, 30);
                        pc.getInventory().consumeItem(40643, 30);
                        pc.getInventory().consumeItem(40645, 30);
                        pc.getInventory().consumeItem(40651, 30);
                        pc.getInventory().consumeItem(40676, 30);
                        pc.getInventory().consumeItem(40514, 20);
                        pc.getInventory().consumeItem(41351, 1);
                        pc.getInventory().consumeItem(41346, 1);
                        pc.getInventory().storeItem(41347, 1);
                        pc.getInventory().storeItem(41350, 1);
                        htmlid = "robinhood10";
                        pc.getQuest().set_step(L1Quest.QUEST_MOONOFLONGBOW, 8);
                    }
                }
            } else if (s.equalsIgnoreCase("B")) {
                if (pc.getInventory().checkItem(41348)
                        && pc.getInventory().checkItem(41346)) {
                    htmlid = "robinhood13";
                } else {
                    pc.getInventory().storeItem(41348, 1);
                    pc.getInventory().storeItem(41346, 1);
                    htmlid = "robinhood13";
                    pc.getQuest().set_step(L1Quest.QUEST_MOONOFLONGBOW, 2);
                }
            } else if (s.equalsIgnoreCase("A")) {
                if (pc.getInventory().checkItem(40028)) {
                    pc.getInventory().consumeItem(40028, 1);
                    htmlid = "robinhood4";
                    pc.getQuest().set_step(L1Quest.QUEST_MOONOFLONGBOW, 1);
                } else {
                    htmlid = "robinhood19";
                }
            }
        }

        // 神官知布烈
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71257) {
            if (s.equalsIgnoreCase("D")) {
                if (pc.getInventory().checkItem(41349)) { // 莎尔之戒
                    htmlid = "zybril10";
                    pc.getInventory().storeItem(41351, 1); // 月光之气息
                    pc.getInventory().consumeItem(41349, 1);
                    pc.getQuest().set_step(L1Quest.QUEST_MOONOFLONGBOW, 7);
                } else {
                    htmlid = "zybril14";
                }
            } else if (s.equalsIgnoreCase("C")) {
                if (pc.getInventory().checkItem(40514, 10)
                        && pc.getInventory().checkItem(41353)) {
                    pc.getInventory().consumeItem(40514, 10);
                    pc.getInventory().consumeItem(41353, 1);
                    pc.getInventory().storeItem(41354, 1);
                    htmlid = "zybril9";
                    pc.getQuest().set_step(L1Quest.QUEST_MOONOFLONGBOW, 6);
                }
            } else if (pc.getInventory().checkItem(41353)
                    && pc.getInventory().checkItem(40514, 10)) {
                htmlid = "zybril8";
            } else if (s.equalsIgnoreCase("B")) {
                if (pc.getInventory().checkItem(40048, 10)
                        && pc.getInventory().checkItem(40049, 10)
                        && pc.getInventory().checkItem(40050, 10)
                        && pc.getInventory().checkItem(40051, 10)) {
                    pc.getInventory().consumeItem(40048, 10);
                    pc.getInventory().consumeItem(40049, 10);
                    pc.getInventory().consumeItem(40050, 10);
                    pc.getInventory().consumeItem(40051, 10);
                    pc.getInventory().storeItem(41353, 1);
                    htmlid = "zybril15";
                    pc.getQuest().set_step(L1Quest.QUEST_MOONOFLONGBOW, 5);
                } else {
                    htmlid = "zybril12";
                    pc.getQuest().set_step(L1Quest.QUEST_MOONOFLONGBOW, 4);
                }
            } else if (s.equalsIgnoreCase("A")) {
                if (pc.getInventory().checkItem(41348)
                        && pc.getInventory().checkItem(41346)) {
                    htmlid = "zybril3";
                    pc.getQuest().set_step(L1Quest.QUEST_MOONOFLONGBOW, 3);
                } else {
                    htmlid = "zybril11";
                }
            }
        }

        // 玛勒巴
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71258) {
            if (pc.getInventory().checkItem(40665)) { // 阿拉斯的信
                htmlid = "marba17"; //
                if (s.equalsIgnoreCase("B")) {
                    htmlid = "marba7"; //
                    if (pc.getInventory().checkItem(214) // %i‧妖精弓
                            && pc.getInventory().checkItem(20389) // %i．妖精头盔
                            && pc.getInventory().checkItem(20393) // %i．妖精金甲
                            && pc.getInventory().checkItem(20401) // %i．妖精斗篷
                            && pc.getInventory().checkItem(20406) // %i．妖精钢靴
                            && pc.getInventory().checkItem(20409)) { // %i．妖精腕甲
                        htmlid = "marba15"; //
                    }
                }
            } else if (s.equalsIgnoreCase("A")) {
                if (pc.getInventory().checkItem(40637)) { // 玛勒巴的信
                    htmlid = "marba20"; //
                } else {
                    final L1NpcInstance npc = (L1NpcInstance) obj;
                    final L1ItemInstance item = pc.getInventory().storeItem(
                            40637, 1);
                    final String npcName = npc.getNpcTemplate().get_name();
                    final String itemName = item.getItem().getName();
                    pc.sendPackets(new S_ServerMessage(143, npcName, itemName)); // \f1%0%s
                                                                                 // 给你
                                                                                 // %1%o
                                                                                 // 。
                    htmlid = "marba6"; // 你就去找阿拉斯看看吧...他人在眠龙洞穴前面。
                }
            }
        }

        // 阿拉斯
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 71259) {
            if (pc.getInventory().checkItem(40665)) { // 阿拉斯的信
                htmlid = "aras8"; // 有去找过玛勒巴大人吗?
            } else if (pc.getInventory().checkItem(40637)) { // 玛勒巴的信
                htmlid = "aras1"; // 我帮你找回来
                if (s.equalsIgnoreCase("A")) {
                    if (pc.getInventory().checkItem(40664)) { // 阿拉斯的护身符
                        htmlid = "aras6"; // 我相信你一定可以找回我儿子的遗物...
                        if (pc.getInventory().checkItem(40679) // 污浊的金甲
                                || pc.getInventory().checkItem(40680) // 污浊斗篷
                                || pc.getInventory().checkItem(40681) // 污浊的钢靴
                                || pc.getInventory().checkItem(40682) // 污浊的腕甲
                                || pc.getInventory().checkItem(40683) // 污浊的头盔
                                || pc.getInventory().checkItem(40684) // 污浊的弓
                                || pc.getInventory().checkItem(40693) // 远征队金甲
                                || pc.getInventory().checkItem(40694) // 远征队斗篷
                                || pc.getInventory().checkItem(40695) // 远征队钢靴
                                || pc.getInventory().checkItem(40697) // 远征队腕甲
                                || pc.getInventory().checkItem(40698) // 远征队头盔
                                || pc.getInventory().checkItem(40699)) { // 远征队弓
                            htmlid = "aras3"; // 有没有找到什么？
                        } else {
                            htmlid = "aras6"; // 我相信你一定可以找回我儿子的遗物...
                        }
                    } else {
                        final L1NpcInstance npc = (L1NpcInstance) obj;
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(40664, 1); // 阿拉斯的护身符
                        final String npcName = npc.getNpcTemplate().get_name();
                        final String itemName = item.getItem().getName();
                        pc.sendPackets(new S_ServerMessage(143, npcName,
                                itemName)); // \f1%0%s 给你 %1%o 。
                        htmlid = "aras6"; // 我相信你一定可以找回我儿子的遗物...
                    }
                } else if (s.equalsIgnoreCase("B")) {
                    if (pc.getInventory().checkItem(40664)) { // 阿拉斯的护身符
                        pc.getInventory().consumeItem(40664, 1);
                        final L1NpcInstance npc = (L1NpcInstance) obj;
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(40665, 1); // 阿拉斯的信
                        final String npcName = npc.getNpcTemplate().get_name();
                        final String itemName = item.getItem().getName();
                        pc.sendPackets(new S_ServerMessage(143, npcName,
                                itemName)); // \f1%0%s 给你 %1%o 。
                        htmlid = "aras13"; // 赶快将这封信交给玛勒巴！
                    } else {
                        htmlid = "aras14"; // 啊...你把护身符弄丢了！ 赶快把信交给玛勒巴吧...
                        final L1NpcInstance npc = (L1NpcInstance) obj;
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(40665, 1); // 阿拉斯的信
                        final String npcName = npc.getNpcTemplate().get_name();
                        final String itemName = item.getItem().getName();
                        pc.sendPackets(new S_ServerMessage(143, npcName,
                                itemName)); // \f1%0%s 给你 %1%o 。
                    }
                } else {
                    if (s.equalsIgnoreCase("7")) {
                        if (pc.getInventory().checkItem(40693) // 远征队金甲
                                && pc.getInventory().checkItem(40694) // 远征队斗篷
                                && pc.getInventory().checkItem(40695) // 远征队钢靴
                                && pc.getInventory().checkItem(40697) // 远征队腕甲
                                && pc.getInventory().checkItem(40698) // 远征队头盔
                                && pc.getInventory().checkItem(40699)) { // 远征队弓
                            htmlid = "aras10"; // 收到了信
                        } else {
                            htmlid = "aras9"; // 希望这种悲剧以后不要再发生...
                        }
                    }
                }
            } else {
                htmlid = "aras7"; // 若有人能帮我将他的遗物找回来就好了...
            }
        }

        // 治安团长 拉罗森
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80099) {
            if (s.equalsIgnoreCase("A")) {
                if (pc.getInventory().checkItem(40308, 300)) {
                    pc.getInventory().consumeItem(40308, 300);
                    pc.getInventory().storeItem(41315, 1); // 圣水
                    pc.getQuest().set_step(
                            L1Quest.QUEST_GENERALHAMELOFRESENTMENT, 1);
                    htmlid = "rarson16";
                } else if (!pc.getInventory().checkItem(40308, 300)) {
                    htmlid = "rarson7";
                }
            } else if (s.equalsIgnoreCase("B")) {
                if ((pc.getQuest().get_step(
                        L1Quest.QUEST_GENERALHAMELOFRESENTMENT) == 1)
                        && (pc.getInventory().checkItem(41325, 1))) {
                    pc.getInventory().consumeItem(41325, 1); // 勇士之证
                    pc.getInventory().storeItem(40308, 2000);
                    pc.getInventory().storeItem(41317, 1); // 拉罗森的推荐书
                    pc.getQuest().set_step(
                            L1Quest.QUEST_GENERALHAMELOFRESENTMENT, 2);
                    htmlid = "rarson9";
                } else {
                    htmlid = "rarson10";
                }
            } else if (s.equalsIgnoreCase("C")) {
                if ((pc.getQuest().get_step(
                        L1Quest.QUEST_GENERALHAMELOFRESENTMENT) == 4)
                        && (pc.getInventory().checkItem(41326, 1))) {
                    pc.getInventory().storeItem(40308, 30000);
                    pc.getInventory().consumeItem(41326, 1); // 勇士之证
                    htmlid = "rarson12";
                    pc.getQuest().set_step(
                            L1Quest.QUEST_GENERALHAMELOFRESENTMENT, 5);
                } else {
                    htmlid = "rarson17";
                }
            } else if (s.equalsIgnoreCase("D")) {
                if ((pc.getQuest().get_step(
                        L1Quest.QUEST_GENERALHAMELOFRESENTMENT) <= 1)
                        || (pc.getQuest().get_step(
                                L1Quest.QUEST_GENERALHAMELOFRESENTMENT) == 5)) {
                    if (pc.getInventory().checkItem(40308, 300)) {
                        pc.getInventory().consumeItem(40308, 300);
                        pc.getInventory().storeItem(41315, 1); // 圣水
                        pc.getQuest().set_step(
                                L1Quest.QUEST_GENERALHAMELOFRESENTMENT, 1);
                        htmlid = "rarson16";
                    } else if (!pc.getInventory().checkItem(40308, 300)) {
                        htmlid = "rarson7";
                    }
                } else if ((pc.getQuest().get_step(
                        L1Quest.QUEST_GENERALHAMELOFRESENTMENT) >= 2)
                        && (pc.getQuest().get_step(
                                L1Quest.QUEST_GENERALHAMELOFRESENTMENT) <= 4)) {
                    if (pc.getInventory().checkItem(40308, 300)) {
                        pc.getInventory().consumeItem(40308, 300);
                        pc.getInventory().storeItem(41315, 1); // 圣水
                        htmlid = "rarson16";
                    } else if (!pc.getInventory().checkItem(40308, 300)) {
                        htmlid = "rarson7";
                    }
                }
            }
        }
        // 可恩
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80101) {
            if (s.equalsIgnoreCase("request letter of kuen")) {
                if ((pc.getQuest().get_step(
                        L1Quest.QUEST_GENERALHAMELOFRESENTMENT) == 2)
                        && (pc.getInventory().checkItem(41317, 1))) {
                    pc.getInventory().consumeItem(41317, 1); // 拉罗森的推荐书
                    pc.getInventory().storeItem(41318, 1); // 可恩的便条纸
                    pc.getQuest().set_step(
                            L1Quest.QUEST_GENERALHAMELOFRESENTMENT, 3);
                    htmlid = "";
                } else {
                    htmlid = "";
                }
            } else if (s.equalsIgnoreCase("request holy mithril dust")) {
                if ((pc.getQuest().get_step(
                        L1Quest.QUEST_GENERALHAMELOFRESENTMENT) == 3)
                        && (pc.getInventory().checkItem(41315, 1)) // 圣水
                        && pc.getInventory().checkItem(40494, 30) // 纯粹的米索莉块
                        && pc.getInventory().checkItem(41318, 1)) { // 可恩的便条纸
                    pc.getInventory().consumeItem(41315, 1); // 圣水
                    pc.getInventory().consumeItem(41318, 1); // 可恩的便条纸
                    pc.getInventory().consumeItem(40494, 30); // 纯粹的米索莉块
                    pc.getInventory().storeItem(41316, 1); // 神圣的米索莉粉
                    pc.getQuest().set_step(
                            L1Quest.QUEST_GENERALHAMELOFRESENTMENT, 4);
                    htmlid = "";
                } else {
                    htmlid = "";
                }
            }
        }

        // 长老 普洛凯尔
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80136) {
            final int lv15_step = pc.getQuest().get_step(L1Quest.QUEST_LEVEL15);
            final int lv30_step = pc.getQuest().get_step(L1Quest.QUEST_LEVEL30);
            final int lv45_step = pc.getQuest().get_step(L1Quest.QUEST_LEVEL45);
            final int lv50_step = pc.getQuest().get_step(L1Quest.QUEST_LEVEL50);
            if (pc.isDragonKnight()) {
                // 执行普洛凯尔的课题
                if (s.equalsIgnoreCase("a") && (lv15_step == 0)) {
                    final L1NpcInstance npc = (L1NpcInstance) obj;
                    final L1ItemInstance item = pc.getInventory().storeItem(
                            49210, 1); // 普洛凯尔的第一次指令书
                    final String npcName = npc.getNpcTemplate().get_name();
                    final String itemName = item.getItem().getName();
                    pc.sendPackets(new S_ServerMessage(143, npcName, itemName)); // \f1%0%s
                                                                                 // 给你
                                                                                 // %1%o
                                                                                 // 。
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL15, 1);
                    htmlid = "prokel3";
                    // 执行普洛凯尔的第二次课题
                } else if (s.equalsIgnoreCase("c") && (lv30_step == 0)) {
                    final int[] item_ids = { 49211, 49215, }; // 普洛凯尔的第二次指令书、普洛凯尔的矿物袋
                    final int[] item_amounts = { 1, 1, };
                    for (int i = 0; i < item_ids.length; i++) {
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(item_ids[i], item_amounts[i]);
                        pc.sendPackets(new S_ServerMessage(143,
                                ((L1NpcInstance) obj).getNpcTemplate()
                                        .get_name(), item.getItem().getName()));
                    }
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL30, 1);
                    htmlid = "prokel9";
                    // 需要普洛凯尔的矿物袋
                } else if (s.equalsIgnoreCase("e")) {
                    if (pc.getInventory().checkItem(49215, 1)) {
                        htmlid = "prokel35";
                    } else {
                        final L1NpcInstance npc = (L1NpcInstance) obj;
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(49215, 1); // 普洛凯尔的矿物袋
                        final String npcName = npc.getNpcTemplate().get_name();
                        final String itemName = item.getItem().getName();
                        pc.sendPackets(new S_ServerMessage(143, npcName,
                                itemName)); // \f1%0%s 给你 %1%o 。
                        htmlid = "prokel13";
                    }
                    // 执行普洛凯尔的第三次课题
                } else if (s.equalsIgnoreCase("f") && (lv45_step == 0)) {
                    final int[] item_ids = { 49209, 49212, 49226, }; // 长老普洛凯尔的信件、普洛凯尔的第三次指令书、结盟瞬间移动卷轴
                    final int[] item_amounts = { 1, 1, 1, };
                    for (int i = 0; i < item_ids.length; i++) {
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(item_ids[i], item_amounts[i]);
                        pc.sendPackets(new S_ServerMessage(143,
                                ((L1NpcInstance) obj).getNpcTemplate()
                                        .get_name(), item.getItem().getName()));
                    }
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL45, 1);
                    htmlid = "prokel16";
                    // 执行普洛凯尔的第四次课题
                } else if (s.equalsIgnoreCase("h") && (lv50_step == 0)) {
                    final int[] item_ids = { 49287, }; // 普洛凯尔的第四次指令书
                    final int[] item_amounts = { 1, };
                    for (int i = 0; i < item_ids.length; i++) {
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(item_ids[i], item_amounts[i]);
                        pc.sendPackets(new S_ServerMessage(143,
                                ((L1NpcInstance) obj).getNpcTemplate()
                                        .get_name(), item.getItem().getName()));
                    }
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL50, 1);
                    htmlid = "prokel22";
                    // 重新接收时空裂痕邪念碎片、普洛凯尔的护身符
                } else if (s.equalsIgnoreCase("k") && (lv50_step >= 2)) {
                    if (pc.getInventory().checkItem(49202, 1)
                            || pc.getInventory().checkItem(49216, 1)) {
                        htmlid = "prokel29";
                    } else {
                        final int[] item_ids = { 49202, 49216, };
                        final int[] item_amounts = { 1, 1, };
                        for (int i = 0; i < item_ids.length; i++) {
                            final L1ItemInstance item = pc.getInventory()
                                    .storeItem(item_ids[i], item_amounts[i]);
                            pc.sendPackets(new S_ServerMessage(143,
                                    ((L1NpcInstance) obj).getNpcTemplate()
                                            .get_name(), item.getItem()
                                            .getName()));
                        }
                        htmlid = "prokel28";
                    }
                }
            }
        }

        // 爱尔菈丝
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80135) {
            if (pc.isDragonKnight()) {
                // “接受妖魔密使变形卷轴”
                if (s.equalsIgnoreCase("a")) {
                    if (pc.getInventory().checkItem(49220, 1)) {
                        htmlid = "elas5";
                    } else {
                        final L1NpcInstance npc = (L1NpcInstance) obj;
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(49220, 1); // 妖魔密使变形卷轴
                        final String npcName = npc.getNpcTemplate().get_name();
                        final String itemName = item.getItem().getName();
                        pc.sendPackets(new S_ServerMessage(143, npcName,
                                itemName)); // \f1%0%s 给你 %1%o 。
                        htmlid = "elas4";
                    }
                }
            }
        }

        // 妖魔密使(HC3F)
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 81245) {
            if (pc.isDragonKnight()) {
                if (s.equalsIgnoreCase("request flute of spy")) {
                    if (pc.getInventory().checkItem(49223, 1)) { // check
                        pc.getInventory().consumeItem(49223, 1); // del
                        final L1NpcInstance npc = (L1NpcInstance) obj;
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(49222, 1); // 妖魔密使之笛子
                        final String npcName = npc.getNpcTemplate().get_name();
                        final String itemName = item.getItem().getName();
                        pc.sendPackets(new S_ServerMessage(143, npcName,
                                itemName)); // \f1%0%s 给你 %1%o 。
                        htmlid = "";
                    } else {
                        htmlid = "";
                    }
                }
            }
        }

        // 夏依蓝
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 81246) {
            if (s.equalsIgnoreCase("0")) {
                materials = new int[] { 40308 };
                counts = new int[] { 2500 };
                if (pc.getLevel() < 30) {
                    htmlid = "sharna4";
                } else if ((pc.getLevel() >= 30) && (pc.getLevel() <= 39)) {
                    createitem = new int[] { 49149 }; // 夏纳的变身卷轴(等级30)
                    createcount = new int[] { 1 };
                } else if ((pc.getLevel() >= 40) && (pc.getLevel() <= 51)) {
                    createitem = new int[] { 49150 }; // 夏纳的变身卷轴(等级40)
                    createcount = new int[] { 1 };
                } else if ((pc.getLevel() >= 52) && (pc.getLevel() <= 54)) {
                    createitem = new int[] { 49151 }; // 夏纳的变身卷轴(等级52)
                    createcount = new int[] { 1 };
                } else if ((pc.getLevel() >= 55) && (pc.getLevel() <= 59)) {
                    createitem = new int[] { 49152 }; // 夏纳的变身卷轴(等级55)
                    createcount = new int[] { 1 };
                } else if ((pc.getLevel() >= 60) && (pc.getLevel() <= 64)) {
                    createitem = new int[] { 49153 }; // 夏纳的变身卷轴(等级60)
                    createcount = new int[] { 1 };
                } else if ((pc.getLevel() >= 65) && (pc.getLevel() <= 69)) {
                    createitem = new int[] { 49154 }; // 夏纳的变身卷轴(等级65)
                    createcount = new int[] { 1 };
                } else if (pc.getLevel() >= 70) {
                    createitem = new int[] { 49155 }; // 夏纳的变身卷轴(等级70)
                    createcount = new int[] { 1 };
                }
                success_htmlid = "sharna3";
                failure_htmlid = "sharna5";
            }
        }

        //
        else if ((((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70035) // 赛西
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70041) // 波金
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70042)) { // 波丽
            if (s.equalsIgnoreCase("status")) { // status
                htmldata = new String[15];
                for (int i = 0; i < 5; i++) {
                    htmldata[i * 3] = (NpcTable.getInstance().getTemplate(
                            com.lineage.server.model.game.L1BugBearRace
                                    .getInstance().getRunner(i).getNpcId())
                            .get_nameid());
                    String condition; // 610 普通
                    if (com.lineage.server.model.game.L1BugBearRace
                            .getInstance().getCondition(i) == 0) {
                        condition = "$610";
                    } else {
                        if (com.lineage.server.model.game.L1BugBearRace
                                .getInstance().getCondition(i) > 0) { // 368 好
                            condition = "$368";
                        } else {// 370 不好
                            condition = "$370";
                        }
                    }
                    htmldata[i * 3 + 1] = condition;
                    htmldata[i * 3 + 2] = String
                            .valueOf(com.lineage.server.model.game.L1BugBearRace
                                    .getInstance().getWinningAverage(i));
                }
                htmlid = "maeno4";
            }
        }

        // 燃柳宠物商
        else if ((((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70077 // 罗德尼
                )
                || (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 81290)) { // 班酷
            int consumeItem = 0;
            int consumeItemCount = 0;
            int petNpcId = 0;
            int petItemId = 0; // 40314 低等宠物项圈
            int upLv = 0; // 等级
            long lvExp = 0; // LV.upLv 经验值
            String msg = "";
            if (s.equalsIgnoreCase("buy 1")) {
                petNpcId = 45042; // 杜宾狗
                consumeItem = 40308;
                consumeItemCount = 50000;
                petItemId = 40314;
                upLv = 5;
                lvExp = ExpTable.getExpByLevel(upLv);
                msg = "金币";
            } else if (s.equalsIgnoreCase("buy 2")) {
                petNpcId = 45034; // 牧羊犬
                consumeItem = 40308;
                consumeItemCount = 50000;
                petItemId = 40314;
                upLv = 5;
                lvExp = ExpTable.getExpByLevel(upLv);
                msg = "金币";
            } else if (s.equalsIgnoreCase("buy 3")) {
                petNpcId = 45046; // 小猎犬
                consumeItem = 40308;
                consumeItemCount = 50000;
                petItemId = 40314;
                upLv = 5;
                lvExp = ExpTable.getExpByLevel(upLv);
                msg = "金币";
            } else if (s.equalsIgnoreCase("buy 4")) {
                petNpcId = 45047; // 圣伯纳犬
                consumeItem = 40308;
                consumeItemCount = 50000;
                petItemId = 40314;
                upLv = 5;
                lvExp = ExpTable.getExpByLevel(upLv);
                msg = "金币";
            } else if (s.equalsIgnoreCase("buy 7")) {
                petNpcId = 97023; // 淘气龙
                consumeItem = 47011; // 淘气幼龙蛋
                consumeItemCount = 1;
                petItemId = 40314;
                upLv = 5;
                lvExp = ExpTable.getExpByLevel(upLv);
                msg = "淘气幼龙蛋";
            } else if (s.equalsIgnoreCase("buy 8")) {
                petNpcId = 97022; // 顽皮龙
                consumeItem = 47012; // 顽皮幼龙蛋
                consumeItemCount = 1;
                petItemId = 40314;
                upLv = 5;
                lvExp = ExpTable.getExpByLevel(upLv);
                msg = "顽皮幼龙蛋";
            }
            if (petNpcId > 0) {
                if (!pc.getInventory().checkItem(consumeItem, consumeItemCount)) {
                    pc.sendPackets(new S_ServerMessage(337, msg));
                } else if (pc.getInventory().getSize() > 180) {
                    pc.sendPackets(new S_ServerMessage(337, "身上空间")); // \f1%0不足%s。
                } else if (pc.getInventory().checkItem(consumeItem,
                        consumeItemCount)) {
                    pc.getInventory()
                            .consumeItem(consumeItem, consumeItemCount);
                    final L1PcInventory inv = pc.getInventory();
                    final L1ItemInstance petamu = inv.storeItem(petItemId, 1);
                    if (petamu != null) {
                        PetTable.getInstance()
                                .buyNewPet(petNpcId, petamu.getId() + 1,
                                        petamu.getId(), upLv, lvExp);
                        pc.sendPackets(new S_ItemName(petamu));
                        pc.sendPackets(new S_ServerMessage(403, petamu
                                .getName())); // 获得%0%o 。
                    }
                }
            } else {
                pc.sendPackets(new S_SystemMessage("对话档版本不符，请下载更新"));
            }
            htmlid = "";
        }

        // 幻术师 试练任务
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 80145) { // 长老
                                                                                // 希莲恩
            final int lv15_step = pc.getQuest().get_step(L1Quest.QUEST_LEVEL15);
            final int lv30_step = pc.getQuest().get_step(L1Quest.QUEST_LEVEL30);
            final int lv45_step = pc.getQuest().get_step(L1Quest.QUEST_LEVEL45);
            final int lv50_step = pc.getQuest().get_step(L1Quest.QUEST_LEVEL50);
            if (pc.isDragonKnight()) {
                if (s.equalsIgnoreCase("l") && (lv45_step == 1)) {
                    if (pc.getInventory().checkItem(49209, 1)) { // check
                        pc.getInventory().consumeItem(49209, 1); // del
                        pc.getQuest().set_step(L1Quest.QUEST_LEVEL45, 2);
                        htmlid = "silrein38";
                    }
                } else if (s.equalsIgnoreCase("m") && (lv45_step == 2)) {
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL45, 3);
                    htmlid = "silrein39";
                }
            }
            if (pc.isIllusionist()) {
                // 希莲恩的第一次课题
                if (s.equalsIgnoreCase("a") && (lv15_step == 0)) {
                    final int[] item_ids = { 49172, 49182, }; // 希莲恩的第一次信件、妖精森林瞬间移动卷轴
                    final int[] item_amounts = { 1, 1, };
                    for (int i = 0; i < item_ids.length; i++) {
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(item_ids[i], item_amounts[i]);
                        pc.sendPackets(new S_ServerMessage(143,
                                ((L1NpcInstance) obj).getNpcTemplate()
                                        .get_name(), item.getItem().getName()));
                    }
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL15, 1);
                    htmlid = "silrein3";
                    // 执行希莲恩的第二课题
                } else if (s.equalsIgnoreCase("c") && (lv30_step == 0)) {
                    final int[] item_ids = { 49173, 49179, }; // 希莲恩的第二次信件、希莲恩之袋
                                                              // 获得【欧瑞村庄瞬间移动卷轴、生锈的笛子】
                    final int[] item_amounts = { 1, 1, };
                    for (int i = 0; i < item_ids.length; i++) {
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(item_ids[i], item_amounts[i]);
                        pc.sendPackets(new S_ServerMessage(143,
                                ((L1NpcInstance) obj).getNpcTemplate()
                                        .get_name(), item.getItem().getName()));
                    }
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL30, 1);
                    htmlid = "silrein12";
                    // 重新接收生锈的笛子
                } else if (s.equalsIgnoreCase("o") && (lv30_step == 1)) {
                    if (pc.getInventory().checkItem(49186, 1)
                            || pc.getInventory().checkItem(49179, 1)) {
                        htmlid = "silrein17";// 已经有 希莲恩之袋、生锈的笛子 不可再取得
                    } else {
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(49186, 1); // 生锈的笛子
                        pc.sendPackets(new S_ServerMessage(143, item.getItem()
                                .getName()));
                        htmlid = "silrein16";
                    }
                    // 执行希莲恩的第三课题
                } else if (s.equalsIgnoreCase("e") && (lv45_step == 0)) {
                    final int[] item_ids = { 49174, 49180, }; // 希莲恩的第三次信件、希莲恩之袋
                                                              // 获得【风木村庄瞬间移动卷轴、时空裂痕水晶(绿色3个)】
                    final int[] item_amounts = { 1, 1, };
                    for (int i = 0; i < item_ids.length; i++) {
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(item_ids[i], item_amounts[i]);
                        pc.sendPackets(new S_ServerMessage(143,
                                ((L1NpcInstance) obj).getNpcTemplate()
                                        .get_name(), item.getItem().getName()));
                    }
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL45, 1);
                    htmlid = "silrein19";
                    // 执行希莲恩的第四课题
                } else if (s.equalsIgnoreCase("h") && (lv50_step == 0)) {
                    final int[] item_ids = { 49176, }; // 希莲恩的第五次信件
                    final int[] item_amounts = { 1, };
                    for (int i = 0; i < item_ids.length; i++) {
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(item_ids[i], item_amounts[i]);
                        pc.sendPackets(new S_ServerMessage(143,
                                ((L1NpcInstance) obj).getNpcTemplate()
                                        .get_name(), item.getItem().getName()));
                    }
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL50, 1);
                    htmlid = "silrein28";
                    // 重新接收时空裂痕邪念碎片、希莲恩的护身符
                } else if (s.equalsIgnoreCase("k") && (lv50_step >= 2)) {
                    if (pc.getInventory().checkItem(49202, 1) // 时空裂痕邪念碎片
                            || pc.getInventory().checkItem(49178, 1)) { // 希莲恩的护身符
                        htmlid = "silrein32";
                    } else {
                        final int[] item_ids = { 49202, 49178, };
                        final int[] item_amounts = { 1, 1, };
                        for (int i = 0; i < item_ids.length; i++) {
                            final L1ItemInstance item = pc.getInventory()
                                    .storeItem(item_ids[i], item_amounts[i]);
                            pc.sendPackets(new S_ServerMessage(143,
                                    ((L1NpcInstance) obj).getNpcTemplate()
                                            .get_name(), item.getItem()
                                            .getName()));
                        }
                        htmlid = "silrein32";
                    }
                }
            }
        }

        // 迪嘉勒廷
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 70739) {
            if (pc.isCrown()) {
                if (s.equalsIgnoreCase("e")) {
                    if (pc.getInventory().checkItem(49159, 1)) { // 调职命令书
                        htmlid = "dicardingp5";
                        pc.getInventory().consumeItem(49159, 1);
                        pc.getQuest().set_step(L1Quest.QUEST_LEVEL50, 2);
                    } else {
                        htmlid = "dicardingp4a";
                    }
                } else if (s.equalsIgnoreCase("d")) {
                    htmlid = "dicardingp7";
                    L1PolyMorph.doPoly(pc, 6035, 900, 1, true);
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL50, 3);
                } else if (s.equalsIgnoreCase("c")) {
                    htmlid = "dicardingp9";
                    L1PolyMorph.undoPoly(pc);
                    L1PolyMorph.doPoly(pc, 6035, 900, 1, true);
                } else if (s.equalsIgnoreCase("b")) {
                    htmlid = "dicardingp12";
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL50, 4);
                    if (pc.getInventory().checkItem(49165)) { // 圣殿2楼钥匙
                        pc.getInventory().consumeItem(49165,
                                pc.getInventory().countItems(49165));
                    }
                    if (pc.getInventory().checkItem(49166)) { // 圣殿3楼钥匙
                        pc.getInventory().consumeItem(49166,
                                pc.getInventory().countItems(49166));
                    }
                    if (pc.getInventory().checkItem(49167)) { // 魔之角笛
                        pc.getInventory().consumeItem(49167,
                                pc.getInventory().countItems(49167));
                    }
                    if (pc.getInventory().checkItem(49168)) { // 破坏之秘药
                        pc.getInventory().consumeItem(49168,
                                pc.getInventory().countItems(49168));
                    }
                    if (pc.getInventory().checkItem(49239)) { // 消灭之意志
                        pc.getInventory().consumeItem(49239,
                                pc.getInventory().countItems(49239));
                    }
                }
            }
            if (pc.isKnight()) {
                if (s.equalsIgnoreCase("h")) {
                    if (pc.getInventory().checkItem(49160, 1)) { // 丹特斯的召书
                        htmlid = "dicardingk5";
                        pc.getInventory().consumeItem(49160, 1);
                        pc.getQuest().set_step(L1Quest.QUEST_LEVEL50, 2);
                    }
                } else if (s.equalsIgnoreCase("j")) {
                    htmlid = "dicardingk10";
                    pc.getInventory().consumeItem(49161, 10); // 精灵的私语
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL50, 4);
                } else if (s.equalsIgnoreCase("k")) {
                    htmlid = "dicardingk13";
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL50, 4);
                    if (pc.getInventory().checkItem(49165)) { // 圣殿2楼钥匙
                        pc.getInventory().consumeItem(49165,
                                pc.getInventory().countItems(49165));
                    }
                    if (pc.getInventory().checkItem(49166)) { // 圣殿3楼钥匙
                        pc.getInventory().consumeItem(49166,
                                pc.getInventory().countItems(49166));
                    }
                    if (pc.getInventory().checkItem(49167)) { // 魔之角笛
                        pc.getInventory().consumeItem(49167,
                                pc.getInventory().countItems(49167));
                    }
                    if (pc.getInventory().checkItem(49168)) { // 破坏之秘药
                        pc.getInventory().consumeItem(49168,
                                pc.getInventory().countItems(49168));
                    }
                    if (pc.getInventory().checkItem(49239)) { // 消灭之意志
                        pc.getInventory().consumeItem(49239,
                                pc.getInventory().countItems(49239));
                    }
                }
            }
            if (pc.isElf()) {
                if (s.equalsIgnoreCase("n")) {
                    if (pc.getInventory().checkItem(49162, 1)) { // 古代黑妖之秘笈
                        htmlid = "dicardinge5";
                        pc.getInventory().consumeItem(49162, 1);
                        pc.getQuest().set_step(L1Quest.QUEST_LEVEL50, 2);
                    }
                } else if (s.equalsIgnoreCase("p")) {
                    htmlid = "dicardinge10";
                    pc.getInventory().consumeItem(49163, 1); // 密封的情报书
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL50, 5);
                } else if (s.equalsIgnoreCase("q")) {
                    htmlid = "dicardinge14";
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL50, 5);
                    if (pc.getInventory().checkItem(49165)) { // 圣殿2楼钥匙
                        pc.getInventory().consumeItem(49165,
                                pc.getInventory().countItems(49165));
                    }
                    if (pc.getInventory().checkItem(49166)) { // 圣殿3楼钥匙
                        pc.getInventory().consumeItem(49166,
                                pc.getInventory().countItems(49166));
                    }
                    if (pc.getInventory().checkItem(49167)) { // 魔之角笛
                        pc.getInventory().consumeItem(49167,
                                pc.getInventory().countItems(49167));
                    }
                    if (pc.getInventory().checkItem(49168)) { // 破坏之秘药
                        pc.getInventory().consumeItem(49168,
                                pc.getInventory().countItems(49168));
                    }
                    if (pc.getInventory().checkItem(49239)) { // 消灭之意志
                        pc.getInventory().consumeItem(49239,
                                pc.getInventory().countItems(49239));
                    }
                }
            }
            if (pc.isWizard()) {
                if (s.equalsIgnoreCase("u")) {
                    if (pc.getInventory().checkItem(49164, 1)) { // 间谍报告书
                        htmlid = "dicardingw6";
                        pc.getInventory().consumeItem(49164, 1);
                        pc.getQuest().set_step(L1Quest.QUEST_LEVEL50, 3);
                    }
                } else if (s.equalsIgnoreCase("w")) {
                    htmlid = "dicardingw12";
                    pc.getQuest().set_step(L1Quest.QUEST_LEVEL50, 4);
                    if (pc.getInventory().checkItem(49165)) { // 圣殿2楼钥匙
                        pc.getInventory().consumeItem(49165,
                                pc.getInventory().countItems(49165));
                    }
                    if (pc.getInventory().checkItem(49166)) { // 圣殿3楼钥匙
                        pc.getInventory().consumeItem(49166,
                                pc.getInventory().countItems(49166));
                    }
                    if (pc.getInventory().checkItem(49167)) { // 魔之角笛
                        pc.getInventory().consumeItem(49167,
                                pc.getInventory().countItems(49167));
                    }
                    if (pc.getInventory().checkItem(49168)) { // 破坏之秘药
                        pc.getInventory().consumeItem(49168,
                                pc.getInventory().countItems(49168));
                    }
                    if (pc.getInventory().checkItem(49239)) { // 消灭之意志
                        pc.getInventory().consumeItem(49239,
                                pc.getInventory().countItems(49239));
                    }
                }
            }
        }

        // 被遗弃的肉身
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 81334) {
            if (s.equalsIgnoreCase("a")) {
                if (pc.getInventory().checkItem(49239, 1)) { //
                    htmlid = "rtf06";
                } else {
                    final int[] item_ids = { 49239, };
                    final int[] item_amounts = { 1, };
                    for (int i = 0; i < item_ids.length; i++) {
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(item_ids[i], item_amounts[i]);
                        pc.sendPackets(new S_ServerMessage(143,
                                ((L1NpcInstance) obj).getNpcTemplate()
                                        .get_name(), item.getItem().getName()));
                    }
                }
            }
        }

        // 魔法商人- 仿正设定
        else if ((((L1NpcInstance) obj).getNpcTemplate().get_npcId() >= 81353)
                && (((L1NpcInstance) obj).getNpcTemplate().get_npcId() <= 81363)) {
            int[] skills = new int[10];
            final char s1 = s.charAt(0);
            switch (s1) {
                case 'b':
                    skills = new int[] { 43, 79, 151, 158, 160, 206, 211, 216,
                            115, 149 };
                    break;
                case 'a':
                    skills = new int[] { 43, 79, 151, 158, 160, 206, 211, 216,
                            115, 148 };
                    break;
            }
            if (s.equalsIgnoreCase("a") || s.equalsIgnoreCase("b")) {
                if (pc.getInventory().consumeItem(40308, 3000)) {
                    final L1SkillUse l1skilluse = new L1SkillUse();
                    for (final int skill : skills) {
                        l1skilluse.handleCommands(pc, skill, pc.getId(),
                                pc.getX(), pc.getY(), null, 0,
                                L1SkillUse.TYPE_GMBUFF);
                    }
                    htmlid = "bs_done";
                } else {
                    htmlid = "bs_adena";
                }
            }
            if (s.equalsIgnoreCase("0")) {
                htmlid = "bs_01";
            }
        }

        // 杰诺
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 50016) {
            if (s.equalsIgnoreCase("0")) {
                if (pc.getLevel() < 13) { // lv < 13 传送隐藏之谷
                    L1Teleport
                            .teleport(pc, 32682, 32874, (short) 2005, 2, true);
                } else {
                    htmlid = "zeno1";
                }
            }
        }

        // 鲁比恩
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 50065) {
            if (s.equalsIgnoreCase("teleport valley-in")) {
                if (pc.getLevel() < 13) { // lv < 13 传送隐藏之谷
                    L1Teleport
                            .teleport(pc, 32682, 32874, (short) 2005, 2, true);
                } else {
                    htmlid = "";
                }
            }
        }

        // 德瑞斯特
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 50055) {
            if (s.equalsIgnoreCase("teleport hidden-valley")) {
                if (pc.getLevel() < 13) { // lv < 13 传送隐藏之谷
                    L1Teleport
                            .teleport(pc, 32682, 32874, (short) 2005, 2, true);
                } else {
                    htmlid = "drist1";
                }
            }
        }

        // 新手帮助
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 81255) {
            @SuppressWarnings("unused")
            final int quest_step = pc.getQuest().get_step(L1Quest.QUEST_TUTOR); // 任务编号阶段
            final int level = pc.getLevel(); // 角色等级
            final char s1 = s.charAt(0);
            if (level < 13) {
                switch (s1) {
                    case 'A':
                    case 'a': // isCrown
                        if ((level > 1) && (level < 5)) { // lv2 ~ lv4
                            htmlid = "tutorp1"; // 指引
                        } else if ((level > 4) && (level < 8)) { // lv5 ~ lv7
                            htmlid = "tutorp2"; // 传送服务
                        } else if ((level > 7) && (level < 10)) { // lv8 ~ lv9
                            htmlid = "tutorp3"; // 传送服务
                        } else if ((level > 9) && (level < 12)) { // lv10 ~ lv11
                            htmlid = "tutorp4"; // 传送服务
                        } else if ((level > 11) && (level < 13)) { // lv12
                            htmlid = "tutorp5"; // 传送服务
                        } else if (level > 12) { // lv13
                            htmlid = "tutorp6"; // 离开隐藏之谷
                        } else {
                            htmlid = "tutorend";
                        }
                        break;
                    case 'B':
                    case 'b': // isKnight
                        if ((level > 1) && (level < 5)) { // lv2 ~ lv4
                            htmlid = "tutork1"; // 接受帮助
                        } else if ((level > 4) && (level < 8)) { // lv5 ~ lv7
                            htmlid = "tutork2"; // 传送服务
                        } else if ((level > 7) && (level < 10)) { // lv8 ~ lv9
                            htmlid = "tutork3"; // 传送服务
                        } else if ((level > 9) && (level < 13)) { // lv10 ~ lv12
                            htmlid = "tutork4"; // 传送服务
                        } else if (level > 12) { // lv13
                            htmlid = "tutork5"; // 离开隐藏之谷
                        } else {
                            htmlid = "tutorend";
                        }
                        break;
                    case 'C':
                    case 'c': // isElf
                        if ((level > 1) && (level < 5)) { // lv2 ~ lv4
                            htmlid = "tutore1"; // 接受帮助
                        } else if ((level > 4) && (level < 8)) { // lv5 ~ lv7
                            htmlid = "tutore2"; // 传送服务
                        } else if ((level > 7) && (level < 10)) { // lv8 ~ lv9
                            htmlid = "tutore3"; // 传送服务
                        } else if ((level > 9) && (level < 12)) { // lv10 ~ lv11
                            htmlid = "tutore4"; // 传送服务
                        } else if ((level > 11) && (level < 13)) { // lv12
                            htmlid = "tutore5"; // 传送服务
                        } else if (level > 12) { // lv13
                            htmlid = "tutore6"; // 离开隐藏之谷
                        } else {
                            htmlid = "tutorend";
                        }
                        break;
                    case 'D':
                    case 'd': // isWizard
                        if ((level > 1) && (level < 5)) { // lv2 ~ lv4
                            htmlid = "tutorm1"; // 接受帮助
                        } else if ((level > 4) && (level < 8)) { // lv5 ~ lv7
                            htmlid = "tutorm2"; // 传送服务
                        } else if ((level > 7) && (level < 10)) { // lv8 ~ lv9
                            htmlid = "tutorm3"; // 传送服务
                        } else if ((level > 9) && (level < 12)) { // lv10 ~ lv11
                            htmlid = "tutorm4"; // 传送服务
                        } else if ((level > 11) && (level < 13)) { // lv12
                            htmlid = "tutorm5"; // 传送服务
                        } else if (level > 12) { // lv13
                            htmlid = "tutorm6"; // 离开隐藏之谷
                        } else {
                            htmlid = "tutorend";
                        }
                        break;
                    case 'E':
                    case 'e': // isDarkelf
                        if ((level > 1) && (level < 5)) { // lv2 ~ lv4
                            htmlid = "tutord1"; // 接受帮助
                        } else if ((level > 4) && (level < 8)) { // lv5 ~ lv7
                            htmlid = "tutord2"; // 传送服务
                        } else if ((level > 7) && (level < 10)) { // lv8 ~ lv9
                            htmlid = "tutord3"; // 传送服务
                        } else if ((level > 9) && (level < 12)) { // lv10 ~ lv11
                            htmlid = "tutord4"; // 传送服务
                        } else if ((level > 11) && (level < 13)) { // lv12
                            htmlid = "tutord5"; // 传送服务
                        } else if (level > 12) { // lv13
                            htmlid = "tutord6"; // 离开隐藏之谷
                        } else {
                            htmlid = "tutorend";
                        }
                        break;
                    case 'F':
                    case 'f': // isDragonKnight
                        if ((level > 1) && (level < 5)) { // lv2 ~ lv4
                            htmlid = "tutordk1"; // 接受帮助
                        } else if ((level > 4) && (level < 8)) { // lv5 ~ lv7
                            htmlid = "tutordk2"; // 传送服务
                        } else if ((level > 7) && (level < 10)) { // lv8 ~ lv9
                            htmlid = "tutordk3"; // 传送服务
                        } else if ((level > 9) && (level < 13)) { // lv10 ~ lv12
                            htmlid = "tutordk4"; // 传送服务
                        } else if (level > 12) { // lv13
                            htmlid = "tutordk5"; // 离开隐藏之谷
                        } else {
                            htmlid = "tutorend";
                        }
                        break;
                    case 'G':
                    case 'g': // isIllusionist
                        if ((level > 1) && (level < 5)) { // lv2 ~ lv4
                            htmlid = "tutori1"; // 接受帮助
                        } else if ((level > 4) && (level < 8)) { // lv5 ~ lv7
                            htmlid = "tutori2"; // 传送服务
                        } else if ((level > 7) && (level < 10)) { // lv8 ~ lv9
                            htmlid = "tutori3"; // 传送服务
                        } else if ((level > 9) && (level < 13)) { // lv10 ~ lv12
                            htmlid = "tutori4"; // 传送服务
                        } else if (level > 12) { // lv13
                            htmlid = "tutori5"; // 离开隐藏之谷
                        } else {
                            htmlid = "tutorend";
                        }
                        break;
                    case 'H':
                    case 'h':
                        L1Teleport.teleport(pc, 32575, 32945, (short) 0, 5,
                                true); // 说话之岛仓库管理员
                        htmlid = "";
                        break;
                    case 'I':
                    case 'i':
                        L1Teleport.teleport(pc, 32579, 32923, (short) 0, 5,
                                true); // 血盟执行人
                        htmlid = "";
                        break;
                    case 'J':
                    case 'j':
                        createitem = new int[] { 42099 };
                        createcount = new int[] { 1 };
                        L1Teleport.teleport(pc, 32676, 32813, (short) 2005, 5,
                                true); // 隐藏之谷地下洞穴
                        htmlid = "";
                        break;
                    case 'K':
                    case 'k':
                        L1Teleport.teleport(pc, 32562, 33082, (short) 0, 5,
                                true); // 魔法师吉伦小屋
                        htmlid = "";
                        break;
                    case 'L':
                    case 'l':
                        L1Teleport.teleport(pc, 32792, 32820, (short) 75, 5,
                                true); // 象牙塔
                        htmlid = "";
                        break;
                    case 'M':
                    case 'm':
                        L1Teleport.teleport(pc, 32877, 32904, (short) 304, 5,
                                true); // 黑暗魔法师赛帝亚
                        htmlid = "";
                        break;
                    case 'N':
                    case 'n':
                        L1Teleport.teleport(pc, 32759, 32884, (short) 1000, 5,
                                true); // 幻术士史菲尔
                        htmlid = "";
                        break;
                    case 'O':
                    case 'o':
                        L1Teleport.teleport(pc, 32605, 32837, (short) 2005, 5,
                                true); // 村庄西郊
                        htmlid = "";
                        break;
                    case 'P':
                    case 'p':
                        L1Teleport.teleport(pc, 32733, 32902, (short) 2005, 5,
                                true); // 村庄东郊
                        htmlid = "";
                        break;
                    case 'Q':
                    case 'q':
                        L1Teleport.teleport(pc, 32559, 32843, (short) 2005, 5,
                                true); // 村庄南部狩猎场
                        htmlid = "";
                        break;
                    case 'R':
                    case 'r':
                        L1Teleport.teleport(pc, 32677, 32982, (short) 2005, 5,
                                true); // 村庄东南部狩猎场
                        htmlid = "";
                        break;
                    case 'S':
                    case 's':
                        L1Teleport.teleport(pc, 32781, 32854, (short) 2005, 5,
                                true); // 村庄东北部狩猎场
                        htmlid = "";
                        break;
                    case 'T':
                    case 't':
                        L1Teleport.teleport(pc, 32674, 32739, (short) 2005, 5,
                                true); // 村庄西北部狩猎场
                        htmlid = "";
                        break;
                    case 'U':
                    case 'u':
                        L1Teleport.teleport(pc, 32578, 32737, (short) 2005, 5,
                                true); // 村庄西部狩猎场
                        htmlid = "";
                        break;
                    case 'V':
                    case 'v':
                        L1Teleport.teleport(pc, 32542, 32996, (short) 2005, 5,
                                true); // 村庄南部狩猎场
                        htmlid = "";
                        break;
                    case 'W':
                    case 'w':
                        L1Teleport.teleport(pc, 32794, 32973, (short) 2005, 5,
                                true); // 村庄东部狩猎场
                        htmlid = "";
                        break;
                    case 'X':
                    case 'x':
                        L1Teleport.teleport(pc, 32803, 32789, (short) 2005, 5,
                                true); // 村庄北部狩猎场
                        htmlid = "";
                        break;
                    default:
                        break;
                }
            }
        }

        // 修练场管理员
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 81256) {
            final int quest_step = pc.getQuest().get_step(L1Quest.QUEST_TUTOR2); // 任务编号阶段
            final int level = pc.getLevel(); // 角色等级
            @SuppressWarnings("unused")
            final boolean isOK = false;
            if (s.equalsIgnoreCase("A")) {
                if ((level > 4) && (quest_step == 2)) {
                    createitem = new int[] { 20028, 20126, 20173, 20206, 20232,
                            40029, 40030, 40098, 40099, 42099 }; // 获得装备
                    createcount = new int[] { 1, 1, 1, 1, 1, 50, 5, 20, 30, 5 }; // 装备数量
                    questid = L1Quest.QUEST_TUTOR2;
                    questvalue = 3;
                }
            }
            htmlid = "";
        }

        // 旅人谘询员
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 81257) {
            final int level = pc.getLevel(); // 角色等级
            final char s1 = s.charAt(0);
            if (level < 46) {
                switch (s1) {
                    case 'A':
                    case 'a':
                        L1Teleport.teleport(pc, 32562, 33082, (short) 0, 5,
                                true); // 魔法师吉伦小屋
                        htmlid = "";
                        break;
                    case 'B':
                    case 'b':
                        L1Teleport.teleport(pc, 33119, 32933, (short) 4, 5,
                                true); // 正义神殿
                        htmlid = "";
                        break;
                    case 'C':
                    case 'c':
                        L1Teleport.teleport(pc, 32887, 32652, (short) 4, 5,
                                true); // 邪恶神殿
                        htmlid = "";
                        break;
                    case 'D':
                    case 'd':
                        L1Teleport.teleport(pc, 32792, 32820, (short) 75, 5,
                                true); // 贩售妖精精灵魔法的琳达
                        htmlid = "";
                        break;
                    case 'E':
                    case 'e':
                        L1Teleport.teleport(pc, 32789, 32851, (short) 76, 5,
                                true); // 象牙塔的精灵魔法修炼室
                        htmlid = "";
                        break;
                    case 'F':
                    case 'f':
                        L1Teleport.teleport(pc, 32750, 32847, (short) 76, 5,
                                true); // 象牙塔的艾利温
                        htmlid = "";
                        break;
                    case 'G':
                    case 'g':
                        if (pc.isDarkelf()) {
                            L1Teleport.teleport(pc, 32877, 32904, (short) 304,
                                    5, true); // 黑暗魔法师赛帝亚
                            htmlid = "";
                        } else {
                            htmlid = "lowlv40";
                        }
                        break;
                    case 'H':
                    case 'h':
                        if (pc.isDragonKnight()) {
                            L1Teleport.teleport(pc, 32811, 32873, (short) 1001,
                                    5, true); // 贩售龙骑士技能的森帕尔处
                            htmlid = "";
                        } else {
                            htmlid = "lowlv41";
                        }
                        break;
                    case 'I':
                    case 'i':
                        if (pc.isIllusionist()) {
                            L1Teleport.teleport(pc, 32759, 32884, (short) 1000,
                                    5, true); // 贩售幻术士魔法的史菲尔处
                            htmlid = "";
                        } else {
                            htmlid = "lowlv42";
                        }
                        break;
                    case 'J':
                    case 'j':
                        L1Teleport.teleport(pc, 32509, 32867, (short) 0, 5,
                                true); // 说话之岛的甘特处
                        htmlid = "";
                        break;
                    case 'K':
                    case 'k':
                        if ((level > 34)) {
                            createitem = new int[] { 20282, 21139 }; // 补充象牙塔饰品
                            createcount = new int[] { 0, 0 };
                            boolean isOK = false;
                            for (int i = 0; i < createitem.length; i++) {
                                if (!pc.getInventory().checkItem(createitem[i],
                                        1)) { // check
                                    createcount[i] = 1;
                                    isOK = true;
                                }
                            }
                            if (isOK) {
                                success_htmlid = "lowlv43";
                            } else {
                                htmlid = "lowlv45";
                            }
                        } else {
                            htmlid = "lowlv44";
                        }
                        break;
                    case '0':
                        if (level < 13) {
                            htmlid = "lowlvS1";
                        } else if ((level > 12) && (level < 46)) {
                            htmlid = "lowlvS2";
                        } else {
                            htmlid = "lowlvno";
                        }
                        break;
                    case '1':
                        if (level < 13) {
                            htmlid = "lowlv14";
                        } else if ((level > 12) && (level < 46)) {
                            htmlid = "lowlv15";
                        } else {
                            htmlid = "lowlvno";
                        }
                        break;
                    case '2':
                        createitem = new int[] { 20028, 20126, 20173, 20206,
                                20232, 21138, 49310 }; // 补充象牙塔装备
                        createcount = new int[] { 0, 0, 0, 0, 0, 0, 0 };
                        boolean isOK = false;
                        for (int i = 0; i < createitem.length; i++) {
                            if (createitem[i] == 49310) {
                                final L1ItemInstance item = pc.getInventory()
                                        .findItemId(createitem[i]);
                                if (item != null) {
                                    if (item.getCount() < 1000) {
                                        createcount[i] = 1000 - item.getCount();
                                        isOK = true;
                                    }
                                } else {
                                    createcount[i] = 1000;
                                    isOK = true;
                                }
                            } else if (!pc.getInventory().checkItem(
                                    createitem[i], 1)) { // check
                                createcount[i] = 1;
                                isOK = true;
                            }
                        }
                        if (isOK) {
                            success_htmlid = "lowlv16";
                        } else {
                            htmlid = "lowlv17";
                        }
                        break;
                    case '6':
                        if (!pc.getInventory().checkItem(49313, 1)
                                && !pc.getInventory().checkItem(49314, 1)) {
                            createitem = new int[] { 49313 }; // 象牙塔魔法袋
                            createcount = new int[] { 2 };
                            materials = new int[] { 40308 };
                            counts = new int[] { 2000 };
                            success_htmlid = "lowlv22";
                            failure_htmlid = "lowlv20";
                        } else if (pc.getInventory().checkItem(49313, 1)
                                || pc.getInventory().checkItem(49314, 1)) {
                            htmlid = "lowlv23";
                        } else {
                            htmlid = "lowlvno";
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        // 村庄福利员
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 81260) {
            final int townid = pc.getHomeTownId(); // 角色所属村庄
            final char s1 = s.charAt(0);
            if ((pc.getLevel() > 9) && (townid > 0) && (townid < 11)) {
                switch (s1) {
                    case '0':
                        createitem = new int[] { 49305 }; // 制作 福利勇敢药水
                                                          // addContribution + 2
                        createcount = new int[] { 1 };
                        materials = new int[] { 40308, 40014 };
                        counts = new int[] { 1000, 3 };
                        contribution = 2;
                        htmlid = "";
                        break;
                    case '1':
                        createitem = new int[] { 49304 }; // 制作 福利森林药水
                                                          // addContribution + 4
                        createcount = new int[] { 1 };
                        materials = new int[] { 40308, 40068 };
                        counts = new int[] { 1000, 3 };
                        contribution = 4;
                        htmlid = "";
                        break;
                    case '2':
                        createitem = new int[] { 49307 }; // 制作 福利慎重药水
                                                          // addContribution + 2
                        createcount = new int[] { 1 };
                        materials = new int[] { 40308, 40016 };
                        counts = new int[] { 500, 3 };
                        contribution = 2;
                        htmlid = "";
                        break;
                    case '3':
                        createitem = new int[] { 49306 }; // 制作 福利蓝色药水
                                                          // addContribution + 2
                        createcount = new int[] { 1 };
                        materials = new int[] { 40308, 40015 };
                        counts = new int[] { 1000, 3 };
                        contribution = 2;
                        htmlid = "";
                        break;
                    case '4':
                        createitem = new int[] { 49302 }; // 制作 福利加速药水
                                                          // addContribution + 1
                        createcount = new int[] { 1 };
                        materials = new int[] { 40308, 40013 };
                        counts = new int[] { 500, 3 };
                        contribution = 1;
                        htmlid = "";
                        break;
                    case '5':
                        createitem = new int[] { 49303 }; // 制作 福利呼吸药水
                                                          // addContribution + 1
                        createcount = new int[] { 1 };
                        materials = new int[] { 40308, 40032 };
                        counts = new int[] { 500, 3 };
                        contribution = 1;
                        htmlid = "";
                        break;
                    case '6':
                        createitem = new int[] { 49308 }; // 制作 福利变形药水
                                                          // addContribution + 3
                        createcount = new int[] { 1 };
                        materials = new int[] { 40308, 40088 };
                        counts = new int[] { 1000, 3 };
                        contribution = 3;
                        htmlid = "";
                        break;
                    case 'A':
                    case 'a':
                        switch (townid) {
                            case 1:
                                createitem = new int[] { 49292 }; // 购买
                                                                  // 福利传送卷轴：说话之岛
                                createcount = new int[] { 1 };
                                materials = new int[] { 40308 };
                                counts = new int[] { 400 };
                                htmlid = "";
                                break;
                            case 2:
                                createitem = new int[] { 49297 }; // 购买
                                                                  // 福利传送卷轴：银骑士
                                createcount = new int[] { 1 };
                                materials = new int[] { 40308 };
                                counts = new int[] { 400 };
                                htmlid = "";
                                break;
                            case 3:
                                createitem = new int[] { 49293 }; // 购买
                                                                  // 福利传送卷轴：古鲁丁
                                createcount = new int[] { 1 };
                                materials = new int[] { 40308 };
                                counts = new int[] { 400 };
                                htmlid = "";
                                break;
                            case 4:
                                createitem = new int[] { 49296 }; // 购买
                                                                  // 福利传送卷轴：燃柳
                                createcount = new int[] { 1 };
                                materials = new int[] { 40308 };
                                counts = new int[] { 400 };
                                htmlid = "";
                                break;
                            case 5:
                                createitem = new int[] { 49295 }; // 购买
                                                                  // 福利传送卷轴：风木
                                createcount = new int[] { 1 };
                                materials = new int[] { 40308 };
                                counts = new int[] { 400 };
                                htmlid = "";
                                break;
                            case 6:
                                createitem = new int[] { 49294 }; // 购买
                                                                  // 福利传送卷轴：肯特
                                createcount = new int[] { 1 };
                                materials = new int[] { 40308 };
                                counts = new int[] { 400 };
                                htmlid = "";
                                break;
                            case 7:
                                createitem = new int[] { 49298 }; // 购买
                                                                  // 福利传送卷轴：奇岩
                                createcount = new int[] { 1 };
                                materials = new int[] { 40308 };
                                counts = new int[] { 400 };
                                htmlid = "";
                                break;
                            case 8:
                                createitem = new int[] { 49299 }; // 购买
                                                                  // 福利传送卷轴：海音
                                createcount = new int[] { 1 };
                                materials = new int[] { 40308 };
                                counts = new int[] { 400 };
                                htmlid = "";
                                break;
                            case 9:
                                createitem = new int[] { 49301 }; // 购买
                                                                  // 福利传送卷轴：威顿
                                createcount = new int[] { 1 };
                                materials = new int[] { 40308 };
                                counts = new int[] { 400 };
                                htmlid = "";
                                break;
                            case 10:
                                createitem = new int[] { 49300 }; // 购买
                                                                  // 福利传送卷轴：欧瑞
                                createcount = new int[] { 1 };
                                materials = new int[] { 40308 };
                                counts = new int[] { 400 };
                                htmlid = "";
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        // 多鲁嘉贝尔
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 81278) { // 多鲁嘉之袋
            if (s.equalsIgnoreCase("0")) {
                if (pc.getInventory().checkItem(46000, 1)) { // 检查身上是否有多鲁嘉之袋
                    htmlid = "veil3"; // 已经有袋子了
                } else if (pc.getInventory().checkItem(40308, 1000000)) { // 检查身上金币是否足够
                    pc.getInventory().consumeItem(40308, 1000000);
                    pc.getInventory().storeItem(46000, 1);
                    htmlid = "veil7"; // 购买成功显示
                } else if (!pc.getInventory().checkItem(40308, 1000000)) { // 检查身上金币是否足够
                    htmlid = "veil4"; // 钱不够显示 我们还是不要约定了
                }
            } else if (s.equalsIgnoreCase("1")) {
                htmlid = "veil9"; // 听取建议
            }
        }

        // 隐匿的巨龙谷入口
        else if (((L1NpcInstance) obj).getNpcTemplate().get_npcId() == 81277) {
            final int level = pc.getLevel(); // 角色等级
            final char s1 = s.charAt(0);
            if (s.equalsIgnoreCase("0")) {
                if ((level >= 30) && (level <= 51)) {
                    L1Teleport
                            .teleport(pc, 32820, 32904, (short) 1002, 5, true); // 前往侏儒部落
                    htmlid = "";
                } else {
                    htmlid = "dsecret3";
                }
            } else if (level >= 52) {
                switch (s1) {
                    case '1':
                        L1Teleport.teleport(pc, 32904, 32627, (short) 1002, 5,
                                true); // 前往造化之地(地)
                        break;
                    case '2':
                        L1Teleport.teleport(pc, 32793, 32593, (short) 1002, 5,
                                true); // 前往造化之地(火)
                        break;
                    case '3':
                        L1Teleport.teleport(pc, 32874, 32785, (short) 1002, 5,
                                true); // 前往造化之地(水)
                        break;
                    case '4':
                        L1Teleport.teleport(pc, 32993, 32716, (short) 1002, 4,
                                true); // 前往造化之地(风)
                        break;
                    case '5':
                        L1Teleport.teleport(pc, 32698, 32664, (short) 1002, 6,
                                true); // 前往龙之墓(北边)
                        break;
                    case '6':
                        L1Teleport.teleport(pc, 32710, 32759, (short) 1002, 6,
                                true); // 前往龙之墓(南边)
                        break;
                    case '7':
                        L1Teleport.teleport(pc, 32986, 32630, (short) 1002, 4,
                                true); // 前往苍空之谷
                        break;
                }
                htmlid = "";
            } else {
                htmlid = "dsecret3";
            }
        }

        // else System.out.println("C_NpcAction: " + s);
        if ((htmlid != null) && htmlid.equalsIgnoreCase("colos2")) {
            htmldata = this.makeUbInfoStrings(((L1NpcInstance) obj)
                    .getNpcTemplate().get_npcId());
        }
        if (createitem != null) { // 净化道具
            boolean isCreate = true;
            if (materials != null) {
                for (int j = 0; j < materials.length; j++) {
                    if (!pc.getInventory().checkItemNotEquipped(materials[j],
                            counts[j])) {
                        final L1Item temp = ItemTable.getInstance()
                                .getTemplate(materials[j]);
                        pc.sendPackets(new S_ServerMessage(337, temp.getName())); // \f1%0不足%s。
                        isCreate = false;
                    }
                }
            }

            if (isCreate) {
                // 容量と重量の计算
                int create_count = 0; // 道具个数（缠まる物は1个）
                int create_weight = 0;
                for (int k = 0; k < createitem.length; k++) {
                    if ((createitem[k] > 0) && (createcount[k] > 0)) {
                        final L1Item temp = ItemTable.getInstance()
                                .getTemplate(createitem[k]);
                        if (temp != null) {
                            if (temp.isStackable()) {
                                if (!pc.getInventory().checkItem(createitem[k])) {
                                    create_count += 1;
                                }
                            } else {
                                create_count += createcount[k];
                            }
                            create_weight += temp.getWeight() * createcount[k]
                                    / 1000;
                        }
                    }
                }
                // 容量确认
                if (pc.getInventory().getSize() + create_count > 180) {
                    pc.sendPackets(new S_ServerMessage(263)); // \f1一个角色最多可携带180个道具。
                    return;
                }
                // 重量确认
                if (pc.getMaxWeight() < pc.getInventory().getWeight()
                        + create_weight) {
                    pc.sendPackets(new S_ServerMessage(82)); // 此物品太重了，所以你无法携带。
                    return;
                }

                if (materials != null) {
                    for (int j = 0; j < materials.length; j++) {
                        // 材料消费
                        pc.getInventory().consumeItem(materials[j], counts[j]);
                    }
                }
                for (int k = 0; k < createitem.length; k++) {
                    if ((createitem[k] > 0) && (createcount[k] > 0)) {
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(createitem[k], createcount[k]);

                        if (item != null) {
                            final String itemName = ItemTable.getInstance()
                                    .getTemplate(createitem[k]).getName();
                            String createrName = "";
                            if (obj instanceof L1NpcInstance) {
                                createrName = ((L1NpcInstance) obj)
                                        .getNpcTemplate().get_name();
                            }
                            if (createcount[k] > 1) {
                                pc.sendPackets(new S_ServerMessage(143,
                                        createrName, itemName + " ("
                                                + createcount[k] + ")")); // \f1%0%s
                                                                          // 给你
                                                                          // %1%o
                                                                          // 。
                            } else {
                                pc.sendPackets(new S_ServerMessage(143,
                                        createrName, itemName)); // \f1%0%s 给你
                                                                 // %1%o 。
                            }
                        }
                    }
                }
                if (success_htmlid != null) { // html指定がある场合は表示
                    pc.sendPackets(new S_NPCTalkReturn(objid, success_htmlid,
                            htmldata));
                }
                if (questid > 0) {
                    pc.getQuest().set_step(questid, questvalue);
                }
                if (contribution > 0) {
                    pc.addContribution(contribution);
                }
            } else { // 精制失败
                if (failure_htmlid != null) { // html指定がある场合は表示
                    pc.sendPackets(new S_NPCTalkReturn(objid, failure_htmlid,
                            htmldata));
                }
            }
        }

        if (htmlid != null) { // html指定がある场合は表示
            pc.sendPackets(new S_NPCTalkReturn(objid, htmlid, htmldata));
        }
    }

    // 进入幽灵之家
    private String enterHauntedHouse(final L1PcInstance pc) {
        if (L1HauntedHouse.getInstance().getHauntedHouseStatus() == L1HauntedHouse.STATUS_PLAYING) { // 竞技中
            pc.sendPackets(new S_ServerMessage(1182)); // 游戏已经开始了。
            return "";
        }
        if (L1HauntedHouse.getInstance().getMembersCount() >= 10) { // 最高人员数量
            pc.sendPackets(new S_ServerMessage(1184)); // 幽灵之家已经到达饱和的状态了。
            return "";
        }

        L1HauntedHouse.getInstance().addMember(pc); // 人员加入
        L1Teleport.teleport(pc, 32722, 32830, (short) 5140, 2, true);
        return "";
    }

    // 进入宠物比赛
    private String enterPetMatch(final L1PcInstance pc, final int objid2) {
        if (pc.getPetList().values().size() > 0) {
            pc.sendPackets(new S_ServerMessage(1187)); // 宠物项链正在使用中。
            return "";
        }
        if (!L1PetMatch.getInstance().enterPetMatch(pc, objid2)) {
            pc.sendPackets(new S_ServerMessage(1182)); // 游戏已经开始了。
        }
        return "";
    }

    // 进入竞技场
    private String enterUb(final L1PcInstance pc, final int npcId) {
        final L1UltimateBattle ub = UBTable.getInstance().getUbForNpcId(npcId);
        if (!ub.isActive() || !ub.canPcEnter(pc)) { // 时间外
            return "colos2";
        }
        if (ub.isNowUb()) { // 竞技中
            return "colos1";
        }
        if (ub.getMembersCount() >= ub.getMaxPlayer()) { // 最高人员数量
            return "colos4";
        }

        ub.addMember(pc); // 人员加入
        final L1Location loc = ub.getLocation().randomLocation(10, false);
        L1Teleport.teleport(pc, loc.getX(), loc.getY(), ub.getMapId(), 5, true);
        return "";
    }

    // 排除其他血盟
    private void expelOtherClan(final L1PcInstance clanPc, final int keeperId) {
        int houseId = 0;
        for (final L1House house : HouseTable.getInstance().getHouseTableList()) {
            if (house.getKeeperId() == keeperId) {
                houseId = house.getHouseId();
            }
        }
        if (houseId == 0) {
            return;
        }

        int[] loc = new int[3];
        for (final L1Object object : L1World.getInstance().getObject()) {
            if (object instanceof L1PcInstance) {
                final L1PcInstance pc = (L1PcInstance) object;
                if (L1HouseLocation.isInHouseLoc(houseId, pc.getX(), pc.getY(),
                        pc.getMapId())
                        && (clanPc.getClanid() != pc.getClanid())) {
                    loc = L1HouseLocation.getHouseTeleportLoc(houseId, 0);
                    if (pc != null) {
                        L1Teleport.teleport(pc, loc[0], loc[1], (short) loc[2],
                                5, true);
                    }
                }
            }
        }
    }

    // 炎魔耳环
    private String getBarlogEarring(final L1PcInstance pc,
            final L1NpcInstance npc, final String s) {
        final int[] earringIdList = { 21020, 21021, 21022, 21023, // 舞动耳环`双子耳环`庆典耳环`绝顶耳环
                21024, 21025, 21026, 21027
        // 暴走耳环`幻魔耳环`族群耳环`奴隶耳环
        };
        int earringId = 0; // 耳环ID
        L1ItemInstance item = null;
        String htmlid = null;
        if (s.equalsIgnoreCase("1")) {
            earringId = earringIdList[0];
        } else if (s.equalsIgnoreCase("2")) {
            earringId = earringIdList[1];
        } else if (s.equalsIgnoreCase("3")) {
            earringId = earringIdList[2];
        } else if (s.equalsIgnoreCase("4")) {
            earringId = earringIdList[3];
        } else if (s.equalsIgnoreCase("5")) {
            earringId = earringIdList[4];
        } else if (s.equalsIgnoreCase("6")) {
            earringId = earringIdList[5];
        } else if (s.equalsIgnoreCase("7")) {
            earringId = earringIdList[6];
        } else if (s.equalsIgnoreCase("8")) {
            earringId = earringIdList[7];
        }
        if (earringId != 0) {
            item = pc.getInventory().storeItem(earringId, 1);
            if (item != null) {
                pc.sendPackets(new S_ServerMessage(143, npc.getNpcTemplate()
                        .get_name(), item.getLogName())); // \f1%0%s 给你 %1%o 。
            }
            for (final int id : earringIdList) {
                if (id == earringId) {
                    break;
                }
                if (pc.getInventory().checkItem(id)) {
                    pc.getInventory().consumeItem(id, 1);
                }
            }
            htmlid = "";
        }
        return htmlid;
    }

    // 获得火焰之影友好度
    private void getBloodCrystalByKarma(final L1PcInstance pc,
            final L1NpcInstance npc, final String s) {
        L1ItemInstance item = null;

        // “血石碎片1个”
        if (s.equalsIgnoreCase("1")) {
            pc.addKarma((int) (500 * Config.RATE_KARMA));
            item = pc.getInventory().storeItem(40718, 1);
            if (item != null) {
                pc.sendPackets(new S_ServerMessage(143, npc.getNpcTemplate()
                        .get_name(), item.getLogName())); // \f1%0%s 给你 %1%o 。
            }
            pc.sendPackets(new S_ServerMessage(1081)); // 我已经开始遗忘火焰之影了。
        }
        // “血石碎片10个”
        else if (s.equalsIgnoreCase("2")) {
            pc.addKarma((int) (5000 * Config.RATE_KARMA));
            item = pc.getInventory().storeItem(40718, 10);
            if (item != null) {
                pc.sendPackets(new S_ServerMessage(143, npc.getNpcTemplate()
                        .get_name(), item.getLogName())); // \f1%0%s 给你 %1%o 。
            }
            pc.sendPackets(new S_ServerMessage(1081)); // 我已经开始遗忘火焰之影了。
        }
        // “血石碎片100个”
        else if (s.equalsIgnoreCase("3")) {
            pc.addKarma((int) (50000 * Config.RATE_KARMA));
            item = pc.getInventory().storeItem(40718, 100);
            if (item != null) {
                pc.sendPackets(new S_ServerMessage(143, npc.getNpcTemplate()
                        .get_name(), item.getLogName())); // \f1%0%s 给你 %1%o 。
            }
            pc.sendPackets(new S_ServerMessage(1081)); // 我已经开始遗忘火焰之影了。
        }
    }

    // 获得获得炎魔友好度
    private void getSoulCrystalByKarma(final L1PcInstance pc,
            final L1NpcInstance npc, final String s) {
        L1ItemInstance item = null;

        // “灵魂石碎片1个”
        if (s.equalsIgnoreCase("1")) {
            pc.addKarma((int) (-500 * Config.RATE_KARMA));
            item = pc.getInventory().storeItem(40678, 1);
            if (item != null) {
                pc.sendPackets(new S_ServerMessage(143, npc.getNpcTemplate()
                        .get_name(), item.getLogName())); // \f1%0%s 给你 %1%o 。
            }
            pc.sendPackets(new S_ServerMessage(1080)); // 炎魔的冷笑激起一阵寒意。
        }
        // “灵魂石碎片10个”
        else if (s.equalsIgnoreCase("2")) {
            pc.addKarma((int) (-5000 * Config.RATE_KARMA));
            item = pc.getInventory().storeItem(40678, 10);
            if (item != null) {
                pc.sendPackets(new S_ServerMessage(143, npc.getNpcTemplate()
                        .get_name(), item.getLogName())); // \f1%0%s 给你 %1%o 。
            }
            pc.sendPackets(new S_ServerMessage(1080)); // 炎魔的冷笑激起一阵寒意。
        }
        // “灵魂石碎片100个”
        else if (s.equalsIgnoreCase("3")) {
            pc.addKarma((int) (-50000 * Config.RATE_KARMA));
            item = pc.getInventory().storeItem(40678, 100);
            if (item != null) {
                pc.sendPackets(new S_ServerMessage(143, npc.getNpcTemplate()
                        .get_name(), item.getLogName())); // \f1%0%s 给你 %1%o 。
            }
            pc.sendPackets(new S_ServerMessage(1080)); // 炎魔的冷笑激起一阵寒意。
        }
    }

    @Override
    public String getType() {
        return C_NPC_ACTION;
    }

    // 火焰之影项链
    private String getYaheeAmulet(final L1PcInstance pc,
            final L1NpcInstance npc, final String s) {
        final int[] amuletIdList = { 20358, 20359, 20360, 20361, // 奴隶项链`约定项链`解放项链`猎犬项链
                20362, 20363, 20364, 20365
        // 魔族项链`勇士项链`将军项链`大将军项链
        };
        int amuletId = 0;
        L1ItemInstance item = null;
        String htmlid = null;
        if (s.equalsIgnoreCase("1")) {
            amuletId = amuletIdList[0];
        } else if (s.equalsIgnoreCase("2")) {
            amuletId = amuletIdList[1];
        } else if (s.equalsIgnoreCase("3")) {
            amuletId = amuletIdList[2];
        } else if (s.equalsIgnoreCase("4")) {
            amuletId = amuletIdList[3];
        } else if (s.equalsIgnoreCase("5")) {
            amuletId = amuletIdList[4];
        } else if (s.equalsIgnoreCase("6")) {
            amuletId = amuletIdList[5];
        } else if (s.equalsIgnoreCase("7")) {
            amuletId = amuletIdList[6];
        } else if (s.equalsIgnoreCase("8")) {
            amuletId = amuletIdList[7];
        }
        if (amuletId != 0) {
            item = pc.getInventory().storeItem(amuletId, 1);
            if (item != null) {
                pc.sendPackets(new S_ServerMessage(143, npc.getNpcTemplate()
                        .get_name(), item.getLogName())); // \f1%0%s 给你 %1%o 。
            }
            for (final int id : amuletIdList) {
                if (id == amuletId) {
                    break;
                }
                if (pc.getInventory().checkItem(id)) {
                    pc.getInventory().consumeItem(id, 1);
                }
            }
            htmlid = "";
        }
        return htmlid;
    }

    //
    private boolean isExistDefenseClan(final int castleId) {
        boolean isExistDefenseClan = false;
        for (final L1Clan clan : L1World.getInstance().getAllClans()) {
            if (castleId == clan.getCastleId()) {
                isExistDefenseClan = true;
                break;
            }
        }
        return isExistDefenseClan;
    }

    // 只有出售的Npc
    private boolean isNpcSellOnly(final L1NpcInstance npc) {
        final int npcId = npc.getNpcTemplate().get_npcId();
        final String npcName = npc.getNpcTemplate().get_name();
        if ((npcId == 70027 // 迪欧
                )
                || "亚丁商团".equals(npcName)) {
            return true;
        }
        return false;
    }

    //
    private String karmaLevelToHtmlId(final int level) {
        if ((level == 0) || (level < -7) || (7 < level)) {
            return "";
        }
        String htmlid = "";
        if (0 < level) {
            htmlid = "vbk" + level;
        } else if (level < 0) {
            htmlid = "vyk" + Math.abs(level);
        }
        return htmlid;
    }

    // 构造房屋税字符串
    private String[] makeHouseTaxStrings(final L1PcInstance pc,
            final L1NpcInstance npc) {
        final String name = npc.getNpcTemplate().get_name();
        String[] result;
        result = new String[] { name, "2000", "1", "1", "00" };
        final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
        if (clan != null) {
            final int houseId = clan.getHouseId();
            if (houseId != 0) {
                final L1House house = HouseTable.getInstance().getHouseTable(
                        houseId);
                final int keeperId = house.getKeeperId();
                if (npc.getNpcTemplate().get_npcId() == keeperId) {
                    final Calendar cal = house.getTaxDeadline();
                    final int month = cal.get(Calendar.MONTH) + 1;
                    final int day = cal.get(Calendar.DATE);
                    final int hour = cal.get(Calendar.HOUR_OF_DAY);
                    result = new String[] { name, "2000",
                            String.valueOf(month), String.valueOf(day),
                            String.valueOf(hour) };
                }
            }
        }
        return result;
    }

    private String[] makeUbInfoStrings(final int npcId) {
        final L1UltimateBattle ub = UBTable.getInstance().getUbForNpcId(npcId);
        return ub.makeUbInfoStrings();
    }

    // 构造战争时间字符串
    private String[] makeWarTimeStrings(final int castleId) {
        final L1Castle castle = CastleTable.getInstance().getCastleTable(
                castleId);
        if (castle == null) {
            return null;
        }
        final Calendar warTime = castle.getWarTime();
        final int year = warTime.get(Calendar.YEAR);
        final int month = warTime.get(Calendar.MONTH) + 1;
        final int day = warTime.get(Calendar.DATE);
        final int hour = warTime.get(Calendar.HOUR_OF_DAY);
        final int minute = warTime.get(Calendar.MINUTE);
        String[] result;
        if (castleId == L1CastleLocation.OT_CASTLE_ID) {
            result = new String[] { String.valueOf(year),
                    String.valueOf(month), String.valueOf(day),
                    String.valueOf(hour), String.valueOf(minute) };
        } else {
            result = new String[] { "", String.valueOf(year),
                    String.valueOf(month), String.valueOf(day),
                    String.valueOf(hour), String.valueOf(minute) };
        }
        return result;
    }

    // 打开关闭 门
    private void openCloseDoor(final L1PcInstance pc, final L1NpcInstance npc,
            final String s) {
        final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
        if (clan != null) {
            final int houseId = clan.getHouseId();
            if (houseId != 0) {
                final L1House house = HouseTable.getInstance().getHouseTable(
                        houseId);
                final int keeperId = house.getKeeperId();
                if (npc.getNpcTemplate().get_npcId() == keeperId) {
                    L1DoorInstance door1 = null;
                    L1DoorInstance door2 = null;
                    L1DoorInstance door3 = null;
                    L1DoorInstance door4 = null;
                    for (final L1DoorInstance door : DoorTable.getInstance()
                            .getDoorList()) {
                        if (door.getKeeperId() == keeperId) {
                            if (door1 == null) {
                                door1 = door;
                                continue;
                            }
                            if (door2 == null) {
                                door2 = door;
                                continue;
                            }
                            if (door3 == null) {
                                door3 = door;
                                continue;
                            }
                            if (door4 == null) {
                                door4 = door;
                                break;
                            }
                        }
                    }
                    if (door1 != null) {
                        if (s.equalsIgnoreCase("open")) {
                            door1.open();
                        } else if (s.equalsIgnoreCase("close")) {
                            door1.close();
                        }
                    }
                    if (door2 != null) {
                        if (s.equalsIgnoreCase("open")) {
                            door2.open();
                        } else if (s.equalsIgnoreCase("close")) {
                            door2.close();
                        }
                    }
                    if (door3 != null) {
                        if (s.equalsIgnoreCase("open")) {
                            door3.open();
                        } else if (s.equalsIgnoreCase("close")) {
                            door3.close();
                        }
                    }
                    if (door4 != null) {
                        if (s.equalsIgnoreCase("open")) {
                            door4.open();
                        } else if (s.equalsIgnoreCase("close")) {
                            door4.close();
                        }
                    }
                }
            }
        }
    }

    // 打开关闭 城门
    private void openCloseGate(final L1PcInstance pc, final int keeperId,
            final boolean isOpen) {
        boolean isNowWar = false;
        int pcCastleId = 0;
        if (pc.getClanid() != 0) {
            final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
            if (clan != null) {
                pcCastleId = clan.getCastleId();
            }
        }
        if ((keeperId == 70656) || (keeperId == 70549) || (keeperId == 70985)) { // 肯特城堡
            if (this.isExistDefenseClan(L1CastleLocation.KENT_CASTLE_ID)) {
                if (pcCastleId != L1CastleLocation.KENT_CASTLE_ID) {
                    return;
                }
            }
            isNowWar = WarTimeController.getInstance().isNowWar(
                    L1CastleLocation.KENT_CASTLE_ID);
        } else if (keeperId == 70600) { // OT
            if (this.isExistDefenseClan(L1CastleLocation.OT_CASTLE_ID)) {
                if (pcCastleId != L1CastleLocation.OT_CASTLE_ID) {
                    return;
                }
            }
            isNowWar = WarTimeController.getInstance().isNowWar(
                    L1CastleLocation.OT_CASTLE_ID);
        } else if ((keeperId == 70778) || (keeperId == 70987)
                || (keeperId == 70687)) { // WW城
            if (this.isExistDefenseClan(L1CastleLocation.WW_CASTLE_ID)) {
                if (pcCastleId != L1CastleLocation.WW_CASTLE_ID) {
                    return;
                }
            }
            isNowWar = WarTimeController.getInstance().isNowWar(
                    L1CastleLocation.WW_CASTLE_ID);
        } else if ((keeperId == 70817) || (keeperId == 70800)
                || (keeperId == 70988) || (keeperId == 70990)
                || (keeperId == 70989) || (keeperId == 70991)) { // ギラン城
            if (this.isExistDefenseClan(L1CastleLocation.GIRAN_CASTLE_ID)) {
                if (pcCastleId != L1CastleLocation.GIRAN_CASTLE_ID) {
                    return;
                }
            }
            isNowWar = WarTimeController.getInstance().isNowWar(
                    L1CastleLocation.GIRAN_CASTLE_ID);
        } else if ((keeperId == 70863) || (keeperId == 70992)
                || (keeperId == 70862)) { // 海音城堡
            if (this.isExistDefenseClan(L1CastleLocation.HEINE_CASTLE_ID)) {
                if (pcCastleId != L1CastleLocation.HEINE_CASTLE_ID) {
                    return;
                }
            }
            isNowWar = WarTimeController.getInstance().isNowWar(
                    L1CastleLocation.HEINE_CASTLE_ID);
        } else if ((keeperId == 70995) || (keeperId == 70994)
                || (keeperId == 70993)) { // 威顿城堡
            if (this.isExistDefenseClan(L1CastleLocation.DOWA_CASTLE_ID)) {
                if (pcCastleId != L1CastleLocation.DOWA_CASTLE_ID) {
                    return;
                }
            }
            isNowWar = WarTimeController.getInstance().isNowWar(
                    L1CastleLocation.DOWA_CASTLE_ID);
        } else if (keeperId == 70996) { // 亚丁城堡
            if (this.isExistDefenseClan(L1CastleLocation.ADEN_CASTLE_ID)) {
                if (pcCastleId != L1CastleLocation.ADEN_CASTLE_ID) {
                    return;
                }
            }
            isNowWar = WarTimeController.getInstance().isNowWar(
                    L1CastleLocation.ADEN_CASTLE_ID);
        }

        for (final L1DoorInstance door : DoorTable.getInstance().getDoorList()) {
            if (door.getKeeperId() == keeperId) {
                if (isNowWar && (door.getMaxHp() > 1)) { // 战争中は城门开闭不可
                } else {
                    if (isOpen) { // 开
                        door.open();
                    } else { // 闭
                        door.close();
                    }
                }
            }
        }
    }

    // 支付费用
    private boolean payFee(final L1PcInstance pc, final L1NpcInstance npc) {
        final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
        if (clan != null) {
            final int houseId = clan.getHouseId();
            if (houseId != 0) {
                final L1House house = HouseTable.getInstance().getHouseTable(
                        houseId);
                final int keeperId = house.getKeeperId();
                if (npc.getNpcTemplate().get_npcId() == keeperId) {
                    final TimeZone tz = TimeZone.getTimeZone(Config.TIME_ZONE);
                    final Calendar cal = Calendar.getInstance(tz); // 目前时间
                    final Calendar deadlineCal = house.getTaxDeadline(); // 盟屋到期时间

                    final int remainingTime = (int) ((deadlineCal
                            .getTimeInMillis() - cal.getTimeInMillis()) / (1000 * 60 * 60 * 24));
                    // 租期剩余时间大于一半 不用缴房租
                    if (remainingTime >= Config.HOUSE_TAX_INTERVAL / 2) {
                        return true;
                    } else if (pc.getInventory()
                            .checkItem(L1ItemId.ADENA, 2000)) {
                        pc.getInventory().consumeItem(L1ItemId.ADENA, 2000);
                        // 支付后 deadline延期
                        deadlineCal.add(Calendar.DATE,
                                Config.HOUSE_TAX_INTERVAL);
                        deadlineCal.set(Calendar.MINUTE, 0); // 分、秒は切り舍て
                        deadlineCal.set(Calendar.SECOND, 0);
                        house.setTaxDeadline(deadlineCal);
                        HouseTable.getInstance().updateHouse(house); // DBに书き迂み
                        return true;
                    } else {
                        pc.sendPackets(new S_ServerMessage(189)); // \f1金币不足。
                    }
                }
            }
        }
        return false;
    }

    // 变身
    private void poly(final ClientThread clientthread, final int polyId) {
        final L1PcInstance pc = clientthread.getActiveChar();
        final int awakeSkillId = pc.getAwakeSkillId();
        if ((awakeSkillId == AWAKEN_ANTHARAS)
                || (awakeSkillId == AWAKEN_FAFURION)
                || (awakeSkillId == AWAKEN_VALAKAS)) {
            pc.sendPackets(new S_ServerMessage(1384)); // 目前状态中无法变身。
            return;
        }

        if (pc.getInventory().checkItem(L1ItemId.ADENA, 100)) { // check
            pc.getInventory().consumeItem(L1ItemId.ADENA, 100); // del

            L1PolyMorph.doPoly(pc, polyId, 1800, L1PolyMorph.MORPH_BY_NPC);
        } else {
            pc.sendPackets(new S_ServerMessage(337, "$4")); // \f1%0不足%s。
        }
    }

    //
    private void polyByKeplisha(final ClientThread clientthread,
            final int polyId) {
        final L1PcInstance pc = clientthread.getActiveChar();
        final int awakeSkillId = pc.getAwakeSkillId();
        if ((awakeSkillId == AWAKEN_ANTHARAS)
                || (awakeSkillId == AWAKEN_FAFURION)
                || (awakeSkillId == AWAKEN_VALAKAS)) {
            pc.sendPackets(new S_ServerMessage(1384)); // 目前状态中无法变身。
            return;
        }

        if (pc.getInventory().checkItem(L1ItemId.ADENA, 100)) { // check
            pc.getInventory().consumeItem(L1ItemId.ADENA, 100); // del

            L1PolyMorph.doPoly(pc, polyId, 1800, L1PolyMorph.MORPH_BY_KEPLISHA);
        } else {
            pc.sendPackets(new S_ServerMessage(337, "$4")); // \f1%0不足%s。
        }
    }

    // 修复城门
    private void repairGate(final L1PcInstance pc) {
        final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
        if (clan != null) {
            final int castleId = clan.getCastleId();
            if (castleId != 0) { // 血盟城主
                if (!WarTimeController.getInstance().isNowWar(castleId)) {
                    // 修复城门
                    for (final L1DoorInstance door : DoorTable.getInstance()
                            .getDoorList()) {
                        if (L1CastleLocation.checkInWarArea(castleId, door)) {
                            door.repairGate();
                        }
                    }
                    pc.sendPackets(new S_ServerMessage(990)); // 下令城门自动修复功能。
                } else {
                    pc.sendPackets(new S_ServerMessage(991)); // 取消城门自动修复功能。
                }
            }
        }
    }

    // 出售盟屋
    private String sellHouse(final L1PcInstance pc, final int objectId,
            final int npcId) {
        final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
        if (clan == null) {
            return ""; // 关闭窗口
        }
        final int houseId = clan.getHouseId();
        if (houseId == 0) {
            return ""; // 关闭窗口
        }
        final L1House house = HouseTable.getInstance().getHouseTable(houseId);
        final int keeperId = house.getKeeperId();
        if (npcId != keeperId) {
            return ""; // 关闭窗口
        }
        if (!pc.isCrown()) {
            pc.sendPackets(new S_ServerMessage(518)); // 血盟君主才可使用此命令。
            return ""; // 关闭窗口
        }
        if (pc.getId() != clan.getLeaderId()) {
            pc.sendPackets(new S_ServerMessage(518)); // 血盟君主才可使用此命令。
            return ""; // 关闭窗口
        }
        if (house.isOnSale()) {
            return "agonsale";
        }

        pc.sendPackets(new S_SellHouse(objectId, String.valueOf(houseId)));
        return null;
    }

    // 召唤怪物
    private void summonMonster(final L1PcInstance pc, final String s) {
        String[] summonstr_list;
        int[] summonid_list;
        int[] summonlvl_list;
        int[] summoncha_list;
        int summonid = 0;
        int levelrange = 0;
        int summoncost = 0;

        // 对话档对应名称
        summonstr_list = new String[] { "7", "263", "519", "8", "264", "520",
                "9", "265", "521", "10", "266", "522", "11", "267", "523",
                "12", "268", "524", "13", "269", "525", "14", "270", "526",
                "15", "271", "527", "16", "17", "18", "274" };

        // 怪物ID
        summonid_list = new int[] { 81210, 81211, 81212, // 哈柏哥布林`艾多伦`安普
                81213, 81214, 81215, // 甘地妖魔`都达玛拉妖魔`妖魔巡守
                81216, 81217, 81218, // 狂野毒牙`狂野之毒`狂野之魔
                81219, 81220, 81221, // 食人妖精`食人妖精王`冰人
                81222, 81223, 81224, // 狂暴蜥蜴人`重装蜥蜴人`高等蜥蜴人
                81225, 81226, 81227, // 火蜥蜴`火焰战士`火焰弓箭手
                81228, 81229, 81230, // 魔熊`魔狼`魔蝙蝠
                81231, 81232, 81233, // 巨大守护蚂蚁`强化白蚂蚁群`巨大强化白蚂蚁
                81234, 81235, 81236, // 地狱奴隶`闇精灵王`食腐兽
                81237, 81238, 81239, 81240
        // 地狱束缚犬`变形怪首领`巨大牛人`黑豹
        };

        // 所需等级
        summonlvl_list = new int[] { 28, 28, 28, 32, 32, 32, 36, 36, 36, 40,
                40, 40, 44, 44, 44, 48, 48, 48, 52, 52, 52, 56, 56, 56, 60, 60,
                60, 64, 68, 72, 72 };

        // 召唤宠物所需魅力值
        summoncha_list = new int[] { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                8, // 28 ~ 44
                8, 8, 8, 8, 8, 8, 10, 10, 10, 12, 12, 12, // 48 ~ 60
                20, 36, 36, 44
        // 64,68,72,72
        };
        // サモンの种类、必要Lv、必要条件(CHA)
        for (int loop = 0; loop < summonstr_list.length; loop++) {
            if (s.equalsIgnoreCase(summonstr_list[loop])) {
                summonid = summonid_list[loop];
                levelrange = summonlvl_list[loop];
                summoncost = summoncha_list[loop];
                break;
            }
        }
        // Lv不足
        if (pc.getLevel() < levelrange) {
            pc.sendPackets(new S_ServerMessage(743)); // 等级太低而无法召唤怪物
            return;
        }

        int petcost = 0;
        for (final L1NpcInstance petNpc : pc.getPetList().values()) {
            // 现在のペットコスト
            petcost += petNpc.getPetcost();
        }

        /*
         * // 既にペットがいる场合は、ドッペルゲンガーボス、クーガーは呼び出せない if ((summonid == 81103 ||
         * summonid == 81104) && petcost != 0) { pc.sendPackets(new
         * S_CloseList(pc.getId())); return; } int charisma = pc.getCha() + 6 -
         * petcost; int summoncount = charisma / summoncost;
         */
        int pcCha = pc.getCha();
        int charisma = 0;
        int summoncount = 0;
        if ((levelrange <= 56 // max count = 5
                )
                || (levelrange == 64)) { // max count = 2
            if (pcCha > 34) {
                pcCha = 34;
            }
        } else if (levelrange == 60) {
            if (pcCha > 30) { // max count = 3
                pcCha = 30;
            }
        } else if (levelrange > 64) {
            if (pcCha > 44) { // max count = 1
                pcCha = 44;
            }
        }
        charisma = pcCha + 6 - petcost;
        summoncount = charisma / summoncost;

        final L1Npc npcTemp = NpcTable.getInstance().getTemplate(summonid);
        for (int cnt = 0; cnt < summoncount; cnt++) {
            final L1SummonInstance summon = new L1SummonInstance(npcTemp, pc);
            // if (summonid == 81103 || summonid == 81104) {
            // summon.setPetcost(pc.getCha() + 7);
            // } else {
            summon.setPetcost(summoncost);
            // }
        }
        pc.sendPackets(new S_CloseList(pc.getId()));
    }

    // 四大领域传送门
    private String talkToDimensionDoor(final L1PcInstance pc,
            final L1NpcInstance npc, final String s) {

        String htmlid = "";
        int protectionId = 0;
        int sealId = 0;
        int locX = 0;
        int locY = 0;
        short mapId = 0;
        if (npc.getNpcTemplate().get_npcId() == 80059) { // 次元之门(土)
            protectionId = 40909;
            sealId = 40913;
            locX = 32773;
            locY = 32835;
            mapId = 607;
        } else if (npc.getNpcTemplate().get_npcId() == 80060) { // 次元之门(风)
            protectionId = 40912;
            sealId = 40916;
            locX = 32757;
            locY = 32842;
            mapId = 606;
        } else if (npc.getNpcTemplate().get_npcId() == 80061) { // 次元之门(水)
            protectionId = 40910;
            sealId = 40914;
            locX = 32830;
            locY = 32822;
            mapId = 604;
        } else if (npc.getNpcTemplate().get_npcId() == 80062) { // 次元之门(火)
            protectionId = 40911;
            sealId = 40915;
            locX = 32835;
            locY = 32822;
            mapId = 605;
        }

        // “进去看看”
        if (s.equalsIgnoreCase("a")) {
            L1Teleport.teleport(pc, locX, locY, mapId, 5, true);
            htmlid = "";
        }
        // “把鬼脸上凸出的部分拿掉”
        else if (s.equalsIgnoreCase("b")) {
            final L1ItemInstance item = pc.getInventory().storeItem(
                    protectionId, 1);
            if (item != null) {
                pc.sendPackets(new S_ServerMessage(143, npc.getNpcTemplate()
                        .get_name(), item.getLogName())); // \f1%0%s 给你 %1%o 。
            }
            htmlid = "";
        }
        // “丢掉通行证放弃这里”
        else if (s.equalsIgnoreCase("c")) {
            htmlid = "wpass07";
        }
        // “继续”
        else if (s.equalsIgnoreCase("d")) {
            if (pc.getInventory().checkItem(sealId)) { // 土之印记
                final L1ItemInstance item = pc.getInventory()
                        .findItemId(sealId);
                pc.getInventory().consumeItem(sealId, item.getCount());
            }
        }
        // “马上捡起通行证”
        else if (s.equalsIgnoreCase("e")) {
            htmlid = "";
        }
        // “就让它消失吧”
        else if (s.equalsIgnoreCase("f")) {
            if (pc.getInventory().checkItem(protectionId)) { // 土之通行证
                pc.getInventory().consumeItem(protectionId, 1);
            }
            if (pc.getInventory().checkItem(sealId)) { // 土之印记
                final L1ItemInstance item = pc.getInventory()
                        .findItemId(sealId);
                pc.getInventory().consumeItem(sealId, item.getCount());
            }
            htmlid = "";
        }
        return htmlid;
    }

    // 使用变形卷轴
    private boolean usePolyScroll(final L1PcInstance pc, final int itemId,
            final String s) {
        int time = 0;
        if ((itemId == 40088) || (itemId == 40096)) { // 变形卷轴、象牙塔变形卷轴
            time = 1800;
        } else if (itemId == 140088) { // 受祝福的 变形卷轴
            time = 2100;
        }

        final L1PolyMorph poly = PolyTable.getInstance().getTemplate(s);
        final L1ItemInstance item = pc.getInventory().findItemId(itemId);
        boolean isUseItem = false;
        if ((poly != null) || s.equals("none")) {
            if (s.equals("none")) {
                if ((pc.getTempCharGfx() == 6034)
                        || (pc.getTempCharGfx() == 6035)) {
                    isUseItem = true;
                } else {
                    pc.removeSkillEffect(SHAPE_CHANGE); // 法师魔法 (变形术)
                    isUseItem = true;
                }
            } else if ((poly.getMinLevel() <= pc.getLevel()) || pc.isGm()) {
                L1PolyMorph.doPoly(pc, poly.getPolyId(), time,
                        L1PolyMorph.MORPH_BY_ITEMMAGIC);
                isUseItem = true;
            }
        }
        if (isUseItem) {
            pc.getInventory().removeItem(item, 1);
        } else {
            pc.sendPackets(new S_ServerMessage(181)); // \f1无法变成你指定的怪物。
        }
        return isUseItem;
    }

    // 观看竞技场
    private String watchUb(final L1PcInstance pc, final int npcId) {
        final L1UltimateBattle ub = UBTable.getInstance().getUbForNpcId(npcId);
        final L1Location loc = ub.getLocation();
        if (pc.getInventory().consumeItem(L1ItemId.ADENA, 100)) {
            try {
                pc.save();
                pc.beginGhost(loc.getX(), loc.getY(), (short) loc.getMapId(),
                        true);
            } catch (final Exception e) {
                _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            }
        } else {
            pc.sendPackets(new S_ServerMessage(189)); // \f1金币不足。
        }
        return "";
    }

}
