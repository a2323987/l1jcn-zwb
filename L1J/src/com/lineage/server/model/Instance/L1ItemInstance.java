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

import static com.lineage.server.model.skill.L1SkillId.BLESS_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.ENCHANT_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.HOLY_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.SHADOW_FANG;

import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

import com.lineage.server.datatables.NpcTable;
import com.lineage.server.datatables.PetItemTable;
import com.lineage.server.datatables.PetTable;
import com.lineage.server.model.L1EquipmentTimer;
import com.lineage.server.model.L1ItemOwnerTimer;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.identity.L1ArmorId;
import com.lineage.server.serverpackets.S_OwnCharStatus;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Armor;
import com.lineage.server.templates.L1Item;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.templates.L1Pet;
import com.lineage.server.templates.L1PetItem;
import com.lineage.server.utils.BinaryOutputStream;

// Referenced classes of package com.lineage.server.model:
// L1Object, L1PcInstance

/**
 * 道具类控制项
 */
public class L1ItemInstance extends L1Object {

    class EnchantTimer extends TimerTask {

        public EnchantTimer() {
        }

        @Override
        public void run() {
            try {
                final int type = L1ItemInstance.this.getItem().getType();
                final int type2 = L1ItemInstance.this.getItem().getType2();
                final int itemId = L1ItemInstance.this.getItem().getItemId();
                if ((L1ItemInstance.this._pc != null)
                        && L1ItemInstance.this._pc.getInventory().checkItem(
                                itemId)) {
                    if ((type == 2) && (type2 == 2)
                            && L1ItemInstance.this.isEquipped()) {
                        L1ItemInstance.this._pc.addAc(3);
                        L1ItemInstance.this._pc
                                .sendPackets(new S_OwnCharStatus(
                                        L1ItemInstance.this._pc));
                    }
                }
                L1ItemInstance.this.setAcByMagic(0);
                L1ItemInstance.this.setDmgByMagic(0);
                L1ItemInstance.this.setHolyDmgByMagic(0);
                L1ItemInstance.this.setHitByMagic(0);
                L1ItemInstance.this._pc.sendPackets(new S_ServerMessage(308,
                        L1ItemInstance.this.getLogName()));
                L1ItemInstance.this._isRunning = false;
                L1ItemInstance.this._timer = null;
            } catch (final Exception e) {
            }
        }
    }

    /**
     * 前回DBへ保存した際のアイテムのステータスを格納するクラス
     */
    public class LastStatus {
        public int count;

        public int itemId;

        public boolean isEquipped = false;

        public int enchantLevel;

        public boolean isIdentified = true;

        public int durability;

        public int chargeCount;

        public int remainingTime;

        public Timestamp lastUsed = null;

        public int bless;

        public int attrEnchantKind;

        public int attrEnchantLevel;

        public int firemr; // Scroll of Enchant Accessory

        public int watermr;

        public int earthmr;

        public int windmr;

        public int addhp;

        public int addmp;

        public int hpr;

        public int mpr;

        public int addsp;

        public int m_def;

        public void updateaddHp() {
            this.addhp = L1ItemInstance.this.getaddHp();
        }

        public void updateaddMp() {
            this.addmp = L1ItemInstance.this.getaddMp();
        }

        public void updateAll() {
            this.count = L1ItemInstance.this.getCount();
            this.itemId = L1ItemInstance.this.getItemId();
            this.isEquipped = L1ItemInstance.this.isEquipped();
            this.isIdentified = L1ItemInstance.this.isIdentified();
            this.enchantLevel = L1ItemInstance.this.getEnchantLevel();
            this.durability = L1ItemInstance.this.get_durability();
            this.chargeCount = L1ItemInstance.this.getChargeCount();
            this.remainingTime = L1ItemInstance.this.getRemainingTime();
            this.lastUsed = L1ItemInstance.this.getLastUsed();
            this.bless = L1ItemInstance.this.getBless();
            this.attrEnchantKind = L1ItemInstance.this.getAttrEnchantKind();
            this.attrEnchantLevel = L1ItemInstance.this.getAttrEnchantLevel();
            this.firemr = L1ItemInstance.this.getFireMr();
            this.watermr = L1ItemInstance.this.getWaterMr();
            this.earthmr = L1ItemInstance.this.getEarthMr();
            this.windmr = L1ItemInstance.this.getWindMr();
            this.addhp = L1ItemInstance.this.getaddHp();
            this.addmp = L1ItemInstance.this.getaddMp();
            this.addsp = L1ItemInstance.this.getaddSp();
            this.hpr = L1ItemInstance.this.getHpr();
            this.mpr = L1ItemInstance.this.getMpr();
            this.m_def = L1ItemInstance.this.getM_Def();
        }

        public void updateAttrEnchantKind() {
            this.attrEnchantKind = L1ItemInstance.this.getAttrEnchantKind();
        }

        public void updateAttrEnchantLevel() {
            this.attrEnchantLevel = L1ItemInstance.this.getAttrEnchantLevel();
        }

        public void updateBless() {
            this.bless = L1ItemInstance.this.getBless();
        }

        public void updateChargeCount() {
            this.chargeCount = L1ItemInstance.this.getChargeCount();
        }

