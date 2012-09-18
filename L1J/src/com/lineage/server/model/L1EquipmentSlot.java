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
import static com.lineage.server.model.skill.L1SkillId.COUNTER_BARRIER;
import static com.lineage.server.model.skill.L1SkillId.DETECTION;
import static com.lineage.server.model.skill.L1SkillId.ENCHANT_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.EXTRA_HEAL;
import static com.lineage.server.model.skill.L1SkillId.GREATER_HASTE;
import static com.lineage.server.model.skill.L1SkillId.HASTE;
import static com.lineage.server.model.skill.L1SkillId.HEAL;
import static com.lineage.server.model.skill.L1SkillId.INVISIBILITY;
import static com.lineage.server.model.skill.L1SkillId.PHYSICAL_ENCHANT_DEX;
import static com.lineage.server.model.skill.L1SkillId.PHYSICAL_ENCHANT_STR;
import static com.lineage.server.model.skill.L1SkillId.STATUS_BRAVE;

import java.util.List;

import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Ability;
import com.lineage.server.serverpackets.S_AddSkill;
import com.lineage.server.serverpackets.S_DelSkill;
import com.lineage.server.serverpackets.S_Invis;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.serverpackets.S_SPMR;
import com.lineage.server.serverpackets.S_SkillBrave;
import com.lineage.server.serverpackets.S_SkillHaste;
import com.lineage.server.templates.L1Item;
import com.lineage.server.utils.collections.Lists;

/**
 * 装备插槽
 */
public class L1EquipmentSlot {

    private final L1PcInstance _owner;

    /**
     * 设置效果中的道具
     */
    private final List<L1ArmorSet> _currentArmorSet;

    private L1ItemInstance _weapon;

    private final List<L1ItemInstance> _armors;

    public L1EquipmentSlot(final L1PcInstance owner) {
        this._owner = owner;

        this._armors = Lists.newList();
        this._currentArmorSet = Lists.newList();
    }

    public List<L1ItemInstance> getArmors() {
        return this._armors;
    }

    public L1ItemInstance getWeapon() {
        return this._weapon;
    }

    public void remove(final L1ItemInstance equipment) {
        final L1Item item = equipment.getItem();
        if (item.getType2() == 0) {
            return;
        }

        if (item.get_addhp() != 0) {
            this._owner.addMaxHp(-item.get_addhp());
        }
        if (item.get_addmp() != 0) {
            this._owner.addMaxMp(-item.get_addmp());
        }
        if (equipment.getaddHp() != 0) {
            this._owner.addMaxHp(-equipment.getaddHp());
        }
        if (equipment.getaddMp() != 0) {
            this._owner.addMaxMp(-equipment.getaddMp());
        }
        this._owner.addStr((byte) -item.get_addstr());
        this._owner.addCon((byte) -item.get_addcon());
        this._owner.addDex((byte) -item.get_adddex());
        this._owner.addInt((byte) -item.get_addint());
        this._owner.addWis((byte) -item.get_addwis());
        if (item.get_addwis() != 0) {
            this._owner.resetBaseMr();
        }
        this._owner.addCha((byte) -item.get_addcha());

        int addMr = 0;
        addMr -= equipment.getMr();
        if ((item.getItemId() == 20236) && this._owner.isElf()) {
            addMr -= 5;
        }
        if (addMr != 0) {
            this._owner.addMr(addMr);
            this._owner.sendPackets(new S_SPMR(this._owner));
        }
        if ((item.get_addsp() != 0) || (equipment.getaddSp() != 0)) {
            this._owner.addSp(-(item.get_addsp() + equipment.getaddSp()));
            this._owner.sendPackets(new S_SPMR(this._owner));
        }
        if (item.isHasteItem()) {
            this._owner.addHasteItemEquipped(-1);
            if (this._owner.getHasteItemEquipped() == 0) {
                this._owner.setMoveSpeed(0);
                this._owner.sendPackets(new S_SkillHaste(this._owner.getId(),
                        0, 0));
                this._owner.broadcastPacket(new S_SkillHaste(this._owner
                        .getId(), 0, 0));
            }
        }
        this._owner.getEquipSlot().removeMagicHelm(this._owner.getId(),
                equipment);

        if (item.getType2() == 1) {
            this.removeWeapon(equipment);
        } else if (item.getType2() == 2) {
            this.removeArmor(equipment);
        }
    }

