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
package l1j.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.server.utils.IntRange;

public final class Config {
	private static final Logger _log = Logger.getLogger(Config.class.getName());

	/** Debug/release mode */
	public static final boolean DEBUG = false;

	/** Thread pools size */
	public static int THREAD_P_EFFECTS;

	public static int THREAD_P_GENERAL;

	public static int AI_MAX_THREAD;

	public static int THREAD_P_TYPE_GENERAL;

	public static int THREAD_P_SIZE_GENERAL;
	
	/**bossRoom control*/
	/**最少人数*/
	public static int minPlayer;
	/**最多人数*/
	public static int maxPlayer;
	/**扣除物品*/
	public static int bossItem;
	/**扣除物品数量*/
	public static int itemCount;
	/**等待时间 80秒 + 倒数 = 总共90秒*/
	public static int readytime;
	/**清洁时间 五分钟*/
	public static int cleartime;
	/**BOSS1*/
	public static int bossId1;
	/**BOSS2*/
	public static int bossId2;
	/**BOSS3*/
	public static int bossId3;
	/**BOSS4*/
	public static int bossId4;
	/**BOSS5*/
	public static int bossId5;
	/**BOSS6*/
	public static int bossId6;
	/**BOSS7*/
	public static int bossId7;
	/**BOSS8*/
	public static int bossId8;
	/**BOSS9*/
	public static int bossId9;
	/**BOSS10*/
	public static int bossId10;
	
	/**tdTower control*/
	/**最少人数*/
	public static int minPlayerTD;
	/**最多人数*/
	public static int maxPlayerTD;
	/**扣除物品*/
	public static int bossItemTD;
	/**扣除物品数量*/
	public static int itemCountTD;
	/**奖励物品*/
	public static int bossItemTDReward;
	/**奖励物品数量*/
	public static int itemCountTDReward;
	/**等待时间 80秒 + 倒数 = 总共90秒*/
	public static int readytimeTD;
	/**清洁时间 五分钟*/
	public static int cleartimeTD;
	/**BOSS1*/
	public static int bossId1TD;
	/**BOSS2*/
	public static int bossId2TD;
	/**BOSS3*/
	public static int bossId3TD;
	/**BOSS4*/
	public static int bossId4TD;
	/**BOSS5*/
	public static int bossId5TD;
	/**BOSS6*/
	public static int bossId6TD;
	/**BOSS7*/
	public static int bossId7TD;
	/**BOSS8*/
	public static int bossId8TD;
	/**BOSS9*/
	public static int bossId9TD;
	/**BOSS10*/
	public static int bossId10TD;
	
	
	/** Server control */
	public static String GAME_SERVER_HOST_NAME;

	/** 连线线程 */
	public static int GAME_SERVER_PORT;
	/** 连线线程1 */
	public static int GAME_SERVER_PORT1;
	/** 连线线程2 */
	public static int GAME_SERVER_PORT2;
	/** 连线线程3 */
	public static int GAME_SERVER_PORT3;
	/** 连线线程4 */
	public static int GAME_SERVER_PORT4;
	/** 连线线程5 */
	public static int GAME_SERVER_PORT5;
	/** 连线线程6 */
	public static int GAME_SERVER_PORT6;
	/** 连线线程7 */
	public static int GAME_SERVER_PORT7;

	/** 连线线程开关 */
	public static boolean OpenPort;
	/** 连线线程开关1 */
	public static boolean OpenPort1;
	/** 连线线程开关2 */
	public static boolean OpenPort2;
	/** 连线线程开关3 */
	public static boolean OpenPort3;
	/** 连线线程开关4 */
	public static boolean OpenPort4;
	/** 连线线程开关5 */
	public static boolean OpenPort5;
	/** 连线线程开关6 */
	public static boolean OpenPort6;
	/** 连线线程开关7 */
	public static boolean OpenPort7;

	public static String DB_DRIVER;

	public static String DB_URL;

	public static String DB_LOGIN;

	public static String DB_PASSWORD;

	public static String TIME_ZONE;

	public static int CLIENT_LANGUAGE;

	public static String CLIENT_LANGUAGE_CODE;

	public static String[] LANGUAGE_CODE_ARRAY = { "UTF8", "EUCKR", "UTF8",
			"BIG5", "SJIS", "GBK" };

	public static boolean HOSTNAME_LOOKUPS;

	public static int AUTOMATIC_KICK;

	public static boolean AUTO_CREATE_ACCOUNTS;

	public static short MAX_ONLINE_USERS;

	public static boolean CACHE_MAP_FILES;

	public static boolean LOAD_V2_MAP_FILES;

	public static boolean CHECK_MOVE_INTERVAL;

	public static boolean CHECK_ATTACK_INTERVAL;

	public static boolean CHECK_SPELL_INTERVAL;

	public static short INJUSTICE_COUNT;

	public static int JUSTICE_COUNT;

	public static int CHECK_STRICTNESS;

	public static int ILLEGAL_SPEEDUP_PUNISHMENT;

	public static int AUTOSAVE_INTERVAL;

	public static int AUTOSAVE_INTERVAL_INVENTORY;

	public static int SKILLTIMER_IMPLTYPE;

	public static int NPCAI_IMPLTYPE;

	public static boolean TELNET_SERVER;

	public static int TELNET_SERVER_PORT;

	public static int PC_RECOGNIZE_RANGE;

	public static boolean CHARACTER_CONFIG_IN_SERVER_SIDE;

	public static boolean ALLOW_2PC;

	public static int LEVEL_DOWN_RANGE;

	public static boolean SEND_PACKET_BEFORE_TELEPORT;

	public static boolean DETECT_DB_RESOURCE_LEAKS;

	public static boolean CmdActive;

	public static int Announcements_Cycle_Time;

	public static boolean Announcements_Cycle_Modify_Time;
	
	public static boolean GUI; // 管理介面开关

	/** Rate control */
	public static double RATE_XP;

	public static double RATE_LA;

	public static double RATE_KARMA;

	public static double RATE_DROP_ADENA;

	public static double RATE_DROP_ITEMS;

	public static int ENCHANT_CHANCE_WEAPON;

	public static int ENCHANT_CHANCE_ARMOR;

	public static int ATTR_ENCHANT_CHANCE;

