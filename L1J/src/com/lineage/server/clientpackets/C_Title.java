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

import com.lineage.Config;
import com.lineage.server.ClientThread;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_CharTitle;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来变更称号的封包
 */
public class C_Title extends ClientBasePacket {

    private static final String C_TITLE = "[C] C_Title";
    private static Logger _log = Logger.getLogger(C_Title.class.getName());

    public C_Title(final byte abyte0[], final ClientThread clientthread) {
        super(abyte0);
        final L1PcInstance pc = clientthread.getActiveChar();
        final String charName = this.readS();
        final String title = this.readS();

        if (charName.isEmpty() || title.isEmpty()) {
            // \f1请以如下的格式输入。: "/title \f0角色名称 角色封号\f1"
            pc.sendPackets(new S_ServerMessage(196));
            return;
        }
        final L1PcInstance target = L1World.getInstance().getPlayer(charName);
        if (target == null) {
            return;
        }

        if (pc.isGm()) {
            this.changeTitle(target, title);
            return;
        }

        if (this.isClanLeader(pc)) { // 血盟主
            if (pc.getId() == target.getId()) { // 自己
                if (pc.getLevel() < 10) {
                    // \f1加入血盟之后等级10以上才可使用封号。
                    pc.sendPackets(new S_ServerMessage(197));
                    return;
                }
                this.changeTitle(pc, title);
            } else { // 他人
                if (pc.getClanid() != target.getClanid()) {
                    // \f1除了王族以外的人，不能够授与头衔给其他人。
                    pc.sendPackets(new S_ServerMessage(199));
                    return;
                }
                if (target.getLevel() < 10) {
                    // \f1%0的等级还不到10级，因此无法给封号。
                    pc.sendPackets(new S_ServerMessage(202, charName));
                    return;
                }
                this.changeTitle(target, title);
                final L1Clan clan = L1World.getInstance().getClan(
                        pc.getClanname());
                if (clan != null) {
                    for (final L1PcInstance clanPc : clan.getOnlineClanMember()) {
                        // \f1%0%s 赋予%1 '%2'的封号。
                        clanPc.sendPackets(new S_ServerMessage(203, pc
                                .getName(), charName, title));
                    }
                }
            }
        } else {
            if (pc.getId() == target.getId()) { // 自身
                if ((pc.getClanid() != 0) && !Config.CHANGE_TITLE_BY_ONESELF) {
                    // \f1王子或公主才可给血盟员封号。
                    pc.sendPackets(new S_ServerMessage(198));
                    return;
                }
                if (target.getLevel() < 40) {
                    // \f1若等级40以上，没有加入血盟也可拥有封号。
                    pc.sendPackets(new S_ServerMessage(200));
                    return;
                }
                this.changeTitle(pc, title);
            } else { // 他人
                if (pc.isCrown()) { // 连合に所属した君主
                    if (pc.getClanid() == target.getClanid()) {
                        // \f1%0%d不是你的血盟成员。
                        pc.sendPackets(new S_ServerMessage(201, pc
                                .getClanname()));
                        return;
                    }
                }
            }
        }
    }

    private void changeTitle(final L1PcInstance pc, final String title) {
        final int objectId = pc.getId();
        pc.setTitle(title);
        pc.sendPackets(new S_CharTitle(objectId, title));
        pc.broadcastPacket(new S_CharTitle(objectId, title));
        try {
            pc.save(); // 储存玩家的资料到资料库中
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    @Override
    public String getType() {
        return C_TITLE;
    }

    private boolean isClanLeader(final L1PcInstance pc) {
        boolean isClanLeader = false;
        if (pc.getClanid() != 0) { // 有血盟
            final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
            if (clan != null) {
                if (pc.isCrown() && (pc.getId() == clan.getLeaderId())) { // 君主、かつ、血盟主
                    isClanLeader = true;
                }
            }
        }
        return isClanLeader;
    }

}
