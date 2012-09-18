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

import java.util.TimerTask;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 装备定时器
 */
public class L1EquipmentTimer extends TimerTask {

    private final L1PcInstance _pc;

    private final L1ItemInstance _item;

    public L1EquipmentTimer(final L1PcInstance pc, final L1ItemInstance item) {
        this._pc = pc;
        this._item = item;
    }

    @Override
    public void run() {
        if ((this._item.getRemainingTime() - 1) > 0) {
            this._item.setRemainingTime(this._item.getRemainingTime() - 1);
            this._pc.getInventory().updateItem(this._item,
                    L1PcInventory.COL_REMAINING_TIME);
        } else {
            this._pc.getInventory().removeItem(this._item, 1);
            this.cancel();
        }
    }

}
