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
import com.lineage.server.model.skill.L1SkillUse;
import com.lineage.server.storage.TrapStorage;

/**
 * 技能陷阱
 */
public class L1SkillTrap extends L1Trap {

    private final int _skillId;
    private final int _skillTimeSeconds;

    public L1SkillTrap(final TrapStorage storage) {
        super(storage);

        this._skillId = storage.getInt("skillId");
        this._skillTimeSeconds = storage.getInt("skillTimeSeconds");
    }

    @Override
    public void onTrod(final L1PcInstance trodFrom, final L1Object trapObj) {
        this.sendEffect(trapObj);

        new L1SkillUse().handleCommands(trodFrom, this._skillId,
                trodFrom.getId(), trodFrom.getX(), trodFrom.getY(), null,
                this._skillTimeSeconds, L1SkillUse.TYPE_GMBUFF);
    }

}