        public void updateCount() {
            this.count = L1ItemInstance.this.getCount();
        }

        public void updateDuraility() {
            this.durability = L1ItemInstance.this.get_durability();
        }

        public void updateEarthMr() {
            this.earthmr = L1ItemInstance.this.getEarthMr();
        }

        public void updateEnchantLevel() {
            this.enchantLevel = L1ItemInstance.this.getEnchantLevel();
        }

        public void updateEquipped() {
            this.isEquipped = L1ItemInstance.this.isEquipped();
        }

        public void updateFireMr() {
            this.firemr = L1ItemInstance.this.getFireMr();
        }

        public void updateHpr() {
            this.hpr = L1ItemInstance.this.getHpr();
        }

        public void updateIdentified() {
            this.isIdentified = L1ItemInstance.this.isIdentified();
        }

        public void updateItemId() {
            this.itemId = L1ItemInstance.this.getItemId();
        }

        public void updateLastUsed() {
            this.lastUsed = L1ItemInstance.this.getLastUsed();
        }

        public void updateM_Def() {
            this.m_def = L1ItemInstance.this.getM_Def();
        }

        public void updateMpr() {
            this.mpr = L1ItemInstance.this.getMpr();
        }

        public void updateRemainingTime() {
            this.remainingTime = L1ItemInstance.this.getRemainingTime();
        }

        public void updateSp() {
            this.addsp = L1ItemInstance.this.getaddSp();
        }

        public void updateWaterMr() {
            this.watermr = L1ItemInstance.this.getWaterMr();
        }

        public void updateWindMr() {
            this.windmr = L1ItemInstance.this.getWindMr();
        }
    }

    private static final long serialVersionUID = 1L;

    private int _count;

    private int _itemId;

    private L1Item _item;

    private boolean _isEquipped = false;

    private int _enchantLevel;

    private boolean _isIdentified = false;

    private int _durability;

    private int _chargeCount;

    private int _remainingTime;

    private Timestamp _lastUsed = null;

    private int _lastWeight;

    private final LastStatus _lastStatus = new LastStatus();

    L1PcInstance _pc;

    boolean _isRunning = false;

    EnchantTimer _timer;

    private int _bless;

    private int _attrEnchantKind;

    private int _attrEnchantLevel;

    private int _acByMagic = 0;

    private int _dmgByMagic = 0;

    private int _holyDmgByMagic = 0;

    private int _hitByMagic = 0;

    private int _FireMr = 0;

    private int _WaterMr = 0;

    private int _EarthMr = 0;

    private int _WindMr = 0;

    private int _M_Def = 0;

    private int _Mpr = 0;

    private int _Hpr = 0;

    private int _addHp = 0;

    private int _addMp = 0;

    private int _addSp = 0;

    private int _itemOwnerId = 0;

    private L1EquipmentTimer _equipmentTimer;

    private boolean _isNowLighting = false;

    // 旅馆钥匙
    private int _keyId = 0;

    private int _innNpcId = 0;

    private boolean _isHall;

    private Timestamp _dueTime;

    public L1ItemInstance() {
        this._count = 1;
        this._enchantLevel = 0;
    }

    /**
     * 道具类控制项
     * 
     * @param item
     * @param count
     */
    public L1ItemInstance(final L1Item item, final int count) {
        this();
        this.setItem(item);
        this.setCount(count);
    }

    public boolean checkRoomOrHall() {
        return this._isHall;
    }

    /** 取得耐久度 */
    public int get_durability() {
        return this._durability;
    }

    /** 动画ID */
    public int get_gfxid() {
        return this._item.getGfxId();
    }

    public int getAcByMagic() {
        return this._acByMagic;
    }

    public int getaddHp() {
        return this._addHp;
    }

    public int getaddMp() {
        return this._addMp;
    }

    public int getaddSp() {
        return this._addSp;
    }

    /** 取得属性加成种类 */
    public int getAttrEnchantKind() {
        return this._attrEnchantKind;
    }

    /** 取得属性加成级别 */
    public int getAttrEnchantLevel() {
        return this._attrEnchantLevel;
    }

    /** 取得封印 */
    public int getBless() {
        return this._bless;
    }

    /**  */
    public int getChargeCount() {
        return this._chargeCount;
    }

    /**
     * 返回道具的个数。
     * 
     * @return 道具的个数
     */
    public int getCount() {
        return this._count;
    }

    public int getDmgByMagic() {
        return this._dmgByMagic;
    }

    public Timestamp getDueTime() {
        return this._dueTime;
    }

    public int getEarthMr() {
        return this._EarthMr;
    }

    /** 取得加成等级 */
    public int getEnchantLevel() {
        return this._enchantLevel;
    }

    public int getFireMr() {
        return this._FireMr;
    }

    public int getHitByMagic() {
        return this._hitByMagic;
    }

    public int getHolyDmgByMagic() {
        return this._holyDmgByMagic;
    }

    public int getHpr() {
        return this._Hpr;
    }

