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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.lineage.server.GameServer;
import com.lineage.telnet.TelnetServer;

/**
 * l1j 伺服器启动
 */
public class Server {

    /** 纪录用 */
    private static Logger _log = Logger.getLogger(Server.class.getName());

    /** 纪录档的路径 */
    private static final String LOG_PROP = "./config/log.properties";

    /**
     * 主伺服器
     * 
     * @param args
     *            命令列参数
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        final File logFolder = new File("log");
        logFolder.mkdir();

        try {
            final InputStream is = new BufferedInputStream(new FileInputStream(
                    LOG_PROP));
            LogManager.getLogManager().readConfiguration(is);
            is.close();
        } catch (final IOException e) {
            _log.log(Level.SEVERE, "无法加载 " + LOG_PROP + " File.", e);
            System.exit(0);
        }
        try {
            Config.load();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            System.exit(0);
        }

        // L1DatabaseFactory初期设定
        L1DatabaseFactory.setDatabaseSettings(Config.DB_DRIVER, Config.DB_URL,
                Config.DB_LOGIN, Config.DB_PASSWORD);
        L1DatabaseFactory.getInstance();

        GameServer.getInstance().initialize();
        if (Config.TELNET_SERVER) {
            TelnetServer.getInstance().start();
        }
    }
}
