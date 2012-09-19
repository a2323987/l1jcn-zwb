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

import l1j.server.Config;
import l1j.server.server.ClientThread;
import l1j.server.server.Opcodes;
import l1j.server.server.datatables.ChatLogTable;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_ChatPacket;
import l1j.server.server.serverpackets.S_ServerMessage;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来的密语封包
 */
public class C_ChatWhisper extends ClientBasePacket {

	private static final String C_CHAT_WHISPER = "[C] C_ChatWhisper";

	public C_ChatWhisper(byte abyte0[], ClientThread client) throws Exception {
		super(abyte0);
		
		L1PcInstance whisperFrom = client.getActiveChar();
		if (whisperFrom == null) {
			return;
		}
		
		String targetName = readS();
		String text = readS();
		
		// 被魔封
		if (whisperFrom.hasSkillEffect(1005)) {
			whisperFrom.sendPackets(new S_ServerMessage(242)); // 你从现在被禁止闲谈。
			return;
		}
		// 等级不够
		if (whisperFrom.getLevel() < Config.WHISPER_CHAT_LEVEL) {
			whisperFrom.sendPackets(new S_ServerMessage(404, String.valueOf(Config.WHISPER_CHAT_LEVEL))); // 等级
																											// %0
																											// 以下无法使用密谈。
			return;
		}
		L1PcInstance whisperTo = L1World.getInstance().getPlayer(targetName);
		// 密语对象不存在
		if (whisperTo == null) {
			whisperFrom.sendPackets(new S_ServerMessage(73, targetName)); // \f1%0%d
																			// 不在线上。
			return;
		}
		// 自己跟自己说话
		if (whisperTo.equals(whisperFrom)) {
			return;
		}
		// 断绝密语
		if (whisperTo.getExcludingList().contains(whisperFrom.getName())) {
			whisperFrom.sendPackets(new S_ServerMessage(117, whisperTo.getName())); // %0%s
																					// 断绝你的密语。
			return;
		}
		// 关闭密语
		if (!whisperTo.isCanWhisper()) {
			whisperFrom.sendPackets(new S_ServerMessage(205, whisperTo.getName())); // \f1%0%d
																					// 目前关闭悄悄话。
			return;
		}
		//add GUI by Eric
		if(Config.GUI)
			l1j.gui.Eric_J_Main.getInstance().addPrivateChat(whisperFrom.getName(),whisperTo.getName(), text);//GUI
		//end
		ChatLogTable.getInstance().storeChat(whisperFrom, whisperTo, text, 1);
		whisperFrom.sendPackets(new S_ChatPacket(whisperTo, text, Opcodes.S_OPCODE_GLOBALCHAT, 9));
		whisperTo.sendPackets(new S_ChatPacket(whisperFrom, text, Opcodes.S_OPCODE_WHISPERCHAT, 16));
	}

	@Override
	public String getType() {
		return C_CHAT_WHISPER;
	}
}
