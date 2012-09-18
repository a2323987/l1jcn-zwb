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
package com.lineage.server.model;

import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_NpcChatPacket;
import com.lineage.server.templates.L1NpcChat;

/**
 * NPC聊天定时器
 */
public class L1NpcChatTimer extends TimerTask {

    private static final Logger _log = Logger.getLogger(L1NpcChatTimer.class
            .getName());

    private final L1NpcInstance _npc;

    private final L1NpcChat _npcChat;

    public L1NpcChatTimer(final L1NpcInstance npc, final L1NpcChat npcChat) {
        this._npc = npc;
        this._npcChat = npcChat;
    }

    private void chat(final L1NpcInstance npc, final int chatTiming,
            final String chatId, final boolean isShout,
            final boolean isWorldChat) {
        if ((chatTiming == L1NpcInstance.CHAT_TIMING_APPEARANCE)
                && npc.isDead()) {
            return;
        }
        if ((chatTiming == L1NpcInstance.CHAT_TIMING_DEAD) && !npc.isDead()) {
            return;
        }
        if ((chatTiming == L1NpcInstance.CHAT_TIMING_HIDE) && npc.isDead()) {
            return;
        }

        if (!isShout) {
            npc.broadcastPacket(new S_NpcChatPacket(npc, chatId, 0));
        } else {
            npc.wideBroadcastPacket(new S_NpcChatPacket(npc, chatId, 2));
        }

        if (isWorldChat) {
            // XXX npcはsendPacketsできないので、ワールド内のPCからsendPacketsさせる
            for (final L1PcInstance pc : L1World.getInstance().getAllPlayers()) {
                if (pc != null) {
                    pc.sendPackets(new S_NpcChatPacket(npc, chatId, 3));
                }
                break;
            }
        }
    }

    @Override
    public void run() {
        try {
            if ((this._npc == null) || (this._npcChat == null)) {
                return;
            }

            if ((this._npc.getHiddenStatus() != L1NpcInstance.HIDDEN_STATUS_NONE)
                    || this._npc._destroyed) {
                return;
            }

            final int chatTiming = this._npcChat.getChatTiming();
            final int chatInterval = this._npcChat.getChatInterval();
            final boolean isShout = this._npcChat.isShout();
            final boolean isWorldChat = this._npcChat.isWorldChat();
            final String chatId1 = this._npcChat.getChatId1();
            final String chatId2 = this._npcChat.getChatId2();
            final String chatId3 = this._npcChat.getChatId3();
            final String chatId4 = this._npcChat.getChatId4();
            final String chatId5 = this._npcChat.getChatId5();

            if (!chatId1.equals("")) {
                this.chat(this._npc, chatTiming, chatId1, isShout, isWorldChat);
            }

            if (!chatId2.equals("")) {
                Thread.sleep(chatInterval);
                this.chat(this._npc, chatTiming, chatId2, isShout, isWorldChat);
            }

            if (!chatId3.equals("")) {
                Thread.sleep(chatInterval);
                this.chat(this._npc, chatTiming, chatId3, isShout, isWorldChat);
            }

            if (!chatId4.equals("")) {
                Thread.sleep(chatInterval);
                this.chat(this._npc, chatTiming, chatId4, isShout, isWorldChat);
            }

            if (!chatId5.equals("")) {
                Thread.sleep(chatInterval);
                this.chat(this._npc, chatTiming, chatId5, isShout, isWorldChat);
            }
        } catch (final Throwable e) {
            _log.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }

}
