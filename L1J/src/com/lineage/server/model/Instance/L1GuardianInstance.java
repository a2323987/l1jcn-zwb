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

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.ActionCodes;
import com.lineage.server.GeneralThreadPool;
import com.lineage.server.datatables.DropTable;
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.datatables.NPCTalkDataTable;
import com.lineage.server.model.L1Attack;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1NpcTalkData;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.serverpackets.S_ChangeHeading;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_NPCTalkReturn;
import com.lineage.server.serverpackets.S_NpcChatPacket;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Item;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.utils.CalcExp;
import com.lineage.server.utils.Random;

/**
 * 精灵森林守护神控制项
 */
public class L1GuardianInstance extends L1NpcInstance {

    class Death implements Runnable {
        L1Character lastAttacker = L1GuardianInstance.this._lastattacker;

        @Override
        public void run() {
            L1GuardianInstance.this.setDeathProcessing(true);
            L1GuardianInstance.this.setCurrentHpDirect(0);
            L1GuardianInstance.this.setDead(true);
            L1GuardianInstance.this.setStatus(ActionCodes.ACTION_Die);
            final int targetobjid = L1GuardianInstance.this.getId();
            L1GuardianInstance.this.getMap().setPassable(
                    L1GuardianInstance.this.getLocation(), true);
            L1GuardianInstance.this.broadcastPacket(new S_DoActionGFX(
                    targetobjid, ActionCodes.ACTION_Die));

            L1PcInstance player = null;
            if (this.lastAttacker instanceof L1PcInstance) {
                player = (L1PcInstance) this.lastAttacker;
            } else if (this.lastAttacker instanceof L1PetInstance) {
                player = (L1PcInstance) ((L1PetInstance) this.lastAttacker)
                        .getMaster();
            } else if (this.lastAttacker instanceof L1SummonInstance) {
                player = (L1PcInstance) ((L1SummonInstance) this.lastAttacker)
                        .getMaster();
            }
            if (player != null) {
                final List<L1Character> targetList = L1GuardianInstance.this._hateList
                        .toTargetArrayList();
                final List<Integer> hateList = L1GuardianInstance.this._hateList
                        .toHateArrayList();
                final long exp = L1GuardianInstance.this.getExp();
                CalcExp.calcExp(player, targetobjid, targetList, hateList, exp);

                final List<L1Character> dropTargetList = L1GuardianInstance.this._dropHateList
                        .toTargetArrayList();
                final List<Integer> dropHateList = L1GuardianInstance.this._dropHateList
                        .toHateArrayList();
                try {
                    DropTable.getInstance().dropShare(
                            L1GuardianInstance.this._npc, dropTargetList,
                            dropHateList);
                } catch (final Exception e) {
                    _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                }
                // カルマは止めを刺したプレイヤーに設定。ペットorサモンで倒した場合も入る。
                player.addKarma((int) (L1GuardianInstance.this.getKarma() * Config.RATE_KARMA));
            }
            L1GuardianInstance.this.setDeathProcessing(false);

            L1GuardianInstance.this.setKarma(0);
            L1GuardianInstance.this.setExp(0);
            L1GuardianInstance.this.allTargetClear();

            L1GuardianInstance.this.startDeleteTimer();
        }
    }

    private class GDropItemTask implements Runnable {
        int npcId = L1GuardianInstance.this.getNpcTemplate().get_npcId();

        GDropItemTask() {
        }

        @Override
        public void run() {
            try {
                if ((L1GuardianInstance.this.GDROPITEM_TIME > 0)
                        && !L1GuardianInstance.this.isDropitems()) {
                    if (this.npcId == 70848) { // 安特
                        if (!L1GuardianInstance.this._inventory
                                .checkItem(40505)
                                && !L1GuardianInstance.this._inventory
                                        .checkItem(40506)
                                && !L1GuardianInstance.this._inventory
                                        .checkItem(40507)) {
                            L1GuardianInstance.this._inventory.storeItem(40506,
                                    1);
                            L1GuardianInstance.this._inventory.storeItem(40507,
                                    66);
                            L1GuardianInstance.this._inventory.storeItem(40505,
                                    8);
                        }
                    }
                    if (this.npcId == 70850) { // 潘
                        if (!L1GuardianInstance.this._inventory
                                .checkItem(40519)) {
                            L1GuardianInstance.this._inventory.storeItem(40519,
                                    30);
                        }
                    }
                    L1GuardianInstance.this.setDropItems(true);
                    L1GuardianInstance.this.giveDropItems(true);
                    L1GuardianInstance.this
                            .doGDropItem(L1GuardianInstance.this.GDROPITEM_TIME);
                } else {
                    L1GuardianInstance.this.giveDropItems(false);
                }
            } catch (final Exception e) {
                _log.log(Level.SEVERE, "资料载入错误", e);
            }
        }
    }