	public static double RATE_WEIGHT_LIMIT;

	public static double RATE_WEIGHT_LIMIT_PET;

	public static double RATE_SHOP_SELLING_PRICE;

	public static double RATE_SHOP_PURCHASING_PRICE;

	public static int CREATE_CHANCE_DIARY;

	public static int CREATE_CHANCE_RECOLLECTION;

	public static int CREATE_CHANCE_MYSTERIOUS;

	public static int CREATE_CHANCE_PROCESSING;

	public static int CREATE_CHANCE_PROCESSING_DIAMOND;

	public static int CREATE_CHANCE_DANTES;

	public static int CREATE_CHANCE_ANCIENT_AMULET;

	public static int CREATE_CHANCE_HISTORY_BOOK;

	public static int MAGIC_STONE_TYPE; // 附魔石类型

	public static int MAGIC_STONE_LEVEL; // 附魔石阶级

	/** AltSettings control */
	public static short GLOBAL_CHAT_LEVEL;

	public static short WHISPER_CHAT_LEVEL;

	public static byte AUTO_LOOT;

	public static int LOOTING_RANGE;

	public static boolean ALT_NONPVP;

	public static boolean ALT_ATKMSG;

	public static boolean CHANGE_TITLE_BY_ONESELF;

	public static int MAX_CLAN_MEMBER;

	public static boolean CLAN_ALLIANCE;

	public static int MAX_PT;

	public static int MAX_CHAT_PT;

	public static boolean SIM_WAR_PENALTY;

	public static boolean GET_BACK;

	public static String ALT_ITEM_DELETION_TYPE;

	public static int ALT_ITEM_DELETION_TIME;

	public static int ALT_ITEM_DELETION_RANGE;

	public static boolean ALT_GMSHOP;

	public static int ALT_GMSHOP_MIN_ID;

	public static int ALT_GMSHOP_MAX_ID;

	public static boolean ALT_HALLOWEENIVENT;

	public static boolean ALT_JPPRIVILEGED;

	public static boolean ALT_TALKINGSCROLLQUEST;

	public static boolean ALT_WHO_COMMAND;

	public static boolean ALT_REVIVAL_POTION;

	public static int ALT_WAR_TIME;

	public static int ALT_WAR_TIME_UNIT;

	public static int ALT_WAR_INTERVAL;

	public static int ALT_WAR_INTERVAL_UNIT;

	public static int ALT_RATE_OF_DUTY;

	public static boolean SPAWN_HOME_POINT;

	public static int SPAWN_HOME_POINT_RANGE;

	public static int SPAWN_HOME_POINT_COUNT;

	public static int SPAWN_HOME_POINT_DELAY;

	public static boolean INIT_BOSS_SPAWN;

	public static int ELEMENTAL_STONE_AMOUNT;

	public static int HOUSE_TAX_INTERVAL;

	public static int MAX_DOLL_COUNT;

	public static boolean RETURN_TO_NATURE;

	public static int MAX_NPC_ITEM;

	public static int MAX_PERSONAL_WAREHOUSE_ITEM;

	public static int MAX_CLAN_WAREHOUSE_ITEM;

	public static boolean DELETE_CHARACTER_AFTER_7DAYS;

	public static int NPC_DELETION_TIME;

	public static int DEFAULT_CHARACTER_SLOT;

	public static int GDROPITEM_TIME;

	/** CharSettings control */
	
	/**单项能力值上限 (力、敏、体、智、精、魅)*/
	public static int BONUS_DANXIANG;
	/**万能药上限 (总共可以喝多少瓶)*/
	public static int BONUS_MAX;
	/**单一能力点完 + 万能药，最后可以到达的素质*/
	public static int BONUS_DANXIANG_MAX;
	
	public static int PRINCE_MAX_HP;

	public static int PRINCE_MAX_MP;

	public static int KNIGHT_MAX_HP;

	public static int KNIGHT_MAX_MP;

	public static int ELF_MAX_HP;

	public static int ELF_MAX_MP;

	public static int WIZARD_MAX_HP;

	public static int WIZARD_MAX_MP;

	public static int DARKELF_MAX_HP;

	public static int DARKELF_MAX_MP;

	public static int DRAGONKNIGHT_MAX_HP;

	public static int DRAGONKNIGHT_MAX_MP;

	public static int ILLUSIONIST_MAX_HP;

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

	/** OtherSettings control */
	
	public static boolean GM_TALK; // ＧＭ使用公频(&)显示方式 1/5

	public static boolean WHO_ONLINE_MSG_ON; // 玩家上线是否通知在线ＧＭ 1/5

	public static boolean GM_Decrease_MP; // GM使用魔法是否扣魔 1/4

	public static int GASH_SHOP_MIN_ID; // GASH商城NPC编号最小值
	public static int GASH_SHOP_MAX_ID; // GASH商城NPC编号最大值
	public static int GASH_SHOP_ITEM_ID; // GASH商城专用货币的道具编号

	public static boolean FULL_HP_MP; // 玩家升级血魔是否补满

	public static boolean NEW_CREATE_BROADCAST; // 创新角色是否公告

	public static boolean Attack_Mob_HP_Bar; // 攻击显示怪物血条

	public static short Hpr_InHotel; // [旅馆]回血量
	public static short Mpr_InHotel; // [旅馆]回魔量

	public static short Hpr_InHouse; // [血盟小屋]回血量
	public static short Mpr_InHouse; // [血盟小屋]回魔量

	public static short Hpr_InCastle; // [城堡内城]回血量
	public static short Mpr_InCastle; // [城堡内城]回魔量

	public static short Hpr_UnderTheTree; // [妖森大树下]回血量
	public static short Mpr_UnderTheTree; // [妖森大树下]回魔量

	public static byte PET_MAX_LV; // 设定宠物最高等级

	public static double RATE_XP_PET; // 设定宠物经验值倍率

	public static boolean AUTO_ADD_SKILL; // 设定是否开启自动学习技能

	public static boolean LEVEL_UP_REWARD; // 设定是否开启升级奖励道具系统

	public static boolean MOVE_SEND_BONUSSTATS; // 设定是否开启免登出提示升级能力值点数

	public static boolean OnLine_Accumulate_Time; // 设定在线一段时间后是否给予道具
	public static short O_L_A_T_Time; // 累积时间
	public static int O_L_A_T_Item; // 发送道具编号

