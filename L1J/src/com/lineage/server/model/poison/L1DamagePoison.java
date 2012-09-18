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

import static com.lineage.server.model.skill.L1SkillId.STATUS_POISON;

import com.lineage.server.GeneralThreadPool;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 毒伤害
 */
public class L1DamagePoison extends L1Poison {

    private class NormalPoisonTimer extends Thread {
        public NormalPoisonTimer() {
            // TODO Auto-generated constructor stub
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(L1DamagePoison.this._damageSpan);
                } catch (final InterruptedException e) {
                    break;
                }

                if (!L1DamagePoison.this._target.hasSkillEffect(STATUS_POISON)) {
                    break;
                }
                if (L1DamagePoison.this._target instanceof L1PcInstance) {
                    final L1PcInstance player = (L1PcInstance) L1DamagePoison.this._target;
                    player.receiveDamage(L1DamagePoison.this._attacker,
                            L1DamagePoison.this._damage, false);
                    if (player.isDead()) { // 死亡时解毒
                        break;
                    }
                } else if (L1DamagePoison.this._target instanceof L1MonsterInstance) {
                    final L1MonsterInstance mob = (L1MonsterInstance) L1DamagePoison.this._target;
                    mob.receiveDamage(L1DamagePoison.this._attacker,
                            L1DamagePoison.this._damage);
                    if (mob.isDead()) { // 死亡时解毒
                        return;
                    }
                }
            }
            L1DamagePoison.this.cure(); // 解毒处理
        }
    }

    public static boolean doInfection(final L1Character attacker,
            final L1Character cha, final int damageSpan, final int damage) {
        if (!isValidTarget(cha)) {
            return false;
        }

        cha.setPoison(new L1DamagePoison(attacker, cha, damageSpan, damage));
        return true;
    }

    private Thread _timer;

    final L1Character _attacker;

    final L1Character _target;

    final int _damageSpan;

    final int _damage;

    private L1DamagePoison(final L1Character attacker, final L1Character cha,
            final int damageSpan, final int damage) {
        this._attacker = attacker;
        this._target = cha;
        this._damageSpan = damageSpan;
        this._damage = damage;

        this.doInfection();
    }

    @Override
    public void cure() {
        if (this._timer != null) {
            this._timer.interrupt(); // 解毒计时器
        }

        this._target.setPoisonEffect(0);
        this._target.killSkillEffectTimer(STATUS_POISON);
        this._target.setPoison(null);
    }

    private void doInfection() {
        this._target.setSkillEffect(STATUS_POISON, 30000);
        this._target.setPoisonEffect(1);

        if (this.isDamageTarget(this._target)) {
            this._timer = new NormalPoisonTimer();
            GeneralThreadPool.getInstance().execute(this._timer); // 通常毒タイマー開始
        }
    }

    @Override
    public int getEffectId() {
        return 1;
    }

    boolean isDamageTarget(final L1Character cha) {
        return (cha instanceof L1PcInstance)
                || (cha instanceof L1MonsterInstance);
    }
}
