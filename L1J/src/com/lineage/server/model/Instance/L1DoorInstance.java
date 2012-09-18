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
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.model.L1Attack;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Location;
import com.lineage.server.model.L1World;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_Door;
import com.lineage.server.serverpackets.S_DoorPack;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.templates.L1DoorGfx;
import com.lineage.server.templates.L1Npc;

/**
 * 门控制项
 */
public class L1DoorInstance extends L1NpcInstance {

    private static final long serialVersionUID = 1L;

    private static final int DOOR_NPC_ID = 81158;

    private int _doorId = 0;

    private int _direction = 0; // ドアの向き

    private int _leftEdgeLocation = 0; // ドアの左端の座標(ドアの向きからX軸orY軸を決定する)

    private int _rightEdgeLocation = 0; // ドアの右端の座標(ドアの向きからX軸orY軸を決定する)

    private int _openStatus = ActionCodes.ACTION_Close;

    private int _keeperId = 0;

    public L1DoorInstance(final int doorId, final L1DoorGfx gfx,
            final L1Location loc, final int hp, final int keeper,
            final boolean isOpening) {
        super(NpcTable.getInstance().getTemplate(DOOR_NPC_ID));
        this.setDoorId(doorId);
        this.setMaxHp(hp);
        this.setCurrentHp(hp);
        this.setGfxId(gfx.getGfxId());
        this.setLocation(loc);
        this.setHomeX(loc.getX());
        this.setHomeY(loc.getY());
        this.setDirection(gfx.getDirection());
        final int baseLoc = gfx.getDirection() == 0 ? loc.getX() : loc.getY();
        this.setLeftEdgeLocation(baseLoc + gfx.getLeftEdgeOffset());
        this.setRightEdgeLocation(baseLoc + gfx.getRightEdgeOffset());
        this.setKeeperId(keeper);
        if (isOpening) {
            this.open();
        }
    }

    public L1DoorInstance(final L1Npc template) {
        super(template);
    }

    public void close() {
        if (this.isDead() || (this.getOpenStatus() == ActionCodes.ACTION_Close)) {
            return;
        }

        this.setOpenStatus(ActionCodes.ACTION_Close);
        this.broadcastPacket(new S_DoorPack(this));
        this.broadcastPacket(new S_DoActionGFX(this.getId(),
                ActionCodes.ACTION_Close));
        this.sendDoorPacket(null);
    }

    @Override
    public void deleteMe() {
        this.setDead(true);
        this.sendDoorPacket(null);

        this._destroyed = true;
        if (this.getInventory() != null) {
            this.getInventory().clearItems();
        }
        this.allTargetClear();
        this._master = null;
        L1World.getInstance().removeVisibleObject(this);
        L1World.getInstance().removeObject(this);
        for (final L1PcInstance pc : L1World.getInstance().getRecognizePlayer(
                this)) {
            pc.removeKnownObject(this);
            pc.sendPackets(new S_RemoveObject(this));
        }
        this.removeAllKnownObjects();
    }

    private void die() {
        this.setCurrentHpDirect(0);
        this.setDead(true);
        this.setStatus(ActionCodes.ACTION_DoorDie);

        this.getMap().setPassable(this.getLocation(), true);

        this.broadcastPacket(new S_DoActionGFX(this.getId(),
                ActionCodes.ACTION_DoorDie));
        this.sendDoorPacket(null);
    }

    public int getDirection() {
        return this._direction;
    }

    public int getDoorId() {
        return this._doorId;
    }

    public int getEntranceX() {
        int entranceX = 0;
        if (this.getDirection() == 0) { // ／向き
            entranceX = this.getX();
        } else { // ＼向き
            entranceX = this.getX() - 1;
        }
        return entranceX;
    }

    public int getEntranceY() {
        int entranceY = 0;
        if (this.getDirection() == 0) { // ／向き
            entranceY = this.getY() + 1;
        } else { // ＼向き
            entranceY = this.getY();
        }
        return entranceY;
    }

    public int getKeeperId() {
        return this._keeperId;
    }

    public int getLeftEdgeLocation() {
        return this._leftEdgeLocation;
    }

    public int getOpenStatus() {
        return this._openStatus;
    }

    public int getRightEdgeLocation() {
        return this._rightEdgeLocation;
    }

    private boolean isPassable() {
        return this.isDead()
                || (this.getOpenStatus() == ActionCodes.ACTION_Open);
    }

    @Override
    public void onAction(final L1PcInstance pc) {
        if (this.getMaxHp() == 0) { // 破壊不可能なドアは対象外
            return;
        }

        if ((this.getCurrentHp() > 0) && !this.isDead()) {
            final L1Attack attack = new L1Attack(pc, this);
            if (attack.calcHit()) {
                attack.calcDamage();
                attack.addPcPoisonAttack(pc, this);
                attack.addChaserAttack();
            }
            attack.action();
            attack.commit();
        }
    }

