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

import java.util.logging.Logger;

import com.lineage.server.GeneralThreadPool;
import com.lineage.server.datatables.NPCTalkDataTable;
import com.lineage.server.model.L1Attack;
import com.lineage.server.model.L1NpcTalkData;
import com.lineage.server.model.L1Quest;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.L1World;
import com.lineage.server.model.npc.L1NpcHtml;
import com.lineage.server.serverpackets.S_NPCTalkReturn;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.utils.Random;

// Referenced classes of package com.lineage.server.model:
// L1NpcInstance, L1Teleport, L1NpcTalkData, L1PcInstance,
// L1TeleporterPrices, L1TeleportLocations

/**
 * 传送控制项
 */
public class L1TeleporterInstance extends L1NpcInstance {

    class TeleportDelyTimer implements Runnable {

        public TeleportDelyTimer() {
        }

        @Override
        public void run() {
            try {
                L1TeleporterInstance.this._isNowDely = true;
                Thread.sleep(900000); // 15分
            } catch (final Exception e) {
                L1TeleporterInstance.this._isNowDely = false;
            }
            L1TeleporterInstance.this._isNowDely = false;
        }
    }

    private static final long serialVersionUID = 1L;

    boolean _isNowDely = false;

    private static Logger _log = Logger
            .getLogger(com.lineage.server.model.Instance.L1TeleporterInstance.class
                    .getName());

    public L1TeleporterInstance(final L1Npc template) {
        super(template);
    }

    /** 做最后的动作 */
    private void doFinalAction(final L1PcInstance player, final String action) {
        final int objid = this.getId();

        final int npcid = this.getNpcTemplate().get_npcId();
        String htmlid = null;
        boolean isTeleport = true;

        if (npcid == 50014) { // 迪隆
            if (!player.getInventory().checkItem(40581)) { // 不死族的钥匙
                isTeleport = false;
                htmlid = "dilongn";
            }
        } else if (npcid == 50043) { // 拉姆塔(50级任务)
            if (this._isNowDely) { // 瞬移延迟中
                isTeleport = false;
            }
        } else if (npcid == 50625) { // 古代人（Lv50级任务古代人空间2F）
            if (this._isNowDely) { // 瞬移延迟中
                isTeleport = false;
            }
        }

        if (isTeleport) { // 瞬移实行
            try {
                // (君主Lv30任务)
                if (action.equalsIgnoreCase("teleport mutant-dungen")) {
                    // 3マス以内のPc
                    for (final L1PcInstance otherPc : L1World.getInstance()
                            .getVisiblePlayer(player, 3)) {
                        if ((otherPc.getClanid() == player.getClanid())
                                && (otherPc.getId() != player.getId())) {
                            L1Teleport.teleport(otherPc, 32740, 32800,
                                    (short) 217, 5, true);
                        }
                    }
                    L1Teleport.teleport(player, 32740, 32800, (short) 217, 5,
                            true);
                }
                // 试练のダンジョン（LV30精灵任务）
                else if (action.equalsIgnoreCase("teleport mage-quest-dungen")) {
                    L1Teleport.teleport(player, 32791, 32788, (short) 201, 5,
                            true);
                } else if (action.equalsIgnoreCase("teleport 29")) { // ラムダ
                    L1PcInstance kni = null;
                    L1PcInstance elf = null;
                    L1PcInstance wiz = null;
                    // 3マス以内のPc
                    for (final L1PcInstance otherPc : L1World.getInstance()
                            .getVisiblePlayer(player, 3)) {
                        final L1Quest quest = otherPc.getQuest();
                        if (otherPc.isKnight() // 骑士
                                && (quest.get_step(L1Quest.QUEST_LEVEL50) == 1)) { // ディガルディン同意济み
                            if (kni == null) {
                                kni = otherPc;
                            }
                        } else if (otherPc.isElf() // 精灵
                                && (quest.get_step(L1Quest.QUEST_LEVEL50) == 1)) { // ディガルディン同意济み
                            if (elf == null) {
                                elf = otherPc;
                            }
                        } else if (otherPc.isWizard() // 法师
                                && (quest.get_step(L1Quest.QUEST_LEVEL50) == 1)) { // ディガルディン同意济み
                            if (wiz == null) {
                                wiz = otherPc;
                            }
                        }
                    }
                    if ((kni != null) && (elf != null) && (wiz != null)) { // 全クラス揃っている
                        L1Teleport.teleport(player, 32723, 32850, (short) 2000,
                                2, true);
                        L1Teleport.teleport(kni, 32750, 32851, (short) 2000, 6,
                                true);
                        L1Teleport.teleport(elf, 32878, 32980, (short) 2000, 6,
                                true);
                        L1Teleport.teleport(wiz, 32876, 33003, (short) 2000, 0,
                                true);
                        final TeleportDelyTimer timer = new TeleportDelyTimer();
                        GeneralThreadPool.getInstance().execute(timer);
                    }
                } else if (action.equalsIgnoreCase("teleport barlog")) { // 古代人（Lv50任务古代人空间2F）
                    L1Teleport.teleport(player, 32755, 32844, (short) 2002, 5,
                            true);
                    final TeleportDelyTimer timer = new TeleportDelyTimer();
                    GeneralThreadPool.getInstance().execute(timer);
                }
            } catch (final Exception e) {
            }
        }
        if (htmlid != null) { // 表示するhtmlがある场合
            player.sendPackets(new S_NPCTalkReturn(objid, htmlid));
        }
    }

