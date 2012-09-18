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
package com.lineage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.utils.IntRange;

/**
 * 伺服器配置器
 */
public final class Config {

    /** 提示信息 */
    private static final Logger _log = Logger.getLogger(Config.class.getName());

    /** 调试/侦错模式 */
    public static final boolean DEBUG = false;

    // 线程设定
    /**  */
    public static int THREAD_P_EFFECTS;

    /**  */
    public static int THREAD_P_GENERAL;
    /**  */
    public static int AI_MAX_THREAD;
    /**  */
    public static int THREAD_P_TYPE_GENERAL;
    /**  */
    public static int THREAD_P_SIZE_GENERAL;
    // -----------------------------------------------------------------------------
    // 游戏伺服器相关 /** Server Settings */
    // -----------------------------------------------------------------------------
    /** 伺服器 listen 的 host name */
    public static String GAME_SERVER_HOST_NAME;

    /** 伺服器 listen 的 Port */
    public static int GAME_SERVER_PORT;
    /** 数据库驱动程序 */
    public static String DB_DRIVER;
    /** 数据库路径 */
    public static String DB_URL;
    /** 数据库账号 */
    public static String DB_LOGIN;
    /** 数据库密码 */
    public static String DB_PASSWORD;
    /** 时区设定 */
    public static String TIME_ZONE;
    /** 客户端语系 (1=KR 2=US 3=TW 4=JP 5=CN) */
    public static int CLIENT_LANGUAGE;
    /** 客户端编码 */
    public static String CLIENT_LANGUAGE_CODE;
    /** 编码的清单 */
    public static String[] LANGUAGE_CODE_ARRAY = { "UTF8", "EUCKR", "UTF8",
            "BIG5", "SJIS", "GBK" };
    /** DNS 反向验证 */
    public static boolean HOSTNAME_LOOKUPS;
    /** 客户端无动作时自动断线时间 */
    public static int AUTOMATIC_KICK;
    /** 自动创建帐号 */
    public static boolean AUTO_CREATE_ACCOUNTS;
    /** 最高在线玩家数量 */
    public static short MAX_ONLINE_USERS;
    /** 生成地图快取档案 */
    public static boolean CACHE_MAP_FILES;
    /** V2 地图 (测试用) */
    public static boolean LOAD_V2_MAP_FILES;
    /** 加速器侦测 (移动间隔) */
    public static boolean CHECK_MOVE_INTERVAL;
    /** 加速器侦测 (攻击间隔) */
    public static boolean CHECK_ATTACK_INTERVAL;
    /** 加速器侦测 (技能使用间隔) */
    public static boolean CHECK_SPELL_INTERVAL;
    /** 设定不正常封包数值,满足条件则切断连线 */
    public static short INJUSTICE_COUNT;
    /** 设定如果参杂正常封包在不正常封包中,数值满足时 InjusticeCount归 0 */
    public static int JUSTICE_COUNT;
    /** 加速器检查严密度 */
    public static int CHECK_STRICTNESS;
    /** 加速处罚机制 */
    public static int ILLEGAL_SPEEDUP_PUNISHMENT;
    /** 伺服器自动存档时间间隔 (单位: 秒) */
    public static int AUTOSAVE_INTERVAL;
    /** 定时自动储存角色装备资料时间间隔 (单位: 秒) */
    public static int AUTOSAVE_INTERVAL_INVENTORY;
    /** 技能计数器实施类型 */
    public static int SKILLTIMER_IMPLTYPE;
    /** NpcAI的实施类型 */
    public static int NPCAI_IMPLTYPE;
    /** 远程登录控制伺服器 */
    public static boolean TELNET_SERVER;
    /** 远程控制的Port号码 */
    public static int TELNET_SERVER_PORT;
    /** 发送到一个范围的信息给客户端对像 */
    public static int PC_RECOGNIZE_RANGE;
    /** 人物资讯统一管理(F5~12快捷键和人物血条位置等) */
    public static boolean CHARACTER_CONFIG_IN_SERVER_SIDE;
    /** 双开(同IP同时连线) */
    public static boolean ALLOW_2PC;
    /** 允许降等的水平范围（检测死亡降等范围） */
    public static int LEVEL_DOWN_RANGE;
    /** 瞬移控制 */
    public static boolean SEND_PACKET_BEFORE_TELEPORT;
    /** 数据库资源泄漏检测 */
    public static boolean DETECT_DB_RESOURCE_LEAKS;
    /** CMD互动指令 */
    public static boolean CmdActive;
    /** 循环时间设置 (单位:分钟) */
    public static int Announcements_Cycle_Time;
    /** 自动显示公告修改时间 */
    public static boolean Announcements_Cycle_Modify_Time;
    // -----------------------------------------------------------------------------
    // 游戏倍率相关 /** Rates Settings */
    // -----------------------------------------------------------------------------
    /** 经验值倍率 */
    public static double RATE_XP;

    /** 正义值倍率 */
    public static double RATE_LA;
    /** 友好度倍率 */
    public static double RATE_KARMA;
    /** 掉落金钱倍率 */
    public static double RATE_DROP_ADENA;
    /** 掉落物品倍率 */
    public static double RATE_DROP_ITEMS;
    /** 冲武器成功率 */
    public static int ENCHANT_CHANCE_WEAPON;
    /** 冲防具成功率 */
    public static int ENCHANT_CHANCE_ARMOR;
    /** 属性强化成功率 */
    public static int ATTR_ENCHANT_CHANCE;
    /** 角色负重倍率 */
    public static double RATE_WEIGHT_LIMIT;
    /** 宠物负重倍率 */
    public static double RATE_WEIGHT_LIMIT_PET;
    /** 商店贩卖价格倍率 */
    public static double RATE_SHOP_SELLING_PRICE;
    /** 商店收购价格倍率 */
    public static double RATE_SHOP_PURCHASING_PRICE;
    /** 航海日志合成几率 */
    public static int CREATE_CHANCE_DIARY;
    /** 净化的部分 */
    public static int CREATE_CHANCE_RECOLLECTION;
    /** 神秘药水 */
    public static int CREATE_CHANCE_MYSTERIOUS;
    /** 被加工了的宝石 */
    public static int CREATE_CHANCE_PROCESSING;
    /** 被加工了的钻石 */
    public static int CREATE_CHANCE_PROCESSING_DIAMOND;
    /** 完整的召唤球 */
    public static int CREATE_CHANCE_DANTES;
    /** 不起眼的古老项练 */
    public static int CREATE_CHANCE_ANCIENT_AMULET;
    /** 封印的历史书 */
    public static int CREATE_CHANCE_HISTORY_BOOK;
    /** 附魔石类型 */
    public static int MAGIC_STONE_TYPE;
    /** 附魔石阶级 */
    public static int MAGIC_STONE_LEVEL;
    // -----------------------------------------------------------------------------
    // 游戏进阶相关 /** AltSettings Settings */
    // -----------------------------------------------------------------------------
    /** 全体聊天最低等级限制 */
    public static short GLOBAL_CHAT_LEVEL;