    // 旅馆钥匙
    public String getInnKeyName() {
        final StringBuilder name = new StringBuilder();
        name.append(" #");
        final String chatText = String.valueOf(this.getKeyId());
        String s1 = "";
        String s2 = "";
        for (int i = 0; i < chatText.length(); i++) {
            if (i >= 5) {
                break;
            }
            s1 = s1 + String.valueOf(chatText.charAt(i));
        }
        name.append(s1);
        for (int i = 0; i < chatText.length(); i++) {
            if ((i % 2) == 0) {
                s1 = String.valueOf(chatText.charAt(i));
            } else {
                s2 = s1 + String.valueOf(chatText.charAt(i));
                name.append(Integer.toHexString(Integer.valueOf(s2))
                        .toLowerCase()); // 转成16进位
            }
        }
        return name.toString();
    }

    public int getInnNpcId() {
        return this._innNpcId;
    }

    /** 获得道具 */
    public L1Item getItem() {
        return this._item;
    }

    /** 取得道具ID */
    public int getItemId() {
        return this._itemId;
    }

    public int getItemOwnerId() {
        return this._itemOwnerId;
    }

    public int getKeyId() {
        return this._keyId;
    }

    public LastStatus getLastStatus() {
        return this._lastStatus;
    }

    /** 取得最后使用 */
    public Timestamp getLastUsed() {
        return this._lastUsed;
    }

    /** 取得最后重量 */
    public int getLastWeight() {
        return this._lastWeight;
    }

    /**
     * 返回在日志中出现的名称。<br>
     * 例:Adena（250）/6匕首
     */
    public String getLogName() {
        return this.getNumberedName(this._count);
    }

    public int getM_Def() {
        return this._M_Def;
    }

    public int getMpr() {
        return this._Mpr;
    }

    /** 取得魔防 */
    public int getMr() {
        int mr = this._item.get_mdef();
        if ((this.getItemId() == L1ArmorId.HELMET_OF_MAGIC_RESISTANCE)
                || (this.getItemId() == L1ArmorId.CHAIN_MAIL_OF_MAGIC_RESISTANCE) // 抗魔法頭盔、抗魔法鏈甲
                || ((this.getItemId() >= L1ArmorId.ELITE_PLATE_MAIL_OF_LINDVIOR) && (this
                        .getItemId() <= L1ArmorId.ELITE_SCALE_MAIL_OF_LINDVIOR)) // 林德拜爾的力量、林德拜爾的魅惑、林德拜爾的泉源、林德拜爾的霸氣
                || (this.getItemId() == L1ArmorId.B_HELMET_OF_MAGIC_RESISTANCE)) { // 受祝福的
                                                                                   // 抗魔法頭盔
            mr += this.getEnchantLevel();
        }
        if ((this.getItemId() == L1ArmorId.CLOAK_OF_MAGIC_RESISTANCE)
                || (this.getItemId() == L1ArmorId.B_CLOAK_OF_MAGIC_RESISTANCE) // 抗魔法斗篷、受祝福的
                                                                               // 抗魔法斗篷
                || (this.getItemId() == L1ArmorId.C_CLOAK_OF_MAGIC_RESISTANCE)) { // 受咀咒的
                                                                                  // 抗魔法斗篷
            mr += this.getEnchantLevel() * 2;
        }
        // 饰品强化效果
        if (this.getM_Def() != 0) {
            mr += this.getM_Def();
        }
        return mr;
    }

    /** 获得名称 */
    public String getName() {
        return this._item.getName();
    }

    /**
     * 获得在日志中出现的指定的名称和数量。
     */
    public String getNumberedName(final int count) {
        final StringBuilder name = new StringBuilder();

        if (this.isIdentified()) {
            if (this.getItem().getType2() == 1) { // 武器
                final int attrEnchantLevel = this.getAttrEnchantLevel();
                if (attrEnchantLevel > 0) {
                    String attrStr = null;
                    switch (this.getAttrEnchantKind()) {
                        case 1: // 地
                            if (attrEnchantLevel == 1) {
                                attrStr = "$6124";
                            } else if (attrEnchantLevel == 2) {
                                attrStr = "$6125";
                            } else if (attrEnchantLevel == 3) {
                                attrStr = "$6126";
                            }
                            break;
                        case 2: // 火
                            if (attrEnchantLevel == 1) {
                                attrStr = "$6115";
                            } else if (attrEnchantLevel == 2) {
                                attrStr = "$6116";
                            } else if (attrEnchantLevel == 3) {
                                attrStr = "$6117";
                            }
                            break;
                        case 4: // 水
                            if (attrEnchantLevel == 1) {
                                attrStr = "$6118";
                            } else if (attrEnchantLevel == 2) {
                                attrStr = "$6119";
                            } else if (attrEnchantLevel == 3) {
                                attrStr = "$6120";
                            }
                            break;
                        case 8: // 风
                            if (attrEnchantLevel == 1) {
                                attrStr = "$6121";
                            } else if (attrEnchantLevel == 2) {
                                attrStr = "$6122";
                            } else if (attrEnchantLevel == 3) {
                                attrStr = "$6123";
                            }
                            break;
                        default:
                            break;
                    }
                    name.append(attrStr + " ");
                }
            }
            if ((this.getItem().getType2() == 1)
                    || (this.getItem().getType2() == 2)) { // 武器・防具
                if (this.getEnchantLevel() >= 0) {
                    name.append("+" + this.getEnchantLevel() + " ");
                } else if (this.getEnchantLevel() < 0) {
                    name.append(String.valueOf(this.getEnchantLevel()) + " ");
                }
            }
        }

        // 鉴定
        if (this.isIdentified()) {
            name.append(this._item.getIdentifiedNameId());
        } else {
            name.append(this._item.getUnidentifiedNameId());
        }

        // 如果鉴定过
        if (this.isIdentified()) {
            if (this.getItem().getMaxChargeCount() > 0) {
                name.append(" (" + this.getChargeCount() + ")");
            }
            if (this.getItem().getItemId() == 20383) { // 军马头盔
                name.append(" (" + this.getChargeCount() + ")");
            }
            if ((this.getItem().getMaxUseTime() > 0)
                    && (this.getItem().getType2() != 0)) { // 有使用时间限制的武器防具
                name.append(" [" + this.getRemainingTime() + "]");
            }
        }

        // 旅馆钥匙
        if ((this.getItem().getItemId() == 40312) && (this.getKeyId() != 0)) {
            name.append(this.getInnKeyName());
        }

        if (count > 1) {
            name.append(" (" + count + ")");
        }

        return name.toString();
    }

