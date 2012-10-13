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

public final class Opcodes {
    // 3.52C Client Packet （客户端代码）
    /** 请求 增加记忆坐标. */
    public static final int C_OPCODE_BOOKMARK = 1;
    /** 城堡宝库(请求存入资金). */
    public static final int C_OPCODE_DEPOSIT = 2;
    /** 请求 执行线上人物列表命令(GM管理选单)传送至指定的外挂使用者身旁. */
    public static final int C_OPCODE_CALL = 3;
    /** 请求 驱逐血盟成员. */
    public static final int C_OPCODE_BANCLAN = 4;
    /** 请求 删除布告栏内容. */
    public static final int C_OPCODE_BOARDDELETE = 5;
    /** 请求 阅读单个布告栏内容. */
    public static final int C_OPCODE_BOARDREAD = 6;
    /** 请求 脱离队伍. */
    public static final int C_OPCODE_LEAVEPARTY = 7;
    /** 请求 查看下一页公布栏讯息. */
    public static final int C_OPCODE_BOARDNEXT = 8;
    /** 请求 使用远程攻击. */
    public static final int C_OPCODE_ARROWATTACK = 11;
    /** 请求 使用物品. */
    public static final int C_OPCODE_USEITEM = 12;
    /** 请求 取得列表中的物品. */
    public static final int C_OPCODE_RESULT = 13;
    /** 请求 决斗. */
    public static final int C_OPCODE_FIGHT = 14;
    /** 请求 踢出队伍. */
    public static final int C_OPCODE_BANPARTY = 18;
    /** 请求 决定下次围城时间(官方已取消使用)-->修正城堡总管全部功能. */
    public static final int C_OPCODE_CHANGEWARTIME = 20;
    /** 请求 增加交易物品(双方交易). */
    public static final int C_OPCODE_TRADEADDITEM = 21;
    /** 请求 退出幽灵模式(观看模式). */
    public static final int C_OPCODE_EXIT_GHOST = 24;
    /** 请求 交易(双方交易). */
    public static final int C_OPCODE_TRADE = 27;
    /** 请求 赋予封号(/title). */
    public static final int C_OPCODE_TITLE = 28;
    /** 请求 门的控制(开关)/宝箱的开启. */
    public static final int C_OPCODE_DOOR = 29;
    /** 请求 传送 (进入地监). */
    public static final int C_OPCODE_ENTERPORTAL = 31;
    /** 请求 学习魔法清单(金币). */
    public static final int C_OPCODE_SKILLBUY = 32;
    /** 请求 雇请佣兵列表(购买佣兵完成). */
    public static final int C_OPCODE_HIRESOLDIER = 33;
    /** 请求 结婚(/propose). */
    public static final int C_OPCODE_PROPOSE = 35;
    /** 请求 使用宠物装备. */
    public static final int C_OPCODE_USEPETITEM = 36;
    /** 请求 改变角色面向. */
    public static final int C_OPCODE_CHANGEHEADING = 37;
    /** 请求 开设个人商店. */
    public static final int C_OPCODE_SHOP = 38;
    /** 请求 传送位置&视窗失焦. */
    public static final int C_OPCODE_SENDLOCATION = 39;
    /** 请求 使用一般聊天频道. */
    public static final int C_OPCODE_CHAT = 40;
    /** 请求 上传盟徽. */
    public static final int C_OPCODE_EMBLEM = 41;
    /** 请求 删除好友. */
    public static final int C_OPCODE_DELETEBUDDY = 42;
    /** 请求 更新血盟数据(例如盟标). */
    public static final int C_OPCODE_CLAN = 45;
    /** 请求 删除记忆坐标. */
    public static final int C_OPCODE_BOOKMARKDELETE = 46;
    /** 请求 移动角色. */
    public static final int C_OPCODE_MOVECHAR = 47;
    /** 请求 查询好友名单. */
    public static final int C_OPCODE_BUDDYLIST = 48;
    /** 请求 死亡后重新开始. */
    public static final int C_OPCODE_RESTART = 49;
    /** 请求 完成交易(双方交易). */
    public static final int C_OPCODE_TRADEADDOK = 54;
    /** 未使用 - 请求 简讯服务(传送简讯). */
    public static final int C_OPCODE_MSG = 55;
    /** 请求 删除物品. */
    public static final int C_OPCODE_DELETEINVENTORYITEM = 56;
    /** 请求 创造角色. */
    public static final int C_OPCODE_NEWCHAR = 57;
    /** 请求 纪录快捷键. */
    public static final int C_OPCODE_CHARACTERCONFIG = 59;
    /** 请求 回到登入画面. */
    public static final int C_OPCODE_RETURNTOLOGIN = 60;
    /** 请求 修理损坏的道具. */
    public static final int C_OPCODE_SELECTLIST = 62;
    /** 请求 查询队伍成员名单. */
    public static final int C_OPCODE_PARTYLIST = 63;
    /** 请求 管理城堡治安. */
    public static final int C_OPCODE_CASTLESECURITY = 64;
    /** 请求 进入游戏. */
    public static final int C_OPCODE_LOGINTOSERVER = 65;
    /** 请求 切换角色. */
    public static final int C_OPCODE_CHANGECHAR = 66;
    /** 未使用 - 请求 自动登入伺服器(使用乐豆自动登录). */
    public static final int C_OPCODE_AUTO = 68;
    /** 请求 更新时间/连线状态. */
    public static final int C_OPCODE_KEEPALIVE = 69;
    /** 请求 变更仓库密码 && 送出仓库密码. */
    public static final int C_OPCODE_WAREHOUSELOCK = 70;
    /** 城堡宝库(请求领出资金). */
    public static final int C_OPCODE_DRAWAL = 71;
    /** 请求 物件对话视窗数量选取结果. */
    public static final int C_OPCODE_AMOUNT = 72;
    /** 请求 新增好友. */
    public static final int C_OPCODE_ADDBUDDY = 73;
    /** 请求 执行物件对话视窗动作/结果. */
    public static final int C_OPCODE_NPCACTION = 74;
    /** 请求 查询在线游戏人数(/who). */
    public static final int C_OPCODE_WHO = 75;
    /** 请求 完成学习魔法(金币). */
    public static final int C_OPCODE_SKILLBUYOK = 76;
    /** 请求 更新周围物件(传送后解除传送锁定). */
    public static final int C_OPCODE_TELEPORT = 77;
    /** 请求 钓鱼收竿. */
    public static final int C_OPCODE_FISHCLICK = 78;
    /** 请求 角色攻击. */
    public static final int C_OPCODE_ATTACK = 79;
    /** 请求 登入伺服器. */
    public static final int C_OPCODE_LOGINPACKET = 80;
    /** 请求 变更领地税率. */
    public static final int C_OPCODE_TAXRATE = 81;
    /** 请求 选取观看频道or配置角色设定. */
    public static final int C_OPCODE_LOGINTOSERVEROK = 83;
    /** 请求 查询血盟成员. */
    public static final int C_OPCODE_PLEDGE = 84;
    /** 请求 宠物回报选单. */
    public static final int C_OPCODE_PETMENU = 85;
    /** 请求 物件对话视窗. */
    public static final int C_OPCODE_NPCTALK = 87;
    /** 请求 请求验证客户端版本. */
    public static final int C_OPCODE_CLIENTVERSION = 89;
    /** 请求 打开邮箱. */
    public static final int C_OPCODE_MAIL = 90;
    /** 请求 查询损坏的道具(维修物品清单). */
    public static final int C_OPCODE_FIX_WEAPON_LIST = 91;
    /** 请求 下一步(伺服器公告视窗后 显示角色列表). */
    public static final int C_OPCODE_COMMONCLICK = 92;
    /** 请求 攻击指定物件(宠物&召唤). */
    public static final int C_OPCODE_SELECTTARGET = 94;
    /** 请求 丢弃物品(丢弃至地面). */
    public static final int C_OPCODE_DROPITEM = 95;
    /** 请求 取消交易(双方交易). */
    public static final int C_OPCODE_TRADEADDCANCEL = 96;
    /** 请求 使用技能. */
    public static final int C_OPCODE_USESKILL = 98;
    /** 请求 离开游戏. */
    public static final int C_OPCODE_QUITGAME = 99;
    /** 请求 查询PK次数(/checkpk). */
    public static final int C_OPCODE_CHECKPK = 100;
    /** 请求 给予角色血盟阶级(/rank 人物 见习). */
    public static final int C_OPCODE_RANK = 101;
    /** 请求 宣战/投降/休战. */
    public static final int C_OPCODE_WAR = 102;
    /** 请求 座标异常重整(回溯检测用). */
    public static final int C_OPCODE_MOVELOCK = 103;
    /** 请求 使用广播聊天频道. */
    public static final int C_OPCODE_CHATGLOBAL = 104;
    /** 请求 脱离血盟. */
    public static final int C_OPCODE_LEAVECLANE = 105;
    /** 请求 读取 布告栏/拍卖公告 讯息列表(浏览布告栏). */
    public static final int C_OPCODE_BOARD = 106;
    /** 请求 使用密语聊天频道. */
    public static final int C_OPCODE_CHATWHISPER = 108;
    /** 请求 加入血盟. */
    public static final int C_OPCODE_JOINCLAN = 109;
    /** 请求 删除角色. */
    public static final int C_OPCODE_DELETECHAR = 111;
    /** 请求 角色表情动作. */
    public static final int C_OPCODE_EXTCOMMAND = 112;
    /** 请求 创立血盟. */
    public static final int C_OPCODE_CREATECLAN = 115;
    /** 请求 使用拒绝名单(开启指定人物讯息). */
    public static final int C_OPCODE_EXCLUDE = 117;
    /** 请求 购买指定的个人商店商品(商品清单). */
    public static final int C_OPCODE_PRIVATESHOPLIST = 120;
    /** 请求 捡取物品. */
    public static final int C_OPCODE_PICKUPITEM = 121;
    /** 请求 重置角色属性点. */
    public static final int C_OPCODE_CHARRESET = 124;
    /** 请求 下船. */
    public static final int C_OPCODE_SHIP = 125;
    /** 请求 给予物品. */
    public static final int C_OPCODE_GIVEITEM = 126;
    /** 请求 写入新的公布栏讯息. */
    public static final int C_OPCODE_BOARDWRITE = 128;
    /** 请求 点选项目的结果(Y/N). */
    public static final int C_OPCODE_ATTR = 129;
    /** 请求 邀请加入队伍/创立队伍. */
    public static final int C_OPCODE_CREATEPARTY = 130;
    /** 请求 聊天队伍对话控制(命令/chatparty). */
    public static final int C_OPCODE_CAHTPARTY = 131;

    // 未知
    /** 未使用 - 请求设置城内治安管理OK. */
    public static final int C_OPCODE_SETCASTLESECURITY = -1; // XXX
    /** 请求 学习魔法清单(材料). */
    public static final int C_OPCODE_SKILLBUYITEM = -2; // XXX
    /** 请求 配置已雇用的士兵. */
    public static final int C_OPCODE_PUTSOLDIER = -3; // XXX
    /** 未使用 - 请求更新周围物件(坐标点/洞穴点切换进出后). */
    public static final int C_OPCODE_TELEPORT2 = -4; // XXX
    /** 未使用 - 请求 配置已雇用的士兵OK. */
    public static final int C_OPCODE_PUTHIRESOLDIER = -5; // XXX
    /** 请求 选择 变更攻城时间. */
    public static final int C_OPCODE_SELECTWARTIME = -6; // XXX
    /** 请求 配置城墙上的弓箭手OK. */
    public static final int C_OPCODE_PUTBOWSOLDIER = -7; // XXX
    /** 请求 完成学习魔法(材料). */
    public static final int C_OPCODE_SKILLBUYOKITEM = -8; // XXX
    /** 未使用 - 请求 进入游戏(确定服务器登入讯息). */
    public static final int C_OPCODE_COMMONINFO = -9; // XXX

