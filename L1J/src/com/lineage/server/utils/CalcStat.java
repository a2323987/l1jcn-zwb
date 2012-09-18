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
package com.lineage.server.utils;

import com.lineage.Config;

/**
 * 计算统计各属性对角色的影响
 */
public class CalcStat {

    /**
     * 追加敏捷对AC的影响
     * 
     * @param level
     * @param dex
     * @return acBonus
     */
    public static int calcAc(final int level, final int dex) {
        int acBonus = 10;
        if (dex <= 9) {
            acBonus -= level / 8;
        } else if ((dex >= 10) && (dex <= 12)) {
            acBonus -= level / 7;
        } else if ((dex >= 13) && (dex <= 15)) {
            acBonus -= level / 6;
        } else if ((dex >= 16) && (dex <= 17)) {
            acBonus -= level / 5;
        } else if (dex >= 18) {
            acBonus -= level / 4;
        }
        return acBonus;
    }

    public static int calcDiffMr(final int wis, final int diff) {
        return calcStatMr(wis + diff) - calcStatMr(wis);
    }

    /**
     * 计算各职业LVUP时的HP上升值
     * 
     * @param charType
     *            职业
     * @param baseMaxHp
     *            目前的最大HP
     * @param baseCon
     *            体质
     * @param originalHpup
     * @return HP上升后的数值
     */
    public static short calcStatHp(final int charType, final int baseMaxHp,
            final short baseCon, final int originalHpup) {
        short randomhp = 0;
        if (baseCon > 15) {
            randomhp = (short) (baseCon - 15);
        }
        if (charType == 0) { // 王族
            randomhp += (short) (11 + Random.nextInt(2)); // 初期值分追加

            if (baseMaxHp + randomhp > Config.PRINCE_MAX_HP) {
                randomhp = (short) (Config.PRINCE_MAX_HP - baseMaxHp);
            }
        } else if (charType == 1) { // 骑士
            randomhp += (short) (17 + Random.nextInt(2)); // 初期值分追加

            if (baseMaxHp + randomhp > Config.KNIGHT_MAX_HP) {
                randomhp = (short) (Config.KNIGHT_MAX_HP - baseMaxHp);
            }
        } else if (charType == 2) { // 精灵
            randomhp += (short) (10 + Random.nextInt(2)); // 初期值分追加

            if (baseMaxHp + randomhp > Config.ELF_MAX_HP) {
                randomhp = (short) (Config.ELF_MAX_HP - baseMaxHp);
            }
        } else if (charType == 3) { // 法师
            randomhp += (short) (7 + Random.nextInt(2)); // 初期值分追加

            if (baseMaxHp + randomhp > Config.WIZARD_MAX_HP) {
                randomhp = (short) (Config.WIZARD_MAX_HP - baseMaxHp);
            }
        } else if (charType == 4) { // 黑暗精灵
            randomhp += (short) (10 + Random.nextInt(2)); // 初期值分追加

            if (baseMaxHp + randomhp > Config.DARKELF_MAX_HP) {
                randomhp = (short) (Config.DARKELF_MAX_HP - baseMaxHp);
            }
        } else if (charType == 5) { // 龙骑士
            randomhp += (short) (13 + Random.nextInt(2)); // 初期值分追加

            if (baseMaxHp + randomhp > Config.DRAGONKNIGHT_MAX_HP) {
                randomhp = (short) (Config.DRAGONKNIGHT_MAX_HP - baseMaxHp);
            }
        } else if (charType == 6) { // 幻术师
            randomhp += (short) (9 + Random.nextInt(2)); // 初期值分追加

            if (baseMaxHp + randomhp > Config.ILLUSIONIST_MAX_HP) {
                randomhp = (short) (Config.ILLUSIONIST_MAX_HP - baseMaxHp);
            }
        }

        randomhp += originalHpup;

        if (randomhp < 0) {
            randomhp = 0;
        }
        return randomhp;
    }

