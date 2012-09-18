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
package com.lineage.server.datatables;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.lineage.server.model.L1Object;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.npc.action.L1NpcAction;
import com.lineage.server.model.npc.action.L1NpcXmlParser;
import com.lineage.server.utils.FileUtil;
import com.lineage.server.utils.PerformanceTimer;
import com.lineage.server.utils.collections.Lists;

/**
 * NPC动作(对话)资料表
 */
public class NpcActionTable {

    private static Logger _log = Logger.getLogger(NpcActionTable.class
            .getName());

    private static NpcActionTable _instance;

    public static NpcActionTable getInstance() {
        return _instance;
    }

    public static void load() {
        try {
            final PerformanceTimer timer = new PerformanceTimer();
            System.out.print("╠》正在读取 NpcAction...");
            _instance = new NpcActionTable();
            System.out.println("完成!\t\t耗时: " + timer.get() + "\t毫秒");
        } catch (final Exception e) {
            _log.log(Level.SEVERE, "无法加载NpcAction", e);
            System.exit(0);
        }
    }

    private final List<L1NpcAction> _actions = Lists.newList();

    private final List<L1NpcAction> _talkActions = Lists.newList();

    private NpcActionTable() throws Exception {
        final File usersDir = new File("./data/xml/NpcActions/users/");
        if (usersDir.exists()) {
            this.loadDirectoryActions(usersDir);
        }
        this.loadDirectoryActions(new File("./data/xml/NpcActions/"));
    }

    public L1NpcAction get(final L1PcInstance pc, final L1Object obj) {
        for (final L1NpcAction action : this._talkActions) {
            if (action.acceptsRequest("", pc, obj)) {
                return action;
            }
        }
        return null;
    }

    public L1NpcAction get(final String actionName, final L1PcInstance pc,
            final L1Object obj) {
        for (final L1NpcAction action : this._actions) {
            if (action.acceptsRequest(actionName, pc, obj)) {
                return action;
            }
        }
        return null;
    }

    private void loadAction(final File file) throws Exception {
        this._actions.addAll(this.loadAction(file, "NpcActionList"));
    }

    private List<L1NpcAction> loadAction(final File file, final String nodeName)

    throws ParserConfigurationException, SAXException, IOException {
        final DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder();
        final Document doc = builder.parse(file);

        if (!doc.getDocumentElement().getNodeName().equalsIgnoreCase(nodeName)) {
            return Lists.newList();
        }
        return L1NpcXmlParser.listActions(doc.getDocumentElement());
    }

    private void loadDirectoryActions(final File dir) throws Exception {
        for (final String file : dir.list()) {
            final File f = new File(dir, file);
            if (FileUtil.getExtension(f).equalsIgnoreCase("xml")) {
                this.loadAction(f);
                this.loadTalkAction(f);
            }
        }
    }

    private void loadTalkAction(final File file) throws Exception {
        this._talkActions.addAll(this.loadAction(file, "NpcTalkActionList"));
    }
}
