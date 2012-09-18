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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.utils.collections.Lists;
import com.lineage.server.utils.collections.Maps;

/**
 * 
 */
public class L1HateList {

    private final Map<L1Character, Integer> _hateMap;

    public L1HateList() {
        /*
         * ConcurrentHashMapを利用するより、 全てのメソッドを同期する方がメモリ使用量、速度共に優れていた。
         * 但し、今後このクラスの利用方法が変わった場合、 例えば多くのスレッドから同時に読み出しがかかるようになった場合は、
         * ConcurrentHashMap使用 可能是更好的。
         */
        this._hateMap = Maps.newMap();
    }

    private L1HateList(final Map<L1Character, Integer> hateMap) {
        this._hateMap = hateMap;
    }

    public synchronized void add(final L1Character cha, final int hate) {
        if (cha == null) {
            return;
        }
        if (this._hateMap.containsKey(cha)) {
            this._hateMap.put(cha, this._hateMap.get(cha) + hate);
        } else {
            this._hateMap.put(cha, hate);
        }
    }

    public synchronized void clear() {
        this._hateMap.clear();
    }

    public synchronized boolean containsKey(final L1Character cha) {
        return this._hateMap.containsKey(cha);
    }

    public synchronized L1HateList copy() {
        return new L1HateList(new HashMap<L1Character, Integer>(this._hateMap));
    }

    public synchronized Set<Entry<L1Character, Integer>> entrySet() {
        return this._hateMap.entrySet();
    }

    public synchronized int get(final L1Character cha) {
        return this._hateMap.get(cha);
    }

    public synchronized L1Character getMaxHateCharacter() {
        L1Character cha = null;
        int hate = Integer.MIN_VALUE;

        for (final Map.Entry<L1Character, Integer> e : this._hateMap.entrySet()) {
            if (hate < e.getValue()) {
                cha = e.getKey();
                hate = e.getValue();
            }
        }
        return cha;
    }

    public synchronized int getPartyHate(final L1Party party) {
        int partyHate = 0;

        for (final Map.Entry<L1Character, Integer> e : this._hateMap.entrySet()) {
            L1PcInstance pc = null;
            if (e.getKey() instanceof L1PcInstance) {
                pc = (L1PcInstance) e.getKey();
            }
            if (e.getKey() instanceof L1NpcInstance) {
                final L1Character cha = ((L1NpcInstance) e.getKey())
                        .getMaster();
                if (cha instanceof L1PcInstance) {
                    pc = (L1PcInstance) cha;
                }
            }

            if ((pc != null) && party.isMember(pc)) {
                partyHate += e.getValue();
            }
        }
        return partyHate;
    }

    public synchronized int getPartyLawfulHate(final L1Party party) {
        int partyHate = 0;

        for (final Map.Entry<L1Character, Integer> e : this._hateMap.entrySet()) {
            L1PcInstance pc = null;
            if (e.getKey() instanceof L1PcInstance) {
                pc = (L1PcInstance) e.getKey();
            }

            if ((pc != null) && party.isMember(pc)) {
                partyHate += e.getValue();
            }
        }
        return partyHate;
    }

    public synchronized int getTotalHate() {
        int totalHate = 0;
        for (final int hate : this._hateMap.values()) {
            totalHate += hate;
        }
        return totalHate;
    }

    public synchronized int getTotalLawfulHate() {
        int totalHate = 0;
        for (final Map.Entry<L1Character, Integer> e : this._hateMap.entrySet()) {
            if (e.getKey() instanceof L1PcInstance) {
                totalHate += e.getValue();
            }
        }
        return totalHate;
    }

    public synchronized boolean isEmpty() {
        return this._hateMap.isEmpty();
    }

    public synchronized void remove(final L1Character cha) {
        this._hateMap.remove(cha);
    }

    public synchronized void removeInvalidCharacter(final L1NpcInstance npc) {
        final List<L1Character> invalidChars = Lists.newList();
        for (final L1Character cha : this._hateMap.keySet()) {
            if ((cha == null) || cha.isDead() || !npc.knownsObject(cha)) {
                invalidChars.add(cha);
            }
        }

        for (final L1Character cha : invalidChars) {
            this._hateMap.remove(cha);
        }
    }

    public synchronized ArrayList<Integer> toHateArrayList() {
        return new ArrayList<Integer>(this._hateMap.values());
    }

    public synchronized ArrayList<L1Character> toTargetArrayList() {
        return new ArrayList<L1Character>(this._hateMap.keySet());
    }
}
