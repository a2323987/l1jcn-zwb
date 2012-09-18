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
package com.lineage.server.model;

import static com.lineage.server.model.skill.L1SkillId.AWAKEN_ANTHARAS;
import static com.lineage.server.model.skill.L1SkillId.AWAKEN_FAFURION;
import static com.lineage.server.model.skill.L1SkillId.AWAKEN_VALAKAS;

import java.util.List;
import java.util.StringTokenizer;

import com.lineage.server.datatables.ArmorSetTable;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1ArmorSets;
import com.lineage.server.utils.collections.Lists;

/**
 * 套装效果:物理、血、魔、魔防.
 */
class AcHpMpBonusEffect implements L1ArmorSetEffect {
    /** 物理防御. */
    private final int ac;
    /** 增加血量. */
    private final int addHp;
    /** 增加魔量. */
    private final int addMp;
    /** 回复血量. */
    private final int regenHp;
    /** 回复魔量. */
    private final int regenMp;
    /** 增加魔防. */
    private final int addMr;

    /**
     * 套装效果:物理、血、魔、魔防.
     * 
     * @param a
     *            物理防御
     * @param hp
     *            增加血量
     * @param mp
     *            增加魔量
     * @param hpr
     *            回复血量
     * @param mpr
     *            回复魔量
     * @param mr
     *            增加魔防
     */
    public AcHpMpBonusEffect(final int a, final int hp, final int mp,
            final int hpr, final int mpr, final int mr) {
        this.ac = a;
        this.addHp = hp;
        this.addMp = mp;
        this.regenHp = hpr;
        this.regenMp = mpr;
        this.addMr = mr;
    }

    @Override
    public void cancelEffect(final L1PcInstance pc) {
        pc.addAc(-this.ac);
        pc.addMaxHp(-this.addHp);
        pc.addMaxMp(-this.addMp);
        pc.addHpr(-this.regenHp);
        pc.addMpr(-this.regenMp);
        pc.addMr(-this.addMr);
    }

    @Override
    public void giveEffect(final L1PcInstance pc) {
        pc.addAc(this.ac);
        pc.addMaxHp(this.addHp);
        pc.addMaxMp(this.addMp);
        pc.addHpr(this.regenHp);
        pc.addMpr(this.regenMp);
        pc.addMr(this.addMr);
    }
}

/**
 * 套装效果:水、风、火、地属性.
 */
class DefenseBonusEffect implements L1ArmorSetEffect {
    /** 属性防御:水. */
    private final int defenseWater;
    /** 属性防御:风. */
    private final int defenseWind;
    /** 属性防御:火. */
    private final int defenseFire;
    /** 属性防御:地. */
    private final int defenseEarth;

    /**
     * 套装效果:水、风、火、地属性.
     * 
     * @param water
     *            水属性
     * @param wind
     *            风属性
     * @param fire
     *            火属性
     * @param earth
     *            地属性
     */
    public DefenseBonusEffect(final int water, final int wind, final int fire,
            final int earth) {
        this.defenseWater = water;
        this.defenseWind = wind;
        this.defenseFire = fire;
        this.defenseEarth = earth;
    }

    @Override
    public void cancelEffect(final L1PcInstance pc) {
        pc.addWater(-this.defenseWater);
        pc.addWind(-this.defenseWind);
        pc.addFire(-this.defenseFire);
        pc.addEarth(-this.defenseEarth);
    }

    @Override
    public void giveEffect(final L1PcInstance pc) {
        pc.addWater(this.defenseWater);
        pc.addWind(this.defenseWind);
        pc.addFire(this.defenseFire);
        pc.addEarth(this.defenseEarth);
    }
}

/**
 * 套装效果:动画效果、获得特定道具.
 */
class GfxEffect implements L1ArmorSetEffect {
    /** 动画效果. */
    private final int gfxEffect;
    /** 间隔时间:动画效果. */
    private final int gfxEffectTime;
    /** 获得道具. */
    private final int obtainProps;
    /** 获得道具间隔时间(秒). */
    private final int obtainPropsTime;

    /**
     * 套装效果:动画效果.
     * 
     * @param effect
     *            动画效果
     * @param effectTime
     *            间隔时间
     * @param props
     *            获得道具
     * @param propsTime
     *            获得道具间隔时间(秒)
     */
    public GfxEffect(final int effect, final int effectTime, final int props,
            final int propsTime) {
        this.gfxEffect = effect;
        this.gfxEffectTime = effectTime;
        this.obtainProps = props;
        this.obtainPropsTime = propsTime;
    }

