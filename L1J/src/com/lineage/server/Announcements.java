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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.utils.StreamUtil;
import com.lineage.server.utils.collections.Lists;

/**
 * 登陆公告
 */
public class Announcements {

    /** 提示信息 */
    private static Logger _log = Logger
            .getLogger(Announcements.class.getName());

    private static Announcements _instance;

    public static Announcements getInstance() {
        if (_instance == null) {
            _instance = new Announcements();
        }

        return _instance;
    }

    private final List<String> _announcements = Lists.newList();

    private Announcements() {
        this.loadAnnouncements();
    }

    /**
     * 全体公告
     */
    public void announceToAll(final String msg) {
        L1World.getInstance().broadcastServerMessage(msg);
    }

    /**
     * 加载公告
     */
    private void loadAnnouncements() {
        this._announcements.clear();
        final File file = new File("data/announcements.txt");
        if (file.exists()) {
            this.readFromDisk(file);
        } else {
            _log.config("data/announcements.txt 不存在");
        }
    }

    /**
     * 从磁盘中读取
     */
    private void readFromDisk(final File file) {
        LineNumberReader lnr = null;
        try {
            int i = 0;
            String line = null;
            lnr = new LineNumberReader(new FileReader(file));
            while ((line = lnr.readLine()) != null) {
                final StringTokenizer st = new StringTokenizer(line, "\n\r");
                if (st.hasMoreTokens()) {
                    final String announcement = st.nextToken();
                    this._announcements.add(announcement);

                    i++;
                }
            }

            _log.config("读取了  " + i + " 件公告");
        } catch (final FileNotFoundException e) {
            // 如果档案不存在
        } catch (final IOException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            StreamUtil.close(lnr);
        }
    }

    /**
     * 显示公告
     */
    public void showAnnouncements(final L1PcInstance showTo) {
        for (final String msg : this._announcements) {
            showTo.sendPackets(new S_SystemMessage(msg));
        }
    }
}