    // 3.52C Server Packet （服务端代码）
    /** 选取物品数量. */
    public static final int S_OPCODE_INPUTAMOUNT = 0;
    /** 交易状态(双方交易)交易是否成功. */
    public static final int S_OPCODE_TRADESTATUS = 1;
    /** 物品增加(背包). */
    public static final int S_OPCODE_ADDITEM = 2;
    /** 物品删除(背包). */
    public static final int S_OPCODE_DELETEINVENTORYITEM = 3;
    /** 宣告进入游戏. */
    public static final int S_OPCODE_LOGINTOGAME = 4;
    /** 角色移除(立即or非立即). */
    public static final int S_OPCODE_DETELECHAROK = 5;
    /** 角色盟徽(下载). */
    public static final int S_OPCODE_EMBLEM = 6;
    /** 将死亡的对象复活. */
    public static final int S_OPCODE_RESURRECTION = 7;
    /** 传送锁定(洞穴点坐标点)进入传送点. */
    public static final int S_OPCODE_TELEPORTLOCK = 8;
    /** 布告栏列表(讯息阅读). */
    public static final int S_OPCODE_BOARDREAD = 9;
    /** 封包盒子(多功能封包). */
    public static final int S_OPCODE_PACKETBOX = 10;
    /** 封包盒子(多功能封包). */
    public static final int S_OPCODE_ACTIVESPELLS = 10;
    /** 封包盒子(多功能封包). */
    public static final int S_OPCODE_SKILLICONGFX = 10;
    /** 封包盒子(多功能封包). */
    public static final int S_OPCODE_UNKNOWN2 = 10;
    /** 魔法效果:物件隐形or现形. */
    public static final int S_OPCODE_INVIS = 11;
    /** 角色列表. */
    public static final int S_OPCODE_CHARAMOUNT = 12;
    /** 聊天频道(一般or大喊). */
    public static final int S_OPCODE_NORMALCHAT = 13;
    /** 魔法效果:敏捷提升. */
    public static final int S_OPCODE_DEXUP = 14;
    /** 更新正义值. */
    public static final int S_OPCODE_LAWFUL = 15;
    /** 传送锁定(NPC瞬间移动)传送术或瞬间移动卷轴. */
    public static final int S_OPCODE_TELEPORT = 16;
    /** 服务器讯息(行数/行数,附加字串). */
    public static final int S_OPCODE_SERVERMSG = 17;
    /** 物件移动. */
    public static final int S_OPCODE_MOVEOBJECT = 18;
    /** 城堡宝库(存入资金). */
    public static final int S_OPCODE_DEPOSIT = 19;
    /** 设置围城时间. */
    public static final int S_OPCODE_WARTIME = 20;
    /** 城堡宝库(取出资金). */
    public static final int S_OPCODE_DRAWAL = 21;
    /** 魔法效果:暗盲咒术. */
    public static final int S_OPCODE_CURSEBLIND = 22;
    /** 选择一个目标. */
    public static final int S_OPCODE_SELECTTARGET = 23;
    /** 商店收购清单 NPC物品购买清单(人物卖出). */
    public static final int S_OPCODE_SHOWSHOPSELLLIST = 24;
    /** 佣兵配置清单(已有的). */
    public static final int S_OPCODE_PUTSOLDIER = 25;
    /** 画面中间红色讯息. */
    public static final int S_OPCODE_BLUEMESSAGE = 26;
    /** 设置税收. */
    public static final int S_OPCODE_TAXRATE = 28;
    /** 伺服器版本. */
    public static final int S_OPCODE_SERVERVERSION = 29;
    /** 角色皇冠. */
    public static final int S_OPCODE_CASTLEMASTER = 30;
    /** 魔法购买清单(金币). */
    public static final int S_OPCODE_SKILLBUY = 31;
    /** 更新物件外型. */
    public static final int S_OPCODE_POLY = 32;
    /** 邮件系统. */
    public static final int S_OPCODE_MAIL = 33;
    /** 移动锁定(座标异常重整)(疑似开加速器则会用这个封包将玩家锁定). */
    public static final int S_OPCODE_CHARLOCK = 34;
    /** 魔法or物品效果图示:勇敢药水类. */
    public static final int S_OPCODE_SKILLBRAVE = 36;
    /** 血盟战争讯息(编号,血盟名称,目标血盟名称). */
    public static final int S_OPCODE_WAR = 37;
    /** 损坏武器清单. */
    public static final int S_OPCODE_SELECTLIST = 38;
    /** 未使用 - 服务器登入讯息(使用string.tbl). */
    public static final int S_OPCODE_COMMONINFO = 40; // XXX
    /** 聊天频道(广播). */
    public static final int S_OPCODE_GLOBALCHAT = 40;
    /** 角色资讯(属性与能力值). */
    public static final int S_OPCODE_OWNCHARSTATUS = 41;
    /** 增加交易物品(双方交易). */
    public static final int S_OPCODE_TRADEADDITEM = 43;
    /** 立即中断连线. */
    public static final int S_OPCODE_DISCONNECT = 44;
    /** 物品状态更新(背包). */
    public static final int S_OPCODE_ITEMSTATUS = 45;
    /** 物品可用次数(背包). */
    public static final int S_OPCODE_ITEMAMOUNT = 45;
    /** 角色能力状态(力量,敏捷等). */
    public static final int S_OPCODE_OWNCHARSTATUS2 = 46;
    /** 物品名单(仓库). */
    public static final int S_OPCODE_SHOWRETRIEVELIST = 47;
    /** 魔法效果:防御类. */
    public static final int S_OPCODE_SKILLICONSHIELD = 48;
    /** 切换物件外观动作(长时间). */
    public static final int S_OPCODE_CHARVISUALUPDATE = 49;
    /** 魔法购买清单(材料). */
    public static final int S_OPCODE_SKILLBUYITEM = 50;
    /** 物件血条. */
    public static final int S_OPCODE_HPMETER = 51;
    /** 增加魔法进魔法名单. */
    public static final int S_OPCODE_ADDSKILL = 52;
    /** 产生动画 [自身]. */
    public static final int S_OPCODE_SKILLSOUNDGFX = 53;
    /** 物件属性(门). */
    public static final int S_OPCODE_ATTRIBUTE = 54;
    /** 物件攻击(伤害力变更封包类型为 writeH(0x0000)). */
    public static final int S_OPCODE_ATTACKPACKET = 56;
    /** NPC聊天频道(一般or大喊). */
    public static final int S_OPCODE_NPCSHOUT = 57;
    /** 物品名单(背包)插入批次道具. */
    public static final int S_OPCODE_INVLIST = 58;
    /** 魔法效果:水底呼吸图示. */
    public static final int S_OPCODE_BLESSOFEVA = 59;
    /** 交易封包(双方交易). */
    public static final int S_OPCODE_TRADE = 60;
    /** 戒指封包. */
    public static final int S_OPCODE_ABILITY = 61;
    /** 角色创造结果. */
    public static final int S_OPCODE_NEWCHARWRONG = 62;
    /** 角色封号. */
    public static final int S_OPCODE_CHARTITLE = 64;
    /** 更新游戏天气. */
    public static final int S_OPCODE_WEATHER = 65;
    /** 范围魔法. */
    public static final int S_OPCODE_RANGESKILLS = 66;
    /** 创造角色成功(新创). */
    public static final int S_OPCODE_NEWCHARPACK = 67;
    /** 未使用 - 学习魔法材料不足. */
    public static final int S_OPCODE_ITEMERROR = 68;
    /** 魔法效果:力量提升. */
    public static final int S_OPCODE_STRUP = 69;
    /** 选项确认视窗(Yes/No). */
    public static final int S_OPCODE_YES_NO = 70;
    /** 更新角色所在的地图. */
    public static final int S_OPCODE_MAPID = 71;
    /** 更新现在的地图 （水中）. */
    public static final int S_OPCODE_UNDERWATER = 71;
    /** 更新物件亮度. */
    public static final int S_OPCODE_LIGHT = 73;
    /** 播放音效. */
    public static final int S_OPCODE_SOUND = 74;
    /** 角色重置升级能力. */
    public static final int S_OPCODE_CHARRESET = 76;
    /** 宠物控制介面移除. */
    public static final int S_OPCODE_PETCTRL = 76;
    /** 商店贩售清单 NPC物品贩卖清单(人物买入). */
    public static final int S_OPCODE_SHOWSHOPBUYLIST = 77;
    /** 魔法效果:中毒. */
    public static final int S_OPCODE_POISON = 78;
    /** 更新物品显示名称(背包). */
    public static final int S_OPCODE_ITEMNAME = 80;
    /** 特别变身封包 - NPC改变外型. */
    public static final int S_OPCODE_SPOLY = 81;
    /** 更新物件面向. */
    public static final int S_OPCODE_CHANGEHEADING = 83;
    /** 更新角色防御属性. */
    public static final int S_OPCODE_OWNCHARATTRDEF = 84;
    /** 更新经验值. */
    public static final int S_OPCODE_EXP = 85;
    /** 更新MP显示(魔力与最大魔力). */
    public static final int S_OPCODE_MPUPDATE = 86;
    /** 角色名称变紫色. */
    public static final int S_OPCODE_PINKNAME = 87;
    /** 公告视窗. */
    public static final int S_OPCODE_COMMONNEWS = 88;
    /** 布告栏(对话视窗). */
    public static final int S_OPCODE_BOARD = 89;
    /** 角色记忆坐标名单/插入记忆座标. */
    public static final int S_OPCODE_BOOKMARKS = 90;
    /** 执行物件外观动作(短时间). */
    public static final int S_OPCODE_DOACTIONGFX = 91;
    /** 物品属性色彩状态(背包)祝福・诅咒状态变化. */
    public static final int S_OPCODE_ITEMCOLOR = 92;
    /** 更新当前游戏时间. */
    public static final int S_OPCODE_GAMETIME = 93;
    /** 初始化演算法. */
    public static final int S_OPCODE_INITPACKET = 95;
    /** 使用地图道具. */
    public static final int S_OPCODE_USEMAP = 97;
    /** 盟屋拍卖公告栏列表(血盟小屋名单). */
    public static final int S_OPCODE_HOUSELIST = 98;
    /** 更新魔攻与魔防. */
    public static final int S_OPCODE_SPMR = 100;
    /** 产生动画 [座标]. */
    public static final int S_OPCODE_EFFECTLOCATION = 102;
    /** 角色列表资讯. */
    public static final int S_OPCODE_CHARLIST = 103;
    /** 物品资讯讯息(鉴定). */
    public static final int S_OPCODE_IDENTIFYDESC = 107;
    /** 移除指定的魔法. */
    public static final int S_OPCODE_DELSKILL = 108;
    /** 交易商店清单(个人商店贩卖或购买). */
    public static final int S_OPCODE_PRIVATESHOPLIST = 109;
    /** 登入状态. */
    public static final int S_OPCODE_LOGINRESULT = 110;
    /** 物件封包. */
    public static final int S_OPCODE_CHARPACK = 112;
    /** 物件封包 (地面道具). */
    public static final int S_OPCODE_DROPITEM = 112;
    /** 聊天频道(密语). */
    public static final int S_OPCODE_WHISPERCHAT = 114;
    /** 魔法效果:精准目标. */
    public static final int S_OPCODE_TRUETARGET = 116;
    /** 魔法效果:醉酒/海浪波浪/三段加速. */
    public static final int S_OPCODE_LIQUOR = 117;
    /** 魔法效果:诅咒/麻痹类. */
    public static final int S_OPCODE_PARALYSIS = 118;
    /** 物件删除. */
    public static final int S_OPCODE_REMOVE_OBJECT = 121;
    /** 魔法or物品产生的效果:加速类. */
    public static final int S_OPCODE_SKILLHASTE = 122;
    /** 更新HP显示(体力与最大体力). */
    public static final int S_OPCODE_HPUPDATE = 123;
    /** 血盟小屋地图 [地点]. */
    public static final int S_OPCODE_HOUSEMAP = 124;
    /** 产生对话视窗(NPC). */
    public static final int S_OPCODE_SHOWHTML = 125;
    /** 雇请佣兵(佣兵购买视窗). */
    public static final int S_OPCODE_HIRESOLDIER = 126;
    /** 更新物件名称. */
    public static final int S_OPCODE_CHANGENAME = 127;

    // 未知
    /** 未使用 - 修理武器清单. */
    public static final int S_OPCODE_FIX_WEAPON_MENU = -10;
    /** 未使用 - 配置城墙上的弓箭手列表(佣兵购买视窗). */
    public static final int S_OPCODE_PUTBOWSOLDIERLIST = -11;
    /** 未使用 - 阅读邮件(旧). */
    public static final int S_OPCODE_LETTER = -12;
    /** 可配置排列佣兵数(HTML)(EX:雇用的总佣兵数:XX 可排列的佣兵数:XX ). */
    public static final int S_OPCODE_PUTHIRESOLDIER = -13;
    /** 未使用 - 更新血盟数据. */
    public static final int S_OPCODE_UPDATECLANID = -14;
    /** 未使用 - 物件新增主人. */
    public static final int S_OPCODE_NEWMASTER = -15;
    /** Ping Time. */
    public static final int S_OPCODE_PINGTIME = -16;
    /** 未使用 - 强制登出人物. */
    public static final int S_OPCODE_CHAROUT = -17;
    /** 未使用 - 画面中红色讯息(登入来源). */
    public static final int S_OPCODE_REDMESSAGE = -18;

