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
package com.lineage.server.model;

import static com.lineage.server.model.skill.L1SkillId.BLIND_HIDING;
import static com.lineage.server.model.skill.L1SkillId.GMSTATUS_FINDINVIS;
import static com.lineage.server.model.skill.L1SkillId.INVISIBILITY;
import static com.lineage.server.model.skill.L1SkillId.LIGHT;
import static com.lineage.server.model.skill.L1SkillId.SECRET_MEDICINE_OF_DESTRUCTION;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CURSE_BARLOG;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CURSE_YAHEE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_HOLY_MITHRIL_POWDER;
import static com.lineage.server.model.skill.L1SkillId.STATUS_HOLY_WATER;
import static com.lineage.server.model.skill.L1SkillId.STATUS_HOLY_WATER_OF_EVA;

import java.util.List;
import java.util.Map;

import com.lineage.server.model.Instance.L1DollInstance;
import com.lineage.server.model.Instance.L1FollowerInstance;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.model.poison.L1Poison;
import com.lineage.server.model.skill.L1SkillTimer;
import com.lineage.server.model.skill.L1SkillTimerCreator;
import com.lineage.server.serverpackets.S_Light;
import com.lineage.server.serverpackets.S_PetCtrlMenu;
import com.lineage.server.serverpackets.S_Poison;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.serverpackets.ServerBasePacket;
import com.lineage.server.templates.L1MagicDoll;
import com.lineage.server.types.Point;
import com.lineage.server.utils.IntRange;
import com.lineage.server.utils.collections.Lists;
import com.lineage.server.utils.collections.Maps;

/**
 * 角色相关
 * 
 * @author jrwz
 */
public class L1Character extends L1Object {

    /**
     * 序列版本UID
     */
    private static final long serialVersionUID = 1L;
    /** 毒 */
    private L1Poison _poison = null;
    /** 麻痹状态 */
    private boolean _paralyzed;
    /** 睡眠状态 */
    private boolean _sleeped;
    /** 宠物清单 */
    private final Map<Integer, L1NpcInstance> _petlist = Maps.newMap();
    /** 娃娃清单 */
    private final Map<Integer, L1DollInstance> _dolllist = Maps.newMap();
    /** 技能效果 */
    private final Map<Integer, L1SkillTimer> _skillEffect = Maps.newMap();
    /** 道具延迟 */
    private final Map<Integer, L1ItemDelay.ItemDelayTimer> _itemdelay = Maps
            .newMap();
    /** 跟随清单 */
    private final Map<Integer, L1FollowerInstance> _followerlist = Maps
            .newMap();

    /** 现在的HP */
    private int _currentHp;

    /**
     * 现在的MP
     */
    private int _currentMp;

    /**
     * 麻痹
     */
    L1Paralysis _paralysis;

