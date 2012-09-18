/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.lineage.server.model.gametime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.L1DatabaseFactory;
import com.lineage.server.Account;
import com.lineage.server.ClientThread;
import com.lineage.server.GameServer;
import com.lineage.server.GeneralThreadPool;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_BlueMessage;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.utils.SQLUtil;

/**
 * 定时自动重启伺服器
 */
public class L1GameReStart {

    private class TimeUpdaterRestar implements Runnable {

        public TimeUpdaterRestar() {
            // TODO Auto-generated constructor stub
        }

        @Override
        public void run() {
            while (true) {
                L1GameReStart.this._previousTime = L1GameReStart.this._currentTime;
                L1GameReStart.this._currentTime = new L1GameTime();
                L1GameReStart.this.notifyChanged();
                final int remnant = L1GameReStart.this.GetRestartTime() * 60;
                System.out.println("╠》正在载入 自动重开设定...完成!\t"
                        + L1GameReStart.this.GetRestartTime() + " 分钟后");
                while (remnant > 0) {
                    for (int i = remnant; i >= 0; i--) {
                        L1GameReStart.this.SetRemnant(i);
                        willRestartTime = i;

                        // (五分钟内 一分钟一次)
                        if ((i % 60 == 0) && (i <= 300) && (i != 0)) {
                            // \f3伺服器将会在 %0 分钟后重新启动。请至安全区准备退出。
                            L1World.getInstance().broadcastPacketToAll(
                                    new S_BlueMessage(166, "\\f3伺服器将会在 " + i
                                            / 60 + " 分钟后重新启动。请至安全区准备退出"));
                            System.out.println("伺服器将于 " + i / 60 + " 分钟后重新启动");
                        }

                        // (30秒内 一秒一次)
                        else if ((i <= 30) && (i != 0)) {
                            // \f3服务器在 %0秒后关闭。请离开游戏。
                            L1World.getInstance().broadcastPacketToAll(
                                    new S_BlueMessage(72, "" + i + ""));
                            System.out.println("伺服器将于 " + i + " 秒后重新启动");
                        }

                        // (1秒)
                        else if (i == 0) {
                            L1GameReStart.this.BroadCastToAll("服务器自动重新启动。");
                            System.out.println("服务器自动重新启动。");
                            GameServer.getInstance().shutdown(); // TODO
                                                                 // 修正自动重开角色资料会回溯
                            L1GameReStart.this.disconnectAllCharacters();
                            System.exit(1);
                        }

                        try {
                            Thread.sleep(1000); // 暂停1秒
                        } catch (final InterruptedException e) {
                            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                        }

                        // 整点报时
                        if (Config.HOURLY_CHIME) {
                            if ((GetNowTime.GetNowHour() == 0)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1GameReStart.this
                                        .BroadCastToAll("\\fR现在时间凌晨12点，长时间在线的玩家注意保护眼睛哦。");
                                L1World.getInstance()
                                        .broadcastPacketToAll(
                                                new S_BlueMessage(166,
                                                        "\\fR现在时间凌晨12点"));
                            }
                            if ((GetNowTime.GetNowHour() == 1)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间凌晨1点"));
                            }
                            if ((GetNowTime.GetNowHour() == 2)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间凌晨2点"));
                            }
                            if ((GetNowTime.GetNowHour() == 3)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间凌晨3点"));
                            }
                            if ((GetNowTime.GetNowHour() == 4)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间凌晨4点"));
                            }
                            if ((GetNowTime.GetNowHour() == 5)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间凌晨5点"));
                            }
                            if ((GetNowTime.GetNowHour() == 6)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间早上6点"));
                                update();
                                // System.out.println("删除地监时间、开始");
                            }
                            if ((GetNowTime.GetNowHour() == 7)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间早上7点"));
                            }
                            if ((GetNowTime.GetNowHour() == 8)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间上午8点"));
                            }
                            if ((GetNowTime.GetNowHour() == 9)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间上午9点"));
                            }
                            if ((GetNowTime.GetNowHour() == 10)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance()
                                        .broadcastPacketToAll(
                                                new S_BlueMessage(166,
                                                        "\\fR现在时间上午10点"));
                            }
                            if ((GetNowTime.GetNowHour() == 11)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance()
                                        .broadcastPacketToAll(
                                                new S_BlueMessage(166,
                                                        "\\fR现在时间上午11点"));
                            }
                            if ((GetNowTime.GetNowHour() == 12)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance()
                                        .broadcastPacketToAll(
                                                new S_BlueMessage(166,
                                                        "\\fR现在时间中午12点"));
                            }
                            if ((GetNowTime.GetNowHour() == 13)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间下午1点"));
                            }
                            if ((GetNowTime.GetNowHour() == 14)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间下午2点"));
                            }
                            if ((GetNowTime.GetNowHour() == 15)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间下午3点"));
                            }
                            if ((GetNowTime.GetNowHour() == 16)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间下午4点"));
                            }
                            if ((GetNowTime.GetNowHour() == 17)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间下午5点"));
                            }
                            if ((GetNowTime.GetNowHour() == 18)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间下午6点"));
                            }
                            if ((GetNowTime.GetNowHour() == 19)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间下午7点"));
                            }
                            if ((GetNowTime.GetNowHour() == 20)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间晚上8点"));
                            }
                            if ((GetNowTime.GetNowHour() == 21)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance().broadcastPacketToAll(
                                        new S_BlueMessage(166, "\\fR现在时间晚上9点"));
                            }
                            if ((GetNowTime.GetNowHour() == 22)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance()
                                        .broadcastPacketToAll(
                                                new S_BlueMessage(166,
                                                        "\\fR现在时间晚上10点"));
                            }
                            if ((GetNowTime.GetNowHour() == 23)
                                    && (GetNowTime.GetNowMinute() == 0)
                                    && (GetNowTime.GetNowSecond() == 0)) {
                                L1World.getInstance()
                                        .broadcastPacketToAll(
                                                new S_BlueMessage(166,
                                                        "\\fR现在时间晚上11点"));
                            }
                        }
                    }
                }
            }
        }
    }

