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
package com.lineage.server.templates;

/**
 * NPC说话设置 (出现或死亡时)
 */
public class L1NpcChat {
    /** NPC编号 */
    private int _npcId;
    /** 说话顺序 0:出现时、1:死亡时 */
    private int _chatTiming;
    /** 说话延迟时间 (毫秒) */
    private int _startDelayTime;
    /** 说话1 (desc-h.tbl 字串编号) */
    private String _chatId1;
    /** 说话2 (desc-h.tbl 字串编号) */
    private String _chatId2;
    /** 说话3 (desc-h.tbl 字串编号) */
    private String _chatId3;
    /** 说话4 (desc-h.tbl 字串编号) */
    private String _chatId4;
    /** 说话5 (desc-h.tbl 字串编号) */
    private String _chatId5;
    /** 说话时间间隔 (毫秒) */
    private int _chatInterval;
    /** 说话显示方式 0:不大喊、1:大喊 */
    private boolean _isShout;
    /** 说话显示方式 0:不全体、1:全体 */
    private boolean _isWorldChat;
    /** 说话是否重复 0:不重复、1:重复 */
    private boolean _isRepeat;
    /** 说话重复间隔 */
    private int _repeatInterval;
    /** 游戏时间 */
    private int _gameTime;

    public L1NpcChat() {
    }

    /**
     * 取得说话1 (desc-h.tbl 字串编号)
     * 
     * @return
     */
    public String getChatId1() {
        return this._chatId1;
    }

    /**
     * 取得说话2 (desc-h.tbl 字串编号)
     * 
     * @return
     */
    public String getChatId2() {
        return this._chatId2;
    }

    /**
     * 取得说话3 (desc-h.tbl 字串编号)
     * 
     * @return
     */
    public String getChatId3() {
        return this._chatId3;
    }

    /**
     * 取得说话4 (desc-h.tbl 字串编号)
     * 
     * @return
     */
    public String getChatId4() {
        return this._chatId4;
    }

    /**
     * 取得说话5 (desc-h.tbl 字串编号)
     * 
     * @return
     */
    public String getChatId5() {
        return this._chatId5;
    }

    /**
     * 取得说话时间间隔 (毫秒)
     * 
     * @return
     */
    public int getChatInterval() {
        return this._chatInterval;
    }

    /**
     * 取得说话顺序 0:出现时、1:死亡时
     * 
     * @return
     */
    public int getChatTiming() {
        return this._chatTiming;
    }

    /**
     * 取得游戏时间
     * 
     * @return
     */
    public int getGameTime() {
        return this._gameTime;
    }

    /**
     * 取得NPC编号
     * 
     * @return
     */
    public int getNpcId() {
        return this._npcId;
    }

    /**
     * 取得说话重复间隔
     * 
     * @return
     */
    public int getRepeatInterval() {
        return this._repeatInterval;
    }

    /**
     * 取得说话延迟时间 (毫秒)
     * 
     * @return
     */
    public int getStartDelayTime() {
        return this._startDelayTime;
    }

    /**
     * 说话是否重复 0:不重复、1:重复
     * 
     * @return
     */
    public boolean isRepeat() {
        return this._isRepeat;
    }

    /**
     * 说话显示方式 0:不大喊、1:大喊
     * 
     * @return
     */
    public boolean isShout() {
        return this._isShout;
    }

    /**
     * 说话显示方式 0:不全体、1:全体
     * 
     * @return
     */
    public boolean isWorldChat() {
        return this._isWorldChat;
    }

    /**
     * 设置说话1 (desc-h.tbl 字串编号)
     * 
     * @param s
     */
    public void setChatId1(final String s) {
        this._chatId1 = s;
    }

    /**
     * 设置说话2 (desc-h.tbl 字串编号)
     * 
     * @param s
     */
    public void setChatId2(final String s) {
        this._chatId2 = s;
    }

    /**
     * 设置说话3 (desc-h.tbl 字串编号)
     * 
     * @param s
     */
    public void setChatId3(final String s) {
        this._chatId3 = s;
    }

    /**
     * 设置说话4 (desc-h.tbl 字串编号)
     * 
     * @param s
     */
    public void setChatId4(final String s) {
        this._chatId4 = s;
    }

    /**
     * 设置说话5 (desc-h.tbl 字串编号)
     * 
     * @param s
     */
    public void setChatId5(final String s) {
        this._chatId5 = s;
    }

    /**
     * 设置说话时间间隔 (毫秒)
     * 
     * @param i
     */
    public void setChatInterval(final int i) {
        this._chatInterval = i;
    }

    /**
     * 设置说话顺序 0:出现时、1:死亡时
     * 
     * @param i
     */
    public void setChatTiming(final int i) {
        this._chatTiming = i;
    }

    /**
     * 设置游戏时间
     * 
     * @param i
     */
    public void setGameTime(final int i) {
        this._gameTime = i;
    }

    /**
     * 设置NPC编号
     * 
     * @param i
     */
    public void setNpcId(final int i) {
        this._npcId = i;
    }

    /**
     * 设置说话是否重复 0:不重复、1:重复
     * 
     * @param flag
     */
    public void setRepeat(final boolean flag) {
        this._isRepeat = flag;
    }

    /**
     * 设置说话重复间隔
     * 
     * @param i
     */
    public void setRepeatInterval(final int i) {
        this._repeatInterval = i;
    }

    /**
     * 设置说话显示方式 0:不大喊、1:大喊
     * 
     * @param flag
     */
    public void setShout(final boolean flag) {
        this._isShout = flag;
    }

    /**
     * 设置说话延迟时间 (毫秒)
     * 
     * @param i
     */
    public void setStartDelayTime(final int i) {
        this._startDelayTime = i;
    }

    /**
     * 设置说话显示方式 0:不全体、1:全体
     * 
     * @param flag
     */
    public void setWorldChat(final boolean flag) {
        this._isWorldChat = flag;
    }

}
