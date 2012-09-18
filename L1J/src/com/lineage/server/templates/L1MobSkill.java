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
package com.lineage.server.templates;

/**
 * Mob技能
 */
public class L1MobSkill implements Cloneable {

    /** 空类型 */
    public static final int TYPE_NONE = 0;
    /** 物理攻击类型 */
    public static final int TYPE_PHYSICAL_ATTACK = 1;
    /** 魔法攻击类型 */
    public static final int TYPE_MAGIC_ATTACK = 2;
    /** 召唤类型 */
    public static final int TYPE_SUMMON = 3;
    /** 变身类型 */
    public static final int TYPE_POLY = 4;
    /** 目标无变化 */
    public static final int CHANGE_TARGET_NO = 0;
    /** 改变目标 (同伴) */
    public static final int CHANGE_TARGET_COMPANION = 1;
    /** 改变目标(自己) */
    public static final int CHANGE_TARGET_ME = 2;
    /** 随机改变目标 */
    public static final int CHANGE_TARGET_RANDOM = 3;
    /** 技能大小 */
    private final int skillSize;

    /** MobID */
    private int mobid;

    /** Mob名称 */
    private String mobName;

    /**
     * 技能类型 0→不采取行动、1→物理攻击、2→魔法攻击、3→サモン
     */
    private final int type[];

    /**
     * 技能范围设定
     */
    int skillArea[];

    /**
     * 魔力消耗判断
     */
    int mpConsume[];

    /**
     * 技能发动条件：随机发动概率（0%～100%）
     */
    private final int triRnd[];

    /**
     * 技能发动条件：HP%以下发动
     */
    int triHp[];

    /**
     * 技能发动条件：同族のHP%以下发动
     */
    int triCompanionHp[];

    /**
     * 技能发动条件：triRange<0の場合、対象との距離がabs(triRange)以下のとき発動
     * triRange>0の場合、対象との距離がtriRange以上のとき発動
     */
    int triRange[];

    /** 触发次数 */
    int triCount[];

    /**
     * 技能发动时、改变目标
     */
    int changeTarget[];

    /**
     * rangeまでの距離ならば攻撃可能、物理攻撃をするならば近接攻撃の場合でも1以上を設定
     */
    int range[];

    /**
     * 范围攻击的宽度、单体攻击设定0、范围攻击设定1以上 WidthとHeightの設定は攻撃者からみて横幅をWidth、奥行きをHeightとする。
     * Widthは+-あるので、1を指定すれば、ターゲットを中心として左右1までが対象となる。
     */
    int areaWidth[];

    /**
     * 范围攻击的高度、单体攻击设定0、范围攻击设定1以上
     */
    int areaHeight[];

    /**
     * 伤害倍率、1/10で表す。物理攻击、魔法攻击共同有效
     */
    int leverage[];

    /**
     * 魔法使用场合、SkillId指定
     */
    int skillId[];

    /**
     * 物理攻击的动画
     */
    int gfxid[];

    /**
     * 物理攻击动作ID
     */
    int actid[];

    /**
     * 召唤怪のNPCID
     */
    int summon[];

    /**
     * 召唤怪的最低数量
     */
    int summonMin[];

    /**
     * 召唤怪的最高数量
     */
    int summonMax[];

    /**
     * 强制变身ID
     */
    int polyId[];

    public L1MobSkill(final int sSize) {
        this.skillSize = sSize;

        this.type = new int[this.skillSize];
        this.mpConsume = new int[this.skillSize];
        this.triRnd = new int[this.skillSize];
        this.triHp = new int[this.skillSize];
        this.triCompanionHp = new int[this.skillSize];
        this.triRange = new int[this.skillSize];
        this.triCount = new int[this.skillSize];
        this.changeTarget = new int[this.skillSize];
        this.range = new int[this.skillSize];
        this.areaWidth = new int[this.skillSize];
        this.areaHeight = new int[this.skillSize];
        this.leverage = new int[this.skillSize];
        this.skillId = new int[this.skillSize];
        this.skillArea = new int[this.skillSize];
        this.gfxid = new int[this.skillSize];
        this.actid = new int[this.skillSize];
        this.summon = new int[this.skillSize];
        this.summonMin = new int[this.skillSize];
        this.summonMax = new int[this.skillSize];
        this.polyId = new int[this.skillSize];
    }

    @Override
    public L1MobSkill clone() {
        try {
            return (L1MobSkill) (super.clone());
        } catch (final CloneNotSupportedException e) {
            throw (new InternalError(e.getMessage()));
        }
    }

    /** 获得MobID */
    public int get_mobid() {
        return this.mobid;
    }

    /**  */
    public int getActid(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.actid[idx];
    }

