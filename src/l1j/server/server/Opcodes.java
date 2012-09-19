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
package l1j.server.server;

public class Opcodes {

	public Opcodes() {
	}

	/**
	 * @3.52CTaiwanServer<b>12042501 Lin.bin
	 */

	/** 3.52CClientPacket */
	public static final int C_OPCODE_BOOKMARK = 1;// 请求增加记忆座标
	public static final int C_OPCODE_DEPOSIT = 2;// 请求将资金存入城堡宝库
	public static final int C_OPCODE_CALL = 3;// 请求传送至指定的外挂使用者身旁
	public static final int C_OPCODE_BANCLAN = 4;// 请求驱逐血盟成员
	public static final int C_OPCODE_BOARDDELETE = 5;// 请求删除公布栏内容
	public static final int C_OPCODE_BOARDREAD = 6;// 请求阅读布告单个栏讯息
	public static final int C_OPCODE_LEAVEPARTY = 7;// 请求退出队伍
	public static final int C_OPCODE_BOARDNEXT = 8;// 请求查看下一页布告栏的讯息
	public static final int C_OPCODE_ARROWATTACK = 11;// 请求使用远距攻击
	public static final int C_OPCODE_USEITEM = 12;// 请求使用物品
	public static final int C_OPCODE_RESULT = 13;// 请求取得列表中的项目
	public static final int C_OPCODE_FIGHT = 14;// 请求决斗
	public static final int C_OPCODE_BANPARTY = 18;// 请求驱逐队伍
	public static final int C_OPCODE_CHANGEWARTIME = 20;// 修正城堡总管全部功能
	public static final int C_OPCODE_TRADEADDITEM = 21;// 请求交易(添加物品)
	public static final int C_OPCODE_EXIT_GHOST = 24;// 请求退出观看模式
	public static final int C_OPCODE_TRADE = 27;// 请求交易
	public static final int C_OPCODE_TITLE = 28;// 请求赋予封号
	public static final int C_OPCODE_DOOR = 29;// 请求开门或关门
	public static final int C_OPCODE_ENTERPORTAL = 31;// 请求传送(进入地监)
	public static final int C_OPCODE_SKILLBUY = 32;// 请求查询可以学习的魔法清单
	public static final int C_OPCODE_HIRESOLDIER = 33;// 要求雇佣佣兵列表(购买)
	public static final int C_OPCODE_PROPOSE = 35;// 请求结婚
	public static final int C_OPCODE_USEPETITEM = 36;// 请求使用宠物装备
	public static final int C_OPCODE_CHANGEHEADING = 37;// 请求改变角色面向
	public static final int C_OPCODE_SHOP = 38;// 请求开设个人商店
	public static final int C_OPCODE_SENDLOCATION = 39;// 请求传送位置
	public static final int C_OPCODE_CHAT = 40;// 请求使用一般聊天频道
	public static final int C_OPCODE_EMBLEM = 41;// 请求上传盟徽
	public static final int C_OPCODE_DELBUDDY = 42;// 请求删除好友
	public static final int C_OPCODE_CLAN = 45;// //要求血盟数据(例如盟标)
	public static final int C_OPCODE_BOOKMARKDELETE = 46;// 请求删除记忆座标
	public static final int C_OPCODE_MOVECHAR = 47;// 请求移动角色
	public static final int C_OPCODE_BUDDYLIST = 48;// 请求查询好友名单
	public static final int C_OPCODE_RESTART = 49;// 请求重新开始
	public static final int C_OPCODE_TRADEADDOK = 54;// 请求完成交易
	public static final int C_OPCODE_SMS = 55;// 请求传送简讯
	public static final int C_OPCODE_DELETEINVENTORYITEM = 56;// 请求删除物品
	public static final int C_OPCODE_NEWCHAR = 57;// 请求创造角色
	public static final int C_OPCODE_CHARACTERCONFIG = 59;// 请求纪录快速键
	public static final int C_OPCODE_RETURNTOLOGIN = 60;// 要求回到选人画面
	public static final int C_OPCODE_SELECTLIST = 62;// 请求修理道具
	public static final int C_OPCODE_PARTYLIST = 63;// 请求查询队伍成员
	public static final int C_OPCODE_CASTLESECURITY = 64;// 请求管理城内治安
	public static final int C_OPCODE_LOGINTOSERVER = 65;// 请求登录角色
	public static final int C_OPCODE_CHANGECHAR = 66;// 请求切换角色
	public static final int C_OPCODE_BEANFUN_LOGIN = 68;// 请求使用乐豆自动登录伺服器(未实装)
	public static final int C_OPCODE_KEEPALIVE = 69;// 请求更新连线状态
	public static final int C_OPCODE_WAREHOUSELOCK = 70;// 请求变更仓库密码&&送出仓库密码
	public static final int C_OPCODE_DRAWAL = 71;// 请求领取城堡宝库资金
	public static final int C_OPCODE_AMOUNT = 72;// 请求传回选取的数量
	public static final int C_OPCODE_ADDBUDDY = 73;// 请求新增好友
	public static final int C_OPCODE_NPCACTION = 74;// 请求执行对话视窗的动作
	public static final int C_OPCODE_WHO = 75;// 请求查询游戏人数
	public static final int C_OPCODE_SKILLBUYOK = 76;// 请求学习魔法
	public static final int C_OPCODE_TELEPORT = 77;// 请求解除传送锁定
	public static final int C_OPCODE_FISHCLICK = 78;// 请求钓鱼收竿
	public static final int C_OPCODE_ATTACK = 79;// 请求攻击对象
	public static final int C_OPCODE_LOGINPACKET = 80;// 请求登录伺服器
	public static final int C_OPCODE_TAXRATE = 81;// 请求配置税收
	public static final int C_OPCODE_LOGINTOSERVEROK = 83;// 请求配置角色设定
	public static final int C_OPCODE_PLEDGE = 84;// 请求查询血盟成员
	public static final int C_OPCODE_PETMENU = 85;// 请求宠物回报选单
	public static final int C_OPCODE_NPCTALK = 87;// 请求对话视窗
	public static final int C_OPCODE_CLIENTVERSION = 89;// 请求验证客户端版本
	public static final int C_OPCODE_MAIL = 90;// 请求打开邮箱
	public static final int C_OPCODE_FIX_WEAPON_LIST = 91;// 请求查询损坏的道具
	public static final int C_OPCODE_COMMONCLICK = 92;// 请求下一步(伺服器公告)
	public static final int C_OPCODE_SELECTTARGET = 94;// 请求攻击指定物件(宠物&召唤)
	public static final int C_OPCODE_DROPITEM = 95;// 请求丢弃物品
	public static final int C_OPCODE_TRADEADDCANCEL = 96;// 请求取消交易
	public static final int C_OPCODE_USESKILL = 98;// 请求使用技能
	public static final int C_OPCODE_QUITGAME = 99;// 请求离开游戏
	public static final int C_OPCODE_CHECKPK = 100;// 请求查询PK次数
	public static final int C_OPCODE_RANK = 101;// 请求给予角色血盟阶级
	public static final int C_OPCODE_WAR = 102;// 请求宣战
	public static final int C_OPCODE_TELEPORTLOCK = 103;// 玩家传送锁定(回溯检测用)
	public static final int C_OPCODE_CHATGLOBAL = 104;// 请求使用广播聊天频道
	public static final int C_OPCODE_LEAVECLANE = 105;// 请求离开血盟
	public static final int C_OPCODE_BOARD = 106;// 请求浏览公布栏
	public static final int C_OPCODE_CHATWHISPER = 108;// 请求使用密语聊天频道
	public static final int C_OPCODE_JOINCLAN = 109;// 请求加入血盟
	public static final int C_OPCODE_DELETECHAR = 111;// 请求删除角色
	public static final int C_OPCODE_EXTCOMMAND = 112;// 请求角色表情动作
	public static final int C_OPCODE_CREATECLAN = 115;// 请求创立血盟
	public static final int C_OPCODE_EXCLUDE = 117;// 请求使用拒绝名单(开启指定人物讯息)
	public static final int C_OPCODE_PRIVATESHOPLIST = 120;// 请求购买指定的个人商店商品
	public static final int C_OPCODE_PICKUPITEM = 121;// 请求拾取物品
	public static final int C_OPCODE_CHARRESET = 124;// 要求重置人物点数
	public static final int C_OPCODE_SHIP = 125;// 请求下船
	public static final int C_OPCODE_GIVEITEM = 126;// 请求给予物品
	public static final int C_OPCODE_BOARDWRITE = 128;// 请求撰写新的布告栏讯息
	public static final int C_OPCODE_ATTR = 129;// 请求点选项目的结果
	public static final int C_OPCODE_CREATEPARTY = 130;// 请求邀请加入队伍或建立队伍
	public static final int C_OPCODE_CAHTPARTY = 131;// 请求聊天队伍

