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

package com.lineage.server;

/**
 * 动作代码
 */
public class ActionCodes {

    /** 出现动作 */
    public static final int ACTION_Appear = 4;

    /** 隐藏动作 */
    public static final int ACTION_Hide = 11;
    /** 安塔瑞斯隐藏动作 */
    public static final int ACTION_AntharasHide = 20;
    /** 移动动作 */
    public static final int ACTION_Walk = 0;
    /** 攻击动作 */
    public static final int ACTION_Attack = 1;
    /** 受伤动作 */
    public static final int ACTION_Damage = 2;
    /** 空闲动作 */
    public static final int ACTION_Idle = 3;
    /** 持剑移动动作 */
    public static final int ACTION_SwordWalk = 4;
    /** 持剑攻击动作 */
    public static final int ACTION_SwordAttack = 5;
    /** 持剑受伤动作 */
    public static final int ACTION_SwordDamage = 6;
    /** 持剑空闲动作 */
    public static final int ACTION_SwordIdle = 7;
    /** 死亡动作 */
    public static final int ACTION_Die = 8;
    /** 持斧移动动作 */
    public static final int ACTION_AxeWalk = 11;
    /** 持斧攻击动作 */
    public static final int ACTION_AxeAttack = 12;
    /** 持斧受伤动作 */
    public static final int ACTION_AxeDamage = 13;
    /** 持斧空闲动作 */
    public static final int ACTION_AxeIdle = 14;
    /** 隐藏伤害动作 */
    public static final int ACTION_HideDamage = 13;
    /** 隐藏空闲动作 */
    public static final int ACTION_HideIdle = 14;
    /** 捡取物品动作 */
    public static final int ACTION_Pickup = 15;
    /** 抛掷动作 */
    public static final int ACTION_Throw = 16;
    /** 魔杖动作 */
    public static final int ACTION_Wand = 17;
    /** 攻击技能动作 */
    public static final int ACTION_SkillAttack = 18;
    /** Buff技能动作 */
    public static final int ACTION_SkillBuff = 19;
    /** 持弓移动动作 */
    public static final int ACTION_BowWalk = 20;
    /** 持弓攻击动作 */
    public static final int ACTION_BowAttack = 21;
    /** 持弓受伤动作 */
    public static final int ACTION_BowDamage = 22;
    /** 持弓空闲动作 */
    public static final int ACTION_BowIdle = 23;
    /** 持矛移动动作 */
    public static final int ACTION_SpearWalk = 24;
    /** 持矛攻击动作 */
    public static final int ACTION_SpearAttack = 25;
    /** 持矛受伤动作 */
    public static final int ACTION_SpearDamage = 26;
    /** 持矛空闲动作 */
    public static final int ACTION_SpearIdle = 27;
    /** 开箱动作 */
    public static final int ACTION_On = 28;
    /** 关箱动作 */
    public static final int ACTION_Off = 29;
    /** 开门动作 */
    public static final int ACTION_Open = 28;
    /** 关门动作 */
    public static final int ACTION_Close = 29;
    /** 方向(南)动作 */
    public static final int ACTION_South = 28;
    /** 方向(西)动作 */
    public static final int ACTION_West = 29;
    /** Alt键攻击动作 */
    public static final int ACTION_AltAttack = 30;
    /** 额外法术方向动作 */
    public static final int ACTION_SpellDirectionExtra = 31;
    /** 塔裂痕动作1 */
    public static final int ACTION_TowerCrack1 = 32;
    /** 塔裂痕动作 2 */
    public static final int ACTION_TowerCrack2 = 33;
    /** 塔裂痕动作 3 */
    public static final int ACTION_TowerCrack3 = 34;
    /** 塔死亡动作 */
    public static final int ACTION_TowerDie = 35;
    /** 门损坏动作1 */
    public static final int ACTION_DoorAction1 = 32;
    /** 门损坏动作 2 */
    public static final int ACTION_DoorAction2 = 33;
    /** 门损坏动作 3 */
    public static final int ACTION_DoorAction3 = 34;
    /** 门损坏动作 4 */
    public static final int ACTION_DoorAction4 = 35;
    /** 门损坏动作 5 */
    public static final int ACTION_DoorAction5 = 36;
    /** 门死亡动作 */
    public static final int ACTION_DoorDie = 37;
    /** 持魔杖移动动作 */
    public static final int ACTION_StaffWalk = 40;
    /** 持魔杖攻击动作 */
    public static final int ACTION_StaffAttack = 41;
    /** 持魔杖受伤动作 */
    public static final int ACTION_StaffDamage = 42;
    /** 持魔杖空闲动作 */
    public static final int ACTION_StaffIdle = 43;
    /** 上移动作 */
    public static final int ACTION_Moveup = 44;
    /** 下移动作 */
    public static final int ACTION_Movedown = 45;
    /** 持匕首移动动作 */
    public static final int ACTION_DaggerWalk = 46;
    /** 持匕首攻击动作 */
    public static final int ACTION_DaggerAttack = 47;
    /** 持匕首受伤动作 */
    public static final int ACTION_DaggerDamage = 48;
    /** 持匕首空闲动作 */
    public static final int ACTION_DaggerIdle = 49;
    /** 持双手剑移动动作 */
    public static final int ACTION_TwoHandSwordWalk = 50;
    /** 持双手剑攻击动作 */
    public static final int ACTION_TwoHandSwordAttack = 51;
    /** 持双手剑受伤动作 */
    public static final int ACTION_TwoHandSwordDamage = 52;
    /** 持双手剑空闲动作 */
    public static final int ACTION_TwoHandSwordIdle = 53;
    /** 持双刀移动动作 */
    public static final int ACTION_EdoryuWalk = 54;
    /** 持双刀攻击动作 */
    public static final int ACTION_EdoryuAttack = 55;
    /** 持双刀受伤动作 */
    public static final int ACTION_EdoryuDamage = 56;
    /** 持双刀空闲动作 */
    public static final int ACTION_EdoryuIdle = 57;
    /** 持双爪移动动作 */
    public static final int ACTION_ClawWalk = 58;
    /** 持双爪攻击动作 */
    public static final int ACTION_ClawAttack = 59;
    /** 持双爪受伤动作 */
    public static final int ACTION_ClawDamage = 60;
    /** 持双爪空闲动作 */
    public static final int ACTION_ClawIdle = 61;
    /** 持铁手甲移动动作 */
    public static final int ACTION_ThrowingKnifeWalk = 62;
    /** 持铁手甲攻击动作 */
    public static final int ACTION_ThrowingKnifeAttack = 63;
    /** 持铁手甲受伤动作 */
    public static final int ACTION_ThrowingKnifeDamage = 64;
    /** 持铁手甲空闲动作 */
    public static final int ACTION_ThrowingKnifeIdle = 65;
    /** 思考动作 Alt+4 */
    public static final int ACTION_Think = 66; // Alt+4
    /** 挑衅动作 Alt+3 */
    public static final int ACTION_Aggress = 67; // Alt+3
    /** 迎接动作 Alt+1 */
    public static final int ACTION_Salute = 68; // Alt+1
    /** 欢呼动作 Alt+2 */
    public static final int ACTION_Cheer = 69; // Alt+2
    /** 商店动作 */
    public static final int ACTION_Shop = 70;
    /** 钓鱼动作 */
    public static final int ACTION_Fishing = 71;

    public ActionCodes() {
    }

}
