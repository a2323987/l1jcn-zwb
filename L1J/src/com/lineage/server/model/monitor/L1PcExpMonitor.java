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
package com.lineage.server.model.monitor;

import com.lineage.Config;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Karma;
import com.lineage.server.serverpackets.S_Lawful;

/**
 * PC经验值监视器
 */
public class L1PcExpMonitor extends L1PcMonitor {

    private int _old_lawful;

    private long _old_exp;

    private int _old_karma;

    // 上一次判断时的战斗特化类别
    private int _oldFight;

    public L1PcExpMonitor(final int oId) {
        super(oId);
    }

    @Override
    public void execTask(final L1PcInstance pc) {

        // ロウフルが变わった场合はS_Lawfulを送信
        // // ただし色が变わらない场合は送信しない
        // if (_old_lawful != pc.getLawful()
        // && !((IntRange.includes(_old_lawful, 9000, 32767) && IntRange
        // .includes(pc.getLawful(), 9000, 32767)) || (IntRange
        // .includes(_old_lawful, -32768, -2000) && IntRange
        // .includes(pc.getLawful(), -32768, -2000)))) {
        if (this._old_lawful != pc.getLawful()) {
            this._old_lawful = pc.getLawful();
            final S_Lawful s_lawful = new S_Lawful(pc.getId(), this._old_lawful);
            pc.sendPackets(s_lawful);
            pc.broadcastPacket(s_lawful);

            // 处理战斗特化系统
            if (Config.FIGHT_IS_ACTIVE) {
                // 计算目前的战斗特化组别
                final int fightType = this._old_lawful / 10000;

                // 判断战斗特化组别是否有所变更
                if (this._oldFight != fightType) {
                    // 进行战斗特化组别的变更
                    pc.changeFightType(this._oldFight, fightType);

                    this._oldFight = fightType;
                }
            }

        }

        if (this._old_karma != pc.getKarma()) {
            this._old_karma = pc.getKarma();
            pc.sendPackets(new S_Karma(pc));
        }

        if (this._old_exp != pc.getExp()) {
            this._old_exp = pc.getExp();
            pc.onChangeExp();
        }
    }
}
