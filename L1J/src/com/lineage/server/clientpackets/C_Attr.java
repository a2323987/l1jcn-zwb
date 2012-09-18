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

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.ClientThread;
import com.lineage.server.WarTimeController;
import com.lineage.server.datatables.CharacterTable;
import com.lineage.server.datatables.ClanTable;
import com.lineage.server.datatables.HouseTable;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.datatables.PetTable;
import com.lineage.server.model.L1CastleLocation;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1ChatParty;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1Party;
import com.lineage.server.model.L1Quest;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.L1War;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.model.map.L1Map;
import com.lineage.server.serverpackets.S_ChangeName;
import com.lineage.server.serverpackets.S_CharTitle;
import com.lineage.server.serverpackets.S_CharVisualUpdate;
import com.lineage.server.serverpackets.S_OwnCharStatus2;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_Resurrection;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.serverpackets.S_Trade;
import com.lineage.server.templates.L1House;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.templates.L1Pet;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 各项属性
 */
public class C_Attr extends ClientBasePacket {

    private static final Logger _log = Logger.getLogger(C_Attr.class.getName());

    private static final String C_ATTR = "[C] C_Attr";

    private static final int HEADING_TABLE_X[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

    private static final int HEADING_TABLE_Y[] = { -1, -1, 0, 1, 1, 1, 0, -1 };

    // 重命名宠物
    private static void renamePet(final L1PetInstance pet, final String name) {
        if ((pet == null) || (name == null)) {
            throw new NullPointerException();
        }

        final int petItemObjId = pet.getItemObjId();
        final L1Pet petTemplate = PetTable.getInstance().getTemplate(
                petItemObjId);
        if (petTemplate == null) {
            throw new NullPointerException();
        }

        final L1PcInstance pc = (L1PcInstance) pet.getMaster();
        if (PetTable.isNameExists(name)) {
            pc.sendPackets(new S_ServerMessage(327)); // 同样的名称已经存在。
            return;
        }
        final L1Npc l1npc = NpcTable.getInstance().getTemplate(pet.getNpcId());
        if (!(pet.getName().equalsIgnoreCase(l1npc.get_name()))) {
            pc.sendPackets(new S_ServerMessage(326)); // 一旦你已决定就不能再变更。
            return;
        }
        pet.setName(name);
        petTemplate.set_name(name);
        PetTable.getInstance().storePet(petTemplate); // 储存宠物资料到资料库中
        final L1ItemInstance item = pc.getInventory().getItem(
                pet.getItemObjId());
        pc.getInventory().updateItem(item);
        pc.sendPackets(new S_ChangeName(pet.getId(), name));
        pc.broadcastPacket(new S_ChangeName(pet.getId(), name));
    }

    @SuppressWarnings("static-access")
    public C_Attr(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);
        final int i = this.readH(); // 3.51C未知的功能
        int attrcode;
        if (i == 479) {
            attrcode = i;
        } else {
            @SuppressWarnings("unused")
            final int count = this.readD(); // 纪录世界中发送YesNo的次数
            attrcode = this.readH();
        }
        String name;
        int c;

        final L1PcInstance pc = clientthread.getActiveChar();

        switch (attrcode) {
            case 97: // \f3%0%s 想加入你的血盟。你接受吗。(Y/N)
                c = this.readH();
                final L1PcInstance joinPc = (L1PcInstance) L1World
                        .getInstance().findObject(pc.getTempID());
                pc.setTempID(0);
                if (joinPc != null) {
                    if (c == 0) { // No
                        joinPc.sendPackets(new S_ServerMessage(96, pc.getName())); // \f1%0%s
                                                                                   // 拒绝你的请求。
                    } else if (c == 1) { // Yes
                        final int clan_id = pc.getClanid();
                        final String clanName = pc.getClanname();
                        final L1Clan clan = L1World.getInstance().getClan(
                                clanName);
                        if (clan != null) {
                            int maxMember = 0;
                            final int charisma = pc.getCha();
                            // 公式
                            maxMember = charisma * 3 * (2 + pc.getLevel() / 50);
                            // 未过45 人数/3
                            if (!pc.getQuest().isEnd(L1Quest.QUEST_LEVEL45)) {
                                maxMember /= 3;
                            }

                            if (Config.MAX_CLAN_MEMBER > 0) { // 设定档中如果有设定血盟的人数上限
                                maxMember = Config.MAX_CLAN_MEMBER;
                            }

                            if (joinPc.getClanid() == 0) { // 加入玩家未加入血盟
                                final String clanMembersName[] = clan
                                        .getAllMembers();
                                if (maxMember <= clanMembersName.length) { // 血盟还有空间可以让玩家加入
                                    joinPc.sendPackets(new S_ServerMessage(188,
                                            pc.getName())); // %0%s
                                                            // 无法接受你成为该血盟成员。
                                    return;
                                }
                                for (final L1PcInstance clanMembers : clan
                                        .getOnlineClanMember()) {
                                    clanMembers
                                            .sendPackets(new S_ServerMessage(
                                                    94, joinPc.getName())); // \f1你接受%0当你的血盟成员。
                                }
                                joinPc.setClanid(clan_id);
                                joinPc.setClanname(clanName);
                                joinPc.setClanRank(L1Clan.CLAN_RANK_PUBLIC);
                                joinPc.setTitle("");
                                joinPc.sendPackets(new S_CharTitle(joinPc
                                        .getId(), ""));
                                joinPc.broadcastPacket(new S_CharTitle(joinPc
                                        .getId(), ""));
                                joinPc.save(); // 储存加入的玩家资料
                                clan.addMemberName(joinPc.getName());
                                joinPc.sendPackets(new S_ServerMessage(95,
                                        clanName)); // \f1加入%0血盟。
                            } else { // 如果是王族加入（联合血盟）
                                if (Config.CLAN_ALLIANCE) {
                                    this.changeClan(clientthread, pc, joinPc,
                                            maxMember);
                                } else {
                                    joinPc.sendPackets(new S_ServerMessage(89)); // \f1你已经有血盟了。
                                }
                            }
                        }
                    }
                }
                break;

            case 217: // %0 血盟向你的血盟宣战。是否接受？(Y/N)
            case 221: // %0 血盟要向你投降。是否接受？(Y/N)
            case 222: // %0 血盟要结束战争。是否接受？(Y/N)
                c = this.readH();
                final L1PcInstance enemyLeader = (L1PcInstance) L1World
                        .getInstance().findObject(pc.getTempID());
                if (enemyLeader == null) {
                    return;
                }
                pc.setTempID(0);
                final String clanName = pc.getClanname();
                final String enemyClanName = enemyLeader.getClanname();
                if (c == 0) { // No
                    if (i == 217) {
                        enemyLeader.sendPackets(new S_ServerMessage(236,
                                clanName)); // %0
                                            // 血盟拒绝你的宣战。
                    } else if ((i == 221) || (i == 222)) {
                        enemyLeader.sendPackets(new S_ServerMessage(237,
                                clanName)); // %0
                                            // 血盟拒绝你的提案。
                    }
                } else if (c == 1) { // Yes
                    if (i == 217) {
                        final L1War war = new L1War();
                        war.handleCommands(2, enemyClanName, clanName); // 盟战开始
                    } else if ((i == 221) || (i == 222)) {
                        // 取得线上所有的盟战
                        for (final L1War war : L1World.getInstance()
                                .getWarList()) {
                            if (war.CheckClanInWar(clanName)) { // 如果有现在的血盟
                                if (i == 221) {
                                    war.SurrenderWar(enemyClanName, clanName); // 投降
                                } else if (i == 222) {
                                    war.CeaseWar(enemyClanName, clanName); // 结束
                                }
                                break;
                            }
                        }
                    }
                }
                break;

            case 252: // \f2%0%s 要与你交易。愿不愿交易？ (Y/N)
                c = this.readH();
                final L1PcInstance trading_partner = (L1PcInstance) L1World
                        .getInstance().findObject(pc.getTradeID());
                if (trading_partner != null) {
                    if (c == 0) // No
                    {
                        trading_partner.sendPackets(new S_ServerMessage(253, pc
                                .getName())); // %0%d 拒绝与你交易。
                        pc.setTradeID(0);
                        trading_partner.setTradeID(0);
                    } else if (c == 1) // Yes
                    {
                        pc.sendPackets(new S_Trade(trading_partner.getName()));
                        trading_partner.sendPackets(new S_Trade(pc.getName()));
                    }
                }
                break;

            case 321: // 是否要复活？ (Y/N)
                c = this.readH();
                final L1PcInstance resusepc1 = (L1PcInstance) L1World
                        .getInstance().findObject(pc.getTempID());
                pc.setTempID(0);
                if (resusepc1 != null) { // 如果有这个人
                    if (c == 0) { // No

                    } else if (c == 1) { // Yes
                        this.resurrection(pc, resusepc1,
                                (short) (pc.getMaxHp() / 2));
                    }
                }
                break;

            case 322: // 是否要复活？ (Y/N)
                c = this.readH();
                final L1PcInstance resusepc2 = (L1PcInstance) L1World
                        .getInstance().findObject(pc.getTempID());
                pc.setTempID(0);
                if (resusepc2 != null) { // 祝福复活卷轴、复活、大复活
                    if (c == 0) { // No

                    } else if (c == 1) { // Yes
                        this.resurrection(pc, resusepc2, pc.getMaxHp());
                        // EXP已经失去、G-RESを挂けられた、EXP死亡掉落
                        // 全有满足场合的EXP恢复
                        if ((pc.getExpRes() == 1) && pc.isGres()
                                && pc.isGresValid()) {
                            pc.resExp();
                            pc.setExpRes(0);
                            pc.setGres(false);
                        }
                    }
                }
                break;

            case 325: // 你想叫它什么名字？
                c = this.readH(); // ?
                name = this.readS();
                final L1PetInstance pet = (L1PetInstance) L1World.getInstance()
                        .findObject(pc.getTempID());
                pc.setTempID(0);
                renamePet(pet, name);
                break;

            case 512: // 请输入血盟小屋名称?
                c = this.readH(); // ?
                name = this.readS();
                final int houseId = pc.getTempID();
                pc.setTempID(0);
                if (name.length() <= 16) {
                    final L1House house = HouseTable.getInstance()
                            .getHouseTable(houseId);
                    house.setHouseName(name);
                    HouseTable.getInstance().updateHouse(house); // 更新到资料库中
                } else {
                    pc.sendPackets(new S_ServerMessage(513)); // 血盟小屋名称太长。
                }
                break;

            case 630: // %0%s 要与你决斗。你是否同意？(Y/N)
                c = this.readH();
                final L1PcInstance fightPc = (L1PcInstance) L1World
                        .getInstance().findObject(pc.getFightId());
                if (c == 0) {
                    pc.setFightId(0);
                    fightPc.setFightId(0);
                    fightPc.sendPackets(new S_ServerMessage(631, pc.getName())); // %0%d
                                                                                 // 拒绝了与你的决斗。
                } else if (c == 1) {
                    fightPc.sendPackets(new S_PacketBox(S_PacketBox.MSG_DUEL,
                            fightPc.getFightId(), fightPc.getId()));
                    pc.sendPackets(new S_PacketBox(S_PacketBox.MSG_DUEL, pc
                            .getFightId(), pc.getId()));
                }
                break;

            case 653: // 若你离婚，你的结婚戒指将会消失。你决定要离婚吗？(Y/N)
                c = this.readH();
                final L1PcInstance target653 = (L1PcInstance) L1World
                        .getInstance().findObject(pc.getPartnerId());
                if (c == 0) { // No
                    return;
                } else if (c == 1) { // Yes
                    if (target653 != null) {
                        target653.setPartnerId(0);
                        target653.save();
                        target653.sendPackets(new S_ServerMessage(662)); // \f1你(你)目前未婚。
                    } else {
                        CharacterTable.getInstance().updatePartnerId(
                                pc.getPartnerId());
                    }
                }
                pc.setPartnerId(0);
                pc.save(); // 将玩家资料储存到资料库中
                pc.sendPackets(new S_ServerMessage(662)); // \f1你(你)目前未婚。
                break;

            case 654: // %0 向你(你)求婚，你(你)答应吗?
                c = this.readH();
                final L1PcInstance partner = (L1PcInstance) L1World
                        .getInstance().findObject(pc.getTempID());
                pc.setTempID(0);
                if (partner != null) {
                    if (c == 0) { // No
                        partner.sendPackets(new S_ServerMessage(656, pc
                                .getName())); // %0
                                              // 拒绝你(你)的求婚。
                    } else if (c == 1) { // Yes
                        pc.setPartnerId(partner.getId());
                        pc.save();
                        pc.sendPackets(new S_ServerMessage(790)); // 俩人的结婚在所有人的祝福下完成
                        pc.sendPackets(new S_ServerMessage(655, partner
                                .getName())); // 恭喜!!
                                              // %0
                                              // 已接受你(你)的求婚。

                        partner.setPartnerId(pc.getId());
                        partner.save();
                        partner.sendPackets(new S_ServerMessage(790)); // 俩人的结婚在所有人的祝福下完成
                        partner.sendPackets(new S_ServerMessage(655, pc
                                .getName())); // 恭喜!!
                                              // %0
                                              // 已接受你(你)的求婚。
                    }
                }
                break;

            // 呼叫血盟成员
            case 729: // 盟主正在呼唤你，你要接受他的呼唤吗？(Y/N)
                c = this.readH();
                if (c == 0) { // No

                } else if (c == 1) { // Yes
                    this.callClan(pc);
                }
                break;

            case 738: // 恢复经验值需消耗%0金币。想要恢复经验值吗?
                c = this.readH();
                if ((c == 1) && (pc.getExpRes() == 1)) { // Yes
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
                    if (pc.getInventory().consumeItem(L1ItemId.ADENA, cost)) {
                        pc.resExp();
                        pc.setExpRes(0);
                    } else {
                        pc.sendPackets(new S_ServerMessage(189)); // \f1金币不足。
                    }
                }
                break;

            case 951: // 您要接受玩家 %0%s 提出的队伍对话邀请吗？(Y/N)
                c = this.readH();
                final L1PcInstance chatPc = (L1PcInstance) L1World
                        .getInstance().findObject(pc.getPartyID());
                if (chatPc != null) {
                    if (c == 0) { // No
                        chatPc.sendPackets(new S_ServerMessage(423, pc
                                .getName())); // %0%s
                                              // 拒绝了您的邀请。
                        pc.setPartyID(0);
                    } else if (c == 1) { // Yes
                        if (chatPc.isInChatParty()) {
                            if (chatPc.getChatParty().isVacancy()
                                    || chatPc.isGm()) {
                                chatPc.getChatParty().addMember(pc);
                            } else {
                                chatPc.sendPackets(new S_ServerMessage(417)); // 你的队伍已经满了，无法再接受队员。
                            }
                        } else {
                            final L1ChatParty chatParty = new L1ChatParty();
                            chatParty.addMember(chatPc);
                            chatParty.addMember(pc);
                            chatPc.sendPackets(new S_ServerMessage(424, pc
                                    .getName())); // %0%s 加入了您的队伍。
                        }
                    }
                }
                break;

            case 953: // 玩家 %0%s 邀请您加入队伍？(Y/N)
                c = this.readH();
                final L1PcInstance target = (L1PcInstance) L1World
                        .getInstance().findObject(pc.getPartyID());
                if (target != null) {
                    if (c == 0) // No
                    {
                        target.sendPackets(new S_ServerMessage(423, pc
                                .getName())); // %0%s
                                              // 拒绝了您的邀请。
                        pc.setPartyID(0);
                    } else if (c == 1) // Yes
                    {
                        if (target.isInParty()) {
                            // 队长组队中
                            if (target.getParty().isVacancy() || target.isGm()) {
                                // 组队是空的
                                target.getParty().addMember(pc);
                            } else {
                                // 组队满了
                                target.sendPackets(new S_ServerMessage(417)); // 你的队伍已经满了，无法再接受队员。
                            }
                        } else {
                            // 还没有组队，建立一个新组队
                            final L1Party party = new L1Party();
                            party.addMember(target);
                            party.addMember(pc);
                            target.sendPackets(new S_ServerMessage(424, pc
                                    .getName())); // %0%s 加入了您的队伍。
                        }
                    }
                }
                break;

            case 954: // 玩家 %0%s 邀请您加入自动分配队伍？(Y/N)
                c = this.readH();
                final L1PcInstance target2 = (L1PcInstance) L1World
                        .getInstance().findObject(pc.getPartyID());
                if (target2 != null) {
                    if (c == 0) { // No
                        target2.sendPackets(new S_ServerMessage(423, pc
                                .getName())); // %0%s
                                              // 拒绝了您的邀请。
                        pc.setPartyID(0);
                    } else if (c == 1) { // Yes
                        if (target2.isInParty()) {
                            // 队长组队中
                            if (target2.getParty().isVacancy()
                                    || target2.isGm()) {
                                // 组队是空的
                                target2.getParty().addMember(pc);
                            } else {
                                // 组队满了
                                target2.sendPackets(new S_ServerMessage(417)); // 你的队伍已经满了，无法再接受队员。
                            }
                        } else {
                            // 还没有组队，建立一个新组队
                            final L1Party party = new L1Party();
                            party.addMember(target2);
                            party.addMember(pc);
                            target2.sendPackets(new S_ServerMessage(424, pc
                                    .getName())); // %0%s 加入了您的队伍。
                        }
                    }
                }
                break;

            case 479: // 提升能力值？（str、dex、int、con、wis、cha）
                if (this.readC() == 1) {
                    final String s = this.readS();
                    if (!(pc.getLevel() - 50 > pc.getBonusStats())) {
                        return;
                    }
                    if (s.toLowerCase().equals("str".toLowerCase())) {
                        // if(l1pcinstance.get_str() < 255)
                        if (pc.getBaseStr() < Config.BONUS_STATS1) {
                            pc.addBaseStr((byte) 1); // 素のSTR值に+1
                            pc.setBonusStats(pc.getBonusStats() + 1);
                            pc.sendPackets(new S_OwnCharStatus2(pc));
                            pc.sendPackets(new S_CharVisualUpdate(pc));
                            pc.save(); // 将玩家资料储存到资料库中
                        } else {
                            pc.sendPackets(new S_SystemMessage("力量属性最大值只能到"
                                    + Config.BONUS_STATS1 + ",请选择其他能力值。"));// \f1属性最大值只能到35。请重试一次。
                        }
                    } else if (s.toLowerCase().equals("dex".toLowerCase())) {
                        // if(l1pcinstance.get_dex() < 255)
                        if (pc.getBaseDex() < Config.BONUS_STATS1) {
                            pc.addBaseDex((byte) 1); // 素のDEX值に+1
                            pc.resetBaseAc();
                            pc.setBonusStats(pc.getBonusStats() + 1);
                            pc.sendPackets(new S_OwnCharStatus2(pc));
                            pc.sendPackets(new S_CharVisualUpdate(pc));
                            pc.save(); // 将玩家资料储存到资料库中
                        } else {
                            pc.sendPackets(new S_SystemMessage("敏捷属性最大值只能到"
                                    + Config.BONUS_STATS1 + ",请选择其他能力值。")); // \f1属性最大值只能到35。请重试一次。
                        }
                    } else if (s.toLowerCase().equals("con".toLowerCase())) {
                        // if(l1pcinstance.get_con() < 255)
                        if (pc.getBaseCon() < Config.BONUS_STATS1) {
                            pc.addBaseCon((byte) 1); // 素のCON值に+1
                            pc.setBonusStats(pc.getBonusStats() + 1);
                            pc.sendPackets(new S_OwnCharStatus2(pc));
                            pc.sendPackets(new S_CharVisualUpdate(pc));
                            pc.save(); // 将玩家资料储存到资料库中
                        } else {
                            pc.sendPackets(new S_SystemMessage("体质属性最大值只能到"
                                    + Config.BONUS_STATS1 + ",请选择其他能力值。")); // \f1属性最大值只能到35。
                                                                            // 请重试一次。
                        }
                    } else if (s.toLowerCase().equals("int".toLowerCase())) {
                        // if(l1pcinstance.get_int() < 255)
                        if (pc.getBaseInt() < Config.BONUS_STATS1) {
                            pc.addBaseInt((byte) 1); // 素のINT值に+1
                            pc.setBonusStats(pc.getBonusStats() + 1);
                            pc.sendPackets(new S_OwnCharStatus2(pc));
                            pc.sendPackets(new S_CharVisualUpdate(pc));
                            pc.save(); // 将玩家资料储存到资料库中
                        } else {
                            pc.sendPackets(new S_SystemMessage("智力属性最大值只能到"
                                    + Config.BONUS_STATS1 + ",请选择其他能力值。")); // \f1属性最大值只能到35。
                                                                            // 请重试一次。
                        }
                    } else if (s.toLowerCase().equals("wis".toLowerCase())) {
                        // if(l1pcinstance.get_wis() < 255)
                        if (pc.getBaseWis() < Config.BONUS_STATS1) {
                            pc.addBaseWis((byte) 1); // 素のWIS值に+1
                            pc.resetBaseMr();
                            pc.setBonusStats(pc.getBonusStats() + 1);
                            pc.sendPackets(new S_OwnCharStatus2(pc));
                            pc.sendPackets(new S_CharVisualUpdate(pc));
                            pc.save(); // 将玩家资料储存到资料库中
                        } else {
                            pc.sendPackets(new S_SystemMessage("精神属性最大值只能到"
                                    + Config.BONUS_STATS1 + ",请选择其他能力值。")); // \f1属性最大值只能到35。
                                                                            // 请重试一次。
                        }
                    } else if (s.toLowerCase().equals("cha".toLowerCase())) {
                        // if(l1pcinstance.get_cha() < 255)
                        if (pc.getBaseCha() < Config.BONUS_STATS1) {
                            pc.addBaseCha((byte) 1); // 素のCHA值に+1
                            pc.setBonusStats(pc.getBonusStats() + 1);
                            pc.sendPackets(new S_OwnCharStatus2(pc));
                            pc.sendPackets(new S_CharVisualUpdate(pc));
                            pc.save(); // 将玩家资料储存到资料库中
                        } else {
                            pc.sendPackets(new S_SystemMessage("魅力属性最大值只能到"
                                    + Config.BONUS_STATS1 + ",请选择其他能力值。")); // \f1属性最大值只能到35。
                                                                            // 请重试一次。
                        }
                    }
                }
                break;
            case 1256:// 宠物竞速 预约名单回应
                com.lineage.server.model.game.L1PolyRace.getInstance()
                        .requsetAttr(pc, this.readC());
                break;
            default:
                break;
        }
    }

