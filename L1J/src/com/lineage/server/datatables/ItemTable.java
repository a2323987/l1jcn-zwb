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
package com.lineage.server.datatables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.IdFactory;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.item.CiteItemClass;
import com.lineage.server.templates.L1Armor;
import com.lineage.server.templates.L1EtcItem;
import com.lineage.server.templates.L1Item;
import com.lineage.server.templates.L1Weapon;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 武器、防具、道具资料表
 */
public class ItemTable {

    /** 序列版本UID */
    private static final long serialVersionUID = 1L;
    /** 提示信息 */
    private static Logger _log = Logger.getLogger(ItemTable.class.getName());
    /** 防具类型 */
    private static final Map<String, Integer> _armorTypes = Maps.newMap();
    /** 武器类型 */
    private static final Map<String, Integer> _weaponTypes = Maps.newMap();
    /** 武器ID */
    private static final Map<String, Integer> _weaponId = Maps.newMap();
    /** 材料类型 */
    private static final Map<String, Integer> _materialTypes = Maps.newMap();
    /** 道具类型 */
    private static final Map<String, Integer> _etcItemTypes = Maps.newMap();
    /** 使用类型 */
    private static final Map<String, Integer> _useTypes = Maps.newMap();
    /**  */
    private static ItemTable _instance;

    /**
     * 取得序列版本UID
     * 
     * @return
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /** 所有模板 */
    private L1Item _allTemplates[];
    /** 道具 */
    private final Map<Integer, L1EtcItem> _etcitems;
    /** 防具 */
    private final Map<Integer, L1Armor> _armors;

    /** 武器 */
    private final Map<Integer, L1Weapon> _weapons;

