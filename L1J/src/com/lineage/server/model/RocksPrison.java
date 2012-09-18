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
 * MAY BE CONSIDERED TO BE A CONTRACT,
 * THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package com.lineage.server.model;

import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * Class <code>RocksPrison</code> 奇岩地监计时器.
 * 
 * @author jrwz
 * @version 2012-3-27上午01:29:05
 * @see com.lineage.server.model
 * @since JDK1.7
 */
public final class RocksPrison extends TimerTask {

    /** 提示信息. */
    private static final Logger LOG = Logger.getLogger(RocksPrison.class
            .getName());
    private final L1PcInstance _pc;

    /**
     * 奇岩地监计时器.
     * 
     * @param pc
     *            角色
     */
    public RocksPrison(final L1PcInstance pc) {
        this._pc = pc;
    }

    /**
     * 时间提示讯息.
     */
    private void message() {
        final int time = this._pc.getRocksPrisonTime();
        switch (time) {
            case 3600:
                final S_ServerMessage msgA = new S_ServerMessage(1526,
                        String.valueOf(2));
                this._pc.sendPackets(msgA); // 地监逗留时间剩下了%0小时
                break;

            case 7200:
                final S_ServerMessage msgB = new S_ServerMessage(1526,
                        String.valueOf(1));
                this._pc.sendPackets(msgB); // 地监逗留时间剩下了%0小时
                break;

            case 9000:
                final S_ServerMessage msgC = new S_ServerMessage(1527,
                        String.valueOf(30));
                this._pc.sendPackets(msgC); // 地监逗留时间剩下了%0分钟
                break;

            case 10500:
                final S_ServerMessage msgD = new S_ServerMessage(1527,
                        String.valueOf(5));
                this._pc.sendPackets(msgD); // 地监逗留时间剩下了%0分钟
                break;

            case 10560:
                final S_ServerMessage msgE = new S_ServerMessage(1527,
                        String.valueOf(4));
                this._pc.sendPackets(msgE); // 地监逗留时间剩下了%0分钟
                break;

            case 10620:
                final S_ServerMessage msgF = new S_ServerMessage(1527,
                        String.valueOf(3));
                this._pc.sendPackets(msgF); // 地监逗留时间剩下了%0分钟
                break;

            case 10680:
                final S_ServerMessage msgG = new S_ServerMessage(1527,
                        String.valueOf(2));
                this._pc.sendPackets(msgG); // 地监逗留时间剩下了%0分钟
                break;

            case 10740:
                final S_ServerMessage msgH = new S_ServerMessage(1527,
                        String.valueOf(1));
                this._pc.sendPackets(msgH); // 地监逗留时间剩下了%0分钟
                break;

            case 10791:
                final S_ServerMessage msgI = new S_ServerMessage(1528,
                        String.valueOf(10));
                this._pc.sendPackets(msgI); // 地监逗留时间剩下了%0秒
                break;

            case 10792:
                final S_ServerMessage msgJ = new S_ServerMessage(1528,
                        String.valueOf(9));
                this._pc.sendPackets(msgJ); // 地监逗留时间剩下了%0秒
                break;

            case 10793:
                final S_ServerMessage msgK = new S_ServerMessage(1528,
                        String.valueOf(8));
                this._pc.sendPackets(msgK); // 地监逗留时间剩下了%0秒
                break;

            case 10794:
                final S_ServerMessage msgL = new S_ServerMessage(1528,
                        String.valueOf(7));
                this._pc.sendPackets(msgL); // 地监逗留时间剩下了%0秒
                break;

            case 10795:
                final S_ServerMessage msgM = new S_ServerMessage(1528,
                        String.valueOf(6));
                this._pc.sendPackets(msgM); // 地监逗留时间剩下了%0秒
                break;

            case 10796:
                final S_ServerMessage msgN = new S_ServerMessage(1528,
                        String.valueOf(5));
                this._pc.sendPackets(msgN); // 地监逗留时间剩下了%0秒
                break;

            case 10797:
                final S_ServerMessage msgO = new S_ServerMessage(1528,
                        String.valueOf(4));
                this._pc.sendPackets(msgO); // 地监逗留时间剩下了%0秒
                break;

            case 10798:
                final S_ServerMessage msgP = new S_ServerMessage(1528,
                        String.valueOf(3));
                this._pc.sendPackets(msgP); // 地监逗留时间剩下了%0秒
                break;

            case 10799:
                final S_ServerMessage msgQ = new S_ServerMessage(1528,
                        String.valueOf(2));
                this._pc.sendPackets(msgQ); // 地监逗留时间剩下了%0秒
                break;

            case 10800:
                final S_ServerMessage msgR = new S_ServerMessage(1528,
                        String.valueOf(1));
                this._pc.sendPackets(msgR); // 地监逗留时间剩下了%0秒
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        // System.out.println("开始计时");
        try {
            if (this._pc.isDead()) {
                return;
            }

            final short map = this._pc.getMapId();
            if (!(map == 53) || (map == 54) || (map == 55) || (map == 56)) {
                this._pc.stopRocksPrison();
                // System.out.println("停止计时、地图编号不对");
                return;
            }

            final int time = this._pc.getRocksPrisonTime();
            final int maxTime = 10800; // 3小时
            if (time >= maxTime) {
                this._pc.stopRocksPrison();
                // System.out.println("停止计时、时间不对");
                this.teleport();
                return;
            }

            this._pc.setRocksPrisonTime(time + 1);
            this.message();
            // System.out.println("增加时间");
        } catch (final Throwable e) {
            LOG.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }

    /**
     * 传送.
     */
    private void teleport() {
        final short map = this._pc.getMapId();
        if ((map == 53) || (map == 54) || (map == 55) || (map == 56)) {
            // this._pc.stopRocksPrison();
            final int locx = 33427;
            final int locy = 32822;
            final short mapid = 4;
            final int heading = 5;
            L1Teleport.teleport(this._pc, locx, locy, mapid, heading, true);
        }
    }

}
