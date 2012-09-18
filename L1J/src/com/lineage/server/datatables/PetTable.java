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
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.templates.L1Pet;
import com.lineage.server.templates.L1PetType;
import com.lineage.server.utils.Random;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

// Referenced classes of package com.lineage.server:
// IdFactory

/**
 * 宠物表
 */
public class PetTable {

    private static Logger _log = Logger.getLogger(PetTable.class.getName());

    private static PetTable _instance;

    public static PetTable getInstance() {
        if (_instance == null) {
            _instance = new PetTable();
        }
        return _instance;
    }

    /**
     * 返回已经在表中存在的宠物名称。
     * 
     * @param nameCaseInsensitive
     *            检查您的宠物的名字。大文字小文字の差異は無視される。
     * @return 如果名称已经存在true
     */
    public static boolean isNameExists(final String nameCaseInsensitive) {
        final String nameLower = nameCaseInsensitive.toLowerCase();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            /*
             * 同じ名前を探す。MySQLはデフォルトでcase insensitiveなため
             * 本来LOWERは必要ないが、binaryに変更された場合に備えて。
             */
            pstm = con
                    .prepareStatement("SELECT item_obj_id FROM pets WHERE LOWER(name)=?");
            pstm.setString(1, nameLower);
            rs = pstm.executeQuery();
            if (!rs.next()) { // 不一样的名字
                return false;
            }
            if (PetTypeTable.getInstance().isNameDefault(nameLower)) { // デフォルトの名前なら重複していないとみなす
                return false;
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
        return true;
    }

    private final Map<Integer, L1Pet> _pets = Maps.newMap();

    private PetTable() {
        this.load();
    }

    /**
     * 经过 C_NPCAction 获得 pet
     */
    public void buyNewPet(final int petNpcId, final int objid,
            final int itemobjid, final int upLv, final long lvExp) {
        final L1PetType petType = PetTypeTable.getInstance().get(petNpcId);
        final L1Pet l1pet = new L1Pet();
        l1pet.set_itemobjid(itemobjid);
        l1pet.set_objid(objid);
        l1pet.set_npcid(petNpcId);
        l1pet.set_name(petType.getName());
        l1pet.set_level(upLv);
        final int hpUpMin = petType.getHpUpRange().getLow();
        final int hpUpMax = petType.getHpUpRange().getHigh();
        final int mpUpMin = petType.getMpUpRange().getLow();
        final int mpUpMax = petType.getMpUpRange().getHigh();
        short randomhp = (short) ((hpUpMin + hpUpMax) / 2);
        short randommp = (short) ((mpUpMin + mpUpMax) / 2);
        for (int i = 1; i < upLv; i++) {
            randomhp += (Random.nextInt(hpUpMax - hpUpMin) + hpUpMin + 1);
            randommp += (Random.nextInt(mpUpMax - mpUpMin) + mpUpMin + 1);
        }
        l1pet.set_hp(randomhp);
        l1pet.set_mp(randommp);
        l1pet.set_exp((int) lvExp); // upLv EXP
        l1pet.set_lawful(0);
        l1pet.set_food(50);
        this._pets.put(new Integer(itemobjid), l1pet);

        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("INSERT INTO pets SET item_obj_id=?,objid=?,npcid=?,name=?,lvl=?,hp=?,mp=?,exp=?,lawful=?,food=?");
            pstm.setInt(1, l1pet.get_itemobjid());
            pstm.setInt(2, l1pet.get_objid());
            pstm.setInt(3, l1pet.get_npcid());
            pstm.setString(4, l1pet.get_name());
            pstm.setInt(5, l1pet.get_level());
            pstm.setInt(6, l1pet.get_hp());
            pstm.setInt(7, l1pet.get_mp());
            pstm.setInt(8, l1pet.get_exp());
            pstm.setInt(9, l1pet.get_lawful());
            pstm.setInt(10, l1pet.get_food());
            pstm.execute();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);

        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);

        }
    }

    public void deletePet(final int itemobjid) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("DELETE FROM pets WHERE item_obj_id=?");
            pstm.setInt(1, itemobjid);
            pstm.execute();
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);

        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
        this._pets.remove(itemobjid);
    }

    public L1Pet[] getPetTableList() {
        return this._pets.values().toArray(new L1Pet[this._pets.size()]);
    }

    public L1Pet getTemplate(final int itemobjid) {
        return this._pets.get(new Integer(itemobjid));
    }

    private void load() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM pets");

            rs = pstm.executeQuery();
            while (rs.next()) {
                final L1Pet pet = new L1Pet();
                final int itemobjid = rs.getInt(1);
                pet.set_itemobjid(itemobjid);
                pet.set_objid(rs.getInt(2));
                pet.set_npcid(rs.getInt(3));
                pet.set_name(rs.getString(4));
                pet.set_level(rs.getInt(5));
                pet.set_hp(rs.getInt(6));
                pet.set_mp(rs.getInt(7));
                pet.set_exp(rs.getInt(8));
                pet.set_lawful(rs.getInt(9));
                pet.set_food(rs.getInt(10));

                this._pets.put(new Integer(itemobjid), pet);
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);

        }
    }

    public void storeNewPet(final L1NpcInstance pet, final int objid,
            final int itemobjid) {
        // XXX 呼ばれる前と処理の重複
        final L1Pet l1pet = new L1Pet();
        l1pet.set_itemobjid(itemobjid);
        l1pet.set_objid(objid);
        l1pet.set_npcid(pet.getNpcTemplate().get_npcId());
        l1pet.set_name(pet.getNpcTemplate().get_name());
        l1pet.set_level(pet.getNpcTemplate().get_level());
        l1pet.set_hp(pet.getMaxHp());
        l1pet.set_mp(pet.getMaxMp());
        l1pet.set_exp(750); // Lv.5のEXP
        l1pet.set_lawful(0);
        l1pet.set_food(50);
        this._pets.put(new Integer(itemobjid), l1pet);

        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("INSERT INTO pets SET item_obj_id=?,objid=?,npcid=?,name=?,lvl=?,hp=?,mp=?,exp=?,lawful=?,food=?");
            pstm.setInt(1, l1pet.get_itemobjid());
            pstm.setInt(2, l1pet.get_objid());
            pstm.setInt(3, l1pet.get_npcid());
            pstm.setString(4, l1pet.get_name());
            pstm.setInt(5, l1pet.get_level());
            pstm.setInt(6, l1pet.get_hp());
            pstm.setInt(7, l1pet.get_mp());
            pstm.setInt(8, l1pet.get_exp());
            pstm.setInt(9, l1pet.get_lawful());
            pstm.setInt(10, l1pet.get_food());
            pstm.execute();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);

        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);

        }
    }

    public void storePet(final L1Pet pet) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("UPDATE pets SET objid=?,npcid=?,name=?,lvl=?,hp=?,mp=?,exp=?,lawful=?,food=? WHERE item_obj_id=?");
            pstm.setInt(1, pet.get_objid());
            pstm.setInt(2, pet.get_npcid());
            pstm.setString(3, pet.get_name());
            pstm.setInt(4, pet.get_level());
            pstm.setInt(5, pet.get_hp());
            pstm.setInt(6, pet.get_mp());
            pstm.setInt(7, pet.get_exp());
            pstm.setInt(8, pet.get_lawful());
            pstm.setInt(9, pet.get_food());
            pstm.setInt(10, pet.get_itemobjid());
            pstm.execute();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    /** 更新宠物饱食度 */
    public void storePetFood(final L1Pet pet) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("UPDATE pets SET food=? WHERE item_obj_id=?");
            pstm.setInt(1, pet.get_food());
            pstm.setInt(2, pet.get_itemobjid());
            pstm.execute();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }
}