    private void removeArmor(final L1ItemInstance armor) {
        final L1Item item = armor.getItem();
        final int itemId = armor.getItem().getItemId();
        // 饰品不加防判断
        if ((armor.getItem().getType2() == 2)
                && (armor.getItem().getType() >= 8)
                && (armor.getItem().getType() <= 12)) {
            this._owner.addAc(-(item.get_ac() - armor.getAcByMagic()));
        } else {
            this._owner.addAc(-(item.get_ac() - armor.getEnchantLevel() - armor
                    .getAcByMagic()));
        }
        this._owner.addDamageReductionByArmor(-item.getDamageReduction());
        this._owner.addWeightReduction(-item.getWeightReduction());
        this._owner.addHitModifierByArmor(-item.getHitModifierByArmor());
        this._owner.addDmgModifierByArmor(-item.getDmgModifierByArmor());
        this._owner.addBowHitModifierByArmor(-item.getBowHitModifierByArmor());
        this._owner.addBowDmgModifierByArmor(-item.getBowDmgModifierByArmor());
        this._owner.addRegistStun(-item.get_regist_stun());
        this._owner.addRegistStone(-item.get_regist_stone());
        this._owner.addRegistSleep(-item.get_regist_sleep());
        this._owner.add_regist_freeze(-item.get_regist_freeze());
        this._owner.addRegistSustain(-item.get_regist_sustain());
        this._owner.addRegistBlind(-item.get_regist_blind());
        // 饰品强化 Scroll of Enchant Accessory
        this._owner.addEarth(-item.get_defense_earth() - armor.getEarthMr());
        this._owner.addWind(-item.get_defense_wind() - armor.getWindMr());
        this._owner.addWater(-item.get_defense_water() - armor.getWaterMr());
        this._owner.addFire(-item.get_defense_fire() - armor.getFireMr());
        this._owner.addExpBonus(-item.getExpBonus()); // 经验值加成

        for (final L1ArmorSet armorSet : L1ArmorSet.getAllSet()) {
            if (armorSet.isPartOfSet(itemId)
                    && this._currentArmorSet.contains(armorSet)
                    && !armorSet.isValid(this._owner)) {
                armorSet.cancelEffect(this._owner);
                this._currentArmorSet.remove(armorSet);
            }
        }

        if ((itemId == 20077) || (itemId == 20062) || (itemId == 120077)) {
            this._owner.delInvis(); // 解除隐形状态
        }
        if (itemId == 20288) { // ROTC
            this._owner.sendPackets(new S_Ability(1, false));
        }
        armor.stopEquipmentTimer(this._owner);

        this._armors.remove(armor);
    }

    public void removeMagicHelm(final int objectId, final L1ItemInstance item) {
        switch (item.getItemId()) {
            case 20013: // 敏捷魔法头盔
                if (!SkillsTable.getInstance().spellCheck(objectId,
                        PHYSICAL_ENCHANT_DEX)) {
                    this._owner.removeSkillMastery(PHYSICAL_ENCHANT_DEX);
                    this._owner.sendPackets(new S_DelSkill(0, 0, 0, 2, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0));
                }
                if (!SkillsTable.getInstance().spellCheck(objectId, HASTE)) {
                    this._owner.removeSkillMastery(HASTE);
                    this._owner.sendPackets(new S_DelSkill(0, 0, 0, 0, 0, 4, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0));
                }
                break;
            case 20014: // 治愈魔法头盔
                if (!SkillsTable.getInstance().spellCheck(objectId, HEAL)) {
                    this._owner.removeSkillMastery(HEAL);
                    this._owner.sendPackets(new S_DelSkill(1, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0));
                }
                if (!SkillsTable.getInstance().spellCheck(objectId, EXTRA_HEAL)) {
                    this._owner.removeSkillMastery(EXTRA_HEAL);
                    this._owner.sendPackets(new S_DelSkill(0, 0, 4, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0));
                }
                break;
            case 20015: // 力量魔法头盔
                if (!SkillsTable.getInstance().spellCheck(objectId,
                        ENCHANT_WEAPON)) {
                    this._owner.removeSkillMastery(ENCHANT_WEAPON);
                    this._owner.sendPackets(new S_DelSkill(0, 8, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0));
                }
                if (!SkillsTable.getInstance().spellCheck(objectId, DETECTION)) {
                    this._owner.removeSkillMastery(DETECTION);
                    this._owner.sendPackets(new S_DelSkill(0, 16, 0, 0, 0, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0));
                }
                if (!SkillsTable.getInstance().spellCheck(objectId,
                        PHYSICAL_ENCHANT_STR)) {
                    this._owner.removeSkillMastery(PHYSICAL_ENCHANT_STR);
                    this._owner.sendPackets(new S_DelSkill(0, 0, 0, 0, 0, 2, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0));
                }
                break;
            case 20008: // 小型风之头盔
                if (!SkillsTable.getInstance().spellCheck(objectId, HASTE)) {
                    this._owner.removeSkillMastery(HASTE);
                    this._owner.sendPackets(new S_DelSkill(0, 0, 0, 0, 0, 4, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0));
                }
                break;
            case 20023: // 风之头盔
                if (!SkillsTable.getInstance().spellCheck(objectId, HASTE)) {
                    this._owner.removeSkillMastery(HASTE);
                    this._owner.sendPackets(new S_DelSkill(0, 0, 0, 0, 0, 4, 0,
                            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0));
                }
                if (!SkillsTable.getInstance().spellCheck(objectId,
                        GREATER_HASTE)) {
                    this._owner.removeSkillMastery(GREATER_HASTE);
                    this._owner.sendPackets(new S_DelSkill(0, 0, 0, 0, 0, 0,
                            32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                            0, 0, 0, 0, 0));
                }
                break;
        }
    }