    /** 获得范围攻击的高度 */
    public int getAreaHeight(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.areaHeight[idx];
    }

    /** 获得范围攻击的宽度 */
    public int getAreaWidth(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.areaWidth[idx];
    }

    /** 获得技能发动时、改变目标 */
    public int getChangeTarget(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.changeTarget[idx];
    }

    /**  */
    public int getGfxid(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.gfxid[idx];
    }

    /** 获得伤害倍率 */
    public int getLeverage(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.leverage[idx];
    }

    /** 获得Mob名称 */
    public String getMobName() {
        return this.mobName;
    }

    /** 获得魔力消耗判断 */
    public int getMpConsume(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.mpConsume[idx];
    }

    /**  */
    public int getPolyId(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.polyId[idx];
    }

    /** 获得范围攻击的距离 */
    public int getRange(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.range[idx];
    }

    /** 获得技能范围设定 */
    public int getSkillArea(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.skillArea[idx];
    }

    /** 获得技能ID */
    public int getSkillId(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.skillId[idx];
    }

    /** 获得技能大小 */
    public int getSkillSize() {
        return this.skillSize;
    }

    /**  */
    public int getSummon(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.summon[idx];
    }

    /**  */
    public int getSummonMax(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.summonMax[idx];
    }

    /**  */
    public int getSummonMin(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.summonMin[idx];
    }

    /** 获得同族のHP%以下发动 */
    public int getTriggerCompanionHp(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.triCompanionHp[idx];
    }

    /**
     * 技能发动条件：技能的发动次数triCount以下发动
     */
    public int getTriggerCount(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.triCount[idx];
    }

    /** 获得HP%以下发动 */
    public int getTriggerHp(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.triHp[idx];
    }

    /** 获得随机发动概率（0%～100%） */
    public int getTriggerRandom(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.triRnd[idx];
    }

    /** 获得技能触发范围 */
    public int getTriggerRange(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.triRange[idx];
    }

    /** 获得技能类型 */
    public int getType(final int idx) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return 0;
        }
        return this.type[idx];
    }

    /** distance指定idx技能的发动条件 */
    public boolean isTriggerDistance(final int idx, final int distance) {
        final int triggerRange = this.getTriggerRange(idx);

        if (((triggerRange < 0) && (distance <= Math.abs(triggerRange)))
                || ((triggerRange > 0) && (distance >= triggerRange))) {
            return true;
        }
        return false;
    }

    /** 设置MobID */
    public void set_mobid(final int i) {
        this.mobid = i;
    }

    /**  */
    public void setActid(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.actid[idx] = i;
    }

    /** 设置范围攻击的高度 */
    public void setAreaHeight(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.areaHeight[idx] = i;
    }

    /** 设置范围攻击的宽度 */
    public void setAreaWidth(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.areaWidth[idx] = i;
    }

    /** 设置技能发动时、改变目标 */
    public void setChangeTarget(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.changeTarget[idx] = i;
    }

    /**  */
    public void setGfxid(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.gfxid[idx] = i;
    }

    /** 设置伤害倍率 */
    public void setLeverage(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.leverage[idx] = i;
    }

    /** 设置Mob名称 */
    public void setMobName(final String s) {
        this.mobName = s;
    }

    /** 设置魔力消耗判断 */
    public void setMpConsume(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.mpConsume[idx] = i;
    }

    /** 设置强制变身ID */
    public void setPolyId(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.polyId[idx] = i;
    }

    /** 设置获得范围攻击的距离 */
    public void setRange(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.range[idx] = i;
    }

    /** 设置技能范围设定 */
    public void setSkillArea(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.skillArea[idx] = i;
    }

    /** 设置技能ID */
    public void setSkillId(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.skillId[idx] = i;
    }

    /**  */
    public void setSummon(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.summon[idx] = i;
    }

    /**  */
    public void setSummonMax(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.summonMax[idx] = i;
    }

    /**  */
    public void setSummonMin(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.summonMin[idx] = i;
    }

    /** 设置同族のHP%以下发动 */
    public void setTriggerCompanionHp(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.triCompanionHp[idx] = i;
    }

    /** 设置技能触发次数 */
    public void setTriggerCount(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.triCount[idx] = i;
    }

    /** 设置HP%以下发动 */
    public void setTriggerHp(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.triHp[idx] = i;
    }

    /** 设置随机发动概率（0%～100%） */
    public void setTriggerRandom(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.triRnd[idx] = i;
    }

    /** 设置技能触发范围 */
    public void setTriggerRange(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.triRange[idx] = i;
    }

    /** 设置技能类型 */
    public void setType(final int idx, final int i) {
        if ((idx < 0) || (idx >= this.getSkillSize())) {
            return;
        }
        this.type[idx] = i;
    }
}
