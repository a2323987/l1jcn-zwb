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

import com.lineage.server.ClientThread;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Message_YN;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来建立组队的封包
 */
public class C_CreateParty extends ClientBasePacket {

    private static final String C_CREATE_PARTY = "[C] C_CreateParty";

    public C_CreateParty(final byte decrypt[], final ClientThread client)
            throws Exception {
        super(decrypt);

        final L1PcInstance pc = client.getActiveChar();

        final int type = this.readC();
        if ((type == 0) || (type == 1)) { // 自动接受组队 on 与 off 的同
            final int targetId = this.readD();
            final L1Object temp = L1World.getInstance().findObject(targetId);
            if (temp instanceof L1PcInstance) {
                final L1PcInstance targetPc = (L1PcInstance) temp;
                if (pc.getId() == targetPc.getId()) {
                    return;
                }

                // 邀请组队时，对象不再荧幕内或是7步内
                if ((!pc.getLocation().isInScreen(targetPc.getLocation()) || (pc
                        .getLocation().getTileLineDistance(
                                targetPc.getLocation()) > 7))) {
                    pc.sendPackets(new S_ServerMessage(952)); // 距离太远以致于无法邀请至队伍。
                    return;
                }
                if (targetPc.isInParty()) {
                    pc.sendPackets(new S_ServerMessage(415)); // 您无法邀请已经参加其他队伍的人。
                    return;
                }

                if (pc.isInParty()) {
                    if (pc.getParty().isLeader(pc)) {
                        targetPc.setPartyType(type);
                        targetPc.setPartyID(pc.getId());
                        switch (type) {
                            case 0:
                                targetPc.sendPackets(new S_Message_YN(953, pc
                                        .getName())); // 玩家 %0%s 邀请您加入队伍？(Y/N)
                                break;
                            case 1:
                                targetPc.sendPackets(new S_Message_YN(954, pc
                                        .getName())); // 玩家 %0%s
                                                      // 邀请您加入自动分配队伍？(Y/N)
                                break;
                        }
                    } else {
                        pc.sendPackets(new S_ServerMessage(416)); // 只有领导者才能邀请其他的成员。
                    }
                } else {
                    pc.setPartyType(type);
                    targetPc.setPartyID(pc.getId());
                    switch (type) {
                        case 0:
                            targetPc.sendPackets(new S_Message_YN(953, pc
                                    .getName())); // 玩家
                                                  // %0%s
                                                  // 邀请您加入队伍？(Y/N)
                            break;
                        case 1:
                            targetPc.sendPackets(new S_Message_YN(954, pc
                                    .getName()));
                            break;
                    }
                }
            }
        } else if (type == 2) { // 聊天组队
            final String name = this.readS();
            final L1PcInstance targetPc = L1World.getInstance().getPlayer(name);
            if (targetPc == null) {
                pc.sendPackets(new S_ServerMessage(109)); // 没有叫%0的人。
                return;
            }
            if (pc.getId() == targetPc.getId()) {
                return;
            }

            // 邀请组队时，对象不再荧幕内或是7步内
            if ((!pc.getLocation().isInScreen(targetPc.getLocation()) || (pc
                    .getLocation().getTileLineDistance(targetPc.getLocation()) > 7))) {
                pc.sendPackets(new S_ServerMessage(952)); // 距离太远以致于无法邀请至队伍。
                return;
            }
            if (targetPc.isInChatParty()) {
                pc.sendPackets(new S_ServerMessage(415)); // 您无法邀请已经参加其他队伍的人。
                return;
            }

            if (pc.isInChatParty()) {
                if (pc.getChatParty().isLeader(pc)) {
                    targetPc.setPartyID(pc.getId());
                    targetPc.sendPackets(new S_Message_YN(951, pc.getName())); // 您要接受玩家
                                                                               // %0%s
                                                                               // 提出的队伍对话邀请吗？(Y/N)
                } else {
                    pc.sendPackets(new S_ServerMessage(416)); // 只有领导者才能邀请其他的成员。
                }
            } else {
                targetPc.setPartyID(pc.getId());
                targetPc.sendPackets(new S_Message_YN(951, pc.getName())); // 您要接受玩家
                                                                           // %0%s
                                                                           // 提出的队伍对话邀请吗？(Y/N)
            }
        }
        // 队长委任
        else if (type == 3) {
            // 不是队长时, 不可使用
            if ((pc.getParty() == null) || !pc.getParty().isLeader(pc)) {
                pc.sendPackets(new S_ServerMessage(1697));
                return;
            }

            // 取得目标物件编号
            final int targetId = this.readD();

            // 尝试取得目标
            final L1Object obj = L1World.getInstance().findObject(targetId);

            // 判断目标是否合理
            if ((obj == null) || (pc.getId() == obj.getId())
                    || !(obj instanceof L1PcInstance)) {
                return;
            }

            // 邀请组队时，对象不再荧幕内或是7步内
            if ((!pc.getLocation().isInScreen(obj.getLocation()) || (pc
                    .getLocation().getTileLineDistance(obj.getLocation()) > 7))) {
                pc.sendPackets(new S_ServerMessage(1695)); // 距离太远以致于无法邀请至队伍。
                return;
            }

            // 转型为玩家物件
            final L1PcInstance targetPc = (L1PcInstance) obj;

            // 判断目标是否属于相同队伍
            if (!targetPc.isInParty()) {
                pc.sendPackets(new S_ServerMessage(1696));
                return;
            }
            // 委任给其他玩家?
            pc.sendPackets(new S_Message_YN(1703, "")); // 确认把队长委任给队员？

            // 指定队长给新的目标
            pc.getParty().passLeader(targetPc);
        }
    }

    @Override
    public String getType() {
        return C_CREATE_PARTY;
    }

}
