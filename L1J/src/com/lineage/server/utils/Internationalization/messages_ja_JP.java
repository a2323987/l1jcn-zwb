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
package com.lineage.server.utils.Internationalization;

import java.util.ListResourceBundle;

/**
 * @category 日本-日本語<br>
 *           國際化的英文是Internationalization 因為單字中總共有18個字母，簡稱I18N，
 *           目的是讓應用程式可以應地區不同而顯示不同的訊息。
 */
public class messages_ja_JP extends ListResourceBundle {
    static final Object[][] contents = {
            { "com.lineage.memoryUse", "利用メモリ: " },
            { "com.lineage.memory", "MB" },
            { "com.lineage.server.model.onGroundItem", "ワールドマップ上のアイテム" },
            { "com.lineage.server.model.seconds", "10秒後に削除されます" },
            { "com.lineage.server.model.deleted", "削除されました" },
            { "com.lineage.server.GameServer.ver",
                    "バージョン: Lineage 3.5C 開発  By L1J For All User" },
            { "com.lineage.server.GameServer.settingslist", "●●●●〈サーバー設定〉●●●●" },
            { "com.lineage.server.GameServer.exp", "「経験値」" },
            { "com.lineage.server.GameServer.x", "【倍】" },
            { "com.lineage.server.GameServer.level", "" },
            { "com.lineage.server.GameServer.justice", "「アライメント」" },
            { "com.lineage.server.GameServer.karma", "「カルマ」" },
            { "com.lineage.server.GameServer.dropitems", "「ドロップ率」" },
            { "com.lineage.server.GameServer.dropadena", "「取得アデナ」" },
            { "com.lineage.server.GameServer.enchantweapon", "「武器エンチャント成功率」" },
            { "com.lineage.server.GameServer.enchantarmor", "「防具エンチャント成功率」" },
            { "com.lineage.server.GameServer.chatlevel", "「全体チャット可能Lv」" },
            { "com.lineage.server.GameServer.nonpvp1", "「Non-PvP設定」: 無効（PvP可能）" },
            { "com.lineage.server.GameServer.nonpvp2", "「Non-PvP設定」: 有効（PvP不可）" },
            { "com.lineage.server.GameServer.maxplayer", "接続人数制限： 最大 " },
            { "com.lineage.server.GameServer.player", " 人 " },
            { "com.lineage.server.GameServer.waitingforuser", "クライアント接続待機中..." },
            { "com.lineage.server.GameServer.from", "接続試行中IP " },
            { "com.lineage.server.GameServer.attempt", "" },
            { "com.lineage.server.GameServer.setporton",
                    "サーバーセッティング: サーバーソケット生成 " },
            { "com.lineage.server.GameServer.initialfinished", "ローディング完了" } };

    @Override
    protected Object[][] getContents() {
        return contents;
    }

}