	/** 3.52CServerPacket */
	public static final int S_OPCODE_INPUTAMOUNT = 0;// 输入数量要产生的数量
	public static final int S_OPCODE_TRADESTATUS = 1;// 交易是否成功
	public static final int S_OPCODE_ADDITEM = 2;// 物品增加封包
	public static final int S_OPCODE_DELETEINVENTORYITEM = 3;// 删除物品
	public static final int S_OPCODE_LOGINTOGAME = 4;// 进入游戏
	public static final int S_OPCODE_DETELECHAROK = 5;// 角色移除(立即或非立即)
	public static final int S_OPCODE_EMBLEM = 6;// 下载血盟徽章
	public static final int S_OPCODE_RESURRECTION = 7;// 将死亡的对象复活
	public static final int S_OPCODE_TELEPORTLOCK = 8;// 进入传送点-传送锁定
	public static final int S_OPCODE_BOARDREAD = 9;// 布告栏(讯息阅读)
	public static final int S_OPCODE_PACKETBOX = 10;// 多功能封包
	public static final int S_OPCODE_ACTIVESPELLS = 10;// 多功能封包
	public static final int S_OPCODE_SKILLICONGFX = 10;// 多功能封包
	public static final int S_OPCODE_UNKNOWN2 = 10;// 多功能封包
	public static final int S_OPCODE_INVIS = 11;// 物件隐形或现形
	public static final int S_OPCODE_CHARAMOUNT = 12;// 角色列表
	public static final int S_OPCODE_NORMALCHAT = 13;// 一般聊天或大喊聊天频道
	public static final int S_OPCODE_DEXUP = 14;// 敏捷提升封包
	public static final int S_OPCODE_LAWFUL = 15;// 正义值更新
	public static final int S_OPCODE_TELEPORT = 16;// 传送术或瞬间移动卷轴-传送锁定
	public static final int S_OPCODE_SERVERMSG = 17;// 系统讯息
	public static final int S_OPCODE_MOVEOBJECT = 18;// 移动物件
	public static final int S_OPCODE_DEPOSIT = 19;// 存入资金城堡宝库
	public static final int S_OPCODE_WARTIME = 20;// 设定围成时间
	public static final int S_OPCODE_DRAWAL = 21;// 领取城堡宝库资金
	public static final int S_OPCODE_CURSEBLIND = 22;// 法术效果-暗盲咒术
	public static final int S_OPCODE_SELECTTARGET = 23;// 选择一个目标
	public static final int S_OPCODE_SHOWSHOPSELLLIST = 24;// 商店收购清单
	public static final int S_OPCODE_PUTSOLDIER = 25;// 配置已的雇用佣兵
	public static final int S_OPCODE_BLUEMESSAGE = 26;// 红色讯息
	public static final int S_OPCODE_TAXRATE = 28;// 设定税收封包
	public static final int S_OPCODE_SERVERVERSION = 29;// 伺服器版本
	public static final int S_OPCODE_CASTLEMASTER = 30;// 角色皇冠
	public static final int S_OPCODE_SKILLBUY = 31;// 魔法购买(金币)
	public static final int S_OPCODE_POLY = 32;// 改变外型
	public static final int S_OPCODE_MAIL = 33;// 邮件封包
	public static final int S_OPCODE_MOVELOCK = 34;// 移动锁定封包(疑似开加速器则会用这个封包将玩家锁定)
	public static final int S_OPCODE_SKILLBRAVE = 36;// 魔法或物品效果图示-勇气药水类
	public static final int S_OPCODE_WAR = 37;// 血盟战争
	public static final int S_OPCODE_SELECTLIST = 38;// 损坏武器名单
	public static final int S_OPCODE_SYSMSG = 40;// 伺服器讯息
	public static final int S_OPCODE_GLOBALCHAT = 40;// 广播聊天频道
	public static final int S_OPCODE_OWNCHARSTATUS = 41;// 角色属性与能力值
	public static final int S_OPCODE_TRADEADDITEM = 43;// 增加交易物品封包
	public static final int S_OPCODE_DISCONNECT = 44;// 立即中断连线
	public static final int S_OPCODE_ITEMSTATUS = 45;// 物品状态更新
	public static final int S_OPCODE_ITEMAMOUNT = 45;// 物品可用次数
	public static final int S_OPCODE_OWNCHARSTATUS2 = 46;// 角色能力值
	public static final int S_OPCODE_SHOWRETRIEVELIST = 47;// 仓库物品名单
	public static final int S_OPCODE_SKILLICONSHIELD = 48;// 魔法效果:防御颣
	public static final int S_OPCODE_CHARVISUALUPDATE = 49;// 切换物件外观动作
	public static final int S_OPCODE_SKILLBUY_2 = 50;// 学习魔法(何仑)
	public static final int S_OPCODE_HPMETER = 51;// 物件血条
	public static final int S_OPCODE_ADDSKILL = 52;// 增加魔法进魔法名单
	public static final int S_OPCODE_SKILLSOUNDGFX = 53;// 产生动画[自身]
	public static final int S_OPCODE_ATTRIBUTE = 54;// 物件属性
	public static final int S_OPCODE_ATTACKPACKET = 56;// 物件攻击
	public static final int S_OPCODE_NPCSHOUT = 57;// 一般聊天或大喊聊天频道
	public static final int S_OPCODE_INVLIST = 58;// 插入批次道具
	public static final int S_OPCODE_BLESSOFEVA = 59;// 效果图示(水底呼吸)
	public static final int S_OPCODE_TRADE = 60;// 交易封包
	public static final int S_OPCODE_ABILITY = 61;// 配置封包
	public static final int S_OPCODE_NEWCHARWRONG = 62;// 角色创造例外
	public static final int S_OPCODE_CHARTITLE = 64;// 角色封号
	public static final int S_OPCODE_WEATHER = 65;// 游戏天气
	public static final int S_OPCODE_RANGESKILLS = 66;// 范围魔法
	public static final int S_OPCODE_NEWCHARPACK = 67;// 角色创造成功
	public static final int S_OPCODE_MATERIAL = 68;// 魔法学习-材料不足
	public static final int S_OPCODE_STRUP = 69;// 力量提升封包
	public static final int S_OPCODE_YES_NO = 70;// 确认视窗
	public static final int S_OPCODE_MAPID = 71;// 更新现在的地图
	public static final int S_OPCODE_UNDERWATER = 71;// 更新现在的地图（水中）
	public static final int S_OPCODE_LIGHT = 73;// 物件亮度
	public static final int S_OPCODE_SOUND = 74;// 拨放音效
	public static final int S_OPCODE_CHARRESET = 76;// 角色重置
	public static final int S_OPCODE_PETCTRL = 76;// 宠物控制介面移除
	public static final int S_OPCODE_SHOWSHOPBUYLIST = 77;// 商店贩售清单
	public static final int S_OPCODE_POISON = 78;// 魔法效果:中毒
	public static final int S_OPCODE_ITEMNAME = 80;// 物品名称
	public static final int S_OPCODE_SPOLY = 81;// 特别变身封包
	public static final int S_OPCODE_CHANGEHEADING = 83;// 物件面向
	public static final int S_OPCODE_OWNCHARATTRDEF = 84;// 角色属性值
	public static final int S_OPCODE_EXP = 85;// 经验值更新
	public static final int S_OPCODE_MPUPDATE = 86;// 魔力与最大魔力更新
	public static final int S_OPCODE_PINKNAME = 87;// 角色名称变紫色
	public static final int S_OPCODE_COMMONNEWS = 88;// 公告视窗
	public static final int S_OPCODE_BOARD = 89;// 布告栏(对话视窗)
	public static final int S_OPCODE_BOOKMARKS = 90;// 插入记忆座标
	public static final int S_OPCODE_DOACTIONGFX = 91;// 执行物件外观动作
	public static final int S_OPCODE_ITEMCOLOR = 92;// 物品属性状态
	public static final int S_OPCODE_GAMETIME = 93;// 更新目前游戏时间
	public static final int S_OPCODE_INITPACKET = 95;// 初始化演算法
	public static final int S_OPCODE_USEMAP = 97;// 使用地图道具
	public static final int S_OPCODE_HOUSELIST = 98;// 血盟小屋名单
	public static final int S_OPCODE_SPMR = 100;// 魔法攻击力与魔法防御力
	public static final int S_OPCODE_EFFECTLOCATION = 102;// 产生动画[座标]
	public static final int S_OPCODE_CHARLIST = 103;// 角色资讯
	public static final int S_OPCODE_IDENTIFYDESC = 107;// 物品资讯讯息
	public static final int S_OPCODE_DELSKILL = 108;// 移除指定的魔法
	public static final int S_OPCODE_PRIVATESHOPLIST = 109;// 个人商店贩卖或购买
	public static final int S_OPCODE_LOGINRESULT = 110;// 登入状态
	public static final int S_OPCODE_CHARPACK = 112;// 物件封包
	public static final int S_OPCODE_DROPITEM = 112;// 物件封包(道具)
	public static final int S_OPCODE_WHISPERCHAT = 114;// 密语聊天频道
	public static final int S_OPCODE_TRUETARGET = 116;// 法术效果-精准目标
	public static final int S_OPCODE_LIQUOR = 117;// 海浪波浪
	public static final int S_OPCODE_PARALYSIS = 118;// 魔法效果:麻痹类
	public static final int S_OPCODE_REMOVE_OBJECT = 121;// 物件删除
	public static final int S_OPCODE_SKILLHASTE = 122;// 魔法或物品产生的加速效果
	public static final int S_OPCODE_HPUPDATE = 123;// 体力与最大体力更新
	public static final int S_OPCODE_HOUSEMAP = 124;// 血盟小屋地图[地点]
	public static final int S_OPCODE_SHOWHTML = 125;// 产生对话视窗
	public static final int S_OPCODE_HIRESOLDIER = 126;// 雇用佣兵
	public static final int S_OPCODE_CHANGENAME = 127;// 改变物件名称

}