    static {

        // 物品类型
        _etcItemTypes.put("arrow", new Integer(0)); // 箭
        _etcItemTypes.put("wand", new Integer(1)); // 魔杖
        _etcItemTypes.put("light", new Integer(2)); // 灯
        _etcItemTypes.put("gem", new Integer(3)); // 宝石
        _etcItemTypes.put("totem", new Integer(4)); // 图腾
        _etcItemTypes.put("firecracker", new Integer(5)); // 烟火
        _etcItemTypes.put("potion", new Integer(6)); // 药水
        _etcItemTypes.put("food", new Integer(7)); // 食物
        _etcItemTypes.put("scroll", new Integer(8)); // 卷轴
        _etcItemTypes.put("questitem", new Integer(9)); // 任务物品
        _etcItemTypes.put("spellbook", new Integer(10)); // 魔法书
        _etcItemTypes.put("petitem", new Integer(11)); // 宠物物品
        _etcItemTypes.put("other", new Integer(12)); // 其他
        _etcItemTypes.put("material", new Integer(13)); // 材料
        _etcItemTypes.put("event", new Integer(14)); // 活动物品
        _etcItemTypes.put("sting", new Integer(15)); // 飞刀
        _etcItemTypes.put("treasure_box", new Integer(16)); // 宝箱
        _etcItemTypes.put("magic_doll", new Integer(17)); // 魔法娃娃

        // 道具使用类型
        _useTypes.put("treasure_box", new Integer(-14)); // 宝箱
        _useTypes.put("arrow", new Integer(-13)); // 箭
        _useTypes.put("sting", new Integer(-12)); // 飞刀
        _useTypes.put("magic_stone_9", new Integer(-11)); // 9
                                                          // 阶附魔石(近战)(远攻)(恢复)(防御)
        _useTypes.put("magic_stone_1_4", new Integer(-10)); // 1 ~ 4
                                                            // 阶附魔石(近战)(远攻)(恢复)(防御)
        _useTypes.put("magic_stone_5_6", new Integer(-9)); // 5 ~
                                                           // 6阶附魔石(近战)(远攻)(恢复)(防御)
        _useTypes.put("magic_stone_7", new Integer(-8)); // 7阶附魔石(近战)(远攻)(恢复)(防御)
        _useTypes.put("magic_stone_8", new Integer(-7)); // 8阶附魔石(近战)(远攻)(恢复)(防御)
        _useTypes.put("cooking_books", new Integer(-6)); // 料理书
        _useTypes.put("potion", new Integer(-5)); // 药水类道具
        _useTypes.put("none", new Integer(-4)); // 无法使用
        _useTypes.put("cooking", new Integer(-3)); // 料理
        _useTypes.put("spellbook", new Integer(-2)); // 技能书
        _useTypes.put("other", new Integer(-1)); // 其他类道具
        _useTypes.put("normal", new Integer(0)); // 一般物品
        _useTypes.put("weapon", new Integer(1)); // 武器
        _useTypes.put("armor", new Integer(2)); // 盔甲
        _useTypes.put("spell_long", new Integer(5)); // 魔杖类型 (须选取目标/坐标)地面 /
                                                     // 选择对象(远距离)
        _useTypes.put("ntele", new Integer(6)); // 瞬间移动卷轴
        _useTypes.put("identify", new Integer(7)); // 鉴定卷轴
        _useTypes.put("res", new Integer(8)); // 复活卷轴
        _useTypes.put("letter", new Integer(12)); // 信纸
        _useTypes.put("letter_w", new Integer(13)); // 信纸 (寄出)
        _useTypes.put("choice", new Integer(14)); // 请选择一个物品 (道具栏位)
        _useTypes.put("instrument", new Integer(15)); // 哨子
        _useTypes.put("sosc", new Integer(16)); // 变形卷轴
        _useTypes.put("spell_short", new Integer(17)); // 选取目标 地面 / 选择对象(近距离)
        _useTypes.put("T", new Integer(18)); // T恤
        _useTypes.put("cloak", new Integer(19)); // 斗篷
        _useTypes.put("glove", new Integer(20)); // 手套
        _useTypes.put("boots", new Integer(21)); // 长靴
        _useTypes.put("helm", new Integer(22)); // 头盔
        _useTypes.put("ring", new Integer(23)); // 戒指
        _useTypes.put("amulet", new Integer(24)); // 项链
        _useTypes.put("shield", new Integer(25)); // 盾牌
        _useTypes.put("guarder", new Integer(25)); // 臂甲
        _useTypes.put("dai", new Integer(26)); // 对武器施法的卷轴
        _useTypes.put("zel", new Integer(27)); // 对盔甲施法的卷轴
        _useTypes.put("blank", new Integer(28)); // 空的魔法卷轴
        _useTypes.put("btele", new Integer(29)); // 瞬间移动卷轴 (祝福)
        _useTypes.put("spell_buff", new Integer(30)); // 选取目标 (对NPC需要Ctrl 远距离
                                                      // 无XY坐标传回) 选择对象(远距离)
        _useTypes.put("ccard", new Integer(31)); // 圣诞卡片
        _useTypes.put("ccard_w", new Integer(32)); // 圣诞卡片 (寄出)
        _useTypes.put("vcard", new Integer(33)); // 情人节卡片
        _useTypes.put("vcard_w", new Integer(34)); // 情人节卡片 (寄出)
        _useTypes.put("wcard", new Integer(35)); // 白色情人节卡片
        _useTypes.put("wcard_w", new Integer(36)); // 白色情人节卡片 (寄出)
        _useTypes.put("belt", new Integer(37)); // 腰带
        // _useTypes.put("spell_long2", new Integer(39)); // 地面 / 选择对象(远距离)
        // 5と同じ？
        _useTypes.put("earring", new Integer(40)); // 耳环
        _useTypes.put("fishing_rod", new Integer(42)); // 钓鱼竿
        _useTypes.put("tattoo_r", new Integer(43)); // 辅助装备 (右)
        _useTypes.put("tattoo_l", new Integer(44)); // 辅助装备 (左)
        _useTypes.put("tattoo_m", new Integer(45)); // 辅助装备 (中)
        _useTypes.put("del", new Integer(46)); // 饰品强化卷轴

        // 防具类型
        _armorTypes.put("none", new Integer(0)); // 无法使用
        _armorTypes.put("helm", new Integer(1)); // 头盔
        _armorTypes.put("armor", new Integer(2)); // 盔甲
        _armorTypes.put("T", new Integer(3)); // T恤
        _armorTypes.put("cloak", new Integer(4)); // 斗篷
        _armorTypes.put("glove", new Integer(5)); // 手套
        _armorTypes.put("boots", new Integer(6)); // 长靴
        _armorTypes.put("shield", new Integer(7)); // 盾牌
        _armorTypes.put("amulet", new Integer(8)); // 项链
        _armorTypes.put("ring", new Integer(9)); // 戒指
        _armorTypes.put("belt", new Integer(10)); // 腰带
        _armorTypes.put("ring2", new Integer(11)); // 戒指2
        _armorTypes.put("earring", new Integer(12)); // 耳环
        _armorTypes.put("guarder", new Integer(13)); // 臂甲
        _armorTypes.put("tattoo_r", new Integer(14)); // 辅助装备 (右)
        _armorTypes.put("tattoo_l", new Integer(15)); // 辅助装备 (左)
        _armorTypes.put("tattoo_m", new Integer(16)); // 辅助装备 (中)

        // 武器类型
        _weaponTypes.put("sword", new Integer(1)); // 剑 (单手)
        _weaponTypes.put("dagger", new Integer(2)); // 匕首 (单手)
        _weaponTypes.put("tohandsword", new Integer(3)); // 双手剑 (双手)
        _weaponTypes.put("bow", new Integer(4)); // 弓 (双手)
        _weaponTypes.put("spear", new Integer(5)); // 矛 (双手)
        _weaponTypes.put("blunt", new Integer(6)); // 斧 (单手)
        _weaponTypes.put("staff", new Integer(7)); // 魔杖 (单手)
        _weaponTypes.put("throwingknife", new Integer(8)); // 飞刀
        _weaponTypes.put("arrow", new Integer(9)); // 箭
        _weaponTypes.put("gauntlet", new Integer(10)); // 铁手甲
        _weaponTypes.put("claw", new Integer(11)); // 钢爪 (双手)
        _weaponTypes.put("edoryu", new Integer(12)); // 双刀 (双手)
        _weaponTypes.put("singlebow", new Integer(13)); // 弓 (单手)
        _weaponTypes.put("singlespear", new Integer(14)); // 矛 (单手)
        _weaponTypes.put("tohandblunt", new Integer(15)); // 双手斧 (双手)
        _weaponTypes.put("tohandstaff", new Integer(16)); // 魔杖 (双手)
        _weaponTypes.put("kiringku", new Integer(17)); // 奇古兽 (单手)
        _weaponTypes.put("chainsword", new Integer(18)); // 锁链剑 (单手)
        _weaponTypes.put("tohandkiringku", new Integer(19));// 艾尔摩奇古兽

        // 武器编号
        _weaponId.put("sword", new Integer(4)); // 剑
        _weaponId.put("dagger", new Integer(46)); // 匕首
        _weaponId.put("tohandsword", new Integer(50)); // 双手剑
        _weaponId.put("bow", new Integer(20)); // 弓
        _weaponId.put("blunt", new Integer(11)); // 斧 (单手)
        _weaponId.put("spear", new Integer(24)); // 矛 (双手)
        _weaponId.put("staff", new Integer(40)); // 魔杖
        _weaponId.put("throwingknife", new Integer(2922)); // 飞刀
        _weaponId.put("arrow", new Integer(66)); // 箭
        _weaponId.put("gauntlet", new Integer(62)); // 铁手甲
        _weaponId.put("claw", new Integer(58)); // 钢爪
        _weaponId.put("edoryu", new Integer(54)); // 双刀
        _weaponId.put("singlebow", new Integer(20)); // 弓 (单手)
        _weaponId.put("singlespear", new Integer(24)); // 矛 (单手)
        _weaponId.put("tohandblunt", new Integer(11)); // 双手斧
        _weaponId.put("tohandstaff", new Integer(40)); // 魔杖 (双手)
        _weaponId.put("kiringku", new Integer(58)); // 奇古兽
        _weaponId.put("chainsword", new Integer(24)); // 锁链剑
        _weaponId.put("tohandkiringku", new Integer(58)); // 艾尔摩奇古兽

        // 道具材质
        _materialTypes.put("none", new Integer(0)); // 无
        _materialTypes.put("liquid", new Integer(1)); // 液体
        _materialTypes.put("web", new Integer(2)); // 蜡
        _materialTypes.put("vegetation", new Integer(3)); // 植物性
        _materialTypes.put("animalmatter", new Integer(4)); // 动物性
        _materialTypes.put("paper", new Integer(5)); // 纸
        _materialTypes.put("cloth", new Integer(6)); // 布
        _materialTypes.put("leather", new Integer(7)); // 皮革
        _materialTypes.put("wood", new Integer(8)); // 木
        _materialTypes.put("bone", new Integer(9)); // 骨头
        _materialTypes.put("dragonscale", new Integer(10)); // 龙鳞
        _materialTypes.put("iron", new Integer(11)); // 铁
        _materialTypes.put("steel", new Integer(12)); // 钢铁
        _materialTypes.put("copper", new Integer(13)); // 铜
        _materialTypes.put("silver", new Integer(14)); // 银
        _materialTypes.put("gold", new Integer(15)); // 黄金
        _materialTypes.put("platinum", new Integer(16)); // 白金
        _materialTypes.put("mithril", new Integer(17)); // 米索莉
        _materialTypes.put("blackmithril", new Integer(18)); // 黑色米索莉
        _materialTypes.put("glass", new Integer(19)); // 玻璃
        _materialTypes.put("gemstone", new Integer(20)); // 宝石
        _materialTypes.put("mineral", new Integer(21)); // 矿物
        _materialTypes.put("oriharukon", new Integer(22)); // 奥里哈鲁根
    }

