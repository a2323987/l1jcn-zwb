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

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.ClientThread;
import com.lineage.server.datatables.CharacterTable;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来血盟阶级的封包
 */
public class C_Rank extends ClientBasePacket {

    private static final String C_RANK = "[C] C_Rank";

    private static Logger _log = Logger.getLogger(C_Rank.class.getName());

    public C_Rank(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);

        final int data = this.readC(); // ?

        final L1PcInstance pc = clientthread.getActiveChar();

        if (pc == null) {
            return;
        }
        // 幽灵状态
        if (pc.isGhost()) {
            return;
        }
        // 死亡状态
        if (pc.isDead()) {
            return;
        }
        // 传送状态
        if (pc.isTeleport()) {
            return;
        }
        // 开个人商店中
        if (pc.isPrivateShop()) {
            return;
        }

        switch (data) {
            case 1:
                final int rank = this.readC();
                final String name = this.readS();
                final L1PcInstance targetPc = L1World.getInstance().getPlayer(
                        name);

                final L1Clan clan = L1World.getInstance().getClan(
                        pc.getClanname());
                if (clan == null) {
                    return;
                }

                if ((rank < 1) && (3 < rank)) {
                    // 请输入想要变更阶级的人的名称与阶级。[阶级 = 守护骑士、一般、见习]
                    pc.sendPackets(new S_ServerMessage(781));
                    return;
                }

                if (pc.isCrown()) { // 君主
                    if (pc.getId() != clan.getLeaderId()) { // 血盟主
                        pc.sendPackets(new S_ServerMessage(785)); // 你不再是君主了
                        return;
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(518)); // 血盟君主才可使用此命令。
                    return;
                }

                if (targetPc != null) { // 玩家在线上
                    if (pc.getClanid() == targetPc.getClanid()) { // 同血盟
                        try {
                            targetPc.setClanRank(rank);
                            targetPc.save(); // 储存玩家的资料到资料库中
                            targetPc.sendPackets(new S_PacketBox(
                                    S_PacketBox.MSG_RANK_CHANGED, rank)); // 你的阶级变更为%s
                        } catch (final Exception e) {
                            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                        }
                    } else {
                        pc.sendPackets(new S_ServerMessage(414)); // 您只能邀请您血盟中的成员。
                        return;
                    }
                } else { // 不在线
                    final L1PcInstance restorePc = CharacterTable.getInstance()
                            .restoreCharacter(name);
                    if ((restorePc != null)
                            && (restorePc.getClanid() == pc.getClanid())) { // 同じ血盟
                        try {
                            restorePc.setClanRank(rank);
                            restorePc.save(); // 储存玩家的资料到资料库中
                        } catch (final Exception e) {
                            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                        }
                    } else {
                        pc.sendPackets(new S_ServerMessage(109, name)); // 没有叫%0的人。
                        return;
                    }
                }
                break;

            case 2:
                pc.sendPackets(new S_ServerMessage(74, "同盟目录"));
                break;

            case 3:
                pc.sendPackets(new S_ServerMessage(74, "加入同盟"));
                break;

            case 4:
                pc.sendPackets(new S_ServerMessage(74, "退出同盟")); // \f1%0%o
                                                                 // 无法使用。
                break;

            case 5: // 生存的呐喊(CTRL+E)
                if (pc.get_food() >= 225) {
                    final int cryTime = pc.getCryTime();
                    if (cryTime < 1) {
                        final S_ServerMessage msg = new S_ServerMessage(1974);
                        pc.sendPackets(msg); // 目前无法使用生存的呐喊。
                        return;
                    }
                    if (pc.getWeapon() == null) {
                        final S_ServerMessage msg = new S_ServerMessage(1973);
                        pc.sendPackets(msg); // 装备武器后才能使用。
                        return;
                    }

                    int newHp = 0;
                    final int lv = pc.getWeapon().getEnchantLevel();
                    if ((cryTime >= 1) && (cryTime <= 29)) {
                        // boolean active = false;
                        if ((lv >= 0) && (lv <= 6)) {
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8907));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8907));
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8684));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8684));
                        } else if ((lv == 7) || (lv == 8)) {
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8909));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8909));
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8685));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8685));
                        } else if ((lv == 9) || (lv == 10)) {
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8910));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8910));
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8773));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8773));
                        } else if (lv >= 11) {
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8908));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8908));
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8686));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8686));
                        }
                        newHp = (int) (pc.getMaxHp() * (cryTime / 100.0D));
                    }
                    if (cryTime >= 30) {
                        final Random random = new Random();
                        if ((lv >= 0) && (lv <= 6)) {
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8907));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8907));
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8684));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8684));
                            newHp = (int) (pc.getMaxHp() * ((random.nextInt(20) + 20) / 100.0D));
                        } else if ((lv == 7) || (lv == 8)) {
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8909));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8909));
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8685));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8685));
                            newHp = (int) (pc.getMaxHp() * ((random.nextInt(20) + 30) / 100.0D));
                        } else if ((lv == 9) || (lv == 10)) {
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8910));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8910));
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8773));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8773));
                            newHp = (int) (pc.getMaxHp() * ((random.nextInt(10) + 50) / 100.0D));
                        } else if (lv >= 11) {
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8908));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8908));
                            pc.sendPackets(new S_SkillSound(pc.getId(), 8686));
                            pc.broadcastPacket(new S_SkillSound(pc.getId(),
                                    8686));
                            newHp = (int) (pc.getMaxHp() * (0.7));
                        }
                    }
                    if (newHp != 0) {
                        pc.setCurrentHp(pc.getCurrentHp() + newHp);
                        pc.set_food((short) 0);
                        pc.sendPackets(new S_PacketBox(11, pc.get_food()));
                        pc.setCryTime(0);
                        pc.stopCryOfSurvival();
                    }
                }
                break;

            case 6: // 生存的呐喊(ALT+0)
                if (pc.getWeapon() == null) {
                    final S_ServerMessage msg = new S_ServerMessage(1973);
                    pc.sendPackets(msg); // 装备武器后才能使用。
                    return;
                }
                final int lv = pc.getWeapon().getEnchantLevel();
                int gfxId = 0;
                if ((lv >= 0) && (lv <= 6)) {
                    gfxId = 8684;
                } else if ((lv == 7) || (lv == 8)) {
                    gfxId = 8685;
                } else if ((lv == 9) || (lv == 10)) {
                    gfxId = 8773;
                } else if (lv >= 11) {
                    gfxId = 8686;
                }
                pc.sendPackets(new S_SkillSound(pc.getId(), gfxId));
                break;

            case 8: // 查询指令(/入场时间)
                final int time = pc.getRocksPrisonTime();
                final int maxTime = 10800; // 3小时
                int newTime = (maxTime - time) / 60;
                if (newTime <= 0) {
                    newTime = 0;
                }
                final S_ServerMessage msg = new S_ServerMessage(2535, "奇岩监狱",
                        String.valueOf(newTime));
                pc.sendPackets(msg);
                break;

            default: // 检测
                _log.info("未处理的C_Rank类型: " + data);
                break;
        }
    }

    @Override
    public String getType() {
        return C_RANK;
    }
}
