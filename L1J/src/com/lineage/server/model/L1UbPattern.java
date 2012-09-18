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

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.lineage.server.utils.collections.Lists;
import com.lineage.server.utils.collections.Maps;

/**
 * UB模式
 */
public class L1UbPattern {

    private boolean _isFrozen = false;

    private final Map<Integer, List<L1UbSpawn>> _groups = Maps.newMap();

    public void addSpawn(final int groupNumber, final L1UbSpawn spawn) {
        if (this._isFrozen) {
            return;
        }

        List<L1UbSpawn> spawnList = this._groups.get(groupNumber);
        if (spawnList == null) {
            spawnList = Lists.newList();
            this._groups.put(groupNumber, spawnList);
        }

        spawnList.add(spawn);
    }

    public void freeze() {
        if (this._isFrozen) {
            return;
        }

        // 按ID排序，产生一个组包含列表
        for (final List<L1UbSpawn> spawnList : this._groups.values()) {
            Collections.sort(spawnList);
        }

        this._isFrozen = true;
    }

    public List<L1UbSpawn> getSpawnList(final int groupNumber) {
        if (!this._isFrozen) {
            return null;
        }

        return this._groups.get(groupNumber);
    }

    public boolean isFrozen() {
        return this._isFrozen;
    }
}
