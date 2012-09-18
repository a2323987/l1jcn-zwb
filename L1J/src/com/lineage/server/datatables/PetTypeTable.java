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
package com.lineage.server.datatables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.templates.L1PetType;
import com.lineage.server.utils.IntRange;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 宠物类型资料表
 */
public class PetTypeTable {

    private static Logger _log = Logger.getLogger(PetTypeTable.class.getName());

    private static PetTypeTable _instance;

    public static PetTypeTable getInstance() {
        return _instance;
    }

    public static void load() {
        _instance = new PetTypeTable();
    }

    private final Map<Integer, L1PetType> _types = Maps.newMap();

    private final Set<String> _defaultNames = new HashSet<String>();

    private PetTypeTable() {
        this.loadTypes();
    }

    public L1PetType get(final int baseNpcId) {
        return this._types.get(baseNpcId);
    }

    public boolean isNameDefault(final String name) {
        return this._defaultNames.contains(name.toLowerCase());
    }

    private void loadTypes() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM pettypes");

            rs = pstm.executeQuery();

            while (rs.next()) {
                final int baseNpcId = rs.getInt("BaseNpcId");
                final String name = rs.getString("Name");
                final int itemIdForTaming = rs.getInt("ItemIdForTaming");
                final int hpUpMin = rs.getInt("HpUpMin");
                final int hpUpMax = rs.getInt("HpUpMax");
                final int mpUpMin = rs.getInt("MpUpMin");
                final int mpUpMax = rs.getInt("MpUpMax");
                final int evolvItemId = rs.getInt("EvolvItemId");
                final int npcIdForEvolving = rs.getInt("NpcIdForEvolving");
                final int msgIds[] = new int[5];
                for (int i = 0; i < 5; i++) {
                    msgIds[i] = rs.getInt("MessageId" + (i + 1));
                }
                final int defyMsgId = rs.getInt("DefyMessageId");
                final boolean canUseEquipment = rs
                        .getBoolean("canUseEquipment");
                final IntRange hpUpRange = new IntRange(hpUpMin, hpUpMax);
                final IntRange mpUpRange = new IntRange(mpUpMin, mpUpMax);
                this._types.put(baseNpcId, new L1PetType(baseNpcId, name,
                        itemIdForTaming, hpUpRange, mpUpRange, evolvItemId,
                        npcIdForEvolving, msgIds, defyMsgId, canUseEquipment));
                this._defaultNames.add(name.toLowerCase());
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }
}
