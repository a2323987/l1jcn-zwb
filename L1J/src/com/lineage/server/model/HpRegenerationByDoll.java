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
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.templates.L1MagicDoll;

/**
 * 魔法娃娃增加角色HP的再生(恢复量)
 */
public class HpRegenerationByDoll extends TimerTask {

    private static Logger _log = Logger.getLogger(HpRegenerationByDoll.class
            .getName());

    private final L1PcInstance _pc;

    public HpRegenerationByDoll(final L1PcInstance pc) {
        this._pc = pc;
    }

    public void regenHp() {
        int newHp = this._pc.getCurrentHp() + L1MagicDoll.getHpByDoll(this._pc);
        if (newHp < 0) {
            newHp = 0;
        }
        this._pc.sendPackets(new S_SkillSound(this._pc.getId(), 744));
        this._pc.broadcastPacket(new S_SkillSound(this._pc.getId(), 744));
        this._pc.setCurrentHp(newHp);
    }

    @Override
    public void run() {
        try {
            if (this._pc.isDead()) {
                return;
            }
            this.regenHp();
        } catch (final Throwable e) {
            _log.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }
}
