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
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_WhoAmount;
import l1j.server.server.serverpackets.S_WhoCharinfo;
import l1j.server.server.serverpackets.S_SystemMessage; // 玩家或GM可线上查询伺服器设定(/who)  by andy52005 1/2
import l1j.plugin.L1GameRestart; // 查询伺服器重新启动时间 by by elfooxx 1/2
import l1j.server.server.serverpackets.S_PacketBox; // 线上人物资料
import l1j.server.server.utils.Random; // 浮动人数

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来查询线上人数的封包
 */
public class C_Who extends ClientBasePacket {

	private static final String C_WHO = "[C] C_Who";

	public C_Who(byte[] decrypt, ClientThread client) {
		super(decrypt);
		
		L1PcInstance pc = client.getActiveChar();
		if (pc == null) {
			return;
		}
		
		String s = readS();
		L1PcInstance find = L1World.getInstance().getPlayer(s);
		

		if (find != null) {
			S_WhoCharinfo s_whocharinfo = new S_WhoCharinfo(find);
			pc.sendPackets(s_whocharinfo);
		} else {
			if (Config.ALT_WHO_COMMAND) {
				String amount = "";	
				if (Config.PP_Is_Active){
					amount = String.valueOf(L1World.getInstance()
							.getAllPlayers().size()+ Config.Phantom_Population + Random.nextInt(Config.Phantom_Population_Random, 1));
				} else {
					amount = String.valueOf(L1World.getInstance()
							.getAllPlayers().size());
				}
				S_WhoAmount s_whoamount = new S_WhoAmount(amount);
				pc.sendPackets(s_whoamount);
				// 是否开放线上查询伺服器配置资讯
				if (Config.OL_ENQUIRE_RATES) {
					pc.sendPackets(new S_SystemMessage("\\fV＊＊㊣＊＊◎《 伺服器配置资讯 》◎＊＊㊣＊＊\n" +
					"【经验值】" + Config.RATE_XP	+ " 倍 【正义值】" + Config.RATE_LA + " 倍\n" +
					"【友好度】" + Config.RATE_KARMA + " 倍 【角色负重】" + Config.RATE_WEIGHT_LIMIT + " 倍\n" +
					"【掉宝率】" + Config.RATE_DROP_ITEMS + " 倍 【取得金币】" + Config.RATE_DROP_ADENA + " 倍\n" +
					"【宠物负重】" + Config.RATE_WEIGHT_LIMIT_PET + " 倍 【商店价格】" + Config.RATE_SHOP_SELLING_PRICE + " 倍\n" +
					"【冲武器】" + Config.ENCHANT_CHANCE_WEAPON + " % 【冲防具】" + Config.ENCHANT_CHANCE_ARMOR + " %\n" +
					"【属性强化】" + Config.ATTR_ENCHANT_CHANCE + " % 【饰品强化】+0(70%) ~ +9(25%)\n" +
					"【附魔石转换】" + Config.MAGIC_STONE_TYPE + " % 【附魔石强化】" + Config.MAGIC_STONE_LEVEL + " %\n"));
					// 查询伺服器重新启动时间 by by elfooxx 2/2
					int second = L1GameRestart.getInstance().GetRemnant();
					pc.sendPackets(new S_SystemMessage("\\fV★ 距离伺服器重开时间还有 "
							+ (second / 60) / 60 + " 小时 " + (second / 60) % 60
							+ " 分 " + second % 60 + " 秒 ★"));
					// end
				}
				// end
				// 查询线上人物资料(/who) by hunter_dny
				if (Config.WHO_ONLINE_LIST) {
					StringBuffer buf = new StringBuffer();
					int i = 1;
					for (L1PcInstance each : L1World.getInstance()
							.getAllPlayers()) {
						buf.append(i + "- [" + each.getName() + "] - "
								+ each.getClanname() + "\n");
						i++;
					}
					if (buf.length() > 0) {
						if (pc.isGm()) {
							pc.sendPackets(new S_PacketBox(
									S_PacketBox.CALL_SOMETHING));
						} else {
							pc.sendPackets(new S_SystemMessage("\\fV＊＊㊣＊＊◎《 线上角色资料  》◎＊＊㊣＊＊"));
							pc.sendPackets(new S_SystemMessage(buf.toString()));
						}
					}
				}
				// end
			}
			// TODO: ChrisLiu: SystemMessage 109
			// 显示消息如果目标是不存在？正方修知道，谢谢你。
		}
	}

	@Override
	public String getType() {
		return C_WHO;
	}
}
