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
package l1j.server.server.model.monitor;

import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;

/**
 * L1PcInstanceの定期处理、监视处理等を行う为の共通的な处理を实装した抽象クラス
 * 
 * 各タスク处理は{@link #run()}ではなく{@link #execTask(L1PcInstance)}にて实装する。
 * PCがログアウトするなどしてサーバ上に存在しなくなった场合、run()メソッドでは即座にリターンする。
 * その场合、タスクが定期实行スケジューリングされていたら、ログアウト处理等でスケジューリングを停止する必要がある。
 * 停止しなければタスクは止まらず、永远に定期实行されることになる。
 * 定期实行でなく单発アクションの场合はそのような制御は不要。
 * 
 * L1PcInstanceの参照を直接持つことは望ましくない。
 * 
 * @author frefre
 *
 */
public abstract class L1PcMonitor implements Runnable {

	/** モニター对象L1PcInstanceのオブジェクトID */
	protected int _id;

	/**
	 * 指定されたパラメータでL1PcInstanceに对するモニターを作成する。
	 * @param oId {@link L1PcInstance#getId()}で取得できるオブジェクトID
	 */
	public L1PcMonitor(int oId) {
		_id = oId;
	}

	@Override
	public final void run() {
		L1PcInstance pc = (L1PcInstance) L1World.getInstance().findObject(_id);
		if (pc == null || pc.getNetConnection() == null) {
			return;
		}
		execTask(pc);
	}

	/**
	 * タスク实行时の处理
	 * @param pc モニター对象のPC
	 */
	public abstract void execTask(L1PcInstance pc);
}
