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

import static com.lineage.server.model.skill.L1SkillId.SHAPE_CHANGE;

import java.util.Map;

import com.lineage.server.datatables.PolyTable;
import com.lineage.server.datatables.SprTable;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.npc.action.L1NpcDefaultAction;
import com.lineage.server.serverpackets.S_ChangeShape;
import com.lineage.server.serverpackets.S_CloseList;
import com.lineage.server.serverpackets.S_NpcChangeShape;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillIconGFX;
import com.lineage.server.utils.collections.Maps;

// Referenced classes of package com.lineage.server.model:
// L1PcInstance

/**
 * 变身
 */
public class L1PolyMorph {

    // weapon equip bit
    /** 短剑/匕首. */
    private static final int DAGGER_EQUIP = 1;
    /** 长剑. */
    private static final int SWORD_EQUIP = 2;
    /** 双手剑. */
    private static final int TWOHANDSWORD_EQUIP = 4;
    /** 斧. */
    private static final int AXE_EQUIP = 8;
    /** 矛. */
    private static final int SPEAR_EQUIP = 16;
    /** 棍/魔杖. */
    private static final int STAFF_EQUIP = 32;
    /** 双刀. */
    private static final int EDORYU_EQUIP = 64;
    /** 钢爪. */
    private static final int CLAW_EQUIP = 128;
    /** 弓/铁手甲. */
    private static final int BOW_EQUIP = 256;
    /** 奇古兽. */
    private static final int KIRINGKU_EQUIP = 512;
    /** 锁链剑. */
    private static final int CHAINSWORD_EQUIP = 1024;

    // armor equip bit
    /** 头盔. */
    private static final int HELM_EQUIP = 1;
    /** 项链. */
    private static final int AMULET_EQUIP = 2;
    /** 耳环. */
    private static final int EARRING_EQUIP = 4;
    /** T恤. */
    private static final int TSHIRT_EQUIP = 8;
    /** 盔甲. */
    private static final int ARMOR_EQUIP = 16;
    /** 斗篷. */
    private static final int CLOAK_EQUIP = 32;
    /** 腰带. */
    private static final int BELT_EQUIP = 64;
    /** 盾牌. */
    private static final int SHIELD_EQUIP = 128;
    /** 手套. */
    private static final int GLOVE_EQUIP = 256;
    /** 戒指. */
    private static final int RING_EQUIP = 512;
    /** 长靴. */
    private static final int BOOTS_EQUIP = 1024;
    /** 臂甲. */
    private static final int GUARDER_EQUIP = 2048;

    // 变身の原因を示すbit
    public static final int MORPH_BY_ITEMMAGIC = 1;

    public static final int MORPH_BY_GM = 2;

    public static final int MORPH_BY_NPC = 4; // 占星术师ケプリシャ以外のNPC

    public static final int MORPH_BY_KEPLISHA = 8;

    public static final int MORPH_BY_LOGIN = 0;

    private static final Map<Integer, Integer> weaponFlgMap = Maps.newMap();
    static {
        weaponFlgMap.put(1, SWORD_EQUIP);
        weaponFlgMap.put(2, DAGGER_EQUIP);
        weaponFlgMap.put(3, TWOHANDSWORD_EQUIP);
        weaponFlgMap.put(4, BOW_EQUIP);
        weaponFlgMap.put(5, SPEAR_EQUIP);
        weaponFlgMap.put(6, AXE_EQUIP);
        weaponFlgMap.put(7, STAFF_EQUIP);
        weaponFlgMap.put(8, BOW_EQUIP);
        weaponFlgMap.put(9, BOW_EQUIP);
        weaponFlgMap.put(10, BOW_EQUIP);
        weaponFlgMap.put(11, CLAW_EQUIP);
        weaponFlgMap.put(12, EDORYU_EQUIP);
        weaponFlgMap.put(13, BOW_EQUIP);
        weaponFlgMap.put(14, SPEAR_EQUIP);
        weaponFlgMap.put(15, AXE_EQUIP);
        weaponFlgMap.put(16, STAFF_EQUIP);
        weaponFlgMap.put(17, KIRINGKU_EQUIP);
        weaponFlgMap.put(18, CHAINSWORD_EQUIP);
        weaponFlgMap.put(19, KIRINGKU_EQUIP);
    }

    private static final Map<Integer, Integer> armorFlgMap = Maps.newMap();
    static {
        armorFlgMap.put(1, HELM_EQUIP);
        armorFlgMap.put(2, ARMOR_EQUIP);
        armorFlgMap.put(3, TSHIRT_EQUIP);
        armorFlgMap.put(4, CLOAK_EQUIP);
        armorFlgMap.put(5, GLOVE_EQUIP);
        armorFlgMap.put(6, BOOTS_EQUIP);
        armorFlgMap.put(7, SHIELD_EQUIP);
        armorFlgMap.put(8, AMULET_EQUIP);
        armorFlgMap.put(9, RING_EQUIP);
        armorFlgMap.put(10, BELT_EQUIP);
        armorFlgMap.put(12, EARRING_EQUIP);
        armorFlgMap.put(13, GUARDER_EQUIP);
    }

