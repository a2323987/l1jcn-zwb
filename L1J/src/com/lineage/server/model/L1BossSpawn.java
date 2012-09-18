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
package com.lineage.server.model;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.GeneralThreadPool;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.utils.Random;

/**
 * 产生BOSS
 */
public class L1BossSpawn extends L1Spawn {

    private class SpawnTask implements Runnable {
        private final int _spawnNumber;
        private final int _objectId;

        SpawnTask(final int spawnNumber, final int objectId) {
            this._spawnNumber = spawnNumber;
            this._objectId = objectId;
        }

        @Override
        public void run() {
            L1BossSpawn.this.doSpawn(this._spawnNumber, this._objectId);
        }
    }

    private static Logger _log = Logger.getLogger(L1BossSpawn.class.getName());

    private int _spawnCount;

    private String _cycleType;

    private int _percentage;

    private L1BossCycle _cycle;

    private Calendar _activeSpawnTime;

    public L1BossSpawn(final L1Npc mobTemplate) {
        super(mobTemplate);
    }

    // 计算出确率计算下一次的出现时间
    private Calendar calcNextSpawnTime(Calendar cal) {
        do {
            cal = this._cycle.nextSpawnTime(cal);
        } while (!(this._percentage > Random.nextInt(100)));
        return cal;
    }

    /**
     * SpawnTask启动。
     * 
     * @param spawnNumber
     *            L1Spawn管理这些数字。也可以指定任意一点。
     */
    @Override
    public void executeSpawnTask(final int spawnNumber, final int objectId) {
        // countをデクリメントして全部死んだかチェック
        if (this.subAndGetCount() != 0) {
            return; // 没有全部死
        }
        // 前回出现时间に对して、次の出现时间を算出
        Calendar spawnTime;
        final Calendar now = Calendar.getInstance(); // 现时刻
        final Calendar latestStart = this._cycle.getLatestStartTime(now); // 现时刻に对する最近の周期の开始时间

        final Calendar activeStart = this._cycle
                .getSpawnStartTime(this._activeSpawnTime); // アクティブだった周期の开始时间
        // アクティブだった周期の开始时间 >= 最近の周期开始时间の场合、次の出现
        if (!activeStart.before(latestStart)) {
            spawnTime = this.calcNextSpawnTime(activeStart);
        } else {
            // アクティブだった周期の开始时间 < 最近の周期开始时间の场合は、最近の周期で出现
            // わかりづらいが确率计算する为に、无理やりcalcNextSpawnTimeを通している。
            latestStart.add(Calendar.SECOND, -1);
            spawnTime = this.calcNextSpawnTime(this._cycle
                    .getLatestStartTime(latestStart));
        }
        this.spawnBoss(spawnTime, objectId);
    }

    @Override
    public void init() {
        if (this._percentage <= 0) {
            return;
        }
        this._cycle = L1BossCycle.getBossCycle(this._cycleType);
        if (this._cycle == null) {
            throw new RuntimeException(this._cycleType + " not found");
        }
        final Calendar now = Calendar.getInstance();
        // 出现时间
        Calendar spawnTime;
        if (Config.INIT_BOSS_SPAWN && (this._percentage > Random.nextInt(100))) {
            spawnTime = this._cycle.calcSpawnTime(now);

        } else {
            spawnTime = this.calcNextSpawnTime(now);
        }
        this.spawnBoss(spawnTime, 0);
    }

    public void setCycleType(final String type) {
        this._cycleType = type;
    }

    public void setPercentage(final int percentage) {
        this._percentage = percentage;
    }

    // 指定时间BOSS出现计划
    private void spawnBoss(final Calendar spawnTime, final int objectId) {
        // 保存这次的出现时间。再出现时使用。
        this._activeSpawnTime = spawnTime;
        final long delay = spawnTime.getTimeInMillis()
                - System.currentTimeMillis();

        int cnt = this._spawnCount;
        this._spawnCount = this.getAmount();
        while (cnt < this.getAmount()) {
            cnt++;
            GeneralThreadPool.getInstance().schedule(
                    new SpawnTask(0, objectId), delay);
        }
        _log.log(Level.FINE, this.toString());
    }

    private synchronized int subAndGetCount() {
        return --this._spawnCount;
    }

    /**
     * 表示现在活动的BOSS 对应周期出现时间表。
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[MOB]npcid:" + this.getNpcId());
        builder.append(" name:" + this.getName());
        builder.append("[Type]" + this._cycle.getName());
        builder.append("[现在周期]");
        builder.append(this._cycle.getSpawnStartTime(this._activeSpawnTime)
                .getTime());
        builder.append(" - ");
        builder.append(this._cycle.getSpawnEndTime(this._activeSpawnTime)
                .getTime());
        builder.append("[出现时间]");
        builder.append(this._activeSpawnTime.getTime());
        return builder.toString();
    }
}
