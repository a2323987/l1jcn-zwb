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
package com.lineage.server.taskmanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.taskmanager.tasks.TaskRestart;
import com.lineage.server.taskmanager.tasks.TaskShutdown;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Lists;
import com.lineage.server.utils.collections.Maps;

/**
 * @author Layane
 */
public final class TaskManager {

    public class ExecutedTask implements Runnable {
        int _id;

        long _lastActivation;

        Task _task;

        TaskTypes _type;

        String[] _params;

        ScheduledFuture<?> _scheduled;

        public ExecutedTask(final Task task, final TaskTypes type,
                final ResultSet rset) throws SQLException {
            this._task = task;
            this._type = type;
            this._id = rset.getInt("id");
            this._lastActivation = rset.getLong("last_activation");
            this._params = new String[] { rset.getString("param1"),
                    rset.getString("param2"), rset.getString("param3") };
        }

        @Override
        public boolean equals(final Object object) {
            return this._id == ((ExecutedTask) object)._id;
        }

        public int getId() {
            return this._id;
        }

        public long getLastActivation() {
            return this._lastActivation;
        }

        public String[] getParams() {
            return this._params;
        }

        public Task getTask() {
            return this._task;
        }

        public TaskTypes getType() {
            return this._type;
        }

        @Override
        public void run() {
            this._task.onTimeElapsed(this);

            this._lastActivation = System.currentTimeMillis();

            java.sql.Connection con = null;
            PreparedStatement pstm = null;
            try {
                con = L1DatabaseFactory.getInstance().getConnection();
                pstm = con.prepareStatement(SQL_STATEMENTS[1]);
                pstm.setLong(1, this._lastActivation);
                pstm.setInt(2, this._id);
                pstm.executeUpdate();
            } catch (final SQLException e) {
                _log.warning("cannot updated the Global Task " + this._id
                        + ": " + e.getMessage());
            } finally {
                SQLUtil.close(pstm);
                SQLUtil.close(con);
            }

        }

        public void stopTask() {
            this._task.onDestroy();

            if (this._scheduled != null) {
                this._scheduled.cancel(true);
            }

            TaskManager.this._currentTasks.remove(this);
        }

    }

    protected static final Logger _log = Logger.getLogger(TaskManager.class
            .getName());

    private static TaskManager _instance;

    protected static final String[] SQL_STATEMENTS = {
            "SELECT id,task,type,last_activation,param1,param2,param3 FROM global_tasks",
            "UPDATE global_tasks SET last_activation=? WHERE id=?",
            "SELECT id FROM global_tasks WHERE task=?",
            "INSERT INTO global_tasks (task,type,last_activation,param1,param2,param3) VALUES(?,?,?,?,?,?)" };

    public static boolean addTask(final String task, final TaskTypes type,
            final String param1, final String param2, final String param3) {
        return addTask(task, type, param1, param2, param3, 0);
    }

    public static boolean addTask(final String task, final TaskTypes type,
            final String param1, final String param2, final String param3,
            final long lastActivation) {
        java.sql.Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_STATEMENTS[3]);
            pstm.setString(1, task);
            pstm.setString(2, type.toString());
            pstm.setLong(3, lastActivation);
            pstm.setString(4, param1);
            pstm.setString(5, param2);
            pstm.setString(6, param3);
            pstm.execute();

            return true;
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, "cannot add the task", e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }

        return false;
    }

    public static boolean addUniqueTask(final String task,
            final TaskTypes type, final String param1, final String param2,
            final String param3) {
        return addUniqueTask(task, type, param1, param2, param3, 0);
    }

    public static boolean addUniqueTask(final String task,
            final TaskTypes type, final String param1, final String param2,
            final String param3, final long lastActivation) {
        java.sql.Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_STATEMENTS[2]);
            pstm.setString(1, task);
            rs = pstm.executeQuery();

            if (!rs.next()) {
                pstm = con.prepareStatement(SQL_STATEMENTS[3]);
                pstm.setString(1, task);
                pstm.setString(2, type.toString());
                pstm.setLong(3, lastActivation);
                pstm.setString(4, param1);
                pstm.setString(5, param2);
                pstm.setString(6, param3);
                pstm.execute();
            }

            return true;
        } catch (final SQLException e) {
            _log.warning("cannot add the unique task: " + e.getMessage());
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }

        return false;
    }

    public static TaskManager getInstance() {
        if (_instance == null) {
            _instance = new TaskManager();
        }
        return _instance;
    }

    private final Map<Integer, Task> _tasks = Maps.newMap();

    protected final List<ExecutedTask> _currentTasks = Lists.newList();

    public TaskManager() {
        this.initializate();
        this.startAllTasks();
    }

    private void initializate() {
        this.registerTask(new TaskRestart());
        this.registerTask(new TaskShutdown());
    }

    public void registerTask(final Task task) {
        final int key = task.getName().hashCode();
        if (!this._tasks.containsKey(key)) {
            this._tasks.put(key, task);
            task.initializate();
        }
    }

    private void startAllTasks() {
        java.sql.Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement(SQL_STATEMENTS[0]);
            rs = pstm.executeQuery();

            while (rs.next()) {
                final Task task = this._tasks.get(rs.getString("task").trim()
                        .toLowerCase().hashCode());

                if (task == null) {
                    continue;
                }

            }

        } catch (final Exception e) {
            _log.log(Level.SEVERE, "error while loading Global Task table", e);
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (final SQLException ignore) {
                    // ignore
                }
                rs = null;
            }

            if (null != pstm) {
                try {
                    pstm.close();
                } catch (final SQLException ignore) {
                    // ignore
                }
                pstm = null;
            }

            if (null != con) {
                try {
                    con.close();
                } catch (final SQLException ignore) {
                    // ignore
                }
                con = null;
            }
        }

    }

}
