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
package com.lineage.server.serverpackets;

import com.lineage.server.Opcodes;
import com.lineage.server.model.L1Location;
import com.lineage.server.types.Point;

/**
 * 产生动画 (坐标)
 */
public class S_EffectLocation extends ServerBasePacket {

    private byte[] _byte = null;

    /**
     * 在指定位置显示动画效果。
     * 
     * @param x
     *            - 动画效果X坐标
     * @param y
     *            - 动画效果Y坐标
     * @param gfxId
     *            - 动画ID
     */
    public S_EffectLocation(final int x, final int y, final int gfxId) {
        this.writeC(Opcodes.S_OPCODE_EFFECTLOCATION);
        this.writeH(x);
        this.writeH(y);
        this.writeH(gfxId);
        this.writeC(0);
    }

    /**
     * 在指定位置显示动画效果。
     * 
     * @param loc
     *            - 动画效果坐标 包含L1Location对象
     * @param gfxId
     *            - 动画ID
     */
    public S_EffectLocation(final L1Location loc, final int gfxId) {
        this(loc.getX(), loc.getY(), gfxId);
    }

    /**
     * 在指定位置显示动画效果。
     * 
     * @param pt
     *            - 动画效果坐标 包含Point对象
     * @param gfxId
     *            - 动画ID
     */
    public S_EffectLocation(final Point pt, final int gfxId) {
        this(pt.getX(), pt.getY(), gfxId);
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this.getBytes();
        }

        return this._byte;
    }
}
