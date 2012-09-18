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

import static com.lineage.server.model.skill.L1SkillId.ABSOLUTE_BARRIER;
import static com.lineage.server.model.skill.L1SkillId.BERSERKERS;
import static com.lineage.server.model.skill.L1SkillId.BONE_BREAK;
import static com.lineage.server.model.skill.L1SkillId.BONE_BREAK_START;
import static com.lineage.server.model.skill.L1SkillId.BOUNCE_ATTACK;
import static com.lineage.server.model.skill.L1SkillId.BURNING_SLASH;
import static com.lineage.server.model.skill.L1SkillId.BURNING_SPIRIT;
import static com.lineage.server.model.skill.L1SkillId.BURNING_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_0_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_1_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_2_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_3_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_4_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_5_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_6_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_7_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_0_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_0_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_1_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_2_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_3_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_3_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_4_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_5_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_6_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_7_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_0_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_0_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_1_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_2_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_2_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_3_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_4_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_5_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_6_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_7_S;
import static com.lineage.server.model.skill.L1SkillId.DOUBLE_BRAKE;
import static com.lineage.server.model.skill.L1SkillId.DRAGON_SKIN;
import static com.lineage.server.model.skill.L1SkillId.EARTH_BIND;
import static com.lineage.server.model.skill.L1SkillId.ELEMENTAL_FIRE;
import static com.lineage.server.model.skill.L1SkillId.ENCHANT_VENOM;
import static com.lineage.server.model.skill.L1SkillId.FIRE_BLESS;
import static com.lineage.server.model.skill.L1SkillId.FIRE_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.FREEZING_BLIZZARD;
import static com.lineage.server.model.skill.L1SkillId.FREEZING_BREATH;
import static com.lineage.server.model.skill.L1SkillId.ICE_LANCE;
import static com.lineage.server.model.skill.L1SkillId.ICE_LANCE_BASILISK;
import static com.lineage.server.model.skill.L1SkillId.ICE_LANCE_COCKATRICE;
import static com.lineage.server.model.skill.L1SkillId.IMMUNE_TO_HARM;
import static com.lineage.server.model.skill.L1SkillId.PATIENCE;
import static com.lineage.server.model.skill.L1SkillId.REDUCTION_ARMOR;
import static com.lineage.server.model.skill.L1SkillId.SMASH;
import static com.lineage.server.model.skill.L1SkillId.SOUL_OF_FLAME;
import static com.lineage.server.model.skill.L1SkillId.SPECIAL_EFFECT_WEAKNESS_LV1;
import static com.lineage.server.model.skill.L1SkillId.SPECIAL_EFFECT_WEAKNESS_LV2;
import static com.lineage.server.model.skill.L1SkillId.SPECIAL_EFFECT_WEAKNESS_LV3;

import com.lineage.Config;
import com.lineage.server.ActionCodes;
import com.lineage.server.WarTimeController;
import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.model.gametime.L1GameTimeClock;
import com.lineage.server.model.npc.action.L1NpcDefaultAction;
import com.lineage.server.model.poison.L1DamagePoison;
import com.lineage.server.model.poison.L1ParalysisPoison;
import com.lineage.server.model.poison.L1SilencePoison;
import com.lineage.server.serverpackets.S_AttackMissPacket;
import com.lineage.server.serverpackets.S_AttackPacket;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_EffectLocation;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillIconGFX;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.serverpackets.S_UseArrowSkill;
import com.lineage.server.serverpackets.S_UseAttackSkill;
import com.lineage.server.templates.L1MagicDoll;
import com.lineage.server.templates.L1Skills;
import com.lineage.server.types.Point;
import com.lineage.server.utils.Random;

/**
 * 攻击
 */
public class L1Attack {
    /** 自身 */
    private L1PcInstance _pc = null;
    /** 目标 */
    private L1Character _target = null;
    /** 目标为 PC */
    private L1PcInstance _targetPc = null;
    /** 怪物 */
    private L1NpcInstance _npc = null;
    /** 目标为 NPC */
    private L1NpcInstance _targetNpc = null;
    /** 目标 ID */
    private final int _targetId;
    /** 目标的 X 坐标 */
    private final int _targetX;
    /** 目标的 Y 坐标 */
    private final int _targetY;
    /** 伤害状况 */
    private int _statusDamage = 0;
    /** 命中率 */
    private int _hitRate = 0;
    /** 状态 (谁攻击谁) */
    private int _calcType;
    /** PC 对 PC */
    private static final int PC_PC = 1;
    /** PC 对 NPC */
    private static final int PC_NPC = 2;
    /** NPC 对 PC */
    private static final int NPC_PC = 3;
    /** NPC 对 NPC */
    private static final int NPC_NPC = 4;
    /** 命中 */
    private boolean _isHit = false;
    /** 伤害 */
    private int _damage = 0;
    /** 吸取魔力 */
    private int _drainMana = 0;
    /** 吸取体力 */
    private int _drainHp = 0;
    /** 效果 ID */
    private byte _effectId = 0;
    /** 攻击图像 ID */
    private int _attckGrfxId = 0;
    /** 攻击动作 ID */
    private int _attckActId = 0;

    /** 攻击者がプレイヤーの场合の武器情报 */
    private L1ItemInstance weapon = null;
    /** 武器编号 */
    private int _weaponId = 0;
    /** 武器种类 */
    private int _weaponType = 0;
    /** 武器种类2 */
    private int _weaponType2 = 0;
    /** 武器的额外命中 */
    private int _weaponAddHit = 0;
    /** 武器的额外伤害 */
    private int _weaponAddDmg = 0;
    /** 武器对小怪的伤害 */
    private int _weaponSmall = 0;
    /** 武器对大怪的伤害 */
    private int _weaponLarge = 0;
    /** 武器攻击范围 */
    private int _weaponRange = 1;
    /** 武器的状态 (祝福、普通、诅咒) */
    private int _weaponBless = 1;
    /** 武器加成 */
    private int _weaponEnchant = 0;
    /** 武器的材质 (23种) */
    private int _weaponMaterial = 0;
    /** 双手武器伤害几率 */
    private int _weaponDoubleDmgChance = 0;
    /** 武器的属性种类 */
    private int _weaponAttrEnchantKind = 0;
    /** 武器的属性等级 */
    private int _weaponAttrEnchantLevel = 0;
    /** 箭 */
    private L1ItemInstance _arrow = null;
    /** 飞刀 */
    private L1ItemInstance _sting = null;

    /** 技能 ID */
    private final int _skillId;

    @SuppressWarnings("unused")
    private double _skillDamage = 0;

    private int _leverage = 10; // 1/10倍で表现する。

    /** STR命中补正 */
    private static final int[] strHit = { -2, -2, -2, -2, -2, -2, -2, // 1～7まで
            -2, -1, -1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 6, 6, 6, // 8～26まで
            7, 7, 7, 8, 8, 8, 9, 9, 9, 10, 10, 10, 11, 11, 11, 12, 12, 12, // 27～44まで
            13, 13, 13, 14, 14, 14, 15, 15, 15, 16, 16, 16, 17, 17, 17 }; // 45～59まで

    /** DEX命中补正 */
    private static final int[] dexHit = { -2, -2, -2, -2, -2, -2, -1, -1, 0, 0, // 1～10まで
            1, 1, 2, 2, 3, 3, 4, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, // 11～30まで
            17, 18, 19, 19, 19, 20, 20, 20, 21, 21, 21, 22, 22, 22, 23, // 31～45まで
            23, 23, 24, 24, 24, 25, 25, 25, 26, 26, 26, 27, 27, 27, 28 }; // 46～60まで

    /** STR伤害补正 */
    private static final int[] strDmg = new int[256];

    static {
        // STR伤害补正
        int dmg = -6;
        for (int str = 0; str <= 22; str++) { // 0～22每两点+1伤害
            if (str % 2 == 1) {
                dmg++;
            }
            strDmg[str] = dmg;
        }
        for (int str = 23; str <= 28; str++) { // 23～28每三点+1伤害
            if (str % 3 == 2) {
                dmg++;
            }
            strDmg[str] = dmg;
        }
        for (int str = 29; str <= 32; str++) { // 29～32每两点+1伤害
            if (str % 2 == 1) {
                dmg++;
            }
            strDmg[str] = dmg;
        }
        for (int str = 33; str <= 34; str++) { // 33～34每一点+1伤害
            dmg++;
            strDmg[str] = dmg;
        }
        for (int str = 35; str <= 255; str++) { // 35～255每四点+1伤害
            if (str % 4 == 1) {
                dmg++;
            }
            strDmg[str] = dmg;
        }
    }

    /** DEX伤害补正 */
    private static final int[] dexDmg = new int[256];

    static {
        // DEX伤害补正
        for (int dex = 0; dex <= 14; dex++) {
            // 0～14点 伤害0
            dexDmg[dex] = 0;
        }
        dexDmg[15] = 1;
        dexDmg[16] = 2;
        dexDmg[17] = 3;
        dexDmg[18] = 4;
        dexDmg[19] = 4;
        dexDmg[20] = 4;
        dexDmg[21] = 5;
        dexDmg[22] = 5;
        dexDmg[23] = 5;
        int dmg = 5;
        for (int dex = 24; dex <= 35; dex++) { // 24～35每三点+1伤害
            if (dex % 3 == 1) {
                dmg++;
            }
            dexDmg[dex] = dmg;
        }
        for (int dex = 36; dex <= 255; dex++) { // 36～255每四点+1伤害
            if (dex % 4 == 1) {
                dmg++;
            }
            dexDmg[dex] = dmg;
        }
    }

    /** 拥有这些状态的, 不会受到伤害(无敌) */
    private static final int[] INVINCIBLE = { ABSOLUTE_BARRIER, // 绝对屏障
            ICE_LANCE, // 冰矛围篱
            FREEZING_BLIZZARD, // 冰雪飓风
            FREEZING_BREATH, // 寒冰喷吐
            EARTH_BIND, // 大地屏障
            ICE_LANCE_COCKATRICE,// 亚力安冰矛围篱
            ICE_LANCE_BASILISK
    // 邪恶蜥蜴冰矛围篱
    };

    public L1Attack(final L1Character attacker, final L1Character target) {
        this(attacker, target, 0);
    }

