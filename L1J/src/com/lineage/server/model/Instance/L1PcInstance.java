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
package com.lineage.server.model.Instance;

import static com.lineage.server.model.skill.L1SkillId.ABSOLUTE_BARRIER;
import static com.lineage.server.model.skill.L1SkillId.BLIND_HIDING;
import static com.lineage.server.model.skill.L1SkillId.BURNING_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.CANCELLATION;
import static com.lineage.server.model.skill.L1SkillId.COOKING_WONDER_DRUG;
import static com.lineage.server.model.skill.L1SkillId.COUNTER_BARRIER;
import static com.lineage.server.model.skill.L1SkillId.DECREASE_WEIGHT;
import static com.lineage.server.model.skill.L1SkillId.DRESS_EVASION;
import static com.lineage.server.model.skill.L1SkillId.EARTH_BIND;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_BLESS_OF_MAZU;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_ENCHANTING_BATTLE;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_AHTHARTS;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_BIRTH;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_FAFURION;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_FIGURE;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_LIFE;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_LINDVIOR;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_EYE_OF_VALAKAS;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_1;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_2;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_3;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_4;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_5;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_6;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_7;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_8;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_9;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_1;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_2;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_3;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_4;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_5;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_6;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_7;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_8;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_9;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_1;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_2;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_3;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_4;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_5;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_6;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_7;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_8;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_9;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_1;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_2;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_3;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_4;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_5;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_6;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_7;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_8;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_9;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_POTION_OF_BATTLE;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_POTION_OF_EXP_150;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_POTION_OF_EXP_175;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_POTION_OF_EXP_200;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_POTION_OF_EXP_225;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_POTION_OF_EXP_250;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_STRENGTHENING_HP;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_STRENGTHENING_MP;
import static com.lineage.server.model.skill.L1SkillId.ENTANGLE;
import static com.lineage.server.model.skill.L1SkillId.FIRE_BLESS;
import static com.lineage.server.model.skill.L1SkillId.FIRE_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.FOG_OF_SLEEPING;
import static com.lineage.server.model.skill.L1SkillId.FREEZING_BLIZZARD;
import static com.lineage.server.model.skill.L1SkillId.FREEZING_BREATH;
import static com.lineage.server.model.skill.L1SkillId.GMSTATUS_FINDINVIS;
import static com.lineage.server.model.skill.L1SkillId.GMSTATUS_HPBAR;
import static com.lineage.server.model.skill.L1SkillId.GREATER_HASTE;
import static com.lineage.server.model.skill.L1SkillId.HASTE;
import static com.lineage.server.model.skill.L1SkillId.ICE_LANCE;
import static com.lineage.server.model.skill.L1SkillId.ICE_LANCE_BASILISK;
import static com.lineage.server.model.skill.L1SkillId.ICE_LANCE_COCKATRICE;
import static com.lineage.server.model.skill.L1SkillId.ILLUSION_AVATAR;
import static com.lineage.server.model.skill.L1SkillId.INVISIBILITY;
import static com.lineage.server.model.skill.L1SkillId.MASS_SLOW;
import static com.lineage.server.model.skill.L1SkillId.MEDITATION;
import static com.lineage.server.model.skill.L1SkillId.MORTAL_BODY;
import static com.lineage.server.model.skill.L1SkillId.SHAPE_CHANGE;
import static com.lineage.server.model.skill.L1SkillId.SHOCK_SKIN;
import static com.lineage.server.model.skill.L1SkillId.SHOCK_STUN;
import static com.lineage.server.model.skill.L1SkillId.SLOW;
import static com.lineage.server.model.skill.L1SkillId.SOLID_CARRIAGE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CHAT_PROHIBITED;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CURSE_PARALYZED;
import static com.lineage.server.model.skill.L1SkillId.STATUS_HASTE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_RIBRAVE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_THIRD_SPEED;
import static com.lineage.server.model.skill.L1SkillId.STORM_EYE;
import static com.lineage.server.model.skill.L1SkillId.STORM_SHOT;
import static com.lineage.server.model.skill.L1SkillId.STRIKER_GALE;
import static com.lineage.server.model.skill.L1SkillId.WIND_SHACKLE;
import static com.lineage.server.model.skill.L1SkillId.WIND_SHOT;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.ActionCodes;
import com.lineage.server.ClientThread;
import com.lineage.server.GeneralThreadPool;
import com.lineage.server.PacketOutput;
import com.lineage.server.WarTimeController;
import com.lineage.server.command.executor.L1HpBar;
import com.lineage.server.datatables.CharacterTable;
import com.lineage.server.datatables.ExpTable;
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.model.AcceleratorChecker;
import com.lineage.server.model.HpRegeneration;
import com.lineage.server.model.HpRegenerationByDoll;
import com.lineage.server.model.ItemMakeByDoll;
import com.lineage.server.model.L1Attack;
import com.lineage.server.model.L1CastleLocation;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1ChatParty;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1DwarfForElfInventory;
import com.lineage.server.model.L1DwarfInventory;
import com.lineage.server.model.L1EquipmentSlot;
import com.lineage.server.model.L1ExcludingList;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.L1Karma;
import com.lineage.server.model.L1Magic;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1Party;
import com.lineage.server.model.L1PartyRefresh;
import com.lineage.server.model.L1PcDeleteTimer;
import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.L1PinkName;
import com.lineage.server.model.L1Quest;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.L1TownLocation;
import com.lineage.server.model.L1War;
import com.lineage.server.model.L1World;
import com.lineage.server.model.MpReductionByAwake;
import com.lineage.server.model.MpRegeneration;
import com.lineage.server.model.MpRegenerationByDoll;
import com.lineage.server.model.RocksPrison;
import com.lineage.server.model.TheCryOfSurvival;
import com.lineage.server.model.classes.L1ClassFeature;
import com.lineage.server.model.gametime.L1GameTimeCarrier;
import com.lineage.server.model.monitor.L1PcAutoUpdate;
import com.lineage.server.model.monitor.L1PcExpMonitor;
import com.lineage.server.model.monitor.L1PcGhostMonitor;
import com.lineage.server.model.monitor.L1PcHellMonitor;
import com.lineage.server.model.monitor.L1PcInvisDelay;
import com.lineage.server.model.skill.L1SkillId;
import com.lineage.server.model.skill.L1SkillUse;
import com.lineage.server.serverpackets.S_BlueMessage;
import com.lineage.server.serverpackets.S_CastleMaster;
import com.lineage.server.serverpackets.S_Disconnect;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_DoActionShop;
import com.lineage.server.serverpackets.S_Exp;
import com.lineage.server.serverpackets.S_Fight;
import com.lineage.server.serverpackets.S_Fishing;
import com.lineage.server.serverpackets.S_HPMeter;
import com.lineage.server.serverpackets.S_HPUpdate;
import com.lineage.server.serverpackets.S_Invis;
import com.lineage.server.serverpackets.S_Lawful;
import com.lineage.server.serverpackets.S_Liquor;
import com.lineage.server.serverpackets.S_MPUpdate;
import com.lineage.server.serverpackets.S_OtherCharPacks;
import com.lineage.server.serverpackets.S_OwnCharAttrDef;
import com.lineage.server.serverpackets.S_OwnCharStatus;
import com.lineage.server.serverpackets.S_OwnCharStatus2;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_Poison;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.serverpackets.S_SPMR;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillHaste;
import com.lineage.server.serverpackets.S_SkillIconGFX;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.serverpackets.S_bonusstats;
import com.lineage.server.serverpackets.ServerBasePacket;
import com.lineage.server.templates.L1BookMark;
import com.lineage.server.templates.L1Item;
import com.lineage.server.templates.L1MagicDoll;
import com.lineage.server.templates.L1PrivateShopBuyList;
import com.lineage.server.templates.L1PrivateShopSellList;
import com.lineage.server.utils.CalcStat;
import com.lineage.server.utils.Random;
import com.lineage.server.utils.collections.Lists;

// Referenced classes of package com.lineage.server.model:
// L1Character, L1DropTable, L1Object, L1ItemInstance,
// L1World
//

/**
 * 对象:PC 控制项
 */
public class L1PcInstance extends L1Character {

    /**
     * 角色死亡的处理
     */
    private class Death implements Runnable {
        L1Character _lastAttacker;

        Death(final L1Character cha) {
            this._lastAttacker = cha;
        }

        @Override
        public void run() {
            final L1Character lastAttacker = this._lastAttacker;
            this._lastAttacker = null;
            L1PcInstance.this.setCurrentHp(0);
            L1PcInstance.this.setGresValid(false); // EXPロストするまでG-RES无效

            while (L1PcInstance.this.isTeleport()) { // 正在传送中等待传送结束
                try {
                    Thread.sleep(300);
                } catch (final Exception e) {
                }
            }

            L1PcInstance.this.stopHpRegeneration();
            L1PcInstance.this.stopMpRegeneration();
            L1PcInstance.this.stopCryOfSurvival();
            L1PcInstance.this.setCryTime(0);
            final int targetobjid = L1PcInstance.this.getId();
            L1PcInstance.this.getMap().setPassable(
                    L1PcInstance.this.getLocation(), true);

            // 死亡时解除变身
            int tempchargfx = 0;
            if (L1PcInstance.this.hasSkillEffect(SHAPE_CHANGE)) {
                tempchargfx = L1PcInstance.this.getTempCharGfx();
                L1PcInstance.this.setTempCharGfxAtDead(tempchargfx);
            } else {
                L1PcInstance.this.setTempCharGfxAtDead(L1PcInstance.this
                        .getClassId());
            }

            // 死亡时解除现有技能效果
            final L1SkillUse l1skilluse = new L1SkillUse();
            l1skilluse.handleCommands(L1PcInstance.this, CANCELLATION,
                    L1PcInstance.this.getId(), L1PcInstance.this.getX(),
                    L1PcInstance.this.getY(), null, 0, L1SkillUse.TYPE_LOGIN);

            // 战斗药水
            if (L1PcInstance.this.hasSkillEffect(EFFECT_POTION_OF_BATTLE)) {
                L1PcInstance.this.removeSkillEffect(EFFECT_POTION_OF_BATTLE);
            }
            // 象牙塔妙药
            if (L1PcInstance.this.hasSkillEffect(COOKING_WONDER_DRUG)) {
                L1PcInstance.this.removeSkillEffect(COOKING_WONDER_DRUG);
            }

            L1PcInstance.this.sendPackets(new S_DoActionGFX(targetobjid,
                    ActionCodes.ACTION_Die));
            L1PcInstance.this.broadcastPacket(new S_DoActionGFX(targetobjid,
                    ActionCodes.ACTION_Die));

            if (lastAttacker != L1PcInstance.this) {
                // セーフティーゾーン、コンバットゾーンで最后に杀したキャラが
                // プレイヤーorペットだったら、ペナルティなし
                if (L1PcInstance.this.getZoneType() != 0) {
                    L1PcInstance player = null;
                    if (lastAttacker instanceof L1PcInstance) {
                        player = (L1PcInstance) lastAttacker;
                    } else if (lastAttacker instanceof L1PetInstance) {
                        player = (L1PcInstance) ((L1PetInstance) lastAttacker)
                                .getMaster();
                    } else if (lastAttacker instanceof L1SummonInstance) {
                        player = (L1PcInstance) ((L1SummonInstance) lastAttacker)
                                .getMaster();
                    }
                    if (player != null) {
                        // 战争中に战争エリアに居る场合は例外
                        if (!L1PcInstance.this.isInWarAreaAndWarTime(
                                L1PcInstance.this, player)) {
                            return;
                        }
                    }
                }

                final boolean sim_ret = L1PcInstance.this
                        .simWarResult(lastAttacker); // 模拟战
                if (sim_ret == true) { // 模拟战中ならペナルティなし
                    return;
                }
            }

            if (!L1PcInstance.this.getMap().isEnabledDeathPenalty()) {
                return;
            }

            // 决斗中ならペナルティなし
            L1PcInstance fightPc = null;
            if (lastAttacker instanceof L1PcInstance) {
                fightPc = (L1PcInstance) lastAttacker;
            }

            // 判断是否属于新手保护阶段, 并且是被其他玩家所杀死
            boolean isNovice = false;
            if (L1PcInstance.this.hasSkillEffect(L1SkillId.STATUS_NOVICE)
                    && (fightPc != null)) {

                // 判断是否在新手等级保护范围内
                if (fightPc.getLevel() > (L1PcInstance.this.getLevel() + Config.NOVICE_PROTECTION_LEVEL_RANGE)) {
                    isNovice = true;
                }
            }

            if (fightPc != null) {
                if ((L1PcInstance.this.getFightId() == fightPc.getId())
                        && (fightPc.getFightId() == L1PcInstance.this.getId())) { // 决斗中
                    L1PcInstance.this.setFightId(0);
                    L1PcInstance.this.sendPackets(new S_PacketBox(
                            S_PacketBox.MSG_DUEL, 0, 0));
                    fightPc.setFightId(0);
                    fightPc.sendPackets(new S_PacketBox(S_PacketBox.MSG_DUEL,
                            0, 0));
                    return;
                }
            }

            /*
             * deathPenalty(); // EXPロスト setGresValid(true); // EXPロストしたらG-RES有效
             * if (getExpRes() == 0) { setExpRes(1); }
             */

            // 减少PK次数
            if (lastAttacker instanceof L1GuardInstance) {
                if (L1PcInstance.this.get_PKcount() > 0) {
                    L1PcInstance.this.set_PKcount(L1PcInstance.this
                            .get_PKcount() - 1);
                }
                L1PcInstance.this.setLastPk(null);
            }

            /** 妖精杀死同族PK值另外计算 */
            if (lastAttacker instanceof L1GuardianInstance) {
                if (L1PcInstance.this.getPkCountForElf() > 0) {
                    L1PcInstance.this.setPkCountForElf(L1PcInstance.this
                            .getPkCountForElf() - 1);
                }
                L1PcInstance.this.setLastPkForElf(null);
            }

            // 增加新手保护阶段, 将不会损失道具(不会喷装)
            if (!isNovice) {
                // 一定の确率でアイテムをDROP
                // アライメント32000以上で0%、以降-1000每に0.4%
                // アライメントが0未满の场合は-1000每に0.8%
                // アライメント-32000以下で最高51.2%のDROP率
                int lostRate = (int) (((L1PcInstance.this.getLawful() + 32768D) / 1000D - 65D) * 4D);
                if (lostRate < 0) {
                    lostRate *= -1;
                    if (L1PcInstance.this.getLawful() < 0) {
                        lostRate *= 2;
                    }
                    final int rnd = Random.nextInt(1000) + 1;
                    if (rnd <= lostRate) {
                        int count = 1;
                        if (L1PcInstance.this.getLawful() <= -30000) {
                            count = Random.nextInt(4) + 1;
                        } else if (L1PcInstance.this.getLawful() <= -20000) {
                            count = Random.nextInt(3) + 1;
                        } else if (L1PcInstance.this.getLawful() <= -10000) {
                            count = Random.nextInt(2) + 1;
                        } else if (L1PcInstance.this.getLawful() < 0) {
                            count = Random.nextInt(1) + 1;
                        }
                        L1PcInstance.this.caoPenaltyResult(count);
                    }
                }
            }

            final boolean castle_ret = L1PcInstance.this.castleWarResult(); // 攻城战
            if (castle_ret == true) { // 攻城战中で旗内なら赤ネームペナルティなし
                return;
            }

            if (!L1PcInstance.this.getMap().isEnabledDeathPenalty()) {
                return;
            }

            // 增加新手保护阶段, 将不会损失经验值
            if (!isNovice) {
                L1PcInstance.this.deathPenalty(); // EXPロスト
                L1PcInstance.this.setGresValid(true); // EXPロストしたらG-RES有效
            }

            if (L1PcInstance.this.get_PKcount() > 0) {
                L1PcInstance.this
                        .set_PKcount(L1PcInstance.this.get_PKcount() - 1);
            }
            L1PcInstance.this.setLastPk(null);

            // 最后に杀したキャラがプレイヤーだったら、赤ネームにする
            L1PcInstance player = null;
            if (lastAttacker instanceof L1PcInstance) {
                player = (L1PcInstance) lastAttacker;
            }
            if (player != null) {
                if ((L1PcInstance.this.getLawful() >= 0)
                        && (L1PcInstance.this.isPinkName() == false)) {
                    boolean isChangePkCount = false;
                    // アライメントが30000未满の场合はPKカウント增加
                    if (player.getLawful() < 30000) {
                        player.set_PKcount(player.get_PKcount() + 1);
                        isChangePkCount = true;
                        if (player.isElf() && L1PcInstance.this.isElf()) {
                            player.setPkCountForElf(player.getPkCountForElf() + 1);
                        }
                    }
                    player.setLastPk();
                    /** 正义值满不会被警卫追杀 */
                    if (player.getLawful() == 32767) {
                        player.setLastPk(null);
                    }
                    if (player.isElf() && L1PcInstance.this.isElf()) {
                        player.setLastPkForElf();
                    }

                    // アライメント处理
                    // 公式の発表および各LVでのPKからつじつまの合うように变更
                    // （PK侧のLVに依存し、高LVほどリスクも高い）
                    // 48あたりで-8kほど DKの时点で10k强
                    // 60で约20k强 65で30k弱
                    int lawful;

                    if (player.getLevel() < 50) {
                        lawful = -1
                                * (int) ((Math.pow(player.getLevel(), 2) * 4));
                    } else {
                        lawful = -1
                                * (int) ((Math.pow(player.getLevel(), 3) * 0.08));
                    }
                    // もし(元々のアライメント-1000)が计算后より低い场合
                    // 元々のアライメント-1000をアライメント值とする
                    // （连续でPKしたときにほとんど值が变わらなかった记忆より）
                    // これは上の式よりも自信度が低いうろ觉えですので
                    // 明らかにこうならない！という场合は修正お愿いします
                    if ((player.getLawful() - 1000) < lawful) {
                        lawful = player.getLawful() - 1000;
                    }

                    if (lawful <= -32768) {
                        lawful = -32768;
                    }
                    player.setLawful(lawful);

                    final S_Lawful s_lawful = new S_Lawful(player.getId(),
                            player.getLawful());
                    player.sendPackets(s_lawful);
                    player.broadcastPacket(s_lawful);

                    if (isChangePkCount && (player.get_PKcount() >= 5)
                            && (player.get_PKcount() < 10)) {
                        // 你已经杀了 %0 人。 如果你PK的次数达到 %1， 你将会被打入地狱。
                        player.sendPackets(new S_BlueMessage(551, String
                                .valueOf(player.get_PKcount()), "10"));
                    } else if (isChangePkCount && (player.get_PKcount() >= 10)) {
                        player.beginHell(true);
                    }
                } else {
                    L1PcInstance.this.setPinkName(false);
                }
            }
            L1PcInstance.this._pcDeleteTimer = new L1PcDeleteTimer(
                    L1PcInstance.this);
            L1PcInstance.this._pcDeleteTimer.begin();
        }
    }

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * 王子
     */
    public static final int CLASSID_PRINCE = 0;

    /**
     * 公主
     */
    public static final int CLASSID_PRINCESS = 1;

    /**
     * 男骑士
     */
    public static final int CLASSID_KNIGHT_MALE = 61;

    /**
     * 女骑士
     */
    public static final int CLASSID_KNIGHT_FEMALE = 48;

    /**
     * 男精灵
     */
    public static final int CLASSID_ELF_MALE = 138;

    /**
     * 女精灵
     */
    public static final int CLASSID_ELF_FEMALE = 37;

    /**
     * 男法师
     */
    public static final int CLASSID_WIZARD_MALE = 734;

    /**
     * 女法师
     */
    public static final int CLASSID_WIZARD_FEMALE = 1186;

    /**
     * 男黑暗精灵
     */
    public static final int CLASSID_DARK_ELF_MALE = 2786;

    /**
     * 女黑暗精灵
     */
    public static final int CLASSID_DARK_ELF_FEMALE = 2796;

    /**
     * 男龙骑士
     */
    public static final int CLASSID_DRAGON_KNIGHT_MALE = 6658;

    /**
     * 女龙骑士
     */
    public static final int CLASSID_DRAGON_KNIGHT_FEMALE = 6661;

    /**
     * 男幻术师
     */
    public static final int CLASSID_ILLUSIONIST_MALE = 6671;

    /**
     * 女幻术师
     */
    public static final int CLASSID_ILLUSIONIST_FEMALE = 6650;

