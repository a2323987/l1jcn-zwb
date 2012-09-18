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

import static com.lineage.server.Opcodes.C_OPCODE_ADDBUDDY;
import static com.lineage.server.Opcodes.C_OPCODE_AMOUNT;
import static com.lineage.server.Opcodes.C_OPCODE_ARROWATTACK;
import static com.lineage.server.Opcodes.C_OPCODE_ATTACK;
import static com.lineage.server.Opcodes.C_OPCODE_ATTR;
import static com.lineage.server.Opcodes.C_OPCODE_BANCLAN;
import static com.lineage.server.Opcodes.C_OPCODE_BANPARTY;
import static com.lineage.server.Opcodes.C_OPCODE_BOARD;
import static com.lineage.server.Opcodes.C_OPCODE_BOARDDELETE;
import static com.lineage.server.Opcodes.C_OPCODE_BOARDNEXT;
import static com.lineage.server.Opcodes.C_OPCODE_BOARDREAD;
import static com.lineage.server.Opcodes.C_OPCODE_BOARDWRITE;
import static com.lineage.server.Opcodes.C_OPCODE_BOOKMARK;
import static com.lineage.server.Opcodes.C_OPCODE_BOOKMARKDELETE;
import static com.lineage.server.Opcodes.C_OPCODE_BUDDYLIST;
import static com.lineage.server.Opcodes.C_OPCODE_CAHTPARTY;
import static com.lineage.server.Opcodes.C_OPCODE_CALL;
import static com.lineage.server.Opcodes.C_OPCODE_CHANGECHAR;
import static com.lineage.server.Opcodes.C_OPCODE_CHANGEHEADING;
import static com.lineage.server.Opcodes.C_OPCODE_CHANGEWARTIME;
import static com.lineage.server.Opcodes.C_OPCODE_CHARACTERCONFIG;
import static com.lineage.server.Opcodes.C_OPCODE_CHARRESET;
import static com.lineage.server.Opcodes.C_OPCODE_CHAT;
import static com.lineage.server.Opcodes.C_OPCODE_CHATGLOBAL;
import static com.lineage.server.Opcodes.C_OPCODE_CHATWHISPER;
import static com.lineage.server.Opcodes.C_OPCODE_CHECKPK;
import static com.lineage.server.Opcodes.C_OPCODE_CLAN;
import static com.lineage.server.Opcodes.C_OPCODE_CLIENTVERSION;
import static com.lineage.server.Opcodes.C_OPCODE_COMMONCLICK;
import static com.lineage.server.Opcodes.C_OPCODE_CREATECLAN;
import static com.lineage.server.Opcodes.C_OPCODE_CREATEPARTY;
import static com.lineage.server.Opcodes.C_OPCODE_DELBUDDY;
import static com.lineage.server.Opcodes.C_OPCODE_DELETECHAR;
import static com.lineage.server.Opcodes.C_OPCODE_DELETEINVENTORYITEM;
import static com.lineage.server.Opcodes.C_OPCODE_DEPOSIT;
import static com.lineage.server.Opcodes.C_OPCODE_DOOR;
import static com.lineage.server.Opcodes.C_OPCODE_DRAWAL;
import static com.lineage.server.Opcodes.C_OPCODE_DROPITEM;
import static com.lineage.server.Opcodes.C_OPCODE_EMBLEM;
import static com.lineage.server.Opcodes.C_OPCODE_ENTERPORTAL;
import static com.lineage.server.Opcodes.C_OPCODE_EXCLUDE;
import static com.lineage.server.Opcodes.C_OPCODE_EXIT_GHOST;
import static com.lineage.server.Opcodes.C_OPCODE_EXTCOMMAND;
import static com.lineage.server.Opcodes.C_OPCODE_FIGHT;
import static com.lineage.server.Opcodes.C_OPCODE_FISHCLICK;
import static com.lineage.server.Opcodes.C_OPCODE_FIX_WEAPON_LIST;
import static com.lineage.server.Opcodes.C_OPCODE_GIVEITEM;
import static com.lineage.server.Opcodes.C_OPCODE_HIRESOLDIER;
import static com.lineage.server.Opcodes.C_OPCODE_JOINCLAN;
import static com.lineage.server.Opcodes.C_OPCODE_KEEPALIVE;
import static com.lineage.server.Opcodes.C_OPCODE_LEAVECLANE;
import static com.lineage.server.Opcodes.C_OPCODE_LEAVEPARTY;
import static com.lineage.server.Opcodes.C_OPCODE_LOGINPACKET;
import static com.lineage.server.Opcodes.C_OPCODE_LOGINTOSERVER;
import static com.lineage.server.Opcodes.C_OPCODE_LOGINTOSERVEROK;
import static com.lineage.server.Opcodes.C_OPCODE_MAIL;
import static com.lineage.server.Opcodes.C_OPCODE_MOVECHAR;
import static com.lineage.server.Opcodes.C_OPCODE_NEWCHAR;
import static com.lineage.server.Opcodes.C_OPCODE_NPCACTION;
import static com.lineage.server.Opcodes.C_OPCODE_NPCTALK;
import static com.lineage.server.Opcodes.C_OPCODE_PARTYLIST;
import static com.lineage.server.Opcodes.C_OPCODE_PETMENU;
import static com.lineage.server.Opcodes.C_OPCODE_PICKUPITEM;
import static com.lineage.server.Opcodes.C_OPCODE_PLEDGE;
import static com.lineage.server.Opcodes.C_OPCODE_PRIVATESHOPLIST;
import static com.lineage.server.Opcodes.C_OPCODE_PROPOSE;
import static com.lineage.server.Opcodes.C_OPCODE_QUITGAME;
import static com.lineage.server.Opcodes.C_OPCODE_RANK;
import static com.lineage.server.Opcodes.C_OPCODE_RESTART;
import static com.lineage.server.Opcodes.C_OPCODE_RESULT;
import static com.lineage.server.Opcodes.C_OPCODE_RETURNTOLOGIN;
import static com.lineage.server.Opcodes.C_OPCODE_SELECTLIST;
import static com.lineage.server.Opcodes.C_OPCODE_SELECTTARGET;
import static com.lineage.server.Opcodes.C_OPCODE_SENDLOCATION;
import static com.lineage.server.Opcodes.C_OPCODE_SHIP;
import static com.lineage.server.Opcodes.C_OPCODE_SHOP;
import static com.lineage.server.Opcodes.C_OPCODE_SKILLBUY;
import static com.lineage.server.Opcodes.C_OPCODE_SKILLBUYITEM;
import static com.lineage.server.Opcodes.C_OPCODE_SKILLBUYOK;
import static com.lineage.server.Opcodes.C_OPCODE_SKILLBUYOKITEM;
import static com.lineage.server.Opcodes.C_OPCODE_TAXRATE;
import static com.lineage.server.Opcodes.C_OPCODE_TELEPORT;
import static com.lineage.server.Opcodes.C_OPCODE_TITLE;
import static com.lineage.server.Opcodes.C_OPCODE_TRADE;
import static com.lineage.server.Opcodes.C_OPCODE_TRADEADDCANCEL;
import static com.lineage.server.Opcodes.C_OPCODE_TRADEADDITEM;
import static com.lineage.server.Opcodes.C_OPCODE_TRADEADDOK;
import static com.lineage.server.Opcodes.C_OPCODE_USEITEM;
import static com.lineage.server.Opcodes.C_OPCODE_USEPETITEM;
import static com.lineage.server.Opcodes.C_OPCODE_USESKILL;
import static com.lineage.server.Opcodes.C_OPCODE_WAR;
import static com.lineage.server.Opcodes.C_OPCODE_WAREHOUSELOCK;
import static com.lineage.server.Opcodes.C_OPCODE_WHO;