    static Logger _log = Logger.getLogger(L1GameReStart.class.getName());

    private static L1GameReStart _instance;

    static int willRestartTime;

    public static L1GameReStart getInstance() {
        return _instance;
    }

    public static int getWillRestartTime() {
        return willRestartTime;
    }

    public static void init() {
        _instance = new L1GameReStart();
    }

    /**
     * 更新奇岩地监时间(每天早上6:00).
     */
    public static void update() {
        // System.out.println("删除地监时间");
        for (final L1PcInstance pc : L1World.getInstance().getAllPlayers()) {
            pc.setRocksPrisonTime(0);
            // System.out.println("删除角色时间");
        }
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("UPDATE characters SET RocksPrisonTime=?");
            pstm.setInt(1, 0);
            pstm.execute();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    volatile L1GameTime _currentTime = new L1GameTime();

    L1GameTime _previousTime = null;

    private final List<L1GameTimeListener> _listeners = new CopyOnWriteArrayList<L1GameTimeListener>();

    public int _remnant;

    private L1GameReStart() {
        GeneralThreadPool.getInstance().execute(new TimeUpdaterRestar());
    }

    public void addListener(final L1GameTimeListener listener) {
        this._listeners.add(listener);
    }

    void BroadCastToAll(final String string) {
        final Collection<L1PcInstance> allpc = L1World.getInstance()
                .getAllPlayers();
        for (final L1PcInstance pc : allpc) {
            pc.sendPackets(new S_SystemMessage(string));
        }
    }

    /**
     * 踢掉世界地图中所有的玩家与储存资料。
     */
    public void disconnectAllCharacters() {
        final Collection<L1PcInstance> players = L1World.getInstance()
                .getAllPlayers();
        for (final L1PcInstance pc : players) {
            pc.getNetConnection().setActiveChar(null);
            pc.getNetConnection().kick();
        }
        // 踢除所有在线上的玩家
        for (final L1PcInstance pc : players) {
            ClientThread.quitGame(pc);
            L1World.getInstance().removeObject(pc);
            final Account account = Account.load(pc.getAccountName());
            Account.online(account, false);
        }
    }

    public L1GameTime getGameTime() {
        return this._currentTime;
    }

    public int GetRemnant() {
        return this._remnant;
    }

    int GetRestartTime() {
        return Config.REST_TIME;
    }

    private boolean isFieldChanged(final int field) {
        return this._previousTime.get(field) != this._currentTime.get(field);
    }

    void notifyChanged() {
        if (this.isFieldChanged(Calendar.MONTH)) {
            for (final L1GameTimeListener listener : this._listeners) {
                listener.onMonthChanged(this._currentTime);
            }
        }
        if (this.isFieldChanged(Calendar.DAY_OF_MONTH)) {
            for (final L1GameTimeListener listener : this._listeners) {
                listener.onDayChanged(this._currentTime);
            }
        }
        if (this.isFieldChanged(Calendar.HOUR_OF_DAY)) {
            for (final L1GameTimeListener listener : this._listeners) {
                listener.onHourChanged(this._currentTime);
            }
        }
        if (this.isFieldChanged(Calendar.MINUTE)) {
            for (final L1GameTimeListener listener : this._listeners) {
                listener.onMinuteChanged(this._currentTime);
            }
        }
    }

    public void removeListener(final L1GameTimeListener listener) {
        this._listeners.remove(listener);
    }

    public void SetRemnant(final int remnant) {
        this._remnant = remnant;
    }

}
