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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.lineage.server.utils.StreamUtil;
import com.lineage.telnet.command.TelnetCommandExecutor;
import com.lineage.telnet.command.TelnetCommandResult;

/**
 * Telnet连接
 */
public class TelnetConnection {

    private class ConnectionThread extends Thread {
        private final Socket _socket;

        public ConnectionThread(final Socket sock) {
            this._socket = sock;
        }

        @Override
        public void run() {
            InputStreamReader isr = null;
            BufferedReader in = null;
            OutputStreamWriter osw = null;
            BufferedWriter out = null;
            try {
                isr = new InputStreamReader(this._socket.getInputStream());
                in = new BufferedReader(isr);
                osw = new OutputStreamWriter(this._socket.getOutputStream());
                out = new BufferedWriter(osw);

                String cmd = null;
                while (null != (cmd = in.readLine())) {
                    final TelnetCommandResult result = TelnetCommandExecutor
                            .getInstance().execute(cmd);
                    out.write(result.getCode() + " " + result.getCodeMessage()
                            + "\r\n");
                    out.write(result.getResult() + "\r\n");
                    out.flush();
                    // // for debug
                    // System.out.println(result.getCode() + " " +
                    // result.getCodeMessage());
                    // System.out.println(result.getResult());
                }
            } catch (final IOException e) {
                StreamUtil.close(isr, in);
                StreamUtil.close(osw, out);
            }
            try {
                this._socket.close();
            } catch (final IOException e) {
            }
        }
    }

    public TelnetConnection(final Socket sock) {
        new ConnectionThread(sock).start();
    }
}
