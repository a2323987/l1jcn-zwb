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
package com.lineage.server.model.trap;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.IdFactory;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.model.L1Location;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.map.L1Map;
import com.lineage.server.storage.TrapStorage;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.types.Point;
import com.lineage.server.utils.collections.Lists;

/**
 * 怪物陷阱
 */
public class L1MonsterTrap extends L1Trap {

    private static Logger _log = Logger
            .getLogger(L1MonsterTrap.class.getName());

    private final int _npcId;

    private final int _count;

    private L1Npc _npcTemp = null; // 性能缓存

    private Constructor<?> _constructor = null; // 性能缓存

    public L1MonsterTrap(final TrapStorage storage) {
        super(storage);

        this._npcId = storage.getInt("monsterNpcId");
        this._count = storage.getInt("monsterCount");
    }

    private void addListIfPassable(final List<Point> list, final L1Map map,
            final Point pt) {
        if (map.isPassable(pt)) {
            list.add(pt);
        }
    }

    private L1NpcInstance createNpc() throws Exception {
        if (this._npcTemp == null) {
            this._npcTemp = NpcTable.getInstance().getTemplate(this._npcId);
        }
        if (this._constructor == null) {
            this._constructor = this.getConstructor(this._npcTemp);
        }

        return (L1NpcInstance) this._constructor
                .newInstance(new Object[] { this._npcTemp });
    }

    private Constructor<?> getConstructor(final L1Npc npc)
            throws ClassNotFoundException {
        return Class.forName(
                "com.lineage.server.model.Instance." + npc.getImpl()
                        + "Instance").getConstructors()[0];
    }

    private List<Point> getSpawnablePoints(final L1Location loc, final int d) {
        final List<Point> result = Lists.newList();
        final L1Map m = loc.getMap();
        final int x = loc.getX();
        final int y = loc.getY();
        // locを中心に、1辺dタイルの正方形を描くPointリストを作る
        for (int i = 0; i < d; i++) {
            this.addListIfPassable(result, m, new Point(d - i + x, i + y));
            this.addListIfPassable(result, m, new Point(-(d - i) + x, -i + y));
            this.addListIfPassable(result, m, new Point(-i + x, d - i + y));
            this.addListIfPassable(result, m, new Point(i + x, -(d - i) + y));
        }
        return result;
    }

    @Override
    public void onTrod(final L1PcInstance trodFrom, final L1Object trapObj) {
        this.sendEffect(trapObj);

        final List<Point> points = this.getSpawnablePoints(
                trapObj.getLocation(), 5);

        // 沸ける場所が無ければ終了
        if (points.isEmpty()) {
            return;
        }

        try {
            int cnt = 0;
            while (true) {
                for (final Point pt : points) {
                    this.spawn(new L1Location(pt, trapObj.getMap()));
                    cnt++;
                    if (this._count <= cnt) {
                        return;
                    }
                }
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    private void spawn(final L1Location loc) throws Exception {
        final L1NpcInstance npc = this.createNpc();
        npc.setId(IdFactory.getInstance().nextId());
        npc.getLocation().set(loc);
        npc.setHomeX(loc.getX());
        npc.setHomeY(loc.getY());
        L1World.getInstance().storeObject(npc);
        L1World.getInstance().addVisibleObject(npc);

        npc.onNpcAI();
        npc.turnOnOffLight();
        npc.startChat(L1NpcInstance.CHAT_TIMING_APPEARANCE); // 开始喊话
    }
}