    /** 封包代码. */
    private Opcodes() {
    }

//public class Opcodes {
//
//	public Opcodes() {
//	}	
//	/** 3.5C Client Packet */
//	public static final int C_OPCODE_BANPARTY = 0; // 请求驱逐队伍
//	public static final int C_OPCODE_SHIP = 1; // 请求下船
//	public static final int C_OPCODE_TELEPORTLOCK = 2; // 玩家传送锁定(回溯检测用)
//	public static final int C_OPCODE_SKILLBUYOK = 4; // 请求学习魔法
//	public static final int C_OPCODE_ADDBUDDY = 5; // 请求新增好友
//	public static final int C_OPCODE_WAREHOUSELOCK = 8; // 请求变更仓库密码 && 送出仓库密码
//	public static final int C_OPCODE_DROPITEM = 9; // 请求弃物品
//	public static final int C_OPCODE_BOARDNEXT = 11; // 请求看下一页告栏的讯息
//	public static final int C_OPCODE_PETMENU = 12; // 请求宠物回报选单
//	public static final int C_OPCODE_JOINCLAN = 13; // 请求加入血盟
//	public static final int C_OPCODE_GIVEITEM = 14; // 请求给予物品
//	public static final int C_OPCODE_USESKILL = 16; // 请求使用技能
//	public static final int C_OPCODE_RESULT = 17; // 请求取得列表中的项目
//	public static final int C_OPCODE_DELETECHAR = 19; // 请求删除角色
//	public static final int C_OPCODE_BOARD = 21; // 请求浏览公栏
//	public static final int C_OPCODE_TRADEADDCANCEL = 23; // 请求取消交易
//	public static final int C_OPCODE_USEITEM = 24; // 请求使用物品
//	public static final int C_OPCODE_PROPOSE = 25; // 请求结婚
//	public static final int C_OPCODE_BOARDDELETE = 26; // 请求删除公栏容
//	public static final int C_OPCODE_CHANGEHEADING = 27; // 请求改变角色面向
//	public static final int C_OPCODE_BOOKMARKDELETE = 28; // 请求删除记忆座标
//	public static final int C_OPCODE_SELECTLIST = 29; // 请求修理道具
//	public static final int C_OPCODE_RETURNTOLOGIN = 30;//要求回到选人画面
//	public static final int C_OPCODE_SELECTTARGET = 32; // 请求攻指定物件(宠物&召唤)
//	public static final int C_OPCODE_DELEXCLUDE = 33; // 请求使用开名单(拒指定人物讯息)
//	public static final int C_OPCODE_BUDDYLIST = 34; // 请求询好友名单
//	public static final int C_OPCODE_SENDLOCATION = 35; // 请求传送位置
//	public static final int C_OPCODE_TITLE = 37; // 请求赋予封号
//	public static final int C_OPCODE_TRADEADDOK = 38; // 请求完成交易
//	public static final int C_OPCODE_EMBLEM = 39; // 请求上传盟徽
//	public static final int C_OPCODE_MOVECHAR = 40; // 请求移动角色
//	public static final int C_OPCODE_CHECKPK = 41; // 请求询PK次数
//	public static final int C_OPCODE_COMMONCLICK = 42; // 请求下一 (伺服器公告)
//	public static final int C_OPCODE_QUITGAME = 43; // 请求离开游戏
//	public static final int C_OPCODE_DEPOSIT = 44; // 请求将资金存入城堡宝库
//	public static final int C_OPCODE_BEANFUN_LOGIN = 45; // 请求使用乐豆自动登伺服器 (未实装)
//	public static final int C_OPCODE_BOOKMARK = 46; // 请求增加记忆座标
//	public static final int C_OPCODE_SHOP = 47; // 请求开设个人商店
//	public static final int C_OPCODE_CHATWHISPER = 48; // 请求使用密语聊天频道
//	public static final int C_OPCODE_PRIVATESHOPLIST = 49; // 请求购买指定的个人商店商品
//	public static final int C_OPCODE_EXTCOMMAND = 52; // 请求角色表情动作
//	public static final int C_OPCODE_CLIENTVERSION = 54; // 请求验证客端版本
//	public static final int C_OPCODE_LOGINTOSERVER = 55; // 请求登角色
//	public static final int C_OPCODE_ATTR = 56; // 请求点选项目的结果
//	public static final int C_OPCODE_NPCTALK = 57; // 请求对话视窗
//	public static final int C_OPCODE_NEWCHAR = 58; // 请求创造角色
//	public static final int C_OPCODE_TRADE = 59; // 请求交易
//	public static final int C_OPCODE_DELETEBUDDY = 61; // 请求删除好友
//	public static final int C_OPCODE_BANCLAN = 62; // 请求驱逐血盟成员
//	public static final int C_OPCODE_FISHCLICK = 63; // 请求钓鱼收竿
//	public static final int C_OPCODE_LEAVECLANE = 65; // 请求离开血盟
//	public static final int C_OPCODE_TAXRATE = 66; // 请求配置收
//	public static final int C_OPCODE_RESTART = 70; // 请求重新开始
//	public static final int C_OPCODE_ENTERPORTAL = 71; // 请求传送 (进入地监)
//	public static final int C_OPCODE_SKILLBUY = 72; // 请求询可以学习的魔法清单
//	public static final int C_OPCODE_TELEPORT = 73; // 请求解除传送锁定
//	public static final int C_OPCODE_DELETEINVENTORYITEM = 74; // 请求删除物品
//	public static final int C_OPCODE_CHAT = 75; // 请求使用一般聊天频道
//	public static final int C_OPCODE_ARROWATTACK = 77; // 请求使用远距攻
//	public static final int C_OPCODE_USEPETITEM = 78; // 请求使用宠物装备
//	public static final int C_OPCODE_EXCLUDE = 79; // 请求使用拒名单(开指定人物讯息)
//	public static final int C_OPCODE_FIX_WEAPON_LIST = 80; // 请求询损坏的道具
//	public static final int C_OPCODE_PLEDGE = 84; // 请求询血盟成员
//	public static final int C_OPCODE_NPCACTION = 87; // 请求执行对话视窗的动作
//	public static final int C_OPCODE_EXIT_GHOST = 90; // 请求退出观看模式
//	public static final int C_OPCODE_CALL = 91; // 请求传送至指定的外挂使用者身旁
//	public static final int C_OPCODE_MAIL = 92; // 请求打开邮箱
//	public static final int C_OPCODE_WHO = 93; // 请求询游戏人数
//	public static final int C_OPCODE_PICKUPITEM = 94; // 请求拾取物品
//	public static final int C_OPCODE_CHARRESET = 95; // 要求重置人物点数
//	public static final int C_OPCODE_AMOUNT = 96; // 请求传回选取的数量
//	public static final int C_OPCODE_RANK = 103; // 请求给予角色血盟阶级
//	public static final int C_OPCODE_FIGHT = 104; // 请求决斗
//	public static final int C_OPCODE_DRAWAL = 105; // 请求领取城堡宝库资金
//	public static final int C_OPCODE_KEEPALIVE = 106; // 请求更新连线态
//	public static final int C_OPCODE_CHARACTERCONFIG = 108; // 请求纪快速键
//	public static final int C_OPCODE_CHATGLOBAL = 109; // 请求使用广播聊天频道
//	public static final int C_OPCODE_WAR = 110; // 请求宣战
//	public static final int C_OPCODE_CREATECLAN = 112; // 请求创立血盟
//	public static final int C_OPCODE_LOGINTOSERVEROK = 114; // 请求配置角色设定
//	public static final int C_OPCODE_LOGINPACKET = 115; // 请求登伺服器
//	public static final int C_OPCODE_DOOR = 116; // 请求开门或关门
//	public static final int C_OPCODE_ATTACK = 117; // 请求攻对象
//	public static final int C_OPCODE_TRADEADDITEM = 119; // 请求交易(添加物品)
//	public static final int C_OPCODE_SMS = 121; // 请求传送简讯
//	public static final int C_OPCODE_LEAVEPARTY = 123; // 请求退出队伍
//	public static final int C_OPCODE_CASTLESECURITY = 124; // 请求管理城治安
//	public static final int C_OPCODE_BOARDREAD = 125; // 请求读告单个栏讯息
//	public static final int C_OPCODE_CHANGECHAR = 126; // 请求切换角色
//	public static final int C_OPCODE_PARTYLIST = 127; // 请求询队伍成员
//	public static final int C_OPCODE_BOARDWRITE = 129; // 请求撰写新的告栏讯息
//	public static final int C_OPCODE_CREATEPARTY = 130; // 请求邀请加入队伍或建立队伍
//	public static final int C_OPCODE_CAHTPARTY = 131; // 请求聊天队伍
//	
//	/** 3.5C Server Packet */
//	public static final int S_OPCODE_PUTSOLDIER = 0; // 配置已的用佣兵
//	public static final int S_OPCODE_SKILLBUY_2 = 1; // 学习魔法 (何仑)
//	public static final int S_OPCODE_SHOWSHOPSELLLIST = 2; // 商店收购清单
//	public static final int S_OPCODE_PINGTIME = 3; // Ping Time
//	public static final int S_OPCODE_DETELECHAROK = 4; // 角色移除 (立即或非立即)
//	public static final int S_OPCODE_CHANGEHEADING = 5; // 物件面向
//	public static final int S_OPCODE_SKILLICONSHIELD = 6; // 魔法效果 : 防御
//	public static final int S_OPCODE_RANGESKILLS = 7; // 范围魔法
//	public static final int S_OPCODE_INPUTAMOUNT = 8; // 输入数量要生的数量
//	public static final int S_OPCODE_DELSKILL = 9; // 移除指定的魔法
//	public static final int S_OPCODE_PUTHIRESOLDIER = 10; // 配置佣兵
//	public static final int S_OPCODE_SKILLHASTE = 11; // 魔法或物品生的加速效果
//	public static final int S_OPCODE_CHARAMOUNT = 12; // 角色列表
//	public static final int S_OPCODE_BOOKMARKS = 13; // 插入记忆座标
//	public static final int S_OPCODE_EXCEPTION_3 = 14; // 例外事件_3
//	public static final int S_OPCODE_MPUPDATE = 15; // 魔力与最大魔力更新
//	public static final int S_OPCODE_EXCEPTION_2 = 16; // 例外事件_2
//	public static final int S_OPCODE_SERVERVERSION = 17; // 伺服器版本
//	public static final int S_OPCODE_CHARVISUALUPDATE = 18; // 切换物件外观动作
//	public static final int S_OPCODE_PARALYSIS = 19; // 魔法效果 : 麻痹类
//	public static final int S_OPCODE_MOVELOCK = 20; // 移动锁定封包(疑似开加速器则会用这个封包将玩家锁定)
//	public static final int S_OPCODE_DELETEINVENTORYITEM = 21; // 删除物品
//	public static final int S_OPCODE_NEW1 = 22; // 不明封包 (会变更头衔)
//	// 23 仿佛是伺服器选单
//	public static final int S_OPCODE_HIRESOLDIER = 24; // 用佣兵
//	public static final int S_OPCODE_PINKNAME = 25; // 角色名称变紫色
//	public static final int S_OPCODE_TELEPORT = 26; // 传送术或瞬间移动卷轴-传送锁定
//	public static final int S_OPCODE_INITPACKET = 27; // 初始化演算法
//	public static final int S_OPCODE_CHANGENAME = 28; // 改变物件名称
//	public static final int S_OPCODE_NEWCHARWRONG = 29; // 角色创造例外
//	public static final int S_OPCODE_DRAWAL = 30; // 领取城堡宝库资金
//	public static final int S_OPCODE_MAPID = 32; // 更新现在的地图
//	public static final int S_OPCODE_UNDERWATER = 32; // 更新现在的地图 （水中）
//	public static final int S_OPCODE_TRADEADDITEM = 33; // 增加交易物品封包
//	public static final int S_OPCODE_OWNCHARSTATUS = 34; // 角色属性与能力
//	public static final int S_OPCODE_EXCEPTION_1 = 35; // 例外事件_1
//	public static final int S_OPCODE_COMMONNEWS = 36; // 公告视窗
//	public static final int S_OPCODE_TRUETARGET = 37; // 法术效果-精准目标
//	public static final int S_OPCODE_HPUPDATE = 38; // 体力与最大体力更新
//	public static final int S_OPCODE_TRADESTATUS = 39; // 交易是否成功
//	public static final int S_OPCODE_SHOWSHOPBUYLIST = 40; // 商店贩售清单
//	public static final int S_OPCODE_LOGINTOGAME = 41; // 进入游戏
//	public static final int S_OPCODE_INVIS = 42; // 物件隐形或现形
//	public static final int S_OPCODE_CHARRESET = 43; // 角色重置
//	public static final int S_OPCODE_PETCTRL = 43; // 宠物控制介面移除
//	public static final int S_OPCODE_WARTIME = 44; // 设定围成时间
//	public static final int S_OPCODE_IDENTIFYDESC = 45; // 物品资讯讯息
//	public static final int S_OPCODE_BLUEMESSAGE = 46; // 红色讯息
//	public static final int S_OPCODE_POISON = 47; // 魔法效果:中毒
//	public static final int S_OPCODE_GAMETIME = 48; // 更新目前游戏时间
//	public static final int S_OPCODE_SKILLBUY = 50; // 魔法购买 (金币)
//	public static final int S_OPCODE_TRADE = 51; // 交易封包
//	public static final int S_OPCODE_WAR = 52; // 血盟战争
//	public static final int S_OPCODE_NPCSHOUT = 53; // 一般聊天或大喊聊天频道
//	public static final int S_OPCODE_COMMONNEWS2 = 54; // 系统讯息视窗
//	public static final int S_OPCODE_CHARPACK = 55; // 物件封包
//	public static final int S_OPCODE_DROPITEM = 55; // 物件封包 (道具)
//	public static final int S_OPCODE_NORMALCHAT = 56; // 一般聊天或大喊聊天频道
//	public static final int S_OPCODE_MAIL = 57; // 邮件封包
//	public static final int S_OPCODE_STRUP = 58; // 力量提升封包
//	public static final int S_OPCODE_CURSEBLIND = 59; // 法术效果-暗盲咒术
//	public static final int S_OPCODE_ITEMCOLOR = 60; // 物品属性态
//	public static final int S_OPCODE_USECOUNT = 61; // 魔杖的使用次数
//	public static final int S_OPCODE_MOVEOBJECT = 62; // 移动物件
//	public static final int S_OPCODE_BOARD = 63; // 告栏 (对话视窗)
//	public static final int S_OPCODE_ADDITEM = 64; // 物品增加封包
//	public static final int S_OPCODE_SHOWRETRIEVELIST = 65; // 仓库物品名单
//	public static final int S_OPCODE_RESTART = 66; // 强制重新选择角色
//	public static final int S_OPCODE_YES_NO = 68; // 确认视窗
//	public static final int S_OPCODE_INVLIST = 69; // 插入批次道具
//	public static final int S_OPCODE_OWNCHARSTATUS2 = 70; // 角色能力
//	public static final int S_OPCODE_NEW3 = 71; // 不明封包 (商店)
//	public static final int S_OPCODE_HPMETER = 72; // 物件血条
//	public static final int S_OPCODE_FIX_WEAPON_MENU = 73; // 修理武器清单
//	public static final int S_OPCODE_SELECTLIST = 73; // 损坏武器名单
//	public static final int S_OPCODE_TELEPORTLOCK = 74; // 进入传送点-传送锁定
//	public static final int S_OPCODE_PRIVATESHOPLIST = 75; // 个人商店贩卖或购买
//	public static final int S_OPCODE_GLOBALCHAT = 76; // 广播聊天频道
//	public static final int S_OPCODE_SYSMSG = 76; // 伺服器讯息
//	public static final int S_OPCODE_ADDSKILL = 77; // 增加魔法进魔法名单
//	public static final int S_OPCODE_SKILLBRAVE = 78; // 魔法或物品效果图示-勇气药水类
//	public static final int S_OPCODE_WEATHER = 79; // 游戏天气
//	public static final int S_OPCODE_CHARLIST = 80; // 角色资讯
//	public static final int S_OPCODE_OWNCHARATTRDEF = 81; // 角色属性
//	public static final int S_OPCODE_EFFECTLOCATION = 82; // 生动画 [座标]
//	public static final int S_OPCODE_SPMR = 83; // 魔法攻力与魔法防御力
//	public static final int S_OPCODE_SELECTTARGET = 84; // 选择一个目标
//	public static final int S_OPCODE_BOARDREAD = 85; // 告栏(讯息读)
//	public static final int S_OPCODE_SKILLSOUNDGFX = 86; // 生动画 [自身]
//	public static final int S_OPCODE_DISCONNECT = 88; // 立即中断连线
//	public static final int S_OPCODE_SPECIALATTACK = 89; // 特殊攻
//	public static final int S_OPCODE_SPOLY = 90; // 特别变身封包
//	public static final int S_OPCODE_SHOWHTML = 91; // 生对话视窗
//	public static final int S_OPCODE_ABILITY = 92; // 配置封包
//	public static final int S_OPCODE_DEPOSIT = 93; // 存入资金城堡宝库
//	public static final int S_OPCODE_ATTACKPACKET = 94; // 物件攻
//	public static final int S_OPCODE_ITEMSTATUS = 95; // 物品态更新
//	public static final int S_OPCODE_ITEMAMOUNT = 95; // 物品可用次数
//	public static final int S_OPCODE_NEW2 = 97; // 不明封包 (会将头衔变更为空白)
//	public static final int S_OPCODE_NEWCHARPACK = 98; // 角色创造成功
//	public static final int S_OPCODE_PACKETBOX = 100; // 多功能封包
//	public static final int S_OPCODE_ACTIVESPELLS = 100; // 多功能封包
//	public static final int S_OPCODE_SKILLICONGFX = 100; // 多功能封包
//	public static final int S_OPCODE_UNKNOWN2 = 100; // 多功能封包
//	public static final int S_OPCODE_DEXUP = 101; // 敏捷提升封包
//	public static final int S_OPCODE_LIGHT = 102; // 物件亮度
//	public static final int S_OPCODE_POLY = 103; // 改变外型
//	public static final int S_OPCODE_SOUND = 104; // 拨放音效
//	public static final int S_OPCODE_BLESSOFEVA = 106; // 效果图示 (水底呼吸)
//	public static final int S_OPCODE_CHARTITLE = 108; // 角色封号
//	public static final int S_OPCODE_TAXRATE = 109; // 设定收封包
//	public static final int S_OPCODE_ITEMNAME = 110; // 物品名称
//	public static final int S_OPCODE_MATERIAL = 111; // 魔法学习-材料不足
//	public static final int S_OPCODE_WHISPERCHAT = 113; // 密语聊天频道
//	public static final int S_OPCODE_REDMESSAGE = 114; // 画面正中出现红色/新增未使用
//	public static final int S_OPCODE_ATTRIBUTE = 115; // 物件属性
//	public static final int S_OPCODE_EXP = 116; // 经验更新
//	public static final int S_OPCODE_LAWFUL = 117; // 正义更新
//	public static final int S_OPCODE_LOGINRESULT = 118; // 登入态
//	public static final int S_OPCODE_CASTLEMASTER = 119; // 角色皇冠
//	public static final int S_OPCODE_SERVERMSG = 120; // 系统讯息
//	public static final int S_OPCODE_HOUSEMAP = 121; // 血盟小屋地图 [地点]
//	public static final int S_OPCODE_RESURRECTION = 122; // 将死亡的对象复活
//	public static final int S_OPCODE_DOACTIONGFX = 123; // 执行物件外观动作
//	public static final int S_OPCODE_REMOVE_OBJECT = 124; // 物件删除
//	public static final int S_OPCODE_EMBLEM = 125; // 下载血盟徽章
//	public static final int S_OPCODE_LIQUOR = 126; // 海浪波浪
//	public static final int S_OPCODE_HOUSELIST = 127; // 血盟小屋名单
//	
//	/** 3.2C ServerPacket */
//	public static final int S_OPCODE_USEMAP = 130;
//	public static final int S_LETTER = 131;
//
//	/** 3.3C Client Packet */
//	public static final int C_OPCODE_HIRESOLDIER = 141;//要求佣佣兵列表(购买)
//	public static final int C_OPCODE_CLAN = 142; //要求血盟数据(例如盟标)**[未抓取]
//	public static final int C_OPCODE_CHANGEWARTIME = 144;//修正城堡总管全部功能
//	public static final int C_OPCODE_PUTSOLDIER = 145;//要求配置已用士兵
//	public static final int C_OPCODE_SELECTWARTIME = 146;//要求选择 变更攻城时间(but3.3C无使用)
//	public static final int C_OPCODE_PUTBOWSOLDIER = 147;//要求配置城墙上弓手

