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
package com.lineage.server.taskmanager.tasks;

import com.lineage.server.Shutdown;
import com.lineage.server.taskmanager.Task;
import com.lineage.server.taskmanager.TaskManager.ExecutedTask;

/**
 * @author Layane
 */
public final class TaskRestart extends Task {
    public static String NAME = "restart";

    /*
     * (non-Javadoc)
     * 
     * @see com.lineage.server.tasks.Task#getName()
     */
    @Override
    public String getName() {
        return NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.lineage.server.tasks.Task#onTimeElapsed(com.lineage.server.tasks.
     * TaskManager.ExecutedTask)
     */
    @Override
    public void onTimeElapsed(final ExecutedTask task) {
        final Shutdown handler = new Shutdown(
                Integer.valueOf(task.getParams()[2]), true);
        handler.start();
    }

}
