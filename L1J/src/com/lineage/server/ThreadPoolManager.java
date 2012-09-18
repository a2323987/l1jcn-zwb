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

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javolution.text.TextBuilder;

import com.lineage.Config;

// import com.lineage.server.network.L2GameClient;

/**
 * 线程池管理器
 */
public class ThreadPoolManager {

    /** 优先级的线程厂 */
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

        public ThreadGroup getGroup() {
            return this._group;
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

    private static Logger _log = Logger.getLogger(ThreadPoolManager.class
            .getName());

    private static ThreadPoolManager _instance;

    public static ThreadPoolManager getInstance() {
        if (_instance == null) {
            _instance = new ThreadPoolManager();
        }
        return _instance;
    }

    /** 影响预定的线程池 */
    private final ScheduledThreadPoolExecutor _effectsScheduledThreadPool;

    /** 一般排定的线程池 */
    private final ScheduledThreadPoolExecutor _generalScheduledThreadPool;

    /** 一般包线程池 */
    private final ThreadPoolExecutor _generalPacketsThreadPool;

    /** IO包线程池 */
    private final ThreadPoolExecutor _ioPacketsThreadPool;

    // 将真正用在未来的AI实施。
    /** AI线程池 */
    private final ThreadPoolExecutor _aiThreadPool;

    /** 通用线程池 */
    private final ThreadPoolExecutor _generalThreadPool;

    // temp
    /** AI排定的线程池 */
    private final ScheduledThreadPoolExecutor _aiScheduledThreadPool;

    /** 关机 */
    private boolean _shutdown;

    /** 线程池管理器 */
    private ThreadPoolManager() {
        this._effectsScheduledThreadPool = new ScheduledThreadPoolExecutor(
                Config.THREAD_P_EFFECTS, new PriorityThreadFactory(
                        "EffectsSTPool", Thread.MIN_PRIORITY));
        this._generalScheduledThreadPool = new ScheduledThreadPoolExecutor(
                Config.THREAD_P_GENERAL, new PriorityThreadFactory(
                        "GerenalSTPool", Thread.NORM_PRIORITY));

        this._ioPacketsThreadPool = new ThreadPoolExecutor(2,
                Integer.MAX_VALUE, 5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(), new PriorityThreadFactory(
                        "I/O Packet Pool", Thread.NORM_PRIORITY + 1));

        this._generalPacketsThreadPool = new ThreadPoolExecutor(4, 6, 15L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
                new PriorityThreadFactory("Normal Packet Pool",
                        Thread.NORM_PRIORITY + 1));

        this._generalThreadPool = new ThreadPoolExecutor(2, 4, 5L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
                new PriorityThreadFactory("General Pool", Thread.NORM_PRIORITY));

        // 将真正用在未来的AI实施。
        this._aiThreadPool = new ThreadPoolExecutor(1, Config.AI_MAX_THREAD,
                10L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        this._aiScheduledThreadPool = new ScheduledThreadPoolExecutor(
                Config.AI_MAX_THREAD, new PriorityThreadFactory("AISTPool",
                        Thread.NORM_PRIORITY));
    }

    /** 执行AI */
    public void executeAi(final Runnable r) {
        this._aiThreadPool.execute(r);
    }

    /*
     * public void executePacket(ReceivablePacket<L2GameClient> pkt) {
     * _generalPacketsThreadPool.execute(pkt); } public void
     * executeIOPacket(ReceivablePacket<L2GameClient> pkt) {
     * _ioPacketsThreadPool.execute(pkt); }
     */
    /** 执行任务 */
    public void executeTask(final Runnable r) {
        this._generalThreadPool.execute(r);
    }

    /** 获得常规统​​计 */
    public String getGeneralStats() {
        final TextBuilder tb = new TextBuilder();
        final ThreadFactory tf = this._generalThreadPool.getThreadFactory();
        if (tf instanceof PriorityThreadFactory) {
            tb.append("General Thread Pool:\r\n");
            tb.append("Tasks in the queue: "
                    + this._generalThreadPool.getQueue().size() + "\r\n");
            tb.append("Showing threads stack trace:\r\n");
            final PriorityThreadFactory ptf = (PriorityThreadFactory) tf;
            final int count = ptf.getGroup().activeCount();
            final Thread[] threads = new Thread[count + 2];
            ptf.getGroup().enumerate(threads);
            tb.append("There should be " + count + " Threads\r\n");
            for (final Thread t : threads) {
                if (t == null) {
                    continue;
                }
                tb.append(t.getName() + "\r\n");
                for (final StackTraceElement ste : t.getStackTrace()) {
                    tb.append(ste.toString());
                    tb.append("\r\n");
                }
            }
        }
        tb.append("Packet Tp stack traces printed.\r\n");
        return tb.toString();
    }

    /** 得到的IO包统计 */
    public String getIOPacketStats() {
        final TextBuilder tb = new TextBuilder();
        final ThreadFactory tf = this._ioPacketsThreadPool.getThreadFactory();
        if (tf instanceof PriorityThreadFactory) {
            tb.append("I/O Packet Thread Pool:\r\n");
            tb.append("Tasks in the queue: "
                    + this._ioPacketsThreadPool.getQueue().size() + "\r\n");
            tb.append("Showing threads stack trace:\r\n");
            final PriorityThreadFactory ptf = (PriorityThreadFactory) tf;
            final int count = ptf.getGroup().activeCount();
            final Thread[] threads = new Thread[count + 2];
            ptf.getGroup().enumerate(threads);
            tb.append("There should be " + count + " Threads\r\n");
            for (final Thread t : threads) {
                if (t == null) {
                    continue;
                }
                tb.append(t.getName() + "\r\n");
                for (final StackTraceElement ste : t.getStackTrace()) {
                    tb.append(ste.toString());
                    tb.append("\r\n");
                }
            }
        }
        tb.append("Packet Tp stack traces printed.\r\n");
        return tb.toString();
    }

    /**
     * 获得包统计
     */
    public String getPacketStats() {
        final TextBuilder tb = new TextBuilder();
        final ThreadFactory tf = this._generalPacketsThreadPool
                .getThreadFactory();
        if (tf instanceof PriorityThreadFactory) {
            tb.append("General Packet Thread Pool:\r\n");
            tb.append("Tasks in the queue: "
                    + this._generalPacketsThreadPool.getQueue().size() + "\r\n");
            tb.append("Showing threads stack trace:\r\n");
            final PriorityThreadFactory ptf = (PriorityThreadFactory) tf;
            final int count = ptf.getGroup().activeCount();
            final Thread[] threads = new Thread[count + 2];
            ptf.getGroup().enumerate(threads);
            tb.append("There should be " + count + " Threads\r\n");
            for (final Thread t : threads) {
                if (t == null) {
                    continue;
                }
                tb.append(t.getName() + "\r\n");
                for (final StackTraceElement ste : t.getStackTrace()) {
                    tb.append(ste.toString());
                    tb.append("\r\n");
                }
            }
        }
        tb.append("Packet Tp stack traces printed.\r\n");
        return tb.toString();
    }

    /** 得到统计 */
    public String[] getStats() {
        return new String[] {
                "STP:",
                " + Effects:",
                " |- ActiveThreads:   "
                        + this._effectsScheduledThreadPool.getActiveCount(),
                " |- getCorePoolSize: "
                        + this._effectsScheduledThreadPool.getCorePoolSize(),
                " |- PoolSize:        "
                        + this._effectsScheduledThreadPool.getPoolSize(),
                " |- MaximumPoolSize: "
                        + this._effectsScheduledThreadPool.getMaximumPoolSize(),
                " |- CompletedTasks:  "
                        + this._effectsScheduledThreadPool
                                .getCompletedTaskCount(),
                " |- ScheduledTasks:  "
                        + (this._effectsScheduledThreadPool.getTaskCount() - this._effectsScheduledThreadPool
                                .getCompletedTaskCount()),
                " | -------",
                " + General:",
                " |- ActiveThreads:   "
                        + this._generalScheduledThreadPool.getActiveCount(),
                " |- getCorePoolSize: "
                        + this._generalScheduledThreadPool.getCorePoolSize(),
                " |- PoolSize:        "
                        + this._generalScheduledThreadPool.getPoolSize(),
                " |- MaximumPoolSize: "
                        + this._generalScheduledThreadPool.getMaximumPoolSize(),
                " |- CompletedTasks:  "
                        + this._generalScheduledThreadPool
                                .getCompletedTaskCount(),
                " |- ScheduledTasks:  "
                        + (this._generalScheduledThreadPool.getTaskCount() - this._generalScheduledThreadPool
                                .getCompletedTaskCount()),
                " | -------",
                " + AI:",
                " |- ActiveThreads:   "
                        + this._aiScheduledThreadPool.getActiveCount(),
                " |- getCorePoolSize: "
                        + this._aiScheduledThreadPool.getCorePoolSize(),
                " |- PoolSize:        "
                        + this._aiScheduledThreadPool.getPoolSize(),
                " |- MaximumPoolSize: "
                        + this._aiScheduledThreadPool.getMaximumPoolSize(),
                " |- CompletedTasks:  "
                        + this._aiScheduledThreadPool.getCompletedTaskCount(),
                " |- ScheduledTasks:  "
                        + (this._aiScheduledThreadPool.getTaskCount() - this._aiScheduledThreadPool
                                .getCompletedTaskCount()),
                "TP:",
                " + Packets:",
                " |- ActiveThreads:   "
                        + this._generalPacketsThreadPool.getActiveCount(),
                " |- getCorePoolSize: "
                        + this._generalPacketsThreadPool.getCorePoolSize(),
                " |- MaximumPoolSize: "
                        + this._generalPacketsThreadPool.getMaximumPoolSize(),
                " |- LargestPoolSize: "
                        + this._generalPacketsThreadPool.getLargestPoolSize(),
                " |- PoolSize:        "
                        + this._generalPacketsThreadPool.getPoolSize(),
                " |- CompletedTasks:  "
                        + this._generalPacketsThreadPool
                                .getCompletedTaskCount(),
                " |- QueuedTasks:     "
                        + this._generalPacketsThreadPool.getQueue().size(),
                " | -------",
                " + I/O Packets:",
                " |- ActiveThreads:   "
                        + this._ioPacketsThreadPool.getActiveCount(),
                " |- getCorePoolSize: "
                        + this._ioPacketsThreadPool.getCorePoolSize(),
                " |- MaximumPoolSize: "
                        + this._ioPacketsThreadPool.getMaximumPoolSize(),
                " |- LargestPoolSize: "
                        + this._ioPacketsThreadPool.getLargestPoolSize(),
                " |- PoolSize:        "
                        + this._ioPacketsThreadPool.getPoolSize(),
                " |- CompletedTasks:  "
                        + this._ioPacketsThreadPool.getCompletedTaskCount(),
                " |- QueuedTasks:     "
                        + this._ioPacketsThreadPool.getQueue().size(),
                " | -------",
                " + General Tasks:",
                " |- ActiveThreads:   "
                        + this._generalThreadPool.getActiveCount(),
                " |- getCorePoolSize: "
                        + this._generalThreadPool.getCorePoolSize(),
                " |- MaximumPoolSize: "
                        + this._generalThreadPool.getMaximumPoolSize(),
                " |- LargestPoolSize: "
                        + this._generalThreadPool.getLargestPoolSize(),
                " |- PoolSize:        " + this._generalThreadPool.getPoolSize(),
                " |- CompletedTasks:  "
                        + this._generalThreadPool.getCompletedTaskCount(),
                " |- QueuedTasks:     "
                        + this._generalThreadPool.getQueue().size(),
                " | -------", " + AI:", " |- Not Done" };
    }

    /** 正在关机 */
    public boolean isShutdown() {
        return this._shutdown;
    }

    /**
     * 清除
     */
    public void purge() {
        this._effectsScheduledThreadPool.purge();
        this._generalScheduledThreadPool.purge();
        this._aiScheduledThreadPool.purge();
        this._ioPacketsThreadPool.purge();
        this._generalPacketsThreadPool.purge();
        this._generalThreadPool.purge();
        this._aiThreadPool.purge();
    }

    /** AI时间表 */
    public ScheduledFuture<?> scheduleAi(final Runnable r, long delay) {
        try {
            if (delay < 0) {
                delay = 0;
            }
            return this._aiScheduledThreadPool.schedule(r, delay,
                    TimeUnit.MILLISECONDS);
        } catch (final RejectedExecutionException e) {
            return null; /* shutdown, ignore */
        }
    }

    /** 在固定汇率的AI时间表 */
    public ScheduledFuture<?> scheduleAiAtFixedRate(final Runnable r,
            long initial, long delay) {
        try {
            if (delay < 0) {
                delay = 0;
            }
            if (initial < 0) {
                initial = 0;
            }
            return this._aiScheduledThreadPool.scheduleAtFixedRate(r, initial,
                    delay, TimeUnit.MILLISECONDS);
        } catch (final RejectedExecutionException e) {
            return null; /* shutdown, ignore */
        }
    }

    /** 时间表的影响 */
    public ScheduledFuture<?> scheduleEffect(final Runnable r, long delay) {
        try {
            if (delay < 0) {
                delay = 0;
            }
            return this._effectsScheduledThreadPool.schedule(r, delay,
                    TimeUnit.MILLISECONDS);
        } catch (final RejectedExecutionException e) {
            return null; /* shutdown, ignore */
        }
    }

    /** 在固定汇率的时间表的影响 */
    public ScheduledFuture<?> scheduleEffectAtFixedRate(final Runnable r,
            long initial, long delay) {
        try {
            if (delay < 0) {
                delay = 0;
            }
            if (initial < 0) {
                initial = 0;
            }
            return this._effectsScheduledThreadPool.scheduleAtFixedRate(r,
                    initial, delay, TimeUnit.MILLISECONDS);
        } catch (final RejectedExecutionException e) {
            return null; /* shutdown, ignore */
        }
    }

    /** 共通的时间表 */
    public ScheduledFuture<?> scheduleGeneral(final Runnable r, long delay) {
        try {
            if (delay < 0) {
                delay = 0;
            }
            return this._generalScheduledThreadPool.schedule(r, delay,
                    TimeUnit.MILLISECONDS);
        } catch (final RejectedExecutionException e) {
            return null; /* shutdown, ignore */
        }
    }

    /** 在固定汇率的共通时间表 */
    public ScheduledFuture<?> scheduleGeneralAtFixedRate(final Runnable r,
            long initial, long delay) {
        try {
            if (delay < 0) {
                delay = 0;
            }
            if (initial < 0) {
                initial = 0;
            }
            return this._generalScheduledThreadPool.scheduleAtFixedRate(r,
                    initial, delay, TimeUnit.MILLISECONDS);
        } catch (final RejectedExecutionException e) {
            return null; /* shutdown, ignore */
        }
    }

    /**
     * 关机
     */
    public void shutdown() {
        this._shutdown = true;
        try {
            this._effectsScheduledThreadPool.awaitTermination(1,
                    TimeUnit.SECONDS);
            this._generalScheduledThreadPool.awaitTermination(1,
                    TimeUnit.SECONDS);
            this._generalPacketsThreadPool
                    .awaitTermination(1, TimeUnit.SECONDS);
            this._ioPacketsThreadPool.awaitTermination(1, TimeUnit.SECONDS);
            this._generalThreadPool.awaitTermination(1, TimeUnit.SECONDS);
            this._aiThreadPool.awaitTermination(1, TimeUnit.SECONDS);
            this._effectsScheduledThreadPool.shutdown();
            this._generalScheduledThreadPool.shutdown();
            this._generalPacketsThreadPool.shutdown();
            this._ioPacketsThreadPool.shutdown();
            this._generalThreadPool.shutdown();
            this._aiThreadPool.shutdown();
            System.out.println("现在清空所有的线程池。");

        } catch (final InterruptedException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);

        }
    }
}