    // 正面的坐标
    private static final byte HEADING_TABLE_X[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

    private static final byte HEADING_TABLE_Y[] = { -1, -1, 0, 1, 1, 1, 0, -1 };

    /**
     * 是否在技能施放延迟中
     */
    private boolean _isSkillDelay = false;

    /**
     * 经验值
     */
    private long _exp;

    // ■■■■■■■■■■ L1PcInstanceへ移动するプロパティ ■■■■■■■■■■
    private final List<L1Object> _knownObjects = Lists.newConcurrentList();

    private final List<L1PcInstance> _knownPlayer = Lists.newConcurrentList();

    /**
     * 名称
     */
    private String _name;

    /**
     * 等级
     */
    private int _level;

    /**
     * 最高ＨＰ（1～32767）
     */
    private short _maxHp = 0;

    /**
     * 真正的最高ＨＰ
     */
    private int _trueMaxHp = 0;

    /**
     * 最高ＭＰ（0～32767）
     */
    private short _maxMp = 0;

    /**
     * 真正的最高ＭＰ
     */
    private int _trueMaxMp = 0;

    /**
     * 物理防御（-211～211）
     */
    private int _ac = 0;

    /**
     * 真正的物理防御
     */
    private int _trueAc = 0;

    /**
     * 力量值（1～255）
     */
    private short _str = 0;

    /**
     * 套装动画效果.
     */
    private int _gfxEffect = 0;

    /**
     * 套装动画效果时间间隔(秒).
     */
    private int _gfxEffectTime = 0;

    /**
     * 真正的力量值
     */
    private short _trueStr = 0;

    /**
     * 体质值（1～255）
     */
    private short _con = 0;

    /**
     * 真正的体质值
     */
    private short _trueCon = 0;

    /**
     * 敏捷值（1～255）
     */
    private short _dex = 0;

    /**
     * 真正的敏捷值
     */
    private short _trueDex = 0;
    /**
     * 魅力值（1～255）
     */
    private short _cha = 0;

    /**
     * 真正的魅力值
     */
    private short _trueCha = 0;

    /**
     * 智力值（1～255）
     */
    private short _int = 0;

    /**
     * 真正的智力值
     */
    private short _trueInt = 0;

    /**
     * 精神值（1～255）
     */
    private short _wis = 0;

    /**
     * 真正的精神值
     */
    private short _trueWis = 0;

    /**
     * 风属性防御（-128～127）
     */
    private int _wind = 0;

    /**
     * 真正的风属性防御
     */
    private int _trueWind = 0;

    /**
     * 水属性防御（-128～127）
     */
    private int _water = 0;

    /**
     * 真正的水属性防御
     */
    private int _trueWater = 0;

    /**
     * 火属性防御（-128～127）
     */
    private int _fire = 0;

    /**
     * 真正的火属性防御
     */
    private int _trueFire = 0;

    /**
     * 地属性防御（-128～127）
     */
    private int _earth = 0;

    /**
     * 真正的地属性防御
     */
    private int _trueEarth = 0;

    /**
     * 增加属性种类
     */
    private int _addAttrKind; // エレメンタルフォールダウンで减少した属性の种类

    /**
     * 昏迷耐性
     */
    private int _registStun = 0;

    /**
     * 真正的昏迷耐性
     */
    private int _trueRegistStun = 0;

    /**
     * 石化耐性
     */
    private int _registStone = 0;

    /**
     * 真正的石化耐性
     */
    private int _trueRegistStone = 0;

    /**
     * 睡眠耐性
     */
    private int _registSleep = 0;

    /**
     * 真正的睡眠耐性
     */
    private int _trueRegistSleep = 0;

    /**
     * 寒冰耐性
     */
    private int _registFreeze = 0;

    /**
     * 真正的寒冰耐性
     */
    private int _trueRegistFreeze = 0;

    /**
     * 支撑耐性
     */
    private int _registSustain = 0;

    /**
     * 真正的支撑耐性
     */
    private int _trueRegistSustain = 0;

    /**
     * 闇黑耐性
     */
    private int _registBlind = 0;

    /**
     * 真正的闇黑耐性
     */
    private int _trueRegistBlind = 0;

    /**
     * 增加近距离伤害（-128～127）
     */
    private int _dmgup = 0;

    /**
     * 真正的增加近距离伤害
     */
    private int _trueDmgup = 0;

    /**
     * 增加远距离伤害（-128～127）
     */
    private int _bowDmgup = 0;

    /**
     * 真正的增加远距离伤害
     */
    private int _trueBowDmgup = 0;

    /**
     * 近距离的命中率增加（-128～127）
     */
    private int _hitup = 0;

    /**
     * 真正的近距离的命中率增加
     */
    private int _trueHitup = 0;

    /**
     * 远距离的命中率增加（-128～127）
     */
    private int _bowHitup = 0;

    /**
     * 真正的远距离的命中率增加
     */
    private int _trueBowHitup = 0;

    /**
     * 魔法防御（0～250）
     */
    private int _mr = 0;

    /**
     * 真正的魔法防御
     */
    private int _trueMr = 0;

    /**
     * 魔攻
     */
    private int _sp = 0;

    /**
     * 死亡状态
     */
    private boolean _isDead;

    /**
     * 初始化状态
     */
    private int _status;

    /**
     * 封号
     */
    private String _title;

    /**
     * 正义值
     */
    private int _lawful;

    /**
     * 面向: 0.左上 1.上 2.右上 3.右 4.右下 5.下 6.左下 7.左
     */
    private int _heading;

    /**
     * 移动速度: 0.通常 1.加速 2.缓速
     */
    private int _moveSpeed;

    /**
     * 攻击速度: 0，通常 1，勇敢
     */
    private int _braveSpeed;

    /**
     * 变身ID
     */
    private int _tempCharGfx;

    // ■■■■■■■■■■ 角色属性 ■■■■■■■■■■

    /**
     * 原始外形ＩＤ
     */
    private int _gfxid;

    /**
     * 友好度
     */
    private int _karma;

    /**
     * 亮度范围
     */
    private int _chaLightSize;

    /**
     * 自身亮度范围(S_OwnCharPack用)
     */
    private int _ownLightSize;

    /**
     * 龙之门扉编号
     */
    private int _portalNumber = -1;

    /**
     * 饱食度
     */
    private int _food;

    /**
     * 附魔石等级
     */
    private byte _magicStoneLevel;
    /**
     * 闪避率 +
     */
    private byte _dodge = 0;

    /**
     * 闪避率 -
     */
    private byte _nDodge = 0;

    /**
     * 旅馆编号
     */
    private int _innRoomNumber;

    /**
     * 旅馆钥匙ID
     */
    private int _innKeyId;

    /**
     * 大厅
     */
    private boolean _isHall;

    /** 获得道具. */
    private int obtainProps;

    /** 获得道具间隔时间(秒). */
    private int obtainPropsTime;

    public L1Character() {
        this._level = 1;
    }

    /**
     * 增加寒冰耐性
     */
    public void add_regist_freeze(final int i) {
        this._trueRegistFreeze += i;
        if (this._trueRegistFreeze > 127) {
            this._registFreeze = 127;
        } else if (this._trueRegistFreeze < -128) {
            this._registFreeze = -128;
        } else {
            this._registFreeze = this._trueRegistFreeze;
        }
    }

    /**
     * 增加物理防御
     */
    public void addAc(final int i) {
        this.setAc(this._trueAc + i);
    }

    /**
     * 增加远距离伤害
     */
    public void addBowDmgup(final int i) {
        this._trueBowDmgup += i;
        if (this._trueBowDmgup >= 127) {
            this._bowDmgup = 127;
        } else if (this._trueBowDmgup <= -128) {
            this._bowDmgup = -128;
        } else {
            this._bowDmgup = this._trueBowDmgup;
        }
    }

    /**
     * 增加远距离的命中率
     */
    public void addBowHitup(final int i) {
        this._trueBowHitup += i;
        if (this._trueBowHitup >= 127) {
            this._bowHitup = 127;
        } else if (this._trueBowHitup <= -128) {
            this._bowHitup = -128;
        } else {
            this._bowHitup = this._trueBowHitup;
        }
    }

    /**
     * 增加魅力值
     */
    public void addCha(final int i) {
        this.setCha(this._trueCha + i);
    }

    /**
     * 增加体质值
     */
    public void addCon(final int i) {
        this.setCon(this._trueCon + i);
    }

    /**
     * 增加敏捷值
     */
    public void addDex(final int i) {
        this.setDex(this._trueDex + i);
    }

    /**
     * 增加近距离伤害
     */
    public void addDmgup(final int i) {
        this._trueDmgup += i;
        if (this._trueDmgup >= 127) {
            this._dmgup = 127;
        } else if (this._trueDmgup <= -128) {
            this._dmgup = -128;
        } else {
            this._dmgup = this._trueDmgup;
        }
    }

    /**
     * 增加闪避率 +
     */
    public void addDodge(final byte i) {
        this._dodge += i;
        if (this._dodge >= 10) {
            this._dodge = 10;
        } else if (this._dodge <= 0) {
            this._dodge = 0;
        }
    }

    /**
     * 增加魔法娃娃。
     * 
     * @param doll
     *            添加到doll表、L1DollInstance对象。
     */
    public void addDoll(final L1DollInstance doll) {
        this._dolllist.put(doll.getId(), doll);
    }

    /**
     * 增加地属性防御
     */
    public void addEarth(final int i) {
        this._trueEarth += i;
        if (this._trueEarth >= 127) {
            this._earth = 127;
        } else if (this._trueEarth <= -128) {
            this._earth = -128;
        } else {
            this._earth = this._trueEarth;
        }
    }

    /**
     * 增加火属性防御
     */
    public void addFire(final int i) {
        this._trueFire += i;
        if (this._trueFire >= 127) {
            this._fire = 127;
        } else if (this._trueFire <= -128) {
            this._fire = -128;
        } else {
            this._fire = this._trueFire;
        }
    }

    /**
     * 添加跟随。
     * 
     * @param follower
     *            添加到follower表、L1FollowerInstance对象。
     */
    public void addFollower(final L1FollowerInstance follower) {
        this._followerlist.put(follower.getId(), follower);
    }

    /**
     * 近距离的命中率增加
     */
    public void addHitup(final int i) {
        this._trueHitup += i;
        if (this._trueHitup >= 127) {
            this._hitup = 127;
        } else if (this._trueHitup <= -128) {
            this._hitup = -128;
        } else {
            this._hitup = this._trueHitup;
        }
    }

    /**
     * 增加智力值
     */
    public void addInt(final int i) {
        this.setInt(this._trueInt + i);
    }

    /**
     * 增加道具延迟时间。
     * 
     * @param delayId
     *            延迟物品ID。 如果是正常的道具0、隐形斗篷、血腥炎魔披风1。
     * @param timer
     *            表示延迟时间 (毫秒)、L1ItemDelay.ItemDelayTimer对象。
     */
    public void addItemDelay(final int delayId,
            final L1ItemDelay.ItemDelayTimer timer) {
        this._itemdelay.put(delayId, timer);
    }

    /**
     * 加入已认识物件
     * 
     * @param obj
     *            加入对象。
     */
    public void addKnownObject(final L1Object obj) {
        if (!this._knownObjects.contains(obj)) {
            this._knownObjects.add(obj);
            if (obj instanceof L1PcInstance) {
                this._knownPlayer.add((L1PcInstance) obj);
            }
        }
    }

    /**
     * 增加正义值
     */
    public synchronized void addLawful(final int i) {
        this._lawful += i;
        if (this._lawful > 32767) {
            this._lawful = 32767;
        } else if (this._lawful < -32768) {
            this._lawful = -32768;
        }
    }

    /**
     * 增加最高ＨＰ
     */
    public void addMaxHp(final int i) {
        this.setMaxHp(this._trueMaxHp + i);
    }

    /**
     * 增加最高ＭＰ
     */
    public void addMaxMp(final int i) {
        this.setMaxMp(this._trueMaxMp + i);
    }

    /**
     * 增加魔法防御
     */
    public void addMr(final int i) {
        this._trueMr += i;
        if (this._trueMr <= 0) {
            this._mr = 0;
        } else {
            this._mr = this._trueMr;
        }
    }

    /**
     * 增加闪避率 -
     */
    public void addNdodge(final byte i) {
        this._nDodge += i;
        if (this._nDodge >= 10) {
            this._nDodge = 10;
        } else if (this._nDodge <= 0) {
            this._nDodge = 0;
        }
    }

    /**
     * 对角色添加、新宠物、召唤怪物、怪物Teimingu、造尸。
     * 
     * @param npc
     *            添加到Npc表、L1NpcInstance对象。
     */
    public void addPet(final L1NpcInstance npc) {
        this._petlist.put(npc.getId(), npc);
        // if (_petlist.size() < 2) {
        this.sendPetCtrlMenu(npc, true); // 显示宠物控制图形介面
        // }

    }

    /**
     * 增加闇黑耐性
     */
    public void addRegistBlind(final int i) {
        this._trueRegistBlind += i;
        if (this._trueRegistBlind > 127) {
            this._registBlind = 127;
        } else if (this._trueRegistBlind < -128) {
            this._registBlind = -128;
        } else {
            this._registBlind = this._trueRegistBlind;
        }
    }

    /**
     * 增加睡眠耐性
     */
    public void addRegistSleep(final int i) {
        this._trueRegistSleep += i;
        if (this._trueRegistSleep > 127) {
            this._registSleep = 127;
        } else if (this._trueRegistSleep < -128) {
            this._registSleep = -128;
        } else {
            this._registSleep = this._trueRegistSleep;
        }
    }

    /**
     * 增加石化耐性
     */
    public void addRegistStone(final int i) {
        this._trueRegistStone += i;
        if (this._trueRegistStone > 127) {
            this._registStone = 127;
        } else if (this._trueRegistStone < -128) {
            this._registStone = -128;
        } else {
            this._registStone = this._trueRegistStone;
        }
    }

    /**
     * 增加昏迷耐性
     */
    public void addRegistStun(final int i) {
        this._trueRegistStun += i;
        if (this._trueRegistStun > 127) {
            this._registStun = 127;
        } else if (this._trueRegistStun < -128) {
            this._registStun = -128;
        } else {
            this._registStun = this._trueRegistStun;
        }
    }

    /**
     * 增加支撑耐性
     */
    public void addRegistSustain(final int i) {
        this._trueRegistSustain += i;
        if (this._trueRegistSustain > 127) {
            this._registSustain = 127;
        } else if (this._trueRegistSustain < -128) {
            this._registSustain = -128;
        } else {
            this._registSustain = this._trueRegistSustain;
        }
    }

    /**
     * 为角色，增加新的技能效果。
     * 
     * @param skillId
     *            要增加的技能效果ID。
     * @param timeMillis
     *            设定技能效果的持续时间。无限制是0。
     */
    private void addSkillEffect(final int skillId, final int timeMillis) {
        L1SkillTimer timer = null;
        if (0 < timeMillis) {
            timer = L1SkillTimerCreator.create(this, skillId, timeMillis);
            timer.begin();
        }
        this._skillEffect.put(skillId, timer);
    }

    /**
     * 增加魔攻
     */
    public void addSp(final int i) {
        this._sp += i;
    }

    /**
     * 增加力量值
     */
    public void addStr(final int i) {
        this.setStr(this._trueStr + i);
    }

    /**
     * 增加水属性防御
     */
    public void addWater(final int i) {
        this._trueWater += i;
        if (this._trueWater >= 127) {
            this._water = 127;
        } else if (this._trueWater <= -128) {
            this._water = -128;
        } else {
            this._water = this._trueWater;
        }
    }

    /**
     * 增加风属性防御
     */
    public void addWind(final int i) {
        this._trueWind += i;
        if (this._trueWind >= 127) {
            this._wind = 127;
        } else if (this._trueWind <= -128) {
            this._wind = -128;
        } else {
            this._wind = this._trueWind;
        }
    }

    /**
     * 增加精神值
     */
    public void addWis(final int i) {
        this.setWis(this._trueWis + i);
    }

    /**
     * 在该物件的全部可见范围内发送封包 (不包括自己在内)
     * 
     * @param packet
     *            ServerBasePacket对象，表示要发送的封包。
     */
    public void broadcastPacket(final ServerBasePacket packet) {
        for (final L1PcInstance pc : L1World.getInstance().getVisiblePlayer(
                this)) {
            // 旅馆内判断
            if ((pc.getMapId() < 16384) || (pc.getMapId() > 25088)
                    || (pc.getInnKeyId() == this.getInnKeyId())) {
                pc.sendPackets(packet);
            }
        }
    }

    /**
     * 恰好是可见的人物的范围，并发送封包。 但中心的画面目标不能发送。
     * 
     * @param packet
     *            ServerBasePacket对象，表示要发送的封包。
     */
    public void broadcastPacketExceptTargetSight(final ServerBasePacket packet,
            final L1Character target) {
        for (final L1PcInstance pc : L1World.getInstance()
                .getVisiblePlayerExceptTargetSight(this, target)) {
            pc.sendPackets(packet);
        }
    }

    /**
     * キャラクターの可视范围でインビジを见破れるor见破れないプレイヤーを区别して、要发送的数据包。
     * 
     * @param packet
     *            ServerBasePacket对象，表示要发送的数据包。
     * @param isFindInvis
     *            true : 见破れるプレイヤーにだけパケットを送信する。 false : 见破れないプレイヤーにだけパケットを送信する。
     */
    public void broadcastPacketForFindInvis(final ServerBasePacket packet,
            final boolean isFindInvis) {
        for (final L1PcInstance pc : L1World.getInstance().getVisiblePlayer(
                this)) {
            if (isFindInvis) {
                if (pc.hasSkillEffect(GMSTATUS_FINDINVIS)) {
                    pc.sendPackets(packet);
                }
            } else {
                if (!pc.hasSkillEffect(GMSTATUS_FINDINVIS)) {
                    pc.sendPackets(packet);
                }
            }
        }
    }

    /**
     * 检查房间或大厅
     */
    public boolean checkRoomOrHall() {
        return this._isHall;
    }

    /**
     * 删除全部的技能效果
     */
    public void clearSkillEffectTimer() {
        for (final L1SkillTimer timer : this._skillEffect.values()) {
            if (timer != null) {
                timer.kill();
            }
        }
        this._skillEffect.clear();
    }

    /**
     * 治愈麻痹
     */
    public void cureParalaysis() {
        if (this._paralysis != null) {
            this._paralysis.cure();
        }
    }

    /**
     * 解毒。
     */
    public void curePoison() {
        if (this._poison == null) {
            return;
        }
        this._poison.cure();
    }

    /**
     * 取得饱食度
     */
    public int get_food() {
        return this._food;
    }

    /**
     * 取得物理防御
     */
    public int getAc() {
        return this._ac + L1MagicDoll.getAcByDoll(this); // TODO 魔法娃娃效果 - 防御增加
    }

    /**
     * 取得属性种类
     */
    public int getAddAttrKind() {
        return this._addAttrKind;
    }

    /**
     * 取得增加远距离伤害
     */
    public int getBowDmgup() {
        return this._bowDmgup + L1MagicDoll.getBowDamageByDoll(this); // 魔法娃娃效果
                                                                      // -
                                                                      // 远距离的攻击力增加
    }

    /**
     * 取得远距离的命中率增加
     */
    public int getBowHitup() {
        return (this._bowHitup + L1MagicDoll.getBowHitAddByDoll(this)); // TODO
                                                                        // 魔法娃娃效果
                                                                        // -
                                                                        // 弓的命中率增加
    }

    /**
     * 取得攻击速度: 0，通常 1，勇敢
     */
    public int getBraveSpeed() {
        return this._braveSpeed;
    }

    /**
     * 取得魅力值
     */
    public short getCha() {
        return this._cha;
    }

    /**
     * 取得亮度范围
     */
    public int getChaLightSize() {
        if (this.isInvisble()) {
            return 0;
        }
        return this._chaLightSize;
    }

    /**
     * 取得体质值
     */
    public short getCon() {
        return this._con;
    }

    /**
     * 取得现在的HP。
     * 
     * @return 现在的HP
     */
    public int getCurrentHp() {
        return this._currentHp;
    }

    /**
     * 取得人物的当前MP。
     * 
     * @return 现在的MP
     */
    public int getCurrentMp() {
        return this._currentMp;
    }

    /**
     * 取得敏捷值
     */
    public short getDex() {
        return this._dex;
    }

    /**
     * 取得增加近距离伤害
     */
    public int getDmgup() {
        return this._dmgup + L1MagicDoll.getDamageAddByDoll(this); // 魔法娃娃效果 -
                                                                   // 近距离的攻击力增加
    } // 当你使用

    /**
     * 取得闪避率 +
     */
    public byte getDodge() {
        return this._dodge;
    }

    /**
     * 取得魔法娃娃清单。
     * 
     * @return 魔法娃娃代表一个角色列表、HashMap对象。这个对象Key的对象ID、ValueはL1NpcInstance
     */
    public Map<Integer, L1DollInstance> getDollList() {
        return this._dolllist;
    }

    /**
     * 取得地属性防御
     */
    public int getEarth() {
        return this._earth;
    } // 当你使用

    /**
     * 取得角色的经验值。
     * 
     * @return 经验值。
     */
    public long getExp() {
        return this._exp;
    }

    /**
     * 取得火属性防御
     */
    public int getFire() {
        return this._fire;
    } // 当你使用

    /**
     * 取得跟随名单。
     * 
     * @return 代表一个跟随名单、HashMap对象。这个对象Key的对象ID、ValueはL1NpcInstance
     */
    public Map<Integer, L1FollowerInstance> getFollowerList() {
        return this._followerlist;
    }

    /**
     * 取得角色正面的坐标。
     * 
     * @return 正面的坐标
     */
    public int[] getFrontLoc() {
        final int[] loc = new int[2];
        int x = this.getX();
        int y = this.getY();
        final int heading = this.getHeading();

        x += HEADING_TABLE_X[heading];
        y += HEADING_TABLE_Y[heading];

        loc[0] = x;
        loc[1] = y;
        return loc;
    }

    /**
     * 取得套装效果.
     * 
     * @return 套装效果
     */
    public final int getGfxEffect() {
        return this._gfxEffect;
    }

    /**
     * 取得套装效果时间间隔.
     * 
     * @return 效果时间间隔
     */
    public final int getGfxEffectTime() {
        return this._gfxEffectTime;
    }

    /**
     * 取得原始外形ＩＤ
     */
    public int getGfxId() {
        return this._gfxid;
    }

    /**
     * 取得面向: 0.左上 1.上 2.右上 3.右 4.右下 5.下 6.左下 7.左
     */
    public int getHeading() {
        return this._heading;
    }

    /**
     * 取得近距离的命中率增加
     */
    public int getHitup() {
        return (this._hitup + L1MagicDoll.getHitAddByDoll(this)); // TODO 魔法娃娃效果
                                                                  // - 近距离的命中率增加
    }

    /**
     * 取得旅馆钥匙ID
     */
    public int getInnKeyId() {
        return this._innKeyId;
    }

    /**
     * 取得旅馆编号
     */
    public int getInnRoomNumber() {
        return this._innRoomNumber;
    }

    /**
     * 取得智力值
     */
    public short getInt() {
        return this._int;
    }

    /**
     * 取得角色背包道具。
     * 
     * @return 表示角色身上的道具，L1Inventory对象。
     */
    public L1Inventory getInventory() {
        return null;
    }

    /**
     * 是否为延迟使用的道具
     * 
     * @param delayId
     *            检查延迟项目ID。 如果是正常的道具0、隐形斗篷、血腥炎魔披风1。
     * @return 延迟设置
     */
    public L1ItemDelay.ItemDelayTimer getItemDelayTimer(final int delayId) {
        return this._itemdelay.get(delayId);
    }

    /**
     * 取得友好度。
     * 
     * @return 友好度。
     */
    public int getKarma() {
        return this._karma;
    }

    /**
     * 取得全部认识物件 (L1Object)清单
     * 
     * @return L1Object的ArrayList，它包含对象来表示角色识别。
     */
    public List<L1Object> getKnownObjects() {
        return this._knownObjects;
    }

    /**
     * 取得全部认识物件 (PC)清单
     * 
     * @return L1PcInstance的ArrayList，它包含对象来表示角色识别。
     */
    public List<L1PcInstance> getKnownPlayers() {
        return this._knownPlayer;
    }

    /**
     * 取得正义值
     */
    public int getLawful() {
        return this._lawful;
    }

    /**
     * 取得等级
     */
    public synchronized int getLevel() {
        return this._level;
    }

    /**
     * 取得智力对魔法命中的影响
     * 
     * @return 魔法命中率
     */
    public int getMagicBonus() {
        switch (this.getInt()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return -2;

            case 6:
            case 7:
            case 8:
                return -1;

            case 9:
            case 10:
            case 11:
                return 0;

            case 12:
            case 13:
            case 14:
                return 1;

            case 15:
            case 16:
            case 17:
                return 2;

            case 18:
                return 3;
            case 19:
                return 4;
            case 20:
                return 5;
            case 21:
                return 6;
            case 22:
                return 7;
            case 23:
                return 8;
            case 24:
                return 9;
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
                return 10;
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
                return 11;
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                return 12;
            case 50:
                return 13;

            default:
                return this.getInt() - 25;
        }
    }

    /**
     * 取得魔法等级
     * 
     * @return 角色目前等级除以4
     */
    public int getMagicLevel() {
        return this.getLevel() >> 2; // 原 (/ 4;)
    }

    /**
     * 取得附魔石等级
     */
    public byte getMagicStoneLevel() {
        return this._magicStoneLevel;
    }

    /**
     * 取得最高ＨＰ
     */
    public short getMaxHp() {
        return this._maxHp;
    }

    /**
     * 取得最高ＭＰ
     */
    public short getMaxMp() {
        return this._maxMp;
    }

    /**
     * 取得移动速度: 0.通常 1.加速 2.缓速
     */
    public int getMoveSpeed() {
        return this._moveSpeed;
    }

    /**
     * 取得魔法防御
     */
    public int getMr() {
        if (this.hasSkillEffect(153) == true) {
            return this._mr / 4;
        }
        return this._mr;
    } // 当你使用

    /**
     * 取得名称
     */
    public String getName() {
        return this._name;
    }

    /**
     * 取得闪避率 -
     */
    public byte getNdodge() {
        return this._nDodge;
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
     * 取得自身亮度范围(S_OwnCharPack用)
     */
    public int getOwnLightSize() {
        return this._ownLightSize;
    }

    /**
     * 取得麻痹
     */
    public L1Paralysis getParalysis() {
        return this._paralysis;
    }

    /**
     * 取得宠物清单。
     * 
     * @return 代表宠物清单，HashMap对象的角色。这个对象Key的对象ID、ValueはL1NpcInstance
     */
    public Map<Integer, L1NpcInstance> getPetList() {
        return this._petlist;
    }

    /**
     * 取得角色的中毒状态。
     * 
     * @return 表示角色中毒、L1Poison对象。
     */
    public L1Poison getPoison() {
        return this._poison;
    }

    /**
     * 取得龙之门扉编号
     */
    public int getPortalNumber() {
        return this._portalNumber;
    }

    /**
     * 取得闇黑耐性
     */
    public int getRegistBlind() {
        return (this._registBlind + L1MagicDoll.getRegistBlindByDoll(this));
    }

    /**
     * 取得寒冰耐性
     */
    public int getRegistFreeze() {
        return (this._registFreeze + L1MagicDoll.getRegistFreezeByDoll(this)); // TODO
                                                                               // 魔法娃娃效果
                                                                               // -
                                                                               // 寒冰耐性增加
    }

    /**
     * 取得睡眠耐性
     */
    public int getRegistSleep() {
        return (this._registSleep + L1MagicDoll.getRegistSleepByDoll(this));
    }

    /**
     * 取得石化耐性
     */
    public int getRegistStone() {
        return (this._registStone + L1MagicDoll.getRegistStoneByDoll(this));
    }

    /**
     * 取得昏迷耐性
     */
    public int getRegistStun() {
        return (this._registStun + L1MagicDoll.getRegistStunByDoll(this));
    }

    /**
     * 取得支撑耐性
     */
    public int getRegistSustain() {
        return (this._registSustain + L1MagicDoll.getRegistSustainByDoll(this));
    }

    /**
     * 取得技能效果剩余时间
     * 
     * @param skillId
     *            技能效果的ID
     * @return 技能效果剩余时间(秒)。无限制 -1。
     */
    public int getSkillEffectTimeSec(final int skillId) {
        final L1SkillTimer timer = this._skillEffect.get(skillId);
        if (timer == null) {
            return -1;
        }
        return timer.getRemainingTime();
    }

    /**
     * 取得魔攻
     */
    public int getSp() {
        return this.getTrueSp() + this._sp;
    }

    /**
     * 取得初始化状态
     */
    public int getStatus() {
        return this._status;
    }

    /**
     * 取得力量值
     */
    public short getStr() {
        return this._str;
    }

    /**
     * 取得变身ID
     */
    public int getTempCharGfx() {
        return this._tempCharGfx;
    }

    /**
     * 取得封号
     */
    public String getTitle() {
        return this._title;
    }

    /**
     * 取得真正的魔法防御
     */
    public int getTrueMr() {
        return this._trueMr;
    } // 当你设定

    /**
     * 取得真正的魔攻
     */
    public int getTrueSp() {
        return this.getMagicLevel() + this.getMagicBonus();
    }

    /**
     * 取得水属性防御
     */
    public int getWater() {
        return this._water;
    } // 当你使用

    /**
     * 取得风属性防御
     */
    public int getWind() {
        return this._wind;
    } // 当你使用

    /**
     * 取得精神值
     */
    public short getWis() {
        return this._wis;
    }

    /**
     * 取得角色所在区域。
     * 
     * @return 表示坐标值区。1:安全区，-1:战斗区，0:一般区。
     */
    public int getZoneType() {
        if (this.getMap().isSafetyZone(this.getLocation())) {
            return 1;
        } else if (this.getMap().isCombatZone(this.getLocation())) {
            return -1;
        } else { // 正常区
            return 0;
        }
    }

    /**
     * 检查指定直线距离的坐标存不存在障碍物。
     * 
     * @param tx
     *            坐标的X值
     * @param ty
     *            坐标的Y值
     * @return 没有障碍物true、有障碍物false。
     */
    public boolean glanceCheck(final int tx, final int ty) {
        int chx = this.getX();
        int chy = this.getY();
        for (int i = 0; i < 15; i++) {
            if ((chx == tx) && (chy == ty)) {
                break;
            }

            if (!this.getMap().isArrowPassable(chx, chy,
                    this.targetDirection(tx, ty))) {
                return false;
            }

            // Targetへ1タイル进める
            chx += Math.max(-1, Math.min(1, tx - chx));
            chy += Math.max(-1, Math.min(1, ty - chy));
        }
        return true;
    }

    /**
     * 取得该道具是否有延迟。
     * 
     * @param delayId
     *            检查延迟项目ID。 如果是正常的道具0、隐形斗篷、血腥炎魔披风1。
     * @return 有延迟true、没有false。
     */
    public boolean hasItemDelay(final int delayId) {
        return this._itemdelay.containsKey(delayId);
    }

    /**
     * 检查是否有指定的技能效果
     * 
     * @param skillId
     *            检查技能效果的ID。
     * @return 有技能效果true、没有false。
     */
    public boolean hasSkillEffect(final int skillId) {
        return this._skillEffect.containsKey(skillId);
    }

    /**
     * 恢复HP
     */
    public void healHp(final int pt) {
        this.setCurrentHp(this.getCurrentHp() + pt);
    }

    /**
     * 判断特定状态下才可攻击 NPC
     */
    public boolean isAttackMiss(final L1Character cha, final int npcId) {
        switch (npcId) {
            case 45912: // 士兵的怨灵
            case 45913: // 士兵的怨灵
            case 45914: // 怨灵
            case 45915: // 怨灵
                if (!cha.hasSkillEffect(STATUS_HOLY_WATER)) {
                    return true;
                }
                return false;
            case 45916: // 哈蒙将军的怨灵
                if (!cha.hasSkillEffect(STATUS_HOLY_MITHRIL_POWDER)) {
                    return true;
                }
                return false;
            case 45941: // 受诅咒的巫女莎尔
                if (!cha.hasSkillEffect(STATUS_HOLY_WATER_OF_EVA)) {
                    return true;
                }
                return false;
            case 45752: // 炎魔(变身前)
                if (!cha.hasSkillEffect(STATUS_CURSE_BARLOG)) {
                    return true;
                }
                return false;
            case 45753: // 炎魔(变身后)
                if (!cha.hasSkillEffect(STATUS_CURSE_BARLOG)) {
                    return true;
                }
                return false;
            case 45675: // 火焰之影(变身前)
                if (!cha.hasSkillEffect(STATUS_CURSE_YAHEE)) {
                    return true;
                }
                return false;
            case 81082: // 火焰之影(变身后)
                if (!cha.hasSkillEffect(STATUS_CURSE_YAHEE)) {
                    return true;
                }
                return false;
            case 45625: // 混沌
                if (!cha.hasSkillEffect(STATUS_CURSE_YAHEE)) {
                    return true;
                }
                return false;
            case 45674: // 死亡
                if (!cha.hasSkillEffect(STATUS_CURSE_YAHEE)) {
                    return true;
                }
                return false;
            case 45685: // 堕落
                if (!cha.hasSkillEffect(STATUS_CURSE_YAHEE)) {
                    return true;
                }
                return false;
            case 81341: // 再生之祭坛
                if (!cha.hasSkillEffect(SECRET_MEDICINE_OF_DESTRUCTION)) {
                    return true;
                }
            default:
                if ((npcId >= 46068) && (npcId <= 46091) // 原生魔族
                        && (cha.getTempCharGfx() == 6035)) {
                    return true;
                } else if ((npcId >= 46092) && (npcId <= 46106) // 不死魔族
                        && (cha.getTempCharGfx() == 6034)) {
                    return true;
                }
                return false;
        }
    }

    /**
     * 返回指定坐标是否可以攻击
     * 
     * @param x
     *            坐标的X值。
     * @param y
     *            坐标的Y值。
     * @param range
     *            可以攻击的范围(格数)
     * @return 可以攻击true,不能攻击false
     */
    public boolean isAttackPosition(final int x, final int y, final int range) {
        if (range >= 7) // 远程武器（走出画面考虑至少7对角线的情况下）
        {
            if (this.getLocation().getTileDistance(new Point(x, y)) > range) {
                return false;
            }
        } else { // 近身武器
            if (this.getLocation().getTileLineDistance(new Point(x, y)) > range) {
                return false;
            }
        }
        return this.glanceCheck(x, y);
    }

    /**
     * 是否为死亡状态
     */
    public boolean isDead() {
        return this._isDead;
    }

    /**
     * 是否在隐身状态
     * 
     * @return 隐身术或暗隐术
     */
    public boolean isInvisble() {
        return (this.hasSkillEffect(INVISIBILITY) || this
                .hasSkillEffect(BLIND_HIDING));
    }

    /**
     * 是否为麻痹状态。
     * 
     * @return true:麻痹 false:无
     */
    public boolean isParalyzed() {
        return this._paralyzed;
    }

    /**
     * 是否在技能施放延迟中
     * 
     * @return true:是 false:否
     */
    public boolean isSkillDelay() {
        return this._isSkillDelay;
    }

    /**
     * 是否为睡眠状态。
     * 
     * @return true:睡眠 false:无
     */
    public boolean isSleeped() {
        return this._sleeped;
    }

    /**
     * 删除指定的技能效果
     * 
     * @param skillId
     *            要删除的技能ＩＤ
     */
    public void killSkillEffectTimer(final int skillId) {
        final L1SkillTimer timer = this._skillEffect.remove(skillId);
        if (timer != null) {
            timer.kill();
        }
    }

    /**
     * 是否为已认识物件
     * 
     * @param obj
     *            检查对象。
     * @return 如果是知道的角色对象true、不知道false。 对自己false。
     */
    public boolean knownsObject(final L1Object obj) {
        return this._knownObjects.contains(obj);
    }

    /**
     * 删除全部认识对象
     */
    public void removeAllKnownObjects() {
        this._knownObjects.clear();
        this._knownPlayer.clear();
    }

    /**
     * 删除魔法娃娃。
     * 
     * @param doll
     *            删除doll表、L1DollInstance对象。
     */
    public void removeDoll(final L1DollInstance doll) {
        this._dolllist.remove(doll.getId());
    }

    /**
     * 删除跟随。
     * 
     * @param follower
     *            删除follower表、L1FollowerInstance对象。
     */
    public void removeFollower(final L1FollowerInstance follower) {
        this._followerlist.remove(follower.getId());
    }

    /**
     * 删除道具延迟时间。
     * 
     * @param delayId
     *            延迟物品ID。 如果是正常的道具0、隐形斗篷、血腥炎魔披风1。
     */
    public void removeItemDelay(final int delayId) {
        this._itemdelay.remove(delayId);
    }

    /**
     * 删除已认识物件
     * 
     * @param obj
     *            删除对象
     */
    public void removeKnownObject(final L1Object obj) {
        this._knownObjects.remove(obj);
        if (obj instanceof L1PcInstance) {
            this._knownPlayer.remove(obj);
        }
    }

    /**
     * 对角色删除、新宠物、召唤怪物、怪物Teimingu、造尸。
     * 
     * @param npc
     *            添加到Npc表、L1NpcInstance对象。
     */
    public void removePet(final L1NpcInstance npc) {
        this._petlist.remove(npc.getId());
        // if (_petlist.isEmpty()) {
        this.sendPetCtrlMenu(npc, false); // 关闭宠物控制图形介面
        // }
    }

    /**
     * 结束指定的技能效果。
     * 
     * @param skillId
     *            结束技能效果的ID
     */
    public void removeSkillEffect(final int skillId) {
        final L1SkillTimer timer = this._skillEffect.remove(skillId);
        if (timer != null) {
            timer.end();
        }
    }

    /**
     * 复活角色。
     * 
     * @param hp
     *            复活后的HP
     */
    public void resurrect(int hp) {
        if (!this.isDead()) {
            return;
        }
        if (hp <= 0) {
            hp = 1;
        }

        // 设置为未死亡
        this.setDead(false);
        // 设置HP
        this.setCurrentHp(hp);
        // 设置状态
        this.setStatus(0);
        // 解除变身
        L1PolyMorph.undoPoly(this);
        // 重新认识物件
        for (final L1PcInstance pc : L1World.getInstance().getRecognizePlayer(
                this)) {
            pc.sendPackets(new S_RemoveObject(this));
            pc.removeKnownObject(this);
            pc.updateObject();
        }
    }

    /**
     * 3.3C PetMenu 控制
     * 
     * @param npc
     * @param type
     *            1:显示 0:关闭
     */
    public void sendPetCtrlMenu(final L1NpcInstance npc, final boolean type) {
        if (npc instanceof L1PetInstance) {
            final L1PetInstance pet = (L1PetInstance) npc;
            final L1Character cha = pet.getMaster();
            if (cha instanceof L1PcInstance) {
                final L1PcInstance pc = (L1PcInstance) cha;
                pc.sendPackets(new S_PetCtrlMenu(cha, npc, type));
                // 处理宠物控制图形介面
            }
        } else if (npc instanceof L1SummonInstance) {
            final L1SummonInstance summon = (L1SummonInstance) npc;
            final L1Character cha = summon.getMaster();
            if (cha instanceof L1PcInstance) {
                final L1PcInstance pc = (L1PcInstance) cha;
                pc.sendPackets(new S_PetCtrlMenu(cha, npc, type));
            }
        }
    }

    /**
     * 设定饱食度
     */
    public void set_food(final int i) {
        this._food = i;
    }

    /**
     * 设置物理防御
     */
    public void setAc(final int i) {
        this._trueAc = i;
        this._ac = IntRange.ensure(i, -211, 211);
    }

    /**
     * 设置属性种类
     */
    public void setAddAttrKind(final int i) {
        this._addAttrKind = i;
    }

    /**
     * 设置攻击速度: 0，通常 1，勇敢
     */
    public void setBraveSpeed(final int i) {
        this._braveSpeed = i;
    }

    /**
     * 设置魅力值
     */
    public void setCha(final int i) {
        this._trueCha = (short) i;
        this._cha = (short) IntRange.ensure(i, 1, 255);
    }

    /**
     * 设定亮度范围
     */
    public void setChaLightSize(final int i) {
        this._chaLightSize = i;
    }

    /**
     * 设置体质值
     */
    public void setCon(final int i) {
        this._trueCon = (short) i;
        this._con = (short) IntRange.ensure(i, 1, 255);
    }

    /**
     * 设置新HP。
     * 
     * @param i
     *            新的HP
     */
    // 特殊处理的场合覆盖（分组传输等）
    public void setCurrentHp(final int i) {
        this._currentHp = i;
        if (this._currentHp >= this.getMaxHp()) {
            this._currentHp = this.getMaxHp();
        }
    }

    /**
     * 设置登场物件的HP。
     * 
     * @param i
     *            登场物件的HP
     */
    public void setCurrentHpDirect(final int i) {
        this._currentHp = i;
    }

    /**
     * 设置新MP。
     * 
     * @param i
     *            新的MP
     */
    // 特殊处理的场合覆盖（分组传输等）
    public void setCurrentMp(final int i) {
        this._currentMp = i;
        if (this._currentMp >= this.getMaxMp()) {
            this._currentMp = this.getMaxMp();
        }
    }

    /**
     * 设置登场物件的MP。
     * 
     * @param i
     *            登场物件的MP
     */
    public void setCurrentMpDirect(final int i) {
        this._currentMp = i;
    }

    /**
     * 设置死亡状态
     */
    public void setDead(final boolean flag) {
        this._isDead = flag;
    }

    /**
     * 设置敏捷值
     */
    public void setDex(final int i) {
        this._trueDex = (short) i;
        this._dex = (short) IntRange.ensure(i, 1, 255);
    }

    /**
     * 设置角色的经验值。
     * 
     * @param exp
     *            经验值。
     */
    public void setExp(final long exp) {
        this._exp = exp;
    }

    /**
     * 设置套装效果.
     * 
     * @param i
     *            套装效果
     */
    public final void setGfxEffect(final int i) {
        this._gfxEffect = i;
    }

    /**
     * 设置套装效果时间间隔.
     * 
     * @param i
     *            效果时间间隔
     */
    public final void setGfxEffectTime(final int i) {
        this._gfxEffectTime = i;
    }

    /**
     * 设置原始外形ＩＤ
     */
    public void setGfxId(final int i) {
        this._gfxid = i;
    }

    /**
     * 设置大厅
     */
    public void setHall(final boolean i) {
        this._isHall = i;
    }

    /**
     * 设置面向: 0.左上 1.上 2.右上 3.右 4.右下 5.下 6.左下 7.左
     */
    public void setHeading(final int i) {
        this._heading = i;
    }

    /**
     * 设置旅馆钥匙ID
     */
    public void setInnKeyId(final int i) {
        this._innKeyId = i;
    }

    /**
     * 设置旅馆编号
     */
    public void setInnRoomNumber(final int i) {
        this._innRoomNumber = i;
    }

    /**
     * 设置智力值
     */
    public void setInt(final int i) {
        this._trueInt = (short) i;
        this._int = (short) IntRange.ensure(i, 1, 255);
    }

    /**
     * 设置友好度。
     * 
     * @param karma
     *            友好度。
     */
    public void setKarma(final int karma) {
        this._karma = karma;
    }

    /**
     * 设置正义值
     */
    public void setLawful(final int i) {
        this._lawful = i;
    }

    /**
     * 设置等级
     */
    public synchronized void setLevel(final long level) {
        this._level = (int) level;
    }

    /**
     * 设定附魔石等级
     */
    public void setMagicStoneLevel(final byte i) {
        this._magicStoneLevel = i;
    }

    /**
     * 设置最高ＨＰ
     */
    public void setMaxHp(final int hp) {
        this._trueMaxHp = hp;
        this._maxHp = (short) IntRange.ensure(this._trueMaxHp, 1, 32767);
        this._currentHp = Math.min(this._currentHp, this._maxHp);
    }

    /**
     * 设置最高ＭＰ
     */
    public void setMaxMp(final int mp) {
        this._trueMaxMp = mp;
        this._maxMp = (short) IntRange.ensure(this._trueMaxMp, 0, 32767);
        this._currentMp = Math.min(this._currentMp, this._maxMp);
    }

    /**
     * 设置移动速度: 0.通常 1.加速 2.缓速
     */
    public void setMoveSpeed(final int i) {
        this._moveSpeed = i;
    }

    /**
     * 设置魔防
     */
    public void setMr(final int i) {
        this._trueMr = i;
        if (this._trueMr <= 0) {
            this._mr = 0;
        } else {
            this._mr = this._trueMr;
        }
    }

    /**
     * 设置名称
     */
    public void setName(final String s) {
        this._name = s;
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
     * 设定自身亮度范围(S_OwnCharPack用)
     */
    public void setOwnLightSize(final int i) {
        this._ownLightSize = i;
    }

    /**
     * 设定麻痹
     */
    public void setParalaysis(final L1Paralysis p) {
        this._paralysis = p;
    }

    /**
     * 设置麻痹状态。
     * 
     * @param paralyzed
     *            true:麻痹 false:无
     */
    public void setParalyzed(final boolean paralyzed) {
        this._paralyzed = paralyzed;
    }

    /**
     * 设置 中毒。
     * 
     * @param poison
     *            毒列表、L1Poison对象。
     */
    public void setPoison(final L1Poison poison) {
        this._poison = poison;
    }

    /**
     * 设置角色的中毒效果
     * 
     * @param effectId
     * @see S_Poison#S_Poison(int, int)
     */
    public void setPoisonEffect(final int effectId) {
        this.broadcastPacket(new S_Poison(this.getId(), effectId));
    }

    /**
     * 设定龙之门扉编号
     */
    public void setPortalNumber(final int portalNumber) {
        this._portalNumber = portalNumber;
    }

    /**
     * 设置技能施放延迟中
     * 
     * @param flag
     */
    public void setSkillDelay(final boolean flag) {
        this._isSkillDelay = flag;
    }

    /**
     * 为角色，设置新的技能效果。<br>
     * 如果没有重复的技能、追加新的技能效果。<br>
     * 如果有重复、优先取剩余效果时间较长的。
     * 
     * @param skillId
     *            设置技能效果的ID。
     * @param timeMillis
     *            设置技能效果的持续时间。无限制是0。
     */
    public void setSkillEffect(final int skillId, final int timeMillis) {
        if (this.hasSkillEffect(skillId)) {
            final int remainingTimeMills = this.getSkillEffectTimeSec(skillId) * 1000;

            // 残り时间が有限で、パラメータの效果时间の方が长いか无限の场合は上书きする。
            if ((remainingTimeMills >= 0)
                    && ((remainingTimeMills < timeMillis) || (timeMillis == 0))) {
                this.killSkillEffectTimer(skillId);
                this.addSkillEffect(skillId, timeMillis);
            }
        } else {
            this.addSkillEffect(skillId, timeMillis);
        }
    }

    /**
     * 设置睡眠状态。
     * 
     * @param sleeped
     *            true:睡眠 false:无
     */
    public void setSleeped(final boolean sleeped) {
        this._sleeped = sleeped;
    }

    /**
     * 设置初始化状态
     */
    public void setStatus(final int i) {
        this._status = i;
    }

    /**
     * 设置力量值
     */
    public void setStr(final int i) {
        this._trueStr = (short) i;
        this._str = (short) IntRange.ensure(i, 1, 255);
    }

    /**
     * 设置变身ID
     */
    public void setTempCharGfx(final int i) {
        this._tempCharGfx = i;
    }

    /**
     * 设置封号
     */
    public void setTitle(final String s) {
        this._title = s;
    }

    /**
     * 设置精神值
     */
    public void setWis(final int i) {
        this._trueWis = (short) i;
        this._wis = (short) IntRange.ensure(i, 1, 255);
    }

    /**
     * 指定的坐标对应的面向。
     * 
     * @param tx
     *            坐标的X值
     * @param ty
     *            坐标的Y值
     * @return 指定的坐标对应的面向
     */
    public int targetDirection(final int tx, final int ty) {
        final float dis_x = Math.abs(this.getX() - tx); // 距离目标在X方向
        final float dis_y = Math.abs(this.getY() - ty); // 距离目标在Y方向
        final float dis = Math.max(dis_x, dis_y); // 距离目标
        if (dis == 0) {
            return this.getHeading(); // 回到同一个位置的面向
        }
        final int avg_x = (int) Math.floor((dis_x / dis) + 0.59f); // 上下左右がちょっと优先な丸め
        final int avg_y = (int) Math.floor((dis_y / dis) + 0.59f); // 上下左右がちょっと优先な丸め

        int dir_x = 0;
        int dir_y = 0;
        if (this.getX() < tx) {
            dir_x = 1;
        }
        if (this.getX() > tx) {
            dir_x = -1;
        }
        if (this.getY() < ty) {
            dir_y = 1;
        }
        if (this.getY() > ty) {
            dir_y = -1;
        }

        if (avg_x == 0) {
            dir_x = 0;
        }
        if (avg_y == 0) {
            dir_y = 0;
        }

        if ((dir_x == 1) && (dir_y == -1)) {
            return 1; // 上
        }
        if ((dir_x == 1) && (dir_y == 0)) {
            return 2; // 右上
        }
        if ((dir_x == 1) && (dir_y == 1)) {
            return 3; // 右
        }
        if ((dir_x == 0) && (dir_y == 1)) {
            return 4; // 右下
        }
        if ((dir_x == -1) && (dir_y == 1)) {
            return 5; // 下
        }
        if ((dir_x == -1) && (dir_y == 0)) {
            return 6; // 左下
        }
        if ((dir_x == -1) && (dir_y == -1)) {
            return 7; // 左
        }
        if ((dir_x == 0) && (dir_y == -1)) {
            return 0; // 左上
        }
        return this.getHeading(); // ここにはこない。はず
    }

    /**
     * 打开灯 (照明的真实范围)
     */
    public void turnOnOffLight() {
        int lightSize = 0;
        if (this instanceof L1NpcInstance) {
            final L1NpcInstance npc = (L1NpcInstance) this;
            lightSize = npc.getLightSize(); // npc.sqlのライトサイズ
        }
        if (this.hasSkillEffect(LIGHT)) {
            lightSize = 14;
        }

        for (final L1ItemInstance item : this.getInventory().getItems()) {
            if ((item.getItem().getType2() == 0)
                    && (item.getItem().getType() == 2)) { // light系アイテム
                final int itemlightSize = item.getItem().getLightRange();
                if ((itemlightSize != 0) && item.isNowLighting()) {
                    if (itemlightSize > lightSize) {
                        lightSize = itemlightSize;
                    }
                }
            }
        }

        // 角色
        if (this instanceof L1PcInstance) {
            final L1PcInstance pc = (L1PcInstance) this;
            pc.sendPackets(new S_Light(pc.getId(), lightSize));
        }
        if (!this.isInvisble()) {
            this.broadcastPacket(new S_Light(this.getId(), lightSize));
        }

        this.setOwnLightSize(lightSize); // S_OwnCharPackのライト范围
        this.setChaLightSize(lightSize); // S_OtherCharPack, S_NPCPackなどのライト范围
    }

    /**
     * 在该物件50格范围内发送封包
     * 
     * @param packet
     *            ServerBasePacket对象，表示要发送的封包。
     */
    public void wideBroadcastPacket(final ServerBasePacket packet) {
        for (final L1PcInstance pc : L1World.getInstance().getVisiblePlayer(
                this, 50)) {
            pc.sendPackets(packet);
        }
    }
}