    private void removeWeapon(final L1ItemInstance weapon) {
        this._owner.setWeapon(null);
        this._owner.setCurrentWeapon(0);
        weapon.stopEquipmentTimer(this._owner);
        this._weapon = null;
        if (this._owner.hasSkillEffect(COUNTER_BARRIER)) {
            this._owner.removeSkillEffect(COUNTER_BARRIER);
        }
    }

    public void set(final L1ItemInstance equipment) {
        final L1Item item = equipment.getItem();
        if (item.getType2() == 0) {
            return;
        }

        if (item.get_addhp() != 0) {
            this._owner.addMaxHp(item.get_addhp());
        }
        if (item.get_addmp() != 0) {
            this._owner.addMaxMp(item.get_addmp());
        }
        if (equipment.getaddHp() != 0) {
            this._owner.addMaxHp(equipment.getaddHp());
        }
        if (equipment.getaddMp() != 0) {
            this._owner.addMaxMp(equipment.getaddMp());
        }
        this._owner.addStr(item.get_addstr());
        this._owner.addCon(item.get_addcon());
        this._owner.addDex(item.get_adddex());
        this._owner.addInt(item.get_addint());
        this._owner.addWis(item.get_addwis());
        if (item.get_addwis() != 0) {
            this._owner.resetBaseMr();
        }
        this._owner.addCha(item.get_addcha());

        int addMr = 0;
        addMr += equipment.getMr();
        if ((item.getItemId() == 20236) && this._owner.isElf()) {
            addMr += 5;
        }
        if (addMr != 0) {
            this._owner.addMr(addMr);
            this._owner.sendPackets(new S_SPMR(this._owner));
        }
        if ((item.get_addsp() != 0) || (equipment.getaddSp() != 0)) {
            this._owner.addSp(item.get_addsp() + equipment.getaddSp());
            this._owner.sendPackets(new S_SPMR(this._owner));
        }
        if (item.isHasteItem()) {
            this._owner.addHasteItemEquipped(1);
            this._owner.removeHasteSkillEffect();
            if (this._owner.getMoveSpeed() != 1) {
                this._owner.setMoveSpeed(1);
                this._owner.sendPackets(new S_SkillHaste(this._owner.getId(),
                        1, -1));
                this._owner.broadcastPacket(new S_SkillHaste(this._owner
                        .getId(), 1, 0));
            }
        }
        if (item.getItemId() == 20383) { // 军马头盔
            if (this._owner.hasSkillEffect(STATUS_BRAVE)) {
                this._owner.killSkillEffectTimer(STATUS_BRAVE);
                this._owner.sendPackets(new S_SkillBrave(this._owner.getId(),
                        0, 0));
                this._owner.broadcastPacket(new S_SkillBrave(this._owner
                        .getId(), 0, 0));
                this._owner.setBraveSpeed(0);
            }
        }
        this._owner.getEquipSlot().setMagicHelm(equipment);

        if (item.getType2() == 1) {
            this.setWeapon(equipment);
        } else if (item.getType2() == 2) {
            this.setArmor(equipment);
            this._owner.sendPackets(new S_SPMR(this._owner));
        }
    }