	//	/**3.3C ClientPacket*/
//	public static final int C_OPCODE_EXIT_GHOST = 0;//要求退出观看模式
//	public static final int C_OPCODE_RETURNTOLOGIN = 1;//要求回到选人画面
//	public static final int C_OPCODE_LOGINTOSERVER = 5;//要求进入游戏
//	public static final int C_OPCODE_HIRESOLDIER = 7;//要求佣佣兵列表(购买)
//	public static final int C_OPCODE_BOOKMARKDELETE = 8;//要求删除记忆座标
//	public static final int C_OPCODE_DROPITEM = 10;//要求弃物品
//	public static final int C_OPCODE_RESULT = 11;//要求列表物品取得 /3.3C领取宠物
//	public static final int C_OPCODE_SELECTTARGET = 13;//要求攻指定物件(宠物&召唤)
//	public static final int C_OPCODE_COMMONCLICK = 14 ;//要求下一 ( 公告资讯 )
//	public static final int C_OPCODE_SETCASTLESECURITY = 15;//要求设置治安管理
//	public static final int C_OPCODE_CLAN = 16;////要求血盟数据(例如盟标)
//	public static final int C_OPCODE_FIX_WEAPON_LIST = 18;//要求维修物品清单
//	public static final int C_OPCODE_USESKILL = 19;//要求使用技能
//	public static final int C_OPCODE_TRADEADDCANCEL = 21;//要求取消交易(个人商店)
//	public static final int C_OPCODE_CHANGEPASS = 22;//要求变更号密码(登陆界面)
//	public static final int C_OPCODE_DEPOSIT = 24;//要求存入资金
//	public static final int C_OPCODE_TRADE = 25;//要求交易(个人)
//	public static final int C_OPCODE_MOVE_CHECK = 26;//人物移动回硕检测 <= 3.3C added
//	public static final int C_OPCODE_ENTERPORTAL = 27;//要求传送 (进入地监)
//	public static final int C_OPCODE_DRAWAL = 28;//要求领出资金
//	public static final int C_OPCODE_SECOND_PLEDGE = 31;//第二次要求询血盟成员
//	public static final int C_OPCODE_RANK = 31;//要求给予角色血盟阶级
//	public static final int C_OPCODE_TRADEADDOK = 32;//要求完成交易(个人)
//	public static final int C_OPCODE_PLEDGE = 33;//要求询血盟成员
//	public static final int C_OPCODE_QUITGAME = 35;//要求离开游戏
//	public static final int C_OPCODE_BANCLAN = 36;//要求驱逐人物离开血盟
//	public static final int C_OPCODE_WAREHOUSELOCK = 37;//要求变更仓库密码 && 送出仓库密码
//	public static final int C_OPCODE_TITLE = 39;//要求赋予封号
//	public static final int C_OPCODE_PICKUPITEM = 41;//要求取物品
//	public static final int C_OPCODE_CHARRESET = 42;//要求重置人物点数
//	public static final int C_OPCODE_NEWCHAR = 43;//要求创造角色 
//	public static final int C_OPCODE_DOOR = 44;//要求开关门
//	public static final int C_OPCODE_PETMENU = 45;//要求宠物回报选单
//	public static final int C_OPCODE_CLIENTVERSION = 46; //要求登入测试 (接收伺服器版本)
//	public static final int C_OPCODE_CREATECLAN = 48;//要求创立血盟
//	public static final int C_OPCODE_CHANGECHAR = 50;//要求切换角色
//	public static final int C_OPCODE_USEITEM = 51;//要求使用物品
//	public static final int C_OPCODE_SKILLBUYOK = 52;//要求学习魔法 完成
//	public static final int C_OPCODE_UNKOWN1 = 53;//用端自动请求在线公告
//	public static final int C_OPCODE_NPCTALK = 55;//要求物件对话视窗
//	public static final int C_OPCODE_TELEPORT = 56;//要求传送 更新周围物件 (无动画传送后)
//	public static final int C_OPCODE_SHIP = 58;//要求下船
//	public static final int C_OPCODE_CHANGEWARTIME = 102;//修正城堡总管全部功能
//	public static final int C_OPCODE_USEPETITEM = 60;//要求使用宠物装备
//	public static final int C_OPCODE_SKILLBUY = 63;//要求学习魔法(金币)
//	public static final int C_OPCODE_ADDBUDDY = 64;//要求新增好友
//	public static final int C_OPCODE_BOARDWRITE = 65;//要求写入公栏讯息
//	public static final int C_OPCODE_BOARDNEXT = 66;//要求下一页 (公栏)
//	public static final int C_OPCODE_FISHCLICK = 67;//要求钓鱼收竿
//	public static final int C_OPCODE_LEAVECLANE = 69;//要求离血盟
//	public static final int C_OPCODE_LOGINTOSERVEROK = 70;//登入伺服器OK
//	public static final int C_OPCODE_BUDDYLIST = 71;//要求询朋友名单
//	public static final int C_OPCODE_MOVECHAR = 73;//要求角色移动
//	public static final int C_OPCODE_ATTR = 74;//要求点选项目的结果
//	public static final int C_OPCODE_BOARDDELETE = 75;//要求删除公栏容
//	public static final int C_OPCODE_DELEXCLUDE = 76;//要求使用开名单(拒指定人物讯息)
//	public static final int C_OPCODE_EXCLUDE = 76;//要求使用拒名单(开指定人物讯息)
//	public static final int C_OPCODE_CHATGLOBAL = 77;//要求使用广播聊天频道
//	public static final int C_OPCODE_PROPOSE = 78;//要求结婚
//	public static final int C_OPCODE_TRADEADDITEM = 79;//要求交易(添加物品)
//	public static final int C_OPCODE_CASTLESECURITY = 81;//要求治安管理  OK
//	public static final int C_OPCODE_SHOP = 82;//要求开个人商店
//	public static final int C_OPCODE_CHAT = 83;//要求使用一般聊天频道
//	public static final int C_OPCODE_PUTSOLDIER = 84;//要求配置已用士兵
//	public static final int C_OPCODE_LEAVEPARTY = 85;//要求离队伍
//	public static final int C_OPCODE_PARTYLIST = 86;//要求看队伍
//	public static final int C_OPCODE_SENDLOCATION = 87; // 地图(3.3C 新增)
//	public static final int C_OPCODE_BOARDREAD = 88;//要求读告单个栏讯息
//	public static final int C_OPCODE_CALL = 89;//要求召唤到身边(gm)
//	public static final int C_OPCODE_WAR = 91;//要求宣战
//	public static final int C_OPCODE_CHECKPK = 92;//要求询PK次数
//	public static final int C_OPCODE_CHANGEHEADING = 93;//要求改变角色面向
//	public static final int C_OPCODE_AMOUNT = 94;//要求确定数量选取
//	public static final int C_OPCODE_WHO = 95;//要求询游戏人数
//	public static final int C_OPCODE_FIGHT = 96;//要求决斗
//	public static final int C_OPCODE_NPCACTION = 97;//要求物件对话视窗结果
//	public static final int C_OPCODE_CHARACTERCONFIG = 100;//要求纪快速键
//	public static final int C_OPCODE_ATTACK = 101;//要求角色攻
//	public static final int C_OPCODE_SELECTWARTIME = 102;//要求选择 变更攻城时间(but3.3C无使用)
//	public static final int C_OPCODE_BOARD = 103;//要求读取公栏
//	public static final int C_OPCODE_PRIVATESHOPLIST = 104;//要求个人商店 （物品列表）
//	public static final int C_OPCODE_LOGINPACKET = 105;//要求登入伺服器
//	public static final int C_OPCODE_SELECTLIST = 106;//要求物品维修
//	public static final int C_OPCODE_MAIL = 107;//要求打开邮箱
//	public static final int C_OPCODE_EXTCOMMAND = 108;//要求角色表情动作
//	public static final int C_OPCODE_DELETECHAR = 110;//要求删除角色
//	public static final int C_OPCODE_DELETEBUDDY = 112;//要求删除好友
//	public static final int C_OPCODE_ARROWATTACK = 113;//要求使用远距武器
//	public static final int C_OPCODE_EMBLEM = 114;//要求上传盟标
//	public static final int C_OPCODE_BANPARTY = 115;//要求出队伍
//	public static final int C_OPCODE_CHATWHISPER = 116;//要求使用密语聊天频道
//	public static final int C_OPCODE_SMS = 117;//要求寄送简讯
//	public static final int C_OPCODE_PUTHIRESOLDIER = 118;//要求配置已佣佣兵 OK
//	public static final int C_OPCODE_BOOKMARK = 119;//要求增加记忆座标
//	public static final int C_OPCODE_PUTBOWSOLDIER = 120;//要求配置城墙上弓手
//	public static final int C_OPCODE_KEEPALIVE = 121;//要求更新时间
//	public static final int C_OPCODE_TAXRATE = 122;//要求收设定封包
//	public static final int C_OPCODE_GIVEITEM = 124;//要求给予物品
//	public static final int C_OPCODE_JOINCLAN = 125;//要求加入血盟
//	public static final int C_OPCODE_DELETEINVENTORYITEM = 126;//要求删除物品
//	public static final int C_OPCODE_RESTART = 127;//要求死亡后重新开始
//	public static final int C_OPCODE_CREATEPARTY = 130;//要求邀请加入队伍(要求创立队伍)
//	public static final int C_OPCODE_CAHTPARTY = 131;//要求人物移出队伍
//	
//	/**3.2C ServerPacket*/
//	public static final int S_OPCODE_COMMONNEWS2 = 0;
//	public static final int S_OPCODE_USEMAP = 71;
//	public static final int S_LETTER = 90;
//
//	/**3.3C ServerPacket*/
//	public static final int S_OPCODE_BLUEMESSAGE = 0;//蓝色讯息 (使用String-h.tbl)
//	public static final int S_OPCODE_BLESSOFEVA = 1;//效果图示 (水底呼吸)
//	public static final int S_OPCODE_NPCSHOUT = 3;//非玩家聊天频道 (一般 & 大喊 )NPC
//	public static final int S_OPCODE_RESURRECTION = 4;//物件复活
//	public static final int S_OPCODE_BOARDREAD = 5;//告栏(讯息读)
//	public static final int S_OPCODE_CASTLEMASTER = 6;//角色皇冠 3.3C
//	public static final int S_OPCODE_FIX_WEAPON_MENU = 7;//修理武器清单 (3.3C新增)
//	public static final int S_OPCODE_SELECTLIST = 7;//损坏武器名单
//	public static final int S_OPCODE_ADDSKILL = 8;//增加魔法进魔法名单
//	public static final int S_OPCODE_CHARVISUALUPDATE = 9;//物件动作种类 (长时间)
//	public static final int S_OPCODE_COMMONNEWS = 10;//公告视窗
//	public static final int S_OPCODE_CHARAMOUNT = 11;//角色列表
//	public static final int S_OPCODE_PARALYSIS = 12;//魔法效果 : 诅咒类 {编号 }麻痹,
//	public static final int S_OPCODE_REDMESSAGE = 13; //画面正中出现红色/新增未使用
//	public static final int S_OPCODE_INPUTAMOUNT = 14;//拍卖公告栏选取金币数量 (选取物品数量)
//	public static final int S_OPCODE_SKILLSOUNDGFX = 15;//生动画 [物件]
//	public static final int S_OPCODE_IDENTIFYDESC = 16;//物品资讯讯息 {使用String-h.tbl}
//	public static final int S_OPCODE_EFFECTLOCATION = 18;//生动画 [地点]
//	public static final int S_OPCODE_MAIL = 19;//邮件封包
//	public static final int S_OPCODE_SHOWRETRIEVELIST = 21;//仓库物品名单
//	public static final int S_OPCODE_HOUSELIST = 22;//血盟小屋名单
//	public static final int S_OPCODE_SKILLBUY = 23;//魔法购买 (金币)
//	public static final int S_OPCODE_GLOBALCHAT = 24;//广播聊天频道
//	public static final int S_OPCODE_SYSMSG = 24;//广播聊天频道/伺服器讯息(字串)
//	public static final int S_OPCODE_CURSEBLIND = 25;//魔法效果 - 暗盲咒术 {编号}
//	public static final int S_OPCODE_INVLIST = 26;//道具栏全物品
//	public static final int S_OPCODE_CHARPACK = 27;//物件封包
//	public static final int S_OPCODE_DROPITEM = 27;//弃物品封包
//	public static final int S_OPCODE_SERVERMSG = 29;//伺服器讯息 (行数)/(行数, 附加字串 )
//	public static final int S_OPCODE_NEWCHARPACK = 31;//创造角色封包
//	public static final int S_OPCODE_DELSKILL = 34;//移除魔法出魔法名单
//	public static final int S_OPCODE_LOGINTOGAME = 35;//进入游戏
//	public static final int S_OPCODE_WHISPERCHAT = 36;//要求使用密语聊天频道
//	public static final int S_OPCODE_DRAWAL = 37;//取出城堡宝库金币
//	public static final int S_OPCODE_CHARLIST = 38;//角色资讯
//	public static final int S_OPCODE_EMBLEM = 39;//角色盟徽
//	public static final int S_OPCODE_ATTACKPACKET = 40;//物件攻
//	public static final int S_OPCODE_SPMR = 42;//魔法攻力与魔法防御力
//	public static final int S_OPCODE_OWNCHARSTATUS = 43;//角色属性与能力
//	public static final int S_OPCODE_RANGESKILLS = 44;//范围魔法
//	public static final int S_OPCODE_SHOWSHOPSELLLIST = 45;//NPC物品贩卖
//	public static final int S_OPCODE_INVIS = 47;//物件隐形 & 现形
//	public static final int S_OPCODE_NORMALCHAT = 48;//一般聊天频道
//	public static final int S_OPCODE_SKILLHASTE = 49;//魔法|物品效果 {加速}
//	public static final int S_OPCODE_TAXRATE = 50;//收设定封包
//	public static final int S_OPCODE_WEATHER = 51;//游戏天气
//	public static final int S_OPCODE_HIRESOLDIER = 52;//佣佣兵 佣兵名单
//	public static final int S_OPCODE_WAR = 53;//血盟战争讯息 {编号,血盟名称,目标血盟名称}
//	public static final int S_OPCODE_TELEPORTLOCK = 54;//人物回硕检测  OR 传送锁定 (无动画)
//	public static final int S_OPCODE_PINKNAME = 55;//角色名称变紫色
//	public static final int S_OPCODE_ITEMSTATUS = 56;//物品态更新
//	public static final int S_OPCODE_ITEMAMOUNT = 56;//物品可用次数
//	public static final int S_OPCODE_PRIVATESHOPLIST = 57;//角色个人商店 {购买}
//	public static final int S_OPCODE_DETELECHAROK = 58;//角色移除 [非立即]/7天后
//	public static final int S_OPCODE_BOOKMARKS = 59;//角色座标名单
//	public static final int S_OPCODE_INITPACKET = 60;//初始化OpCodes
//	public static final int S_OPCODE_MOVEOBJECT = 62;//物件移动
//	public static final int S_OPCODE_PUTSOLDIER = 63;//配置已用士兵
//	public static final int S_OPCODE_TELEPORT = 64;//要求传送 (有动画)
//	public static final int S_OPCODE_STRUP = 65;//力量提升封包
//	public static final int S_OPCODE_LAWFUL = 66;//正义更新
//	public static final int S_OPCODE_SELECTTARGET = 67;//选择一个目标
//	public static final int S_OPCODE_ABILITY = 68;//戒指
//	public static final int S_OPCODE_HPMETER = 69;//物件血条
//	public static final int S_OPCODE_ATTRIBUTE = 70;//物件属性
//	public static final int S_OPCODE_SERVERVERSION = 72;//伺服器版本
//	public static final int S_OPCODE_EXP = 73;//经验更新封包
//	public static final int S_OPCODE_MPUPDATE = 74;//魔力更新
//	public static final int S_OPCODE_CHANGENAME = 75;//改变物件名称
//	public static final int S_OPCODE_POLY = 76;//改变外型
//	public static final int S_OPCODE_MAPID = 77;//更新角色所在的地图
//	public static final int S_OPCODE_ITEMCOLOR = 79;//物品态
//	public static final int S_OPCODE_OWNCHARATTRDEF = 80;//角色防御 & 属性 更新
//	public static final int S_OPCODE_PACKETBOX = 82;//角色选择视窗/开拒名单(封包盒子)
//	public static final int S_OPCODE_ACTIVESPELLS = 82;
//	public static final int S_OPCODE_SKILLICONGFX = 82;
//	public static final int S_OPCODE_UNKNOWN2 = 82;
//	public static final int S_OPCODE_DELETEINVENTORYITEM = 83;//物品删除
//	public static final int S_OPCODE_RESTART = 84;//角色重新选择 返回选人画面 功能未知
//	public static final int S_OPCODE_PINGTIME = 85;//Ping Time
//	public static final int S_OPCODE_DEPOSIT = 86;//存入资金城堡宝库 (2)
//	public static final int S_OPCODE_TRUETARGET = 88;//魔法动画 {精准目标}
//	public static final int S_OPCODE_HOUSEMAP = 89;//血盟小屋地图 [地点]
//	public static final int S_OPCODE_CHARTITLE = 90;//角色封号
//	public static final int S_OPCODE_DEXUP = 92;//敏捷提升封包
//	public static final int S_OPCODE_CHANGEHEADING = 94;//物件面向
//	public static final int S_OPCODE_BOARD = 96;//告栏 (讯息列表)
//	public static final int S_OPCODE_LIQUOR = 97;//海底波纹
//	public static final int S_OPCODE_TRADESTATUS = 99;//交易态
//	public static final int S_OPCODE_SPOLY = 100;//特别变身封包
//	public static final int S_OPCODE_UNDERWATER = 101;//更新角色所在的地图 （水下）
//	public static final int S_OPCODE_SKILLBRAVE = 102;//魔法|物品效果图示 {勇敢药水类}
//	public static final int S_OPCODE_PUTHIRESOLDIER = 103;//配置佣兵
//	public static final int S_OPCODE_POISON = 104 ;//魔法效果:中毒 { 编号 }
//	public static final int S_OPCODE_DISCONNECT = 105;//立即中断连线
//	public static final int S_OPCODE_NEWCHARWRONG = 106;//角色创造失败
//	public static final int S_OPCODE_REMOVE_OBJECT = 107;//物件删除
//	public static final int S_OPCODE_NPC_ATTACKPACKET = 108;//NPC攻 用于特殊攻?!
//	public static final int S_OPCODE_ADDITEM = 110;//物品增加封包
//	public static final int S_OPCODE_TRADE = 111;//交易封包
//	public static final int S_OPCODE_OWNCHARSTATUS2 = 112;//角色态 (2)
//	public static final int S_OPCODE_SHOWHTML = 113;//生对话视窗
//	public static final int S_OPCODE_SKILLICONSHIELD = 114;//魔法效果 : 防御
//	public static final int S_OPCODE_DOACTIONGFX = 115;//物件动作种类 (短时间)
//	public static final int S_OPCODE_TRADEADDITEM = 116;//增加交易物品封包
//	public static final int S_OPCODE_YES_NO = 117;//选项封包 {Yes || No}
//	public static final int S_OPCODE_HPUPDATE = 118;//体力更新
//	public static final int S_OPCODE_SHOWSHOPBUYLIST = 119;//物品购买
//	public static final int S_OPCODE_GAMETIME = 120;//更新目前游戏时间 ( 游戏时间 )
//	public static final int S_OPCODE_PETCTRL = 121;//宠物控制介面移除
//	public static final int S_OPCODE_CHARRESET = 121; //角色重置
//	public static final int S_OPCODE_SOUND = 122;//拨放音效
//	public static final int S_OPCODE_LIGHT = 123;//物件亮度
//	public static final int S_OPCODE_LOGINRESULT = 124;//登入态
//	public static final int S_OPCODE_PUTBOWSOLDIERLIST = 125;//配置墙上弓手
//	public static final int S_OPCODE_WARTIME = 126;//攻城时间设定
//	public static final int S_OPCODE_ITEMNAME = 127;//物品显示名称

/*
	// 3.0 ClientPacket
	public static final int C_OPCODE_BOOKMARK = 0;

	public static final int C_OPCODE_FIGHT = 3;

	public static final int C_OPCODE_KEEPALIVE = 4;

	public static final int C_OPCODE_ATTACK = 5;

	public static final int C_OPCODE_CHANGEHEADING = 6;

	public static final int C_OPCODE_PICKUPITEM = 7;

	public static final int C_OPCODE_SHOP = 8;

	public static final int C_OPCODE_DELETEBUDDY = 10;

	public static final int C_OPCODE_LEAVEPARTY = 11;

	public static final int C_OPCODE_CHARACTERCONFIG = 14;

	public static final int C_OPCODE_MOVECHAR = 15;

	public static final int C_OPCODE_CHANGECHAR = 16;

	public static final int C_OPCODE_PRIVATESHOPLIST = 17;

	public static final int C_OPCODE_CHAT = 18;

	public static final int C_OPCODE_BOARDREAD = 19;

	public static final int C_OPCODE_TRADEADDITEM = 20;

	public static final int C_OPCODE_PROPOSE = 22;

	public static final int C_OPCODE_HIRESOLDIER = 23;

	public static final int C_OPCODE_BOARD = 24;

	public static final int C_OPCODE_LOGINTOSERVEROK = 25;

	public static final int C_OPCODE_ENTERPORTAL = 26;

	public static final int C_OPCODE_LEAVECLANE = 27;

	public static final int C_OPCODE_CALL = 29;

	public static final int C_OPCODE_TRADE = 30;

	public static final int C_OPCODE_SKILLBUYOK = 31;

	public static final int C_OPCODE_DELEXCLUDE = 32;

	public static final int C_OPCODE_SHIP = 33;

	public static final int C_OPCODE_CLIENTVERSION = 34;

	public static final int C_OPCODE_EXTCOMMAND = 38;

	public static final int C_OPCODE_TRADEADDCANCEL = 41;

	public static final int C_OPCODE_DRAWAL = 42;

	public static final int C_OPCODE_COMMONCLICK = 46;

	public static final int C_OPCODE_SELECTTARGET = 47;

	public static final int C_OPCODE_NEWCHAR = 50;

	public static final int C_OPCODE_FIX_WEAPON_LIST = 51;

	public static final int C_OPCODE_DROPITEM = 52;

	public static final int C_OPCODE_DELETECHAR = 54;

	public static final int C_OPCODE_ADDBUDDY = 56;

	public static final int C_OPCODE_WHO = 57;

	public static final int C_OPCODE_BOARDDELETE = 60;

	public static final int C_OPCODE_TRADEADDOK = 61;

	public static final int C_OPCODE_CREATECLAN = 62;

	public static final int C_OPCODE_ATTR = 63;

	public static final int C_OPCODE_ARROWATTACK = 64;

	public static final int C_OPCODE_NPCACTION = 65;

	public static final int C_OPCODE_TITLE = 66;

	public static final int C_OPCODE_DEPOSIT = 68;

	public static final int C_OPCODE_DELETEINVENTORYITEM = 69;

	public static final int C_OPCODE_CHECKPK = 70;

	public static final int C_OPCODE_BANPARTY = 72;

	public static final int C_OPCODE_CLAN = 73;

	public static final int C_OPCODE_DOOR = 75;

	public static final int C_OPCODE_PLEDGE = 76;

	public static final int C_OPCODE_PARTY = 77;

	public static final int C_OPCODE_RANK = 78;

	public static final int C_OPCODE_TELEPORT = 79;

	public static final int C_OPCODE_CHARRESET = 80;

	public static final int C_OPCODE_RESTART = 82;

	public static final int C_OPCODE_PETMENU = 83;

	public static final int C_OPCODE_BOARDWRITE = 84;

	public static final int C_OPCODE_GIVEITEM = 85;

	public static final int C_OPCODE_BOARDBACK = 87;

	public static final int C_OPCODE_LOGINTOSERVER = 89;

	public static final int C_OPCODE_CHATWHISPER = 92;

	public static final int C_OPCODE_SKILLBUY = 93;

	public static final int C_OPCODE_JOINCLAN = 94;

	public static final int C_OPCODE_RETURNTOLOGIN = 95;

	public static final int C_OPCODE_CHANGEWARTIME = 98;

	public static final int C_OPCODE_WAR = 101;

	public static final int C_OPCODE_BANCLAN = 103;

	public static final int C_OPCODE_RESULT = 104;

	public static final int C_OPCODE_BUDDYLIST = 109;

	public static final int C_OPCODE_TAXRATE = 110;

	public static final int C_OPCODE_USEPETITEM = 111;

	public static final int C_OPCODE_SELECTLIST = 112;

	public static final int C_OPCODE_LOGINPACKET = 113;

	public static final int C_OPCODE_QUITGAME = 114;

	public static final int C_OPCODE_CHATGLOBAL = 115;

	public static final int C_OPCODE_EXCLUDE = 116;

	public static final int C_OPCODE_NPCTALK = 118;

	public static final int C_OPCODE_USEITEM = 119;

	public static final int C_OPCODE_EMBLEM = 120;

	public static final int C_OPCODE_EXIT_GHOST = 121;

	public static final int C_OPCODE_AMOUNT = 124;

	public static final int C_OPCODE_FISHCLICK = 125;

	public static final int C_OPCODE_MAIL = 127;

	public static final int C_OPCODE_BOOKMARKDELETE = 128;

	public static final int C_OPCODE_USESKILL = 129;

	public static final int C_OPCODE_CREATEPARTY = 130;

	public static final int C_OPCODE_CAHTPARTY = 131;

	// 3.0 ServerPacket
	public static final int S_OPCODE_REMOVE_OBJECT = 0;

	public static final int S_OPCODE_CHARPACK = 1;

	public static final int S_OPCODE_DROPITEM = 1;

	public static final int S_OPCODE_POLY = 2;

	public static final int S_OPCODE_SYSMSG = 3;

	public static final int S_OPCODE_GLOBALCHAT = 3;

	public static final int S_OPCODE_DOACTIONGFX = 6;

	public static final int S_OPCODE_EMBLEM = 7;

	public static final int S_OPCODE_INVLIST = 8;

	public static final int S_OPCODE_ITEMNAME = 9;

	public static final int S_OPCODE_POISON = 10;

	public static final int S_OPCODE_TELEPORT = 11;

	public static final int S_OPCODE_SHOWSHOPSELLLIST = 12;

	public static final int S_OPCODE_CHARVISUALUPDATE = 13;

	public static final int S_OPCODE_USEMAP = 14;

	public static final int S_OPCODE_CHANGEHEADING = 15;

	public static final int S_OPCODE_BLESSOFEVA = 17;

	public static final int S_OPCODE_SELECTLIST = 18;

	public static final int S_OPCODE_OWNCHARSTATUS2 = 19;

	public static final int S_OPCODE_SKILLBRAVE = 20;

	public static final int S_OPCODE_TRADEADDITEM = 21;

	public static final int S_OPCODE_INVIS = 22;

	public static final int S_OPCODE_SHOWRETRIEVELIST = 24;

	// ITEMAMOUNTとITEMSTATUSは同じ?
	public static final int S_OPCODE_ITEMAMOUNT = 25;

	public static final int S_OPCODE_ITEMSTATUS = 25;

	public static final int S_OPCODE_WARTIME = 26;

	public static final int S_OPCODE_CHARRESET = 27;

	public static final int S_OPCODE_ADDSKILL = 28;

	public static final int S_OPCODE_NEWCHARWRONG = 29;

	public static final int S_OPCODE_WEATHER = 31;

	public static final int S_OPCODE_CHARTITLE = 32;

	public static final int S_OPCODE_ADDITEM = 33;

	public static final int S_OPCODE_HPUPDATE = 34;

	public static final int S_OPCODE_ATTACKPACKET = 35;

	public static final int S_OPCODE_SHOWHTML = 37;

	public static final int S_OPCODE_CHANGENAME = 38;

	public static final int S_OPCODE_NEWMASTER = 39;

	public static final int S_OPCODE_DISCONNECT = 41;

	public static final int S_OPCODE_LIQUOR = 43;

	public static final int S_OPCODE_RESURRECTION = 44;

	public static final int S_OPCODE_PUTSOLDIER = 45;

	public static final int S_OPCODE_SHOWSHOPBUYLIST = 46;

	public static final int S_OPCODE_WHISPERCHAT = 47;

	public static final int S_OPCODE_SKILLBUY = 48;

	public static final int S_OPCODE_SKILLHASTE = 49;

	public static final int S_OPCODE_NPCSHOUT = 50;

	public static final int S_OPCODE_DEXUP = 51;

	public static final int S_OPCODE_SPMR = 52;

	public static final int S_OPCODE_TRADE = 53;

	public static final int S_OPCODE_SERVERSTAT = 55;

	public static final int S_OPCODE_NEWCHARPACK = 56;

	public static final int S_OPCODE_DELSKILL = 57;

	public static final int S_OPCODE_GAMETIME = 58;

	public static final int S_OPCODE_OWNCHARSTATUS = 59;

	public static final int S_OPCODE_EXP = 95;

	public static final int S_OPCODE_DEPOSIT = 60;

	public static final int S_OPCODE_SELECTTARGET = 61;

	public static final int S_OPCODE_PACKETBOX = 62;

	public static final int S_OPCODE_ACTIVESPELLS = 62;

	public static final int S_OPCODE_SKILLICONGFX = 62;

	public static final int S_OPCODE_LOGINRESULT = 63;

	public static final int S_OPCODE_BLUEMESSAGE = 65;

	public static final int S_OPCODE_COMMONNEWS = 66;

	public static final int S_OPCODE_DRAWAL = 67;

	public static final int S_OPCODE_HIRESOLDIER = 68;

	public static final int S_OPCODE_EFFECTLOCATION = 69;

	public static final int S_OPCODE_TRUETARGET = 70;

	public static final int S_OPCODE_NORMALCHAT = 71;

	public static final int S_OPCODE_HOUSELIST = 72;

	public static final int S_OPCODE_MAPID = 73;

	public static final int S_OPCODE_UNDERWATER = 73;

	public static final int S_OPCODE_DELETEINVENTORYITEM = 75;

	public static final int S_OPCODE_CHARAMOUNT = 80;

	public static final int S_OPCODE_PARALYSIS = 81;

	public static final int S_OPCODE_ATTRIBUTE = 82;

	public static final int S_OPCODE_SOUND = 83;

	public static final int S_OPCODE_DETELECHAROK = 84;

	public static final int S_OPCODE_TELEPORTLOCK = 85;

	public static final int S_OPCODE_ABILITY = 86;

	public static final int S_OPCODE_PINKNAME = 87;

	public static final int S_OPCODE_SERVERVERSION = 89;

	public static final int S_OPCODE_BOARDREAD = 91;

	public static final int S_OPCODE_MPUPDATE = 92;

	public static final int S_OPCODE_BOARD = 93;

	public static final int S_OPCODE_WAR = 94;

	public static final int S_OPCODE_OWNCHARATTRDEF = 96;

	public static final int S_OPCODE_RESTART = 97;

	public static final int S_OPCODE_SERVERMSG = 98;

	public static final int S_OPCODE_IDENTIFYDESC = 99;

	public static final int S_OPCODE_PINGTIME = 100;

	public static final int S_OPCODE_SKILLSOUNDGFX = 101;

	public static final int S_OPCODE_CHARLIST = 102;

	public static final int S_OPCODE_BOOKMARKS = 103;

	public static final int S_OPCODE_HPMETER = 104;

	public static final int S_OPCODE_YES_NO = 105;

	public static final int S_OPCODE_STRUP = 106;

	public static final int S_OPCODE_ITEMCOLOR = 107;

	public static final int S_OPCODE_CURSEBLIND = 110;

	public static final int S_OPCODE_CASTLEMASTER = 111;

	public static final int S_OPCODE_RANGESKILLS = 112;

	public static final int S_OPCODE_HOUSEMAP = 113;

	public static final int S_OPCODE_SKILLICONSHIELD = 114;

	public static final int S_OPCODE_PRIVATESHOPLIST = 115;

	public static final int S_OPCODE_UNKNOWN1 = 116;

	public static final int S_OPCODE_CHARLOCK = 117;

	public static final int S_OPCODE_LAWFUL = 119;

	public static final int S_OPCODE_TAXRATE = 120;

	public static final int S_OPCODE_TRADESTATUS = 122;

	public static final int S_OPCODE_INPUTAMOUNT = 123;

	public static final int S_OPCODE_LIGHT = 124;

	public static final int S_OPCODE_MOVEOBJECT = 126;

	public static final int S_OPCODE_MAIL = 127;
*/
	
