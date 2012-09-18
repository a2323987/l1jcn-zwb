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

import static com.lineage.server.model.skill.L1SkillId.COOKING_1_0_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_0_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_6_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_6_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_0_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_0_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_6_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_6_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_0_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_0_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_6_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_6_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_WONDER_DRUG;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BEGIN;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLOODSTAIN_OF_ANTHARAS;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLOODSTAIN_OF_FAFURION;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_END;
import static com.lineage.server.model.skill.L1SkillId.MIRROR_IMAGE;
import static com.lineage.server.model.skill.L1SkillId.SHAPE_CHANGE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_BLUE_POTION;
import static com.lineage.server.model.skill.L1SkillId.STATUS_BRAVE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_BRAVE2;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CHAT_PROHIBITED;
import static com.lineage.server.model.skill.L1SkillId.STATUS_ELFBRAVE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_HASTE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_RIBRAVE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_THIRD_SPEED;
import static com.lineage.server.model.skill.L1SkillId.UNCANNY_DODGE;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.L1DatabaseFactory;
import com.lineage.server.Account;
import com.lineage.server.ActionCodes;
import com.lineage.server.ClientThread;
import com.lineage.server.WarTimeController;
import com.lineage.server.datatables.CharacterTable;
import com.lineage.server.datatables.GetBackRestartTable;
import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.model.Getback;
import com.lineage.server.model.L1CastleLocation;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1Cooking;
import com.lineage.server.model.L1PolyMorph;
import com.lineage.server.model.L1War;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.model.skill.L1BuffUtil;
import com.lineage.server.model.skill.L1SkillUse;
import com.lineage.server.serverpackets.S_ActiveSpells;
import com.lineage.server.serverpackets.S_AddSkill;
import com.lineage.server.serverpackets.S_Bookmarks;
import com.lineage.server.serverpackets.S_CharTitle;
import com.lineage.server.serverpackets.S_CharacterConfig;
import com.lineage.server.serverpackets.S_InitialAbilityGrowth;
import com.lineage.server.serverpackets.S_InvList;
import com.lineage.server.serverpackets.S_Invis;
import com.lineage.server.serverpackets.S_Karma;
import com.lineage.server.serverpackets.S_Liquor;
import com.lineage.server.serverpackets.S_LoginGame;
import com.lineage.server.serverpackets.S_MapID;
import com.lineage.server.serverpackets.S_OwnCharPack;
import com.lineage.server.serverpackets.S_OwnCharStatus;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.serverpackets.S_SPMR;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillBrave;
import com.lineage.server.serverpackets.S_SkillHaste;
import com.lineage.server.serverpackets.S_SkillIconGFX;
import com.lineage.server.serverpackets.S_SkillIconThirdSpeed;
import com.lineage.server.serverpackets.S_SummonPack;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.serverpackets.S_War;
import com.lineage.server.serverpackets.S_Weather;
import com.lineage.server.templates.L1BookMark;
import com.lineage.server.templates.L1GetBackRestart;
import com.lineage.server.templates.L1Skills;
import com.lineage.server.utils.SQLUtil;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket
//

/**
 * 处理收到由客户端传来登入到伺服器的封包
 */
public class C_LoginToServer extends ClientBasePacket {

    private static final String C_LOGIN_TO_SERVER = "[C] C_LoginToServer";

    private static Logger _log = Logger.getLogger(C_LoginToServer.class
            .getName());

