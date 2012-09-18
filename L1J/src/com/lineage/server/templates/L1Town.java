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

public class L1Town {

    private int _townid;

    private String _name;

    private int _leader_id;

    private String _leader_name;

    private int _tax_rate;

    private int _tax_rate_reserved;

    private int _sales_money;

    private int _sales_money_yesterday;

    private int _town_tax;

    private int _town_fix_tax;

    public L1Town() {
    }

    public int get_leader_id() {
        return this._leader_id;
    }

    public String get_leader_name() {
        return this._leader_name;
    }

    public String get_name() {
        return this._name;
    }

    public int get_sales_money() {
        return this._sales_money;
    }

    public int get_sales_money_yesterday() {
        return this._sales_money_yesterday;
    }

    public int get_tax_rate() {
        return this._tax_rate;
    }

    public int get_tax_rate_reserved() {
        return this._tax_rate_reserved;
    }

    public int get_town_fix_tax() {
        return this._town_fix_tax;
    }

    public int get_town_tax() {
        return this._town_tax;
    }

    public int get_townid() {
        return this._townid;
    }

    public void set_leader_id(final int i) {
        this._leader_id = i;
    }

    public void set_leader_name(final String s) {
        this._leader_name = s;
    }

    public void set_name(final String s) {
        this._name = s;
    }

    public void set_sales_money(final int i) {
        this._sales_money = i;
    }

    public void set_sales_money_yesterday(final int i) {
        this._sales_money_yesterday = i;
    }

    public void set_tax_rate(final int i) {
        this._tax_rate = i;
    }

    public void set_tax_rate_reserved(final int i) {
        this._tax_rate_reserved = i;
    }

    public void set_town_fix_tax(final int i) {
        this._town_fix_tax = i;
    }

    public void set_town_tax(final int i) {
        this._town_tax = i;
    }

    public void set_townid(final int i) {
        this._townid = i;
    }
}
