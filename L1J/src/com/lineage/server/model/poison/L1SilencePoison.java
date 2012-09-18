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
package com.lineage.server.model.poison;

import static com.lineage.server.model.skill.L1SkillId.STATUS_POISON_SILENCE;

import com.lineage.server.model.L1Character;

/**
 * 沉默毒
 */
public class L1SilencePoison extends L1Poison {

    public static boolean doInfection(final L1Character cha) {
        if (!L1Poison.isValidTarget(cha)) {
            return false;
        }

        cha.setPoison(new L1SilencePoison(cha));
        return true;
    }

    private final L1Character _target;

    private L1SilencePoison(final L1Character cha) {
        this._target = cha;

        this.doInfection();
    }

    @Override
    public void cure() {
        this._target.setPoisonEffect(0);
        sendMessageIfPlayer(this._target, 311);

        this._target.killSkillEffectTimer(STATUS_POISON_SILENCE);
        this._target.setPoison(null);
    }

    private void doInfection() {
        this._target.setPoisonEffect(1);
        sendMessageIfPlayer(this._target, 310);

        this._target.setSkillEffect(STATUS_POISON_SILENCE, 0);
    }

    @Override
    public int getEffectId() {
        return 1;
    }
}
