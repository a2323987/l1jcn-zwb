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
package com.lineage.server.model;

import static com.lineage.server.model.skill.L1SkillId.CANCELLATION;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.lineage.server.model.Instance.L1DoorInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.skill.L1SkillUse;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.collections.Lists;

/**
 * 幽灵之家
 */
public class L1HauntedHouse {

    public class L1HauntedHouseReadyTimer extends TimerTask {

        public L1HauntedHouseReadyTimer() {
        }

        public void begin() {
            final Timer timer = new Timer();
            timer.schedule(this, 90000); // 约90秒？
        }

        @Override
        public void run() {
            L1HauntedHouse.this.startHauntedHouse();
            final L1HauntedHouseTimer hhTimer = new L1HauntedHouseTimer();
            hhTimer.begin();
        }

    }

    public class L1HauntedHouseTimer extends TimerTask {

        public L1HauntedHouseTimer() {
        }

        public void begin() {
            final Timer timer = new Timer();
            timer.schedule(this, 300000); // 5分
        }

        @Override
        public void run() {
            L1HauntedHouse.this.endHauntedHouse();
            this.cancel();
        }

    }

    public static final int STATUS_NONE = 0;

    public static final int STATUS_READY = 1;

    public static final int STATUS_PLAYING = 2;

    private final List<L1PcInstance> _members = Lists.newList();

    private int _hauntedHouseStatus = STATUS_NONE;

    private int _winnersCount = 0;

    private int _goalCount = 0;

    private static L1HauntedHouse _instance;

    public static L1HauntedHouse getInstance() {
        if (_instance == null) {
            _instance = new L1HauntedHouse();
        }
        return _instance;
    }

    public void addMember(final L1PcInstance pc) {
        if (!this._members.contains(pc)) {
            this._members.add(pc);
        }
        if ((this.getMembersCount() == 1)
                && (this.getHauntedHouseStatus() == STATUS_NONE)) {
            this.readyHauntedHouse();
        }
    }

    public void clearMembers() {
        this._members.clear();
    }

    public void endHauntedHouse() {
        this.setHauntedHouseStatus(STATUS_NONE);
        this.setWinnersCount(0);
        this.setGoalCount(0);
        for (final L1PcInstance pc : this.getMembersArray()) {
            if (pc.getMapId() == 5140) {
                final L1SkillUse l1skilluse = new L1SkillUse();
                l1skilluse.handleCommands(pc, CANCELLATION, pc.getId(),
                        pc.getX(), pc.getY(), null, 0, L1SkillUse.TYPE_LOGIN);
                L1Teleport.teleport(pc, 32624, 32813, (short) 4, 5, true);
            }
        }
        this.clearMembers();
        for (final L1Object object : L1World.getInstance().getObject()) {
            if (object instanceof L1DoorInstance) {
                final L1DoorInstance door = (L1DoorInstance) object;
                if (door.getMapId() == 5140) {
                    door.close();
                }
            }
        }
    }

    public int getGoalCount() {
        return this._goalCount;
    }

    public int getHauntedHouseStatus() {
        return this._hauntedHouseStatus;
    }

    public L1PcInstance[] getMembersArray() {
        return this._members.toArray(new L1PcInstance[this._members.size()]);
    }

    public int getMembersCount() {
        return this._members.size();
    }

    public int getWinnersCount() {
        return this._winnersCount;
    }

    public boolean isMember(final L1PcInstance pc) {
        return this._members.contains(pc);
    }

    private void readyHauntedHouse() {
        this.setHauntedHouseStatus(STATUS_READY);
        final L1HauntedHouseReadyTimer hhrTimer = new L1HauntedHouseReadyTimer();
        hhrTimer.begin();
    }

    public void removeMember(final L1PcInstance pc) {
        this._members.remove(pc);
    }

    public void removeRetiredMembers() {
        final L1PcInstance[] temp = this.getMembersArray();
        for (final L1PcInstance element : temp) {
            if (element.getMapId() != 5140) {
                this.removeMember(element);
            }
        }
    }

    public void sendMessage(final int type, final String msg) {
        for (final L1PcInstance pc : this.getMembersArray()) {
            pc.sendPackets(new S_ServerMessage(type, msg));
        }
    }

    public void setGoalCount(final int i) {
        this._goalCount = i;
    }

    private void setHauntedHouseStatus(final int i) {
        this._hauntedHouseStatus = i;
    }

    private void setWinnersCount(final int i) {
        this._winnersCount = i;
    }

    void startHauntedHouse() {
        this.setHauntedHouseStatus(STATUS_PLAYING);
        final int membersCount = this.getMembersCount();
        if (membersCount <= 4) {
            this.setWinnersCount(1);
        } else if ((5 >= membersCount) && (membersCount <= 7)) {
            this.setWinnersCount(2);
        } else if ((8 >= membersCount) && (membersCount <= 10)) {
            this.setWinnersCount(3);
        }
        for (final L1PcInstance pc : this.getMembersArray()) {
            final L1SkillUse l1skilluse = new L1SkillUse();
            l1skilluse.handleCommands(pc, CANCELLATION, pc.getId(), pc.getX(),
                    pc.getY(), null, 0, L1SkillUse.TYPE_LOGIN);
            L1PolyMorph.doPoly(pc, 6284, 300, L1PolyMorph.MORPH_BY_NPC);
        }

        for (final L1Object object : L1World.getInstance().getObject()) {
            if (object instanceof L1DoorInstance) {
                final L1DoorInstance door = (L1DoorInstance) object;
                if (door.getMapId() == 5140) {
                    door.open();
                }
            }
        }
    }

}
