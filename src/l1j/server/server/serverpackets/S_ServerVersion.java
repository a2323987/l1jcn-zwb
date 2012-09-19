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

import l1j.server.Config;
import l1j.server.server.Opcodes;

public class S_ServerVersion extends ServerBasePacket {
	private static final int CLIENT_LANGUAGE = Config.CLIENT_LANGUAGE;

	public S_ServerVersion() {                
		writeC(Opcodes.S_OPCODE_SERVERVERSION);
		writeC(0x00);
		writeC(0x03);
		writeD(0x00b770fb); // 3.52TW server version
		writeD(0x00b76f63); // 3.52TW cache version
		writeD(0x77cf6eba); // 3.52TW auth version
		writeD(0x00b76a4e); // 3.52TW npc version
		writeD(0x00000000);
		writeC(0x00);
		writeC(0x00);
		writeC(CLIENT_LANGUAGE);
		writeD(0x00000040);
		writeD((int)(System.currentTimeMillis() / 1000));                
	}

	@Override
	public byte[] getContent() {
		return getBytes();
	}
}