	/*
	 * // clientpackets public static final int C_OPCODE_USEITEM = 0;
	 * 
	 * public static final int C_OPCODE_EXCLUDE = 2;
	 * 
	 * public static final int C_OPCODE_CHARACTERCONFIG = 5;
	 * 
	 * public static final int C_OPCODE_CHANGECHAR = 9;
	 * 
	 * public static final int C_OPCODE_SHIP = 10;
	 * 
	 * public static final int C_OPCODE_RANK = 11;
	 * 
	 * public static final int C_OPCODE_MOVECHAR = 12;
	 * 
	 * public static final int C_OPCODE_TAXRATE = 13;
	 * 
	 * public static final int C_OPCODE_WAR = 14;
	 * 
	 * public static final int C_OPCODE_CHATGLOBAL = 15;
	 * 
	 * public static final int C_OPCODE_CLIENTVERSION = 17;
	 * 
	 * public static final int C_OPCODE_AMOUNT = 19;
	 * 
	 * public static final int C_OPCODE_BOARDBACK = 20;
	 * 
	 * public static final int C_OPCODE_ADDBUDDY = 21;
	 * 
	 * public static final int C_OPCODE_FIX_WEAPON_LIST = 22;
	 * 
	 * public static final int C_OPCODE_LOGINPACKET = 23;
	 * 
	 * public static final int C_OPCODE_TRADEADDCANCEL = 25;
	 * 
	 * public static final int C_OPCODE_CHATWHISPER = 27;
	 * 
	 * public static final int C_OPCODE_TRADEADDITEM = 28;
	 * 
	 * public static final int C_OPCODE_JOINCLAN = 29;
	 * 
	 * public static final int C_OPCODE_KEEPALIVE = 30;
	 * 
	 * public static final int C_OPCODE_NEWCHAR = 31;
	 * 
	 * public static final int C_OPCODE_PLEDGE = 32;
	 * 
	 * public static final int C_OPCODE_FIGHT = 34;
	 * 
	 * public static final int C_OPCODE_BOARD = 39;
	 * 
	 * public static final int C_OPCODE_FISHCLICK = 40;
	 * 
	 * public static final int C_OPCODE_ARROWATTACK = 41;
	 * 
	 * public static final int C_OPCODE_BANCLAN = 42;
	 * 
	 * public static final int C_OPCODE_DEPOSIT = 43;
	 * 
	 * public static final int C_OPCODE_PARTY = 44;
	 * 
	 * public static final int C_OPCODE_ENTERPORTAL = 45;
	 * 
	 * public static final int C_OPCODE_DELETEINVENTORYITEM = 49;
	 * 
	 * public static final int C_OPCODE_EXIT_GHOST = 50;
	 * 
	 * public static final int C_OPCODE_SKILLBUY = 51;
	 * 
	 * public static final int C_OPCODE_CHECKPK = 54;
	 * 
	 * public static final int C_OPCODE_USESKILL = 55;
	 * 
	 * public static final int C_OPCODE_SELECTLIST = 58;
	 * 
	 * public static final int C_OPCODE_PICKUPITEM = 59;
	 * 
	 * public static final int C_OPCODE_RESULT = 61;
	 * 
	 * public static final int C_OPCODE_CHANGEWARTIME = 62;
	 * 
	 * public static final int C_OPCODE_PRIVATESHOPLIST = 63;
	 * 
	 * public static final int C_OPCODE_COMMONCLICK = 65;
	 * 
	 * public static final int C_OPCODE_RETURNTOLOGIN = 67;
	 * 
	 * public static final int C_OPCODE_ATTACK = 68;
	 * 
	 * public static final int C_OPCODE_LEAVEPARTY = 69;
	 * 
	 * public static final int C_OPCODE_SHOP = 71;
	 * 
	 * public static final int C_OPCODE_CALL = 72;
	 * 
	 * public static final int C_OPCODE_WHO = 75;
	 * 
	 * public static final int C_OPCODE_LEAVECLANE = 76;
	 * 
	 * public static final int C_OPCODE_EMBLEM = 77;
	 * 
	 * public static final int C_OPCODE_BUDDYLIST = 79;
	 * 
	 * public static final int C_OPCODE_DRAWAL = 80;
	 * 
	 * public static final int C_OPCODE_GIVEITEM = 82;
	 * 
	 * public static final int C_OPCODE_TRADE = 83;
	 * 
	 * public static final int C_OPCODE_PETMENU = 84;
	 * 
	 * public static final int C_OPCODE_TELEPORT = 85;
	 * 
	 * public static final int C_OPCODE_DELETECHAR = 87;
	 * 
	 * public static final int C_OPCODE_NPCACTION = 88;
	 * 
	 * public static final int C_OPCODE_HIRESOLDIER = 90;
	 * 
	 * public static final int C_OPCODE_BOARDDELETE = 91;
	 * 
	 * public static final int C_OPCODE_EXTCOMMAND = 92;
	 * 
	 * public static final int C_OPCODE_TITLE = 93;
	 * 
	 * public static final int C_OPCODE_DOOR = 94;
	 * 
	 * public static final int C_OPCODE_QUITGAME = 98;
	 * 
	 * public static final int C_OPCODE_PROPOSE = 99;
	 * 
	 * public static final int C_OPCODE_CREATECLAN = 100;
	 * 
	 * public static final int C_OPCODE_BOOKMARK = 101;
	 * 
	 * public static final int C_OPCODE_USEPETITEM = 103;
	 * 
	 * public static final int C_OPCODE_BOOKMARKDELETE = 104;
	 * 
	 * public static final int C_OPCODE_BANPARTY = 105;
	 * 
	 * public static final int C_OPCODE_ATTR = 112;
	 * 
	 * public static final int C_OPCODE_CHAT = 113;
	 * 
	 * public static final int C_OPCODE_SELECTTARGET = 114;
	 * 
	 * public static final int C_OPCODE_DROPITEM = 115;
	 * 
	 * public static final int C_OPCODE_BOARDREAD = 116;
	 * 
	 * public static final int C_OPCODE_RESTART = 117;
	 * 
	 * public static final int C_OPCODE_SKILLBUYOK = 118;
	 * 
	 * public static final int C_OPCODE_COMMONCLICK2 = 119; // new addition
	 * 
	 * public static final int C_OPCODE_TRADEADDOK = 120;
	 * 
	 * public static final int C_OPCODE_CHANGEHEADING = 122;
	 * 
	 * public static final int C_OPCODE_DELETEBUDDY = 123;
	 * 
	 * public static final int C_OPCODE_DELEXCLUDE = 124; // new addition
	 * 
	 * public static final int C_OPCODE_LOGINTOSERVER = 125;
	 * 
	 * public static final int C_OPCODE_BOARDWRITE = 126;
	 * 
	 * public static final int C_OPCODE_LOGINTOSERVEROK = 127;
	 * 
	 * public static final int C_OPCODE_NPCTALK = 129;
	 * 
	 * public static final int C_OPCODE_CREATEPARTY = 130;
	 * 
	 * public static final int C_OPCODE_CAHTPARTY = 131; // serverpackets //
	 * public static final int S_OPCODE_NEWCHARWRONG = 0;
	 * 
	 * public static final int S_OPCODE_LETTER = 1;
	 * 
	 * public static final int S_OPCODE_RANGESKILLS = 2;
	 * 
	 * public static final int S_OPCODE_DOACTIONGFX = 3;
	 * 
	 * public static final int S_OPCODE_USEMAP = 5;
	 * 
	 * public static final int S_OPCODE_ITEMSTATUS = 6;
	 * 
	 * public static final int S_OPCODE_SELETESERVER = 7; // new addition
	 * 
	 * public static final int S_OPCODE_INVIS = 8;
	 * 
	 * public static final int S_OPCODE_CHARDELETEOK = 10; // new addition
	 * 
	 * public static final int S_OPCODE_LAWFUL = 12; // 画面中央に青い文字で“Account ・ has
	 * just logged in from”と表示される public static final int S_OPCODE_BLUEMESSAGE2 =
	 * 13;
	 * 
	 * public static final int S_OPCODE_SELECTLIST = 14;
	 * 
	 * public static final int S_OPCODE_BOARDREAD = 15;
	 * 
	 * public static final int S_OPCODE_SKILLBUY = 17; //
	 * “魔法ヒール(4/0)を习うために渡す材料が不足しています。”と表示される public static final int
	 * S_OPCODE_MATERIAL = 18; // new addition
	 * 
	 * public static final int S_OPCODE_HPUPDATE = 19;
	 * 
	 * public static final int S_OPCODE_SHOWRETRIEVELIST = 20;
	 * 
	 * public static final int S_OPCODE_DELSKILL = 21;
	 * 
	 * public static final int S_OPCODE_NEWCHARPACK = 22;
	 * 
	 * public static final int S_OPCODE_LOGINOK = 23;
	 * 
	 * public static final int S_OPCODE_ADDITEM = 24;
	 * 
	 * public static final int S_OPCODE_TAXRATE = 25;
	 * 
	 * public static final int S_OPCODE_TRADEADDITEM = 26;
	 * 
	 * public static final int S_OPCODE_MAPID = 27;
	 * 
	 * public static final int S_OPCODE_UNDERWATER = 27;
	 * 
	 * public static final int S_OPCODE_YES_NO = 28;
	 * 
	 * public static final int S_OPCODE_DETELECHAROK = 29;
	 * 
	 * public static final int S_OPCODE_TELEPORT = 30;
	 * 
	 * public static final int S_OPCODE_WHISPERCHAT = 33;
	 * 
	 * public static final int S_OPCODE_REMOVE_OBJECT = 34;
	 * 
	 * public static final int S_OPCODE_SERVERVERSION = 35;
	 * 
	 * public static final int S_OPCODE_COMMONNEWS = 36;
	 * 
	 * public static final int S_OPCODE_HOUSELIST = 37;
	 * 
	 * public static final int S_OPCODE_ITEMNAME = 38;
	 * 
	 * public static final int S_OPCODE_DEXUP = 39;
	 * 
	 * public static final int S_OPCODE_SELECTTARGET = 40;
	 * 
	 * public static final int S_OPCODE_EMBLEM = 41;
	 * 
	 * public static final int S_OPCODE_IDENTIFYDESC = 42;
	 * 
	 * public static final int S_OPCODE_PINKNAME = 43;
	 * 
	 * public static final int S_OPCODE_NEWMASTER = 44; // new addition
	 * 
	 * public static final int S_OPCODE_POISON = 45;
	 * 
	 * public static final int S_OPCODE_BOOKMARKS = 49;
	 * 
	 * public static final int S_OPCODE_PRIVATESHOPLIST = 50;
	 * 
	 * public static final int S_OPCODE_TRADE = 51;
	 * 
	 * public static final int S_OPCODE_INPUTAMOUNT = 52;
	 * 
	 * public static final int S_OPCODE_PINGTIME = 53; // new addition
	 * 
	 * public static final int S_OPCODE_WAR = 54;
	 * 
	 * public static final int S_OPCODE_MOVEOBJECT = 55;
	 * 
	 * public static final int S_OPCODE_HOUSEMAP = 56;
	 * 
	 * public static final int S_OPCODE_SHOWSHOPSELLLIST = 57;
	 * 
	 * public static final int S_OPCODE_BLUEMESSAGE = 58;
	 * 
	 * public static final int S_OPCODE_ATTACKPACKET = 59;
	 * 
	 * public static final int S_OPCODE_WARTIME = 60;
	 * 
	 * public static final int S_OPCODE_ITEMAMOUNT = 61;
	 * 
	 * public static final int S_OPCODE_PACKETBOX = 62;
	 * 
	 * public static final int S_OPCODE_ACTIVESPELLS = 62;
	 * 
	 * public static final int S_OPCODE_SKILLICONGFX = 62;
	 * 
	 * public static final int S_OPCODE_CURSEBLIND = 63;
	 * 
	 * public static final int S_OPCODE_COMMONNEWS2 = 64; // new addition
	 * 
	 * public static final int S_OPCODE_STRUP = 65;
	 * 
	 * public static final int S_OPCODE_UNKNOWN1 = 66;
	 * 
	 * public static final int S_OPCODE_SPMR = 67;
	 * 
	 * public static final int S_OPCODE_PUTSOLDIER = 68; // new addition
	 * 
	 * public static final int S_OPCODE_GAMETIME = 69;
	 * 
	 * public static final int S_OPCODE_HPMETER = 70;
	 * 
	 * public static final int S_OPCODE_SYSMSG = 71;
	 * 
	 * public static final int S_OPCODE_GLOBALCHAT = 71;
	 * 
	 * public static final int S_OPCODE_SERVERMSG = 72;
	 * 
	 * public static final int S_OPCODE_TELEPORTLOCK = 73; // new addition
	 * 
	 * public static final int S_OPCODE_CHARPACK = 74;
	 * 
	 * public static final int S_OPCODE_DROPITEM = 74;
	 * 
	 * public static final int S_OPCODE_CHANGENAME = 75; // new addition
	 * 
	 * public static final int S_OPCODE_SKILLHASTE = 77;
	 * 
	 * public static final int S_OPCODE_ADDSKILL = 78;
	 * 
	 * public static final int S_OPCODE_ABILITY = 79;
	 * 
	 * public static final int S_OPCODE_SKILLSOUNDGFX = 80;
	 * 
	 * public static final int S_OPCODE_ATTRIBUTE = 81;
	 * 
	 * public static final int S_OPCODE_INVLIST = 82;
	 * 
	 * public static final int S_OPCODE_CHARVISUALUPDATE = 84;
	 * 
	 * public static final int S_OPCODE_OWNCHARATTRDEF = 85;
	 * 
	 * public static final int S_OPCODE_EFFECTLOCATION = 86;
	 * 
	 * public static final int S_OPCODE_DRAWAL = 87;
	 * 
	 * public static final int S_OPCODE_DISCONNECT = 88;
	 * 
	 * public static final int S_OPCODE_OWNCHARSTATUS = 89;
	 * 
	 * public static final int S_OPCODE_RESURRECTION = 90;
	 * 
	 * public static final int S_OPCODE_EXP = 91;
	 * 
	 * public static final int S_OPCODE_SHOWHTML = 92;
	 * 
	 * public static final int S_OPCODE_TRUETARGET = 93;
	 * 
	 * public static final int S_OPCODE_HIRESOLDIER = 94;
	 * 
	 * public static final int S_OPCODE_LOGINRESULT = 95;
	 * 
	 * public static final int S_OPCODE_BOARD = 96;
	 * 
	 * public static final int S_OPCODE_CHARLOCK = 97; // new addition
	 * 
	 * public static final int S_OPCODE_NEWCHARWRONG = 98; // new addition
	 * 
	 * public static final int S_OPCODE_SHOWSHOPBUYLIST = 99;
	 * 
	 * public static final int S_OPCODE_BLESSOFEVA = 100;
	 * 
	 * public static final int S_OPCODE_RESTART = 101; // new addition
	 * 
	 * public static final int S_OPCODE_DEPOSIT = 102;
	 * 
	 * public static final int S_OPCODE_NORMALCHAT = 103;
	 * 
	 * public static final int S_OPCODE_CHANGEHEADING = 105;
	 * 
	 * public static final int S_OPCODE_UNKNOWN2 = 105;
	 * 
	 * public static final int S_OPCODE_TRADESTATUS = 106;
	 * 
	 * public static final int S_OPCODE_CASTLEMASTER = 107;
	 * 
	 * public static final int S_OPCODE_OWNCHARSTATUS2 = 108;
	 * 
	 * public static final int S_OPCODE_CHARTITLE = 109;
	 * 
	 * public static final int S_OPCODE_PARALYSIS = 111;
	 * 
	 * public static final int S_OPCODE_POLY = 112;
	 * 
	 * public static final int S_OPCODE_SKILLICONSHIELD = 114;
	 * 
	 * public static final int S_OPCODE_SOUND = 116;
	 * 
	 * public static final int S_OPCODE_CHARAMOUNT = 117;
	 * 
	 * public static final int S_OPCODE_CHARLIST = 118;
	 * 
	 * public static final int S_OPCODE_MPUPDATE = 119;
	 * 
	 * public static final int S_OPCODE_DELETEINVENTORYITEM = 120;
	 * 
	 * public static final int S_OPCODE_ITEMCOLOR = 121;
	 * 
	 * public static final int S_OPCODE_SERVERSTAT = 122;
	 * 
	 * public static final int S_OPCODE_WEATHER = 122;
	 * 
	 * public static final int S_OPCODE_LIQUOR = 123;
	 * 
	 * public static final int S_OPCODE_SKILLBRAVE = 124;
	 * 
	 * public static final int S_OPCODE_LIGHT = 126;
	 * 
	 * public static final int S_OPCODE_NPCSHOUT = 127;
	 */