    public L1Attack(final L1Character attacker, final L1Character target,
            final int skillId) {
        this._skillId = skillId;
        if (this._skillId != 0) {
            final L1Skills skills = SkillsTable.getInstance().getTemplate(
                    this._skillId);
            this._skillDamage = skills.getDamageValue();
        }
        // 攻击方为PC
        if (attacker instanceof L1PcInstance) {
            this._pc = (L1PcInstance) attacker;
            if (target instanceof L1PcInstance) {
                this._targetPc = (L1PcInstance) target;
                this._calcType = PC_PC;
            } else if (target instanceof L1NpcInstance) {
                this._targetNpc = (L1NpcInstance) target;
                this._calcType = PC_NPC;
            }
            // 取回使用的武器
            this.weapon = this._pc.getWeapon();
            if (this.weapon != null) {
                this._weaponId = this.weapon.getItem().getItemId(); // 武器编号
                this._weaponType = this.weapon.getItem().getType1(); // 武器类型
                this._weaponType2 = this.weapon.getItem().getType(); // 武器类型2
                this._weaponAddHit = this.weapon.getItem().getHitModifier()
                        + this.weapon.getHitByMagic(); // 武器的额外命中
                this._weaponAddDmg = this.weapon.getItem().getDmgModifier()
                        + this.weapon.getDmgByMagic(); // 武器的额外伤害
                this._weaponSmall = this.weapon.getItem().getDmgSmall(); // 武器最小伤害值
                this._weaponLarge = this.weapon.getItem().getDmgLarge(); // 武器最大伤害值
                this._weaponRange = this.weapon.getItem().getRange(); // 武器的攻击范围
                this._weaponBless = this.weapon.getItem().getBless(); // 武器的属性
                                                                      // 0:祝福
                                                                      // 1:一般
                                                                      // 2:诅咒
                this._weaponEnchant = this.weapon.getEnchantLevel(); // 武器的加成
                this._weaponMaterial = this.weapon.getItem().getMaterial(); // 武器的材质
                this._statusDamage = dexDmg[this._pc.getDex()]; // 伤害预设用敏捷补正

                switch (this._weaponType) {
                    case 20: // 弓箭
                        this._arrow = this._pc.getInventory().getArrow();
                        if (this._arrow != null) { // 有箭
                            this._weaponBless = this._arrow.getItem()
                                    .getBless();
                            this._weaponMaterial = this._arrow.getItem()
                                    .getMaterial();
                        }
                        break;

                    case 62: // 铁手甲
                        this._sting = this._pc.getInventory().getSting();
                        if (this._sting != null) { // 有飞刀
                            this._weaponBless = this._sting.getItem()
                                    .getBless();
                            this._weaponMaterial = this._sting.getItem()
                                    .getMaterial();
                        }
                        break;

                    default: // 近战类武器
                        this._weaponEnchant = this.weapon.getEnchantLevel()
                                - this.weapon.get_durability(); // 计算武器损伤
                        this._statusDamage = strDmg[this._pc.getStr()]; // 伤害用力量补正
                        break;
                }
                this._weaponDoubleDmgChance = this.weapon.getItem()
                        .getDoubleDmgChance(); // 双手武器伤害发动几率
                this._weaponAttrEnchantKind = this.weapon.getAttrEnchantKind(); // 武器的属性加成种类
                this._weaponAttrEnchantLevel = this.weapon
                        .getAttrEnchantLevel(); // 武器的属性加成等级
            }
        }
        // 攻击方为NPC
        else if (attacker instanceof L1NpcInstance) {
            this._npc = (L1NpcInstance) attacker;
            if (target instanceof L1PcInstance) {
                this._targetPc = (L1PcInstance) target;
                this._calcType = NPC_PC;
            } else if (target instanceof L1NpcInstance) {
                this._targetNpc = (L1NpcInstance) target;
                this._calcType = NPC_NPC;
            }
        }
        this._target = target;
        this._targetId = target.getId();
        this._targetX = target.getX();
        this._targetY = target.getY();
    }

    /**
     * ■■■■■■■■■■■■■■ 攻击动作送信 ■■■■■■■■■■■■■■
     */
    public void action() {
        if ((this._calcType == PC_PC) || (this._calcType == PC_NPC)) {
            this.actionPc(); // PC攻击动作
        } else if ((this._calcType == NPC_PC) || (this._calcType == NPC_NPC)) {
            this.actionNpc(); // NPC攻击动作
        }
    }

    /**
     * ■■■■ 发送反击屏障时的攻击动作 ■■■■
     */
    public void actionCounterBarrier() {
        if (this._calcType == PC_PC) {
            this._pc.setHeading(this._pc.targetDirection(this._targetX,
                    this._targetY)); // 设置面向
            this._pc.sendPackets(new S_AttackMissPacket(this._pc,
                    this._targetId));
            this._pc.broadcastPacket(new S_AttackMissPacket(this._pc,
                    this._targetId)); // 攻击MISS封包
            this._pc.sendPackets(new S_DoActionGFX(this._pc.getId(),
                    ActionCodes.ACTION_Damage));
            this._pc.broadcastPacket(new S_DoActionGFX(this._pc.getId(),
                    ActionCodes.ACTION_Damage)); // 受伤动作
        } else if (this._calcType == NPC_PC) {
            int actId = 0;
            this._npc.setHeading(this._npc.targetDirection(this._targetX,
                    this._targetY)); // 设置面向
            if (this.getActId() > 0) {
                actId = this.getActId();
            } else {
                actId = ActionCodes.ACTION_Attack;
            }
            if (this.getGfxId() > 0) {
                final int[] data = { actId, 0, this.getGfxId(), 6 }; // data =
                                                                     // {actId,
                                                                     // dmg,
                                                                     // getGfxId(),
                                                                     // use_type}
                this._npc.broadcastPacket(new S_UseAttackSkill(this._target,
                        this._npc.getId(), this._targetX, this._targetY, data));
            } else {
                this._npc.broadcastPacket(new S_AttackMissPacket(this._npc,
                        this._targetId, actId));
            }
            this._npc.broadcastPacket(new S_DoActionGFX(this._npc.getId(),
                    ActionCodes.ACTION_Damage));
        }
    }

    /**
     * ●●●● ＮＰＣ攻击动作 ●●●●
     */
    private void actionNpc() {
        int bowActId = 0;
        final int npcGfxid = this._npc.getTempCharGfx();
        int actId = L1NpcDefaultAction.getInstance().getSpecialAttack(npcGfxid); // 特殊攻击动作
        double dmg = this._damage;
        int[] data = null; // 封包参数

        this._npc.setHeading(this._npc.targetDirection(this._targetX,
                this._targetY)); // 改变面向

        // 与目标距离2格以上
        boolean isLongRange = false;
        if ((npcGfxid == 4521) || (npcGfxid == 4550) || (npcGfxid == 5062)
                || (npcGfxid == 5317) || (npcGfxid == 5324)
                || (npcGfxid == 5331) || (npcGfxid == 5338)
                || (npcGfxid == 5412)) {
            isLongRange = (this._npc.getLocation().getTileLineDistance(
                    new Point(this._targetX, this._targetY)) > 2);
        } else {
            isLongRange = (this._npc.getLocation().getTileLineDistance(
                    new Point(this._targetX, this._targetY)) > 1);
        }
        bowActId = this._npc.getPolyArrowGfx(); // 被变身后的远距图像
        if (bowActId == 0) {
            bowActId = this._npc.getNpcTemplate().getBowActId();
        }
        if (this.getActId() == 0) {
            if ((actId != 0) && ((Random.nextInt(100) + 1) <= 40)) {
                dmg *= 1.2;
            } else {
                if (!isLongRange || (bowActId == 0)) { // 近距离
                    actId = L1NpcDefaultAction.getInstance().getDefaultAttack(
                            npcGfxid);
                    if (bowActId > 0) { // 远距离怪物，近距离时攻击力加成
                        dmg *= 1.2;
                    }
                } else { // 远距离
                    actId = L1NpcDefaultAction.getInstance().getRangedAttack(
                            npcGfxid);
                }
            }
        } else {
            actId = this.getActId(); // 攻击动作由 mobskill控制
        }
        this._damage = (int) dmg;

        if (!this._isHit) { // 判断是否发送miss包
            this._damage = 0; // 伤害设0, 无受伤动作出现
        }

        // 距离2格以上使用 弓 攻击
        if (isLongRange && (bowActId > 0)) {
            data = new int[] { actId, this._damage, bowActId }; // data =
                                                                // {actid,
                                                                // dmg,
                                                                // spellgfx}
            this._npc.broadcastPacket(new S_UseArrowSkill(this._npc,
                    this._targetId, this._targetX, this._targetY, data));
        } else {
            if (this.getGfxId() > 0) {
                data = new int[] { actId, this._damage, this.getGfxId(), 6 }; // data
                                                                              // =
                                                                              // {actid,
                                                                              // dmg,
                                                                              // spellgfx,
                                                                              // use_type}
                this._npc.broadcastPacket(new S_UseAttackSkill(this._npc,
                        this._targetId, this._targetX, this._targetY, data));
            } else {
                data = new int[] { actId, this._damage, 0 }; // data = {actid,
                                                             // dmg,
                                                             // effect}
                this._npc.broadcastPacket(new S_AttackPacket(this._npc,
                        this._targetId, data)); // 对非自身送出
            }
        }
        if (this._isHit) {
            this._target.broadcastPacketExceptTargetSight(new S_DoActionGFX(
                    this._targetId, ActionCodes.ACTION_Damage), this._npc);
        }
    }

    /**
     * ●●●● ＰＣ攻击动作 ●●●●
     */
    public void actionPc() {
        this._attckActId = 1;
        boolean isFly = false;
        this._pc.setHeading(this._pc.targetDirection(this._targetX,
                this._targetY)); // 改变面向
        /*
         * switch (this._weaponType) { case 20: // 弓 if ((this._arrow != null)
         * || (this._weaponId == 190)) { // 弓 有箭或沙哈之弓 if (this._arrow != null) {
         * // 弓 - 有箭 this._attckGrfxId = 66; // 箭
         * this._pc.getInventory().removeItem(this._arrow, 1); // 移除一支箭矢 } else
         * if (this._weaponId == 190) { this._attckGrfxId = 2349; // 魔法箭 }
         * switch (this._pc.getTempCharGfx()) { case 8719: this._attckGrfxId =
         * 8721; // 橘子籽 break; case 8900: this._attckGrfxId = 8904; // 魔法箭
         * break; case 8913: this._attckGrfxId = 8916; // 魔法箭 break; } isFly =
         * true; } break; case 62: // 铁手甲 if (this._sting != null) { // 铁手甲 -
         * 有飞刀 this._pc.getInventory().removeItem(this._sting, 1); // 移除一个飞刀
         * this._attckGrfxId = 2989; // 飞刀 isFly = true; } break; }
         */
        if ((this._weaponType == 20)
                && ((this._arrow != null) || (this._weaponId == 190))) { // 弓
                                                                         // 有箭或沙哈之弓
            if (this._arrow != null) { // 弓 - 有箭
                this._pc.getInventory().removeItem(this._arrow, 1);
                this._attckGrfxId = 66; // 箭
            } else if (this._weaponId == 190) {
                this._attckGrfxId = 2349; // 魔法箭
            }

            if (this._pc.getTempCharGfx() == 8719) {
                this._attckGrfxId = 8721; // 橘子籽
            }
            if (this._pc.getTempCharGfx() == 8900) {
                this._attckGrfxId = 8904; // 魔法箭
            }
            if (this._pc.getTempCharGfx() == 8913) {
                this._attckGrfxId = 8916; // 魔法箭
            }
            isFly = true;
        } else if ((this._weaponType == 62) && (this._sting != null)) { // 铁手甲 -
                                                                        // 有飞刀
            this._pc.getInventory().removeItem(this._sting, 1);
            this._attckGrfxId = 2989; // 飞刀
            isFly = true;
        }
        // 判断玩家是否发送miss包
        if (!this._isHit) { // Miss
            this._damage = 0; // 伤害设0, 无受伤动作出现
        }

        int[] data = null; // 攻击封包的参数

        if (isFly) { // 远距离攻击
            data = new int[] { this._attckActId, this._damage,
                    this._attckGrfxId }; // 参数
            this._pc.sendPackets(new S_UseArrowSkill(this._pc, this._targetId,
                    this._targetX, this._targetY, data));
            this._pc.broadcastPacket(new S_UseArrowSkill(this._pc,
                    this._targetId, this._targetX, this._targetY, data));
        } else { // 近距离攻击
            data = new int[] { this._attckActId, this._damage, this._effectId }; // 参数
            this._pc.sendPackets(new S_AttackPacket(this._pc, this._targetId,
                    data)); // 对自身送出
            this._pc.broadcastPacket(new S_AttackPacket(this._pc,
                    this._targetId, data)); // 对非自身送出
        }

        if (this._isHit) {
            this._target.broadcastPacketExceptTargetSight(new S_DoActionGFX(
                    this._targetId, ActionCodes.ACTION_Damage), this._pc);
        }
    }

