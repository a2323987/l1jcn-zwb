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

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.npc.NpcExecutor;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * NPC资料表
 */
public class NpcTable {

    static Logger _log = Logger.getLogger(NpcTable.class.getName());

    /**
     * 建立NPC家族清单
     * 
     * @return
     */
    public static Map<String, Integer> buildFamily() {
        final Map<String, Integer> result = Maps.newMap();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("select distinct(family) as family from npc WHERE NOT trim(family) =''");
            rs = pstm.executeQuery();
            int id = 1;
            while (rs.next()) {
                final String family = rs.getString("family");
                result.put(family, id++);
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
        return result;
    }

    private final boolean _initialized;

    private static NpcTable _instance;

    private final Map<Integer, L1Npc> _npcs = Maps.newMap();

    private final Map<String, Constructor<?>> _constructorCache = Maps.newMap();

    private static final Map<String, Integer> _familyTypes = NpcTable
            .buildFamily();

    public static NpcTable getInstance() {
        if (_instance == null) {
            _instance = new NpcTable();
        }
        return _instance;
    }

    private NpcTable() {
        this.loadNpcData();
        this._initialized = true;
    }

    /**
     * 加入独立执行CLASS位置
     * 
     * @param npcid
     * @param className
     * @return
     */
    private NpcExecutor addClass(final int npcid, final String className) {
        try {
            if (!className.equals("0")) {
                final StringBuilder stringBuilder = new StringBuilder();
                stringBuilder
                        .append("com.lineage.server.model.npc.actiontalk.");
                stringBuilder.append(className);

                final Class<?> cls = Class.forName(stringBuilder.toString());
                final NpcExecutor exe = (NpcExecutor) cls.getMethod("get")
                        .invoke(null);
                return exe;
            }
        } catch (final Exception e) {
            _log.severe("不正确的NPC档案 className = " + className + "  NpcId:"
                    + npcid);
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
        return null;
    }

    /**
     * 依照NPC名称传回NPCID
     * 
     * @param name
     *            依照NPC名称
     * @return
     */
    public int findNpcIdByName(final String name) {
        for (final L1Npc npc : this._npcs.values()) {
            if (npc.get_name().equals(name)) {
                return npc.get_npcId();
            }
        }
        return 0;
    }

    /**
     * 依照NPC名称传回NPCID
     * 
     * @param name
     *            依照NPC名称
     * @return
     */
    public int findNpcIdByNameWithoutSpace(final String name) {
        for (final L1Npc npc : this._npcs.values()) {
            if (npc.get_name().replace(" ", "").equals(name)) {
                return npc.get_npcId();
            }
        }
        return 0;
    }

    /**
     * 取得执行类位置
     * 
     * @param implName
     * @return
     */
    private Constructor<?> getConstructor(final String implName) {
        try {
            final String implFullName = "com.lineage.server.model.Instance."
                    + implName + "Instance";
            final Constructor<?> con = Class.forName(implFullName)
                    .getConstructors()[0];
            return con;
        } catch (final ClassNotFoundException e) {
            _log.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
        return null;
    }

    /**
     * 取得该编号NPC资料
     * 
     * @param id
     * @return
     */
    public L1Npc getTemplate(final int id) {
        return this._npcs.get(id);
    }

    public boolean isInitialized() {
        return this._initialized;
    }

    private void loadNpcData() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM npc");
            rs = pstm.executeQuery();
            while (rs.next()) {
                final L1Npc npc = new L1Npc();
                final int npcId = rs.getInt("npcid");
                npc.set_npcId(npcId);
                npc.set_name(rs.getString("name"));
                final String class_name = rs.getString("class_name");
                npc.set_classname(class_name);
                npc.setNpcExecutor(this.addClass(npcId, class_name));
                npc.set_nameid(rs.getString("nameid"));
                npc.setImpl(rs.getString("impl"));
                npc.set_gfxid(rs.getInt("gfxid"));
                npc.set_level(rs.getInt("lvl"));
                npc.set_hp(rs.getInt("hp"));
                npc.set_mp(rs.getInt("mp"));
                npc.set_ac(rs.getInt("ac"));
                npc.set_str(rs.getByte("str"));
                npc.set_con(rs.getByte("con"));
                npc.set_dex(rs.getByte("dex"));
                npc.set_wis(rs.getByte("wis"));
                npc.set_int(rs.getByte("intel"));
                npc.set_mr(rs.getInt("mr"));
                npc.set_exp(rs.getInt("exp"));
                npc.set_lawful(rs.getInt("lawful"));
                npc.set_size(rs.getString("size"));
                npc.set_weakAttr(rs.getInt("weakAttr"));
                npc.set_ranged(rs.getInt("ranged"));
                npc.setTamable(rs.getBoolean("tamable"));
                npc.set_passispeed(rs.getInt("passispeed"));
                npc.set_atkspeed(rs.getInt("atkspeed"));
                npc.setAltAtkSpeed(rs.getInt("alt_atk_speed"));
                npc.setAtkMagicSpeed(rs.getInt("atk_magic_speed"));
                npc.setSubMagicSpeed(rs.getInt("sub_magic_speed"));
                npc.set_undead(rs.getInt("undead"));
                npc.set_poisonatk(rs.getInt("poison_atk"));
                npc.set_paralysisatk(rs.getInt("paralysis_atk"));
                npc.set_agro(rs.getBoolean("agro"));
                npc.set_agrososc(rs.getBoolean("agrososc"));
                npc.set_agrocoi(rs.getBoolean("agrocoi"));
                final Integer family = _familyTypes.get(rs.getString("family"));
                if (family == null) {
                    npc.set_family(0);
                } else {
                    npc.set_family(family.intValue());
                }
                final int agrofamily = rs.getInt("agrofamily");
                if ((npc.get_family() == 0) && (agrofamily == 1)) {
                    npc.set_agrofamily(0);
                } else {
                    npc.set_agrofamily(agrofamily);
                }
                npc.set_agrogfxid1(rs.getInt("agrogfxid1"));
                npc.set_agrogfxid2(rs.getInt("agrogfxid2"));
                npc.set_picupitem(rs.getBoolean("picupitem"));
                npc.set_digestitem(rs.getInt("digestitem"));
                npc.set_bravespeed(rs.getBoolean("bravespeed"));
                npc.set_hprinterval(rs.getInt("hprinterval"));
                npc.set_hpr(rs.getInt("hpr"));
                npc.set_mprinterval(rs.getInt("mprinterval"));
                npc.set_mpr(rs.getInt("mpr"));
                npc.set_teleport(rs.getBoolean("teleport"));
                npc.set_randomlevel(rs.getInt("randomlevel"));
                npc.set_randomhp(rs.getInt("randomhp"));
                npc.set_randommp(rs.getInt("randommp"));
                npc.set_randomac(rs.getInt("randomac"));
                npc.set_randomexp(rs.getInt("randomexp"));
                npc.set_randomlawful(rs.getInt("randomlawful"));
                npc.set_damagereduction(rs.getInt("damage_reduction"));
                npc.set_hard(rs.getBoolean("hard"));
                npc.set_doppel(rs.getBoolean("doppel"));
                npc.set_IsTU(rs.getBoolean("IsTU"));
                npc.set_IsErase(rs.getBoolean("IsErase"));
                npc.setBowActId(rs.getInt("bowActId"));
                npc.setKarma(rs.getInt("karma"));
                npc.setTransformId(rs.getInt("transform_id"));
                npc.setTransformGfxId(rs.getInt("transform_gfxid"));
                npc.setLightSize(rs.getInt("light_size"));
                npc.setAmountFixed(rs.getBoolean("amount_fixed"));
                npc.setChangeHead(rs.getBoolean("change_head"));
                npc.setCantResurrect(rs.getBoolean("cant_resurrect"));

                this.registerConstructorCache(npc.getImpl());
                this._npcs.put(npcId, npc);
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    /**
     * 依照NPCID取回新的L1NpcInstance资料
     * 
     * @param id
     *            NPCID
     * @return
     */
    public L1NpcInstance newNpcInstance(final int id) {
        final L1Npc npcTemp = this.getTemplate(id);
        if (npcTemp == null) {
            throw new IllegalArgumentException(String.format(
                    "NpcTemplate: %d not found", id));
        }
        return this.newNpcInstance(npcTemp);
    }

    /**
     * 依照NPC资料 取回新的L1NpcInstance资料
     * 
     * @param template
     *            NPC资料
     * @return
     */
    public L1NpcInstance newNpcInstance(final L1Npc template) {
        try {
            final Constructor<?> con = this._constructorCache.get(template
                    .getImpl());
            return (L1NpcInstance) con.newInstance(new Object[] { template });
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
        return null;
    }

    /**
     * 加载NPC执行类位置
     * 
     * @param implName
     */
    private void registerConstructorCache(final String implName) {
        if (implName.isEmpty() || this._constructorCache.containsKey(implName)) {
            return;
        }
        this._constructorCache.put(implName, this.getConstructor(implName));
    }

}
