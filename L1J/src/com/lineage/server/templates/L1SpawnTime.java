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
package com.lineage.server.templates;

import java.sql.Time;
import java.sql.Timestamp;

import com.lineage.server.utils.TimePeriod;

public class L1SpawnTime {

    public static class L1SpawnTimeBuilder {
        final int _spawnId;

        Time _timeStart;

        Time _timeEnd;

        Timestamp _periodStart;

        Timestamp _periodEnd;

        boolean _isDeleteAtEndTime;

        public L1SpawnTimeBuilder(final int spawnId) {
            this._spawnId = spawnId;
        }

        public L1SpawnTime build() {
            return new L1SpawnTime(this);
        }

        public void setDeleteAtEndTime(final boolean f) {
            this._isDeleteAtEndTime = f;
        }

        public void setPeriodEnd(final Timestamp periodEnd) {
            this._periodEnd = periodEnd;
        }

        public void setPeriodStart(final Timestamp periodStart) {
            this._periodStart = periodStart;
        }

        public void setTimeEnd(final Time timeEnd) {
            this._timeEnd = timeEnd;
        }

        public void setTimeStart(final Time timeStart) {
            this._timeStart = timeStart;
        }

    }

    private final int _spawnId;

    private final Time _timeStart;

    private final Time _timeEnd;

    private final TimePeriod _timePeriod;

    private final Timestamp _periodStart;

    private final Timestamp _periodEnd;

    private final boolean _isDeleteAtEndTime;

    L1SpawnTime(final L1SpawnTimeBuilder builder) {
        this._spawnId = builder._spawnId;
        this._timeStart = builder._timeStart;
        this._timeEnd = builder._timeEnd;
        this._timePeriod = new TimePeriod(this._timeStart, this._timeEnd);
        this._periodStart = builder._periodStart;
        this._periodEnd = builder._periodEnd;
        this._isDeleteAtEndTime = builder._isDeleteAtEndTime;
    }

    public Timestamp getPeriodEnd() {
        return this._periodEnd;
    }

    public Timestamp getPeriodStart() {
        return this._periodStart;
    }

    public int getSpawnId() {
        return this._spawnId;
    }

    public Time getTimeEnd() {
        return this._timeEnd;
    }

    public TimePeriod getTimePeriod() {
        return this._timePeriod;
    }

    public Time getTimeStart() {
        return this._timeStart;
    }

    public boolean isDeleteAtEndTime() {
        return this._isDeleteAtEndTime;
    }
}