import java.util.logging.Logger;

import com.lineage.server.clientpackets.C_AddBookmark;
import com.lineage.server.clientpackets.C_AddBuddy;
import com.lineage.server.clientpackets.C_Amount;
import com.lineage.server.clientpackets.C_Attack;
import com.lineage.server.clientpackets.C_Attr;
import com.lineage.server.clientpackets.C_AuthLogin;
import com.lineage.server.clientpackets.C_BanClan;
import com.lineage.server.clientpackets.C_BanParty;
import com.lineage.server.clientpackets.C_Board;
import com.lineage.server.clientpackets.C_BoardBack;
import com.lineage.server.clientpackets.C_BoardDelete;
import com.lineage.server.clientpackets.C_BoardRead;
import com.lineage.server.clientpackets.C_BoardWrite;
import com.lineage.server.clientpackets.C_Buddy;
import com.lineage.server.clientpackets.C_CallPlayer;
import com.lineage.server.clientpackets.C_ChangeHeading;
import com.lineage.server.clientpackets.C_ChangeWarTime;
import com.lineage.server.clientpackets.C_CharReset;
import com.lineage.server.clientpackets.C_CharcterConfig;
import com.lineage.server.clientpackets.C_Chat;
import com.lineage.server.clientpackets.C_ChatParty;
import com.lineage.server.clientpackets.C_ChatWhisper;
import com.lineage.server.clientpackets.C_CheckPK;
import com.lineage.server.clientpackets.C_Clan;
import com.lineage.server.clientpackets.C_CommonClick;
import com.lineage.server.clientpackets.C_CreateChar;
import com.lineage.server.clientpackets.C_CreateClan;
import com.lineage.server.clientpackets.C_CreateParty;
import com.lineage.server.clientpackets.C_DelBuddy;
import com.lineage.server.clientpackets.C_DeleteBookmark;
import com.lineage.server.clientpackets.C_DeleteChar;
import com.lineage.server.clientpackets.C_DeleteInventoryItem;
import com.lineage.server.clientpackets.C_Deposit;
import com.lineage.server.clientpackets.C_Door;
import com.lineage.server.clientpackets.C_Drawal;
import com.lineage.server.clientpackets.C_DropItem;
import com.lineage.server.clientpackets.C_Emblem;
import com.lineage.server.clientpackets.C_EnterPortal;
import com.lineage.server.clientpackets.C_Exclude;
import com.lineage.server.clientpackets.C_ExitGhost;
import com.lineage.server.clientpackets.C_ExtraCommand;
import com.lineage.server.clientpackets.C_Fight;
import com.lineage.server.clientpackets.C_FishClick;
import com.lineage.server.clientpackets.C_FixWeaponList;
import com.lineage.server.clientpackets.C_GiveItem;
import com.lineage.server.clientpackets.C_HireSoldier;
import com.lineage.server.clientpackets.C_ItemUSe;
import com.lineage.server.clientpackets.C_JoinClan;
import com.lineage.server.clientpackets.C_KeepALIVE;
import com.lineage.server.clientpackets.C_LeaveClan;
import com.lineage.server.clientpackets.C_LeaveParty;
import com.lineage.server.clientpackets.C_LoginToServer;
import com.lineage.server.clientpackets.C_LoginToServerOK;
import com.lineage.server.clientpackets.C_Mail;
import com.lineage.server.clientpackets.C_MoveChar;
import com.lineage.server.clientpackets.C_NPCAction;
import com.lineage.server.clientpackets.C_NPCTalk;
import com.lineage.server.clientpackets.C_NewCharSelect;
import com.lineage.server.clientpackets.C_Party;
import com.lineage.server.clientpackets.C_PetMenu;
import com.lineage.server.clientpackets.C_PickUpItem;
import com.lineage.server.clientpackets.C_Pledge;
import com.lineage.server.clientpackets.C_Propose;
import com.lineage.server.clientpackets.C_Rank;
import com.lineage.server.clientpackets.C_Restart;
import com.lineage.server.clientpackets.C_Result;
import com.lineage.server.clientpackets.C_ReturnToLogin;
import com.lineage.server.clientpackets.C_SelectList;
import com.lineage.server.clientpackets.C_SelectTarget;
import com.lineage.server.clientpackets.C_SendLocation;
import com.lineage.server.clientpackets.C_ServerVersion;
import com.lineage.server.clientpackets.C_Ship;
import com.lineage.server.clientpackets.C_Shop;
import com.lineage.server.clientpackets.C_ShopList;
import com.lineage.server.clientpackets.C_SkillBuy;
import com.lineage.server.clientpackets.C_SkillBuyItem;
import com.lineage.server.clientpackets.C_SkillBuyItemOK;
import com.lineage.server.clientpackets.C_SkillBuyOK;
import com.lineage.server.clientpackets.C_TaxRate;
import com.lineage.server.clientpackets.C_Teleport;
import com.lineage.server.clientpackets.C_Title;
import com.lineage.server.clientpackets.C_Trade;
import com.lineage.server.clientpackets.C_TradeAddItem;
import com.lineage.server.clientpackets.C_TradeCancel;
import com.lineage.server.clientpackets.C_TradeOK;
import com.lineage.server.clientpackets.C_UsePetItem;
import com.lineage.server.clientpackets.C_UseSkill;
import com.lineage.server.clientpackets.C_War;
import com.lineage.server.clientpackets.C_WarePassword;
import com.lineage.server.clientpackets.C_Who;
import com.lineage.server.model.Instance.L1PcInstance;

