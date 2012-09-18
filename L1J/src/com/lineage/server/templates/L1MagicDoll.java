package com.lineage.server.templates;

import com.lineage.server.datatables.ItemTable;
import com.lineage.server.datatables.MagicDollTable;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1DollInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.utils.Random;

/**
 * 魔法娃娃
 */
public class L1MagicDoll {

    /** 防御力增加 */
    public static int getAcByDoll(final L1Character _master) {
        int s = 0;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                s += doll.getAc();
            }
        }
        return s;
    }

    /** 弓的攻击力增加 */
    public static int getBowDamageByDoll(final L1Character _master) {
        int s = 0;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                s += doll.getBowDmg();
            }
        }
        return s;
    }

    /** 弓的命中率增加 */
    public static int getBowHitAddByDoll(final L1Character _master) {
        int s = 0;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                s += doll.getBowHit();
            }
        }
        return s;
    }

    /** 近距离的攻击力增加 */
    public static int getDamageAddByDoll(final L1Character _master) {
        int s = 0;
        final int chance = Random.nextInt(100) + 1;
        boolean isAdd = false;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                if ((doll.getDmgChance() > 0) && !isAdd) { // 额外伤害发动机率
                    if (doll.getDmgChance() >= chance) {
                        s += doll.getDmg();
                        isAdd = true;
                    }
                } else if (doll.getDmg() != 0) { // 额外伤害
                    s += doll.getDmg();
                }
            }
        }
        if (isAdd) {
            if (_master instanceof L1PcInstance) {
                final L1PcInstance pc = (L1PcInstance) _master;
                pc.sendPackets(new S_SkillSound(_master.getId(), 6319));
            }
            _master.broadcastPacket(new S_SkillSound(_master.getId(), 6319));
        }
        return s;
    }

    /** 伤害回避 */
    public static int getDamageEvasionByDoll(final L1Character _master) {
        final int chance = Random.nextInt(100) + 1;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                if (doll.getDmgEvasionChance() >= chance) { // 伤害回避发动机率
                    if (_master instanceof L1PcInstance) {
                        final L1PcInstance pc = (L1PcInstance) _master;
                        pc.sendPackets(new S_SkillSound(_master.getId(), 6320));
                    }
                    _master.broadcastPacket(new S_SkillSound(_master.getId(),
                            6320));
                    return 1;
                }
            }
        }
        return 0;
    }

    /** 取得魔法娃娃伤害减免 */
    public static int getDamageReductionByDoll(final L1Character _master) {
        int s = 0;
        final int chance = Random.nextInt(100) + 1;
        boolean isReduction = false;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                if ((doll.getDmgReductionChance() > 0) && !isReduction) { // 伤害减免发动机率
                    if (doll.getDmgReductionChance() >= chance) {
                        s += doll.getDmgReduction();
                        isReduction = true;
                    }
                } else if (doll.getDmgReduction() != 0) { // 伤害减免
                    s += doll.getDmgReduction();
                }
            }
        }
        if (isReduction) {
            if (_master instanceof L1PcInstance) {
                final L1PcInstance pc = (L1PcInstance) _master;
                pc.sendPackets(new S_SkillSound(_master.getId(), 6320));
            }
            _master.broadcastPacket(new S_SkillSound(_master.getId(), 6320));
        }
        return s;
    }

    /** 取得魔法娃娃效果 */
    public static int getEffectByDoll(final L1Character _master, final byte type) {
        final int chance = Random.nextInt(100) + 1;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll.getEffectChance() > chance) {
                if (doll != null) {
                    if (doll.getEffect() == type) {
                        return type;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 取得经验值增加.
     * 
     * @param _master
     *            对象
     * @return 经验值
     */
    public static double getExpByDoll(final L1Character _master) {
        double s = 1.0;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                if (doll.getExp() > 0.0) {
                    s = doll.getExp();
                }
            }
        }
        return s;
    }

    /** 近距离的命中率增加 */
    public static int getHitAddByDoll(final L1Character _master) {
        int s = 0;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                s += doll.getHit();
            }
        }
        return s;
    }

    /** 体力回覆量 (时间固定性) */
    public static int getHpByDoll(final L1Character _master) {
        int s = 0;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                if (doll.getHprTime() && (doll.getHpr() != 0)) {
                    s += doll.getHpr();
                }
            }
        }
        return s;
    }

    /** 体力回覆量 */
    public static int getHprByDoll(final L1Character _master) {
        int s = 0;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                if (!doll.getHprTime() && (doll.getHpr() != 0)) {
                    s += doll.getHpr();
                }
            }
        }
        return s;
    }

    /** 取得道具 */
    public static int getMakeItemId(final L1Character _master) {
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                final L1Item item = ItemTable.getInstance().getTemplate(
                        (doll.getMakeItemId()));
                if (item != null) {
                    return item.getItemId();
                }
            }
        }
        return 0;
    }

    /** 魔力回覆量 (时间固定性) */
    public static int getMpByDoll(final L1Character _master) {
        int s = 0;

        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                if (doll.getMprTime() && (doll.getMpr() != 0)) {
                    s += doll.getMpr();
                }
            }
        }
        return s;
    }

    /** 魔力回覆量 */
    public static int getMprByDoll(final L1Character _master) {
        int s = 0;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                if (!doll.getMprTime() && (doll.getMpr() != 0)) {
                    s += doll.getMpr();
                }
            }
        }
        return s;
    }

    /** 闇黑耐性增加 */
    public static int getRegistBlindByDoll(final L1Character _master) {
        int s = 0;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                s += doll.getRegistBlind();
            }
        }
        return s;
    }

    /** 寒冰耐性增加 */
    public static int getRegistFreezeByDoll(final L1Character _master) {
        int s = 0;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                s += doll.getRegistFreeze();
            }
        }
        return s;
    }

    /** 睡眠耐性增加 */
    public static int getRegistSleepByDoll(final L1Character _master) {
        int s = 0;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                s += doll.getRegistSleep();
            }
        }
        return s;
    }

    /** 石化耐性增加 */
    public static int getRegistStoneByDoll(final L1Character _master) {
        int s = 0;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                s += doll.getRegistStone();
            }
        }
        return s;
    }

    /** 昏迷耐性增加 */
    public static int getRegistStunByDoll(final L1Character _master) {
        int s = 0;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                s += doll.getRegistStun();
            }
        }
        return s;
    }

    /** 支撑耐性增加 */
    public static int getRegistSustainByDoll(final L1Character _master) {
        int s = 0;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                s += doll.getRegistSustain();
            }
        }
        return s;
    }

    /** 负重减轻 */
    public static int getWeightReductionByDoll(final L1Character _master) {
        int s = 0;
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                s += doll.getWeightReduction();
            }
        }
        return s;
    }

    /** 回血判断 (时间固定性) */
    public static boolean isHpRegeneration(final L1Character _master) {
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                if (doll.getHprTime() && (doll.getHpr() != 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** 创造道具 */
    public static boolean isItemMake(final L1Character _master) {
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                final L1Item item = ItemTable.getInstance().getTemplate(
                        (doll.getMakeItemId()));
                if (item != null) {
                    return true;
                }
            }
        }
        return false;
    }

    /** 回魔判断 (时间固定性) */
    public static boolean isMpRegeneration(final L1Character _master) {
        for (final L1DollInstance dollIns : _master.getDollList().values()) {
            final L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(
                    dollIns.getItemId());
            if (doll != null) {
                if (doll.getMprTime() && (doll.getMpr() != 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** 道具ID */
    private int _itemId;
    /** 娃娃ID */
    private int _dollId;

    /** 物理防御 */
    private int _ac;

    /** 回血 */
    private int _hpr;

    /** 回魔 */
    private int _mpr;

    /** 回血时间 */
    private boolean _hprTime;

    /** 会魔时间 */
    private boolean _mprTime;

    /** 伤害 */
    private int _dmg;

    /** 弓伤害 */
    private int _bowDmg;

    /** 伤害几率 */
    private int _dmgChance;

    /** 命中率 */
    private int _hit;

    /** 弓的命中率 */
    private int _bowHit;

    /** 伤害减免 */
    private int _dmgReduction;

    /** 伤害减免的几率 */
    private int _dmgReductionChance;

    /** 伤害回避 */
    private int _dmgEvasionChance;

    /** 负重轻减 */
    private int _weightReduction;

    /** 昏迷耐性 */
    private int _registStun;

    /** 石化耐性 */
    private int _registStone;

    /** 睡眠耐性 */
    private int _registSleep;

    /** 寒冰耐性 */
    private int _registFreeze;

    /** 支撑耐性 */
    private int _registSustain;

    /** 闇黑耐性 */
    private int _registBlind;

    /** 取得道具 */
    private int _makeItemId;

    /** 效果 */
    private byte _effect;

    /** 效果几率 */
    private int _EffectChance;

    /** 经验值. */
    private double _exp;

    /** 取得AC */
    public int getAc() {
        return this._ac;
    }

    /** 取得弓伤害 */
    public int getBowDmg() {
        return this._bowDmg;
    }

    /** 取得弓的命中率 */
    public int getBowHit() {
        return this._bowHit;
    }

    /** 取得伤害 */
    public int getDmg() {
        return this._dmg;
    }

    /** 取得伤害几率 */
    public int getDmgChance() {
        return this._dmgChance;
    }

    /** 取得伤害回避 */
    public int getDmgEvasionChance() {
        return this._dmgEvasionChance;
    }

    /** 取得伤害减免 */
    public int getDmgReduction() {
        return this._dmgReduction;
    }

    /** 取得伤害减免的几率 */
    public int getDmgReductionChance() {
        return this._dmgReductionChance;
    }

    /** 取得娃娃ID */
    public int getDollId() {
        return this._dollId;
    }

    /** 取得效果 */
    public byte getEffect() {
        return this._effect;
    }

    /** 取得效果几率 */
    public int getEffectChance() {
        return this._EffectChance;
    }

    /**
     * 取得经验值.
     * 
     * @return 经验值
     */
    public final double getExp() {
        return this._exp;
    }

    /** 取得命中率 */
    public int getHit() {
        return this._hit;
    }

    /** 取得回血 */
    public int getHpr() {
        return this._hpr;
    }

    /** 取得回血时间 */
    public boolean getHprTime() {
        return this._hprTime;
    }

    /** 取得道具ID */
    public int getItemId() {
        return this._itemId;
    }

    /** 返回取得道具 */
    public int getMakeItemId() {
        return this._makeItemId;
    }

    /** 取得回魔 */
    public int getMpr() {
        return this._mpr;
    }

    /** 取得回魔时间 */
    public boolean getMprTime() {
        return this._mprTime;
    }

    /** 取得闇黑耐性 */
    public int getRegistBlind() {
        return this._registBlind;
    }

    /** 取得寒冰耐性 */
    public int getRegistFreeze() {
        return this._registFreeze;
    }

    /** 取得睡眠耐性 */
    public int getRegistSleep() {
        return this._registSleep;
    }

    /** 取得石化耐性 */
    public int getRegistStone() {
        return this._registStone;
    }

    /** 取得昏迷耐性 */
    public int getRegistStun() {
        return this._registStun;
    }

    /** 取得支撑耐性 */
    public int getRegistSustain() {
        return this._registSustain;
    }

    /** 取得负重轻减 */
    public int getWeightReduction() {
        return this._weightReduction;
    }

    /** 设定AC */
    public void setAc(final int i) {
        this._ac = i;
    }

    /** 设定弓伤害 */
    public void setBowDmg(final int i) {
        this._bowDmg = i;
    }

    /** 设定弓的命中率 */
    public void setBowHit(final int i) {
        this._bowHit = i;
    }

    /** 设定伤害 */
    public void setDmg(final int i) {
        this._dmg = i;
    }

    /** 设定伤害几率 */
    public void setDmgChance(final int i) {
        this._dmgChance = i;
    }

    /** 设定伤害回避 */
    public void setDmgEvasionChance(final int i) {
        this._dmgEvasionChance = i;
    }

    /** 设定伤害减免 */
    public void setDmgReduction(final int i) {
        this._dmgReduction = i;
    }

    /** 设定伤害减免的几率 */
    public void setDmgReductionChance(final int i) {
        this._dmgReductionChance = i;
    }

    /** 设定娃娃ID */
    public void setDollId(final int i) {
        this._dollId = i;
    }

    /** 设定效果 */
    public void setEffect(final byte i) {
        this._effect = i;
    }

    /** 设定效果几率 */
    public void setEffectChance(final int i) {
        this._EffectChance = i;
    }

    /**
     * 设置经验值.
     * 
     * @param i
     *            经验值
     */
    public final void setExp(final double i) {
        this._exp = i;
    }

    /** 设定命中率 */
    public void setHit(final int i) {
        this._hit = i;
    }

    /** 设定回血 */
    public void setHpr(final int i) {
        this._hpr = i;
    }

    /** 设定回血时间 */
    public void setHprTime(final boolean i) {
        this._hprTime = i;
    }

    /** 设定道具ID */
    public void setItemId(final int i) {
        this._itemId = i;
    }

    /** 设定取得道具 */
    public void setMakeItemId(final int i) {
        this._makeItemId = i;
    }

    /** 设定回魔 */
    public void setMpr(final int i) {
        this._mpr = i;
    }

    /** 设定回魔时间 */
    public void setMprTime(final boolean i) {
        this._mprTime = i;
    }

    /** 设定闇黑耐性 */
    public void setRegistBlind(final int i) {
        this._registBlind = i;
    }

    /** 设定寒冰耐性 */
    public void setRegistFreeze(final int i) {
        this._registFreeze = i;
    }

    /** 设定睡眠耐性 */
    public void setRegistSleep(final int i) {
        this._registSleep = i;
    }

    /** 设定石化耐性 */
    public void setRegistStone(final int i) {
        this._registStone = i;
    }

    /** 设定昏迷耐性 */
    public void setRegistStun(final int i) {
        this._registStun = i;
    }

    /** 设定支撑耐性 */
    public void setRegistSustain(final int i) {
        this._registSustain = i;
    }

    /** 设定负重轻减 */
    public void setWeightReduction(final int i) {
        this._weightReduction = i;
    }

}
