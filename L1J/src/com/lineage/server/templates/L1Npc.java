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
package com.lineage.server.templates;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lineage.server.model.L1Object;
import com.lineage.server.model.npc.NpcExecutor;

/**
 * 
 */
public class L1Npc extends L1Object implements Cloneable {

    private static final Log _log = LogFactory.getLog(L1Npc.class);

    private static final long serialVersionUID = 1L;

    private int _npcid;

    private String _name;

    private String _impl;

    private int _level;

    private int _hp;

    private int _mp;

    private int _ac;

    private byte _str;

    private byte _con;

    private byte _dex;

    private byte _wis;

    private byte _int;

    private int _mr;

    private int _exp;

    private int _lawful;

    private String _size;

    private int _weakAttr;

    private int _ranged;

    private boolean _agrososc;

    private boolean _agrocoi;

    private boolean _tameable;

    private int _passispeed;

    private int _atkspeed;

    private boolean _agro;

    private int _gfxid;

    private String _nameid;

    private int _undead;

    private int _poisonatk;

    private int _paralysisatk;

    private int _family;

    private int _agrofamily;

    private int _agrogfxid1;

    private int _agrogfxid2;

    private boolean _picupitem;

    private int _digestitem;

    private boolean _bravespeed;

    private int _hprinterval;

    private int _hpr;

    private int _mprinterval;

    private int _mpr;

    private boolean _teleport;

    private int _randomlevel;

    private int _randomhp;

    private int _randommp;

    private int _randomac;

    private int _randomexp;

    private int _randomlawful;

    private int _damagereduction;

    private boolean _hard;

    private boolean _doppel;

    private boolean _tu;

    private boolean _erase;

    private int bowActId = 0;

    private int _karma;

    private int _transformId;

    private int _transformGfxId;

    private int _altAtkSpeed;

    private int _atkMagicSpeed;

    private int _subMagicSpeed;

    private int _lightSize;

    private boolean _amountFixed;

    private boolean _changeHead;

    private boolean _isCantResurrect;

    private String _classname;

    private NpcExecutor _class;

    private boolean _talk = false;

    private boolean _action = false;

    public L1Npc() {
    }

    /**
     * NPC对话动作
     */
    public boolean action() {
        return this._action;
    }

    @Override
    public L1Npc clone() {
        try {
            return (L1Npc) (super.clone());
        } catch (final CloneNotSupportedException e) {
            throw (new InternalError(e.getMessage()));
        }
    }

    public int get_ac() {
        return this._ac;
    }

    public int get_agrofamily() {
        return this._agrofamily;
    }

    public int get_atkspeed() {
        return this._atkspeed;
    }

    /**
     * 取得CLASS名称
     * 
     * @return
     */
    public String get_classname() {
        return this._classname;
    }

    public byte get_con() {
        return this._con;
    }

    public int get_damagereduction() {
        return this._damagereduction;
    }

    public byte get_dex() {
        return this._dex;
    }

    public int get_digestitem() {
        return this._digestitem;
    }

    public int get_exp() {
        return this._exp;
    }

    public int get_family() {
        return this._family;
    }

    public int get_gfxid() {
        return this._gfxid;
    }

    public int get_hp() {
        return this._hp;
    }

    public int get_hpr() {
        return this._hpr;
    }

    public int get_hprinterval() {
        return this._hprinterval;
    }

    public byte get_int() {
        return this._int;
    }

    public boolean get_IsErase() {
        return this._erase;
    }

    public boolean get_IsTU() {
        return this._tu;
    }

    public int get_lawful() {
        return this._lawful;
    }

    public int get_level() {
        return this._level;
    }

    public int get_mp() {
        return this._mp;
    }

    public int get_mpr() {
        return this._mpr;
    }

    public int get_mprinterval() {
        return this._mprinterval;
    }

    public int get_mr() {
        return this._mr;
    }

    public String get_name() {
        return this._name;
    }

    public String get_nameid() {
        return this._nameid;
    }

    public int get_npcId() {
        return this._npcid;
    }

    public int get_paralysisatk() {
        return this._paralysisatk;
    }

    public int get_passispeed() {
        return this._passispeed;
    }