    // 呼叫血盟成员
    private void callClan(final L1PcInstance pc) {
        final L1PcInstance callClanPc = (L1PcInstance) L1World.getInstance()
                .findObject(pc.getTempID());
        pc.setTempID(0);
        if (callClanPc == null) {
            return;
        }
        if (!pc.getMap().isEscapable() && !pc.isGm()) {
            pc.sendPackets(new S_ServerMessage(647)); // 这附近的能量影响到瞬间移动。在此地无法使用瞬间移动。
            L1Teleport.teleport(pc, pc.getLocation(), pc.getHeading(), false);
            return;
        }
        if (pc.getId() != callClanPc.getCallClanId()) {
            return;
        }

        boolean isInWarArea = false;
        final int castleId = L1CastleLocation.getCastleIdByArea(callClanPc);
        if (castleId != 0) {
            isInWarArea = true;
            if (WarTimeController.getInstance().isNowWar(castleId)) {
                isInWarArea = false; // 战争也可以在时间的旗
            }
        }
        final short mapId = callClanPc.getMapId();
        if (((mapId != 0) && (mapId != 4) && (mapId != 304)) || isInWarArea) {
            pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生。
            return;
        }

        final L1Map map = callClanPc.getMap();
        int locX = callClanPc.getX();
        int locY = callClanPc.getY();
        int heading = callClanPc.getCallClanHeading();
        locX += HEADING_TABLE_X[heading];
        locY += HEADING_TABLE_Y[heading];
        heading = (heading + 4) % 4;

        boolean isExsistCharacter = false;
        for (final L1Object object : L1World.getInstance().getVisibleObjects(
                callClanPc, 1)) {
            if (object instanceof L1Character) {
                final L1Character cha = (L1Character) object;
                if ((cha.getX() == locX) && (cha.getY() == locY)
                        && (cha.getMapId() == mapId)) {
                    isExsistCharacter = true;
                    break;
                }
            }
        }

        if (((locX == 0) && (locY == 0)) || !map.isPassable(locX, locY)
                || isExsistCharacter) {
            pc.sendPackets(new S_ServerMessage(627)); // 因你要去的地方有障碍物以致于无法直接传送到该处。
            return;
        }
        L1Teleport.teleport(pc, locX, locY, mapId, heading, true,
                L1Teleport.CALL_CLAN);
    }