    /**  */
    public static ItemTable getInstance() {
        if (_instance == null) {
            _instance = new ItemTable();
        }
        return _instance;
    }

    private ItemTable() {
        this._etcitems = this.allEtcItem();
        this._weapons = this.allWeapon();
        this._armors = this.allArmor();
        this.buildFastLookupTable();
    }

    /**
     * 防具载入
     * 
     * @return
     */
    private Map<Integer, L1Armor> allArmor() {
        final Map<Integer, L1Armor> result = Maps.newMap();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        L1Armor armor = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM armor");

            rs = pstm.executeQuery();
            while (rs.next()) {
                armor = new L1Armor();
                final int item_id = rs.getInt("item_id");
                armor.setItemId(item_id);
                armor.setName(rs.getString("name"));
                final String class_name = rs.getString("class_name");
                armor.setClassName(class_name);
                armor.setUnidentifiedNameId(rs
                        .getString("unidentified_name_id"));
                armor.setIdentifiedNameId(rs.getString("identified_name_id"));
                armor.setType((_armorTypes.get(rs.getString("type")))
                        .intValue());
                // armor.setType1((_armorId
                // .get(rs.getString("armor_type"))).intValue()); // 使わない
                armor.setType2(2);
                armor.setUseType((_useTypes.get(rs.getString("type")))
                        .intValue());
                armor.setMaterial((_materialTypes.get(rs.getString("material")))
                        .intValue());
                armor.setWeight(rs.getInt("weight"));
                armor.setGfxId(rs.getInt("invgfx"));
                armor.setGroundGfxId(rs.getInt("grdgfx"));
                armor.setItemDescId(rs.getInt("itemdesc_id"));
                armor.set_ac(rs.getInt("ac"));
                armor.set_safeenchant(rs.getInt("safenchant"));
                armor.setUseRoyal(rs.getInt("use_royal") == 0 ? false : true);
                armor.setUseKnight(rs.getInt("use_knight") == 0 ? false : true);
                armor.setUseElf(rs.getInt("use_elf") == 0 ? false : true);
                armor.setUseMage(rs.getInt("use_mage") == 0 ? false : true);
                armor.setUseDarkelf(rs.getInt("use_darkelf") == 0 ? false
                        : true);
                armor.setUseDragonknight(rs.getInt("use_dragonknight") == 0 ? false
                        : true);
                armor.setUseIllusionist(rs.getInt("use_illusionist") == 0 ? false
                        : true);
                armor.set_addstr(rs.getByte("add_str"));
                armor.set_addcon(rs.getByte("add_con"));
                armor.set_adddex(rs.getByte("add_dex"));
                armor.set_addint(rs.getByte("add_int"));
                armor.set_addwis(rs.getByte("add_wis"));
                armor.set_addcha(rs.getByte("add_cha"));
                armor.set_addhp(rs.getInt("add_hp"));
                armor.set_addmp(rs.getInt("add_mp"));
                armor.set_addhpr(rs.getInt("add_hpr"));
                armor.set_addmpr(rs.getInt("add_mpr"));
                armor.set_addsp(rs.getInt("add_sp"));
                armor.setMinLevel(rs.getInt("min_lvl"));
                armor.setMaxLevel(rs.getInt("max_lvl"));
                armor.set_mdef(rs.getInt("m_def"));
                armor.setDamageReduction(rs.getInt("damage_reduction"));
                armor.setWeightReduction(rs.getInt("weight_reduction"));
                armor.setHitModifierByArmor(rs.getInt("hit_modifier"));
                armor.setDmgModifierByArmor(rs.getInt("dmg_modifier"));
                armor.setBowHitModifierByArmor(rs.getInt("bow_hit_modifier"));
                armor.setBowDmgModifierByArmor(rs.getInt("bow_dmg_modifier"));
                armor.setHasteItem(rs.getInt("haste_item") == 0 ? false : true);
                armor.setBless(rs.getInt("bless"));
                armor.setTradable(rs.getInt("trade") == 0 ? true : false);
                armor.setCantDelete(rs.getInt("cant_delete") == 1 ? true
                        : false);
                armor.set_defense_earth(rs.getInt("defense_earth"));
                armor.set_defense_water(rs.getInt("defense_water"));
                armor.set_defense_wind(rs.getInt("defense_wind"));
                armor.set_defense_fire(rs.getInt("defense_fire"));
                armor.set_regist_stun(rs.getInt("regist_stun"));
                armor.set_regist_stone(rs.getInt("regist_stone"));
                armor.set_regist_sleep(rs.getInt("regist_sleep"));
                armor.set_regist_freeze(rs.getInt("regist_freeze"));
                armor.set_regist_sustain(rs.getInt("regist_sustain"));
                armor.set_regist_blind(rs.getInt("regist_blind"));
                armor.setMaxUseTime(rs.getInt("max_use_time"));
                armor.setGrade(rs.getInt("grade"));
                armor.setExpBonus(rs.getDouble("exp_bonus")); // 经验值加成

                CiteItemClass.getInstance().addList(item_id, class_name, 2);
                result.put(new Integer(armor.getItemId()), armor);
            }
        } catch (final NullPointerException e) {
            _log.log(Level.SEVERE, new StringBuilder().append(armor.getName())
                    .append("(" + armor.getItemId() + ")").append("无法加载。")
                    .toString());
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);

        }
        return result;
    }

    /**
     * 道具载入
     * 
     * @return
     */
    private Map<Integer, L1EtcItem> allEtcItem() {
        final Map<Integer, L1EtcItem> result = Maps.newMap();

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        L1EtcItem item = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM etcitem");

            rs = pstm.executeQuery();
            while (rs.next()) {
                item = new L1EtcItem();
                final int item_id = rs.getInt("item_id");
                item.setItemId(item_id);
                item.setName(rs.getString("name"));
                final String class_name = rs.getString("class_name");
                item.setClassName(class_name);
                item.setUnidentifiedNameId(rs.getString("unidentified_name_id"));
                item.setIdentifiedNameId(rs.getString("identified_name_id"));
                item.setType((_etcItemTypes.get(rs.getString("item_type")))
                        .intValue());
                item.setUseType(_useTypes.get(rs.getString("use_type"))
                        .intValue());
                // item.setType1(0); // 使わない
                item.setType2(0);
                item.setMaterial((_materialTypes.get(rs.getString("material")))
                        .intValue());
                item.setWeight(rs.getInt("weight"));
                item.setGfxId(rs.getInt("invgfx"));
                item.setGroundGfxId(rs.getInt("grdgfx"));
                item.setItemDescId(rs.getInt("itemdesc_id"));
                item.setMinLevel(rs.getInt("min_lvl"));
                item.setMaxLevel(rs.getInt("max_lvl"));
                item.setBless(rs.getInt("bless"));
                item.setTradable(rs.getInt("trade") == 0 ? true : false);
                item.setCantDelete(rs.getInt("cant_delete") == 1 ? true : false);
                item.setCanSeal(rs.getInt("can_seal") == 1 ? true : false);
                item.setDmgSmall(rs.getInt("dmg_small"));
                item.setDmgLarge(rs.getInt("dmg_large"));
                item.set_stackable(rs.getInt("stackable") == 1 ? true : false);
                item.setMaxChargeCount(rs.getInt("max_charge_count"));
                item.set_locx(rs.getInt("locx"));
                item.set_locy(rs.getInt("locy"));
                item.set_mapid(rs.getShort("mapid"));
                item.set_delayid(rs.getInt("delay_id"));
                item.set_delaytime(rs.getInt("delay_time"));
                item.set_delayEffect(rs.getInt("delay_effect"));
                item.setFoodVolume(rs.getInt("food_volume"));
                item.setToBeSavedAtOnce((rs.getInt("save_at_once") == 1) ? true
                        : false);

                CiteItemClass.getInstance().addList(item_id, class_name, 0);
                result.put(new Integer(item.getItemId()), item);
            }
        } catch (final NullPointerException e) {
            _log.log(Level.SEVERE, new StringBuilder().append(item.getName())
                    .append("(" + item.getItemId() + ")").append("无法加载。")
                    .toString());
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
        return result;
    }

    /**
     * 武器载入
     * 
     * @return
     */
    private Map<Integer, L1Weapon> allWeapon() {
        final Map<Integer, L1Weapon> result = Maps.newMap();

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        L1Weapon weapon = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM weapon");

            rs = pstm.executeQuery();
            while (rs.next()) {
                weapon = new L1Weapon();
                final int item_id = rs.getInt("item_id");
                weapon.setItemId(item_id);
                weapon.setName(rs.getString("name"));
                final String class_name = rs.getString("class_name");
                weapon.setClassName(class_name);
                weapon.setUnidentifiedNameId(rs
                        .getString("unidentified_name_id"));
                weapon.setIdentifiedNameId(rs.getString("identified_name_id"));
                weapon.setType((_weaponTypes.get(rs.getString("type")))
                        .intValue());
                weapon.setType1((_weaponId.get(rs.getString("type")))
                        .intValue());
                weapon.setType2(1);
                weapon.setUseType(1);
                weapon.setMaterial((_materialTypes.get(rs.getString("material")))
                        .intValue());
                weapon.setWeight(rs.getInt("weight"));
                weapon.setGfxId(rs.getInt("invgfx"));
                weapon.setGroundGfxId(rs.getInt("grdgfx"));
                weapon.setItemDescId(rs.getInt("itemdesc_id"));
                weapon.setDmgSmall(rs.getInt("dmg_small"));
                weapon.setDmgLarge(rs.getInt("dmg_large"));
                weapon.setRange(rs.getInt("range"));
                weapon.set_safeenchant(rs.getInt("safenchant"));
                weapon.setUseRoyal(rs.getInt("use_royal") == 0 ? false : true);
                weapon.setUseKnight(rs.getInt("use_knight") == 0 ? false : true);
                weapon.setUseElf(rs.getInt("use_elf") == 0 ? false : true);
                weapon.setUseMage(rs.getInt("use_mage") == 0 ? false : true);
                weapon.setUseDarkelf(rs.getInt("use_darkelf") == 0 ? false
                        : true);
                weapon.setUseDragonknight(rs.getInt("use_dragonknight") == 0 ? false
                        : true);
                weapon.setUseIllusionist(rs.getInt("use_illusionist") == 0 ? false
                        : true);
                weapon.setHitModifier(rs.getInt("hitmodifier"));
                weapon.setDmgModifier(rs.getInt("dmgmodifier"));
                weapon.set_addstr(rs.getByte("add_str"));
                weapon.set_adddex(rs.getByte("add_dex"));
                weapon.set_addcon(rs.getByte("add_con"));
                weapon.set_addint(rs.getByte("add_int"));
                weapon.set_addwis(rs.getByte("add_wis"));
                weapon.set_addcha(rs.getByte("add_cha"));
                weapon.set_addhp(rs.getInt("add_hp"));
                weapon.set_addmp(rs.getInt("add_mp"));
                weapon.set_addhpr(rs.getInt("add_hpr"));
                weapon.set_addmpr(rs.getInt("add_mpr"));
                weapon.set_addsp(rs.getInt("add_sp"));
                weapon.set_mdef(rs.getInt("m_def"));
                weapon.setDoubleDmgChance(rs.getInt("double_dmg_chance"));
                weapon.setMagicDmgModifier(rs.getInt("magicdmgmodifier"));
                weapon.set_canbedmg(rs.getInt("canbedmg"));
                weapon.setMinLevel(rs.getInt("min_lvl"));
                weapon.setMaxLevel(rs.getInt("max_lvl"));
                weapon.setBless(rs.getInt("bless"));
                weapon.setTradable(rs.getInt("trade") == 0 ? true : false);
                weapon.setCantDelete(rs.getInt("cant_delete") == 1 ? true
                        : false);
                weapon.setHasteItem(rs.getInt("haste_item") == 0 ? false : true);
                weapon.setMaxUseTime(rs.getInt("max_use_time"));

                CiteItemClass.getInstance().addList(item_id, class_name, 1);
                result.put(new Integer(weapon.getItemId()), weapon);
            }
        } catch (final NullPointerException e) {
            _log.log(Level.SEVERE, new StringBuilder().append(weapon.getName())
                    .append("(" + weapon.getItemId() + ")").append("无法加载。")
                    .toString());
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);

        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);

        }
        return result;
    }

    /**
     * 构建快速查找表
     */
    private void buildFastLookupTable() {
        int highestId = 0;

        final Collection<L1EtcItem> items = this._etcitems.values();
        for (final L1EtcItem item : items) {
            if (item.getItemId() > highestId) {
                highestId = item.getItemId();
            }
        }

        final Collection<L1Weapon> weapons = this._weapons.values();
        for (final L1Weapon weapon : weapons) {
            if (weapon.getItemId() > highestId) {
                highestId = weapon.getItemId();
            }
        }

        final Collection<L1Armor> armors = this._armors.values();
        for (final L1Armor armor : armors) {
            if (armor.getItemId() > highestId) {
                highestId = armor.getItemId();
            }
        }

        this._allTemplates = new L1Item[highestId + 1];

        for (final Integer id : this._etcitems.keySet()) {
            final L1EtcItem item = this._etcitems.get(id);
            this._allTemplates[id.intValue()] = item;
        }

        for (final Integer id : this._weapons.keySet()) {
            final L1Weapon item = this._weapons.get(id);
            this._allTemplates[id.intValue()] = item;
        }

        for (final Integer id : this._armors.keySet()) {
            final L1Armor item = this._armors.get(id);
            this._allTemplates[id.intValue()] = item;
        }
    }

    /**
     * 产生新物件
     * 
     * @param itemId
     * @return
     */
    public L1ItemInstance createItem(final int itemId) {
        final L1Item temp = this.getTemplate(itemId);
        if (temp == null) {
            return null;
        }
        final L1ItemInstance item = new L1ItemInstance();
        item.setId(IdFactory.getInstance().nextId());
        item.setItem(temp);
        L1World.getInstance().storeObject(item);
        return item;
    }

    /**
     * 依名称 (NameId)找回itemid
     * 
     * @param name
     * @return
     */
    public int findItemIdByName(final String name) {
        int itemid = 0;
        for (final L1Item item : this._allTemplates) {
            if ((item != null) && item.getName().equals(name)) {
                itemid = item.getItemId();
                break;
            }
        }
        return itemid;
    }

    /**
     * 依名称 (中文)找回itemid
     * 
     * @param name
     * @return
     */
    public int findItemIdByNameWithoutSpace(final String name) {
        int itemid = 0;
        for (final L1Item item : this._allTemplates) {
            if ((item != null) && item.getName().replace(" ", "").equals(name)) {
                itemid = item.getItemId();
                break;
            }
        }
        return itemid;
    }

    /**
     * 传回指定编号物品资料
     * 
     * @param id
     * @return
     */
    public L1Item getTemplate(final int id) {
        return this._allTemplates[id];
    }

    /**
     * 传回指定名称物品资料
     * 
     * @param nameid
     * @return
     */
    public L1Item getTemplate(final String nameid) {
        for (final L1Item item : this._allTemplates) {
            if ((item != null) && item.getNameId().equals(nameid)) {
                return item;
            }
        }
        return null;
    }
}
