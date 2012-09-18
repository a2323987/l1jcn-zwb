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
 * 防具相关
 */
public class L1Armor extends L1Item {

    private static final long serialVersionUID = 1L;

    /** 物理防御 */
    private int _ac = 0;

    /** 伤害减免 */
    private int _damageReduction = 0;

    /** 负重轻减 */
    private int _weightReduction = 0;

    /** 装备的命中率修正 */
    private int _hitModifierByArmor = 0;

    /** 装备的伤害修正 */
    private int _dmgModifierByArmor = 0;

    /** 弓类装备的命中率修正 */
    private int _bowHitModifierByArmor = 0;

    /** 弓类装备的伤害修正 */
    private int _bowDmgModifierByArmor = 0;

    /** 水属性防御 */
    private int _defense_water = 0;

    /** 风属性防御 */
    private int _defense_wind = 0;

    /** 火属性防御 */
    private int _defense_fire = 0;

    /** 地属性防御 */
    private int _defense_earth = 0;

    /** 昏迷耐性 */
    private int _regist_stun = 0;

    /** 石化耐性 */
    private int _regist_stone = 0;

    /** 睡眠耐性 */
    private int _regist_sleep = 0;

    /** 寒冰耐性 */
    private int _regist_freeze = 0;

    /** 支撑耐性 */
    private int _regist_sustain = 0;

    /** 暗闇耐性 */
    private int _regist_blind = 0;

    /** 饰品级别 */
    private int _grade = 0;

    /** 经验值加成. */
    private double _expBonus = 0;

    public L1Armor() {
    }

    @Override
    public int get_ac() {
        return this._ac;
    }

    @Override
    public int get_defense_earth() {
        return this._defense_earth;
    }

    @Override
    public int get_defense_fire() {
        return this._defense_fire;
    }

    @Override
    public int get_defense_water() {
        return this._defense_water;
    }

    @Override
    public int get_defense_wind() {
        return this._defense_wind;
    }

    @Override
    public int get_regist_blind() {
        return this._regist_blind;
    }

    @Override
    public int get_regist_freeze() {
        return this._regist_freeze;
    }

    @Override
    public int get_regist_sleep() {
        return this._regist_sleep;
    }

    @Override
    public int get_regist_stone() {
        return this._regist_stone;
    }

    @Override
    public int get_regist_stun() {
        return this._regist_stun;
    }

    @Override
    public int get_regist_sustain() {
        return this._regist_sustain;
    }

    @Override
    public int getBowDmgModifierByArmor() {
        return this._bowDmgModifierByArmor;
    }

    @Override
    public int getBowHitModifierByArmor() {
        return this._bowHitModifierByArmor;
    }

    @Override
    public int getDamageReduction() {
        return this._damageReduction;
    }

    @Override
    public int getDmgModifierByArmor() {
        return this._dmgModifierByArmor;
    }

    /**
     * 取得经验值加成.
     * 
     * @return the _expBonus
     */
    @Override
    public final double getExpBonus() {
        return this._expBonus;
    }

    @Override
    public int getGrade() {
        return this._grade;
    }

    @Override
    public int getHitModifierByArmor() {
        return this._hitModifierByArmor;
    }

    @Override
    public int getWeightReduction() {
        return this._weightReduction;
    }

    /** 设定物理防御 */
    public void set_ac(final int i) {
        this._ac = i;
    }

    /** 设定地属性防御 */
    public void set_defense_earth(final int i) {
        this._defense_earth = i;
    }

    /** 设定火属性防御 */
    public void set_defense_fire(final int i) {
        this._defense_fire = i;
    }

    /** 设定水属性防御 */
    public void set_defense_water(final int i) {
        this._defense_water = i;
    }

    /** 设定风属性防御 */
    public void set_defense_wind(final int i) {
        this._defense_wind = i;
    }

    /** 设定暗闇耐性 */
    public void set_regist_blind(final int i) {
        this._regist_blind = i;
    }

    /** 设定寒冰耐性 */
    public void set_regist_freeze(final int i) {
        this._regist_freeze = i;
    }

    /** 设定睡眠耐性 */
    public void set_regist_sleep(final int i) {
        this._regist_sleep = i;
    }

    /** 设定石化耐性 */
    public void set_regist_stone(final int i) {
        this._regist_stone = i;
    }

    /** 设定昏迷耐性 */
    public void set_regist_stun(final int i) {
        this._regist_stun = i;
    }

    /** 设定支撑耐性 */
    public void set_regist_sustain(final int i) {
        this._regist_sustain = i;
    }

    /** 设定弓类装备的伤害修正 */
    public void setBowDmgModifierByArmor(final int i) {
        this._bowDmgModifierByArmor = i;
    }

    /** 设定弓类装备的命中率修正 */
    public void setBowHitModifierByArmor(final int i) {
        this._bowHitModifierByArmor = i;
    }

    /** 设定伤害减免 */
    public void setDamageReduction(final int i) {
        this._damageReduction = i;
    }

    /** 设定装备的伤害修正 */
    public void setDmgModifierByArmor(final int i) {
        this._dmgModifierByArmor = i;
    }

    /**
     * 设置经验值加成.
     * 
     * @param i
     *            the _expBonus to set
     */
    public final void setExpBonus(final double i) {
        this._expBonus = i;
    }

    /** 设定饰品级别 */
    public void setGrade(final int i) {
        this._grade = i;
    }

    /** 设定装备的命中率修正 */
    public void setHitModifierByArmor(final int i) {
        this._hitModifierByArmor = i;
    }

    /** 设定负重轻减 */
    public void setWeightReduction(final int i) {
        this._weightReduction = i;
    }

}