    public C_LoginToServer(final byte abyte0[], final ClientThread client)
            throws FileNotFoundException, Exception {

        // 载入资料
        super(abyte0);

        // 取得账号名称
        final String login = client.getAccountName();

        // 取得角色名称
        final String charName = this.readS();

        if (client.getActiveChar() != null) {
            _log.info("同一个角色重复登入，强制切断 " + client.getHostname() + ") 的连结");
            client.close();
            return;
        }

        // 取得角色
        final L1PcInstance pc = L1PcInstance.load(charName);
        // 取得账号
        final Account account = Account.load(pc.getAccountName());

        // 该账号中已有角色在线上
        if (account.isOnlineStatus()) {
            _log.info("同一个帐号双重角色登入，强制切断 " + client.getHostname() + ") 的连结");
            client.close();
            return;
        }

        // 空名称或不一致
        if ((pc == null) || !login.equals(pc.getAccountName())) {
            _log.info("无效的角色名称: 名称=" + charName + " 账号=" + login + " IP="
                    + client.getHostname());
            client.close();
            return;
        }

        // 允许降低等级的范围
        if (Config.LEVEL_DOWN_RANGE != 0) {
            if (pc.getHighLevel() - pc.getLevel() >= Config.LEVEL_DOWN_RANGE) {
                _log.info("登录请求超出了容忍的等级下降的角色: 名称=" + charName + " 账号=" + login
                        + " IP=" + client.getHostname());
                client.kick();
                return;
            }
        }

        _log.info("角色登入到伺服器中: 名称=" + charName + " 账号=" + login + " IP="
                + client.getHostname());

        final int currentHpAtLoad = pc.getCurrentHp(); // 取得角色的当前血量
        final int currentMpAtLoad = pc.getCurrentMp(); // 取得角色的当前魔量
        pc.clearSkillMastery();
        pc.setOnlineStatus(1); // 设置为在线状态
        CharacterTable.updateOnlineStatus(pc); // 更新在线状态
        L1World.getInstance().storeObject(pc);

        pc.setNetConnection(client); // 设置网络连接状态
        pc.setPacketOutput(client); // 设置封包加密管理
        client.setActiveChar(pc); // 设置为在线角色

        /** 初始能力加成 */
        final S_InitialAbilityGrowth AbilityGrowth = new S_InitialAbilityGrowth(
                pc);
        pc.sendPackets(AbilityGrowth); // 发送初始能力加成

        /*
         * S_Unknown1 s_unknown1 = new S_Unknown1(); pc.sendPackets(s_unknown1);
         * S_Unknown2 s_unknown2 = new S_Unknown2(); pc.sendPackets(s_unknown2);
         */
        pc.sendPackets(new S_LoginGame());
        this.bookmarks(pc); // 加载记忆坐标

        // Online = 1
        // Account account = Account.load(pc.getAccountName());
        // Account.online(account, true);

        // OnlineStatus = 1
        Account.OnlineStatus(account, true); // 设置账号在线

        // 如果设定档中设定自动回村的话
        final GetBackRestartTable gbrTable = GetBackRestartTable.getInstance();
        final L1GetBackRestart[] gbrList = gbrTable
                .getGetBackRestartTableList();
        for (final L1GetBackRestart gbr : gbrList) {
            if (pc.getMapId() == gbr.getArea()) {
                pc.setX(gbr.getLocX());
                pc.setY(gbr.getLocY());
                pc.setMap(gbr.getMapId());
                break;
            }
        }

        // altsettings.properties 中 GetBack 设定为 true 就自动回村
        if (Config.GET_BACK) {
            final int[] loc = Getback.GetBack_Location(pc, true);
            pc.setX(loc[0]);
            pc.setY(loc[1]);
            pc.setMap((short) loc[2]);
        }

        // 如果标记是在战争期间，如果不是血盟成员回到城堡。
        final int castle_id = L1CastleLocation.getCastleIdByArea(pc);
        if (0 < castle_id) {
            if (WarTimeController.getInstance().isNowWar(castle_id)) {
                final L1Clan clan = L1World.getInstance().getClan(
                        pc.getClanname());
                if (clan != null) {
                    if (clan.getCastleId() != castle_id) {
                        // 没有城堡
                        int[] loc = new int[3];
                        loc = L1CastleLocation.getGetBackLoc(castle_id);
                        pc.setX(loc[0]);
                        pc.setY(loc[1]);
                        pc.setMap((short) loc[2]);
                    }
                } else {
                    // 有城堡就回到城堡
                    int[] loc = new int[3];
                    loc = L1CastleLocation.getGetBackLoc(castle_id);
                    pc.setX(loc[0]);
                    pc.setY(loc[1]);
                    pc.setMap((short) loc[2]);
                }
            }
        }

        L1World.getInstance().addVisibleObject(pc);
        final S_ActiveSpells s_activespells = new S_ActiveSpells(pc);
        pc.sendPackets(s_activespells);

        pc.beginGameTimeCarrier();

        final S_OwnCharStatus s_owncharstatus = new S_OwnCharStatus(pc);
        pc.sendPackets(s_owncharstatus); // 发送角色属性与能力值

        final S_MapID s_mapid = new S_MapID(pc.getMapId(), pc.getMap()
                .isUnderwater());
        pc.sendPackets(s_mapid); // 发送角色所在地图编号

        final S_OwnCharPack s_owncharpack = new S_OwnCharPack(pc);
        pc.sendPackets(s_owncharpack); // 发送角色本身讯息

        pc.sendPackets(new S_SPMR(pc)); // 发送角色魔攻魔防

        // XXX S_OwnCharPack 可能是不必要的
        final S_CharTitle s_charTitle = new S_CharTitle(pc.getId(),
                pc.getTitle());
        pc.sendPackets(s_charTitle); // 发送角色封号
        pc.broadcastPacket(s_charTitle);

        pc.sendVisualEffectAtLogin(); // 皇冠，毒，水和其他视觉效果显示

        pc.sendPackets(new S_Weather(L1World.getInstance().getWeather())); // 发送游戏天气

        this.items(pc); // 读取角色的道具
        this.skills(pc); // 读取角色的技能
        this.buff(client, pc); // 读取角色的状态
        pc.turnOnOffLight(); // 打开灯光 (照明范围)

        pc.sendPackets(new S_Karma(pc)); // 友好度
        /* 闪避率 */
        pc.sendPackets(new S_PacketBox(88, pc.getDodge())); // 正
        pc.sendPackets(new S_PacketBox(101, pc.getNdodge())); // 负
        /* 闪避率 */

        // 角色当前血量大于0
        if (pc.getCurrentHp() > 0) {
            pc.setDead(false); // 设置死亡状态 (关闭)
            pc.setStatus(0); // 初始化状态
            // 生存的呐喊
            if (pc.get_food() >= 225) {
                pc.setCryTime(0);
                pc.startCryOfSurvival();
            }
        } else {
            pc.setDead(true); // 设置死亡状态 (打开)
            pc.setStatus(ActionCodes.ACTION_Die); // 发送死亡动作
        }

        // 如在奇岩地监内则开始计时
        final short map = pc.getMapId();
        if ((map == 53) || (map == 54) || (map == 55) || (map == 56)) {
            pc.startRocksPrison();
            // System.out.println("开始计时、登陆");
        }

        // GM 上线自动隐身
        if (pc.isGm() || pc.isMonitor()) {
            pc.setGmInvis(true);
            pc.sendPackets(new S_Invis(pc.getId(), 1));
            pc.broadcastPacket(new S_RemoveObject(pc));
            pc.sendPackets(new S_SystemMessage("现在是隐身状态。"));
        }

        // 快捷键与血条位置统一管理
        if (Config.CHARACTER_CONFIG_IN_SERVER_SIDE) {
            pc.sendPackets(new S_CharacterConfig(pc.getId()));
        }

        this.serchSummon(pc); // 读取角色的召唤物
        pc.setBeginGhostFlag(true);

        WarTimeController.getInstance().checkCastleWar(pc); // 检查攻城战

        if (pc.getClanid() != 0) { // 有血盟
            // 取得血盟名称
            final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
            if (clan != null) {
                if ((pc.getClanid() == clan.getClanId()) && // 血盟解散、又重新用同样名字创立时的对策
                        pc.getClanname().toLowerCase()
                                .equals(clan.getClanName().toLowerCase())) {
                    // 取得在线血盟成员
                    final L1PcInstance[] clanMembers = clan
                            .getOnlineClanMember();
                    for (final L1PcInstance clanMember : clanMembers) {
                        if (clanMember.getId() != pc.getId()) {
                            clanMember.sendPackets(new S_ServerMessage(843, pc
                                    .getName())); // 血盟成员%0%s刚进入游戏。
                        }
                    }

                    // 取得所有的盟战
                    for (final L1War war : L1World.getInstance().getWarList()) {
                        // 取得战争中的血盟名称
                        final boolean ret = war
                                .CheckClanInWar(pc.getClanname());
                        // 盟战中
                        if (ret) {
                            // 取得敌方血盟名称
                            final String enemy_clan_name = war
                                    .GetEnemyClanName(pc.getClanname());
                            if (enemy_clan_name != null) {
                                pc.sendPackets(new S_War(8, pc.getClanname(),
                                        enemy_clan_name)); // \f1目前你的血盟与 %0
                                                           // 血盟交战当中。
                            }
                            break;
                        }
                    }
                } // 没有血盟的处理
                else {
                    pc.setClanid(0); // 设置血盟ID
                    pc.setClanname(""); // 设置血盟名称
                    pc.setClanRank(0); // 设置血盟阶级
                    pc.save(); // 储存玩家的资料到资料库中
                }
            }
        }

        // 已经结婚的角色
        if (pc.getPartnerId() != 0) {
            // 取得伴侣ID
            final L1PcInstance partner = (L1PcInstance) L1World.getInstance()
                    .findObject(pc.getPartnerId());
            if ((partner != null) && (partner.getPartnerId() != 0)) {
                if ((pc.getPartnerId() == partner.getId())
                        && (partner.getPartnerId() == pc.getId())) {
                    pc.sendPackets(new S_ServerMessage(548)); // 你的情人目前正在线上。
                    partner.sendPackets(new S_ServerMessage(549)); // 你的情人上线了!!
                }
            }
        }

        if (currentHpAtLoad > pc.getCurrentHp()) {
            pc.setCurrentHp(currentHpAtLoad);
        }
        if (currentMpAtLoad > pc.getCurrentMp()) {
            pc.setCurrentMp(currentMpAtLoad);
        }
        pc.startHpRegeneration(); // 开始恢复角色自身体力
        pc.startMpRegeneration(); // 开始恢复角色自身魔力
        pc.startObjectAutoUpdate(); // 自动更新物件
        client.CharReStart(false); // 重新开始状态 (关闭)
        pc.beginExpMonitor(); // 开始角色的经验值监视器
        pc.save(); // 储存玩家的资料到资料库中

        pc.sendPackets(new S_OwnCharStatus(pc)); // 更新角色属性与能力值

        // 地狱停留时间大于0
        if (pc.getHellTime() > 0) {
            pc.beginHell(false); // 强制传送地狱 (关闭)
        }

        // 处理新手保护系统(遭遇的守护)状态资料的变动
        pc.checkNoviceType();
    }