    /**
     * 获得道具在仓库和背包内显示的名称与数量。<br>
     */
    public String getNumberedViewName(final int count) {
        final StringBuilder name = new StringBuilder(
                this.getNumberedName(count));
        final int itemType2 = this.getItem().getType2();
        final int itemId = this.getItem().getItemId();

        // 宠物项圈
        if ((itemId == 40314) || (itemId == 40316)) {
            final L1Pet pet = PetTable.getInstance().getTemplate(this.getId());
            if (pet != null) {
                final L1Npc npc = NpcTable.getInstance().getTemplate(
                        pet.get_npcid());
                // name.append("[Lv." + pet.get_level() + " "
                // + npc.get_nameid() + "]");
                name.append("[Lv." + pet.get_level() + " " + pet.get_name()
                        + "]HP" + pet.get_hp() + " " + npc.get_nameid());
            }
        }

        // light系道具(灯)
        if ((this.getItem().getType2() == 0) && (this.getItem().getType() == 2)) {
            if (this.isNowLighting()) {
                name.append(" ($10)");
            }
            if ((itemId == 40001) || (itemId == 40002)) { // 灯or灯笼
                if (this.getRemainingTime() <= 0) {
                    name.append(" ($11)");
                }
            }
        }

        // 已经装备上
        if (this.isEquipped()) {
            if (itemType2 == 1) {
                name.append(" ($9)"); // 装備(Armed)
            } else if (itemType2 == 2) {
                name.append(" ($117)"); // 装備(Worn)
            }
        }
        return name.toString();
    }

    /**
     * 前回DBに保存した時から変化しているカラムをビット集合として返す。
     */
    public int getRecordingColumns() {
        int column = 0;

        if (this.getCount() != this._lastStatus.count) {
            column += L1PcInventory.COL_COUNT;
        }
        if (this.getItemId() != this._lastStatus.itemId) {
            column += L1PcInventory.COL_ITEMID;
        }
        if (this.isEquipped() != this._lastStatus.isEquipped) {
            column += L1PcInventory.COL_EQUIPPED;
        }
        if (this.getEnchantLevel() != this._lastStatus.enchantLevel) {
            column += L1PcInventory.COL_ENCHANTLVL;
        }
        if (this.get_durability() != this._lastStatus.durability) {
            column += L1PcInventory.COL_DURABILITY;
        }
        if (this.getChargeCount() != this._lastStatus.chargeCount) {
            column += L1PcInventory.COL_CHARGE_COUNT;
        }
        if (this.getLastUsed() != this._lastStatus.lastUsed) {
            column += L1PcInventory.COL_DELAY_EFFECT;
        }
        if (this.isIdentified() != this._lastStatus.isIdentified) {
            column += L1PcInventory.COL_IS_ID;
        }
        if (this.getRemainingTime() != this._lastStatus.remainingTime) {
            column += L1PcInventory.COL_REMAINING_TIME;
        }
        if (this.getBless() != this._lastStatus.bless) {
            column += L1PcInventory.COL_BLESS;
        }
        if (this.getAttrEnchantKind() != this._lastStatus.attrEnchantKind) {
            column += L1PcInventory.COL_ATTR_ENCHANT_KIND;
        }
        if (this.getAttrEnchantLevel() != this._lastStatus.attrEnchantLevel) {
            column += L1PcInventory.COL_ATTR_ENCHANT_LEVEL;
        }

        return column;
    }