    /**
     * ■■■■ 增加底比斯武器的付加攻击 ■■■■
     */
    public void addChaserAttack() {
        final int random = Random.nextInt(100) + 1;
        if (5 > random) {
            switch (this._weaponId) {
                case 265: // 底比斯欧西里斯双刀
                case 266: // 底比斯欧西里斯双手剑
                case 267: // 底比斯欧西里斯弓
                case 268: // 底比斯欧西里斯魔杖
                case 280: // 艾尔摩锁链剑
                case 281: // 艾尔摩魔杖
                    // 地属性
                    final L1Chaser chaser0 = new L1Chaser(this._pc,
                            this._target, L1Skills.ATTR_EARTH, 7025);
                    chaser0.begin();
                    break;

                case 276: // 库库尔坎之矛
                case 277: // 库库尔坎之铁手甲
                    // 水属性
                    final L1Chaser chaser1 = new L1Chaser(this._pc,
                            this._target, L1Skills.ATTR_WATER, 7179);
                    chaser1.begin();
                    break;

                case 304: // 埋藏的魔族剑
                case 307: // 埋藏的魔族钢爪
                case 308: // 埋藏的魔族链锁剑
                    // 水属性
                    final L1Chaser chaser2 = new L1Chaser(this._pc,
                            this._target, L1Skills.ATTR_WATER, 8150);
                    chaser2.begin();
                    break;

                case 305: // 埋藏的魔族弓
                case 306: // 埋藏的魔族杖
                case 309: // 埋藏的魔族奇古兽
                    // 水属性
                    final L1Chaser chaser3 = new L1Chaser(this._pc,
                            this._target, L1Skills.ATTR_WATER, 8152);
                    chaser3.begin();
                    break;
            }
        }
    }

    /* ■■■■■■■■■■■■■■■■ 命中判定 ■■■■■■■■■■■■■■■■ */

    /**
     * ●●●● 增加ＮＰＣ的附毒攻击 ●●●●
     * 
     * @param attacker
     *            攻击方
     * @param target
     *            目标
     */
    private void addNpcPoisonAttack(final L1Character attacker,
            final L1Character target) {
        if (this._npc.getNpcTemplate().get_poisonatk() != 0) { // 附毒攻击
            if (15 >= Random.nextInt(100) + 1) { // 15%的几率附毒攻击
                final int poison = this._npc.getNpcTemplate().get_poisonatk();
                switch (poison) {
                    case 1: // 通常毒
                        // 3秒周期、伤害HP-5
                        L1DamagePoison.doInfection(attacker, target, 3000, 5);
                        break;

                    case 2: // 沉默毒
                        L1SilencePoison.doInfection(target);
                        break;

                    case 4: // 麻痹毒
                        // 20秒后至45秒间麻痹
                        L1ParalysisPoison.doInfection(target, 20000, 45000);
                        break;
                }
            }
        } else if (this._npc.getNpcTemplate().get_paralysisatk() != 0) { // 随着麻痹攻击
        }
    }

    /**
     * ■■■■ 增加ＰＣ的附毒攻击 ■■■■
     * 
     * @param attacker
     *            攻击方
     * @param target
     *            目标
     */
    public void addPcPoisonAttack(final L1Character attacker,
            final L1Character target) {
        final int chance = Random.nextInt(100) + 1;
        if (((this._weaponId == 13 // 死亡之指
                )
                || (this._weaponId == 44 // 古代黑暗妖精之剑
                ) || ((this._weaponId != 0) && this._pc
                .hasSkillEffect(ENCHANT_VENOM))) // 附加剧毒
                && (chance <= 10)) {
            // 通常毒、3秒周期、伤害HP-5
            L1DamagePoison.doInfection(attacker, target, 3000, 5);
        } else {
            // 魔法娃娃效果 - 中毒
            if (L1MagicDoll.getEffectByDoll(attacker, (byte) 1) == 1) {
                L1DamagePoison.doInfection(attacker, target, 3000, 5);
            }
        }
    }

    /**
     * ●●●● 属性强化卷追加伤害 ●●●●
     * 
     * @return
     */
    private int calcAttrEnchantDmg() {
        int damage = 0;
        switch (this._weaponAttrEnchantLevel) {
            case 1: // 一段
                damage = 1;
                break;

            case 2: // 二段
                damage = 3;
                break;

            case 3: // 三段
                damage = 5;
                break;
        }

        // 抗性处理
        int resist = 0;
        switch (this._calcType) {
            case PC_PC:
                switch (this._weaponAttrEnchantKind) {
                    case 1: // 地
                        resist = this._targetPc.getEarth();
                        break;

                    case 2: // 火
                        resist = this._targetPc.getFire();
                        break;

                    case 4: // 水
                        resist = this._targetPc.getWater();
                        break;

                    case 8: // 风
                        resist = this._targetPc.getWind();
                        break;
                }
                break;

            case PC_NPC:
                final int weakAttr = this._targetNpc.getNpcTemplate()
                        .get_weakAttr();
                if (((this._weaponAttrEnchantKind == 1) && (weakAttr == 1)) // 地
                        || ((this._weaponAttrEnchantKind == 2) && (weakAttr == 2)) // 火
                        || ((this._weaponAttrEnchantKind == 4) && (weakAttr == 4)) // 水
                        || ((this._weaponAttrEnchantKind == 8) && (weakAttr == 8))) { // 风
                    resist = -50;
                }
                break;
        }

        int resistFloor = (int) (0.32 * Math.abs(resist));
        if (resist >= 0) {
            resistFloor *= 1;
        } else {
            resistFloor *= -1;
        }

        final double attrDeffence = resistFloor / 32.0;
        final double attrCoefficient = 1 - attrDeffence;

        damage *= attrCoefficient;

        return damage;
    }

