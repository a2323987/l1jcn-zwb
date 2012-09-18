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
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Message_YN;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.FaceToFace;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来加入血盟的封包
 */
public class C_JoinClan extends ClientBasePacket {

    private static final String C_JOIN_CLAN = "[C] C_JoinClan";

    public C_JoinClan(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);

        final L1PcInstance pc = clientthread.getActiveChar();
        if (pc.isGhost()) {
            return;
        }

        final L1PcInstance target = FaceToFace.faceToFace(pc);
        if (target != null) {
            this.JoinClan(pc, target);
        }
    }

    @Override
    public String getType() {
        return C_JOIN_CLAN;
    }

    // 加入血盟
    private void JoinClan(final L1PcInstance player, final L1PcInstance target) {
        if (!target.isCrown()) { // 如果面对的对象不是王族
            player.sendPackets(new S_ServerMessage(92, target.getName())); // \f1%0%d
                                                                           // 不是王子或公主。
            return;
        }

        final int clan_id = target.getClanid();
        final String clan_name = target.getClanname();
        if (clan_id == 0) { // 面对的对象没有创立血盟
            player.sendPackets(new S_ServerMessage(90, target.getName())); // \f1%0%d
                                                                           // 尚未创立血盟。
            return;
        }

        final L1Clan clan = L1World.getInstance().getClan(clan_name);
        if (clan == null) {
            return;
        }

        if (target.getId() != clan.getLeaderId()) { // 面对的对象不是盟主
            player.sendPackets(new S_ServerMessage(92, target.getName())); // \f1%0%d
                                                                           // 不是王子或公主。
            return;
        }

        if (player.getClanid() != 0) { // 已经加入血盟
            if (player.isCrown()) { // 自己是盟主
                final String player_clan_name = player.getClanname();
                final L1Clan player_clan = L1World.getInstance().getClan(
                        player_clan_name);
                if (player_clan == null) {
                    return;
                }

                if (player.getId() != player_clan.getLeaderId()) { // 已经加入其他血盟
                    player.sendPackets(new S_ServerMessage(89)); // \f1你已经有血盟了。
                    return;
                }

                if ((player_clan.getCastleId() != 0) || // 有城堡或有血盟小屋
                        (player_clan.getHouseId() != 0)) {
                    player.sendPackets(new S_ServerMessage(665)); // \f1拥有城堡与血盟小屋的状态下无法解散血盟。
                    return;
                }
            } else {
                player.sendPackets(new S_ServerMessage(89)); // \f1你已经有血盟了。
                return;
            }
        }

        target.setTempID(player.getId()); // 暂时保存面对的人的ID
        target.sendPackets(new S_Message_YN(97, player.getName())); // \f3%0%s
                                                                    // 想加入你的血盟。你接受吗。(Y/N)
    }
}
