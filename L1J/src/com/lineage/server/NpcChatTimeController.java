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
package com.lineage.server;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.datatables.NpcChatTable;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.templates.L1NpcChat;

/**
 * NPC聊天时间控制器
 */
public class NpcChatTimeController implements Runnable {

    private static Logger _log = Logger.getLogger(NpcChatTimeController.class
            .getName());

    private static NpcChatTimeController _instance;

    public static NpcChatTimeController getInstance() {
        if (_instance == null) {
            _instance = new NpcChatTimeController();
        }
        return _instance;
    }

    // 获得现实时间
    private static Calendar getRealTime() {
        final TimeZone _tz = TimeZone.getTimeZone(Config.TIME_ZONE);
        final Calendar cal = Calendar.getInstance(_tz);
        return cal;
    }

    // 检查NPC聊天时间
    private void checkNpcChatTime() {
        for (final L1NpcChat npcChat : NpcChatTable.getInstance()
                .getAllGameTime()) {
            if (this.isChatTime(npcChat.getGameTime())) {
                final int npcId = npcChat.getNpcId();
                for (final L1Object obj : L1World.getInstance().getObject()) {
                    if (!(obj instanceof L1NpcInstance)) {
                        continue;
                    }
                    final L1NpcInstance npc = (L1NpcInstance) obj;
                    if (npc.getNpcTemplate().get_npcId() == npcId) {
                        npc.startChat(L1NpcInstance.CHAT_TIMING_GAME_TIME);
                    }
                }
            }
        }
    }

    private boolean isChatTime(final int chatTime) {
        final SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        final Calendar realTime = getRealTime();
        final int nowTime = Integer.valueOf(sdf.format(realTime.getTime()));
        return (nowTime == chatTime);
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.checkNpcChatTime(); // 检查开始聊天时间
                Thread.sleep(60000);
            }
        } catch (final Exception e1) {
            _log.warning(e1.getMessage());
        }
    }

}