    public int getRecordingColumnsEnchantAccessory() {
        int column = 0;

        if (this.getaddHp() != this._lastStatus.addhp) {
            column += L1PcInventory.COL_ADDHP;
        }
        if (this.getaddMp() != this._lastStatus.addmp) {
            column += L1PcInventory.COL_ADDMP;
        }
        if (this.getHpr() != this._lastStatus.hpr) {
            column += L1PcInventory.COL_HPR;
        }
        if (this.getMpr() != this._lastStatus.mpr) {
            column += L1PcInventory.COL_MPR;
        }
        if (this.getaddSp() != this._lastStatus.addsp) {
            column += L1PcInventory.COL_ADDSP;
        }
        if (this.getM_Def() != this._lastStatus.m_def) {
            column += L1PcInventory.COL_M_DEF;
        }
        if (this.getEarthMr() != this._lastStatus.earthmr) {
            column += L1PcInventory.COL_EARTHMR;
        }
        if (this.getFireMr() != this._lastStatus.firemr) {
            column += L1PcInventory.COL_FIREMR;
        }
        if (this.getWaterMr() != this._lastStatus.watermr) {
            column += L1PcInventory.COL_WATERMR;
        }
        if (this.getWindMr() != this._lastStatus.windmr) {
            column += L1PcInventory.COL_WINDMR;
        }

        return column;
    }

    /** 取得剩余时间 */
    public int getRemainingTime() {
        return this._remainingTime;
    }

