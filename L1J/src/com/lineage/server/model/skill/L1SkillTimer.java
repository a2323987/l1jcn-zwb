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
package com.lineage.server.model.skill;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.GeneralThreadPool;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.skill.stop.L1SkillStop;
import com.lineage.server.model.skill.stop.Producer;

/**
 * 技能计时器接口
 * 
 * @author jrwz
 */
public interface L1SkillTimer {

    /**
     * 技能开始
     */
    public void begin();

    /**
     * 技能结束
     */
    public void end();

    /**
     * 取得技能剩余时间 (秒)
     */
    public int getRemainingTime();

    /**
     * 终止技能
     */
    public void kill();
}

/*
 * XXX 2008/02/13 vala 本来、このクラスはあるべきではないが暂定处置。
 */

/**
 * 技能计时器类型:2
 */
class L1SkillTimerThreadImpl extends Thread implements L1SkillTimer {
    private final L1Character _cha;

    private final int _timeMillis;

    private final int _skillId;

    private int _remainingTime;

    /**
     * 技能计时器类型:2
     * 
     * @param cha
     *            技能使用者
     * @param skillId
     *            技能编号
     * @param timeMillis
     *            技能时间 (毫秒)
     */
    public L1SkillTimerThreadImpl(final L1Character cha, final int skillId,
            final int timeMillis) {
        this._cha = cha;
        this._skillId = skillId;
        this._timeMillis = timeMillis;
    }

    @Override
    public void begin() {
        GeneralThreadPool.getInstance().execute(this);
    }

    @Override
    public void end() {
        super.interrupt();
        // L1SkillStop.stopSkill(this._cha, this._skillId);
        final List<?> queue = Producer.produceRequests();
        for (final Object name2 : queue) {
            ((L1SkillStop) name2).stopSkill(this._cha, this._skillId);
        }
    }

    @Override
    public int getRemainingTime() {
        return this._remainingTime;
    }

    @Override
    public void kill() {
        if (Thread.currentThread().getId() == super.getId()) {
            return; // 如果停止调用线程
        }
        super.interrupt();
    }

    @Override
    public void run() {
        for (int timeCount = this._timeMillis / 1000; timeCount > 0; timeCount--) {
            try {
                Thread.sleep(1000);
                this._remainingTime = timeCount;
            } catch (final InterruptedException e) {
                return;
            }
        }
        this._cha.removeSkillEffect(this._skillId);
    }
}

/**
 * 技能计时器类型:1
 */
class L1SkillTimerTimerImpl implements L1SkillTimer, Runnable {
    private static Logger _log = Logger.getLogger(L1SkillTimerTimerImpl.class
            .getName());

    private ScheduledFuture<?> _future = null;

    private final L1Character _cha;

    private final int _timeMillis;

    private final int _skillId;

    private int _remainingTime;

    /**
     * 技能计时器类型:1
     * 
     * @param cha
     *            技能使用者
     * @param skillId
     *            技能编号
     * @param timeMillis
     *            技能时间 (毫秒)
     */
    public L1SkillTimerTimerImpl(final L1Character cha, final int skillId,
            final int timeMillis) {
        this._cha = cha;
        this._skillId = skillId;
        this._timeMillis = timeMillis;

        this._remainingTime = this._timeMillis / 1000;
    }

    @Override
    public void begin() {
        this._future = GeneralThreadPool.getInstance().scheduleAtFixedRate(
                this, 1000, 1000);
    }

    @Override
    public void end() {
        this.kill();
        try {
            // L1SkillStop.stopSkill(this._cha, this._skillId);
            final List<?> queue = Producer.produceRequests();
            for (final Object name : queue) {
                ((L1SkillStop) name).stopSkill(this._cha, this._skillId);
            }
        } catch (final Throwable e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    @Override
    public int getRemainingTime() {
        return this._remainingTime;
    }

    @Override
    public void kill() {
        if (this._future != null) {
            this._future.cancel(false);
        }
    }

    @Override
    public void run() {
        this._remainingTime--;
        if (this._remainingTime <= 0) {
            this._cha.removeSkillEffect(this._skillId);
        }
    }
}
