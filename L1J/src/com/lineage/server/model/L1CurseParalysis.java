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

import static com.lineage.server.model.skill.L1SkillId.STATUS_CURSE_PARALYZED;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CURSE_PARALYZING;

import com.lineage.server.GeneralThreadPool;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Paralysis;
import com.lineage.server.serverpackets.S_ServerMessage;

/*
 * L1ParalysisPoison的大量代码。尤其是定时器。想要的东西很难共通化。
 */
public class L1CurseParalysis extends L1Paralysis {

    private class ParalysisDelayTimer extends Thread {
        public ParalysisDelayTimer() {
            // TODO Auto-generated constructor stub
        }

        @Override
        public void run() {
            L1CurseParalysis.this._target.setSkillEffect(
                    STATUS_CURSE_PARALYZING, 0);

            try {
                Thread.sleep(L1CurseParalysis.this._delay); // 麻痹毒瘫痪前的等待时间。
            } catch (final InterruptedException e) {
                L1CurseParalysis.this._target
                        .killSkillEffectTimer(STATUS_CURSE_PARALYZING);
                return;
            }

            if (L1CurseParalysis.this._target instanceof L1PcInstance) {
                final L1PcInstance player = (L1PcInstance) L1CurseParalysis.this._target;
                if (!player.isDead()) {
                    player.sendPackets(new S_Paralysis(1, true)); // 麻痹状态
                }
            }
            L1CurseParalysis.this._target.setParalyzed(true);
            L1CurseParalysis.this._timer = new ParalysisTimer();
            GeneralThreadPool.getInstance().execute(
                    L1CurseParalysis.this._timer); // 启动麻痹定时器
            if (this.isInterrupted()) {
                L1CurseParalysis.this._timer.interrupt();
            }
        }
    }

    private class ParalysisTimer extends Thread {
        public ParalysisTimer() {
            // TODO Auto-generated constructor stub
        }

        @Override
        public void run() {
            L1CurseParalysis.this._target
                    .killSkillEffectTimer(STATUS_CURSE_PARALYZING);
            L1CurseParalysis.this._target.setSkillEffect(
                    STATUS_CURSE_PARALYZED, 0);
            try {
                Thread.sleep(L1CurseParalysis.this._time);
            } catch (final InterruptedException e) {
            }

            L1CurseParalysis.this._target
                    .killSkillEffectTimer(STATUS_CURSE_PARALYZED);
            if (L1CurseParalysis.this._target instanceof L1PcInstance) {
                final L1PcInstance player = (L1PcInstance) L1CurseParalysis.this._target;
                if (!player.isDead()) {
                    player.sendPackets(new S_Paralysis(1, false)); // 解除麻痹状态
                }
            }
            L1CurseParalysis.this._target.setParalyzed(false);
            L1CurseParalysis.this.cure(); // 解除诅咒
        }
    }

    public static boolean curse(final L1Character cha, final int delay,
            final int time) {
        if (!((cha instanceof L1PcInstance) || (cha instanceof L1MonsterInstance))) {
            return false;
        }
        if (cha.hasSkillEffect(STATUS_CURSE_PARALYZING)
                || cha.hasSkillEffect(STATUS_CURSE_PARALYZED)) {
            return false; // 已经麻痹或瘫痪
        }

        cha.setParalaysis(new L1CurseParalysis(cha, delay, time));
        return true;
    }

    final L1Character _target;

    final int _delay;

    final int _time;

    Thread _timer;

    private L1CurseParalysis(final L1Character cha, final int delay,
            final int time) {
        this._target = cha;
        this._delay = delay;
        this._time = time;

        this.curse();
    }

    @Override
    public void cure() {
        if (this._timer != null) {
            this._timer.interrupt(); // 清除瘫痪定时器
        }

        this._target.setPoisonEffect(0);
        this._target.setParalaysis(null);
    }

    private void curse() {
        if (this._target instanceof L1PcInstance) {
            final L1PcInstance player = (L1PcInstance) this._target;
            player.sendPackets(new S_ServerMessage(212)); // \f1你的身体渐渐麻痹。
        }

        this._target.setPoisonEffect(2);

        this._timer = new ParalysisDelayTimer();
        GeneralThreadPool.getInstance().execute(this._timer);
    }

    @Override
    public int getEffectId() {
        return 2;
    }
}
