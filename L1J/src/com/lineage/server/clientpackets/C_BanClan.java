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

import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.ClientThread;
import com.lineage.server.datatables.CharacterTable;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理从客户端传来脱离血盟的封包
 */
public class C_BanClan extends ClientBasePacket {

    private static final String C_BAN_CLAN = "[C] C_BanClan";

    private static Logger _log = Logger.getLogger(C_BanClan.class.getName());

    public C_BanClan(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);
        final String s = this.readS();

        final L1PcInstance pc = clientthread.getActiveChar();
        final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
        if (clan != null) {
            final String clanMemberName[] = clan.getAllMembers();
            int i;
            if (pc.isCrown() && (pc.getId() == clan.getLeaderId())) { // 王族，或者已经创立血盟
                for (i = 0; i < clanMemberName.length; i++) {
                    if (pc.getName().toLowerCase().equals(s.toLowerCase())) { // 是血盟创立者
                        return;
                    }
                }
                final L1PcInstance tempPc = L1World.getInstance().getPlayer(s);
                if (tempPc != null) { // 玩家在线上
                    if (tempPc.getClanid() == pc.getClanid()) { // 确定同血盟
                        tempPc.setClanid(0);
                        tempPc.setClanname("");
                        tempPc.setClanRank(0);
                        tempPc.save(); // 储存玩家的资料到资料库中
                        clan.delMemberName(tempPc.getName());
                        tempPc.sendPackets(new S_ServerMessage(238, pc
                                .getClanname())); // 你被 %0 血盟驱逐了。
                        pc.sendPackets(new S_ServerMessage(240, tempPc
                                .getName())); // %0%o 被你从你的血盟驱逐了。
                    } else {
                        pc.sendPackets(new S_ServerMessage(109, s)); // 没有叫%0的人。
                    }
                } else { // 玩家离线中
                    try {
                        final L1PcInstance restorePc = CharacterTable
                                .getInstance().restoreCharacter(s);
                        if ((restorePc != null)
                                && (restorePc.getClanid() == pc.getClanid())) { // 确定同血盟
                            restorePc.setClanid(0);
                            restorePc.setClanname("");
                            restorePc.setClanRank(0);
                            restorePc.save(); // 储存玩家的资料到资料库中
                            clan.delMemberName(restorePc.getName());
                            pc.sendPackets(new S_ServerMessage(240, restorePc
                                    .getName())); // %0%o 被你从你的血盟驱逐了。
                        } else {
                            pc.sendPackets(new S_ServerMessage(109, s)); // %0%o
                                                                         // 被你从你的血盟驱逐了。
                        }
                    } catch (final Exception e) {
                        _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                    }
                }
            } else {
                pc.sendPackets(new S_ServerMessage(518)); // 血盟君主才可使用此命令。
            }
        }
    }

    @Override
    public String getType() {
        return C_BAN_CLAN;
    }
}
