package com.lineage.server.model.npc.actiontalk;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.npc.NpcExecutor;
import com.lineage.server.serverpackets.S_NPCTalkReturn;
import com.lineage.server.utils.Random;

/**
 * 新手村：预言者 - 50007
 * 
 * @author jrwz
 */
public class Journey implements NpcExecutor {

    private static Logger _log = Logger.getLogger(Journey.class.getName());

    private static byte _w;
    private static int _newX;
    private static int _newY;
    private static short _newMapId;

    public static NpcExecutor get() {
        return new Journey();
    }

    // 预言者
    private static void journey(final L1PcInstance pc) {
        final int r_w = Random.nextInt(14);
        int ghost = pc.getBeginGhost();
        final boolean flag = pc.isBeginGhostFlag();
        if (flag) {
            pc.setBeginGhostFlag(false);
            // flag = false;
            ghost = r_w;
        }
        switch (ghost) {
            case 0: // 肯特正义神殿
                _newX = 33127;
                _newY = 32940;
                _newMapId = 4;
                _w = 1;
                break;
            case 1: // 水晶洞穴
                _newX = 32774;
                _newY = 32909;
                _newMapId = 74;
                _w = 2;
                break;
            case 2: // 伊娃神殿
                _newX = 32732;
                _newY = 32814;
                _newMapId = 62;
                _w = 3;
                break;
            case 3: // 海底
                _newX = 32742;
                _newY = 32679;
                _newMapId = 63;
                _w = 4;
                break;
            case 4: // 象牙塔3
                _newX = 32771;
                _newY = 32797;
                _newMapId = 77;
                _w = 5;
                break;
            case 5: // 亚丁村
                _newX = 33937;
                _newY = 33345;
                _newMapId = 4;
                _w = 6;
                break;
            case 6: // 妖精森林大树下
                _newX = 33051;
                _newY = 32340;
                _newMapId = 4;
                _w = 7;
                break;
            case 7: // 沙漠水店
                _newX = 32870;
                _newY = 33255;
                _newMapId = 4;
                _w = 8;
                break;
            case 8: // 亚丁教堂
                _newX = 33972;
                _newY = 33363;
                _newMapId = 4;
                _w = 9;
                break;
            case 9: // 奇岩城
                _newX = 33429;
                _newY = 32813;
                _newMapId = 4;
                _w = 10;
                break;
            case 10: // 海音城
                _newX = 33597;
                _newY = 33239;
                _newMapId = 4;
                _w = 11;
                break;
            case 11: // 火山
                _newX = 33743;
                _newY = 32277;
                _newMapId = 4;
                _w = 12;
                break;
            case 12: // 说话岛码头
                _newX = 32643;
                _newY = 32954;
                _newMapId = 0;
                _w = 13;
                break;
            case 13: // 傲慢塔门口
                _newX = 34249;
                _newY = 33452;
                _newMapId = 4;
                _w = 14;
                break;
            case 14: // 古鲁丁邪恶神殿
                _newX = 32879;
                _newY = 32652;
                _newMapId = 4;
                _w = 0;
                break;
            default:
                break;
        }
        try {
            pc.save();
            pc.beginGhost(_newX, _newY, _newMapId, true);
            pc.setBeginGhost(_w);
        } catch (final Exception e) {
            // System.out.println(e.getLocalizedMessage());
            _log.severe(String.format("预言者发生错误 ", pc.getName()));
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    private Journey() {
    }

    @Override
    public void attack(final L1PcInstance pc, final L1NpcInstance npc) {
        // TODO Auto-generated method stub

    }

    @Override
    public L1PcInstance death(final L1Character lastAttacker,
            final L1NpcInstance npc) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void npcTalkAction(final L1PcInstance pc, final L1NpcInstance npc,
            final String s, final int objid) {

        if (s.equalsIgnoreCase("journey")) {
            journey(pc);
            try {
                Thread.sleep(20000); // 暂停二十秒 (滞留时间:20秒)
            } catch (final Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            pc.makeReadyEndGhost(); // 传回坐标
        }
    }

    @Override
    public void npcTalkReturn(final L1PcInstance pc, final L1NpcInstance npc) {
        // TODO Auto-generated method stub esmereld2
        if (pc.isGhost() || pc.isDead()) {
            return;
        }
        pc.sendPackets(new S_NPCTalkReturn(npc.getId(), "esmereld"));

    }

    @Override
    public void spawn(final L1NpcInstance npc) {
        // TODO Auto-generated method stub

    }

    @Override
    public int type() {
        // TODO Auto-generated method stub
        return 3;
    }

    @Override
    public void work(final L1NpcInstance npc) {
        // TODO Auto-generated method stub

    }

    @Override
    public int workTime() {
        // TODO Auto-generated method stub
        return 0;
    }

}