    /** 密语最低等级限制 */
    public static short WHISPER_CHAT_LEVEL;
    /** 自动取得道具的方式 */
    public static byte AUTO_LOOT;
    /** 道具掉落的范围大小 */
    public static int LOOTING_RANGE;
    /** Non-PvP设定 */
    public static boolean ALT_NONPVP;
    /** GM是否显示伤害讯息 */
    public static boolean ALT_ATKMSG;
    /** 自己更改称号 */
    public static boolean CHANGE_TITLE_BY_ONESELF;
    /** 血盟人数上限 */
    public static int MAX_CLAN_MEMBER;
    /** 血盟联盟系统 */
    public static boolean CLAN_ALLIANCE;
    /** 组队人数上限 */
    public static int MAX_PT;
    /** 组队聊天人数上限 */
    public static int MAX_CHAT_PT;
    /** 攻城战中红人死亡后是否会受到处罚 */
    public static boolean SIM_WAR_PENALTY;
    /** 重新登入时是否在出生地 */
    public static boolean GET_BACK;
    /** 地图上地面道具删除设置 */
    public static String ALT_ITEM_DELETION_TYPE;
    /** 物品在地面自动清除掉的时间 */
    public static int ALT_ITEM_DELETION_TIME;
    /** 人物周围不清除物品范围大小 */
    public static int ALT_ITEM_DELETION_RANGE;
    /** 是否开启GM商店 */
    public static boolean ALT_GMSHOP;
    /** GM商店编号最小值 */
    public static int ALT_GMSHOP_MIN_ID;
    /** GM商店编号最大值 */
    public static int ALT_GMSHOP_MAX_ID;
    /** 南瓜怪任务开关 */
    public static boolean ALT_HALLOWEENIVENT;
    /** 日本特典道具NPC开关 */
    public static boolean ALT_JPPRIVILEGED;
    /** 说话卷轴任务开关 */
    public static boolean ALT_TALKINGSCROLLQUEST;
    /** /who 指令是否可以使用 */
    public static boolean ALT_WHO_COMMAND;
    /** 99级是否可以获得返生药水 */
    public static boolean ALT_REVIVAL_POTION;
    /** 攻城战时间 */
    public static int ALT_WAR_TIME;
    /** 攻城战时间单位 */
    public static int ALT_WAR_TIME_UNIT;
    /** 攻城日的间隔 */
    public static int ALT_WAR_INTERVAL;
    /** 攻城日的间隔单位 */
    public static int ALT_WAR_INTERVAL_UNIT;
    /** 城堡纳税倍率 */
    public static int ALT_RATE_OF_DUTY;
    /** 范围性怪物刷新 */
    public static boolean SPAWN_HOME_POINT;
    /** 怪物刷新的范围大小 */
    public static int SPAWN_HOME_POINT_RANGE;
    /** 怪物出生点设定最小 */
    public static int SPAWN_HOME_POINT_COUNT;
    /** 怪物出生点设定的最大 */
    public static int SPAWN_HOME_POINT_DELAY;
    /** 服务器启动时Boss是否出现 */
    public static boolean INIT_BOSS_SPAWN;
    /** 妖精森林 元素石 的数量 */
    public static int ELEMENTAL_STONE_AMOUNT;
    /** 盟屋税金的支付期限(日) */
    public static int HOUSE_TAX_INTERVAL;
    /** 魔法娃娃召唤数量上限 */
    public static int MAX_DOLL_COUNT;
    /** 释放元素技能的使用 */
    public static boolean RETURN_TO_NATURE;
    /** NPC(召唤, 宠物)身上可以持有的最大物品数量 */
    public static int MAX_NPC_ITEM;
    /** 个人仓库物品上限数量 */
    public static int MAX_PERSONAL_WAREHOUSE_ITEM;
    /** 血盟仓库物品上限数量 */
    public static int MAX_CLAN_WAREHOUSE_ITEM;
    /** 角色等级30以上，删除角色是否要等待7天 */
    public static boolean DELETE_CHARACTER_AFTER_7DAYS;
    /** NPC死亡后尸体消失时间（秒） */
    public static int NPC_DELETION_TIME;
    /** 预设角色数量 */
    public static int DEFAULT_CHARACTER_SLOT;
    /** 妖精森林NPC道具重置时间 */
    public static int GDROPITEM_TIME;
    // -----------------------------------------------------------------------------
    // 游戏角色相关 /** Character Settings */
    // -----------------------------------------------------------------------------
    /** 王族 HP 上限 */
    public static int PRINCE_MAX_HP;

    /** 王族 MP 上限 */
    public static int PRINCE_MAX_MP;
    /** 骑士 HP 上限 */
    public static int KNIGHT_MAX_HP;
    /** 骑士 MP 上限 */
    public static int KNIGHT_MAX_MP;
    /** 精灵 HP 上限 */
    public static int ELF_MAX_HP;
    /** 精灵 MP 上限 */
    public static int ELF_MAX_MP;
    /** 法师 HP 上限 */
    public static int WIZARD_MAX_HP;
    /** 法师 MP 上限 */
    public static int WIZARD_MAX_MP;
    /** 黑暗精灵 HP 上限 */
    public static int DARKELF_MAX_HP;
    /** 黑暗精灵 MP 上限 */
    public static int DARKELF_MAX_MP;
    /** 龙骑士 HP 上限 */
    public static int DRAGONKNIGHT_MAX_HP;
    /** 龙骑士 MP 上限 */
    public static int DRAGONKNIGHT_MAX_MP;
    /** 幻术师 HP 上限 */
    public static int ILLUSIONIST_MAX_HP;
    /** 幻术师 MP 上限 */
    public static int ILLUSIONIST_MAX_MP;
    public static int LV50_EXP;

    public static int LV51_EXP;

    public static int LV52_EXP;

    public static int LV53_EXP;

    public static int LV54_EXP;

    public static int LV55_EXP;

    public static int LV56_EXP;

    public static int LV57_EXP;

    public static int LV58_EXP;

    public static int LV59_EXP;

    public static int LV60_EXP;

    public static int LV61_EXP;

    public static int LV62_EXP;

    public static int LV63_EXP;

    public static int LV64_EXP;

    public static int LV65_EXP;

    public static int LV66_EXP;

    public static int LV67_EXP;

    public static int LV68_EXP;

    public static int LV69_EXP;

    public static int LV70_EXP;

    public static int LV71_EXP;

    public static int LV72_EXP;

    public static int LV73_EXP;

    public static int LV74_EXP;

    public static int LV75_EXP;

    public static int LV76_EXP;

    public static int LV77_EXP;

    public static int LV78_EXP;

    public static int LV79_EXP;

    public static int LV80_EXP;

    public static int LV81_EXP;

    public static int LV82_EXP;

    public static int LV83_EXP;

    public static int LV84_EXP;

    public static int LV85_EXP;

    public static int LV86_EXP;

    public static int LV87_EXP;

    public static int LV88_EXP;

    public static int LV89_EXP;

    public static int LV90_EXP;

    public static int LV91_EXP;

    public static int LV92_EXP;

    public static int LV93_EXP;

    public static int LV94_EXP;

    public static int LV95_EXP;

    public static int LV96_EXP;

    public static int LV97_EXP;

    public static int LV98_EXP;

    public static int LV99_EXP;

    public static int LV100_EXP;

    public static int LV101_EXP;

    public static int LV102_EXP;

    public static int LV103_EXP;

    public static int LV104_EXP;

    public static int LV105_EXP;

    public static int LV106_EXP;

    public static int LV107_EXP;

    public static int LV108_EXP;

    public static int LV109_EXP;

    public static int LV110_EXP;

	// TODO 伺服器捆绑
	public static boolean LOGINS_TO_AUTOENTICATION;
	public static String RSA_KEY_E;
	public static String RSA_KEY_N;
	// 伺服器捆绑
    // -----------------------------------------------------------------------------
    // 游戏战斗特化相关 /** Fights Settings */
    // -----------------------------------------------------------------------------
    /** 启动战斗特化系统 */
    public static boolean FIGHT_IS_ACTIVE;

