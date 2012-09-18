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
import com.lineage.server.model.poison.L1DamagePoison;
import com.lineage.server.model.poison.L1ParalysisPoison;
import com.lineage.server.model.poison.L1SilencePoison;
import com.lineage.server.storage.TrapStorage;

/**
 * 毒陷阱
 */
public class L1PoisonTrap extends L1Trap {

    private final String _type;
    private final int _delay;
    private final int _time;
    private final int _damage;

    public L1PoisonTrap(final TrapStorage storage) {
        super(storage);

        this._type = storage.getString("poisonType");
        this._delay = storage.getInt("poisonDelay");
        this._time = storage.getInt("poisonTime");
        this._damage = storage.getInt("poisonDamage");
    }

    @Override
    public void onTrod(final L1PcInstance trodFrom, final L1Object trapObj) {
        this.sendEffect(trapObj);

        if (this._type.equals("d")) {
            L1DamagePoison.doInfection(trodFrom, trodFrom, this._time,
                    this._damage);
        } else if (this._type.equals("s")) {
            L1SilencePoison.doInfection(trodFrom);
        } else if (this._type.equals("p")) {
            L1ParalysisPoison.doInfection(trodFrom, this._delay, this._time);
        }
    }
}
