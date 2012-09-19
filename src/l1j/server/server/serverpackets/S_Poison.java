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
package l1j.server.server.serverpackets;

import l1j.server.server.Opcodes;

// Referenced classes of package l1j.server.server.serverpackets:
// ServerBasePacket

public class S_Poison extends ServerBasePacket {

	/**
	 * キャラクターの外见を毒状态へ变更する际に送信するパケットを构筑する
	 * 
	 * @param objId
	 *            外见を变えるキャラクターのID
	 * @param type
	 *            外见のタイプ 0 = 通常色, 1 = 绿色, 2 = 灰色
	 */
	public S_Poison(int objId, int type) {
		writeC(Opcodes.S_OPCODE_POISON);
		writeD(objId);

		if (type == 0) { // 通常
			writeC(0);
			writeC(0);
		} else if (type == 1) { // 绿色
			writeC(1);
			writeC(0);
		} else if (type == 2) { // 灰色
			writeC(0);
			writeC(1);
		} else {
			throw new IllegalArgumentException("不正当的参数。type = " + type);
		}
	}

	@Override
	public byte[] getContent() {
		return getBytes();
	}

	@Override
	public String getType() {
		return S_POISON;
	}

	private static final String S_POISON = "[S] S_Poison";
}
