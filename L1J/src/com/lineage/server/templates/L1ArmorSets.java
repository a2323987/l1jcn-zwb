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
 * MAY BE CONSIDERED TO BE A CONTRACT,
 * THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package com.lineage.server.templates;

/**
 * Class <code>L1ArmorSets</code> 数据库模板:套装.
 * 
 * @author jrwz
 * @version 2012-4-2下午10:38:03
 * @see com.lineage.server.templates
 * @since JDK1.7
 */
public final class L1ArmorSets {
    /** 流水编号. */
    private int id;
    /** 套装编号:资料表(armor.sql)装备ID、多件装备以逗号分隔(范例:20010,20100,20166,20198). */
    private String sets;
    /** 变身编号. */
    private int polyId;
    /** 物理防御. */
    private int ac;
    /** 增加血量. */
    private int hp;
    /** 增加魔量. */
    private int mp;
    /** 增加回血. */
    private int hpr;
    /** 增加回魔. */
    private int mpr;
    /** 增加魔防. */
    private int mr;
    /** 增加力量. */
    private int str;
    /** 增加敏捷. */
    private int dex;
    /** 增加体质. */
    private int con;
    /** 增加精神. */
    private int wis;
    /** 增加魅力. */
    private int cha;
    /** 增加智力. */
    private int intl;
    /** 增加命中率(近战). */
    private int hitModifier;
    /** 增加伤害值(近战). */
    private int dmgModifier;
    /** 增加命中率(远战). */
    private int bowHitModifier;
    /** 增加伤害值(远战). */
    private int bowDmgModifier;
    /** 增加魔攻. */
    private int sp;
    /** 增加属性防御(水). */
    private int defenseWater;
    /** 增加属性防御(风). */
    private int defenseWind;
    /** 增加属性防御(火). */
    private int defenseFire;
    /** 增加属性防御(地). */
    private int defenseEarth;
    /** 动画效果. */
    private int gfxEffect;
    /** 动画效果间隔时间(秒). */
    private int gfxEffectTime;
    /** 获得道具. */
    private int obtainProps;
    /** 获得道具间隔时间(秒). */
    private int obtainPropsTime;

    /** 数据库模板:套装. */
    public L1ArmorSets() {
    }

    /**
     * 取得物理防御.
     * 
     * @return 物理防御
     */
    public int getAc() {
        return this.ac;
    }

    /**
     * 取得伤害值:远战.
     * 
     * @return 远战伤害值
     */
    public int getBowDmgModifier() {
        return this.bowDmgModifier;
    }

    /**
     * 取得命中率:远战.
     * 
     * @return 远战命中率
     */
    public int getBowHitModifier() {
        return this.bowHitModifier;
    }

    /**
     * 取得魅力值.
     * 
     * @return 魅力值
     */
    public int getCha() {
        return this.cha;
    }

    /**
     * 取得体质值.
     * 
     * @return 体质值
     */
    public int getCon() {
        return this.con;
    }

    /**
     * 取得属性防御:地.
     * 
     * @return 属性防御:地
     */
    public int getDefenseEarth() {
        return this.defenseEarth;
    }

    /**
     * 取得属性防御:火.
     * 
     * @return 属性防御:火
     */
    public int getDefenseFire() {
        return this.defenseFire;
    }

    /**
     * 取得属性防御:水.
     * 
     * @return 属性防御:水
     */
    public int getDefenseWater() {
        return this.defenseWater;
    }

    /**
     * 取得属性防御:风.
     * 
     * @return 属性防御:风
     */
    public int getDefenseWind() {
        return this.defenseWind;
    }

    /**
     * 取得敏捷值.
     * 
     * @return 敏捷值
     */
    public int getDex() {
        return this.dex;
    }

    /**
     * 取得伤害值:近战.
     * 
     * @return 伤害值:近战
     */
    public int getDmgModifier() {
        return this.dmgModifier;
    }

    /**
     * 取得动画效果.
     * 
     * @return 动画效果
     */
    public int getGfxEffect() {
        return this.gfxEffect;
    }

    /**
     * 取得动画效果时间间隔.
     * 
     * @return 动画效果时间间隔
     */
    public int getGfxEffectTime() {
        return this.gfxEffectTime;
    }

    /**
     * 取得命中率:近战.
     * 
     * @return 命中率:近战
     */
    public int getHitModifier() {
        return this.hitModifier;
    }

    /**
     * 取得血量.
     * 
     * @return 血量
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * 取得回血.
     * 
     * @return 回血
     */
    public int getHpr() {
        return this.hpr;
    }

    /**
     * 取得流水编号.
     * 
     * @return 流水编号
     */
    public int getId() {
        return this.id;
    }

    /**
     * 取得智力值.
     * 
     * @return 智力值
     */
    public int getIntl() {
        return this.intl;
    }

    /**
     * 取得魔量.
     * 
     * @return 魔量
     */
    public int getMp() {
        return this.mp;
    }

    /**
     * 取得回魔.
     * 
     * @return 回魔
     */
    public int getMpr() {
        return this.mpr;
    }

    /**
     * 取得魔防.
     * 
     * @return 魔防
     */
    public int getMr() {
        return this.mr;
    }

    /**
     * 取得获得的道具.
     * 
     * @return the obtainProps
     */
    public int getObtainProps() {
        return this.obtainProps;
    }

