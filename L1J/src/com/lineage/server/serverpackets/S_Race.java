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
package com.lineage.server.serverpackets;

import javolution.util.FastTable;

import com.lineage.server.Opcodes;
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 屬於PacketBox的封包 只是抓出來另外寫 GameStart 進入賽跑的畫面 GameEnd 離開賽跑的畫面
 */
public class S_Race extends ServerBasePacket {
    private static final String S_RACE = "[S] S_Race";

    private byte[] _byte = null;

    public static final int GameStart = 0x40;
    public static final int CountDown = 0x41;
    public static final int PlayerInfo = 0x42;
    public static final int Lap = 0x43;
    public static final int Winner = 0x44;
    public static final int GameOver = 0x45;
    public static final int GameEnd = 0x46;

    public S_Race(final FastTable<L1PcInstance> playerList,
            final L1PcInstance pc) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(PlayerInfo);
        this.writeH(playerList.size()); // 參賽者人數
        this.writeH(playerList.indexOf(pc)); // 名次
        for (final L1PcInstance player : playerList) {
            if (player == null) {
                continue;
            }
            this.writeS(player.getName());
        }
    }

    // GameStart// CountDown// GameOver// GameEnd
    public S_Race(final int type) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(type);
        if (type == GameStart) {
            this.writeC(0x05); // 倒數5秒
        }
    }

    public S_Race(final int maxLap, final int lap) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(Lap);
        this.writeH(maxLap); // 最大圈數
        this.writeH(lap); // 目前圈數
    }

    public S_Race(final String winnerName, final int time) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(Winner);
        this.writeS(winnerName);
        this.writeD(time * 1000);
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this.getBytes();
        }
        return this._byte;
    }

    @Override
    public String getType() {
        return S_RACE;
    }
}
