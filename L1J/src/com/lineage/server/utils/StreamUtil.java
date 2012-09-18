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
package com.lineage.server.utils;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 流控制
 */
public class StreamUtil {

    private static Logger _log = Logger.getLogger(StreamUtil.class.getName());

    /**
     * 关闭此流并释放与此流关联的所有系统资源。如果已经关闭该流，则调用此方法无效。
     * 
     * @param closeables
     */
    public static void close(final Closeable... closeables) {
        for (final Closeable c : closeables) {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (final IOException e) {
                _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            }
        }
    }
}
