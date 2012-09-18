package com.lineage.server.model.skill.use;

import com.lineage.server.model.L1Character;

/**
 * 目标状态
 * 
 * @author jrwz
 */
public class TargetStatus {

    /**
     * 目标
     */
    private L1Character _target = null;

    /**
     * 命中
     */
    private boolean _isCalc = true;

    /**
     * 目标状态
     * 
     * @param _cha
     */
    public TargetStatus(final L1Character _cha) {
        this._target = _cha;
    }

    /**
     * 目标状态
     * 
     * @param _cha
     * @param _flg
     */
    public TargetStatus(final L1Character _cha, final boolean _flg) {
        this._target = _cha;
        this._isCalc = _flg;
    }

    /**
     * 取得目标
     * 
     * @return
     */
    public L1Character getTarget() {
        return this._target;
    }

    /**
     * 是否命中
     * 
     * @return
     */
    public boolean isCalc() {
        return this._isCalc;
    }

    /**
     * 是否命中
     * 
     * @param flg
     */
    public void isCalc(final boolean _flg) {
        this._isCalc = _flg;
    }
}
