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

import static com.lineage.server.model.Instance.L1PcInstance.REGENSTATE_MOVE;
import static com.lineage.server.model.skill.L1SkillId.ABSOLUTE_BARRIER;
import static com.lineage.server.model.skill.L1SkillId.MEDITATION;

import com.lineage.Config;
import com.lineage.server.ClientThread;
import com.lineage.server.model.AcceleratorChecker;
import com.lineage.server.model.Dungeon;
import com.lineage.server.model.DungeonRandom;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.trap.L1WorldTraps;
import com.lineage.server.serverpackets.S_MoveCharPacket;
import com.lineage.server.serverpackets.S_SystemMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来移动角色的封包
 */
public class C_MoveChar extends ClientBasePacket {

    private static final byte HEADING_TABLE_X[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

    private static final byte HEADING_TABLE_Y[] = { -1, -1, 0, 1, 1, 1, 0, -1 };

    private static final int CLIENT_LANGUAGE = Config.CLIENT_LANGUAGE;

    // 移动
    public C_MoveChar(final byte decrypt[], final ClientThread client)
            throws Exception {
        super(decrypt);
        int locx = this.readH();
        int locy = this.readH();
        int heading = this.readC();

        final L1PcInstance pc = client.getActiveChar();

        if ((pc == null) || pc.isTeleport()) { // 传送中
            return;
        }

        // 检查移动的时间间隔
        if (Config.CHECK_MOVE_INTERVAL) {
            int result;
            result = pc.getAcceleratorChecker().checkInterval(
                    AcceleratorChecker.ACT_TYPE.MOVE);
            if (result == AcceleratorChecker.R_DISPOSED) {
                return;
            }
        }

        if (pc.hasSkillEffect(MEDITATION)) { // 取消冥想效果
            pc.removeSkillEffect(MEDITATION);
        }
        pc.setCallClanId(0); // 移动后呼叫血盟成员无效

        if (!pc.hasSkillEffect(ABSOLUTE_BARRIER)) { // 绝对屏障
            pc.setRegenState(REGENSTATE_MOVE);
        }
        pc.getMap().setPassable(pc.getLocation(), true);

        //if (CLIENT_LANGUAGE == 3) { // Taiwan Only
        if (heading > 8) { // Taiwan Only
            heading ^= 0x49;
            locx = pc.getX();
            locy = pc.getY();
        }

        locx += HEADING_TABLE_X[heading];
        locy += HEADING_TABLE_Y[heading];

        if (Dungeon.getInstance().dg(locx, locy, pc.getMap().getId(), pc)) { // 传点
            return;
        }
        if (DungeonRandom.getInstance().dg(locx, locy, pc.getMap().getId(), pc)) { // 取得随机传送地点
            return;
        }

        pc.getLocation().set(locx, locy);
        pc.setHeading(heading);
        if (pc.isGmInvis() || pc.isGhost()) {
        } else if (pc.isInvisble()) {
            pc.broadcastPacketForFindInvis(new S_MoveCharPacket(pc), true);
        } else {
            pc.broadcastPacket(new S_MoveCharPacket(pc));
        }

        // sendMapTileLog(pc); //发送信息的目的地瓦（为调查地图）
        // 宠物竞速-判断圈数
        com.lineage.server.model.game.L1PolyRace.getInstance().checkLapFinish(
                pc);
        L1WorldTraps.getInstance().onPlayerMoved(pc);

        pc.getMap().setPassable(pc.getLocation(), false);
        // user.UpdateObject(); // 可视范围内の全オブジェクト更新
    }

    // 地图编号的研究
    @SuppressWarnings("unused")
    private void sendMapTileLog(final L1PcInstance pc) {
        pc.sendPackets(new S_SystemMessage(pc.getMap().toString(
                pc.getLocation())));
    }
}
