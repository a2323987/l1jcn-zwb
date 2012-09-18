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

import java.util.List;

import com.lineage.server.ClientThread;
import com.lineage.server.WarTimeController;
import com.lineage.server.model.L1CastleLocation;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1War;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Message_YN;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * TODO 翻译，好多 处理收到由客户端传来盟战的封包
 */
public class C_War extends ClientBasePacket {

    private static final String C_WAR = "[C] C_War";

    /**
     * 要求:宣战、停战、投降
     */
    public C_War(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);
        final int type = this.readC();
        final String s = this.readS();

        final L1PcInstance player = clientthread.getActiveChar(); // 线上角色
        final String playerName = player.getName(); // 角色名称
        final String clanName = player.getClanname(); // 血盟名称
        final int clanId = player.getClanid(); // 血盟ID

        // 不是王族
        if (!player.isCrown()) {
            player.sendPackets(new S_ServerMessage(478)); // \f1只有王子和公主才能宣战。
            return;
        }

        // 没有血盟
        if (clanId == 0) {
            player.sendPackets(new S_ServerMessage(272)); // \f1若想要战争，首先必须创立血盟。
            return;
        }

        // 取回血盟
        final L1Clan clan = L1World.getInstance().getClan(clanName);

        // 找不到血盟
        if (clan == null) {
            return;
        }

        // 不是血盟主
        if (player.getId() != clan.getLeaderId()) {
            player.sendPackets(new S_ServerMessage(478)); // \f1只有王子和公主才能宣战。
            return;
        }

        // 指定的为自己的血盟
        if (clanName.toLowerCase().equals(s.toLowerCase())) {
            return;
        }

        // 对手血盟
        L1Clan enemyClan = null;

        // 对手血盟名称
        String enemyClanName = null;

        // 取得所有的血盟
        for (final L1Clan checkClan : L1World.getInstance().getAllClans()) {
            if (checkClan.getClanName().toLowerCase().equals(s.toLowerCase())) {
                enemyClan = checkClan;
                enemyClanName = checkClan.getClanName();
                break;
            }
        }

        // 对手为空血盟
        if (enemyClan == null) {
            return;
        }

        // 战争中
        boolean inWar = false;

        // 取得所有的盟战
        final List<L1War> warList = L1World.getInstance().getWarList();
        for (final L1War war : warList) {

            // 检查是否在盟战中
            if (war.CheckClanInWar(clanName)) {

                // 宣战公告
                if (type == 0) {
                    player.sendPackets(new S_ServerMessage(234)); // \f1你的血盟已经在战争中。
                    return;
                }
                inWar = true;
                break;
            }
        }

        // 自己不在战争中的降伏或终结
        if (!inWar && ((type == 2) || (type == 3))) {
            return;
        }

        // 有城堡
        if (clan.getCastleId() != 0) {

            // 宣战公告
            if (type == 0) {
                player.sendPackets(new S_ServerMessage(474)); // 你已经拥有城堡，无法再拥有其他城堡。
                return;
            } else if ((type == 2) || (type == 3)) { // 投降、或是结束
                return;
            }
        }

        // 对方王族等级低于15
        if ((enemyClan.getCastleId() == 0) && (player.getLevel() <= 15)) {
            player.sendPackets(new S_ServerMessage(232)); // \f1等级25以下的君主无法宣战。
            return;
        }

        // 对方王族等级低于25
        if ((enemyClan.getCastleId() != 0) && (player.getLevel() < 25)) {
            player.sendPackets(new S_ServerMessage(475)); // 若要攻城，君主的等级得２５以上。
            return;
        }

        // 对方王族有城堡
        if (enemyClan.getCastleId() != 0) {
            final int castle_id = enemyClan.getCastleId();
            if (WarTimeController.getInstance().isNowWar(castle_id)) { // 在战争时间内
                final L1PcInstance clanMember[] = clan.getOnlineClanMember();
                for (final L1PcInstance element : clanMember) {
                    if (L1CastleLocation.checkInWarArea(castle_id, element)) {
                        player.sendPackets(new S_ServerMessage(477)); // 包含你，所有的血盟成员到城外，才能宣布攻城。
                        return;
                    }
                }
                boolean enemyInWar = false;
                for (final L1War war : warList) {
                    if (war.CheckClanInWar(enemyClanName)) { // 对方已经在战争中
                        if (type == 0) { // 宣战布告
                            war.DeclareWar(clanName, enemyClanName); // 谁对谁宣战
                            war.AddAttackClan(clanName); // 增加攻击血盟名称
                        } else if ((type == 2) || (type == 3)) {
                            if (!war.CheckClanInSameWar(clanName, enemyClanName)) { // 对方在别的战争中
                                return;
                            }
                            if (type == 2) { // 降伏
                                war.SurrenderWar(clanName, enemyClanName);
                            } else if (type == 3) { // 终结
                                war.CeaseWar(clanName, enemyClanName);
                            }
                        }
                        enemyInWar = true;
                        break;
                    }
                }
                if (!enemyInWar && (type == 0)) { // 与对手不在同一场战争中、宣战布告
                    final L1War war = new L1War();
                    war.handleCommands(1, clanName, enemyClanName); // 攻城战开始
                }
            } else { // 战争时间外
                if (type == 0) { // 宣战布告
                    player.sendPackets(new S_ServerMessage(476)); // 尚未到攻城时间。
                }
            }
        } else { // 没有对手的其他血盟主
            boolean enemyInWar = false;
            for (final L1War war : warList) {
                if (war.CheckClanInWar(enemyClanName)) { // 对手在战争中
                    if (type == 0) { // 宣战布告
                        player.sendPackets(new S_ServerMessage(236,
                                enemyClanName)); // %0 血盟拒绝你的宣战。
                        return;
                    } else if ((type == 2) || (type == 3)) { // 降伏或终结
                        if (!war.CheckClanInSameWar(clanName, enemyClanName)) { // 自己与对手不在同一场战争
                            return;
                        }
                    }
                    enemyInWar = true;
                    break;
                }
            }
            if (!enemyInWar && ((type == 2) || (type == 3))) { // 对手在战争时间以外、降伏或终结
                return;
            }

            // 取回对方血盟主资料
            final L1PcInstance enemyLeader = L1World.getInstance().getPlayer(
                    enemyClan.getLeaderName());

            if (enemyLeader == null) { // 没有找到对方血盟主
                player.sendPackets(new S_ServerMessage(218, enemyClanName)); // \f1%0
                                                                             // 血盟君主不在线上。
                return;
            }

            if (type == 0) { // 宣战布告
                enemyLeader.setTempID(player.getId()); // 保存对手ID
                enemyLeader.sendPackets(new S_Message_YN(217, clanName,
                        playerName)); // %0 血盟向你的血盟宣战。是否接受？(Y/N)
            } else if (type == 2) { // 降伏
                enemyLeader.setTempID(player.getId()); // 保存对手ID
                enemyLeader.sendPackets(new S_Message_YN(221, clanName)); // %0
                                                                          // 血盟要向你投降。是否接受？(Y/N)
            } else if (type == 3) { // 终结
                enemyLeader.setTempID(player.getId()); // 保存对手ID
                enemyLeader.sendPackets(new S_Message_YN(222, clanName)); // %0
                                                                          // 血盟要结束战争。是否接受？(Y/N)
            }
        }
    }

    @Override
    public String getType() {
        return C_WAR;
    }

}
