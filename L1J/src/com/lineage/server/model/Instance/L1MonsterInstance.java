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
package com.lineage.server.model.Instance;

import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLOODSTAIN_OF_ANTHARAS;
import static com.lineage.server.model.skill.L1SkillId.FOG_OF_SLEEPING;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.ActionCodes;
import com.lineage.server.GeneralThreadPool;
import com.lineage.server.IdFactory;
import com.lineage.server.datatables.DropTable;
import com.lineage.server.datatables.NPCTalkDataTable;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.datatables.SprTable;
import com.lineage.server.datatables.UBTable;
import com.lineage.server.model.L1Attack;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1DragonSlayer;
import com.lineage.server.model.L1Location;
import com.lineage.server.model.L1NpcTalkData;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1UltimateBattle;
import com.lineage.server.model.L1World;
import com.lineage.server.model.skill.L1BuffUtil;
import com.lineage.server.serverpackets.S_ChangeName;
import com.lineage.server.serverpackets.S_CharVisualUpdate;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_NPCPack;
import com.lineage.server.serverpackets.S_NPCTalkReturn;
import com.lineage.server.serverpackets.S_NpcChangeShape;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillBrave;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.utils.CalcExp;
import com.lineage.server.utils.Random;

/**
 * 怪物控制项
 */
public class L1MonsterInstance extends L1NpcInstance {

    class Death implements Runnable {
        L1Character _lastAttacker;

        public Death(final L1Character lastAttacker) {
            this._lastAttacker = lastAttacker;
        }

        @Override
        public void run() {
            L1MonsterInstance.this.setDeathProcessing(true);
            L1MonsterInstance.this.setCurrentHpDirect(0);
            L1MonsterInstance.this.setDead(true);
            L1MonsterInstance.this.setStatus(ActionCodes.ACTION_Die);

            L1MonsterInstance.this.getMap().setPassable(
                    L1MonsterInstance.this.getLocation(), true);

            L1MonsterInstance.this.broadcastPacket(new S_DoActionGFX(
                    L1MonsterInstance.this.getId(), ActionCodes.ACTION_Die));
            // 变形判断
            L1MonsterInstance.this.onDoppel(false);

            L1MonsterInstance.this.startChat(CHAT_TIMING_DEAD);

            L1MonsterInstance.this.distributeExpDropKarma(this._lastAttacker);
            L1MonsterInstance.this.giveUbSeal();

            L1MonsterInstance.this.setDeathProcessing(false);

            L1MonsterInstance.this.setExp(0);
            L1MonsterInstance.this.setKarma(0);
            L1MonsterInstance.this.allTargetClear();

            L1MonsterInstance.this.startDeleteTimer();
        }
    }

    class NextDragonStep implements Runnable {
        L1PcInstance _pc;
        L1MonsterInstance _mob;
        int _npcid;
        int _transformId;
        int _x;
        int _y;
        int _h;
        short _m;
        L1Location _loc = new L1Location();

        public NextDragonStep(final L1PcInstance pc,
                final L1MonsterInstance mob, final int transformId) {
            this._pc = pc;
            this._mob = mob;
            this._transformId = transformId;
            this._x = mob.getX();
            this._y = mob.getY();
            this._h = mob.getHeading();
            this._m = mob.getMapId();
            this._loc = mob.getLocation();
        }

        @Override
        public void run() {
            L1MonsterInstance.this.setNextDragonStepRunning(true);
            try {
                Thread.sleep(10500);
                final L1NpcInstance npc = NpcTable.getInstance()
                        .newNpcInstance(this._transformId);
                npc.setId(IdFactory.getInstance().nextId());
                npc.setMap(this._m);
                npc.setHomeX(this._x);
                npc.setHomeY(this._y);
                npc.setHeading(this._h);
                npc.getLocation().set(this._loc);
                npc.getLocation().forward(this._h);
                npc.setPortalNumber(L1MonsterInstance.this.getPortalNumber());

                L1MonsterInstance.this.broadcastPacket(new S_NPCPack(npc));
                L1MonsterInstance.this.broadcastPacket(new S_DoActionGFX(npc
                        .getId(), ActionCodes.ACTION_Hide));

                L1World.getInstance().storeObject(npc);
                L1World.getInstance().addVisibleObject(npc);
                npc.turnOnOffLight();
                npc.startChat(L1NpcInstance.CHAT_TIMING_APPEARANCE); // 开始喊话
                L1MonsterInstance.this.setNextDragonStepRunning(false);
            } catch (final InterruptedException e) {
            }
        }
    }

    private static final long serialVersionUID = 1L;

    private static Logger _log = Logger.getLogger(L1MonsterInstance.class
            .getName());

    private static void openDoorWhenNpcDied(final L1NpcInstance npc) {
        final int[] npcId = { 46143, 46144, 46145, 46146, 46147, 46148, 46149,
                46150, 46151, 46152 };
        final int[] doorId = { 5001, 5002, 5003, 5004, 5005, 5006, 5007, 5008,
                5009, 5010 };

        for (int i = 0; i < npcId.length; i++) {
            if (npc.getNpcTemplate().get_npcId() == npcId[i]) {
                openDoorInCrystalCave(doorId[i]);
                break;
            }
        }
    }

