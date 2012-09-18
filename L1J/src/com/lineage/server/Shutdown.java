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

import java.util.logging.Logger;

import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 这个类提供了关闭并重新启动服务器的功能 <br>
 * 它关闭所有打开的clientconnections，并保存所有数据。
 * 
 * @version $Revision: 1.2 $ $Date: 2004/11/18 15:43:30 $
 */
public class Shutdown extends Thread {

    private static Logger _log = Logger.getLogger(Shutdown.class.getName());

    private static Shutdown _instance;

    /** 计数器实例 */
    private static Shutdown _counterInstance = null;

    /** 秒关机 */
    private int secondsShut;

    /** 关机状态 */
    private int shutdownMode;

    /** 终止一个进程 */
    public static final int SIGTERM = 0;

    /** GM关机 */
    public static final int GM_SHUTDOWN = 1;

    /** GM重新启动 */
    public static final int GM_RESTART = 2;

    /** 中断 */
    public static final int ABORT = 3;

    private static String[] _modeText = { "【经由‘黑盒子’ 关闭伺服器】",
            "【‘游戏管理员’执行 关闭伺服器 动作】", "【‘游戏管理员’执行 重启伺服器 动作】", "【中断动作】" };

    /**
     * 关机-hook的实例被创建，得到关机-hook的实例 第一次调用此功能，但它已被registrered externaly。
     * 
     * @return 例如关机，关闭hook被用作
     */
    public static Shutdown getInstance() {
        if (_instance == null) {
            _instance = new Shutdown();
        }
        return _instance;
    }

    /**
     * 默认constucter是只用于内部创建关机-hook <br>
     * instance
     */
    public Shutdown() {
        this.secondsShut = -1;
        this.shutdownMode = SIGTERM;
    }

    /**
     * 这将创建一个关机倒计时实例。
     * 
     * @param seconds
     *            多少秒，直到关机。
     * @param restart
     *            真正的服务器关机后重新启动。
     */
    public Shutdown(int seconds, final boolean restart) {
        if (seconds < 0) {
            seconds = 0;
        }
        this.secondsShut = seconds;
        if (restart) {
            this.shutdownMode = GM_RESTART;
        } else {
            this.shutdownMode = GM_SHUTDOWN;
        }
    }

    /**
     * 设置关机模式下中止
     */
    private void _abort() {
        this.shutdownMode = ABORT;
    }

    /**
     * 此功能将中止正在运行的倒计时
     * 
     * @param activeChar
     *            GM who 发出中止命令
     */
    public void abort(final L1PcInstance activeChar) {
        final Announcements _an = Announcements.getInstance();
        _log.warning("‘游戏管理员’: " + activeChar.getName() + " 使用指令中断之前的行为。");
        _an.announceToAll("伺服器【中断关机】 并维持正常运作！");

        if (_counterInstance != null) {
            _counterInstance._abort();
        }
    }

    /**
     * 倒计时计数，并报告给所有玩家进入倒计时 <br>
     * 中止，如果中止的模式改变
     */
    private void countdown() {
        final Announcements _an = Announcements.getInstance();

        try {
            while (this.secondsShut > 0) {

                switch (this.secondsShut) {
                    case 240:
                        _an.announceToAll("服务器将在4分钟内关闭。");
                        break;
                    case 180:
                        _an.announceToAll("服务器将在3分钟内关闭。");
                        break;
                    case 120:
                        _an.announceToAll("服务器将在2分钟内关闭。");
                        break;
                    case 60:
                        _an.announceToAll("服务器将在1分钟内关闭。");
                        break;
                    case 30:
                        _an.announceToAll("服务器将在30秒内关闭。");
                        break;
                    case 10:
                        _an.announceToAll("服务器将在10秒内关闭。");
                        break;
                    case 5:
                        _an.announceToAll("服务器将在5秒内关闭。");
                        break;
                    case 4:
                        _an.announceToAll("服务器将在4秒内关闭。");
                        break;
                    case 3:
                        _an.announceToAll("服务器将在3秒内关闭。");
                        break;
                    case 2:
                        _an.announceToAll("服务器将在2秒内关闭。");
                        break;
                    case 1:
                        _an.announceToAll("服务器将在1秒内关闭。");
                        break;
                }

                this.secondsShut--;

                final int delay = 1000; // 毫秒
                Thread.sleep(delay);

                if (this.shutdownMode == ABORT) {
                    break;
                }
            }
        } catch (final InterruptedException e) {
            // this will never happen
            // 这绝不会发生
        }
    }

    /**
     * 设置关机模式
     * 
     * @param mode
     *            应设置什么样的模式
     */
    int getMode() {
        return this.shutdownMode;
    }

