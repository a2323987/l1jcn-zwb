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
package com.lineage.server;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.lineage.Config;
import com.lineage.server.model.monitor.L1PcMonitor;

/**
 * 一般线程
 */
public class GeneralThreadPool {

    // ThreadPoolManager 借用
    private class PriorityThreadFactory implements ThreadFactory {
        private final int _prio;

        private final String _name;

        private final AtomicInteger _threadNumber = new AtomicInteger(1);

        private final ThreadGroup _group;

        public PriorityThreadFactory(final String name, final int prio) {
            this._prio = prio;
            this._name = name;
            this._group = new ThreadGroup(this._name);
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
         */
        @Override
        public Thread newThread(final Runnable r) {
            final Thread t = new Thread(this._group, r);
            t.setName(this._name + "-" + this._threadNumber.getAndIncrement());
            t.setPriority(this._prio);
            return t;
        }
    }

    private static GeneralThreadPool _instance;

    private static final int SCHEDULED_CORE_POOL_SIZE = 10;

    public static GeneralThreadPool getInstance() {
        if (_instance == null) {
            _instance = new GeneralThreadPool();
        }
        return _instance;
    }

    private Executor _executor; // 通用的ExecutorService

    private final ScheduledExecutorService _scheduler; // 通用的ScheduledExecutorService

    private final ScheduledExecutorService _pcScheduler; // 监测玩家专用的ScheduledExecutorService

    // (AutoUpdate:约6ms,ExpMonitor:极小)
    private final int _pcSchedulerPoolSize = 1 + Config.MAX_ONLINE_USERS / 20; // 每
                                                                               // 20
                                                                               // 人增加一个
                                                                               // PoolSize

    private GeneralThreadPool() {
        if (Config.THREAD_P_TYPE_GENERAL == 1) {
            this._executor = Executors
                    .newFixedThreadPool(Config.THREAD_P_SIZE_GENERAL);
        } else if (Config.THREAD_P_TYPE_GENERAL == 2) {
            this._executor = Executors.newCachedThreadPool();
        } else {
            this._executor = null;
        }
        this._scheduler = Executors.newScheduledThreadPool(
                SCHEDULED_CORE_POOL_SIZE, new PriorityThreadFactory(
                        "GerenalSTPool", Thread.NORM_PRIORITY));
        this._pcScheduler = Executors.newScheduledThreadPool(
                this._pcSchedulerPoolSize, new PriorityThreadFactory(
                        "PcMonitorSTPool", Thread.NORM_PRIORITY));
    }

    public void execute(final Runnable r) {
        if (this._executor == null) {
            final Thread t = new Thread(r);
            t.start();
        } else {
            this._executor.execute(r);
        }
    }

    public void execute(final Thread t) {
        t.start();
    }

    public ScheduledFuture<?> pcSchedule(final L1PcMonitor r, final long delay) {
        try {
            if (delay <= 0) {
                this._executor.execute(r);
                return null;
            }
            return this._pcScheduler.schedule(r, delay, TimeUnit.MILLISECONDS);
        } catch (final RejectedExecutionException e) {
            return null;
        }
    }

    public ScheduledFuture<?> pcScheduleAtFixedRate(final L1PcMonitor r,
            final long initialDelay, final long period) {
        return this._pcScheduler.scheduleAtFixedRate(r, initialDelay, period,
                TimeUnit.MILLISECONDS);
    }

    public ScheduledFuture<?> schedule(final Runnable r, final long delay) {
        try {
            if (delay <= 0) {
                this._executor.execute(r);
                return null;
            }
            return this._scheduler.schedule(r, delay, TimeUnit.MILLISECONDS);
        } catch (final RejectedExecutionException e) {
            return null;
        }
    }

    public ScheduledFuture<?> scheduleAtFixedRate(final Runnable r,
            final long initialDelay, final long period) {
        return this._scheduler.scheduleAtFixedRate(r, initialDelay, period,
                TimeUnit.MILLISECONDS);
    }
}
