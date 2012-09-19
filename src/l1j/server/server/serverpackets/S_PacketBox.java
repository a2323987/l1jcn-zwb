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

import java.util.Calendar;
import java.util.Iterator;
import java.util.TimeZone;

import l1j.server.Config;
import l1j.server.server.Account;
import l1j.server.server.Opcodes;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;

/**
 * スキルアイコンや遮断リストの表示など复数の用途に使われるパケットのクラス
 */
public class S_PacketBox extends ServerBasePacket {
	private static final String S_PACKETBOX = "[S] S_PacketBox";

	private byte[] _byte = null;

	// *** S_107 sub code list ***
	/** Updating */
	public static final int UPDATE_OLD_PART_MEMBER = 104;

	/** 3.3 组队系统(更新新加入的队员信息) */
	public static final int PATRY_UPDATE_MEMBER = 105;

	/** 3.3组队系统(委任新队长) */
	public static final int PATRY_SET_MASTER = 106;

	/** 3.3 组队系统(更新队伍信息,所有队员) */
	public static final int PATRY_MEMBERS = 110;

	// 1:Kent 2:Orc 3:WW 4:Giran 5:Heine 6:Dwarf 7:Aden 8:Diad 9:城名9 ...
	/** C(id) H(?): %sの攻城战が始まりました。 */
	public static final int MSG_WAR_BEGIN = 0;

	/** C(id) H(?): %sの攻城战が终了しました。 */
	public static final int MSG_WAR_END = 1;

	/** C(id) H(?): %sの攻城战が进行中です。 */
	public static final int MSG_WAR_GOING = 2;

	/** -: 城の主导权を握りました。 (音乐が变わる) */
	public static final int MSG_WAR_INITIATIVE = 3;

	/** -: 城を占据しました。 */
	public static final int MSG_WAR_OCCUPY = 4;

	/** ?: 决斗が终りました。 (音乐が变わる) */
	public static final int MSG_DUEL = 5;

	/** C(count): SMSの送信に失败しました。 / 全部で%d件送信されました。 */
	public static final int MSG_SMS_SENT = 6;

	/** -: 祝福の中、2人は夫妇として结ばれました。 (音乐が变わる) */
	public static final int MSG_MARRIED = 9;

	/** C(weight): 重量(30段阶) */
	public static final int WEIGHT = 10;

	/** C(food): 满腹度(30段阶) */
	public static final int FOOD = 11;

	/** C(0) C(level): このアイテムは%dレベル以下のみ使用できます。 (0~49以外は表示されない) */
	public static final int MSG_LEVEL_OVER = 12;

	/** UB情报HTML */
	public static final int HTML_UB = 14;

	/**
	 * C(id)<br>
	 * 1:身に迂められていた精灵の力が空气の中に溶けて行くのを感じました。<br>
	 * 2:体の隅々に火の精灵力が染みこんできます。<br>
	 * 3:体の隅々に水の精灵力が染みこんできます。<br>
	 * 4:体の隅々に风の精灵力が染みこんできます。<br>
	 * 5:体の隅々に地の精灵力が染みこんできます。<br>
	 */
	public static final int MSG_ELF = 15;

	/** C(count) S(name)...: 遮断リスト复数追加 */
	public static final int ADD_EXCLUDE2 = 17;

	/** S(name): 遮断リスト追加 */
	public static final int ADD_EXCLUDE = 18;

	/** S(name): 遮断解除 */
	public static final int REM_EXCLUDE = 19;

	/** スキルアイコン */
	public static final int ICONS1 = 20;

	/** スキルアイコン */
	public static final int ICONS2 = 21;

	/** オーラ系のスキルアイコン */
	public static final int ICON_AURA = 22;

	/** S(name): タウンリーダーに%sが选ばれました。 */
	public static final int MSG_TOWN_LEADER = 23;

	/**
	 * C(id): あなたのランクが%sに变更されました。<br>
	 * id - 1:见习い 2:一般 3:ガーディアン
	 */
	public static final int MSG_RANK_CHANGED = 27;

	/** D(?) S(name) S(clanname): %s血盟の%sがラスタバド军を退けました。 */
	public static final int MSG_WIN_LASTAVARD = 30;

