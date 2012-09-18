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
package com.lineage.telnet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.lineage.Config;

/**
 * Telnet服务器
 */
public class TelnetServer {

    private class ServerThread extends Thread {
        ServerSocket _sock;

        public ServerThread() {
            // TODO Auto-generated constructor stub
        }

        @Override
        public void run() {
            try {
                this._sock = new ServerSocket(Config.TELNET_SERVER_PORT);

                while (true) {
                    final Socket sock = this._sock.accept();
                    new TelnetConnection(sock);
                }
            } catch (final IOException e) {
            }
            try {
                this._sock.close();
            } catch (final IOException e) {
            }
        }
    }

    private static TelnetServer _instance;

    public static TelnetServer getInstance() {
        if (_instance == null) {
            _instance = new TelnetServer();
        }
        return _instance;
    }

    private TelnetServer() {
    }

    public void start() {
        new ServerThread().start();
    }
}
