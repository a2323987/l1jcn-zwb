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
package com.lineage.server.datatables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.templates.L1NpcChat;
import com.lineage.server.utils.SQLUtil;
import com.lineage.server.utils.collections.Maps;

/**
 * NPC喊话资料表
 */
public class NpcChatTable {

    private static Logger _log = Logger.getLogger(NpcChatTable.class.getName());

    private static NpcChatTable _instance;

    public static NpcChatTable getInstance() {
        if (_instance == null) {
            _instance = new NpcChatTable();
        }
        return _instance;
    }

    private final Map<Integer, L1NpcChat> _npcChatAppearance = Maps.newMap();

    private final Map<Integer, L1NpcChat> _npcChatDead = Maps.newMap();

    private final Map<Integer, L1NpcChat> _npcChatHide = Maps.newMap();

    private final Map<Integer, L1NpcChat> _npcChatGameTime = Maps.newMap();

    private NpcChatTable() {
        this.FillNpcChatTable();
    }

    private void FillNpcChatTable() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con.prepareStatement("SELECT * FROM npcchat");
            rs = pstm.executeQuery();
            while (rs.next()) {
                final L1NpcChat npcChat = new L1NpcChat();
                npcChat.setNpcId(rs.getInt("npc_id"));
                npcChat.setChatTiming(rs.getInt("chat_timing"));
                npcChat.setStartDelayTime(rs.getInt("start_delay_time"));
                npcChat.setChatId1(rs.getString("chat_id1"));
                npcChat.setChatId2(rs.getString("chat_id2"));
                npcChat.setChatId3(rs.getString("chat_id3"));
                npcChat.setChatId4(rs.getString("chat_id4"));
                npcChat.setChatId5(rs.getString("chat_id5"));
                npcChat.setChatInterval(rs.getInt("chat_interval"));
                npcChat.setShout(rs.getBoolean("is_shout"));
                npcChat.setWorldChat(rs.getBoolean("is_world_chat"));
                npcChat.setRepeat(rs.getBoolean("is_repeat"));
                npcChat.setRepeatInterval(rs.getInt("repeat_interval"));
                npcChat.setGameTime(rs.getInt("game_time"));

                if (npcChat.getChatTiming() == L1NpcInstance.CHAT_TIMING_APPEARANCE) {
                    this._npcChatAppearance.put(
                            new Integer(npcChat.getNpcId()), npcChat);
                } else if (npcChat.getChatTiming() == L1NpcInstance.CHAT_TIMING_DEAD) {
                    this._npcChatDead.put(new Integer(npcChat.getNpcId()),
                            npcChat);
                } else if (npcChat.getChatTiming() == L1NpcInstance.CHAT_TIMING_HIDE) {
                    this._npcChatHide.put(new Integer(npcChat.getNpcId()),
                            npcChat);
                } else if (npcChat.getChatTiming() == L1NpcInstance.CHAT_TIMING_GAME_TIME) {
                    this._npcChatGameTime.put(new Integer(npcChat.getNpcId()),
                            npcChat);
                }
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }
    }

    /**
     * 取得全部NPC喊话时间
     * 
     * @return
     */
    public L1NpcChat[] getAllGameTime() {
        return this._npcChatGameTime.values().toArray(
                new L1NpcChat[this._npcChatGameTime.size()]);
    }

    /**
     * 取得NPC出现时的喊话
     * 
     * @param i
     *            NPCID
     * @return
     */
    public L1NpcChat getTemplateAppearance(final int i) {
        return this._npcChatAppearance.get(new Integer(i));
    }

    /**
     * 取得NPC死亡时的喊话
     * 
     * @param i
     *            NPCID
     * @return
     */
    public L1NpcChat getTemplateDead(final int i) {
        return this._npcChatDead.get(new Integer(i));
    }

    /**
     * 取得NPC定时喊话
     * 
     * @param i
     *            NPCID
     * @return
     */
    public L1NpcChat getTemplateGameTime(final int i) {
        return this._npcChatGameTime.get(new Integer(i));
    }

    /**
     * 取得NPC取消隐藏状态时的喊话
     * 
     * @param i
     *            NPCID
     * @return
     */
    public L1NpcChat getTemplateHide(final int i) {
        return this._npcChatHide.get(new Integer(i));
    }
}