	/** -: \f1气分が良くなりました。 */
	public static final int MSG_FEEL_GOOD = 31;

	/** 不明。C_30パケットが飞ぶ */
	public static final int SOMETHING1 = 33;

	/** H(time): ブルーポーションのアイコンが表示される。 */
	public static final int ICON_BLUEPOTION = 34;

	/** H(time): 变身のアイコンが表示される。 */
	public static final int ICON_POLYMORPH = 35;

	/** H(time): チャット禁止のアイコンが表示される。 */
	public static final int ICON_CHATBAN = 36;

	/** 不明。C_7パケットが飞ぶ。C_7はペットのメニューを开いたときにも飞ぶ。 */
	public static final int SOMETHING2 = 37;

	/** 血盟情报のHTMLが表示される */
	public static final int HTML_CLAN1 = 38;

	/** H(time): イミュのアイコンが表示される */
	public static final int ICON_I2H = 40;

	/** キャラクターのゲームオプション、ショートカット情报などを送る */
	public static final int CHARACTER_CONFIG = 41;

	/** キャラクター选択画面に戻る */
	public static final int LOGOUT = 42;

	/** 战斗中に再始动することはできません。 */
	public static final int MSG_CANT_LOGOUT = 43;

	/**
	 * C(count) D(time) S(name) S(info):<br>
	 * [CALL] ボタンのついたウィンドウが表示される。これはBOTなどの不正者チェックに
	 * 使われる机能らしい。名前をダブルクリックするとC_RequestWhoが飞び、クライアントの
	 * フォルダにbot_list.txtが生成される。名前を选択して+キーを押すと新しいウィンドウが开く。
	 */
	public static final int CALL_SOMETHING = 45;

	/**
	 * C(id): バトル コロシアム、カオス大战がー<br>
	 * id - 1:开始します 2:取り消されました 3:终了します
	 */
	public static final int MSG_COLOSSEUM = 49;

	/** 血盟情报のHTML */
	public static final int HTML_CLAN2 = 51;

	/** 料理ウィンドウを开く */
	public static final int COOK_WINDOW = 52;

	/** C(type) H(time): 料理アイコンが表示される */
	public static final int ICON_COOKING = 53;

	/** 鱼がかかったグラフィックが表示される */
	public static final int FISHING = 55;

	/** 魔法娃娃状态图示*/
	public static final int ICON_MAGIC_DOLL = 56;
	
	/** 殷海萨的祝福*/
	public static final int ICON_EINHASAD = 82;

	public S_PacketBox(int subCode) {
		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);