// Referenced classes of package com.lineage.server:
// Opcodes, LoginController, ClientThread, Logins

/**
 * 数据包处理程序
 */
public class PacketHandler {
    private static Logger _log = Logger
            .getLogger(PacketHandler.class.getName());
    private final ClientThread _client;

    public PacketHandler(final ClientThread clientthread) {
        this._client = clientthread;
    }

    /** 处理数据包 */
    public void handlePacket(final byte abyte0[], final L1PcInstance object)
            throws Exception {
        final int i = abyte0[0] & 0xff;

        switch (i) {
            case C_OPCODE_EXCLUDE:
                new C_Exclude(abyte0, this._client);
                break;

            case C_OPCODE_CHARACTERCONFIG:
                new C_CharcterConfig(abyte0, this._client);
                break;

            case C_OPCODE_DOOR:
                new C_Door(abyte0, this._client);
                break;

            case C_OPCODE_TITLE:
                new C_Title(abyte0, this._client);
                break;

            case C_OPCODE_BOARDDELETE:
                new C_BoardDelete(abyte0, this._client);
                break;

            case C_OPCODE_PLEDGE:
                new C_Pledge(abyte0, this._client);
                break;

            case C_OPCODE_CHANGEHEADING:
                new C_ChangeHeading(abyte0, this._client);
                break;

            case C_OPCODE_NPCACTION:
                new C_NPCAction(abyte0, this._client);
                break;

            case C_OPCODE_USESKILL:
                new C_UseSkill(abyte0, this._client);
                break;

            case C_OPCODE_EMBLEM:
                new C_Emblem(abyte0, this._client);
                break;

            case C_OPCODE_TRADEADDCANCEL:
                new C_TradeCancel(abyte0, this._client);
                break;

            case C_OPCODE_CHANGEWARTIME:
                new C_ChangeWarTime(abyte0, this._client);
                break;

            case C_OPCODE_BOOKMARK:
                new C_AddBookmark(abyte0, this._client);
                break;

            case C_OPCODE_CREATECLAN:
                new C_CreateClan(abyte0, this._client);
                break;

            case C_OPCODE_CLIENTVERSION:
                new C_ServerVersion(abyte0, this._client);
                break;

            case C_OPCODE_PROPOSE:
                new C_Propose(abyte0, this._client);
                break;

            case C_OPCODE_SKILLBUY:
                new C_SkillBuy(abyte0, this._client);
                break;

            case C_OPCODE_SKILLBUYITEM:
                new C_SkillBuyItem(abyte0, this._client);
                break;

            case C_OPCODE_SKILLBUYOKITEM:
                new C_SkillBuyItemOK(abyte0, this._client);
                break;

            case C_OPCODE_BOARDNEXT:
                new C_BoardBack(abyte0, this._client);
                break;

            case C_OPCODE_SHOP:
                new C_Shop(abyte0, this._client);
                break;

            case C_OPCODE_BOARDREAD:
                new C_BoardRead(abyte0, this._client);
                break;

            case C_OPCODE_TRADE:
                new C_Trade(abyte0, this._client);
                break;

            case C_OPCODE_DELETECHAR:
                new C_DeleteChar(abyte0, this._client);
                break;

            case C_OPCODE_KEEPALIVE:
                new C_KeepALIVE(abyte0, this._client);
                break;

            case C_OPCODE_ATTR:
                new C_Attr(abyte0, this._client);
                break;

            case C_OPCODE_LOGINPACKET:
                new C_AuthLogin(abyte0, this._client);
                break;

            case C_OPCODE_RESULT:
                new C_Result(abyte0, this._client);
                break;

            case C_OPCODE_DEPOSIT:
                new C_Deposit(abyte0, this._client);
                break;

            case C_OPCODE_DRAWAL:
                new C_Drawal(abyte0, this._client);
                break;

            case C_OPCODE_LOGINTOSERVEROK:
                new C_LoginToServerOK(abyte0, this._client);
                break;

            case C_OPCODE_SKILLBUYOK:
                new C_SkillBuyOK(abyte0, this._client);
                break;

            case C_OPCODE_TRADEADDITEM:
                new C_TradeAddItem(abyte0, this._client);
                break;

            case C_OPCODE_ADDBUDDY:
                new C_AddBuddy(abyte0, this._client);
                break;

            case C_OPCODE_RETURNTOLOGIN:
                new C_ReturnToLogin(abyte0, this._client);
                break;

            case C_OPCODE_CHAT:
                new C_Chat(abyte0, this._client);
                break;

            case C_OPCODE_TRADEADDOK:
                new C_TradeOK(abyte0, this._client);
                break;

            case C_OPCODE_CHECKPK:
                new C_CheckPK(abyte0, this._client);
                break;

            case C_OPCODE_TAXRATE:
                new C_TaxRate(abyte0, this._client);
                break;

            case C_OPCODE_CHANGECHAR:
                new C_NewCharSelect(abyte0, this._client);
                // new C_CommonClick(this._client);
                break;

            case C_OPCODE_BUDDYLIST:
                new C_Buddy(abyte0, this._client);
                break;

            case C_OPCODE_DROPITEM:
                new C_DropItem(abyte0, this._client);
                break;

            case C_OPCODE_LEAVEPARTY:
                new C_LeaveParty(abyte0, this._client);
                break;

            case C_OPCODE_ATTACK:
            case C_OPCODE_ARROWATTACK:
                new C_Attack(abyte0, this._client);
                break;

            // TODO 翻譯
            // キャラクターのショートカットやインベントリの状態がプレイ中に変動した場合に
            // ショートカットやインベントリの状態を付加してクライアントから送信されてくる
            // 送られてくるタイミングはクライアント終了時
            case C_OPCODE_QUITGAME:
                break;

            case C_OPCODE_BANCLAN:
                new C_BanClan(abyte0, this._client);
                break;

            case C_OPCODE_BOARD:
                new C_Board(abyte0, this._client);
                break;

            case C_OPCODE_DELETEINVENTORYITEM:
                new C_DeleteInventoryItem(abyte0, this._client);
                break;

            case C_OPCODE_CHATWHISPER:
                new C_ChatWhisper(abyte0, this._client);
                break;

            case C_OPCODE_PARTYLIST:
                new C_Party(abyte0, this._client);
                break;

            case C_OPCODE_PICKUPITEM:
                new C_PickUpItem(abyte0, this._client);
                break;

            case C_OPCODE_WHO:
                new C_Who(abyte0, this._client);
                break;

            case C_OPCODE_GIVEITEM:
                new C_GiveItem(abyte0, this._client);
                break;

            case C_OPCODE_MOVECHAR:
                new C_MoveChar(abyte0, this._client);
                break;

            case C_OPCODE_BOOKMARKDELETE:
                new C_DeleteBookmark(abyte0, this._client);
                break;

            case C_OPCODE_RESTART:
                new C_Restart(abyte0, this._client);
                break;

            case C_OPCODE_LEAVECLANE:
                new C_LeaveClan(abyte0, this._client);
                break;

            case C_OPCODE_NPCTALK:
                new C_NPCTalk(abyte0, this._client);
                break;

            case C_OPCODE_BANPARTY:
                new C_BanParty(abyte0, this._client);
                break;

            case C_OPCODE_DELBUDDY:
                new C_DelBuddy(abyte0, this._client);
                break;

            case C_OPCODE_WAR:
                new C_War(abyte0, this._client);
                break;

            case C_OPCODE_LOGINTOSERVER:
                new C_LoginToServer(abyte0, this._client);
                break;

            case C_OPCODE_PRIVATESHOPLIST:
                new C_ShopList(abyte0, this._client);
                break;

            case C_OPCODE_CHATGLOBAL:
                new C_Chat(abyte0, this._client);
                break;

            case C_OPCODE_JOINCLAN:
                new C_JoinClan(abyte0, this._client);
                break;

            case C_OPCODE_COMMONCLICK:
                new C_CommonClick(this._client);
                break;

            case C_OPCODE_NEWCHAR:
                new C_CreateChar(abyte0, this._client);
                break;

            case C_OPCODE_EXTCOMMAND:
                new C_ExtraCommand(abyte0, this._client);
                break;

            case C_OPCODE_BOARDWRITE:
                new C_BoardWrite(abyte0, this._client);
                break;

            case C_OPCODE_USEITEM:
                new C_ItemUSe(abyte0, this._client);
                break;

            case C_OPCODE_CREATEPARTY:
                new C_CreateParty(abyte0, this._client);
                break;

            case C_OPCODE_ENTERPORTAL:
                new C_EnterPortal(abyte0, this._client);
                break;

            case C_OPCODE_AMOUNT:
                new C_Amount(abyte0, this._client);
                break;

            case C_OPCODE_FIX_WEAPON_LIST:
                new C_FixWeaponList(abyte0, this._client);
                break;

            case C_OPCODE_SELECTLIST:
                new C_SelectList(abyte0, this._client);
                break;

            case C_OPCODE_EXIT_GHOST:
                new C_ExitGhost(abyte0, this._client);
                break;

            case C_OPCODE_CALL:
                new C_CallPlayer(abyte0, this._client);
                break;

            case C_OPCODE_HIRESOLDIER:
                new C_HireSoldier(abyte0, this._client);
                break;

            case C_OPCODE_FISHCLICK:
                new C_FishClick(abyte0, this._client);
                break;

            case C_OPCODE_SELECTTARGET:
                new C_SelectTarget(abyte0, this._client);
                break;

            case C_OPCODE_PETMENU:
                new C_PetMenu(abyte0, this._client);
                break;

            case C_OPCODE_USEPETITEM:
                new C_UsePetItem(abyte0, this._client);
                break;

            case C_OPCODE_TELEPORT:
                new C_Teleport(abyte0, this._client);
                break;

            case C_OPCODE_RANK:
                new C_Rank(abyte0, this._client);
                break;

            case C_OPCODE_CAHTPARTY:
                new C_ChatParty(abyte0, this._client);
                break;

            case C_OPCODE_FIGHT:
                new C_Fight(abyte0, this._client);
                break;

            case C_OPCODE_SHIP:
                new C_Ship(abyte0, this._client);
                break;

            case C_OPCODE_MAIL:
                new C_Mail(abyte0, this._client);
                break;

            case C_OPCODE_CHARRESET:
                new C_CharReset(abyte0, this._client);
                break;

            case C_OPCODE_CLAN:
                new C_Clan(abyte0, this._client);
                break;

            case C_OPCODE_SENDLOCATION:
                new C_SendLocation(abyte0, this._client);
                break;

            case C_OPCODE_WAREHOUSELOCK:
                new C_WarePassword(abyte0, this._client);
                break;

            default:
                _log.warning("--->>未处理的封包:" + i);
                // String s = Integer.toHexString(abyte0[0] & 0xff);
                // _log.warning("用途不明オペコード:データ内容");
                // _log.warning((new StringBuilder()).append("オペコード ").append(s)
                // .toString());
                // _log.warning(new ByteArrayUtil(abyte0).dumpToString());
                break;
        }
        // _log.warning((new StringBuilder()).append("オペコード
        // ").append(i).toString());
    }
}
