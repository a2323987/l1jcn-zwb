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
package l1j.server.server.model.poison;

import static l1j.server.server.model.skill.L1SkillId.STATUS_POISON_PARALYZED;
import static l1j.server.server.model.skill.L1SkillId.STATUS_POISON_PARALYZING;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.model.L1Character;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_Paralysis;

public class L1ParalysisPoison extends L1Poison {
	// 麻痹毒の性能一览 犹予 持续 (参考值、未适用)
	// グール 20 45
	// アステ 10 60
	// 蚁穴ムカデ 14 30
	// D-グール 39 45

	private final L1Character _target;

	private Thread _timer;

	private final int _delay;

	private final int _time;

	private int _effectId = 1;

	private class ParalysisPoisonTimer extends Thread {
		@Override
		public void run() {
			_target.setSkillEffect(STATUS_POISON_PARALYZING, 0);

			try {
				Thread.sleep(_delay); // 麻痹するまでの犹予时间を待つ。
			}
			catch (InterruptedException e) {
				_target.killSkillEffectTimer(STATUS_POISON_PARALYZING);
				return;
			}

			// エフェクトを绿から灰色へ
			_effectId = 2;
			_target.setPoisonEffect(2);

			if (_target instanceof L1PcInstance) {
				L1PcInstance player = (L1PcInstance) _target;
				if (player.isDead() == false) {
					player.sendPackets(new S_Paralysis(1, true)); // 麻痹状态にする
					_timer = new ParalysisTimer();
					GeneralThreadPool.getInstance().execute(_timer); // 麻痹タイマー开始
					if (isInterrupted()) {
						_timer.interrupt();
					}
				}
			}
		}
	}

	private class ParalysisTimer extends Thread {
		@Override
		public void run() {
			_target.killSkillEffectTimer(STATUS_POISON_PARALYZING);
			_target.setSkillEffect(STATUS_POISON_PARALYZED, 0);
			try {
				Thread.sleep(_time);
			}
			catch (InterruptedException e) {}

			_target.killSkillEffectTimer(STATUS_POISON_PARALYZED);
			if (_target instanceof L1PcInstance) {
				L1PcInstance player = (L1PcInstance) _target;
				if (!player.isDead()) {
					player.sendPackets(new S_Paralysis(1, false)); // 麻痹状态を解除する
					cure(); // 解毒处理
				}
			}
		}
	}

	private L1ParalysisPoison(L1Character cha, int delay, int time) {
		_target = cha;
		_delay = delay;
		_time = time;

		doInfection();
	}

	public static boolean doInfection(L1Character cha, int delay, int time) {
		if (!L1Poison.isValidTarget(cha)) {
			return false;
		}

		cha.setPoison(new L1ParalysisPoison(cha, delay, time));
		return true;
	}

	private void doInfection() {
		sendMessageIfPlayer(_target, 212);
		_target.setPoisonEffect(1);

		if (_target instanceof L1PcInstance) {
			_timer = new ParalysisPoisonTimer();
			GeneralThreadPool.getInstance().execute(_timer);
		}
	}

	@Override
	public int getEffectId() {
		return _effectId;
	}

	@Override
	public void cure() {
		if (_timer != null) {
			_timer.interrupt(); // 麻痹毒タイマー解除
		}

		_target.setPoisonEffect(0);
		_target.setPoison(null);
	}
}
