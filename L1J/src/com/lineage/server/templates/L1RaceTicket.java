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

/**
 * 赛狗票
 */
public class L1RaceTicket {

    private int _itemobjid;
    private int _round;
    private double _allotment_percentage;
    private int victory;
    private int _runner_num;

    public double get_allotment_percentage() {
        return this._allotment_percentage;
    }

    public int get_itemobjid() {
        return this._itemobjid;
    }

    public int get_round() {
        return this._round;
    }

    public int get_runner_num() {
        return this._runner_num;
    }

    public int get_victory() {
        return this.victory;
    }

    public void set_allotment_percentage(final double allotment_percentage) {
        this._allotment_percentage = allotment_percentage;
    }

    public void set_itemobjid(final int i) {
        this._itemobjid = i;
    }

    public void set_round(final int _round) {
        this._round = _round;
    }

    public void set_runner_num(final int _runner_num) {
        this._runner_num = _runner_num;
    }

    public void set_victory(final int victory) {
        this.victory = victory;
    }

}