	public static boolean LEVEL_BONUS; // 设定是否开启经验值回馈奖励系统

	public static boolean LOGIN_GAME_DELAY; // 设定登入游戏是否给予延迟

	public static boolean NEW_CREATE_SET_GM; // 设定创新角色是否设定为GM

	public static boolean OL_ENQUIRE_RATES; // 是否开放线上查询伺服器配置资讯

	public static boolean WHO_ONLINE_LIST; // 是否可以查询线上所有名单 1/3
	
	public static boolean PP_Is_Active; // 设定是否使用指令/谁，显示幽灵人口机制
	public static short Phantom_Population; // 幽灵人口固定增加人数
	public static short Phantom_Population_Random; // 幽灵人口浮动人数
	
	/**最小掉落+几物品*/
	public static int Enchantlv_Min; 
	/**最大掉落+几物品*/
	public static int Enchantlv_Max; 
	
	
	/** 公频&买卖频道发话延迟 **/
	public static short Chat_Delay_Time;

	/** EinhasadSettings control */
	public static boolean EINHASAD_IS_ACTIVE; // 是否启动殷海萨的祝福系统

	public static short EIN_TIME;// 登入多久时间取得1%

	public static short EIN_OUTTIME;// 登出多久时间取得1%

	public static short EIN_MAX_PERCENT;// 最高百分比

	/** FightSettings control */
	public static boolean FIGHT_IS_ACTIVE;

	public static boolean NOVICE_PROTECTION_IS_ACTIVE;

	public static int NOVICE_MAX_LEVEL;

	public static int NOVICE_PROTECTION_LEVEL_RANGE;

	/** FameSystemSettings control */
	public static boolean FAME_IS_ACTIVE; // 声望系统
	
	public static short FAME_LV1_POINT;
	public static short FAME_LV2_POINT;
	public static short FAME_LV3_POINT;
	public static short FAME_LV4_POINT;
	public static short FAME_LV5_POINT;
	public static short FAME_LV6_POINT;
	public static short FAME_LV7_POINT;
	public static short FAME_LV8_POINT;
	public static short FAME_LV9_POINT;
	public static short FAME_LV10_POINT;
	public static short FAME_LV11_POINT;

	/** Record Settings */
	public static byte LOGGING_WEAPON_ENCHANT;

	public static byte LOGGING_ARMOR_ENCHANT;

	public static boolean LOGGING_CHAT_NORMAL;

	public static boolean LOGGING_CHAT_WHISPER;

	public static boolean LOGGING_CHAT_SHOUT;

	public static boolean LOGGING_CHAT_WORLD;

	public static boolean LOGGING_CHAT_CLAN;

	public static boolean LOGGING_CHAT_PARTY;

	public static boolean LOGGING_CHAT_COMBINED;

	public static boolean LOGGING_CHAT_CHAT_PARTY;

	public static boolean writeTradeLog;

	public static boolean writeRobotsLog;

	public static boolean writeDropLog;

	public static int MysqlAutoBackup;

	public static boolean CompressGzip;

	/** 登入器绑定功能 */
	public static boolean LOGINS_TO_AUTOENTICATION;

	public static String RSA_KEY_E;

	public static String RSA_KEY_N;

	/** Configuration files */
	public static final String SERVER_CONFIG_FILE = "./config/server.properties";

	public static final String RATES_CONFIG_FILE = "./config/rates.properties";

	public static final String ALT_SETTINGS_FILE = "./config/altsettings.properties";

	public static final String CHAR_SETTINGS_CONFIG_FILE = "./config/charsettings.properties";

	public static final String OTHER_SETTINGS_CONFIG_FILE = "./config/othersettings.properties";

	public static final String EINHASAD_SETTINGS_CONFIG_FILE = "./config/einhasad.properties";

	public static final String FIGHT_SETTINGS_CONFIG_FILE = "./config/fights.properties";
	
	public static final String RECORD_SETTINGS_CONFIG_FILE = "./config/record.properties";
	
	public static final String FAME_SETTINGS_CONFIG_FILE = "./config/famesystem.properties"; // 声望系统
	
	public static final String BOSSROOM = "./config/bossRoom.properties"; // boss馆
	
	public static final String TDTOWER = "./config/tdTower.properties"; // boss馆
	/** 其他设定 */

	// 吸收每个 NPC 的 MP 上限
	public static final int MANA_DRAIN_LIMIT_PER_NPC = 40;

	// 每一次攻击吸收的 MP 上限(马那、钢铁马那）
	public static final int MANA_DRAIN_LIMIT_PER_SOM_ATTACK = 9;

