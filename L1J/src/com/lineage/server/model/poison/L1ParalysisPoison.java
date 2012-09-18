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

import static com.lineage.server.model.skill.L1SkillId.STATUS_POISON_PARALYZED;
import static com.lineage.server.model.skill.L1SkillId.STATUS_POISON_PARALYZING;

import com.lineage.server.GeneralThreadPool;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Paralysis;

/**
 * 麻痹毒
 */
public class L1ParalysisPoison extends L1Poison {
    // 麻痺毒の性能一覧 猶予 持続 (参考値、未適用)
    // グール 20 45
    // アステ 10 60
    // 蟻穴ムカデ 14 30
    // D-グール 39 45

    private class ParalysisPoisonTimer extends Thread {
        public ParalysisPoisonTimer() {
            // TODO Auto-generated constructor stub
        }

        @Override
        public void run() {
            L1ParalysisPoison.this._target.setSkillEffect(
                    STATUS_POISON_PARALYZING, 0);

            try {
                Thread.sleep(L1ParalysisPoison.this._delay); // 麻痺するまでの猶予時間を待つ。
            } catch (final InterruptedException e) {
                L1ParalysisPoison.this._target
                        .killSkillEffectTimer(STATUS_POISON_PARALYZING);
                return;
            }

            // エフェクトを緑から灰色へ
            L1ParalysisPoison.this._effectId = 2;
            L1ParalysisPoison.this._target.setPoisonEffect(2);

            if (L1ParalysisPoison.this._target instanceof L1PcInstance) {
                final L1PcInstance player = (L1PcInstance) L1ParalysisPoison.this._target;
                if (player.isDead() == false) {
                    player.sendPackets(new S_Paralysis(1, true)); // 麻痺状態にする
                    L1ParalysisPoison.this._timer = new ParalysisTimer();
                    GeneralThreadPool.getInstance().execute(
                            L1ParalysisPoison.this._timer); // 麻痺タイマー開始
                    if (this.isInterrupted()) {
                        L1ParalysisPoison.this._timer.interrupt();
                    }
                }
            }
        }
    }

    private class ParalysisTimer extends Thread {
        public ParalysisTimer() {
            // TODO Auto-generated constructor stub
        }

        @Override
        public void run() {
            L1ParalysisPoison.this._target
                    .killSkillEffectTimer(STATUS_POISON_PARALYZING);
            L1ParalysisPoison.this._target.setSkillEffect(
                    STATUS_POISON_PARALYZED, 0);
            try {
                Thread.sleep(L1ParalysisPoison.this._time);
            } catch (final InterruptedException e) {
            }

            L1ParalysisPoison.this._target
                    .killSkillEffectTimer(STATUS_POISON_PARALYZED);
            if (L1ParalysisPoison.this._target instanceof L1PcInstance) {
                final L1PcInstance player = (L1PcInstance) L1ParalysisPoison.this._target;
                if (!player.isDead()) {
                    player.sendPackets(new S_Paralysis(1, false)); // 麻痺状態を解除する
                    L1ParalysisPoison.this.cure(); // 解毒処理
                }
            }
        }
    }

    public static boolean doInfection(final L1Character cha, final int delay,
            final int time) {
        if (!L1Poison.isValidTarget(cha)) {
            return false;
        }

        cha.setPoison(new L1ParalysisPoison(cha, delay, time));
        return true;
    }

    final L1Character _target;

    Thread _timer;

    final int _delay;

    final int _time;

    int _effectId = 1;

    private L1ParalysisPoison(final L1Character cha, final int delay,
            final int time) {
        this._target = cha;
        this._delay = delay;
        this._time = time;

        this.doInfection();
    }

    @Override
    public void cure() {
        if (this._timer != null) {
            this._timer.interrupt(); // 麻痺毒タイマー解除
        }

        this._target.setPoisonEffect(0);
        this._target.setPoison(null);
    }

    private void doInfection() {
        sendMessageIfPlayer(this._target, 212);
        this._target.setPoisonEffect(1);

        if (this._target instanceof L1PcInstance) {
            this._timer = new ParalysisPoisonTimer();
            GeneralThreadPool.getInstance().execute(this._timer);
        }
    }

    @Override
    public int getEffectId() {
        return this._effectId;
    }
}