	/*
	 * clientpackets for Episode6
	 * 
	 * public static final int C_OPCODE_QUITGAME = 0;
	 * 
	 * public static final int C_OPCODE_EXCLUDE = 1;
	 * 
	 * public static final int C_OPCODE_SHOP = 2;
	 * 
	 * public static final int C_OPCODE_CHARACTERCONFIG = 3;
	 * 
	 * public static final int C_OPCODE_CHECKPK = 6;
	 * 
	 * public static final int C_OPCODE_PROPOSE = 8;
	 * 
	 * public static final int C_OPCODE_REQUESTCHAT = 9;
	 * 
	 * public static final int C_OPCODE_JOINCLAN = 10;
	 * 
	 * public static final int C_OPCODE_SKILLBUYOK = 12;
	 * 
	 * public static final int C_OPCODE_RETURNTOLOGIN = 14;
	 * 
	 * public static final int C_OPCODE_COMMONCLICK = 15;
	 * 
	 * public static final int C_OPCODE_BOOKMARK = 17;
	 * 
	 * public static final int C_OPCODE_DEPOSIT = 20;
	 * 
	 * public static final int C_OPCODE_LOGINTOSERVER = 21;
	 * 
	 * public static final int C_OPCODE_CREATECLAN = 22;
	 * 
	 * public static final int C_OPCODE_REQUESTARROWATTACK = 24;
	 * 
	 * public static final int C_OPCODE_TRADE = 25;
	 * 
	 * public static final int C_OPCODE_TRADEADDCANCEL = 26;
	 * 
	 * public static final int C_OPCODE_REQUESTDOOR = 27;
	 * 
	 * public static final int C_OPCODE_BANPARTY = 28;
	 * 
	 * public static final int C_OPCODE_REQUESTTITLE = 29;
	 * 
	 * public static final int C_OPCODE_NPCTALK = 31;
	 * 
	 * public static final int C_OPCODE_REQUESTRESULT = 32;
	 * 
	 * public static final int C_OPCODE_KEEPALIVE = 34;
	 * 
	 * public static final int C_OPCODE_REQUESTPARTY = 35;
	 * 
	 * public static final int C_OPCODE_REQUESTEMBLEM = 37;
	 * 
	 * public static final int C_OPCODE_BOOKMARKDELETE = 40;
	 * 
	 * public static final int C_OPCODE_DELETEBUDDY = 42;
	 * 
	 * public static final int C_OPCODE_LEAVECLANE = 43;
	 * 
	 * public static final int C_OPCODE_REQUESTCHANGECHAR = 44;
	 * 
	 * public static final int C_OPCODE_REQUESTCHATGLOBAL = 46;
	 * 
	 * public static final int C_OPCODE_REQUESTWHO = 50;
	 * 
	 * public static final int C_OPCODE_REQUESTBUDDYLIST = 51;
	 * 
	 * public static final int C_OPCODE_USESKILL = 52;
	 * 
	 * public static final int C_OPCODE_FIX_WEAPON_LIST = 53;
	 * 
	 * public static final int C_OPCODE_ADDBUDDY = 54;
	 * 
	 * public static final int C_OPCODE_DELETECHAR = 57;
	 * 
	 * public static final int C_OPCODE_TAXRATE = 60;
	 * 
	 * public static final int C_OPCODE_REQUESTPLEDGE = 61;
	 * 
	 * public static final int C_OPCODE_CHANGEHEADING = 62;
	 * 
	 * public static final int C_OPCODE_REQUESTPICKUPITEM = 63;
	 * 
	 * public static final int C_OPCODE_REQUESTRESTART = 64;
	 * 
	 * public static final int C_OPCODE_TRADEADDOK = 65;
	 * 
	 * public static final int C_OPCODE_BOARD = 66;
	 * 
	 * public static final int C_OPCODE_BOARDREAD = 67;
	 * 
	 * public static final int C_OPCODE_BOARDWRITE = 68;
	 * 
	 * public static final int C_OPCODE_NPCACTION = 70;
	 * 
	 * public static final int C_OPCODE_BOARDDELETE = 72;
	 * 
	 * public static final int C_OPCODE_ENTERPORTAL = 76;
	 * 
	 * public static final int C_OPCODE_USEITEM = 77;
	 * 
	 * public static final int C_OPCODE_LOGINTOSERVEROK = 78;
	 * 
	 * public static final int C_OPCODE_SELECTLIST = 79;
	 * 
	 * public static final int C_OPCODE_SKILLBUY = 82;
	 * 
	 * public static final int C_OPCODE_LOGINPACKET = 83;
	 * 
	 * public static final int C_OPCODE_EXTCOMMAND = 86;
	 * 
	 * public static final int C_OPCODE_REQUESTDROPITEM = 87;
	 * 
	 * public static final int C_OPCODE_CALL = 88;
	 * 
	 * public static final int C_OPCODE_REQUESTAMOUNT = 92;
	 * 
	 * public static final int C_OPCODE_REQUESTWAR = 94;
	 * 
	 * public static final int C_OPCODE_REQUESTATTR = 97;
	 * 
	 * public static final int C_OPCODE_CLIENTVERSION = 98;
	 * 
	 * public static final int C_OPCODE_EXIT_GHOST = 99;
	 * 
	 * public static final int C_OPCODE_BANCLAN = 101;
	 * 
	 * public static final int C_OPCODE_REQUESTLEAVEPARTY = 104;
	 * 
	 * public static final int C_OPCODE_REQUESTCHATWHISPER = 107;
	 * 
	 * public static final int C_OPCODE_TRADEADDITEM = 109;
	 * 
	 * public static final int C_OPCODE_DELETEINVENTORYITEM = 112;
	 * 
	 * public static final int C_OPCODE_PRIVATESHOPLIST = 116;
	 * 
	 * public static final int C_OPCODE_BOARDBACK = 118;
	 * 
	 * public static final int C_OPCODE_NEWCHAR = 120;
	 * 
	 * public static final int C_OPCODE_WITHDRAWPET = 121;
	 * 
	 * public static final int C_OPCODE_REQUESTMOVECHAR = 124;
	 * 
	 * public static final int C_OPCODE_DRAWAL = 125;
	 * 
	 * public static final int C_OPCODE_REQUESTATTACK = 126;
	 * 
	 * public static final int C_OPCODE_HIRESOLDIER = 127;
	 * 
	 * public static final int C_OPCODE_GIVEITEM = 128;
	 * 
	 * public static final int C_OPCODE_CHANGEWARTIME = 129;
	 * 
	 * public static final int C_OPCODE_CREATEPARTY = 130;
	 * 
	 * serverpackets for Episode6
	 * 
	 * public static final int S_OPCODE_HPUPDATE = 0;
	 * 
	 * public static final int S_OPCODE_LIGHT = 2;
	 * 
	 * public static final int S_OPCODE_DETELECHAROK = 4;
	 * 
	 * public static final int S_OPCODE_ATTRIBUTE = 7;
	 * 
	 * public static final int S_OPCODE_MOVEOBJECT = 8;
	 * 
	 * public static final int S_OPCODE_TAXRATE = 9;
	 * 
	 * public static final int S_OPCODE_HIRESOLDIER = 10;
	 * 
	 * public static final int S_OPCODE_EFFECTLOCATION = 11;
	 * 
	 * public static final int S_OPCODE_OWNCHARATTRDEF = 12;
	 * 
	 * public static final int S_OPCODE_CHANGECHARNAME = 13;
	 * 
	 * public static final int S_OPCODE_OWNCHARSTATUS2 = 14;
	 * 
	 * public static final int S_OPCODE_LOGINRESULT = 15;
	 * 
	 * public static final int S_OPCODE_DISCONNECT = 17;
	 * 
	 * public static final int S_OPCODE_DELSKILL = 18;
	 * 
	 * public static final int S_OPCODE_CHANGEHEADING = 19;
	 * 
	 * public static final int S_OPCODE_UNKNOWN2 = 19;
	 * 
	 * public static final int S_OPCODE_TRUETARGET = 21;
	 * 
	 * public static final int S_OPCODE_INVLIST = 23;
	 * 
	 * public static final int S_OPCODE_ADDITEM = 24;
	 * 
	 * public static final int S_OPCODE_ITEMCOLOR = 25;
	 * 
	 * public static final int S_OPCODE_CHARAMOUNT = 26;
	 * 
	 * public static final int S_OPCODE_CHARLIST = 27;
	 * 
	 * public static final int S_OPCODE_INVIS = 29;
	 * 
	 * public static final int S_OPCODE_TRADE = 30;
	 * 
	 * public static final int S_OPCODE_HOUSEMAP = 31;
	 * 
	 * public static final int S_OPCODE_EMPLOY = 32;
	 * 
	 * public static final int S_OPCODE_SKILLHASTE = 33;
	 * 
	 * public static final int S_OPCODE_WHOPET = 35;
	 * 
	 * public static final int S_OPCODE_DOACTIONGFX = 36;
	 * 
	 * public static final int S_OPCODE_EMBLEM = 37;
	 * 
	 * public static final int S_OPCODE_CURSEBLIND = 38;
	 * 
	 * public static final int S_OPCODE_SKILLSOUNDGFX = 39;
	 * 
	 * public static final int S_OPCODE_UNKNOWN1 = 40;
	 * 
	 * public static final int S_OPCODE_USEMAP = 41;
	 * 
	 * public static final int S_OPCODE_HOUSELIST = 42;
	 * 
	 * public static final int S_OPCODE_OWNCHARSTATUS = 43;
	 * 
	 * public static final int S_OPCODE_NPCSHOUT = 44;
	 * 
	 * public static final int S_OPCODE_POLY = 45;
	 * 
	 * public static final int S_OPCODE_PARALYSIS = 46;
	 * 
	 * public static final int S_OPCODE_INPUTAMOUNT = 47;
	 * 
	 * public static final int S_OPCODE_BOARD = 48;
	 * 
	 * public static final int S_OPCODE_CHARPACK = 49;
	 * 
	 * public static final int S_OPCODE_DROPITEM = 49;
	 * 
	 * public static final int S_OPCODE_SERVERVERSION = 50;
	 * 
	 * public static final int S_OPCODE_CANTMOVE = 51;
	 * 
	 * public static final int S_OPCODE_RESURRECTION = 52;
	 * 
	 * public static final int S_OPCODE_DRAWAL = 53;
	 * 
	 * public static final int S_OPCODE_LAWFUL = 54;
	 * 
	 * public static final int S_OPCODE_DEPOSIT = 55;
	 * 
	 * public static final int S_OPCODE_PINGSERVER = 56;
	 * 
	 * public static final int S_OPCODE_ATTACKPACKET = 57;
	 * 
	 * public static final int S_OPCODE_CHARINFO3 = 58;
	 * 
	 * public static final int S_OPCODE_SELECTLIST = 59;
	 * 
	 * public static final int S_OPCODE_RETURNLOGIN = 60;
	 * 
	 * public static final int S_OPCODE_CHARTITLE = 62;
	 * 
	 * public static final int S_OPCODE_PRIVATESHOPLIST = 63;
	 * 
	 * public static final int S_OPCODE_SOUND = 65;
	 * 
	 * public static final int S_OPCODE_ITEMNAME = 66;
	 * 
	 * public static final int S_OPCODE_NEWCHARPACK = 67;
	 * 
	 * public static final int S_OPCODE_SERVERSTAT = 68;
	 * 
	 * public static final int S_OPCODE_WEATHER = 68;
	 * 
	 * public static final int S_OPCODE_BUY002 = 69;
	 * 
	 * public static final int S_OPCODE_ADDSKILL = 70;
	 * 
	 * public static final int S_OPCODE_COMMONNEWS = 73;
	 * 
	 * public static final int S_OPCODE_ABILITY = 74;
	 * 
	 * public static final int S_OPCODE_BOARDREAD = 75;
	 * 
	 * public static final int S_OPCODE_MPUPDATE = 76;
	 * 
	 * public static final int S_OPCODE_LOGINOK = 78;
	 * 
	 * public static final int S_OPCODE_SYSMSG = 79;
	 * 
	 * public static final int S_OPCODE_GLOBALCHAT = 79;
	 * 
	 * public static final int S_OPCODE_SHOWHTML = 80;
	 * 
	 * public static final int S_OPCODE_BONUSSTATS = 80;
	 * 
	 * public static final int S_OPCODE_DELETEINVENTORYITEM = 81;
	 * 
	 * public static final int S_OPCODE_MAPID = 82;
	 * 
	 * public static final int S_OPCODE_UNDERWATER = 82;
	 * 
	 * public static final int S_OPCODE_REMOVE_OBJECT = 83;
	 * 
	 * public static final int S_OPCODE_HPMETER = 84;
	 * 
	 * public static final int S_OPCODE_POISON = 85;
	 * 
	 * public static final int S_OPCODE_BLESSOFEVA = 86;
	 * 
	 * public static final int S_OPCODE_CASTLEMASTER = 87;
	 * 
	 * public static final int S_OPCODE_SPMR = 89;
	 * 
	 * public static final int S_OPCODE_GMACCOUNTMSG = 90;
	 * 
	 * public static final int S_OPCODE_CANTMOVEBEFORETELE = 91;
	 * 
	 * public static final int S_OPCODE_CHARVISUALUPDATE = 92;
	 * 
	 * public static final int S_OPCODE_NEWCHARWRONG = 93;
	 * 
	 * public static final int S_OPCODE_WARTIME = 95;
	 * 
	 * public static final int S_OPCODE_GAMETIME = 96;
	 * 
	 * public static final int S_OPCODE_SELECTTARGET = 97;
	 * 
	 * public static final int S_OPCODE_NORMALCHAT = 98;
	 * 
	 * public static final int S_OPCODE_TRADESTATUS = 99;
	 * 
	 * public static final int S_OPCODE_SKILLICONSHIELD = 100;
	 * 
	 * public static final int S_OPCODE_SHOWRETRIEVELIST = 101;
	 * 
	 * public static final int S_OPCODE_SERVERMSG = 102;
	 * 
	 * public static final int S_OPCODE_SKILLBRAVE = 103;
	 * 
	 * public static final int S_OPCODE_SHOWSHOPSELLLIST = 105;
	 * 
	 * public static final int S_OPCODE_WAR = 106;
	 * 
	 * public static final int S_OPCODE_WEIGHT = 107;
	 * 
	 * public static final int S_OPCODE_PACKETBOX = 107;
	 * 
	 * public static final int S_OPCODE_ACTIVESPELLS = 107;
	 * 
	 * public static final int S_OPCODE_SKILLICONGFX = 107;
	 * 
	 * public static final int S_OPCODE_STRUP = 109;
	 * 
	 * public static final int S_OPCODE_LIQUOR = 110;
	 * 
	 * public static final int S_OPCODE_WHISPERCHAT = 112;
	 * 
	 * public static final int S_OPCODE_BLUEMESSAGE = 113;
	 * 
	 * public static final int S_OPCODE_TRADEADDITEM = 115;
	 * 
	 * public static final int S_OPCODE_LETTER = 116;
	 * 
	 * public static final int S_OPCODE_EXP = 118;
	 * 
	 * public static final int S_OPCODE_BOOKMARKS = 119;
	 * 
	 * public static final int S_OPCODE_SHOWSHOPBUYLIST = 120;
	 * 
	 * public static final int S_OPCODE_IDENTIFYDESC = 121;
	 * 
	 * public static final int S_OPCODE_DEXUP = 122;
	 * 
	 * public static final int S_OPCODE_PINKNAME = 123;
	 * 
	 * public static final int S_OPCODE_ITEMSTATUS = 124;
	 * 
	 * public static final int S_OPCODE_ITEMAMOUNT = 125;
	 * 
	 * public static final int S_OPCODE_YES_NO = 126;
	 * 
	 * public static final int S_OPCODE_SKILLBUY = 127;
	 */

