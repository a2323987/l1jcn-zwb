/**
 * License THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS
 * CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). THE WORK IS PROTECTED
 * BY COPYRIGHT AND/OR OTHER APPLICABLE LAW. ANY USE OF THE WORK OTHER THAN AS
 * AUTHORIZED UNDER THIS LICENSE OR COPYRIGHT LAW IS PROHIBITED.
 * 
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND AGREE TO
 * BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE MAY BE
 * CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */

package com.lineage.server.templates;

import java.util.Collections;
import java.util.List;

import com.lineage.server.utils.collections.Lists;

public class L1MobGroup {
    private final int _id;

    private final int _leaderId;

    private final List<L1NpcCount> _minions = Lists.newList();

    private final boolean _isRemoveGroupIfLeaderDie;

    public L1MobGroup(final int id, final int leaderId,
            final List<L1NpcCount> minions,
            final boolean isRemoveGroupIfLeaderDie) {
        this._id = id;
        this._leaderId = leaderId;
        this._minions.addAll(minions); // 参照コピーの方が速いが、不変性が保証できない
        this._isRemoveGroupIfLeaderDie = isRemoveGroupIfLeaderDie;
    }

    public int getId() {
        return this._id;
    }

    public int getLeaderId() {
        return this._leaderId;
    }

    public List<L1NpcCount> getMinions() {
        return Collections.unmodifiableList(this._minions);
    }

    public boolean isRemoveGroupIfLeaderDie() {
        return this._isRemoveGroupIfLeaderDie;
    }

}
