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

import static com.lineage.server.model.skill.L1SkillId.FOG_OF_SLEEPING;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import com.lineage.server.ActionCodes;
import com.lineage.server.GeneralThreadPool;
import com.lineage.server.IdFactory;
import com.lineage.server.datatables.DropTable;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.datatables.PetTypeTable;
import com.lineage.server.model.L1Attack;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.L1World;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_HPMeter;
import com.lineage.server.serverpackets.S_NpcChatPacket;
import com.lineage.server.serverpackets.S_PetCtrlMenu;
import com.lineage.server.serverpackets.S_PetMenuPacket;
import com.lineage.server.serverpackets.S_PetPack;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.serverpackets.S_SummonPack;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.templates.L1PetType;
import com.lineage.server.utils.Random;

/**
 * 召唤物控制项
 */
public class L1SummonInstance extends L1NpcInstance {
    // １时间计测用
    class SummonTimer implements Runnable {
        @Override
        public void run() {
            if (L1SummonInstance.this._destroyed) { // 既に破弃されていないかチェック
                return;
            }
            if (L1SummonInstance.this._tamed) {
                // テイミングモンスター、クリエイトゾンビの解放
                L1SummonInstance.this.liberate();
            } else {
                // サモンの解散
                L1SummonInstance.this.Death(null);
            }
        }
    }

    private static final long serialVersionUID = 1L;

    private ScheduledFuture<?> _summonFuture;

    private static final long SUMMON_TIME = 3600000L;

    private int _currentPetStatus;

    final boolean _tamed;

    private boolean _isReturnToNature = false;

    private int _dir;

    // 对于召唤怪物
    public L1SummonInstance(final L1Npc template, final L1Character master) {
        super(template);
        this.setId(IdFactory.getInstance().nextId());

        this._summonFuture = GeneralThreadPool.getInstance().schedule(
                new SummonTimer(), SUMMON_TIME);

        this.setMaster(master);
        this.setX(master.getX() + Random.nextInt(5) - 2);
        this.setY(master.getY() + Random.nextInt(5) - 2);
        this.setMap(master.getMapId());
        this.setHeading(5);
        this.setLightSize(template.getLightSize());

        this._currentPetStatus = 3;
        this._tamed = false;

        L1World.getInstance().storeObject(this);
        L1World.getInstance().addVisibleObject(this);
        for (final L1PcInstance pc : L1World.getInstance().getRecognizePlayer(
                this)) {
            this.onPerceive(pc);
        }
        master.addPet(this);
    }

    // 造尸术处理
    public L1SummonInstance(final L1NpcInstance target,
            final L1Character master, final boolean isCreateZombie) {
        super(null);
        this.setId(IdFactory.getInstance().nextId());

        if (isCreateZombie) { // 造尸
            int npcId = 45065;
            final L1PcInstance pc = (L1PcInstance) master;
            final int level = pc.getLevel();
            if (pc.isWizard()) {
                if ((level >= 24) && (level <= 31)) {
                    npcId = 81183; // 人形僵尸
                } else if ((level >= 32) && (level <= 39)) {
                    npcId = 81184;
                } else if ((level >= 40) && (level <= 43)) {
                    npcId = 81185;
                } else if ((level >= 44) && (level <= 47)) {
                    npcId = 81186;
                } else if ((level >= 48) && (level <= 51)) {
                    npcId = 81187;
                } else if (level >= 52) {
                    npcId = 81188;
                }
            } else if (pc.isElf()) {
                if (level >= 48) {
                    npcId = 81183;
                }
            }
            final L1Npc template = NpcTable.getInstance().getTemplate(npcId)
                    .clone();
            this.setting_template(template);
        } else { // テイミングモンスター
            this.setting_template(target.getNpcTemplate());
            this.setCurrentHpDirect(target.getCurrentHp());
            this.setCurrentMpDirect(target.getCurrentMp());
        }

        this._summonFuture = GeneralThreadPool.getInstance().schedule(
                new SummonTimer(), SUMMON_TIME);

        this.setMaster(master);
        this.setX(target.getX());
        this.setY(target.getY());
        this.setMap(target.getMapId());
        this.setHeading(target.getHeading());
        this.setLightSize(target.getLightSize());
        this.setPetcost(6);

        if ((target instanceof L1MonsterInstance)
                && !((L1MonsterInstance) target).is_storeDroped()) {
            DropTable.getInstance().setDrop(target, target.getInventory());
        }
        this.setInventory(target.getInventory());
        target.setInventory(null);

        this._currentPetStatus = 3;
        this._tamed = true;

        // ペットが攻击中だった场合止めさせる
        for (final L1NpcInstance each : master.getPetList().values()) {
            each.targetRemove(target);
        }

        target.deleteMe();
        L1World.getInstance().storeObject(this);
        L1World.getInstance().addVisibleObject(this);
        for (final L1PcInstance pc : L1World.getInstance().getRecognizePlayer(
                this)) {
            this.onPerceive(pc);
        }
        master.addPet(this);
    }