    // 改变血盟
    private void changeClan(final ClientThread clientthread,
            final L1PcInstance pc, final L1PcInstance joinPc,
            final int maxMember) {
        final int clanId = pc.getClanid();
        final String clanName = pc.getClanname();
        final L1Clan clan = L1World.getInstance().getClan(clanName);

        final int oldClanId = joinPc.getClanid();
        final String oldClanName = joinPc.getClanname();
        final L1Clan oldClan = L1World.getInstance().getClan(oldClanName);

        if ((clan != null) && (oldClan != null) && joinPc.isCrown() && // 自己的王族
                (joinPc.getId() == oldClan.getLeaderId())) {
            if (maxMember < clan.getAllMembers().length
                    + oldClan.getAllMembers().length) { // 没有空缺
                joinPc.sendPackets( // %0%s 无法接受你成为该血盟成员。
                new S_ServerMessage(188, pc.getName()));
                return;
            }
            for (final L1PcInstance element : clan.getOnlineClanMember()) {
                element.sendPackets(new S_ServerMessage(94, joinPc.getName())); // \f1你接受%0当你的血盟成员。
            }

            for (final String element : oldClan.getAllMembers()) {
                final L1PcInstance oldClanMember = L1World.getInstance()
                        .getPlayer(element);
                if (oldClanMember != null) { // 旧血盟成员在线上
                    oldClanMember.setClanid(clanId);
                    oldClanMember.setClanname(clanName);
                    // TODO: 翻译
                    // 血盟连合に加入した君主はガーディアン
                    // 君主が连れてきた血盟员は见习い
                    if (oldClanMember.getId() == joinPc.getId()) {
                        oldClanMember.setClanRank(L1Clan.CLAN_RANK_GUARDIAN);
                    } else {
                        oldClanMember.setClanRank(L1Clan.CLAN_RANK_PROBATION);
                    }
                    try {
                        // 储存玩家资料到资料库中
                        oldClanMember.save();
                    } catch (final Exception e) {
                        _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                    }
                    clan.addMemberName(oldClanMember.getName());
                    oldClanMember
                            .sendPackets(new S_ServerMessage(95, clanName)); // \f1加入%0血盟。
                } else { // 旧血盟成员不在线上
                    try {
                        final L1PcInstance offClanMember = CharacterTable
                                .getInstance().restoreCharacter(element);
                        offClanMember.setClanid(clanId);
                        offClanMember.setClanname(clanName);
                        offClanMember.setClanRank(L1Clan.CLAN_RANK_PROBATION);
                        offClanMember.save(); // 储存玩家资料到资料库中
                        clan.addMemberName(offClanMember.getName());
                    } catch (final Exception e) {
                        _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                    }
                }
            }
            // 删除旧盟徽
            final String emblem_file = String.valueOf(oldClanId);
            final File file = new File("emblem/" + emblem_file);
            file.delete();
            ClanTable.getInstance().deleteClan(oldClanName);
        }
    }

    @Override
    public String getType() {
        return C_ATTR;
    }

    // 复活
    private void resurrection(final L1PcInstance pc,
            final L1PcInstance resusepc, final short resHp) {
        // 由其他角色复活
        pc.sendPackets(new S_SkillSound(pc.getId(), '\346'));
        pc.broadcastPacket(new S_SkillSound(pc.getId(), '\346'));
        pc.resurrect(resHp);
        pc.setCurrentHp(resHp);
        pc.startHpRegeneration();
        pc.startMpRegeneration();
        pc.startHpRegenerationByDoll();
        pc.startMpRegenerationByDoll();
        pc.stopPcDeleteTimer();
        pc.sendPackets(new S_Resurrection(pc, resusepc, 0));
        pc.broadcastPacket(new S_Resurrection(pc, resusepc, 0));
        pc.sendPackets(new S_CharVisualUpdate(pc));
        pc.broadcastPacket(new S_CharVisualUpdate(pc));
    }
}