    /**
     * ●●●● 强化魔法近战用 ●●●●
     * 
     * @param dmg
     *            伤害值
     * @return
     */
    private double calcBuffDamage(double dmg) {
        // 属性之火、燃烧斗志1.5倍伤害
        if (this._pc.hasSkillEffect(BURNING_SPIRIT) // 燃烧斗志
                || this._pc.hasSkillEffect(ELEMENTAL_FIRE)) { // 属性之火
            if ((Random.nextInt(100) + 1) <= 33) {
                double tempDmg = dmg;
                if (this._pc.hasSkillEffect(FIRE_WEAPON)) { // 火焰武器
                    tempDmg -= 4;
                } else if (this._pc.hasSkillEffect(FIRE_BLESS)) { // 烈炎气息
                    tempDmg -= 5;
                } else if (this._pc.hasSkillEffect(BURNING_WEAPON)) { // 烈炎武器
                    tempDmg -= 6;
                }
                if (this._pc.hasSkillEffect(BERSERKERS)) { // 狂暴术
                    tempDmg -= 5;
                }
                final double diffDmg = dmg - tempDmg;
                dmg = tempDmg * 1.5 + diffDmg;
            }
        }

        // 锁链剑
        if (this._weaponType2 == 18) {
            // 弱点曝光 - 伤害加成
            if (this._pc.hasSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV3)) {
                dmg += 60; // 物理攻击力+60
            } else if (this._pc.hasSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV2)) {
                dmg += 40; // 物理攻击力+40
            } else if (this._pc.hasSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV1)) {
                dmg += 20; // 物理攻击力+20
            }
        }

        // 屠宰者 & 弱点曝光LV3 - 伤害 *1.3
        if (this._pc.isFoeSlayer()
                && this._pc.hasSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV3)) {
            dmg *= 1.3;
        }

        // 燃烧击砍
        if (this._pc.hasSkillEffect(BURNING_SLASH)) {
            dmg += 7; // 攻击时让敌人受到额外伤害7点
            this._pc.sendPackets(new S_EffectLocation(this._targetX,
                    this._targetY, 6591));
            this._pc.broadcastPacket(new S_EffectLocation(this._targetX,
                    this._targetY, 6591));
            this._pc.killSkillEffectTimer(BURNING_SLASH);
        }

        return dmg;
    }

    /**
     * ●●●● 反击屏障伤害 ●●●●
     * 
     * @return
     */
    private int calcCounterBarrierDamage() {
        int damage = 0;
        L1ItemInstance weapon = null;
        weapon = this._targetPc.getWeapon();
        if (weapon != null) {
            if (weapon.getItem().getType() == 3) { // 双手剑
                // (BIG最大伤害+强化数+额外伤害)*2
                damage = (weapon.getItem().getDmgLarge()
                        + weapon.getEnchantLevel() + weapon.getItem()
                        .getDmgModifier()) * 2;
            }
        }
        return damage;
    }

    /**
     * 检查伤害
     */
    public int calcDamage() {
        switch (this._calcType) {
            case PC_PC:
                this._damage = this.calcPcPcDamage();
                break;

            case PC_NPC:
                this._damage = this.calcPcNpcDamage();
                break;

            case NPC_PC:
                this._damage = this.calcNpcPcDamage();
                break;

            case NPC_NPC:
                this._damage = this.calcNpcNpcDamage();
                break;
        }
        return this._damage;
    }

    /**
     * ■■■■ 毁灭巨剑的HP吸收量 ■■■■
     * 
     * @param dmg
     *            伤害值
     * @return
     */
    private int calcDestruction(final int dmg) {
        this._drainHp = (dmg / 8) + 1;
        return this._drainHp > 0 ? this._drainHp : 1;
    }

    /**
     * ●●●● ＥＲ决定回避率 ●●●●
     * 
     * @return
     */
    private boolean calcErEvasion() {
        final int er = this._targetPc.getEr();

        final int rnd = Random.nextInt(100) + 1;
        return er < rnd;
    }

    /**
     * 检查命中
     * 
     * @return
     */
    public boolean calcHit() {
        // 检查无敌状态
        for (final int skillId : INVINCIBLE) {
            if (this._target.hasSkillEffect(skillId)) {
                this._isHit = false;
                return this._isHit;
            }
        }

        switch (this._calcType) {
            case PC_PC:
            case PC_NPC:
                if (this._weaponRange != -1) {
                    if (this._pc.getLocation().getTileLineDistance(
                            this._target.getLocation()) > this._weaponRange + 1) { // 当前所在位置
                                                                                   // 武器的射程范围+1
                        this._isHit = false; // 射程范围外
                        return this._isHit;
                    }
                } else {
                    if (!this._pc.getLocation().isInScreen(
                            this._target.getLocation())) {
                        this._isHit = false; // 射程范围外
                        return this._isHit;
                    }
                }
                if ((this._weaponType == 20) && (this._weaponId != 190)
                        && (this._arrow == null)) {
                    this._isHit = false; // 没有箭
                } else if ((this._weaponType == 62) && (this._sting == null)) {
                    this._isHit = false; // 没有飞刀
                } else if ((this._weaponRange != 1)
                        && !this._pc.glanceCheck(this._targetX, this._targetY)) {
                    this._isHit = false; // 两格以上武器 直线距离上有障碍物
                } else if ((this._weaponId == 247) || (this._weaponId == 248)
                        || (this._weaponId == 249)) {
                    this._isHit = false; // 试练之剑B～C 攻击无效
                } else if (this._calcType == PC_PC) {
                    this._isHit = this.calcPcPcHit();
                } else if (this._calcType == PC_NPC) {
                    this._isHit = this.calcPcNpcHit();
                }
                break;

            case NPC_PC:
                this._isHit = this.calcNpcPcHit();
                break;

            case NPC_NPC:
                this._isHit = this.calcNpcNpcHit();
                break;
        }
        return this._isHit;
    }

    /* ■■■■■■■■■■■■■■■ 伤害算出 ■■■■■■■■■■■■■■■ */

    /**
     * ●●●● 武器的材质(祝福等)追加的额外伤害 ●●●●
     * 
     * @return
     */
    private int calcMaterialBlessDmg() {
        int damage = 0;
        final int undead = this._targetNpc.getNpcTemplate().get_undead();
        switch (this._weaponMaterial) {
            case 14: // 银质,米索莉,黑色米索莉 武器对不死系怪物的随机伤害 (1-20)
            case 17:
            case 22:
                if ((undead == 1) || (undead == 3) || (undead == 5)) {
                    damage += Random.nextInt(20) + 1;
                }
                break;
        }

        // 米索莉・黑色米索莉、恶魔系 (1-3)
        if (((this._weaponMaterial == 17) || (this._weaponMaterial == 22))
                && (undead == 2)) {
            damage += Random.nextInt(3) + 1;
        }

        // 祝福武器、・恶魔系・不死系 (1-4)
        if ((this._weaponBless == 0)
                && ((undead == 1) || (undead == 2) || (undead == 3))) {
            damage += Random.nextInt(4) + 1;
        }
        if ((this._pc.getWeapon() != null) && (this._weaponType != 20)
                && (this._weaponType != 62)
                && (this.weapon.getHolyDmgByMagic() != 0)
                && ((undead == 1) || (undead == 3))) {
            damage += this.weapon.getHolyDmgByMagic();
        }
        return damage;
    }

    /**
     * ●●●● NPC 的伤害减免 ●●●●
     * 
     * @return
     */
    private int calcNpcDamageReduction() {
        return this._targetNpc.getNpcTemplate().get_damagereduction();
    }

    /**
     * ●●●● NPC 对 NPC 的伤害 ●●●●
     * 
     * @return
     */
    private int calcNpcNpcDamage() {
        final int lvl = this._npc.getLevel();
        double dmg = 0;

        if (this._npc instanceof L1PetInstance) {
            dmg = Random.nextInt(this._npc.getNpcTemplate().get_level())
                    + this._npc.getStr() / 2 + 1;
            dmg += (lvl / 16); // 宠物每LV16追加打击
            dmg += ((L1PetInstance) this._npc).getDamageByWeapon();
        } else {
            dmg = Random.nextInt(lvl) + this._npc.getStr() / 2 + 1;
        }

        if (this.isUndeadDamage()) {
            dmg *= 1.1;
        }

        dmg = dmg * this.getLeverage() / 10;

        dmg -= this.calcNpcDamageReduction();

        if (this._npc.isWeaponBreaked()) { // ＮＰＣ武器损坏中。
            dmg /= 2;
        }

        this.addNpcPoisonAttack(this._npc, this._targetNpc);

        // 吉尔塔斯反击屏障伤害判断 (NPC_NPC)
        if (this._targetNpc.getHiddenStatus() == L1NpcInstance.HIDDEN_STATUS_COUNTERATTACK_BARRIER) {
            this._npc.broadcastPacket(new S_DoActionGFX(this._npc.getId(),
                    ActionCodes.ACTION_Damage));
            this._npc.receiveDamage(this._targetNpc, (int) (dmg * 2));
            dmg = 0;
        }

        if (dmg <= 0) {
            this._isHit = false;
        }

        return (int) dmg;
    }

    /**
     * ●●●● NPC 对 NPC 的命中判定 ●●●●
     */
    private boolean calcNpcNpcHit() {

        this._hitRate += this._npc.getLevel();

        if (this._npc instanceof L1PetInstance) { // 宠物的武器追加命中
            this._hitRate += ((L1PetInstance) this._npc).getHitByWeapon();
        }

        this._hitRate += this._npc.getHitup();

        int attackerDice = Random.nextInt(20) + 1 + this._hitRate - 1;

        // 闪避率
        attackerDice -= this._targetNpc.getDodge();
        attackerDice += this._targetNpc.getNdodge();

        int defenderDice = 0;

        final int defenderValue = (this._targetNpc.getAc()) * -1;

        if (this._targetNpc.getAc() >= 0) {
            defenderDice = 10 - this._targetNpc.getAc();
        } else if (this._targetNpc.getAc() < 0) {
            defenderDice = 10 + Random.nextInt(defenderValue) + 1;
        }

        final int fumble = this._hitRate;
        final int critical = this._hitRate + 19;

        if (attackerDice <= fumble) {
            this._hitRate = 0;
        } else if (attackerDice >= critical) {
            this._hitRate = 100;
        } else {
            if (attackerDice > defenderDice) {
                this._hitRate = 100;
            } else if (attackerDice <= defenderDice) {
                this._hitRate = 0;
            }
        }
        if (((this._npc instanceof L1PetInstance) || (this._npc instanceof L1SummonInstance))
                && ((this._targetNpc instanceof L1PetInstance) || (this._targetNpc instanceof L1SummonInstance))) {
            // 目标在安区、攻击者在安区、NOPVP
            if ((this._targetNpc.getZoneType() == 1)
                    || (this._npc.getZoneType() == 1)) {
                this._hitRate = 0;
            }
        }

        final int rnd = Random.nextInt(100) + 1;
        return this._hitRate >= rnd;
    }

    /**
     * ●●●● NPC 对 PC 的伤害 ●●●●
     * 
     * @return
     */
    private int calcNpcPcDamage() {
        final int lvl = this._npc.getLevel();
        double dmg = 0D;
        if (lvl < 10) {
            dmg = Random.nextInt(lvl) + 10D + this._npc.getStr() / 2 + 1;
        } else {
            dmg = Random.nextInt(lvl) + this._npc.getStr() / 2 + 1;
        }

        if (this._npc instanceof L1PetInstance) {
            dmg += (lvl / 16); // 宠物每LV16追加打击
            dmg += ((L1PetInstance) this._npc).getDamageByWeapon();
        }

        dmg += this._npc.getDmgup();

        if (this.isUndeadDamage()) {
            dmg *= 1.1;
        }

        dmg = dmg * this.getLeverage() / 10;

        dmg -= this.calcPcDefense();

        if (this._npc.isWeaponBreaked()) { // ＮＰＣ的武器损坏中。
            dmg /= 2;
        }

        dmg -= this._targetPc.getDamageReductionByArmor(); // 防具伤害减免

        // 魔法娃娃效果 - 伤害减免
        dmg -= L1MagicDoll.getDamageReductionByDoll(this._targetPc);

        // 新水龙装备魔法效果(法利昂的治愈结界)
        if ((this._targetPc.getInventory().checkEquipped(21119)) // 法利昂的力量
                || (this._targetPc.getInventory().checkEquipped(21120)) // 法利昂的魅惑
                || (this._targetPc.getInventory().checkEquipped(21121)) // 法利昂的泉源
                || (this._targetPc.getInventory().checkEquipped(21122)) // 法利昂的霸气
        ) {
            final int random = (Random.nextInt(100) + 1);
            if (!this._targetPc.isDead() && (random <= 4)) { // 没有死亡
                final int randomHp = (Random.nextInt(14) + 1);
                final int healHp = 72 + randomHp;
                this._targetPc.setCurrentHp(this._targetPc.getCurrentHp()
                        + healHp);
                this._targetPc.sendPackets(new S_SkillSound(this._targetPc
                        .getId(), 2187)); // 特效
                this._targetPc.broadcastPacket(new S_SkillSound(this._targetPc
                        .getId(), 2187));
                // pc.sendPackets(new S_SkillIconGFX(75, 8));
                this._targetPc.sendPackets(new S_ServerMessage(77)); // \f1你觉得舒服多了。
            }
        }

        // 特别的料理伤害减免
        if (this._targetPc.hasSkillEffect(COOKING_1_0_S) //
                || this._targetPc.hasSkillEffect(COOKING_1_1_S) //
                || this._targetPc.hasSkillEffect(COOKING_1_2_S) //
                || this._targetPc.hasSkillEffect(COOKING_1_3_S) //
                || this._targetPc.hasSkillEffect(COOKING_1_4_S) //
                || this._targetPc.hasSkillEffect(COOKING_1_5_S) //
                || this._targetPc.hasSkillEffect(COOKING_1_6_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_0_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_1_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_2_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_3_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_4_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_5_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_6_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_0_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_1_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_2_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_3_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_4_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_5_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_6_S) //
                || this._targetPc.hasSkillEffect(COOKING_1_7_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_7_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_7_S) //
        ) {
            dmg -= 5;
        }

        // 增幅防御伤害减免
        if (this._targetPc.hasSkillEffect(REDUCTION_ARMOR)) {
            int targetPcLvl = this._targetPc.getLevel();
            if (targetPcLvl < 50) {
                targetPcLvl = 50;
            }
            dmg -= (targetPcLvl - 50) / 5 + 1;
        }

        // 龙之护铠伤害减免
        if (this._targetPc.hasSkillEffect(DRAGON_SKIN)) {
            dmg -= 3; // 伤害减免+3 (NPC_PC)
        }

        // 耐力伤害减免
        if (this._targetPc.hasSkillEffect(PATIENCE)) {
            dmg -= 2;
        }

        // 圣结界伤害减半
        if (this._targetPc.hasSkillEffect(IMMUNE_TO_HARM)) {
            dmg /= 2;
        }

        // 非攻城区域宠物、召唤兽对玩家伤害减少
        boolean isNowWar = false;
        final int castleId = L1CastleLocation.getCastleIdByArea(this._targetPc);
        if (castleId > 0) {
            isNowWar = WarTimeController.getInstance().isNowWar(castleId);
        }
        if (!isNowWar) {
            if (this._npc instanceof L1PetInstance) {
                dmg /= 8;
            } else if (this._npc instanceof L1SummonInstance) {
                final L1SummonInstance summon = (L1SummonInstance) this._npc;
                if (summon.isExsistMaster()) {
                    dmg /= 8;
                }
            }
        }

        if (dmg <= 0) {
            this._isHit = false;
        }

        this.addNpcPoisonAttack(this._npc, this._targetPc);

        return (int) dmg;
    }

    /**
     * ●●●● NPC 对 PC 的命中判定 ●●●●
     * 
     * @return
     */
    private boolean calcNpcPcHit() {

        this._hitRate += this._npc.getLevel();

        if (this._npc instanceof L1PetInstance) { // 宠物的武器追加命中
            this._hitRate += ((L1PetInstance) this._npc).getHitByWeapon();
        }

        this._hitRate += this._npc.getHitup();

        int attackerDice = Random.nextInt(20) + 1 + this._hitRate - 1;

        // 闪避率
        attackerDice -= this._targetPc.getDodge();
        attackerDice += this._targetPc.getNdodge();

        int defenderDice = 0;

        final int defenderValue = (this._targetPc.getAc()) * -1;

        if (this._targetPc.getAc() >= 0) {
            defenderDice = 10 - this._targetPc.getAc();
        } else if (this._targetPc.getAc() < 0) {
            defenderDice = 10 + Random.nextInt(defenderValue) + 1;
        }

        final int fumble = this._hitRate;
        final int critical = this._hitRate + 19;

        if (attackerDice <= fumble) {
            this._hitRate = 0;
        } else if (attackerDice >= critical) {
            this._hitRate = 100;
        } else {
            if (attackerDice > defenderDice) {
                this._hitRate = 100;
            } else if (attackerDice <= defenderDice) {
                this._hitRate = 0;
            }
        }

        if ((this._npc instanceof L1PetInstance)
                || (this._npc instanceof L1SummonInstance)) {
            // 目标在安区、攻击者在安区、NOPVP
            if ((this._targetPc.getZoneType() == 1)
                    || (this._npc.getZoneType() == 1)
                    || (this._targetPc.checkNonPvP(this._targetPc, this._npc))) {
                this._hitRate = 0;
            }
        }
        // TODO 魔法娃娃效果 - 伤害回避
        else if (L1MagicDoll.getDamageEvasionByDoll(this._targetPc) > 0) {
            this._hitRate = 0;
        }

        final int rnd = Random.nextInt(100) + 1;

        // NPC的攻击范围10以上的场合、2以上距离的场合视为弓攻击
        if ((this._npc.getAtkRanged() >= 10)
                && (this._hitRate > rnd)
                && (this._npc.getLocation().getTileLineDistance(
                        new Point(this._targetX, this._targetY)) >= 2)) {
            return this.calcErEvasion();
        }
        return this._hitRate >= rnd;
    }

    /**
     * ●●●● 角色防御的伤害减免 ●●●●
     * 
     * @return
     */
    private int calcPcDefense() {
        final int ac = Math.max(0, 10 - this._targetPc.getAc());
        final int acDefMax = this._targetPc.getClassFeature().getAcDefenseMax(
                ac);
        return Random.nextInt(acDefMax + 1);
    }

    /**
     * ●●●● PC 对 NPC 的伤害 ●●●●
     * 
     * @return
     */
    private int calcPcNpcDamage() {
        int weaponMaxDamage = 0;
        if (this._targetNpc.getNpcTemplate().get_size()
                .equalsIgnoreCase("small")
                && (this._weaponSmall > 0)) {
            weaponMaxDamage = this._weaponSmall;
        } else if (this._targetNpc.getNpcTemplate().get_size()
                .equalsIgnoreCase("large")
                && (this._weaponLarge > 0)) {
            weaponMaxDamage = this._weaponLarge;
        }

        // 计算武器总伤害
        int weaponTotalDamage = this.calcWeponDamage(weaponMaxDamage);

        if ((this._weaponId == 262) && (Random.nextInt(100) + 1 <= 75)) { // 装备毁灭巨剑的成功确率(暂定)75%
            weaponTotalDamage += this.calcDestruction(weaponTotalDamage);
        }

        // 计算伤害 远程 或 近战武器 及buff
        double dmg = weaponTotalDamage + this._statusDamage;
        if ((this._weaponType == 20) || (this._weaponType == 62)) {
            dmg = this.calLongRageDamage(dmg);
        } else {
            dmg = this.calShortRageDamage(dmg);
        }

        switch (this._weaponId) {
            case 124: // 巴风特与耀武类武器 (地裂魔法)
            case 289:
            case 290:
            case 291:
            case 292:
            case 293:
            case 294:
            case 295:
            case 296:
            case 297:
            case 298:
            case 299:
            case 300:
            case 301:
            case 302:
            case 303:
                dmg += L1WeaponSkill.getBaphometStaffDamage(this._pc,
                        this._target);
                break;

            case 2: // 骰子匕首 (当前HP 2/3)
            case 200002:
                dmg += L1WeaponSkill.getDiceDaggerDamage(this._pc,
                        this._targetNpc, this.weapon);
                break;

            case 204: // 深红之弩 (束缚术)
            case 100204:
                L1WeaponSkill.giveFettersEffect(this._pc, this._targetNpc);
                break;

            case 264: // 雷雨之剑,天雷剑 (极道落雷)
            case 288:
                dmg += L1WeaponSkill.getLightningEdgeDamage(this._pc,
                        this._target);
                break;

            case 260: // 暴风之斧,酷寒之矛,玄冰弓 (范围攻击)
            case 263:
            case 287:
                dmg += L1WeaponSkill.getAreaSkillWeaponDamage(this._pc,
                        this._target, this._weaponId);
                break;

            case 261: // 大法师之杖 (疾病术)
                L1WeaponSkill.giveArkMageDiseaseEffect(this._pc, this._target);
                break;

            default:
                dmg += L1WeaponSkill.getWeaponSkillDamage(this._pc,
                        this._target, this._weaponId);
                break;
        }

        dmg -= this.calcNpcDamageReduction();

        switch (this._skillId) {
            case SMASH: // 使用暴击增加15点伤害，而奇古兽固定15点伤害
                dmg += 15;
                if ((this._weaponType2 == 17) || (this._weaponType2 == 19)) {
                    dmg = 15;
                }
                break;

            case BONE_BREAK: // 使用骷髅毁坏增加10点伤害，而奇古兽固定10点伤害
                dmg += 10;
                if ((this._weaponType2 == 17) || (this._weaponType2 == 19)) {
                    dmg = 10;
                }
                // 再次发动判断
                if (!this._targetNpc.hasSkillEffect(BONE_BREAK)) {
                    final int change = Random.nextInt(100) + 1;
                    if (change < (30 + Random.nextInt(11))) { // 30 ~ 40%
                        L1EffectSpawn.getInstance().spawnEffect(93001, 1700,
                                this._targetNpc.getX(), this._targetNpc.getY(),
                                this._targetNpc.getMapId());
                        this._targetNpc.setSkillEffect(BONE_BREAK, 2 * 1000); // 发动后再次发动间隔
                                                                              // (2秒)
                        this._targetNpc.setSkillEffect(BONE_BREAK_START, 700);
                    }
                }
                break;
        }

        // 非攻城区域对宠物、召唤兽伤害减少
        boolean isNowWar = false;
        final int castleId = L1CastleLocation
                .getCastleIdByArea(this._targetNpc);
        if (castleId > 0) {
            isNowWar = WarTimeController.getInstance().isNowWar(castleId);
        }
        if (!isNowWar) {
            if (this._targetNpc instanceof L1PetInstance) {
                dmg /= 8;
            } else if (this._targetNpc instanceof L1SummonInstance) {
                final L1SummonInstance summon = (L1SummonInstance) this._targetNpc;
                if (summon.isExsistMaster()) {
                    dmg /= 8;
                }
            }
        }

        // 吉尔塔斯反击屏障伤害判断 (PC_NPC)
        if (this._targetNpc.getHiddenStatus() == L1NpcInstance.HIDDEN_STATUS_COUNTERATTACK_BARRIER) {
            this._pc.broadcastPacket(new S_DoActionGFX(this._pc.getId(),
                    ActionCodes.ACTION_Damage));
            this._pc.receiveDamage(this._targetNpc, (int) (dmg * 2), true);
            dmg = 0;
        }

        if (dmg <= 0) {
            this._isHit = false;
            this._drainHp = 0; // 没有伤害 不能吸取体力
        }

        return (int) dmg;
    }

    /**
     * ●●●● PC 对 NPC 的命中判定 ●●●●
     * 
     * @return
     */
    private boolean calcPcNpcHit() {
        // ＮＰＣ的命中率
        // ＝（PC的Lv＋クラス补正＋STR补正＋DEX补正＋武器补正＋DAIの枚数/2＋魔法补正）×5－{NPCのAC×（-5）}
        this._hitRate = this._pc.getLevel();

        if (this._pc.getStr() > 59) {
            this._hitRate += strHit[58];
        } else {
            this._hitRate += strHit[this._pc.getStr() - 1];
        }

        if (this._pc.getDex() > 60) {
            this._hitRate += dexHit[59];
        } else {
            this._hitRate += dexHit[this._pc.getDex() - 1];
        }

        // 命中计算 与魔法、食物buff
        this._hitRate += this._weaponAddHit + (this._weaponEnchant / 2);
        if ((this._weaponType == 20) || (this._weaponType == 62)) {
            this._hitRate = this.calLongRageHit(this._hitRate);
        } else {
            this._hitRate = this.calShortRageHit(this._hitRate);
        }

        // 负重命中补正
        final int weight = this._pc.getInventory().getWeight242();
        if ((80 < weight) && (121 >= weight)) {
            this._hitRate -= 1;
        } else if ((122 <= weight) && (160 >= weight)) {
            this._hitRate -= 3;
        } else if ((161 <= weight) && (200 >= weight)) {
            this._hitRate -= 5;
        }

        int attackerDice = Random.nextInt(20) + 1 + this._hitRate - 10;

        // 闪避率
        attackerDice -= this._targetNpc.getDodge();
        attackerDice += this._targetNpc.getNdodge();

        final int defenderDice = 10 - this._targetNpc.getAc();

        final int fumble = this._hitRate - 9;
        final int critical = this._hitRate + 10;

        if (attackerDice <= fumble) {
            this._hitRate = 0;
        } else if (attackerDice >= critical) {
            this._hitRate = 100;
        } else {
            if (attackerDice > defenderDice) {
                this._hitRate = 100;
            } else if (attackerDice <= defenderDice) {
                this._hitRate = 0;
            }
        }

        // 奇古兽 命中率 100%
        if ((this._weaponType2 == 17) || (this._weaponType2 == 19)) {
            this._hitRate = 100;
        }

        // 特定状态下才可攻击 NPC
        if (this._pc.isAttackMiss(this._pc, this._targetNpc.getNpcTemplate()
                .get_npcId())) {
            this._hitRate = 0;
        }

        final int rnd = Random.nextInt(100) + 1;

        return this._hitRate >= rnd;
    }

    /**
     * ●●●● PC 对 PC 的伤害算出 ●●●●
     * 
     * @return
     */
    public int calcPcPcDamage() {
        // 计算武器总伤害
        int weaponTotalDamage = this.calcWeponDamage(this._weaponSmall);

        if ((this._weaponId == 262) && (Random.nextInt(100) + 1 <= 75)) { // 装备毁灭巨剑的成功确率(暂定)75%
            weaponTotalDamage += this.calcDestruction(weaponTotalDamage);
        }

        // 计算 远程 或 近战武器 伤害 与魔法、食物buff
        double dmg = weaponTotalDamage + this._statusDamage;
        if ((this._weaponType == 20) || (this._weaponType == 62)) {
            dmg = this.calLongRageDamage(dmg);
        } else {
            dmg = this.calShortRageDamage(dmg);
        }

        switch (this._weaponId) {
            case 124: // 巴风特魔杖 (地裂魔法)
            case 289: // 耀武短剑 (地裂魔法)
            case 290: // 耀武双手剑 (地裂魔法)
            case 291: // 耀武双刀 (地裂魔法)
            case 292: // 耀武之弩 (地裂魔法)
            case 293: // 耀武魔杖 (地裂魔法)
            case 294: // 特制的耀武短剑 (地裂魔法)
            case 295: // 特制的耀武双手剑 (地裂魔法)
            case 296: // 特制的耀武双刀 (地裂魔法)
            case 297: // 特制的耀武之弩 (地裂魔法)
            case 298: // 特制的耀武魔杖 (地裂魔法)
            case 299: // 超特制的耀武短剑 (地裂魔法)
            case 300: // 超特制的耀武双手剑 (地裂魔法)
            case 301: // 超特制的耀武双刀 (地裂魔法)
            case 302: // 超特制的耀武之弩 (地裂魔法)
            case 303: // 超特制的耀武魔杖 (地裂魔法)
                dmg += L1WeaponSkill.getBaphometStaffDamage(this._pc,
                        this._target);
                break;

            case 2: // 骰子匕首 (当前HP 2/3)
            case 200002:
                dmg += L1WeaponSkill.getDiceDaggerDamage(this._pc,
                        this._targetPc, this.weapon);
                break;

            case 204: // 深红之弩 (束缚术)
            case 100204:
                L1WeaponSkill.giveFettersEffect(this._pc, this._targetPc);
                break;

            case 264: // 雷雨之剑,天雷剑 (极道落雷)
            case 288:
                dmg += L1WeaponSkill.getLightningEdgeDamage(this._pc,
                        this._target);
                break;

            case 260: // 暴风之斧 (范围攻击)
            case 263: // 酷寒之矛 (范围攻击)
            case 287: // 玄冰弓 (范围攻击)
                dmg += L1WeaponSkill.getAreaSkillWeaponDamage(this._pc,
                        this._target, this._weaponId);
                break;

            case 261: // 大法师之杖 (疾病术)
                L1WeaponSkill.giveArkMageDiseaseEffect(this._pc, this._target);
                break;

            default:
                dmg += L1WeaponSkill.getWeaponSkillDamage(this._pc,
                        this._target, this._weaponId);
                break;
        }

        dmg -= this._targetPc.getDamageReductionByArmor(); // 防具的伤害减免

        dmg -= L1MagicDoll.getDamageReductionByDoll(this._targetPc); // 魔法娃娃效果 -
                                                                     // 伤害减免

        // 新水龙装备魔法效果(法利昂的治愈结界)
        if ((this._targetPc.getInventory().checkEquipped(21119)) // 法利昂的力量
                || (this._targetPc.getInventory().checkEquipped(21120)) // 法利昂的魅惑
                || (this._targetPc.getInventory().checkEquipped(21121)) // 法利昂的泉源
                || (this._targetPc.getInventory().checkEquipped(21122)) // 法利昂的霸气
        ) {
            final int random = (Random.nextInt(100) + 1);
            if (!this._targetPc.isDead() && (random <= 4)) { // 没有死亡
                final int randomHp = (Random.nextInt(14) + 1);
                final int healHp = 72 + randomHp;
                this._targetPc.setCurrentHp(this._targetPc.getCurrentHp()
                        + healHp);
                this._targetPc.sendPackets(new S_SkillSound(this._targetPc
                        .getId(), 2187)); // 特效
                this._targetPc.broadcastPacket(new S_SkillSound(this._targetPc
                        .getId(), 2187));
                // pc.sendPackets(new S_SkillIconGFX(75, 8));
                this._targetPc.sendPackets(new S_ServerMessage(77)); // \f1你觉得舒服多了。
            }
        }

        // 特别的料理伤害减免
        if (this._targetPc.hasSkillEffect(COOKING_1_0_S) //
                || this._targetPc.hasSkillEffect(COOKING_1_1_S) //
                || this._targetPc.hasSkillEffect(COOKING_1_2_S) //
                || this._targetPc.hasSkillEffect(COOKING_1_3_S) //
                || this._targetPc.hasSkillEffect(COOKING_1_4_S) //
                || this._targetPc.hasSkillEffect(COOKING_1_5_S) //
                || this._targetPc.hasSkillEffect(COOKING_1_6_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_0_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_1_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_2_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_3_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_4_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_5_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_6_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_0_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_1_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_2_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_3_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_4_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_5_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_6_S) //
                || this._targetPc.hasSkillEffect(COOKING_1_7_S) //
                || this._targetPc.hasSkillEffect(COOKING_2_7_S) //
                || this._targetPc.hasSkillEffect(COOKING_3_7_S) //
        ) {
            dmg -= 5;
        }

        // 增幅防御伤害减免
        if (this._targetPc.hasSkillEffect(REDUCTION_ARMOR)) {
            int targetPcLvl = this._targetPc.getLevel();
            if (targetPcLvl < 50) {
                targetPcLvl = 50;
            }
            dmg -= (targetPcLvl - 50) / 5 + 1;
        }

        // 龙之护铠伤害减免
        if (this._targetPc.hasSkillEffect(DRAGON_SKIN)
                || this._targetPc.hasSkillEffect(PATIENCE)) {
            dmg -= 3; // 伤害减免+3 (PC_PC)
        }

        // 圣结界伤害减半
        if (this._targetPc.hasSkillEffect(IMMUNE_TO_HARM)) {
            dmg /= 2;
        }

        switch (this._skillId) {
            case SMASH: // 使用暴击增加15点伤害，而奇古兽固定15点伤害
                dmg += 15;
                if ((this._weaponType2 == 17) || (this._weaponType2 == 19)) {
                    dmg = 15;
                }
                break;

            case BONE_BREAK: // 使用骷髅毁坏增加10点伤害，而奇古兽固定10点伤害
                dmg += 10;
                if ((this._weaponType2 == 17) || (this._weaponType2 == 19)) {
                    dmg = 10;
                }
                // 再次发动判断
                if (!this._targetPc.hasSkillEffect(BONE_BREAK)) {
                    final int change = Random.nextInt(100) + 1;
                    if (change < (30 + Random.nextInt(11))) { // 30 ~ 40%
                        L1EffectSpawn.getInstance().spawnEffect(93001, 1700,
                                this._targetPc.getX(), this._targetPc.getY(),
                                this._targetPc.getMapId());
                        this._targetPc.setSkillEffect(BONE_BREAK, 2 * 1000); // 发动后再次发动间隔
                                                                             // (2秒)
                        this._targetPc.setSkillEffect(BONE_BREAK_START, 700);
                    }
                }
                break;
        }

        if (dmg <= 0) {
            this._isHit = false;
            this._drainHp = 0; // 没有伤害 不能吸取体力
        }

        return (int) dmg;
    }

    /**
     * ●●●● PC对PC的命中判定 ●●●● <br>
     * <br>
     * ＰＣへの命中率 ＝（PCのLv＋クラス补正＋STR补正＋DEX补正＋武器补正＋DAIの枚数/2＋魔法补正）×0.68－10<br>
     * これで算出された数值は自分が最大命中(95%)を与える事のできる相手侧PCのAC そこから相手侧PCのACが1良くなる每に自命中率から1引いていく<br>
     * 最小命中率5% 最大命中率95%
     * 
     * @return
     */
    private boolean calcPcPcHit() {
        this._hitRate = this._pc.getLevel();

        if (this._pc.getStr() > 59) {
            this._hitRate += strHit[58];
        } else {
            this._hitRate += strHit[this._pc.getStr() - 1];
        }

        if (this._pc.getDex() > 60) {
            this._hitRate += dexHit[59];
        } else {
            this._hitRate += dexHit[this._pc.getDex() - 1];
        }

        // 命中计算 与魔法、食物buff
        this._hitRate += this._weaponAddHit + (this._weaponEnchant / 2);
        if ((this._weaponType == 20) || (this._weaponType == 62)) {
            this._hitRate = this.calLongRageHit(this._hitRate);
        } else {
            this._hitRate = this.calShortRageHit(this._hitRate);
        }

        // 负重命中补正
        final int weight = this._pc.getInventory().getWeight242();
        if ((80 < weight) && (121 >= weight)) {
            this._hitRate -= 1;
        } else if ((122 <= weight) && (160 >= weight)) {
            this._hitRate -= 3;
        } else if ((161 <= weight) && (200 >= weight)) {
            this._hitRate -= 5;
        }

        int attackerDice = Random.nextInt(20) + 1 + this._hitRate - 10;

        // 闪避率
        attackerDice -= this._targetPc.getDodge();
        attackerDice += this._targetPc.getNdodge();

        int defenderDice = 0;

        final int defenderValue = (int) (this._targetPc.getAc() * 1.5) * -1;

        if (this._targetPc.getAc() >= 0) {
            defenderDice = 10 - this._targetPc.getAc();
        } else if (this._targetPc.getAc() < 0) {
            defenderDice = 10 + Random.nextInt(defenderValue) + 1;
        }

        final int fumble = this._hitRate - 9;
        final int critical = this._hitRate + 10;

        if (attackerDice <= fumble) {
            this._hitRate = 0;
        } else if (attackerDice >= critical) {
            this._hitRate = 100;
        } else {
            if (attackerDice > defenderDice) {
                this._hitRate = 100;
            } else if (attackerDice <= defenderDice) {
                this._hitRate = 0;
            }
        }

        // 奇古兽命中率100%
        if ((this._weaponType2 == 17) || (this._weaponType2 == 19)) {
            this._hitRate = 100;
        }

        // TODO 魔法娃娃效果 - 伤害回避
        else if (L1MagicDoll.getDamageEvasionByDoll(this._targetPc) > 0) {
            this._hitRate = 0;
        }

        final int rnd = Random.nextInt(100) + 1;
        if ((this._weaponType == 20) && (this._hitRate > rnd)) { // 弓の场合、ヒットした场合でもERでの回避を再度行う。
            return this.calcErEvasion();
        }

        return this._hitRate >= rnd;

    }

    /**
     * ■■■■ 玛那魔杖、钢铁玛那魔杖、魔力短剑的MP吸收量 ■■■■
     */
    public void calcStaffOfMana() {
        switch (this._weaponId) {
            case 126: // 玛那魔杖、钢铁玛那魔杖
            case 127:
                int som_lvl = this._weaponEnchant + 3; // 设定最大MP吸收量
                if (som_lvl < 0) {
                    som_lvl = 0;
                }
                // 获取随机的MP吸收量
                this._drainMana = Random.nextInt(som_lvl) + 1;
                // 吸收MP上限的最高限额为9
                if (this._drainMana > Config.MANA_DRAIN_LIMIT_PER_SOM_ATTACK) {
                    this._drainMana = Config.MANA_DRAIN_LIMIT_PER_SOM_ATTACK;
                }
                break;

            case 259: // 魔力短剑
                switch (this._calcType) {
                    case PC_PC:
                        if (this._targetPc.getMr() <= Random.nextInt(100) + 1) { // 确率取决于目标的MR
                            this._drainMana = 1; // 吸收量固定为1
                        }
                        break;

                    case PC_NPC:
                        if (this._targetNpc.getMr() <= Random.nextInt(100) + 1) { // 确率取决于目标的MR
                            this._drainMana = 1; // 吸收量固定为1
                        }
                        break;
                }
                break;
        }
    }

    /**
     * 检查武器伤害
     * 
     * @param weaponMaxDamage
     *            武器最高伤害值
     * @return
     */
    private int calcWeponDamage(final int weaponMaxDamage) {
        int weaponDamage = Random.nextInt(weaponMaxDamage) + 1;
        // 判断魔法辅助
        if (this._pc.hasSkillEffect(SOUL_OF_FLAME)) {
            weaponDamage = weaponMaxDamage;
        }

        // 判断武器类型
        boolean darkElfWeapon = false;
        if (this._pc.isDarkelf() && (this._weaponType == 58)) { // 钢爪
                                                                // (追加判断持有者为黑妖，避免与幻术师奇谷兽相冲)
            darkElfWeapon = true;
            if ((Random.nextInt(100) + 1) <= this._weaponDoubleDmgChance) { // 出现最大值的机率
                weaponDamage = weaponMaxDamage;
            }
            if (weaponDamage == weaponMaxDamage) { // 出现最大值时 - 爪痕
                this._effectId = 2;
            }
        } else if ((this._weaponType == 20) || (this._weaponType == 62)) {// 弓、铁手甲
                                                                          // 不算武器伤害
            weaponDamage = 0;
        }

        weaponDamage += this._weaponAddDmg + this._weaponEnchant; // 加上武器(额外点数+祝福魔法武器)跟武卷数

        if (this._calcType == PC_NPC) {
            weaponDamage += this.calcMaterialBlessDmg(); // 银祝福武器加伤害
        }
        if (this._weaponType == 54) {
            darkElfWeapon = true;
            if ((Random.nextInt(100) + 1) <= this._weaponDoubleDmgChance) { // 双刀双击
                weaponDamage *= 2;
                this._effectId = 4;
            }
        }
        weaponDamage += this.calcAttrEnchantDmg(); // 属性强化伤害

        if (darkElfWeapon && this._pc.hasSkillEffect(DOUBLE_BRAKE)) {
            if ((Random.nextInt(100) + 1) <= 33) {
                weaponDamage *= 2;
            }
        }

        return weaponDamage;
    }

    /**
     * 远距离伤害 (Long)
     * 
     * @param dmg
     *            伤害值
     * @return
     */
    private double calLongRageDamage(final double dmg) {
        double longdmg = dmg + this._pc.getBowDmgup()
                + this._pc.getOriginalBowDmgup();

        int add_dmg = 1;
        switch (this._weaponType) {
            case 20: // 弓
                if (this._arrow != null) {
                    add_dmg = this._arrow.getItem().getDmgSmall();
                    if (this._calcType == PC_NPC) {
                        if (this._targetNpc.getNpcTemplate().get_size()
                                .equalsIgnoreCase("large")) {
                            add_dmg = this._arrow.getItem().getDmgLarge();
                        }
                        if (this._targetNpc.getNpcTemplate().is_hard()) {
                            add_dmg /= 2;
                        }
                    }
                } else if (this._weaponId == 190) {
                    add_dmg = 15;
                }
                break;

            case 62: // 铁手甲
                add_dmg = this._sting.getItem().getDmgSmall();
                if (this._calcType == PC_NPC) {
                    if (this._targetNpc.getNpcTemplate().get_size()
                            .equalsIgnoreCase("large")) {
                        add_dmg = this._sting.getItem().getDmgLarge();
                    }
                }
                break;
        }

        if (add_dmg > 0) {
            longdmg += Random.nextInt(add_dmg) + 1;
        }

        // 防具增伤
        longdmg += this._pc.getDmgModifierByArmor();

        if (this._pc.hasSkillEffect(COOKING_2_3_N) // 料理追加伤害
                || this._pc.hasSkillEffect(COOKING_2_3_S) //
                || this._pc.hasSkillEffect(COOKING_3_0_N) //
                || this._pc.hasSkillEffect(COOKING_3_0_S) //
        ) {
            longdmg += 1;
        }

        return longdmg;
    }

    /**
     * 远距离命中率
     * 
     * @param hitRate
     * @return
     */
    private int calLongRageHit(final int hitRate) {
        int longHit = hitRate + this._pc.getBowHitup()
                + this._pc.getOriginalBowHitup();
        // 防具增加命中
        longHit += this._pc.getBowHitModifierByArmor();

        if (this._pc.hasSkillEffect(COOKING_2_3_N) // 料理追加命中
                || this._pc.hasSkillEffect(COOKING_2_3_S) //
                || this._pc.hasSkillEffect(COOKING_3_0_N) //
                || this._pc.hasSkillEffect(COOKING_3_0_S) //
        ) {
            longHit += 1;
        }
        return longHit;
    }

    /**
     * 近距离伤害 (Short)
     * 
     * @param dmg
     * @return
     */
    private double calShortRageDamage(final double dmg) {
        double shortdmg = dmg + this._pc.getDmgup()
                + this._pc.getOriginalDmgup();
        // 弱点曝光发动判断
        this.WeaknessExposure();
        // 近战魔法增伤
        shortdmg = this.calcBuffDamage(shortdmg);
        // 防具增伤
        shortdmg += this._pc.getBowDmgModifierByArmor();

        if (this._weaponType == 0) {
            shortdmg = (Random.nextInt(5) + 4) / 4;
        } else if ((this._weaponType2 == 17) || (this._weaponType2 == 19)) {
            shortdmg = L1WeaponSkill.getKiringkuDamage(this._pc, this._target);
        }

        if (this._pc.hasSkillEffect(COOKING_2_0_N) // 料理
                || this._pc.hasSkillEffect(COOKING_2_0_S) //
                || this._pc.hasSkillEffect(COOKING_3_2_N) //
                || this._pc.hasSkillEffect(COOKING_3_2_S) //
        ) {
            shortdmg += 1;
        }

        return shortdmg;
    }

    /**
     * 近距离命中率
     * 
     * @param hitRate
     * @return
     */
    private int calShortRageHit(final int hitRate) {
        int shortHit = hitRate + this._pc.getHitup()
                + this._pc.getOriginalHitup();
        // 防具增加命中
        shortHit += this._pc.getHitModifierByArmor();

        if (this._pc.hasSkillEffect(COOKING_2_0_N) // 料理追加命中
                || this._pc.hasSkillEffect(COOKING_2_0_S)) {
            shortHit += 1;
        }
        if (this._pc.hasSkillEffect(COOKING_3_2_N) // 料理追加命中
                || this._pc.hasSkillEffect(COOKING_3_2_S)) {
            shortHit += 2;
        }
        return shortHit;
    }

    /**
     * ■■■■■■■■■■■■■■■ 计算结果反映 ■■■■■■■■■■■■■■■
     */
    public void commit() {
        if (this._isHit) {
            switch (this._calcType) {
                case PC_PC:
                case NPC_PC:
                    this.commitPc();
                    break;

                case PC_NPC:
                case NPC_NPC:
                    this.commitNpc();
                    break;
            }
        }

        // 伤害值与命中率确认
        if (!Config.ALT_ATKMSG) {
            return;
        }
        if (Config.ALT_ATKMSG) {
            if (((this._calcType == PC_PC) || (this._calcType == PC_NPC))
                    && !this._pc.isGm()) {
                return;
            }
            if (((this._calcType == PC_PC) || (this._calcType == NPC_PC))
                    && !this._targetPc.isGm()) {
                return;
            }
        }
        String msg0 = "";
        final String msg1 = " 造成 ";
        String msg2 = "";
        String msg3 = "";
        String msg4 = "";
        if ((this._calcType == PC_PC) || (this._calcType == PC_NPC)) { // 攻击者为ＰＣ的场合
            msg0 = "物攻 对";
        } else if (this._calcType == NPC_PC) { // 攻击者为ＮＰＣ的场合
            msg0 = this._npc.getNameId() + "(物攻)：";
        }

        if ((this._calcType == NPC_PC) || (this._calcType == PC_PC)) { // 目标为ＰＣ的场合
            msg4 = this._targetPc.getName();
            msg2 = "，剩余 " + this._targetPc.getCurrentHp() + "，命中	"
                    + this._hitRate + "%";
        } else if (this._calcType == PC_NPC) { // 目标为ＮＰＣ的场合
            msg4 = this._targetNpc.getNameId();
            msg2 = "，剩余 " + this._targetNpc.getCurrentHp() + "，命中 "
                    + this._hitRate + "%";
        }
        msg3 = this._isHit ? this._damage + " 伤害" : "  0 伤害";

        // 物攻 对 目标 造成 X 伤害，剩余 Y，命中 Z %。
        if ((this._calcType == PC_PC) || (this._calcType == PC_NPC)) {
            this._pc.sendPackets(new S_ServerMessage(166, msg0, msg1, msg2,
                    msg3, msg4));
        }
        // 攻击者(物攻)： X伤害，剩余 Y，命中%。
        else if ((this._calcType == NPC_PC)) {
            this._targetPc.sendPackets(new S_ServerMessage(166, msg0, null,
                    msg2, msg3, null));
        }
    }

    /**
     * ■■■■ 反映反击屏障伤害 ■■■■
     */
    public void commitCounterBarrier() {
        final int damage = this.calcCounterBarrierDamage();
        if (damage == 0) {
            return;
        }
        switch (this._calcType) {
            case PC_PC:
                this._pc.receiveDamage(this._targetPc, damage, false);
                break;

            case NPC_PC:
                this._npc.receiveDamage(this._targetPc, damage);
                break;
        }
    }

    /**
     * ●●●● 反映ＮＰＣ的计算结果 ●●●●
     */
    private void commitNpc() {
        switch (this._calcType) {
            case PC_NPC:
                if (this._drainMana > 0) {
                    final int drainValue = this._targetNpc
                            .drainMana(this._drainMana);
                    final int newMp = this._pc.getCurrentMp() + drainValue;
                    this._pc.setCurrentMp(newMp);
                    if (drainValue > 0) {
                        final int newMp2 = this._targetNpc.getCurrentMp()
                                - drainValue;
                        this._targetNpc.setCurrentMpDirect(newMp2);
                    }
                }
                if (this._drainHp > 0) { // HP吸收恢复
                    final short newHp = (short) (this._pc.getCurrentHp() + this._drainHp);
                    this._pc.setCurrentHp(newHp);
                }
                this.damageNpcWeaponDurability(); // 武器有损坏。
                this._targetNpc.receiveDamage(this._pc, this._damage);
                break;

            case NPC_NPC:
                this._targetNpc.receiveDamage(this._npc, this._damage);
                break;
        }
    }

    /**
     * ●●●● 反映PC的计算结果 ●●●●
     */
    private void commitPc() {
        switch (this._calcType) {
            case PC_PC:
                if ((this._drainMana > 0)
                        && (this._targetPc.getCurrentMp() > 0)) {
                    if (this._drainMana > this._targetPc.getCurrentMp()) {
                        this._drainMana = this._targetPc.getCurrentMp();
                    }
                    short newMp = (short) (this._targetPc.getCurrentMp() - this._drainMana);
                    this._targetPc.setCurrentMp(newMp);
                    newMp = (short) (this._pc.getCurrentMp() + this._drainMana);
                    this._pc.setCurrentMp(newMp);
                }
                if (this._drainHp > 0) { // HP吸收恢复
                    final short newHp = (short) (this._pc.getCurrentHp() + this._drainHp);
                    this._pc.setCurrentHp(newHp);
                }
                this.damagePcWeaponDurability(); // 武器有损坏。
                this._targetPc.receiveDamage(this._pc, this._damage, false);
                break;

            case NPC_PC:
                this._targetPc.receiveDamage(this._npc, this._damage, false);
                break;
        }
    }

    /**
     * 武器的损伤。 对NPC的场合、10%的几率损坏。祝福武器3%的几率损坏。
     */
    private void damageNpcWeaponDurability() {
        final int chance = 10;
        final int bchance = 3;

        // 不攻击NPC、空手、不会损坏的武器、SOF中的场合什么都不做。
        if ((this._calcType != PC_NPC)
                || (this._targetNpc.getNpcTemplate().is_hard() == false)
                || (this._weaponType == 0)
                || (this.weapon.getItem().get_canbedmg() == 0)
                || this._pc.hasSkillEffect(SOUL_OF_FLAME)) {
            return;
        }
        // 普通武器・诅咒武器
        if (((this._weaponBless == 1) || (this._weaponBless == 2))
                && ((Random.nextInt(100) + 1) < chance)) {
            this._pc.sendPackets(new S_ServerMessage(268, this.weapon
                    .getLogName())); // \f1你的%0%s坏了。
            this._pc.getInventory().receiveDamage(this.weapon);
        }
        // 祝福武器
        if ((this._weaponBless == 0) && ((Random.nextInt(100) + 1) < bchance)) {
            this._pc.sendPackets(new S_ServerMessage(268, this.weapon
                    .getLogName())); // \f1你的%0%s坏了。
            this._pc.getInventory().receiveDamage(this.weapon);
        }
    }

    /**
     * 反弹攻击 损坏武器。 反弹攻击损坏的几率10%
     * 
     * @return
     */
    private void damagePcWeaponDurability() {
        // PvP以外、空手、弓、ガントトレット、对手未使用反弹攻击、SOF中的场合什么都不做
        if ((this._calcType != PC_PC) || (this._weaponType == 0)
                || (this._weaponType == 20) || (this._weaponType == 62)
                || (this._targetPc.hasSkillEffect(BOUNCE_ATTACK) == false)
                || this._pc.hasSkillEffect(SOUL_OF_FLAME)) {
            return;
        }

        if (Random.nextInt(100) + 1 <= 10) {
            this._pc.sendPackets(new S_ServerMessage(268, this.weapon
                    .getLogName())); // \f1你的%0%s坏了。
            this._pc.getInventory().receiveDamage(this.weapon);
        }
    }

    /**
     * 取得动作ID
     * 
     * @return
     */
    public int getActId() {
        return this._attckActId;
    }

    /**
     * 取得动画ID
     * 
     * @return
     */
    public int getGfxId() {
        return this._attckGrfxId;
    }

    /* ■■■■■■■■■■■■■■■ 反击屏障 ■■■■■■■■■■■■■■■ */

    private int getLeverage() {
        return this._leverage;
    }

    /**
     * ■■■■ 对手的攻击对反击屏障是否有效判别 ■■■■
     * 
     * @return
     */
    public boolean isShortDistance() {
        boolean isShortDistance = true;
        switch (this._calcType) {
            case PC_PC:
                if ((this._weaponType == 20) || (this._weaponType == 62)) { // 单手弓？
                    isShortDistance = false;
                }
                break;

            case NPC_PC:
                final boolean isLongRange = (this._npc.getLocation()
                        .getTileLineDistance(
                                new Point(this._targetX, this._targetY)) > 1);
                int bowActId = this._npc.getPolyArrowGfx();
                if (bowActId == 0) {
                    bowActId = this._npc.getNpcTemplate().getBowActId();
                }
                // 距离2格以上、远程攻击变身
                if (isLongRange && (bowActId > 0)) {
                    isShortDistance = false;
                }
                break;
        }
        return isShortDistance;
    }

    /**
     * ●●●● 亡灵系列ＮＰＣ在夜间的攻击力变化 ●●●●
     * 
     * @return
     */
    private boolean isUndeadDamage() {
        boolean flag = false;
        final int undead = this._npc.getNpcTemplate().get_undead();
        final boolean isNight = L1GameTimeClock.getInstance().currentTime()
                .isNight();
        if (isNight && ((undead == 1) || (undead == 3) || (undead == 4))) { // 18～6时、かつ、亡灵系列・不死系列・不死系的弱点无效
            flag = true;
        }
        return flag;
    }

    /**
     * 设置动作ID
     * 
     * @param actId
     */
    public void setActId(final int actId) {
        this._attckActId = actId;
    }

    /**
     * 设置动画ID
     * 
     * @param gfxId
     */
    public void setGfxId(final int gfxId) {
        this._attckGrfxId = gfxId;
    }

    /**
     * 设置攻击倍率 (1/10倍)
     * 
     * @param i
     */
    public void setLeverage(final int i) {
        this._leverage = i;
    }

    /**
     * 弱点曝光
     */
    private void WeaknessExposure() {
        if (this.weapon != null) {
            final int random = Random.nextInt(100) + 1;
            if (this._weaponType2 == 18) { // 锁链剑
                // 使用屠宰者...
                if (this._pc.isFoeSlayer()) {
                    return;
                }
                if (this._pc.hasSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV3)) { // 目前阶段三
                    if ((random > 30) && (random <= 60)) { // 阶段三
                        this._pc.killSkillEffectTimer(SPECIAL_EFFECT_WEAKNESS_LV3);
                        this._pc.setSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV3,
                                16 * 1000);
                        this._pc.sendPackets(new S_SkillIconGFX(75, 3));
                    }
                } else if (this._pc.hasSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV2)) { // 目前阶段二
                    if (random <= 30) { // 阶段二
                        this._pc.killSkillEffectTimer(SPECIAL_EFFECT_WEAKNESS_LV2);
                        this._pc.setSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV2,
                                16 * 1000);
                        this._pc.sendPackets(new S_SkillIconGFX(75, 2));
                    } else if (random >= 70) { // 阶段三
                        this._pc.killSkillEffectTimer(SPECIAL_EFFECT_WEAKNESS_LV2);
                        this._pc.setSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV3,
                                16 * 1000);
                        this._pc.sendPackets(new S_SkillIconGFX(75, 3));
                    }
                } else if (this._pc.hasSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV1)) { // 目前阶段一
                    if (random <= 40) { // 阶段一
                        this._pc.killSkillEffectTimer(SPECIAL_EFFECT_WEAKNESS_LV1);
                        this._pc.setSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV1,
                                16 * 1000);
                        this._pc.sendPackets(new S_SkillIconGFX(75, 1));
                    } else if (random >= 70) { // 阶段二
                        this._pc.killSkillEffectTimer(SPECIAL_EFFECT_WEAKNESS_LV1);
                        this._pc.setSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV2,
                                16 * 1000);
                        this._pc.sendPackets(new S_SkillIconGFX(75, 2));
                    }
                } else {
                    if (random <= 40) { // 阶段一
                        this._pc.setSkillEffect(SPECIAL_EFFECT_WEAKNESS_LV1,
                                16 * 1000);
                        this._pc.sendPackets(new S_SkillIconGFX(75, 1));
                    }
                }
            }
        }
    }
}
