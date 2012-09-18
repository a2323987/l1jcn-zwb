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

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.ActionCodes;
import com.lineage.server.IdFactory;
import com.lineage.server.datatables.MobSkillTable;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.datatables.SprTable;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.model.skill.L1SkillUse;
import com.lineage.server.serverpackets.S_CharVisualUpdate;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_NPCPack;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.templates.L1MobSkill;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.templates.L1Skills;
import com.lineage.server.utils.Random;
import com.lineage.server.utils.collections.Lists;
import com.lineage.server.utils.collections.Maps;

/**
 * MOB使用技能
 */
public class L1MobSkillUse {

    private static Logger _log = Logger
            .getLogger(L1MobSkillUse.class.getName());

    private L1MobSkill _mobSkillTemplate = null;

    private L1NpcInstance _attacker = null;

    private L1Character _target = null;

    private int _sleepTime = 0;

    private int _skillUseCount[];

    public L1MobSkillUse(final L1NpcInstance npc) {
        this._sleepTime = 0;

        this._mobSkillTemplate = MobSkillTable.getInstance().getTemplate(
                npc.getNpcTemplate().get_npcId());
        if (this._mobSkillTemplate == null) {
            return;
        }
        this._attacker = npc;
        this._skillUseCount = new int[this.getMobSkillTemplate().getSkillSize()];
    }

    // 现在ChangeTargetで有效な值は2,3のみ
    private L1Character changeTarget(final int type, final int idx) {
        L1Character target;

        switch (type) {
            case L1MobSkill.CHANGE_TARGET_ME:
                target = this._attacker;
                break;
            case L1MobSkill.CHANGE_TARGET_RANDOM:
                // 选定候补目标
                final List<L1Character> targetList = Lists.newList();
                for (final L1Object obj : L1World.getInstance()
                        .getVisibleObjects(this._attacker)) {
                    if ((obj instanceof L1PcInstance)
                            || (obj instanceof L1PetInstance)
                            || (obj instanceof L1SummonInstance)) {
                        final L1Character cha = (L1Character) obj;

                        final int distance = this._attacker.getLocation()
                                .getTileLineDistance(cha.getLocation());

                        // 目标在发动范围外
                        if (!this.getMobSkillTemplate().isTriggerDistance(idx,
                                distance)) {
                            continue;
                        }

                        // 有障碍物
                        if (!this._attacker.glanceCheck(cha.getX(), cha.getY())) {
                            continue;
                        }

                        if (!this._attacker.getHateList().containsKey(cha)) { // 无攻击对象
                            continue;
                        }

                        if (cha.isDead()) { // 死亡
                            continue;
                        }

                        // 幽灵状态
                        if (cha instanceof L1PcInstance) {
                            if (((L1PcInstance) cha).isGhost()) {
                                continue;
                            }
                        }
                        targetList.add((L1Character) obj);
                    }
                }

                if (targetList.isEmpty()) {
                    target = this._target;
                } else {
                    final int randomSize = targetList.size() * 100;
                    final int targetIndex = Random.nextInt(randomSize) / 100;
                    target = targetList.get(targetIndex);
                }
                break;

            default:
                target = this._target;
                break;
        }
        return target;
    }

    public L1MobSkill getMobSkillTemplate() {
        return this._mobSkillTemplate;
    }

    private int getSkillUseCount(final int idx) {
        return this._skillUseCount[idx];
    }

    public int getSleepTime() {
        return this._sleepTime;
    }

