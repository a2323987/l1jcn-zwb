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
 * 道具相关
 */
public class L1EtcItem extends L1Item {

    private static final long serialVersionUID = 1L;

    /** 可堆叠 */
    private boolean _stackable;

    /** X坐标 */
    private int _locx;
    /** Y坐标 */
    private int _locy;
    /** 地图ID */
    private short _mapid;
    /** 延迟ID */
    private int _delay_id;
    /** 延迟时间 */
    private int _delay_time;
    /** 延迟效果 */
    private int _delay_effect;
    /** 最高使用次数 */
    private int _maxChargeCount;
    /** 是可以封印 */
    private boolean _isCanSeal;

    public L1EtcItem() {
    }

    /** 取得延迟效果 */
    public int get_delayEffect() {
        return this._delay_effect;
    }

    @Override
    public int get_delayid() {
        return this._delay_id;
    }

    @Override
    public int get_delaytime() {
        return this._delay_time;
    }

    @Override
    public int get_locx() {
        return this._locx;
    }

    @Override
    public int get_locy() {
        return this._locy;
    }

    @Override
    public short get_mapid() {
        return this._mapid;
    }

    @Override
    public int getMaxChargeCount() {
        return this._maxChargeCount;
    }

    @Override
    public boolean isCanSeal() {
        return this._isCanSeal;
    }

    @Override
    public boolean isStackable() {
        return this._stackable;
    }

    /** 设定延迟效果 */
    public void set_delayEffect(final int delay_effect) {
        this._delay_effect = delay_effect;
    }

    /** 设定延迟ID */
    public void set_delayid(final int delay_id) {
        this._delay_id = delay_id;
    }

    /** 设定延迟时间 */
    public void set_delaytime(final int delay_time) {
        this._delay_time = delay_time;
    }

    /** 设定X坐标 */
    public void set_locx(final int locx) {
        this._locx = locx;
    }

    /** 设定Y坐标 */
    public void set_locy(final int locy) {
        this._locy = locy;
    }

    /** 设定地图ID */
    public void set_mapid(final short mapid) {
        this._mapid = mapid;
    }

    /** 设定可堆叠 */
    public void set_stackable(final boolean stackable) {
        this._stackable = stackable;
    }

    /** 设定可以封印 */
    public void setCanSeal(final boolean flag) {
        this._isCanSeal = flag;
    }

    /** 设定最高使用次数 */
    public void setMaxChargeCount(final int i) {
        this._maxChargeCount = i;
    }

}
