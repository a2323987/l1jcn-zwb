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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.templates.L1PetItem;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * 宠物道具资料表
 */
public class PetItemTable {

    private static Logger _log = Logger.getLogger(PetItemTable.class.getName());

    private static PetItemTable _instance;

    private final Map<Integer, L1PetItem> _petItemIdIndex = Maps.newMap();

    private static final Map<String, Integer> _useTypes = Maps.newMap();

    static {
        _useTypes.put("armor", new Integer(0));
        _useTypes.put("tooth", new Integer(1));
    }

    public static PetItemTable getInstance() {
        if (_instance == null) {
            _instance = new PetItemTable();
        }
        return _instance;
    }

    private PetItemTable() {
        this.loadPetItem();
    }

    private void fillPetItemTable(final ResultSet rs) throws SQLException {
        while (rs.next()) {
            final L1PetItem petItem = new L1PetItem();
            petItem.setItemId(rs.getInt("item_id"));
            petItem.setUseType((_useTypes.get(rs.getString("use_type")))
                    .intValue());
            petItem.setHitModifier(rs.getInt("hitmodifier"));
            petItem.setDamageModifier(rs.getInt("dmgmodifier"));
            petItem.setAddAc(rs.getInt("ac"));
            petItem.setAddStr(rs.getInt("add_str"));
            petItem.setAddCon(rs.getInt("add_con"));
            petItem.setAddDex(rs.getInt("add_dex"));
            petItem.setAddInt(rs.getInt("add_int"));
            petItem.setAddWis(rs.getInt("add_wis"));
            petItem.setAddHp(rs.getInt("add_hp"));
            petItem.setAddMp(rs.getInt("add_mp"));
            petItem.setAddSp(rs.getInt("add_sp"));
            petItem.setAddMr(rs.getInt("m_def"));
            this._petItemIdIndex.put(petItem.getItemId(), petItem);
        }
    }

    public L1PetItem getTemplate(final int itemId) {
        return this._petItemIdIndex.get(itemId);
    }

    private void loadPetItem() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM petitem");
            rs = pstm.executeQuery();
            this.fillPetItemTable(rs);
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, "创建etcitem_petitem表时出现错误", e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

}
