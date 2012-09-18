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

public class L1Pet {
    private int _itemobjid;

    private int _objid;

    private int _npcid;

    private String _name;

    private int _level;

    private int _hp;

    private int _mp;

    private int _exp;

    private int _lawful;

    // 饱食度
    private int _food;

    public L1Pet() {
    }

    public int get_exp() {
        return this._exp;
    }

    public int get_food() {
        return this._food;
    }

    public int get_hp() {
        return this._hp;
    }

    public int get_itemobjid() {
        return this._itemobjid;
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

    public String get_name() {
        return this._name;
    }

    public int get_npcid() {
        return this._npcid;
    }

    public int get_objid() {
        return this._objid;
    }

    public void set_exp(final int i) {
        this._exp = i;
    }

    public void set_food(final int i) {
        this._food = i;
    }

    public void set_hp(final int i) {
        this._hp = i;
    }

    public void set_itemobjid(final int i) {
        this._itemobjid = i;
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

    public void set_name(final String s) {
        this._name = s;
    }

    public void set_npcid(final int i) {
        this._npcid = i;
    }

    public void set_objid(final int i) {
        this._objid = i;
    }
}
