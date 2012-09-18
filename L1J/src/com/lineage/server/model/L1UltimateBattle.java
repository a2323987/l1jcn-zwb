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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.ActionCodes;
import com.lineage.server.GeneralThreadPool;
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.datatables.UBSpawnTable;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Item;
import com.lineage.server.utils.IntRange;
import com.lineage.server.utils.Random;
import com.lineage.server.utils.collections.Lists;

// Referenced classes of package com.lineage.server.model:
// L1UltimateBattle

/**
 * 无限大战
 */
public class L1UltimateBattle {

    class UbThread implements Runnable {
        /**
         * 竞技场开始倒计时。
         * 
         * @throws InterruptedException
         */
        private void countDown() throws InterruptedException {
            // XXX - 此ID错误
            final int MSGID_COUNT = 637;
            final int MSGID_START = 632;

            for (int loop = 0; loop < BEFORE_MINUTE * 60 - 10; loop++) { // 开始前等待10秒
                Thread.sleep(1000);
                // removeRetiredMembers();
            }
            L1UltimateBattle.this.removeRetiredMembers();

            L1UltimateBattle.this.sendMessage(MSGID_COUNT, "10"); // 10秒前

            Thread.sleep(5000);
            L1UltimateBattle.this.sendMessage(MSGID_COUNT, "5"); // 5秒前

            Thread.sleep(1000);
            L1UltimateBattle.this.sendMessage(MSGID_COUNT, "4"); // 4秒前

            Thread.sleep(1000);
            L1UltimateBattle.this.sendMessage(MSGID_COUNT, "3"); // 3秒前

            Thread.sleep(1000);
            L1UltimateBattle.this.sendMessage(MSGID_COUNT, "2"); // 2秒前

            Thread.sleep(1000);
            L1UltimateBattle.this.sendMessage(MSGID_COUNT, "1"); // 1秒前

            Thread.sleep(1000);
            L1UltimateBattle.this.sendMessage(MSGID_START, "无限大战开始"); // 开始
            L1UltimateBattle.this.removeRetiredMembers();
        }

        /**
         * 线程过程。
         */
        @Override
        public void run() {
            try {
                L1UltimateBattle.this.setActive(true);
                this.countDown();
                L1UltimateBattle.this.setNowUb(true);
                for (int round = 1; round <= 4; round++) {
                    L1UltimateBattle.this.sendRoundMessage(round);

                    final L1UbPattern pattern = UBSpawnTable.getInstance()
                            .getPattern(L1UltimateBattle.this._ubId,
                                    L1UltimateBattle.this._pattern);

                    final List<L1UbSpawn> spawnList = pattern
                            .getSpawnList(round);

                    for (final L1UbSpawn spawn : spawnList) {
                        if (L1UltimateBattle.this.getMembersCount() > 0) {
                            spawn.spawnAll();
                        }

                        Thread.sleep(spawn.getSpawnDelay() * 1000);
                        // removeRetiredMembers();
                    }

                    if (L1UltimateBattle.this.getMembersCount() > 0) {
                        L1UltimateBattle.this.spawnSupplies(round);
                    }

                    this.waitForNextRound(round);
                }

                for (final L1PcInstance pc : L1UltimateBattle.this
                        .getMembersArray()) // 竞技场内的PC出来
                {
                    final int rndx = Random.nextInt(4);
                    final int rndy = Random.nextInt(4);
                    final int locx = 33503 + rndx;
                    final int locy = 32764 + rndy;
                    final short mapid = 4;
                    L1Teleport.teleport(pc, locx, locy, mapid, 5, true);
                    L1UltimateBattle.this.removeMember(pc);
                }
                L1UltimateBattle.this.clearColosseum();
                L1UltimateBattle.this.setActive(false);
                L1UltimateBattle.this.setNowUb(false);
            } catch (final Exception e) {
                _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            }
        }