    @Override
    public void cancelEffect(final L1PcInstance pc) {
        pc.setGfxEffect(0);
        pc.setGfxEffectTime(0);
        pc.setObtainProps(0);
        pc.setObtainPropsTime(0);
    }

    @Override
    public void giveEffect(final L1PcInstance pc) {
        pc.setGfxEffect(this.gfxEffect);
        pc.setGfxEffectTime(this.gfxEffectTime);
        pc.setObtainProps(this.obtainProps);
        pc.setObtainPropsTime(this.obtainPropsTime);
    }
}

/**
 * 套装效果:命中率、伤害值、魔攻.
 */
class HitDmgModifierEffect implements L1ArmorSetEffect {
    /** 命中率:近战. */
    private final int hitModifier;
    /** 伤害值:近战. */
    private final int dmgModifier;
    /** 命中率:远战. */
    private final int bowHitModifier;
    /** 伤害值:远战. */
    private final int bowDmgModifier;
    /** 魔攻. */
    private final int sp;

    /**
     * 套装效果:命中率、伤害值、魔攻.
     * 
     * @param hit
     *            命中率:近战
     * @param dmg
     *            伤害值:近战
     * @param bowHit
     *            命中率:远战
     * @param bowDmg
     *            伤害值:远战
     * @param s
     *            魔攻
     */
    public HitDmgModifierEffect(final int hit, final int dmg, final int bowHit,
            final int bowDmg, final int s) {
        this.hitModifier = hit;
        this.dmgModifier = dmg;
        this.bowHitModifier = bowHit;
        this.bowDmgModifier = bowDmg;
        this.sp = s;
    }

    @Override
    public void cancelEffect(final L1PcInstance pc) {
        pc.addHitModifierByArmor(-this.hitModifier);
        pc.addDmgModifierByArmor(-this.dmgModifier);
        pc.addBowHitModifierByArmor(-this.bowHitModifier);
        pc.addBowDmgModifierByArmor(-this.bowDmgModifier);
        pc.addSp(-this.sp);
    }

    @Override
    public void giveEffect(final L1PcInstance pc) {
        pc.addHitModifierByArmor(this.hitModifier);
        pc.addDmgModifierByArmor(this.dmgModifier);
        pc.addBowHitModifierByArmor(this.bowHitModifier);
        pc.addBowDmgModifierByArmor(this.bowDmgModifier);
        pc.addSp(this.sp);
    }
}

/**
 * Class <code>L1ArmorSet</code> 套装效果:抽象类及其实现类.
 * 
 * @author jrwz
 * @version 2012-4-3上午03:32:07
 * @see com.lineage.server.model
 * @since JDK1.7
 */
public abstract class L1ArmorSet {
    /** 所有套装. */
    private static List<L1ArmorSet> allSet = Lists.newList();

