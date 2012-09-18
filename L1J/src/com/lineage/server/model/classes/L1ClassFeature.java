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
package com.lineage.server.model.classes;

import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 角色的类别
 */
public abstract class L1ClassFeature {

    public static L1ClassFeature newClassFeature(final int classId) {
        if ((classId == L1PcInstance.CLASSID_PRINCE // 王子
                )
                || (classId == L1PcInstance.CLASSID_PRINCESS)) { // 公主
            return new L1RoyalClassFeature();
        }
        if ((classId == L1PcInstance.CLASSID_ELF_MALE // 男精灵
                )
                || (classId == L1PcInstance.CLASSID_ELF_FEMALE)) { // 女精灵
            return new L1ElfClassFeature();
        }
        if ((classId == L1PcInstance.CLASSID_KNIGHT_MALE // 男骑士
                )
                || (classId == L1PcInstance.CLASSID_KNIGHT_FEMALE)) { // 女骑士
            return new L1KnightClassFeature();
        }
        if ((classId == L1PcInstance.CLASSID_WIZARD_MALE // 男法师
                )
                || (classId == L1PcInstance.CLASSID_WIZARD_FEMALE)) { // 女法师
            return new L1WizardClassFeature();
        }
        if ((classId == L1PcInstance.CLASSID_DARK_ELF_MALE // 男黑暗精灵
                )
                || (classId == L1PcInstance.CLASSID_DARK_ELF_FEMALE)) { // 女黑暗精灵
            return new L1DarkElfClassFeature();
        }
        if ((classId == L1PcInstance.CLASSID_DRAGON_KNIGHT_MALE // 男龙骑士
                )
                || (classId == L1PcInstance.CLASSID_DRAGON_KNIGHT_FEMALE)) { // 女龙骑士
            return new L1DragonKnightClassFeature();
        }
        if ((classId == L1PcInstance.CLASSID_ILLUSIONIST_MALE // 男幻术师
                )
                || (classId == L1PcInstance.CLASSID_ILLUSIONIST_FEMALE)) { // 女幻术师
            return new L1IllusionistClassFeature();
        }
        throw new IllegalArgumentException();
    }

    /** 取得最高AC防御 */
    public abstract int getAcDefenseMax(int ac);

    /** 取得最高魔法等级 */
    public abstract int getMagicLevel(int playerLevel);

}