    private boolean _storeDroped; // ドロップアイテムの读迂が完了したか

    private boolean isDoppel;

    // 寻找目标
    public static int[][] _classGfxId = { { 0, 1 }, { 48, 61 }, { 37, 138 },
            { 734, 1186 }, { 2786, 2796 } };

    private static void openDoorInCrystalCave(final int doorId) {
        for (final L1Object object : L1World.getInstance().getObject()) {
            if (object instanceof L1DoorInstance) {
                final L1DoorInstance door = (L1DoorInstance) object;
                if (door.getDoorId() == doorId) {
                    door.open();
                }
            }
        }
    }

    private int _ubSealCount = 0; // UBで倒された时、给予参加者勇者之证的个数

    private int _ubId = 0; // UBID

    private boolean _nextDragonStepRunning = false;

    public L1MonsterInstance(final L1Npc template) {
        super(template);
        this._storeDroped = false;
    }

    /** 龙之血痕 */
    private void bloodstain() {
        for (final L1PcInstance pc : L1World.getInstance().getVisiblePlayer(
                this, 50)) {
            if (this.getNpcTemplate().get_npcId() == 97008) { // 安塔瑞斯 第三阶段
                pc.sendPackets(new S_ServerMessage(1580)); // 安塔瑞斯：黑暗的诅咒将会降临到你们身上！席琳，
                                                           // 我的母亲，请让我安息吧...
                L1BuffUtil.bloodstain(pc, (byte) 0, 4320, true);
            } else if (this.getNpcTemplate().get_npcId() == 97046) { // 法利昂 第三阶段
                pc.sendPackets(new S_ServerMessage(1668)); // 法利昂：莎尔...你这个家伙...怎么...对得起我的母亲...席琳啊...请拿走...我的生命吧...
                L1BuffUtil.bloodstain(pc, (byte) 1, 4320, true);
            }
        }
    }

