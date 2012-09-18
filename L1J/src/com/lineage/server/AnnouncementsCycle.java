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
package com.lineage.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javolution.util.FastList;

import com.lineage.Config;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;

/**
 * 循环公告
 */
public class AnnouncementsCycle {

    /**
     * 处理广播字串任务
     */
    class AnnouncementsCycleTask implements Runnable {
        @Override
        public void run() {
            AnnouncementsCycle.this.scanfile();
            // 启用修改时间显示 - 〈yyyy.MM.dd〉
            if (AnnouncementsCycle.this.AnnounceTimeDisplay) {
                final SimpleDateFormat formatter = new SimpleDateFormat(
                        "yyyy.MM.dd");
                AnnouncementsCycle.this.ShowAnnouncementsCycle("〈"
                        + formatter.format(new Date(lastmodify)) + "〉");
            }
            final Iterator<String> iterator = AnnouncementsCycle.this.list
                    .listIterator();
            if (iterator.hasNext()) {
                AnnouncementsCycle.this.round %= AnnouncementsCycle.this.list
                        .size();
                AnnouncementsCycle.this
                        .ShowAnnouncementsCycle(AnnouncementsCycle.this.list
                                .get(AnnouncementsCycle.this.round));
                AnnouncementsCycle.this.round++;
            }
        }
    }

    int round = 0;

    private String line = null;

    private boolean firstboot = true;

    private final StringBuffer sb = new StringBuffer();

    private static AnnouncementsCycle _instance;

    /** 缓冲读取 */
    private static BufferedReader buf;

    /** announcementsCycle文件的位置 */
    private static File dir = new File("data/announceCycle.txt");

    /** 纪录上一次修改时间 */
    static long lastmodify = dir.lastModified();

    public static AnnouncementsCycle getInstance() {
        if (_instance == null) {
            _instance = new AnnouncementsCycle();
        }
        return _instance;
    }

    /** 在公告首显示公告修改时间 */
    final boolean AnnounceTimeDisplay = Config.Announcements_Cycle_Modify_Time;

    /** 容器 */
    List<String> list = new FastList<String>();

    private AnnouncementsCycle() {
        this.cycle();
    }

    private void cycle() {
        final AnnouncementsCycleTask task = new AnnouncementsCycleTask();
        GeneralThreadPool.getInstance().scheduleAtFixedRate(task, 100000,
                60000 * Config.Announcements_Cycle_Time); // 10分钟公告一次
    }

    /**
     * 确保announcementsCycle.txt存在
     * 
     * @throws IOException
     *             产生档案错误
     */
    private void fileEnsure() throws IOException {
        if (!dir.exists()) {
            dir.createNewFile();
        }
    }

    /**
     * 从announcementsCycle.txt将字串读入
     */
    void scanfile() {
        try {
            this.fileEnsure(); // 先确保档案存在
            if ((dir.lastModified() > lastmodify) || this.firstboot) { // 如果有修改过
                this.list.clear(); // 清空容器
                buf = new BufferedReader(new InputStreamReader(
                        new FileInputStream(dir)));
                while ((this.line = buf.readLine()) != null) {
                    if (this.line.startsWith("#") || this.line.isEmpty()) {
                        continue;
                    }
                    this.sb.delete(0, this.sb.length()); // 清空 buffer [未来扩充用]
                    this.list.add(this.line);
                }
                lastmodify = dir.lastModified(); // 回存修改时间
            } else {
                // 档案没修改过，不做任何事。
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buf.close();
                this.firstboot = false;
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 把字串广播到伺服器上
     */
    void ShowAnnouncementsCycle(final String announcement) {
        final Collection<L1PcInstance> AllPlayer = L1World.getInstance()
                .getAllPlayers();
        for (final L1PcInstance pc : AllPlayer) {
            pc.sendPackets(new S_SystemMessage(announcement));
        }
    }
}
