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
package com.lineage.console;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * 控制台日志格式化
 */
public class ConsoleLogFormatter extends Formatter {
    /*
     * (non-Javadoc)
     * 
     * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
     */
    public ConsoleLogFormatter() {
    }

    @Override
    public String format(final LogRecord record) {
        final StringBuffer output = new StringBuffer();
        output.append(record.getMessage());
        output.append("\r\n");

        if (record.getThrown() != null) {
            try {
                final StringWriter sw = new StringWriter();
                final PrintWriter pw = new PrintWriter(sw);
                record.getThrown().printStackTrace(pw);
                pw.close();
                output.append(sw.toString());
                output.append("\r\n");
            } catch (final Exception ex) {
            }
        }
        return output.toString();
    }
}