    public int get_poisonatk() {
        return this._poisonatk;
    }

    public int get_randomac() {
        return this._randomac;
    }

    public int get_randomexp() {
        return this._randomexp;
    }

    public int get_randomhp() {
        return this._randomhp;
    }

    public int get_randomlawful() {
        return this._randomlawful;
    }

    public int get_randomlevel() {
        return this._randomlevel;
    }

    public int get_randommp() {
        return this._randommp;
    }

    public int get_ranged() {
        return this._ranged;
    }

    public String get_size() {
        return this._size;
    }

    public byte get_str() {
        return this._str;
    }

    public int get_undead() {
        return this._undead;
    }

    public int get_weakAttr() {
        return this._weakAttr;
    }

    public byte get_wis() {
        return this._wis;
    }

    public int getAltAtkSpeed() {
        return this._altAtkSpeed;
    }

    public int getAtkMagicSpeed() {
        return this._atkMagicSpeed;
    }

    public int getBowActId() {
        return this.bowActId;
    }

    public boolean getChangeHead() {
        return this._changeHead;
    }

    public String getImpl() {
        return this._impl;
    }

    public int getKarma() {
        return this._karma;
    }

    public int getLightSize() {
        return this._lightSize;
    }

    /**
     * 取得NPC执行
     * 
     * @return
     */
    public NpcExecutor getNpcExecutor() {
        return this._class;
    }

    public int getSubMagicSpeed() {
        return this._subMagicSpeed;
    }

    public int getTransformGfxId() {
        return this._transformGfxId;
    }

    public int getTransformId() {
        return this._transformId;
    }

    public boolean is_agro() {
        return this._agro;
    }

    public boolean is_agrocoi() {
        return this._agrocoi;
    }

    public int is_agrogfxid1() {
        return this._agrogfxid1;
    }

    public int is_agrogfxid2() {
        return this._agrogfxid2;
    }

    public boolean is_agrososc() {
        return this._agrososc;
    }

    public boolean is_bravespeed() {
        return this._bravespeed;
    }

    public boolean is_doppel() {
        return this._doppel;
    }

    public boolean is_hard() {
        return this._hard;
    }

    public boolean is_picupitem() {
        return this._picupitem;
    }

    public boolean is_teleport() {
        return this._teleport;
    }

    /**
     * mapidsテーブルで設定されたモンスター量倍率の影響を受けるかどうかを返す。
     * 
     * @return 影響を受けないように設定されている場合はtrueを返す。
     */
    public boolean isAmountFixed() {
        return this._amountFixed;
    }

    public boolean isCantResurrect() {
        return this._isCantResurrect;
    }

    public boolean isTamable() {
        return this._tameable;
    }

    public void set_ac(final int i) {
        this._ac = i;
    }

    public void set_agro(final boolean flag) {
        this._agro = flag;
    }

    public void set_agrocoi(final boolean flag) {
        this._agrocoi = flag;
    }

    public void set_agrofamily(final int i) {
        this._agrofamily = i;
    }

    public void set_agrogfxid1(final int i) {
        this._agrogfxid1 = i;
    }

    public void set_agrogfxid2(final int i) {
        this._agrogfxid2 = i;
    }

    public void set_agrososc(final boolean flag) {
        this._agrososc = flag;
    }

    public void set_atkspeed(final int i) {
        this._atkspeed = i;
    }

    public void set_bravespeed(final boolean flag) {
        this._bravespeed = flag;
    }

    /**
     * 设置CLASS名称
     * 
     * @param classname
     */
    public void set_classname(final String classname) {
        this._classname = classname;
    }

    public void set_con(final byte i) {
        this._con = i;
    }

    public void set_damagereduction(final int i) {
        this._damagereduction = i;
    }

    public void set_dex(final byte i) {
        this._dex = i;
    }

    public void set_digestitem(final int i) {
        this._digestitem = i;
    }

    public void set_doppel(final boolean flag) {
        this._doppel = flag;
    }

    public void set_exp(final int i) {
        this._exp = i;
    }

    public void set_family(final int i) {
        this._family = i;
    }

    public void set_gfxid(final int i) {
        this._gfxid = i;
    }

    public void set_hard(final boolean flag) {
        this._hard = flag;
    }