    /**
     * アイテムの状態からサーバーパケットで利用する形式のバイト列を生成し、返す。
     */
    public byte[] getStatusBytes() {
        final int itemType2 = this.getItem().getType2();
        final int itemId = this.getItemId();
        final BinaryOutputStream os = new BinaryOutputStream();
        final L1PetItem petItem = PetItemTable.getInstance()
                .getTemplate(itemId);

        if (petItem != null) { // 宠物装备
            if (petItem.getUseType() == 1) { // 牙齿
                os.writeC(7); // 可使用职业：
                os.writeC(128); // [高等宠物]
                os.writeC(23); // 材质
                os.writeC(this.getItem().getMaterial());
                os.writeD(this.getWeight());
            } else { // 盔甲
                     // AC
                os.writeC(19);
                int ac = petItem.getAddAc();
                if (ac < 0) {
                    ac = ac - ac - ac;
                }
                os.writeC(ac);
                os.writeC(this.getItem().getMaterial());
                os.writeC(-1); // 饰品级别 - 0:上等 1:中等 2:初级 3:特等
                os.writeD(this.getWeight());

                os.writeC(7); // 可使用职业：
                os.writeC(128); // [高等宠物]

                // STR~CHA
                if (petItem.getAddStr() != 0) {
                    os.writeC(8);
                    os.writeC(petItem.getAddStr());
                }
                if (petItem.getAddDex() != 0) {
                    os.writeC(9);
                    os.writeC(petItem.getAddDex());
                }
                if (petItem.getAddCon() != 0) {
                    os.writeC(10);
                    os.writeC(petItem.getAddCon());
                }
                if (petItem.getAddWis() != 0) {
                    os.writeC(11);
                    os.writeC(petItem.getAddWis());
                }
                if (petItem.getAddInt() != 0) {
                    os.writeC(12);
                    os.writeC(petItem.getAddInt());
                }
                // HP, MP
                if (petItem.getAddHp() != 0) {
                    os.writeC(14);
                    os.writeH(petItem.getAddHp());
                }
                if (petItem.getAddMp() != 0) {
                    os.writeC(32);
                    os.writeC(petItem.getAddMp());
                }
                // MR
                if (petItem.getAddMr() != 0) {
                    os.writeC(15);
                    os.writeH(petItem.getAddMr());
                }
                // SP(魔力)
                if (petItem.getAddSp() != 0) {
                    os.writeC(17);
                    os.writeC(petItem.getAddSp());
                }
            }
        } else if (itemType2 == 0) { // etcitem
            switch (this.getItem().getType()) {
                case 2: // light
                    os.writeC(22); // 亮度
                    os.writeH(this.getItem().getLightRange());
                    break;
                case 7: // food
                    os.writeC(21);
                    // 营养
                    os.writeH(this.getItem().getFoodVolume());
                    break;
                case 0: // arrow
                case 15: // sting
                    os.writeC(1); // 打击值
                    os.writeC(this.getItem().getDmgSmall());
                    os.writeC(this.getItem().getDmgLarge());
                    break;
                default:
                    os.writeC(23); // 材质
                    break;
            }
            os.writeC(this.getItem().getMaterial());
            os.writeD(this.getWeight());
        } else if ((itemType2 == 1) || (itemType2 == 2)) { // weapon | armor
            if (itemType2 == 1) { // weapon
                // 打击值
                os.writeC(1);
                os.writeC(this.getItem().getDmgSmall());
                os.writeC(this.getItem().getDmgLarge());
                os.writeC(this.getItem().getMaterial());
                os.writeD(this.getWeight());
            } else if (itemType2 == 2) { // armor
                // AC
                os.writeC(19);
                int ac = ((L1Armor) this.getItem()).get_ac();
                if (ac < 0) {
                    ac = ac - ac - ac;
                }
                os.writeC(ac);
                os.writeC(this.getItem().getMaterial());
                os.writeC(this.getItem().getGrade()); // 饰品级别 - 0:上等 1:中等 2:初级
                                                      // 3:特等
                os.writeD(this.getWeight());
            }
            /** 强化数判断 */
            if (this.getEnchantLevel() != 0) {
                os.writeC(2);
                /** 饰品强化卷轴 */
                if ((this.getItem().getType2() == 2)
                        && (this.getItem().getType() >= 8)
                        && (this.getItem().getType() <= 12)) { // 8:项链 9:戒指1
                                                               // 10:腰带
                    // 11:戒指2 12:耳环
                    os.writeC(0);
                } else {
                    os.writeC(this.getEnchantLevel());
                }
            }
            // 损坏程度
            if (this.get_durability() != 0) {
                os.writeC(3);
                os.writeC(this.get_durability());
            }
            // 双手武器
            if (this.getItem().isTwohandedWeapon()) {
                os.writeC(4);
            }
            // 攻击成功
            if (itemType2 == 1) { // weapon
                if (this.getItem().getHitModifier() != 0) {
                    os.writeC(5);
                    os.writeC(this.getItem().getHitModifier());
                }
            } else if (itemType2 == 2) { // armor
                if (this.getItem().getHitModifierByArmor() != 0) {
                    os.writeC(5);
                    os.writeC(this.getItem().getHitModifierByArmor());
                }
            }
            // 追加打击
            if (itemType2 == 1) { // weapon
                if (this.getItem().getDmgModifier() != 0) {
                    os.writeC(6);
                    os.writeC(this.getItem().getDmgModifier());
                }
            } else if (itemType2 == 2) { // armor
                if (this.getItem().getDmgModifierByArmor() != 0) {
                    os.writeC(6);
                    os.writeC(this.getItem().getDmgModifierByArmor());
                }
            }
            // 使用可能
            int bit = 0;
            bit |= this.getItem().isUseRoyal() ? 1 : 0;
            bit |= this.getItem().isUseKnight() ? 2 : 0;
            bit |= this.getItem().isUseElf() ? 4 : 0;
            bit |= this.getItem().isUseMage() ? 8 : 0;
            bit |= this.getItem().isUseDarkelf() ? 16 : 0;
            bit |= this.getItem().isUseDragonknight() ? 32 : 0;
            bit |= this.getItem().isUseIllusionist() ? 64 : 0;
            // bit |= getItem().isUseHiPet() ? 64 : 0; // ハイペット
            os.writeC(7);
            os.writeC(bit);
            // 弓の命中率補正
            if (this.getItem().getBowHitModifierByArmor() != 0) {
                os.writeC(24);
                os.writeC(this.getItem().getBowHitModifierByArmor());
            }
            // 弓の伤害值補正
            if (this.getItem().getBowDmgModifierByArmor() != 0) {
                os.writeC(35);
                os.writeC(this.getItem().getBowDmgModifierByArmor());
            }
            // MP吸収
            if ((itemId == 126) || (itemId == 127)) { // 玛那魔杖、钢铁玛那魔杖
                os.writeC(16);
            }
            // HP吸収
            if (itemId == 262) { // 毁灭巨剑
                os.writeC(34);
            }
            // STR~CHA
            if (this.getItem().get_addstr() != 0) {
                os.writeC(8);
                os.writeC(this.getItem().get_addstr());
            }
            if (this.getItem().get_adddex() != 0) {
                os.writeC(9);
                os.writeC(this.getItem().get_adddex());
            }
            if (this.getItem().get_addcon() != 0) {
                os.writeC(10);
                os.writeC(this.getItem().get_addcon());
            }
            if (this.getItem().get_addwis() != 0) {
                os.writeC(11);
                os.writeC(this.getItem().get_addwis());
            }
            if (this.getItem().get_addint() != 0) {
                os.writeC(12);
                os.writeC(this.getItem().get_addint());
            }
            if (this.getItem().get_addcha() != 0) {
                os.writeC(13);
                os.writeC(this.getItem().get_addcha());
            }
            // HP, MP
            if ((this.getItem().get_addhp() != 0) || (this.getaddHp() != 0)) {
                os.writeC(14);
                os.writeH(this.getItem().get_addhp() + this.getaddHp());
            }
            if ((this.getItem().get_addmp() != 0) || (this.getaddMp() != 0)) {
                os.writeC(32);
                os.writeC(this.getItem().get_addmp() + this.getaddMp());
            }
            // SP(魔力)
            if ((this.getItem().get_addsp() != 0) || (this.getaddSp() != 0)) {
                os.writeC(17);
                os.writeC(this.getItem().get_addsp() + this.getaddSp());
            }
            // 加速道具
            if (this.getItem().isHasteItem()) {
                os.writeC(18);
            }
            // 火の属性
            if ((this.getItem().get_defense_fire() != 0)
                    || (this.getFireMr() != 0)) {
                os.writeC(27);
                os.writeC(this.getItem().get_defense_fire() + this.getFireMr());
            }
            // 水の属性
            if ((this.getItem().get_defense_water() != 0)
                    || (this.getWaterMr() != 0)) {
                os.writeC(28);
                os.writeC(this.getItem().get_defense_water()
                        + this.getWaterMr());
            }
            // 风の属性
            if ((this.getItem().get_defense_wind() != 0)
                    || (this.getWindMr() != 0)) {
                os.writeC(29);
                os.writeC(this.getItem().get_defense_wind() + this.getWindMr());
            }
            // 地の属性
            if ((this.getItem().get_defense_earth() != 0)
                    || (this.getEarthMr() != 0)) {
                os.writeC(30);
                os.writeC(this.getItem().get_defense_earth()
                        + this.getEarthMr());
            }
            // 冻结耐性
            if (this.getItem().get_regist_freeze() != 0) {
                os.writeC(15);
                os.writeH(this.getItem().get_regist_freeze());
                os.writeC(33);
                os.writeC(1);
            }
            // 石化耐性
            if (this.getItem().get_regist_stone() != 0) {
                os.writeC(15);
                os.writeH(this.getItem().get_regist_stone());
                os.writeC(33);
                os.writeC(2);
            }
            // 睡眠耐性
            if (this.getItem().get_regist_sleep() != 0) {
                os.writeC(15);
                os.writeH(this.getItem().get_regist_sleep());
                os.writeC(33);
                os.writeC(3);
            }
            // 暗闇耐性
            if (this.getItem().get_regist_blind() != 0) {
                os.writeC(15);
                os.writeH(this.getItem().get_regist_blind());
                os.writeC(33);
                os.writeC(4);
            }
            // 昏迷耐性
            if (this.getItem().get_regist_stun() != 0) {
                os.writeC(15);
                os.writeH(this.getItem().get_regist_stun());
                os.writeC(33);
                os.writeC(5);
            }
            // 支撑耐性
            if (this.getItem().get_regist_sustain() != 0) {
                os.writeC(15);
                os.writeH(this.getItem().get_regist_sustain());
                os.writeC(33);
                os.writeC(6);
            }
            // MR
            if (this.getMr() != 0) {
                os.writeC(15);
                os.writeH(this.getMr());
            }

            // 装备经验值加成
            if (this.getItem().getExpBonus() != 0) {
                os.writeC(36);
                os.writeC((int) ((this.getItem().getExpBonus() - 1.0) * 100));
            }

            // 体力恢复率
            if ((this.getItem().get_addhpr() != 0) || (this.getHpr() != 0)) {
                os.writeC(37);
                os.writeC(this.getItem().get_addhpr() + this.getHpr());
            }
            // 魔力恢复率
            if ((this.getItem().get_addmpr() != 0) || (this.getMpr() != 0)) {
                os.writeC(38);
                os.writeC(this.getItem().get_addmpr() + this.getMpr());
            }
            // 幸运
            // if (getItem.getLuck() != 0) {
            // os.writeC(20);
            // os.writeC(val);
            // }
            // 种类
            // if (getItem.getDesc() != 0) {
            // os.writeC(25);
            // os.writeH(val); // desc.tbl ID
            // }
            // 等级
            // if (getItem.getLevel() != 0) {
            // os.writeC(26);
            // os.writeH(val);
            // }
        }
        return os.getBytes();
    }