        /**
         * 全部的怪物出现后、待机 等待下一轮开始。
         * 
         * @param curRound
         *            本轮
         * @throws InterruptedException
         */
        private void waitForNextRound(final int curRound)
                throws InterruptedException {
            final int WAIT_TIME_TABLE[] = { 6, 6, 2, 18 };

            final int wait = WAIT_TIME_TABLE[curRound - 1];
            for (int i = 0; i < wait; i++) {
                Thread.sleep(10000);
                // removeRetiredMembers();
            }
            L1UltimateBattle.this.removeRetiredMembers();
        }
    }

    private static Calendar getRealTime() {
        final TimeZone _tz = TimeZone.getTimeZone(Config.TIME_ZONE);
        final Calendar cal = Calendar.getInstance(_tz);
        return cal;
    }

    private int _locX;

    private int _locY;

    private L1Location _location; // 中心点

    private short _mapId;

    private int _locX1;

    private int _locY1;

    private int _locX2;

    private int _locY2;

    int _ubId;

    int _pattern;

    private boolean _isNowUb;

    private boolean _active; // UB入場可能～競技終了までtrue

    private int _minLevel;

    private int _maxLevel;

    private int _maxPlayer;

    private boolean _enterRoyal;

    private boolean _enterKnight;

    private boolean _enterMage;

    private boolean _enterElf;

    private boolean _enterDarkelf;

    private boolean _enterDragonKnight;

    private boolean _enterIllusionist;

    private boolean _enterMale;

    private boolean _enterFemale;

    private boolean _usePot;

    private int _hpr;

    private int _mpr;

    static int BEFORE_MINUTE = 5; // 入场前5分钟开始

    private final Set<Integer> _managers = new HashSet<Integer>();

    private final SortedSet<Integer> _ubTimes = new TreeSet<Integer>();

    static final Logger _log = Logger.getLogger(L1UltimateBattle.class
            .getName());

    private static String intToTimeFormat(final int n) {
        return n / 100 + ":" + n % 100 / 10 + "" + n % 10;
    }

    private final List<L1PcInstance> _members = Lists.newList();

    private String[] _ubInfo;

    /**
     * 构造。
     */
    public L1UltimateBattle() {
    }

    public void addManager(final int npcId) {
        this._managers.add(npcId);
    }

    /**
     * 加入角色的名单。
     * 
     * @param pc
     *            新角色参加
     */
    public void addMember(final L1PcInstance pc) {
        if (!this._members.contains(pc)) {
            this._members.add(pc);
        }
    }

    public void addUbTime(final int time) {
        this._ubTimes.add(time);
    }

    /**
     * UB参加可能、等级、检查角色的类。
     * 
     * @param pc
     *            检查可以参加UB的PC
     * @return 能参加true,不能false
     */
    public boolean canPcEnter(final L1PcInstance pc) {
        _log.log(Level.FINE, "pcname={0} ubid={1} minlvl={2} maxlvl={3}",
                new Object[] { pc.getName(), this._ubId, this._minLevel,
                        this._maxLevel });
        // 什么级别可以参加
        if (!IntRange.includes(pc.getLevel(), this._minLevel, this._maxLevel)) {
            return false;
        }

        // 可以参加的玩家类别
        if (!((pc.isCrown() && this._enterRoyal)
                || (pc.isKnight() && this._enterKnight)
                || (pc.isWizard() && this._enterMage)
                || (pc.isElf() && this._enterElf)
                || (pc.isDarkelf() && this._enterDarkelf)
                || (pc.isDragonKnight() && this._enterDragonKnight) || (pc
                .isIllusionist() && this._enterIllusionist))) {
            return false;
        }

        return true;
    }

    public boolean canUsePot() {
        return this._usePot;
    }

    public boolean checkUbTime() {
        final SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        final Calendar realTime = getRealTime();
        realTime.add(Calendar.MINUTE, BEFORE_MINUTE);
        final int nowTime = Integer.valueOf(sdf.format(realTime.getTime()));
        return this._ubTimes.contains(nowTime);
    }