    private void distributeDrop() {
        final ArrayList<L1Character> dropTargetList = this._dropHateList
                .toTargetArrayList();
        final ArrayList<Integer> dropHateList = this._dropHateList
                .toHateArrayList();
        try {
            final int npcId = this.getNpcTemplate().get_npcId();
            if ((npcId != 45640) // 梦幻岛 独角兽
                    || ((npcId == 45640) && (this.getTempCharGfx() == 2332))) {
                DropTable.getInstance().dropShare(L1MonsterInstance.this,
                        dropTargetList, dropHateList);
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    void distributeExpDropKarma(L1Character lastAttacker) {
        if (lastAttacker == null) {
            return;
        }
        L1PcInstance pc = null;
        if (lastAttacker instanceof L1PcInstance) {
            pc = (L1PcInstance) lastAttacker;
        } else if (lastAttacker instanceof L1PetInstance) {
            pc = (L1PcInstance) ((L1PetInstance) lastAttacker).getMaster();
        } else if (lastAttacker instanceof L1SummonInstance) {
            pc = (L1PcInstance) ((L1SummonInstance) lastAttacker).getMaster();
        }

        if (pc != null) {
            final ArrayList<L1Character> targetList = this._hateList
                    .toTargetArrayList();
            final ArrayList<Integer> hateList = this._hateList
                    .toHateArrayList();
            final long exp = this.getExp();
            CalcExp.calcExp(pc, this.getId(), targetList, hateList, exp);
            // 死亡した场合はドロップとカルマも分配、死亡せず变身した场合はEXPのみ
            if (this.isDead()) {
                this.distributeDrop();
                this.giveKarma(pc);
            }
        } else if (lastAttacker instanceof L1EffectInstance) { // FWが倒した场合
            final ArrayList<L1Character> targetList = this._hateList
                    .toTargetArrayList();
            final ArrayList<Integer> hateList = this._hateList
                    .toHateArrayList();
            // ヘイトリストにキャラクターが存在する
            if (!hateList.isEmpty()) {
                // 最大ヘイトを持つキャラクターが倒したものとする
                int maxHate = 0;
                for (int i = hateList.size() - 1; i >= 0; i--) {
                    if (maxHate < (hateList.get(i))) {
                        maxHate = (hateList.get(i));
                        lastAttacker = targetList.get(i);
                    }
                }
                if (lastAttacker instanceof L1PcInstance) {
                    pc = (L1PcInstance) lastAttacker;
                } else if (lastAttacker instanceof L1PetInstance) {
                    pc = (L1PcInstance) ((L1PetInstance) lastAttacker)
                            .getMaster();
                } else if (lastAttacker instanceof L1SummonInstance) {
                    pc = (L1PcInstance) ((L1SummonInstance) lastAttacker)
                            .getMaster();
                }
                if (pc != null) {
                    final long exp = this.getExp();
                    CalcExp.calcExp(pc, this.getId(), targetList, hateList, exp);
                    // 死亡した场合はドロップとカルマも分配、死亡せず变身した场合はEXPのみ
                    if (this.isDead()) {
                        this.distributeDrop();
                        this.giveKarma(pc);
                    }
                }
            }
        }
    }

    /** 下一阶段的龙 */
    private void doNextDragonStep(final L1Character attacker, final int npcid) {
        if (!this.isNextDragonStepRunning()) {
            final int[] dragonId = { 97006, // 安塔瑞斯 第一阶段
                    97007, // 安塔瑞斯 第二阶段
                    97044, // 法利昂 第一阶段
                    97045
            // 法利昂 第二阶段
            };
            final int[] nextStepId = { 97007, // 安塔瑞斯 第二阶段
                    97008, // 安塔瑞斯 第三阶段
                    97045, // 法利昂 第二阶段
                    97046
            // 法利昂 第三阶段
            };
            int nextSpawnId = 0;
            for (int i = 0; i < dragonId.length; i++) {
                if (npcid == dragonId[i]) {
                    nextSpawnId = nextStepId[i];
                    break;
                }
            }
            if ((attacker != null) && (nextSpawnId > 0)) {
                L1PcInstance _pc = null;
                if (attacker instanceof L1PcInstance) {
                    _pc = (L1PcInstance) attacker;
                } else if (attacker instanceof L1PetInstance) {
                    final L1PetInstance pet = (L1PetInstance) attacker;
                    final L1Character cha = pet.getMaster();
                    if (cha instanceof L1PcInstance) {
                        _pc = (L1PcInstance) cha;
                    }
                } else if (attacker instanceof L1SummonInstance) {
                    final L1SummonInstance summon = (L1SummonInstance) attacker;
                    final L1Character cha = summon.getMaster();
                    if (cha instanceof L1PcInstance) {
                        _pc = (L1PcInstance) cha;
                    }
                }
                if (_pc != null) {
                    final NextDragonStep nextDragonStep = new NextDragonStep(
                            _pc, this, nextSpawnId);
                    GeneralThreadPool.getInstance().execute(nextDragonStep);
                }
            }
        }
    }

    public int getUbId() {
        return this._ubId;
    }

    public int getUbSealCount() {
        return this._ubSealCount;
    }

    /** 给予友好度 */
    private void giveKarma(final L1PcInstance pc) {
        int karma = this.getKarma();
        if (karma != 0) {
            final int karmaSign = Integer.signum(karma);
            final int pcKarmaLevel = pc.getKarmaLevel();
            final int pcKarmaLevelSign = Integer.signum(pcKarmaLevel);
            // カルマ背信行为は5倍
            if ((pcKarmaLevelSign != 0) && (karmaSign != pcKarmaLevelSign)) {
                karma *= 5;
            }
            // カルマは止めを刺したプレイヤーに设定。ペットorサモンで倒した场合も入る。
            pc.addKarma((int) (karma * Config.RATE_KARMA));
        }
    }

    /** 给予UB勋章 */
    void giveUbSeal() {
        if (this.getUbSealCount() != 0) { // UB勇者的勋章
            final L1UltimateBattle ub = UBTable.getInstance().getUb(
                    this.getUbId());
            if (ub != null) {
                for (final L1PcInstance pc : ub.getMembersArray()) {
                    if ((pc != null) && !pc.isDead() && !pc.isGhost()) {
                        final L1ItemInstance item = pc.getInventory()
                                .storeItem(41402, this.getUbSealCount()); // 勇者的勋章
                        pc.sendPackets(new S_ServerMessage(403, item
                                .getLogName())); // 获得%0%o 。
                    }
                }
            }
        }
    }

    /**
     * 隐藏动作
     */
    private void hide() {
        final int npcid = this.getNpcTemplate().get_npcId();
        switch (npcid) {
            case 45061: // 弱化史巴托 (新手村)
            case 45161: // 弱化史巴托 (普通)
            case 45181: // 史巴托 (SC)
            case 45455: // 残暴的史巴托 (傲慢塔)
                if (this.getMaxHp() / 3 > this.getCurrentHp()) {
                    final int rnd = Random.nextInt(10);
                    if (2 > rnd) {
                        this.allTargetClear();
                        this.setHiddenStatus(HIDDEN_STATUS_SINK);
                        this.broadcastPacket(new S_DoActionGFX(this.getId(),
                                ActionCodes.ACTION_Hide));
                        this.setStatus(11);
                        this.broadcastPacket(new S_CharVisualUpdate(this, this
                                .getStatus()));
                    }
                }
                break;

            case 45067: // 弱化哈维 (新手村庄)
            case 45264: // 哈维 (普通)
            case 45452: // 哈维 (遗忘之岛)
            case 45090: // 弱化格利芬 (新手村庄)
            case 45321: // 格利芬 (普通)
            case 45445: // 格利芬 (遗忘之岛)
                if (this.getMaxHp() / 3 > this.getCurrentHp()) {
                    final int rnd = Random.nextInt(10);
                    if (2 > rnd) {
                        this.allTargetClear();
                        this.setHiddenStatus(HIDDEN_STATUS_FLY);
                        this.broadcastPacket(new S_DoActionGFX(this.getId(),
                                ActionCodes.ACTION_Moveup));
                    }
                }
                break;

            case 46107: // 底比斯 曼陀罗草(白)
            case 46108: // 底比斯 曼陀罗草(黑)
                if (this.getMaxHp() / 4 > this.getCurrentHp()) {
                    final int rnd = Random.nextInt(10);
                    if (2 > rnd) {
                        this.allTargetClear();
                        this.setHiddenStatus(HIDDEN_STATUS_SINK);
                        this.broadcastPacket(new S_DoActionGFX(this.getId(),
                                ActionCodes.ACTION_Hide));
                        this.setStatus(11);
                        this.broadcastPacket(new S_CharVisualUpdate(this, this
                                .getStatus()));
                    }
                }
                break;

            case 45681: // 林德拜尔 (亚丁:风龙的伤痕)
                if (this.getMaxHp() / 3 > this.getCurrentHp()) {
                    final int rnd = Random.nextInt(50);
                    if (1 > rnd) {
                        this.allTargetClear();
                        this.setHiddenStatus(HIDDEN_STATUS_FLY);
                        this.broadcastPacket(new S_DoActionGFX(this.getId(),
                                ActionCodes.ACTION_Moveup));
                    }
                }
                break;

            case 45682: // 安塔瑞斯 (龙之谷地监7)
                if (this.getMaxHp() / 3 > this.getCurrentHp()) {
                    final int rnd = Random.nextInt(50);
                    if (1 > rnd) {
                        this.allTargetClear();
                        this.setHiddenStatus(HIDDEN_STATUS_SINK);
                        this.broadcastPacket(new S_DoActionGFX(this.getId(),
                                ActionCodes.ACTION_AntharasHide));
                        this.setStatus(20);
                        this.broadcastPacket(new S_CharVisualUpdate(this, this
                                .getStatus()));
                    }
                }
                break;

            case 81163: // 吉尔塔斯 (黑暗妖精圣地)
                if (this.getCurrentHp() <= 5000) {
                    this.allTargetClear();
                    this.setHiddenStatus(HIDDEN_STATUS_COUNTERATTACK_BARRIER);
                    this.broadcastPacket(new S_DoActionGFX(this.getId(),
                            ActionCodes.ACTION_Hide));
                    this.setStatus(4);
                    this.broadcastPacket(new S_CharVisualUpdate(this, this
                            .getStatus()));
                }
                break;
        }
    }

    /**
     * 初始化隐藏动作 (出现)
     */
    public void initHide() {
        // 出现的隐藏动作
        // MOB有一定概率钻入地下或飞到天上
        final int npcid = this.getNpcTemplate().get_npcId();
        switch (npcid) {

            case 45061: // 弱化史巴托 (新手村)
            case 45161: // 弱化史巴托 (普通)
            case 45181: // 史巴托 (SC)
            case 45455: // 残暴的史巴托 (傲慢塔)
            case 46107: // 底比斯 曼陀罗草 (白)
            case 46108: // 底比斯 曼陀罗草 (黑)
                final int rnd = Random.nextInt(3);
                if (1 > rnd) {
                    this.setHiddenStatus(HIDDEN_STATUS_SINK);
                    this.setStatus(11);
                }
                break;

            case 45045: // 弱化石头高仑 (新手村)
            case 45126: // 石头高仑 (普通)
            case 45134: // 石头高仑 (SC)
            case 45281: // 奇岩 石头高仑 (奇岩地监)
                final int rnd1 = Random.nextInt(3);
                if (1 > rnd1) {
                    this.setHiddenStatus(HIDDEN_STATUS_SINK);
                    this.setStatus(4);
                }
                break;

            case 45067: // 弱化哈维 (新手村庄)
            case 45264: // 哈维 (普通)
            case 45452: // 哈维 (遗忘之岛)
            case 45090: // 弱化格利芬 (新手村庄)
            case 45321: // 格利芬 (普通)
            case 45445: // 格利芬 (遗忘之岛)
            case 45681: // 林德拜尔 (亚丁:风龙的伤痕)
                this.setHiddenStatus(HIDDEN_STATUS_FLY);
                break;

            case 46125: // 钢铁高仑 (水晶洞穴)
            case 46126: // 莱肯 (水晶洞穴)
            case 46127: // 欧熊 (水晶洞穴)
            case 46128: // 冰原老虎 (水晶洞穴)
                this.setHiddenStatus(L1NpcInstance.HIDDEN_STATUS_ICE);
                this.setStatus(4);
                break;

            case 81163: // 吉尔塔斯 (黑暗妖精圣地)
                this.setHiddenStatus(HIDDEN_STATUS_COUNTERATTACK_BARRIER);
                this.setStatus(4);
                break;
        }
    }

    /**
     * 初始化隐藏动作 (一直出现)
     */
    public void initHideForMinion(final L1NpcInstance leader) {
        // 怪物一直出现的隐藏动作（读取相同的动作）
        // 取得NPC编号
        final int npcid = this.getNpcTemplate().get_npcId();
        final int HiddenStatus = leader.getHiddenStatus();
        switch (HiddenStatus) {
            case HIDDEN_STATUS_SINK: // 隐藏状态 (遁地)
                switch (npcid) {
                    case 45061: // 弱化史巴托 (新手村)
                    case 45161: // 弱化史巴托 (普通)
                    case 45181: // 史巴托 (SC)
                    case 45455: // 残暴的史巴托 (傲慢塔)
                    case 46107: // 底比斯 曼陀罗草 (白)
                    case 46108: // 底比斯 曼陀罗草 (黑)
                        this.setHiddenStatus(HIDDEN_STATUS_SINK);
                        this.setStatus(11);
                        break;

                    case 45045: // 弱化石头高仑 (新手村)
                    case 45126: // 石头高仑 (普通)
                    case 45134: // 石头高仑 (SC)
                    case 45281: // 奇岩 石头高仑 (奇岩地监)
                        this.setHiddenStatus(HIDDEN_STATUS_SINK);
                        this.setStatus(4);
                        break;
                }
                break;

            case HIDDEN_STATUS_FLY: // 隐藏状态 (飞天)
                switch (npcid) {
                    case 45067: // 弱化哈维 (新手村庄)
                    case 45264: // 哈维 (普通)
                    case 45452: // 哈维 (遗忘之岛)
                    case 45090: // 弱化格利芬 (新手村庄)
                    case 45321: // 格利芬 (普通)
                    case 45445: // 格利芬 (遗忘之岛)
                        this.setHiddenStatus(HIDDEN_STATUS_FLY);
                        this.setStatus(4);
                        break;

                    case 45681: // 林德拜尔 (亚丁:风龙的伤痕)
                        this.setHiddenStatus(HIDDEN_STATUS_FLY);
                        this.setStatus(11);
                        break;
                }
                break;

            case HIDDEN_STATUS_COUNTERATTACK_BARRIER: // 隐藏状态 (反击屏障)
                switch (npcid) {
                    case 81163: // 吉尔塔斯 (黑暗妖精圣地)
                        this.setHiddenStatus(HIDDEN_STATUS_COUNTERATTACK_BARRIER);
                        this.setStatus(4);
                        break;
                }
                break;

            case HIDDEN_STATUS_ICE: // 隐藏状态 (冰冻)
                switch (npcid) {
                    case 46125: // 钢铁高仑 (水晶洞穴)
                    case 46126: // 莱肯 (水晶洞穴)
                    case 46127: // 欧熊 (水晶洞穴)
                    case 46128: // 冰原老虎 (水晶洞穴)
                        this.setHiddenStatus(L1NpcInstance.HIDDEN_STATUS_ICE);
                        this.setStatus(4);
                        break;
                }
                break;
        }
    }

    public boolean is_storeDroped() {
        return this._storeDroped;
    }

    protected boolean isNextDragonStepRunning() {
        return this._nextDragonStepRunning;
    }

    @Override
    public void onAction(final L1PcInstance pc) {
        this.onAction(pc, 0);
    }

    @Override
    public void onAction(final L1PcInstance pc, final int skillId) {
        if ((this.getCurrentHp() > 0) && !this.isDead()) {
            final L1Attack attack = new L1Attack(pc, this, skillId);
            if (attack.calcHit()) {
                attack.calcDamage();
                attack.calcStaffOfMana();
                attack.addPcPoisonAttack(pc, this);
                attack.addChaserAttack();
            }
            attack.action();
            attack.commit();
        }
    }

    // 变形怪变成玩家判断
    @Override
    public void onDoppel(final boolean isChangeShape) {
        if (this.getNpcTemplate().is_doppel()) {
            boolean updateObject = false;

            if (!isChangeShape) { // 复原
                updateObject = true;
                // setName(getNpcTemplate().get_name());
                // setNameId(getNpcTemplate().get_nameid());
                this.setTempLawful(this.getNpcTemplate().get_lawful());
                this.setGfxId(this.getNpcTemplate().get_gfxid());
                this.setTempCharGfx(this.getNpcTemplate().get_gfxid());
            } else if (!this.isDoppel && (this._target instanceof L1PcInstance)) { // 未变形
                this.setSleepTime(300);
                final L1PcInstance targetPc = (L1PcInstance) this._target;
                this.isDoppel = true;
                updateObject = true;
                this.setName(targetPc.getName());
                this.setNameId(targetPc.getName());
                this.setTempLawful(targetPc.getLawful());
                this.setGfxId(targetPc.getClassId());
                this.setTempCharGfx(targetPc.getClassId());

                if (targetPc.getClassId() != 6671) { // 非幻术师拿剑
                    this.setStatus(4);
                } else { // 幻术师拿斧头
                    this.setStatus(11);
                }
            }
            // 移动、攻击速度
            this.setPassispeed(SprTable.getInstance().getMoveSpeed(
                    this.getTempCharGfx(), this.getStatus()));
            this.setAtkspeed(SprTable.getInstance().getAttackSpeed(
                    this.getTempCharGfx(), this.getStatus() + 1));
            // 变形
            if (updateObject) {
                for (final L1PcInstance pc : L1World.getInstance()
                        .getRecognizePlayer(this)) {
                    if (!isChangeShape) {
                        pc.sendPackets(new S_ChangeName(this.getId(), this
                                .getNpcTemplate().get_nameid()));
                    } else {
                        pc.sendPackets(new S_ChangeName(this.getId(), this
                                .getNameId()));
                    }
                    pc.sendPackets(new S_NpcChangeShape(this.getId(), this
                            .getGfxId(), this.getTempLawful(), this.getStatus()));
                }
            }
        }
    }

    // 使用的物品处理
    @Override
    public void onItemUse() {
        if (!this.isActived() && (this._target != null)) {
            this.useItem(USEITEM_HASTE, 40); // ４０％使用加速药水
            // 变形判断
            this.onDoppel(true);
        }
        if (this.getCurrentHp() * 100 / this.getMaxHp() < 40) { // ＨＰが４０％きったら
            this.useItem(USEITEM_HEAL, 50); // ５０％の确率で回复ポーション使用
        }
    }

    @Override
    public void onNpcAI() {
        if (this.isAiRunning()) {
            return;
        }
        if (!this._storeDroped) // 无驮なオブジェクトＩＤを発行しないようにここでセット
        {
            DropTable.getInstance().setDrop(this, this.getInventory());
            this.getInventory().shuffle();
            this._storeDroped = true;
        }
        this.setActived(false);
        this.startAI();
    }

    // 荧幕范围内进入玩家
    @Override
    public void onPerceive(final L1PcInstance perceivedFrom) {
        perceivedFrom.addKnownObject(this);
        if (0 < this.getCurrentHp()) {
            perceivedFrom.sendPackets(new S_NPCPack(this));
            this.onNpcAI(); // 怪物AI，开始
            if (this.getBraveSpeed() == 1) { // 二段加速状态
                perceivedFrom.sendPackets(new S_SkillBrave(this.getId(), 1,
                        600000));
                this.setBraveSpeed(1);
            }
        } else {
            // 水龙 阶段一、二 死亡隐形
            if ((this.getGfxId() != 7864) && (this.getGfxId() != 7869)) {
                perceivedFrom.sendPackets(new S_NPCPack(this));
            }
        }
    }

    @Override
    public void onTalkAction(final L1PcInstance pc) {
        final int objid = this.getId();
        final L1NpcTalkData talking = NPCTalkDataTable.getInstance()
                .getTemplate(this.getNpcTemplate().get_npcId());

        // html表示パケット送信
        if (pc.getLawful() < -1000) { // プレイヤーがカオティック
            pc.sendPackets(new S_NPCTalkReturn(talking, objid, 2));
        } else {
            pc.sendPackets(new S_NPCTalkReturn(talking, objid, 1));
        }
    }

    @Override
    public void receiveDamage(final L1Character attacker, int damage) { // 攻击でＨＰを减らすときはここを使用
        if ((this.getCurrentHp() > 0) && !this.isDead()) {
            if ((this.getHiddenStatus() == HIDDEN_STATUS_SINK)
                    || (this.getHiddenStatus() == HIDDEN_STATUS_FLY)) {
                return;
            }
            if (damage >= 0) {
                if (!(attacker instanceof L1EffectInstance)) { // FWはヘイトなし
                    this.setHate(attacker, damage);
                }
            }
            if (damage > 0) {
                this.removeSkillEffect(FOG_OF_SLEEPING);
            }

            this.onNpcAI();

            if (attacker instanceof L1PcInstance) { // 仲间意识をもつモンスターのターゲットに设定
                this.serchLink((L1PcInstance) attacker, this.getNpcTemplate()
                        .get_family());
            }

            // 血痕相克伤害增加 1.5倍
            if (((this.getNpcTemplate().get_npcId() == 97044)
                    || (this.getNpcTemplate().get_npcId() == 97045) || (this
                    .getNpcTemplate().get_npcId() == 97046))
                    && (attacker.hasSkillEffect(EFFECT_BLOODSTAIN_OF_ANTHARAS))) { // 有安塔瑞斯的血痕时对法利昂增伤
                damage *= 1.5;
            }

            if ((attacker instanceof L1PcInstance) && (damage > 0)) {
                final L1PcInstance player = (L1PcInstance) attacker;
                player.setPetTarget(this);
            }

            final int newHp = this.getCurrentHp() - damage;
            if ((newHp <= 0) && !this.isDead()) {
                final int transformId = this.getNpcTemplate().getTransformId();

                // 变身しないモンスター
                if (transformId == -1) {
                    if (this.getPortalNumber() != -1) {
                        if ((this.getNpcTemplate().get_npcId() == 97006)
                                || (this.getNpcTemplate().get_npcId() == 97044)) {
                            // 准备阶段二
                            L1DragonSlayer.getInstance().startDragonSlayer2rd(
                                    this.getPortalNumber());
                        } else if ((this.getNpcTemplate().get_npcId() == 97007)
                                || (this.getNpcTemplate().get_npcId() == 97045)) {
                            // 准备阶段三
                            L1DragonSlayer.getInstance().startDragonSlayer3rd(
                                    this.getPortalNumber());
                        } else if ((this.getNpcTemplate().get_npcId() == 97008)
                                || (this.getNpcTemplate().get_npcId() == 97046)) {
                            this.bloodstain();
                            // 结束屠龙副本
                            L1DragonSlayer.getInstance().endDragonSlayer(
                                    this.getPortalNumber());
                        }
                    }
                    this.setCurrentHpDirect(0);
                    this.setDead(true);
                    this.setStatus(ActionCodes.ACTION_Die);
                    openDoorWhenNpcDied(this);
                    final Death death = new Death(attacker);
                    GeneralThreadPool.getInstance().execute(death);
                    // Death(attacker);
                    if ((this.getPortalNumber() == -1)
                            && ((this.getNpcTemplate().get_npcId() == 97006)
                                    || (this.getNpcTemplate().get_npcId() == 97007)
                                    || (this.getNpcTemplate().get_npcId() == 97044) || (this
                                    .getNpcTemplate().get_npcId() == 97045))) {
                        this.doNextDragonStep(attacker, this.getNpcTemplate()
                                .get_npcId());
                    }
                } else { // 变身するモンスター
                         // distributeExpDropKarma(attacker);
                    this.transform(transformId);
                }
            }
            if (newHp > 0) {
                this.setCurrentHp(newHp);
                this.hide();
            }
        } else if (!this.isDead()) { // 念のため
            this.setDead(true);
            this.setStatus(ActionCodes.ACTION_Die);
            final Death death = new Death(attacker);
            GeneralThreadPool.getInstance().execute(death);
            // Death(attacker);
            if ((this.getPortalNumber() == -1)
                    && ((this.getNpcTemplate().get_npcId() == 97006)
                            || (this.getNpcTemplate().get_npcId() == 97007)
                            || (this.getNpcTemplate().get_npcId() == 97044) || (this
                            .getNpcTemplate().get_npcId() == 97045))) {
                this.doNextDragonStep(attacker, this.getNpcTemplate()
                        .get_npcId());
            }
        }
    }

    @Override
    public void ReceiveManaDamage(final L1Character attacker, final int mpDamage) { // 攻击でＭＰを减らすときはここを使用
        if ((mpDamage > 0) && !this.isDead()) {
            // int Hate = mpDamage / 10 + 10; // 注意！计算适当 ダメージの１０分の１＋ヒットヘイト１０
            // setHate(attacker, Hate);
            this.setHate(attacker, mpDamage);

            this.onNpcAI();

            if (attacker instanceof L1PcInstance) { // 仲间意识をもつモンスターのターゲットに设定
                this.serchLink((L1PcInstance) attacker, this.getNpcTemplate()
                        .get_family());
            }

            int newMp = this.getCurrentMp() - mpDamage;
            if (newMp < 0) {
                newMp = 0;
            }
            this.setCurrentMp(newMp);
        }
    }

    @Override
    public void searchTarget() {
        // 目标搜索
        L1PcInstance lastTarget = null;
        L1PcInstance targetPlayer = null;

        if ((this._target != null) && (this._target instanceof L1PcInstance)) {
            lastTarget = (L1PcInstance) this._target;
            this.tagertClear();
        }

        for (final L1PcInstance pc : L1World.getInstance().getVisiblePlayer(
                this)) {

            if ((pc == lastTarget) || (pc.getCurrentHp() <= 0) || pc.isDead()
                    || pc.isGm() || pc.isMonitor() || pc.isGhost()) {
                continue;
            }

            // 斗技场内は变身／未变身に限らず全てアクティブ
            final int mapId = this.getMapId();
            if ((mapId == 88 // 奇岩竞技场
                    )
                    || (mapId == 98 // 威顿竞技场
                    ) || (mapId == 92 // 古鲁丁竞技场
                    ) || (mapId == 91 // 说话之岛竞技场
                    ) || (mapId == 95 // 银骑士竞技场
                    )) {
                if (!pc.isInvisble() || this.getNpcTemplate().is_agrocoi()) { // 检查隐身
                    targetPlayer = pc;
                    break;
                }
            }

            if (this.getNpcId() == 45600) { // 克特
                if (pc.isCrown() || pc.isDarkelf()
                        || (pc.getTempCharGfx() != pc.getClassId())) { // 未变身的君主、黑暗精灵
                    targetPlayer = pc;
                    break;
                }
            }

            // 如果你满足任一条件、友好と见なされ先制攻击されない。
            // ・モンスターのカルマがマイナス值（バルログ侧モンスター）でPCのカルマレベルが1以上（バルログ友好）
            // ・モンスターのカルマがプラス值（ヤヒ侧モンスター）でPCのカルマレベルが-1以下（ヤヒ友好）
            if (((this.getNpcTemplate().getKarma() < 0) && (pc.getKarmaLevel() >= 1))
                    || ((this.getNpcTemplate().getKarma() > 0) && (pc
                            .getKarmaLevel() <= -1))) {
                continue;
            }
            // 见弃てられた者たちの地 カルマクエストの变身中は、各阵営のモンスターから先制攻击されない
            if (((pc.getTempCharGfx() == 6034) && (this.getNpcTemplate()
                    .getKarma() < 0))
                    || ((pc.getTempCharGfx() == 6035) && (this.getNpcTemplate()
                            .getKarma() > 0))
                    || ((pc.getTempCharGfx() == 6035) && (this.getNpcTemplate()
                            .get_npcId() == 46070))
                    || ((pc.getTempCharGfx() == 6035) && (this.getNpcTemplate()
                            .get_npcId() == 46072))) {
                continue;
            }

            if (!this.getNpcTemplate().is_agro()
                    && !this.getNpcTemplate().is_agrososc()
                    && (this.getNpcTemplate().is_agrogfxid1() < 0)
                    && (this.getNpcTemplate().is_agrogfxid2() < 0)) { // 完全なノンアクティブモンスター
                if (pc.getLawful() < -1000) { // プレイヤーがカオティック
                    targetPlayer = pc;
                    break;
                }
                continue;
            }

            if (!pc.isInvisble() || this.getNpcTemplate().is_agrocoi()) { // インビジチェック
                if (pc.hasSkillEffect(67)) { // 变身してる
                    if (this.getNpcTemplate().is_agrososc()) { // 变身に对してアクティブ
                        targetPlayer = pc;
                        break;
                    }
                } else if (this.getNpcTemplate().is_agro()) { // アクティブモンスター
                    targetPlayer = pc;
                    break;
                }

                // 特定のクラスorグラフィックＩＤにアクティブ
                if ((this.getNpcTemplate().is_agrogfxid1() >= 0)
                        && (this.getNpcTemplate().is_agrogfxid1() <= 4)) { // クラス指定
                    if ((_classGfxId[this.getNpcTemplate().is_agrogfxid1()][0] == pc
                            .getTempCharGfx())
                            || (_classGfxId[this.getNpcTemplate()
                                    .is_agrogfxid1()][1] == pc.getTempCharGfx())) {
                        targetPlayer = pc;
                        break;
                    }
                } else if (pc.getTempCharGfx() == this.getNpcTemplate()
                        .is_agrogfxid1()) { // グラフィックＩＤ指定
                    targetPlayer = pc;
                    break;
                }

                if ((this.getNpcTemplate().is_agrogfxid2() >= 0)
                        && (this.getNpcTemplate().is_agrogfxid2() <= 4)) { // クラス指定
                    if ((_classGfxId[this.getNpcTemplate().is_agrogfxid2()][0] == pc
                            .getTempCharGfx())
                            || (_classGfxId[this.getNpcTemplate()
                                    .is_agrogfxid2()][1] == pc.getTempCharGfx())) {
                        targetPlayer = pc;
                        break;
                    }
                } else if (pc.getTempCharGfx() == this.getNpcTemplate()
                        .is_agrogfxid2()) { // グラフィックＩＤ指定
                    targetPlayer = pc;
                    break;
                }
            }
        }
        if (targetPlayer != null) {
            this._hateList.add(targetPlayer, 0);
            this._target = targetPlayer;
        }
    }

    public void set_storeDroped(final boolean flag) {
        this._storeDroped = flag;
    }

    /**
     * 距离が5以上离れているpcを距离3～4の位置に引き寄せる。
     * 
     * @param pc
     */
    /*
     * private void recall(L1PcInstance pc) { if (getMapId() != pc.getMapId()) {
     * return; } if (getLocation().getTileLineDistance(pc.getLocation()) > 4) {
     * for (int count = 0; count < 10; count++) { L1Location newLoc =
     * getLocation().randomLocation(3, 4, false); if (glanceCheck(newLoc.getX(),
     * newLoc.getY())) { L1Teleport.teleport(pc, newLoc.getX(), newLoc.getY(),
     * getMapId(), 5, true); break; } } } }
     */

    @Override
    public void setCurrentHp(final int i) {
        int currentHp = i;
        if (currentHp >= this.getMaxHp()) {
            currentHp = this.getMaxHp();
        }
        this.setCurrentHpDirect(currentHp);

        if (this.getMaxHp() > this.getCurrentHp()) {
            this.startHpRegeneration();
        }
    }

    @Override
    public void setCurrentMp(final int i) {
        int currentMp = i;
        if (currentMp >= this.getMaxMp()) {
            currentMp = this.getMaxMp();
        }
        this.setCurrentMpDirect(currentMp);

        if (this.getMaxMp() > this.getCurrentMp()) {
            this.startMpRegeneration();
        }
    }

    // 设置链接
    @Override
    public void setLink(final L1Character cha) {
        if ((cha != null) && this._hateList.isEmpty()) { // ターゲットがいない场合のみ追加
            this._hateList.add(cha, 0);
            this.checkTarget();
        }
    }

    protected void setNextDragonStepRunning(final boolean nextDragonStepRunning) {
        this._nextDragonStepRunning = nextDragonStepRunning;
    }

    public void setUbId(final int i) {
        this._ubId = i;
    }

    public void setUbSealCount(final int i) {
        this._ubSealCount = i;
    }

    @Override
    protected void transform(final int transformId) {
        super.transform(transformId);

        // DROPの再设定
        this.getInventory().clearItems();
        DropTable.getInstance().setDrop(this, this.getInventory());
        this.getInventory().shuffle();
    }
}
