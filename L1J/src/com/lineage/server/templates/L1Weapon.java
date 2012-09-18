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
 * 武器相关
 */
public class L1Weapon extends L1Item {

    /**
     * 序列版本UID
     */
    private static final long serialVersionUID = 1L;

    /** 射程范围 */
    private int _range = 0;

    /** 命中率修正 */
    private int _hitModifier = 0;

    /** 伤害修正 */
    private int _dmgModifier = 0;

    /** DB、双倍伤害发动几率 */
    private int _doubleDmgChance;

    /** 魔法攻击的伤害修正 */
    private int _magicDmgModifier = 0;

    /** 有无损伤 */
    private int _canbedmg = 0;

    public L1Weapon() {
    }

    @Override
    public int get_canbedmg() {
        return this._canbedmg;
    }

    @Override
    public int getDmgModifier() {
        return this._dmgModifier;
    }

    @Override
    public int getDoubleDmgChance() {
        return this._doubleDmgChance;
    }

    @Override
    public int getHitModifier() {
        return this._hitModifier;
    }

    @Override
    public int getMagicDmgModifier() {
        return this._magicDmgModifier;
    }

    @Override
    public int getRange() {
        return this._range;
    }

    @Override
    public boolean isTwohandedWeapon() {
        final int weapon_type = this.getType();

        final boolean bool = ((weapon_type == 3 // 巨剑
                )
                || (weapon_type == 4 // 弓
                ) || (weapon_type == 5 // 长矛
                ) || (weapon_type == 11 // 钢爪
                ) || (weapon_type == 12 // 双刀
                ) || (weapon_type == 15 // 双手斧
                ) || (weapon_type == 16 // 双手魔杖
                ) || (weapon_type == 18 // 锁链剑
                ) || (weapon_type == 19 // 不明
        ));

        return bool;
    }

    /** 设定有无损伤 */
    public void set_canbedmg(final int i) {
        this._canbedmg = i;
    }

    /** 设定伤害修正 */
    public void setDmgModifier(final int i) {
        this._dmgModifier = i;
    }

    /** 设定DB、双倍伤害发动几率 */
    public void setDoubleDmgChance(final int i) {
        this._doubleDmgChance = i;
    }

    /** 设定命中率修正 */
    public void setHitModifier(final int i) {
        this._hitModifier = i;
    }

    /** 设定魔法攻击的伤害修正 */
    public void setMagicDmgModifier(final int i) {
        this._magicDmgModifier = i;
    }

    /** 设定射程范围 */
    public void setRange(final int i) {
        this._range = i;
    }
}