    // 读取角色的记忆坐标
    private void bookmarks(final L1PcInstance pc) {

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT * FROM character_teleport WHERE char_id=? ORDER BY name ASC");
            pstm.setInt(1, pc.getId());

            rs = pstm.executeQuery();
            while (rs.next()) {
                final L1BookMark bookmark = new L1BookMark();
                bookmark.setId(rs.getInt("id"));
                bookmark.setCharId(rs.getInt("char_id"));
                bookmark.setName(rs.getString("name"));
                bookmark.setLocX(rs.getInt("locx"));
                bookmark.setLocY(rs.getInt("locy"));
                bookmark.setMapId(rs.getShort("mapid"));
                final S_Bookmarks s_bookmarks = new S_Bookmarks(
                        bookmark.getName(), bookmark.getMapId(),
                        bookmark.getId());
                pc.addBookMark(bookmark);
                pc.sendPackets(s_bookmarks);
            }

        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    // 读取角色的状态
    private void buff(final ClientThread clientthread, final L1PcInstance pc) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT * FROM character_buff WHERE char_obj_id=?");
            pstm.setInt(1, pc.getId());
            rs = pstm.executeQuery();
            while (rs.next()) {
                final int skillid = rs.getInt("skill_id");
                int remaining_time = rs.getInt("remaining_time");
                int time = 0;
                switch (skillid) {
                    case SHAPE_CHANGE: // 变身
                        final int poly_id = rs.getInt("poly_id");
                        L1PolyMorph.doPoly(pc, poly_id, remaining_time,
                                L1PolyMorph.MORPH_BY_LOGIN);
                        break;
                    case STATUS_BRAVE: // 勇敢药水
                        pc.sendPackets(new S_SkillBrave(pc.getId(), 1,
                                remaining_time));
                        pc.broadcastPacket(new S_SkillBrave(pc.getId(), 1, 0));
                        pc.setBraveSpeed(1);
                        pc.setSkillEffect(skillid, remaining_time * 1000);
                        break;
                    case STATUS_ELFBRAVE: // 精灵饼干
                        pc.sendPackets(new S_SkillBrave(pc.getId(), 3,
                                remaining_time));
                        pc.broadcastPacket(new S_SkillBrave(pc.getId(), 3, 0));
                        pc.setBraveSpeed(3);
                        pc.setSkillEffect(skillid, remaining_time * 1000);
                        break;
                    case STATUS_BRAVE2: // 超级加速
                        pc.sendPackets(new S_SkillBrave(pc.getId(), 5,
                                remaining_time));
                        pc.broadcastPacket(new S_SkillBrave(pc.getId(), 5, 0));
                        pc.setBraveSpeed(5);
                        pc.setSkillEffect(skillid, remaining_time * 1000);
                        break;
                    case STATUS_HASTE: // 加速
                        pc.sendPackets(new S_SkillHaste(pc.getId(), 1,
                                remaining_time));
                        pc.broadcastPacket(new S_SkillHaste(pc.getId(), 1, 0));
                        pc.setMoveSpeed(1);
                        pc.setSkillEffect(skillid, remaining_time * 1000);
                        break;
                    case STATUS_BLUE_POTION: // 蓝色药水
                        pc.sendPackets(new S_SkillIconGFX(34, remaining_time));
                        pc.setSkillEffect(skillid, remaining_time * 1000);
                        break;
                    case STATUS_CHAT_PROHIBITED: // 禁言
                        pc.sendPackets(new S_SkillIconGFX(36, remaining_time));
                        pc.setSkillEffect(skillid, remaining_time * 1000);
                        break;
                    case STATUS_THIRD_SPEED: // 三段加速
                        time = remaining_time / 4;
                        pc.sendPackets(new S_Liquor(pc.getId(), 8)); // 人物 *
                                                                     // 1.15
                        pc.broadcastPacket(new S_Liquor(pc.getId(), 8)); // 人物 *
                                                                         // 1.15
                        pc.sendPackets(new S_SkillIconThirdSpeed(time));
                        pc.setSkillEffect(skillid, time * 4 * 1000);
                        break;
                    case MIRROR_IMAGE: // 镜像
                    case UNCANNY_DODGE: // 暗影闪避
                        time = remaining_time / 16;
                        pc.addDodge((byte) 5); // 闪避率 + 50%
                        // 更新闪避率显示
                        pc.sendPackets(new S_PacketBox(88, pc.getDodge()));
                        pc.sendPackets(new S_PacketBox(21, time));
                        pc.setSkillEffect(skillid, time * 16 * 1000);
                        break;
                    case EFFECT_BLOODSTAIN_OF_ANTHARAS: // 安塔瑞斯的血痕
                        remaining_time = remaining_time / 60;
                        if (remaining_time != 0) {
                            L1BuffUtil.bloodstain(pc, (byte) 0, remaining_time,
                                    false);
                        }
                        break;
                    case EFFECT_BLOODSTAIN_OF_FAFURION: // 法利昂的血痕
                        remaining_time = remaining_time / 60;
                        if (remaining_time != 0) {
                            L1BuffUtil.bloodstain(pc, (byte) 1, remaining_time,
                                    false);
                        }
                        break;
                    default:
                        // 魔法料理
                        if (((skillid >= COOKING_1_0_N) && (skillid <= COOKING_1_6_N))
                                || ((skillid >= COOKING_1_0_S) && (skillid <= COOKING_1_6_S))
                                || ((skillid >= COOKING_2_0_N) && (skillid <= COOKING_2_6_N))
                                || ((skillid >= COOKING_2_0_S) && (skillid <= COOKING_2_6_S))
                                || ((skillid >= COOKING_3_0_N) && (skillid <= COOKING_3_6_N))
                                || ((skillid >= COOKING_3_0_S) && (skillid <= COOKING_3_6_S))) {
                            L1Cooking.eatCooking(pc, skillid, remaining_time);
                        }
                        // 生命之树果实、商城道具
                        else if ((skillid == STATUS_RIBRAVE)
                                || ((skillid >= EFFECT_BEGIN) && (skillid <= EFFECT_END))
                                || (skillid == COOKING_WONDER_DRUG)) {
                            ;
                        } else {
                            final L1SkillUse l1skilluse = new L1SkillUse();
                            l1skilluse.handleCommands(
                                    clientthread.getActiveChar(), skillid,
                                    pc.getId(), pc.getX(), pc.getY(), null,
                                    remaining_time, L1SkillUse.TYPE_LOGIN);
                        }
                        break;
                }
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    @Override
    public String getType() {
        return C_LOGIN_TO_SERVER;
    }

    private void items(final L1PcInstance pc) {
        // 从资料库中读取角色的道具
        CharacterTable.getInstance().restoreInventory(pc);

        pc.sendPackets(new S_InvList(pc.getInventory().getItems()));
    }

    // 读取角色的召唤物
    private void serchSummon(final L1PcInstance pc) {
        for (final L1SummonInstance summon : L1World.getInstance()
                .getAllSummons()) {
            if (summon.getMaster().getId() == pc.getId()) {
                summon.setMaster(pc);
                pc.addPet(summon);
                for (final L1PcInstance visiblePc : L1World.getInstance()
                        .getVisiblePlayer(summon)) {
                    visiblePc.sendPackets(new S_SummonPack(summon, visiblePc));
                }
            }
        }
    }

    // 读取角色的技能
    private void skills(final L1PcInstance pc) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT * FROM character_skills WHERE char_obj_id=?");
            pstm.setInt(1, pc.getId());
            rs = pstm.executeQuery();
            int i = 0;
            int lv1 = 0;
            int lv2 = 0;
            int lv3 = 0;
            int lv4 = 0;
            int lv5 = 0;
            int lv6 = 0;
            int lv7 = 0;
            int lv8 = 0;
            int lv9 = 0;
            int lv10 = 0;
            int lv11 = 0;
            int lv12 = 0;
            int lv13 = 0;
            int lv14 = 0;
            int lv15 = 0;
            int lv16 = 0;
            int lv17 = 0;
            int lv18 = 0;
            int lv19 = 0;
            int lv20 = 0;
            int lv21 = 0;
            int lv22 = 0;
            int lv23 = 0;
            int lv24 = 0;
            int lv25 = 0;
            int lv26 = 0;
            int lv27 = 0;
            int lv28 = 0;
            while (rs.next()) {
                final int skillId = rs.getInt("skill_id");
                final L1Skills l1skills = SkillsTable.getInstance()
                        .getTemplate(skillId);
                switch (l1skills.getSkillLevel()) {
                    case 1:
                        lv1 |= l1skills.getId();
                        break;

                    case 2:
                        lv2 |= l1skills.getId();
                        break;

                    case 3:
                        lv3 |= l1skills.getId();
                        break;

                    case 4:
                        lv4 |= l1skills.getId();
                        break;

                    case 5:
                        lv5 |= l1skills.getId();
                        break;

                    case 6:
                        lv6 |= l1skills.getId();
                        break;

                    case 7:
                        lv7 |= l1skills.getId();
                        break;

                    case 8:
                        lv8 |= l1skills.getId();
                        break;

                    case 9:
                        lv9 |= l1skills.getId();
                        break;

                    case 10:
                        lv10 |= l1skills.getId();
                        break;

                    case 11:
                        lv11 |= l1skills.getId();
                        break;

                    case 12:
                        lv12 |= l1skills.getId();
                        break;

                    case 13:
                        lv13 |= l1skills.getId();
                        break;

                    case 14:
                        lv14 |= l1skills.getId();
                        break;

                    case 15:
                        lv15 |= l1skills.getId();
                        break;

                    case 16:
                        lv16 |= l1skills.getId();
                        break;

                    case 17:
                        lv17 |= l1skills.getId();
                        break;

                    case 18:
                        lv18 |= l1skills.getId();
                        break;

                    case 19:
                        lv19 |= l1skills.getId();
                        break;

                    case 20:
                        lv20 |= l1skills.getId();
                        break;

                    case 21:
                        lv21 |= l1skills.getId();
                        break;

                    case 22:
                        lv22 |= l1skills.getId();
                        break;

                    case 23:
                        lv23 |= l1skills.getId();
                        break;

                    case 24:
                        lv24 |= l1skills.getId();
                        break;

                    case 25:
                        lv25 |= l1skills.getId();
                        break;

                    case 26:
                        lv26 |= l1skills.getId();
                        break;

                    case 27:
                        lv27 |= l1skills.getId();
                        break;

                    case 28:
                        lv28 |= l1skills.getId();
                        break;
                }
                i = lv1 + lv2 + lv3 + lv4 + lv5 + lv6 + lv7 + lv8 + lv9 + lv10
                        + lv11 + lv12 + lv13 + lv14 + lv15 + lv16 + lv17 + lv18
                        + lv19 + lv20 + lv21 + lv22 + lv23 + lv24 + lv25 + lv26
                        + lv27 + lv28;
                pc.setSkillMastery(skillId);
            }

            // 将魔法插入技能栏
            if (i > 0) {
                pc.sendPackets(new S_AddSkill(lv1, lv2, lv3, lv4, lv5, lv6,
                        lv7, lv8, lv9, lv10, lv11, lv12, lv13, lv14, lv15,
                        lv16, lv17, lv18, lv19, lv20, lv21, lv22, lv23, lv24,
                        lv25, lv26, lv27, lv28));
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }
}