    /**
     * 加载指定PC资料
     * 
     * @param charName
     *            PC名称
     * @return
     */
    public static L1PcInstance load(final String charName) {
        L1PcInstance result = null;
        try {
            result = CharacterTable.getInstance().loadCharacter(charName);
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
        return result;
    }

    /**
     * 3.3C组队系统
     */
    boolean _rpActive = false;

    /**
     * 标准的
     */
    private L1PartyRefresh _rp;

    /**
     * 组队类型
     */
    private int _partyType;

    /**
     * 回血
     */
    private short _hpr = 0;

    /**
     * 真正的回血
     */
    private short _trueHpr = 0;

    /**
     * 回魔
     */
    private short _mpr = 0;

    /**
     * 真正的回魔
     */
    private short _trueMpr = 0;

    /**
     * 原始 CON HPR
     */
    public short _originalHpr = 0;

    /**
     * 原始 WIS MPR
     */
    public short _originalMpr = 0;

    /**
     * 自动更新间隔
     */
    private static final long INTERVAL_AUTO_UPDATE = 300;

    /** 
	 * 
	 */
    private ScheduledFuture<?> _autoUpdateFuture;

    /** 
	 * 
	 */
    private static final long INTERVAL_EXP_MONITOR = 500;

    /** 
	 * 
	 */
    private ScheduledFuture<?> _expMonitorFuture;

    /**
     * 技能清单
     */
    private final List<Integer> skillList = Lists.newList();

    /**
     * 宠物竞速圈数
     */
    private int _lap = 1;

    /**
     * 暂存传送编号 (幽灵状态)
     */
    private byte _beginGhost;

    /**
     * 检查宠物竞速圈数
     */
    private int _lapCheck = 0;

    // 补充
    private boolean _order_list = false;

    /** 
	 * 
	 */
    private L1ClassFeature _classFeature = null;

    /**
     * PK次数
     */
    private int _PKcount;

    /**
     * PK次数 (精灵用)
     */
    private int _PkCountForElf;

    /**
     * 血盟 ID
     */
    private int _clanid;

    /**
     * 血盟名称
     */
    private String clanname;

    /**
     * 血盟内的阶级(联盟君主、守护骑士、一般、见习)
     */
    private int _clanRank;

    /**
     * 角色生日
     */
    private Timestamp _birthday;

    /**
     * 性别
     */
    private byte _sex;

    /**
     * 贩卖清单
     */
    private final List<L1PrivateShopSellList> _sellList = Lists.newList();

    /**
     * 购买清单
     */
    private final List<L1PrivateShopBuyList> _buyList = Lists.newList();

    /**
     * 商店聊天
     */
    private byte[] _shopChat;

    /**
     * 商店模式
     */
    private boolean _isPrivateShop = false;

    /**
     * 正在进行个人商店交易
     */
    private boolean _isTradingInPrivateShop = false;

    /**
     * 出售物品种类数量
     */
    private int _partnersPrivateShopItemCount = 0;

    /**
     * 封包加密管理
     */
    private PacketOutput _out;

    /** 
	 * 
	 */
    public double _oldTime = 0; // 连续魔法伤害の轻减に使用する

    /**
     * 原始DEX ER补正
     */
    private int _originalEr = 0;

    /** 
	 * 
	 */
    private static Logger _log = Logger.getLogger(L1PcInstance.class.getName());

    /** 
	 * 
	 */
    private ClientThread _netConnection;

    /** 
	 * 
	 */
    private int _classId;

    /** 
	 * 
	 */
    private int _type;

    /** 
	 * 
	 */
    private long _exp;

    /** 
	 * 
	 */
    private final L1Karma _karma = new L1Karma();

    /** 
	 * 
	 */
    private boolean _gm;

    /** 
	 * 
	 */
    private boolean _monitor;

    /** 
	 * 
	 */
    private boolean _gmInvis;

    /** 
	 * 
	 */
    private short _accessLevel;

    /** 
	 * 
	 */
    private int _currentWeapon;

    /** 
	 * 
	 */
    private final L1PcInventory _inventory;

    /** 
	 * 
	 */
    private final L1DwarfInventory _dwarf;

    /** 
	 * 
	 */
    private final L1DwarfForElfInventory _dwarfForElf;

    /** 
	 * 
	 */
    private final L1Inventory _tradewindow;

    /** 
	 * 
	 */
    private L1ItemInstance _weapon;

    /** 
	 * 
	 */
    private L1Party _party;

    /** 
	 * 
	 */
    private L1ChatParty _chatParty;

    /** 
	 * 
	 */
    private int _partyID;

    /** 
	 * 
	 */
    private int _tradeID;

    /** 
	 * 
	 */
    private boolean _tradeOk;

    /** 
	 * 
	 */
    private int _tempID;

    /** 
	 * 
	 */
    private boolean _isTeleport = false;

    /** 
	 * 
	 */
    private boolean _isDrink = false;

    /** 
	 * 
	 */
    private boolean _isGres = false;

    /** 
	 * 
	 */
    private boolean _isPinkName = false;

    /** 
	 * 
	 */
    private final List<L1BookMark> _bookmarks;

    /** 
	 * 
	 */
    private final L1Quest _quest;

    /** 
	 * 
	 */
    private MpRegeneration _mpRegen;

    /** 
	 * 
	 */
    private MpRegenerationByDoll _mpRegenByDoll;

    /** 
	 * 
	 */
    private MpReductionByAwake _mpReductionByAwake;

    /** 
	 * 
	 */
    private HpRegeneration _hpRegen;

    /** 
	 * 
	 */
    private HpRegenerationByDoll _hpRegenByDoll;

    /** 
	 * 
	 */
    private ItemMakeByDoll _itemMakeByDoll;

    /** 
	 * 
	 */
    private static Timer _regenTimer = new Timer(true);

    /** 
	 * 
	 */
    private boolean _mpRegenActive;

    /** 
	 * 
	 */
    private boolean _mpRegenActiveByDoll;

    /** 
	 * 
	 */
    private boolean _mpReductionActiveByAwake;

    /** 
	 * 
	 */
    private boolean _hpRegenActive;

    /** 
	 * 
	 */
    private boolean _hpRegenActiveByDoll;

    /** 
	 * 
	 */
    private boolean _ItemMakeActiveByDoll;

    /** 
	 * 
	 */
    private final L1EquipmentSlot _equipSlot;

    /** 
	 * 
	 */
    L1PcDeleteTimer _pcDeleteTimer;

    /**
     * 账号名称
     */
    private String _accountName;

    /**
     * 基本MaxHp
     */
    private short _baseMaxHp = 0; // ● ＭＡＸＨＰ基本（1～32767）

    /**
     * 基本MaxMp
     */
    private short _baseMaxMp = 0; // ● ＭＡＸＭＰ基本（0～32767）

    /**
     * 基本Ac
     */
    private int _baseAc = 0; // ● ＡＣ基本（-211～211）

    /**
     * 原始DEX ＡＣ补正
     */
    private int _originalAc = 0;

    /**
     * 基本的力量值
     */
    private short _baseStr = 0; // ● 基本ＳＴＲ（1～255）

    /**
     * 基本的体质值
     */
    private short _baseCon = 0; // ● 基本ＣＯＮ（1～255）

    /**
     * 基本的敏捷值
     */
    private short _baseDex = 0; // ● 基本ＤＥＸ（1～255）

    /**
     * 基本的魅力值
     */
    private short _baseCha = 0; // ● 基本ＣＨＡ（1～255）

    /**
     * 基本的智力值
     */
    private short _baseInt = 0; // ● 基本ＩＮＴ（1～255）

    /**
     * 基本的精神值
     */
    private short _baseWis = 0; // ● 基本ＷＩＳ（1～255）

    /**
     * 原始的力量值
     */
    private int _originalStr = 0;

    /**
     * 原始的体质值
     */
    private int _originalCon = 0;

    /**
     * 原始的敏捷值
     */
    private int _originalDex = 0;

    /**
     * 原始的魅力值
     */
    private int _originalCha = 0;

    /**
     * 原始的智力值
     */
    private int _originalInt = 0;

    /**
     * 原始的精神值
     */
    private int _originalWis = 0;

    /**
     * 原始STR 伤害补正
     */
    private int _originalDmgup = 0;

    /**
     * 原始DEX 弓伤害补正
     */
    private int _originalBowDmgup = 0;

    /**
     * 原始STR 命中补正
     */
    private int _originalHitup = 0;

    /**
     * 原始DEX 命中补正
     */
    private int _originalBowHitup = 0;

    /**
     * 原始WIS 魔法防御
     */
    private int _originalMr = 0;

    /**
     * 原始INT 魔法命中
     */
    private int _originalMagicHit = 0;

    /**
     * 原始INT 魔法攻击
     */
    private int _originalMagicCritical = 0;

    /**
     * 原始INT 消费MP轻减
     */
    private int _originalMagicConsumeReduction = 0;

    /**
     * 原始INT的魔法伤害
     */
    private int _originalMagicDamage = 0;

    /**
     * 原始CON HP上升值补正
     */
    private int _originalHpup = 0;

    /**
     * 原始WIS MP上升值补正
     */
    private int _originalMpup = 0;

    /**
     * 基本伤害补正
     */
    private int _baseDmgup = 0; // ● 基本伤害补正（-128～127）

    /**
     * 弓类基本伤害补正
     */
    private int _baseBowDmgup = 0; // ● 弓类基本伤害补正（-128～127）

    /**
     * 基本命中补正
     */
    private int _baseHitup = 0; // ● 基本命中补正（-128～127）

    /**
     * 弓基本命中补正
     */
    private int _baseBowHitup = 0; // ● 弓基本命中补正（-128～127）

    /**
     * 基本魔法防御
     */
    private int _baseMr = 0;

    /**
     * 灵魂升华增加的HP
     */
    private int _advenHp;

    /**
     * 灵魂升华增加的MP
     */
    private int _advenMp;

    /**
     * 过去最高等级
     */
    private int _highLevel;

    /**
     * 奖励点分配状况
     */
    private int _bonusStats;

    /**
     * 万能药使用状况
     */
    private int _elixirStats;

    /**
     * 精灵属性
     */
    private int _elfAttr;

    /**
     * 恢复EXP
     */
    private int _expRes;

    /**
     * 结婚伴侣ID
     */
    private int _partnerId;

    /**
     * 在线状态
     */
    private int _onlineStatus;

    /**
     * 所住村镇ID
     */
    private int _homeTownId;

    /**
     * 贡献度
     */
    private int _contribution;

    /**
     * 村庄福利金
     */
    private int _pay; // 村庄福利金 此栏位由 HomeTownTimeController 处理 update

    /**
     * 地狱停留时间 (秒)
     */
    private int _hellTime;

    /**
     * 角色封锁
     */
    private boolean _banned; // ● 冻结

    /**
	 * 
	 */
    public static final int REGENSTATE_NONE = 4;

    /**
	 * 
	 */
    public static final int REGENSTATE_MOVE = 2;

    /**
	 * 
	 */
    public static final int REGENSTATE_ATTACK = 1;

    /**
     * 隐身延迟计时器
     */
    private int invisDelayCounter = 0;

    /**
     * 隐身计时器监视器
     */
    private final Object _invisTimerMonitor = new Object();

    /**
     * 隐身延迟
     */
    private static final long DELAY_INVIS = 3000L;

    /**
     * 幽灵状态
     */
    private boolean _ghost = false;

    /**
     * 幽灵状态可以与NPC对话
     */
    private boolean _ghostCanTalk = true;

    /**
     * 准备解除幽灵状态
     */
    private boolean _isReserveGhost = false;

    /**
	 * 
	 */
    private ScheduledFuture<?> _ghostFuture;

    /**
	 * 
	 */
    private int _ghostSaveLocX = 0;

    /**
	 * 
	 */
    private int _ghostSaveLocY = 0;

    /**
	 * 
	 */
    private short _ghostSaveMapId = 0;

    /**
	 * 
	 */
    private int _ghostSaveHeading = 0;

    /**
	 * 
	 */
    private ScheduledFuture<?> _hellFuture;

    /**
     * 最终PK时间
     */
    private Timestamp _lastPk;

    /**
     * 妖精杀死同族 PK值另外计算
     */
    private Timestamp _lastPkForElf;

    /**
     * 角色删除时间
     */
    private Timestamp _deleteTime;

    /** 
	 * 
	 */
    private int _weightReduction = 0;

    /**
     * 原始STR 重量轻减
     */
    private int _originalStrWeightReduction = 0;

    /**
     * 原始CON 重量轻减
     */
    private int _originalConWeightReduction = 0;

    /**
     * 装备加速道具
     */
    private int _hasteItemEquipped = 0;

    /**
     * 防具的伤害减免
     */
    private int _damageReductionByArmor = 0;

    /**
     * 防具的命中率补正
     */
    private int _hitModifierByArmor = 0;

    /**
     * 防具的伤害补正
     */
    private int _dmgModifierByArmor = 0;

    /**
     * 防具对弓的命中率补正
     */
    private int _bowHitModifierByArmor = 0;

    /**
     * 防具对损坏的弓的补正
     */
    private int _bowDmgModifierByArmor = 0;

    /**
     * G-RES有效
     */
    private boolean _gresValid;

    /**
     * 钓鱼时间
     */
    private long _fishingTime = 0;

    /**
     * 钓鱼中
     */
    private boolean _isFishing = false;

    /**
     * 准备好钓鱼
     */
    private boolean _isFishingReady = false;

    /**
     * 烹饪ID
     */
    private int _cookingId = 0;

    /**
     * 点心ID
     */
    private int _dessertId = 0;

    /**
     * 角色讯息拒绝名单
     */
    private final L1ExcludingList _excludingList = new L1ExcludingList();

    /**
     * -- 加速器检知机能 --
     */
    private final AcceleratorChecker _acceleratorChecker = new AcceleratorChecker(
            this);

    /**
     * 使用屠宰者判断
     */
    private boolean _FoeSlayer = false;

    /**
     * 传送目的地坐标X
     */
    private int _teleportX = 0;

    /**
     * 传送目的地坐标Y
     */
    private int _teleportY = 0;

    /**
     * 传送目的地 地图ID
     */
    private short _teleportMapId = 0;

    /**
     * 传送目的地 面向
     */
    private int _teleportHeading = 0;

    /**
     * 角色死亡时的临时GFX图像
     */
    private int _tempCharGfxAtDead;

    /**
     * 是可以密语
     */
    private boolean _isCanWhisper = true;

    /**
     * 是显示交易聊天
     */
    private boolean _isShowTradeChat = true;

    /**
     * 是显示血盟聊天
     */
    private boolean _isShowClanChat = true;

    /**
     * 是显示组队聊天
     */
    private boolean _isShowPartyChat = true;

    /**
     * 是显示世界聊天
     */
    private boolean _isShowWorldChat = true;

    /**
     * 战斗ID
     */
    private int _fightId;

    /**
     * 钓鱼坐标X
     */
    private int _fishX;

    /**
     * 钓鱼坐标Y
     */
    private int _fishY;

    /**
     * 聊天数量
     */
    private byte _chatCount = 0;

    /**
     * 旧的聊天时间在Millis？
     */
    private long _oldChatTimeInMillis = 0L;

    /**
     * 呼叫血盟ID
     */
    private int _callClanId;

    /**
     * 呼叫血盟名称
     */
    private int _callClanHeading;

    /**
     * 角色重新开始
     */
    private boolean _isInCharReset = false;

    /**
     * 临时等级
     */
    private int _tempLevel = 1;

    /**
     * 临时最高等级
     */
    private int _tempMaxLevel = 1;

    /**
     * 觉醒技能ID
     */
    private int _awakeSkillId = 0;

    /**
     * 判断是否无道具施法(召戒清单、变身清单)
     */
    private boolean _isSummonMonster = false;

    /**
     * 变身
     */
    private boolean _isShapeChange = false;

    /**
     * 暂存字符串
     */
    private String _text;

    /**
     * 暂存byte[]阵列
     */
    private byte[] _textByte = null;

    /**
     * 暂存乱数传送状态 (幽灵)
     */
    private boolean _beginGhostFlag = true;
    /** . */
    private TheCryOfSurvival _CryOfSurvival;
    /** 生存的呐喊(开关). */
    private boolean _CryOfSurvivalActive;
    /** 生存的呐喊时间(分). */
    private int _CryTime = 0;
    /** . */
    private RocksPrison _rocksPrison;
    /** 奇岩地监(开关). */
    private boolean _rocksPrisonActive;
    /** 奇岩地监时间(秒). */
    private int _rocksPrisonTime = 0;

    /** 经验值加成(装备). */
    private double _expBonus = 0;

    /**
	 * 
	 */
    public L1PcInstance() {
        this._accessLevel = 0;
        this._currentWeapon = 0;
        this._inventory = new L1PcInventory(this);
        this._dwarf = new L1DwarfInventory(this);
        this._dwarfForElf = new L1DwarfForElfInventory(this);
        this._tradewindow = new L1Inventory();
        this._bookmarks = Lists.newList();
        this._quest = new L1Quest(this);
        this._equipSlot = new L1EquipmentSlot(this); // コンストラクタでthisポインタを渡すのは安全だろうか・・・
    }

    /**
     * 增加基本的魅力值
     * 
     * @param i
     */
    public void addBaseCha(short i) {
        i += this._baseCha;
        if (i >= 255) {
            i = 255;
        } else if (i < 1) {
            i = 1;
        }
        this.addCha((short) (i - this._baseCha));
        this._baseCha = i;
    }

    /**
     * 增加基本的体质值
     * 
     * @param i
     */
    public void addBaseCon(short i) {
        i += this._baseCon;
        if (i >= 255) {
            i = 255;
        } else if (i < 1) {
            i = 1;
        }
        this.addCon((short) (i - this._baseCon));
        this._baseCon = i;
    }

    /**
     * 增加基本的敏捷值
     * 
     * @param i
     */
    public void addBaseDex(short i) {
        i += this._baseDex;
        if (i >= 255) {
            i = 255;
        } else if (i < 1) {
            i = 1;
        }
        this.addDex((short) (i - this._baseDex));
        this._baseDex = i;
    }

    /**
     * 增加基本的智力值
     * 
     * @param i
     */
    public void addBaseInt(short i) {
        i += this._baseInt;
        if (i >= 255) {
            i = 255;
        } else if (i < 1) {
            i = 1;
        }
        this.addInt((short) (i - this._baseInt));
        this._baseInt = i;
    }

    /**
     * 增加基本MaxHp
     * 
     * @param i
     */
    public void addBaseMaxHp(short i) {
        i += this._baseMaxHp;
        if (i >= 32767) {
            i = 32767;
        } else if (i < 1) {
            i = 1;
        }
        this.addMaxHp(i - this._baseMaxHp);
        this._baseMaxHp = i;
    }

    /**
     * 增加基本MaxMp
     * 
     * @param i
     */
    public void addBaseMaxMp(short i) {
        i += this._baseMaxMp;
        if (i >= 32767) {
            i = 32767;
        } else if (i < 0) {
            i = 0;
        }
        this.addMaxMp(i - this._baseMaxMp);
        this._baseMaxMp = i;
    }

    /**
     * 增加基本的力量值
     * 
     * @param s
     */
    public void addBaseStr(short s) {
        s += this._baseStr;
        if (s >= 255) {
            s = 255;
        } else if (s < 1) {
            s = 1;
        }
        this.addStr((short) (s - this._baseStr));
        this._baseStr = s;
    }

    /**
     * 增加基本的精神值
     * 
     * @param i
     */
    public void addBaseWis(short i) {
        i += this._baseWis;
        if (i >= 255) {
            i = 255;
        } else if (i < 1) {
            i = 1;
        }
        this.addWis((short) (i - this._baseWis));
        this._baseWis = i;
    }

    /**
     * 增加记忆坐标
     * 
     * @param book
     */
    public void addBookMark(final L1BookMark book) {
        this._bookmarks.add(book);
    }

    /**
     * 增加防具对损坏的弓的补正
     * 
     * @param i
     */
    public void addBowDmgModifierByArmor(final int i) {
        this._bowDmgModifierByArmor += i;
    }

    /**
     * 增加防具对弓的命中率补正
     * 
     * @param i
     */
    public void addBowHitModifierByArmor(final int i) {
        this._bowHitModifierByArmor += i;
    }

    /**
     * 增加贡献度
     * 
     * @param contribution
     */
    public synchronized void addContribution(final int contribution) {
        this._contribution += contribution;
    }

    /**
     * 增加防具的伤害减免
     * 
     * @param i
     */
    public void addDamageReductionByArmor(final int i) {
        this._damageReductionByArmor += i;
    }

    /**
     * 增加防具的伤害补正
     * 
     * @param i
     */
    public void addDmgModifierByArmor(final int i) {
        this._dmgModifierByArmor += i;
    }

    /**
     * 增加经验值
     * 
     * @param exp
     */
    public synchronized void addExp(final long exp) {
        this._exp += exp;
        if (this._exp > ExpTable.MAX_EXP) {
            this._exp = ExpTable.MAX_EXP;
        }
    }

    /**
     * 增加经验值加成(装备).
     * 
     * @param i
     *            经验值加成
     */
    public void addExpBonus(final double i) {
        this._expBonus += i;
    }

    /**
     * 增加装备加速道具
     * 
     * @param i
     */
    public void addHasteItemEquipped(final int i) {
        this._hasteItemEquipped += i;
    }

    /**
     * 增加防具的命中率补正
     * 
     * @param i
     */
    public void addHitModifierByArmor(final int i) {
        this._hitModifierByArmor += i;
    }

    /**
     * 增加回血
     * 
     * @param i
     */
    public void addHpr(final int i) {
        this._trueHpr += i;
        this._hpr = (short) Math.max(0, this._trueHpr);
    }

    /**
     * 增加隐身延迟计时器
     * 
     * @param counter
     */
    public void addInvisDelayCounter(final int counter) {
        synchronized (this._invisTimerMonitor) {
            this.invisDelayCounter += counter;
        }
    }

    /**
     * 增加友好度
     * 
     * @param i
     */
    public void addKarma(final int i) {
        synchronized (this._karma) {
            this._karma.add(i);
        }
    }

    /**
     * 增加回魔
     * 
     * @param i
     */
    public void addMpr(final int i) {
        this._trueMpr += i;
        this._mpr = (short) Math.max(0, this._trueMpr);
    }

    /**
     * @param i
     */
    public void addWeightReduction(final int i) {
        this._weightReduction += i;
    }

    /**
     * 开始经验值监视器
     */
    public void beginExpMonitor() {
        this._expMonitorFuture = GeneralThreadPool.getInstance()
                .pcScheduleAtFixedRate(new L1PcExpMonitor(this.getId()), 0L,
                        INTERVAL_EXP_MONITOR);
    }

    /**
     * @return
     */
    public void beginGameTimeCarrier() {
        new L1GameTimeCarrier(this).start();
    }

    /**
     * 幽灵状态传送
     * 
     * @param locx
     * @param locy
     * @param mapid
     * @param canTalk
     */
    public void beginGhost(final int locx, final int locy, final short mapid,
            final boolean canTalk) {
        this.beginGhost(locx, locy, mapid, canTalk, 0);
    }

    /**
     * 幽灵状态传送
     * 
     * @param locx
     * @param locy
     * @param mapid
     * @param canTalk
     * @param sec
     */
    public void beginGhost(final int locx, final int locy, final short mapid,
            final boolean canTalk, final int sec) {
        if (this.isGhost()) {
            return;
        }
        this.setGhost(true);
        this._ghostSaveLocX = this.getX();
        this._ghostSaveLocY = this.getY();
        this._ghostSaveMapId = this.getMapId();
        this._ghostSaveHeading = this.getHeading();
        this.setGhostCanTalk(canTalk);
        L1Teleport.teleport(this, locx, locy, mapid, 5, true);
        if (sec > 0) {
            this._ghostFuture = GeneralThreadPool.getInstance().pcSchedule(
                    new L1PcGhostMonitor(this.getId()), sec * 1000);
        }
    }

    /**
     * PK值过高传送到地狱
     * 
     * @param isFirst
     *            开关
     */
    public void beginHell(final boolean isFirst) {
        // 坐标非地狱则强制传送到地狱地图
        if (this.getMapId() != 666) {
            final int locx = 32701;
            final int locy = 32777;
            final short mapid = 666;
            L1Teleport.teleport(this, locx, locy, mapid, 5, false);
        }

        if (isFirst) {
            if (this.get_PKcount() <= 10) {
                this.setHellTime(300);
            } else {
                this.setHellTime(300 * (this.get_PKcount() - 10) + 300);
            }
            // 因为你已经杀了 %0 人所以被打入地狱。 你将在这里停留 %1 分钟。
            this.sendPackets(new S_BlueMessage(552, String.valueOf(this
                    .get_PKcount()), String.valueOf(this.getHellTime() / 60)));
        } else {
            // 你必须在此地停留 %0 秒。
            this.sendPackets(new S_BlueMessage(637, String.valueOf(this
                    .getHellTime())));
        }
        if (this._hellFuture == null) {
            this._hellFuture = GeneralThreadPool.getInstance()
                    .pcScheduleAtFixedRate(new L1PcHellMonitor(this.getId()),
                            0L, 1000L);
        }
    }

    /**
     * 开始隐身计时器
     */
    public void beginInvisTimer() {
        this.addInvisDelayCounter(1);
        GeneralThreadPool.getInstance().pcSchedule(
                new L1PcInvisDelay(this.getId()), DELAY_INVIS);
    }

    /**
     * 什么都不能做的状态 (无法离线、无法移动、无法使用道具等)
     * 
     * @return
     */
    public boolean CanNotDoAnything() {
        return (/*
                 * hasSkillEffect(IMMUNE_TO_HARM) // 法师魔法 (圣结界) 测试用 ||
                 */
        this.hasSkillEffect(STATUS_CURSE_PARALYZED) // 麻痹毒(木乃伊诅咒)后
                || this.hasSkillEffect(ICE_LANCE) // 法师魔法 (冰矛围篱)
                || this.hasSkillEffect(FREEZING_BLIZZARD) // 法师魔法 (冰雪飓风)
                || this.hasSkillEffect(ICE_LANCE_COCKATRICE) // 亚力安冰矛围篱
                || this.hasSkillEffect(ICE_LANCE_BASILISK) // 邪恶蜥蜴冰矛围篱
                || this.hasSkillEffect(FOG_OF_SLEEPING) // 法师魔法 (沉睡之雾)
                || this.hasSkillEffect(SHOCK_STUN) // 骑士魔法 (冲击之晕)
                || this.hasSkillEffect(EARTH_BIND) // 妖精魔法 (大地屏障)
                || this.hasSkillEffect(SHOCK_SKIN) // 龙骑士魔法 (冲击之肤) 冲晕效果
        || this.hasSkillEffect(FREEZING_BREATH)); // 龙骑士魔法 (寒冰喷吐)
    }

    /**
     * 惩罚结果
     * 
     * @param count
     */
    void caoPenaltyResult(final int count) {
        for (int i = 0; i < count; i++) {
            final L1ItemInstance item = this.getInventory().CaoPenalty();

            if (item != null) {
                this.getInventory().tradeItem(
                        item,
                        item.isStackable() ? item.getCount() : 1,
                        L1World.getInstance().getInventory(this.getX(),
                                this.getY(), this.getMapId()));
                this.sendPackets(new S_ServerMessage(638, item.getLogName())); // 您损失了
                                                                               // %0。
            } else {
            }
        }
    }

    /**
     * 是否在參加攻城战中
     * 
     * @return true:是 false:不是
     */
    public boolean castleWarResult() {
        if ((this.getClanid() != 0) && this.isCrown()) { // 有血盟并且是王族
            final L1Clan clan = L1World.getInstance().getClan(
                    this.getClanname());
            // 取得全部战争列表
            for (final L1War war : L1World.getInstance().getWarList()) {
                final int warType = war.GetWarType();
                final boolean isInWar = war.CheckClanInWar(this.getClanname());
                final boolean isAttackClan = war.CheckAttackClan(this
                        .getClanname());
                if ((this.getId() == clan.getLeaderId()) && // 血盟主で攻击侧で攻城战中
                        (warType == 1) && isInWar && isAttackClan) {
                    final String enemyClanName = war.GetEnemyClanName(this
                            .getClanname());
                    if (enemyClanName != null) {
                        war.CeaseWar(this.getClanname(), enemyClanName); // 终结
                    }
                    break;
                }
            }
        }

        int castleId = 0;
        boolean isNowWar = false;
        castleId = L1CastleLocation.getCastleIdByArea(this);
        if (castleId != 0) { // 旗内に居る
            isNowWar = WarTimeController.getInstance().isNowWar(castleId);
        }
        return isNowWar;
    }

    /****************************** 战斗特化系统 ******************************/
    // 改变战斗特化状态
    public void changeFightType(final int oldType, final int newType) {
        // 消除既有的战斗特化状态
        switch (oldType) {
            case 1:
                this.addAc(2);
                this.addMr(-3);
                this.sendPackets(new S_OwnCharStatus2(this)); // 更新六项能力值
                this.sendPackets(new S_OwnCharAttrDef(this)); // 更新物理防显示
                this.sendPackets(new S_SPMR(this)); // 更新魔防及魔攻显示
                this.sendPackets(new S_Fight(S_Fight.TYPE_JUSTICE_LEVEL1,
                        S_Fight.FLAG_OFF));
                break;

            case 2:
                this.addAc(4);
                this.addMr(-6);
                this.sendPackets(new S_OwnCharStatus2(this)); // 更新六项能力值
                this.sendPackets(new S_OwnCharAttrDef(this)); // 更新物理防显示
                this.sendPackets(new S_SPMR(this)); // 更新魔防及魔攻显示
                this.sendPackets(new S_Fight(S_Fight.TYPE_JUSTICE_LEVEL2,
                        S_Fight.FLAG_OFF));
                break;

            case 3:
                this.addAc(6);
                this.addMr(-9);
                this.sendPackets(new S_OwnCharStatus2(this)); // 更新六项能力值
                this.sendPackets(new S_OwnCharAttrDef(this)); // 更新物理防显示
                this.sendPackets(new S_SPMR(this)); // 更新魔防及魔攻显示
                this.sendPackets(new S_Fight(S_Fight.TYPE_JUSTICE_LEVEL3,
                        S_Fight.FLAG_OFF));
                break;

            case -1:
                this.addDmgup(-1);
                this.addBowDmgup(-1);
                this.addSp(-1);
                this.sendPackets(new S_OwnCharStatus2(this)); // 更新六项能力值
                this.sendPackets(new S_OwnCharAttrDef(this)); // 更新物理防显示
                this.sendPackets(new S_SPMR(this)); // 更新魔防及魔攻显示
                this.sendPackets(new S_Fight(S_Fight.TYPE_EVIL_LEVEL1,
                        S_Fight.FLAG_OFF));
                break;

            case -2:
                this.addDmgup(-3);
                this.addBowDmgup(-3);
                this.addSp(-2);
                this.sendPackets(new S_OwnCharStatus2(this)); // 更新六项能力值
                this.sendPackets(new S_OwnCharAttrDef(this)); // 更新物理防显示
                this.sendPackets(new S_SPMR(this)); // 更新魔防及魔攻显示
                this.sendPackets(new S_Fight(S_Fight.TYPE_EVIL_LEVEL2,
                        S_Fight.FLAG_OFF));
                break;

            case -3:
                this.addDmgup(-5);
                this.addBowDmgup(-5);
                this.addSp(-3);
                this.sendPackets(new S_OwnCharStatus2(this)); // 更新六项能力值
                this.sendPackets(new S_OwnCharAttrDef(this)); // 更新物理防显示
                this.sendPackets(new S_SPMR(this)); // 更新魔防及魔攻显示
                this.sendPackets(new S_Fight(S_Fight.TYPE_EVIL_LEVEL3,
                        S_Fight.FLAG_OFF));
                break;
        }

        // 增加新的战斗特化状态
        switch (newType) {
            case 1:
                this.addAc(-2);
                this.addMr(3);
                this.sendPackets(new S_OwnCharStatus2(this)); // 更新六项能力值
                this.sendPackets(new S_OwnCharAttrDef(this)); // 更新物理防显示
                this.sendPackets(new S_SPMR(this)); // 更新魔防及魔攻显示
                this.sendPackets(new S_Fight(S_Fight.TYPE_JUSTICE_LEVEL1,
                        S_Fight.FLAG_ON));
                break;

            case 2:
                this.addAc(-4);
                this.addMr(6);
                this.sendPackets(new S_OwnCharStatus2(this)); // 更新六项能力值
                this.sendPackets(new S_OwnCharAttrDef(this)); // 更新物理防显示
                this.sendPackets(new S_SPMR(this)); // 更新魔防及魔攻显示
                this.sendPackets(new S_Fight(S_Fight.TYPE_JUSTICE_LEVEL2,
                        S_Fight.FLAG_ON));
                break;

            case 3:
                this.addAc(-6);
                this.addMr(9);
                this.sendPackets(new S_OwnCharStatus2(this)); // 更新六项能力值
                this.sendPackets(new S_OwnCharAttrDef(this)); // 更新物理防显示
                this.sendPackets(new S_SPMR(this)); // 更新魔防及魔攻显示
                this.sendPackets(new S_Fight(S_Fight.TYPE_JUSTICE_LEVEL3,
                        S_Fight.FLAG_ON));
                break;

            case -1:
                this.addDmgup(1);
                this.addBowDmgup(1);
                this.addSp(1);
                this.sendPackets(new S_OwnCharStatus2(this)); // 更新六项能力值
                this.sendPackets(new S_OwnCharAttrDef(this)); // 更新物理防显示
                this.sendPackets(new S_SPMR(this)); // 更新魔防及魔攻显示
                this.sendPackets(new S_Fight(S_Fight.TYPE_EVIL_LEVEL1,
                        S_Fight.FLAG_ON));
                break;

            case -2:
                this.addDmgup(3);
                this.addBowDmgup(3);
                this.addSp(2);
                this.sendPackets(new S_OwnCharStatus2(this)); // 更新六项能力值
                this.sendPackets(new S_OwnCharAttrDef(this)); // 更新物理防显示
                this.sendPackets(new S_SPMR(this)); // 更新魔防及魔攻显示
                this.sendPackets(new S_Fight(S_Fight.TYPE_EVIL_LEVEL2,
                        S_Fight.FLAG_ON));
                break;

            case -3:
                this.addDmgup(5);
                this.addBowDmgup(5);
                this.addSp(3);
                this.sendPackets(new S_OwnCharStatus2(this)); // 更新六项能力值
                this.sendPackets(new S_OwnCharAttrDef(this)); // 更新物理防显示
                this.sendPackets(new S_SPMR(this)); // 更新魔防及魔攻显示
                this.sendPackets(new S_Fight(S_Fight.TYPE_EVIL_LEVEL3,
                        S_Fight.FLAG_ON));
                break;
        }
    }

    /**
     * 检查聊天间隔
     */
    public void checkChatInterval() {
        final long nowChatTimeInMillis = System.currentTimeMillis();
        if (this._chatCount == 0) {
            this._chatCount++;
            this._oldChatTimeInMillis = nowChatTimeInMillis;
            return;
        }

        final long chatInterval = nowChatTimeInMillis
                - this._oldChatTimeInMillis;
        if (chatInterval > 2000) {
            this._chatCount = 0;
            this._oldChatTimeInMillis = 0;
        } else {
            if (this._chatCount >= 3) {
                this.setSkillEffect(STATUS_CHAT_PROHIBITED, 120 * 1000);
                this.sendPackets(new S_SkillIconGFX(36, 120));
                this.sendPackets(new S_ServerMessage(153)); // \f3因洗画面的关系，2分钟之内无法聊天。
                this._chatCount = 0;
                this._oldChatTimeInMillis = 0;
            }
            this._chatCount++;
        }
    }

    /**
     * 检查是否可以攻击
     * 
     * @param pc
     * @param target
     * @return
     */
    public boolean checkNonPvP(final L1PcInstance pc, final L1Character target) {
        L1PcInstance targetpc = null;
        if (target instanceof L1PcInstance) {
            targetpc = (L1PcInstance) target;
        } else if (target instanceof L1PetInstance) {
            targetpc = (L1PcInstance) ((L1PetInstance) target).getMaster();
        } else if (target instanceof L1SummonInstance) {
            targetpc = (L1PcInstance) ((L1SummonInstance) target).getMaster();
        }
        if (targetpc == null) {
            return false; // 组队的PC、召唤、宠物以外
        }
        if (!Config.ALT_NONPVP) { // Non-PvP设定
            if (this.getMap().isCombatZone(this.getLocation())) {
                return false;
            }

            // 取得所有战争列表
            for (final L1War war : L1World.getInstance().getWarList()) {
                if ((pc.getClanid() != 0) && (targetpc.getClanid() != 0)) { // 两方都有血盟
                    final boolean same_war = war.CheckClanInSameWar(
                            pc.getClanname(), targetpc.getClanname());
                    if (same_war == true) { // 参加同一场战争中
                        return false;
                    }
                }
            }
            // Non-PvP设定でも战争中は布告なしで攻击可能
            if (target instanceof L1PcInstance) {
                final L1PcInstance targetPc = (L1PcInstance) target;
                if (this.isInWarAreaAndWarTime(pc, targetPc)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 确认是否属于新手, 并设定相关状态
     */
    public void checkNoviceType() {
        // 判断是否启动新手保护系统(遭遇的守护)
        if (!Config.NOVICE_PROTECTION_IS_ACTIVE) {
            return;
        }

        // 判断目前等级是否已超过新手阶段
        if (this.getLevel() > Config.NOVICE_MAX_LEVEL) {
            // 判断之前是否具有新手保护状态
            if (this.hasSkillEffect(L1SkillId.STATUS_NOVICE)) {
                // 移除新手保护状态
                this.removeSkillEffect(L1SkillId.STATUS_NOVICE);

                // 关闭遭遇的守护图示
                this.sendPackets(new S_Fight(S_Fight.TYPE_ENCOUNTER,
                        S_Fight.FLAG_OFF));
            }
        } else {
            // 判断是否未具有新手保护状态
            if (!this.hasSkillEffect(L1SkillId.STATUS_NOVICE)) {
                // 增加新手保护状态
                this.setSkillEffect(L1SkillId.STATUS_NOVICE, 0);

                // 开启遭遇的守护图示
                this.sendPackets(new S_Fight(S_Fight.TYPE_ENCOUNTER,
                        S_Fight.FLAG_ON));
            }
        }
    }

    /**
     * @return
     */
    public void clearSkillMastery() {
        this.skillList.clear();
    }

    /**
     * 产生新道具
     * 
     * @param pc
     *            对象
     * @param item_id
     *            道具ID
     * @param count
     *            道具数量
     */
    public boolean createNewItem(final L1PcInstance pc, final int item_id,
            final int count) {
        final L1ItemInstance item = ItemTable.getInstance().createItem(item_id);
        if (item != null) {
            item.setCount(count);
            if (pc.getInventory().checkAddItem(item, count) == L1Inventory.OK) {
                pc.getInventory().storeItem(item);
            } else { // 背包道具满掉落地面（不正防止）
                L1World.getInstance()
                        .getInventory(pc.getX(), pc.getY(), pc.getMapId())
                        .storeItem(item);
            }
            pc.sendPackets(new S_ServerMessage(403, item.getLogName())); // 获得%0%o
                                                                         // 。
            return true;
        }
        return false;
    }

    /**
     * 死亡处理
     * 
     * @param lastAttacker
     *            攻击致死的攻击者
     */
    public void death(final L1Character lastAttacker) {
        synchronized (this) {
            if (this.isDead()) {
                return;
            }
            this.setDead(true);
            this.setStatus(ActionCodes.ACTION_Die); // 死亡动作
        }
        GeneralThreadPool.getInstance().execute(new Death(lastAttacker));

    }

    /**
     * 角色死亡经验值处罚
     */
    public void deathPenalty() {
        final int oldLevel = this.getLevel();
        final long needExp = ExpTable.getNeedExpNextLevel(oldLevel);
        long exp = 0;
        switch (oldLevel) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                exp = 0;
                break;

            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
                exp = (long) (needExp * 0.1);
                break;

            case 45:
                exp = (long) (needExp * 0.09);
                break;

            case 46:
                exp = (long) (needExp * 0.08);
                break;

            case 47:
                exp = (long) (needExp * 0.07);
                break;

            case 48:
                exp = (long) (needExp * 0.06);
                break;

            case 49:
                exp = (long) (needExp * 0.05);
                break;

            default:
                exp = (long) (needExp * 0.05);
                break;
        }

        if (exp == 0) {
            return;
        }

        if (this.getExpRes() >= 0) {
            this.setExpRes(this.getExpRes() + 1);
        }
        this.addExp(-exp);
    }

    /**
     * 解除绝对屏障状态
     */
    public void delAbsoluteBarrier() {
        if (this.hasSkillEffect(ABSOLUTE_BARRIER)) {
            this.killSkillEffectTimer(ABSOLUTE_BARRIER);
            this.startHpRegeneration(); // 开始角色回血
            this.startMpRegeneration(); // 开始角色回魔
            this.startHpRegenerationByDoll(); // 开始魔法娃娃对角色的回血
            this.startMpRegenerationByDoll(); // 开始魔法娃娃对角色的回魔
        }
    }

    /**
     * 解除暗隐术
     */
    public void delBlindHiding() {
        // 魔法接续时间终了はこちら
        this.killSkillEffectTimer(BLIND_HIDING); // 黑暗妖精魔法 (暗隐术)
        this.sendPackets(new S_Invis(this.getId(), 0));
        this.broadcastPacket(new S_OtherCharPacks(this));
    }

    /**
     * 删除重复的一段加速效果 (绿色药水)
     * 
     * @param skill_id
     *            技能ID
     */
    public void delEffectOfGreenPotion(final int skill_id) {
        if (this.hasSkillEffect(skill_id)) {
            this.killSkillEffectTimer(skill_id);
            this.sendPackets(new S_SkillHaste(this.getId(), 0, 0));
            this.broadcastPacket(new S_SkillHaste(this.getId(), 0, 0));
            this.setMoveSpeed(0);
        }
    }

    /**
     * 删除重复的缓速效果
     * 
     * @param skill_id
     *            技能ID
     */
    public void delEffectOfSlow(final int skill_id) {
        if (this.hasSkillEffect(skill_id)) {
            this.killSkillEffectTimer(skill_id);
            this.sendPackets(new S_SkillHaste(this.getId(), 0, 0));
            this.broadcastPacket(new S_SkillHaste(this.getId(), 0, 0));
        }
    }

    /**
     * 设定不可重复的魔法状态 (经验药水、魔眼等等)
     * 
     * @param skillId
     *            技能ID
     */
    public void deleteRepeatedSkills(final int skillId) {
        final int[][] repeatedSkills = {
                // 经验加成状态
                { EFFECT_POTION_OF_EXP_150, EFFECT_POTION_OF_EXP_175,
                        EFFECT_POTION_OF_EXP_200, EFFECT_POTION_OF_EXP_225,
                        EFFECT_POTION_OF_EXP_250, EFFECT_POTION_OF_BATTLE },
                // 体力增强卷轴、魔力增强卷轴、强化战斗卷
                { EFFECT_STRENGTHENING_HP, EFFECT_STRENGTHENING_MP,
                        EFFECT_ENCHANTING_BATTLE },
                // 火焰武器、风之神射、烈炎气息、暴风之眼、烈炎武器、暴风神射、妈祖的祝福
                { FIRE_WEAPON, WIND_SHOT, FIRE_BLESS, STORM_EYE,
                        BURNING_WEAPON, STORM_SHOT, EFFECT_BLESS_OF_MAZU },
                // 1 ~ 9 阶附魔石(近战)(远攻)(恢复)(防御)
                { EFFECT_MAGIC_STONE_A_1, EFFECT_MAGIC_STONE_A_2,
                        EFFECT_MAGIC_STONE_A_3, EFFECT_MAGIC_STONE_A_4,
                        EFFECT_MAGIC_STONE_A_5, EFFECT_MAGIC_STONE_A_6,
                        EFFECT_MAGIC_STONE_A_7, EFFECT_MAGIC_STONE_A_8,
                        EFFECT_MAGIC_STONE_A_9, EFFECT_MAGIC_STONE_B_1,
                        EFFECT_MAGIC_STONE_B_2, EFFECT_MAGIC_STONE_B_3,
                        EFFECT_MAGIC_STONE_B_4, EFFECT_MAGIC_STONE_B_5,
                        EFFECT_MAGIC_STONE_B_6, EFFECT_MAGIC_STONE_B_7,
                        EFFECT_MAGIC_STONE_B_8, EFFECT_MAGIC_STONE_B_9,
                        EFFECT_MAGIC_STONE_C_1, EFFECT_MAGIC_STONE_C_2,
                        EFFECT_MAGIC_STONE_C_3, EFFECT_MAGIC_STONE_C_4,
                        EFFECT_MAGIC_STONE_C_5, EFFECT_MAGIC_STONE_C_6,
                        EFFECT_MAGIC_STONE_C_7, EFFECT_MAGIC_STONE_C_8,
                        EFFECT_MAGIC_STONE_C_9, EFFECT_MAGIC_STONE_D_1,
                        EFFECT_MAGIC_STONE_D_2, EFFECT_MAGIC_STONE_D_3,
                        EFFECT_MAGIC_STONE_D_4, EFFECT_MAGIC_STONE_D_5,
                        EFFECT_MAGIC_STONE_D_6, EFFECT_MAGIC_STONE_D_7,
                        EFFECT_MAGIC_STONE_D_8, EFFECT_MAGIC_STONE_D_9 },
                // 魔眼
                { EFFECT_MAGIC_EYE_OF_AHTHARTS, EFFECT_MAGIC_EYE_OF_FAFURION,
                        EFFECT_MAGIC_EYE_OF_LINDVIOR,
                        EFFECT_MAGIC_EYE_OF_VALAKAS, EFFECT_MAGIC_EYE_OF_BIRTH,
                        EFFECT_MAGIC_EYE_OF_FIGURE, EFFECT_MAGIC_EYE_OF_LIFE } };

        for (final int[] skills : repeatedSkills) {
            for (final int id : skills) {
                if (id == skillId) {
                    this.stopSkillList(skillId, skills);
                }
            }
        }
    }

    /**
     * 解除隐形状态
     */
    public void delInvis() {
        // 魔法接续时间内はこちらを利用
        if (this.hasSkillEffect(INVISIBILITY)) { // 法师魔法 (隐身术)
            this.killSkillEffectTimer(INVISIBILITY);
            this.sendPackets(new S_Invis(this.getId(), 0));
            this.broadcastPacket(new S_OtherCharPacks(this));
        }
        if (this.hasSkillEffect(BLIND_HIDING)) { // 黑暗妖精魔法 (暗隐术)
            this.killSkillEffectTimer(BLIND_HIDING);
            this.sendPackets(new S_Invis(this.getId(), 0));
            this.broadcastPacket(new S_OtherCharPacks(this));
        }
    }

    /**
     * 取消冥想效果
     */
    public void delMeditation() {
        if (this.hasSkillEffect(MEDITATION)) {
            this.removeSkillEffect(MEDITATION);
        }
    }

    /**
     * 删除重复的技能效果
     * 
     * @param skill_id
     *            要删除的技能编号
     */
    public void delRepeatSkillEffect(final int skill_id) {
        if (this.hasSkillEffect(skill_id)) {
            this.killSkillEffectTimer(skill_id);
        }
    }

    /**
     * 结束幽灵状态
     */
    public void endGhost() {
        this.setGhost(false);
        this.setGhostCanTalk(true);
        this.setReserveGhost(false);
    }

    /**
     * 传送出地狱
     */
    public void endHell() {
        if (this._hellFuture != null) {
            this._hellFuture.cancel(false);
            this._hellFuture = null;
        }
        // 从地狱出来后传送到燃柳村。
        final int[] loc = L1TownLocation
                .getGetBackLoc(L1TownLocation.TOWNID_ORCISH_FOREST);
        L1Teleport.teleport(this, loc[0], loc[1], (short) loc[2], 5, true);
        try {
            this.save();
        } catch (final Exception ignore) {
            // ignore
        }
    }

    /**
     * 取得PK次数
     * 
     * @return
     */
    public int get_PKcount() {
        return this._PKcount;
    }

    /**
     * 取得性别
     * 
     * @return
     */
    public byte get_sex() {
        return this._sex;
    }

    /**
     * 取得 -- 加速器检知机能 --
     * 
     * @return
     */
    public AcceleratorChecker getAcceleratorChecker() {
        return this._acceleratorChecker;
    }

    /**
     * 取得账号等级
     * 
     * @return
     */
    public short getAccessLevel() {
        return this._accessLevel;
    }

    /**
     * 取得账号名称
     * 
     * @return
     */
    public String getAccountName() {
        return this._accountName;
    }

    /**
     * 取得灵魂升华增加的HP
     * 
     * @return
     */
    public int getAdvenHp() {
        return this._advenHp;
    }

    /**
     * 取得灵魂升华增加的MP
     * 
     * @return
     */
    public int getAdvenMp() {
        return this._advenMp;
    }

    /**
     * 取得觉醒技能ID
     * 
     * @return
     */
    public int getAwakeSkillId() {
        return this._awakeSkillId;
    }

    /**
     * 取得基本Ac
     * 
     * @return
     */
    public int getBaseAc() {
        return this._baseAc;
    }

    /**
     * 取得弓类基本伤害补正
     * 
     * @return
     */
    public int getBaseBowDmgup() {
        return this._baseBowDmgup;
    }

    /**
     * 取得弓基本命中补正
     * 
     * @return
     */
    public int getBaseBowHitup() {
        return this._baseBowHitup;
    }

    /**
     * 取得基本的魅力值
     * 
     * @return
     */
    public short getBaseCha() {
        return this._baseCha;
    }

    /**
     * 取得基本的体质值
     * 
     * @return
     */
    public short getBaseCon() {
        return this._baseCon;
    }

    /**
     * 取得基本的敏捷值
     * 
     * @return
     */
    public short getBaseDex() {
        return this._baseDex;
    }

    /**
     * 取得基本伤害补正
     * 
     * @return
     */
    public int getBaseDmgup() {
        return this._baseDmgup;
    }

    /**
     * 取得基本命中补正
     * 
     * @return
     */
    public int getBaseHitup() {
        return this._baseHitup;
    }

    /**
     * 取得基本的智力值
     * 
     * @return
     */
    public short getBaseInt() {
        return this._baseInt;
    }

    /**
     * 取得基本MaxHp
     * 
     * @return
     */
    public short getBaseMaxHp() {
        return this._baseMaxHp;
    }

    /**
     * 取得基本MaxMp
     * 
     * @return
     */
    public short getBaseMaxMp() {
        return this._baseMaxMp;
    }

    /**
     * 取得基本魔法防御
     * 
     * @return
     */
    public int getBaseMr() {
        return this._baseMr;
    }

    /**
     * 取得基本的力量值
     * 
     * @return
     */
    public short getBaseStr() {
        return this._baseStr;
    }

    /**
     * 取得基本的精神值
     * 
     * @return
     */
    public short getBaseWis() {
        return this._baseWis;
    }

    /**
     * 取得暂存传送编号 (幽灵状态)
     * 
     * @return
     */
    public byte getBeginGhost() {
        return this._beginGhost;
    }

    /**
     * 取得角色生日
     * 
     * @return
     */
    public Timestamp getBirthday() {
        return this._birthday;
    }

    /**
     * 取得奖励点分配状况
     * 
     * @return
     */
    public int getBonusStats() {
        return this._bonusStats;
    }

    /**
     * 取得记忆坐标ID
     * 
     * @return
     */
    public L1BookMark getBookMark(final int id) {
        for (int i = 0; i < this._bookmarks.size(); i++) {
            final L1BookMark element = this._bookmarks.get(i);
            if (element.getId() == id) {
                return element;
            }

        }
        return null;
    }

    /**
     * 取得记忆坐标名称
     * 
     * @return
     */
    public L1BookMark getBookMark(final String name) {
        for (int i = 0; i < this._bookmarks.size(); i++) {
            final L1BookMark element = this._bookmarks.get(i);
            if (element.getName().equalsIgnoreCase(name)) {
                return element;
            }

        }
        return null;
    }

    /**
     * 取得记忆坐标大小
     * 
     * @return
     */
    public int getBookMarkSize() {
        return this._bookmarks.size();
    }

    /**
     * 取得防具对损坏的弓的补正
     * 
     * @return
     */
    public int getBowDmgModifierByArmor() {
        return this._bowDmgModifierByArmor;
    }

    /**
     * 取得防具对弓的命中率补正
     * 
     * @return
     */
    public int getBowHitModifierByArmor() {
        return this._bowHitModifierByArmor;
    }

    /**
     * 取得购买清单
     * 
     * @return
     */
    public List<L1PrivateShopBuyList> getBuyList() {
        return this._buyList;
    }

    /**
     * 取得呼叫血盟名称
     * 
     * @return
     */
    public int getCallClanHeading() {
        return this._callClanHeading;
    }

    /**
     * 取得呼叫血盟ID
     * 
     * @return
     */
    public int getCallClanId() {
        return this._callClanId;
    }

    /**
     * 取得聊天组队
     * 
     * @return
     */
    public L1ChatParty getChatParty() {
        return this._chatParty;
    }

    /**
     * 取得血盟名称
     * 
     * @return
     */
    public L1Clan getClan() {
        return L1World.getInstance().getClan(this.getClanname());
    }

    /**
     * 取得血盟 ID
     * 
     * @return
     */
    public int getClanid() {
        return this._clanid;
    }

    /**
     * 取得血盟名称
     * 
     * @return
     */
    public String getClanname() {
        return this.clanname;
    }

    /**
     * 取得血盟内的阶级(联盟君主、守护骑士、一般、见习)
     * 
     * @return
     */
    public int getClanRank() {
        return this._clanRank;
    }

    /**
     * @return
     */
    public L1ClassFeature getClassFeature() {
        return this._classFeature;
    }

    /**
     * 取得角色的ClassId
     * 
     * @return
     */
    public int getClassId() {
        return this._classId;
    }

    /**
     * 取得贡献度
     * 
     * @return
     */
    public int getContribution() {
        return this._contribution;
    }

    /**
     * 取得烹饪ID
     * 
     * @return
     */
    public int getCookingId() {
        return this._cookingId;
    }

    /**
     * 取得生存的呐喊时间.
     * 
     * @return 时间
     */
    public int getCryTime() {
        return this._CryTime;
    }

    /**
     * 取得当前武器
     * 
     * @return
     */
    public int getCurrentWeapon() {
        return this._currentWeapon;
    }

    /**
     * 取得防具的伤害减免
     * 
     * @return
     */
    public int getDamageReductionByArmor() {
        return this._damageReductionByArmor;
    }

    /**
     * 取得角色删除时间
     * 
     * @return
     */
    public Timestamp getDeleteTime() {
        return this._deleteTime;
    }

    /**
     * 取得点心ID
     * 
     * @return
     */
    public int getDessertId() {
        return this._dessertId;
    }

    /**
     * 取得防具的伤害补正
     * 
     * @return
     */
    public int getDmgModifierByArmor() {
        return this._dmgModifierByArmor;
    }

    /**
     * @return
     */
    public L1DwarfForElfInventory getDwarfForElfInventory() {
        return this._dwarfForElf;
    }

    /**
     * @return
     */
    public L1DwarfInventory getDwarfInventory() {
        return this._dwarf;
    }

    /**
     * 取得精灵属性
     * 
     * @return
     */
    public int getElfAttr() {
        return this._elfAttr;
    }

    /**
     * 取得万能药使用状况
     * 
     * @return
     */
    public int getElixirStats() {
        return this._elixirStats;
    }

    /**
     * 取得装备插槽
     * 
     * @return
     */
    public L1EquipmentSlot getEquipSlot() {
        return this._equipSlot;
    }

    /**
     * 取得回避率变化
     * 
     * @return
     */
    public int getEr() {
        if (this.hasSkillEffect(STRIKER_GALE)) { // 精准射击
            return 0;
        }

        int er = 0;
        switch (this.getClassId()) {
            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
                er = this.getLevel() >> 2; // 4级增加一点回避
                break;

            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                er = this.getLevel() >> 3; // 8级增加一点回避
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                er = this.getLevel() / 6; // 6级增加一点回避
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                er = this.getLevel() / 10; // 10级增加一点回避
                break;

            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                er = this.getLevel() / 7; // 7级增加一点回避
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                er = this.getLevel() / 9; // 9级增加一点回避
                break;

            default:
                break;
        }

        er += (this.getDex() - 8) >> 1; // (/ 2) 移位算法

        er += this.getOriginalEr();

        if (this.hasSkillEffect(DRESS_EVASION)) { // 闪避提升
            er += 12;
        }
        if (this.hasSkillEffect(SOLID_CARRIAGE)) { // 坚固防护
            er += 15;
        }
        return er;
    }

    /**
     * 取得角色讯息拒绝名单
     * 
     * @return
     */
    public L1ExcludingList getExcludingList() {
        return this._excludingList;
    }

    // 取得经验值
    @Override
    public synchronized long getExp() {
        return this._exp;
    }

    /**
     * 取得经验值加成(装备).
     * 
     * @return 经验值加成
     */
    public double getExpBonus() {
        return this._expBonus;
    }

    /**
     * 取得恢复EXP
     * 
     * @return
     */
    public int getExpRes() {
        return this._expRes;
    }

    /**
     * 取得战斗ID
     * 
     * @return
     */
    public int getFightId() {
        return this._fightId;
    }

    /**
     * 取得钓鱼时间
     * 
     * @return
     */
    public long getFishingTime() {
        return this._fishingTime;
    }

    /**
     * 取得钓鱼坐标X
     * 
     * @return
     */
    public int getFishX() {
        return this._fishX;
    }

    /**
     * 取得钓鱼坐标Y
     * 
     * @return
     */
    public int getFishY() {
        return this._fishY;
    }

    /**
     * 取得装备加速道具
     * 
     * @return
     */
    public int getHasteItemEquipped() {
        return this._hasteItemEquipped;
    }

    /**
     * 取得地狱停留时间 (秒)
     * 
     * @return
     */
    public int getHellTime() {
        return this._hellTime;
    }

    /**
     * 取得过去最高等级
     * 
     * @return
     */
    public int getHighLevel() {
        return this._highLevel;
    }

    /**
     * 取得防具的命中率补正
     * 
     * @return
     */
    public int getHitModifierByArmor() {
        return this._hitModifierByArmor;
    }

    /**
     * 取得所住村镇ID
     * 
     * @return
     */
    public int getHomeTownId() {
        return this._homeTownId;
    }

    /**
     * 取得回血
     * 
     * @return
     */
    public short getHpr() {
        return this._hpr;
    }

    // 取得玩家背包道具
    @Override
    public L1PcInventory getInventory() {
        return this._inventory;
    }

    // 取得友好度
    @Override
    public int getKarma() {
        return this._karma.get();
    }

    /**
     * 取得友好度级别
     * 
     * @return
     */
    public int getKarmaLevel() {
        return this._karma.getLevel();
    }

    /**
     * 取得友好度的百分比
     * 
     * @return
     */
    public int getKarmaPercent() {
        return this._karma.getPercent();
    }

    /**
     * 取得宠物竞速圈数
     * 
     * @return
     */
    public int getLap() {
        return this._lap;
    }

    /**
     * 取得检查宠物竞速圈数
     * 
     * @return
     */
    public int getLapCheck() {
        return this._lapCheck;
    }

    /**
     * 只是将总圈数的完程度数量化
     * 
     * @return
     */
    public int getLapScore() {
        return this._lap * 29 + this._lapCheck;
    }

    /**
     * 取得玩家的最后PK时间。
     * 
     * @return _lastPk
     */
    public Timestamp getLastPk() {
        return this._lastPk;
    }

    /**
     * @return
     */
    public Timestamp getLastPkForElf() {
        return this._lastPkForElf;
    }

    // 取得魔法等级
    @Override
    public int getMagicLevel() {
        return this.getClassFeature().getMagicLevel(this.getLevel());
    }

    /**
     * 取得最高负重
     * 
     * @return
     */
    public double getMaxWeight() {
        final int str = this.getStr();
        final int con = this.getCon();
        double maxWeight = 150 * (Math.floor(0.6 * str + 0.4 * con + 1));

        double weightReductionByArmor = this.getWeightReduction(); // 防具的负重减轻
        weightReductionByArmor /= 100;

        double weightReductionByDoll = 0; // 魔法娃娃负重减轻
        weightReductionByDoll += L1MagicDoll.getWeightReductionByDoll(this);
        weightReductionByDoll /= 100;

        int weightReductionByMagic = 0;
        if (this.hasSkillEffect(DECREASE_WEIGHT)) { // 负重强化
            weightReductionByMagic = 180;
        }

        double originalWeightReduction = 0; // 原始状态的负重减轻
        originalWeightReduction += 0.04 * (this.getOriginalStrWeightReduction() + this
                .getOriginalConWeightReduction());

        final double weightReduction = 1 + weightReductionByArmor
                + weightReductionByDoll + originalWeightReduction;

        maxWeight *= weightReduction;

        maxWeight += weightReductionByMagic;

        maxWeight *= Config.RATE_WEIGHT_LIMIT; // 最大负重倍率

        return maxWeight;
    }

    /**
     * 取得回魔
     * 
     * @return
     */
    public short getMpr() {
        return this._mpr;
    }

    /**
     * 网络连接状态
     * 
     * @return
     */
    public ClientThread getNetConnection() {
        return this._netConnection;
    }

    /**
     * 取得在线状态
     * 
     * @return
     */
    public int getOnlineStatus() {
        return this._onlineStatus;
    }

    /**
     * 取得原始DEX ＡＣ补正
     * 
     * @return
     */
    public int getOriginalAc() {
        return this._originalAc;
    }

    /**
     * 取得原始DEX 弓伤害补正
     * 
     * @return
     */
    public int getOriginalBowDmgup() {
        return this._originalBowDmgup;
    }

    /**
     * 取得原始DEX 命中补正
     * 
     * @return
     */
    public int getOriginalBowHitup() {
        return this._originalBowHitup;
    }

    /**
     * 取得原始的魅力值
     * 
     * @return
     */
    public int getOriginalCha() {
        return this._originalCha;
    }

    /**
     * 取得原始的体质值
     * 
     * @return
     */
    public int getOriginalCon() {
        return this._originalCon;
    }

    /**
     * 取得原始CON 重量轻减
     * 
     * @return
     */
    public int getOriginalConWeightReduction() {
        return this._originalConWeightReduction;
    }

    /**
     * 取得原始的敏捷值
     * 
     * @return
     */
    public int getOriginalDex() {
        return this._originalDex;
    }

    /**
     * 取得原始STR 伤害补正
     * 
     * @return
     */
    public int getOriginalDmgup() {
        return this._originalDmgup;
    }

    /**
     * 取得原始DEX ER补正
     * 
     * @return
     */
    public int getOriginalEr() {
        return this._originalEr;
    }

    /**
     * 取得原始STR 命中补正
     * 
     * @return
     */
    public int getOriginalHitup() {
        return this._originalHitup;
    }

    /**
     * 取得原始 CON HPR
     * 
     * @return
     */
    public short getOriginalHpr() {
        return this._originalHpr;
    }

    /**
     * 取得原始CON HP上升值补正
     * 
     * @return
     */
    public int getOriginalHpup() {
        return this._originalHpup;
    }

    /**
     * 取得原始的智力值
     * 
     * @return
     */
    public int getOriginalInt() {
        return this._originalInt;
    }

    /**
     * 取得原始INT 消费MP轻减
     * 
     * @return
     */
    public int getOriginalMagicConsumeReduction() {
        return this._originalMagicConsumeReduction;
    }

    /**
     * @return
     */
    public int getOriginalMagicCritical() {
        return this._originalMagicCritical;
    }

    /**
     * 取得原始INT的魔法伤害
     * 
     * @return
     */
    public int getOriginalMagicDamage() {
        return this._originalMagicDamage;
    }

    /**
     * 取得原始INT 魔法命中
     * 
     * @return
     */
    public int getOriginalMagicHit() {
        return this._originalMagicHit;
    }

    /**
     * 取得原始 WIS MPR
     * 
     * @return
     */
    public short getOriginalMpr() {
        return this._originalMpr;
    }

    /**
     * 取得原始WIS MP上升值补正
     * 
     * @return
     */
    public int getOriginalMpup() {
        return this._originalMpup;
    }

    /**
     * 取得原始WIS 魔法防御
     * 
     * @return
     */
    public int getOriginalMr() {
        return this._originalMr;
    }

    /**
     * 取得原始的力量值
     * 
     * @return
     */
    public int getOriginalStr() {
        return this._originalStr;
    }

    /**
     * 取得原始STR 重量轻减
     * 
     * @return
     */
    public int getOriginalStrWeightReduction() {
        return this._originalStrWeightReduction;
    }

    /**
     * 取得原始的精神值
     * 
     * @return
     */
    public int getOriginalWis() {
        return this._originalWis;
    }

    /**
     * 取得结婚伴侣ID
     * 
     * @return
     */
    public int getPartnerId() {
        return this._partnerId;
    }

    /**
     * 取得出售物品种类数量
     * 
     * @return
     */
    public int getPartnersPrivateShopItemCount() {
        return this._partnersPrivateShopItemCount;
    }

    /**
     * 取得组队
     * 
     * @return
     */
    public L1Party getParty() {
        return this._party;
    }

    /**
     * 取得组队ID
     * 
     * @return
     */
    public int getPartyID() {
        return this._partyID;
    }

    /**
     * 取得组队类型
     * 
     * @return
     */
    public int getPartyType() {
        return this._partyType;
    }

    /**
     * 取得村庄福利金
     * 
     * @return
     */
    public int getPay() {
        return this._pay;
    }

    /**
     * 取得PK次数 (精灵用)
     * 
     * @return
     */
    public int getPkCountForElf() {
        return this._PkCountForElf;
    }

    /**
     * 取得任务
     * 
     * @return
     */
    public L1Quest getQuest() {
        return this._quest;
    }

    /**
     * 取得奇岩地监时间.
     * 
     * @return 时间
     */
    public int getRocksPrisonTime() {
        return this._rocksPrisonTime;
    }

    /**
     * 取得贩卖清单
     * 
     * @return
     */
    public List<L1PrivateShopSellList> getSellList() {
        return this._sellList;
    }

    /**
     * 取得商店聊天
     * 
     * @return
     */
    public byte[] getShopChat() {
        return this._shopChat;
    }

    /**
     * 取得简单的角色生日
     * 
     * @return
     */
    public int getSimpleBirthday() {
        if (this._birthday != null) {
            final SimpleDateFormat SimpleDate = new SimpleDateFormat("yyyyMMdd");
            final int BornTime = Integer.parseInt(SimpleDate
                    .format(this._birthday.getTime()));
            return BornTime;
        }
        return 0;
    }

    /**
     * 取得统计
     */
    private L1PcInstance getStat() {
        return null;
    }

    /**
     * 取得传送目的地 面向
     * 
     * @return
     */
    public int getTeleportHeading() {
        return this._teleportHeading;
    }

    /**
     * 取得传送目的地 地图ID
     * 
     * @return
     */
    public short getTeleportMapId() {
        return this._teleportMapId;
    }

    /**
     * 取得传送目的地坐标X
     * 
     * @return
     */
    public int getTeleportX() {
        return this._teleportX;
    }

    /**
     * 取得传送目的地坐标Y
     * 
     * @return
     */
    public int getTeleportY() {
        return this._teleportY;
    }

    /**
     * 取得角色死亡时的临时GFX图像
     * 
     * @return
     */
    public int getTempCharGfxAtDead() {
        return this._tempCharGfxAtDead;
    }

    /**
     * 取得临时ID
     * 
     * @return
     */
    public int getTempID() {
        return this._tempID;
    }

    /**
     * 取得临时等级
     * 
     * @return
     */
    public int getTempLevel() {
        return this._tempLevel;
    }

    /**
     * 取得临时最高等级
     * 
     * @return
     */
    public int getTempMaxLevel() {
        return this._tempMaxLevel;
    }

    /**
     * 取得暂存字符串(收件者)
     * 
     * @return
     */
    public String getText() {
        return this._text;
    }

    /**
     * 取得暂存byte[]阵列
     * 
     * @return
     */
    public byte[] getTextByte() {
        return this._textByte;
    }

    /**
     * 取得交易ID
     * 
     * @return
     */
    public int getTradeID() {
        return this._tradeID;
    }

    /**
     * 取得交易OK
     * 
     * @return
     */
    public boolean getTradeOk() {
        return this._tradeOk;
    }

    /**
     * 取得交易窗口的库存
     * 
     * @return
     */
    public L1Inventory getTradeWindowInventory() {
        return this._tradewindow;
    }

    /**
     * 取得类型 <b> 0:王族 1:骑士 2:精灵 3:法师 4:黑妖 5:龙骑 6:幻术
     * 
     * @return
     */
    public int getType() {
        return this._type;
    }

    /**
     * 取得使用的武器
     * 
     * @return
     */
    public L1ItemInstance getWeapon() {
        return this._weapon;
    }

    /**
     * @return
     */
    public int getWeightReduction() {
        return this._weightReduction;
    }

    // 恢复HP
    @Override
    public void healHp(final int pt) {
        super.healHp(pt);

        this.sendPackets(new S_HPUpdate(this));
    }

    /**
     * 角色封锁中
     * 
     * @return
     */
    public boolean isBanned() {
        return this._banned;
    }

    /**
     * 暂存乱数传送状态 (幽灵)
     * 
     * @return
     */
    public boolean isBeginGhostFlag() {
        return this._beginGhostFlag;
    }

    /**
     * 是可以密语
     * 
     * @return
     */
    public boolean isCanWhisper() {
        return this._isCanWhisper;
    }

    /**
     * 是王族
     * 
     * @return
     */
    public boolean isCrown() {
        return ((this.getClassId() == CLASSID_PRINCE) || (this.getClassId() == CLASSID_PRINCESS));
    }

    /**
     * 是黑暗精灵
     * 
     * @return
     */
    public boolean isDarkelf() {
        return ((this.getClassId() == CLASSID_DARK_ELF_MALE) || (this
                .getClassId() == CLASSID_DARK_ELF_FEMALE));
    }

    /**
     * 是龙骑士
     * 
     * @return
     */
    public boolean isDragonKnight() {
        return ((this.getClassId() == CLASSID_DRAGON_KNIGHT_MALE) || (this
                .getClassId() == CLASSID_DRAGON_KNIGHT_FEMALE));
    }

    /**
     * 醉酒状态
     * 
     * @return
     */
    public boolean isDrink() {
        return this._isDrink;
    }

    /**
     * 是精灵
     * 
     * @return
     */
    public boolean isElf() {
        return ((this.getClassId() == CLASSID_ELF_MALE) || (this.getClassId() == CLASSID_ELF_FEMALE));
    }

    /**
     * 钓鱼中
     * 
     * @return
     */
    public boolean isFishing() {
        return this._isFishing;
    }

    /**
     * 准备好钓鱼
     * 
     * @return
     */
    public boolean isFishingReady() {
        return this._isFishingReady;
    }

    /**
     * 使用屠宰者判断
     * 
     * @return
     */
    public boolean isFoeSlayer() {
        return this._FoeSlayer;
    }

    /**
     * 是幽灵状态
     * 
     * @return
     */
    public boolean isGhost() {
        return this._ghost;
    }

    /**
     * 幽灵状态可以与NPC对话
     * 
     * @return
     */
    public boolean isGhostCanTalk() {
        return this._ghostCanTalk;
    }

    /**
     * 是GM
     * 
     * @return
     */
    public boolean isGm() {
        return this._gm;
    }

    /**
     * GM隐身中
     * 
     * @return
     */
    public boolean isGmInvis() {
        return this._gmInvis;
    }

    /**
     * @return
     */
    public boolean isGres() {
        return this._isGres;
    }

    /**
     * 是G-RES有效
     * 
     * @return
     */
    public boolean isGresValid() {
        return this._gresValid;
    }

    /**
     * 是幻术师
     * 
     * @return
     */
    public boolean isIllusionist() {
        return ((this.getClassId() == CLASSID_ILLUSIONIST_MALE) || (this
                .getClassId() == CLASSID_ILLUSIONIST_FEMALE));
    }

    /**
     * 角色重新开始
     * 
     * @return
     */
    public boolean isInCharReset() {
        return this._isInCharReset;
    }

    /**
     * 在聊天组队
     * 
     * @return
     */
    public boolean isInChatParty() {
        return this.getChatParty() != null;
    }

    /**
     * @return
     */
    public boolean isInOrderList() {
        return this._order_list;
    }

    /**
     * 在组队中
     * 
     * @return
     */
    public boolean isInParty() {
        return this.getParty() != null;
    }

    /**
     * 隐身延迟中
     * 
     * @return
     */
    public boolean isInvisDelay() {
        return (this.invisDelayCounter > 0);
    }

    /**
     * 在战争旗帜坐标內
     * 
     * @param pc
     * @param target
     * @return
     */
    boolean isInWarAreaAndWarTime(final L1PcInstance pc,
            final L1PcInstance target) {
        // pcとtargetが战争中に战争エリアに居るか
        final int castleId = L1CastleLocation.getCastleIdByArea(pc);
        final int targetCastleId = L1CastleLocation.getCastleIdByArea(target);
        if ((castleId != 0) && (targetCastleId != 0)
                && (castleId == targetCastleId)) {
            if (WarTimeController.getInstance().isNowWar(castleId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是骑士
     * 
     * @return
     */
    public boolean isKnight() {
        return ((this.getClassId() == CLASSID_KNIGHT_MALE) || (this
                .getClassId() == CLASSID_KNIGHT_FEMALE));
    }

    /**
     * 是管理者
     * 
     * @return
     */
    public boolean isMonitor() {
        return this._monitor;
    }

    /**
     * 粉名
     * 
     * @return
     */
    public boolean isPinkName() {
        return this._isPinkName;
    }

    /**
     * 商店模式
     * 
     * @return
     */
    public boolean isPrivateShop() {
        return this._isPrivateShop;
    }

    /**
     * 准备解除幽灵状态
     * 
     * @return
     */
    public boolean isReserveGhost() {
        return this._isReserveGhost;
    }

    /**
     * 生命之树果实 移速 * 1.15
     * 
     * @return
     */
    public boolean isRibrave() {
        return this.hasSkillEffect(STATUS_RIBRAVE);
    }

    /**
     * 变身中
     * 
     * @return
     */
    public boolean isShapeChange() {
        return this._isShapeChange;
    }

    /**
     * 是显示血盟聊天
     * 
     * @return
     */
    public boolean isShowClanChat() {
        return this._isShowClanChat;
    }

    /**
     * 是显示组队聊天
     * 
     * @return
     */
    public boolean isShowPartyChat() {
        return this._isShowPartyChat;
    }

    /**
     * 是显示交易聊天
     * 
     * @return
     */
    public boolean isShowTradeChat() {
        return this._isShowTradeChat;
    }

    /**
     * 是显示世界聊天
     * 
     * @return
     */
    public boolean isShowWorldChat() {
        return this._isShowWorldChat;
    }

    /**
     * 在学习技能
     * 
     * @return
     */
    public boolean isSkillMastery(final int skillid) {
        return this.skillList.contains(skillid);
    }

    /**
     * 判断是否无道具施法(召戒清单、变身清单)
     * 
     * @return
     */
    public boolean isSummonMonster() {
        return this._isSummonMonster;
    }

    /**
     * 瞬移中
     * 
     * @return
     */
    public boolean isTeleport() {
        return this._isTeleport;
    }

    /**
     * 三段加速 * 1.15
     * 
     * @return
     */
    public boolean isThirdSpeed() {
        return this.hasSkillEffect(STATUS_THIRD_SPEED);
    }

    /**
     * 正在进行个人商店交易
     * 
     * @return
     */
    public boolean isTradingInPrivateShop() {
        return this._isTradingInPrivateShop;
    }

    /**
     * 返回角色正在被通缉中。
     * 
     * @return 角色正在被通缉、true
     */
    public boolean isWanted() {
        if (this._lastPk == null) {
            return false;
        } else if (System.currentTimeMillis() - this._lastPk.getTime() > 24 * 3600 * 1000) {
            this.setLastPk(null);
            return false;
        }
        return true;
    }

    /**
     * 返回角色(精灵)正在被通缉中。
     * 
     * @return 角色正在被通缉、true
     */
    public boolean isWantedForElf() {
        if (this._lastPkForElf == null) {
            return false;
        } else if (System.currentTimeMillis() - this._lastPkForElf.getTime() > 24 * 3600 * 1000) {
            this.setLastPkForElf(null);
            return false;
        }
        return true;
    }

    /**
     * 风之枷锁 攻速 / 2
     * 
     * @return
     */
    public boolean isWindShackle() {
        return this.hasSkillEffect(WIND_SHACKLE);
    }

    /**
     * 是法师
     * 
     * @return
     */
    public boolean isWizard() {
        return ((this.getClassId() == CLASSID_WIZARD_MALE) || (this
                .getClassId() == CLASSID_WIZARD_FEMALE));
    }

    /**
     * 等级下降
     * 
     * @param gap
     */
    private void levelDown(final int gap) {
        this.resetLevel();

        for (int i = 0; i > gap; i--) {
            // レベルダウン时はランダム值をそのままマイナスする为に、base值に0を设定
            final short randomHp = CalcStat.calcStatHp(this.getType(), 0,
                    this.getBaseCon(), this.getOriginalHpup());
            final short randomMp = CalcStat.calcStatMp(this.getType(), 0,
                    this.getBaseWis(), this.getOriginalMpup());
            this.addBaseMaxHp((short) -randomHp);
            this.addBaseMaxMp((short) -randomMp);
        }
        this.resetBaseHitup();
        this.resetBaseDmgup();
        this.resetBaseAc();
        this.resetBaseMr();
        if (Config.LEVEL_DOWN_RANGE != 0) {
            if (this.getHighLevel() - this.getLevel() >= Config.LEVEL_DOWN_RANGE) {
                this.sendPackets(new S_ServerMessage(64)); // 连线中断。
                this.sendPackets(new S_Disconnect());
                _log.info(String.format("超过允许等级上下限差异的范围，切断 %s的连线。",
                        this.getName()));
            }
        }

        try {
            // 将资料保存到资料库
            this.save();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
        this.sendPackets(new S_OwnCharStatus(this));

        // 处理新手保护系统(遭遇的守护)状态资料的变动
        this.checkNoviceType();
    }

    /**
     * 判断提升等级
     * 
     * @param gap
     */
    private void levelUp(final int gap) {
        this.resetLevel();

        // 返生药水
        if ((this.getLevel() == 99) && Config.ALT_REVIVAL_POTION) {
            try {
                final L1Item l1item = ItemTable.getInstance()
                        .getTemplate(43000);
                if (l1item != null) {
                    this.getInventory().storeItem(43000, 1);
                    this.sendPackets(new S_ServerMessage(403, l1item.getName())); // 获得%0%o
                                                                                  // 。
                } else {
                    this.sendPackets(new S_SystemMessage("返生药水取得失败。"));
                }
            } catch (final Exception e) {
                _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                this.sendPackets(new S_SystemMessage("返生药水取得失败。"));
            }
        }

        for (int i = 0; i < gap; i++) {
            final short randomHp = CalcStat.calcStatHp(this.getType(),
                    this.getBaseMaxHp(), this.getBaseCon(),
                    this.getOriginalHpup());
            final short randomMp = CalcStat.calcStatMp(this.getType(),
                    this.getBaseMaxMp(), this.getBaseWis(),
                    this.getOriginalMpup());
            this.addBaseMaxHp(randomHp);
            this.addBaseMaxMp(randomMp);

            if (Config.LvUpHpMpFull) {
                this.setCurrentHp(this.getMaxHp()); // 升级血满
                this.setCurrentMp(this.getMaxMp()); // 升级魔满
            }
        }
        this.resetBaseHitup();
        this.resetBaseDmgup();
        this.resetBaseAc();
        this.resetBaseMr();
        if (this.getLevel() > this.getHighLevel()) {
            this.setHighLevel(this.getLevel());
        }

        try {
            // 将资料保存到资料库
            this.save();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }

        // 奖励点数
        if ((this.getLevel() >= 51)
                && (this.getLevel() - 50 > this.getBonusStats())) {
            if ((this.getBaseStr() + this.getBaseDex() + this.getBaseCon()
                    + this.getBaseInt() + this.getBaseWis() + this.getBaseCha()) < (Config.BONUS_STATS1 * 6)) {
                this.sendPackets(new S_bonusstats(this.getId(), 1));
            }
        }
        this.sendPackets(new S_OwnCharStatus(this)); // 更新角色属性资料

        // 根据等级判断地图限制
        switch (this.getMapId()) {
            case 2005: // 隐藏之谷
                if (this.getLevel() >= 13) { // 等级大于13
                    if (this.getQuest().get_step(L1Quest.QUEST_TUTOR) != 255) {
                        this.getQuest().set_step(L1Quest.QUEST_TUTOR, 255);
                    }
                    L1Teleport.teleport(this, 33084, 33391, (short) 4, 5, true);// 银骑士村
                }
                break;

            case 777: // 原生魔族抛弃之地地面(暗影神殿)
                if (this.getLevel() >= 52) {
                    L1Teleport.teleport(this, 34043, 32184, (short) 4, 5, true); // 象牙塔门口前
                }
                break;

            case 778: // 原生魔族抛弃之地海底(欲望洞窟)or不死魔族抛弃之地
            case 779:
                if (this.getLevel() >= 52) {
                    L1Teleport.teleport(this, 32608, 33178, (short) 4, 5, true); // WB
                }
                break;
        }
        // 处理新手保护系统(遭遇的守护)状态资料的变动
        this.checkNoviceType();
    }

    /**
     * 玩家登出游戏
     */
    public void logout() {
        final L1World world = L1World.getInstance();
        if (this.getClanid() != 0) // 有血盟
        {
            final L1Clan clan = world.getClan(this.getClanname());
            if (clan != null) {
                if (clan.getWarehouseUsingChar() == this.getId()) // 使用自身的血盟仓库中
                {
                    clan.setWarehouseUsingChar(0); // 解锁血盟仓库
                }
            }
        }
        this.notifyPlayersLogout(this.getKnownPlayers());
        world.removeVisibleObject(this);
        world.removeObject(this);
        this.notifyPlayersLogout(world.getRecognizePlayer(this));
        this._inventory.clearItems();
        this._dwarf.clearItems();
        this.removeAllKnownObjects();
        this.stopHpRegeneration();
        this.stopMpRegeneration();
        this.setDead(true); // 使い方おかしいかもしれないけど、删除ＮＰＣ
        this.setNetConnection(null);
        this.setPacketOutput(null);
        this.stopCryOfSurvival();
        this.setCryTime(0);
        this.stopRocksPrison();
    }

    /**
     * 离开幽灵状态 (传送回出发点)
     */
    public void makeReadyEndGhost() {
        this.setReserveGhost(true);
        L1Teleport.teleport(this, this._ghostSaveLocX, this._ghostSaveLocY,
                this._ghostSaveMapId, this._ghostSaveHeading, true);
    }

    /**
     * 通知指定的玩家登出
     * 
     * @param playersList
     *            玩家列表
     */
    private void notifyPlayersLogout(final List<L1PcInstance> playersArray) {
        for (final L1PcInstance player : playersArray) {
            if (player.knownsObject(this)) {
                player.removeKnownObject(this);
                player.sendPackets(new S_RemoveObject(this));
            }
        }
    }

    /**
     * 对该物件攻击的调用
     * 
     * @param attacker
     *            攻击方
     */
    @Override
    public void onAction(final L1PcInstance attacker) {
        this.onAction(attacker, 0);
    }

    /**
     * 对该物件攻击的调用
     * 
     * @param attacker
     *            攻击方
     * @param skillId
     *            技能ID
     */
    @Override
    public void onAction(final L1PcInstance attacker, final int skillId) {
        // XXX:NullPointerException回避。onActionの引数の型はL1Characterのほうが良い？
        if (attacker == null) {
            return;
        }
        // 在瞬移中
        if (this.isTeleport()) {
            return;
        }

        // 攻击或攻击方在安全区 仅送出动作资讯
        if ((this.getZoneType() == 1) || (attacker.getZoneType() == 1)) {
            // 发送攻击动作
            final L1Attack attack_mortion = new L1Attack(attacker, this,
                    skillId);
            attack_mortion.action();
            return;
        }

        // 禁止PK服务器 仅送出动作资讯
        if (this.checkNonPvP(this, attacker) == true) {
            // 发送攻击动作
            final L1Attack attack_mortion = new L1Attack(attacker, this,
                    skillId);
            attack_mortion.action();
            return;
        }

        // 攻击解除隐身
        if ((this.getCurrentHp() > 0) && !this.isDead()) {
            attacker.delInvis();

            boolean isCounterBarrier = false;

            // 开始计算攻击
            final L1Attack attack = new L1Attack(attacker, this, skillId);
            if (attack.calcHit()) {
                if (this.hasSkillEffect(COUNTER_BARRIER)) {
                    final L1Magic magic = new L1Magic(this, attacker);
                    final boolean isProbability = magic
                            .calcProbabilityMagic(COUNTER_BARRIER);
                    final boolean isShortDistance = attack.isShortDistance();
                    if (isProbability && isShortDistance) {
                        isCounterBarrier = true;
                    }
                }
                if (!isCounterBarrier) {
                    attacker.setPetTarget(this);

                    attack.calcDamage();
                    attack.calcStaffOfMana();
                    attack.addPcPoisonAttack(attacker, this);
                    attack.addChaserAttack();
                }
            }
            if (isCounterBarrier) {
                attack.actionCounterBarrier();
                attack.commitCounterBarrier();
            } else {
                attack.action();
                attack.commit();
            }
        }
    }

    /**
     * 改变经验值
     */
    public void onChangeExp() {
        final int level = ExpTable.getLevelByExp(this.getExp());
        final int char_level = this.getLevel();
        final int gap = level - char_level;
        if (gap == 0) {
            // sendPackets(new S_OwnCharStatus(this));
            this.sendPackets(new S_Exp(this));
            return;
        }

        // 等级变化的场合
        if (gap > 0) {
            this.levelUp(gap);
        } else if (gap < 0) {
            this.levelDown(gap);
        }
    }

    // 对象的荧幕范围进入玩家
    @Override
    public void onPerceive(final L1PcInstance perceivedFrom) {
        // 判断旅馆内是否使用相同钥匙
        if ((perceivedFrom.getMapId() >= 16384)
                && (perceivedFrom.getMapId() <= 25088 // 旅馆内判断
                ) && (perceivedFrom.getInnKeyId() != this.getInnKeyId())) {
            return;
        }
        if (this.isGmInvis() || this.isGhost()) {
            return;
        }
        if (this.isInvisble()
                && !perceivedFrom.hasSkillEffect(GMSTATUS_FINDINVIS)) {
            return;
        }

        perceivedFrom.addKnownObject(this);
        perceivedFrom.sendPackets(new S_OtherCharPacks(this, perceivedFrom
                .hasSkillEffect(GMSTATUS_FINDINVIS))); // 自分の情报を送る
        if (this.isInParty() && this.getParty().isMember(perceivedFrom)) { // PTメンバーならHPメーターも送る
            perceivedFrom.sendPackets(new S_HPMeter(this));
        }

        if (this.isPrivateShop()) { // 开个人商店中
            perceivedFrom.sendPackets(new S_DoActionShop(this.getId(),
                    ActionCodes.ACTION_Shop, this.getShopChat()));
        } else if (this.isFishing()) { // 钓鱼中
            perceivedFrom.sendPackets(new S_Fishing(this.getId(),
                    ActionCodes.ACTION_Fishing, this.getFishX(), this
                            .getFishY()));
        }

        if (this.isCrown()) { // 君主
            final L1Clan clan = L1World.getInstance().getClan(
                    this.getClanname());
            if (clan != null) {
                if ((this.getId() == clan.getLeaderId() // 有城的血盟主
                        )
                        && (clan.getCastleId() != 0)) {
                    perceivedFrom.sendPackets(new S_CastleMaster(clan
                            .getCastleId(), this.getId())); // 发送王冠
                }
            }
        }
    }

    /**
     * 处理受到攻击HP减少
     * 
     * @param attacker
     * @param Damage
     * @param isMagicDamage
     */
    public void receiveDamage(final L1Character attacker, double damage,
            final boolean isMagicDamage) { // 攻击でＨＰを减らすときはここを使用
        if ((this.getCurrentHp() > 0) && !this.isDead()) {
            if (attacker != this) {
                if (!(attacker instanceof L1EffectInstance)
                        && !this.knownsObject(attacker)
                        && (attacker.getMapId() == this.getMapId())) {
                    attacker.onPerceive(this);
                }
            }

            if (isMagicDamage == true) { // 连续魔法伤害による轻减
                final double nowTime = System.currentTimeMillis();
                final double interval = (20D - (nowTime - this._oldTime) / 100D) % 20D;

                if (damage > 0) {
                    if (interval > 0) {
                        damage *= (1D - interval / 30D);
                    }

                    if (damage < 1) {
                        damage = 0;
                    }

                    this._oldTime = nowTime; // 次回のために时间を保存
                }
            }
            if (damage > 0) {
                this.delInvis();
                if (attacker instanceof L1PcInstance) {
                    L1PinkName.onAction(this, attacker);
                }
                if ((attacker instanceof L1PcInstance)
                        && ((L1PcInstance) attacker).isPinkName()) {
                    // ガードが画面内にいれば、攻击者をガードのターゲットに设定する
                    final Iterator<L1Object> itr = L1World.getInstance()
                            .getVisibleObjects(attacker).iterator();
                    while (itr.hasNext()) {
                        final L1Object object = itr.next();
                        /*
                         * for (L1Object object :
                         * L1World.getInstance().getVisibleObjects(attacker)) {
                         */
                        if (object instanceof L1GuardInstance) {
                            final L1GuardInstance guard = (L1GuardInstance) object;
                            guard.setTarget(((L1PcInstance) attacker));
                        }
                    }
                }
                this.removeSkillEffect(FOG_OF_SLEEPING); // 法师魔法 (沉睡之雾)
            }

            if (this.hasSkillEffect(MORTAL_BODY)
                    && (this.getId() != attacker.getId())) {
                final int rnd = Random.nextInt(100) + 1;
                if ((damage > 0) && (rnd <= 10)) {
                    if (attacker instanceof L1PcInstance) {
                        final L1PcInstance attackPc = (L1PcInstance) attacker;
                        attackPc.sendPackets(new S_DoActionGFX(
                                attackPc.getId(), ActionCodes.ACTION_Damage));
                        attackPc.broadcastPacket(new S_DoActionGFX(attackPc
                                .getId(), ActionCodes.ACTION_Damage));
                        attackPc.receiveDamage(this, 30, false);
                    } else if (attacker instanceof L1NpcInstance) {
                        final L1NpcInstance attackNpc = (L1NpcInstance) attacker;
                        attackNpc.broadcastPacket(new S_DoActionGFX(attackNpc
                                .getId(), ActionCodes.ACTION_Damage));
                        attackNpc.receiveDamage(this, 30);
                    }
                }
            }
            if (this.getInventory().checkEquipped(145) // 狂战士斧
                    || this.getInventory().checkEquipped(149)) { // 牛人斧头
                damage *= 1.5; // 伤害提高1.5倍
            }
            if (this.hasSkillEffect(ILLUSION_AVATAR)) {// 幻术师魔法 (幻觉：化身)
                damage *= 1.2; // 伤害提高1.2倍
            }
            if (attacker instanceof L1PetInstance) {
                final L1PetInstance pet = (L1PetInstance) attacker;
                // 目标在安区、攻击者在安区、NOPVP
                if ((this.getZoneType() == 1) || (pet.getZoneType() == 1)
                        || (this.checkNonPvP(this, pet))) {
                    damage = 0;
                }
            } else if (attacker instanceof L1SummonInstance) {
                final L1SummonInstance summon = (L1SummonInstance) attacker;
                // 目标在安区、攻击者在安区、NOPVP
                if ((this.getZoneType() == 1) || (summon.getZoneType() == 1)
                        || (this.checkNonPvP(this, summon))) {
                    damage = 0;
                }
            }
            int newHp = this.getCurrentHp() - (int) (damage);
            if (newHp > this.getMaxHp()) {
                newHp = this.getMaxHp();
            }
            if (newHp <= 0) {
                if (this.isGm()) {
                    this.setCurrentHp(this.getMaxHp());
                } else {
                    this.death(attacker);
                }
            }
            if (newHp > 0) {
                this.setCurrentHp(newHp);
            }
        } else if (!this.isDead()) { // 不是死亡状态
            System.out.println("警告∶PC的hp减少的运算出现错误。※将视为hp=0作处理");
            this.death(attacker);
        }
    }

    /**
     * 魔法伤害减免使用 attr:0.无属性魔法,1.地魔法,2.火魔法,3.水魔法,4.风魔法
     * 
     * @param attacker
     *            攻击者
     * @param damage
     *            伤害
     * @param attr
     *            属性
     */
    public void receiveDamage(final L1Character attacker, int damage,
            final int attr) {
        final int player_mr = this.getMr();
        final int rnd = Random.nextInt(100) + 1;
        if (player_mr >= rnd) {
            damage /= 2;
        }
        this.receiveDamage(attacker, damage, false);
    }

    /**
     * 处理受到攻击MP减少
     * 
     * @param attacker
     * @param mpDamage
     */
    public void receiveManaDamage(final L1Character attacker, final int mpDamage) { // 攻击でＭＰを减らすときはここを使用
        if ((mpDamage > 0) && !this.isDead()) {
            this.delInvis();
            if (attacker instanceof L1PcInstance) {
                L1PinkName.onAction(this, attacker);
            }
            if ((attacker instanceof L1PcInstance)
                    && ((L1PcInstance) attacker).isPinkName()) {
                // ガードが画面内にいれば、攻击者をガードのターゲットに设定する
                for (final L1Object object : L1World.getInstance()
                        .getVisibleObjects(attacker)) {
                    if (object instanceof L1GuardInstance) {
                        final L1GuardInstance guard = (L1GuardInstance) object;
                        guard.setTarget(((L1PcInstance) attacker));
                    }
                }
            }

            int newMp = this.getCurrentMp() - mpDamage;
            if (newMp > this.getMaxMp()) {
                newMp = this.getMaxMp();
            }

            if (newMp <= 0) {
                newMp = 0;
            }
            this.setCurrentMp(newMp);
        }
    }

    /**
     * 减少当前的HP
     * 
     * @param d
     * @param l1character
     */
    public void reduceCurrentHp(final double d, final L1Character l1character) {
        this.getStat().reduceCurrentHp(d, l1character);
    }

    /**
     * 全属性重置
     */
    public void refresh() {
        this.resetLevel(); // 重置等级
        this.resetBaseHitup(); // 重置基本命中率
        this.resetBaseDmgup(); // 重置基本伤害值
        this.resetBaseMr(); // 重置基本魔防
        this.resetBaseAc(); // 重置基本防御
        this.resetOriginalHpup(); // 重置原本的最高 HP
        this.resetOriginalMpup(); // 重置原本的最高 MP
        this.resetOriginalDmgup(); // 重置原本的最高伤害值
        this.resetOriginalBowDmgup(); // 重置原本的最高弓箭伤害值
        this.resetOriginalHitup(); // 重置原本的最高命中率
        this.resetOriginalBowHitup(); // 重置原本弓箭的最高命中率
        this.resetOriginalMr(); // 重置原本的魔防
        this.resetOriginalMagicHit(); // 重置原本的魔法命中率
        this.resetOriginalMagicCritical(); // 重置原本的魔法攻击
        this.resetOriginalMagicConsumeReduction(); // 重置原始魔法消耗减少
        this.resetOriginalMagicDamage(); // 重置原始的魔法伤害值
        this.resetOriginalAc(); // 重置原始的防御
        this.resetOriginalEr(); // 重置原始的回避率
        this.resetOriginalHpr(); // 重置原始的回血量
        this.resetOriginalMpr(); // 重置原始的回魔量
        this.resetOriginalStrWeightReduction(); // 重置原始的 STR 减轻负重
        this.resetOriginalConWeightReduction(); // 重置原始的 CON 减轻负重
    }

    /**
     * 删除记忆坐标
     * 
     * @param book
     *            记忆坐标
     */
    public void removeBookMark(final L1BookMark book) {
        this._bookmarks.remove(book);
    }

    /**
     * 结束重复的魔法状态 <br>
     * 缓速、集体缓速、地面障碍、加速术、强力加速术、一段加速
     */
    public void removeHasteSkillEffect() {
        if (this.hasSkillEffect(SLOW)) {
            this.removeSkillEffect(SLOW);
        }
        if (this.hasSkillEffect(MASS_SLOW)) {
            this.removeSkillEffect(MASS_SLOW);
        }
        if (this.hasSkillEffect(ENTANGLE)) {
            this.removeSkillEffect(ENTANGLE);
        }
        if (this.hasSkillEffect(HASTE)) {
            this.removeSkillEffect(HASTE);
        }
        if (this.hasSkillEffect(GREATER_HASTE)) {
            this.removeSkillEffect(GREATER_HASTE);
        }
        if (this.hasSkillEffect(STATUS_HASTE)) {
            this.removeSkillEffect(STATUS_HASTE);
        }
    }

    /**
     * 删除画面范围外的对象
     */
    private void removeOutOfRangeObjects() {
        for (final L1Object known : this.getKnownObjects()) {
            if (known == null) {
                continue;
            }

            if (Config.PC_RECOGNIZE_RANGE == -1) {
                if (!this.getLocation().isInScreen(known.getLocation())) { // 画面外
                    this.removeKnownObject(known);
                    this.sendPackets(new S_RemoveObject(known));
                }
            } else {
                if (this.getLocation().getTileLineDistance(known.getLocation()) > Config.PC_RECOGNIZE_RANGE) {
                    this.removeKnownObject(known);
                    this.sendPackets(new S_RemoveObject(known));
                }
            }
        }
    }

    /**
     * 删除学习的技能
     * 
     * @param skillid
     *            技能编号
     */
    public void removeSkillMastery(final int skillid) {
        if (this.skillList.contains(skillid)) {
            this.skillList.remove((Object) skillid);
        }
    }

    /**
     * 角色AC状态的初始设定、LVUP,LVDown时呼出
     */
    public void resetBaseAc() {
        final int newAc = CalcStat.calcAc(this.getLevel(), this.getBaseDex());
        this.addAc(newAc - this._baseAc);
        this._baseAc = newAc;
    }

    /**
     * LV伤害奖励设定 LV变动的场合重新再计算
     * 
     * @return
     */
    public void resetBaseDmgup() {
        int newBaseDmgup = 0;
        int newBaseBowDmgup = 0;
        switch (this.getClassId()) {
            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                newBaseDmgup = this.getLevel() / 10;
                newBaseBowDmgup = 0;
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                newBaseDmgup = 0;
                newBaseBowDmgup = this.getLevel() / 10;
                break;
        }
        this.addDmgup(newBaseDmgup - this._baseDmgup);
        this.addBowDmgup(newBaseBowDmgup - this._baseBowDmgup);
        this._baseDmgup = newBaseDmgup;
        this._baseBowDmgup = newBaseBowDmgup;
    }

    /**
     * LV命中奖励设定 LV变动的场合重新再计算
     * 
     * @return
     */
    public void resetBaseHitup() {
        int newBaseHitup = 0;
        int newBaseBowHitup = 0;
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                newBaseHitup = this.getLevel() / 5;
                newBaseBowHitup = this.getLevel() / 5;
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                newBaseHitup = this.getLevel() / 3;
                newBaseBowHitup = this.getLevel() / 3;
                break;
        }
        this.addHitup(newBaseHitup - this._baseHitup);
        this.addBowHitup(newBaseBowHitup - this._baseBowHitup);
        this._baseHitup = newBaseHitup;
        this._baseBowHitup = newBaseBowHitup;
    }

    /**
     * 重新计算角色的MR初始设定、使用技能和LVUP,LVDown时呼出
     */
    public void resetBaseMr() {
        int newMr = 0;
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                newMr = 10;
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                newMr = 25;
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                newMr = 15;
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                newMr = 10;
                break;

            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                newMr = 18;
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                newMr = 20;
                break;
        }
        newMr += CalcStat.calcStatMr(this.getWis()); // 精神对魔防的加成
        newMr += this.getLevel() / 2; // LVの半分だけ追加
        this.addMr(newMr - this._baseMr);
        this._baseMr = newMr;
    }

    /**
     * 重新设定等级与目前的经验值匹配
     */
    public void resetLevel() {
        this.setLevel(ExpTable.getLevelByExp(this._exp));

        if (this._hpRegen != null) {
            this._hpRegen.updateLevel();
        }
    }

    /**
     * 重置原始的物理防御
     */
    public void resetOriginalAc() {
        final int originalDex = this.getOriginalDex();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                if ((originalDex >= 12) && (originalDex <= 14)) {
                    this._originalAc = 1;
                } else if ((originalDex == 15) || (originalDex == 16)) {
                    this._originalAc = 2;
                } else if (originalDex >= 17) {
                    this._originalAc = 3;
                } else {
                    this._originalAc = 0;
                }
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
                if ((originalDex == 13) || (originalDex == 14)) {
                    this._originalAc = 1;
                } else if (originalDex >= 15) {
                    this._originalAc = 3;
                } else {
                    this._originalAc = 0;
                }
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                if ((originalDex >= 15) && (originalDex <= 17)) {
                    this._originalAc = 1;
                } else if (originalDex == 18) {
                    this._originalAc = 2;
                } else {
                    this._originalAc = 0;
                }
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                if (originalDex >= 17) {
                    this._originalAc = 1;
                } else {
                    this._originalAc = 0;
                }
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                if ((originalDex == 8) || (originalDex == 9)) {
                    this._originalAc = 1;
                } else if (originalDex >= 10) {
                    this._originalAc = 2;
                } else {
                    this._originalAc = 0;
                }
                break;

            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                if ((originalDex == 12) || (originalDex == 13)) {
                    this._originalAc = 1;
                } else if (originalDex >= 14) {
                    this._originalAc = 2;
                } else {
                    this._originalAc = 0;
                }
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                if ((originalDex == 11) || (originalDex == 12)) {
                    this._originalAc = 1;
                } else if (originalDex >= 13) {
                    this._originalAc = 2;
                } else {
                    this._originalAc = 0;
                }
                break;
        }

        this.addAc(0 - this._originalAc);
    }

    /**
     * 重置原始远程武器的最大伤害
     */
    public void resetOriginalBowDmgup() {
        final int originalDex = this.getOriginalDex();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                if (originalDex >= 13) {
                    this._originalBowDmgup = 1;
                } else {
                    this._originalBowDmgup = 0;
                }
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                this._originalBowDmgup = 0;
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                if ((originalDex >= 14) && (originalDex <= 16)) {
                    this._originalBowDmgup = 2;
                } else if (originalDex >= 17) {
                    this._originalBowDmgup = 3;
                } else {
                    this._originalBowDmgup = 0;
                }
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                if (originalDex == 18) {
                    this._originalBowDmgup = 2;
                } else {
                    this._originalBowDmgup = 0;
                }
                break;
        }
    }

    /**
     * 重置原始远程武器的最大命中率
     */
    public void resetOriginalBowHitup() {
        final int originalDex = this.getOriginalDex();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                this._originalBowHitup = 0;
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                switch (originalDex) {
                    case 13:
                    case 14:
                    case 15:
                        this._originalBowHitup = 2;
                        break;

                    case 16:
                    case 17:
                    case 18:
                        this._originalBowHitup = 3;
                        break;

                    default:
                        this._originalBowHitup = 0;
                        break;
                }
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                switch (originalDex) {
                    case 17:
                        this._originalBowHitup = 1;
                        break;

                    case 18:
                        this._originalBowHitup = 2;
                        break;

                    default:
                        this._originalBowHitup = 0;
                        break;
                }
                break;

            default:
                break;
        }
    }

    /**
     * 重置原始的 CON 负重减轻
     */
    public void resetOriginalConWeightReduction() {
        final int originalCon = this.getOriginalCon();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                switch (originalCon) {
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        this._originalConWeightReduction = 1;
                        break;

                    default:
                        this._originalConWeightReduction = 0;
                        break;
                }
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
                switch (originalCon) {
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        this._originalConWeightReduction = 1;
                        break;

                    default:
                        this._originalConWeightReduction = 0;
                        break;
                }
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                switch (originalCon) {
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        this._originalConWeightReduction = 1;
                        break;

                    default:
                        this._originalConWeightReduction = 0;
                        break;
                }
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                switch (originalCon) {
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        this._originalConWeightReduction = 1;
                        break;

                    default:
                        this._originalConWeightReduction = 0;
                        break;
                }
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                switch (originalCon) {
                    case 13:
                    case 14:
                        this._originalConWeightReduction = 1;
                        break;

                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        this._originalConWeightReduction = 2;
                        break;

                    default:
                        this._originalConWeightReduction = 0;
                        break;
                }
                break;

            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                this._originalConWeightReduction = 0;
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                switch (originalCon) {
                    case 17:
                        this._originalConWeightReduction = 1;
                        break;

                    case 18:
                        this._originalConWeightReduction = 2;
                        break;

                    default:
                        this._originalConWeightReduction = 0;
                        break;
                }
                break;

            default:
                break;
        }
    }

    /** 重置原始的最大伤害 */
    public void resetOriginalDmgup() {
        final int originalStr = this.getOriginalStr();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                switch (originalStr) {
                    case 15:
                    case 16:
                    case 17:
                        this._originalDmgup = 1;
                        break;

                    case 18:
                        this._originalDmgup = 2;
                        break;

                    default:
                        this._originalDmgup = 0;
                        break;
                }
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
                switch (originalStr) {
                    case 18:
                    case 19:
                        this._originalDmgup = 2;
                        break;

                    case 20:
                        this._originalDmgup = 4;
                        break;

                    default:
                        this._originalDmgup = 0;
                        break;
                }
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                switch (originalStr) {
                    case 12:
                    case 13:
                        this._originalDmgup = 1;
                        break;

                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        this._originalDmgup = 2;
                        break;

                    default:
                        this._originalDmgup = 0;
                        break;
                }
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                switch (originalStr) {
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                        this._originalDmgup = 1;
                        break;

                    case 18:
                        this._originalDmgup = 2;
                        break;

                    default:
                        this._originalDmgup = 0;
                        break;
                }
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                switch (originalStr) {
                    case 10:
                    case 11:
                        this._originalDmgup = 1;
                        break;

                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        this._originalDmgup = 2;
                        break;

                    default:
                        this._originalDmgup = 0;
                        break;
                }
                break;

            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                switch (originalStr) {
                    case 15:
                    case 16:
                    case 17:
                        this._originalDmgup = 1;
                        break;

                    case 18:
                    case 19:
                        this._originalDmgup = 3;
                        break;

                    default:
                        this._originalDmgup = 0;
                        break;
                }
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                switch (originalStr) {
                    case 13:
                    case 14:
                        this._originalDmgup = 1;
                        break;

                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                        this._originalDmgup = 2;
                        break;

                    default:
                        this._originalDmgup = 0;
                        break;
                }
                break;

            default:
                break;
        }
    }

    /**
     * 重置原始的回避率
     */
    public void resetOriginalEr() {

        // 取得原始的敏捷值
        final int originalDex = this.getOriginalDex();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                switch (originalDex) {
                    case 14:
                    case 15:
                        this._originalEr = 1; // 原始的敏捷值对回避率的影响
                        break;

                    case 16:
                    case 17:
                        this._originalEr = 2;
                        break;

                    case 18:
                        this._originalEr = 3;
                        break;

                    default:
                        this._originalEr = 0;
                        break;
                }
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
                switch (originalDex) {
                    case 14:
                    case 15:
                        this._originalEr = 1; // 原始的敏捷值对回避率的影响
                        break;

                    case 16:
                        this._originalEr = 3; // 原始的敏捷值对回避率的影响
                        break;

                    default:
                        this._originalEr = 0;
                        break;
                }
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                this._originalEr = 0;
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                switch (originalDex) {
                    case 16:
                    case 17:
                    case 18:
                        this._originalEr = 2;
                        break;

                    default:
                        this._originalEr = 0;
                        break;
                }
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                switch (originalDex) {
                    case 9:
                    case 10:
                        this._originalEr = 1;
                        break;

                    case 11:
                        this._originalEr = 2;
                        break;

                    default:
                        this._originalEr = 0;
                        break;
                }
                break;

            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                switch (originalDex) {
                    case 13:
                    case 14:
                        this._originalEr = 1;
                        break;

                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        this._originalEr = 2;
                        break;

                    default:
                        this._originalEr = 0;
                        break;
                }
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                switch (originalDex) {
                    case 12:
                    case 13:
                        this._originalEr = 1;
                        break;

                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        this._originalEr = 2;
                        break;

                    default:
                        this._originalEr = 0;
                        break;
                }
                break;

            default:
                break;
        }
    }

    /**
     * 重置原始的最大命中率
     */
    public void resetOriginalHitup() {
        final int originalStr = this.getOriginalStr();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                if ((originalStr >= 16) && (originalStr <= 18)) {
                    this._originalHitup = 1;
                } else if (originalStr >= 19) {
                    this._originalHitup = 2;
                } else {
                    this._originalHitup = 0;
                }
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
                if ((originalStr == 17) || (originalStr == 18)) {
                    this._originalHitup = 2;
                } else if (originalStr >= 19) {
                    this._originalHitup = 4;
                } else {
                    this._originalHitup = 0;
                }
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                if ((originalStr == 13) || (originalStr == 14)) {
                    this._originalHitup = 1;
                } else if (originalStr >= 15) {
                    this._originalHitup = 2;
                } else {
                    this._originalHitup = 0;
                }
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                if ((originalStr >= 15) && (originalStr <= 17)) {
                    this._originalHitup = 1;
                } else if (originalStr == 18) {
                    this._originalHitup = 2;
                } else {
                    this._originalHitup = 0;
                }
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                if ((originalStr == 11) || (originalStr == 12)) {
                    this._originalHitup = 1;
                } else if (originalStr >= 13) {
                    this._originalHitup = 2;
                } else {
                    this._originalHitup = 0;
                }
                break;

            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                if ((originalStr >= 14) && (originalStr <= 16)) {
                    this._originalHitup = 1;
                } else if (originalStr >= 17) {
                    this._originalHitup = 3;
                } else {
                    this._originalHitup = 0;
                }
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                if ((originalStr == 12) || (originalStr == 13)) {
                    this._originalHitup = 1;
                } else if ((originalStr == 14) || (originalStr == 15)) {
                    this._originalHitup = 2;
                } else if (originalStr == 16) {
                    this._originalHitup = 3;
                } else if (originalStr >= 17) {
                    this._originalHitup = 4;
                } else {
                    this._originalHitup = 0;
                }
                break;
        }
    }

