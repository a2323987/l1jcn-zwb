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

import java.lang.reflect.Constructor;

import com.lineage.server.IdFactory;
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.model.L1Attack;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1Quest;
import com.lineage.server.model.L1World;
import com.lineage.server.serverpackets.S_FollowerPack;
import com.lineage.server.serverpackets.S_NPCTalkReturn;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Npc;

/**
 * 跟随者控制项
 */
public class L1FollowerInstance extends L1NpcInstance {

    private static final long serialVersionUID = 1L;

    public L1FollowerInstance(final L1Npc template, final L1NpcInstance target,
            final L1Character master) {
        super(template);

        this._master = master;
        this.setId(IdFactory.getInstance().nextId());

        this.setMaster(master);
        this.setX(target.getX());
        this.setY(target.getY());
        this.setMap(target.getMapId());
        this.setHeading(target.getHeading());
        this.setLightSize(target.getLightSize());

        target.setParalyzed(true);
        target.setDead(true);
        target.deleteMe();

        L1World.getInstance().storeObject(this);
        L1World.getInstance().addVisibleObject(this);
        for (final L1PcInstance pc : L1World.getInstance().getRecognizePlayer(
                this)) {
            this.onPerceive(pc);
        }

        this.startAI();
        master.addFollower(this);
    }

    private void createNewItem(final L1PcInstance pc, final int item_id,
            final int count) {
        final L1ItemInstance item = ItemTable.getInstance().createItem(item_id);
        item.setCount(count);
        if (item != null) {
            if (pc.getInventory().checkAddItem(item, count) == L1Inventory.OK) {
                pc.getInventory().storeItem(item);
            } else {
                L1World.getInstance()
                        .getInventory(pc.getX(), pc.getY(), pc.getMapId())
                        .storeItem(item);
            }
            pc.sendPackets(new S_ServerMessage(403, item.getLogName()));
        }
    }

    @Override
    public synchronized void deleteMe() {
        this._master.getFollowerList().remove(this.getId());
        this.getMap().setPassable(this.getLocation(), true);
        super.deleteMe();
    }