    // 变身
    public static void doPoly(final L1Character cha, final int polyId,
            final int timeSecs, final int cause) {
        doPoly(cha, polyId, timeSecs, cause, true);
    }

    // 变身
    public static void doPoly(final L1Character cha, final int polyId,
            final int timeSecs, final int cause, final boolean cantPolyMessage) {
        if ((cha == null) || cha.isDead()) {
            return;
        }
        if (cha instanceof L1PcInstance) {
            final L1PcInstance pc = (L1PcInstance) cha;
            if ((pc.getMapId() == 5124) || (pc.getMapId() == 5300)
                    || (pc.getMapId() == 5301)) { // 钓鱼池
                if (cantPolyMessage) {
                    pc.sendPackets(new S_ServerMessage(1170)); // 这里不可以变身。
                } else {
                    pc.sendPackets(new S_ServerMessage(79));
                }
                return;
            }
            if ((pc.getTempCharGfx() == 6034) || (pc.getTempCharGfx() == 6035)
                    || !isMatchCause(polyId, cause)) {
                if (cantPolyMessage) {
                    pc.sendPackets(new S_ServerMessage(181)); // \f1无法变成你指定的怪物。
                } else {
                    pc.sendPackets(new S_ServerMessage(79));
                }
                return;
            }
            pc.killSkillEffectTimer(SHAPE_CHANGE);
            pc.setSkillEffect(SHAPE_CHANGE, timeSecs * 1000);
            if (pc.getTempCharGfx() != polyId) {
                final L1ItemInstance weapon = pc.getWeapon();
                final boolean weaponTakeoff = ((weapon != null) && !isEquipableWeapon(
                        polyId, weapon.getItem().getType()));
                if (weaponTakeoff) { // 解除武器时
                    pc.setCurrentWeapon(0);
                }
                pc.setTempCharGfx(polyId);
                pc.sendPackets(new S_ChangeShape(pc.getId(), polyId, pc
                        .getCurrentWeapon()));
                if (pc.isGmInvis()) { // GM隐身
                } else if (pc.isInvisble()) { // 一般隐身
                    pc.broadcastPacketForFindInvis(new S_ChangeShape(
                            pc.getId(), polyId, pc.getCurrentWeapon()), true);
                } else {
                    pc.broadcastPacket(new S_ChangeShape(pc.getId(), polyId, pc
                            .getCurrentWeapon()));
                }
                pc.getInventory().takeoffEquip(polyId); // 是否将装备的武器强制解除。
            }
            pc.sendPackets(new S_SkillIconGFX(35, timeSecs));
        } else if (cha instanceof L1MonsterInstance) { // 怪物变身
            final L1MonsterInstance mob = (L1MonsterInstance) cha;
            mob.killSkillEffectTimer(SHAPE_CHANGE);
            mob.setSkillEffect(SHAPE_CHANGE, timeSecs * 1000);
            if (mob.getTempCharGfx() != polyId) {
                mob.setTempCharGfx(polyId);
                final int npcStatus = L1NpcDefaultAction.getInstance()
                        .getStatus(polyId);
                mob.setStatus(npcStatus);
                if (npcStatus == 20) { // 弓类
                    mob.setPolyAtkRanged(10);
                    mob.setPolyArrowGfx(66);
                } else if ((npcStatus == 24) || (polyId == 95)
                        || (polyId == 146)) { // 矛类、夏洛伯、杨果里恩
                    mob.setPolyAtkRanged(2);
                    mob.setPolyArrowGfx(0);
                } else {
                    mob.setPolyAtkRanged(1);
                    mob.setPolyArrowGfx(0);
                }
                mob.setPassispeed(SprTable.getInstance().getSprSpeed(polyId,
                        mob.getStatus())); // 移动速度
                mob.setAtkspeed(SprTable.getInstance().getSprSpeed(polyId,
                        mob.getStatus() + 1)); // 攻击速度
                mob.broadcastPacket(new S_NpcChangeShape(mob.getId(), polyId,
                        mob.getLawful(), mob.getStatus())); // 更新NPC外观
            }
        }
    }

