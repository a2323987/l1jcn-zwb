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
package com.lineage.server.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.ActionCodes;
import com.lineage.server.IdFactory;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.model.Instance.L1DoorInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_CharVisualUpdate;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_MapID;
import com.lineage.server.serverpackets.S_OtherCharPacks;
import com.lineage.server.serverpackets.S_OwnCharPack;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_Weather;
import com.lineage.server.utils.Random;
import com.lineage.server.utils.collections.Maps;

/** 安塔瑞斯、法利昂副本 */
public class L1DragonSlayer {

    private static class DragonSlayer {
        final ArrayList<L1PcInstance> _members = new ArrayList<L1PcInstance>();

        public DragonSlayer() {
            // TODO Auto-generated constructor stub
        }
    }

    // 计时器
    public class DragonSlayerTimer extends TimerTask {

        private final int _num;
        private final int _status;
        private final int _time;

        public DragonSlayerTimer(final int num, final int status, final int time) {
            this._num = num;
            this._status = status;
            this._time = time;
        }

        public void begin() {
            final Timer timer = new Timer();
            timer.schedule(this, this._time); // 延迟时间
        }

        @Override
        public void run() {
            final short mapId = (short) (1005 + this._num);
            int[] msg = { 1570, 1571, 1572, 1574, 1575, 1576, 1578, 1579, 1581 };
            if ((this._num >= 6) && (this._num <= 11)) {
                msg = new int[] { 1657, 1658, 1659, 1662, 1663, 1664, 1666,
                        1667, 1669 };
            }
            switch (this._status) {
                // 阶段一
                case STATUS_DRAGONSLAYER_READY_1RD:
                    L1DragonSlayer.this.setDragonSlayerStatus(this._num,
                            STATUS_DRAGONSLAYER_READY_2RD);
                    L1DragonSlayer.this.sendMessage(this._num, msg[0], null); // 安塔瑞斯：到底是谁把我吵醒了？
                    // 法利昂：竟敢闯入我的领域...勇气可嘉啊...
                    final DragonSlayerTimer timer_1rd = new DragonSlayerTimer(
                            this._num, STATUS_DRAGONSLAYER_READY_2RD, 10000);
                    timer_1rd.begin();
                    break;
                case STATUS_DRAGONSLAYER_READY_2RD:
                    L1DragonSlayer.this.setDragonSlayerStatus(this._num,
                            STATUS_DRAGONSLAYER_READY_3RD);
                    L1DragonSlayer.this.sendMessage(this._num, msg[1], null); // 卡瑞：安塔瑞斯！我不停追逐你，结果追到这黑暗的地方来！
                    // 巫女莎尔：你这卑劣的法利昂！你会付出欺骗我的代价！
                    final DragonSlayerTimer timer_2rd = new DragonSlayerTimer(
                            this._num, STATUS_DRAGONSLAYER_READY_3RD, 10000);
                    timer_2rd.begin();
                    break;
                case STATUS_DRAGONSLAYER_READY_3RD:
                    L1DragonSlayer.this.setDragonSlayerStatus(this._num,
                            STATUS_DRAGONSLAYER_READY_4RD);
                    L1DragonSlayer.this.sendMessage(this._num, msg[2], null); // 安塔瑞斯：真可怜，就让我把你解决掉，受死吧！卡瑞！
                    // 法利昂：虽然在解除封印时你帮了很大的忙...但现在我不会再仁慈了！！
                    final DragonSlayerTimer timer_3rd = new DragonSlayerTimer(
                            this._num, STATUS_DRAGONSLAYER_READY_4RD, 10000);
                    timer_3rd.begin();
                    break;
                case STATUS_DRAGONSLAYER_READY_4RD:
                    L1DragonSlayer.this.setDragonSlayerStatus(this._num,
                            STATUS_DRAGONSLAYER_START_1RD);
                    // 召唤龙
                    if ((this._num >= 0) && (this._num <= 5)) {
                        L1DragonSlayer.this.spawn(97006, this._num, 32783,
                                32693, mapId, 10, 0); // 地龙 - 阶段一
                    } else {
                        L1DragonSlayer.this.spawn(97044, this._num, 32955,
                                32839, mapId, 10, 0); // 水龙 - 阶段一
                    }
                    break;
                // 阶段二
                case STATUS_DRAGONSLAYER_START_2RD:
                    L1DragonSlayer.this.setDragonSlayerStatus(this._num,
                            STATUS_DRAGONSLAYER_START_2RD_1);
                    L1DragonSlayer.this.sendMessage(this._num, msg[3], null); // 卡瑞：勇士们！亚丁的命运就掌握在你们的武器上了，
                                                                              // 能够让安塔瑞斯窒息的人就是你们了！
                    // 巫女莎尔：勇士们！请消灭邪恶的法利昂，解除伊娃王国的血之诅咒吧！
                    final DragonSlayerTimer timer_4rd = new DragonSlayerTimer(
                            this._num, STATUS_DRAGONSLAYER_START_2RD_1, 10000);
                    timer_4rd.begin();
                    break;
                case STATUS_DRAGONSLAYER_START_2RD_1:
                    L1DragonSlayer.this.setDragonSlayerStatus(this._num,
                            STATUS_DRAGONSLAYER_START_2RD_2);
                    L1DragonSlayer.this.sendMessage(this._num, msg[4], null); // 安塔瑞斯：像这种虾兵蟹将也想要赢我！噗哈哈哈…
                    // 法利昂：你们只够格当我的玩具！！
                    final DragonSlayerTimer timer_5rd = new DragonSlayerTimer(
                            this._num, STATUS_DRAGONSLAYER_START_2RD_2, 30000);
                    timer_5rd.begin();
                    break;
                case STATUS_DRAGONSLAYER_START_2RD_2:
                    L1DragonSlayer.this.setDragonSlayerStatus(this._num,
                            STATUS_DRAGONSLAYER_START_2RD_3);
                    L1DragonSlayer.this.sendMessage(this._num, msg[5], null); // 安塔瑞斯：我今天又可以饱餐一顿了？你们的血激起我的斗志。
                    // 法利昂：刻骨的恐惧到底是什么，就让你们尝一下吧！
                    final DragonSlayerTimer timer_6rd = new DragonSlayerTimer(
                            this._num, STATUS_DRAGONSLAYER_START_2RD_3, 10000);
                    timer_6rd.begin();
                    break;
                case STATUS_DRAGONSLAYER_START_2RD_3:
                    L1DragonSlayer.this.setDragonSlayerStatus(this._num,
                            STATUS_DRAGONSLAYER_START_2RD_4);
                    // 召唤龙
                    if ((this._num >= 0) && (this._num <= 5)) {
                        L1DragonSlayer.this.spawn(97007, this._num, 32783,
                                32693, mapId, 10, 0); // 地龙 - 阶段二
                    } else {
                        L1DragonSlayer.this.spawn(97045, this._num, 32955,
                                32839, mapId, 10, 0); // 水龙 - 阶段二
                    }
                    break;
                // 阶段三
                case STATUS_DRAGONSLAYER_START_3RD:
                    L1DragonSlayer.this.setDragonSlayerStatus(this._num,
                            STATUS_DRAGONSLAYER_START_3RD_1);
                    L1DragonSlayer.this.sendMessage(this._num, msg[6], null); // 安塔瑞斯：你竟然敢对付我...我看你们是不想活了？
                    // 法利昂：我要让你们知道你们所谓的希望，只不过是妄想！
                    final DragonSlayerTimer timer_7rd = new DragonSlayerTimer(
                            this._num, STATUS_DRAGONSLAYER_START_3RD_1, 40000);
                    timer_7rd.begin();
                    break;
                case STATUS_DRAGONSLAYER_START_3RD_1:
                    L1DragonSlayer.this.setDragonSlayerStatus(this._num,
                            STATUS_DRAGONSLAYER_START_3RD_2);
                    L1DragonSlayer.this.sendMessage(this._num, msg[7], null); // 安塔瑞斯：我的愤怒值已经冲上天了，我的父亲格兰肯将会赐我力量。
                    // 法利昂：你们会后悔跟了莎尔！ 可笑的愚民…
                    final DragonSlayerTimer timer_8rd = new DragonSlayerTimer(
                            this._num, STATUS_DRAGONSLAYER_START_3RD_2, 10000);
                    timer_8rd.begin();
                    break;
                case STATUS_DRAGONSLAYER_START_3RD_2:
                    L1DragonSlayer.this.setDragonSlayerStatus(this._num,
                            STATUS_DRAGONSLAYER_START_3RD_3);
                    // 召唤龙
                    if ((this._num >= 0) && (this._num <= 5)) {
                        L1DragonSlayer.this.spawn(97008, this._num, 32783,
                                32693, mapId, 10, 0); // 地龙 - 阶段三
                    } else {
                        L1DragonSlayer.this.spawn(97046, this._num, 32955,
                                32839, mapId, 10, 0); // 水龙 - 阶段三
                    }
                    break;
                case STATUS_DRAGONSLAYER_END_1:
                    L1DragonSlayer.this.setDragonSlayerStatus(this._num,
                            STATUS_DRAGONSLAYER_END_2);
                    L1DragonSlayer.this.sendMessage(this._num, msg[8], null); // 卡瑞：喔...
                                                                              // 顶尖的勇士们！你们经历了多少的失败才有今天的成就，你们击败了安塔瑞斯！
                    // 我终于复仇了呜哈哈哈！！ 谢谢你们，你们是最顶尖的战士！
                    if (L1DragonSlayer.this.checkHiddenDragonValleyStstus() == STATUS_NONE) { // 准备开启隐匿的巨龙谷入口
                        L1DragonSlayer.this
                                .setHiddenDragonValleyStstus(STATUS_READY_SPAWN);
                        final DragonSlayerTimer timer_9rd = new DragonSlayerTimer(
                                this._num, STATUS_DRAGONSLAYER_END_2, 10000);
                        timer_9rd.begin();
                    } else { // 直接结束
                        if (L1DragonSlayer.this.getDragonSlayerStatus()[this._num] != STATUS_DRAGONSLAYER_END_5) {
                            L1DragonSlayer.this.setDragonSlayerStatus(
                                    this._num, STATUS_DRAGONSLAYER_END_5);
                            final DragonSlayerTimer timer = new DragonSlayerTimer(
                                    this._num, STATUS_DRAGONSLAYER_END_5, 5000);
                            timer.begin();
                        }
                    }
                    break;
                case STATUS_DRAGONSLAYER_END_2:
                    L1DragonSlayer.this.setDragonSlayerStatus(this._num,
                            STATUS_DRAGONSLAYER_END_3);
                    L1DragonSlayer.this.sendMessage(this._num, 1582, null); // 侏儒的呼唤：威顿村庄出现了通往隐匿的巨龙谷入口。
                    if (L1DragonSlayer.this.checkHiddenDragonValleyStstus() == STATUS_READY_SPAWN) { // 开启隐匿的巨龙谷入口
                        L1DragonSlayer.this
                                .setHiddenDragonValleyStstus(STATUS_SPAWN);
                        L1DragonSlayer.this.spawn(81277, -1, 33726, 32506,
                                (short) 4, 0, 86400000); // 24小时
                    }
                    final DragonSlayerTimer timer_10rd = new DragonSlayerTimer(
                            this._num, STATUS_DRAGONSLAYER_END_3, 5000);
                    timer_10rd.begin();
                    break;
                case STATUS_DRAGONSLAYER_END_3:
                    L1DragonSlayer.this.setDragonSlayerStatus(this._num,
                            STATUS_DRAGONSLAYER_END_4);
                    L1DragonSlayer.this.sendMessage(this._num, 1583, null); // 侏儒的呼唤：威顿村庄通往隐匿的巨龙谷入口已经开启了。
                    final DragonSlayerTimer timer_11rd = new DragonSlayerTimer(
                            this._num, STATUS_DRAGONSLAYER_END_4, 5000);
                    timer_11rd.begin();
                    break;
                case STATUS_DRAGONSLAYER_END_4:
                    L1DragonSlayer.this.setDragonSlayerStatus(this._num,
                            STATUS_DRAGONSLAYER_END_5);
                    L1DragonSlayer.this.sendMessage(this._num, 1584, null); // 侏儒的呼唤：快离开这里吧，门就快要关了。
                    final DragonSlayerTimer timer_12rd = new DragonSlayerTimer(
                            this._num, STATUS_DRAGONSLAYER_END_5, 5000);
                    timer_12rd.begin();
                    break;
                case STATUS_DRAGONSLAYER_END_5:
                    // 删除龙之门扉
                    if (L1DragonSlayer.this.portalPack()[this._num] != null) {
                        L1DragonSlayer.this.portalPack()[this._num]
                                .setStatus(ActionCodes.ACTION_Die);
                        L1DragonSlayer.this.portalPack()[this._num]
                                .broadcastPacket(new S_DoActionGFX(
                                        L1DragonSlayer.this.portalPack()[this._num]
                                                .getId(),
                                        ActionCodes.ACTION_Die));
                        L1DragonSlayer.this.portalPack()[this._num].deleteMe();
                    }
                    // 龙之门扉重置
                    L1DragonSlayer.this.resetDragonSlayer(this._num);
                    final DragonSlayerTimer timer_13rd = new DragonSlayerTimer(
                            this._num, STATUS_DRAGONSLAYER_END, 300000); // 下次可重新开启同编号龙门的等候时间
                    timer_13rd.begin();
                    break;
                case STATUS_DRAGONSLAYER_END:
                    L1DragonSlayer.this.setPortalNumber(this._num, false);
                    break;
            }
            this.cancel();
        }
    }