    @Override
    public void onPerceive(final L1PcInstance perceivedFrom) {
        perceivedFrom.addKnownObject(this);
        perceivedFrom.sendPackets(new S_DoorPack(this));
        this.sendDoorPacket(perceivedFrom);
    }

    public void open() {
        if (this.isDead() || (this.getOpenStatus() == ActionCodes.ACTION_Open)) {
            return;
        }

        this.setOpenStatus(ActionCodes.ACTION_Open);
        this.broadcastPacket(new S_DoorPack(this));
        this.broadcastPacket(new S_DoActionGFX(this.getId(),
                ActionCodes.ACTION_Open));
        this.sendDoorPacket(null);
    }

    @Override
    public void receiveDamage(final L1Character attacker, final int damage) {
        if (this.getMaxHp() == 0) { // 破壊不可能なドアは対象外
            return;
        }
        if ((this.getCurrentHp() <= 0) || this.isDead()) {
            return;
        }

        final int newHp = this.getCurrentHp() - damage;
        if ((newHp <= 0) && !this.isDead()) {
            this.die();
            return;
        }

        this.setCurrentHpDirect(newHp);
        this.updateStatus();
    }

    public void repairGate() {
        if (this.getMaxHp() <= 1) {
            return;
        }

        this.setDead(false);
        this.setCurrentHp(this.getMaxHp());
        this.setStatus(0);
        this.setOpenStatus(ActionCodes.ACTION_Open);
        this.close();
    }

    public void sendDoorPacket(final L1PcInstance pc) {
        final int entranceX = this.getEntranceX();
        final int entranceY = this.getEntranceY();
        final int leftEdgeLocation = this.getLeftEdgeLocation();
        final int rightEdgeLocation = this.getRightEdgeLocation();

        final int size = rightEdgeLocation - leftEdgeLocation;
        if (size == 0) { // 1マス分の幅のドア
            this.sendPacket(pc, entranceX, entranceY);
        } else { // 2マス分以上の幅があるドア
            if (this.getDirection() == 0) { // ／向き
                for (int x = leftEdgeLocation; x <= rightEdgeLocation; x++) {
                    this.sendPacket(pc, x, entranceY);
                }
            } else { // ＼向き
                for (int y = leftEdgeLocation; y <= rightEdgeLocation; y++) {
                    this.sendPacket(pc, entranceX, y);
                }
            }
        }
    }

    private void sendPacket(final L1PcInstance pc, final int x, final int y) {
        final S_Door packet = new S_Door(x, y, this.getDirection(),
                this.isPassable());
        if (pc != null) { // onPerceive()経由の場合
            // 開いている場合は通行不可パケット送信不要
            if (this.getOpenStatus() == ActionCodes.ACTION_Close) {
                pc.sendPackets(packet);
            }
        } else {
            this.broadcastPacket(packet);
        }
    }

    @Override
    public void setCurrentHp(final int i) {
        int currentHp = i;
        if (currentHp >= this.getMaxHp()) {
            currentHp = this.getMaxHp();
        }
        this.setCurrentHpDirect(currentHp);
    }

    public void setDirection(final int i) {
        if ((i != 0) && (i != 1)) {
            throw new IllegalArgumentException();
        }
        this._direction = i;
    }

    public void setDoorId(final int i) {
        this._doorId = i;
    }

    public void setKeeperId(final int i) {
        this._keeperId = i;
    }

    public void setLeftEdgeLocation(final int i) {
        this._leftEdgeLocation = i;
    }

    private void setOpenStatus(final int newStatus) {
        if ((newStatus != ActionCodes.ACTION_Open)
                && (newStatus != ActionCodes.ACTION_Close)) {
            throw new IllegalArgumentException();
        }
        this._openStatus = newStatus;
    }

    public void setRightEdgeLocation(final int i) {
        this._rightEdgeLocation = i;
    }

    private void updateStatus() {
        int newStatus = 0;
        if ((this.getMaxHp() * 1 / 6) > this.getCurrentHp()) {
            newStatus = ActionCodes.ACTION_DoorAction5;
        } else if ((this.getMaxHp() * 2 / 6) > this.getCurrentHp()) {
            newStatus = ActionCodes.ACTION_DoorAction4;
        } else if ((this.getMaxHp() * 3 / 6) > this.getCurrentHp()) {
            newStatus = ActionCodes.ACTION_DoorAction3;
        } else if ((this.getMaxHp() * 4 / 6) > this.getCurrentHp()) {
            newStatus = ActionCodes.ACTION_DoorAction2;
        } else if ((this.getMaxHp() * 5 / 6) > this.getCurrentHp()) {
            newStatus = ActionCodes.ACTION_DoorAction1;
        }
        if (this.getStatus() == newStatus) {
            return;
        }
        this.setStatus(newStatus);
        this.broadcastPacket(new S_DoActionGFX(this.getId(), newStatus));
    }
}