    /**
     * <b>计算各职业LVUP时的MP上升值</b>
     * 
     * @param charType
     *            职业
     * @param baseMaxMp
     *            目前的最大MP
     * @param baseWis
     *            精神
     * @param originalMpup
     * @return MP上升后的数值
     */
    public static short calcStatMp(final int charType, final int baseMaxMp,
            final short baseWis, final int originalMpup) {
        int randommp = 0;
        int seedY = 0;
        int seedZ = 0;
        if ((baseWis < 9) || ((baseWis > 9) && (baseWis < 12))) {
            seedY = 2;
        } else if ((baseWis == 9) || ((baseWis >= 12) && (baseWis <= 17))) {
            seedY = 3;
        } else if (((baseWis >= 18) && (baseWis <= 23)) || (baseWis == 25)
                || (baseWis == 26) || (baseWis == 29) || (baseWis == 30)
                || (baseWis == 33) || (baseWis == 34)) {
            seedY = 4;
        } else if ((baseWis == 24) || (baseWis == 27) || (baseWis == 28)
                || (baseWis == 31) || (baseWis == 32) || (baseWis >= 35)) {
            seedY = 5;
        }

        if ((baseWis >= 7) && (baseWis <= 9)) {
            seedZ = 0;
        } else if ((baseWis >= 10) && (baseWis <= 14)) {
            seedZ = 1;
        } else if ((baseWis >= 15) && (baseWis <= 20)) {
            seedZ = 2;
        } else if ((baseWis >= 21) && (baseWis <= 24)) {
            seedZ = 3;
        } else if ((baseWis >= 25) && (baseWis <= 28)) {
            seedZ = 4;
        } else if ((baseWis >= 29) && (baseWis <= 32)) {
            seedZ = 5;
        } else if (baseWis >= 33) {
            seedZ = 6;
        }

        randommp = Random.nextInt(seedY) + 1 + seedZ;

        if (charType == 0) { // 王族
            if (baseMaxMp + randommp > Config.PRINCE_MAX_MP) {
                randommp = Config.PRINCE_MAX_MP - baseMaxMp;
            }
        } else if (charType == 1) { // 骑士
            randommp = (randommp * 2 / 3);
            if (baseMaxMp + randommp > Config.KNIGHT_MAX_MP) {
                randommp = Config.KNIGHT_MAX_MP - baseMaxMp;
            }
        } else if (charType == 2) { // 精灵
            randommp = (int) (randommp * 1.5);

            if (baseMaxMp + randommp > Config.ELF_MAX_MP) {
                randommp = Config.ELF_MAX_MP - baseMaxMp;
            }
        } else if (charType == 3) { // 法师
            randommp *= 2;

            if (baseMaxMp + randommp > Config.WIZARD_MAX_MP) {
                randommp = Config.WIZARD_MAX_MP - baseMaxMp;
            }
        } else if (charType == 4) { // 黑暗精灵
            randommp = (int) (randommp * 1.5);

            if (baseMaxMp + randommp > Config.DARKELF_MAX_MP) {
                randommp = Config.DARKELF_MAX_MP - baseMaxMp;
            }
        } else if (charType == 5) { // 龙骑士
            randommp = (randommp * 2 / 3);

            if (baseMaxMp + randommp > Config.DRAGONKNIGHT_MAX_MP) {
                randommp = Config.DRAGONKNIGHT_MAX_MP - baseMaxMp;
            }
        } else if (charType == 6) { // 幻术师
            randommp = (randommp * 5 / 3);

            if (baseMaxMp + randommp > Config.ILLUSIONIST_MAX_MP) {
                randommp = Config.ILLUSIONIST_MAX_MP - baseMaxMp;
            }
        }

        randommp += originalMpup;

        if (randommp < 0) {
            randommp = 0;
        }
        return (short) randommp;
    }

    /**
     * <b> 追加精神对抗魔的影响 </b>
     * 
     * @param wis
     *            精神点数
     * @return mrBonus 抗魔值
     */
    public static int calcStatMr(final int wis) {
        int mrBonus = 0;
        if (wis <= 14) {
            mrBonus = 0;
        } else if ((wis >= 15) && (wis <= 16)) {
            mrBonus = 3;
        } else if (wis == 17) {
            mrBonus = 6;
        } else if (wis == 18) {
            mrBonus = 10;
        } else if (wis == 19) {
            mrBonus = 15;
        } else if (wis == 20) {
            mrBonus = 21;
        } else if (wis == 21) {
            mrBonus = 28;
        } else if (wis == 22) {
            mrBonus = 37;
        } else if (wis == 23) {
            mrBonus = 47;
        } else if ((wis >= 24) && (wis <= 29)) {
            mrBonus = 50;
        } else if ((wis >= 30) && (wis <= 34)) {
            mrBonus = 52;
        } else if ((wis >= 35) && (wis <= 39)) {
            mrBonus = 55;
        } else if ((wis >= 40) && (wis <= 43)) {
            mrBonus = 59;
        } else if ((wis >= 44) && (wis <= 46)) {
            mrBonus = 62;
        } else if ((wis >= 47) && (wis <= 49)) {
            mrBonus = 64;
        } else if (wis == 50) {
            mrBonus = 65;
        } else {
            mrBonus = 65;
        }
        return mrBonus;
    }

    private CalcStat() {

    }
}