	public static void load() {
		_log.info("正在载入游戏伺服器相关设定...");
		// server.properties
		try {
			Properties serverSettings = new Properties();
			InputStream is = new FileInputStream(new File(SERVER_CONFIG_FILE));
			serverSettings.load(is);
			is.close();

			GAME_SERVER_HOST_NAME = serverSettings.getProperty(
					"GameserverHostname", "*");

			/** 连线线程 */
			GAME_SERVER_PORT = Integer.parseInt(serverSettings.getProperty(
					"GameserverPort", "2000"));
			GAME_SERVER_PORT1 = Integer.parseInt(serverSettings.getProperty(
					"GameserverPort1", "2000"));
			GAME_SERVER_PORT2 = Integer.parseInt(serverSettings.getProperty(
					"GameserverPort2", "2000"));
			GAME_SERVER_PORT3 = Integer.parseInt(serverSettings.getProperty(
					"GameserverPort3", "2000"));
			GAME_SERVER_PORT4 = Integer.parseInt(serverSettings.getProperty(
					"GameserverPort4", "2000"));
			GAME_SERVER_PORT5 = Integer.parseInt(serverSettings.getProperty(
					"GameserverPort5", "2000"));
			GAME_SERVER_PORT6 = Integer.parseInt(serverSettings.getProperty(
					"GameserverPort6", "2000"));
			GAME_SERVER_PORT7 = Integer.parseInt(serverSettings.getProperty(
					"GameserverPort7", "2000"));

			/** 连线线程开关 */
			OpenPort = Boolean.parseBoolean(serverSettings.getProperty(
					"OpenPort", "true"));
			OpenPort1 = Boolean.parseBoolean(serverSettings.getProperty(
					"OpenPort1", "false"));
			OpenPort2 = Boolean.parseBoolean(serverSettings.getProperty(
					"OpenPort2", "false"));
			OpenPort3 = Boolean.parseBoolean(serverSettings.getProperty(
					"OpenPort3", "false"));
			OpenPort4 = Boolean.parseBoolean(serverSettings.getProperty(
					"OpenPort4", "false"));
			OpenPort5 = Boolean.parseBoolean(serverSettings.getProperty(
					"OpenPort5", "false"));
			OpenPort6 = Boolean.parseBoolean(serverSettings.getProperty(
					"OpenPort6", "false"));
			OpenPort7 = Boolean.parseBoolean(serverSettings.getProperty(
					"OpenPort7", "false"));

			DB_DRIVER = serverSettings.getProperty("Driver",
					"com.mysql.jdbc.Driver");
			DB_URL = serverSettings
					.getProperty("URL",
							"jdbc:mysql://localhost/l1jdb?useUnicode=true&characterEncoding=utf8");
			DB_LOGIN = serverSettings.getProperty("Login", "root");
			DB_PASSWORD = serverSettings.getProperty("Password", "");
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
							"AnnounceTimeDisplay", "false"));
			GUI = Boolean.parseBoolean(serverSettings.getProperty(
					"GUI", "false")); // 管理介面开关
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("Failed to Load " + SERVER_CONFIG_FILE + " File.");
		}

		// rates.properties
		try {
			Properties rateSettings = new Properties();
			InputStream is = new FileInputStream(new File(RATES_CONFIG_FILE));
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
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("Failed to Load " + RATES_CONFIG_FILE + " File.");
		}

		// altsettings.properties
		try {
			Properties altSettings = new Properties();
			InputStream is = new FileInputStream(new File(ALT_SETTINGS_FILE));
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
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("Failed to Load " + ALT_SETTINGS_FILE + " File.");
		}

		// charsettings.properties
		try {
			Properties charSettings = new Properties();
			InputStream is = new FileInputStream(new File(
					CHAR_SETTINGS_CONFIG_FILE));
			charSettings.load(is);
			is.close();
			BONUS_DANXIANG = Integer.parseInt(charSettings.getProperty(
					"BONUS_DANXIANG", "35"));
			BONUS_MAX = Integer.parseInt(charSettings.getProperty(
					"BONUS_MAX", "35"));
			BONUS_DANXIANG_MAX = Integer.parseInt(charSettings.getProperty(
					"BONUS_DANXIANG_MAX", "50"));			
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
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("Failed to Load " + CHAR_SETTINGS_CONFIG_FILE
					+ " File.");
		}

		// othersettings.properties
		Properties otherSettings = new Properties();
		try {
			InputStream is = new FileInputStream(new File(
					OTHER_SETTINGS_CONFIG_FILE));
			otherSettings.load(is);
			is.close();

			GM_TALK = Boolean.parseBoolean(otherSettings.getProperty(
					"GMSpeakName", "false")); // ＧＭ使用公频(&)显示方式 2/5
			WHO_ONLINE_MSG_ON = Boolean.parseBoolean(otherSettings.getProperty(
					"WhoOnlineMessageOn", "false")); // 玩家上线是否通知在线ＧＭ 2/5
			GM_Decrease_MP = Boolean.parseBoolean(otherSettings.getProperty(
					"GMDecreaseMP", "false")); // GM使用魔法是否扣魔 2/4
			GASH_SHOP_MIN_ID = Integer.parseInt(otherSettings.getProperty(
					"GSshopMinID", "150000")); // GASH商城NPC编号最小值
			GASH_SHOP_MAX_ID = Integer.parseInt(otherSettings.getProperty(
					"GSshopMaxID", "150009")); // GASH商城NPC编号最大值
			GASH_SHOP_ITEM_ID = Integer.parseInt(otherSettings.getProperty(
					"GSItemID", "60000")); // GASH商城专用货币的道具编号
			FULL_HP_MP = Boolean.parseBoolean(otherSettings.getProperty(
					"FullHPMP", "false")); // 玩家升级血魔是否补满
			NEW_CREATE_BROADCAST = Boolean.parseBoolean(otherSettings
					.getProperty("NewCreateBroadcast", "false")); // 创新角色是否公告
			Attack_Mob_HP_Bar = Boolean.parseBoolean(otherSettings.getProperty(
					"AttackMobHPBar", "false")); // 攻击显示怪物血条
			Hpr_InHotel = Short.parseShort(otherSettings.getProperty(
					"HprInHotel", "5")); // [旅馆]回血量
			Mpr_InHotel = Short.parseShort(otherSettings.getProperty(
					"MprInHotel", "3")); // [旅馆]回魔量
			Hpr_InHouse = Short.parseShort(otherSettings.getProperty(
					"HprInHouse", "5")); // [血盟小屋]回血量
			Mpr_InHouse = Short.parseShort(otherSettings.getProperty(
					"MprInHouse", "3")); // [血盟小屋]回魔量
			Hpr_InCastle = Short.parseShort(otherSettings.getProperty(
					"HprInCastle", "5")); // [城堡内城]回血量
			Mpr_InCastle = Short.parseShort(otherSettings.getProperty(
					"MprInCastle", "3")); // [城堡内城]回魔量
			Hpr_UnderTheTree = Short.parseShort(otherSettings.getProperty(
					"HprUnderTheTree", "5")); // [妖森大树下]回血量
			Mpr_UnderTheTree = Short.parseShort(otherSettings.getProperty(
					"MprUnderTheTree", "3")); // [妖森大树下]回魔量
			PET_MAX_LV = Byte.parseByte(otherSettings.getProperty("PetMaxLV",
					"50")); // 设定宠物最高等级
			RATE_XP_PET = Double.parseDouble(otherSettings.getProperty(
					"PetRateXp", "1.0")); // 设定宠物经验值倍率
			AUTO_ADD_SKILL = Boolean.parseBoolean(otherSettings.getProperty(
					"AutoAddSkill", "false")); // 设定是否开启自动学习技能
			LEVEL_UP_REWARD = Boolean.parseBoolean(otherSettings.getProperty(
					"LevelUpReward", "false")); // 设定是否开启升级奖励道具系统
			MOVE_SEND_BONUSSTATS = Boolean.parseBoolean(otherSettings
					.getProperty("MoveSendBonusStats", "false")); // 设定是否开启免登出提示升级能力值点数
			OnLine_Accumulate_Time = Boolean.parseBoolean(otherSettings
					.getProperty("OnLineAccumulateTime", "false")); // 设定在线一段时间后是否给予道具
			O_L_A_T_Time = Short.parseShort(otherSettings.getProperty(
					"OLATTime", "3600")); // 累积时间
			O_L_A_T_Item = Integer.parseInt(otherSettings.getProperty(
					"OLATItem", "60000")); // 发送道具编号
			LEVEL_BONUS = Boolean.parseBoolean(otherSettings.getProperty(
					"LevelBonus", "false")); // 设定是否开启经验值回馈奖励系统
			LOGIN_GAME_DELAY = Boolean.parseBoolean(otherSettings.getProperty(
					"LoginGameDelay", "false")); // 设定登入游戏是否给予延迟
			NEW_CREATE_SET_GM = Boolean.parseBoolean(otherSettings.getProperty(
					"NewCreateSetGM", "false")); // 设定创新角色是否设定为GM
			OL_ENQUIRE_RATES = Boolean.parseBoolean(otherSettings.getProperty(
					"OnlineEnquireRates", "false")); // 是否开放线上查询伺服器配置资讯
			WHO_ONLINE_LIST = Boolean.parseBoolean(otherSettings.getProperty(
					"WhoOnlineList", "false")); // 是否可以查询线上所有名单 2/3
			PP_Is_Active = Boolean.parseBoolean(otherSettings
					.getProperty("PPIsActive", "false")); // 设定是否使用指令/谁，显示幽灵人口机制
			Phantom_Population = Short.parseShort(otherSettings.getProperty(
					"PhantomPopulation", "0")); // 幽灵人口固定增加人数
			Phantom_Population_Random = Short.parseShort(otherSettings.getProperty(
					"PhantomPopulationRandom", "0")); // 幽灵人口浮动人数
			Chat_Delay_Time = Short.parseShort(otherSettings.getProperty(
					"ChatDelayTime", "0")); // 公频&买卖频道发话延迟
			Enchantlv_Min = Integer.parseInt(otherSettings.getProperty("Enchantlv_Min",
					"0")); // 设定物品掉落最小加几
			Enchantlv_Max = Integer.parseInt(otherSettings.getProperty("Enchantlv_Max",
					"3")); // 设定物品掉落最大加几
			
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("无法读取设定档: " + OTHER_SETTINGS_CONFIG_FILE);
		}
		// bossRoom.properties
		Properties bossRoomSettings = new Properties();
		try {
			InputStream is = new FileInputStream(new File(
					BOSSROOM));
			bossRoomSettings.load(is);
			is.close();
			minPlayer = Integer.parseInt(bossRoomSettings.getProperty("minPlayer",
					"0")); 
			maxPlayer = Integer.parseInt(bossRoomSettings.getProperty("maxPlayer",
					"0")); 
			bossItem = Integer.parseInt(bossRoomSettings.getProperty("bossItem",
					"0")); 
			itemCount = Integer.parseInt(bossRoomSettings.getProperty("itemCount",
					"0")); 
			readytime = Integer.parseInt(bossRoomSettings.getProperty("readytime",
					"0")); 
			cleartime = Integer.parseInt(bossRoomSettings.getProperty("cleartime",
					"0")); 
			bossId1 = Integer.parseInt(bossRoomSettings.getProperty("bossId1",
					"0")); 
			bossId2 = Integer.parseInt(bossRoomSettings.getProperty("bossId2",
					"0")); 
			bossId3 = Integer.parseInt(bossRoomSettings.getProperty("bossId3",
					"0")); 
			bossId4 = Integer.parseInt(bossRoomSettings.getProperty("bossId4",
					"0")); 
			bossId5 = Integer.parseInt(bossRoomSettings.getProperty("bossId5",
					"0")); 
			bossId6 = Integer.parseInt(bossRoomSettings.getProperty("bossId6",
					"0")); 
			bossId7 = Integer.parseInt(bossRoomSettings.getProperty("bossId7",
					"0")); 
			bossId8 = Integer.parseInt(bossRoomSettings.getProperty("bossId8",
					"0")); 
			bossId9 = Integer.parseInt(bossRoomSettings.getProperty("bossId9",
					"0")); 
			bossId10 = Integer.parseInt(bossRoomSettings.getProperty("bossId10",
					"0")); 
			
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("无法读取设定档: " + BOSSROOM);
		}
		// tdTower.properties
		Properties tdTowerSettings = new Properties();
		try {
			InputStream is = new FileInputStream(new File(TDTOWER));
			bossRoomSettings.load(is);
			is.close();
			minPlayerTD = Integer.parseInt(bossRoomSettings.getProperty("minPlayerTD", "0"));
			maxPlayerTD = Integer.parseInt(bossRoomSettings.getProperty("maxPlayerTD", "0"));
			bossItemTD = Integer.parseInt(bossRoomSettings.getProperty("bossItemTD", "0"));
			itemCountTD = Integer.parseInt(bossRoomSettings.getProperty("itemCountTD", "0"));
			bossItemTDReward = Integer.parseInt(bossRoomSettings.getProperty("bossItemTDReward", "0"));
			itemCountTDReward = Integer.parseInt(bossRoomSettings.getProperty("itemCountTDReward", "0"));
			readytimeTD = Integer.parseInt(bossRoomSettings.getProperty("readytimeTD", "0"));
			cleartimeTD = Integer.parseInt(bossRoomSettings.getProperty("cleartimeTD", "0"));
			bossId1TD = Integer.parseInt(bossRoomSettings.getProperty("bossId1TD", "0"));
			bossId2TD = Integer.parseInt(bossRoomSettings.getProperty("bossId2TD", "0"));
			bossId3TD = Integer.parseInt(bossRoomSettings.getProperty("bossId3TD", "0"));
			bossId4TD = Integer.parseInt(bossRoomSettings.getProperty("bossId4TD", "0"));
			bossId5TD = Integer.parseInt(bossRoomSettings.getProperty("bossId5TD", "0"));
			bossId6TD = Integer.parseInt(bossRoomSettings.getProperty("bossId6TD", "0"));
			bossId7TD = Integer.parseInt(bossRoomSettings.getProperty("bossId7TD", "0"));
			bossId8TD = Integer.parseInt(bossRoomSettings.getProperty("bossId8TD", "0"));
			bossId9TD = Integer.parseInt(bossRoomSettings.getProperty("bossId9TD", "0"));
			bossId10TD = Integer.parseInt(bossRoomSettings.getProperty("bossId10TD", "0"));
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("无法读取设定档: " + TDTOWER);
		}
		// einhasad.properties
		Properties einhasadSettings = new Properties();
		try {
			InputStream is = new FileInputStream(new File(
					EINHASAD_SETTINGS_CONFIG_FILE));
			einhasadSettings.load(is);
			is.close();

			EINHASAD_IS_ACTIVE = Boolean.parseBoolean(einhasadSettings
					.getProperty("EinhasadIsActive", "false")); // 是否启动殷海萨的祝福系统
			EIN_TIME = Short.parseShort(einhasadSettings.getProperty("EinTime",
					"15")); // 登入多久时间取得1%
			EIN_OUTTIME = Short.parseShort(einhasadSettings.getProperty(
					"EinOutTime", "15")); // 登出多久时间取得1%
			EIN_MAX_PERCENT = Short.parseShort(einhasadSettings.getProperty(
					"EinMaxPercent", "200")); // 最高百分比
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("无法读取设定档: " + EINHASAD_SETTINGS_CONFIG_FILE);
		}

		// fights.properties
		Properties fightSettings = new Properties();
		try {
			InputStream is = new FileInputStream(new File(
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
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("无法读取设定档: " + FIGHT_SETTINGS_CONFIG_FILE);
		}

		// famesystem.properties
		Properties fameSettings = new Properties();
		try {
			InputStream is = new FileInputStream(new File(
					FAME_SETTINGS_CONFIG_FILE));
			fameSettings.load(is);
			is.close();

			FAME_IS_ACTIVE = Boolean.parseBoolean(fameSettings.getProperty(
					"FameIsActive", "False"));
			
			FAME_LV1_POINT = Short.parseShort(fameSettings.getProperty(
					"FameLV1Point", "10"));
			FAME_LV2_POINT = Short.parseShort(fameSettings.getProperty(
					"FameLV2Point", "30"));
			FAME_LV3_POINT = Short.parseShort(fameSettings.getProperty(
					"FameLV3Point", "50"));
			FAME_LV4_POINT = Short.parseShort(fameSettings.getProperty(
					"FameLV4Point", "70"));
			FAME_LV5_POINT = Short.parseShort(fameSettings.getProperty(
					"FameLV5Point", "90"));
			FAME_LV6_POINT = Short.parseShort(fameSettings.getProperty(
					"FameLV6Point", "110"));
			FAME_LV7_POINT = Short.parseShort(fameSettings.getProperty(
					"FameLV7Point", "130"));
			FAME_LV8_POINT = Short.parseShort(fameSettings.getProperty(
					"FameLV8Point", "150"));
			FAME_LV9_POINT = Short.parseShort(fameSettings.getProperty(
					"FameLV9Point", "170"));
			FAME_LV10_POINT = Short.parseShort(fameSettings.getProperty(
					"FameLV10Point", "190"));
			FAME_LV11_POINT = Short.parseShort(fameSettings.getProperty(
					"FameLV11Point", "200"));

		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("无法读取设定档: " + FAME_SETTINGS_CONFIG_FILE);
		}
		
		// record.properties
		try {
			Properties recordSettings = new Properties();
			InputStream is = new FileInputStream(new File(
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

		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			throw new Error("Failed to Load: " + RECORD_SETTINGS_CONFIG_FILE);
		}
		// pack.properties
		try {
			Properties packSettings = new Properties();
			InputStream is = new FileInputStream(new File(
					"./config/pack.properties"));
			packSettings.load(is);
			is.close();
			LOGINS_TO_AUTOENTICATION = Boolean.parseBoolean(packSettings
					.getProperty("Autoentication", "false"));
			RSA_KEY_E = packSettings.getProperty("RSA_KEY_E", "0");
			RSA_KEY_N = packSettings.getProperty("RSA_KEY_N", "0");
		} catch (Exception e) {
		}

		validate();
	}

	private static void validate() {
		if (!IntRange.includes(Config.ALT_ITEM_DELETION_RANGE, 0, 5)) {
			throw new IllegalStateException("ItemDeletionRange 的设定值超出范围。");
		}

		if (!IntRange.includes(Config.ALT_ITEM_DELETION_TIME, 1, 35791)) {
			throw new IllegalStateException("ItemDeletionTime 的设定值超出范围。");
		}
	}

	public static boolean setParameterValue(String pName, String pValue) {
		// server.properties
		if (pName.equalsIgnoreCase("GameserverHostname")) {
			GAME_SERVER_HOST_NAME = pValue;
		}
		/** 连线线程 */
		else if (pName.equalsIgnoreCase("GameserverPort")) {
			GAME_SERVER_PORT = Integer.parseInt(pValue);
		} else if (pName.equalsIgnoreCase("GameserverPort1")) {
			GAME_SERVER_PORT1 = Integer.parseInt(pValue);
		} else if (pName.equalsIgnoreCase("GameserverPort2")) {
			GAME_SERVER_PORT2 = Integer.parseInt(pValue);
		} else if (pName.equalsIgnoreCase("GameserverPort3")) {
			GAME_SERVER_PORT3 = Integer.parseInt(pValue);
		} else if (pName.equalsIgnoreCase("GameserverPort4")) {
			GAME_SERVER_PORT4 = Integer.parseInt(pValue);
		} else if (pName.equalsIgnoreCase("GameserverPort5")) {
			GAME_SERVER_PORT5 = Integer.parseInt(pValue);
		} else if (pName.equalsIgnoreCase("GameserverPort6")) {
			GAME_SERVER_PORT6 = Integer.parseInt(pValue);
		} else if (pName.equalsIgnoreCase("GameserverPort7")) {
			GAME_SERVER_PORT7 = Integer.parseInt(pValue);
		}
		/** 连线线程开关 */
		else if (pName.equalsIgnoreCase("OpenPort")) {
			OpenPort = Boolean.parseBoolean(pValue);
		} else if (pName.equalsIgnoreCase("OpenPort1")) {
			OpenPort1 = Boolean.parseBoolean(pValue);
		} else if (pName.equalsIgnoreCase("OpenPort2")) {
			OpenPort2 = Boolean.parseBoolean(pValue);
		} else if (pName.equalsIgnoreCase("OpenPort3")) {
			OpenPort3 = Boolean.parseBoolean(pValue);
		} else if (pName.equalsIgnoreCase("OpenPort4")) {
			OpenPort4 = Boolean.parseBoolean(pValue);
		} else if (pName.equalsIgnoreCase("OpenPort5")) {
			OpenPort5 = Boolean.parseBoolean(pValue);
		} else if (pName.equalsIgnoreCase("OpenPort6")) {
			OpenPort6 = Boolean.parseBoolean(pValue);
		} else if (pName.equalsIgnoreCase("OpenPort7")) {
			OpenPort7 = Boolean.parseBoolean(pValue);
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
		} else if (pName.equalsIgnoreCase("GUI")) {
			GUI = Boolean.parseBoolean(pValue); // 管理介面开关
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
		else if (pName.equalsIgnoreCase("BONUS_DANXIANG")) {
			BONUS_DANXIANG  = Integer.parseInt(pValue);	
		}else if (pName.equalsIgnoreCase("BONUS_MAX")) {
			BONUS_MAX = Integer.parseInt(pValue);
		}else if (pName.equalsIgnoreCase("BONUS_DANXIANG_MAX")) {
			BONUS_DANXIANG_MAX = Integer.parseInt(pValue);
    	}else if (pName.equalsIgnoreCase("PrinceMaxHP")) {
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
		}
		// othersettings.properties
		else if (pName.equalsIgnoreCase("GMSpeakName")) {
			GM_TALK = Boolean.valueOf(pValue); // ＧＭ使用公频(&)显示方式 3/5
		} else if (pName.equalsIgnoreCase("WhoOnlineMessageOn")) {
			WHO_ONLINE_MSG_ON = Boolean.valueOf(pValue); // 玩家上线是否通知在线ＧＭ 3/5
		} else if (pName.equalsIgnoreCase("GMDecreaseMP")) {
			GM_Decrease_MP = Boolean.valueOf(pValue); // GM使用魔法是否扣魔 3/4
		} else if (pName.equalsIgnoreCase("GSshopMinID")) {
			GASH_SHOP_MIN_ID = Integer.valueOf(pValue); // GASH商城NPC编号最小值
		} else if (pName.equalsIgnoreCase("GSshopMaxID")) {
			GASH_SHOP_MAX_ID = Integer.valueOf(pValue); // GASH商城NPC编号最大值
		} else if (pName.equalsIgnoreCase("GSItemID")) {
			GASH_SHOP_ITEM_ID = Integer.valueOf(pValue); // GASH商城专用货币的道具编号	
		} else if (pName.equalsIgnoreCase("FullHPMP")) {
			FULL_HP_MP = Boolean.valueOf(pValue); // 玩家升级血魔是否补满
		} else if (pName.equalsIgnoreCase("NewCreateBroadcast")) {
			NEW_CREATE_BROADCAST = Boolean.valueOf(pValue); // 创新角色是否公告
		} else if (pName.equalsIgnoreCase("AttackMobHPBar")) {
			Attack_Mob_HP_Bar = Boolean.valueOf(pValue); // 攻击显示怪物血条
		} else if (pName.equalsIgnoreCase("HprInHotel")) {
			Hpr_InHotel = Short.valueOf(pValue); // [旅馆]回血量
		} else if (pName.equalsIgnoreCase("MprInHotel")) {
			Mpr_InHotel = Short.valueOf(pValue); // [旅馆]回魔量
		} else if (pName.equalsIgnoreCase("HprInHouse")) {
			Hpr_InHouse = Short.valueOf(pValue); // [血盟小屋]回血量
		} else if (pName.equalsIgnoreCase("MprInHouse")) {
			Mpr_InHouse = Short.valueOf(pValue); // [血盟小屋]回魔量
		} else if (pName.equalsIgnoreCase("HprInCastle")) {
			Hpr_InCastle = Short.valueOf(pValue); // [城堡内城]回血量
		} else if (pName.equalsIgnoreCase("MprInCastle")) {
			Mpr_InCastle = Short.valueOf(pValue); // [城堡内城]回魔量
		} else if (pName.equalsIgnoreCase("HprUnderTheTree")) {
			Hpr_UnderTheTree = Short.valueOf(pValue); // [妖森大树下]回血量
		} else if (pName.equalsIgnoreCase("MprUnderTheTree")) {
			Mpr_UnderTheTree = Short.valueOf(pValue); // [妖森大树下]回魔量
		} else if (pName.equalsIgnoreCase("PetMaxLV")) {
			PET_MAX_LV = Byte.valueOf(pValue); // 设定宠物最高等级
		} else if (pName.equalsIgnoreCase("PetRateXp")) {
			RATE_XP_PET = Double.valueOf(pValue); // 设定宠物经验值倍率
		} else if (pName.equalsIgnoreCase("AutoAddSkill")) {
			AUTO_ADD_SKILL = Boolean.valueOf(pValue); // 设定是否开启自动学习技能
		} else if (pName.equalsIgnoreCase("LevelUpReward")) {
			LEVEL_UP_REWARD = Boolean.valueOf(pValue); // 设定是否开启升级奖励道具系统
		} else if (pName.equalsIgnoreCase("MoveSendBonusStats")) {
			MOVE_SEND_BONUSSTATS = Boolean.valueOf(pValue); // 设定是否开启免登出提示升级能力值点数
		} else if (pName.equalsIgnoreCase("OnLineAccumulateTime")) {
			OnLine_Accumulate_Time = Boolean.valueOf(pValue); // 设定在线一段时间后是否给予道具
		} else if (pName.equalsIgnoreCase("OLATTime")) {
			O_L_A_T_Time = Short.valueOf(pValue); // 累积时间
		} else if (pName.equalsIgnoreCase("OLATItem")) {
			O_L_A_T_Item = Short.valueOf(pValue); // 发送道具编号
		} else if (pName.equalsIgnoreCase("LevelBonus")) {
			LEVEL_BONUS = Boolean.valueOf(pValue); // 设定是否开启经验值回馈奖励系统
		} else if (pName.equalsIgnoreCase("LoginGameDelay")) {
			LOGIN_GAME_DELAY = Boolean.valueOf(pValue); // 设定登入游戏是否给予延迟
		} else if (pName.equalsIgnoreCase("NewCreateSetGM")) {
			NEW_CREATE_SET_GM = Boolean.valueOf(pValue); // 设定创新角色是否设定为GM
		} else if (pName.equalsIgnoreCase("OnlineEnquireRates")) {
			OL_ENQUIRE_RATES = Boolean.valueOf(pValue); // 是否开放线上查询伺服器配置资讯
		} else if (pName.equalsIgnoreCase("WhoOnlineList")) {
			WHO_ONLINE_LIST = Boolean.valueOf(pValue); // 是否可以查询线上所有名单 3/3
		} else if (pName.equalsIgnoreCase("PPIsActive")) {
			PP_Is_Active = Boolean.valueOf(pValue); // 设定是否使用指令/谁，显示幽灵人口机制
		} else if (pName.equalsIgnoreCase("PhantomPopulation")) {
			Phantom_Population = Short.valueOf(pValue); // 幽灵人口固定增加人数
		} else if (pName.equalsIgnoreCase("PhantomPopulationRandom")) {
			Phantom_Population_Random = Short.valueOf(pValue); //  幽灵人口浮动人数
		} else if (pName.equalsIgnoreCase("ChatDelayTime")) {
			Chat_Delay_Time = Short.valueOf(pValue); //  公频&买卖频道发话延迟
		} else if (pName.equalsIgnoreCase("Enchantlv_Min")) {
			Enchantlv_Min = Integer.valueOf(pValue); // GASH商城NPC编号最小值
		} else if (pName.equalsIgnoreCase("Enchantlv_Max")) {
			Enchantlv_Max = Integer.valueOf(pValue); // GASH商城NPC编号最小值
		}
		// famesystem.properties
		else if (pName.equalsIgnoreCase("FameIsActive")) {
			FAME_IS_ACTIVE = Boolean.valueOf(pValue);
		} else if (pName.equalsIgnoreCase("FameLV1Point")) {
			FAME_LV1_POINT = Short.valueOf(pValue);
		} else if (pName.equalsIgnoreCase("FameLV2Point")) {
			FAME_LV2_POINT = Short.valueOf(pValue);
		} else if (pName.equalsIgnoreCase("FameLV3Point")) {
			FAME_LV3_POINT = Short.valueOf(pValue);
		} else if (pName.equalsIgnoreCase("FameLV4Point")) {
			FAME_LV4_POINT = Short.valueOf(pValue);
		} else if (pName.equalsIgnoreCase("FameLV5Point")) {
			FAME_LV5_POINT = Short.valueOf(pValue);
		} else if (pName.equalsIgnoreCase("FameLV6Point")) {
			FAME_LV6_POINT = Short.valueOf(pValue);
		} else if (pName.equalsIgnoreCase("FameLV7Point")) {
			FAME_LV7_POINT = Short.valueOf(pValue);
		} else if (pName.equalsIgnoreCase("FameLV8Point")) {
			FAME_LV8_POINT = Short.valueOf(pValue);
		} else if (pName.equalsIgnoreCase("FameLV9Point")) {
			FAME_LV9_POINT = Short.valueOf(pValue);
		} else if (pName.equalsIgnoreCase("FameLV10Point")) {
			FAME_LV10_POINT = Short.valueOf(pValue);
		} else if (pName.equalsIgnoreCase("FameLV11Point")) {
			FAME_LV11_POINT = Short.valueOf(pValue);
		}
		//bossRoom.properties
	    else if (pName.equalsIgnoreCase("minPlayer")) {
	    	minPlayer = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("maxPlayer")) {
     		maxPlayer = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossItem")) {
     		bossItem = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("itemCount")) {
     		itemCount = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("readytime")) {
     		readytime = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("cleartime")) {
     		cleartime = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId1")) {
     		bossId1 = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId2")) {
     		bossId2 = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId3")) {
     		bossId3 = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId4")) {
     		bossId4 = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId5")) {
     		bossId5 = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId6")) {
     		bossId6 = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId7")) {
     		bossId7 = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId8")) {
     		bossId8 = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId9")) {
     		bossId9 = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId10")) {
     		bossId10 = Integer.valueOf(pValue); 
     	}
		//tdTower.properties
	    else if (pName.equalsIgnoreCase("minPlayerTD")) {
	    	minPlayerTD = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("maxPlayerTD")) {
     		maxPlayerTD = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossItemTD")) {
     		bossItemTD = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("itemCountTD")) {
     		itemCountTD = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossItemTDReward")) {
     		bossItemTDReward = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("itemCountTDReward")) {
     		itemCountTDReward = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("readytimeTD")) {
     		readytimeTD = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("cleartimeTD")) {
     		cleartimeTD = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId1TD")) {
     		bossId1TD = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId2TD")) {
     		bossId2TD = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId3TD")) {
     		bossId3TD = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId4TD")) {
     		bossId4TD = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId5TD")) {
     		bossId5TD = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId6TD")) {
     		bossId6TD = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId7TD")) {
     		bossId7TD = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId8TD")) {
     		bossId8TD = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId9TD")) {
     		bossId9TD = Integer.valueOf(pValue); 
     	} else if (pName.equalsIgnoreCase("bossId10TD")) {
     		bossId10TD = Integer.valueOf(pValue); 
     	}
		// record.properties
		else if (pName.equalsIgnoreCase("LoggingWeaponEnchant")) {
			LOGGING_WEAPON_ENCHANT = Byte.parseByte(pValue);
		} else if (pName.equalsIgnoreCase("LoggingArmorEnchant")) {
			LOGGING_ARMOR_ENCHANT = Byte.parseByte(pValue);
		} else {
			return false;
		}
		return true;
	}

	private Config() {
	}
}