	/*
	 * clientpackets for Episode5
	 * 
	 * public static final int C_OPCODE_REQUESTDOOR = 0;
	 * 
	 * public static final int C_OPCODE_REQUESTTITLE = 5;
	 * 
	 * public static final int C_OPCODE_REQUESTRANGEATTACK = 6;
	 * 
	 * public static final int C_OPCODE_BOARDDELETE = 8;
	 * 
	 * public static final int C_OPCODE_REQUESTPLEDGE = 9;
	 * 
	 * public static final int C_OPCODE_CHANGEHEADING = 11;
	 * 
	 * public static final int C_OPCODE_NPCACTION = 12;
	 * 
	 * public static final int C_OPCODE_ASKDISMISSPET = 12;
	 * 
	 * public static final int C_OPCODE_USESKILL = 14;
	 * 
	 * public static final int C_OPCODE_REQUESTEMBLEM = 15;
	 * 
	 * public static final int C_OPCODE_TRADEADDCANCEL = 18;
	 * 
	 * public static final int C_OPCODE_CHANGEWARTIME = 22;
	 * 
	 * public static final int C_OPCODE_BOOKMARK = 25;
	 * 
	 * public static final int C_OPCODE_CREATECLAN = 26;
	 * 
	 * public static final int C_OPCODE_CLIENTVERSION = 27;
	 * 
	 * public static final int C_OPCODE_PROPOSE = 29;
	 * 
	 * public static final int C_OPCODE_SKILLBUY = 31;
	 * 
	 * public static final int C_OPCODE_BOARDBACK = 38;
	 * 
	 * public static final int C_OPCODE_SHOP = 39;
	 * 
	 * public static final int C_OPCODE_BOARDREAD = 40;
	 * 
	 * public static final int C_OPCODE_TRADE = 42;
	 * 
	 * public static final int C_OPCODE_DELETECHAR = 48;
	 * 
	 * public static final int C_OPCODE_KEEPALIVE = 49;
	 * 
	 * public static final int C_OPCODE_REQUESTATTR = 51;
	 * 
	 * public static final int C_OPCODE_LOGINPACKET = 52;
	 * 
	 * public static final int C_OPCODE_REQUESTRESULT = 54;
	 * 
	 * public static final int C_OPCODE_DEPOSIT = 56;
	 * 
	 * public static final int C_OPCODE_LOGINTOSERVEROK = 57;
	 * 
	 * public static final int C_OPCODE_SKILLBUYOK = 58;
	 * 
	 * public static final int C_OPCODE_TRADEADDITEM = 61;
	 * 
	 * public static final int C_OPCODE_ADDBUDDY = 63;
	 * 
	 * public static final int C_OPCODE_RETURNTOLOGIN = 65;
	 * 
	 * public static final int C_OPCODE_REQUESTCHAT = 68;
	 * 
	 * public static final int C_OPCODE_TRADEADDOK = 69;
	 * 
	 * public static final int C_OPCODE_CHECKPK = 70;
	 * 
	 * public static final int C_OPCODE_TAXRATE = 74;
	 * 
	 * public static final int C_OPCODE_REQUESTCHANGECHAR = 75;
	 * 
	 * public static final int C_OPCODE_REQUESTBUDDYLIST = 76;
	 * 
	 * public static final int C_OPCODE_REQUESTDROPITEM = 77;
	 * 
	 * public static final int C_OPCODE_REQUESTLEAVEPARTY = 78;
	 * 
	 * public static final int C_OPCODE_REQUESTATTACK = 79;
	 * 
	 * public static final int C_OPCODE_QUITGAME = 81;
	 * 
	 * public static final int C_OPCODE_BANCLAN = 82;
	 * 
	 * public static final int C_OPCODE_BOARD = 84;
	 * 
	 * public static final int C_OPCODE_DELETEINVENTORYITEM = 85;
	 * 
	 * public static final int C_OPCODE_REQUESTCHATWHISPER = 86;
	 * 
	 * public static final int C_OPCODE_REQUESTPARTY = 87;
	 * 
	 * public static final int C_OPCODE_REQUESTPICKUPITEM = 88;
	 * 
	 * public static final int C_OPCODE_REQUESTWHO = 89;
	 * 
	 * public static final int C_OPCODE_GIVEITEM = 90;
	 * 
	 * public static final int C_OPCODE_REQUESTMOVECHAR = 91;
	 * 
	 * public static final int C_OPCODE_BOOKMARKDELETE = 93;
	 * 
	 * public static final int C_OPCODE_REQUESTRESTART = 94;
	 * 
	 * public static final int C_OPCODE_LEAVECLANE = 98;
	 * 
	 * public static final int C_OPCODE_NPCTALK = 100;
	 * 
	 * public static final int C_OPCODE_BANPARTY = 102;
	 * 
	 * public static final int C_OPCODE_DELETEBUDDY = 106;
	 * 
	 * public static final int C_OPCODE_REQUESTWAR = 109;
	 * 
	 * public static final int C_OPCODE_LOGINTOSERVER = 111;
	 * 
	 * public static final int C_OPCODE_PRIVATESHOPLIST = 113;
	 * 
	 * public static final int C_OPCODE_REQUESTCHATGLOBAL = 114;
	 * 
	 * public static final int C_OPCODE_JOINCLAN = 115;
	 * 
	 * public static final int C_OPCODE_COMMONCLICK = 117;
	 * 
	 * public static final int C_OPCODE_NEWCHAR = 118;
	 * 
	 * public static final int C_OPCODE_EXTCOMMAND = 123;
	 * 
	 * public static final int C_OPCODE_BOARDWRITE = 124;
	 * 
	 * public static final int C_OPCODE_USEITEM = 129;
	 * 
	 * public static final int C_OPCODE_CREATEPARTY = 130;
	 * 
	 * serverpackets for Episode5
	 * 
	 * public static final int S_OPCODE_TPGFX = 0;
	 * 
	 * public static final int S_OPCODE_DELETENEWOBJECT = 0;
	 * 
	 * public static final int S_OPCODE_DELETEOBJECTFROMSCREEN = 0;
	 * 
	 * public static final int S_OPCODE_KILL = 0;
	 * 
	 * public static final int S_OPCODE_UNKNOWN1 = 1;
	 * 
	 * public static final int S_OPCODE_OWNCHARSTATUS2 = 2;
	 * 
	 * public static final int S_OPCODE_CHARSTATUS = 3;
	 * 
	 * public static final int S_OPCODE_SERVERSTAT = 4;
	 * 
	 * public static final int S_OPCODE_SKILLICONSHIELD = 5;
	 * 
	 * public static final int S_OPCODE_NEWCHARWRONG = 7;
	 * 
	 * public static final int S_OPCODE_CASTLEMASTER = 8;
	 * 
	 * public static final int S_OPCODE_RESURRECTION = 9;
	 * 
	 * public static final int S_OPCODE_LAWFUL = 10;
	 * 
	 * public static final int S_OPCODE_CHARAMOUNT = 11;
	 * 
	 * public static final int S_OPCODE_LOGINRESULT = 12;
	 * 
	 * public static final int S_OPCODE_SKILLHASTE = 14;
	 * 
	 * public static final int S_OPCODE_DOACTIONGFX = 15;
	 * 
	 * public static final int S_OPCODE_NPCNORMALCHAT = 16;
	 * 
	 * public static final int S_OPCODE_CURSEBLIND = 17;
	 * 
	 * public static final int S_OPCODE_BOARD = 18;
	 * 
	 * public static final int S_OPCODE_INVLIST = 20;
	 * 
	 * public static final int S_OPCODE_INVENTORYPACK = 20;
	 * 
	 * public static final int S_OPCODE_DELETEOBJECT = 21;
	 * 
	 * public static final int S_OPCODE_HPUPDATE = 23;
	 * 
	 * public static final int S_OPCODE_SHOWPOLYLIST = 23;
	 * 
	 * public static final int S_OPCODE_BLUEMESSAGE = 24;
	 * 
	 * public static final int S_OPCODE_MAPID = 25;
	 * 
	 * public static final int S_OPCODE_UNDERWATER = 25;
	 * 
	 * public static final int S_OPCODE_POLY = 26;
	 * 
	 * public static final int S_OPCODE_WAR = 27;
	 * 
	 * public static final int S_OPCODE_CHARTITLE = 28;
	 * 
	 * public static final int S_OPCODE_NORMALCHAT = 29;
	 * 
	 * public static final int S_OPCODE_SHOWSHOPBUYLIST = 30;
	 * 
	 * public static final int S_OPCODE_MOVEOBJECT = 32;
	 * 
	 * public static final int S_OPCODE_SPMR = 33;
	 * 
	 * public static final int S_OPCODE_SERVERVERSION = 36;
	 * 
	 * public static final int S_OPCODE_EMBLEM = 37;
	 * 
	 * public static final int S_OPCODE_CHARVISUALUPDATE = 39;
	 * 
	 * public static final int S_OPCODE_DISCONNECT = 41;
	 * 
	 * public static final int S_OPCODE_TRADESTATUS = 42;
	 * 
	 * public static final int S_OPCODE_PINKNAME = 44;
	 * 
	 * public static final int S_OPCODE_BOOKMARKS = 45;
	 * 
	 * public static final int S_OPCODE_OWNCHARSTATUS = 46;
	 * 
	 * public static final int S_OPCODE_PARALYSIS = 47;
	 * 
	 * public static final int S_OPCODE_LIQUOR = 49;
	 * 
	 * public static final int S_OPCODE_DELSKILL = 50;
	 * 
	 * public static final int S_OPCODE_GLOBALCHAT = 51;
	 * 
	 * public static final int S_OPCODE_SYSMSG = 51;
	 * 
	 * public static final int S_OPCODE_BLESSOFEVA = 53;
	 * 
	 * public static final int S_OPCODE_SKILLBRAVE = 55;
	 * 
	 * public static final int S_OPCODE_DELETEMOBOBJECT = 55;
	 * 
	 * public static final int S_OPCODE_LIGHT = 56;
	 * 
	 * public static final int S_OPCODE_UNKNOWN2 = 57;
	 * 
	 * public static final int S_OPCODE_CHANGEHEADING = 57;
	 * 
	 * public static final int S_OPCODE_SERVERMSG = 59;
	 * 
	 * public static final int S_OPCODE_TRUETARGET = 60;
	 * 
	 * public static final int S_OPCODE_HPMETER = 61;
	 * 
	 * public static final int S_OPCODE_SENDITEMAMOUNTUPDATE = 62;
	 * 
	 * public static final int S_OPCODE_ADDSKILL = 63;
	 * 
	 * public static final int S_OPCODE_WARHOUSELIST = 64;
	 * 
	 * public static final int S_OPCODE_DETELECHAROK = 66;
	 * 
	 * public static final int S_OPCODE_NEWCHARPACK = 67;
	 * 
	 * public static final int S_OPCODE_CHARPACK = 68;
	 * 
	 * public static final int S_OPCODE_DROPITEM = 68;
	 * 
	 * public static final int S_OPCODE_DELETEINVENTORYITEM = 71;
	 * 
	 * public static final int S_OPCODE_POISON = 75;
	 * 
	 * public static final int S_OPCODE_CHARINVVISUALUPDATE = 79;
	 * 
	 * public static final int S_OPCODE_SHOWHTML = 81;
	 * 
	 * public static final int S_OPCODE_TPUNK1 = 81;
	 * 
	 * public static final int S_OPCODE_EXP = 81;
	 * 
	 * public static final int S_OPCODE_INVIS = 82;
	 * 
	 * public static final int S_OPCODE_DEPOSIT = 83;
	 * 
	 * public static final int S_OPCODE_WHISPERCHAT = 85;
	 * 
	 * public static final int S_OPCODE_DEXUP = 86;
	 * 
	 * public static final int S_OPCODE_MESSAGE = 87;
	 * 
	 * public static final int S_OPCODE_OWNCHARATTRDEF = 88;
	 * 
	 * public static final int S_OPCODE_AUTHSERVERTIME = 89;
	 * 
	 * public static final int S_OPCODES_ABILITY = 93;
	 * 
	 * public static final int S_OPCODE_ATTACKPACKET = 94;
	 * 
	 * public static final int S_OPCODE_ACTIVESPELLS = 96;
	 * 
	 * public static final int S_OPCODE_SHOWRETRIEVELIST = 96;
	 * 
	 * public static final int S_OPCODE_CHARLIST = 97;
	 * 
	 * public static final int S_OPCODE_BECOMEYOURPET = 100;
	 * 
	 * public static final int S_OPCODE_SHOWSELLLIST = 100;
	 * 
	 * public static final int S_OPCODE_TAXRATE = 102;
	 * 
	 * public static final int S_OPCODE_SHOWSHOPSELLLIST = 103;
	 * 
	 * public static final int S_OPCODE_SKILLBUY = 104;
	 * 
	 * public static final int S_OPCODE_SKILLSOUNDGFX = 106;
	 * 
	 * public static final int S_OPCODE_WEATHER = 108;
	 * 
	 * public static final int S_OPCODE_PRIVATESHOPLIST = 107;
	 * 
	 * public static final int S_OPCODE_GAMETIME = 109;
	 * 
	 * public static final int S_OPCODE_BOARDREAD = 110;
	 * 
	 * public static final int S_OPCODE_YES_NO = 111;
	 * 
	 * public static final int S_OPCODE_WARTIME = 113;
	 * 
	 * public static final int S_OPCODE_STRUP = 114;
	 * 
	 * public static final int S_OPCODE_USEMAP = 115;
	 * 
	 * public static final int S_OPCODE_TRADE = 119;
	 * 
	 * public static final int S_OPCODE_TRADEADDITEM = 121;
	 * 
	 * public static final int S_OPCODE_SKILLICONGFX = 123;
	 * 
	 * public static final int S_OPCODE_COMMONNEWS = 125;
	 * 
	 * public static final int S_OPCODE_MPUPDATE = 126;
	 */

}