    private static Logger _log = Logger.getLogger(L1DragonSlayer.class
            .getName());
    private static L1DragonSlayer _instance;
    public static final int STATUS_DRAGONSLAYER_NONE = 0;
    public static final int STATUS_DRAGONSLAYER_READY_1RD = 1;
    public static final int STATUS_DRAGONSLAYER_READY_2RD = 2;
    public static final int STATUS_DRAGONSLAYER_READY_3RD = 3;
    public static final int STATUS_DRAGONSLAYER_READY_4RD = 4;
    public static final int STATUS_DRAGONSLAYER_START_1RD = 5;
    public static final int STATUS_DRAGONSLAYER_START_2RD = 6;
    public static final int STATUS_DRAGONSLAYER_START_2RD_1 = 7;
    public static final int STATUS_DRAGONSLAYER_START_2RD_2 = 8;
    public static final int STATUS_DRAGONSLAYER_START_2RD_3 = 9;
    public static final int STATUS_DRAGONSLAYER_START_2RD_4 = 10;
    public static final int STATUS_DRAGONSLAYER_START_3RD = 11;
    public static final int STATUS_DRAGONSLAYER_START_3RD_1 = 12;
    public static final int STATUS_DRAGONSLAYER_START_3RD_2 = 13;
    public static final int STATUS_DRAGONSLAYER_START_3RD_3 = 14;
    public static final int STATUS_DRAGONSLAYER_END_1 = 15;
    public static final int STATUS_DRAGONSLAYER_END_2 = 16;
    public static final int STATUS_DRAGONSLAYER_END_3 = 17;
    public static final int STATUS_DRAGONSLAYER_END_4 = 18;

