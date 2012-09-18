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
package com.lineage.server.model.Instance;

import static com.lineage.server.model.skill.L1SkillId.GMSTATUS_SHOWTRAPS;

import java.util.List;

import com.lineage.server.model.L1Location;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.map.L1Map;
import com.lineage.server.model.trap.L1Trap;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.serverpackets.S_Trap;
import com.lineage.server.types.Point;
import com.lineage.server.utils.Random;
import com.lineage.server.utils.collections.Lists;

/**
 * 陷阱控制项
 */
public class L1TrapInstance extends L1Object {

    private static final long serialVersionUID = 1L;

    private final L1Trap _trap;

    private final Point _baseLoc = new Point();

    private final Point _rndPt = new Point();

    private final int _span;

    private boolean _isEnable = true;

    private final String _nameForView;

    private final List<L1PcInstance> _knownPlayers = Lists.newConcurrentList();

    public L1TrapInstance(final int id, final L1Location loc) {
        this.setId(id);
        this._trap = L1Trap.newNull();
        this.getLocation().set(loc);
        this._span = 0;
        this._nameForView = "trap base";
    }

    public L1TrapInstance(final int id, final L1Trap trap,
            final L1Location loc, final Point rndPt, final int span) {
        this.setId(id);
        this._trap = trap;
        this.getLocation().set(loc);
        this._baseLoc.set(loc);
        this._rndPt.set(rndPt);
        this._span = span;
        this._nameForView = "trap";

        this.resetLocation();
    }

    public void disableTrap() {
        this._isEnable = false;

        for (final L1PcInstance pc : this._knownPlayers) {
            pc.removeKnownObject(this);
            pc.sendPackets(new S_RemoveObject(this));
        }
        this._knownPlayers.clear();
    }

    public void enableTrap() {
        this._isEnable = true;
    }

    public int getSpan() {
        return this._span;
    }

    public boolean isEnable() {
        return this._isEnable;
    }

    public void onDetection(final L1PcInstance caster) {
        this._trap.onDetection(caster, this);
    }

    @Override
    public void onPerceive(final L1PcInstance perceivedFrom) {
        if (perceivedFrom.hasSkillEffect(GMSTATUS_SHOWTRAPS)) {
            perceivedFrom.addKnownObject(this);
            perceivedFrom.sendPackets(new S_Trap(this, this._nameForView));
            this._knownPlayers.add(perceivedFrom);
        }
    }

    public void onTrod(final L1PcInstance trodFrom) {
        this._trap.onTrod(trodFrom, this);
    }

    public void resetLocation() {
        if ((this._rndPt.getX() == 0) && (this._rndPt.getY() == 0)) {
            return;
        }

        for (int i = 0; i < 50; i++) {
            int rndX = Random.nextInt(this._rndPt.getX() + 1)
                    * (Random.nextInt(2) == 1 ? 1 : -1); // 1/2の確率でマイナスにする
            int rndY = Random.nextInt(this._rndPt.getY() + 1)
                    * (Random.nextInt(2) == 1 ? 1 : -1);

            rndX += this._baseLoc.getX();
            rndY += this._baseLoc.getY();

            final L1Map map = this.getLocation().getMap();
            if (map.isInMap(rndX, rndY) && map.isPassable(rndX, rndY)) {
                this.getLocation().set(rndX, rndY);
                break;
            }
        }
        // ループ内で位置が確定しない場合、前回と同じ位置になる。
    }
}