    /**
     * 这个函数被调用，当一个新的线程启动 如果此线程的getInstance线程，那么这就是关机 hook，我们保存所有数据，并断开所有客户端。
     * 这个线头后，服务器将完全退出 如果不是这样的getInstance线程，那么这是一个倒计时线程。 我们开始倒计时，当我们完成它，它不中止，
     * 我们告诉我们为什么调用exit关机钩，然后调用exit 当服务器的退出状态是1，startServer.sh /
     * startserver.bat来启动 将重新启动服务器。
     */
    @Override
    public void run() {
        if (this == _instance) {
            // last byebye, save all data and quit this server
            // logging doesnt work here :(
            this.saveData();
            // server will quit, when this function ends.
        } else {
            // gm shutdown: send warnings and then call exit to start shutdown
            // sequence
            this.countdown();
            // last point where logging is operational :(
            _log.warning("‘游戏管理员’ 关闭伺服器 倒数。" + _modeText[this.shutdownMode]
                    + " NOW！");
            switch (this.shutdownMode) {
                case GM_SHUTDOWN:
                    _instance.setMode(GM_SHUTDOWN);
                    System.gc();// 内存回收
                    System.exit(0);
                    break;
                case GM_RESTART:
                    _instance.setMode(GM_RESTART);
                    System.gc(); // 内存回收
                    System.exit(1);
                    break;
            }
        }
    }

    /**
     * 发送一个最后信息 (byebye)，断开所有的角色和保存数据
     */
    private void saveData() {
        final Announcements _an = Announcements.getInstance();
        switch (this.shutdownMode) {
            case SIGTERM:
                System.err.println("【 动作：经由主程式 执行关闭 】");
                break;
            case GM_SHUTDOWN:
                System.err.println("【 动作：‘游戏管理员’ 执行关闭!!! 】");
                break;
            case GM_RESTART:
                System.err.println("【 动作：‘游戏管理员’ 执行重启!!! 】");
                break;

        }
        _an.announceToAll("伺服器目前是" + _modeText[this.shutdownMode]
                + " NOW! bye bye");

        // we cannt abort shutdown anymore, so i removed the "if"
        GameServer.getInstance().disconnectAllCharacters();

        System.err.println("【资料储存完毕，强制玩家全部离线】");
        try {
            final int delay = 500;
            Thread.sleep(delay);
        } catch (final InterruptedException e) {
            // never happens :p
        }
    }

    /**
     * 设置关机模式
     * 
     * @param mode
     *            应设置什么样的模式
     */
    private void setMode(final int mode) {
        this.shutdownMode = mode;
    }

    /**
     * 此功能启动关机倒计时。
     * 
     * @param activeChar
     *            GM who 发出关机指令
     * @param seconds
     *            秒，直到关机
     * @param restart
     *            如果服务器关机后重新启动
     */
    public void startShutdown(final L1PcInstance activeChar, final int seconds,
            final boolean restart) {
        final Announcements _an = Announcements.getInstance();
        _log.warning("‘游戏管理员’: " + activeChar.getId() + " 使用关机指令。"
                + _modeText[this.shutdownMode] + " 在 " + seconds + " 秒！");
        _an.announceToAll("伺服器 是 " + _modeText[this.shutdownMode] + " 在 "
                + seconds + " 秒！");

        if (_counterInstance != null) {
            _counterInstance._abort();
        }

        // the main instance should only run for shutdown hook, so we start a
        // new instance
        _counterInstance = new Shutdown(seconds, restart);
        GeneralThreadPool.getInstance().execute(_counterInstance);
    }

    public void startTelnetShutdown(final String IP, final int seconds,
            final boolean restart) {
        final Announcements _an = Announcements.getInstance();
        _log.warning("IP: " + IP + " 使用关闭指令。" + _modeText[this.shutdownMode]
                + " 在 " + seconds + " 秒！");
        _an.announceToAll("服务器 是 " + _modeText[this.shutdownMode] + " 在 "
                + seconds + " 秒！");

        if (_counterInstance != null) {
            _counterInstance._abort();
        }
        _counterInstance = new Shutdown(seconds, restart);
        GeneralThreadPool.getInstance().execute(_counterInstance);
    }

    /**
     * 此功能将中止正在运行的倒计时
     * 
     * @param IP
     * <br>
     * <br>
     *            IP 发出关机指令
     */
    public void Telnetabort(final String IP) {
        final Announcements _an = Announcements.getInstance();
        _log.warning("IP: " + IP + " 使用中断关闭指令。" + _modeText[this.shutdownMode]
                + " 已停止！");
        _an.announceToAll("伺服器中断了 " + _modeText[this.shutdownMode]
                + " 并维持正常运作！");

        if (_counterInstance != null) {
            _counterInstance._abort();
        }
    }
}