    public static final int STATUS_DRAGONSLAYER_END_5 = 19;
    public static final int STATUS_DRAGONSLAYER_END = 20;
    public static final int STATUS_NONE = 0;

    public static final int STATUS_READY_SPAWN = 1;

    public static final int STATUS_SPAWN = 2;

    private static final Map<Integer, DragonSlayer> _dataMap = Maps.newMap();

    public static L1DragonSlayer getInstance() {
        if (_instance == null) {
            _instance = new L1DragonSlayer();
        }
        return _instance;
    }

    // 判断龙之门扉是否开启 ,最多12个龙门
    private final boolean[] _portalNumber = new boolean[12];

    // 判断龙之钥匙显示可开启的龙门
    private final boolean[] _checkDragonPortal = new boolean[4];

    // 龙之门扉物件
    private final L1NpcInstance[] _portal = new L1NpcInstance[12];

    // 副本目前状态
    private final int[] _DragonSlayerStatus = new int[12];

    // 判断隐匿的巨龙谷入口是否已出现
    private int _hiddenDragonValleyStstus = 0;

    // 加入玩家
    public void addPlayerList(final L1PcInstance pc, final int portalNum) {
        if (_dataMap.containsKey(portalNum)) {
            if (!_dataMap.get(portalNum)._members.contains(pc)) {
                _dataMap.get(portalNum)._members.add(pc);
            }
        }
    }