    /**
     * 取得获得道具的时间间隔(秒).
     * 
     * @return the obtainPropsTime
     */
    public int getObtainPropsTime() {
        return this.obtainPropsTime;
    }

    /**
     * 取得变身编号.
     * 
     * @return 变身编号
     */
    public int getPolyId() {
        return this.polyId;
    }

    /**
     * 取得套装编号.
     * 
     * @return 套装编号
     */
    public String getSets() {
        return this.sets;
    }

    /**
     * 取得魔攻.
     * 
     * @return 魔攻
     */
    public int getSp() {
        return this.sp;
    }

    /**
     * 取得力量值.
     * 
     * @return 力量值
     */
    public int getStr() {
        return this.str;
    }

    /**
     * 取得精神值.
     * 
     * @return 精神值
     */
    public int getWis() {
        return this.wis;
    }

    /**
     * 设置物理防御.
     * 
     * @param i
     *            物理防御
     */
    public void setAc(final int i) {
        this.ac = i;
    }

    /**
     * 设置伤害值:远战.
     * 
     * @param i
     *            伤害值:远战
     */
    public void setBowDmgModifier(final int i) {
        this.bowDmgModifier = i;
    }

    /**
     * 设置命中率:远战.
     * 
     * @param i
     *            命中率:远战
     */
    public void setBowHitModifier(final int i) {
        this.bowHitModifier = i;
    }

    /**
     * 设置魅力值.
     * 
     * @param i
     *            魅力值
     */
    public void setCha(final int i) {
        this.cha = i;
    }

    /**
     * 设置体质值.
     * 
     * @param i
     *            体质值
     */
    public void setCon(final int i) {
        this.con = i;
    }

    /**
     * 设置属性防御:地.
     * 
     * @param i
     *            属性防御:地
     */
    public void setDefenseEarth(final int i) {
        this.defenseEarth = i;
    }

    /**
     * 设置属性防御:火.
     * 
     * @param i
     *            属性防御:火
     */
    public void setDefenseFire(final int i) {
        this.defenseFire = i;
    }

    /**
     * 设置属性防御:水.
     * 
     * @param i
     *            属性防御:水
     */
    public void setDefenseWater(final int i) {
        this.defenseWater = i;
    }

    /**
     * 设置属性防御:风.
     * 
     * @param i
     *            属性防御:风
     */
    public void setDefenseWind(final int i) {
        this.defenseWind = i;
    }

    /**
     * 设置敏捷值.
     * 
     * @param i
     *            敏捷值
     */
    public void setDex(final int i) {
        this.dex = i;
    }

    /**
     * 设置伤害值:近战.
     * 
     * @param i
     *            伤害值:近战
     */
    public void setDmgModifier(final int i) {
        this.dmgModifier = i;
    }

    /**
     * 设置动画效果.
     * 
     * @param i
     *            动画效果
     */
    public void setGfxEffect(final int i) {
        this.gfxEffect = i;
    }

    /**
     * 设置动画效果时间间隔.
     * 
     * @param i
     *            效果时间间隔
     */
    public void setGfxEffectTime(final int i) {
        this.gfxEffectTime = i;
    }

    /**
     * 设置命中率:近战.
     * 
     * @param i
     *            命中率:近战
     */
    public void setHitModifier(final int i) {
        this.hitModifier = i;
    }

    /**
     * 设置血量.
     * 
     * @param i
     *            血量
     */
    public void setHp(final int i) {
        this.hp = i;
    }

    /**
     * 设置回血.
     * 
     * @param i
     *            回血
     */
    public void setHpr(final int i) {
        this.hpr = i;
    }

    /**
     * 设置流水编号.
     * 
     * @param i
     *            流水编号
     */
    public void setId(final int i) {
        this.id = i;
    }

    /**
     * 设置智力值.
     * 
     * @param i
     *            智力值
     */
    public void setIntl(final int i) {
        this.intl = i;
    }

    /**
     * 设置魔量.
     * 
     * @param i
     *            魔量
     */
    public void setMp(final int i) {
        this.mp = i;
    }

    /**
     * 设置回魔.
     * 
     * @param i
     *            回魔
     */
    public void setMpr(final int i) {
        this.mpr = i;
    }

    /**
     * 设置魔防.
     * 
     * @param i
     *            魔防
     */
    public void setMr(final int i) {
        this.mr = i;
    }

    /**
     * 设置获得的道具.
     * 
     * @param i
     *            the obtainProps to set
     */
    public void setObtainProps(final int i) {
        this.obtainProps = i;
    }

    /**
     * 设置获得道具的时间间隔(秒).
     * 
     * @param i
     *            the obtainPropsTime to set
     */
    public void setObtainPropsTime(final int i) {
        this.obtainPropsTime = i;
    }

    /**
     * 设置变身编号.
     * 
     * @param i
     *            变身编号
     */
    public void setPolyId(final int i) {
        this.polyId = i;
    }

    /**
     * 设置套装编号.
     * 
     * @param s
     *            套装编号
     */
    public void setSets(final String s) {
        this.sets = s;
    }

    /**
     * 设置魔攻.
     * 
     * @param i
     *            魔攻
     */
    public void setSp(final int i) {
        this.sp = i;
    }

    /**
     * 设置力量值.
     * 
     * @param i
     *            力量值
     */
    public void setStr(final int i) {
        this.str = i;
    }

    /**
     * 设置精神值.
     * 
     * @param i
     *            精神值
     */
    public void setWis(final int i) {
        this.wis = i;
    }
}
