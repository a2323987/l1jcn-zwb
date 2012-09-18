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
package com.lineage;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.lineage.server.utils.Internationalization.messages;

/**
 * 国际化的英文是Internationalization 因为单字中总共有18个字母，简称I18N， 目的是让应用程式可以应地区不同而显示不同的讯息。
 */
public class L1Message {

    private static L1Message _instance;

    public static L1Message getInstance() {
        if (_instance == null) {
            _instance = new L1Message();
        }
        return _instance;
    }

    ResourceBundle resource;

    /**
     * static 变数
     */
    public static String memoryUse;

    public static String onGroundItem;

    public static String secondsDelete;
    public static String deleted;
    public static String ver;
    public static String settingslist;
    public static String exp;
    public static String x;
    public static String level;
    public static String justice;
    public static String karma;
    public static String dropitems;
    public static String dropadena;
    public static String enchantweapon;
    public static String enchantarmor;
    public static String chatlevel;
    public static String nonpvpNo;
    public static String nonpvpYes;
    public static String memory;
    public static String maxplayer;
    public static String player;
    public static String waitingforuser;
    public static String from;
    public static String attempt;
    public static String setporton;
    public static String initialfinished;

    private L1Message() {
        try {
            this.resource = ResourceBundle.getBundle(messages.class.getName());
            this.initLocaleMessage();
        } catch (final MissingResourceException mre) {
            mre.printStackTrace();
        }
    }

    /**
     * 简短化变数名词
     */
    public void initLocaleMessage() {
        memoryUse = this.resource.getString("com.lineage.memoryUse");
        memory = this.resource.getString("com.lineage.memory");
        onGroundItem = this.resource
                .getString("com.lineage.server.model.onGroundItem");
        secondsDelete = this.resource
                .getString("com.lineage.server.model.seconds");
        deleted = this.resource.getString("com.lineage.server.model.deleted");
        ver = this.resource.getString("com.lineage.server.GameServer.ver");
        settingslist = this.resource
                .getString("com.lineage.server.GameServer.settingslist");
        exp = this.resource.getString("com.lineage.server.GameServer.exp");
        x = this.resource.getString("com.lineage.server.GameServer.x");
        level = this.resource.getString("com.lineage.server.GameServer.level");
        justice = this.resource
                .getString("com.lineage.server.GameServer.justice");
        karma = this.resource.getString("com.lineage.server.GameServer.karma");
        dropitems = this.resource
                .getString("com.lineage.server.GameServer.dropitems");
        dropadena = this.resource
                .getString("com.lineage.server.GameServer.dropadena");
        enchantweapon = this.resource
                .getString("com.lineage.server.GameServer.enchantweapon");
        enchantarmor = this.resource
                .getString("com.lineage.server.GameServer.enchantarmor");
        chatlevel = this.resource
                .getString("com.lineage.server.GameServer.chatlevel");
        nonpvpNo = this.resource
                .getString("com.lineage.server.GameServer.nonpvp1");
        nonpvpYes = this.resource
                .getString("com.lineage.server.GameServer.nonpvp2");
        maxplayer = this.resource
                .getString("com.lineage.server.GameServer.maxplayer");
        player = this.resource
                .getString("com.lineage.server.GameServer.player");
        waitingforuser = this.resource
                .getString("com.lineage.server.GameServer.waitingforuser");
        from = this.resource.getString("com.lineage.server.GameServer.from");
        attempt = this.resource
                .getString("com.lineage.server.GameServer.attempt");
        setporton = this.resource
                .getString("com.lineage.server.GameServer.setporton");
        initialfinished = this.resource
                .getString("com.lineage.server.GameServer.initialfinished");
    }
}