    public boolean[] checkDragonPortal() {
        this._checkDragonPortal[0] = false; // 安塔瑞斯
        this._checkDragonPortal[1] = false; // 法利昂
        this._checkDragonPortal[2] = false; // 林德拜尔
        this._checkDragonPortal[3] = false; // 巴拉卡斯

        for (int i = 0; i < 12; i++) {
            if (!this.getPortalNumber()[i]) {
                if (i < 6) { // 前6个安塔瑞斯
                    this._checkDragonPortal[0] = true;
                } else { // 后6个法利昂
                    this._checkDragonPortal[1] = true;
                }
            }
        }
        return this._checkDragonPortal;
    }

    public int checkHiddenDragonValleyStstus() {
        return this._hiddenDragonValleyStstus;
    }

    // 清除玩家
    private void clearPlayerList(final int portalNum) {
        if (_dataMap.containsKey(portalNum)) {
            _dataMap.get(portalNum)._members.clear();
        }
    }

    // 门扉存在时间结束
    public void endDragonPortal(final int portalNum) {
        if (this.getDragonSlayerStatus()[portalNum] != STATUS_DRAGONSLAYER_END_5) {
            this.setDragonSlayerStatus(portalNum, STATUS_DRAGONSLAYER_END_5);
            final DragonSlayerTimer timer = new DragonSlayerTimer(portalNum,
                    STATUS_DRAGONSLAYER_END_5, 5000);
            timer.begin();
        }
    }