    /**
     * 检查触发的条件。
     */
    public boolean isSkillTrigger(final L1Character tg) {
        if (this._mobSkillTemplate == null) {
            return false;
        }
        this._target = tg;

        int type;
        type = this.getMobSkillTemplate().getType(0);

        if (type == L1MobSkill.TYPE_NONE) {
            return false;
        }

        for (int i = 0; (i < this.getMobSkillTemplate().getSkillSize())
                && (this.getMobSkillTemplate().getType(i) != L1MobSkill.TYPE_NONE); i++) {
            if (this.isSkillUseble(i, false)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 只检查触发条件
     */
    private boolean isSkillUseble(final int skillIdx, final boolean isTriRnd) {
        boolean useble = false;
        final int type = this.getMobSkillTemplate().getType(skillIdx);
        final int chance = Random.nextInt(100) + 1;

        if (chance > this.getMobSkillTemplate().getTriggerRandom(skillIdx)) {
            return false;
        }

        if (isTriRnd || (type == L1MobSkill.TYPE_SUMMON)
                || (type == L1MobSkill.TYPE_POLY)) {
            /*
             * if (getMobSkillTemplate().getTriggerRandom(skillIdx) > 0) { int
             * chance = Random.nextInt(100) + 1; if (chance <
             * getMobSkillTemplate().getTriggerRandom(skillIdx)) { useble =
             * true; } else { return false; } }
             */// 确定此修改后的模式是仿正的，再移除此注解掉的程式码
            useble = true;
        }

        if (this.getMobSkillTemplate().getTriggerHp(skillIdx) > 0) {
            final int hpRatio = (this._attacker.getCurrentHp() * 100)
                    / this._attacker.getMaxHp();
            if (hpRatio <= this.getMobSkillTemplate().getTriggerHp(skillIdx)) {
                useble = true;
            } else {
                return false;
            }
        }

        if (this.getMobSkillTemplate().getTriggerCompanionHp(skillIdx) > 0) {
            final L1NpcInstance companionNpc = this.searchMinCompanionHp();
            if (companionNpc == null) {
                return false;
            }

            final int hpRatio = (companionNpc.getCurrentHp() * 100)
                    / companionNpc.getMaxHp();
            if (hpRatio <= this.getMobSkillTemplate().getTriggerCompanionHp(
                    skillIdx)) {
                useble = true;
                this._target = companionNpc; // 更换目标
            } else {
                return false;
            }
        }

        if (this.getMobSkillTemplate().getTriggerRange(skillIdx) != 0) {
            final int distance = this._attacker.getLocation()
                    .getTileLineDistance(this._target.getLocation());

            if (this.getMobSkillTemplate()
                    .isTriggerDistance(skillIdx, distance)) {
                useble = true;
            } else {
                return false;
            }
        }

        if (this.getMobSkillTemplate().getTriggerCount(skillIdx) > 0) {
            if (this.getSkillUseCount(skillIdx) < this.getMobSkillTemplate()
                    .getTriggerCount(skillIdx)) {
                useble = true;
            } else {
                return false;
            }
        }
        return useble;
    }

    /** 魔法攻击 */
    private boolean magicAttack(final int idx) {
        final L1SkillUse skillUse = new L1SkillUse();
        final int skillid = this.getMobSkillTemplate().getSkillId(idx);
        int actId = this.getMobSkillTemplate().getActid(idx);
        final int gfxId = this.getMobSkillTemplate().getGfxid(idx);
        final int mpConsume = this.getMobSkillTemplate().getMpConsume(idx);
        boolean canUseSkill = false;

        if (skillid > 0) {
            skillUse.setSkillRanged(this.getMobSkillTemplate().getRange(idx)); // 变更技能施放距离
            skillUse.setSkillRanged(this.getMobSkillTemplate()
                    .getSkillArea(idx)); // 变更技能施放范围
            canUseSkill = skillUse.checkUseSkill(null, skillid,
                    this._target.getId(), this._target.getX(),
                    this._target.getY(), null, 0, L1SkillUse.TYPE_NORMAL,
                    this._attacker, actId, gfxId, mpConsume);
        }

        final L1Skills skill = SkillsTable.getInstance().getTemplate(skillid);
        if (skill.getTarget().equals("buff")
                && this._target.hasSkillEffect(skillid)) {
            return false;
        }

        if (canUseSkill == true) {
            if (this.getMobSkillTemplate().getLeverage(idx) > 0) {
                skillUse.setLeverage(this.getMobSkillTemplate()
                        .getLeverage(idx));
            }
            skillUse.handleCommands(null, skillid, this._target.getId(),
                    this._target.getX(), this._target.getX(), null, 0,
                    L1SkillUse.TYPE_NORMAL, this._attacker);

            // 延迟时间判断
            if (actId == 0) {
                actId = skill.getActionId();
            }
            this._sleepTime = SprTable.getInstance().getSprSpeed(
                    this._attacker.getTempCharGfx(), actId);

            return true;
        }
        return false;
    }

    private void mobspawn(final int summonId) {
        try {
            final L1Npc spawnmonster = NpcTable.getInstance().getTemplate(
                    summonId);
            if (spawnmonster != null) {
                L1NpcInstance mob = null;
                try {
                    final String implementationName = spawnmonster.getImpl();
                    final Constructor<?> _constructor = Class
                            .forName(
                                    (new StringBuilder())
                                            .append("com.lineage.server.model.Instance.")
                                            .append(implementationName)
                                            .append("Instance").toString())
                            .getConstructors()[0];
                    mob = (L1NpcInstance) _constructor
                            .newInstance(new Object[] { spawnmonster });
                    mob.setId(IdFactory.getInstance().nextId());
                    final L1Location loc = this._attacker.getLocation()
                            .randomLocation(8, false);
                    final int heading = Random.nextInt(8);
                    mob.setX(loc.getX());
                    mob.setY(loc.getY());
                    mob.setHomeX(loc.getX());
                    mob.setHomeY(loc.getY());
                    final short mapid = this._attacker.getMapId();
                    mob.setMap(mapid);
                    mob.setHeading(heading);
                    L1World.getInstance().storeObject(mob);
                    L1World.getInstance().addVisibleObject(mob);
                    final L1Object object = L1World.getInstance().findObject(
                            mob.getId());
                    final L1MonsterInstance newnpc = (L1MonsterInstance) object;
                    newnpc.set_storeDroped(true); // 召唤怪不会掉落道具
                    if (newnpc.getTempCharGfx() == 145) { // 史巴托
                        newnpc.setStatus(11);
                        newnpc.broadcastPacket(new S_NPCPack(newnpc));
                        newnpc.broadcastPacket(new S_DoActionGFX(
                                newnpc.getId(), ActionCodes.ACTION_Appear));
                        newnpc.setStatus(0);
                        newnpc.broadcastPacket(new S_CharVisualUpdate(newnpc,
                                newnpc.getStatus()));
                    } else if (newnpc.getTempCharGfx() == 7591) { // 泥龙(地)
                        newnpc.broadcastPacket(new S_NPCPack(newnpc));
                        newnpc.broadcastPacket(new S_DoActionGFX(
                                newnpc.getId(), ActionCodes.ACTION_AxeWalk));
                    }
                    newnpc.onNpcAI();
                    newnpc.turnOnOffLight();
                    newnpc.startChat(L1NpcInstance.CHAT_TIMING_APPEARANCE); // 开始喊话
                } catch (final Exception e) {
                    _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                }
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    private void mobspawn(final int summonId, final int count) {
        int i;

        for (i = 0; i < count; i++) {
            this.mobspawn(summonId);
        }
    }

    /**
     * 物理攻击
     */
    private boolean physicalAttack(final int idx) {
        final Map<Integer, Integer> targetList = Maps.newConcurrentMap();
        final int areaWidth = this.getMobSkillTemplate().getAreaWidth(idx);
        final int areaHeight = this.getMobSkillTemplate().getAreaHeight(idx);
        final int range = this.getMobSkillTemplate().getRange(idx);
        final int actId = this.getMobSkillTemplate().getActid(idx);
        final int gfxId = this.getMobSkillTemplate().getGfxid(idx);

        // 超出范围
        if (this._attacker.getLocation().getTileLineDistance(
                this._target.getLocation()) > range) {
            return false;
        }

        // 有障碍物不能攻击
        if (!this._attacker.glanceCheck(this._target.getX(),
                this._target.getY())) {
            return false;
        }

        this._attacker.setHeading(this._attacker.targetDirection(
                this._target.getX(), this._target.getY())); // 面向集合

        if (areaHeight > 0) {
            // 范围攻击
            final List<L1Object> objs = L1World.getInstance()
                    .getVisibleBoxObjects(this._attacker,
                            this._attacker.getHeading(), areaWidth, areaHeight);

            for (final L1Object obj : objs) {
                if (!(obj instanceof L1Character)) { // 目标在角色范围以外不执行。
                    continue;
                }

                final L1Character cha = (L1Character) obj;
                if (cha.isDead()) { // 角色死亡
                    continue;
                }

                // 幽灵状态
                if (cha instanceof L1PcInstance) {
                    if (((L1PcInstance) cha).isGhost()) {
                        continue;
                    }
                }

                // 有障碍物
                if (!this._attacker.glanceCheck(cha.getX(), cha.getY())) {
                    continue;
                }

                if ((this._target instanceof L1PcInstance)
                        || (this._target instanceof L1SummonInstance)
                        || (this._target instanceof L1PetInstance)) {
                    // 对PC
                    if (((obj instanceof L1PcInstance)
                            && !((L1PcInstance) obj).isGhost() && !((L1PcInstance) obj)
                            .isGmInvis())
                            || (obj instanceof L1SummonInstance)
                            || (obj instanceof L1PetInstance)) {
                        targetList.put(obj.getId(), 0);
                    }
                } else {
                    // 对NPC
                    if (obj instanceof L1MonsterInstance) {
                        targetList.put(obj.getId(), 0);
                    }
                }
            }
        } else {
            // 单体攻击
            targetList.put(this._target.getId(), 0); // 追加额外的目标
        }

        if (targetList.isEmpty()) {
            return false;
        }

        final Iterator<Integer> ite = targetList.keySet().iterator();
        while (ite.hasNext()) {
            final int targetId = ite.next();
            final L1Attack attack = new L1Attack(this._attacker,
                    (L1Character) L1World.getInstance().findObject(targetId));
            if (attack.calcHit()) {
                if (this.getMobSkillTemplate().getLeverage(idx) > 0) {
                    attack.setLeverage(this.getMobSkillTemplate().getLeverage(
                            idx));
                }
                attack.calcDamage();
            }
            if (actId > 0) {
                attack.setActId(actId);
            }
            // 攻击モーションは实际のターゲットに对してのみ行う
            if (targetId == this._target.getId()) {
                if (gfxId > 0) {
                    this._attacker.broadcastPacket(new S_SkillSound(
                            this._attacker.getId(), gfxId));
                }
                attack.action();
            }
            attack.commit();
        }

        if (actId > 0) {
            this._sleepTime = SprTable.getInstance().getSprSpeed(
                    this._attacker.getTempCharGfx(), actId);
        } else {
            this._sleepTime = this._attacker.getAtkspeed();
        }
        return true;
    }

    /*
     * 15セル以内で射线が通るPCを指定したモンスターに强制变身させる。 对PCしか使えない。
     */
    private boolean poly(final int idx) {
        final int polyId = this.getMobSkillTemplate().getPolyId(idx);
        final int actId = this.getMobSkillTemplate().getActid(idx);
        boolean usePoly = false;

        if (polyId == 0) {
            return false;
        }
        // 施法动作
        if (actId > 0) {
            final S_DoActionGFX gfx = new S_DoActionGFX(this._attacker.getId(),
                    actId);
            this._attacker.broadcastPacket(gfx);
            this._sleepTime = SprTable.getInstance().getSprSpeed(
                    this._attacker.getTempCharGfx(), actId);
        }

        for (final L1PcInstance pc : L1World.getInstance().getVisiblePlayer(
                this._attacker)) {
            if (pc.isDead()) { // 死亡
                continue;
            }
            if (pc.isGhost()) {
                continue;
            }
            if (pc.isGmInvis()) {
                continue;
            }
            if (this._attacker.glanceCheck(pc.getX(), pc.getY()) == false) {
                continue; // 射线が通らない
            }

            switch (this._attacker.getNpcTemplate().get_npcId()) {
                case 81082: // 火焰之影
                    pc.getInventory().takeoffEquip(945); // 将目标装备卸下。
                    break;
                default:
                    break;
            }
            this._attacker.broadcastPacket(new S_SkillSound(pc.getId(), 230));
            L1PolyMorph.doPoly(pc, polyId, 1800, L1PolyMorph.MORPH_BY_NPC);
            usePoly = true;
        }
        return usePoly;
    }

    public void resetAllSkillUseCount() {
        if (this.getMobSkillTemplate() == null) {
            return;
        }

        for (int i = 0; i < this.getMobSkillTemplate().getSkillSize(); i++) {
            this._skillUseCount[i] = 0;
        }
    }

    private L1NpcInstance searchMinCompanionHp() {
        L1NpcInstance npc;
        L1NpcInstance minHpNpc = null;
        int hpRatio = 100;
        int companionHpRatio;
        final int family = this._attacker.getNpcTemplate().get_family();

        for (final L1Object object : L1World.getInstance().getVisibleObjects(
                this._attacker)) {
            if (object instanceof L1NpcInstance) {
                npc = (L1NpcInstance) object;
                if (npc.getNpcTemplate().get_family() == family) {
                    companionHpRatio = (npc.getCurrentHp() * 100)
                            / npc.getMaxHp();
                    if (companionHpRatio < hpRatio) {
                        hpRatio = companionHpRatio;
                        minHpNpc = npc;
                    }
                }
            }
        }
        return minHpNpc;
    }

    public void setSleepTime(final int i) {
        this._sleepTime = i;
    }

    /**
     * 技能攻击 如果可以攻击true。 不能攻击false。
     */
    public boolean skillUse(final L1Character tg, final boolean isTriRnd) {
        if (this._mobSkillTemplate == null) {
            return false;
        }
        this._target = tg;

        int type;
        type = this.getMobSkillTemplate().getType(0);

        if (type == L1MobSkill.TYPE_NONE) {
            return false;
        }

        int[] skills = null;
        int skillSizeCounter = 0;
        final int skillSize = this.getMobSkillTemplate().getSkillSize();
        if (skillSize >= 0) {
            skills = new int[skillSize];
        }

        for (int i = 0; (i < this.getMobSkillTemplate().getSkillSize())
                && (this.getMobSkillTemplate().getType(i) != L1MobSkill.TYPE_NONE); i++) {
            if (this.isSkillUseble(i, isTriRnd) == false) {
                continue;
            }
            skills[skillSizeCounter] = i;
            skillSizeCounter++;
        }

        if (skillSizeCounter != 0) {
            final int num = Random.nextInt(skillSizeCounter);
            if (this.useSkill(skills[num])) { // 使用技能
                return true;
            }
        }

        return false;
    }

    private void skillUseCountUp(final int idx) {
        this._skillUseCount[idx]++;
    }

    private boolean summon(final int idx) {
        final int summonId = this.getMobSkillTemplate().getSummon(idx);
        final int min = this.getMobSkillTemplate().getSummonMin(idx);
        final int max = this.getMobSkillTemplate().getSummonMax(idx);
        int count = 0;
        final int actId = this.getMobSkillTemplate().getActid(idx);
        final int gfxId = this.getMobSkillTemplate().getGfxid(idx);

        if (summonId == 0) {
            return false;
        }

        // 施法动作
        if (actId > 0) {
            final S_DoActionGFX gfx = new S_DoActionGFX(this._attacker.getId(),
                    actId);
            this._attacker.broadcastPacket(gfx);
            this._sleepTime = SprTable.getInstance().getSprSpeed(
                    this._attacker.getTempCharGfx(), actId);
        }
        // 魔方阵
        if (gfxId > 0) {
            this._attacker.broadcastPacket(new S_SkillSound(this._attacker
                    .getId(), gfxId));
        }
        count = Random.nextInt(max) + min;
        this.mobspawn(summonId, count);
        return true;
    }

    /** 使用技能 */
    private boolean useSkill(final int i) {
        // 对自身施法判断
        final int changeType = this.getMobSkillTemplate().getChangeTarget(i);
        if (changeType == 2) {
            this._target = this.changeTarget(changeType, i);
        }

        boolean isUseSkill = false;
        final int type = this.getMobSkillTemplate().getType(i);
        if (type == L1MobSkill.TYPE_PHYSICAL_ATTACK) { // 物理攻击
            if (this.physicalAttack(i) == true) {
                this.skillUseCountUp(i);
                isUseSkill = true;
            }
        } else if (type == L1MobSkill.TYPE_MAGIC_ATTACK) { // 魔法攻击
            if (this.magicAttack(i) == true) {
                this.skillUseCountUp(i);
                isUseSkill = true;
            }
        } else if (type == L1MobSkill.TYPE_SUMMON) { // サモンする
            if (this.summon(i) == true) {
                this.skillUseCountUp(i);
                isUseSkill = true;
            }
        } else if (type == L1MobSkill.TYPE_POLY) { // 强制变身させる
            if (this.poly(i) == true) {
                this.skillUseCountUp(i);
                isUseSkill = true;
            }
        }
        return isUseSkill;
    }
}
