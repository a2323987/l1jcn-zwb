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

import com.lineage.server.model.L1World;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.templates.L1Npc;

/**
 * 家具控制项
 */
public class L1FurnitureInstance extends L1NpcInstance {

    private static final long serialVersionUID = 1L;

    private int _itemObjId;

    public L1FurnitureInstance(final L1Npc template) {
        super(template);
    }

    @Override
    public void deleteMe() {
        this._destroyed = true;
        if (this.getInventory() != null) {
            this.getInventory().clearItems();
        }
        L1World.getInstance().removeVisibleObject(this);
        L1World.getInstance().removeObject(this);
        for (final L1PcInstance pc : L1World.getInstance().getRecognizePlayer(
                this)) {
            pc.removeKnownObject(this);
            pc.sendPackets(new S_RemoveObject(this));
        }
        this.removeAllKnownObjects();
    }

    public int getItemObjId() {
        return this._itemObjId;
    }

    @Override
    public void onAction(final L1PcInstance player) {
    }

    public void setItemObjId(final int i) {
        this._itemObjId = i;
    }

}