    @Override
    public void onAction(final L1PcInstance pc) {
        this.onAction(pc, 0);
    }

    @Override
    public void onAction(final L1PcInstance pc, final int skillId) {
        final L1Attack attack = new L1Attack(pc, this, skillId);
        attack.calcHit();
        attack.action();
        attack.addChaserAttack();
        attack.calcDamage();
        attack.calcStaffOfMana();
        attack.addPcPoisonAttack(pc, this);
        attack.commit();
    }

    @Override
    public void onFinalAction(final L1PcInstance player, final String action) {
        final int objid = this.getId();
        final L1NpcTalkData talking = NPCTalkDataTable.getInstance()
                .getTemplate(this.getNpcTemplate().get_npcId());
        if (action.equalsIgnoreCase("teleportURL")) {
            final L1NpcHtml html = new L1NpcHtml(talking.getTeleportURL());
            player.sendPackets(new S_NPCTalkReturn(objid, html));
        } else if (action.equalsIgnoreCase("teleportURLA")) {
            final L1NpcHtml html = new L1NpcHtml(talking.getTeleportURLA());
            player.sendPackets(new S_NPCTalkReturn(objid, html));
        }
        if (action.startsWith("teleport ")) {
            _log.finest((new StringBuilder()).append("Setting action to : ")
                    .append(action).toString());
            this.doFinalAction(player, action);
        }
    }

