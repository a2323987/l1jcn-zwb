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
package com.lineage.server.storage.mysql;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TimerTask;

import com.lineage.Config;
import com.lineage.L1Message;
import com.lineage.server.utils.SystemUtil;
import com.lineage.server.utils.UnZipUtil;

/**
 * MySQL dump 备份程序
 * 
 * @author L1J-TW-99nets
 */
public class MysqlAutoBackup extends TimerTask {
    private static MysqlAutoBackup _instance;
    private static final String Username = Config.DB_LOGIN;
    private static final String Passwords = Config.DB_PASSWORD;
    private static String FilenameEx = "";
    private static String GzipCmd = "";
    private static String Database = null;
    private static File dir = new File(".\\DbBackup\\");
    private static boolean GzipUse = Config.CompressGzip;
    private static boolean done = false;
    private static String os = SystemUtil.gerOs();
    private static String osArch = SystemUtil.getOsArchitecture();

    /**
     * 负责检查Gzip.exe是否安装
     * 
     * @param SystemRoot
     */
    private static void checkGzip(final String SystemRoot) {
        System.out.println("[MySQL]checking gzip.exe is installed or not...");
        File gzip = new File(SystemRoot + "\\gzip.exe");
        if (gzip.exists()) {
            System.out.println("mysql auto backup is running...ok!");
        } else {
            System.err.println("[MySQL]Gzip.exe不存在，系统正在处理中...");
            gzip = new File(".\\docs\\gzip124xN.zip");
            UnZipUtil.unZip(gzip.getAbsolutePath(), SystemRoot);
        }
    }

    /**
     * @return database name
     */
    private static String DatabaseName() {
        StringTokenizer sk = new StringTokenizer(Config.DB_URL, "/");
        sk.nextToken();
        sk.nextToken();
        sk = new StringTokenizer(sk.nextToken(), "?");
        Database = sk.nextToken();
        return Database;
    }

    public static MysqlAutoBackup getInstance() {
        if (_instance == null) {
            _instance = new MysqlAutoBackup();
        }
        return _instance;
    }

    public MysqlAutoBackup() {
        L1Message.getInstance();
        Database = DatabaseName();
        if (!dir.isDirectory()) {
            dir.mkdir();
        }

        // 压缩是否开启
        GzipCmd = GzipUse ? " | gzip" : "";
        FilenameEx = GzipUse ? ".sql.gz" : ".sql";

        // 检查gzip.exe是否安装 for Windows
        if (GzipUse && (os == "Windows") && !done) {
            if (osArch == "x86") {
                checkGzip("C:\\Windows\\System32");
            } else if (osArch == "x64") {
                checkGzip("C:\\Windows\\SysWOW64");
            }
            done = true;
        }
    }

    @Override
    public void run() {
        if (os == "Windows") {
            try {
                System.out.println("(MYSQL is backing now...)");
                /**
                 * mysqldump --user=[Username] --password=[password]
                 * [databasename] > [backupfile.sql]
                 */
                final StringBuilder exeText = new StringBuilder(
                        "mysqldump --user=");
                exeText.append(Username + " --password=");
                exeText.append(Passwords + " ");
                exeText.append(Database
                        + " --opt --skip-extended-insert --skip-quick");
                exeText.append(GzipCmd + " > ");
                exeText.append(dir.getAbsolutePath()
                        + new SimpleDateFormat("\\yyyy-MM-dd-kkmm")
                                .format(new Date()) + FilenameEx);
                try {
                    final Runtime rt = Runtime.getRuntime();
                    rt.exec("cmd /c " + exeText.toString());
                } finally {
                    System.out.println("(MYSQL is backed over.)" + "\n"
                            + L1Message.waitingforuser); // 等待玩家连线
                }
            } catch (final IOException ioe) {
                ioe.printStackTrace();

            } catch (final Exception e) {
                e.printStackTrace();
            }
        } else if (os == "Linux") {
            try {
                System.out.println("(MYSQL is backing now...)");
                /**
                 * mysqldump --user=[Username] --password=[password]
                 * [databasename] > [backupfile.sql]
                 */
                final StringBuilder exeText = new StringBuilder(
                        "mysqldump --user=");
                exeText.append(Username + " --password=");
                exeText.append(Passwords + " ");
                exeText.append(Database
                        + " --opt --skip-extended-insert --skip-quick");
                exeText.append(GzipCmd + " > ");
                exeText.append(dir.getAbsolutePath()
                        + new SimpleDateFormat("\\yyyy-MM-dd-kkmm")
                                .format(new Date()) + FilenameEx);
                try {
                    final Runtime rt = Runtime.getRuntime();
                    rt.exec(exeText.toString());
                } finally {
                    System.out.println("(MYSQL is backed over.)" + "\n"
                            + L1Message.waitingforuser); // 等待玩家连线
                }
            } catch (final IOException ioe) {
                ioe.printStackTrace();

            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }
}