    /** 新手保护系统(遭遇的守护) */
    public static boolean NOVICE_PROTECTION_IS_ACTIVE;
    /** 被归类为新手的等级上限 */
    public static int NOVICE_MAX_LEVEL;
    /** 启动新手保护机制 */
    public static int NOVICE_PROTECTION_LEVEL_RANGE;
    // -----------------------------------------------------------------------------
    // 游戏记录相关 /** Record Settings */
    // -----------------------------------------------------------------------------
    /** 武器强化 */
    public static byte LOGGING_WEAPON_ENCHANT;

    /** 防具强化 */
    public static byte LOGGING_ARMOR_ENCHANT;
    /** 一般频道 */
    public static boolean LOGGING_CHAT_NORMAL;
    /** 密语频道 */
    public static boolean LOGGING_CHAT_WHISPER;
    /** 大喊频道 */
    public static boolean LOGGING_CHAT_SHOUT;
    /** 广播频道 */
    public static boolean LOGGING_CHAT_WORLD;
    /** 血盟频道 */
    public static boolean LOGGING_CHAT_CLAN;
    /** 组队频道 */
    public static boolean LOGGING_CHAT_PARTY;
    /** 联盟频道 */
    public static boolean LOGGING_CHAT_COMBINED;
    /** 聊天队伍频道 */
    public static boolean LOGGING_CHAT_CHAT_PARTY;
    /** 交易纪录 */
    public static boolean writeTradeLog;
    /** 记录加速器讯息 */
    public static boolean writeRobotsLog;
    /** 丢弃物品纪录 */
    public static boolean writeDropLog;
    /** MySQL定时自动备份 */
    public static int MysqlAutoBackup;
    /** 备份的输出SQL是否启用GZip压缩 */
    public static boolean CompressGzip;
    // -----------------------------------------------------------------------------
    // 其他设置相关 /** OtherSettings Settings */
    // -----------------------------------------------------------------------------
    /** 是否新建角色即为GM */
    public static boolean NewCreateRoleSetGM;

    /** 是否显示NpcId */
    public static boolean ShowNpcId;
    /** 升级血魔满 */
    public static boolean LvUpHpMpFull;
    /** 伺服器重启时间 */
    public static int REST_TIME;
    /** 整点报时 */
    public static boolean HOURLY_CHIME;
    // 能力值上限调整原创 by 阿傑
    /** 能力值上限调整 */
    public static int BONUS_STATS1;
    /** 能力值上限调整 */
    public static int BONUS_STATS2;
    /** 能力值上限调整 */
    public static int BONUS_STATS3;
    // 配置文件
    // -----------------------------------------------------------------------------
    // 设定档路径 /** Configuration files */
    // -----------------------------------------------------------------------------
    /** 伺服器设定档路径 */
    public static final String SERVER_CONFIG_FILE = "./config/server.properties";
    /** 数据库设定档路径 */
    public static final String SQL_CONFIG_FILE = "./config/sql.properties";

    /** 倍率设定档路径 */
    public static final String RATES_CONFIG_FILE = "./config/rates.properties";
    /** 进阶设定档路径 */
    public static final String ALT_SETTINGS_FILE = "./config/altsettings.properties";
    /** 角色设定档路径 */
    public static final String CHAR_SETTINGS_CONFIG_FILE = "./config/charsettings.properties";
    /** 战斗特化设定档路径 */
    public static final String FIGHT_SETTINGS_CONFIG_FILE = "./config/fights.properties";
    /** 纪录设定档路径 */
    public static final String RECORD_SETTINGS_CONFIG_FILE = "./config/record.properties";
    /** 其他设定档路径 */
    public static final String OTHER_SETTINGS_CONFIG_FILE = "./config/othersettings.properties";
    /** 登陆器设定档路径 */
    public static final String LOGIN_SETTINGS_CONFIG_FILE = "./config/pack.properties";
    // -----------------------------------------------------------------------------
    // 其他设定 /** Other files */
    // -----------------------------------------------------------------------------
    /** 吸收每个 NPC 的 MP 上限 */
    public static final int MANA_DRAIN_LIMIT_PER_NPC = 40;

    /** 每一次攻击吸收的 MP 上限(玛那、钢铁玛那） */
    public static final int MANA_DRAIN_LIMIT_PER_SOM_ATTACK = 9;

