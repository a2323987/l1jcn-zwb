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
package com.lineage.server.model.skill;

import static com.lineage.server.model.skill.L1SkillId.*;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.ActionCodes;
import com.lineage.server.datatables.PolyTable;
import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.model.L1CastleLocation;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1EffectSpawn;
import com.lineage.server.model.L1Location;
import com.lineage.server.model.L1Magic;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.L1PolyMorph;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.L1War;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1AuctionBoardInstance;
import com.lineage.server.model.Instance.L1BoardInstance;
import com.lineage.server.model.Instance.L1CrownInstance;
import com.lineage.server.model.Instance.L1DollInstance;
import com.lineage.server.model.Instance.L1DoorInstance;
import com.lineage.server.model.Instance.L1DwarfInstance;
import com.lineage.server.model.Instance.L1EffectInstance;
import com.lineage.server.model.Instance.L1FieldObjectInstance;
import com.lineage.server.model.Instance.L1FurnitureInstance;
import com.lineage.server.model.Instance.L1GuardInstance;
import com.lineage.server.model.Instance.L1HousekeeperInstance;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1MerchantInstance;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.model.Instance.L1TeleporterInstance;
import com.lineage.server.model.Instance.L1TowerInstance;
import com.lineage.server.model.skill.stop.Producer;
import com.lineage.server.model.skill.use.L1SkillEffect;
import com.lineage.server.model.skill.use.TargetStatus;
import com.lineage.server.model.trap.L1WorldTraps;
import com.lineage.server.serverpackets.S_AttackPacket;
import com.lineage.server.serverpackets.S_ChangeHeading;
import com.lineage.server.serverpackets.S_Dexup;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_EffectLocation;
import com.lineage.server.serverpackets.S_Message_YN;
import com.lineage.server.serverpackets.S_OwnCharAttrDef;
import com.lineage.server.serverpackets.S_OwnCharStatus;
import com.lineage.server.serverpackets.S_Paralysis;
import com.lineage.server.serverpackets.S_Poison;
import com.lineage.server.serverpackets.S_RangeSkill;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillBrave;
import com.lineage.server.serverpackets.S_SkillHaste;
import com.lineage.server.serverpackets.S_SkillIconAura;
import com.lineage.server.serverpackets.S_SkillIconGFX;
import com.lineage.server.serverpackets.S_SkillIconShield;
import com.lineage.server.serverpackets.S_SkillIconWaterLife;
import com.lineage.server.serverpackets.S_SkillIconWindShackle;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.serverpackets.S_Sound;
import com.lineage.server.serverpackets.S_Strup;
import com.lineage.server.serverpackets.S_TrueTarget;
import com.lineage.server.serverpackets.S_UseAttackSkill;
import com.lineage.server.templates.L1BookMark;
import com.lineage.server.templates.L1Skills;
import com.lineage.server.utils.Random;
import com.lineage.server.utils.collections.Lists;

/**
 * 使用技能判断
 */
public class L1SkillUse {

    private static Logger _log = Logger.getLogger(L1SkillUse.class.getName());

    /** 类型:一般 */
    public static final int TYPE_NORMAL = 0;
    /** 类型:登入 */
    public static final int TYPE_LOGIN = 1;
    /** 类型:魔法卷轴 */
    public static final int TYPE_SPELLSC = 2;
    /** 类型:NPC BUFF */
    public static final int TYPE_NPCBUFF = 3;
    /** 类型:GM BUFF */
    public static final int TYPE_GMBUFF = 4;
    /** 技能 */
    private L1Skills _skill;
    /** 技能ID */
    private int _skillId;
    /** 伤害 */
    private int _dmg;
    /** 取得状态持续时间 */
    private int _getBuffDuration;
    /** 昏迷状态持续时间 */
    private int _shockStunDuration;
    /** 取得状态图示的时间 */
    private int _getBuffIconDuration;
    /** 目标 ID */
    private int _targetID;
    /** MP 消耗 */
    private int _mpConsume = 0;
    /** HP 消耗 */
    private int _hpConsume = 0;
    /** 目标 X 坐标 */
    private int _targetX = 0;
    /** 目标Y 坐标 */
    private int _targetY = 0;
    /** 通知信息 */
    private String _message = null;
    /** 技能时间 */
    private int _skillTime = 0;
    /** 类型 */
    private int _type = 0;
    /** PK中 */
    private boolean _isPK = false;
    /** 记忆坐标ID */
    private int _bookmarkId = 0;
    /** 道具的唯一ID */
    private int _itemobjid = 0;
    /** 检查所使用技能 */
    private boolean _checkedUseSkill = false;
    /** 攻击倍率 (1/10倍) */
    private int _leverage = 10;
    /** 技能攻击距离 */
    private int _skillRanged = 0;
    /** 技能攻击范围 */
    private int _skillArea = 0;
    /** 是否被冻结 */
    private boolean _isFreeze = false;
    /** 是否魔法屏障状态 */
    private boolean _isCounterMagic = true;
    /**  */
    private boolean _isGlanceCheckFail = false;
    /** 技能使用者 */
    private L1Character _user = null;
    /** 技能攻击目标 */
    private L1Character _target = null;
    /** 使用者为PC */
    private L1PcInstance _player = null;
    /** 使用者为NPC */
    private L1NpcInstance _npc = null;
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
    /** 目标清单 */
    private List<TargetStatus> _targetList;
    /** 动作 ID */
    private int _actid = 0;
    /** 图像 ID */
    private int _gfxid = 0;

    /** 设定魔法屏障不可抵挡的魔法 */
    private static final int[] EXCEPT_COUNTER_MAGIC = { HEAL, LIGHT, SHIELD,
            TELEPORT, HOLY_WEAPON, CURE_POISON, ENCHANT_WEAPON, DETECTION,
            DECREASE_WEIGHT, EXTRA_HEAL, BLESSED_ARMOR, PHYSICAL_ENCHANT_DEX,
            COUNTER_MAGIC, MEDITATION, GREATER_HEAL, REMOVE_CURSE,
            PHYSICAL_ENCHANT_STR, HASTE, CANCELLATION, BLESS_WEAPON, HEAL_ALL,
            HOLY_WALK, GREATER_HASTE, BERSERKERS, FULL_HEAL, INVISIBILITY,
            RESURRECTION, LIFE_STREAM, SHAPE_CHANGE, IMMUNE_TO_HARM,
            MASS_TELEPORT, COUNTER_DETECTION, CREATE_MAGICAL_WEAPON,
            GREATER_RESURRECTION, ABSOLUTE_BARRIER, ADVANCE_SPIRIT, SHOCK_STUN,
            REDUCTION_ARMOR, BOUNCE_ATTACK, SOLID_CARRIAGE, COUNTER_BARRIER,
            BLIND_HIDING, ENCHANT_VENOM, SHADOW_ARMOR, BRING_STONE,
            MOVING_ACCELERATION, BURNING_SPIRIT, VENOM_RESIST, DOUBLE_BRAKE,
            UNCANNY_DODGE, SHADOW_FANG, DRESS_MIGHTY, DRESS_DEXTERITY,
            DRESS_EVASION, TRUE_TARGET, GLOWING_AURA, SHINING_AURA, CALL_CLAN,
            BRAVE_AURA, RUN_CLAN, RESIST_MAGIC, BODY_TO_MIND,
            TELEPORT_TO_MATHER, TRIPLE_ARROW, COUNTER_MIRROR, CLEAR_MIND,
            RESIST_ELEMENTAL, BLOODY_SOUL, ELEMENTAL_PROTECTION, FIRE_WEAPON,
            WIND_SHOT, WIND_WALK, EARTH_SKIN, FIRE_BLESS, STORM_EYE,
            NATURES_TOUCH, EARTH_BLESS, AREA_OF_SILENCE, BURNING_WEAPON,
            NATURES_BLESSING, CALL_OF_NATURE, STORM_SHOT, IRON_SKIN,
            EXOTIC_VITALIZE, WATER_LIFE, ELEMENTAL_FIRE, SOUL_OF_FLAME,
            ADDITIONAL_FIRE, DRAGON_SKIN, AWAKEN_ANTHARAS, AWAKEN_FAFURION,
            AWAKEN_VALAKAS, MIRROR_IMAGE, ILLUSION_OGRE, ILLUSION_LICH,
            PATIENCE, ILLUSION_DIA_GOLEM, INSIGHT, ILLUSION_AVATAR, 10026,
            10027, 10028, 10029 };

    /** 构建 */
    public L1SkillUse() {
    }

    /**
     * 使用相应技能要对玩家人物或者使用的相应道具追加效果时间
     * 
     * @param cha
     * @param repetition
     */
    private void addMagicList(final L1Character cha, final boolean repetition) {
        if (this._skillTime == 0) {
            this._getBuffDuration = this._skill.getBuffDuration() * 1000; // 效果时间
            if (this._skill.getBuffDuration() == 0) {
                if (this._skillId == INVISIBILITY) { // 隐身术
                    cha.setSkillEffect(INVISIBILITY, 0);
                }
                return;
            }
        } else {
            this._getBuffDuration = this._skillTime * 1000; // 如果参数time非0、设定效果时间
        }

        if (this._skillId == SHOCK_STUN) {
            this._getBuffDuration = this._shockStunDuration;
        }

        // 毒咒効果移至L1Poison处理。
        if (this._skillId == CURSE_POISON) {
            return;
        }

        // 木乃伊的咀咒、石化持续时间移至L1CurseParalysis处理。
        if ((this._skillId == CURSE_PARALYZE)
                || (this._skillId == CURSE_PARALYZE2)) {
            return;
        }

        // 变形术持续时间移至 L1PolyMorph 处理。
        if (this._skillId == SHAPE_CHANGE) {
            return;
        }

        // 武器・防具效果移至L1ItemInstance处理。
        if ((this._skillId == BLESSED_ARMOR) || (this._skillId == HOLY_WEAPON)
                || (this._skillId == ENCHANT_WEAPON)
                || (this._skillId == BLESS_WEAPON)
                || (this._skillId == SHADOW_FANG)) {
            return;
        }

        // 冻结失败
        if (((this._skillId == ICE_LANCE)
                || (this._skillId == FREEZING_BLIZZARD)
                || (this._skillId == FREEZING_BREATH)
                || (this._skillId == ICE_LANCE_COCKATRICE) || (this._skillId == ICE_LANCE_BASILISK))
                && !this._isFreeze) {
            return;
        }

        // 觉醒技能效果移至L1Awake处理。
        if ((this._skillId == AWAKEN_ANTHARAS)
                || (this._skillId == AWAKEN_FAFURION)
                || (this._skillId == AWAKEN_VALAKAS)) {
            return;
        }

        // 骷髅毁坏持续时间另外处理
        if ((this._skillId == BONE_BREAK) || (this._skillId == CONFUSION)) {
            return;
        }
        cha.setSkillEffect(this._skillId, this._getBuffDuration);

        if ((this._skillId == ELEMENTAL_FALL_DOWN) && repetition) { // 弱化属性重复施放
            if (this._skillTime == 0) {
                this._getBuffIconDuration = this._skill.getBuffDuration(); // 效果时间
            } else {
                this._getBuffIconDuration = this._skillTime;
            }
            this._target.removeSkillEffect(ELEMENTAL_FALL_DOWN);
            this.runSkill();
            return;
        }
        if ((cha instanceof L1PcInstance) && repetition) { // 对象为PC 技能重复释放的情况
            final L1PcInstance pc = (L1PcInstance) cha;
            this.sendIcon(pc);
        }
    }

    /**
     * @param player
     *            攻击者为PC
     * @param skillid
     *            技能编号
     * @param target_id
     *            目标OBJID
     * @param x
     *            X坐标
     * @param y
     *            Y坐标
     * @param message
     *            讯息
     * @param time
     *            时间
     * @param type
     *            类型
     * @param attacker
     *            攻击者为NPC
     * @return
     */
    public boolean checkUseSkill(final L1PcInstance player, final int skillid,
            final int target_id, final int x, final int y,
            final String message, final int time, final int type,
            final L1Character attacker) {
        return this.checkUseSkill(player, skillid, target_id, x, y, message,
                time, type, attacker, 0, 0, 0);
    }

