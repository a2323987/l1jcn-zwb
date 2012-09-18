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
package com.lineage.server.utils;

import java.util.List;

import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.utils:
// FaceToFace

/**
 * 面向物件判断
 */
public class FaceToFace {

    /**
     * 面向物件判断
     * 
     * @param pc
     */
    public static L1PcInstance faceToFace(final L1PcInstance pc) {

        // X坐标
        final int pcX = pc.getX();
        // Y坐标
        final int pcY = pc.getY();
        // 面向
        final int pcHeading = pc.getHeading();
        final List<L1PcInstance> players = L1World.getInstance()
                .getVisiblePlayer(pc, 1);

        if (players.size() == 0) { // 1格内无物件 (PC)
            pc.sendPackets(new S_ServerMessage(93)); // \f1你注视的地方没有人。
            return null;
        }
        for (final L1PcInstance target : players) {
            final int targetX = target.getX();
            final int targetY = target.getY();
            final int targetHeading = target.getHeading();
            if ((pcHeading == 0) && (pcX == targetX) && (pcY == (targetY + 1))) {
                if (targetHeading == 4) {
                    return target;
                }
                pc.sendPackets(new S_ServerMessage(91, target.getName())); // \f1%0%s
                                                                           // 没有面对看你。
                return null;
            } else if ((pcHeading == 1) && (pcX == (targetX - 1))
                    && (pcY == (targetY + 1))) {
                if (targetHeading == 5) {
                    return target;
                }
                pc.sendPackets(new S_ServerMessage(91, target.getName())); // \f1%0%s
                                                                           // 没有面对看你。
                return null;
            } else if ((pcHeading == 2) && (pcX == (targetX - 1))
                    && (pcY == targetY)) {
                if (targetHeading == 6) {
                    return target;
                }
                pc.sendPackets(new S_ServerMessage(91, target.getName())); // \f1%0%s
                                                                           // 没有面对看你。
                return null;
            } else if ((pcHeading == 3) && (pcX == (targetX - 1))
                    && (pcY == (targetY - 1))) {
                if (targetHeading == 7) {
                    return target;
                }
                pc.sendPackets(new S_ServerMessage(91, target.getName())); // \f1%0%s
                                                                           // 没有面对看你。
                return null;
            } else if ((pcHeading == 4) && (pcX == targetX)
                    && (pcY == (targetY - 1))) {
                if (targetHeading == 0) {
                    return target;
                }
                pc.sendPackets(new S_ServerMessage(91, target.getName())); // \f1%0%s
                                                                           // 没有面对看你。
                return null;
            } else if ((pcHeading == 5) && (pcX == (targetX + 1))
                    && (pcY == (targetY - 1))) {
                if (targetHeading == 1) {
                    return target;
                }
                pc.sendPackets(new S_ServerMessage(91, target.getName())); // \f1%0%s
                                                                           // 没有面对看你。
                return null;
            } else if ((pcHeading == 6) && (pcX == (targetX + 1))
                    && (pcY == targetY)) {
                if (targetHeading == 2) {
                    return target;
                }
                pc.sendPackets(new S_ServerMessage(91, target.getName())); // \f1%0%s
                                                                           // 没有面对看你。
                return null;
            } else if ((pcHeading == 7) && (pcX == (targetX + 1))
                    && (pcY == (targetY + 1))) {
                if (targetHeading == 3) {
                    return target;
                }
                pc.sendPackets(new S_ServerMessage(91, target.getName())); // \f1%0%s
                                                                           // 没有面对看你。
                return null;
            }
        }
        pc.sendPackets(new S_ServerMessage(93)); // \f1你注视的地方没有人。
        return null;
    }

    private FaceToFace() {
    }
}