    // 副本完成
    public void endDragonSlayer(final int portalNum) {
        if (this.getDragonSlayerStatus()[portalNum] == STATUS_DRAGONSLAYER_START_3RD_3) {
            this.setDragonSlayerStatus(portalNum, STATUS_DRAGONSLAYER_END_1);
            final DragonSlayerTimer timer = new DragonSlayerTimer(portalNum,
                    STATUS_DRAGONSLAYER_END_1, 10000);
            timer.begin();
        }
    }

    public int[] getDragonSlayerStatus() {
        return this._DragonSlayerStatus;
    }

    private L1PcInstance[] getPlayersArray(final int num) {
        return _dataMap.get(num)._members.toArray(new L1PcInstance[_dataMap
                .get(num)._members.size()]);
    }

    // 取得参加人数
    public int getPlayersCount(final int num) {
        DragonSlayer _DragonSlayer = null;
        if (!_dataMap.containsKey(num)) {
            _DragonSlayer = new DragonSlayer();
            _dataMap.put(num, _DragonSlayer);
        }
        return _dataMap.get(num)._members.size();
    }

    public boolean[] getPortalNumber() {
        return this._portalNumber;
    }

    public L1NpcInstance[] portalPack() {
        return this._portal;
    }

    // 移除玩家
    public void removePlayer(final L1PcInstance pc, final int portalNum) {
        if (_dataMap.containsKey(portalNum)) {
            if (_dataMap.get(portalNum)._members.contains(pc)) {
                _dataMap.get(portalNum)._members.remove(pc);
            }
        }
    }

    // 重置副本
    public void resetDragonSlayer(final int portalNumber) {
        final short mapId = (short) (1005 + portalNumber); // MapId 判断

        for (final Object obj : L1World.getInstance().getVisibleObjects(mapId)
                .values()) {
            // 将玩家传出副本地图
            if (obj instanceof L1PcInstance) {
                final L1PcInstance pc = (L1PcInstance) obj;
                if (pc != null) {
                    if (pc.isDead()) {
                        this.reStartPlayer(pc);
                    } else {
                        // 传送至威顿村
                        pc.setPortalNumber(-1);
                        L1Teleport.teleport(pc, 33710, 32521, (short) 4,
                                pc.getHeading(), true);
                    }
                }
            }
            // 门关闭
            else if (obj instanceof L1DoorInstance) {
                final L1DoorInstance door = (L1DoorInstance) obj;
                door.close();
            }
            // 删除副本内的怪物
            else if (obj instanceof L1NpcInstance) {
                final L1NpcInstance npc = (L1NpcInstance) obj;
                if ((npc.getMaster() == null)
                        && ((npc.getNpcTemplate().get_npcId() < 81301) && (npc
                                .getNpcTemplate().get_npcId() > 81306))) {
                    npc.deleteMe();
                }
            }
            // 删除副本内的物品
            else if (obj instanceof L1Inventory) {
                final L1Inventory inventory = (L1Inventory) obj;
                inventory.clearItems();
            }
        }
        this.setPortalPack(portalNumber, null);
        this.setDragonSlayerStatus(portalNumber, STATUS_DRAGONSLAYER_NONE);
        this.clearPlayerList(portalNumber);
    }