    /*
     * 这里的初始化会觉得是丑什么的〜〜V
     */
    static {
        L1ArmorSetImpl impl;

        for (final L1ArmorSets armorSets : ArmorSetTable.getInstance()
                .getAllList()) {
            try {

                impl = new L1ArmorSetImpl(getArray(armorSets.getSets(), ","));
                if (armorSets.getPolyId() != -1) {
                    impl.addEffect(new PolymorphEffect(armorSets.getPolyId()));
                }
                impl.addEffect(new AcHpMpBonusEffect(armorSets.getAc(), //
                        armorSets.getHp(), //
                        armorSets.getMp(), //
                        armorSets.getHpr(), //
                        armorSets.getMpr(), //
                        armorSets.getMr()));
                impl.addEffect(new StatBonusEffect(armorSets.getStr(), //
                        armorSets.getDex(), //
                        armorSets.getCon(), //
                        armorSets.getWis(), //
                        armorSets.getCha(), //
                        armorSets.getIntl()));
                impl.addEffect(new DefenseBonusEffect(armorSets
                        .getDefenseWater(), //
                        armorSets.getDefenseWind(), //
                        armorSets.getDefenseFire(), //
                        armorSets.getDefenseWind()));
                impl.addEffect(new HitDmgModifierEffect(armorSets
                        .getHitModifier(), //
                        armorSets.getDmgModifier(), //
                        armorSets.getBowHitModifier(), //
                        armorSets.getBowDmgModifier(), //
                        armorSets.getSp()));
                impl.addEffect(new GfxEffect(armorSets.getGfxEffect(),
                        armorSets.getGfxEffectTime(), //
                        armorSets.getObtainProps(), //
                        armorSets.getObtainPropsTime()));
                allSet.add(impl);
            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 取得所有套装.
     * 
     * @return 所有套装
     */
    public static List<L1ArmorSet> getAllSet() {
        return allSet;
    }

    /**
     * 取得阵列.
     * 
     * @param s
     *            解析的字串
     * @param sToken
     *            分隔符
     * @return 阵列
     */
    private static int[] getArray(final String s, final String sToken) {
        final StringTokenizer st = new StringTokenizer(s, sToken);
        final int size = st.countTokens();
        String temp = null;
        final int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            temp = st.nextToken();
            array[i] = Integer.parseInt(temp);
        }
        return array;
    }

    /**
     * 取消效果.
     * 
     * @param pc
     *            角色
     */
    public abstract void cancelEffect(L1PcInstance pc);

    /**
     * 给予效果.
     * 
     * @param pc
     *            角色
     */
    public abstract void giveEffect(L1PcInstance pc);

    /**
     * 是否装备的为戒指.
     * 
     * @param pc
     *            角色
     * @return true = 是、 false = 否
     */
    public abstract boolean isEquippedRingOfArmorSet(L1PcInstance pc);

    /**
     * 是否套装的零件.
     * 
     * @param id
     *            零件ID
     * @return true = 是、 false = 否
     */
    public abstract boolean isPartOfSet(int id);

    /**
     * 是否有效果.
     * 
     * @param pc
     *            角色
     * @return 套装效果
     */
    public abstract boolean isValid(L1PcInstance pc);
}

/**
 * 套装效果接口.
 */
interface L1ArmorSetEffect {
    /**
     * 套装效果:取消.
     * 
     * @param pc
     *            角色
     */
    void cancelEffect(L1PcInstance pc);

    /**
     * 套装效果:给予.
     * 
     * @param pc
     *            角色
     */
    void giveEffect(L1PcInstance pc);
}

/**
 * 套装效果入口.
 */
class L1ArmorSetImpl extends L1ArmorSet {
    /** 套装编号阵列. */
    private final int[] ids;
    /** 套装效果. */
    private final List<L1ArmorSetEffect> effects;

    /**
     * 套装效果入口.
     * 
     * @param id
     *            套装编号阵列
     */
    protected L1ArmorSetImpl(final int[] id) {
        this.ids = id;
        this.effects = Lists.newList();
    }

    /**
     * 增加效果.
     * 
     * @param effect
     *            效果
     */
    public void addEffect(final L1ArmorSetEffect effect) {
        this.effects.add(effect);
    }

    @Override
    public void cancelEffect(final L1PcInstance pc) {
        for (final L1ArmorSetEffect effect : this.effects) {
            effect.cancelEffect(pc);
        }
    }

    @Override
    public void giveEffect(final L1PcInstance pc) {
        for (final L1ArmorSetEffect effect : this.effects) {
            effect.giveEffect(pc);
        }
    }

    @Override
    public boolean isEquippedRingOfArmorSet(final L1PcInstance pc) {
        final L1PcInventory pcInventory = pc.getInventory();
        L1ItemInstance armor = null;
        boolean isSetContainRing = false;

        for (final int id : this.ids) {
            armor = pcInventory.findItemId(id);
            if ((armor.getItem().getType2() == 2)
                    && (armor.getItem().getType() == 9)) { // 戒指
                isSetContainRing = true;
                break;
            }
        }

        if ((armor != null) && isSetContainRing) {
            final int itemId = armor.getItem().getItemId();
            if (pcInventory.getTypeEquipped(2, 9) == 2) {
                L1ItemInstance[] ring = new L1ItemInstance[2];
                ring = pcInventory.getRingEquipped();
                if ((ring[0].getItem().getItemId() == itemId)
                        && (ring[1].getItem().getItemId() == itemId)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isPartOfSet(final int id) {
        for (final int i : this.ids) {
            if (id == i) {
                return true;
            }
        }
        return false;
    }

    @Override
    public final boolean isValid(final L1PcInstance pc) {
        return pc.getInventory().checkEquipped(this.ids);
    }

    /**
     * 删除效果.
     * 
     * @param effect
     *            效果
     */
    public void removeEffect(final L1ArmorSetEffect effect) {
        this.effects.remove(effect);
    }

}

/**
 * 套装效果:变身.
 */
class PolymorphEffect implements L1ArmorSetEffect {
    /** 变身编号. */
    private int gfxId;

    /**
     * 套装效果:变身.
     * 
     * @param id
     *            变身编号
     */
    public PolymorphEffect(final int id) {
        this.gfxId = id;
    }

    @Override
    public void cancelEffect(final L1PcInstance pc) {
        final int awakeSkillId = pc.getAwakeSkillId();
        if ((awakeSkillId == AWAKEN_ANTHARAS)
                || (awakeSkillId == AWAKEN_FAFURION)
                || (awakeSkillId == AWAKEN_VALAKAS)) {
            final S_ServerMessage msg = new S_ServerMessage(1384);
            pc.sendPackets(msg); // 目前状态中无法变身。
            return;
        }
        if (this.gfxId == 6080) {
            if (pc.get_sex() == 0) {
                this.gfxId = 6094;
            }
        }
        if (pc.getTempCharGfx() != this.gfxId) {
            return;
        }
        L1PolyMorph.undoPoly(pc); // 解除变身
    }

    @Override
    public void giveEffect(final L1PcInstance pc) {
        final int awakeSkillId = pc.getAwakeSkillId();
        if ((awakeSkillId == AWAKEN_ANTHARAS)
                || (awakeSkillId == AWAKEN_FAFURION)
                || (awakeSkillId == AWAKEN_VALAKAS)) {
            final S_ServerMessage msg = new S_ServerMessage(1384);
            pc.sendPackets(msg); // 目前状态中无法变身。
            return;
        }
        if ((this.gfxId == 6080) || (this.gfxId == 6094)) {
            if (pc.get_sex() == 0) {
                this.gfxId = 6094;
            } else {
                this.gfxId = 6080;
            }
            if (!this.isRemainderOfCharge(pc)) { // 没有剩余次数
                return;
            }
        }
        L1PolyMorph.doPoly(pc, this.gfxId, 0, L1PolyMorph.MORPH_BY_ITEMMAGIC);
    }

    /**
     * 是否有剩余次数.
     * 
     * @param pc
     *            角色
     * @return 剩余次数
     */
    private boolean isRemainderOfCharge(final L1PcInstance pc) {
        boolean isRemainderOfCharge = false;
        if (pc.getInventory().checkItem(20383, 1)) { // 军马头盔
            final L1ItemInstance item = pc.getInventory().findItemId(20383);
            if (item != null) {
                if (item.getChargeCount() != 0) {
                    isRemainderOfCharge = true;
                }
            }
        }
        return isRemainderOfCharge;
    }

}

/**
 * 套装效果:六属性(力、敏、体、精、魅、智).
 */
class StatBonusEffect implements L1ArmorSetEffect {
    /** 力量. */
    private final int str;
    /** 敏捷. */
    private final int dex;
    /** 体质. */
    private final int con;
    /** 精神. */
    private final int wis;
    /** 魅力. */
    private final int cha;
    /** 智力. */
    private final int intl;

    /**
     * 套装效果:六属性(力、敏、体、精、魅、智).
     * 
     * @param a
     *            力量
     * @param b
     *            敏捷
     * @param c
     *            体质
     * @param d
     *            精神
     * @param e
     *            魅力
     * @param f
     *            智力
     */
    public StatBonusEffect(final int a, final int b, final int c, final int d,
            final int e, final int f) {
        this.str = a;
        this.dex = b;
        this.con = c;
        this.wis = d;
        this.cha = e;
        this.intl = f;
    }

    @Override
    public void cancelEffect(final L1PcInstance pc) {
        pc.addStr((byte) -this.str);
        pc.addDex((byte) -this.dex);
        pc.addCon((byte) -this.con);
        pc.addWis((byte) -this.wis);
        pc.addCha((byte) -this.cha);
        pc.addInt((byte) -this.intl);
    }

    @Override
    public void giveEffect(final L1PcInstance pc) {
        pc.addStr((byte) this.str);
        pc.addDex((byte) this.dex);
        pc.addCon((byte) this.con);
        pc.addWis((byte) this.wis);
        pc.addCha((byte) this.cha);
        pc.addInt((byte) this.intl);
    }
}
