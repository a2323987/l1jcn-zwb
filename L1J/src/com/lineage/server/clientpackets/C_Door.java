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
package com.lineage.server.clientpackets;

import java.util.Timer;
import java.util.TimerTask;

import com.lineage.server.ActionCodes;
import com.lineage.server.ClientThread;
import com.lineage.server.datatables.HouseTable;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1DoorInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.templates.L1House;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket, C_Door

/**
 * 处理收到由客户端传来开关门的封包
 */
public class C_Door extends ClientBasePacket {

    public class CloseTimer extends TimerTask {

        private final L1DoorInstance _door;

        public CloseTimer(final L1DoorInstance door) {
            this._door = door;
        }

        public void begin() {
            final Timer timer = new Timer();
            timer.schedule(this, 5 * 1000);
        }

        @Override
        public void run() {
            if (this._door.getOpenStatus() == ActionCodes.ACTION_Open) {
                this._door.close();
            }
        }
    }

    private static final String C_DOOR = "[C] C_Door";

    public C_Door(final byte abyte0[], final ClientThread client)
            throws Exception {
        super(abyte0);
        this.readH();
        this.readH();
        final int objectId = this.readD();

        final L1PcInstance pc = client.getActiveChar();
        final L1DoorInstance door = (L1DoorInstance) L1World.getInstance()
                .findObject(objectId);
        if (door == null) {
            return;
        }

        if (((door.getDoorId() >= 5001) && (door.getDoorId() <= 5010))) { // 水晶洞窟
            return;
        } else if (door.getDoorId() == 6006) { // 冒险洞穴二楼
            if (door.getOpenStatus() == ActionCodes.ACTION_Open) {
                return;
            }
            if (pc.getInventory().consumeItem(40163, 1)) { // 黄金钥匙
                door.open();
                final CloseTimer closetimer = new CloseTimer(door);
                closetimer.begin();
            }
        } else if (door.getDoorId() == 6007) { // 冒险洞穴二楼
            if (door.getOpenStatus() == ActionCodes.ACTION_Open) {
                return;
            }
            if (pc.getInventory().consumeItem(40313, 1)) { // 银钥匙
                door.open();
                final CloseTimer closetimer = new CloseTimer(door);
                closetimer.begin();
            }
        } else if (!this.isExistKeeper(pc, door.getKeeperId())) {
            if (door.getOpenStatus() == ActionCodes.ACTION_Open) {
                door.close();
            } else if (door.getOpenStatus() == ActionCodes.ACTION_Close) {
                door.open();
            }
        }
    }

    @Override
    public String getType() {
        return C_DOOR;
    }

    private boolean isExistKeeper(final L1PcInstance pc, final int keeperId) {
        if (keeperId == 0) {
            return false;
        }

        final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
        if (clan != null) {
            final int houseId = clan.getHouseId();
            if (houseId != 0) {
                final L1House house = HouseTable.getInstance().getHouseTable(
                        houseId);
                if (keeperId == house.getKeeperId()) {
                    return false;
                }
            }
        }
        return true;
    }
}