    /**
     * 返回道具在仓库与背包内显示的名称。<br>
     * 例:+10 武士刀 (装备)
     */
    public String getViewName() {
        return this.getNumberedViewName(this._count);
    }

    public int getWaterMr() {
        return this._WaterMr;
    }

    /** 获得重量 */
    public int getWeight() {
        if (this.getItem().getWeight() == 0) {
            return 0;
        }
        return Math.max(this.getCount() * this.getItem().getWeight() / 1000, 1);
    }

    public int getWindMr() {
        return this._WindMr;
    }

    /**
     * 返回道具是否已装备。
     * 
     * @return 如果已经装备true、没有装备false。
     */
    public boolean isEquipped() {
        return this._isEquipped;
    }

    /**
     * 返回道具的状态(鉴定)。
     * 
     * @return 鉴定true、未鉴定false。
     */
    public boolean isIdentified() {
        return this._isIdentified;
    }

    public boolean isNowLighting() {
        return this._isNowLighting;
    }

    /** 是可堆叠 */
    public boolean isStackable() {
        return this._item.isStackable();
    }

    @Override
    public void onAction(final L1PcInstance player) {
    }

    /**
     * 耐久性、许可的范围值0~127。
     */
    public void set_durability(int i) {
        if (i < 0) {
            i = 0;
        }

        if (i > 127) {
            i = 127;
        }
        this._durability = i;
    }

    public void setAcByMagic(final int i) {
        this._acByMagic = i;
    }

    public void setaddHp(final int i) {
        this._addHp = i;
    }

    public void setaddMp(final int i) {
        this._addMp = i;
    }

    public void setaddSp(final int i) {
        this._addSp = i;
    }

    /** 设定属性加成种类 */
    public void setAttrEnchantKind(final int i) {
        this._attrEnchantKind = i;
    }

    /** 设定属性加成级别 */
    public void setAttrEnchantLevel(final int i) {
        this._attrEnchantLevel = i;
    }

    /** 设定封印 */
    public void setBless(final int i) {
        this._bless = i;
    }

    /**  */
    public void setChargeCount(final int i) {
        this._chargeCount = i;
    }

    /**
     * 设置道具的个数。
     * 
     * @param count
     *            道具的个数
     */
    public void setCount(final int count) {
        this._count = count;
    }

    public void setDmgByMagic(final int i) {
        this._dmgByMagic = i;
    }

