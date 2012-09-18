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
package com.lineage.server.model.trap;

import com.lineage.server.model.L1Object;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.storage.TrapStorage;
import com.lineage.server.utils.Dice;

/**
 * 治疗陷阱
 */
public class L1HealingTrap extends L1Trap {

    private final Dice _dice;
    private final int _base;
    private final int _diceCount;

    public L1HealingTrap(final TrapStorage storage) {
        super(storage);

        this._dice = new Dice(storage.getInt("dice"));
        this._base = storage.getInt("base");
        this._diceCount = storage.getInt("diceCount");
    }

    @Override
    public void onTrod(final L1PcInstance trodFrom, final L1Object trapObj) {
        this.sendEffect(trapObj);

        final int pt = this._dice.roll(this._diceCount) + this._base;

        trodFrom.healHp(pt);
    }
}
