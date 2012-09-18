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
 * @category 英美-英語<br>
 *           國際化的英文是Internationalization 因為單字中總共有18個字母，簡稱I18N，
 *           目的是讓應用程式可以應地區不同而顯示不同的訊息。
 */

public class messages_en_US extends ListResourceBundle {
    static final Object[][] contents = {
            { "com.lineage.memoryUse", "Used: " },
            { "com.lineage.memory", "MB of memory" },
            { "com.lineage.server.model.onGroundItem", "items on the ground" },
            { "com.lineage.server.model.seconds",
                    "will be delete after 10 seconds" },
            { "com.lineage.server.model.deleted", "was deleted" },
            { "com.lineage.server.GameServer.ver",
                    "Version: Lineage 3.5C  Dev. By L1J-TW For All User" },
            { "com.lineage.server.GameServer.settingslist",
                    "●●●●〈Server Config List〉●●●●" },
            { "com.lineage.server.GameServer.exp", "「exp」" },
            { "com.lineage.server.GameServer.x", "【times】" },
            { "com.lineage.server.GameServer.level", "【LV】" },
            { "com.lineage.server.GameServer.justice", "「justice」" },
            { "com.lineage.server.GameServer.karma", "「karma」" },
            { "com.lineage.server.GameServer.dropitems", "「dropitems」" },
            { "com.lineage.server.GameServer.dropadena", "「dropadena」" },
            { "com.lineage.server.GameServer.enchantweapon", "「enchantweapon」" },
            { "com.lineage.server.GameServer.enchantarmor", "「enchantarmor」" },
            { "com.lineage.server.GameServer.chatlevel", "「chatLevel」" },
            { "com.lineage.server.GameServer.nonpvp1",
                    "「Non-PvP」: Not Work (PvP)" },
            { "com.lineage.server.GameServer.nonpvp2",
                    "「Non-PvP」: Work (Non-PvP)" },
            { "com.lineage.server.GameServer.maxplayer",
                    "Max connection limit " },
            { "com.lineage.server.GameServer.player", " players" },
            { "com.lineage.server.GameServer.waitingforuser",
                    "Waiting for user's connection..." },
            { "com.lineage.server.GameServer.from", "from " },
            { "com.lineage.server.GameServer.attempt", " attempt to connect." },
            { "com.lineage.server.GameServer.setporton",
                    "Server is successfully set on port " },
            { "com.lineage.server.GameServer.initialfinished",
                    "Initialize finished.." } };

    @Override
    protected Object[][] getContents() {
        return contents;
    }

}