    /**
     * 读取设定档中的设定
     */
    public static void load() {
        _log.info("正在读取游戏伺服器设定...");
        // server.properties
        try {
            final Properties serverSettings = new Properties();
            final InputStream is = new FileInputStream(new File(
                    SERVER_CONFIG_FILE));
            serverSettings.load(is);
            is.close();

            GAME_SERVER_HOST_NAME = serverSettings.getProperty(
                    "GameserverHostname", "*");
            GAME_SERVER_PORT = Integer.parseInt(serverSettings.getProperty(
                    "GameserverPort", "2000"));
            THREAD_P_TYPE_GENERAL = Integer.parseInt(
                    serverSettings.getProperty("GeneralThreadPoolType", "0"),
                    10);
            THREAD_P_SIZE_GENERAL = Integer.parseInt(
                    serverSettings.getProperty("GeneralThreadPoolSize", "0"),
                    10);
            CLIENT_LANGUAGE = Integer.parseInt(serverSettings.getProperty(
                    "ClientLanguage", "4"));
            CLIENT_LANGUAGE_CODE = LANGUAGE_CODE_ARRAY[CLIENT_LANGUAGE];
            TIME_ZONE = serverSettings.getProperty("TimeZone", "Asia/Taipei");
            HOSTNAME_LOOKUPS = Boolean.parseBoolean(serverSettings.getProperty(
                    "HostnameLookups", "false"));
            AUTOMATIC_KICK = Integer.parseInt(serverSettings.getProperty(
                    "AutomaticKick", "10"));
            AUTO_CREATE_ACCOUNTS = Boolean.parseBoolean(serverSettings
                    .getProperty("AutoCreateAccounts", "true"));
            MAX_ONLINE_USERS = Short.parseShort(serverSettings.getProperty(
                    "MaximumOnlineUsers", "30"));
            CACHE_MAP_FILES = Boolean.parseBoolean(serverSettings.getProperty(
                    "CacheMapFiles", "false"));
            LOAD_V2_MAP_FILES = Boolean.parseBoolean(serverSettings
                    .getProperty("LoadV2MapFiles", "false"));
            CHECK_MOVE_INTERVAL = Boolean.parseBoolean(serverSettings
                    .getProperty("CheckMoveInterval", "false"));
            CHECK_ATTACK_INTERVAL = Boolean.parseBoolean(serverSettings
                    .getProperty("CheckAttackInterval", "false"));
            CHECK_SPELL_INTERVAL = Boolean.parseBoolean(serverSettings
                    .getProperty("CheckSpellInterval", "false"));
            INJUSTICE_COUNT = Short.parseShort(serverSettings.getProperty(
                    "InjusticeCount", "10"));
            JUSTICE_COUNT = Integer.parseInt(serverSettings.getProperty(
                    "JusticeCount", "4"));
            CHECK_STRICTNESS = Integer.parseInt(serverSettings.getProperty(
                    "CheckStrictness", "102"));
            ILLEGAL_SPEEDUP_PUNISHMENT = Integer.parseInt(serverSettings
                    .getProperty("Punishment", "0"));
            AUTOSAVE_INTERVAL = Integer.parseInt(
                    serverSettings.getProperty("AutosaveInterval", "1200"), 10);
            AUTOSAVE_INTERVAL_INVENTORY = Integer.parseInt(serverSettings
                    .getProperty("AutosaveIntervalOfInventory", "300"), 10);
            SKILLTIMER_IMPLTYPE = Integer.parseInt(serverSettings.getProperty(
                    "SkillTimerImplType", "1"));
            NPCAI_IMPLTYPE = Integer.parseInt(serverSettings.getProperty(
                    "NpcAIImplType", "1"));
            TELNET_SERVER = Boolean.parseBoolean(serverSettings.getProperty(
                    "TelnetServer", "false"));
            TELNET_SERVER_PORT = Integer.parseInt(serverSettings.getProperty(
                    "TelnetServerPort", "23"));
            PC_RECOGNIZE_RANGE = Integer.parseInt(serverSettings.getProperty(
                    "PcRecognizeRange", "20"));
            CHARACTER_CONFIG_IN_SERVER_SIDE = Boolean
                    .parseBoolean(serverSettings.getProperty(
                            "CharacterConfigInServerSide", "true"));
            ALLOW_2PC = Boolean.parseBoolean(serverSettings.getProperty(
                    "Allow2PC", "true"));
            LEVEL_DOWN_RANGE = Integer.parseInt(serverSettings.getProperty(
                    "LevelDownRange", "0"));
            SEND_PACKET_BEFORE_TELEPORT = Boolean.parseBoolean(serverSettings
                    .getProperty("SendPacketBeforeTeleport", "false"));
            DETECT_DB_RESOURCE_LEAKS = Boolean.parseBoolean(serverSettings
                    .getProperty("EnableDatabaseResourceLeaksDetection",
                            "false"));
            CmdActive = Boolean.parseBoolean(serverSettings.getProperty(
                    "CmdActive", "false"));
            Announcements_Cycle_Time = Integer.parseInt(serverSettings
                    .getProperty("AnnouncementsCycleTime", "10"));
            Announcements_Cycle_Modify_Time = Boolean
                    .parseBoolean(serverSettings.getProperty(
                            "AnnounceTimeDisplay", "True"));
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new Error("无法读取设定档: " + SERVER_CONFIG_FILE + " File.");
        }

        _log.info("读取游戏数据库设定...");
        // sql.properties
        try {
            final Properties sqlSettings = new Properties();
            final InputStream is = new FileInputStream(
                    new File(SQL_CONFIG_FILE));
            sqlSettings.load(is);
            is.close();
            DB_DRIVER = sqlSettings.getProperty("Driver",
                    "com.mysql.jdbc.Driver");
            DB_URL = sqlSettings
                    .getProperty("URL",
                            "jdbc:mysql://localhost/l1jdb?useUnicode=true&characterEncoding=utf8");
            DB_LOGIN = sqlSettings.getProperty("Login", "root");
            DB_PASSWORD = sqlSettings.getProperty("Password", "");
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new Error("无法读取设定档: " + SQL_CONFIG_FILE + " File.");
        }

        _log.info("读取游戏倍率设定...");
        // rates.properties
        try {
            final Properties rateSettings = new Properties();
            final InputStream is = new FileInputStream(new File(
                    RATES_CONFIG_FILE));
            rateSettings.load(is);
            is.close();

            RATE_XP = Double.parseDouble(rateSettings.getProperty("RateXp",
                    "1.0"));
            RATE_LA = Double.parseDouble(rateSettings.getProperty("RateLawful",
                    "1.0"));
            RATE_KARMA = Double.parseDouble(rateSettings.getProperty(
                    "RateKarma", "1.0"));
            RATE_DROP_ADENA = Double.parseDouble(rateSettings.getProperty(
                    "RateDropAdena", "1.0"));
            RATE_DROP_ITEMS = Double.parseDouble(rateSettings.getProperty(
                    "RateDropItems", "1.0"));
            ENCHANT_CHANCE_WEAPON = Integer.parseInt(rateSettings.getProperty(
                    "EnchantChanceWeapon", "68"));
            ENCHANT_CHANCE_ARMOR = Integer.parseInt(rateSettings.getProperty(
                    "EnchantChanceArmor", "52"));
            ATTR_ENCHANT_CHANCE = Integer.parseInt(rateSettings.getProperty(
                    "AttrEnchantChance", "10"));
            RATE_WEIGHT_LIMIT = Double.parseDouble(rateSettings.getProperty(
                    "RateWeightLimit", "1"));
            RATE_WEIGHT_LIMIT_PET = Double.parseDouble(rateSettings
                    .getProperty("RateWeightLimitforPet", "1"));
            RATE_SHOP_SELLING_PRICE = Double.parseDouble(rateSettings
                    .getProperty("RateShopSellingPrice", "1.0"));
            RATE_SHOP_PURCHASING_PRICE = Double.parseDouble(rateSettings
                    .getProperty("RateShopPurchasingPrice", "1.0"));
            CREATE_CHANCE_DIARY = Integer.parseInt(rateSettings.getProperty(
                    "CreateChanceDiary", "33"));
            CREATE_CHANCE_RECOLLECTION = Integer.parseInt(rateSettings
                    .getProperty("CreateChanceRecollection", "90"));
            CREATE_CHANCE_MYSTERIOUS = Integer.parseInt(rateSettings
                    .getProperty("CreateChanceMysterious", "90"));
            CREATE_CHANCE_PROCESSING = Integer.parseInt(rateSettings
                    .getProperty("CreateChanceProcessing", "90"));
            CREATE_CHANCE_PROCESSING_DIAMOND = Integer.parseInt(rateSettings
                    .getProperty("CreateChanceProcessingDiamond", "90"));
            CREATE_CHANCE_DANTES = Integer.parseInt(rateSettings.getProperty(
                    "CreateChanceDantes", "50"));
            CREATE_CHANCE_ANCIENT_AMULET = Integer.parseInt(rateSettings
                    .getProperty("CreateChanceAncientAmulet", "90"));
            CREATE_CHANCE_HISTORY_BOOK = Integer.parseInt(rateSettings
                    .getProperty("CreateChanceHistoryBook", "50"));
            MAGIC_STONE_TYPE = Integer.parseInt(rateSettings.getProperty(
                    "MagicStoneAttr", "50"));
            MAGIC_STONE_LEVEL = Integer.parseInt(rateSettings.getProperty(
                    "MagicStoneLevel", "50"));
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new Error("无法读取设定档: " + RATES_CONFIG_FILE + " File.");
        }

        _log.info("读取游戏进阶设定...");
        // altsettings.properties
        try {
            final Properties altSettings = new Properties();
            final InputStream is = new FileInputStream(new File(
                    ALT_SETTINGS_FILE));
            altSettings.load(is);
            is.close();

            GLOBAL_CHAT_LEVEL = Short.parseShort(altSettings.getProperty(
                    "GlobalChatLevel", "30"));
            WHISPER_CHAT_LEVEL = Short.parseShort(altSettings.getProperty(
                    "WhisperChatLevel", "5"));
            AUTO_LOOT = Byte
                    .parseByte(altSettings.getProperty("AutoLoot", "2"));
            LOOTING_RANGE = Integer.parseInt(altSettings.getProperty(
                    "LootingRange", "3"));
            ALT_NONPVP = Boolean.parseBoolean(altSettings.getProperty("NonPvP",
                    "true"));
            ALT_ATKMSG = Boolean.parseBoolean(altSettings.getProperty(
                    "AttackMessageOn", "true"));
            CHANGE_TITLE_BY_ONESELF = Boolean.parseBoolean(altSettings
                    .getProperty("ChangeTitleByOneself", "false"));
            MAX_CLAN_MEMBER = Integer.parseInt(altSettings.getProperty(
                    "MaxClanMember", "0"));
            CLAN_ALLIANCE = Boolean.parseBoolean(altSettings.getProperty(
                    "ClanAlliance", "true"));
            MAX_PT = Integer.parseInt(altSettings.getProperty("MaxPT", "8"));
            MAX_CHAT_PT = Integer.parseInt(altSettings.getProperty("MaxChatPT",
                    "8"));
            SIM_WAR_PENALTY = Boolean.parseBoolean(altSettings.getProperty(
                    "SimWarPenalty", "true"));
            GET_BACK = Boolean.parseBoolean(altSettings.getProperty("GetBack",
                    "false"));
            ALT_ITEM_DELETION_TYPE = altSettings.getProperty(
                    "ItemDeletionType", "auto");
            ALT_ITEM_DELETION_TIME = Integer.parseInt(altSettings.getProperty(
                    "ItemDeletionTime", "10"));
            ALT_ITEM_DELETION_RANGE = Integer.parseInt(altSettings.getProperty(
                    "ItemDeletionRange", "5"));
            ALT_GMSHOP = Boolean.parseBoolean(altSettings.getProperty("GMshop",
                    "false"));
            ALT_GMSHOP_MIN_ID = Integer.parseInt(altSettings.getProperty(
                    "GMshopMinID", "0xffffffff")); // 设定错误时就取消GM商店
            ALT_GMSHOP_MAX_ID = Integer.parseInt(altSettings.getProperty(
                    "GMshopMaxID", "0xffffffff")); // 设定错误时就取消GM商店
            ALT_HALLOWEENIVENT = Boolean.parseBoolean(altSettings.getProperty(
                    "HalloweenIvent", "true"));
            ALT_JPPRIVILEGED = Boolean.parseBoolean(altSettings.getProperty(
                    "JpPrivileged", "false"));
            ALT_TALKINGSCROLLQUEST = Boolean.parseBoolean(altSettings
                    .getProperty("TalkingScrollQuest", "false"));
            ALT_WHO_COMMAND = Boolean.parseBoolean(altSettings.getProperty(
                    "WhoCommand", "false"));
            ALT_REVIVAL_POTION = Boolean.parseBoolean(altSettings.getProperty(
                    "RevivalPotion", "false"));
            GDROPITEM_TIME = Integer.parseInt(altSettings.getProperty(
                    "GDropItemTime", "10"));

            String strWar;
            strWar = altSettings.getProperty("WarTime", "2h");
            if (strWar.indexOf("d") >= 0) {
                ALT_WAR_TIME_UNIT = Calendar.DATE;
                strWar = strWar.replace("d", "");
            } else if (strWar.indexOf("h") >= 0) {
                ALT_WAR_TIME_UNIT = Calendar.HOUR_OF_DAY;
                strWar = strWar.replace("h", "");
            } else if (strWar.indexOf("m") >= 0) {
                ALT_WAR_TIME_UNIT = Calendar.MINUTE;
                strWar = strWar.replace("m", "");
            }
            ALT_WAR_TIME = Integer.parseInt(strWar);
            strWar = altSettings.getProperty("WarInterval", "4d");
            if (strWar.indexOf("d") >= 0) {
                ALT_WAR_INTERVAL_UNIT = Calendar.DATE;
                strWar = strWar.replace("d", "");
            } else if (strWar.indexOf("h") >= 0) {
                ALT_WAR_INTERVAL_UNIT = Calendar.HOUR_OF_DAY;
                strWar = strWar.replace("h", "");
            } else if (strWar.indexOf("m") >= 0) {
                ALT_WAR_INTERVAL_UNIT = Calendar.MINUTE;
                strWar = strWar.replace("m", "");
            }
            ALT_WAR_INTERVAL = Integer.parseInt(strWar);
            SPAWN_HOME_POINT = Boolean.parseBoolean(altSettings.getProperty(
                    "SpawnHomePoint", "true"));
            SPAWN_HOME_POINT_COUNT = Integer.parseInt(altSettings.getProperty(
                    "SpawnHomePointCount", "2"));
            SPAWN_HOME_POINT_DELAY = Integer.parseInt(altSettings.getProperty(
                    "SpawnHomePointDelay", "100"));
            SPAWN_HOME_POINT_RANGE = Integer.parseInt(altSettings.getProperty(
                    "SpawnHomePointRange", "8"));
            INIT_BOSS_SPAWN = Boolean.parseBoolean(altSettings.getProperty(
                    "InitBossSpawn", "true"));
            ELEMENTAL_STONE_AMOUNT = Integer.parseInt(altSettings.getProperty(
                    "ElementalStoneAmount", "300"));
            HOUSE_TAX_INTERVAL = Integer.parseInt(altSettings.getProperty(
                    "HouseTaxInterval", "10"));
            MAX_DOLL_COUNT = Integer.parseInt(altSettings.getProperty(
                    "MaxDollCount", "1"));
            RETURN_TO_NATURE = Boolean.parseBoolean(altSettings.getProperty(
                    "ReturnToNature", "false"));
            MAX_NPC_ITEM = Integer.parseInt(altSettings.getProperty(
                    "MaxNpcItem", "8"));
            MAX_PERSONAL_WAREHOUSE_ITEM = Integer.parseInt(altSettings
                    .getProperty("MaxPersonalWarehouseItem", "100"));
            MAX_CLAN_WAREHOUSE_ITEM = Integer.parseInt(altSettings.getProperty(
                    "MaxClanWarehouseItem", "200"));
            DELETE_CHARACTER_AFTER_7DAYS = Boolean.parseBoolean(altSettings
                    .getProperty("DeleteCharacterAfter7Days", "True"));
            NPC_DELETION_TIME = Integer.parseInt(altSettings.getProperty(
                    "NpcDeletionTime", "10"));
            DEFAULT_CHARACTER_SLOT = Integer.parseInt(altSettings.getProperty(
                    "DefaultCharacterSlot", "6"));
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new Error("无法读取设定档: " + ALT_SETTINGS_FILE + " File.");
        }

        _log.info("读取游戏角色设定...");
        // charsettings.properties
        try {
            final Properties charSettings = new Properties();
            final InputStream is = new FileInputStream(new File(
                    CHAR_SETTINGS_CONFIG_FILE));
            charSettings.load(is);
            is.close();

            PRINCE_MAX_HP = Integer.parseInt(charSettings.getProperty(
                    "PrinceMaxHP", "1000"));
            PRINCE_MAX_MP = Integer.parseInt(charSettings.getProperty(
                    "PrinceMaxMP", "800"));
            KNIGHT_MAX_HP = Integer.parseInt(charSettings.getProperty(
                    "KnightMaxHP", "1400"));
            KNIGHT_MAX_MP = Integer.parseInt(charSettings.getProperty(
                    "KnightMaxMP", "600"));
            ELF_MAX_HP = Integer.parseInt(charSettings.getProperty("ElfMaxHP",
                    "1000"));
            ELF_MAX_MP = Integer.parseInt(charSettings.getProperty("ElfMaxMP",
                    "900"));
            WIZARD_MAX_HP = Integer.parseInt(charSettings.getProperty(
                    "WizardMaxHP", "800"));
            WIZARD_MAX_MP = Integer.parseInt(charSettings.getProperty(
                    "WizardMaxMP", "1200"));
            DARKELF_MAX_HP = Integer.parseInt(charSettings.getProperty(
                    "DarkelfMaxHP", "1000"));
            DARKELF_MAX_MP = Integer.parseInt(charSettings.getProperty(
                    "DarkelfMaxMP", "900"));
            DRAGONKNIGHT_MAX_HP = Integer.parseInt(charSettings.getProperty(
                    "DragonKnightMaxHP", "1400"));
            DRAGONKNIGHT_MAX_MP = Integer.parseInt(charSettings.getProperty(
                    "DragonKnightMaxMP", "600"));
            ILLUSIONIST_MAX_HP = Integer.parseInt(charSettings.getProperty(
                    "IllusionistMaxHP", "900"));
            ILLUSIONIST_MAX_MP = Integer.parseInt(charSettings.getProperty(
                    "IllusionistMaxMP", "1100"));
            LV50_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv50Exp", "1"));
            LV51_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv51Exp", "1"));
            LV52_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv52Exp", "1"));
            LV53_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv53Exp", "1"));
            LV54_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv54Exp", "1"));
            LV55_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv55Exp", "1"));
            LV56_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv56Exp", "1"));
            LV57_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv57Exp", "1"));
            LV58_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv58Exp", "1"));
            LV59_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv59Exp", "1"));
            LV60_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv60Exp", "1"));
            LV61_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv61Exp", "1"));
            LV62_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv62Exp", "1"));
            LV63_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv63Exp", "1"));
            LV64_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv64Exp", "1"));
            LV65_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv65Exp", "2"));
            LV66_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv66Exp", "2"));
            LV67_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv67Exp", "2"));
            LV68_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv68Exp", "2"));
            LV69_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv69Exp", "2"));
            LV70_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv70Exp", "4"));
            LV71_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv71Exp", "4"));
            LV72_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv72Exp", "4"));
            LV73_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv73Exp", "4"));
            LV74_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv74Exp", "4"));
            LV75_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv75Exp", "8"));
            LV76_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv76Exp", "8"));
            LV77_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv77Exp", "8"));
            LV78_EXP = Integer.parseInt(charSettings
                    .getProperty("Lv78Exp", "8"));
            LV79_EXP = Integer.parseInt(charSettings.getProperty("Lv79Exp",
                    "16"));
            LV80_EXP = Integer.parseInt(charSettings.getProperty("Lv80Exp",
                    "32"));
            LV81_EXP = Integer.parseInt(charSettings.getProperty("Lv81Exp",
                    "64"));
            LV82_EXP = Integer.parseInt(charSettings.getProperty("Lv82Exp",
                    "128"));
            LV83_EXP = Integer.parseInt(charSettings.getProperty("Lv83Exp",
                    "256"));
            LV84_EXP = Integer.parseInt(charSettings.getProperty("Lv84Exp",
                    "512"));
            LV85_EXP = Integer.parseInt(charSettings.getProperty("Lv85Exp",
                    "1024"));
            LV86_EXP = Integer.parseInt(charSettings.getProperty("Lv86Exp",
                    "2048"));
            LV87_EXP = Integer.parseInt(charSettings.getProperty("Lv87Exp",
                    "4096"));
            LV88_EXP = Integer.parseInt(charSettings.getProperty("Lv88Exp",
                    "8192"));
            LV89_EXP = Integer.parseInt(charSettings.getProperty("Lv89Exp",
                    "16384"));
            LV90_EXP = Integer.parseInt(charSettings.getProperty("Lv90Exp",
                    "32768"));
            LV91_EXP = Integer.parseInt(charSettings.getProperty("Lv91Exp",
                    "65536"));
            LV92_EXP = Integer.parseInt(charSettings.getProperty("Lv92Exp",
                    "131072"));
            LV93_EXP = Integer.parseInt(charSettings.getProperty("Lv93Exp",
                    "262144"));
            LV94_EXP = Integer.parseInt(charSettings.getProperty("Lv94Exp",
                    "524288"));
            LV95_EXP = Integer.parseInt(charSettings.getProperty("Lv95Exp",
                    "1048576"));
            LV96_EXP = Integer.parseInt(charSettings.getProperty("Lv96Exp",
                    "2097152"));
            LV97_EXP = Integer.parseInt(charSettings.getProperty("Lv97Exp",
                    "4194304"));
            LV98_EXP = Integer.parseInt(charSettings.getProperty("Lv98Exp",
                    "8388608"));
            LV99_EXP = Integer.parseInt(charSettings.getProperty("Lv99Exp",
                    "16777216"));
            LV100_EXP = Integer.parseInt(charSettings.getProperty("Lv100Exp",
                    "16777216"));
            LV101_EXP = Integer.parseInt(charSettings.getProperty("Lv101Exp",
                    "65536"));
            LV102_EXP = Integer.parseInt(charSettings.getProperty("Lv102Exp",
                    "131072"));
            LV103_EXP = Integer.parseInt(charSettings.getProperty("Lv103Exp",
                    "262144"));
            LV104_EXP = Integer.parseInt(charSettings.getProperty("Lv104Exp",
                    "524288"));
            LV105_EXP = Integer.parseInt(charSettings.getProperty("Lv105Exp",
                    "1048576"));
            LV106_EXP = Integer.parseInt(charSettings.getProperty("Lv106Exp",
                    "2097152"));
            LV107_EXP = Integer.parseInt(charSettings.getProperty("Lv107Exp",
                    "4194304"));
            LV108_EXP = Integer.parseInt(charSettings.getProperty("Lv108Exp",
                    "8388608"));
            LV109_EXP = Integer.parseInt(charSettings.getProperty("Lv109Exp",
                    "16777216"));
            LV110_EXP = Integer.parseInt(charSettings.getProperty("Lv110Exp",
                    "16777216"));
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new Error("无法读取设定档: " + CHAR_SETTINGS_CONFIG_FILE + " File.");
        }

        _log.info("读取游戏战斗特化设定...");
        // fights.properties
        final Properties fightSettings = new Properties();
        try {
            final InputStream is = new FileInputStream(new File(
                    FIGHT_SETTINGS_CONFIG_FILE));
            fightSettings.load(is);
            is.close();

            FIGHT_IS_ACTIVE = Boolean.parseBoolean(fightSettings.getProperty(
                    "FightIsActive", "False"));
            NOVICE_PROTECTION_IS_ACTIVE = Boolean.parseBoolean(fightSettings
                    .getProperty("NoviceProtectionIsActive", "False"));
            NOVICE_MAX_LEVEL = Integer.parseInt(fightSettings.getProperty(
                    "NoviceMaxLevel", "20"));
            NOVICE_PROTECTION_LEVEL_RANGE = Integer.parseInt(fightSettings
                    .getProperty("ProtectionLevelRange", "10"));
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new Error("无法读取设定档: " + FIGHT_SETTINGS_CONFIG_FILE);
        }

        _log.info("读取游戏记录设定...");
        // record.properties
        try {
            final Properties recordSettings = new Properties();
            final InputStream is = new FileInputStream(new File(
                    RECORD_SETTINGS_CONFIG_FILE));
            recordSettings.load(is);
            is.close();

            LOGGING_WEAPON_ENCHANT = Byte.parseByte(recordSettings.getProperty(
                    "LoggingWeaponEnchant", "0"));
            LOGGING_ARMOR_ENCHANT = Byte.parseByte(recordSettings.getProperty(
                    "LoggingArmorEnchant", "0"));
            LOGGING_CHAT_NORMAL = Boolean.parseBoolean(recordSettings
                    .getProperty("LoggingChatNormal", "false"));
            LOGGING_CHAT_WHISPER = Boolean.parseBoolean(recordSettings
                    .getProperty("LoggingChatWhisper", "false"));
            LOGGING_CHAT_SHOUT = Boolean.parseBoolean(recordSettings
                    .getProperty("LoggingChatShout", "false"));
            LOGGING_CHAT_WORLD = Boolean.parseBoolean(recordSettings
                    .getProperty("LoggingChatWorld", "false"));
            LOGGING_CHAT_CLAN = Boolean.parseBoolean(recordSettings
                    .getProperty("LoggingChatClan", "false"));
            LOGGING_CHAT_PARTY = Boolean.parseBoolean(recordSettings
                    .getProperty("LoggingChatParty", "false"));
            LOGGING_CHAT_COMBINED = Boolean.parseBoolean(recordSettings
                    .getProperty("LoggingChatCombined", "false"));
            LOGGING_CHAT_CHAT_PARTY = Boolean.parseBoolean(recordSettings
                    .getProperty("LoggingChatChatParty", "false"));
            writeTradeLog = Boolean.parseBoolean(recordSettings.getProperty(
                    "writeTradeLog", "false"));
            writeRobotsLog = Boolean.parseBoolean(recordSettings.getProperty(
                    "writeRobotsLog", "false"));
            writeDropLog = Boolean.parseBoolean(recordSettings.getProperty(
                    "writeDropLog", "false"));
            MysqlAutoBackup = Integer.parseInt(recordSettings.getProperty(
                    "MysqlAutoBackup", "false"));
            CompressGzip = Boolean.parseBoolean(recordSettings.getProperty(
                    "CompressGzip", "false"));
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new Error("无法读取设定档: " + RECORD_SETTINGS_CONFIG_FILE);
        }

        _log.info("读取游戏其他设定...");
        // othersettings.properties
        try {
            final Properties otherSettings = new Properties();
            final InputStream is = new FileInputStream(new File(
                    OTHER_SETTINGS_CONFIG_FILE));
            otherSettings.load(is);
            is.close();

            NewCreateRoleSetGM = Boolean.parseBoolean(otherSettings
                    .getProperty("NewCreateRoleSetGM", "false"));
            ShowNpcId = Boolean.parseBoolean(otherSettings.getProperty(
                    "ShowNpcId", "false"));
            LvUpHpMpFull = Boolean.parseBoolean(otherSettings.getProperty(
                    "LvUpHpMpFull", "false"));
            REST_TIME = Integer.parseInt(otherSettings.getProperty(
                    "RestartTime", "240"));
            HOURLY_CHIME = Boolean.parseBoolean(otherSettings.getProperty(
                    "HourlyChime", "false"));
            // 能力值上限调整原创 by 阿傑
            BONUS_STATS1 = Integer.parseInt(otherSettings.getProperty(
                    "BONUS_STATS1", "35"));
            BONUS_STATS2 = Integer.parseInt(otherSettings.getProperty(
                    "BONUS_STATS2", "5"));
            BONUS_STATS3 = Integer.parseInt(otherSettings.getProperty(
                    "BONUS_STATS3", "35"));
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new Error("无法读取设定档: " + OTHER_SETTINGS_CONFIG_FILE);
        }
		// TODO 伺服器捆绑
		try {
			Properties packSettings = new Properties();
			InputStream is = new FileInputStream(new File(LOGIN_SETTINGS_CONFIG_FILE));
			packSettings.load(is);
			is.close();
			LOGINS_TO_AUTOENTICATION = Boolean.parseBoolean(packSettings
					.getProperty("Autoentication", "false"));
			RSA_KEY_E = packSettings.getProperty("RSA_KEY_E", "0");
			RSA_KEY_N = packSettings.getProperty("RSA_KEY_N", "0");
		} catch (Exception e) {
		}
		// 伺服器捆绑
        validate();
    }

