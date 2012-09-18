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

import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_EffectLocation;
import com.lineage.server.storage.TrapStorage;

class L1NullTrap extends L1Trap {
    public L1NullTrap() {
        super(0, 0, false);
    }

    @Override
    public void onTrod(final L1PcInstance trodFrom, final L1Object trapObj) {
    }
}

/**
 * 陷阱
 */
public abstract class L1Trap {

    public static L1Trap newNull() {
        return new L1NullTrap();
    }

    protected final int _id;
    protected final int _gfxId;

    protected final boolean _isDetectionable;

    public L1Trap(final int id, final int gfxId, final boolean detectionable) {
        this._id = id;
        this._gfxId = gfxId;
        this._isDetectionable = detectionable;
    }

    public L1Trap(final TrapStorage storage) {
        this._id = storage.getInt("id");
        this._gfxId = storage.getInt("gfxId");
        this._isDetectionable = storage.getBoolean("isDetectionable");
    }

    public int getGfxId() {
        return this._gfxId;
    }

    public int getId() {
        return this._id;
    }

    public void onDetection(final L1PcInstance caster, final L1Object trapObj) {
        if (this._isDetectionable) {
            this.sendEffect(trapObj);
        }
    }

    public abstract void onTrod(L1PcInstance trodFrom, L1Object trapObj);

    protected void sendEffect(final L1Object trapObj) {
        if (this.getGfxId() == 0) {
            return;
        }
        final S_EffectLocation effect = new S_EffectLocation(
                trapObj.getLocation(), this.getGfxId());

        for (final L1PcInstance pc : L1World.getInstance().getRecognizePlayer(
                trapObj)) {
            pc.sendPackets(effect);
        }
    }
}