    public void setDueTime(final Timestamp i) {
        this._dueTime = i;
    }

    public void setEarthMr(final int i) {
        this._EarthMr = i;
    }

    /** 设定加成等级 */
    public void setEnchantLevel(final int enchantLevel) {
        this._enchantLevel = enchantLevel;
    }

    /**
     * 设置道具是否已装备。
     * 
     * @param equipped
     *            如果已经装备true、没有装备false。
     */
    public void setEquipped(final boolean equipped) {
        this._isEquipped = equipped;
    }

    public void setFireMr(final int i) {
        this._FireMr = i;
    }

    public void setHall(final boolean i) {
        this._isHall = i;
    }

    public void setHitByMagic(final int i) {
        this._hitByMagic = i;
    }

    public void setHolyDmgByMagic(final int i) {
        this._holyDmgByMagic = i;
    }

    public void setHpr(final int i) {
        this._Hpr = i;
    }

    /**
     * 设置道具的状态(鉴定)。
     * 
     * @param identified
     *            鉴定true、未鉴定false。
     */
    public void setIdentified(final boolean identified) {
        this._isIdentified = identified;
    }

    public void setInnNpcId(final int i) {
        this._innNpcId = i;
    }

    /** 设置道具 */
    public void setItem(final L1Item item) {
        this._item = item;
        this._itemId = item.getItemId();
    }

    /** 设定道具ID */
    public void setItemId(final int itemId) {
        this._itemId = itemId;
    }

    public void setItemOwnerId(final int i) {
        this._itemOwnerId = i;
    }

    public void setKeyId(final int i) {
        this._keyId = i;
    }

    /** 设定最后使用 */
    public void setLastUsed(final Timestamp t) {
        this._lastUsed = t;
    }

    /** 设定最后重量 */
    public void setLastWeight(final int weight) {
        this._lastWeight = weight;
    }

    public void setM_Def(final int i) {
        this._M_Def = i;
    }

    public void setMpr(final int i) {
        this._Mpr = i;
    }

    public void setNowLighting(final boolean flag) {
        this._isNowLighting = flag;
    }

    /** 设定剩余时间 */
    public void setRemainingTime(final int i) {
        this._remainingTime = i;
    }

    public void setSkillArmorEnchant(final L1PcInstance pc, final int skillId,
            final int skillTime) {
        final int type = this.getItem().getType();
        final int type2 = this.getItem().getType2();
        if (this._isRunning) {
            this._timer.cancel();
            final int itemId = this.getItem().getItemId();
            if ((pc != null) && pc.getInventory().checkItem(itemId)) {
                if ((type == 2) && (type2 == 2) && this.isEquipped()) {
                    pc.addAc(3);
                    pc.sendPackets(new S_OwnCharStatus(pc));
                }
            }
            this.setAcByMagic(0);
            this._isRunning = false;
            this._timer = null;
        }

        if ((type == 2) && (type2 == 2) && this.isEquipped()) {
            pc.addAc(-3);
            pc.sendPackets(new S_OwnCharStatus(pc));
        }
        this.setAcByMagic(3);
        this._pc = pc;
        this._timer = new EnchantTimer();
        (new Timer()).schedule(this._timer, skillTime);
        this._isRunning = true;
    }

    public void setSkillWeaponEnchant(final L1PcInstance pc, final int skillId,
            final int skillTime) {
        if (this.getItem().getType2() != 1) {
            return;
        }
        if (this._isRunning) {
            this._timer.cancel();
            this.setDmgByMagic(0);
            this.setHolyDmgByMagic(0);
            this.setHitByMagic(0);
            this._isRunning = false;
            this._timer = null;
        }

        switch (skillId) {
            case HOLY_WEAPON:
                this.setHolyDmgByMagic(1);
                this.setHitByMagic(1);
                break;

            case ENCHANT_WEAPON:
                this.setDmgByMagic(2);
                break;

            case BLESS_WEAPON:
                this.setDmgByMagic(2);
                this.setHitByMagic(2);
                break;

            case SHADOW_FANG:
                this.setDmgByMagic(5);
                break;

            default:
                break;
        }

        this._pc = pc;
        this._timer = new EnchantTimer();
        (new Timer()).schedule(this._timer, skillTime);
        this._isRunning = true;
    }

    public void setWaterMr(final int i) {
        this._WaterMr = i;
    }

    public void setWindMr(final int i) {
        this._WindMr = i;
    }

    public void startEquipmentTimer(final L1PcInstance pc) {
        if (this.getRemainingTime() > 0) {
            this._equipmentTimer = new L1EquipmentTimer(pc, this);
            final Timer timer = new Timer(true);
            timer.scheduleAtFixedRate(this._equipmentTimer, 1000, 1000);
        }
    }

    public void startItemOwnerTimer(final L1PcInstance pc) {
        this.setItemOwnerId(pc.getId());
        final L1ItemOwnerTimer timer = new L1ItemOwnerTimer(this, 10000);
        timer.begin();
    }

    public void stopEquipmentTimer(final L1PcInstance pc) {
        if (this.getRemainingTime() > 0) {
            this._equipmentTimer.cancel();
            this._equipmentTimer = null;
        }
    }
}
