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
package com.lineage.server;

import static com.lineage.server.model.identity.L1SystemMessageId.$647;
import static com.lineage.server.model.identity.L1SystemMessageId.$77;

import java.util.concurrent.ConcurrentHashMap;

import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.ServerBasePacket;

/**
 * Class <code>PacketCreate</code> 封包产生器.
 * 
 * @author jrwz
 * @version 2012-4-5上午01:35:21
 * @see com.lineage.server
 * @since JDK1.7
 */
public final class PacketCreate {
    /** 实例. */
    private static PacketCreate _instance;
    /** 公用封包集合. */
    private static ConcurrentHashMap<String, ServerBasePacket> packetMap;

    static {
        packetMap = new ConcurrentHashMap<String, ServerBasePacket>();

        packetMap.put("你觉得舒服多了", new S_ServerMessage($77));
        packetMap.put("这附近的能量影响到瞬间移动。在此地无法使用瞬间移动。", new S_ServerMessage($647));
    }

    /**
     * 实例为空时才产生一个.
     * 
     * @return 实例
     */
    public static PacketCreate get() {
        if (_instance == null) {
            _instance = new PacketCreate();
        }
        return _instance;
    }

    /** 封包产生器. */
    private PacketCreate() {
    }

    /**
     * 取得封包.
     * 
     * @param s
     *            要发送的封包名称
     * @return 集合内的封包名称
     */
    public ServerBasePacket getPacket(final String s) {
        return packetMap.get(s);
    }
}
