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

/**
 * 封包代码 - 提供者:薇薇
 * 
 * @3.51C Taiwan Server <b>12011702 Lin.bin
 */
public class Opcodes {

    // 3.51C Client Packet （客户端代码）
    /** 请求 传送 (进入地监) */
    public static final int C_OPCODE_ENTERPORTAL = 0;
    /** 请求 点选项目的结果(Y/N) */
    public static final int C_OPCODE_ATTR = 4;
    /** 请求 进入游戏 */
    public static final int C_OPCODE_LOGINTOSERVER = 5;
    /** 请求 创立血盟 */
    public static final int C_OPCODE_CREATECLAN = 6;
    /** 请求 驱逐血盟成员 */
    public static final int C_OPCODE_BANCLAN = 7;
    /** 请求 脱离血盟 */
    public static final int C_OPCODE_LEAVECLANE = 8;
    /** 请求 请求验证客户端版本 */
    public static final int C_OPCODE_CLIENTVERSION = 12;
    /** 请求 使用物品 */
    public static final int C_OPCODE_USEITEM = 13;
    /** 请求 登入伺服器 */
    public static final int C_OPCODE_LOGINPACKET = 14;
    /** 请求 管理城堡治安 */
    public static final int C_OPCODE_CASTLESECURITY = 15;
    /** 请求 读取 公布栏/拍卖公告 讯息列表(浏览公布栏) */
    public static final int C_OPCODE_BOARD = 16;
    /** 未使用 - 请求 座标异常重整 */
    public static final int C_OPCODE_MOVELOCK = 17;
    /** 请求 选取观看频道or配置角色设定 */
    public static final int C_OPCODE_LOGINTOSERVEROK = 18;
    /** 请求 查询好友名单 */
    public static final int C_OPCODE_BUDDYLIST = 19;
    /** 请求 使用远程攻击 */
    public static final int C_OPCODE_ARROWATTACK = 20;
    /** 请求 雇请佣兵列表(购买佣兵完成) */
    public static final int C_OPCODE_HIRESOLDIER = 21;
    /** 请求 新增好友 */
    public static final int C_OPCODE_ADDBUDDY = 22;
    /** 请求 查询队伍成员名单 */
    public static final int C_OPCODE_PARTYLIST = 23;
    /** 请求 移动角色 */
    public static final int C_OPCODE_MOVECHAR = 24;
    /** 请求 执行物件对话视窗动作/结果 */
    public static final int C_OPCODE_NPCACTION = 25;
    /** 未使用 - 请求设置城内治安管理OK */
    public static final int C_OPCODE_SETCASTLESECURITY = 26;
    /** 增加交易物品(双方交易) */
    public static final int S_OPCODE_TRADEADDITEM = 27;
    /** 请求 使用宠物装备 */
    public static final int C_OPCODE_USEPETITEM = 30;
    /** 请求 物件对话视窗 */
    public static final int C_OPCODE_NPCTALK = 31;
    /** 请求 阅读单个公布栏内容 */
    public static final int C_OPCODE_BOARDREAD = 32;
    /** 请求 交易(双方交易) */
    public static final int C_OPCODE_TRADE = 33;
    /** 请求 创造角色 */
    public static final int C_OPCODE_NEWCHAR = 34;
    /** 请求 捡取物品 */
    public static final int C_OPCODE_PICKUPITEM = 35;
    /** 请求 学习魔法清单(金币) */
    public static final int C_OPCODE_SKILLBUY = 36;
    /** 请求 赋予封号(/title) */
    public static final int C_OPCODE_TITLE = 37;
    /** 城堡宝库(请求领出资金) */
    public static final int C_OPCODE_DRAWAL = 38;
    /** 请求 删除好友 */
    public static final int C_OPCODE_DELBUDDY = 40;
    /** 请求 删除记忆坐标 */
    public static final int C_OPCODE_BOOKMARKDELETE = 41;
    /** 请求 增加交易物品(双方交易) */
    public static final int C_OPCODE_TRADEADDITEM = 42;
    /** 请求 丢弃物品(丢弃至地面) */
    public static final int C_OPCODE_DROPITEM = 43;
    /** 请求 纪录快捷键 */
    public static final int C_OPCODE_CHARACTERCONFIG = 47;
    /** 请求 取得列表中的物品 */
    public static final int C_OPCODE_RESULT = 48;
    /** 请求 物件对话视窗数量选取结果 */
    public static final int C_OPCODE_AMOUNT = 50;
    /** 请求 学习魔法清单(材料) */
    public static final int C_OPCODE_SKILLBUYITEM = 52;
    /** 请求 门的控制(开关)/宝箱的开启 */
    public static final int C_OPCODE_DOOR = 53;
    /** 请求 下船 */
    public static final int C_OPCODE_SHIP = 54;
    /** 请求 给予物品 */
    public static final int C_OPCODE_GIVEITEM = 55;
    /** 请求 使用广播聊天频道 */
    public static final int C_OPCODE_CHATGLOBAL = 56;
    /** 请求 配置已雇用的士兵 */
    public static final int C_OPCODE_PUTSOLDIER = 57;
    /** 未使用 - 请求更新周围物件(坐标点/洞穴点切换进出后) */
    public static final int C_OPCODE_TELEPORT2 = 58;
    /** 请求 删除公布栏内容 */
    public static final int C_OPCODE_BOARDDELETE = 59;
    /** 请求 打开邮箱 */
    public static final int C_OPCODE_MAIL = 60;
    /** 请求 踢出队伍 */
    public static final int C_OPCODE_BANPARTY = 61;
    /** 请求 查看下一页公布栏讯息 */
    public static final int C_OPCODE_BOARDNEXT = 62;
    /** 请求 使用拒绝名单(开启指定人物讯息) */
    public static final int C_OPCODE_EXCLUDE = 63;
    /** 请求 完成交易(双方交易) */
    public static final int C_OPCODE_TRADEADDOK = 64;
    /** 请求 变更仓库密码 && 送出仓库密码 */
    public static final int C_OPCODE_WAREHOUSELOCK = 65;
    /** 请求 更新时间/连线状态 */
    public static final int C_OPCODE_KEEPALIVE = 66;
    /** 未使用 - 请求 自动登入伺服器 */
    public static final int C_OPCODE_AUTO = 68;
    /** 请求 改变角色面向 */
    public static final int C_OPCODE_CHANGEHEADING = 69;
    /** 请求 使用技能 */
    public static final int C_OPCODE_USESKILL = 71;
    /** 请求 写入新的公布栏讯息 */
    public static final int C_OPCODE_BOARDWRITE = 72;
    /** 请求 宠物回报选单 */
    public static final int C_OPCODE_PETMENU = 74;
    /** 请求 宣战/投降/休战 */
    public static final int C_OPCODE_WAR = 75;
    /** 请求 更新周围物件(传送后解除传送锁定) */
    public static final int C_OPCODE_TELEPORT = 76;
    /** 请求 变更领地税率 */
    public static final int C_OPCODE_TAXRATE = 77;
    /** 请求 重置角色属性点 */
    public static final int C_OPCODE_CHARRESET = 78;
    /** 请求 回到登入画面 */
    public static final int C_OPCODE_RETURNTOLOGIN = 79;
    /** 请求 下一步(伺服器公告视窗后 显示角色列表) */
    public static final int C_OPCODE_COMMONCLICK = 80;
    /** 请求 查询PK次数(/checkpk) */
    public static final int C_OPCODE_CHECKPK = 81;
    /** 未使用 - 请求 简讯服务 */
    public static final int C_OPCODE_MSG = 82;
    /** 请求 查询在线游戏人数(/who) */
    public static final int C_OPCODE_WHO = 83;
    /** 请求 开设个人商店 */
    public static final int C_OPCODE_SHOP = 84;
    /** 请求 退出鬼魂(观看模式) */
    public static final int C_OPCODE_EXIT_GHOST = 85;
    /** 请求 给予角色血盟阶级(/rank 人物 见习) */
    public static final int C_OPCODE_RANK = 86;
    /** 请求 离开游戏 */
    public static final int C_OPCODE_QUITGAME = 87;
    /** 请求 删除角色 */
    public static final int C_OPCODE_DELETECHAR = 88;
    /** 请求 上传盟徽 */
    public static final int C_OPCODE_EMBLEM = 90;
    /** 请求 结婚(/propose) */
    public static final int C_OPCODE_PROPOSE = 91;
    /** 请求 脱离队伍 */
    public static final int C_OPCODE_LEAVEPARTY = 92;
    /** 请求 查询血盟成员 */
    public static final int C_OPCODE_PLEDGE = 93;
    /** 未使用 - 请求 配置已雇用的士兵OK */
    public static final int C_OPCODE_PUTHIRESOLDIER = 94;
    /** 请求 完成学习魔法(金币) */
    public static final int C_OPCODE_SKILLBUYOK = 95;
    /** 请求 选择 变更攻城时间 */
    public static final int C_OPCODE_SELECTWARTIME = 96;
    /** 请求 死亡后重新开始 */
    public static final int C_OPCODE_RESTART = 97;
    /** 请求 更新血盟数据(例如盟标) */
    public static final int C_OPCODE_CLAN = 99;
    /** 请求 角色表情动作 */
    public static final int C_OPCODE_EXTCOMMAND = 100;
    /** 请求 传送位置&视窗失焦 */
    public static final int C_OPCODE_SENDLOCATION = 102;
    /** 请求 取消交易(双方交易) */
    public static final int C_OPCODE_TRADEADDCANCEL = 103;
    /** 请求 加入血盟 */
    public static final int C_OPCODE_JOINCLAN = 104;
    /** 请求 删除物品 */
    public static final int C_OPCODE_DELETEINVENTORYITEM = 105;
    /** 请求 购买指定的个人商店商品(商品清单) */
    public static final int C_OPCODE_PRIVATESHOPLIST = 106;
    /** 请求 使用一般聊天频道 */
    public static final int C_OPCODE_CHAT = 109;
    /** 请求 配置城墙上的弓箭手OK */
    public static final int C_OPCODE_PUTBOWSOLDIER = 110;
    /** 请求 切换角色 */
    public static final int C_OPCODE_CHANGECHAR = 111;
    /** 请求 增加记忆坐标 */
    public static final int C_OPCODE_BOOKMARK = 112;
    /** 请求 修理损坏的道具 */
    public static final int C_OPCODE_SELECTLIST = 114;
    /** 请求 决斗 */
    public static final int C_OPCODE_FIGHT = 115;
    /** 请求 使用密语聊天频道 */
    public static final int C_OPCODE_CHATWHISPER = 117;
    /** 请求 执行线上人物列表命令(GM管理选单)传送至指定的外挂使用者身旁 */
    public static final int C_OPCODE_CALL = 118;
    /** 请求 查询损坏的道具(维修物品清单) */
    public static final int C_OPCODE_FIX_WEAPON_LIST = 119;
    /** 请求 完成学习魔法(材料) */
    public static final int C_OPCODE_SKILLBUYOKITEM = 120;
    /** 未使用 - 请求 进入游戏(确定服务器登入讯息) */
    public static final int C_OPCODE_COMMONINFO = 122;
    /** 城堡宝库(请求存入资金) */
    public static final int C_OPCODE_DEPOSIT = 124;
    /** 请求 决定下次围城时间(官方已取消使用)-->修正城堡总管全部功能 */
    public static final int C_OPCODE_CHANGEWARTIME = 125;
    /** 请求 攻击指定物件(宠物&召唤) */
    public static final int C_OPCODE_SELECTTARGET = 126;
    /** 请求 钓鱼收竿 */
    public static final int C_OPCODE_FISHCLICK = 127;
    /** 请求 角色攻击 */
    public static final int C_OPCODE_ATTACK = 129;
    /** 请求 邀请加入队伍/创立队伍 */
    public static final int C_OPCODE_CREATEPARTY = 130;
    /** 请求 队伍对话控制(命令/chatparty) */
    public static final int C_OPCODE_CAHTPARTY = 131;