    /**
     * 删除竞技场内所有的怪物与道具。
     */
    void clearColosseum() {
        for (final Object obj : L1World.getInstance()
                .getVisibleObjects(this._mapId).values()) {
            if (obj instanceof L1MonsterInstance) // 删除怪物
            {
                final L1MonsterInstance mob = (L1MonsterInstance) obj;
                if (!mob.isDead()) {
                    mob.setDead(true);
                    mob.setStatus(ActionCodes.ACTION_Die);
                    mob.setCurrentHpDirect(0);
                    mob.deleteMe();

                }
            } else if (obj instanceof L1Inventory) // 删除道具
            {
                final L1Inventory inventory = (L1Inventory) obj;
                inventory.clearItems();
            }
        }
    }

    /**
     * 清除参加人员名单。
     */
    public void clearMembers() {
        this._members.clear();
    }

    public boolean containsManager(final int npcId) {
        return this._managers.contains(npcId);
    }

    public int getHpr() {
        return this._hpr;
    }

    public L1Location getLocation() {
        return this._location;
    }

    public int getLocX1() {
        return this._locX1;
    }

    public int getLocX2() {
        return this._locX2;
    }

    public int getLocY1() {
        return this._locY1;
    }

    public int getLocY2() {
        return this._locY2;
    }

    public short getMapId() {
        return this._mapId;
    }

    public int getMaxLevel() {
        return this._maxLevel;
    }

    public int getMaxPlayer() {
        return this._maxPlayer;
    }

    /**
     * 返回参加的角色是否组队。
     * 
     * @return 参加者的组队
     */
    public L1PcInstance[] getMembersArray() {
        return this._members.toArray(new L1PcInstance[this._members.size()]);
    }

    /**
     * 返回参与成员数量。
     * 
     * @return 参加人数
     */
    public int getMembersCount() {
        return this._members.size();
    }

    public int getMinLevel() {
        return this._minLevel;
    }

    public int getMpr() {
        return this._mpr;
    }

    public String getNextUbTime() {
        return intToTimeFormat(this.nextUbTime());
    }

    public int getUbId() {
        return this._ubId;
    }

    /**
     * @return UB入場可能～競技終了true,否则false。
     */
    public boolean isActive() {
        return this._active;
    }

    /**
     * 玩家、返回是否参加。
     * 
     * @param pc
     *            检查玩家
     * @return 如果参加true、否则false。
     */
    public boolean isMember(final L1PcInstance pc) {
        return this._members.contains(pc);
    }

    /**
     * 返回是否在UB中。
     * 
     * @return UB中true、否则false。
     */
    public boolean isNowUb() {
        return this._isNowUb;
    }

    public String[] makeUbInfoStrings() {
        if (this._ubInfo != null) {
            return this._ubInfo;
        }
        final String nextUbTime = this.getNextUbTime();
        // 类
        final StringBuilder classesBuff = new StringBuilder();
        if (this._enterDarkelf) {
            classesBuff.append("黑暗精灵 ");
        }
        if (this._enterMage) {
            classesBuff.append("魔法师 ");
        }
        if (this._enterElf) {
            classesBuff.append("精灵 ");
        }
        if (this._enterKnight) {
            classesBuff.append("骑士 ");
        }
        if (this._enterRoyal) {
            classesBuff.append("王族 ");
        }
        if (this._enterDragonKnight) {
            classesBuff.append("龙骑士 ");
        }
        if (this._enterIllusionist) {
            classesBuff.append("幻术师 ");
        }
        final String classes = classesBuff.toString().trim();
        // 性別
        final StringBuilder sexBuff = new StringBuilder();
        if (this._enterMale) {
            sexBuff.append("男 ");
        }
        if (this._enterFemale) {
            sexBuff.append("女 ");
        }
        final String sex = sexBuff.toString().trim();
        final String loLevel = String.valueOf(this._minLevel);
        final String hiLevel = String.valueOf(this._maxLevel);
        final String teleport = this._location.getMap().isEscapable() ? "可能"
                : "不可能";
        final String res = this._location.getMap().isUseResurrection() ? "可能"
                : "不可能";
        final String pot = "可能";
        final String hpr = String.valueOf(this._hpr);
        final String mpr = String.valueOf(this._mpr);
        final String summon = this._location.getMap().isTakePets() ? "可能"
                : "不可能";
        final String summon2 = this._location.getMap().isRecallPets() ? "可能"
                : "不可能";
        this._ubInfo = new String[] { nextUbTime, classes, sex, loLevel,
                hiLevel, teleport, res, pot, hpr, mpr, summon, summon2 };
        return this._ubInfo;
    }

