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
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.IdFactory;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1FieldObjectInstance;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.utils.SQLUtil;

/**
 * 产生照明资料表
 */
public class LightSpawnTable {

    private static Logger _log = Logger.getLogger(LightSpawnTable.class
            .getName());

    private static LightSpawnTable _instance;

    public static LightSpawnTable getInstance() {
        if (_instance == null) {
            _instance = new LightSpawnTable();
        }
        return _instance;
    }

    private LightSpawnTable() {
        this.FillLightSpawnTable();
    }

    /**
     * 填入产生照明表
     */
    private void FillLightSpawnTable() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM spawnlist_light");
            rs = pstm.executeQuery();
            do {
                if (!rs.next()) {
                    break;
                }

                final L1Npc l1npc = NpcTable.getInstance().getTemplate(
                        rs.getInt(2));
                if (l1npc != null) {
                    final String s = l1npc.getImpl();
                    final Constructor<?> constructor = Class.forName(
                            "com.lineage.server.model.Instance." + s
                                    + "Instance").getConstructors()[0];
                    final Object parameters[] = { l1npc };
                    L1FieldObjectInstance field = (L1FieldObjectInstance) constructor
                            .newInstance(parameters);
                    field = (L1FieldObjectInstance) constructor
                            .newInstance(parameters);
                    field.setId(IdFactory.getInstance().nextId());
                    field.setX(rs.getInt("locx"));
                    field.setY(rs.getInt("locy"));
                    field.setMap((short) rs.getInt("mapid"));
                    field.setHomeX(field.getX());
                    field.setHomeY(field.getY());
                    field.setHeading(0);
                    field.setLightSize(l1npc.getLightSize());

                    L1World.getInstance().storeObject(field);
                    L1World.getInstance().addVisibleObject(field);
                }
            } while (true);
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } catch (final SecurityException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } catch (final ClassNotFoundException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } catch (final IllegalArgumentException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } catch (final InstantiationException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } catch (final IllegalAccessException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } catch (final InvocationTargetException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

}
