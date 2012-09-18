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
package com.lineage.server.serverpackets;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.List;
import java.util.StringTokenizer;

import com.lineage.server.Opcodes;
import com.lineage.server.utils.collections.Lists;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 公告视窗 (点击登入后)
 */
public class S_CommonNews extends ServerBasePacket {

    private List<String> _announcements;

    public S_CommonNews() {
        this._announcements = Lists.newList();
        this.loadAnnouncements();
        this.writeC(Opcodes.S_OPCODE_COMMONNEWS);
        String message = "";
        for (int i = 0; i < this._announcements.size(); i++) {
            message = (new StringBuilder()).append(message)
                    .append(this._announcements.get(i)).append("\n").toString();
        }
        this.writeS(message);
    }

    /**
     * 公告视窗 (点击登入后)
     * 
     * @param s
     */
    public S_CommonNews(final String s) {
        this.writeC(Opcodes.S_OPCODE_COMMONNEWS);
        this.writeS(s);
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }

    @Override
    public String getType() {
        return "[S] S_CommonNews";
    }

    /** 载入公告 */
    private void loadAnnouncements() {
        this._announcements.clear();
        final File file = new File("data/announcements.txt");
        if (file.exists()) {
            this.readFromDisk(file);
        }
    }

    /** 读取 */
    private void readFromDisk(final File file) {
        LineNumberReader lnr = null;
        try {
            String line = null;
            lnr = new LineNumberReader(new FileReader(file));
            do {
                if ((line = lnr.readLine()) == null) {
                    break;
                }
                final StringTokenizer st = new StringTokenizer(line, "\n\r");
                if (st.hasMoreTokens()) {
                    final String announcement = st.nextToken();
                    this._announcements.add(announcement);
                } else {
                    this._announcements.add(" ");
                }
            } while (true);
        } catch (final Exception e) {
        }
    }

}