    private void setArmor(final L1ItemInstance armor) {
        final L1Item item = armor.getItem();
        final int itemId = armor.getItem().getItemId();
        // 饰品不加防判断
        if ((armor.getItem().getType2() == 2)
                && (armor.getItem().getType() >= 8)
                && (armor.getItem().getType() <= 12)) {
            this._owner.addAc(item.get_ac() - armor.getAcByMagic());
        } else {
            this._owner.addAc(item.get_ac() - armor.getEnchantLevel()
                    - armor.getAcByMagic());
        }
        this._owner.addDamageReductionByArmor(item.getDamageReduction());
        this._owner.addWeightReduction(item.getWeightReduction());
        this._owner.addHitModifierByArmor(item.getHitModifierByArmor());
        this._owner.addDmgModifierByArmor(item.getDmgModifierByArmor());
        this._owner.addBowHitModifierByArmor(item.getBowHitModifierByArmor());
        this._owner.addBowDmgModifierByArmor(item.getBowDmgModifierByArmor());
        this._owner.addRegistStun(item.get_regist_stun());
        this._owner.addRegistStone(item.get_regist_stone());
        this._owner.addRegistSleep(item.get_regist_sleep());
        this._owner.add_regist_freeze(item.get_regist_freeze());
        this._owner.addRegistSustain(item.get_regist_sustain());
        this._owner.addRegistBlind(item.get_regist_blind());
        // 饰品强化 Scroll of Enchant Accessory
        this._owner.addEarth(item.get_defense_earth() + armor.getEarthMr());
        this._owner.addWind(item.get_defense_wind() + armor.getWindMr());
        this._owner.addWater(item.get_defense_water() + armor.getWaterMr());
        this._owner.addFire(item.get_defense_fire() + armor.getFireMr());
        this._owner.addExpBonus(item.getExpBonus()); // 经验值加成

        this._armors.add(armor);

        for (final L1ArmorSet armorSet : L1ArmorSet.getAllSet()) {
            if (armorSet.isPartOfSet(itemId) && armorSet.isValid(this._owner)) {
                if ((armor.getItem().getType2() == 2)
                        && (armor.getItem().getType() == 9)) { // ring
                    if (!armorSet.isEquippedRingOfArmorSet(this._owner)) {
                        armorSet.giveEffect(this._owner);
                        this._currentArmorSet.add(armorSet);
                    }
                } else {
                    armorSet.giveEffect(this._owner);
                    this._currentArmorSet.add(armorSet);
                }
            }
        }

        // 隐身斗篷
        if ((itemId == 20077) || (itemId == 20062) || (itemId == 120077)) {
            if (!this._owner.hasSkillEffect(INVISIBILITY)) {
                this._owner.killSkillEffectTimer(BLIND_HIDING);
                this._owner.setSkillEffect(INVISIBILITY, 0);
                this._owner.sendPackets(new S_Invis(this._owner.getId(), 1));
                this._owner.broadcastPacketForFindInvis(new S_RemoveObject(
                        this._owner), false);
                // _owner.broadcastPacket(new S_RemoveObject(_owner));
            }
        }
        if (itemId == 20288) { // ROTC 传送控制戒指
            this._owner.sendPackets(new S_Ability(1, true));
        }
        if (itemId == 20383) { // 军马头盔
            if (armor.getChargeCount() != 0) {
                armor.setChargeCount(armor.getChargeCount() - 1);
                this._owner.getInventory().updateItem(armor,
                        L1PcInventory.COL_CHARGE_COUNT);
            }
        }
        armor.startEquipmentTimer(this._owner);
    }

    public void setMagicHelm(final L1ItemInstance item) {
        switch (item.getItemId()) {
            case 20013:
                this._owner.setSkillMastery(PHYSICAL_ENCHANT_DEX);
                this._owner.setSkillMastery(HASTE);
                this._owner.sendPackets(new S_AddSkill(0, 0, 0, 2, 0, 4, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0));
                break;
            case 20014:
                this._owner.setSkillMastery(HEAL);
                this._owner.setSkillMastery(EXTRA_HEAL);
                this._owner.sendPackets(new S_AddSkill(1, 0, 4, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0));
                break;
            case 20015:
                this._owner.setSkillMastery(ENCHANT_WEAPON);
                this._owner.setSkillMastery(DETECTION);
                this._owner.setSkillMastery(PHYSICAL_ENCHANT_STR);
                this._owner.sendPackets(new S_AddSkill(0, 24, 0, 0, 0, 2, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0));
                break;
            case 20008:
                this._owner.setSkillMastery(HASTE);
                this._owner.sendPackets(new S_AddSkill(0, 0, 0, 0, 0, 4, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0));
                break;
            case 20023:
                this._owner.setSkillMastery(HASTE);
                this._owner.setSkillMastery(GREATER_HASTE);
                this._owner.sendPackets(new S_AddSkill(0, 0, 0, 0, 0, 4, 32, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0));
                break;
        }
    }

    private void setWeapon(final L1ItemInstance weapon) {
        this._owner.setWeapon(weapon);
        this._owner.setCurrentWeapon(weapon.getItem().getType1());
        weapon.startEquipmentTimer(this._owner);
        this._weapon = weapon;
    }

}
