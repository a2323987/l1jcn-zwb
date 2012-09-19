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
package l1j.server.server.utils.Internationalization;

import java.util.ListResourceBundle;

/**
 * @category 日本-日本语<br>
 *           国际化的英文是Internationalization 因为单字中总共有18个字母，简称I18N，
 *           目的是让应用程式可以应地区不同而显示不同的讯息。
 */
public class messages_ja_JP extends ListResourceBundle {
	static final Object[][] contents = { 
		{ "l1j.server.memoryUse", "利用メモリ: " },
		{ "l1j.server.memory", "MB" },
		{ "l1j.server.server.model.onGroundItem", "ワールドマップ上のアイテム" },
		{ "l1j.server.server.model.seconds", "10秒后に削除されます" },
		{ "l1j.server.server.model.deleted", "削除されました" },
		{ "l1j.server.server.GameServer.ver","バージョン: Lineage 3.5C 开発  By L1J For All User" },
		{ "l1j.server.server.GameServer.settingslist","●●●●〈サーバー设定〉●●●●"},
		{ "l1j.server.server.GameServer.exp","“经验值”"},
		{ "l1j.server.server.GameServer.x","【倍】"},
		{ "l1j.server.server.GameServer.level",""},
		{ "l1j.server.server.GameServer.justice","“アライメント”"},
		{ "l1j.server.server.GameServer.karma","“カルマ”"},
		{ "l1j.server.server.GameServer.dropitems","“ドロップ率”"},
		{ "l1j.server.server.GameServer.dropadena","“取得アデナ”"},
		{ "l1j.server.server.GameServer.enchantweapon","“武器エンチャント成功率”"},
		{ "l1j.server.server.GameServer.enchantarmor","“防具エンチャント成功率”"},
		{ "l1j.server.server.GameServer.chatlevel","“全体チャット可能Lv”"},
		{ "l1j.server.server.GameServer.nonpvp1","“Non-PvP设定”: 无效（PvP可能）"},
		{ "l1j.server.server.GameServer.nonpvp2","“Non-PvP设定”: 有效（PvP不可）"},
		{ "l1j.server.server.GameServer.maxplayer","接续人数制限： 最大 "},
		{ "l1j.server.server.GameServer.player"," 人 "},
		{ "l1j.server.server.GameServer.waitingforuser","クライアント接续待机中..."},
		{ "l1j.server.server.GameServer.from","接续试行中IP "},
		{ "l1j.server.server.GameServer.attempt",""},
		{ "l1j.server.server.GameServer.setporton","サーバーセッティング: サーバーソケット生成 "},
		{ "l1j.server.server.GameServer.initialfinished","ローディング完了"}};

	@Override
	protected Object[][] getContents() {
		return contents;
	}

}