    private int ActionType(final String action) {
        int status = 0;
        if (action.equalsIgnoreCase("aggressive")) { // 攻击态势
            status = 1;
        } else if (action.equalsIgnoreCase("defensive")) { // 防御态势
            status = 2;
        } else if (action.equalsIgnoreCase("stay")) { // 休憩
            status = 3;
        } else if (action.equalsIgnoreCase("extend")) { // 配备
            status = 4;
        } else if (action.equalsIgnoreCase("alert")) { // 警戒
            status = 5;
        } else if (action.equalsIgnoreCase("dismiss")) { // 解散
            status = 6;
        }
        return status;
    }

    public synchronized void Death(final L1Character lastAttacker) {
        if (!this.isDead()) {
            this.setDead(true);
            this.setCurrentHp(0);
            this.setStatus(ActionCodes.ACTION_Die);

            this.getMap().setPassable(this.getLocation(), true);

            // 死亡时物品给予主人或掉落地面
            L1Inventory targetInventory = this._master.getInventory();
            final List<L1ItemInstance> items = this._inventory.getItems();
            for (final L1ItemInstance item : items) {
                if (this._master.getInventory().checkAddItem( // 容量重量确认及びメッセージ送信
                        item, item.getCount()) == L1Inventory.OK) {
                    this._inventory.tradeItem(item, item.getCount(),
                            targetInventory);
                    // \f1%0が%1をくれました。
                    ((L1PcInstance) this._master)
                            .sendPackets(new S_ServerMessage(143, this
                                    .getName(), item.getLogName()));
                } else { // 持てないので足元に落とす
                    targetInventory = L1World.getInstance().getInventory(
                            this.getX(), this.getY(), this.getMapId());
                    this._inventory.tradeItem(item, item.getCount(),
                            targetInventory);
                }
            }

            if (this._tamed) {
                this.broadcastPacket(new S_DoActionGFX(this.getId(),
                        ActionCodes.ACTION_Die));
                this.startDeleteTimer();
            } else {
                this.deleteMe();
            }
        }
    }

    // オブジェクト消去处理
    @Override
    public synchronized void deleteMe() {
        if (this._destroyed) {
            return;
        }
        if (!this._tamed && !this._isReturnToNature) {
            this.broadcastPacket(new S_SkillSound(this.getId(), 169));
        }
        // if (_master.getPetList().isEmpty()) {
        final L1PcInstance pc = (L1PcInstance) this._master;
        if (pc != null) {
            pc.sendPackets(new S_PetCtrlMenu(this._master, this, false));// 关闭宠物控制图形介面
        }
        // }
        this._master.getPetList().remove(this.getId());
        super.deleteMe();

        if (this._summonFuture != null) {
            this._summonFuture.cancel(false);
            this._summonFuture = null;
        }
    }

    public int get_currentPetStatus() {
        return this._currentPetStatus;
    }

