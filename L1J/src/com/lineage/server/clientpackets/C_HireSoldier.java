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

import com.lineage.server.ClientThread;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * TODO: 尚未实装雇用佣兵的处理 处理收到由客户端传来雇用佣兵的封包
 */
public class C_HireSoldier extends ClientBasePacket {

    private static final String C_HIRE_SOLDIER = "[C] C_HireSoldier";

    // S_HireSoldierを送ると表示される雇用ウィンドウでOKを押すとこのパケットが送られる
    @SuppressWarnings("unused")
    public C_HireSoldier(final byte[] decrypt, final ClientThread client) {
        super(decrypt);
        final int something1 = this.readH(); // S_HireSoldier封包の引数
        final int something2 = this.readH(); // S_HireSoldier封包の引数
        final int something3 = this.readD(); // 1以外入らない？
        final int something4 = this.readD(); // S_HireSoldier封包の引数
        final int number = this.readH(); // 雇用人数

        // < 佣兵雇用处理
    }

    @Override
    public String getType() {
        return C_HIRE_SOLDIER;
    }
}