    @Override
    public boolean noTarget() {
        for (final L1Object object : L1World.getInstance().getVisibleObjects(
                this)) {
            if (object instanceof L1NpcInstance) {
                final L1NpcInstance npc = (L1NpcInstance) object;
                if ((npc.getNpcTemplate().get_npcId() == 70740 // 公爵的士兵
                        )
                        && (this.getNpcTemplate().get_npcId() == 71093)) { // 调查员
                    this.setParalyzed(true);
                    final L1PcInstance pc = (L1PcInstance) this._master;
                    if (!pc.getInventory().checkItem(40593)) {
                        this.createNewItem(pc, 40593, 1);
                    }
                    this.deleteMe();
                    return true;
                } else if ((npc.getNpcTemplate().get_npcId() == 70811 // 莱拉
                        )
                        && (this.getNpcTemplate().get_npcId() == 71094)) { // 安迪亚
                    this.setParalyzed(true);
                    final L1PcInstance pc = (L1PcInstance) this._master;
                    if (!pc.getInventory().checkItem(40582)
                            && !pc.getInventory().checkItem(40583)) { // 身上无安迪亚之袋、安迪亚之信
                        this.createNewItem(pc, 40582, 1);
                    }
                    this.deleteMe();
                    return true;
                } else if ((npc.getNpcTemplate().get_npcId() == 71061 // カドモス
                        )
                        && (this.getNpcTemplate().get_npcId() == 71062)) { // カミット
                    if (this.getLocation().getTileLineDistance(
                            this._master.getLocation()) < 3) {
                        final L1PcInstance pc = (L1PcInstance) this._master;
                        if (((pc.getX() >= 32448) && (pc.getX() <= 32452)) // カドモス周辺座标
                                && ((pc.getY() >= 33048) && (pc.getY() <= 33052))
                                && (pc.getMapId() == 440)) {
                            this.setParalyzed(true);
                            if (!pc.getInventory().checkItem(40711)) {
                                this.createNewItem(pc, 40711, 1);
                                pc.getQuest().set_step(L1Quest.QUEST_CADMUS, 3);
                            }
                            this.deleteMe();
                            return true;
                        }
                    }
                } else if ((npc.getNpcTemplate().get_npcId() == 71074 // リザードマンの长老
                        )
                        && (this.getNpcTemplate().get_npcId() == 71075)) {
                    // 疲れ果てたリザードマンファイター
                    if (this.getLocation().getTileLineDistance(
                            this._master.getLocation()) < 3) {
                        final L1PcInstance pc = (L1PcInstance) this._master;
                        if (((pc.getX() >= 32731) && (pc.getX() <= 32735)) // リザードマン长老周辺座标
                                && ((pc.getY() >= 32854) && (pc.getY() <= 32858))
                                && (pc.getMapId() == 480)) {
                            this.setParalyzed(true);
                            if (!pc.getInventory().checkItem(40633)) {
                                this.createNewItem(pc, 40633, 1);
                                pc.getQuest().set_step(L1Quest.QUEST_LIZARD, 2);
                            }
                            this.deleteMe();
                            return true;
                        }
                    }
                } else if ((npc.getNpcTemplate().get_npcId() == 70964 // バッシュ
                        )
                        && (this.getNpcTemplate().get_npcId() == 70957)) { // ロイ
                    if (this.getLocation().getTileLineDistance(
                            this._master.getLocation()) < 3) {
                        final L1PcInstance pc = (L1PcInstance) this._master;
                        if (((pc.getX() >= 32917) && (pc.getX() <= 32921)) // バッシュ周辺座标
                                && ((pc.getY() >= 32974) && (pc.getY() <= 32978))
                                && (pc.getMapId() == 410)) {
                            this.setParalyzed(true);
                            this.createNewItem(pc, 41003, 1);
                            pc.getQuest().set_step(L1Quest.QUEST_ROI, 0);
                            this.deleteMe();
                            return true;
                        }
                    }
                } else if ((npc.getNpcTemplate().get_npcId() == 71114)
                        && (this.getNpcTemplate().get_npcId() == 81350)) { // 迪嘉勒廷的女间谍
                    if (this.getLocation().getTileLineDistance(
                            this._master.getLocation()) < 15) {
                        final L1PcInstance pc = (L1PcInstance) this._master;
                        if (((pc.getX() >= 32542) && (pc.getX() <= 32585))
                                && ((pc.getY() >= 32656) && (pc.getY() <= 32698))
                                && (pc.getMapId() == 400)) {
                            this.setParalyzed(true);
                            this.createNewItem(pc, 49163, 1);
                            pc.getQuest().set_step(4, 4);
                            this.deleteMe();
                            return true;
                        }
                    }
                }
            }
        }

        if (this._master.isDead()
                || (this.getLocation().getTileLineDistance(
                        this._master.getLocation()) > 10)) {
            this.setParalyzed(true);
            this.spawn(this.getNpcTemplate().get_npcId(), this.getX(),
                    this.getY(), this.getHeading(), this.getMapId());
            this.deleteMe();
            return true;
        } else if ((this._master != null)
                && (this._master.getMapId() == this.getMapId())) {
            if (this.getLocation().getTileLineDistance(
                    this._master.getLocation()) > 2) {
                this.setDirectionMove(this.moveDirection(this._master.getX(),
                        this._master.getY()));
                this.setSleepTime(this.calcSleepTime(this.getPassispeed(),
                        MOVE_SPEED));
            }
        }
        return false;
    }

    @Override
    public void onAction(final L1PcInstance pc) {
        this.onAction(pc, 0);
    }