    public class RestMonitor extends TimerTask {
        @Override
        public void run() {
            L1GuardianInstance.this.setRest(false);
        }
    }

    private static final long serialVersionUID = 1L;

    static Logger _log = Logger.getLogger(L1GuardianInstance.class.getName());

    final L1GuardianInstance _npc = this;

    /** NPC道具重置时间 */
    final int GDROPITEM_TIME = Config.GDROPITEM_TIME;

    L1Character _lastattacker;

    private static final long REST_MILLISEC = 10000;

    private static final Timer _restTimer = new Timer(true);

    private RestMonitor _monitor;

    /**
     * @param template
     */
    public L1GuardianInstance(final L1Npc template) {
        super(template);
        if (!this.isDropitems()) {
            this.doGDropItem(0);
        }
    }

    public void doFinalAction(final L1PcInstance player) {
    }

    public void doGDropItem(final int timer) {
        final GDropItemTask task = new GDropItemTask();
        GeneralThreadPool.getInstance().schedule(task, timer * 60000);
    }

    @Override
    public void onAction(final L1PcInstance pc) {
        this.onAction(pc, 0);
    }

    @Override
    public void onAction(final L1PcInstance pc, final int skillId) {
        if ((pc.getType() == 2) && (pc.getCurrentWeapon() == 0) && pc.isElf()) {
            final L1Attack attack = new L1Attack(pc, this, skillId);

            if (attack.calcHit()) {
                try {
                    int chance = 0;
                    final int npcId = this.getNpcTemplate().get_npcId();
                    final String npcName = this.getNpcTemplate().get_name();
                    String itemName = "";
                    int itemCount = 0;
                    final L1Item item40499 = ItemTable.getInstance()
                            .getTemplate(40499);
                    final L1Item item40503 = ItemTable.getInstance()
                            .getTemplate(40503);
                    final L1Item item40505 = ItemTable.getInstance()
                            .getTemplate(40505);
                    final L1Item item40506 = ItemTable.getInstance()
                            .getTemplate(40506);
                    final L1Item item40507 = ItemTable.getInstance()
                            .getTemplate(40507);
                    final L1Item item40519 = ItemTable.getInstance()
                            .getTemplate(40519);
                    if (npcId == 70848) { // 安特
                        if (this._inventory.checkItem(40499)
                                && !this._inventory.checkItem(40505)) { // 蘑菇汁 换
                                                                        // 安特之树皮
                            itemName = item40505.getName();
                            itemCount = this._inventory.countItems(40499);
                            if (itemCount > 1) {
                                itemName += " (" + itemCount + ")";
                            }
                            this._inventory.consumeItem(40499, itemCount);
                            pc.getInventory().storeItem(40505, itemCount);
                            pc.sendPackets(new S_ServerMessage(143, npcName,
                                    itemName));
                            if (!this.isDropitems()) {
                                this.doGDropItem(3);
                            }
                        }
                        if (this._inventory.checkItem(40505)) { // 安特之树皮
                            chance = Random.nextInt(100) + 1;
                            if ((chance <= 60) && (chance >= 50)) {
                                itemName = item40505.getName();
                                this._inventory.consumeItem(40505, 1);
                                pc.getInventory().storeItem(40505, 1);
                                pc.sendPackets(new S_ServerMessage(143,
                                        npcName, itemName));
                            } else {
                                itemName = item40499.getName();
                                pc.sendPackets(new S_ServerMessage(337,
                                        itemName));
                            }
                        } else if (this._inventory.checkItem(40507)
                                && !this._inventory.checkItem(40505)) { // 安特之树枝
                            chance = Random.nextInt(100) + 1;
                            if ((chance <= 40) && (chance >= 25)) {
                                itemName = item40507.getName();
                                itemName += " (6)";
                                this._inventory.consumeItem(40507, 6);
                                pc.getInventory().storeItem(40507, 6);
                                pc.sendPackets(new S_ServerMessage(143,
                                        npcName, itemName));
                            } else {
                                itemName = item40499.getName();
                                pc.sendPackets(new S_ServerMessage(337,
                                        itemName));
                            }
                        } else if (this._inventory.checkItem(40506)
                                && !this._inventory.checkItem(40507)) { // 安特的水果
                            chance = Random.nextInt(100) + 1;
                            if ((chance <= 90) && (chance >= 85)) {
                                itemName = item40506.getName();
                                this._inventory.consumeItem(40506, 1);
                                pc.getInventory().storeItem(40506, 1);
                                pc.sendPackets(new S_ServerMessage(143,
                                        npcName, itemName));
                            } else {
                                itemName = item40499.getName();
                                pc.sendPackets(new S_ServerMessage(337,
                                        itemName));
                            }
                        } else {
                            if (!this.forDropitems()) {
                                this.setDropItems(false);
                                this.doGDropItem(this.GDROPITEM_TIME);
                            }
                            chance = Random.nextInt(100) + 1;
                            if ((chance <= 80) && (chance >= 40)) {
                                this.broadcastPacket(new S_NpcChatPacket(
                                        this._npc, "$822", 0));
                            } else {
                                itemName = item40499.getName();
                                pc.sendPackets(new S_ServerMessage(337,
                                        itemName));
                            }
                        }
                    }
                    if (npcId == 70850) { // 潘
                        if (this._inventory.checkItem(40519)) { // 潘的鬃毛
                            chance = Random.nextInt(100) + 1;
                            if (chance <= 25) {
                                itemName = item40519.getName();
                                itemName += " (5)";
                                this._inventory.consumeItem(40519, 5);
                                pc.getInventory().storeItem(40519, 5);
                                pc.sendPackets(new S_ServerMessage(143,
                                        npcName, itemName));
                            }
                        } else {
                            if (!this.forDropitems()) {
                                this.setDropItems(false);
                                this.doGDropItem(this.GDROPITEM_TIME);
                            }
                            chance = Random.nextInt(100) + 1;
                            if ((chance <= 80) && (chance >= 40)) {
                                this.broadcastPacket(new S_NpcChatPacket(
                                        this._npc, "$824", 0));
                            }
                        }
                    }
                    if (npcId == 70846) { // 芮克妮
                        if (this._inventory.checkItem(40507)) { // 安特之树枝 换 芮克妮的网
                            itemName = item40503.getName();
                            itemCount = this._inventory.countItems(40507);
                            if (itemCount > 1) {
                                itemName += " (" + itemCount + ")";
                            }
                            this._inventory.consumeItem(40507, itemCount);
                            pc.getInventory().storeItem(40503, itemCount);
                            pc.sendPackets(new S_ServerMessage(143, npcName,
                                    itemName));
                        } else {
                            itemName = item40507.getName();
                            pc.sendPackets(new S_ServerMessage(337, itemName)); // \\f1%0不足%s。
                        }
                    }
                } catch (final Exception e) {
                    _log.log(Level.SEVERE, "发生错误", e);
                }
                attack.calcDamage();
                attack.calcStaffOfMana();
                attack.addPcPoisonAttack(pc, this);
                attack.addChaserAttack();
            }
            attack.action();
            attack.commit();
        } else if ((this.getCurrentHp() > 0) && !this.isDead()) {
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

    @Override
    public void onFinalAction(final L1PcInstance player, final String action) {
    }

    @Override
    public void onNpcAI() {
        if (this.isAiRunning()) {
            return;
        }
        this.setActived(false);
        this.startAI();
    }

    @Override
    public void onTalkAction(final L1PcInstance player) {
        final int objid = this.getId();
        final L1NpcTalkData talking = NPCTalkDataTable.getInstance()
                .getTemplate(this.getNpcTemplate().get_npcId());
        final L1Object object = L1World.getInstance().findObject(this.getId());
        final L1NpcInstance target = (L1NpcInstance) object;

        if (talking != null) {
            final int pcx = player.getX(); // PCのX座標
            final int pcy = player.getY(); // PCのY座標
            final int npcx = target.getX(); // NPCのX座標
            final int npcy = target.getY(); // NPCのY座標

            if ((pcx == npcx) && (pcy < npcy)) {
                this.setHeading(0);
            } else if ((pcx > npcx) && (pcy < npcy)) {
                this.setHeading(1);
            } else if ((pcx > npcx) && (pcy == npcy)) {
                this.setHeading(2);
            } else if ((pcx > npcx) && (pcy > npcy)) {
                this.setHeading(3);
            } else if ((pcx == npcx) && (pcy > npcy)) {
                this.setHeading(4);
            } else if ((pcx < npcx) && (pcy > npcy)) {
                this.setHeading(5);
            } else if ((pcx < npcx) && (pcy == npcy)) {
                this.setHeading(6);
            } else if ((pcx < npcx) && (pcy < npcy)) {
                this.setHeading(7);
            }
            this.broadcastPacket(new S_ChangeHeading(this));

            // html表示パケット送信
            if (player.getLawful() < -1000) { // プレイヤーがカオティック
                player.sendPackets(new S_NPCTalkReturn(talking, objid, 2));
            } else {
                player.sendPackets(new S_NPCTalkReturn(talking, objid, 1));
            }

            // 動かないようにする
            synchronized (this) {
                if (this._monitor != null) {
                    this._monitor.cancel();
                }
                this.setRest(true);
                this._monitor = new RestMonitor();
                _restTimer.schedule(this._monitor, REST_MILLISEC);
            }
        }
    }

    @Override
    public void receiveDamage(final L1Character attacker, final int damage) { // 攻撃でＨＰを減らすときはここを使用
        if ((attacker instanceof L1PcInstance) && (damage > 0)) {
            final L1PcInstance pc = (L1PcInstance) attacker;
            if ((pc.getType() == 2) && // 素手ならダメージなし
                    (pc.getCurrentWeapon() == 0)) {
            } else {
                if ((this.getCurrentHp() > 0) && !this.isDead()) {
                    if (damage >= 0) {
                        this.setHate(attacker, damage);
                    }
                    if (damage > 0) {
                        this.removeSkillEffect(FOG_OF_SLEEPING);
                    }
                    this.onNpcAI();
                    // 仲間意識をもつモンスターのターゲットに設定
                    this.serchLink(pc, this.getNpcTemplate().get_family());
                    if (damage > 0) {
                        pc.setPetTarget(this);
                    }

                    final int newHp = this.getCurrentHp() - damage;
                    if ((newHp <= 0) && !this.isDead()) {
                        this.setCurrentHpDirect(0);
                        this.setDead(true);
                        this.setStatus(ActionCodes.ACTION_Die);
                        this._lastattacker = attacker;
                        final Death death = new Death();
                        GeneralThreadPool.getInstance().execute(death);
                    }
                    if (newHp > 0) {
                        this.setCurrentHp(newHp);
                    }
                } else if (!this.isDead()) { // 念のため
                    this.setDead(true);
                    this.setStatus(ActionCodes.ACTION_Die);
                    this._lastattacker = attacker;
                    final Death death = new Death();
                    GeneralThreadPool.getInstance().execute(death);
                }
            }
        }
    }

    @Override
    public void searchTarget() {
        // 目标搜索
        L1PcInstance targetPlayer = null;

        for (final L1PcInstance pc : L1World.getInstance().getVisiblePlayer(
                this)) {
            if ((pc.getCurrentHp() <= 0) || pc.isDead() || pc.isGm()
                    || pc.isGhost()) {
                continue;
            }
            if (!pc.isInvisble() || this.getNpcTemplate().is_agrocoi()) { // 检查隐身
                if (!pc.isElf()) { // 不是精灵
                    targetPlayer = pc;
                    this.wideBroadcastPacket(new S_NpcChatPacket(this, "$804",
                            2)); // 人类，如果你重视你的生命现在就快离开这神圣的地方。
                    break;
                } else if (pc.isElf() && pc.isWantedForElf()) {
                    targetPlayer = pc;
                    this.wideBroadcastPacket(new S_NpcChatPacket(this, "$815",
                            1)); // 若杀害同族，必须以自己的生命赎罪。
                    break;
                }
            }
        }
        if (targetPlayer != null) {
            this._hateList.add(targetPlayer, 0);
            this._target = targetPlayer;
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
        if ((cha != null) && this._hateList.isEmpty()) { // ターゲットがいない場合のみ追加
            this._hateList.add(cha, 0);
            this.checkTarget();
        }
    }
}