    private int nextUbTime() {
        final SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        final int nowTime = Integer
                .valueOf(sdf.format(getRealTime().getTime()));
        SortedSet<Integer> tailSet = this._ubTimes.tailSet(nowTime);
        if (tailSet.isEmpty()) {
            tailSet = this._ubTimes;
        }
        return tailSet.first();
    }

    /**
     * 删除参加的角色名单。
     * 
     * @param pc
     *            删除角色
     */
    public void removeMember(final L1PcInstance pc) {
        this._members.remove(pc);
    }

    /**
     * 从成员列表删除退出人员。
     */
    void removeRetiredMembers() {
        final L1PcInstance[] temp = this.getMembersArray();
        for (final L1PcInstance element : temp) {
            if (element.getMapId() != this._mapId) {
                this.removeMember(element);
            }
        }
    }

    // setされたlocx1～locy2から中心点を求める。
    public void resetLoc() {
        this._locX = (this._locX2 + this._locX1) / 2;
        this._locY = (this._locY2 + this._locY1) / 2;
        this._location = new L1Location(this._locX, this._locY, this._mapId);
    }

    /**
     * UB参加人员发送信息(S_ServerMessage)。
     * 
     * @param type
     *            消息类型
     * @param msg
     *            发送消息
     */
    void sendMessage(final int type, final String msg) {
        for (final L1PcInstance pc : this.getMembersArray()) {
            pc.sendPackets(new S_ServerMessage(type, msg));
        }
    }

    /**
     * 全面开始时发送消息。
     * 
     * @param curRound
     *            回合开始
     */
    void sendRoundMessage(final int curRound) {
        // XXX - 此ID错误
        final int MSGID_ROUND_TABLE[] = { 893, 894, 895, 896 };

        this.sendMessage(MSGID_ROUND_TABLE[curRound - 1], "");
    }

    void setActive(final boolean f) {
        this._active = f;
    }

    public void setEnterDarkelf(final boolean enterDarkelf) {
        this._enterDarkelf = enterDarkelf;
    }

    public void setEnterDragonKnight(final boolean enterDragonKnight) {
        this._enterDragonKnight = enterDragonKnight;
    }

    public void setEnterElf(final boolean enterElf) {
        this._enterElf = enterElf;
    }

    public void setEnterFemale(final boolean enterFemale) {
        this._enterFemale = enterFemale;
    }

    public void setEnterIllusionist(final boolean enterIllusionist) {
        this._enterIllusionist = enterIllusionist;
    }

    public void setEnterKnight(final boolean enterKnight) {
        this._enterKnight = enterKnight;
    }

    public void setEnterMage(final boolean enterMage) {
        this._enterMage = enterMage;
    }

    public void setEnterMale(final boolean enterMale) {
        this._enterMale = enterMale;
    }

    public void setEnterRoyal(final boolean enterRoyal) {
        this._enterRoyal = enterRoyal;
    }

    public void setHpr(final int hpr) {
        this._hpr = hpr;
    }

    public void setLocX1(final int locX1) {
        this._locX1 = locX1;
    }

    public void setLocX2(final int locX2) {
        this._locX2 = locX2;
    }

    public void setLocY1(final int locY1) {
        this._locY1 = locY1;
    }

    public void setLocY2(final int locY2) {
        this._locY2 = locY2;
    }

    public void setMapId(final short mapId) {
        this._mapId = mapId;
    }

    public void setMaxLevel(final int level) {
        this._maxLevel = level;
    }

    public void setMaxPlayer(final int count) {
        this._maxPlayer = count;
    }

    public void setMinLevel(final int level) {
        this._minLevel = level;
    }

    public void setMpr(final int mpr) {
        this._mpr = mpr;
    }

    /**
     * 设置UB。
     * 
     * @param i
     *            true/false
     */
    void setNowUb(final boolean i) {
        this._isNowUb = i;
    }

    public void setUbId(final int id) {
        this._ubId = id;
    }