    /**
     * @param player
     *            攻击者为PC
     * @param skillid
     *            技能编号
     * @param target_id
     *            目标OBJID
     * @param x
     *            X坐标
     * @param y
     *            Y坐标
     * @param message
     *            讯息
     * @param time
     *            时间
     * @param type
     *            类型
     * @param attacker
     *            攻击者为NPC
     * @param actid
     *            动作ID
     * @param mpConsume
     *            消耗MP
     * @return
     */
    public boolean checkUseSkill(final L1PcInstance player, final int skillid,
            final int target_id, final int x, final int y,
            final String message, final int time, final int type,
            final L1Character attacker, final int actid, final int gfxid,
            final int mpConsume) {
        // 初期从这里设定
        this.setCheckedUseSkill(true); // 检查所用技能
        this._targetList = Lists.newList(); // 目标名单的初始化

        this._skill = SkillsTable.getInstance().getTemplate(skillid);
        this._skillId = skillid;
        this._targetX = x;
        this._targetY = y;
        this._message = message;
        this._skillTime = time;
        this._type = type;
        this._actid = actid;
        this._gfxid = gfxid;
        this._mpConsume = mpConsume;
        boolean checkedResult = true;

        if (attacker == null) {
            // pc
            this._player = player;
            this._user = this._player;
        } else {
            // npc
            this._npc = (L1NpcInstance) attacker;
            this._user = this._npc;
        }

        if (this._skill.getTarget().equals("none")) {
            this._targetID = this._user.getId();
            this._targetX = this._user.getX();
            this._targetY = this._user.getY();
        } else {
            this._targetID = target_id;
        }

        switch (type) {
            case TYPE_NORMAL: // 使用一般魔法
                checkedResult = this.isNormalSkillUsable();
                break;

            case TYPE_SPELLSC: // 使用魔法卷轴
                checkedResult = this.isSpellScrollUsable();
                break;

            case TYPE_NPCBUFF: // 使用者为NPC
                checkedResult = true;
                break;
        }
        if (!checkedResult) {
            return false;
        }

        // 火牢、治愈能量风暴对象坐标
        // 精准目标的使用者坐标例外
        if ((this._skillId == FIRE_WALL) || (this._skillId == LIFE_STREAM)
                || (this._skillId == TRUE_TARGET)) {
            return true;
        }

        final L1Object l1object = L1World.getInstance().findObject(
                this._targetID);
        if (l1object instanceof L1ItemInstance) {
            _log.fine("技能目标项目名称: " + ((L1ItemInstance) l1object).getViewName());
            // 精灵石可以成为技能目标。
            // Linux环境确认（Windows未确认）
            // 2008.5.4追加：对地面和物品使用魔法。继续发生错误时return
            return false;
        }
        if (this._user instanceof L1PcInstance) {
            if (l1object instanceof L1PcInstance) {
                this._calcType = PC_PC;
            } else {
                this._calcType = PC_NPC;
            }
        } else if (this._user instanceof L1NpcInstance) {
            if (l1object instanceof L1PcInstance) {
                this._calcType = NPC_PC;
            } else if (this._skill.getTarget().equals("none")) {
                this._calcType = NPC_PC;
            } else {
                this._calcType = NPC_NPC;
            }
        }

        switch (this._skillId) {

            // 可使用传送控制戒指的技能
            case TELEPORT: // 法师魔法 (指定传送)
            case MASS_TELEPORT: // 法师魔法 (集体传送术)
                this._bookmarkId = target_id;
                break;

            // 技能对象为道具
            case CREATE_MAGICAL_WEAPON: // 法师魔法 (创造魔法武器)
            case BRING_STONE: // 黑暗妖精魔法 (提炼魔石)
            case BLESSED_ARMOR: // 法师魔法 (铠甲护持)
            case ENCHANT_WEAPON: // 法师魔法 (拟似魔法武器)
            case SHADOW_FANG: // 黑暗妖精魔法 (暗影之牙)
                this._itemobjid = target_id;
                break;
        }

        this._target = (L1Character) l1object;

        // 在目标怪物以外的攻击技能、其他场合与PK模式。
        if (!(this._target instanceof L1MonsterInstance)
                && this._skill.getTarget().equals("attack")
                && (this._user.getId() != target_id)) {
            this._isPK = true;
        }

        // 到目前为止的初期设定
        // 预检查

        // 目标角色以外的情况什么也不做。
        if (!(l1object instanceof L1Character)) {
            checkedResult = false;
        }

        // 技能发动 目标清单判定
        this.makeTargetList();

        // 目标列表为空&&使用者为NPC
        if (this._targetList.isEmpty() && (this._user instanceof L1NpcInstance)) {
            checkedResult = false;
        }
        // 预先检查结果
        return checkedResult;
    }

    /**
     * 删除不能重复/同时使用的技能，图标更改为刚使用时的图标
     * 
     * @param cha
     */
    private void deleteRepeatedSkills(final L1Character cha) {
        final int[][] repeatedSkills = {

                // 火焰武器、风之神射、烈炎气息、暴风之眼、烈炎武器、暴风神射、妈祖的祝福
                { FIRE_WEAPON, WIND_SHOT, FIRE_BLESS, STORM_EYE,
                        BURNING_WEAPON, STORM_SHOT, EFFECT_BLESS_OF_MAZU },
                // 防护罩、影之防护、大地防护、大地的祝福、钢铁防护
                { SHIELD, SHADOW_ARMOR, EARTH_SKIN, EARTH_BLESS, IRON_SKIN },
                // 勇敢药水、精灵饼干、(神圣疾走、行走加速、风之疾走)、超级加速、血之渴望
                { STATUS_BRAVE, STATUS_ELFBRAVE, HOLY_WALK,
                        MOVING_ACCELERATION, WIND_WALK, STATUS_BRAVE2,
                        BLOODLUST },
                // 加速术、强力加速术、自我加速药水
                { HASTE, GREATER_HASTE, STATUS_HASTE },
                // 缓速、集体缓术、地面障碍
                { SLOW, MASS_SLOW, ENTANGLE },
                // 通畅气脉术、敏捷提升
                { PHYSICAL_ENCHANT_DEX, DRESS_DEXTERITY },
                // 体魄强健术、力量提升
                { PHYSICAL_ENCHANT_STR, DRESS_MIGHTY },
                // 激励士气、钢铁士气
                { GLOWING_AURA, SHINING_AURA },
                // 镜像、暗影闪避
                { MIRROR_IMAGE, UNCANNY_DODGE } };

        for (final int[] skills : repeatedSkills) {
            for (final int id : skills) {
                if (id == this._skillId) {
                    this.stopSkillList(cha, skills);
                }
            }
        }
    }

    /**
     * 检测隐身
     * 
     * @param pc
     */
    private void detection(final L1PcInstance pc) {
        if (!pc.isGmInvis() && pc.isInvisble()) { // 自己隐身中
            pc.delInvis();
            pc.beginInvisTimer();
        }

        for (final L1PcInstance tgt : L1World.getInstance()
                .getVisiblePlayer(pc)) { // 画面内其他隐身者
            if (!tgt.isGmInvis() && tgt.isInvisble()) {
                tgt.delInvis();
            }
        }
        L1WorldTraps.getInstance().onDetection(pc); // 侦测陷阱处理
    }

    /**
     * 技能失败处理(仅PC)
     */
    private void failSkill() {
        this.setCheckedUseSkill(false);
        switch (this._skillId) {
            // 瞬移技能
            case TELEPORT:
            case MASS_TELEPORT:
            case TELEPORT_TO_MATHER:
                // 解除传送锁定状态
                this._player.sendPackets(new S_Paralysis(
                        S_Paralysis.TYPE_TELEPORT_UNLOCK, false));
                break;
        }
    }

    /**
     * 取得攻击倍率 (1/10)
     * 
     * @return
     */
    public int getLeverage() {
        return this._leverage;
    }

    /**
     * 取得魔法攻击范围变更。
     * 
     * @return
     */
    public int getSkillArea() {
        if (this._skillArea == 0) {
            return this._skill.getArea();
        }
        return this._skillArea;
    }

    /**
     * 取得魔法攻击距离变更。
     * 
     * @return
     */
    public int getSkillRanged() {
        if (this._skillRanged == 0) {
            return this._skill.getRanged();
        }
        return this._skillRanged;
    }

    /**
     * pc 施放技能判断
     * 
     * @param player
     *            角色
     * @param skillId
     *            技能ID
     * @param targetId
     *            目标ID
     * @param x
     *            X坐标
     * @param y
     *            Y坐标
     * @param message
     *            讯息
     * @param timeSecs
     *            时间:秒
     * @param type
     *            类型
     */
    public void handleCommands(final L1PcInstance player, final int skillId,
            final int targetId, final int x, final int y, final String message,
            final int timeSecs, final int type) {
        final L1Character attacker = null;
        this.handleCommands(player, skillId, targetId, x, y, message, timeSecs,
                type, attacker);
    }

    /**
     * 通用技能施放判断
     * 
     * @param player
     *            角色
     * @param skillId
     *            技能ID
     * @param targetId
     *            目标ID
     * @param x
     *            X坐标
     * @param y
     *            Y坐标
     * @param message
     *            讯息
     * @param timeSecs
     *            时间:秒
     * @param type
     *            类型
     * @param attacker
     *            攻击者
     */
    public void handleCommands(final L1PcInstance player, final int skillId,
            final int targetId, final int x, final int y, final String message,
            final int timeSecs, final int type, final L1Character attacker) {

        try {
            // 提前检查？
            if (!this.isCheckedUseSkill()) {
                final boolean isUseSkill = this.checkUseSkill(player, skillId,
                        targetId, x, y, message, timeSecs, type, attacker);

                // 不能使用技能
                if (!isUseSkill) {
                    this.failSkill(); // 做失败处理
                    return;
                }
            }

            switch (type) {
                case TYPE_NORMAL: // 魔法咏唱时
                    if (!this._isGlanceCheckFail || (this.getSkillArea() > 0)
                            || this._skill.getTarget().equals("none")) {
                        this.runSkill();
                        this.useConsume();
                        this.sendGrfx(true);
                        this.sendFailMessageHandle();
                        this.setDelay();
                    }
                    break;

                case TYPE_LOGIN: // 登陆时（不消耗材料与HPMP、没有图形）
                    this.runSkill();
                    break;

                case TYPE_SPELLSC: // 使用魔法卷轴时 (不消耗材料与HPMP)
                    this.runSkill();
                    this.sendGrfx(true);
                    break;

                case TYPE_GMBUFF: // GMBUFF使用时（不消耗材料与HPMP、没有魔法动作）
                    this.runSkill();
                    this.sendGrfx(false);
                    break;

                case TYPE_NPCBUFF: // NPCBUFF使用时（不消耗材料与HPMP）
                    this.runSkill();
                    this.sendGrfx(true);
                    break;
            }
            this.setCheckedUseSkill(false);
        } catch (final Exception e) {
            _log.log(Level.SEVERE, "", e);
        }
    }

    /**
     * 精灵魔法的属性与使用者的属性不一致
     */
    private boolean isAttrAgrees() {
        final int magicattr = this._skill.getAttr();
        if (this._user instanceof L1NpcInstance) { // NPC使用的任何场合都OK
            return true;
        }

        // 精灵魔法、无属性魔法、
        switch (this._skill.getSkillLevel()) {
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22: // 使用者魔法的属性不一致。 GM例外
                if ((magicattr != 0)
                        && (magicattr != this._player.getElfAttr())
                        && (!this._player.isGm())) {
                    return false;
                }
                break;
        }
        return true;
    }

    /**
     * 检查所使用技能
     * 
     * @return
     */
    private boolean isCheckedUseSkill() {
        return this._checkedUseSkill;
    }

    /**
     * 判断技能的使用是否需要消耗HP/MP
     * 
     * @return
     */
    private boolean isHPMPConsume() {
        if (this._mpConsume == 0) {
            this._mpConsume = this._skill.getMpConsume();
        }
        this._hpConsume = this._skill.getHpConsume();
        int currentMp = 0;
        int currentHp = 0;

        if (this._user instanceof L1NpcInstance) {
            currentMp = this._npc.getCurrentMp();
            currentHp = this._npc.getCurrentHp();
        } else {
            currentMp = this._player.getCurrentMp();
            currentHp = this._player.getCurrentHp();

            // 智力的MP减免
            if ((this._player.getInt() > 12) && (this._skillId > HOLY_WEAPON)
                    && (this._skillId <= FREEZING_BLIZZARD)) { // LV2以上
                this._mpConsume--;
            }
            if ((this._player.getInt() > 13) && (this._skillId > STALAC)
                    && (this._skillId <= FREEZING_BLIZZARD)) { // LV3以上
                this._mpConsume--;
            }
            if ((this._player.getInt() > 14)
                    && (this._skillId > WEAK_ELEMENTAL)
                    && (this._skillId <= FREEZING_BLIZZARD)) { // LV4以上
                this._mpConsume--;
            }
            if ((this._player.getInt() > 15) && (this._skillId > MEDITATION)
                    && (this._skillId <= FREEZING_BLIZZARD)) { // LV5以上
                this._mpConsume--;
            }
            if ((this._player.getInt() > 16) && (this._skillId > DARKNESS)
                    && (this._skillId <= FREEZING_BLIZZARD)) { // LV6以上
                this._mpConsume--;
            }
            if ((this._player.getInt() > 17) && (this._skillId > BLESS_WEAPON)
                    && (this._skillId <= FREEZING_BLIZZARD)) { // LV7以上
                this._mpConsume--;
            }
            if ((this._player.getInt() > 18) && (this._skillId > DISEASE)
                    && (this._skillId <= FREEZING_BLIZZARD)) { // LV8以上
                this._mpConsume--;
            }

            // 骑士智力减免
            if ((this._player.getInt() > 12) && (this._skillId >= SHOCK_STUN)
                    && (this._skillId <= COUNTER_BARRIER)) {
                if (this._player.getInt() <= 17) {
                    this._mpConsume -= (this._player.getInt() - 12);
                } else {
                    this._mpConsume -= 5; // int > 18
                    if (this._mpConsume > 1) { // 法术还可以减免
                        final byte extraInt = (byte) (this._player.getInt() - 17);
                        // 减免公式
                        for (int first = 1, range = 2; first <= extraInt; first += range, range++) {
                            this._mpConsume--;
                        }
                    }
                }

            }

            // 装备MP减免 一次只需判断一个
            switch (this._skillId) {
                case PHYSICAL_ENCHANT_DEX: // 敏捷魔法头盔使用通畅气脉术
                    if (this._player.getInventory().checkEquipped(20013)) {
                        this._mpConsume /= 2;
                    }
                    break;

                case HEAL: // 治愈魔法头盔使用初级治愈术
                case EXTRA_HEAL: // 治愈魔法头盔使用中级治愈术
                    if (this._player.getInventory().checkEquipped(20014)) {
                        this._mpConsume /= 2;
                    }
                    break;

                case ENCHANT_WEAPON: // 力量魔法头盔使用拟似魔法武器
                case DETECTION: // 力量魔法头盔使用无所遁形术
                case PHYSICAL_ENCHANT_STR: // 力量魔法头盔使用体魄强健术
                    if (this._player.getInventory().checkEquipped(20015)) {
                        this._mpConsume /= 2;
                    }
                    break;

                case HASTE: // 小型风之头盔、敏捷魔法头盔、风之头盔使用加速术
                    if (this._player.getInventory().checkEquipped(20008)
                            || this._player.getInventory().checkEquipped(20013)
                            || this._player.getInventory().checkEquipped(20023)) {
                        this._mpConsume /= 2;
                    }
                    break;

                case GREATER_HASTE: // 风之头盔使用强力加速术
                    if (this._player.getInventory().checkEquipped(20023)) {
                        this._mpConsume /= 2;
                    }
                    break;
            }

            // 初始能力减免
            if (this._player.getOriginalMagicConsumeReduction() > 0) {
                this._mpConsume -= this._player
                        .getOriginalMagicConsumeReduction();
            }

            if (0 < this._skill.getMpConsume()) {
                this._mpConsume = Math.max(this._mpConsume, 1); // 最小值 1
            }
        }

        if (currentHp < this._hpConsume + 1) {
            if (this._user instanceof L1PcInstance) {
                this._player.sendPackets(new S_ServerMessage(279)); // \f1因体力不足而无法使用魔法。
            }
            return false;
        } else if (currentMp < this._mpConsume) {
            if (this._user instanceof L1PcInstance) {
                this._player.sendPackets(new S_ServerMessage(278)); // \f1因魔力不足而无法使用魔法。
            }
            return false;
        }

        return true;
    }