    public boolean isExsistMaster() {
        boolean isExsistMaster = true;
        if (this.getMaster() != null) {
            final String masterName = this.getMaster().getName();
            if (L1World.getInstance().getPlayer(masterName) == null) {
                isExsistMaster = false;
            }
        }
        return isExsistMaster;
    }

    // 迷魅的怪物解散处理
    public void liberate() {
        final L1MonsterInstance monster = new L1MonsterInstance(
                this.getNpcTemplate());
        monster.setId(IdFactory.getInstance().nextId());

        monster.setX(this.getX());
        monster.setY(this.getY());
        monster.setMap(this.getMapId());
        monster.setHeading(this.getHeading());
        monster.set_storeDroped(true);
        monster.setInventory(this.getInventory());
        this.getInventory().clearItems();
        monster.setCurrentHpDirect(this.getCurrentHp());
        monster.setCurrentMpDirect(this.getCurrentMp());
        monster.setExp(0);

        if (!this.isDead()) { // 原迷魅怪解散时死亡
            this.setDead(true);
            this.setCurrentHp(0);
            this.getMap().setPassable(this.getLocation(), true);
        }
        this.deleteMe();
        L1World.getInstance().storeObject(monster);
        L1World.getInstance().addVisibleObject(monster);
    }

    // 如果没有目标处理
    @Override
    public boolean noTarget() {
        switch (this._currentPetStatus) {
            case 3: // 休息
                return true;
            case 4: // 散开
                if ((this._master != null)
                        && (this._master.getMapId() == this.getMapId())
                        && (this.getLocation().getTileLineDistance(
                                this._master.getLocation()) < 5)) {
                    this._dir = this.targetReverseDirection(
                            this._master.getX(), this._master.getY());
                    this._dir = checkObject(this.getX(), this.getY(),
                            this.getMapId(), this._dir);
                    this.setDirectionMove(this._dir);
                    this.setSleepTime(this.calcSleepTime(this.getPassispeed(),
                            MOVE_SPEED));
                } else {
                    this._currentPetStatus = 3;
                    return true;
                }
                return false;
            case 5:
                if ((Math.abs(this.getHomeX() - this.getX()) > 1)
                        || (Math.abs(this.getHomeY() - this.getY()) > 1)) {
                    this._dir = this.moveDirection(this.getHomeX(),
                            this.getHomeY());
                    if (this._dir == -1) {
                        this.setHomeX(this.getX());
                        this.setHomeY(this.getY());
                    } else {
                        this.setDirectionMove(this._dir);
                        this.setSleepTime(this.calcSleepTime(
                                this.getPassispeed(), MOVE_SPEED));
                    }
                }
                return false;
            default:
                if ((this._master != null)
                        && (this._master.getMapId() == this.getMapId())) {
                    if (this.getLocation().getTileLineDistance(
                            this._master.getLocation()) > 2) {
                        this._dir = this.moveDirection(this._master.getX(),
                                this._master.getY());
                        this.setDirectionMove(this._dir);
                        this.setSleepTime(this.calcSleepTime(
                                this.getPassispeed(), MOVE_SPEED));
                    }
                } else {
                    this._currentPetStatus = 3;
                    return true;
                }
                return false;
        }
    }

    @Override
    public void onAction(final L1PcInstance attacker) {
        this.onAction(attacker, 0);
    }

    @Override
    public void onAction(final L1PcInstance attacker, final int skillId) {
        // XXX:NullPointerException回避。onActionの引数の型はL1Characterのほうが良い？
        if (attacker == null) {
            return;
        }
        final L1Character cha = this.getMaster();
        if (cha == null) {
            return;
        }
        final L1PcInstance master = (L1PcInstance) cha;
        if (master.isTeleport()) {
            // テレポート处理中
            return;
        }
        if (((this.getZoneType() == 1) || (attacker.getZoneType() == 1))
                && this.isExsistMaster()) {
            // 攻击される侧がセーフティーゾーン
            // 攻击モーション送信
            final L1Attack attack_mortion = new L1Attack(attacker, this,
                    skillId);
            attack_mortion.action();
            return;
        }

        if (attacker.checkNonPvP(attacker, this)) {
            return;
        }

        final L1Attack attack = new L1Attack(attacker, this, skillId);
        if (attack.calcHit()) {
            attack.calcDamage();
        }
        attack.action();
        attack.commit();
    }