    public static void handleCommands(final L1PcInstance pc, final String s) {
        if ((pc == null) || pc.isDead()) {
            return;
        }
        final L1PolyMorph poly = PolyTable.getInstance().getTemplate(s);
        if ((poly != null) || s.equals("none")) {
            if (s.equals("none")) {
                if ((pc.getTempCharGfx() == 6034)
                        || (pc.getTempCharGfx() == 6035)) {
                } else {
                    pc.removeSkillEffect(SHAPE_CHANGE);
                    pc.sendPackets(new S_CloseList(pc.getId()));
                }
            } else if ((pc.getLevel() >= poly.getMinLevel()) || pc.isGm()) {
                if ((pc.getTempCharGfx() == 6034)
                        || (pc.getTempCharGfx() == 6035)) {
                    pc.sendPackets(new S_ServerMessage(181)); // \f1无法变成你指定的怪物。
                } else {
                    doPoly(pc, poly.getPolyId(), 7200, MORPH_BY_ITEMMAGIC);
                    pc.sendPackets(new S_CloseList(pc.getId()));
                }
            } else {
                pc.sendPackets(new S_ServerMessage(181)); // // \f1无法变成你指定的怪物。
            }
        }
    }

    // 指定polyId与armorType装备防具出来？
    public static boolean isEquipableArmor(final int polyId, final int armorType) {
        final L1PolyMorph poly = PolyTable.getInstance().getTemplate(polyId);
        if (poly == null) {
            return true;
        }

        final Integer flg = armorFlgMap.get(armorType);
        if (flg != null) {
            return 0 != (poly.getArmorEquipFlg() & flg);
        }
        return true;
    }

    // 指定polyId与weapontTypeの武器を装备出来るか？
    public static boolean isEquipableWeapon(final int polyId,
            final int weaponType) {
        final L1PolyMorph poly = PolyTable.getInstance().getTemplate(polyId);
        if (poly == null) {
            return true;
        }

        final Integer flg = weaponFlgMap.get(weaponType);
        if (flg != null) {
            return 0 != (poly.getWeaponEquipFlg() & flg);
        }
        return true;
    }

    // 指定polyId把你变身、他将如何变身？
    public static boolean isMatchCause(final int polyId, final int cause) {
        final L1PolyMorph poly = PolyTable.getInstance().getTemplate(polyId);
        if (poly == null) {
            return true;
        }
        if (cause == MORPH_BY_LOGIN) {
            return true;
        }

        return 0 != (poly.getCauseFlg() & cause);
    }

    /** 解除变身 */
    public static void undoPoly(final L1Character cha) {
        if (cha instanceof L1PcInstance) {
            final L1PcInstance pc = (L1PcInstance) cha;
            final int classId = pc.getClassId();
            pc.setTempCharGfx(classId);
            if (!pc.isDead()) {
                pc.sendPackets(new S_ChangeShape(pc.getId(), classId, pc
                        .getCurrentWeapon()));
                pc.broadcastPacket(new S_ChangeShape(pc.getId(), classId, pc
                        .getCurrentWeapon()));
            }
        } else if (cha instanceof L1MonsterInstance) {
            final L1MonsterInstance mob = (L1MonsterInstance) cha;
            final int gfxId = mob.getGfxId();
            mob.setTempCharGfx(0);
            mob.setStatus(L1NpcDefaultAction.getInstance().getStatus(gfxId));
            mob.setPolyAtkRanged(-1);
            mob.setPolyArrowGfx(0);
            mob.setPassispeed(SprTable.getInstance().getSprSpeed(gfxId,
                    mob.getStatus())); // 移动速度
            mob.setAtkspeed(SprTable.getInstance().getSprSpeed(gfxId,
                    mob.getStatus() + 1)); // 攻击速度
            mob.broadcastPacket(new S_NpcChangeShape(mob.getId(), gfxId, mob
                    .getLawful(), mob.getStatus())); // 更新NPC外观
        }
    }

    private final int _id;

    private final String _name;

    private final int _polyId;

    private final int _minLevel;

    private final int _weaponEquipFlg;

    private final int _armorEquipFlg;

    private final boolean _canUseSkill;

    private final int _causeFlg;

    public L1PolyMorph(final int id, final String name, final int polyId,
            final int minLevel, final int weaponEquipFlg,
            final int armorEquipFlg, final boolean canUseSkill,
            final int causeFlg) {
        this._id = id;
        this._name = name;
        this._polyId = polyId;
        this._minLevel = minLevel;
        this._weaponEquipFlg = weaponEquipFlg;
        this._armorEquipFlg = armorEquipFlg;
        this._canUseSkill = canUseSkill;
        this._causeFlg = causeFlg;
    }

    public boolean canUseSkill() {
        return this._canUseSkill;
    }

    public int getArmorEquipFlg() {
        return this._armorEquipFlg;
    }

    public int getCauseFlg() {
        return this._causeFlg;
    }

    public int getId() {
        return this._id;
    }

    public int getMinLevel() {
        return this._minLevel;
    }

    public String getName() {
        return this._name;
    }

    public int getPolyId() {
        return this._polyId;
    }

    public int getWeaponEquipFlg() {
        return this._weaponEquipFlg;
    }
}
