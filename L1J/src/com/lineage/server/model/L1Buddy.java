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

import java.util.LinkedHashMap;
import java.util.Map;

import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 
 */
public class L1Buddy {

    private final int _charId;

    private final LinkedHashMap<Integer, String> _buddys = new LinkedHashMap<Integer, String>();

    public L1Buddy(final int charId) {
        this._charId = charId;
    }

    public boolean add(final int objId, final String name) {
        if (this._buddys.containsKey(objId)) {
            return false;
        }
        this._buddys.put(objId, name);
        return true;
    }

    public boolean containsId(final int objId) {
        return this._buddys.containsKey(objId);
    }

    public boolean containsName(final String name) {
        for (final String buddyName : this._buddys.values()) {
            if (name.equalsIgnoreCase(buddyName)) {
                return true;
            }
        }
        return false;
    }

    public String getBuddyListString() {
        String result = new String("");
        for (final String name : this._buddys.values()) {
            result += name + " ";
        }
        return result;
    }

    public int getCharId() {
        return this._charId;
    }

    public String getOnlineBuddyListString() {
        String result = new String("");
        for (final L1PcInstance pc : L1World.getInstance().getAllPlayers()) {
            if (this._buddys.containsKey(pc.getId())) {
                result += pc.getName() + " ";
            }
        }
        return result;
    }

    public boolean remove(final int objId) {
        final String result = this._buddys.remove(objId);
        return (result != null ? true : false);
    }

    public boolean remove(final String name) {
        int id = 0;
        for (final Map.Entry<Integer, String> buddy : this._buddys.entrySet()) {
            if (name.equalsIgnoreCase(buddy.getValue())) {
                id = buddy.getKey();
                break;
            }
        }
        if (id == 0) {
            return false;
        }
        this._buddys.remove(id);
        return true;
    }

    public int size() {
        return this._buddys.size();
    }
}