    public void set_hp(final int i) {
        this._hp = i;
    }

    public void set_hpr(final int i) {
        this._hpr = i;
    }

    public void set_hprinterval(final int i) {
        this._hprinterval = i;
    }

    public void set_int(final byte i) {
        this._int = i;
    }

    public void set_IsErase(final boolean i) {
        this._erase = i;
    }

    public void set_IsTU(final boolean i) {
        this._tu = i;
    }

    public void set_lawful(final int i) {
        this._lawful = i;
    }

    public void set_level(final int i) {
        this._level = i;
    }

    public void set_mp(final int i) {
        this._mp = i;
    }

    public void set_mpr(final int i) {
        this._mpr = i;
    }

    public void set_mprinterval(final int i) {
        this._mprinterval = i;
    }

    public void set_mr(final int i) {
        this._mr = i;
    }

    public void set_name(final String s) {
        this._name = s;
    }

    public void set_nameid(final String s) {
        this._nameid = s;
    }

    public void set_npcId(final int i) {
        this._npcid = i;
    }

    public void set_paralysisatk(final int i) {
        this._paralysisatk = i;
    }

    public void set_passispeed(final int i) {
        this._passispeed = i;
    }

    public void set_picupitem(final boolean flag) {
        this._picupitem = flag;
    }

    public void set_poisonatk(final int i) {
        this._poisonatk = i;
    }

    public void set_randomac(final int i) {
        this._randomac = i;
    }

    public void set_randomexp(final int i) {
        this._randomexp = i;
    }

    public void set_randomhp(final int i) {
        this._randomhp = i;
    }

    public void set_randomlawful(final int i) {
        this._randomlawful = i;
    }

    public void set_randomlevel(final int i) {
        this._randomlevel = i;
    }

    public void set_randommp(final int i) {
        this._randommp = i;
    }

    public void set_ranged(final int i) {
        this._ranged = i;
    }

    public void set_size(final String s) {
        this._size = s;
    }

    public void set_str(final byte i) {
        this._str = i;
    }

    public void set_teleport(final boolean flag) {
        this._teleport = flag;
    }

    public void set_undead(final int i) {
        this._undead = i;
    }

    public void set_weakAttr(final int i) {
        this._weakAttr = i;
    }

    public void set_wis(final byte i) {
        this._wis = i;
    }

    public void setAltAtkSpeed(final int altAtkSpeed) {
        this._altAtkSpeed = altAtkSpeed;
    }

    public void setAmountFixed(final boolean fixed) {
        this._amountFixed = fixed;
    }

    public void setAtkMagicSpeed(final int atkMagicSpeed) {
        this._atkMagicSpeed = atkMagicSpeed;
    }

    public void setBowActId(final int i) {
        this.bowActId = i;
    }

    public void setCantResurrect(final boolean isCantResurrect) {
        this._isCantResurrect = isCantResurrect;
    }

    public void setChangeHead(final boolean changeHead) {
        this._changeHead = changeHead;
    }

    public void setImpl(final String s) {
        this._impl = s;
    }

    public void setKarma(final int i) {
        this._karma = i;
    }

    public void setLightSize(final int lightSize) {
        this._lightSize = lightSize;
    }

    /**
     * 设置NPC执行
     * 
     * @param _class
     */
    public void setNpcExecutor(final NpcExecutor _class) {
        try {
            if (_class == null) {
                return;
            }
            this._class = _class;

            int type = _class.type();

            if (type >= 2) {
                this._action = true;
                type -= 2;
            }
            if (type >= 1) {
                this._talk = true;
                type -= 1;
            }
            if (type > 0) {
                _log.error("NPC执行错误 NpcId: " + this._npcid);
            }
        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }

    public void setSubMagicSpeed(final int subMagicSpeed) {
        this._subMagicSpeed = subMagicSpeed;
    }

    public void setTamable(final boolean flag) {
        this._tameable = flag;
    }

    public void setTransformGfxId(final int i) {
        this._transformGfxId = i;
    }

    public void setTransformId(final int transformId) {
        this._transformId = transformId;
    }

    /**
     * NPC对话视窗
     */
    public boolean talk() {
        return this._talk;
    }
}
