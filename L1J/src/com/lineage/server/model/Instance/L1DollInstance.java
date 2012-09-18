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

import com.lineage.server.ActionCodes;
import com.lineage.server.GeneralThreadPool;
import com.lineage.server.IdFactory;
import com.lineage.server.datatables.SprTable;
import com.lineage.server.model.L1World;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_DollPack;
import com.lineage.server.serverpackets.S_OwnCharStatus;
import com.lineage.server.serverpackets.S_SkillIconGFX;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.templates.L1MagicDoll;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.utils.Random;

/**
 * 魔法娃娃控制项
 */
public class L1DollInstance extends L1NpcInstance {

    // 时间计测用
    class DollTimer implements Runnable {
        @Override
        public void run() {
            if (L1DollInstance.this._destroyed) { // 检查是否已经被抛弃
                return;
            }
            L1DollInstance.this.deleteDoll();
        }
    }

    private static final long serialVersionUID = 1L;
    public static final int DOLL_TIME = 1800000;
    private int _itemId;
    private int _itemObjId;
    private int run;

    private boolean _isDelete = false;

    public L1DollInstance(final L1Npc template, final L1PcInstance master,
            final int itemId, final int itemObjId) {

        super(template);
        this.setId(IdFactory.getInstance().nextId());

        this.setItemId(itemId);
        this.setItemObjId(itemObjId);
        GeneralThreadPool.getInstance().schedule(new DollTimer(), DOLL_TIME);

        this.setMaster(master);
        this.setX(master.getX() + Random.nextInt(5) - 2);
        this.setY(master.getY() + Random.nextInt(5) - 2);
        this.setMap(master.getMapId());
        this.setHeading(5);
        this.setLightSize(template.getLightSize());
        this.setMoveSpeed(1);
        this.setBraveSpeed(1);

        L1World.getInstance().storeObject(this);
        L1World.getInstance().addVisibleObject(this);
        for (final L1PcInstance pc : L1World.getInstance().getRecognizePlayer(
                this)) {
            this.onPerceive(pc);
        }
        master.addDoll(this);
        if (!this.isAiRunning()) {
            this.startAI();
        }
        if (L1MagicDoll.isHpRegeneration(this._master)) {
            master.startHpRegenerationByDoll();
        }
        if (L1MagicDoll.isMpRegeneration(this._master)) {
            master.startMpRegenerationByDoll();
        }
        if (L1MagicDoll.isItemMake(this._master)) {
            master.startItemMakeByDoll();
        }
    }

    public void deleteDoll() {
        this.broadcastPacket(new S_SkillSound(this.getId(), 5936));
        if ((this._master != null) && this._isDelete) {
            final L1PcInstance pc = (L1PcInstance) this._master;
            pc.sendPackets(new S_SkillIconGFX(56, 0));
            pc.sendPackets(new S_OwnCharStatus(pc));
        }
        if (L1MagicDoll.isHpRegeneration(this._master)) {
            ((L1PcInstance) this._master).stopHpRegenerationByDoll();
        }
        if (L1MagicDoll.isMpRegeneration(this._master)) {
            ((L1PcInstance) this._master).stopMpRegenerationByDoll();
        }
        if (L1MagicDoll.isItemMake(this._master)) {
            ((L1PcInstance) this._master).stopItemMakeByDoll();
        }
        this._master.getDollList().remove(this.getId());
        this.deleteMe();
    }

    // 表情动作
    private void dollAction() {
        this.run = Random.nextInt(100) + 1;
        if (this.run <= 10) {
            int actionCode = ActionCodes.ACTION_Aggress; // 67
            if (this.run <= 5) {
                actionCode = ActionCodes.ACTION_Think; // 66
            }

            this.broadcastPacket(new S_DoActionGFX(this.getId(), actionCode));
            this.setSleepTime(this.calcSleepTime(SprTable.getInstance()
                    .getSprSpeed(this.getTempCharGfx(), actionCode), MOVE_SPEED)); //
        }
    }

    public int getItemId() {
        return this._itemId;
    }

    public int getItemObjId() {
        return this._itemObjId;
    }

    // 如果没有目标处理
    @Override
    public boolean noTarget() {
        if ((this._master != null) && !this._master.isDead()
                && (this._master.getMapId() == this.getMapId())) {
            if (this.getLocation().getTileLineDistance(
                    this._master.getLocation()) > 2) {
                final int dir = this.moveDirection(this._master.getX(),
                        this._master.getY());
                this.setDirectionMove(dir);
                this.setSleepTime(this.calcSleepTime(this.getPassispeed(),
                        MOVE_SPEED));
            } else {
                // 魔法娃娃 - 特殊动作
                this.dollAction();
            }
        } else {
            this._isDelete = true;
            this.deleteDoll();
            return true;
        }
        return false;
    }

    @Override
    public void onGetItem(final L1ItemInstance item) {
    }

    @Override
    public void onItemUse() {
    }

    @Override
    public void onPerceive(final L1PcInstance perceivedFrom) {
        // 判断旅馆内是否使用相同钥匙
        if ((perceivedFrom.getMapId() >= 16384)
                && (perceivedFrom.getMapId() <= 25088 // 旅馆内判断
                )
                && (perceivedFrom.getInnKeyId() != this._master.getInnKeyId())) {
            return;
        }
        perceivedFrom.addKnownObject(this);
        perceivedFrom.sendPackets(new S_DollPack(this));
    }

    public void setItemId(final int i) {
        this._itemId = i;
    }

    public void setItemObjId(final int i) {
        this._itemObjId = i;
    }
}