    // 副本内死亡的玩家重新开始
    private void reStartPlayer(final L1PcInstance pc) {
        pc.stopPcDeleteTimer();

        final int[] loc = Getback.GetBack_Location(pc, true);

        pc.removeAllKnownObjects();
        pc.broadcastPacket(new S_RemoveObject(pc));

        pc.setCurrentHp(pc.getLevel());
        pc.set_food(40);
        pc.setDead(false);
        pc.setStatus(0);
        L1World.getInstance().moveVisibleObject(pc, loc[2]);
        pc.setX(loc[0]);
        pc.setY(loc[1]);
        pc.setMap((short) loc[2]);
        pc.sendPackets(new S_MapID(pc.getMapId(), pc.getMap().isUnderwater()));
        pc.broadcastPacket(new S_OtherCharPacks(pc));
        pc.sendPackets(new S_OwnCharPack(pc));
        pc.sendPackets(new S_CharVisualUpdate(pc));
        pc.startHpRegeneration();
        pc.startMpRegeneration();
        pc.sendPackets(new S_Weather(L1World.getInstance().getWeather()));
        if (pc.getHellTime() > 0) {
            pc.beginHell(false);
        }
    }

    // 讯息发送
    public void sendMessage(final int portalNum, final int type,
            final String msg) {
        final short mapId = (short) (1005 + portalNum);
        final L1PcInstance[] temp = this.getPlayersArray(portalNum);
        for (final L1PcInstance element : temp) {
            if ((element.getMapId() == mapId)
                    && ((element.getX() >= 32740) && (element.getX() <= 32827))
                    && ((element.getY() >= 32652) && (element.getY() <= 32727))
                    && ((portalNum >= 0) && (portalNum <= 5))) { // 安塔瑞斯栖息地
                element.sendPackets(new S_ServerMessage(type, msg));
            } else if ((element.getMapId() == mapId)
                    && ((element.getX() >= 32921) && (element.getX() <= 33009))
                    && ((element.getY() >= 32799) && (element.getY() <= 32869))
                    && ((portalNum >= 6) && (portalNum <= 11))) { // 法利昂栖息地
                element.sendPackets(new S_ServerMessage(type, msg));
            }
        }
    }

    public void setDragonSlayerStatus(final int portalNum, final int i) {
        this._DragonSlayerStatus[portalNum] = i;
    }

    public void setHiddenDragonValleyStstus(final int i) {
        this._hiddenDragonValleyStstus = i;
    }

    public void setPortalNumber(final int number, final boolean i) {
        this._portalNumber[number] = i;
    }

    public void setPortalPack(final int number, final L1NpcInstance portal) {
        this._portal[number] = portal;
    }