    /**
     * 判断技能的使用是否需要其他物品
     * 
     * @return
     */
    private boolean isItemConsume() {

        final int itemConsume = this._skill.getItemConsumeId();
        final int itemConsumeCount = this._skill.getItemConsumeCount();

        // 施放魔法不需要材料
        if (itemConsume == 0) {
            return true;
        }

        // 必要材料不足。
        if (!this._player.getInventory().checkItem(itemConsume,
                itemConsumeCount)) {
            return false;
        }

        return true;
    }

    /**
     * 施法时判断角色目前状态能否使用
     * 
     * @return false 不能使用
     */
    private boolean isNormalSkillUsable() {

        // 检查PC使用技能的场合
        if (this._user instanceof L1PcInstance) {

            // 取得使用者 (PC)
            final L1PcInstance pc = (L1PcInstance) this._user;

            // 传送中
            if (pc.isTeleport()) {
                return false;
            }

            // 麻痹・冻结状态
            if (pc.isParalyzed()) {
                return false;
            }

            // 隐身中无法使用技能
            if ((pc.isInvisble() || pc.isInvisDelay())
                    && !this._skill.isInvisUsableSkill()) {
                return false;
            }

            // 负重过高
            if (pc.getInventory().getWeight242() >= 197) {
                pc.sendPackets(new S_ServerMessage(316)); // \f1你携带太多物品，因此无法使用法术。
                return false;
            }

            // 取得变身ID
            final int polyId = pc.getTempCharGfx();

            // 取得变身
            final L1PolyMorph poly = PolyTable.getInstance()
                    .getTemplate(polyId);

            // 有变身、该变身不能使用的技能
            if ((poly != null) && !poly.canUseSkill()) {
                pc.sendPackets(new S_ServerMessage(285)); // \f1在此状态下无法使用魔法。
                return false;
            }

            // 精灵魔法、属性不一致不能使用。
            if (!this.isAttrAgrees()) {
                return false;
            }

            // 精灵没有定系无法使用单属性防御
            if ((this._skillId == ELEMENTAL_PROTECTION)
                    && (pc.getElfAttr() == 0)) {
                pc.sendPackets(new S_ServerMessage(280)); // \f1施咒失败。
                return false;
            }

            // 水中无法使用火属性魔法
            if (pc.getMap().isUnderwater() && (this._skill.getAttr() == 2)) {
                pc.sendPackets(new S_ServerMessage(280)); // \f1施咒失败。
                return false;
            }

            // 技能延迟中无法使用
            if (pc.isSkillDelay()) {
                return false;
            }

            // 魔法封印、封印禁地、卡毒、幻想
            if (pc.hasSkillEffect(SILENCE)
                    || pc.hasSkillEffect(AREA_OF_SILENCE)
                    || pc.hasSkillEffect(STATUS_POISON_SILENCE)
                    || pc.hasSkillEffect(CONFUSION_ING)) {
                pc.sendPackets(new S_ServerMessage(285)); // \f1在此状态下无法使用魔法。
                return false;
            }

            // 正义值小于500不能使用 究极光裂术
            if ((this._skillId == DISINTEGRATE) && (pc.getLawful() < 500)) {
                pc.sendPackets(new S_ServerMessage(352, "$967")); // 若要使用这个法术，属性必须成为(正义)。
                return false;
            }

            // 在同样的立方体效果范围中无法再次使用
            if ((this._skillId == CUBE_IGNITION // 幻术士魔法 (立方：燃烧)
                    )
                    || (this._skillId == CUBE_QUAKE // 幻术士魔法 (立方：地裂)
                    ) || (this._skillId == CUBE_SHOCK // 幻术士魔法 (立方：冲击)
                    ) || (this._skillId == CUBE_BALANCE // 幻术士魔法 (立方：和谐)
                    )) {

                // 附近有无立方体
                boolean isNearSameCube = false;

                // 动画ID
                int gfxId = 0;

                for (final L1Object obj : L1World.getInstance()
                        .getVisibleObjects(pc, 3)) {
                    if (obj instanceof L1EffectInstance) {
                        final L1EffectInstance effect = (L1EffectInstance) obj;
                        gfxId = effect.getGfxId();
                        if (((this._skillId == CUBE_IGNITION) && (gfxId == 6706))
                                || ((this._skillId == CUBE_QUAKE) && (gfxId == 6712))
                                || ((this._skillId == CUBE_SHOCK) && (gfxId == 6718))
                                || ((this._skillId == CUBE_BALANCE) && (gfxId == 6724))) {
                            isNearSameCube = true;
                            break;
                        }
                    }
                }
                if (isNearSameCube) {
                    pc.sendPackets(new S_ServerMessage(1412)); // 已在地板上召唤了魔法立方块。
                    return false;
                }
            }

            // 觉醒状态 - 非觉醒技能无法使用
            if (((pc.getAwakeSkillId() == AWAKEN_ANTHARAS) && (this._skillId != AWAKEN_ANTHARAS))
                    || ((pc.getAwakeSkillId() == AWAKEN_FAFURION) && (this._skillId != AWAKEN_FAFURION))
                    || (((pc.getAwakeSkillId() == AWAKEN_VALAKAS) && (this._skillId != AWAKEN_VALAKAS))
                            && (this._skillId != MAGMA_BREATH)
                            && (this._skillId != SHOCK_SKIN) && (this._skillId != FREEZING_BREATH))) {
                pc.sendPackets(new S_ServerMessage(1385)); // 目前状态中无法使用觉醒魔法。
                return false;
            }

            // 法术消耗道具判断。
            if ((this.isItemConsume() == false) && !this._player.isGm()) {
                this._player.sendPackets(new S_ServerMessage(299)); // \f1施放魔法所需材料不足。
                return false;
            }
        }

        // 检查使用者技能对NPC的情况
        else if (this._user instanceof L1NpcInstance) {

            // 禁言状态无法使用
            if (this._user.hasSkillEffect(SILENCE)) {
                // NPC禁言状态只能有1回的效果。(只能使用一次)
                this._user.removeSkillEffect(SILENCE);
                return false;
            }
        }

        // PC、NPC共通检查HP、MP是否足够
        if (!this.isHPMPConsume()) { // 花费的HP、MP计算
            return false;
        }
        return true;
    }

