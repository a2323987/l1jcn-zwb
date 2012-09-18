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

public class L1Drop {
    int _mobId;

    int _itemId;

    int _min;

    int _max;

    int _chance;

    public L1Drop(final int mobId, final int itemId, final int min,
            final int max, final int chance) {
        this._mobId = mobId;
        this._itemId = itemId;
        this._min = min;
        this._max = max;
        this._chance = chance;
    }

    public int getChance() {
        return this._chance;
    }

    public int getItemid() {
        return this._itemId;
    }

    public int getMax() {
        return this._max;
    }

    public int getMin() {
        return this._min;
    }

    public int getMobid() {
        return this._mobId;
    }
}
