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
 * @category 中国 - 简体中文<br>
 *           国际化的英文是Internationalization 因为单字中总共有18个字母，简称I18N，
 *           目的是让应用程式可以应地区不同而显示不同的讯息。
 */
public class messages_zh_CN extends ListResourceBundle {
    static final Object[][] contents = {
            { "com.lineage.memoryUse", "使用了: " },
            { "com.lineage.memory", "MB 的记忆体" },
            { "com.lineage.server.model.onGroundItem", "地上的物品" },
            { "com.lineage.server.model.seconds", "10秒后将被清除" },
            { "com.lineage.server.model.deleted", "已经被清除了" },
            { "com.lineage.server.GameServer.ver",
                    "版本: Lineage 3.51C  开发 By L1J-CN-jrwz For All User" },
            { "com.lineage.server.GameServer.settingslist", "●●●●〈伺服器设置清单〉●●●●" },
            { "com.lineage.server.GameServer.exp", "「经验值」" },
            { "com.lineage.server.GameServer.x", "【倍】" },
            { "com.lineage.server.GameServer.level", "【级】" },
            { "com.lineage.server.GameServer.justice", "「正义值」" },
            { "com.lineage.server.GameServer.karma", "「友好度」" },
            { "com.lineage.server.GameServer.dropitems", "「物品掉落」" },
            { "com.lineage.server.GameServer.dropadena", "「金币掉落」" },
            { "com.lineage.server.GameServer.enchantweapon", "「冲武」" },
            { "com.lineage.server.GameServer.enchantarmor", "「冲防」" },
            { "com.lineage.server.GameServer.chatlevel", "「广播频道可用等级」" },
            { "com.lineage.server.GameServer.nonpvp1",
                    "「Non-PvP设定」: 【无效 (PvP可能)】" },
            { "com.lineage.server.GameServer.nonpvp2",
                    "「Non-PvP设定」: 【有效 (PvP不可)】" },
            { "com.lineage.server.GameServer.maxplayer", "连线人数上限为 " },
            { "com.lineage.server.GameServer.player", " 人 " },
            { "com.lineage.server.GameServer.waitingforuser", "等待客户端连接中..." },
            { "com.lineage.server.GameServer.from", "从 " },
            { "com.lineage.server.GameServer.attempt", " 试图连线" },
            { "com.lineage.server.GameServer.setporton", "伺服器成功建立在 port " },
            { "com.lineage.server.GameServer.initialfinished", "初始化完毕" } };

    @Override
    protected Object[][] getContents() {
        return contents;
    }

}
