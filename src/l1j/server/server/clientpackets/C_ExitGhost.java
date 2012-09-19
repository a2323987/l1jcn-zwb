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
package l1j.server.server.clientpackets;

import l1j.server.server.ClientThread;
import l1j.server.server.model.Instance.L1PcInstance;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来结束 ghost 状态的封包
 */
public class C_ExitGhost extends ClientBasePacket {

	private static final String C_EXIT_GHOST = "[C] C_ExitGhost";

	// 移动
	public C_ExitGhost(byte decrypt[], ClientThread client) throws Exception {
		super(decrypt);

		L1PcInstance pc = client.getActiveChar();
		if ((pc == null) ||(!pc.isGhost())) {
			return;
		}

		pc.makeReadyEndGhost();
	}

	@Override
	public String getType() {
		return C_EXIT_GHOST;
	}
}