    /**
     * 目标对象 是否为PC、召唤物、宠物
     * 
     * @param cha
     * @return
     */
    private boolean isPcSummonPet(final L1Character cha) {

        switch (this._calcType) {
            case PC_PC: // 对象为PC
                return true;

            case PC_NPC:
                if (cha instanceof L1SummonInstance) { // 对象召唤物
                    final L1SummonInstance summon = (L1SummonInstance) cha;
                    if (summon.isExsistMaster()) { // 有主人
                        return true;
                    }
                }
                if (cha instanceof L1PetInstance) { // 对象宠物
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * 判断目前状态能否使用魔法卷轴
     * 
     * @return false 不能使用
     */
    private boolean isSpellScrollUsable() {

        // 要使用魔法卷轴的PC
        final L1PcInstance pc = (L1PcInstance) this._user;

        // 传送中
        if (pc.isTeleport()) {
            return false;
        }

        // 麻痹・冻结状态
        if (pc.isParalyzed()) {
            return false;
        }

        // 隐身中无法使用
        if ((pc.isInvisble() || pc.isInvisDelay())
                && !this._skill.isInvisUsableSkill()) {
            return false;
        }

        return true;
    }

    /**
     * 可加入设置目标的判断
     * 
     * @param cha
     *            加入判断的目标物件
     * @return true:可加入目标 false:不可加入目标
     * @throws Exception
     */
    private boolean isTarget(final L1Character cha) throws Exception {

        // 角色为空
        if (cha == null) {
            return false;
        }

        // 目标为角色
        if (cha instanceof L1PcInstance) {
            final L1PcInstance pc = (L1PcInstance) cha;
            if (pc.isGhost() || pc.isGmInvis()) {
                return false;
            }
        }

        boolean _flg = false;

        // NPC 对 PC 目标为PC或PC的宠物召唤物
        if ((this._calcType == NPC_PC)
                && ((cha instanceof L1PcInstance)
                        || (cha instanceof L1PetInstance) || (cha instanceof L1SummonInstance))) {
            _flg = true;
        }

        // 不能破坏的门
        if (cha instanceof L1DoorInstance) {
            if ((cha.getMaxHp() == 0) || (cha.getMaxHp() == 1)) {
                return false;
            }
        }

        // 只能对魔法娃娃释放加速术
        if ((cha instanceof L1DollInstance) && (this._skillId != HASTE)) {
            return false;
        }

        // 宠物与召唤怪范围技能不可以攻击同组
        if (((this._user instanceof L1PetInstance) || (this._user instanceof L1SummonInstance))
                && ((this._skill.getArea() > 0) || (this._skillId == LIGHTNING))) {
            if ((this._user.glanceCheck(cha.getX(), cha.getY()))
                    || (this._skill.isThrough())) {
                return (cha instanceof L1MonsterInstance);
            }
        }

        // 元のターゲットがPet、Summon以外のNPCの场合、PC、Pet、Summonは对象外
        if ((this._calcType == PC_NPC)
                && (this._target instanceof L1NpcInstance)
                && !(this._target instanceof L1PetInstance)
                && !(this._target instanceof L1SummonInstance)
                && ((cha instanceof L1PetInstance)
                        || (cha instanceof L1SummonInstance) || (cha instanceof L1PcInstance))) {
            return false;
        }

        // 元のターゲットがガード以外のNPCの场合、ガードは对象外
        if ((this._calcType == PC_NPC)
                && (this._target instanceof L1NpcInstance)
                && !(this._target instanceof L1GuardInstance)
                && (cha instanceof L1GuardInstance)) {
            return false;
        }

        // NPC对PCでターゲットがモンスターの场合ターゲットではない。
        if ((this._skill.getTarget().equals("attack") || (this._skill.getType() == L1Skills.TYPE_ATTACK))
                && (this._calcType == NPC_PC)
                && !(cha instanceof L1PetInstance)
                && !(cha instanceof L1SummonInstance)
                && !(cha instanceof L1PcInstance)) {
            return false;
        }

        // NPC对NPCで使用者がMOBで、ターゲットがMOBの场合ターゲットではない。
        if ((this._skill.getTarget().equals("attack") || (this._skill.getType() == L1Skills.TYPE_ATTACK))
                && (this._calcType == NPC_NPC)
                && (this._user instanceof L1MonsterInstance)
                && (cha instanceof L1MonsterInstance)) {
            return false;
        }

        // 无方向范围攻击魔法 不能攻击NPC对象外
        if (this._skill.getTarget().equals("none")
                && (this._skill.getType() == L1Skills.TYPE_ATTACK)
                && ((cha instanceof L1AuctionBoardInstance)
                        || (cha instanceof L1BoardInstance)
                        || (cha instanceof L1CrownInstance)
                        || (cha instanceof L1DwarfInstance)
                        || (cha instanceof L1EffectInstance)
                        || (cha instanceof L1FieldObjectInstance)
                        || (cha instanceof L1FurnitureInstance)
                        || (cha instanceof L1HousekeeperInstance)
                        || (cha instanceof L1MerchantInstance) || (cha instanceof L1TeleporterInstance))) {
            return false;
        }

        // 攻击型魔法无法攻击自己
        if ((this._skill.getType() == L1Skills.TYPE_ATTACK)
                && (cha.getId() == this._user.getId())) {
            return false;
        }

        // 体力回复术判断施法者不补血
        if ((cha.getId() == this._user.getId()) && (this._skillId == HEAL_ALL)) {
            return false;
        }

        if ((((this._skill.getTargetTo() & L1Skills.TARGET_TO_PC) == L1Skills.TARGET_TO_PC)
                || ((this._skill.getTargetTo() & L1Skills.TARGET_TO_CLAN) == L1Skills.TARGET_TO_CLAN) || ((this._skill
                .getTargetTo() & L1Skills.TARGET_TO_PARTY) == L1Skills.TARGET_TO_PARTY))
                && (cha.getId() == this._user.getId())
                && (this._skillId != HEAL_ALL)) {
            return true; // ターゲットがパーティーかクラン员のものは自分に效果がある。（ただし、ヒールオールは除外）
        }

        // 技能使用者为PC、不是PK模式、自己召唤的宠物以外
        if ((this._user instanceof L1PcInstance)
                && (this._skill.getTarget().equals("attack") || (this._skill
                        .getType() == L1Skills.TYPE_ATTACK))
                && (this._isPK == false)) {
            // 目标为召唤物
            if (cha instanceof L1SummonInstance) {
                final L1SummonInstance summon = (L1SummonInstance) cha;
                // 自己的召唤物
                if (this._player.getId() == summon.getMaster().getId()) {
                    return false;
                }
            }
            // 目标为宠物
            else if (cha instanceof L1PetInstance) {
                final L1PetInstance pet = (L1PetInstance) cha;
                // 自己的宠物
                if (this._player.getId() == pet.getMaster().getId()) {
                    return false;
                }
            }
        }

        if ((this._skill.getTarget().equals("attack") || (this._skill.getType() == L1Skills.TYPE_ATTACK))
                // 目标不是怪物
                && !(cha instanceof L1MonsterInstance)
                // 不是PK状态
                && (this._isPK == false)
                // 目标是人物
                && (this._target instanceof L1PcInstance)) {
            final L1PcInstance enemy = (L1PcInstance) cha;
            // 强力无所遁形术
            if ((this._skillId == COUNTER_DETECTION)
                    && (enemy.getZoneType() != 1)
                    && (cha.hasSkillEffect(INVISIBILITY) || cha
                            .hasSkillEffect(BLIND_HIDING))) {
                return true; // 隐身术或暗隐术中
            }
            if ((this._player.getClanid() != 0) && (enemy.getClanid() != 0)) { // 有血盟
                // 取得全部战争列表
                for (final L1War war : L1World.getInstance().getWarList()) {
                    if (war.CheckClanInWar(this._player.getClanname())) { // 自己在盟战中
                        if (war.CheckClanInSameWar( // 俩血盟在同一场战争中
                                this._player.getClanname(), enemy.getClanname())) {
                            if (L1CastleLocation.checkInAllWarArea(
                                    enemy.getX(), enemy.getY(),
                                    enemy.getMapId())) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false; // 攻击スキルでPKモードじゃない场合
        }

        if ((this._user.glanceCheck(cha.getX(), cha.getY()) == false)
                && (this._skill.isThrough() == false)) {
            // 变形术、复活术判断有无障碍物
            if (!((this._skill.getType() == L1Skills.TYPE_CHANGE) || (this._skill
                    .getType() == L1Skills.TYPE_RESTORE))) {
                this._isGlanceCheckFail = true;
                return false; // 直线距离上有障碍物
            }
        }

        if (cha.hasSkillEffect(ICE_LANCE)
                || cha.hasSkillEffect(FREEZING_BLIZZARD)
                || cha.hasSkillEffect(FREEZING_BREATH)
                || cha.hasSkillEffect(ICE_LANCE_COCKATRICE)
                || cha.hasSkillEffect(ICE_LANCE_BASILISK)) {
            if ((this._skillId == ICE_LANCE)
                    || (this._skillId == FREEZING_BLIZZARD)
                    || (this._skillId == FREEZING_BREATH)
                    || (this._skillId == ICE_LANCE_COCKATRICE)
                    || (this._skillId == ICE_LANCE_BASILISK)) {
                return false;
            }
        }

        // 大地屏障不能连续使用
        if (cha.hasSkillEffect(EARTH_BIND) && (this._skillId == EARTH_BIND)) {
            return false;
        }

        // 目标不是怪物 不能使用 迷魅或造尸
        if (!(cha instanceof L1MonsterInstance)
                && ((this._skillId == TAMING_MONSTER) || (this._skillId == CREATE_ZOMBIE))) {
            return false;
        }

        // 目标已死亡 法术非复活类
        if (cha.isDead()
                && ((this._skillId != CREATE_ZOMBIE)
                        && (this._skillId != RESURRECTION)
                        && (this._skillId != GREATER_RESURRECTION) && (this._skillId != CALL_OF_NATURE))) {
            return false;
        }

        // 目标未死亡 法术复活类
        if ((cha.isDead() == false)
                && ((this._skillId == CREATE_ZOMBIE)
                        || (this._skillId == RESURRECTION)
                        || (this._skillId == GREATER_RESURRECTION) || (this._skillId == CALL_OF_NATURE))) {
            return false;
        }

        // 塔跟门不可放复活法术
        if (((cha instanceof L1TowerInstance) || (cha instanceof L1DoorInstance))
                && ((this._skillId == CREATE_ZOMBIE)
                        || (this._skillId == RESURRECTION)
                        || (this._skillId == GREATER_RESURRECTION) || (this._skillId == CALL_OF_NATURE))) {
            return false;
        }

        // 对绝对屏障有效的魔法
        if (cha instanceof L1PcInstance) {
            final L1PcInstance pc = (L1PcInstance) cha;
            if (pc.hasSkillEffect(ABSOLUTE_BARRIER)) { // 绝对屏障状态中
                switch (this._skillId) {
                    case CURSE_BLIND:
                    case WEAPON_BREAK:
                    case DARKNESS:
                    case WEAKNESS:
                    case DISEASE:
                    case FOG_OF_SLEEPING:
                    case MASS_SLOW:
                    case SLOW:
                    case CANCELLATION:
                    case SILENCE:
                    case DECAY_POTION:
                    case MASS_TELEPORT:
                    case DETECTION:
                    case COUNTER_DETECTION:
                    case ERASE_MAGIC:
                    case ENTANGLE:
                    case PHYSICAL_ENCHANT_DEX:
                    case PHYSICAL_ENCHANT_STR:
                    case BLESS_WEAPON:
                    case EARTH_SKIN:
                    case IMMUNE_TO_HARM:
                    case REMOVE_CURSE:
                        return true;
                    default:
                        return false;
                }
            }
        }

        // 对隐藏状态的怪物有效的魔法 (钻地)
        if (cha instanceof L1NpcInstance) {
            final int hiddenStatus = ((L1NpcInstance) cha).getHiddenStatus();
            switch (hiddenStatus) {
                case L1NpcInstance.HIDDEN_STATUS_SINK:
                    switch (this._skillId) {
                        case DETECTION:
                        case COUNTER_DETECTION: // 无所遁形、强力无所遁形
                            return true;
                    }
                    return false;

                case L1NpcInstance.HIDDEN_STATUS_FLY: // 对飞天的怪物无效
                    return false;
            }
        }

        // 目标PC
        if (((this._skill.getTargetTo() & L1Skills.TARGET_TO_PC) == L1Skills.TARGET_TO_PC)
                && (cha instanceof L1PcInstance)) {
            _flg = true;
        }
        // 目标NPC
        else if (((this._skill.getTargetTo() & L1Skills.TARGET_TO_NPC) == L1Skills.TARGET_TO_NPC)
                && ((cha instanceof L1MonsterInstance)
                        || (cha instanceof L1NpcInstance)
                        || (cha instanceof L1SummonInstance) || (cha instanceof L1PetInstance))) {
            _flg = true;
        }
        // 目标 召唤怪与宠物
        else if (((this._skill.getTargetTo() & L1Skills.TARGET_TO_PET) == L1Skills.TARGET_TO_PET)
                && (this._user instanceof L1PcInstance)) {
            if (cha instanceof L1SummonInstance) {
                final L1SummonInstance summon = (L1SummonInstance) cha;
                if (summon.getMaster() != null) {
                    if (this._player.getId() == summon.getMaster().getId()) {
                        _flg = true;
                    }
                }
            } else if (cha instanceof L1PetInstance) {
                final L1PetInstance pet = (L1PetInstance) cha;
                if (pet.getMaster() != null) {
                    if (this._player.getId() == pet.getMaster().getId()) {
                        _flg = true;
                    }
                }
            }
        }

        if ((this._calcType == PC_PC) && (cha instanceof L1PcInstance)) {
            // 目标血盟
            if (((this._skill.getTargetTo() & L1Skills.TARGET_TO_CLAN) == L1Skills.TARGET_TO_CLAN)
                    && (((this._player.getClanid() != 0) && (this._player
                            .getClanid() == ((L1PcInstance) cha).getClanid())) || this._player
                            .isGm())) {
                return true;
            }
            // 目标组队
            if (((this._skill.getTargetTo() & L1Skills.TARGET_TO_PARTY) == L1Skills.TARGET_TO_PARTY)
                    && (this._player.getParty().isMember((L1PcInstance) cha) || this._player
                            .isGm())) {
                return true;
            }
        }

        return _flg;
    }

    /**
     * 目标判定
     * 
     * @param cha
     * @param cha
     * @return
     */
    private boolean isTargetCalc(final L1Character cha) {
        // 三重矢、屠宰者、暴击、骷髅毁坏
        if ((this._user instanceof L1PcInstance)
                && ((this._skillId == TRIPLE_ARROW)
                        || (this._skillId == FOE_SLAYER)
                        || (this._skillId == SMASH) || (this._skillId == BONE_BREAK))) {
            return true;
        }
        // 攻击魔法のNon－PvP判定
        if (this._skill.getTarget().equals("attack")
                && (this._skillId != TURN_UNDEAD)) { // 攻击魔法
            if (this.isPcSummonPet(cha)) { // 对象为PC、召唤、宠物
                if ((this._player.getZoneType() == 1)
                        || (cha.getZoneType() == 1 // 攻击方或被攻击方在安全区
                        ) || this._player.checkNonPvP(this._player, cha)) { // Non-PvP设定
                    return false;
                }
            }
        }

        // 沉睡之雾的对象是自己
        if ((this._skillId == FOG_OF_SLEEPING)
                && (this._user.getId() == cha.getId())) {
            return false;
        }

        // 集体缓速对自己和自己的宠物
        if (this._skillId == MASS_SLOW) {
            if (this._user.getId() == cha.getId()) {
                return false;
            }
            if (cha instanceof L1SummonInstance) {
                final L1SummonInstance summon = (L1SummonInstance) cha;
                if (this._user.getId() == summon.getMaster().getId()) {
                    return false;
                }
            } else if (cha instanceof L1PetInstance) {
                final L1PetInstance pet = (L1PetInstance) cha;
                if (this._user.getId() == pet.getMaster().getId()) {
                    return false;
                }
            }
        }

        // 集体传送的对象只是自己（同血盟成员可以传送）
        if (this._skillId == MASS_TELEPORT) {
            if (this._user.getId() != cha.getId()) {
                return false;
            }
        }

        return true;
    }

    /**
     * 返回目标失败
     * 
     * @param cha
     */
    private boolean isTargetFailure(final L1Character cha) {
        boolean isTU = false;
        boolean isErase = false;
        boolean isManaDrain = false;
        int undeadType = 0;

        if ((cha instanceof L1TowerInstance) || (cha instanceof L1DoorInstance)) { // 守护塔、门
                                                                                   // 确率系技能无效
            return true;
        }

        if (cha instanceof L1PcInstance) { // 对PCの场合
            if ((this._calcType == PC_PC)
                    && this._player.checkNonPvP(this._player, cha)) { // Non-PvP设定
                final L1PcInstance pc = (L1PcInstance) cha;
                if ((this._player.getId() == pc.getId())
                        || ((pc.getClanid() != 0) && (this._player.getClanid() == pc
                                .getClanid()))) {
                    return false;
                }
                return true;
            }
            return false;
        }

        if (cha instanceof L1MonsterInstance) { // ターンアンデット可能か判定
            isTU = ((L1MonsterInstance) cha).getNpcTemplate().get_IsTU();
        }

        if (cha instanceof L1MonsterInstance) { // イレースマジック可能か判定
            isErase = ((L1MonsterInstance) cha).getNpcTemplate().get_IsErase();
        }

        if (cha instanceof L1MonsterInstance) { // アンデットの判定
            undeadType = ((L1MonsterInstance) cha).getNpcTemplate()
                    .get_undead();
        }

        // マナドレインが可能か？
        if (cha instanceof L1MonsterInstance) {
            isManaDrain = true;
        }
        /*
         * 成功除外条件１：T-Uが成功したが、对象がアンデットではない。 成功除外条件２：T-Uが成功したが、对象にはターンアンデット无效。
         * 成功除外条件３：スロー、マススロー、マナドレイン、エンタングル、イレースマジック、ウィンドシャックル无效
         * 成功除外条件４：マナドレインが成功したが、モンスター以外の场合
         */
        if (((this._skillId == TURN_UNDEAD) && ((undeadType == 0) || (undeadType == 2)))
                || ((this._skillId == TURN_UNDEAD) && (isTU == false))
                || (((this._skillId == ERASE_MAGIC) || (this._skillId == SLOW)
                        || (this._skillId == MANA_DRAIN)
                        || (this._skillId == MASS_SLOW)
                        || (this._skillId == ENTANGLE) || (this._skillId == WIND_SHACKLE)) && (isErase == false))
                || ((this._skillId == MANA_DRAIN) && (isManaDrain == false))) {
            return true;
        }
        return false;
    }

    /**
     * 魔法屏障发动判断
     * 
     * @param cha
     */
    private boolean isUseCounterMagic(final L1Character cha) {
        if (this._isCounterMagic && cha.hasSkillEffect(COUNTER_MAGIC)) {
            cha.removeSkillEffect(COUNTER_MAGIC);
            final int castgfx = SkillsTable.getInstance()
                    .getTemplate(COUNTER_MAGIC).getCastGfx();
            cha.broadcastPacket(new S_SkillSound(cha.getId(), castgfx));
            if (cha instanceof L1PcInstance) {
                final L1PcInstance pc = (L1PcInstance) cha;
                pc.sendPackets(new S_SkillSound(pc.getId(), castgfx));
            }
            return true;
        }
        return false;
    }

    /**
     * 技能发动 目标清单判定
     */
    private void makeTargetList() {
        try {
            if (this._type == TYPE_LOGIN) { // 仅用于登录时 (死亡时 包括取消死亡状态？)
                this._targetList.add(new TargetStatus(this._user));
                return;
            }
            if ((this._skill.getTargetTo() == L1Skills.TARGET_TO_ME)
                    && ((this._skill.getType() & L1Skills.TYPE_ATTACK) != L1Skills.TYPE_ATTACK)) {
                this._targetList.add(new TargetStatus(this._user)); // 目标使用者
                return;
            }

            // 具有攻击范围 (画面内的对象)
            if (this.getSkillRanged() != -1) {
                if (this._user.getLocation().getTileLineDistance(
                        this._target.getLocation()) > this.getSkillRanged()) {
                    return; // 射程范围外
                }
            } else {
                if (!this._user.getLocation().isInScreen(
                        this._target.getLocation())) {
                    return; // 射程范围外
                }
            }

            if ((this.isTarget(this._target) == false)
                    && !(this._skill.getTarget().equals("none"))) {
                // 对象が违うのでスキルが発动しない。
                return;
            }

            // 直线的范围
            switch (this._skillId) {
                case LIGHTNING:
                case FREEZING_BREATH: // 极光雷电、寒冰喷吐
                    final List<L1Object> al1object = L1World.getInstance()
                            .getVisibleLineObjects(this._user, this._target);

                    for (final L1Object tgobj : al1object) {
                        if (tgobj == null) {
                            continue;
                        }
                        if (!(tgobj instanceof L1Character)) { // 角色没有目标的场合。
                            continue;
                        }
                        final L1Character cha = (L1Character) tgobj;
                        if (this.isTarget(cha) == false) {
                            continue;
                        }
                        this._targetList.add(new TargetStatus(cha));
                    }
                    return;
            }

            // 单体攻击
            if (this.getSkillArea() == 0) {
                if (!this._user.glanceCheck(this._target.getX(),
                        this._target.getY())) { // 直线上有障碍物
                    if (((this._skill.getType() & L1Skills.TYPE_ATTACK) == L1Skills.TYPE_ATTACK)
                            && (this._skillId != 10026)
                            && (this._skillId != 10027)
                            && (this._skillId != 10028)
                            && (this._skillId != 10029)) { // 安息攻击以外の攻击スキル
                        this._targetList.add(new TargetStatus(this._target,
                                false)); // 不能发生伤害、ダメージモーションも発生しないが、スキルは発动
                        return;
                    }
                }
                this._targetList.add(new TargetStatus(this._target));
            }

            // 范围攻击
            else {
                if (!this._skill.getTarget().equals("none")) {
                    this._targetList.add(new TargetStatus(this._target));
                }

                // 法师魔法 (体力回复术)
                if ((this._skillId != HEAL_ALL)
                        && !(this._skill.getTarget().equals("attack") || (this._skill
                                .getType() == L1Skills.TYPE_ATTACK))) {
                    // 攻击系以外のスキルとH-A以外はターゲット自身を含める
                    this._targetList.add(new TargetStatus(this._user));
                }

                List<L1Object> objects;

                // 全画面物件
                if (this.getSkillArea() == -1) {
                    objects = L1World.getInstance().getVisibleObjects(
                            this._user);
                }
                // 指定范围物件
                else {
                    objects = L1World.getInstance().getVisibleObjects(
                            this._target, this.getSkillArea());
                }
                for (final L1Object tgobj : objects) {
                    if (tgobj == null) {
                        continue;
                    }
                    if (!(tgobj instanceof L1Character)) { // ターゲットがキャラクター以外の场合何もしない。
                        continue;
                    }
                    final L1Character cha = (L1Character) tgobj;
                    if (!this.isTarget(cha)) {
                        continue;
                    }

                    // 技能发动 目标清单判定:加入目标清单 - 回圈
                    this._targetList.add(new TargetStatus(cha));
                }
                return;
            }

        } catch (final Exception e) {
            _log.log(Level.FINEST, "exception in L1Skilluse makeTargetList{0}",
                    e);
        }
    }

    /**
     * 发动技能效果
     */
    private void runSkill() {

        switch (this._skillId) {
            case LIFE_STREAM: // 治愈能量风暴
                L1EffectSpawn.getInstance().spawnEffect(81169,
                        this._skill.getBuffDuration() * 1000, this._targetX,
                        this._targetY, this._user.getMapId());
                return;
            case CUBE_IGNITION: // 立方:燃烧
                L1EffectSpawn.getInstance().spawnEffect(80149,
                        this._skill.getBuffDuration() * 1000, this._targetX,
                        this._targetY, this._user.getMapId(),
                        (L1PcInstance) this._user, this._skillId);
                return;
            case CUBE_QUAKE: // 立方:地裂
                L1EffectSpawn.getInstance().spawnEffect(80150,
                        this._skill.getBuffDuration() * 1000, this._targetX,
                        this._targetY, this._user.getMapId(),
                        (L1PcInstance) this._user, this._skillId);
                return;
            case CUBE_SHOCK: // 立方:冲击
                L1EffectSpawn.getInstance().spawnEffect(80151,
                        this._skill.getBuffDuration() * 1000, this._targetX,
                        this._targetY, this._user.getMapId(),
                        (L1PcInstance) this._user, this._skillId);
                return;
            case CUBE_BALANCE: // 立方:和谐
                L1EffectSpawn.getInstance().spawnEffect(80152,
                        this._skill.getBuffDuration() * 1000, this._targetX,
                        this._targetY, this._user.getMapId(),
                        (L1PcInstance) this._user, this._skillId);
                return;
            case FIRE_WALL: // 火牢
                L1EffectSpawn.getInstance().doSpawnFireWall(this._user,
                        this._targetX, this._targetY);
                return;
            case BLKS_FIRE_WALL: // 巴拉卡斯火牢
                this._user.setSkillEffect(this._skillId, 11 * 1000);
                L1EffectSpawn.getInstance().doSpawnFireWallforNpc(this._user,
                        this._target);
                return;
            case TRUE_TARGET: // 精准目标
                if (this._user instanceof L1PcInstance) {
                    final L1PcInstance pri = (L1PcInstance) this._user;
                    final L1EffectInstance effect = L1EffectSpawn.getInstance()
                            .spawnEffect(80153, 5 * 1000, this._targetX + 2,
                                    this._targetY - 1, this._user.getMapId());
                    if (this._targetID != 0) {
                        pri.sendPackets(new S_TrueTarget(this._targetID, pri
                                .getId(), this._message));
                        if (pri.getClanid() != 0) {
                            final L1PcInstance players[] = L1World
                                    .getInstance().getClan(pri.getClanname())
                                    .getOnlineClanMember();
                            for (final L1PcInstance pc : players) {
                                pc.sendPackets(new S_TrueTarget(this._targetID,
                                        pc.getId(), this._message));
                            }
                        }
                    } else if (effect != null) {
                        pri.sendPackets(new S_TrueTarget(effect.getId(), pri
                                .getId(), this._message));
                        if (pri.getClanid() != 0) {
                            final L1PcInstance players[] = L1World
                                    .getInstance().getClan(pri.getClanname())
                                    .getOnlineClanMember();
                            for (final L1PcInstance pc : players) {
                                pc.sendPackets(new S_TrueTarget(effect.getId(),
                                        pc.getId(), this._message));
                            }
                        }
                    }
                }
                return;
            default:
                break;
        }

        // 魔法屏障不可抵挡的魔法
        for (final int skillId : EXCEPT_COUNTER_MAGIC) {
            if (this._skillId == skillId) {
                this._isCounterMagic = false; // 无效
                break;
            }
        }

        // NPCにショックスタンを使用させるとonActionでNullPointerExceptionが発生するため
        // とりあえずPCが使用した时のみ
        if ((this._skillId == SHOCK_STUN)
                && (this._user instanceof L1PcInstance)) {
            this._target.onAction(this._player);
        }

        if (!this.isTargetCalc(this._target)) {
            return;
        }

        try {
            TargetStatus ts = null;
            L1Character cha = null;
            int dmg = 0;
            int drainMana = 0;
            int heal = 0;
            boolean isSuccess = false;
            int undeadType = 0;

            for (final Iterator<TargetStatus> iter = this._targetList
                    .iterator(); iter.hasNext();) {
                ts = null;
                cha = null;
                dmg = 0;
                heal = 0;
                isSuccess = false;
                undeadType = 0;

                ts = iter.next();
                cha = ts.getTarget();

                if (!ts.isCalc() || !this.isTargetCalc(cha)) {
                    continue; // 计算する必要がない。
                }

                final L1Magic _magic = new L1Magic(this._user, cha);
                _magic.setLeverage(this.getLeverage());

                if (cha instanceof L1MonsterInstance) { // 不死系判断
                    undeadType = ((L1MonsterInstance) cha).getNpcTemplate()
                            .get_undead();
                }

                // 确率系技能失败确定
                if (((this._skill.getType() == L1Skills.TYPE_CURSE) || (this._skill
                        .getType() == L1Skills.TYPE_PROBABILITY))
                        && this.isTargetFailure(cha)) {
                    iter.remove();
                    continue;
                }

                if (cha instanceof L1PcInstance) { // 只有在目标为PC的情况下发送图标，代表使用成功。
                    if (this._skillTime == 0) {
                        this._getBuffIconDuration = this._skill
                                .getBuffDuration(); // 效果时间
                    } else {
                        this._getBuffIconDuration = this._skillTime; // パラメータのtimeが0以外なら、效果时间として设定する
                    }
                }

                this.deleteRepeatedSkills(cha); // 删除无法共同存在的魔法状态

                // 攻击系技能和使用者除外。
                if ((this._skill.getType() == L1Skills.TYPE_ATTACK)
                        && (this._user.getId() != cha.getId())) {
                    if (this.isUseCounterMagic(cha)) { // カウンターマジックが発动した场合、リストから削除
                        iter.remove();
                        continue;
                    }
                    dmg = _magic.calcMagicDamage(this._skillId);
                    this._dmg = dmg;
                    cha.removeSkillEffect(ERASE_MAGIC); // イレースマジック中なら、攻击魔法で解除
                } else if ((this._skill.getType() == L1Skills.TYPE_CURSE)
                        || (this._skill.getType() == L1Skills.TYPE_PROBABILITY)) { // 确率系スキル
                    isSuccess = _magic.calcProbabilityMagic(this._skillId);
                    if (this._skillId != ERASE_MAGIC) {
                        cha.removeSkillEffect(ERASE_MAGIC); // イレースマジック中なら、确率魔法で解除
                    }
                    if (this._skillId != FOG_OF_SLEEPING) {
                        cha.removeSkillEffect(FOG_OF_SLEEPING); // フォグオブスリーピング中なら、确率魔法で解除
                    }
                    if (isSuccess) { // 成功したがカウンターマジックが発动した场合、リストから削除
                        if (this.isUseCounterMagic(cha)) { // カウンターマジックが発动したか
                            iter.remove();
                            continue;
                        }
                    } else { // 失败した场合、リストから削除
                        if ((this._skillId == FOG_OF_SLEEPING)
                                && (cha instanceof L1PcInstance)) {
                            final L1PcInstance pc = (L1PcInstance) cha;
                            pc.sendPackets(new S_ServerMessage(297)); // 你感觉些微地晕眩。
                        }
                        iter.remove();
                        continue;
                    }
                }

                // 治愈性魔法
                else if (this._skill.getType() == L1Skills.TYPE_HEAL) {
                    // 回复量
                    dmg = -1 * _magic.calcHealing(this._skillId);
                    if (cha.hasSkillEffect(WATER_LIFE)) { // 水之元气-效果 2倍
                        dmg *= 2;
                        cha.killSkillEffectTimer(WATER_LIFE); // 效果只有一次
                        if (cha instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) cha;
                            pc.sendPackets(new S_SkillIconWaterLife());
                        }
                    }
                    if (cha.hasSkillEffect(POLLUTE_WATER)) { // 污浊之水-效果减半
                        dmg /= 2;
                    }
                }
                // 显示团体魔法效果在队友或盟友
                else if (((this._skillId == FIRE_BLESS)
                        || (this._skillId == STORM_EYE // 烈炎气息、暴风之眼
                        ) || (this._skillId == EARTH_BLESS // 大地的祝福
                        ) || (this._skillId == GLOWING_AURA // 激励士气
                        ) || (this._skillId == SHINING_AURA) || (this._skillId == BRAVE_AURA)) // 钢铁士气、冲击士气
                        && (this._user.getId() != cha.getId())) {
                    if (cha instanceof L1PcInstance) {
                        final L1PcInstance _targetPc = (L1PcInstance) cha;
                        _targetPc.sendPackets(new S_SkillSound(_targetPc
                                .getId(), this._skill.getCastGfx()));
                        _targetPc.broadcastPacket(new S_SkillSound(_targetPc
                                .getId(), this._skill.getCastGfx()));
                    }
                }

                // ■■■■ 个别处理のあるスキルのみ书いてください。 ■■■■

                // 除了冲晕、骷髅毁坏之外魔法效果存在时，只更新效果时间跟图示。
                if (cha.hasSkillEffect(this._skillId)
                        && ((this._skillId != SHOCK_STUN)
                                && (this._skillId != BONE_BREAK)
                                && (this._skillId != CONFUSION) && (this._skillId != THUNDER_GRAB))) {
                    this.addMagicList(cha, true); // 魔法效果已存在时
                    if (this._skillId != SHAPE_CHANGE) { // 除了变形术之外
                        continue;
                    }
                }

                switch (this._skillId) {
                    // 加速术
                    case HASTE:
                        if (cha.getMoveSpeed() != 2) { // 缓速中以外
                            if (cha instanceof L1PcInstance) {
                                final L1PcInstance pc = (L1PcInstance) cha;
                                if (pc.getHasteItemEquipped() > 0) {
                                    continue;
                                }
                                pc.setDrink(false);
                                pc.sendPackets(new S_SkillHaste(pc.getId(), 1,
                                        this._getBuffIconDuration));
                            }
                            cha.broadcastPacket(new S_SkillHaste(cha.getId(),
                                    1, 0));
                            cha.setMoveSpeed(1);
                        } else { // 缓速中
                            int skillNum = 0;
                            if (cha.hasSkillEffect(SLOW)) {
                                skillNum = SLOW;
                            } else if (cha.hasSkillEffect(MASS_SLOW)) {
                                skillNum = MASS_SLOW;
                            } else if (cha.hasSkillEffect(ENTANGLE)) {
                                skillNum = ENTANGLE;
                            }
                            if (skillNum != 0) {
                                cha.removeSkillEffect(skillNum);
                                cha.removeSkillEffect(HASTE);
                                cha.setMoveSpeed(0);
                                continue;
                            }
                        }
                        break;
                    // 强力加速术
                    case GREATER_HASTE:
                        if (cha instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) cha;
                            if (pc.getHasteItemEquipped() > 0) {
                                continue;
                            }
                            if (pc.getMoveSpeed() != 2) { // 缓速中以外
                                pc.setDrink(false);
                                pc.setMoveSpeed(1);
                                pc.sendPackets(new S_SkillHaste(pc.getId(), 1,
                                        this._getBuffIconDuration));
                                pc.broadcastPacket(new S_SkillHaste(pc.getId(),
                                        1, 0));
                            } else { // 缓速中
                                int skillNum = 0;
                                if (pc.hasSkillEffect(SLOW)) {
                                    skillNum = SLOW;
                                } else if (pc.hasSkillEffect(MASS_SLOW)) {
                                    skillNum = MASS_SLOW;
                                } else if (pc.hasSkillEffect(ENTANGLE)) {
                                    skillNum = ENTANGLE;
                                }
                                if (skillNum != 0) {
                                    pc.removeSkillEffect(skillNum);
                                    pc.removeSkillEffect(GREATER_HASTE);
                                    pc.setMoveSpeed(0);
                                    continue;
                                }
                            }
                        }
                        break;
                    // 缓速术、集体缓速术、地面障碍
                    case SLOW:
                    case MASS_SLOW:
                    case ENTANGLE:
                        if (cha instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) cha;
                            if (pc.getHasteItemEquipped() > 0) {
                                continue;
                            }
                        }
                        if (cha.getMoveSpeed() == 0) {
                            if (cha instanceof L1PcInstance) {
                                final L1PcInstance pc = (L1PcInstance) cha;
                                pc.sendPackets(new S_SkillHaste(pc.getId(), 2,
                                        this._getBuffIconDuration));
                            }
                            cha.broadcastPacket(new S_SkillHaste(cha.getId(),
                                    2, this._getBuffIconDuration));
                            cha.setMoveSpeed(2);
                        } else if (cha.getMoveSpeed() == 1) {
                            int skillNum = 0;
                            if (cha.hasSkillEffect(HASTE)) {
                                skillNum = HASTE;
                            } else if (cha.hasSkillEffect(GREATER_HASTE)) {
                                skillNum = GREATER_HASTE;
                            } else if (cha.hasSkillEffect(STATUS_HASTE)) {
                                skillNum = STATUS_HASTE;
                            }
                            if (skillNum != 0) {
                                cha.removeSkillEffect(skillNum);
                                cha.removeSkillEffect(this._skillId);
                                cha.setMoveSpeed(0);
                                continue;
                            }
                        }
                        break;
                    // 寒冷战栗、吸血鬼之吻
                    case CHILL_TOUCH:
                    case VAMPIRIC_TOUCH:
                        heal = dmg;
                        break;
                    // 亚力安冰矛围篱
                    case ICE_LANCE_COCKATRICE:
                        // 邪恶蜥蜴冰矛围篱
                    case ICE_LANCE_BASILISK:
                        // 冰毛围篱、冰雪飓风、寒冰喷吐
                    case ICE_LANCE:
                    case FREEZING_BLIZZARD:
                    case FREEZING_BREATH:
                        this._isFreeze = _magic
                                .calcProbabilityMagic(this._skillId);
                        if (this._isFreeze) {
                            final int time = this._skill.getBuffDuration() * 1000;
                            L1EffectSpawn.getInstance().spawnEffect(81168,
                                    time, cha.getX(), cha.getY(),
                                    cha.getMapId());
                            if (cha instanceof L1PcInstance) {
                                final L1PcInstance pc = (L1PcInstance) cha;
                                pc.sendPackets(new S_Poison(pc.getId(), 2));
                                pc.broadcastPacket(new S_Poison(pc.getId(), 2));
                                pc.sendPackets(new S_Paralysis(
                                        S_Paralysis.TYPE_FREEZE, true));
                            } else if ((cha instanceof L1MonsterInstance)
                                    || (cha instanceof L1SummonInstance)
                                    || (cha instanceof L1PetInstance)) {
                                final L1NpcInstance npc = (L1NpcInstance) cha;
                                npc.broadcastPacket(new S_Poison(npc.getId(), 2));
                                npc.setParalyzed(true);
                                npc.setParalysisTime(time);
                            }
                        }
                        break;
                    // 大地屏障
                    case EARTH_BIND:
                        if (cha instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) cha;
                            pc.sendPackets(new S_Poison(pc.getId(), 2));
                            pc.broadcastPacket(new S_Poison(pc.getId(), 2));
                            pc.sendPackets(new S_Paralysis(
                                    S_Paralysis.TYPE_FREEZE, true));
                        } else if ((cha instanceof L1MonsterInstance)
                                || (cha instanceof L1SummonInstance)
                                || (cha instanceof L1PetInstance)) {
                            final L1NpcInstance npc = (L1NpcInstance) cha;
                            npc.broadcastPacket(new S_Poison(npc.getId(), 2));
                            npc.setParalyzed(true);
                            npc.setParalysisTime(this._skill.getBuffDuration() * 1000);
                        }
                        break;
                    case POISON_SMOG: // 毒雾-前方 3X3
                        this._user.setHeading(this._user.targetDirection(
                                this._targetX, this._targetY)); // 改变面向
                        int locX = 0;
                        int locY = 0;
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                switch (this._user.getHeading()) {
                                    case 0:
                                        locX = (-1 + j);
                                        locY = -1 * (-3 + i);
                                        break;
                                    case 1:
                                        locX = -1 * (2 + j - i);
                                        locY = -1 * (-4 + j + i);
                                        break;
                                    case 2:
                                        locX = -1 * (3 - i);
                                        locY = (-1 + j);
                                        break;
                                    case 3:
                                        locX = -1 * (4 - j - i);
                                        locY = -1 * (2 + j - i);
                                        break;
                                    case 4:
                                        locX = (1 - j);
                                        locY = -1 * (3 - i);
                                        break;
                                    case 5:
                                        locX = -1 * (-2 - j + i);
                                        locY = -1 * (4 - j - i);
                                        break;
                                    case 6:
                                        locX = -1 * (-3 + i);
                                        locY = (1 - j);
                                        break;
                                    case 7:
                                        locX = -1 * (-4 + j + i);
                                        locY = -1 * (-2 - j + i);
                                        break;
                                }
                                L1EffectSpawn.getInstance().spawnEffect(93002,
                                        10000, this._user.getX() - locX,
                                        this._user.getY() - locY,
                                        this._user.getMapId());
                            }
                        }
                        break;
                    // 冲击之晕
                    case SHOCK_STUN:
                        final int[] stunTimeArray = { 500, 1000, 1500, 2000,
                                2500, 3000 };
                        final int rnd = Random.nextInt(stunTimeArray.length);
                        this._shockStunDuration = stunTimeArray[rnd];
                        if ((cha instanceof L1PcInstance)
                                && cha.hasSkillEffect(SHOCK_STUN)) {
                            this._shockStunDuration += cha
                                    .getSkillEffectTimeSec(SHOCK_STUN) * 1000;
                        }

                        L1EffectSpawn.getInstance().spawnEffect(81162,
                                this._shockStunDuration, cha.getX(),
                                cha.getY(), cha.getMapId());
                        if (cha instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) cha;
                            pc.sendPackets(new S_Paralysis(
                                    S_Paralysis.TYPE_STUN, true));
                        } else if ((cha instanceof L1MonsterInstance)
                                || (cha instanceof L1SummonInstance)
                                || (cha instanceof L1PetInstance)) {
                            final L1NpcInstance npc = (L1NpcInstance) cha;
                            npc.setParalyzed(true);
                            npc.setParalysisTime(this._shockStunDuration);
                        }
                        break;
                    // 夺命之雷
                    case THUNDER_GRAB:
                        isSuccess = _magic.calcProbabilityMagic(this._skillId);
                        if (isSuccess) {
                            if (!cha.hasSkillEffect(THUNDER_GRAB_START)
                                    && !cha.hasSkillEffect(STATUS_FREEZE)) {
                                if (cha instanceof L1PcInstance) {
                                    final L1PcInstance pc = (L1PcInstance) cha;
                                    pc.sendPackets(new S_Paralysis(
                                            S_Paralysis.TYPE_BIND, true));
                                    pc.sendPackets(new S_SkillSound(pc.getId(),
                                            4184));
                                    pc.broadcastPacket(new S_SkillSound(pc
                                            .getId(), 4184));
                                } else if (cha instanceof L1NpcInstance) {
                                    final L1NpcInstance npc = (L1NpcInstance) cha;
                                    npc.setParalyzed(true);
                                    npc.broadcastPacket(new S_SkillSound(npc
                                            .getId(), 4184));
                                }
                                cha.setSkillEffect(THUNDER_GRAB_START, 500);
                            }
                        }
                        break;
                    // 起死回生术
                    case TURN_UNDEAD:
                        if ((undeadType == 1) || (undeadType == 3)) {
                            dmg = cha.getCurrentHp();
                        }
                        break;
                    // 魔力夺取
                    case MANA_DRAIN:
                        final int chance = Random.nextInt(10) + 5;
                        drainMana = chance + (this._user.getInt() / 2);
                        if (cha.getCurrentMp() < drainMana) {
                            drainMana = cha.getCurrentMp();
                        }
                        break;
                    // 指定传送、集体传送术
                    case TELEPORT:
                    case MASS_TELEPORT:
                        if (cha instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) cha;
                            final L1BookMark bookm = pc
                                    .getBookMark(this._bookmarkId);
                            if (bookm != null) { // 从书签中取出一个传送坐标
                                if (pc.getMap().isEscapable() || pc.isGm()) {
                                    final int newX = bookm.getLocX();
                                    final int newY = bookm.getLocY();
                                    final short mapId = bookm.getMapId();

                                    if (this._skillId == MASS_TELEPORT) { // 集体传送术
                                        final List<L1PcInstance> clanMember = L1World
                                                .getInstance()
                                                .getVisiblePlayer(pc);
                                        for (final L1PcInstance member : clanMember) {
                                            if ((pc.getLocation()
                                                    .getTileLineDistance(
                                                            member.getLocation()) <= 3)
                                                    && (member.getClanid() == pc
                                                            .getClanid())
                                                    && (pc.getClanid() != 0)
                                                    && (member.getId() != pc
                                                            .getId())) {
                                                L1Teleport.teleport(member,
                                                        newX, newY, mapId, 5,
                                                        true);
                                            }
                                        }
                                    }
                                    L1Teleport.teleport(pc, newX, newY, mapId,
                                            5, true);
                                } else {
                                    pc.sendPackets(new S_ServerMessage(79));
                                    pc.sendPackets(new S_Paralysis(
                                            S_Paralysis.TYPE_TELEPORT_UNLOCK,
                                            true));
                                }
                            } else { // ブックマークが取得出来なかった、あるいは“任意の场所”を选択した场合の处理
                                if (pc.getMap().isTeleportable() || pc.isGm()) {
                                    final L1Location newLocation = pc
                                            .getLocation().randomLocation(200,
                                                    true);
                                    final int newX = newLocation.getX();
                                    final int newY = newLocation.getY();
                                    final short mapId = (short) newLocation
                                            .getMapId();

                                    if (this._skillId == MASS_TELEPORT) {
                                        final List<L1PcInstance> clanMember = L1World
                                                .getInstance()
                                                .getVisiblePlayer(pc);
                                        for (final L1PcInstance member : clanMember) {
                                            if ((pc.getLocation()
                                                    .getTileLineDistance(
                                                            member.getLocation()) <= 3)
                                                    && (member.getClanid() == pc
                                                            .getClanid())
                                                    && (pc.getClanid() != 0)
                                                    && (member.getId() != pc
                                                            .getId())) {
                                                L1Teleport.teleport(member,
                                                        newX, newY, mapId, 5,
                                                        true);
                                            }
                                        }
                                    }
                                    L1Teleport.teleport(pc, newX, newY, mapId,
                                            5, true);
                                } else {
                                    pc.sendPackets(new S_ServerMessage(276)); // \f1在此无法使用传送。
                                    pc.sendPackets(new S_Paralysis(
                                            S_Paralysis.TYPE_TELEPORT_UNLOCK,
                                            true));
                                }
                            }
                        }
                        break;
                    // 呼唤盟友
                    case CALL_CLAN:
                        if (cha instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) cha;
                            final L1PcInstance clanPc = (L1PcInstance) L1World
                                    .getInstance().findObject(this._targetID);
                            if (clanPc != null) {
                                clanPc.setTempID(pc.getId());
                                clanPc.sendPackets(new S_Message_YN(729, "")); // 盟主正在呼唤你，你要接受他的呼唤吗？(Y/N)
                            }
                        }
                        break;
                    // 援护盟友
                    case RUN_CLAN:
                        if (cha instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) cha;
                            final L1PcInstance clanPc = (L1PcInstance) L1World
                                    .getInstance().findObject(this._targetID);
                            if (clanPc != null) {
                                if (pc.getMap().isEscapable() || pc.isGm()) {
                                    final boolean castle_area = L1CastleLocation
                                            .checkInAllWarArea(clanPc.getX(),
                                                    clanPc.getY(),
                                                    clanPc.getMapId());
                                    if (((clanPc.getMapId() == 0)
                                            || (clanPc.getMapId() == 4) || (clanPc
                                            .getMapId() == 304))
                                            && (castle_area == false)) {
                                        L1Teleport.teleport(pc, clanPc.getX(),
                                                clanPc.getY(),
                                                clanPc.getMapId(), 5, true);
                                    } else {
                                        pc.sendPackets(new S_ServerMessage(79));
                                    }
                                } else {
                                    // 这附近的能量影响到瞬间移动。在此地无法使用瞬间移动。
                                    pc.sendPackets(new S_ServerMessage(647));
                                    pc.sendPackets(new S_Paralysis(
                                            S_Paralysis.TYPE_TELEPORT_UNLOCK,
                                            true));
                                }
                            }
                        }
                        break;
                    // 强力无所遁形
                    case COUNTER_DETECTION:
                        if (cha instanceof L1PcInstance) {
                            dmg = _magic.calcMagicDamage(this._skillId);
                        } else if (cha instanceof L1NpcInstance) {
                            final L1NpcInstance npc = (L1NpcInstance) cha;
                            final int hiddenStatus = npc.getHiddenStatus();
                            if (hiddenStatus == L1NpcInstance.HIDDEN_STATUS_SINK) {
                                npc.appearOnGround(this._player);
                            } else {
                                dmg = 0;
                            }
                        } else {
                            dmg = 0;
                        }
                        break;
                    // 创造魔法武器
                    case CREATE_MAGICAL_WEAPON:
                        if (cha instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) cha;
                            final L1ItemInstance item = pc.getInventory()
                                    .getItem(this._itemobjid);
                            if ((item != null)
                                    && (item.getItem().getType2() == 1)) {
                                final int item_type = item.getItem().getType2();
                                final int safe_enchant = item.getItem()
                                        .get_safeenchant();
                                final int enchant_level = item
                                        .getEnchantLevel();
                                String item_name = item.getName();
                                if (safe_enchant < 0) { // 强化不可
                                    pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
                                } else if (safe_enchant == 0) { // 安全圏+0
                                    pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
                                } else if ((item_type == 1)
                                        && (enchant_level == 0)) {
                                    if (!item.isIdentified()) { // 未鉴定
                                        pc.sendPackets(new S_ServerMessage(161,
                                                item_name, "$245", "$247")); // \f1%0%s
                                                                             // %2
                                                                             // %1
                                                                             // 光芒。
                                    } else {
                                        item_name = "+0 " + item_name;
                                        pc.sendPackets(new S_ServerMessage(161,
                                                "+0 " + item_name, "$245",
                                                "$247")); // \f1%0%s
                                                          // %2
                                                          // %1
                                                          // 光芒。
                                    }
                                    item.setEnchantLevel(1);
                                    pc.getInventory().updateItem(item,
                                            L1PcInventory.COL_ENCHANTLVL);
                                } else {
                                    pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
                                }
                            } else {
                                pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
                            }
                        }
                        break;
                    // 提炼魔石
                    case BRING_STONE:
                        if (cha instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) cha;
                            final L1ItemInstance item = pc.getInventory()
                                    .getItem(this._itemobjid);
                            if (item != null) {
                                final int dark = (int) (10 + (pc.getLevel() * 0.8) + (pc
                                        .getWis() - 6) * 1.2);
                                final int brave = (int) (dark / 2.1);
                                final int wise = (int) (brave / 2.0);
                                final int kayser = (int) (wise / 1.9);
                                final int run = Random.nextInt(100) + 1;
                                final int oldItem = item.getItem().getItemId();
                                switch (oldItem) {
                                    case 40320: // 一级黑魔石
                                        pc.getInventory().removeItem(item, 1);
                                        if (dark >= run) {
                                            pc.getInventory().storeItem(40321,
                                                    1);
                                            pc.sendPackets(new S_ServerMessage(
                                                    403, "$2475")); // 获得%0%o 。
                                        } else {
                                            pc.sendPackets(new S_ServerMessage(
                                                    280)); // \f1施咒失败。
                                        }
                                        break;

                                    case 40321: // 二级黑魔石
                                        pc.getInventory().removeItem(item, 1);
                                        if (brave >= run) {
                                            pc.getInventory().storeItem(40322,
                                                    1);
                                            pc.sendPackets(new S_ServerMessage(
                                                    403, "$2476")); // 获得%0%o 。
                                        } else {
                                            pc.sendPackets(new S_ServerMessage(
                                                    280));// \f1施咒失败。
                                        }
                                        break;

                                    case 40322: // 三级黑魔石
                                        pc.getInventory().removeItem(item, 1);
                                        if (wise >= run) {
                                            pc.getInventory().storeItem(40323,
                                                    1);
                                            pc.sendPackets(new S_ServerMessage(
                                                    403, "$2477")); // 获得%0%o 。
                                        } else {
                                            pc.sendPackets(new S_ServerMessage(
                                                    280));// \f1施咒失败。
                                        }
                                        break;

                                    case 40323: // 四级黑魔石
                                        pc.getInventory().removeItem(item, 1);
                                        if (kayser >= run) {
                                            pc.getInventory().storeItem(40324,
                                                    1);
                                            pc.sendPackets(new S_ServerMessage(
                                                    403, "$2478")); // 获得%0%o 。
                                        } else {
                                            pc.sendPackets(new S_ServerMessage(
                                                    280));// \f1施咒失败。
                                        }
                                        break;

                                    default:
                                        break;
                                }
                            }
                        }
                        break;
                    // 日光术
                    case LIGHT:
                        if (cha instanceof L1PcInstance) {
                        }
                        break;
                    // 暗影之牙
                    case SHADOW_FANG:
                        if (cha instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) cha;
                            final L1ItemInstance item = pc.getInventory()
                                    .getItem(this._itemobjid);
                            if ((item != null)
                                    && (item.getItem().getType2() == 1)) {
                                item.setSkillWeaponEnchant(pc, this._skillId,
                                        this._skill.getBuffDuration() * 1000);
                            } else {
                                pc.sendPackets(new S_ServerMessage(79));
                            }
                        }
                        break;
                    // 拟似魔法武器
                    case ENCHANT_WEAPON:
                        if (cha instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) cha;
                            final L1ItemInstance item = pc.getInventory()
                                    .getItem(this._itemobjid);
                            if ((item != null)
                                    && (item.getItem().getType2() == 1)) {
                                pc.sendPackets(new S_ServerMessage(161, item
                                        .getLogName(), "$245", "$247"));
                                item.setSkillWeaponEnchant(pc, this._skillId,
                                        this._skill.getBuffDuration() * 1000);
                            } else {
                                pc.sendPackets(new S_ServerMessage(79));
                            }
                        }
                        break;
                    // 神圣武器、祝福魔法武器
                    case HOLY_WEAPON:
                    case BLESS_WEAPON:
                        if (cha instanceof L1PcInstance) {
                            if (!(cha instanceof L1PcInstance)) {
                                return;
                            }
                            final L1PcInstance pc = (L1PcInstance) cha;
                            if (pc.getWeapon() == null) {
                                pc.sendPackets(new S_ServerMessage(79));
                                return;
                            }
                            for (final L1ItemInstance item : pc.getInventory()
                                    .getItems()) {
                                if (pc.getWeapon().equals(item)) {
                                    pc.sendPackets(new S_ServerMessage(161,
                                            item.getLogName(), "$245", "$247"));
                                    item.setSkillWeaponEnchant(
                                            pc,
                                            this._skillId,
                                            this._skill.getBuffDuration() * 1000);
                                    return;
                                }
                            }
                        }
                        break;
                    // 铠甲护持
                    case BLESSED_ARMOR:
                        if (cha instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) cha;
                            final L1ItemInstance item = pc.getInventory()
                                    .getItem(this._itemobjid);
                            if ((item != null)
                                    && (item.getItem().getType2() == 2)
                                    && (item.getItem().getType() == 2)) {
                                pc.sendPackets(new S_ServerMessage(161, item
                                        .getLogName(), "$245", "$247"));
                                item.setSkillArmorEnchant(pc, this._skillId,
                                        this._skill.getBuffDuration() * 1000);
                            } else {
                                pc.sendPackets(new S_ServerMessage(79));
                            }
                        }
                        break;
                    default:
                        // L1BuffUtil.skillEffect(this._user, cha, this._target,
                        // this._skillId, this._getBuffIconDuration, dmg);
                        final List<?> queue = Producer.useRequests();
                        for (final Object name : queue) {
                            ((L1SkillEffect) name).execute(this._user, cha,
                                    this._target, this._skillId,
                                    this._getBuffIconDuration, dmg);
                        }
                        break;
                }

                // ■■■■ 到目前为止的个别处理 ■■■■

                // 治愈性魔法攻击不死系的怪物。
                if ((this._skill.getType() == L1Skills.TYPE_HEAL)
                        && (this._calcType == PC_NPC) && (undeadType == 1)) {
                    dmg *= -1;
                }
                // 治愈性魔法无法对此不死系起作用
                if ((this._skill.getType() == L1Skills.TYPE_HEAL)
                        && (this._calcType == PC_NPC) && (undeadType == 3)) {
                    dmg = 0;
                }
                // 无法对城门、守护塔补血
                if (((cha instanceof L1TowerInstance) || (cha instanceof L1DoorInstance))
                        && (dmg < 0)) {
                    dmg = 0;
                }
                // 吸取魔力。
                if ((dmg > 0) || (drainMana != 0)) {
                    _magic.commit(dmg, drainMana);
                }
                // 补血判断
                if ((this._skill.getType() == L1Skills.TYPE_HEAL) && (dmg < 0)) {
                    cha.setCurrentHp((dmg * -1) + cha.getCurrentHp());
                }
                // 非治愈性魔法补血判断(寒战、吸吻等)
                if (heal > 0) {
                    this._user.setCurrentHp(heal + this._user.getCurrentHp());
                }

                if (cha instanceof L1PcInstance) { // 更新自身状态
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.turnOnOffLight();
                    pc.sendPackets(new S_OwnCharAttrDef(pc));
                    pc.sendPackets(new S_OwnCharStatus(pc));
                    this.sendHappenMessage(pc); // 发送消息到目标
                }

                this.addMagicList(cha, false); // 为目标设定魔法效果时间

                if (cha instanceof L1PcInstance) { // 如果目标为PC、更新灯光状态
                    final L1PcInstance pc = (L1PcInstance) cha;
                    pc.turnOnOffLight(); // 打开灯
                }
            }

            // 解除隐身
            if ((this._skillId == DETECTION)
                    || (this._skillId == COUNTER_DETECTION)) { // 无所遁形、强力无所遁形
                this.detection(this._player);
            }

        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    /** 发送失败信息（失败） */
    private void sendFailMessage() {
        final int msgID = this._skill.getSysmsgIdFail();
        if ((msgID > 0) && (this._user instanceof L1PcInstance)) {
            this._player.sendPackets(new S_ServerMessage(msgID));
        }
    }

    /** 处理失败的消息显示 */
    private void sendFailMessageHandle() {
        // 攻击スキル以外で对象を指定するスキルが失败した场合は失败したメッセージをクライアントに送信
        // ※攻击スキルは障害物があっても成功时と同じアクションであるべき。
        if ((this._skill.getType() != L1Skills.TYPE_ATTACK)
                && !this._skill.getTarget().equals("none")
                && this._targetList.isEmpty()) {
            this.sendFailMessage();
        }
    }

    /**
     * 技能使用完毕后发送结束提示图标
     * 
     * @param isSkillAction
     */
    private void sendGrfx(final boolean isSkillAction) {
        if (this._actid == 0) {
            this._actid = this._skill.getActionId();
        }
        if (this._gfxid == 0) {
            this._gfxid = this._skill.getCastGfx();
        }
        if (this._gfxid == 0) {
            return; // 如果图形显示 无
        }
        int[] data = null;

        // 使用者为PC
        if (this._user instanceof L1PcInstance) {
            int targetid = 0;
            if ((this._skillId != FIRE_WALL) && (this._target != null)) {
                targetid = this._target.getId();
            }
            final L1PcInstance pc = (L1PcInstance) this._user;

            switch (this._skillId) {
                case FIRE_WALL: // 火牢
                case LIFE_STREAM: // 治愈能量风暴
                case ELEMENTAL_FALL_DOWN: // 弱化属性
                    if ((this._skillId == FIRE_WALL)
                    /* || (this._skillId == LIFE_STREAM) */) {
                        pc.setHeading(pc.targetDirection(this._targetX,
                                this._targetY));
                        pc.sendPackets(new S_ChangeHeading(pc));
                        pc.broadcastPacket(new S_ChangeHeading(pc));
                    }
                    final S_DoActionGFX gfx = new S_DoActionGFX(pc.getId(),
                            this._actid);
                    pc.sendPackets(gfx);
                    pc.broadcastPacket(gfx);
                    return;
                case SHOCK_STUN: // 冲击之晕
                    if (this._targetList.isEmpty()) { // 失败
                        return;
                    }
                    if (this._target instanceof L1PcInstance) {
                        final L1PcInstance targetPc = (L1PcInstance) this._target;
                        targetPc.sendPackets(new S_SkillSound(targetid, 4434));
                        targetPc.broadcastPacket(new S_SkillSound(targetid,
                                4434));
                    } else if (this._target instanceof L1NpcInstance) {
                        this._target.broadcastPacket(new S_SkillSound(targetid,
                                4434));
                    }
                    return;
                case LIGHT: // 日光术
                    pc.sendPackets(new S_Sound(145));
                    break;
                case MIND_BREAK: // 心灵破坏
                case JOY_OF_PAIN: // 疼痛的欢愉
                    data = new int[] { this._actid, this._dmg, 0 }; // data =
                                                                    // {actid,
                                                                    // dmg,
                                                                    // effect}
                    pc.sendPackets(new S_AttackPacket(pc, targetid, data));
                    pc.broadcastPacket(new S_AttackPacket(pc, targetid, data));
                    pc.sendPackets(new S_SkillSound(targetid, this._gfxid));
                    pc.broadcastPacket(new S_SkillSound(targetid, this._gfxid));
                    return;
                case CONFUSION: // 混乱
                    data = new int[] { this._actid, this._dmg, 0 }; // data =
                                                                    // {actid,
                                                                    // dmg,
                                                                    // effect}
                    pc.sendPackets(new S_AttackPacket(pc, targetid, data));
                    pc.broadcastPacket(new S_AttackPacket(pc, targetid, data));
                    return;
                case SMASH: // 暴击
                    pc.sendPackets(new S_SkillSound(targetid, this._gfxid));
                    pc.broadcastPacket(new S_SkillSound(targetid, this._gfxid));
                    return;
                case TAMING_MONSTER: // 迷魅
                    pc.sendPackets(new S_EffectLocation(this._targetX,
                            this._targetY, this._gfxid));
                    pc.broadcastPacket(new S_EffectLocation(this._targetX,
                            this._targetY, this._gfxid));
                    return;
                default:
                    break;
            }

            if (this._targetList.isEmpty()
                    && !(this._skill.getTarget().equals("none"))) {

                final int tempchargfx = this._player.getTempCharGfx();

                switch (tempchargfx) {
                    case 5727:
                    case 5730:
                        this._actid = ActionCodes.ACTION_SkillBuff;
                        break;

                    case 5733:
                    case 5736:
                        this._actid = ActionCodes.ACTION_Attack;
                        break;
                }
                if (isSkillAction) {
                    final S_DoActionGFX gfx = new S_DoActionGFX(
                            this._player.getId(), this._actid);
                    this._player.sendPackets(gfx);
                    this._player.broadcastPacket(gfx);
                }
                return;
            }

            if (this._skill.getTarget().equals("attack")
                    && (this._skillId != 18)) {
                if (this.isPcSummonPet(this._target)) { // 目标玩家、宠物、召唤兽
                    if ((this._player.getZoneType() == 1) // 自己在安全区
                            || (this._target.getZoneType() == 1) // 目标在安全区
                            || this._player.checkNonPvP(this._player,
                                    this._target)) { // Non-PvP设定
                        data = new int[] { this._actid, 0, this._gfxid, 6 };
                        this._player.sendPackets(new S_UseAttackSkill(
                                this._player, this._target.getId(),
                                this._targetX, this._targetY, data));
                        this._player.broadcastPacket(new S_UseAttackSkill(
                                this._player, this._target.getId(),
                                this._targetX, this._targetY, data));
                        return;
                    }
                }

                // 单体攻击魔法 (NPC / PC 技能使用)
                if (this.getSkillArea() == 0) {
                    data = new int[] { this._actid, this._dmg, this._gfxid, 6 };
                    this._player.sendPackets(new S_UseAttackSkill(this._player,
                            targetid, this._targetX, this._targetY, data));
                    this._player.broadcastPacket(new S_UseAttackSkill(
                            this._player, targetid, this._targetX,
                            this._targetY, data));
                    this._target.broadcastPacketExceptTargetSight(
                            new S_DoActionGFX(targetid,
                                    ActionCodes.ACTION_Damage), this._player);
                }

                // 有方向范围攻击魔法
                else {
                    final L1Character[] cha = new L1Character[this._targetList
                            .size()];
                    int i = 0;
                    for (final TargetStatus ts : this._targetList) {
                        cha[i] = ts.getTarget();
                        i++;
                    }
                    this._player.sendPackets(new S_RangeSkill(this._player,
                            cha, this._gfxid, this._actid,
                            S_RangeSkill.TYPE_DIR));
                    this._player.broadcastPacket(new S_RangeSkill(this._player,
                            cha, this._gfxid, this._actid,
                            S_RangeSkill.TYPE_DIR));
                }
            }

            // 无方向范围攻击魔法
            else if (this._skill.getTarget().equals("none")
                    && (this._skill.getType() == L1Skills.TYPE_ATTACK)) { // 无方向范围攻击魔法
                final L1Character[] cha = new L1Character[this._targetList
                        .size()];
                int i = 0;
                for (final TargetStatus ts : this._targetList) {
                    cha[i] = ts.getTarget();
                    cha[i].broadcastPacketExceptTargetSight(new S_DoActionGFX(
                            cha[i].getId(), ActionCodes.ACTION_Damage),
                            this._player);
                    i++;
                }
                this._player.sendPackets(new S_RangeSkill(this._player, cha,
                        this._gfxid, this._actid, S_RangeSkill.TYPE_NODIR));
                this._player
                        .broadcastPacket(new S_RangeSkill(this._player, cha,
                                this._gfxid, this._actid,
                                S_RangeSkill.TYPE_NODIR));
            }

            // 辅助魔法
            else {
                // 指定传送、集体传送术、世界树的呼唤以外
                if ((this._skillId != TELEPORT)
                        && (this._skillId != MASS_TELEPORT)
                        && (this._skillId != TELEPORT_TO_MATHER)) {
                    // 施法动作
                    if (isSkillAction) {
                        final S_DoActionGFX gfx = new S_DoActionGFX(
                                this._player.getId(), this._skill.getActionId());
                        this._player.sendPackets(gfx);
                        this._player.broadcastPacket(gfx);
                    }
                    // 魔法屏障、反击屏障、镜反射 魔法效果只有自身显示
                    if ((this._skillId == COUNTER_MAGIC)
                            || (this._skillId == COUNTER_BARRIER)
                            || (this._skillId == COUNTER_MIRROR)) {
                        this._player.sendPackets(new S_SkillSound(targetid,
                                this._gfxid));
                    } else if ((this._skillId == AWAKEN_ANTHARAS // 觉醒：安塔瑞斯
                            )
                            || (this._skillId == AWAKEN_FAFURION // 觉醒：法利昂
                            ) || (this._skillId == AWAKEN_VALAKAS)) { // 觉醒：巴拉卡斯
                        if (this._skillId == this._player.getAwakeSkillId()) { // 再次使用就解除觉醒状态
                            this._player.sendPackets(new S_SkillSound(targetid,
                                    this._gfxid));
                            this._player.broadcastPacket(new S_SkillSound(
                                    targetid, this._gfxid));
                        } else {
                            return;
                        }
                    } else {
                        this._player.sendPackets(new S_SkillSound(targetid,
                                this._gfxid));
                        this._player.broadcastPacket(new S_SkillSound(targetid,
                                this._gfxid));
                    }
                }

                // スキルのエフェクト表示はターゲット全员だが、あまり必要性がないので、ステータスのみ送信
                for (final TargetStatus ts : this._targetList) {
                    final L1Character cha = ts.getTarget();
                    if (cha instanceof L1PcInstance) {
                        final L1PcInstance chaPc = (L1PcInstance) cha;
                        chaPc.sendPackets(new S_OwnCharStatus(chaPc));
                    }
                }
            }
        }

        // 施法者为NPC
        else if (this._user instanceof L1NpcInstance) {
            final int targetid = this._target.getId();

            if (this._user instanceof L1MerchantInstance) {
                this._user.broadcastPacket(new S_SkillSound(targetid,
                        this._gfxid));
                return;
            }

            if ((this._skillId == CURSE_PARALYZE)
                    || (this._skillId == WEAKNESS)
                    || (this._skillId == DISEASE)) { // 木乃伊的诅咒、弱化术、疾病术
                this._user.setHeading(this._user.targetDirection(this._targetX,
                        this._targetY)); // 改变面向
                this._user.broadcastPacket(new S_ChangeHeading(this._user));
            }

            if (this._targetList.isEmpty()
                    && !(this._skill.getTarget().equals("none"))) {
                // ターゲット数が０で对象を指定するスキルの场合、魔法使用エフェクトだけ表示して终了
                final S_DoActionGFX gfx = new S_DoActionGFX(this._user.getId(),
                        this._actid);
                this._user.broadcastPacket(gfx);
                return;
            }

            if (this._skill.getTarget().equals("attack")
                    && (this._skillId != TURN_UNDEAD)) {
                if (this.getSkillArea() == 0) { // 单体攻击魔法
                    data = new int[] { this._actid, this._dmg, this._gfxid, 6 };
                    this._user.broadcastPacket(new S_UseAttackSkill(this._user,
                            targetid, this._targetX, this._targetY, data));
                    this._target.broadcastPacketExceptTargetSight(
                            new S_DoActionGFX(targetid,
                                    ActionCodes.ACTION_Damage), this._user);
                } else { // 有方向范围攻击魔法
                    final L1Character[] cha = new L1Character[this._targetList
                            .size()];
                    int i = 0;
                    for (final TargetStatus ts : this._targetList) {
                        cha[i] = ts.getTarget();
                        cha[i].broadcastPacketExceptTargetSight(
                                new S_DoActionGFX(cha[i].getId(),
                                        ActionCodes.ACTION_Damage), this._user);
                        i++;
                    }
                    this._user.broadcastPacket(new S_RangeSkill(this._user,
                            cha, this._gfxid, this._actid,
                            S_RangeSkill.TYPE_DIR));
                }
            }

            // 无方向范围魔法
            else if (this._skill.getTarget().equals("none")
                    && (this._skill.getType() == L1Skills.TYPE_ATTACK)) { // 无方向范围攻击魔法
                final L1Character[] cha = new L1Character[this._targetList
                        .size()];
                int i = 0;
                for (final TargetStatus ts : this._targetList) {
                    cha[i] = ts.getTarget();
                    i++;
                }
                this._user.broadcastPacket(new S_RangeSkill(this._user, cha,
                        this._gfxid, this._actid, S_RangeSkill.TYPE_NODIR));
            }

            // 辅助魔法
            else {
                // 指定传送、集体传送术、世界树的呼唤以外
                if ((this._skillId != TELEPORT)
                        && (this._skillId != MASS_TELEPORT)
                        && (this._skillId != TELEPORT_TO_MATHER)) {
                    // 魔法を使う动作のエフェクトは使用者だけ
                    final S_DoActionGFX gfx = new S_DoActionGFX(
                            this._user.getId(), this._actid);
                    this._user.broadcastPacket(gfx);
                    this._user.broadcastPacket(new S_SkillSound(targetid,
                            this._gfxid));
                }
            }
        }
    }

    /**
     * 发送发生的信息（发生了什么事时）
     * 
     * @param pc
     */
    private void sendHappenMessage(final L1PcInstance pc) {
        final int msgID = this._skill.getSysmsgIdHappen();
        if (msgID > 0) {
            // 效果讯息排除施法者本身。
            if ((this._skillId == AREA_OF_SILENCE)
                    && (this._user.getId() == pc.getId())) { // 封印禁地
                return;
            }
            pc.sendPackets(new S_ServerMessage(msgID));
        }
    }

    /**
     * 发送技能图示
     * 
     * @param pc
     */
    private void sendIcon(final L1PcInstance pc) {
        if (this._skillTime == 0) {
            this._getBuffIconDuration = this._skill.getBuffDuration(); // 效果时间
        } else {
            this._getBuffIconDuration = this._skillTime; // パラメータのtimeが0以外なら、效果时间として设定する
        }

        switch (this._skillId) {
            case SHIELD: // 防护罩
                pc.sendPackets(new S_SkillIconShield(5,
                        this._getBuffIconDuration));
                break;

            case SHADOW_ARMOR: // 影之防护
                pc.sendPackets(new S_SkillIconShield(3,
                        this._getBuffIconDuration));
                break;

            case DRESS_DEXTERITY: // 敏捷提升
                pc.sendPackets(new S_Dexup(pc, 2, this._getBuffIconDuration));
                break;

            case DRESS_MIGHTY: // 力量提升
                pc.sendPackets(new S_Strup(pc, 2, this._getBuffIconDuration));
                break;

            case GLOWING_AURA: // 激励士气
                pc.sendPackets(new S_SkillIconAura(113,
                        this._getBuffIconDuration));
                break;

            case SHINING_AURA: // 钢铁士气
                pc.sendPackets(new S_SkillIconAura(114,
                        this._getBuffIconDuration));
                break;

            case BRAVE_AURA: // 冲击士气
                pc.sendPackets(new S_SkillIconAura(116,
                        this._getBuffIconDuration));
                break;

            case FIRE_WEAPON: // 火焰武器
                pc.sendPackets(new S_SkillIconAura(147,
                        this._getBuffIconDuration));
                break;

            case WIND_SHOT: // 风之神射
                pc.sendPackets(new S_SkillIconAura(148,
                        this._getBuffIconDuration));
                break;

            case FIRE_BLESS: // 烈炎气息
                pc.sendPackets(new S_SkillIconAura(154,
                        this._getBuffIconDuration));
                break;

            case STORM_EYE: // 暴风之眼
                pc.sendPackets(new S_SkillIconAura(155,
                        this._getBuffIconDuration));
                break;

            case EARTH_BLESS: // 大地的祝福
                pc.sendPackets(new S_SkillIconShield(7,
                        this._getBuffIconDuration));
                break;

            case BURNING_WEAPON: // 烈炎武器
                pc.sendPackets(new S_SkillIconAura(162,
                        this._getBuffIconDuration));
                break;

            case STORM_SHOT: // 暴风神射
                pc.sendPackets(new S_SkillIconAura(165,
                        this._getBuffIconDuration));
                break;

            case IRON_SKIN: // 钢铁防护
                pc.sendPackets(new S_SkillIconShield(10,
                        this._getBuffIconDuration));
                break;

            case EARTH_SKIN: // 大地防护
                pc.sendPackets(new S_SkillIconShield(6,
                        this._getBuffIconDuration));
                break;

            case PHYSICAL_ENCHANT_STR: // 体魄强健术：STR
                pc.sendPackets(new S_Strup(pc, 5, this._getBuffIconDuration));
                break;

            case PHYSICAL_ENCHANT_DEX: // 通畅气脉术：DEX
                pc.sendPackets(new S_Dexup(pc, 5, this._getBuffIconDuration));
                break;

            case HASTE:
            case GREATER_HASTE: // 加速术,强力加速术
                pc.sendPackets(new S_SkillHaste(pc.getId(), 1,
                        this._getBuffIconDuration));
                pc.broadcastPacket(new S_SkillHaste(pc.getId(), 1, 0));
                break;

            case HOLY_WALK:
            case MOVING_ACCELERATION:
            case WIND_WALK: // 神圣疾走、行走加速、风之疾走
                pc.sendPackets(new S_SkillBrave(pc.getId(), 4,
                        this._getBuffIconDuration));
                pc.broadcastPacket(new S_SkillBrave(pc.getId(), 4, 0));
                break;

            case BLOODLUST: // 血之渴望
                pc.sendPackets(new S_SkillBrave(pc.getId(), 6,
                        this._getBuffIconDuration));
                pc.broadcastPacket(new S_SkillBrave(pc.getId(), 6, 0));
                break;

            case SLOW:
            case MASS_SLOW:
            case ENTANGLE: // 缓速、集体缓速、地面障碍
                pc.sendPackets(new S_SkillHaste(pc.getId(), 2,
                        this._getBuffIconDuration));
                pc.broadcastPacket(new S_SkillHaste(pc.getId(), 2, 0));
                break;

            case IMMUNE_TO_HARM: // 圣结界
                pc.sendPackets(new S_SkillIconGFX(40, this._getBuffIconDuration));
                break;

            case WIND_SHACKLE: // 风之枷锁
                pc.sendPackets(new S_SkillIconWindShackle(pc.getId(),
                        this._getBuffIconDuration));
                pc.broadcastPacket(new S_SkillIconWindShackle(pc.getId(),
                        this._getBuffIconDuration));
                break;
        }
        pc.sendPackets(new S_OwnCharStatus(pc));
    }

    /**
     * 设定检查所使用技能
     * 
     * @param flg
     */
    private void setCheckedUseSkill(final boolean flg) {
        this._checkedUseSkill = flg;
    }

    // 设定技能使用延迟
    private void setDelay() {
        if (this._skill.getReuseDelay() > 0) {
            L1SkillDelay.onSkillUse(this._user, this._skill.getReuseDelay());
        }
    }

    /**
     * 设定攻击倍率 (1/10)
     * 
     * @param i
     */
    public void setLeverage(final int i) {
        this._leverage = i;
    }

    /**
     * 设定魔法攻击范围变更。
     * 
     * @param i
     */
    public void setSkillArea(final int i) {
        this._skillArea = i;
    }

    /**
     * 设定魔法攻击距离变更。
     * 
     * @param i
     */
    public void setSkillRanged(final int i) {
        this._skillRanged = i;
    }

    /**
     * 删除全部重复的正在使用的技能
     * 
     * @param cha
     * @param repeat_skill
     */
    private void stopSkillList(final L1Character cha, final int[] repeat_skill) {
        for (final int skillId : repeat_skill) {
            if (skillId != this._skillId) {
                cha.removeSkillEffect(skillId);
            }
        }
    }

    /**
     * 使用技能后，相应的HP和MP、Lawful、材料的减少
     */
    private void useConsume() {

        // 如果为NPC、仅减少HP、MP
        if (this._user instanceof L1NpcInstance) {
            final int current_hp = this._npc.getCurrentHp() - this._hpConsume;
            this._npc.setCurrentHp(current_hp);

            final int current_mp = this._npc.getCurrentMp() - this._mpConsume;
            this._npc.setCurrentMp(current_mp);
            return;
        }

        // HP・MP消耗 已经计算使用量
        if (this._skillId == FINAL_BURN) { // 会心一击
            this._player.setCurrentHp(1);
            this._player.setCurrentMp(0);
        } else {
            final int current_hp = this._player.getCurrentHp()
                    - this._hpConsume;
            this._player.setCurrentHp(current_hp);

            final int current_mp = this._player.getCurrentMp()
                    - this._mpConsume;
            this._player.setCurrentMp(current_mp);
        }

        // 减去Lawful
        int lawful = this._player.getLawful() + this._skill.getLawful();
        if (lawful > 32767) {
            lawful = 32767;
        } else if (lawful < -32767) {
            lawful = -32767;
        }
        this._player.setLawful(lawful);

        final int itemConsume = this._skill.getItemConsumeId();
        final int itemConsumeCount = this._skill.getItemConsumeCount();

        if (itemConsume == 0) {
            return; // 施放魔法没有必要材料
        }

        // 减去使用材料
        this._player.getInventory().consumeItem(itemConsume, itemConsumeCount);
    }

}