    /** 未知 - 请求 提取天宝 */
    public static final int C_OPCODE_CNITEM = -1;
    /** 未知 - 请求 确认未知购物清单2 */
    public static final int C_OPCODE_SHOPX2 = -2;
    /** 未知 - 请求 新增帐号 */
    public static final int C_OPCODE_NEWACC = -3;

    // 3.51C Server Packet （服务端代码）
    /** 物件移动 */
    public static final int S_OPCODE_MOVEOBJECT = 0;
    /** 魔法效果:醉酒/海浪波浪/三段加速 */
    public static final int S_OPCODE_LIQUOR = 2;
    /** 角色封号 */
    public static final int S_OPCODE_CHARTITLE = 3;
    /** 宣告进入游戏 */
    public static final int S_OPCODE_LOGINTOGAME = 4;
    /** 魔法效果:水底呼吸图示 */
    public static final int S_OPCODE_BLESSOFEVA = 5;
    /** 增加魔法进魔法名单 */
    public static final int S_OPCODE_ADDSKILL = 6;
    /** 更新HP显示(体力与最大体力) */
    public static final int S_OPCODE_HPUPDATE = 7;
    /** 登入状态 */
    public static final int S_OPCODE_LOGINRESULT = 8;
    /** 传送控制戒指 */
    public static final int S_OPCODE_ABILITY = 9;
    /** 创造角色成功(新创) */
    public static final int S_OPCODE_NEWCHARPACK = 10;
    /** 角色名称变紫色 */
    public static final int S_OPCODE_PINKNAME = 11;
    /** 更新物件亮度 */
    public static final int S_OPCODE_LIGHT = 12;
    /** 设置税收 */
    public static final int S_OPCODE_TAXRATE = 14;
    /** 更新游戏天气 */
    public static final int S_OPCODE_WEATHER = 15;
    /** 聊天频道(一般or大喊) */
    public static final int S_OPCODE_NORMALCHAT = 16;
    /** 切换物件外观动作(长时间) */
    public static final int S_OPCODE_CHARVISUALUPDATE = 17;
    /** 初始化演算法 */
    public static final int S_OPCODE_INITPACKET = 18;
    /** 公告视窗 */
    public static final int S_OPCODE_COMMONNEWS = 19;
    /** 特别变身封包 - NPC改变外型 */
    public static final int S_OPCODE_SPOLY = 20;
    /** 城堡宝库(取出资金) */
    public static final int S_OPCODE_DRAWAL = 21;
    /** 魔法购买清单(金币) */
    public static final int S_OPCODE_SKILLBUY = 22;
    /** 未使用 - 配置城墙上的弓箭手列表(佣兵购买视窗) */
    public static final int S_OPCODE_PUTBOWSOLDIERLIST = 23;
    /** 产生对话视窗(NPC) */
    public static final int S_OPCODE_SHOWHTML = 24;
    /** 更新MP显示(魔力与最大魔力) */
    public static final int S_OPCODE_MPUPDATE = 25;
    /** 更新物件面向 */
    public static final int S_OPCODE_CHANGEHEADING = 26;
    /** 移除指定的魔法 */
    public static final int S_OPCODE_DELSKILL = 28;
    /** 未使用 - 阅读邮件(旧) */
    public static final int S_OPCODE_LETTER = 29;
    /** 角色资讯(属性与能力值) */
    public static final int S_OPCODE_OWNCHARSTATUS = 30;
    /** 更新物件外型 */
    public static final int S_OPCODE_POLY = 31;
    /** 将死亡的对象复活 */
    public static final int S_OPCODE_RESURRECTION = 32;
    /** 魔法效果:物件隐形or现形 */
    public static final int S_OPCODE_INVIS = 33;
    /** 物品增加(背包) */
    public static final int S_OPCODE_ADDITEM = 34;
    /** 选取物品数量 */
    public static final int S_OPCODE_INPUTAMOUNT = 36;
    /** 角色列表资讯 */
    public static final int S_OPCODE_CHARLIST = 37;
    /** 角色盟徽(下载) */
    public static final int S_OPCODE_EMBLEM = 38;
    /** 魔法购买清单(材料) */
    public static final int S_OPCODE_SKILLBUYITEM = 39;
    /** 更新物品显示名称(背包) */
    public static final int S_OPCODE_ITEMNAME = 40;
    /** 布告栏列表(讯息阅读) */
    public static final int S_OPCODE_BOARDREAD = 41;
    /** NPC聊天频道(一般or大喊) */
    public static final int S_OPCODE_NPCSHOUT = 42;
    /** 伺服器版本 */
    public static final int S_OPCODE_SERVERVERSION = 43;
    /** 可配置排列佣兵数(HTML)(EX:雇用的总佣兵数:XX 可排列的佣兵数:XX ) */
    public static final int S_OPCODE_PUTHIRESOLDIER = 44;
    /** NPC物品购买清单(人物卖出)商店收购清单 */
    public static final int S_OPCODE_SHOWSHOPSELLLIST = 45;
    /** NPC物品贩卖清单(人物买入)商店贩售清单 */
    public static final int S_OPCODE_SHOWSHOPBUYLIST = 47;
    /** 物件攻击(伤害力变更封包类型为 writeH(0x0000)) */
    public static final int S_OPCODE_ATTACKPACKET = 48;
    /** 设置围城时间 */
    public static final int S_OPCODE_WARTIME = 49;
    /** 魔法效果:中毒 */
    public static final int S_OPCODE_POISON = 50;
    /** 角色能力状态(力量,敏捷等) */
    public static final int S_OPCODE_OWNCHARSTATUS2 = 52;
    /** 魔法效果:诅咒/麻痹类 */
    public static final int S_OPCODE_PARALYSIS = 53;
    /** 角色移除(立即or非立即) */
    public static final int S_OPCODE_DETELECHAROK = 54;
    /** 未使用 - 更新血盟数据 */
    public static final int S_OPCODE_UPDATECLANID = 56;
    /** 布告栏(对话视窗) */
    public static final int S_OPCODE_BOARD = 57;
    /** 未使用 - 物件新增主人 */
    public static final int S_OPCODE_NEWMASTER = 58;
    /** 更新角色防御属性 */
    public static final int S_OPCODE_OWNCHARATTRDEF = 59;
    /** 魔法效果:敏捷提升 */
    public static final int S_OPCODE_DEXUP = 61;
    /** 服务器讯息(行数/行数,附加字串) */
    public static final int S_OPCODE_SERVERMSG = 63;
    /** 聊天频道(密语) */
    public static final int S_OPCODE_WHISPERCHAT = 65;
    /** 损坏武器清单 */
    public static final int S_OPCODE_SELECTLIST = 65;
    /** 未使用 - 修理武器清单 */
    public static final int S_OPCODE_FIX_WEAPON_MENU = 65;
    /** 魔法效果:暗盲咒术 */
    public static final int S_OPCODE_CURSEBLIND = 66;
    /** 更新魔攻与魔防 */
    public static final int S_OPCODE_SPMR = 67;
    /** 封包盒子(多功能封包) */
    public static final int S_OPCODE_PACKETBOX = 68;
    /** 封包盒子(多功能封包) */
    public static final int S_OPCODE_ACTIVESPELLS = 68;
    /** 封包盒子(多功能封包) */
    public static final int S_OPCODE_SKILLICONGFX = 68;
    /** 封包盒子(多功能封包) */
    public static final int S_OPCODE_UNKNOWN2 = 68;
    /** 魔法效果:防御类 */
    public static final int S_OPCODE_SKILLICONSHIELD = 71;
    /** 更新角色所在的地图 */
    public static final int S_OPCODE_MAPID = 72;
    /** 更新现在的地图 （水中） */
    public static final int S_OPCODE_UNDERWATER = 72;
    /** 魔法效果:精准目标 */
    public static final int S_OPCODE_TRUETARGET = 73;
    /** 角色创造结果 */
    public static final int S_OPCODE_NEWCHARWRONG = 74;
    /** 角色重置升级能力 */
    public static final int S_OPCODE_CHARRESET = 75;
    /** 宠物控制介面移除 */
    public static final int S_OPCODE_PETCTRL = 75;
    /** 角色皇冠 */
    public static final int S_OPCODE_CASTLEMASTER = 76;
    /** 佣兵配置清单(已有的) */
    public static final int S_OPCODE_PUTSOLDIER = 77;
    /** 交易封包(双方交易) */
    public static final int S_OPCODE_TRADE = 78;
    /** 产生动画 [自身] */
    public static final int S_OPCODE_SKILLSOUNDGFX = 80;
    /** 物品名单(背包)插入批次道具 */
    public static final int S_OPCODE_INVLIST = 81;
    /** 物件属性(门) */
    public static final int S_OPCODE_ATTRIBUTE = 82;
    /** 选择一个目标 */
    public static final int S_OPCODE_SELECTTARGET = 83;
    /** 更新当前游戏时间 */
    public static final int S_OPCODE_GAMETIME = 84;
    /** 城堡宝库(存入资金) */
    public static final int S_OPCODE_DEPOSIT = 86;
    /** 血盟战争讯息(编号,血盟名称,目标血盟名称) */
    public static final int S_OPCODE_WAR = 87;
    /** 未使用 - 服务器登入讯息(使用string.tbl) */
    public static final int S_OPCODE_COMMONINFO = 88;
    /** 物品属性色彩状态(背包)祝福・诅咒状态变化 */
    public static final int S_OPCODE_ITEMCOLOR = 89;
    /** Ping Time */
    public static final int S_OPCODE_PINGTIME = 90;
    /** 画面中间红色讯息 */
    public static final int S_OPCODE_BLUEMESSAGE = 91;
    /** 角色记忆坐标名单/插入记忆座标 */
    public static final int S_OPCODE_BOOKMARKS = 92;
    /** 交易状态(双方交易)交易是否成功 */
    public static final int S_OPCODE_TRADESTATUS = 93;
    /** 物件删除 */
    public static final int S_OPCODE_REMOVE_OBJECT = 94;
    /** 物品资讯讯息(鉴定) */
    public static final int S_OPCODE_IDENTIFYDESC = 95;
    /** 魔法or物品效果图示:勇敢药水类 */
    public static final int S_OPCODE_SKILLBRAVE = 96;
    /** 物件封包 */
    public static final int S_OPCODE_CHARPACK = 97;
    /** 物件封包 (地面道具) */
    public static final int S_OPCODE_DROPITEM = 97;
    /** 魔法效果:力量提升 */
    public static final int S_OPCODE_STRUP = 99;
    /** 更新经验值 */
    public static final int S_OPCODE_EXP = 100;
    /** 物品名单(仓库) */
    public static final int S_OPCODE_SHOWRETRIEVELIST = 101;
    /** 物件血条 */
    public static final int S_OPCODE_HPMETER = 103;
    /** 物品状态更新(背包) */
    public static final int S_OPCODE_ITEMSTATUS = 104;
    /** 物品可用次数(背包) */
    public static final int S_OPCODE_ITEMAMOUNT = 104;
    /** 传送锁定(洞穴点坐标点)进入传送点 */
    public static final int S_OPCODE_TELEPORTLOCK = 105;
    /** 角色列表 */
    public static final int S_OPCODE_CHARAMOUNT = 106;
    /** 范围魔法 */
    public static final int S_OPCODE_RANGESKILLS = 107;
    /** 选项确认视窗(Yes/No) */
    public static final int S_OPCODE_YES_NO = 108;
    /** 盟屋拍卖公告栏列表(血盟小屋名单) */
    public static final int S_OPCODE_HOUSELIST = 109;
    /** 未使用 - 强制登出人物 */
    public static final int S_OPCODE_CHAROUT = 110;
    /** 播放音效 */
    public static final int S_OPCODE_SOUND = 111;
    /** 交易商店清单(个人商店贩卖或购买) */
    public static final int S_OPCODE_PRIVATESHOPLIST = 112;
    /** 立即中断连线 */
    public static final int S_OPCODE_DISCONNECT = 113;
    /** 邮件系统 */
    public static final int S_OPCODE_MAIL = 114;
    /** 未使用 - 画面中红色讯息(登入来源) */
    public static final int S_OPCODE_REDMESSAGE = 115;
    /** 传送锁定(NPC瞬间移动)传送术或瞬间移动卷轴 */
    public static final int S_OPCODE_TELEPORT = 116;
    /** 产生动画 [座标] */
    public static final int S_OPCODE_EFFECTLOCATION = 117;
    /** 更新正义值 */
    public static final int S_OPCODE_LAWFUL = 118;
    /** 血盟小屋地图 [地点] */
    public static final int S_OPCODE_HOUSEMAP = 119;
    /** 聊天频道(广播) */
    public static final int S_OPCODE_GLOBALCHAT = 120;
    /** 未使用 - 学习魔法材料不足 */
    public static final int S_OPCODE_ITEMERROR = 121;
    /** 更新物件名称 */
    public static final int S_OPCODE_CHANGENAME = 122;
    /** 雇请佣兵(佣兵购买视窗) */
    public static final int S_OPCODE_HIRESOLDIER = 123;
    /** 执行物件外观动作(短时间) */
    public static final int S_OPCODE_DOACTIONGFX = 124;
    /** 魔法or物品产生的效果:加速类 */
    public static final int S_OPCODE_SKILLHASTE = 125;
    /** 物品删除(背包) */
    public static final int S_OPCODE_DELETEINVENTORYITEM = 126;
    /** 未使用 - 角色锁定(座标异常重整) */
    public static final int S_OPCODE_CHARLOCK = 127;

    // XXX unknown
    /**
     * 未知购物清单1 Server op: 0
     */
    public static final int S_OPCODE_SHOPX1 = -0;

    /**
     * 未知购物清单2 Server op: 71
     */
    public static final int S_OPCODE_SHOPX2 = -71;

    /**
     * 物理范围攻击 Server op: 0000
     */
    public static final int S_OPCODE_ATTACKRANGE = -1;

    /** 3.2C ServerPacket (3.5C 未抓取) id非正确 */
    public static final int S_OPCODE_USEMAP = 130;

    public Opcodes() {
    }

}