    /**
     * 重置原始的回血
     */
    public void resetOriginalHpr() {
        final int originalCon = this.getOriginalCon();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                if ((originalCon == 13) || (originalCon == 14)) {
                    this._originalHpr = 1;
                } else if ((originalCon == 15) || (originalCon == 16)) {
                    this._originalHpr = 2;
                } else if (originalCon == 17) {
                    this._originalHpr = 3;
                } else if (originalCon == 18) {
                    this._originalHpr = 4;
                } else {
                    this._originalHpr = 0;
                }
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
                if ((originalCon == 16) || (originalCon == 17)) {
                    this._originalHpr = 2;
                } else if (originalCon == 18) {
                    this._originalHpr = 4;
                } else {
                    this._originalHpr = 0;
                }
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                if ((originalCon == 14) || (originalCon == 15)) {
                    this._originalHpr = 1;
                } else if (originalCon == 16) {
                    this._originalHpr = 2;
                } else if (originalCon >= 17) {
                    this._originalHpr = 3;
                } else {
                    this._originalHpr = 0;
                }
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                if ((originalCon == 11) || (originalCon == 12)) {
                    this._originalHpr = 1;
                } else if (originalCon >= 13) {
                    this._originalHpr = 2;
                } else {
                    this._originalHpr = 0;
                }
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                if (originalCon == 17) {
                    this._originalHpr = 1;
                } else if (originalCon == 18) {
                    this._originalHpr = 2;
                } else {
                    this._originalHpr = 0;
                }
                break;

            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                if ((originalCon == 16) || (originalCon == 17)) {
                    this._originalHpr = 1;
                } else if (originalCon == 18) {
                    this._originalHpr = 3;
                } else {
                    this._originalHpr = 0;
                }
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                if ((originalCon == 14) || (originalCon == 15)) {
                    this._originalHpr = 1;
                } else if (originalCon >= 16) {
                    this._originalHpr = 2;
                } else {
                    this._originalHpr = 0;
                }
                break;
        }
    }

    /**
     * 设定时的初始点数和计算现在再次从初始状态，重新分配时调用
     */
    /**
     * 重置原始的最大 HP
     */
    public void resetOriginalHpup() {
        final int originalCon = this.getOriginalCon();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                if ((originalCon == 12) || (originalCon == 13)) {
                    this._originalHpup = 1;
                } else if ((originalCon == 14) || (originalCon == 15)) {
                    this._originalHpup = 2;
                } else if (originalCon >= 16) {
                    this._originalHpup = 3;
                } else {
                    this._originalHpup = 0;
                }
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
                if ((originalCon == 15) || (originalCon == 16)) {
                    this._originalHpup = 1;
                } else if (originalCon >= 17) {
                    this._originalHpup = 3;
                } else {
                    this._originalHpup = 0;
                }
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                if ((originalCon >= 13) && (originalCon <= 17)) {
                    this._originalHpup = 1;
                } else if (originalCon == 18) {
                    this._originalHpup = 2;
                } else {
                    this._originalHpup = 0;
                }
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                if ((originalCon == 10) || (originalCon == 11)) {
                    this._originalHpup = 1;
                } else if (originalCon >= 12) {
                    this._originalHpup = 2;
                } else {
                    this._originalHpup = 0;
                }
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                if ((originalCon == 14) || (originalCon == 15)) {
                    this._originalHpup = 1;
                } else if (originalCon >= 16) {
                    this._originalHpup = 2;
                } else {
                    this._originalHpup = 0;
                }
                break;

            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                if ((originalCon == 15) || (originalCon == 16)) {
                    this._originalHpup = 1;
                } else if (originalCon >= 17) {
                    this._originalHpup = 3;
                } else {
                    this._originalHpup = 0;
                }
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                if ((originalCon == 13) || (originalCon == 14)) {
                    this._originalHpup = 1;
                } else if (originalCon >= 15) {
                    this._originalHpup = 2;
                } else {
                    this._originalHpup = 0;
                }
                break;
        }
    }

    /**
     * 重置原始的魔力减免
     */
    public void resetOriginalMagicConsumeReduction() {
        final int originalInt = this.getOriginalInt();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                if ((originalInt == 11) || (originalInt == 12)) {
                    this._originalMagicConsumeReduction = 1;
                } else if (originalInt >= 13) {
                    this._originalMagicConsumeReduction = 2;
                } else {
                    this._originalMagicConsumeReduction = 0;
                }
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
                if ((originalInt == 9) || (originalInt == 10)) {
                    this._originalMagicConsumeReduction = 1;
                } else if (originalInt >= 11) {
                    this._originalMagicConsumeReduction = 2;
                } else {
                    this._originalMagicConsumeReduction = 0;
                }
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                this._originalMagicConsumeReduction = 0;
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                if ((originalInt == 13) || (originalInt == 14)) {
                    this._originalMagicConsumeReduction = 1;
                } else if (originalInt >= 15) {
                    this._originalMagicConsumeReduction = 2;
                } else {
                    this._originalMagicConsumeReduction = 0;
                }
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                if (originalInt == 14) {
                    this._originalMagicConsumeReduction = 1;
                } else if (originalInt >= 15) {
                    this._originalMagicConsumeReduction = 2;
                } else {
                    this._originalMagicConsumeReduction = 0;
                }
                break;
        }
    }

    /**
     * 重置原始的魔法攻击
     */
    public void resetOriginalMagicCritical() {
        final int originalInt = this.getOriginalInt();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                this._originalMagicCritical = 0;
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                if ((originalInt == 14) || (originalInt == 15)) {
                    this._originalMagicCritical = 2;
                } else if (originalInt >= 16) {
                    this._originalMagicCritical = 4;
                } else {
                    this._originalMagicCritical = 0;
                }
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                if (originalInt == 15) {
                    this._originalMagicCritical = 2;
                } else if (originalInt == 16) {
                    this._originalMagicCritical = 4;
                } else if (originalInt == 17) {
                    this._originalMagicCritical = 6;
                } else if (originalInt == 18) {
                    this._originalMagicCritical = 8;
                } else {
                    this._originalMagicCritical = 0;
                }
                break;
        }
    }

    /**
     * 重置原始的魔法伤害
     */
    public void resetOriginalMagicDamage() {
        final int originalInt = this.getOriginalInt();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                this._originalMagicDamage = 0;
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
                this._originalMagicDamage = 0;
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                this._originalMagicDamage = 0;
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                this._originalMagicDamage = 0;
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                if (originalInt >= 13) {
                    this._originalMagicDamage = 1;
                } else {
                    this._originalMagicDamage = 0;
                }
                break;

            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                if ((originalInt == 13) || (originalInt == 14)) {
                    this._originalMagicDamage = 1;
                } else if ((originalInt == 15) || (originalInt == 16)) {
                    this._originalMagicDamage = 2;
                } else if (originalInt == 17) {
                    this._originalMagicDamage = 3;
                } else {
                    this._originalMagicDamage = 0;
                }
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                if (originalInt == 16) {
                    this._originalMagicDamage = 1;
                } else if (originalInt == 17) {
                    this._originalMagicDamage = 2;
                } else {
                    this._originalMagicDamage = 0;
                }
                break;
        }
    }

    /**
     * 重置原始的魔法命中率
     */
    public void resetOriginalMagicHit() {
        final int originalInt = this.getOriginalInt();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                if ((originalInt == 12) || (originalInt == 13)) {
                    this._originalMagicHit = 1;
                } else if (originalInt >= 14) {
                    this._originalMagicHit = 2;
                } else {
                    this._originalMagicHit = 0;
                }
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
                if ((originalInt == 10) || (originalInt == 11)) {
                    this._originalMagicHit = 1;
                } else if (originalInt == 12) {
                    this._originalMagicHit = 2;
                } else {
                    this._originalMagicHit = 0;
                }
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                if ((originalInt == 13) || (originalInt == 14)) {
                    this._originalMagicHit = 1;
                } else if (originalInt >= 15) {
                    this._originalMagicHit = 2;
                } else {
                    this._originalMagicHit = 0;
                }
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                if ((originalInt == 12) || (originalInt == 13)) {
                    this._originalMagicHit = 1;
                } else if (originalInt >= 14) {
                    this._originalMagicHit = 2;
                } else {
                    this._originalMagicHit = 0;
                }
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                if (originalInt >= 14) {
                    this._originalMagicHit = 1;
                } else {
                    this._originalMagicHit = 0;
                }
                break;

            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                if ((originalInt == 12) || (originalInt == 13)) {
                    this._originalMagicHit = 2;
                } else if ((originalInt == 14) || (originalInt == 15)) {
                    this._originalMagicHit = 3;
                } else if (originalInt >= 16) {
                    this._originalMagicHit = 4;
                } else {
                    this._originalMagicHit = 0;
                }
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                if (originalInt >= 13) {
                    this._originalMagicHit = 1;
                } else {
                    this._originalMagicHit = 0;
                }
                break;
        }
    }

    /**
     * 重置原始的回魔
     */
    public void resetOriginalMpr() {
        final int originalWis = this.getOriginalWis();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                if ((originalWis == 13) || (originalWis == 14)) {
                    this._originalMpr = 1;
                } else if (originalWis >= 15) {
                    this._originalMpr = 2;
                } else {
                    this._originalMpr = 0;
                }
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
                if ((originalWis == 11) || (originalWis == 12)) {
                    this._originalMpr = 1;
                } else if (originalWis == 13) {
                    this._originalMpr = 2;
                } else {
                    this._originalMpr = 0;
                }
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                if ((originalWis >= 15) && (originalWis <= 17)) {
                    this._originalMpr = 1;
                } else if (originalWis == 18) {
                    this._originalMpr = 2;
                } else {
                    this._originalMpr = 0;
                }
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                if (originalWis >= 13) {
                    this._originalMpr = 1;
                } else {
                    this._originalMpr = 0;
                }
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                if ((originalWis == 14) || (originalWis == 15)) {
                    this._originalMpr = 1;
                } else if ((originalWis == 16) || (originalWis == 17)) {
                    this._originalMpr = 2;
                } else if (originalWis == 18) {
                    this._originalMpr = 3;
                } else {
                    this._originalMpr = 0;
                }
                break;

            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                if ((originalWis == 15) || (originalWis == 16)) {
                    this._originalMpr = 1;
                } else if (originalWis >= 17) {
                    this._originalMpr = 2;
                } else {
                    this._originalMpr = 0;
                }
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                if ((originalWis >= 14) && (originalWis <= 16)) {
                    this._originalMpr = 1;
                } else if (originalWis >= 17) {
                    this._originalMpr = 2;
                } else {
                    this._originalMpr = 0;
                }
                break;
        }
    }

    /**
     * 重置原始的最大 MP
     */
    public void resetOriginalMpup() {
        final int originalWis = this.getOriginalWis();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                if (originalWis >= 16) {
                    this._originalMpup = 1;
                } else {
                    this._originalMpup = 0;
                }
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
                this._originalMpup = 0;
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                if ((originalWis >= 14) && (originalWis <= 16)) {
                    this._originalMpup = 1;
                } else if (originalWis >= 17) {
                    this._originalMpup = 2;
                } else {
                    this._originalMpup = 0;
                }
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                if (originalWis >= 12) {
                    this._originalMpup = 1;
                } else {
                    this._originalMpup = 0;
                }
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                if ((originalWis >= 13) && (originalWis <= 16)) {
                    this._originalMpup = 1;
                } else if (originalWis >= 17) {
                    this._originalMpup = 2;
                } else {
                    this._originalMpup = 0;
                }
                break;

            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                if ((originalWis >= 13) && (originalWis <= 15)) {
                    this._originalMpup = 1;
                } else if (originalWis >= 16) {
                    this._originalMpup = 2;
                } else {
                    this._originalMpup = 0;
                }
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                if ((originalWis >= 13) && (originalWis <= 15)) {
                    this._originalMpup = 1;
                } else if (originalWis >= 16) {
                    this._originalMpup = 2;
                } else {
                    this._originalMpup = 0;
                }
                break;
        }
    }

    /**
     * 重置原始的魔防
     */
    public void resetOriginalMr() {
        final int originalWis = this.getOriginalWis();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                if ((originalWis == 12) || (originalWis == 13)) {
                    this._originalMr = 1;
                } else if (originalWis >= 14) {
                    this._originalMr = 2;
                } else {
                    this._originalMr = 0;
                }
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
                if ((originalWis == 10) || (originalWis == 11)) {
                    this._originalMr = 1;
                } else if (originalWis >= 12) {
                    this._originalMr = 2;
                } else {
                    this._originalMr = 0;
                }
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                if ((originalWis >= 13) && (originalWis <= 15)) {
                    this._originalMr = 1;
                } else if (originalWis >= 16) {
                    this._originalMr = 2;
                } else {
                    this._originalMr = 0;
                }
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                if ((originalWis >= 11) && (originalWis <= 13)) {
                    this._originalMr = 1;
                } else if (originalWis == 14) {
                    this._originalMr = 2;
                } else if (originalWis == 15) {
                    this._originalMr = 3;
                } else if (originalWis >= 16) {
                    this._originalMr = 4;
                } else {
                    this._originalMr = 0;
                }
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                if (originalWis >= 15) {
                    this._originalMr = 1;
                } else {
                    this._originalMr = 0;
                }
                break;

            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                if (originalWis >= 14) {
                    this._originalMr = 2;
                } else {
                    this._originalMr = 0;
                }
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                if ((originalWis >= 15) && (originalWis <= 17)) {
                    this._originalMr = 2;
                } else if (originalWis == 18) {
                    this._originalMr = 4;
                } else {
                    this._originalMr = 0;
                }
                break;
        }

        this.addMr(this._originalMr);
    }

    /**
     * 重置原始的 STR 负重减轻
     */
    public void resetOriginalStrWeightReduction() {
        final int originalStr = this.getOriginalStr();
        switch (this.getClassId()) {
            case CLASSID_PRINCE: // 王族 (男)
            case CLASSID_PRINCESS: // 王族 (女)
                switch (originalStr) {
                    case 14:
                    case 15:
                    case 16:
                        this._originalStrWeightReduction = 1;
                        break;

                    case 17:
                    case 18:
                    case 19:
                        this._originalStrWeightReduction = 2;
                        break;

                    case 20:
                        this._originalStrWeightReduction = 3;
                        break;

                    default:
                        this._originalStrWeightReduction = 0;
                        break;
                }
                break;

            case CLASSID_KNIGHT_MALE: // 骑士 (男)
            case CLASSID_KNIGHT_FEMALE: // 骑士 (女)
                this._originalStrWeightReduction = 0;
                break;

            case CLASSID_ELF_MALE: // 精灵 (男)
            case CLASSID_ELF_FEMALE: // 精灵 (女)
                switch (originalStr) {
                    case 16:
                    case 17:
                    case 18:
                        this._originalStrWeightReduction = 2;
                        break;

                    default:
                        this._originalStrWeightReduction = 0;
                        break;
                }
                break;

            case CLASSID_DARK_ELF_MALE: // 黑暗精灵 (男)
            case CLASSID_DARK_ELF_FEMALE: // 黑暗精灵 (女)
                switch (originalStr) {
                    case 13:
                    case 14:
                    case 15:
                        this._originalStrWeightReduction = 2;
                        break;

                    case 16:
                    case 17:
                    case 18:
                        this._originalStrWeightReduction = 3;
                        break;

                    default:
                        this._originalStrWeightReduction = 0;
                        break;
                }
                break;

            case CLASSID_WIZARD_MALE: // 魔法师 (男)
            case CLASSID_WIZARD_FEMALE: // 魔法师 (女)
                if (originalStr >= 9) {
                    this._originalStrWeightReduction = 1;
                } else {
                    this._originalStrWeightReduction = 0;
                }
                break;

            case CLASSID_DRAGON_KNIGHT_MALE: // 龙骑士 (男)
            case CLASSID_DRAGON_KNIGHT_FEMALE: // 龙骑士 (女)
                if (originalStr >= 16) {
                    this._originalStrWeightReduction = 1;
                } else {
                    this._originalStrWeightReduction = 0;
                }
                break;

            case CLASSID_ILLUSIONIST_MALE: // 幻术师 (男)
            case CLASSID_ILLUSIONIST_FEMALE: // 幻术师 (女)
                if (originalStr == 18) {
                    this._originalStrWeightReduction = 1;
                } else {
                    this._originalStrWeightReduction = 0;
                }
                break;

            default:
                break;
        }
    }

    /**
     * 恢复经验值
     */
    public void resExp() {
        final int oldLevel = this.getLevel();
        final long needExp = ExpTable.getNeedExpNextLevel(oldLevel);
        long exp = 0;
        switch (oldLevel) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
                exp = (long) (needExp * 0.05);
                break;

            case 45:
                exp = (long) (needExp * 0.045);
                break;

            case 46:
                exp = (long) (needExp * 0.04);
                break;

            case 47:
                exp = (long) (needExp * 0.035);
                break;

            case 48:
                exp = (long) (needExp * 0.03);
                break;

            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
                exp = (long) (needExp * 0.025);
                break;

            default:
                exp = (long) (needExp * 0.0025);
                break;
        }

        if (exp == 0) {
            return;
        }
        this.addExp(exp);
    }

    /**
     * 将玩家资料储存到资料库中。
     * 
     * @throws Exception
     */
    public void save() throws Exception {
        if (this.isGhost()) {
            return;
        }
        if (this.isInCharReset()) {
            return;
        }

        CharacterTable.getInstance().storeCharacter(this);
    }

    /**
     * 将角色身上道具的状态存入资料库。
     */
    public void saveInventory() {
        for (final L1ItemInstance item : this.getInventory().getItems()) {
            this.getInventory().saveItem(item, item.getRecordingColumns());
            this.getInventory().saveEnchantAccessory(item,
                    item.getRecordingColumnsEnchantAccessory());
        }
    }

    /**
     * 发送单体封包.
     * 
     * @param packet
     *            封包
     */
    public final void sendPackets(final ServerBasePacket packet) {
        if (this._out == null) {
            return;
        }

        try {
            this._out.sendPacket(packet);
        } catch (final Exception e) {
        }
    }

    /**
     * 发送视觉效果
     */
    private void sendVisualEffect() {
        int poisonId = 0;
        if (this.getPoison() != null) { // 毒状态
            poisonId = this.getPoison().getEffectId();
        }
        if (this.getParalysis() != null) { // 麻痹状态
            // 麻痹エフェクトを优先して送りたい为、poisonIdを上书き。
            poisonId = this.getParalysis().getEffectId();
        }
        if (poisonId != 0) { // このifはいらないかもしれない
            this.sendPackets(new S_Poison(this.getId(), poisonId));
            this.broadcastPacket(new S_Poison(this.getId(), poisonId));
        }
    }

    /**
     * 在登录时发送的视觉效果
     */
    public void sendVisualEffectAtLogin() {

        if (this.getClanid() != 0) { // 有血盟
            final L1Clan clan = L1World.getInstance().getClan(
                    this.getClanname());
            if (clan != null) {
                if (this.isCrown() && (this.getId() == clan.getLeaderId()) && // 有城血盟王族发送王冠
                        (clan.getCastleId() != 0)) {
                    this.sendPackets(new S_CastleMaster(clan.getCastleId(),
                            this.getId()));
                }
            }
        }

        this.sendVisualEffect();
    }

    /**
     * 发送瞬移视觉效果
     */
    public void sendVisualEffectAtTeleport() {
        if (this.isDrink()) { // liquor醉酒状态
            this.sendPackets(new S_Liquor(this.getId(), 1));
        }

        this.sendVisualEffect();
    }

    /**
     * 设定PK次数
     * 
     * @param i
     */
    public void set_PKcount(final int i) {
        this._PKcount = i;
    }

    /**
     * 设定性别
     * 
     * @param i
     */
    public void set_sex(final int i) {
        this._sex = (byte) i;
    }

    /**
     * 设定账号等级
     * 
     * @param i
     */
    public void setAccessLevel(final short i) {
        this._accessLevel = i;
    }

    /**
     * 设定账号名称
     * 
     * @param s
     */
    public void setAccountName(final String s) {
        this._accountName = s;
    }

    /**
     * 设定灵魂升华增加的HP
     * 
     * @param i
     */
    public void setAdvenHp(final int i) {
        this._advenHp = i;
    }

    /**
     * 设定灵魂升华增加的MP
     * 
     * @param i
     */
    public void setAdvenMp(final int i) {
        this._advenMp = i;
    }

    /**
     * 设定觉醒技能ID
     * 
     * @param i
     */
    public void setAwakeSkillId(final int i) {
        this._awakeSkillId = i;
    }

    /**
     * 设定角色封锁
     * 
     * @param flag
     */
    public void setBanned(final boolean flag) {
        this._banned = flag;
    }

    /**
     * 设置暂存传送编号 (幽灵状态)
     * 
     * @param i
     */
    public void setBeginGhost(final byte i) {
        this._beginGhost = i;
    }

    /**
     * 设置暂存乱数传送状态 (幽灵)
     * 
     * @param flag
     */
    public void setBeginGhostFlag(final boolean flag) {
        this._beginGhostFlag = flag;
    }

    /** 
	 * 
	 */
    public void setBirthday() {
        this._birthday = new Timestamp(System.currentTimeMillis());
    }

    /**
     * 设定角色生日
     * 
     * @param time
     */
    public void setBirthday(final Timestamp time) {
        this._birthday = time;
    }

    /**
     * 设定奖励点分配状况
     * 
     * @param i
     */
    public void setBonusStats(final int i) {
        this._bonusStats = i;
    }

    /**
     * 设定呼叫血盟名称
     * 
     * @param i
     */
    public void setCallClanHeading(final int i) {
        this._callClanHeading = i;
    }

    /**
     * 设定呼叫血盟ID
     * 
     * @param i
     */
    public void setCallClanId(final int i) {
        this._callClanId = i;
    }

    /**
     * 设定可以密语
     * 
     * @param flag
     */
    public void setCanWhisper(final boolean flag) {
        this._isCanWhisper = flag;
    }

    /**
     * 设定聊天组队
     * 
     * @param cp
     */
    public void setChatParty(final L1ChatParty cp) {
        this._chatParty = cp;
    }

    /**
     * 设定血盟 ID
     * 
     * @param i
     */
    public void setClanid(final int i) {
        this._clanid = i;
    }

    /**
     * 设定血盟名称
     * 
     * @param s
     */
    public void setClanname(final String s) {
        this.clanname = s;
    }

    /**
     * 设定血盟内的阶级(联盟君主、守护骑士、一般、见习)
     * 
     * @param i
     */
    public void setClanRank(final int i) {
        this._clanRank = i;
    }

    /**
     * 设定角色的ClassId
     * 
     * @param i
     */
    public void setClassId(final int i) {
        this._classId = i;
        this._classFeature = L1ClassFeature.newClassFeature(i);
    }

    /**
     * 设定贡献度
     * 
     * @param i
     */
    public void setContribution(final int i) {
        this._contribution = i;
    }

    /**
     * 设定烹饪ID
     * 
     * @param i
     */
    public void setCookingId(final int i) {
        this._cookingId = i;
    }

    /**
     * 设置生存的呐喊时间.
     * 
     * @param time
     *            时间
     */
    public void setCryTime(final int time) {
        this._CryTime = time;
    }

    // 设置新HP
    @Override
    public void setCurrentHp(final int i) {
        if (this.getCurrentHp() == i) {
            return;
        }
        int currentHp = i;
        if (currentHp >= this.getMaxHp()) {
            currentHp = this.getMaxHp();
        }
        this.setCurrentHpDirect(currentHp);
        this.sendPackets(new S_HPUpdate(currentHp, this.getMaxHp()));
        if (this.isInParty()) { // 组队中
            this.getParty().updateMiniHP(this);
        }
    }

    // 设置新MP
    @Override
    public void setCurrentMp(final int i) {
        if (this.getCurrentMp() == i) {
            return;
        }
        int currentMp = i;
        if ((currentMp >= this.getMaxMp()) || this.isGm()) {
            currentMp = this.getMaxMp();
        }
        this.setCurrentMpDirect(currentMp);
        this.sendPackets(new S_MPUpdate(currentMp, this.getMaxMp()));
    }

    /**
     * 设定当前武器
     * 
     * @param i
     */
    public void setCurrentWeapon(final int i) {
        this._currentWeapon = i;
    }

    /**
     * 设定角色删除时间
     * 
     * @param time
     */
    public void setDeleteTime(final Timestamp time) {
        this._deleteTime = time;
    }

    /**
     * 设定点心ID
     * 
     * @param i
     */
    public void setDessertId(final int i) {
        this._dessertId = i;
    }

    /**
     * 设定醉酒状态
     * 
     * @param flag
     */
    public void setDrink(final boolean flag) {
        this._isDrink = flag;
    }

    /**
     * 设定精灵属性
     * 
     * @param i
     */
    public void setElfAttr(final int i) {
        this._elfAttr = i;
    }

    /**
     * 设定万能药使用状况
     * 
     * @param i
     */
    public void setElixirStats(final int i) {
        this._elixirStats = i;
    }

    // 设置角色的经验值
    @Override
    public synchronized void setExp(final long i) {
        this._exp = i;
    }

    /**
     * 设定恢复EXP
     * 
     * @param i
     */
    public void setExpRes(final int i) {
        this._expRes = i;
    }

    /**
     * 设定战斗ID
     * 
     * @param i
     */
    public void setFightId(final int i) {
        this._fightId = i;
    }

    /**
     * 设定钓鱼
     * 
     * @param flag
     */
    public void setFishing(final boolean flag) {
        this._isFishing = flag;
    }

    /**
     * 设定准备好钓鱼
     * 
     * @param flag
     */
    public void setFishingReady(final boolean flag) {
        this._isFishingReady = flag;
    }

    /**
     * 设定钓鱼时间
     * 
     * @param i
     */
    public void setFishingTime(final long i) {
        this._fishingTime = i;
    }

    /**
     * 设定钓鱼坐标X
     * 
     * @param i
     */
    public void setFishX(final int i) {
        this._fishX = i;
    }

    /**
     * 设定钓鱼坐标Y
     * 
     * @param i
     */
    public void setFishY(final int i) {
        this._fishY = i;
    }

    /**
     * 设定使用屠宰者判断
     * 
     * @param FoeSlayer
     */
    public void setFoeSlayer(final boolean FoeSlayer) {
        this._FoeSlayer = FoeSlayer;
    }

    /**
     * 设定幽灵状态
     * 
     * @param flag
     */
    private void setGhost(final boolean flag) {
        this._ghost = flag;
    }

    /**
     * 设定幽灵状态可以与NPC对话
     * 
     * @param flag
     */
    private void setGhostCanTalk(final boolean flag) {
        this._ghostCanTalk = flag;
    }

    /**
     * 设定GM
     * 
     * @param flag
     */
    public void setGm(final boolean flag) {
        this._gm = flag;
    }

    /**
     * 设定GM隐身
     * 
     * @param flag
     */
    public void setGmInvis(final boolean flag) {
        this._gmInvis = flag;
    }

    /**
     * @param flag
     */
    public void setGres(final boolean flag) {
        this._isGres = flag;
    }

    /**
     * 设定G-RES有效
     * 
     * @param valid
     */
    void setGresValid(final boolean valid) {
        this._gresValid = valid;
    }

    /**
     * 设定地狱停留时间 (秒)
     * 
     * @param i
     */
    public void setHellTime(final int i) {
        this._hellTime = i;
    }

    /**
     * 设定过去最高等级
     * 
     * @param i
     */
    public void setHighLevel(final int i) {
        this._highLevel = i;
    }

    /**
     * 设定所住村镇ID
     * 
     * @param i
     */
    public void setHomeTownId(final int i) {
        this._homeTownId = i;
    }

    /**
     * 设定角色重新开始
     * 
     * @param flag
     */
    public void setInCharReset(final boolean flag) {
        this._isInCharReset = flag;
    }

    /**
     * @param bool
     */
    public void setInOrderList(final boolean bool) {
        this._order_list = bool;
    }

    // 设置友好度
    @Override
    public void setKarma(final int i) {
        this._karma.set(i);
    }

    /**
     * 设定宠物竞速圈数
     * 
     * @param i
     */
    public void setLap(final int i) {
        this._lap = i;
    }

    /**
     * 设定检查宠物竞速圈数
     * 
     * @param i
     */
    public void setLapCheck(final int i) {
        this._lapCheck = i;
    }

    /**
     * 设定角色的最终PK时间为现在的时刻。
     */
    public void setLastPk() {
        this._lastPk = new Timestamp(System.currentTimeMillis());
    }

    /**
     * 设定玩家的最后PK时间。
     * 
     * @param time
     *            最终PK时间（Timestamp型） 解除する场合はnullを代入
     */
    public void setLastPk(final Timestamp time) {
        this._lastPk = time;
    }

    /**
     * 设置精灵PK值
     */
    public void setLastPkForElf() {
        this._lastPkForElf = new Timestamp(System.currentTimeMillis());
    }

    /**
     * 设置精灵PK值
     * 
     * @param time
     */
    public void setLastPkForElf(final Timestamp time) {
        this._lastPkForElf = time;
    }

    /**
     * 设定管理者
     * 
     * @param flag
     */
    public void setMonitor(final boolean flag) {
        this._monitor = flag;
    }

    /**
     * 设定网络连接状态
     * 
     * @param clientthread
     */
    public void setNetConnection(final ClientThread clientthread) {
        this._netConnection = clientthread;
    }

    /**
     * 设定在线状态
     * 
     * @param i
     */
    public void setOnlineStatus(final int i) {
        this._onlineStatus = i;
    }

    /**
     * 设定原始的魅力值
     * 
     * @param i
     */
    public void setOriginalCha(final int i) {
        this._originalCha = i;
    }

    /**
     * 设定原始的体质值
     * 
     * @param i
     */
    public void setOriginalCon(final int i) {
        this._originalCon = i;
    }

    /**
     * 设定原始的敏捷值
     * 
     * @param i
     */
    public void setOriginalDex(final int i) {
        this._originalDex = i;
    }

    /**
     * 设定原始的智力值
     * 
     * @param i
     */
    public void setOriginalInt(final int i) {
        this._originalInt = i;
    }

    /**
     * 设定原始的力量值
     * 
     * @param i
     */
    public void setOriginalStr(final int i) {
        this._originalStr = i;
    }

    /**
     * 设定原始的精神值
     * 
     * @param i
     */
    public void setOriginalWis(final int i) {
        this._originalWis = i;
    }

    /**
     * 设定封包加密管理
     * 
     * @param out
     */
    public void setPacketOutput(final PacketOutput out) {
        this._out = out;
    }

    /**
     * 设定结婚伴侣ID
     * 
     * @param i
     */
    public void setPartnerId(final int i) {
        this._partnerId = i;
    }

    /**
     * 设定出售物品种类数量
     * 
     * @param i
     */
    public void setPartnersPrivateShopItemCount(final int i) {
        this._partnersPrivateShopItemCount = i;
    }

    /**
     * 设定组队
     * 
     * @param p
     */
    public void setParty(final L1Party p) {
        this._party = p;
    }

    /**
     * 设定组队ID
     * 
     * @param partyID
     */
    public void setPartyID(final int partyID) {
        this._partyID = partyID;
    }

    /**
     * 设定组队类型
     * 
     * @param type
     */
    public void setPartyType(final int type) {
        this._partyType = type;
    }

    /**
     * 设定村庄福利金
     * 
     * @param i
     */
    public void setPay(final int i) {
        this._pay = i;
    }

    /**
     * 设定 宠物/召唤兽 攻击目标
     * 
     * @param target
     */
    public void setPetTarget(final L1Character target) {
        final Object[] petList = this.getPetList().values().toArray();
        for (final Object pet : petList) {
            if (pet instanceof L1PetInstance) {
                final L1PetInstance pets = (L1PetInstance) pet;
                pets.setMasterTarget(target);
            } else if (pet instanceof L1SummonInstance) {
                final L1SummonInstance summon = (L1SummonInstance) pet;
                summon.setMasterTarget(target);
            }
        }
    }

    /**
     * 设定粉名
     * 
     * @param flag
     */
    public void setPinkName(final boolean flag) {
        this._isPinkName = flag;
    }

    /**
     * 设定PK次数 (精灵用)
     * 
     * @param i
     */
    public void setPkCountForElf(final int i) {
        this._PkCountForElf = i;
    }

    // 设置角色的中毒效果
    @Override
    public void setPoisonEffect(final int effectId) {
        this.sendPackets(new S_Poison(this.getId(), effectId));

        if (!this.isGmInvis() && !this.isGhost() && !this.isInvisble()) {
            this.broadcastPacket(new S_Poison(this.getId(), effectId));
        }
        if (this.isGmInvis() || this.isGhost()) {
        } else if (this.isInvisble()) {
            this.broadcastPacketForFindInvis(new S_Poison(this.getId(),
                    effectId), true);
        } else {
            this.broadcastPacket(new S_Poison(this.getId(), effectId));
        }
    }

    /**
     * 设定商店模式
     * 
     * @param flag
     */
    public void setPrivateShop(final boolean flag) {
        this._isPrivateShop = flag;
    }

    /**
     * @param state
     */
    public void setRegenState(final int state) {
        this._mpRegen.setState(state);
        this._hpRegen.setState(state);
    }

    /**
     * 设定准备解除幽灵状态
     * 
     * @param flag
     */
    private void setReserveGhost(final boolean flag) {
        this._isReserveGhost = flag;
    }

    /**
     * 设置奇岩地监时间.
     * 
     * @param time
     *            时间
     */
    public void setRocksPrisonTime(final int time) {
        this._rocksPrisonTime = time;
    }

    /**
     * 设定变身
     * 
     * @param isShapeChange
     */
    public void setShapeChange(final boolean isShapeChange) {
        this._isShapeChange = isShapeChange;
    }

    /**
     * 设定商店聊天
     * 
     * @param chat
     */
    public void setShopChat(final byte[] chat) {
        this._shopChat = chat;
    }

    /**
     * 设定显示血盟聊天
     * 
     * @param flag
     */
    public void setShowClanChat(final boolean flag) {
        this._isShowClanChat = flag;
    }

    /**
     * 设定显示组队聊天
     * 
     * @param flag
     */
    public void setShowPartyChat(final boolean flag) {
        this._isShowPartyChat = flag;
    }

    /**
     * 设定显示交易聊天
     * 
     * @param flag
     */
    public void setShowTradeChat(final boolean flag) {
        this._isShowTradeChat = flag;
    }

    /**
     * 设定显示世界聊天
     * 
     * @param flag
     */
    public void setShowWorldChat(final boolean flag) {
        this._isShowWorldChat = flag;
    }

    /**
     * 设定学习技能
     * 
     * @param skillid
     */
    public void setSkillMastery(final int skillid) {
        if (!this.skillList.contains(skillid)) {
            this.skillList.add(skillid);
        }
    }

    /**
     * 设定判断是否无道具施法(召戒清单、变身清单)
     * 
     * @param SummonMonster
     */
    public void setSummonMonster(final boolean SummonMonster) {
        this._isSummonMonster = SummonMonster;
    }

    /**
     * 设定瞬移
     * 
     * @param flag
     */
    public void setTeleport(final boolean flag) {
        this._isTeleport = flag;
    }

    /**
     * 设定传送目的地 面向
     * 
     * @param i
     */
    public void setTeleportHeading(final int i) {
        this._teleportHeading = i;
    }

    /**
     * 设定传送目的地 地图ID
     * 
     * @param i
     */
    public void setTeleportMapId(final short i) {
        this._teleportMapId = i;
    }

    /**
     * 设定传送目的地坐标X
     * 
     * @param i
     */
    public void setTeleportX(final int i) {
        this._teleportX = i;
    }

    /**
     * 设定传送目的地坐标Y
     * 
     * @param i
     */
    public void setTeleportY(final int i) {
        this._teleportY = i;
    }

    /**
     * 设定角色死亡时的临时GFX图像
     * 
     * @param i
     */
    public void setTempCharGfxAtDead(final int i) {
        this._tempCharGfxAtDead = i;
    }

    /**
     * 设定临时ID
     * 
     * @param tempID
     */
    public void setTempID(final int tempID) {
        this._tempID = tempID;
    }

    /**
     * 设定临时等级
     * 
     * @param i
     */
    public void setTempLevel(final int i) {
        this._tempLevel = i;
    }

    /**
     * 设定临时最高等级
     * 
     * @param i
     */
    public void setTempMaxLevel(final int i) {
        this._tempMaxLevel = i;
    }

    /**
     * 设置暂存字符串(收件者)
     * 
     * @param text
     */
    public void setText(final String text) {
        this._text = text;
    }

    /**
     * 设置暂存byte[]阵列
     * 
     * @param textByte
     */
    public void setTextByte(final byte[] textByte) {
        this._textByte = textByte;
    }

    /**
     * 设定交易ID
     * 
     * @param tradeID
     */
    public void setTradeID(final int tradeID) {
        this._tradeID = tradeID;
    }

    /**
     * 设定交易OK
     * 
     * @param tradeOk
     */
    public void setTradeOk(final boolean tradeOk) {
        this._tradeOk = tradeOk;
    }

    /**
     * 设定正在进行个人商店交易
     * 
     * @param flag
     */
    public void setTradingInPrivateShop(final boolean flag) {
        this._isTradingInPrivateShop = flag;
    }

    /**
     * 设定类型
     * 
     * @param i
     */
    public void setType(final int i) {
        this._type = i;
    }

    /**
     * 设定使用的武器
     * 
     * @param weapon
     */
    public void setWeapon(final L1ItemInstance weapon) {
        this._weapon = weapon;
    }

    /**
     * 是否在参加血盟战争中
     * 
     * @param lastAttacker
     * @return true:是 false:不是
     */
    public boolean simWarResult(final L1Character lastAttacker) {
        if (this.getClanid() == 0) { // 没有血盟
            return false;
        }
        if (Config.SIM_WAR_PENALTY) { // 模拟战中false
            return false;
        }
        L1PcInstance attacker = null;
        String enemyClanName = null;
        boolean sameWar = false;

        if (lastAttacker instanceof L1PcInstance) {
            attacker = (L1PcInstance) lastAttacker;
        } else if (lastAttacker instanceof L1PetInstance) {
            attacker = (L1PcInstance) ((L1PetInstance) lastAttacker)
                    .getMaster();
        } else if (lastAttacker instanceof L1SummonInstance) {
            attacker = (L1PcInstance) ((L1SummonInstance) lastAttacker)
                    .getMaster();
        } else {
            return false;
        }

        // 取得全部战争列表
        for (final L1War war : L1World.getInstance().getWarList()) {
            final L1Clan clan = L1World.getInstance().getClan(
                    this.getClanname());

            final int warType = war.GetWarType();
            final boolean isInWar = war.CheckClanInWar(this.getClanname());
            if ((attacker != null) && (attacker.getClanid() != 0)) { // lastAttackerがPC、サモン、ペットでクラン所属中
                sameWar = war.CheckClanInSameWar(this.getClanname(),
                        attacker.getClanname());
            }

            if ((this.getId() == clan.getLeaderId()) && // 血盟主で模拟战中
                    (warType == 2) && (isInWar == true)) {
                enemyClanName = war.GetEnemyClanName(this.getClanname());
                if (enemyClanName != null) {
                    war.CeaseWar(this.getClanname(), enemyClanName); // 终结
                }
            }

            if ((warType == 2) && sameWar) { // 模拟战で同じ战争に参加中の场合、ペナルティなし
                return true;
            }
        }
        return false;
    }

    /**
     * 生存的呐喊(开始).
     */
    public void startCryOfSurvival() {
        final int INTERVAL = 60000;
        if (!this._CryOfSurvivalActive) {
            this._CryOfSurvival = new TheCryOfSurvival(this);
            _regenTimer.scheduleAtFixedRate(this._CryOfSurvival, INTERVAL,
                    INTERVAL);
            this._CryOfSurvivalActive = true;
        }
    }

    /**
     * 开始玩家恢复自身体力
     */
    public void startHpRegeneration() {
        final int INTERVAL = 1000;

        if (!this._hpRegenActive) {
            this._hpRegen = new HpRegeneration(this);
            _regenTimer.scheduleAtFixedRate(this._hpRegen, INTERVAL, INTERVAL);
            this._hpRegenActive = true;
        }
    }

    /**
     * 开始娃娃恢复玩家体力
     */
    public void startHpRegenerationByDoll() {
        final int INTERVAL_BY_DOLL = 64000;
        boolean isExistHprDoll = false;
        if (L1MagicDoll.isHpRegeneration(this)) {
            isExistHprDoll = true;
        }
        if (!this._hpRegenActiveByDoll && isExistHprDoll) {
            this._hpRegenByDoll = new HpRegenerationByDoll(this);
            _regenTimer.scheduleAtFixedRate(this._hpRegenByDoll,
                    INTERVAL_BY_DOLL, INTERVAL_BY_DOLL);
            this._hpRegenActiveByDoll = true;
        }
    }

    /**
     * 取得道具开始
     */
    public void startItemMakeByDoll() {
        final int INTERVAL_BY_DOLL = 240000;
        boolean isExistItemMakeDoll = false;
        if (L1MagicDoll.isItemMake(this)) {
            isExistItemMakeDoll = true;
        }
        if (!this._ItemMakeActiveByDoll && isExistItemMakeDoll) {
            this._itemMakeByDoll = new ItemMakeByDoll(this);
            _regenTimer.scheduleAtFixedRate(this._itemMakeByDoll,
                    INTERVAL_BY_DOLL, INTERVAL_BY_DOLL);
            this._ItemMakeActiveByDoll = true;
        }
    }

    /**
     * 开始觉醒恢复玩家魔力
     */
    public void startMpReductionByAwake() {
        final int INTERVAL_BY_AWAKE = 4000;
        if (!this._mpReductionActiveByAwake) {
            this._mpReductionByAwake = new MpReductionByAwake(this);
            _regenTimer.scheduleAtFixedRate(this._mpReductionByAwake,
                    INTERVAL_BY_AWAKE, INTERVAL_BY_AWAKE);
            this._mpReductionActiveByAwake = true;
        }
    }

    /**
     * 开始玩家恢复自身魔力
     */
    public void startMpRegeneration() {
        final int INTERVAL = 1000;

        if (!this._mpRegenActive) {
            this._mpRegen = new MpRegeneration(this);
            _regenTimer.scheduleAtFixedRate(this._mpRegen, INTERVAL, INTERVAL);
            this._mpRegenActive = true;
        }
    }

    /**
     * 开始娃娃恢复玩家魔力
     */
    public void startMpRegenerationByDoll() {
        final int INTERVAL_BY_DOLL = 64000;
        boolean isExistMprDoll = false;
        if (L1MagicDoll.isMpRegeneration(this)) {
            isExistMprDoll = true;
        }
        if (!this._mpRegenActiveByDoll && isExistMprDoll) {
            this._mpRegenByDoll = new MpRegenerationByDoll(this);
            _regenTimer.scheduleAtFixedRate(this._mpRegenByDoll,
                    INTERVAL_BY_DOLL, INTERVAL_BY_DOLL);
            this._mpRegenActiveByDoll = true;
        }
    }

    /**
     * 开始自动更新物件
     */
    public void startObjectAutoUpdate() {
        this.removeAllKnownObjects();
        this._autoUpdateFuture = GeneralThreadPool.getInstance()
                .pcScheduleAtFixedRate(new L1PcAutoUpdate(this.getId()), 0L,
                        INTERVAL_AUTO_UPDATE);
    }

    /**
     * 组队更新 3.3C
     */
    public void startRefreshParty() {

        final int INTERVAL = 25000;

        if (!this._rpActive) {

            this._rp = new L1PartyRefresh(this);

            _regenTimer.scheduleAtFixedRate(this._rp, INTERVAL, INTERVAL);

            this._rpActive = true;

        }

    }

    /**
     * 奇岩地监计时(开始).
     */
    public void startRocksPrison() {
        final int INTERVAL = 1000;
        if (!this._rocksPrisonActive) {
            this._rocksPrison = new RocksPrison(this);
            _regenTimer.scheduleAtFixedRate(this._rocksPrison, INTERVAL,
                    INTERVAL);
            this._rocksPrisonActive = true;
        }
    }

    /**
     * 生存的呐喊(停止).
     */
    public void stopCryOfSurvival() {
        if (this._CryOfSurvivalActive) {
            this._CryOfSurvival.cancel();
            this._CryOfSurvival = null;
            this._CryOfSurvivalActive = false;
        }
    }

    /**
     * 停止各种监控任务。
     */
    public void stopEtcMonitor() {
        if (this._autoUpdateFuture != null) {
            this._autoUpdateFuture.cancel(true);
            this._autoUpdateFuture = null;
        }
        if (this._expMonitorFuture != null) {
            this._expMonitorFuture.cancel(true);
            this._expMonitorFuture = null;
        }
        if (this._ghostFuture != null) {
            this._ghostFuture.cancel(true);
            this._ghostFuture = null;
        }

        if (this._hellFuture != null) {
            this._hellFuture.cancel(true);
            this._hellFuture = null;
        }

    }

    /**
     * 停止玩家恢复自身体力
     */
    public void stopHpRegeneration() {
        if (this._hpRegenActive) {
            this._hpRegen.cancel();
            this._hpRegen = null;
            this._hpRegenActive = false;
        }
    }

    /**
     * 停止娃娃恢复玩家体力
     */
    public void stopHpRegenerationByDoll() {
        if (this._hpRegenActiveByDoll) {
            this._hpRegenByDoll.cancel();
            this._hpRegenByDoll = null;
            this._hpRegenActiveByDoll = false;
        }
    }

    /**
     * 取得道具停止
     */
    public void stopItemMakeByDoll() {
        if (this._ItemMakeActiveByDoll) {
            this._itemMakeByDoll.cancel();
            this._itemMakeByDoll = null;
            this._ItemMakeActiveByDoll = false;
        }
    }

    /**
     * 停止觉醒恢复玩家魔力
     */
    public void stopMpReductionByAwake() {
        if (this._mpReductionActiveByAwake) {
            this._mpReductionByAwake.cancel();
            this._mpReductionByAwake = null;
            this._mpReductionActiveByAwake = false;
        }
    }

    /**
     * 停止玩家恢复自身魔力
     */
    public void stopMpRegeneration() {
        if (this._mpRegenActive) {
            this._mpRegen.cancel();
            this._mpRegen = null;
            this._mpRegenActive = false;
        }
    }

    /**
     * 停止娃娃恢复玩家魔力
     */
    public void stopMpRegenerationByDoll() {
        if (this._mpRegenActiveByDoll) {
            this._mpRegenByDoll.cancel();
            this._mpRegenByDoll = null;
            this._mpRegenActiveByDoll = false;
        }
    }

    /**
     * 复活后移出死亡清单
     */
    public void stopPcDeleteTimer() {
        if (this._pcDeleteTimer != null) {
            this._pcDeleteTimer.cancel();
            this._pcDeleteTimer = null;
        }
    }

    /**
     * 组队暂停更新 3.3C
     */
    public void stopRefreshParty() {

        if (this._rpActive) {

            this._rp.cancel();

            this._rp = null;

            this._rpActive = false;

        }
    }

    /**
     * 奇岩地监计时(停止).
     */
    public void stopRocksPrison() {
        if (this._rocksPrisonActive) {
            this._rocksPrison.cancel();
            this._rocksPrison = null;
            this._rocksPrisonActive = false;
        }
    }

    /**
     * 将重复的状态删除 (经验药水、魔眼等等)
     * 
     * @param _skillId
     *            技能ID
     * @param repeat_skill
     *            重复的技能ID
     */
    private void stopSkillList(final int _skillId, final int[] repeat_skill) {
        for (final int skillId : repeat_skill) {
            if (skillId != _skillId) {
                this.removeSkillEffect(skillId);
            }
        }
    }

    /**
     * 更新范围内的物件
     */
    public void updateObject() {
        this.removeOutOfRangeObjects();

        if (this.getMapId() <= 10000) {
            for (final L1Object visible : L1World.getInstance()
                    .getVisibleObjects(this, Config.PC_RECOGNIZE_RANGE)) {
                if (!this.knownsObject(visible)) {
                    visible.onPerceive(this);
                } else {
                    if (visible instanceof L1NpcInstance) {
                        final L1NpcInstance npc = (L1NpcInstance) visible;
                        if (this.getLocation().isInScreen(npc.getLocation())
                                && (npc.getHiddenStatus() != 0)) {
                            npc.approachPlayer(this);
                        }
                    }
                }
                if (this.hasSkillEffect(GMSTATUS_HPBAR)
                        && L1HpBar.isHpBarTarget(visible)) {
                    this.sendPackets(new S_HPMeter((L1Character) visible));
                }
            }
        } else { // 旅馆内判断
            for (final L1Object visible : L1World.getInstance()
                    .getVisiblePlayer(this)) {
                if (!this.knownsObject(visible)) {
                    visible.onPerceive(this);
                }
                if (this.hasSkillEffect(GMSTATUS_HPBAR)
                        && L1HpBar.isHpBarTarget(visible)) {
                    if (this.getInnKeyId() == ((L1Character) visible)
                            .getInnKeyId()) {
                        this.sendPackets(new S_HPMeter((L1Character) visible));
                    }
                }
            }
        }
    }
}