    @Override
    public void onTalkAction(final L1PcInstance player) {
        final int objid = this.getId();
        final L1NpcTalkData talking = NPCTalkDataTable.getInstance()
                .getTemplate(this.getNpcTemplate().get_npcId());
        final int npcid = this.getNpcTemplate().get_npcId();
        final L1Quest quest = player.getQuest();
        String htmlid = null;

        if (talking != null) {
            if (npcid == 50014) { // 迪隆(法师30级任务)
                if (player.isWizard()) { // 魔法师
                    if ((quest.get_step(L1Quest.QUEST_LEVEL30) == 1)
                            && !player.getInventory().checkItem(40579)) { // 不死族的骨头
                        htmlid = "dilong1";
                    } else {
                        htmlid = "dilong3";
                    }
                }
            } else if (npcid == 70779) { // 看守蚂蚁
                if (player.getTempCharGfx() == 1037) { // 巨型蚂蚁变身
                    htmlid = "ants3";
                } else if (player.getTempCharGfx() == 1039) {// 巨大兵蚁变身
                    if (player.isCrown()) { // 君主
                        if (quest.get_step(L1Quest.QUEST_LEVEL30) == 1) {
                            if (player.getInventory().checkItem(40547)) { // 村民的遗物
                                htmlid = "antsn";
                            } else {
                                htmlid = "ants1";
                            }
                        } else { // Step1以外
                            htmlid = "antsn";
                        }
                    } else { // 君主以外
                        htmlid = "antsn";
                    }
                }
            } else if (npcid == 70853) { // 精灵公主(精灵30级任务)
                if (player.isElf()) { // 精灵
                    if (quest.get_step(L1Quest.QUEST_LEVEL30) == 1) {
                        if (!player.getInventory().checkItem(40592)) { // 受诅咒的精灵书
                            if (Random.nextInt(100) < 50) { // 50%的几率传送到黑暗精灵地牢
                                htmlid = "fairyp2";
                            } else { // 黑暗精灵地牢
                                htmlid = "fairyp1";
                            }
                        }
                    }
                }
            } else if (npcid == 50031) { // 赛菲亚(精灵45级任务)
                if (player.isElf()) { // 精灵
                    if (quest.get_step(L1Quest.QUEST_LEVEL45) == 2) {
                        if (!player.getInventory().checkItem(40602)) { // 蓝色长笛
                            htmlid = "sepia1";
                        }
                    }
                }
            } else if (npcid == 50043) { // 拉姆塔(50级任务)
                if (quest.get_step(L1Quest.QUEST_LEVEL50) == L1Quest.QUEST_END) {
                    htmlid = "ramuda2";
                } else if (quest.get_step(L1Quest.QUEST_LEVEL50) == 1) { // ディガルディン同意济み
                    if (player.isCrown()) { // 君主
                        if (this._isNowDely) { // 瞬移延迟中
                            htmlid = "ramuda4";
                        } else {
                            htmlid = "ramudap1";
                        }
                    } else { // 君主以外
                        htmlid = "ramuda1";
                    }
                } else {
                    htmlid = "ramuda3";
                }
            }
            // 歌う岛のテレポーター(废止)
            else if (npcid == 50082) {
                if (player.getLevel() < 13) {
                    htmlid = "en0221";
                } else {
                    if (player.isElf()) {
                        htmlid = "en0222e";
                    } else if (player.isDarkelf()) {
                        htmlid = "en0222d";
                    } else {
                        htmlid = "en0222";
                    }
                }
            }
            // 巴尼亚
            else if (npcid == 50001) {
                if (player.isElf()) {
                    htmlid = "barnia3";
                } else if (player.isKnight() || player.isCrown()) {
                    htmlid = "barnia2";
                } else if (player.isWizard() || player.isDarkelf()) {
                    htmlid = "barnia1";
                }
            } else if (npcid == 81258) { // 幻术师 艾希雅
                if (player.isIllusionist()) {
                    htmlid = "asha1";
                } else {
                    htmlid = "asha2";
                }
            } else if (npcid == 81259) { // 龙骑士 费艾娜
                if (player.isDragonKnight()) {
                    htmlid = "feaena1";
                } else {
                    htmlid = "feaena2";
                }
            } else if (npcid == 71013) { // 卡连
                if (player.isDarkelf()) {
                    if (player.getLevel() < 14) {
                        htmlid = "karen1";
                    } else {
                        htmlid = "karen4";
                    }
                } else {
                    htmlid = "karen2";
                }
            } else if (npcid == 71095) { // 堕落的灵魂
                if (player.isDarkelf()) { // 黑暗妖精
                    if (player.getLevel() >= 50) {
                        final int lv50_step = quest
                                .get_step(L1Quest.QUEST_LEVEL50);
                        if (lv50_step == L1Quest.QUEST_END) {
                            htmlid = "csoulq3";
                        } else if (lv50_step >= 3) {
                            boolean find = false;
                            for (final Object objs : L1World.getInstance()
                                    .getVisibleObjects(306).values()) {
                                if (objs instanceof L1PcInstance) {
                                    final L1PcInstance _pc = (L1PcInstance) objs;
                                    if (_pc != null) {
                                        find = true;
                                        htmlid = "csoulqn"; // 你的邪念还不够！
                                        break;
                                    }
                                }
                            }
                            if (!find) {
                                htmlid = "csoulq1";
                            } else {
                                htmlid = "csoulqn";
                            }
                        }
                    }
                }
            }

            // html表示
            if (htmlid != null) { // htmlidが指定されている场合
                player.sendPackets(new S_NPCTalkReturn(objid, htmlid));
            } else {
                if (player.getLawful() < -1000) { // プレイヤーがカオティック
                    player.sendPackets(new S_NPCTalkReturn(talking, objid, 2));
                } else {
                    player.sendPackets(new S_NPCTalkReturn(talking, objid, 1));
                }
            }
        } else {
            _log.finest((new StringBuilder())
                    .append("No actions for npc id : ").append(objid)
                    .toString());
        }
    }

}