    public void setUsePot(final boolean usePot) {
        this._usePot = usePot;
    }

    /**
     * 在竞技场出现的道具。
     * 
     * @param itemId
     *            出现道具的编号ID
     * @param stackCount
     *            道具的堆叠数量
     * @param count
     *            出现数量
     */
    private void spawnGroundItem(final int itemId, final int stackCount,
            final int count) {
        final L1Item temp = ItemTable.getInstance().getTemplate(itemId);
        if (temp == null) {
            return;
        }

        for (int i = 0; i < count; i++) {
            final L1Location loc = this._location.randomLocation(
                    (this.getLocX2() - this.getLocX1()) / 2, false);
            if (temp.isStackable()) {
                final L1ItemInstance item = ItemTable.getInstance().createItem(
                        itemId);
                item.setEnchantLevel(0);
                item.setCount(stackCount);
                final L1GroundInventory ground = L1World.getInstance()
                        .getInventory(loc.getX(), loc.getY(), this._mapId);
                if (ground.checkAddItem(item, stackCount) == L1Inventory.OK) {
                    ground.storeItem(item);
                }
            } else {
                L1ItemInstance item = null;
                for (int createCount = 0; createCount < stackCount; createCount++) {
                    item = ItemTable.getInstance().createItem(itemId);
                    item.setEnchantLevel(0);
                    final L1GroundInventory ground = L1World.getInstance()
                            .getInventory(loc.getX(), loc.getY(), this._mapId);
                    if (ground.checkAddItem(item, stackCount) == L1Inventory.OK) {
                        ground.storeItem(item);
                    }
                }
            }
        }
    }

    /**
     * 药水等补给项目出现。
     * 
     * @param curRound
     *            本轮
     */
    void spawnSupplies(final int curRound) {
        if (curRound == 1) {
            this.spawnGroundItem(L1ItemId.ADENA, 1000, 60);
            this.spawnGroundItem(L1ItemId.POTION_OF_CURE_POISON, 3, 20);
            this.spawnGroundItem(L1ItemId.POTION_OF_EXTRA_HEALING, 5, 20);
            this.spawnGroundItem(L1ItemId.POTION_OF_GREATER_HEALING, 3, 20);
            this.spawnGroundItem(40317, 1, 5); // 磨刀石
            this.spawnGroundItem(40079, 1, 20); // 传送回家的卷轴
        } else if (curRound == 2) {
            this.spawnGroundItem(L1ItemId.ADENA, 5000, 50);
            this.spawnGroundItem(L1ItemId.POTION_OF_CURE_POISON, 5, 20);
            this.spawnGroundItem(L1ItemId.POTION_OF_EXTRA_HEALING, 10, 20);
            this.spawnGroundItem(L1ItemId.POTION_OF_GREATER_HEALING, 5, 20);
            this.spawnGroundItem(40317, 1, 7); // 磨刀石
            this.spawnGroundItem(40093, 1, 10); // 空的魔法卷轴(Lv4)
            this.spawnGroundItem(40079, 1, 5); // 传送回家的卷轴
        } else if (curRound == 3) {
            this.spawnGroundItem(L1ItemId.ADENA, 10000, 30);
            this.spawnGroundItem(L1ItemId.POTION_OF_CURE_POISON, 7, 20);
            this.spawnGroundItem(L1ItemId.POTION_OF_EXTRA_HEALING, 20, 20);
            this.spawnGroundItem(L1ItemId.POTION_OF_GREATER_HEALING, 10, 20);
            this.spawnGroundItem(40317, 1, 10); // 磨刀石
            this.spawnGroundItem(40094, 1, 10); // 空的魔法卷轴(Lv5)
        }
    }

    /**
     * 开始无限大战。
     * 
     * @param ubId
     *            开始无限大战的ID
     */
    public void start() {
        final int patternsMax = UBSpawnTable.getInstance().getMaxPattern(
                this._ubId);
        this._pattern = Random.nextInt(patternsMax) + 1; // 确定出现模式

        final UbThread ub = new UbThread();
        GeneralThreadPool.getInstance().execute(ub);
    }
}
