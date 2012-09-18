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

import java.io.File;

/**
 * 系统控制
 */
public class SystemUtil {
    /**
     * 取得目前的作业系统
     * 
     * @return Linux or Windows
     */
    public static String gerOs() {
        String Os = "";
        final String OsName = System.getProperty("os.name");
        if (OsName.toLowerCase().indexOf("windows") >= 0) {
            Os = "Windows";
        } else if (OsName.toLowerCase().indexOf("linux") >= 0) {
            Os = "Linux";
        }
        return Os;
    }

    /**
     * 得知作业系统是几位元 Only for Windows
     * 
     * @return x86 or x64
     */
    public static String getOsArchitecture() {
        final String x64_System = "C:\\Windows\\SysWOW64";
        String result;
        final File dir = new File(x64_System);
        if (dir.exists()) {
            result = "x64";
        } else {
            result = "x86";
        }
        return result;
    }

    /**
     * 返回兆字节的可用系统堆大小。<br>
     * 栈的大小是不包括在这个值。
     * 
     * @return 使用中的堆大小
     */
    public static long getUsedMemoryMB() {
        return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime()
                .freeMemory()) / 1024L / 1024L;
    }
}