    @Override
    public void onFinalAction(final L1PcInstance player, final String action) {
        final int status = this.ActionType(action);
        if (status == 0) {
            return;
        }
        if (status == 6) {
            final L1PcInstance petMaster = (L1PcInstance) this._master;
            if (this._tamed) {
                // テイミングモンスター、クリエイトゾンビの解放
                this.liberate();
            } else {
                // サモンの解散
                this.Death(null);
            }
            // 更新宠物控制介面
            final Object[] petList = petMaster.getPetList().values().toArray();
            for (final Object petObject : petList) {
                if (petObject instanceof L1SummonInstance) {
                    final L1SummonInstance summon = (L1SummonInstance) petObject;
                    petMaster.sendPackets(new S_SummonPack(summon, petMaster));
                    return;
                } else if (petObject instanceof L1PetInstance) {
                    final L1PetInstance pet = (L1PetInstance) petObject;
                    petMaster.sendPackets(new S_PetPack(pet, petMaster));
                    return;
                }
            }
        } else {
            // 同じ主人のペットの状态をすべて更新
            final Object[] petList = this._master.getPetList().values()
                    .toArray();
            for (final Object petObject : petList) {
                if (petObject instanceof L1SummonInstance) {
                    // サモンモンスター
                    final L1SummonInstance summon = (L1SummonInstance) petObject;
                    summon.set_currentPetStatus(status);
                } else if (petObject instanceof L1PetInstance) { // ペット
                    final L1PetInstance pet = (L1PetInstance) petObject;
                    if ((player != null)
                            && (player.getLevel() >= pet.getLevel())
                            && (pet.get_food() > 0)) {
                        pet.setCurrentPetStatus(status);
                    } else {
                        if (!pet.isDead()) {
                            final L1PetType type = PetTypeTable.getInstance()
                                    .get(pet.getNpcTemplate().get_npcId());
                            final int id = type.getDefyMessageId();
                            if (id != 0) {
                                pet.broadcastPacket(new S_NpcChatPacket(pet,
                                        "$" + id, 0));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onGetItem(final L1ItemInstance item) {
        if (this.getNpcTemplate().get_digestitem() > 0) {
            this.setDigestItem(item);
        }
        Arrays.sort(healPotions);
        Arrays.sort(haestPotions);
        if (Arrays.binarySearch(healPotions, item.getItem().getItemId()) >= 0) {
            if (this.getCurrentHp() != this.getMaxHp()) {
                this.useItem(USEITEM_HEAL, 100);
            }
        } else if (Arrays
                .binarySearch(haestPotions, item.getItem().getItemId()) >= 0) {
            this.useItem(USEITEM_HASTE, 100);
        }
    }

    @Override
    public void onItemUse() {
        if (!this.isActived()) {
            // １００％の确率でヘイストポーション使用
            this.useItem(USEITEM_HASTE, 100);
        }
        if (this.getCurrentHp() * 100 / this.getMaxHp() < 40) {
            // ＨＰが４０％きったら
            // １００％の确率で回复ポーション使用
            this.useItem(USEITEM_HEAL, 100);
        }
    }

    @Override
    public void onPerceive(final L1PcInstance perceivedFrom) {
        perceivedFrom.addKnownObject(this);
        perceivedFrom.sendPackets(new S_SummonPack(this, perceivedFrom));
    }

    @Override
    public void onTalkAction(final L1PcInstance player) {
        if (this.isDead()) {
            return;
        }
        if (this._master.equals(player)) {
            player.sendPackets(new S_PetMenuPacket(this, 0));
        }
    }

    @Override
    public void receiveDamage(final L1Character attacker, int damage) { // 攻击でＨＰを减らすときはここを使用
        if (this.getCurrentHp() > 0) {
            if (damage > 0) {
                this.setHate(attacker, 0); // サモンはヘイト无し
                this.removeSkillEffect(FOG_OF_SLEEPING);
                if (!this.isExsistMaster()) {
                    this._currentPetStatus = 1;
                    this.setTarget(attacker);
                }
            }

            if ((attacker instanceof L1PcInstance) && (damage > 0)) {
                final L1PcInstance player = (L1PcInstance) attacker;
                player.setPetTarget(this);
            }

            if (attacker instanceof L1PetInstance) {
                final L1PetInstance pet = (L1PetInstance) attacker;
                // 目标在安区、攻击者在安区、NOPVP
                if ((this.getZoneType() == 1) || (pet.getZoneType() == 1)) {
                    damage = 0;
                }
            } else if (attacker instanceof L1SummonInstance) {
                final L1SummonInstance summon = (L1SummonInstance) attacker;
                // 目标在安区、攻击者在安区、NOPVP
                if ((this.getZoneType() == 1) || (summon.getZoneType() == 1)) {
                    damage = 0;
                }
            }

            final int newHp = this.getCurrentHp() - damage;
            if (newHp <= 0) {
                this.Death(attacker);
            } else {
                this.setCurrentHp(newHp);
            }
        } else if (!this.isDead()) // 念のため
        {
            System.out.println("警告：サモンのＨＰ减少处理が正しく行われていない个所があります。※もしくは最初からＨＰ０");
            this.Death(attacker);
        }
    }

    public synchronized void returnToNature() {
        this._isReturnToNature = true;
        if (!this._tamed) {
            this.getMap().setPassable(this.getLocation(), true);
            // アイテム解放处理
            L1Inventory targetInventory = this._master.getInventory();
            final List<L1ItemInstance> items = this._inventory.getItems();
            for (final L1ItemInstance item : items) {
                if (this._master.getInventory().checkAddItem( // 容量重量确认及びメッセージ送信
                        item, item.getCount()) == L1Inventory.OK) {
                    this._inventory.tradeItem(item, item.getCount(),
                            targetInventory);
                    // \f1%0が%1をくれました。
                    ((L1PcInstance) this._master)
                            .sendPackets(new S_ServerMessage(143, this
                                    .getName(), item.getLogName()));
                } else { // 持てないので足元に落とす
                    targetInventory = L1World.getInstance().getInventory(
                            this.getX(), this.getY(), this.getMapId());
                    this._inventory.tradeItem(item, item.getCount(),
                            targetInventory);
                }
            }
            this.deleteMe();
        } else {
            this.liberate();
        }
    }

    public void set_currentPetStatus(final int i) {
        this._currentPetStatus = i;
        if (this._currentPetStatus == 5) {
            this.setHomeX(this.getX());
            this.setHomeY(this.getY());
        }

        if (this._currentPetStatus == 3) {
            this.allTargetClear();
        } else {
            if (!this.isAiRunning()) {
                this.startAI();
            }
        }
    }

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

        if (this._master instanceof L1PcInstance) {
            final int HpRatio = 100 * currentHp / this.getMaxHp();
            final L1PcInstance Master = (L1PcInstance) this._master;
            Master.sendPackets(new S_HPMeter(this.getId(), HpRatio));
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

    public void setMasterTarget(final L1Character target) {
        if ((target != null)
                && ((this._currentPetStatus == 1) || (this._currentPetStatus == 5))) {
            this.setHate(target, 0);
            if (!this.isAiRunning()) {
                this.startAI();
            }
        }
    }

    public void setTarget(final L1Character target) {
        if ((target != null)
                && ((this._currentPetStatus == 1)
                        || (this._currentPetStatus == 2) || (this._currentPetStatus == 5))) {
            this.setHate(target, 0);
            if (!this.isAiRunning()) {
                this.startAI();
            }
        }
    }

}