    // 召唤用
    public void spawn(final int npcId, final int portalNumber, final int X,
            final int Y, final short mapId, final int randomRange,
            final int timeMillisToDelete) {
        try {
            final L1NpcInstance npc = NpcTable.getInstance().newNpcInstance(
                    npcId);
            npc.setId(IdFactory.getInstance().nextId());
            npc.setMap(mapId);

            if (randomRange == 0) {
                npc.setX(X);
                npc.setY(Y);
            } else {
                int tryCount = 0;
                do {
                    tryCount++;
                    npc.setX(X + Random.nextInt(randomRange)
                            - Random.nextInt(randomRange));
                    npc.setY(Y + Random.nextInt(randomRange)
                            - Random.nextInt(randomRange));
                    if (npc.getMap().isInMap(npc.getLocation())
                            && npc.getMap().isPassable(npc.getLocation())) {
                        break;
                    }
                    Thread.sleep(1);
                } while (tryCount < 50);

                if (tryCount >= 50) {
                    npc.setX(X);
                    npc.setY(Y);
                }
            }

            npc.setHomeX(npc.getX());
            npc.setHomeY(npc.getY());
            npc.setHeading(Random.nextInt(8));
            npc.setPortalNumber(portalNumber);

            L1World.getInstance().storeObject(npc);
            L1World.getInstance().addVisibleObject(npc);

            if ((npc.getGfxId() == 7548) || (npc.getGfxId() == 7550)
                    || (npc.getGfxId() == 7552) || (npc.getGfxId() == 7554)
                    || (npc.getGfxId() == 7585) || (npc.getGfxId() == 7539)
                    || (npc.getGfxId() == 7557) || (npc.getGfxId() == 7558)
                    || (npc.getGfxId() == 7864) || (npc.getGfxId() == 7869)
                    || (npc.getGfxId() == 7870)) {
                npc.npcSleepTime(ActionCodes.ACTION_AxeWalk,
                        L1NpcInstance.ATTACK_SPEED);
                for (final L1PcInstance pc : L1World.getInstance()
                        .getVisiblePlayer(npc)) {
                    npc.onPerceive(pc);
                    final S_DoActionGFX gfx = new S_DoActionGFX(npc.getId(),
                            ActionCodes.ACTION_AxeWalk);
                    pc.sendPackets(gfx);
                }
            }

            npc.turnOnOffLight();
            npc.startChat(L1NpcInstance.CHAT_TIMING_APPEARANCE); // 喊话开始

            if (0 < timeMillisToDelete) {
                final L1NpcDeleteTimer timer = new L1NpcDeleteTimer(npc,
                        timeMillisToDelete);
                timer.begin();
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    // 开始第一阶段
    public void startDragonSlayer(final int portalNum) {
        if (this.getDragonSlayerStatus()[portalNum] == STATUS_DRAGONSLAYER_NONE) {
            this.setDragonSlayerStatus(portalNum, STATUS_DRAGONSLAYER_READY_1RD);
            final DragonSlayerTimer timer = new DragonSlayerTimer(portalNum,
                    STATUS_DRAGONSLAYER_READY_1RD, 150000);
            timer.begin();
        }
    }

    // 开始第二阶段
    public void startDragonSlayer2rd(final int portalNum) {
        if (this.getDragonSlayerStatus()[portalNum] == STATUS_DRAGONSLAYER_START_1RD) {
            if ((portalNum >= 6) && (portalNum <= 11)) {
                this.sendMessage(portalNum, 1661, null); // 法利昂：可怜啊！他们就是和你一样，注定要当我的祭品！
            } else {
                this.sendMessage(portalNum, 1573, null); // 安塔瑞斯：你这顽固的家伙！你又激起我的愤怒了！
            }
            this.setDragonSlayerStatus(portalNum, STATUS_DRAGONSLAYER_START_2RD);
            final DragonSlayerTimer timer = new DragonSlayerTimer(portalNum,
                    STATUS_DRAGONSLAYER_START_2RD, 10000);
            timer.begin();
        }
    }

    // 开始第三阶段
    public void startDragonSlayer3rd(final int portalNum) {
        if (this.getDragonSlayerStatus()[portalNum] == STATUS_DRAGONSLAYER_START_2RD_4) {
            if ((portalNum >= 6) && (portalNum <= 11)) {
                this.sendMessage(portalNum, 1665, null); // 巫女莎尔：法利昂的力量好像削弱了不少！
                                                         // 勇士们啊，再接再厉吧！
            } else {
                this.sendMessage(portalNum, 1577, null); // 卡瑞：呜啊！你有听到那些冤魂的惨叫声吗！受死吧！！
            }
            this.setDragonSlayerStatus(portalNum, STATUS_DRAGONSLAYER_START_3RD);
            final DragonSlayerTimer timer = new DragonSlayerTimer(portalNum,
                    STATUS_DRAGONSLAYER_START_3RD, 10000);
            timer.begin();
        }
    }
}