    @Override
    public void onAction(final L1PcInstance pc, final int skillId) {
        final L1Attack attack = new L1Attack(pc, this, skillId);
        if (attack.calcHit()) {
            attack.calcDamage();
            attack.calcStaffOfMana();
            attack.addPcPoisonAttack(pc, this);
            attack.addChaserAttack();
        }
        attack.action();
        attack.commit();
    }

    @Override
    public void onPerceive(final L1PcInstance perceivedFrom) {
        perceivedFrom.addKnownObject(this);
        perceivedFrom.sendPackets(new S_FollowerPack(this, perceivedFrom));
    }

    @Override
    public void onTalkAction(final L1PcInstance player) {
        if (this.isDead()) {
            return;
        }
        if (this.getNpcTemplate().get_npcId() == 71093) {
            if (this._master.equals(player)) {
                player.sendPackets(new S_NPCTalkReturn(this.getId(),
                        "searcherk2"));
            } else {
                player.sendPackets(new S_NPCTalkReturn(this.getId(),
                        "searcherk4"));
            }
        } else if (this.getNpcTemplate().get_npcId() == 71094) {
            if (this._master.equals(player)) {
                player.sendPackets(new S_NPCTalkReturn(this.getId(), "endiaq2"));
            } else {
                player.sendPackets(new S_NPCTalkReturn(this.getId(), "endiaq4"));
            }
        } else if (this.getNpcTemplate().get_npcId() == 71062) {
            if (this._master.equals(player)) {
                player.sendPackets(new S_NPCTalkReturn(this.getId(), "kamit2"));
            } else {
                player.sendPackets(new S_NPCTalkReturn(this.getId(), "kamit1"));
            }
        } else if (this.getNpcTemplate().get_npcId() == 71075) {
            if (this._master.equals(player)) {
                player.sendPackets(new S_NPCTalkReturn(this.getId(), "llizard2"));
            } else {
                player.sendPackets(new S_NPCTalkReturn(this.getId(),
                        "llizard1a"));
            }
        } else if (this.getNpcTemplate().get_npcId() == 70957) {
            if (this._master.equals(player)) {
                player.sendPackets(new S_NPCTalkReturn(this.getId(), "roi2"));
            } else {
                player.sendPackets(new S_NPCTalkReturn(this.getId(), "roi2"));
            }
        } else if (this.getNpcTemplate().get_npcId() == 81350) {
            if (this._master.equals(player)) {
                player.sendPackets(new S_NPCTalkReturn(this.getId(), "dspy3"));
            } else {
                player.sendPackets(new S_NPCTalkReturn(this.getId(), "dspy3"));
            }
        }

    }

    public void spawn(final int npcId, final int X, final int Y, final int H,
            final short Map) {
        final L1Npc l1npc = NpcTable.getInstance().getTemplate(npcId);
        if (l1npc != null) {
            L1NpcInstance mob = null;
            try {
                final String implementationName = l1npc.getImpl();
                final Constructor<?> _constructor = Class.forName(
                        (new StringBuilder())
                                .append("com.lineage.server.model.Instance.")
                                .append(implementationName).append("Instance")
                                .toString()).getConstructors()[0];
                mob = (L1NpcInstance) _constructor
                        .newInstance(new Object[] { l1npc });
                mob.setId(IdFactory.getInstance().nextId());
                mob.setX(X);
                mob.setY(Y);
                mob.setHomeX(X);
                mob.setHomeY(Y);
                mob.setMap(Map);
                mob.setHeading(H);
                L1World.getInstance().storeObject(mob);
                L1World.getInstance().addVisibleObject(mob);
                final L1Object object = L1World.getInstance().findObject(
                        mob.getId());
                final L1QuestInstance newnpc = (L1QuestInstance) object;
                newnpc.onNpcAI();
                newnpc.turnOnOffLight();
                newnpc.startChat(L1NpcInstance.CHAT_TIMING_APPEARANCE); // 开始喊话
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

}