    /**
     * 设置参数值
     * 
     * @param pName
     * @param pValue
     */
    public static boolean setParameterValue(final String pName,
            final String pValue) {
        // server.properties
        if (pName.equalsIgnoreCase("GameserverHostname")) {
            GAME_SERVER_HOST_NAME = pValue;
        } else if (pName.equalsIgnoreCase("GameserverPort")) {
            GAME_SERVER_PORT = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Driver")) {
            DB_DRIVER = pValue;
        } else if (pName.equalsIgnoreCase("URL")) {
            DB_URL = pValue;
        } else if (pName.equalsIgnoreCase("Login")) {
            DB_LOGIN = pValue;
        } else if (pName.equalsIgnoreCase("Password")) {
            DB_PASSWORD = pValue;
        } else if (pName.equalsIgnoreCase("ClientLanguage")) {
            CLIENT_LANGUAGE = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("TimeZone")) {
            TIME_ZONE = pValue;
        } else if (pName.equalsIgnoreCase("AutomaticKick")) {
            AUTOMATIC_KICK = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("AutoCreateAccounts")) {
            AUTO_CREATE_ACCOUNTS = Boolean.parseBoolean(pValue);
        } else if (pName.equalsIgnoreCase("MaximumOnlineUsers")) {
            MAX_ONLINE_USERS = Short.parseShort(pValue);
        } else if (pName.equalsIgnoreCase("CharacterConfigInServerSide")) {
            CHARACTER_CONFIG_IN_SERVER_SIDE = Boolean.parseBoolean(pValue);
        } else if (pName.equalsIgnoreCase("Allow2PC")) {
            ALLOW_2PC = Boolean.parseBoolean(pValue);
        } else if (pName.equalsIgnoreCase("LevelDownRange")) {
            LEVEL_DOWN_RANGE = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("SendPacketBeforeTeleport")) {
            SEND_PACKET_BEFORE_TELEPORT = Boolean.parseBoolean(pValue);
        } else if (pName.equalsIgnoreCase("Punishment")) {
            ILLEGAL_SPEEDUP_PUNISHMENT = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("AnnounceTimeDisplay")) {
            Announcements_Cycle_Modify_Time = Boolean.parseBoolean(pValue);
        }
        // rates.properties
        else if (pName.equalsIgnoreCase("RateXp")) {
            RATE_XP = Double.parseDouble(pValue);
        } else if (pName.equalsIgnoreCase("RateLawful")) {
            RATE_LA = Double.parseDouble(pValue);
        } else if (pName.equalsIgnoreCase("RateKarma")) {
            RATE_KARMA = Double.parseDouble(pValue);
        } else if (pName.equalsIgnoreCase("RateDropAdena")) {
            RATE_DROP_ADENA = Double.parseDouble(pValue);
        } else if (pName.equalsIgnoreCase("RateDropItems")) {
            RATE_DROP_ITEMS = Double.parseDouble(pValue);
        } else if (pName.equalsIgnoreCase("EnchantChanceWeapon")) {
            ENCHANT_CHANCE_WEAPON = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("EnchantChanceArmor")) {
            ENCHANT_CHANCE_ARMOR = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("AttrEnchantChance")) {
            ATTR_ENCHANT_CHANCE = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Weightrate")) {
            RATE_WEIGHT_LIMIT = Byte.parseByte(pValue);
        }
        // altsettings.properties
        else if (pName.equalsIgnoreCase("GlobalChatLevel")) {
            GLOBAL_CHAT_LEVEL = Short.parseShort(pValue);
        } else if (pName.equalsIgnoreCase("WhisperChatLevel")) {
            WHISPER_CHAT_LEVEL = Short.parseShort(pValue);
        } else if (pName.equalsIgnoreCase("AutoLoot")) {
            AUTO_LOOT = Byte.parseByte(pValue);
        } else if (pName.equalsIgnoreCase("LOOTING_RANGE")) {
            LOOTING_RANGE = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("AltNonPvP")) {
            ALT_NONPVP = Boolean.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("AttackMessageOn")) {
            ALT_ATKMSG = Boolean.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("ChangeTitleByOneself")) {
            CHANGE_TITLE_BY_ONESELF = Boolean.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("MaxClanMember")) {
            MAX_CLAN_MEMBER = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("ClanAlliance")) {
            CLAN_ALLIANCE = Boolean.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("MaxPT")) {
            MAX_PT = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("MaxChatPT")) {
            MAX_CHAT_PT = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("SimWarPenalty")) {
            SIM_WAR_PENALTY = Boolean.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("GetBack")) {
            GET_BACK = Boolean.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("AutomaticItemDeletionTime")) {
            ALT_ITEM_DELETION_TIME = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("AutomaticItemDeletionRange")) {
            ALT_ITEM_DELETION_RANGE = Byte.parseByte(pValue);
        } else if (pName.equalsIgnoreCase("GMshop")) {
            ALT_GMSHOP = Boolean.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("GMshopMinID")) {
            ALT_GMSHOP_MIN_ID = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("GMshopMaxID")) {
            ALT_GMSHOP_MAX_ID = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("HalloweenIvent")) {
            ALT_HALLOWEENIVENT = Boolean.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("JpPrivileged")) {
            ALT_JPPRIVILEGED = Boolean.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("TalkingScrollQuest")) {
            ALT_TALKINGSCROLLQUEST = Boolean.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("HouseTaxInterval")) {
            HOUSE_TAX_INTERVAL = Integer.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("MaxDollCount")) {
            MAX_DOLL_COUNT = Integer.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("ReturnToNature")) {
            RETURN_TO_NATURE = Boolean.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("MaxNpcItem")) {
            MAX_NPC_ITEM = Integer.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("MaxPersonalWarehouseItem")) {
            MAX_PERSONAL_WAREHOUSE_ITEM = Integer.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("MaxClanWarehouseItem")) {
            MAX_CLAN_WAREHOUSE_ITEM = Integer.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("DeleteCharacterAfter7Days")) {
            DELETE_CHARACTER_AFTER_7DAYS = Boolean.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("NpcDeletionTime")) {
            NPC_DELETION_TIME = Integer.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("DefaultCharacterSlot")) {
            DEFAULT_CHARACTER_SLOT = Integer.valueOf(pValue);
        } else if (pName.equalsIgnoreCase("GDropItemTime")) {
            GDROPITEM_TIME = Integer.parseInt(pValue);
        }

        // charsettings.properties
        else if (pName.equalsIgnoreCase("PrinceMaxHP")) {
            PRINCE_MAX_HP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("PrinceMaxMP")) {
            PRINCE_MAX_MP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("KnightMaxHP")) {
            KNIGHT_MAX_HP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("KnightMaxMP")) {
            KNIGHT_MAX_MP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("ElfMaxHP")) {
            ELF_MAX_HP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("ElfMaxMP")) {
            ELF_MAX_MP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("WizardMaxHP")) {
            WIZARD_MAX_HP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("WizardMaxMP")) {
            WIZARD_MAX_MP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("DarkelfMaxHP")) {
            DARKELF_MAX_HP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("DarkelfMaxMP")) {
            DARKELF_MAX_MP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("DragonKnightMaxHP")) {
            DRAGONKNIGHT_MAX_HP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("DragonKnightMaxMP")) {
            DRAGONKNIGHT_MAX_MP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("IllusionistMaxHP")) {
            ILLUSIONIST_MAX_HP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("IllusionistMaxMP")) {
            ILLUSIONIST_MAX_MP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv50Exp")) {
            LV50_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv51Exp")) {
            LV51_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv52Exp")) {
            LV52_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv53Exp")) {
            LV53_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv54Exp")) {
            LV54_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv55Exp")) {
            LV55_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv56Exp")) {
            LV56_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv57Exp")) {
            LV57_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv58Exp")) {
            LV58_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv59Exp")) {
            LV59_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv60Exp")) {
            LV60_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv61Exp")) {
            LV61_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv62Exp")) {
            LV62_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv63Exp")) {
            LV63_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv64Exp")) {
            LV64_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv65Exp")) {
            LV65_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv66Exp")) {
            LV66_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv67Exp")) {
            LV67_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv68Exp")) {
            LV68_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv69Exp")) {
            LV69_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv70Exp")) {
            LV70_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv71Exp")) {
            LV71_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv72Exp")) {
            LV72_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv73Exp")) {
            LV73_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv74Exp")) {
            LV74_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv75Exp")) {
            LV75_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv76Exp")) {
            LV76_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv77Exp")) {
            LV77_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv78Exp")) {
            LV78_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv79Exp")) {
            LV79_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv80Exp")) {
            LV80_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv81Exp")) {
            LV81_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv82Exp")) {
            LV82_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv83Exp")) {
            LV83_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv84Exp")) {
            LV84_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv85Exp")) {
            LV85_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv86Exp")) {
            LV86_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv87Exp")) {
            LV87_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv88Exp")) {
            LV88_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv89Exp")) {
            LV89_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv90Exp")) {
            LV90_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv91Exp")) {
            LV91_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv92Exp")) {
            LV92_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv93Exp")) {
            LV93_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv94Exp")) {
            LV94_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv95Exp")) {
            LV95_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv96Exp")) {
            LV96_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv97Exp")) {
            LV97_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv98Exp")) {
            LV98_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv99Exp")) {
            LV99_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv100Exp")) {
            LV100_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv101Exp")) {
            LV101_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv102Exp")) {
            LV102_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv103Exp")) {
            LV103_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv104Exp")) {
            LV104_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv105Exp")) {
            LV105_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv106Exp")) {
            LV106_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv107Exp")) {
            LV107_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv108Exp")) {
            LV108_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv109Exp")) {
            LV109_EXP = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("Lv110Exp")) {
            LV110_EXP = Integer.parseInt(pValue);
        }

        // record.properties
        else if (pName.equalsIgnoreCase("LoggingWeaponEnchant")) {
            LOGGING_WEAPON_ENCHANT = Byte.parseByte(pValue);
        } else if (pName.equalsIgnoreCase("LoggingArmorEnchant")) {
            LOGGING_ARMOR_ENCHANT = Byte.parseByte(pValue);
        }

        // othersettings.properties
        else if (pName.equalsIgnoreCase("RestartTime")) {
            REST_TIME = Integer.parseInt(pValue);
        }
        // 能力值上限调整原创 by 阿傑
        else if (pName.equalsIgnoreCase("BONUS_STATS1")) {
            BONUS_STATS1 = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("BONUS_STATS2")) {
            BONUS_STATS2 = Integer.parseInt(pValue);
        } else if (pName.equalsIgnoreCase("BONUS_STATS3")) {
            BONUS_STATS3 = Integer.parseInt(pValue);
        }

        else {
            return false;
        }
        return true;
    }

    // 验证
    private static void validate() {
        if (!IntRange.includes(Config.ALT_ITEM_DELETION_RANGE, 0, 5)) {
            throw new IllegalStateException(
                    "ItemDeletionRange 的设定值超出( 0 ~ 5 )范围。");
        }

        if (!IntRange.includes(Config.ALT_ITEM_DELETION_TIME, 1, 35791)) {
            throw new IllegalStateException(
                    "ItemDeletionTime 的设定值超出( 1 ~ 35791 )范围。");
        }
    }

    private Config() {
    }
}