		switch (subCode) {
			case MSG_WAR_INITIATIVE:
			case MSG_WAR_OCCUPY:
			case MSG_MARRIED:
			case MSG_FEEL_GOOD:
			case MSG_CANT_LOGOUT:
			case LOGOUT:
			case FISHING:
				break;
			case CALL_SOMETHING:
				callSomething();
			default:
				break;
		}
	}

	public S_PacketBox(int subCode, int value) {
		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);

		switch (subCode) {
			case ICON_BLUEPOTION:
			case ICON_CHATBAN:
			case ICON_I2H:
			case ICON_POLYMORPH:
				writeH(value); // time
				break;
			case MSG_WAR_BEGIN:
			case MSG_WAR_END:
			case MSG_WAR_GOING:
				writeC(value); // castle id
				writeH(0); // ?
				break;
			case MSG_SMS_SENT:
			case WEIGHT:
			case FOOD:
				writeC(value);
				break;
			case MSG_ELF: // 忽然全身充满了%s的灵力。
			case MSG_RANK_CHANGED: // 你的阶级变更为%s
			case MSG_COLOSSEUM: // 大圆形竞技场，混沌的大战开始！结束！取消！
				writeC(value); // msg id
				writeC(0);
				break;
			case MSG_LEVEL_OVER:
				writeC(0); // ?
				writeC(value); // 0-49以外は表示されない
				break;
			case COOK_WINDOW:
				writeC(0xdb); // ?
				writeC(0x31);
				writeC(0xdf);
				writeC(0x02);
				writeC(0x01);
				writeC(value); // level
				break;
			case ICON_EINHASAD: 	// 殷海萨的祝福
				writeC(value); // %值为0 ~ 200
				break;
			case 88: // + 闪避率
				writeC(value);
				writeC(0x00);
				break;
			case 101: // - 闪避率
				writeC(value);
				break;
			case 21: // 状态图示
				writeC(0x00);
				writeC(0x00);
				writeC(0x00);
				writeC(value); // 闪避图示 (幻术:镜像、黑妖:闇影闪避)
				break;
			default:
				break;
		}
	}

	public S_PacketBox(int subCode, int type, int time) {
		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);

		switch (subCode) {
			case ICON_COOKING:
				if (type == 54) { // 象牙塔妙药
					writeC(0x12);
					writeC(0x0c);
					writeC(0x0c);
					writeC(0x07);
					writeC(0x12);
					writeC(0x08);
					writeH(0x0000); // 饱和度 值:2000，饱和度100%
					writeC(type); // 类型
					writeC(0x2a);
					writeH(time); // 时间
					writeC(0x0); // 负重度 值:242，负重度100%
				} else if (type != 7) {
					writeC(0x12);
					writeC(0x0b);
					writeC(0x0c);
					writeC(0x0b);
					writeC(0x0f);
					writeC(0x08);
					writeH(0x0000); // 饱和度 值:2000，饱和度100%
					writeC(type); // 类型
					writeC(0x24);
					writeH(time); // 时间
					writeC(0x00); // 负重度 值:242，负重度100%
				} else {
					writeC(0x12);
					writeC(0x0b);
					writeC(0x0c);
					writeC(0x0b);
					writeC(0x0f);
					writeC(0x08);
					writeH(0x0000); // 饱和度 值:2000，饱和度100%
					writeC(type); // 类型
					writeC(0x26);
					writeH(time); // 时间
					writeC(0x00); // 负重度 值:240，负重度100%
				}
				break;
			case MSG_DUEL:
				writeD(type); // 相手のオブジェクトID
				writeD(time); // 自分のオブジェクトID
				break;
			case ICON_MAGIC_DOLL:
				if (type == 32) { // 爱心图示
					writeH(time);
					writeC(type);
					writeC(12);
				} else { // 魔法娃娃图示
					writeH(time);
					writeC(0);
					writeC(0);
				}
				break;
			default:
				break;
		}
	}

	public S_PacketBox(int subCode, String name) {
		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);

		switch (subCode) {
			case ADD_EXCLUDE:
			case REM_EXCLUDE:
			case MSG_TOWN_LEADER:
				writeS(name);
				break;
			default:
				break;
		}
	}

	public S_PacketBox(int subCode, int id, String name, String clanName) {
		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);

		switch (subCode) {
			case MSG_WIN_LASTAVARD:
				writeD(id); // クランIDか何か？
				writeS(name);
				writeS(clanName);
				break;
			default:
				break;
		}
	}

	public S_PacketBox(int subCode, Object[] names) {
		writeC(Opcodes.S_OPCODE_PACKETBOX);
		writeC(subCode);

		switch (subCode) {
			case ADD_EXCLUDE2:
				writeC(names.length);
				for (Object name : names) {
					writeS(name.toString());
				}
				break;
			default:
				break;
		}
	}

	private void callSomething() {
		Iterator<L1PcInstance> itr = L1World.getInstance().getAllPlayers().iterator();

		writeC(L1World.getInstance().getAllPlayers().size());

		while (itr.hasNext()) {
			L1PcInstance pc = itr.next();
			Account acc = Account.load(pc.getAccountName());

			// 时间情报 とりあえずログイン时间を入れてみる
			if (acc == null) {
				writeD(0);
			}
			else {
				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(Config.TIME_ZONE));
				long lastactive = acc.getLastActive().getTime();
				cal.setTimeInMillis(lastactive);
				cal.set(Calendar.YEAR, 1970);
				int time = (int) (cal.getTimeInMillis() / 1000);
				writeD(time); // JST 1970 1/1 09:00 が基准
			}

			// キャラ情报
			writeS(pc.getName()); // 半角12字まで
			writeS(pc.getClanname()); // []内に表示される文字列。半角12字まで
		}
	}

	@Override
	public byte[] getContent() {
		if (_byte == null) {
			_byte = getBytes();
		}

		return _byte;
	}

	@Override
	public String getType() {
		return S_PACKETBOX;
	}
}
