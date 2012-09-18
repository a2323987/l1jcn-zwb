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

import java.util.Calendar;

import com.lineage.server.ClientThread;
import com.lineage.server.model.L1DragonSlayer;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SendLocation;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.L1SpawnUtil;

/**
 * 发送座标位置
 */
public class C_SendLocation extends ClientBasePacket {

    private static final String C_SEND_LOCATION = "[C] C_SendLocation";

    public C_SendLocation(final byte abyte0[], final ClientThread client) {
        super(abyte0);
        final int type = this.readC();

        // 客户端在主动,非主动转换时
        // オペコード 0x57 0x0dパケットを送ってくるが详细不明の为无视
        // マップ座标转送时は0x0bパケット
        if (type == 0x0d) {
            return;
            /*
             * 视窗内:0d 01 xx // xx 就是值会变动，不知原因。 视窗外:0d 00 xx // xx 就是值会变动，不知原因。
             */
        }

        if (type == 0x0b) {
            final String name = this.readS();
            final int mapId = this.readH();
            final int x = this.readH();
            final int y = this.readH();
            final int msgId = this.readC();

            if (name.isEmpty()) {
                return;
            }
            final L1PcInstance target = L1World.getInstance().getPlayer(name);
            if (target != null) {
                final L1PcInstance pc = client.getActiveChar();
                final String sender = pc.getName();
                target.sendPackets(new S_SendLocation(type, sender, mapId, x,
                        y, msgId));
                pc.sendPackets(new S_ServerMessage(1783, name)); // 已发送座标位置给%0。
            }
        } else if (type == 0x06) {
            @SuppressWarnings("unused")
            final int objectId = this.readD();
            final int gate = this.readD();
            final int dragonGate[] = { 81273, // 龙之门扉 (安塔瑞斯副本)
                    81274, // 龙之门扉 (法利昂副本)
                    81275, // 龙之门扉 (林德拜尔副本)
                    81276
            // 龙之门扉 (巴拉卡斯副本)
            };
            final L1PcInstance pc = client.getActiveChar();
            if ((gate >= 0) && (gate <= 3)) {
                final Calendar nowTime = Calendar.getInstance();
                if ((nowTime.get(Calendar.HOUR_OF_DAY) >= 8)
                        && (nowTime.get(Calendar.HOUR_OF_DAY) < 12)) {
                    pc.sendPackets(new S_ServerMessage(1643)); // 每日上午 8 点到 12
                                                               // 点为止，暂时无法使用龙之钥匙。
                } else {
                    boolean limit = true;
                    switch (gate) {
                        case 0:
                            for (int i = 0; i < 6; i++) {
                                if (!L1DragonSlayer.getInstance()
                                        .getPortalNumber()[i]) {
                                    limit = false;
                                }
                            }
                            break;
                        case 1:
                            for (int i = 6; i < 12; i++) {
                                if (!L1DragonSlayer.getInstance()
                                        .getPortalNumber()[i]) {
                                    limit = false;
                                }
                            }
                            break;
                    }
                    if (!limit) { // 未达上限可开设龙门
                        if (!pc.getInventory().consumeItem(47010, 1)) {
                            pc.sendPackets(new S_ServerMessage(1567)); // 需要龙之钥匙。
                            return;
                        }
                        L1SpawnUtil.spawn(pc, dragonGate[gate], 0,
                                120 * 60 * 1000); // 开启 2 小时
                    }
                }
            }
        }
    }

    @Override
    public String getType() {
        return C_SEND_LOCATION;
    }
}
